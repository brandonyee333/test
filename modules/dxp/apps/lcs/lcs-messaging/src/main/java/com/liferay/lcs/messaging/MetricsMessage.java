/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * Represents a Liferay Cloud Services Protocol metrics message. Metrics
 * messages transfer metrics data in their payload.
 *
 * <p>
 * The payload must include metrics of a single type, being either cache, JVM,
 * layout, portal, portlet, server, or service type metrics. These metrics types
 * are represented in this class's public metrics type constants.
 * </p>
 *
 * @author  Miguel Pastor
 * @author  Ivica Cardic
 * @author  Igor Beslic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public abstract class MetricsMessage extends Message {

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
		sb.append(", key=");
		sb.append(getKey());

		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private String _toString;

}