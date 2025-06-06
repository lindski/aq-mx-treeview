# AqCommon

A set of common, application agnostic, utility objects to support our Mendix development

**Minimum supported Mendix version:** 10.4.1

## Module dependencies

- [Mx Model Reflection](https://marketplace.mendix.com/link/component/69) >= 6.2.0

## Project artefacts

### App Helpers

| Artefact                                  | Summary                                                                         | Documentation |
| ----------------------------------------- | ------------------------------------------------------------------------------- | ------------- |
| GenerateRandomPassword                    | Generates a random password                                                     |               |
| GetApplicationUrl                         | Returns the currently configured root application URL                           |               |
| SUB_GetApplicationUrlWithoutTrailingSlash | Wraps the GetApplicationUrl and ensures the URL does not end in a forward slash |               |

#### Environment

| Artefact            | Summary | Documentation |
| ------------------- | ------- | ------------- |
| ENUM_Environment    |         |               |
| EnvironmentUrl_Prod |         |               |
| EnvironmentUrl_Test |         |               |
| EnvironmentUrl_UAT  |         |               |

### Certificate Helpers

| Artefact                        | Summary                                                                                                | Documentation |
| ------------------------------- | ------------------------------------------------------------------------------------------------------ | ------------- |
| DecryptStringWithPrivateKey     | Decrypts a string using a personal information file (.pfx), a password and an alias.                   |               |
| EncryptStringWithPublicKey      | Encrypts a string using a certification file (.cer), example file can be found in resources.           |               |
| SignStringWithPrivateKey        | Similar to encryption, this will sign the string with a hashed string, using a password and alias.     |               |
| VerifySignedStringWithPublicKey | Using the original and signed strings will return a boolean verifying if the original string is signed.|               |

### Date Helpers

| Artefact                        | Summary | Documentation |
| ------------------------------- | ------- | ------------- |
| SUB_GetBeginningOfDayForDateUTC |         |               |
| SUB_GetEndOfDayForDateUTC       |         |               |

### Domain Object Helpers

| Artefact                      | Summary                                               | Documentation                                         |
| ----------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| AutoMapper                    | Maps one object to another                            |                                                       |
| CompareObjects                | Checks equality between two objects                   |                                                       |
| ExportToJsonWithCustomMapping | Exports domain model to JSON using mapping definition | [Go to documentation](#exporttojsonwithcustommapping) |
| GetObjectById                 | Returns an object by it's ID                          |                                                       |
| GetObjectId                   | Returns the ID of an object                           |                                                       |

### Dynamic XPath

| Artefact                         | Summary | Documentation |
| -------------------------------- | ------- | ------------- |
| EscapeXml                        |         |               |
| ExecuteXPathQuery                |         |               |
| ExecuteXPathQueryAggregateDouble |         |               |
| ExecuteXPathQueryAggregateLong   |         |               |

### FileHelpers

| Artefact                    | Summary                                                                                            | Documentation |
| --------------------------- | -------------------------------------------------------------------------------------------------- | ------------- |
| CloneFileDocument           | This Java action will clone a file document.                                                       |               |
| GetFileContentsFromResource | This Java action will get the file contents from the resources folder using the files name.        |               |
| GetFileContentsFromTemp     | This Java action will get the file contents from the temp folder.                                  |               |
| GetFileSeparator            | This Java action will get the file separator, this: \                                              |               |

### JMESPath

| Artefact                  | Summary | Documentation |
| ------------------------- | ------- | ------------- |
| ExecuteJMESPathExpression |         |               |

### List Helpers

| Artefact           | Summary                                                                                                                                                                                           | Documentation |
| ------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| GetLastItemInList  | This Java action will get the last item in a list.                                                                                                                                                |               |
| GetListItemByIndex | This Java action will retrieve the object based on the index entered, ie 0 will be the first in the list, if the list is n long and n is entered then you will receive the last item in the list. |               |

### OQL

| Artefact        | Summary | Documentation |
| --------------- | ------- | ------------- |
| ExecuteOQLQuery |         |               |

### Microflows

| Artefact        | Summary | Documentation |
| --------------- | ------- | ------------- |
| ExecuteMicroflowWithNoResult |         | Calls a microflow but does not return a result. Supports running on task queue and with delayed execution  |

### Reflection Helpers

| Artefact                                                | Summary | Documentation |
| ------------------------------------------------------- | ------- | ------------- |
| SUB_CreateMxReflectionAttributeToken                    |         |               |
| SUB_CreateMxReflectionAttributeTokenForTokenList        |         |               |
| SUB_CreateMxReflectionAttributeTokenListForMxObjectType |         |               |
| SUB_SyncRequiredModules                                 |         |               |

### String Utilities

| Artefact                               | Summary                                                                  | Documentation |
| -------------------------------------- | ------------------------------------------------------------------------ | ------------- |
| GenerateUUID                           | This Java action generates a GenerateUUID                                |               |
| SplitToStringItemList                  | Splits the string into a list and removes the delimiter.                 |               |
| StringLeftPad                          | Concatenates a repeating string from the left using the amount provided. |               |
| SUB_AppendDelimitedString              |         |               |
| SUB_AppendDelimitedStringViaStringItem |         |               |

### Validation

| Artefact                       | Summary | Documentation |
| ------------------------------ | ------- | ------------- |
| DuplicateRecordMessage         |         |               |
| RequiredFieldMessage           |         |               |
| VAL_DecimalNotEmptyAndPositive |         |               |
| VAL_DecimalNotEmptyOr0         |         |               |
| VAL_DecimalNotEmptyOr0andPos   |         |               |
| VAL_IntegerNotEmptyOr0         |         |               |
| VAL_IntegerNotEmptyOr0AndPos   |         |               |
| VAL_StringNotEmpty             |         |               |

---

---

# ExportToJsonWithCustomMapping

## Top level structure of a definition

```json
{
  "rootObjectName": "Module.Entity",
  "mappings": []
}
```

**rootObjectName**: The complete name (i.e. _ModuleName.EntityName_) for the root object that is used for the mapping  
**mappings**: An array of mapping definitions

## Minimum requirements for a mapping

```json
{
  "id": "1",
  "parentId": null,
  "nodePropertyName": "propertyName"
}
```

This will take all attributes from the entity in scope (the root object if top level, the parent object if a child level) and create properties with a property name that matches the attribute name

**id**: A unique identifier for a single mapping  
**parentId**: null if this is a top level node, or the id of the node’s parent  
**nodePropertyName**: the name to give the node generated for this mapping

## Mapping to associated data

```json
{
  "id": "1",
  "parentId": null,
  "nodePropertyName": "propertyName",
  "association": {
    "associationName": "Module.Association"
  }
}
```

This will navigate the association and generate an object or array of objects depending on the association type (the module will automatically identify the entity on the other side)

**association.associationName**: The complete name (i.e. _ModuleName.AssociationName_) of the association to traverse

## Specific property details

```json
{
  "id": "1",
  "parentId": null,
  "nodePropertyName": "testEntity",
  "properties": [
    {
      "propertyName": "myCustomDecimalProperty",
      "attributeName": "DecimalAttribute"
    }
  ]
}
```

This will only export the specified properties

**properties.propertyName**: The name of the property in the resulting JSON (optional - if left out then attributeName is used)  
**properties.attributeName**: The attribute to export.

## A complex example

```json
{
  "rootObjectName": "MyFirstModule.TestEntity",
  "mappings": [
    {
      "id": "1",
      "parentId": null,
      "nodePropertyName": "testEntity"
    },
    {
      "id": "2",
      "parentId": "1",
      "nodePropertyName": "testEntityReferenced",
      "properties": [
        {
          "attributeName": "BooleanAttribute"
        },
        {
          "propertyName": "myCustomDecimalProperty",
          "attributeName": "DecimalAttribute"
        }
      ],
      "association": {
        "associationName": "MyFirstModule.TestEntity_TestEntityReferenced"
      }
    },
    {
      "id": "3",
      "parentId": null,
      "nodePropertyName": "testEntityReferencedSet",
      "properties": [
        {
          "attributeName": "BooleanAttribute"
        },
        {
          "propertyName": "myCustomDecimalProperty",
          "attributeName": "DecimalAttribute"
        }
      ],
      "association": {
        "associationName": "MyFirstModule.TestEntity_TestEntityReferenced_SetDefault"
      }
    },
    {
      "id": "4",
      "parentId": null,
      "nodePropertyName": "testEntityReferencerSet",
      "association": {
        "associationName": "MyFirstModule.TestEntityReferencer_TestEntity_SetBoth"
      }
    },
    {
      "id": "5",
      "parentId": "4",
      "nodePropertyName": "testEntityChild",
      "association": {
        "associationName": "MyFirstModule.TestEntityReferencer_TestEntity"
      }
    }
  ]
}
```
