/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.mappings;

import com.liferay.portal.search.solr.internal.SolrIndexingFixture;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.mappings.BaseTitleFieldQueryBuilderTestCase;

import org.junit.Test;

/**
 * @author André de Oliveira
 * @author Rodrigo Paulino
 */
public class TitleFieldQueryBuilderTest
	extends BaseTitleFieldQueryBuilderTestCase {

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
	public void testLuceneUnfriendlyTerms() throws Exception {
		super.testLuceneUnfriendlyTerms();
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
	public void testWhitespace() throws Exception {
		super.testWhitespace();
	}

	@Override
	@Test
	public void testWildcardCharacters() throws Exception {
		super.testWildcardCharacters();
	}

	@Override
	@Test
	public void testWordPrefixes() throws Exception {
		super.testWordPrefixes();
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return new SolrIndexingFixture();
	}

}