/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.test.rule.PACLTestRule;
import com.liferay.portlet.PortletBagImpl;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class PortletBagPoolTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void test1() throws Exception {
		PortletBagPool.get("1_WAR_pacl_testportlet");
	}

	@Test
	public void test2() throws Exception {
		try {
			PortletBagPool.get("fail");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test3() throws Exception {
		PortletBagPool.get("pacl-test-portlet");
	}

	@Test
	public void test4() throws Exception {
		PortletBagPool.put(
			"1_WAR_pacl_testportlet",
			new PortletBagImpl(
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null));
	}

	@Test
	public void test5() throws Exception {
		try {
			PortletBagPool.put(
				"fail",
				new PortletBagImpl(
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null,
					null));

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test6() throws Exception {
		PortletBagPool.put(
			"pacl-test-portlet",
			new PortletBagImpl(
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null));
	}

	@Test
	public void test7() throws Exception {
		PortletBagPool.remove("1_WAR_pacl_testportlet");
	}

	@Test
	public void test8() throws Exception {
		try {
			PortletBagPool.remove("fail");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test9() throws Exception {
		PortletBagPool.remove("pacl-test-portlet");
	}

	@Test
	public void test10() throws Exception {
		try {
			PortletBagPool.reset();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

}