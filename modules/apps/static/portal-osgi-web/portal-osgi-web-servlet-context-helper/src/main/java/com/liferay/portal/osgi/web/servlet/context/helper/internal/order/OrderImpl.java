/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.servlet.context.helper.internal.order;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.osgi.web.servlet.context.helper.order.Order;

import java.util.Arrays;
import java.util.EnumMap;

/**
 * @author Vernon Singleton
 * @author Juan González
 */
public class OrderImpl implements Order {

	public OrderImpl() {
		_routes = new EnumMap<>(Path.class);

		_routes.put(Path.BEFORE, new String[0]);
		_routes.put(Path.AFTER, new String[0]);
	}

	@Override
	public EnumMap<Path, String[]> getRoutes() {
		return _routes;
	}

	@Override
	public boolean isAfter(String name) {
		if (Arrays.binarySearch(_routes.get(Path.AFTER), name) >= 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAfterOthers() {
		boolean value = false;

		if ((_routes.get(Path.AFTER) != null) &&
			(Arrays.binarySearch(_routes.get(Path.AFTER), Order.OTHERS) >= 0)) {

			value = true;
		}

		return value;
	}

	@Override
	public boolean isBefore(String name) {
		if (Arrays.binarySearch(_routes.get(Path.BEFORE), name) >= 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isBeforeOthers() {
		boolean value = false;

		if ((_routes.get(Path.BEFORE) != null) &&
			(Arrays.binarySearch(_routes.get(Path.BEFORE), Order.OTHERS) >=
				0)) {

			value = true;
		}

		return value;
	}

	@Override
	public boolean isOrdered() {
		if (ArrayUtil.isNotEmpty(_routes.get(Path.AFTER)) ||
			ArrayUtil.isNotEmpty(_routes.get(Path.BEFORE))) {

			return true;
		}

		return false;
	}

	@Override
	public void setRoutes(EnumMap<Path, String[]> routes) {
		_routes = routes;
	}

	private EnumMap<Path, String[]> _routes;

}