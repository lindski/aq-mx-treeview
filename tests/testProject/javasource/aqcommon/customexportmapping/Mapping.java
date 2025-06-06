package aqcommon.customexportmapping;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mapping {
	@JsonProperty("id")
	public String Id;
	
	@JsonProperty("parentId")
	public String ParentId;
	
	@JsonProperty("nodePropertyName")
	public String NodePropertyName;
	
	@JsonProperty("properties")
	public List<Property> Properties = new ArrayList<Property>();
	
	@JsonProperty("association")
	public Association Association;
}
