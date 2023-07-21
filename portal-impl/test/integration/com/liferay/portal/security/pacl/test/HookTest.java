/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBStatsUserLocalServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.security.pacl.test.hook.action.FailureStrutsAction;
import com.liferay.portal.security.pacl.test.hook.action.SuccessStrutsAction;
import com.liferay.portal.security.pacl.test.hook.indexer.OrganizationIndexerPostProcessor;
import com.liferay.portal.security.pacl.test.hook.indexer.UserIndexerPostProcessor;
import com.liferay.portal.test.rule.PACLTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class HookTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void testIndexer1() throws Exception {
		Assert.assertFalse(OrganizationIndexerPostProcessor.isInstantiated());
	}

	@Test
	public void testIndexer2() throws Exception {
		Assert.assertTrue(UserIndexerPostProcessor.isInstantiated());
	}

	@Test
	public void testPortalProperties() throws Exception {
		Assert.assertFalse(LanguageUtil.isBetaLocale(LocaleUtil.US));
	}

	@Test
	public void testServices1() throws Exception {
		Assert.assertTrue(MBMessageLocalServiceUtil.getMBMessagesCount() < 0);
	}

	@Test
	public void testServices2() throws Exception {
		Assert.assertTrue(
			MBStatsUserLocalServiceUtil.getMBStatsUsersCount() >= 0);
	}

	@Test
	public void testStruts1() throws Exception {
		Assert.assertFalse(FailureStrutsAction.isInstantiated());
	}

	@Test
	public void testStruts2() throws Exception {
		Assert.assertTrue(SuccessStrutsAction.isInstantiated());
	}

}