/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.mappings;

import com.liferay.portal.search.elasticsearch.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch.internal.connection.LiferayIndexCreator;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.mappings.BaseDescriptionFieldQueryBuilderTestCase;

import org.junit.Test;

/**
 * @author André de Oliveira
 * @author Rodrigo Paulino
 */
public class DescriptionFieldQueryBuilderTest
	extends BaseDescriptionFieldQueryBuilderTestCase {

	@Override
	@Test
	public void testBasicWordMatches() throws Exception {
		super.testBasicWordMatches();
	}

	@Override
	@Test
	public void testExactMatchBoost() throws Exception {
		super.testExactMatchBoost();
	}

	@Override
	@Test
	public void testMultiwordPhrasePrefixes() throws Exception {
		super.testMultiwordPhrasePrefixes();
	}

	@Override
	@Test
	public void testMultiwordPrefixes() throws Exception {
		super.testMultiwordPrefixes();
	}

	@Override
	@Test
	public void testNull() throws Exception {
		super.testNull();
	}

	@Override
	@Test
	public void testNumbers() throws Exception {
		super.testNumbers();
	}

	@Override
	@Test
	public void testPhrasePrefixes() throws Exception {
		super.testPhrasePrefixes();
	}

	@Override
	@Test
	public void testPhrasePrefixRequiresTrailingStar() throws Exception {
		super.testPhrasePrefixRequiresTrailingStar();
	}

	@Override
	@Test
	public void testPhrases() throws Exception {
		super.testPhrases();
	}

	@Override
	@Test
	public void testStopwords() throws Exception {
		super.testStopwords();
	}

	@Override
	@Test
	public void testWordPrefixes() throws Exception {
		super.testWordPrefixes();
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			DescriptionFieldQueryBuilderTest.class.getSimpleName());

		return new ElasticsearchIndexingFixture(
			elasticsearchFixture, BaseIndexingTestCase.COMPANY_ID,
			new LiferayIndexCreator(elasticsearchFixture));
	}

}