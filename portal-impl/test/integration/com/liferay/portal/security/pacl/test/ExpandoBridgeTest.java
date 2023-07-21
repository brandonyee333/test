/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.PACLTestRule;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class ExpandoBridgeTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_companyId = CompanyThreadLocal.getCompanyId();

		CompanyThreadLocal.setCompanyId(TestPropsValues.getCompanyId());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		CompanyThreadLocal.setCompanyId(_companyId);
	}

	@Test
	public void test1() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			group.getExpandoBridge();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			ServiceContext serviceContext = new ServiceContext();

			group.setExpandoBridgeAttributes(serviceContext);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test3() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			group = new GroupWrapper(group);

			ServiceContext serviceContext = new ServiceContext();

			group.setExpandoBridgeAttributes(serviceContext);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test4() throws Exception {
		User user = TestPropsValues.getUser();

		user.getExpandoBridge();
	}

	@Test
	public void test5() throws Exception {
		User user = TestPropsValues.getUser();

		ServiceContext serviceContext = new ServiceContext();

		user.setExpandoBridgeAttributes(serviceContext);
	}

	@Test
	public void test6() throws Exception {
		User user = TestPropsValues.getUser();

		user = new UserWrapper(user);

		user.getExpandoBridge();
	}

	@Test
	public void test7() throws Exception {
		User user = TestPropsValues.getUser();

		user = new UserWrapper(user);

		ServiceContext serviceContext = new ServiceContext();

		user.setExpandoBridgeAttributes(serviceContext);
	}

	private static long _companyId;

}