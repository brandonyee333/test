/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.facet.FacetProcessor;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Tibor Lipusz
 */
@Component(
	immediate = true,
	property = "class.name=com.liferay.portal.kernel.search.facet.RangeFacet"
)
public class RangeFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public void processFacet(SolrQuery solrQuery, Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		solrQuery.addFacetField(facetConfiguration.getFieldName());

		addConfigurationRanges(facetConfiguration, solrQuery);

		addCustomRange(facet, solrQuery);
	}

	protected void addConfigurationRanges(
		FacetConfiguration facetConfiguration, SolrQuery solrQuery) {

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			String facetQuery =
				facetConfiguration.getFieldName() + StringPool.COLON + range;

			solrQuery.addFacetQuery(facetQuery);
		}
	}

	protected void addCustomRange(Facet facet, SolrQuery solrQuery) {
		SearchContext searchContext = facet.getSearchContext();

		String range = GetterUtil.getString(
			searchContext.getAttribute(facet.getFieldId()));

		if (Validator.isNull(range)) {
			return;
		}

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		String facetQuery =
			facetConfiguration.getFieldName() + StringPool.COLON + range;

		solrQuery.addFacetQuery(facetQuery);
	}

}