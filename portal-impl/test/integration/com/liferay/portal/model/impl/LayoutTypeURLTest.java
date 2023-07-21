/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.BasePortalImplURLTestCase;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Stian Sigvartsen
 */
public class LayoutTypeURLTest extends BasePortalImplURLTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGetRegularURLLayoutTypeURL() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, publicLayout, VIRTUAL_HOSTNAME);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		Layout layoutURLType = LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), TestPropsValues.getGroupId(), false,
			publicLayout.getLayoutId(), "Link", "Link", "Test invalid URL",
			LayoutConstants.TYPE_URL, false, null, serviceContext);

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		UnicodeProperties properties =
			layoutURLType.getTypeSettingsProperties();

		properties.setProperty("url", "javascript:alert(1)");

		Assert.assertTrue(
			Validator.isUrl(
				layoutURLType.getRegularURL(mockHttpServletRequest), true));
	}

}