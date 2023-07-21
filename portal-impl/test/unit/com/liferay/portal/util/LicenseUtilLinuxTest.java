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
public class LicenseUtilLinuxTest extends BaseLicenseUtilTestCase {

	@Override
	protected String getDependenciesFileName() {
		return "ubuntu";
	}

	@Override
	protected void testMacAddresses(Set<String> macAddresses) {
		Assert.assertEquals(macAddresses.toString(), 2, macAddresses.size());
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("5c:26:0a:33:b3:d5"));
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("00:24:d7:82:96:f4"));
	}

}