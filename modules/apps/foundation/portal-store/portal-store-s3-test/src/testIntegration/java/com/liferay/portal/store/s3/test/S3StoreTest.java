/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.store.s3.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.AssumeTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.documentlibrary.store.test.BaseStoreTestCase;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Preston Crary
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class S3StoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new AssumeTestRule("assume"), new LiferayIntegrationTestRule());

	public static void assume() {
		String dlStoreImpl = PropsUtil.get(PropsKeys.DL_STORE_IMPL);

		String s3StoreClassName = "com.liferay.portal.store.s3.S3Store";

		Assume.assumeTrue(
			"Property \"" + PropsKeys.DL_STORE_IMPL + "\" is not set to \"" +
				s3StoreClassName + "\"",
			dlStoreImpl.equals(s3StoreClassName));
	}

	@Override
	@Test
	public void testUpdateFileWithNewFileNameNoSuchFileException()
		throws Exception {

		updateFileShouldNotUpdateFile();
	}

	@Override
	@Test
	public void testUpdateFileWithNewRepositoryIdNoSuchFileException()
		throws Exception {

		updateFileShouldNotUpdateFile();
	}

	@Override
	protected String getStoreType() {
		return "com.liferay.portal.store.s3.S3Store";
	}

	protected void updateFileShouldNotUpdateFile() throws Exception {
		String fileName = RandomTestUtil.randomString();

		store.updateFile(
			companyId, repositoryId, fileName, RandomTestUtil.randomString());

		Assert.assertFalse(store.hasFile(companyId, repositoryId, fileName));
	}

}