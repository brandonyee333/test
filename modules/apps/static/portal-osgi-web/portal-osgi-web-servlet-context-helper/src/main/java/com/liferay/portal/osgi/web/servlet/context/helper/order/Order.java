/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.servlet.context.helper.order;

import java.util.EnumMap;

/**
 * @author Vernon Singleton
 * @author Juan González
 */
public interface Order {

	public static final String OTHERS = Order.class.getName() + "#OTHERS";

	public EnumMap<Path, String[]> getRoutes();

	public boolean isAfter(String name);

	public boolean isAfterOthers();

	public boolean isBefore(String name);

	public boolean isBeforeOthers();

	public boolean isOrdered();

	public void setRoutes(EnumMap<Path, String[]> routes);

	public enum Path {

		AFTER, BEFORE

	}

}