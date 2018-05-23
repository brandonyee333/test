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

package com.liferay.lcs.messaging;

import java.util.Map;

/**
 * Represents the response to a Liferay Cloud Services Protocol command message.
 *
 * @author  Ivica Cardic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public class ResponseMessage extends Message {

	public String getCommandType() {
		return _commandType;
	}

	public String getCorrelationId() {
		return _correlationId;
	}

	public void setCommandType(String commandType) {
		_commandType = commandType;
	}

	public void setCorrelationId(String correlationId) {
		_correlationId = correlationId;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder(11);

		sb.append("{className=");

		Class<?> clazz = getClass();

		sb.append(clazz.getName());

		sb.append(", commandType=");
		sb.append(_commandType);
		sb.append(", correlationId=");
		sb.append(_correlationId);
		sb.append(", createTime=");
		sb.append(getCreateTime());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", payload=");
		sb.append(getPayload());
		sb.append(", values=");

		Map<String, Object> values = getValues();

		if (values != null) {
			sb.append(values.toString());
		}

		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private String _commandType;
	private String _correlationId;
	private String _toString;

}