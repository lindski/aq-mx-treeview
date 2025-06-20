// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package aqcommon.actions;

import java.util.ArrayList;
import java.util.List;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import aqcommon.proxies.StringItem;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.thirdparty.org.json.JSONArray;
import com.mendix.thirdparty.org.json.JSONObject;

public class SplitJsonArrayToObjectList extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private final java.lang.String JsonArray;

	public SplitJsonArrayToObjectList(
		IContext context,
		java.lang.String _jsonArray
	)
	{
		super(context);
		this.JsonArray = _jsonArray;
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		JSONArray jsonArray = new JSONArray(JsonArray);
		
		List<IMendixObject> mxStringItemList = new ArrayList<IMendixObject>();
		
		for(Object object : jsonArray.toCollection()) {
			String objectValue = object.toString();
			IMendixObject mxStringItem = Core.instantiate(getContext(), StringItem.entityName);
			mxStringItem.setValue(getContext(), StringItem.MemberNames.Value.toString(), objectValue);
			mxStringItemList.add(mxStringItem);
		}
		
		return mxStringItemList;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "SplitJsonArrayToObjectList";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
