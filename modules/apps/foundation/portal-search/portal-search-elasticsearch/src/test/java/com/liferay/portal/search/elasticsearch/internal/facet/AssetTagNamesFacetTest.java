/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.LiferayIndexCreator;
import com.liferay.portal.search.test.util.facet.BaseAssetTagNamesFacetTestCase;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class AssetTagNamesFacetTest extends BaseAssetTagNamesFacetTestCase {

	@Override
	@Test
	public void testCaseInsensitiveSearchCaseSensitiveGrouping()
		throws Exception {

		super.testCaseInsensitiveSearchCaseSensitiveGrouping();
	}

	@Override
	@Test
	public void testKeysWithSpaces() throws Exception {
		super.testKeysWithSpaces();
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			AssetTagNamesFacetTest.class.getSimpleName());

		return new ElasticsearchIndexingFixture(
			elasticsearchFixture, BaseIndexingTestCase.COMPANY_ID,
			new LiferayIndexCreator(elasticsearchFixture));
	}

}