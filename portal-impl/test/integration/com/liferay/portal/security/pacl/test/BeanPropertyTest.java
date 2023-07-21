/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.dao.orm.PortalCustomSQL;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.PACLTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class BeanPropertyTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void testGet1() throws Exception {
		try {
			PortalRuntimePermission.checkGetBeanProperty(HttpUtil.class);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testGet2() throws Exception {
		LanguageUtil.getLanguage();
	}

	@Test
	public void testGet3() throws Exception {
		LanguageUtil.getLocale("en_US");
	}

	@Test
	public void testGet4() throws Exception {
		PortalRuntimePermission.checkGetBeanProperty(PortalUtil.class);
	}

	@Test
	public void testSet() throws Exception {
		try {
			LanguageUtil languageUtil = new LanguageUtil();

			languageUtil.setLanguage(null);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testSet3() throws Exception {
		PortalCustomSQLUtil portalCustomSQLUtil = new PortalCustomSQLUtil();

		PortalCustomSQL portalCustomSQL =
			PortalCustomSQLUtil.getPortalCustomSQL();

		portalCustomSQLUtil.setPortalCustomSQL(portalCustomSQL);
	}

}