package aqcommon.customexportmapping;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive;

public class JsonTransformer {
	
	private IMendixObject _objectToTransform = null;
	private IMetaObject _metaObjectToTransform = null;
	private ObjectMapper _objectMapper = null;
	private MappingDefinition _mappingDefinition = null;
	private IContext _context = null;
	private DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	private ILogNode _logger = Core.getLogger(this.getClass().getName());
	private static final String DEFAULTVALUE_STRING = "abcdef";
	private static final String DEFAULTVALUE_NUMBER = "123456";
	private static final String DEFAULTVALUE_DECIMAL = "654.321";
	private static final String DEFAULTVALUE_BOOLEAN = "true";
	private static final String DEFAULTVALUE_DATETIME = "2023-01-14T00:00:00";

	public JsonTransformer(String mappingDefinition) throws Exception
	{
		this(mappingDefinition, null, null);	
	}
	
	public JsonTransformer(String mappingDefinition, IMendixObject objectToTransform, IContext context) throws Exception {
		_objectToTransform = objectToTransform;
		_context = context;
		
		_objectMapper = JsonMapper
				.builder()
				.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
				.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
				.build();
		_objectMapper.setDateFormat(_dateFormat);
		
		_mappingDefinition = _objectMapper.readValue(mappingDefinition, MappingDefinition.class);
		
		// if we have an object to transform validate the type matches the definition
		// no object means we use the object name from the definition to generate based on metadata
		if(_mappingDefinition.RootObjectName == null || _mappingDefinition.RootObjectName.equals("")) {
			throw new Exception("No 'rootObjectName' node found in mapping definition");
		}
		
		if(objectToTransform != null) {
			if(!objectToTransform.getType().equals(_mappingDefinition.RootObjectName)) {
				throw new Exception("Provided object does not match the value of 'rootObjectName' in the mapping definition");
			}
		}
		
		_metaObjectToTransform = Core.getMetaObject(_mappingDefinition.RootObjectName);
		
		if(_metaObjectToTransform == null) {
			throw new Exception(String.format("Meta object not found for '%s'!", _mappingDefinition.RootObjectName));
		}
	}
	
	public String transform() throws Exception {
		List<Mapping> parentMappings = _mappingDefinition.Mappings.stream().filter(m->m.ParentId == null || m.ParentId.equals("")).collect(Collectors.toList());
		
		ObjectNode rootOutputNode = _objectMapper.createObjectNode();
		for(Mapping parentMapping : parentMappings) {
			if(_objectToTransform == null) {
				processNodeForMetaObject(_metaObjectToTransform, parentMapping, rootOutputNode);
			}
			else {
				processNodeForMendixObject(_objectToTransform, parentMapping, rootOutputNode);
			}
		}
		
		return _objectMapper.writeValueAsString(rootOutputNode); 
	}
	
	private void processNodeForMendixObject(IMendixObject mxMendixObject, Mapping mapping, ObjectNode parentNode) throws Exception {
		// check if we have an association defined
		AssociationDetail associationDetail = null;
		if(mapping.Association != null) {
			associationDetail = new AssociationDetail(mxMendixObject.getMetaObject(), mapping.Association.AssociationName);
			List<IMendixObject> mxAssociatedObjectList = Core.retrieveByPath(_context, mxMendixObject, associationDetail.getAssociationCompleteName());
			if(associationDetail.getIsArray()) {
				// retrieve a list
				ArrayNode innerArrayNode = _objectMapper.createArrayNode();
				for(IMendixObject mxAssociatedObject : mxAssociatedObjectList) {
					innerArrayNode.add(getNodeForMendixObject(mapping, mapping.Properties, mxAssociatedObject));
				}
				parentNode.set(mapping.NodePropertyName, innerArrayNode);				
			}
			else {
				_logger.debug(String.format("Association Complete Name: %s", associationDetail.getAssociationCompleteName()));
				_logger.debug(String.format("Current Object type: %s", mxMendixObject.getType()));
				
				IMendixIdentifier mxIdentifier = mxMendixObject.getValue(_context, associationDetail.getAssociationCompleteName());
				_logger.debug(String.format("MxIdentifier: %s", mxIdentifier == null ? "null" : mxIdentifier.toString()));
				
				if(mxIdentifier != null) {
					IMendixObject mxSingleObject = Core.retrieveId(_context, mxIdentifier);
					
					parentNode.set(mapping.NodePropertyName, getNodeForMendixObject(mapping, mapping.Properties, mxSingleObject));
				}
			}
		}
		else {
			parentNode.set(mapping.NodePropertyName, getNodeForMendixObject(mapping, mapping.Properties, mxMendixObject));
		}
	}

	private ObjectNode getNodeForMendixObject(Mapping mapping, List<Property> properties, IMendixObject mxObject)
			throws Exception {
		ObjectNode node = _objectMapper.createObjectNode();
		addAttributeDetailsToNodeForMendixObject(mxObject, properties, node);
		List<Mapping> childMappings = _mappingDefinition.Mappings.stream()
				.filter(m-> m.ParentId != null && m.ParentId.equals(mapping.Id))
				.collect(Collectors.toList());
		
		for(Mapping childMapping : childMappings) {
			processNodeForMendixObject(mxObject, childMapping, node);
		}
		return node;
	}
	
