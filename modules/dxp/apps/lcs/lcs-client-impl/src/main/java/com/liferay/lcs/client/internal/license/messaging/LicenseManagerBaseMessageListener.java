/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.license.messaging;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Igor Beslic
 */
public abstract class LicenseManagerBaseMessageListener
	extends BaseMessageListener {

	public LicenseManagerMessageType getAllowedLicenseManagerMessageType() {
		return _allowedLicenseManagerMessageType;
	}

	public void setAllowedLicenseManagerMessageType(
		LicenseManagerMessageType allowedLicenseManagerMessageType) {

		_allowedLicenseManagerMessageType = allowedLicenseManagerMessageType;
	}

	protected abstract Message createResponseMessage(JSONObject jsonObject);

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!(message.getPayload() instanceof String)) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			(String)message.getPayload());

		LicenseManagerMessageType licenseManagerMessageType =
			LicenseManagerMessageType.valueOf(jsonObject);

		if (!licenseManagerMessageType.equals(
				_allowedLicenseManagerMessageType)) {

			return;
		}

		Message responseMessage = createResponseMessage(jsonObject);

		if (isSynchronous(message)) {
			responseMessage.setDestinationName(
				message.getResponseDestinationName());
			responseMessage.setResponseId(message.getResponseId());
		}

		MessageBusUtil.sendMessage(
			responseMessage.getDestinationName(), responseMessage);
	}

	protected boolean isSynchronous(Message message) {
		if (Validator.isNull(message.getResponseDestinationName()) ||
			Validator.isNull(message.getResponseId())) {

			return false;
		}

		return true;
	}

	private LicenseManagerMessageType _allowedLicenseManagerMessageType;

}