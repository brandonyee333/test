/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import java.util.Set;

import org.junit.Assert;

/**
 * @author Manuel de la Peña
 */
public class LicenseUtilWindowsTest extends BaseLicenseUtilTestCase {

	@Override
	protected String getDependenciesFileName() {
		return "windows";
	}

	@Override
	protected void testMacAddresses(Set<String> macAddresses) {
		Assert.assertEquals(macAddresses.toString(), 3, macAddresses.size());
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("08:00:27:62:4c:9d"));
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("08:00:27:c0:ab:91"));
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("00:ff:b0:3b:1f:e7"));
	}

}