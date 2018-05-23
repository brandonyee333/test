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

package com.liferay.lcs.util;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseMessage;

/**
 * @author Ivica Cardic
 */
public class ResponseMessageUtil {

	public static ResponseMessage createResponseMessage(
		CommandMessage commandMessage, Object payload) {

		return createResponseMessage(commandMessage, payload, null);
	}

	public static ResponseMessage createResponseMessage(
		CommandMessage commandMessage, Object payload, String error) {

		ResponseMessage responseMessage = new ResponseMessage();

		if (error != null) {
			responseMessage.put(Message.KEY_ERROR, error);
		}

		responseMessage.setCommandType(commandMessage.getCommandType());
		responseMessage.setCorrelationId(commandMessage.getCorrelationId());
		responseMessage.setCreateTime(System.currentTimeMillis());
		responseMessage.setKey(commandMessage.getKey());

		if (payload != null) {
			responseMessage.setPayload(payload);
		}

		return responseMessage;
	}

}