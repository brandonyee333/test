/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.PACLTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class PortalServicesTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void test1() throws Exception {

		// We need CompanyLocalServiceUtil#getCompanyId to work for our message
		// bus listeners. Test CompanyLocalServiceUtil#getCompanyByWebId since
		// it is an unallowed method.

		try {
			CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		GroupLocalServiceUtil.getGroup(TestPropsValues.getGroupId());
	}

	@Test
	public void test3() throws Exception {
		try {
			UserLocalServiceUtil.getUser(TestPropsValues.getUserId());

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

}