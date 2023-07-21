/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.asset;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.asset.search.test.BaseAssetSearchTestCase;

import java.util.Locale;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Eudaldo Alonso
 */
@Sync
public class BlogsEntryAssetSearchTest extends BaseAssetSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Ignore
	@Override
	@Test
	public void testClassTypeIds1() throws Exception {
	}

	@Ignore
	@Override
	@Test
	public void testClassTypeIds2() throws Exception {
	}

	@Ignore
	@Override
	@Test
	public void testOrderByExpirationDateAsc() throws Exception {
	}

	@Ignore
	@Override
	@Test
	public void testOrderByExpirationDateDesc() throws Exception {
	}

	@Override
	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, Map<Locale, String> titleMap,
			ServiceContext serviceContext)
		throws Exception {

		return BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), titleMap.get(LocaleUtil.getDefault()),
			RandomTestUtil.randomString(), serviceContext);
	}

	@Override
	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), keywords,
			RandomTestUtil.randomString(), serviceContext);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return BlogsEntry.class;
	}

	@Override
	protected String getSearchKeywords() {
		return "title";
	}

	@Override
	protected boolean isLocalizableTitle() {
		return false;
	}

}