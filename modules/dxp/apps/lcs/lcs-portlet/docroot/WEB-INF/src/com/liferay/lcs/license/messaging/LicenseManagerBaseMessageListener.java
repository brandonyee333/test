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

package com.liferay.lcs.license.messaging;

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