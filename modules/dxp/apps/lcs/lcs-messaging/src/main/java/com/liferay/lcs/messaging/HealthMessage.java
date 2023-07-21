/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * Represents a Liferay Cloud Services Protocol health message. Health messages
 * transfer the sender's health state information in their payload.
 *
 * <p>
 * Health messages include a health type, which indicates its particular
 * software module or infrastructure (e.g. cluster). These health types are
 * represented in this class's public health type constants.
 * </p>
 *
 * @author  Ivica Cardic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public abstract class HealthMessage extends Message {

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{className=");

		Class<?> clazz = getClass();

		sb.append(clazz.getName());

		sb.append(", createTime=");
		sb.append(getCreateTime());
		sb.append(", healthType=");
		sb.append(", key=");
		sb.append(getKey());

		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private String _toString;

}