	private void addAttributeDetailsToNodeForMendixObject(IMendixObject mxMendixObject, List<Property> properties, ObjectNode parentNode) {
		
		Map<String, String> propertyMap = getPropertyMap(mxMendixObject.getMetaObject(), properties);
		
		for (var entry : propertyMap.entrySet()) {
			_logger.debug(String.format("Property Map, key: %s, value: %s", entry.getKey(), entry.getValue()));
			// need to get the values from the object!
			IMetaPrimitive mxMetaPrimitive = mxMendixObject.getMetaObject().getMetaPrimitive(entry.getKey());
			
			_logger.debug(String.format("Meta Primitive type: %s", mxMetaPrimitive.getType()));
			Object attributeValue = mxMendixObject.getValue(_context, entry.getKey());
			_logger.debug(String.format("Attribute value: %s", attributeValue));
			switch(mxMetaPrimitive.getType()) {
				case AutoNumber:
					parentNode.put(entry.getValue(), attributeValue == null ? null : (Long)attributeValue);
					break;
				case Binary:
					// not yet supported
					break;
				case Boolean:
					parentNode.put(entry.getValue(), (Boolean)mxMendixObject.getValue(_context, entry.getKey()));				
					break;
				case DateTime:
					String formattedDate = attributeValue == null ? null : _dateFormat.format((Date)attributeValue);
					parentNode.put(entry.getValue(), formattedDate);
					break;
				case Decimal:
					parentNode.put(entry.getValue(), attributeValue == null ? null : (BigDecimal)attributeValue);	
					break;
				case Enum:
					parentNode.put(entry.getValue(), attributeValue == null ? null : attributeValue.toString());
					break;
				case HashString:
					// not yet supported
					break;
				case Integer:
					parentNode.put(entry.getValue(), attributeValue == null ? null : (Integer)attributeValue);
					break;
				case Long:
					parentNode.put(entry.getValue(), attributeValue == null ? null : (Long)attributeValue);
					break;
				case String:
					parentNode.put(entry.getValue(), attributeValue == null ? null : attributeValue.toString());
					break;
				default:
					break;			
			}
		}
	}
	
	private void processNodeForMetaObject(IMetaObject mxMetaObject, Mapping mapping, ObjectNode parentNode) throws Exception {
		
		// check if we have an association defined
		AssociationDetail associationDetail = null;
		if(mapping.Association != null) {
			associationDetail = new AssociationDetail(mxMetaObject, mapping.Association.AssociationName);
			// switch the meta object context
			mxMetaObject = 	associationDetail.getMxMetaObjectTarget();	
		}
		
		ObjectNode innerNode = _objectMapper.createObjectNode();
		// add attributes here
		addAttributeDetailsToNodeForMetaObject(mxMetaObject, mapping.Properties, innerNode);
		
		// recursive call for any child nodes
		List<Mapping> childMappings = _mappingDefinition.Mappings.stream()
				.filter(m->m.ParentId != null && m.ParentId.equals(mapping.Id))
				.collect(Collectors.toList());
		
		for(Mapping childMapping : childMappings) {
			processNodeForMetaObject(mxMetaObject, childMapping, innerNode);
		}		
		
		// once we've processed all levels add the result to our parent		
		Boolean innerNodeIsArray = (associationDetail != null && associationDetail.getIsArray());
		
		if(innerNodeIsArray) {
			ArrayNode innerArrayNode = _objectMapper.createArrayNode();				
			innerArrayNode.add(innerNode);					
			parentNode.set(mapping.NodePropertyName, innerArrayNode);
		}
		else {
			parentNode.set(mapping.NodePropertyName, innerNode);
		}		
	}
	
	private void addAttributeDetailsToNodeForMetaObject(IMetaObject mxMetaObject, List<Property> properties, ObjectNode parentNode) {
			
			Map<String, String> propertyMap = getPropertyMap(mxMetaObject, properties);
			
			for (var entry : propertyMap.entrySet()) {
				
				IMetaPrimitive mxMetaPrimitive = mxMetaObject.getMetaPrimitive(entry.getKey());
				
				switch(mxMetaPrimitive.getType()) {
					case AutoNumber:
						parentNode.put(entry.getValue(), DEFAULTVALUE_NUMBER);
						break;
					case Binary:
						// not yet supported
						break;
					case Boolean:
						parentNode.put(entry.getValue(), DEFAULTVALUE_BOOLEAN);				
						break;
					case DateTime:
						parentNode.put(entry.getValue(), DEFAULTVALUE_DATETIME);
						break;
					case Decimal:
						parentNode.put(entry.getValue(), DEFAULTVALUE_DECIMAL);	
						break;
					case Enum:
						parentNode.put(entry.getValue(), DEFAULTVALUE_STRING);
						break;
					case HashString:
						// not yet supported
						break;
					case Integer:
						parentNode.put(entry.getValue(), DEFAULTVALUE_NUMBER);
						break;
					case Long:
						parentNode.put(entry.getValue(), DEFAULTVALUE_NUMBER);
						break;
					case String:
						parentNode.put(entry.getValue(), DEFAULTVALUE_STRING);
						break;
					default:
						break;			
				}
			}
	}

	private Map<String, String> getPropertyMap(IMetaObject mxMetaObject, List<Property> properties){
		
		Map<String, String> propertyMap = new HashMap<String, String>();
		
		if(properties == null || properties.size() <= 0) {
			for(IMetaPrimitive mxMetaPrimitive : mxMetaObject.getMetaPrimitives()) {
				propertyMap.put(mxMetaPrimitive.getName(), mxMetaPrimitive.getName()); // we could translate to camel case or snake case for props here if we wanted
			}
		}
		else {
			properties.stream()
			.forEach(m-> {
				String propertyName = m.PropertyName != null && !m.PropertyName.equals("") ? m.PropertyName : m.AttributeName;
				propertyMap.put(m.AttributeName, propertyName);
			});
		}	
		
		return propertyMap;
	}
}
