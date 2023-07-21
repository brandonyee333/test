/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.test.rule.PACLTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.RegistryUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class MessageBusTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Before
	public void setUp() {
		RegistryUtil.setRegistry(new BasicRegistryImpl());
	}

	@Test
	public void testListen1() throws Exception {
		Object value = MessageBusUtil.sendSynchronousMessage(
			"liferay/test_pacl_listen_failure", "Listen Failure");

		Assert.assertNull(value);
	}

	@Test
	public void testListen2() throws Exception {
		Object value = MessageBusUtil.sendSynchronousMessage(
			"liferay/test_pacl_listen_success", "Listen Success");

		Assert.assertEquals("Listen Success", value);
	}

	@Test
	public void testSend1() throws Exception {
		try {
			MessageBusUtil.sendMessage(
				"liferay/test_pacl_send_failure", "Send Failure");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testSend2() throws Exception {
		MessageBusUtil.sendMessage(
			"liferay/test_pacl_send_success", "Send Success");
	}

}