/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexander Chow
 */
public class UnicodePropertiesTest {

	@Test
	public void testSetNullProperty() throws Exception {
		UnicodeProperties props = new UnicodeProperties();

		int hashCode = props.hashCode();

		props.setProperty(null, "value");

		Assert.assertEquals(
			"setProperty() of null key must not change properties", hashCode,
			props.hashCode());

		props.setProperty("key", null);
		props.setProperty("key", "value");
		props.setProperty("key", null);

		Assert.assertEquals(
			"setProperty() of null value must remove entry", hashCode,
			props.hashCode());
	}

}