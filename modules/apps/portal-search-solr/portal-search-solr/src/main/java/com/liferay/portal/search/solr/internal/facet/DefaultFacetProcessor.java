/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.search.solr.facet.FacetProcessor;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.FacetParams;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, property = "class.name=DEFAULT")
public class DefaultFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public void processFacet(SolrQuery solrQuery, Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		String fieldName = facetConfiguration.getFieldName();

		String prefix = "f." + fieldName + ".";

		JSONObject dataJSONObject = facetConfiguration.getData();

		applyFrequencyThreshold(solrQuery, prefix, dataJSONObject);
		applyMaxTerms(solrQuery, prefix, dataJSONObject);

		solrQuery.addFacetField(fieldName);
	}

	protected void applyFrequencyThreshold(
		SolrQuery solrQuery, String prefix, JSONObject dataJSONObject) {

		int minCount = dataJSONObject.getInt("frequencyThreshold");

		if (minCount > 0) {
			solrQuery.set(prefix.concat(FacetParams.FACET_MINCOUNT), minCount);
		}
	}

	protected void applyMaxTerms(
		SolrQuery solrQuery, String prefix, JSONObject dataJSONObject) {

		int limit = dataJSONObject.getInt("maxTerms");

		if (limit > 0) {
			solrQuery.set(prefix.concat(FacetParams.FACET_LIMIT), limit);
		}
	}

}