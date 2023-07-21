/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * Represents a Liferay Cloud Services Protocol command message.
 *
 * @author  Ivica Cardic
 * @author  Igor Beslic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public abstract class CommandMessage extends Message {

	/**
	 * Returns the message's correlation ID.
	 *
	 * @return the message's correlation ID
	 * @since  LCS 0.1
	 */
	public String getCorrelationId() {
		return _correlationId;
	}

	public String getSignature() {
		return _signature;
	}

	/**
	 * Sets the command message's correlation ID. The correlation ID is used to
	 * match the receiver's response if the command message is sent
	 * asynchronously, and must be unique within the current LCS client session.
	 *
	 * @param correlationId the command message's correlation ID
	 * @since LCS 0.1
	 */
	public void setCorrelationId(String correlationId) {
		_correlationId = correlationId;
	}

	public void setSignature(String signature) {
		_signature = signature;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{className=");

		Class<?> clazz = getClass();

		sb.append(clazz.getName());

		sb.append(", correlationId=");
		sb.append(_correlationId);
		sb.append(", createTime=");
		sb.append(getCreateTime());
		sb.append(", key=");
		sb.append(getKey());

		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private String _correlationId;
	private String _signature;
	private String _toString;

}