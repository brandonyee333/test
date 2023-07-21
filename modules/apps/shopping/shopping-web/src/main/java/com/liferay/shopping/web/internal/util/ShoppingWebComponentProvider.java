/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.web.internal.util;

import com.liferay.shopping.configuration.ShoppingGroupServiceConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(immediate = true)
public class ShoppingWebComponentProvider {

	public static ShoppingWebComponentProvider
		getShoppingWebComponentProvider() {

		return _shoppingWebComponentProvider;
	}

	public ShoppingGroupServiceConfiguration
		getShoppingGroupServiceConfiguration() {

		return _shoppingGroupServiceConfiguration;
	}

	@Activate
	protected void activate() {
		_shoppingWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_shoppingWebComponentProvider = null;
	}

	@Reference
	protected void setShoppingGroupServiceConfiguration(
		ShoppingGroupServiceConfiguration shoppingGroupServiceConfiguration) {

		_shoppingGroupServiceConfiguration = shoppingGroupServiceConfiguration;
	}

	protected void unsetShoppingGroupServiceConfiguration(
		ShoppingGroupServiceConfiguration shoppingGroupServiceConfiguration) {

		_shoppingGroupServiceConfiguration = null;
	}

	private static ShoppingWebComponentProvider _shoppingWebComponentProvider;

	private ShoppingGroupServiceConfiguration
		_shoppingGroupServiceConfiguration;

}