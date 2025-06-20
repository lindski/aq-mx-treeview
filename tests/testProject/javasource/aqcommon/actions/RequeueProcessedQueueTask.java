// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package aqcommon.actions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.thirdparty.org.json.JSONObject;

/**
 * Note, that this will re-queue using the current context - not the original context that was used for the task
 */
public class RequeueProcessedQueueTask extends CustomJavaAction<java.lang.Void>
{
	/** @deprecated use ProcessedQueueTask.getMendixObject() instead. */
	@java.lang.Deprecated(forRemoval = true)
	private final IMendixObject __ProcessedQueueTask;
	private final system.proxies.ProcessedQueueTask ProcessedQueueTask;

	public RequeueProcessedQueueTask(
		IContext context,
		IMendixObject _processedQueueTask
	)
	{
		super(context);
		this.__ProcessedQueueTask = _processedQueueTask;
		this.ProcessedQueueTask = _processedQueueTask == null ? null : system.proxies.ProcessedQueueTask.initialize(getContext(), _processedQueueTask);
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		// BEGIN USER CODE
		//TODO - investigate how re-queue using same context as before
		// For now, use the current context
		IContext context = getContext();
		
		String microflow = ProcessedQueueTask.getMicroflowName();
		if(microflow == null || microflow.equals("")) {
			throw new IllegalArgumentException("No microflow defined");
		}
		
		String queue = ProcessedQueueTask.getQueueName();
		if(queue == null || queue.equals("")) {
			throw new IllegalArgumentException("No queue defined");
		}
		
		String arguments = ProcessedQueueTask.getArguments();
		if(arguments == null || arguments.equals("")) {
			Core.microflowCall(microflow).executeInBackground(context, queue);
			return null;
		}
		else {
			Map<String, Object> parameters = new HashMap<String,Object>();
			JSONObject argumentsJson = new JSONObject(arguments);
			argumentsJson.keys().forEachRemaining((k) -> {
				JSONObject singleArgumentJson = argumentsJson.optJSONObject(k);
				if(singleArgumentJson != null) {
					String type = singleArgumentJson.optString("type", "");
					switch(type) {
						case "String":
							parameters.put(k, singleArgumentJson.optString("value", ""));
							break;
						case "Long":
							parameters.put(k, singleArgumentJson.optLong("value"));
							break;
						case "Datetime":
							parameters.put(k, new Date(singleArgumentJson.optLong("value")));
							break;
						case "Decimal":
							parameters.put(k, BigDecimal.valueOf(singleArgumentJson.optDouble("value")));
							break;
						case "Boolean":
							parameters.put(k, singleArgumentJson.optBoolean("value"));
							break;
						case "Object":
							String objectIdValue = singleArgumentJson.optString("value", "");
							if(objectIdValue != null && !objectIdValue.equals("")) {
								Long objectId = Long.valueOf(objectIdValue);
								IMendixIdentifier mxIdentifier = Core.createMendixIdentifier(objectId);
								IMendixObject mxObject;
								try {
									mxObject = Core.retrieveId(context, mxIdentifier);
								} catch (CoreException e) {
									return;
								}
								parameters.put(k, mxObject);
							}
							break;
						default:
							return;
					}
				}
			});
			
			Core.microflowCall(microflow).withParams(parameters).executeInBackground(context, queue);
			
			return null;
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "RequeueProcessedQueueTask";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
