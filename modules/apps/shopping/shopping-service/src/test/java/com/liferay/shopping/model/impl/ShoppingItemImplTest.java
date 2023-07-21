/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class ShoppingItemImplTest {

	@Before
	public void setUp() {
		ConfigurationFactoryUtil.setConfigurationFactory(
			new ConfigurationFactoryImpl());
	}

	@Test
	public void testInfiniteStock() {
		ShoppingItemImpl shoppingItemImpl = new ShoppingItemImpl();

		Assert.assertFalse(shoppingItemImpl.isInfiniteStock());

		shoppingItemImpl.setStockQuantity(
			ShoppingItemImpl.STOCK_QUANTITY_INFINITE_STOCK);

		Assert.assertTrue(shoppingItemImpl.isInfiniteStock());
	}

}