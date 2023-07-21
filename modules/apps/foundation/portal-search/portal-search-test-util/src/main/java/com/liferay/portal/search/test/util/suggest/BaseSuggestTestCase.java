/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.suggest;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.search.suggest.SpellCheckIndexWriter;
import com.liferay.portal.kernel.search.suggest.SuggestionConstants;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
public abstract class BaseSuggestTestCase extends BaseIndexingTestCase {

	@Test
	public void testMultipleWords() throws Exception {
		indexSuccessfulQuery("indexed this phrase");

		assertSuggest("[indexed this phrase]", "indexef   this   phrasd");
	}

	@Test
	public void testNothingToSuggest() throws Exception {
		assertSuggest("[]", "nothign");
	}

	@Test
	public void testNull() throws Exception {
		assertSuggest("[]", null);
	}

	protected void assertSuggest(String expectedSuggestions, String keywords)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			3, TimeUnit.SECONDS,
			() -> {
				Assert.assertEquals(
					expectedSuggestions,
					String.valueOf(
						Arrays.asList(suggestKeywordQueries(keywords))));

				return null;
			});
	}

	protected SearchContext createSearchContext(String keywords) {
		SearchContext searchContext = createSearchContext();

		searchContext.setKeywords(keywords);

		return searchContext;
	}

	protected void indexSuccessfulQuery(String value) throws Exception {
		SpellCheckIndexWriter spellCheckIndexWriter = getIndexWriter();

		spellCheckIndexWriter.indexKeyword(
			createSearchContext(value), 0,
			SuggestionConstants.TYPE_QUERY_SUGGESTION);
	}

	protected String[] suggestKeywordQueries(
			SearchContext searchContext, int max)
		throws Exception {

		QuerySuggester querySuggester = getIndexSearcher();

		return querySuggester.suggestKeywordQueries(searchContext, max);
	}

	protected String[] suggestKeywordQueries(String keywords) throws Exception {
		return suggestKeywordQueries(createSearchContext(keywords), 1);
	}

}