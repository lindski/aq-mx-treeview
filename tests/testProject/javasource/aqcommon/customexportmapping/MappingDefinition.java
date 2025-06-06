package aqcommon.customexportmapping;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MappingDefinition {
	@JsonProperty("rootObjectName")
	public String RootObjectName;
	
	@JsonProperty("mappings")
	public List<Mapping> Mappings = new ArrayList<Mapping>();
}
