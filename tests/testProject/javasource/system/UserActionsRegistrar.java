package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.registerUserAction(aqcommon.actions.AddJsonArrayToJsonObject.class);
    registrator.registerUserAction(aqcommon.actions.AddJsonObjectToJsonObject.class);
    registrator.registerUserAction(aqcommon.actions.AddStringFieldToJsonObject.class);
    registrator.registerUserAction(aqcommon.actions.AddValidationFeedback.class);
    registrator.registerUserAction(aqcommon.actions.AutoMapper.class);
    registrator.registerUserAction(aqcommon.actions.CloneFileDocument.class);
    registrator.registerUserAction(aqcommon.actions.CompareObjects.class);
    registrator.registerUserAction(aqcommon.actions.CompleteWorkflowTask.class);
    registrator.registerUserAction(aqcommon.actions.DecryptStringWithPrivateKey.class);
    registrator.registerUserAction(aqcommon.actions.EncryptStringWithPublicKey.class);
    registrator.registerUserAction(aqcommon.actions.EscapeXml.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteJMESPathExpression.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteMicroflowWithNoResult.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteOQLQuery.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteXPathQuery.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteXPathQueryAggregateDouble.class);
    registrator.registerUserAction(aqcommon.actions.ExecuteXPathQueryAggregateLong.class);
    registrator.registerUserAction(aqcommon.actions.ExportToJsonWithCustomMapping.class);
    registrator.registerUserAction(aqcommon.actions.GenerateRandomPassword.class);
    registrator.registerUserAction(aqcommon.actions.GenerateUUID.class);
    registrator.registerUserAction(aqcommon.actions.GetApplicationUrl.class);
    registrator.registerUserAction(aqcommon.actions.GetDomainFromUrl.class);
    registrator.registerUserAction(aqcommon.actions.GetFileContentsFromResource.class);
    registrator.registerUserAction(aqcommon.actions.GetFileContentsFromTemp.class);
    registrator.registerUserAction(aqcommon.actions.GetFileSeparator.class);
    registrator.registerUserAction(aqcommon.actions.GetLastItemInList.class);
    registrator.registerUserAction(aqcommon.actions.GetListItemByIndex.class);
    registrator.registerUserAction(aqcommon.actions.GetObjectById.class);
    registrator.registerUserAction(aqcommon.actions.GetObjectId.class);
    registrator.registerUserAction(aqcommon.actions.GetWorkflowContext.class);
    registrator.registerUserAction(aqcommon.actions.RequeueProcessedQueueTask.class);
    registrator.registerUserAction(aqcommon.actions.SignStringWithPrivateKey.class);
    registrator.registerUserAction(aqcommon.actions.SplitJsonArrayToObjectList.class);
    registrator.registerUserAction(aqcommon.actions.SplitToStringItemList.class);
    registrator.registerUserAction(aqcommon.actions.StringLeftPad.class);
    registrator.registerUserAction(aqcommon.actions.VerifySignedStringWithPublicKey.class);
    registrator.registerUserAction(aqhandlebars.actions.CustomHelperRegisterClassIsValid.class);
    registrator.registerUserAction(aqhandlebars.actions.GenerateForExportDocumentAndObject.class);
    registrator.registerUserAction(aqhandlebars.actions.GenerateForExportDocumentAndObjectList.class);
    registrator.registerUserAction(aqhandlebars.actions.GenerateForExportNameAndObject.class);
    registrator.registerUserAction(aqhandlebars.actions.GenerateForExportNameAndObjectList.class);
    registrator.registerUserAction(aqhandlebars.actions.GenerateForJSONString.class);
    registrator.registerUserAction(mxmodelreflection.actions.JA_EnumValueCaptions.class);
    registrator.registerUserAction(mxmodelreflection.actions.JA_EnumValueLanguages.class);
    registrator.registerUserAction(mxmodelreflection.actions.JA_ReferenceObjects.class);
    registrator.registerUserAction(mxmodelreflection.actions.ReplaceToken.class);
    registrator.registerUserAction(mxmodelreflection.actions.SyncObjects.class);
    registrator.registerUserAction(mxmodelreflection.actions.TestThePattern.class);
    registrator.registerUserAction(mxmodelreflection.actions.ValidateTokensInMessage.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
