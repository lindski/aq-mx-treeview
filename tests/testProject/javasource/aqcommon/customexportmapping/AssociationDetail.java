package aqcommon.customexportmapping;

import com.mendix.systemwideinterfaces.core.meta.IMetaAssociation;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaAssociation.AssociationOwner;
import com.mendix.systemwideinterfaces.core.meta.IMetaAssociation.AssociationType;

public class AssociationDetail{
	private IMetaAssociation _mxMetaAssociation = null;
	private IMetaObject _mxMetaObjectTarget = null;
	private Boolean _isArray = false;
	private Boolean _isChild = false;
	private String _associationCompleteName = null;
	private String _associationName = null;
	
	public AssociationDetail(IMetaObject mxMetaObjectParent, String associationCompleteName) throws Exception {
		IMetaAssociation childAssociation = mxMetaObjectParent.getMetaAssociationChild(associationCompleteName);
		IMetaAssociation parentAssociation = mxMetaObjectParent.getMetaAssociationParent(associationCompleteName);
		//_associationCompleteName = associationCompleteName;
		
		updateMetaAssociation(childAssociation, parentAssociation);
		updateMetaObjectTarget();
		updateIsArray();
		
		_associationCompleteName = _mxMetaAssociation.getName();
		_associationName = _associationCompleteName.split("\\.")[1];
	}
	
	public IMetaObject getMxMetaObjectTarget() {
		return _mxMetaObjectTarget;
	}
	
	public Boolean getIsArray() {
		return _isArray;
	}
	
	public String getAssociationCompleteName() {
		return _associationCompleteName;
	}
	
	public String getAssociationName() {
		return _associationName;
	}

	private void updateMetaAssociation(IMetaAssociation childAssociation, IMetaAssociation parentAssociation) {
		if( childAssociation != null) {
			_isChild = true;
			_mxMetaAssociation = childAssociation;
		}
		else if ( parentAssociation != null) {
			_isChild = false;
			_mxMetaAssociation = parentAssociation;
		}
	}
	
	private void updateMetaObjectTarget() {
		if(_isChild) {
			_mxMetaObjectTarget = _mxMetaAssociation.getParent();
		} else {
			_mxMetaObjectTarget = _mxMetaAssociation.getChild();
		}
	}
	
	// works out whether the target object for an association points to an array of objects or a single object
	private void updateIsArray() throws Exception {
		if(_mxMetaAssociation.getType() == AssociationType.REFERENCE && _mxMetaAssociation.getOwner() == AssociationOwner.BOTH) {
			_isArray = false;				
		}
		else if(_mxMetaAssociation.getType() == AssociationType.REFERENCESET && _mxMetaAssociation.getOwner() == AssociationOwner.BOTH) {
			_isArray = true;
		}
		else if(_mxMetaAssociation.getType() == AssociationType.REFERENCE && _mxMetaAssociation.getOwner() == AssociationOwner.DEFAULT) {
			// array for a child association, single object for a parent
			_isArray = _isChild;
		}
		else if(_mxMetaAssociation.getType() == AssociationType.REFERENCESET && _mxMetaAssociation.getOwner() == AssociationOwner.DEFAULT) {
			if(_isChild) {
				throw new Exception("A ReferenceSet with an owner of 'Default' for a child association cannot be used here");
			}
			_isArray = true;				
		}
	}
}
