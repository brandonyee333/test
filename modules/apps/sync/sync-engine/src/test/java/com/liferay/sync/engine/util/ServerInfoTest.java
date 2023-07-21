/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class ServerInfoTest {

	@Test
	public void testIsCompatible() {
		Assert.assertTrue(ServerInfo.isCompatible("6.2.0.5", "6.2.0.4"));
		Assert.assertTrue(
			ServerInfo.isCompatible("6.2.10.5", "6.2.0.4", "7.0.0.1"));
		Assert.assertTrue(
			ServerInfo.isCompatible("7.0.0.1", "6.2.0.4", "7.0.0.1"));

		Assert.assertFalse(ServerInfo.isCompatible("6.2.10.5", "7.0.0.1"));
		Assert.assertFalse(
			ServerInfo.isCompatible("6.2.10.5", "6.2.0.6", "7.0.0.1"));
	}

}