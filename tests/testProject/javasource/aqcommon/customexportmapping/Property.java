package aqcommon.customexportmapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {
	@JsonProperty("propertyName")
	public String PropertyName;
	
	@JsonProperty("attributeName")
	public String AttributeName;
}
