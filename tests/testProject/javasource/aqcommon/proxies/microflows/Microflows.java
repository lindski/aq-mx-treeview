// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package aqcommon.proxies.microflows;

import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public final class Microflows
{
	/**
	 * Private constructor to prevent instantiation of this class. 
	 */
	private Microflows() {}

	// These are the microflows for the AqCommon module
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder aCT_RequeueProcessedQueueTaskBuilder(
		system.proxies.ProcessedQueueTask _processedQueueTask
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.ACT_RequeueProcessedQueueTask");
		builder = builder.withParam("ProcessedQueueTask", _processedQueueTask);
		return builder;
	}

	public static void aCT_RequeueProcessedQueueTask(
		IContext context,
		system.proxies.ProcessedQueueTask _processedQueueTask
	)
	{
		aCT_RequeueProcessedQueueTaskBuilder(
				_processedQueueTask
			)
			.execute(context);
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_AppendDelimitedStringBuilder(
		java.lang.String _currentString,
		java.lang.String _stringToAppend,
		java.lang.String _delimiter
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_AppendDelimitedString");
		builder = builder.withParam("CurrentString", _currentString);
		builder = builder.withParam("StringToAppend", _stringToAppend);
		builder = builder.withParam("Delimiter", _delimiter);
		return builder;
	}

	public static java.lang.String sUB_AppendDelimitedString(
		IContext context,
		java.lang.String _currentString,
		java.lang.String _stringToAppend,
		java.lang.String _delimiter
	)
	{
		Object result = sUB_AppendDelimitedStringBuilder(
				_currentString,
				_stringToAppend,
				_delimiter
			)
			.execute(context);
		return (java.lang.String) result;
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_AppendDelimitedStringViaStringItemBuilder(
		aqcommon.proxies.StringItem _stringItem,
		java.lang.String _stringToAppend,
		java.lang.String _delimiter
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_AppendDelimitedStringViaStringItem");
		builder = builder.withParam("StringItem", _stringItem);
		builder = builder.withParam("StringToAppend", _stringToAppend);
		builder = builder.withParam("Delimiter", _delimiter);
		return builder;
	}

	public static void sUB_AppendDelimitedStringViaStringItem(
		IContext context,
		aqcommon.proxies.StringItem _stringItem,
		java.lang.String _stringToAppend,
		java.lang.String _delimiter
	)
	{
		sUB_AppendDelimitedStringViaStringItemBuilder(
				_stringItem,
				_stringToAppend,
				_delimiter
			)
			.execute(context);
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_CreateMxReflectionAttributeTokenBuilder(
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		java.lang.String _attributeName,
		boolean _commitTokenBeforeReturning
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_CreateMxReflectionAttributeToken");
		builder = builder.withParam("MxObjectType", _mxObjectType);
		builder = builder.withParam("AttributeName", _attributeName);
		builder = builder.withParam("CommitTokenBeforeReturning", _commitTokenBeforeReturning);
		return builder;
	}

	public static mxmodelreflection.proxies.Token sUB_CreateMxReflectionAttributeToken(
		IContext context,
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		java.lang.String _attributeName,
		boolean _commitTokenBeforeReturning
	)
	{
		Object result = sUB_CreateMxReflectionAttributeTokenBuilder(
				_mxObjectType,
				_attributeName,
				_commitTokenBeforeReturning
			)
			.execute(context);
		return result == null ? null : mxmodelreflection.proxies.Token.initialize(context, (IMendixObject) result);
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_CreateMxReflectionAttributeTokenForTokenListBuilder(
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		java.lang.String _attributeName,
		java.util.List<mxmodelreflection.proxies.Token> _tokenList
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_CreateMxReflectionAttributeTokenForTokenList");
		builder = builder.withParam("MxObjectType", _mxObjectType);
		builder = builder.withParam("AttributeName", _attributeName);
		builder = builder.withParam("TokenList", _tokenList);
		return builder;
	}

	public static void sUB_CreateMxReflectionAttributeTokenForTokenList(
		IContext context,
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		java.lang.String _attributeName,
		java.util.List<mxmodelreflection.proxies.Token> _tokenList
	)
	{
		sUB_CreateMxReflectionAttributeTokenForTokenListBuilder(
				_mxObjectType,
				_attributeName,
				_tokenList
			)
			.execute(context);
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_CreateMxReflectionAttributeTokenListForMxObjectTypeBuilder(
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		boolean _commitListBeforeReturning
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_CreateMxReflectionAttributeTokenListForMxObjectType");
		builder = builder.withParam("MxObjectType", _mxObjectType);
		builder = builder.withParam("CommitListBeforeReturning", _commitListBeforeReturning);
		return builder;
	}

	public static java.util.List<mxmodelreflection.proxies.Token> sUB_CreateMxReflectionAttributeTokenListForMxObjectType(
		IContext context,
		mxmodelreflection.proxies.MxObjectType _mxObjectType,
		boolean _commitListBeforeReturning
	)
	{
		Object result = sUB_CreateMxReflectionAttributeTokenListForMxObjectTypeBuilder(
				_mxObjectType,
				_commitListBeforeReturning
			)
			.execute(context);
		return result == null ? null : com.mendix.utils.ListUtils.map((java.util.List<IMendixObject>) result, obj -> mxmodelreflection.proxies.Token.initialize(context, obj));
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_GetApplicationUrlWithoutTrailingSlashBuilder()
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_GetApplicationUrlWithoutTrailingSlash");
		return builder;
	}

	public static java.lang.String sUB_GetApplicationUrlWithoutTrailingSlash(IContext context)
	{
		Object result = sUB_GetApplicationUrlWithoutTrailingSlashBuilder().execute(context);
		return (java.lang.String) result;
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_GetBeginningOfDayForDateUTCBuilder(
		java.util.Date _dateTime
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_GetBeginningOfDayForDateUTC");
		builder = builder.withParam("DateTime", _dateTime);
		return builder;
	}

	public static java.util.Date sUB_GetBeginningOfDayForDateUTC(
		IContext context,
		java.util.Date _dateTime
	)
	{
		Object result = sUB_GetBeginningOfDayForDateUTCBuilder(
				_dateTime
			)
			.execute(context);
		return (java.util.Date) result;
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_GetCurrentEnvironmentBuilder()
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_GetCurrentEnvironment");
		return builder;
	}

	public static aqcommon.proxies.ENUM_Environment sUB_GetCurrentEnvironment(IContext context)
	{
		Object result = sUB_GetCurrentEnvironmentBuilder().execute(context);
		return result == null ? null : aqcommon.proxies.ENUM_Environment.valueOf((java.lang.String) result);
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_GetEndOfDayForDateUTCBuilder(
		java.util.Date _dateTime
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_GetEndOfDayForDateUTC");
		builder = builder.withParam("DateTime", _dateTime);
		return builder;
	}

	public static java.util.Date sUB_GetEndOfDayForDateUTC(
		IContext context,
		java.util.Date _dateTime
	)
	{
		Object result = sUB_GetEndOfDayForDateUTCBuilder(
				_dateTime
			)
			.execute(context);
		return (java.util.Date) result;
	}
	public static com.mendix.core.actionmanagement.MicroflowCallBuilder sUB_SyncRequiredModulesBuilder(
		java.lang.String _delimitedListOfModuleNames,
		java.lang.String _delimiter
	)
	{
		com.mendix.core.actionmanagement.MicroflowCallBuilder builder = Core.microflowCall("AqCommon.SUB_SyncRequiredModules");
		builder = builder.withParam("DelimitedListOfModuleNames", _delimitedListOfModuleNames);
		builder = builder.withParam("Delimiter", _delimiter);
		return builder;
	}

	public static boolean sUB_SyncRequiredModules(
		IContext context,
		java.lang.String _delimitedListOfModuleNames,
		java.lang.String _delimiter
	)
	{
		Object result = sUB_SyncRequiredModulesBuilder(
				_delimitedListOfModuleNames,
				_delimiter
			)
			.execute(context);
		return (boolean) result;
	}
}
