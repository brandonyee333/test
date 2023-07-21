/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.search.test.util.FacetsAssert;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.QueryContributor;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.mockito.Mockito;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
public abstract class BaseFacetTestCase extends BaseIndexingTestCase {

	protected void addDocument(String... values) throws Exception {
		addDocument(DocumentCreationHelpers.singleText(getField(), values));
	}

	protected void addDocuments(int count, String... values) throws Exception {
		for (int i = 0; i < count; i++) {
			addDocument(values);
		}
	}

	protected void assertFacet(
			Function<SearchContext, Facet> function,
			QueryContributor queryContributor, List<String> expectedTerms)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			5, TimeUnit.SECONDS,
			() -> doAssertFacet(function, queryContributor, expectedTerms));
	}

	protected Void doAssertFacet(
			Function<SearchContext, Facet> function,
			QueryContributor queryContributor, List<String> expectedFrequencies)
		throws Exception {

		SearchContext searchContext = createSearchContext();

		Facet facet = function.apply(searchContext);

		searchContext.addFacet(facet);

		search(searchContext, queryContributor);

		FacetsAssert.assertFrequencies(
			facet.getFieldName(), searchContext, expectedFrequencies);

		return null;
	}

	protected abstract String getField();

	protected Facet initFacet(Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		facetConfiguration.setDataJSONObject(Mockito.mock(JSONObject.class));

		return facet;
	}

	protected JSONObject setUpFrequencyThreshold(
		int frequencyThreshold, JSONObject jsonObject) {

		Mockito.doReturn(
			frequencyThreshold
		).when(
			jsonObject
		).getInt(
			"frequencyThreshold"
		);

		return jsonObject;
	}

	protected JSONObject setUpMaxTerms(int maxTerms) {
		JSONObject jsonObject = Mockito.mock(JSONObject.class);

		Mockito.doReturn(
			maxTerms
		).when(
			jsonObject
		).getInt(
			"maxTerms"
		);

		return jsonObject;
	}

}