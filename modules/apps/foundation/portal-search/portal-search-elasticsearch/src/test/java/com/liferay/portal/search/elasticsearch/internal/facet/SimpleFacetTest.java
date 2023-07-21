/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.LiferayIndexCreator;
import com.liferay.portal.search.elasticsearch.internal.count.ElasticsearchCountTest;
import com.liferay.portal.search.test.util.facet.BaseSimpleFacetTestCase;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class SimpleFacetTest extends BaseSimpleFacetTestCase {

	@Override
	@Test
	public void testFrequencyThreshold() throws Exception {
		super.testFrequencyThreshold();
	}

	@Override
	@Test
	public void testMaxTerms() throws Exception {
		super.testMaxTerms();
	}

	@Override
	@Test
	public void testMaxTermsNegative() throws Exception {
		super.testMaxTermsNegative();
	}

	@Override
	@Test
	public void testMaxTermsZero() throws Exception {
		super.testMaxTermsZero();
	}

	@Override
	@Test
	public void testUnmatchedAreIgnored() throws Exception {
		super.testUnmatchedAreIgnored();
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			ElasticsearchCountTest.class.getSimpleName());

		return new ElasticsearchIndexingFixture(
			elasticsearchFixture, BaseIndexingTestCase.COMPANY_ID,
			new LiferayIndexCreator(elasticsearchFixture));
	}

}