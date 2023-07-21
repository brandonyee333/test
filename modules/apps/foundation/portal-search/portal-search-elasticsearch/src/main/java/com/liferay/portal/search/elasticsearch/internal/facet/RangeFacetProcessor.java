/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.RangeParserUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.elasticsearch.action.search.SearchRequestBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 * @author Tibor Lipusz
 */
@Component(
	immediate = true,
	property = "class.name=com.liferay.portal.kernel.search.facet.RangeFacet"
)
public class RangeFacetProcessor
	implements FacetProcessor<SearchRequestBuilder> {

	@Override
	public void processFacet(
		SearchRequestBuilder searchRequestBuilder, Facet facet) {

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		DefaultRangeBuilder defaultRangeBuilder = new DefaultRangeBuilder(
			facetConfiguration.getFieldName());

		defaultRangeBuilder.field(facetConfiguration.getFieldName());

		addConfigurationRanges(facetConfiguration, defaultRangeBuilder);

		addCustomRange(facet, defaultRangeBuilder);

		if (defaultRangeBuilder.hasRanges()) {
			searchRequestBuilder.addAggregation(defaultRangeBuilder);
		}
	}

	protected void addConfigurationRanges(
		FacetConfiguration facetConfiguration,
		DefaultRangeBuilder defaultRangeBuilder) {

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String rangeString = rangeJSONObject.getString("range");

			String[] range = RangeParserUtil.parserRange(rangeString);

			defaultRangeBuilder.addRange(range[0], range[1]);
		}
	}

	protected void addCustomRange(
		Facet facet, DefaultRangeBuilder defaultRangeBuilder) {

		SearchContext searchContext = facet.getSearchContext();

		String rangeString = GetterUtil.getString(
			searchContext.getAttribute(facet.getFieldId()));

		if (Validator.isNull(rangeString)) {
			return;
		}

		String[] range = RangeParserUtil.parserRange(rangeString);

		defaultRangeBuilder.addRange(range[0], range[1]);
	}

}