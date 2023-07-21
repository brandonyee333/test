/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.service.permission.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.journal.service.permission.JournalPermission;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.permission.test.BasePermissionTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eric Chin
 * @author Shinn Lok
 */
@RunWith(Arquillian.class)
public class JournalArticlePermissionCheckerTest
	extends BasePermissionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_journalServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				JournalServiceConfiguration.class,
				TestPropsValues.getCompanyId());

		CompanyThreadLocal.setCompanyId(TestPropsValues.getCompanyId());
	}

	@Test
	public void testContains() throws Exception {
		Assert.assertTrue(
			JournalArticlePermission.contains(
				permissionChecker, _article, ActionKeys.VIEW));
		Assert.assertTrue(
			JournalArticlePermission.contains(
				permissionChecker, _subarticle, ActionKeys.VIEW));

		removePortletModelViewPermission();

		if (_journalServiceConfiguration.articleViewPermissionsCheckEnabled()) {
			Assert.assertFalse(
				JournalArticlePermission.contains(
					permissionChecker, _article, ActionKeys.VIEW));
			Assert.assertFalse(
				JournalArticlePermission.contains(
					permissionChecker, _subarticle, ActionKeys.VIEW));
		}
		else {
			Assert.assertTrue(
				JournalArticlePermission.contains(
					permissionChecker, _article, ActionKeys.VIEW));
			Assert.assertTrue(
				JournalArticlePermission.contains(
					permissionChecker, _subarticle, ActionKeys.VIEW));
		}
	}

	@Override
	protected void doSetUp() throws Exception {
		_article = JournalTestUtil.addArticle(
			group.getGroupId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), RandomTestUtil.randomString());

		_subarticle = JournalTestUtil.addArticle(
			group.getGroupId(), folder.getFolderId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString());
	}

	@Override
	protected String getResourceName() {
		return JournalPermission.RESOURCE_NAME;
	}

	private JournalArticle _article;
	private JournalServiceConfiguration _journalServiceConfiguration;
	private JournalArticle _subarticle;

}