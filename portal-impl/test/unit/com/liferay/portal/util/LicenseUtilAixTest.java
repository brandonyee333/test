/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Assert;

/**
 * @author Manuel de la Peña
 */
public class LicenseUtilAixTest extends BaseLicenseUtilTestCase {

	@Override
	protected String getDependenciesFileName() {
		return "aix";
	}

	@Override
	protected Pattern getMacAddressPattern() {
		return Pattern.compile(
			"\\s((\\p{XDigit}{1,2}(\\.)){5}(\\p{XDigit}{1,2}))(?:\\s|$)");
	}

	@Override
	protected void testMacAddresses(Set<String> macAddresses) {
		Assert.assertEquals(macAddresses.toString(), 2, macAddresses.size());
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("66:da:90:6b:f1:17"));
		Assert.assertTrue(
			macAddresses.toString(),
			macAddresses.contains("66:da:90:6b:f1:18"));
	}

}