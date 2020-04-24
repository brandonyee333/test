/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.osb.loop.web.internal.util.LoopEmailNotificationUtil;
import com.liferay.osb.loop.web.internal.util.LoopSyncUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Timothy Bell
 */
public abstract class BaseLoopControllerMessageListener
	extends BaseMessageListener {

	public void setAlloyController(AlloyController alloyController) {
		_alloyController = alloyController;
	}

	@Override
	protected void doReceive(Message message) {
		JSONObject jsonObject = (JSONObject)message.getPayload();

		try {
			String methodName = getMethodName(jsonObject.getString("apiName"));

			LoopSyncUtil.invokeTransaction(
				methodName, new Object[] {_alloyController, jsonObject},
				new Class<?>[] {AlloyController.class, JSONObject.class});
		}
		catch (Exception e) {
			if (e.getCause() != null) {
				e = (Exception)e.getCause();
			}

			LoopEmailNotificationUtil.sendExceptionEmail(
				e.getMessage(), jsonObject);
		}
	}

	protected abstract String getMethodName(String apiName) throws Exception;

	private AlloyController _alloyController;

}