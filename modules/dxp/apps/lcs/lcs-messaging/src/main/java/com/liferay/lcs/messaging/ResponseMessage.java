/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * Represents the response to a Liferay Cloud Services Protocol command message.
 *
 * @author  Ivica Cardic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public abstract class ResponseMessage extends Message {

	public String getCorrelationId() {
		return _correlationId;
	}

	public void setCorrelationId(String correlationId) {
		_correlationId = correlationId;
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
	private String _toString;

}