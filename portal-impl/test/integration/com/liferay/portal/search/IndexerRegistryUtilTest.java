/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.messageboards.util.MBMessageIndexer;
import com.liferay.portlet.messageboards.util.MBThreadIndexer;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * @author Gregory Amerson
 */
public class IndexerRegistryUtilTest {

	@ClassRule
	@Rule
	public static final TestRule rule = new LiferayIntegrationTestRule();

	@Test
	public void testGetIndexerByIndexerClassName() throws Exception {
		Indexer<MBMessage> mbMessageIndexer = IndexerRegistryUtil.getIndexer(
			MBMessageIndexer.class.getName());

		Assert.assertNotNull(mbMessageIndexer);

		Indexer<MBThread> mbThreadIndexer = IndexerRegistryUtil.getIndexer(
			MBThreadIndexer.class.getName());

		Assert.assertNotNull(mbThreadIndexer);
	}

	@Test
	public void testGetIndexerByModelClassName() throws Exception {
		Indexer<User> userIndexer = IndexerRegistryUtil.getIndexer(
			User.class.getName());

		Assert.assertNotNull(userIndexer);

		Indexer<UserGroup> userGroupIndexer = IndexerRegistryUtil.getIndexer(
			UserGroup.class.getName());

		Assert.assertNotNull(userGroupIndexer);
	}

}