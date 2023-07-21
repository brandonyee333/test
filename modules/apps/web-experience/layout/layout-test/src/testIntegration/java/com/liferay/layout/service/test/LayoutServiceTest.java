/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Akos Thurzo
 */
@RunWith(Arquillian.class)
public class LayoutServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testUpdateLayoutFriendlyURLMap() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(_group);

		long userId = layout.getUserId();

		layout.setUserId(-1);

		LayoutLocalServiceUtil.updateLayout(layout);

		Map<Locale, String> friendlyURLMap = layout.getFriendlyURLMap();

		friendlyURLMap.put(
			LocaleUtil.GERMANY,
			StringPool.SLASH + RandomTestUtil.randomString());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUserId(userId);

		LayoutLocalServiceUtil.updateLayout(
			_group.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getParentLayoutId(), layout.getNameMap(),
			layout.getTitleMap(), layout.getDescriptionMap(),
			layout.getKeywordsMap(), layout.getRobotsMap(), layout.getType(),
			layout.isHidden(), friendlyURLMap, layout.getIconImage(), null,
			serviceContext);
	}

	@Test
	public void testUpdateLayoutLookAndFeel() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(_group);

		long userId = layout.getUserId();

		layout = LayoutLocalServiceUtil.updateLookAndFeel(
			_group.getGroupId(), false, layout.getLayoutId(),
			"test_WAR_testtheme", "01", StringPool.BLANK);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(userId, "1_column", false);

		LayoutLocalServiceUtil.updateLayout(layout);
	}

	@DeleteAfterTestRun
	private Group _group;

}