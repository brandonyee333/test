/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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