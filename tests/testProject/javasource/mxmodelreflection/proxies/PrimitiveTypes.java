// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mxmodelreflection.proxies;

public enum PrimitiveTypes
{
	AutoNumber(new java.lang.String[][] { new java.lang.String[] { "en_US", "Auto number" }, new java.lang.String[] { "nl_NL", "Autonummer" }, new java.lang.String[] { "en_GB", "Auto number" }, new java.lang.String[] { "en_ZA", "Auto number" } }),
	BooleanType(new java.lang.String[][] { new java.lang.String[] { "en_US", "Boolean" }, new java.lang.String[] { "nl_NL", "Boolean" }, new java.lang.String[] { "en_GB", "Boolean" }, new java.lang.String[] { "en_ZA", "Boolean" } }),
	DateTime(new java.lang.String[][] { new java.lang.String[] { "en_US", "DateTime" }, new java.lang.String[] { "nl_NL", "DateTime" }, new java.lang.String[] { "en_GB", "DateTime" }, new java.lang.String[] { "en_ZA", "DateTime" } }),
	EnumType(new java.lang.String[][] { new java.lang.String[] { "en_US", "Enum" }, new java.lang.String[] { "nl_NL", "Enum" }, new java.lang.String[] { "en_GB", "Enum" }, new java.lang.String[] { "en_ZA", "Enum" } }),
	HashString(new java.lang.String[][] { new java.lang.String[] { "en_US", "Hash string (i.e. passwords)" }, new java.lang.String[] { "nl_NL", "Hash string (bv. wachtwoorden)" }, new java.lang.String[] { "en_GB", "Hash string (i.e. passwords)" }, new java.lang.String[] { "en_ZA", "Hash string (i.e. passwords)" } }),
	IntegerType(new java.lang.String[][] { new java.lang.String[] { "en_US", "Integer" }, new java.lang.String[] { "nl_NL", "Integer" }, new java.lang.String[] { "en_GB", "Integer" }, new java.lang.String[] { "en_ZA", "Integer" } }),
	LongType(new java.lang.String[][] { new java.lang.String[] { "en_US", "Long" }, new java.lang.String[] { "nl_NL", "Long" }, new java.lang.String[] { "en_GB", "Long" }, new java.lang.String[] { "en_ZA", "Long" } }),
	StringType(new java.lang.String[][] { new java.lang.String[] { "en_US", "String" }, new java.lang.String[] { "nl_NL", "String" }, new java.lang.String[] { "en_GB", "String" }, new java.lang.String[] { "en_ZA", "String" } }),
	ObjectType(new java.lang.String[][] { new java.lang.String[] { "en_US", "Object Type" }, new java.lang.String[] { "en_GB", "Object Type" }, new java.lang.String[] { "en_ZA", "Object Type" }, new java.lang.String[] { "nl_NL", "Object Type" } }),
	ObjectList(new java.lang.String[][] { new java.lang.String[] { "en_US", "Object List" }, new java.lang.String[] { "en_GB", "Object List" }, new java.lang.String[] { "en_ZA", "Object List" }, new java.lang.String[] { "nl_NL", "Object Lijst" } }),
	Decimal(new java.lang.String[][] { new java.lang.String[] { "en_US", "Decimal" }, new java.lang.String[] { "nl_NL", "Decimal" }, new java.lang.String[] { "en_GB", "Decimal" }, new java.lang.String[] { "en_ZA", "Decimal" } });

	private final java.util.Map<java.lang.String, java.lang.String> captions;

	private PrimitiveTypes(java.lang.String[][] captionStrings)
	{
		this.captions = new java.util.HashMap<>();
		for (java.lang.String[] captionString : captionStrings) {
			captions.put(captionString[0], captionString[1]);
		}
	}

	public java.lang.String getCaption(java.lang.String languageCode)
	{
		return captions.getOrDefault(languageCode, "en_US");
	}

	public java.lang.String getCaption()
	{
		return captions.get("en_US");
	}
}
