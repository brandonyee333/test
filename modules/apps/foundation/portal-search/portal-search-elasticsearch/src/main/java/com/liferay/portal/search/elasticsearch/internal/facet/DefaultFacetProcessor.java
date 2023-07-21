/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
@Component(immediate = true, property = "class.name=DEFAULT")
public class DefaultFacetProcessor
	implements FacetProcessor<SearchRequestBuilder> {

	@Override
	public void processFacet(
		SearchRequestBuilder searchRequestBuilder, Facet facet) {

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		String fieldName = facetConfiguration.getFieldName();

		TermsBuilder termsBuilder = new TermsBuilder(fieldName);

		termsBuilder.field(fieldName);

		JSONObject data = facetConfiguration.getData();

		int minDocCount = data.getInt("frequencyThreshold");

		if (minDocCount > 0) {
			termsBuilder.minDocCount(minDocCount);
		}

		int size = data.getInt("maxTerms");

		if (size > 0) {
			termsBuilder.size(size);
		}

		searchRequestBuilder.addAggregation(termsBuilder);
	}

}