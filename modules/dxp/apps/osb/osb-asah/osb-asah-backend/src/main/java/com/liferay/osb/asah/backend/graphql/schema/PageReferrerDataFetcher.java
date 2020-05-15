/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.page.PageReferrerDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.PageReferrerMetric;

import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leonardo Barros
 */
@Component
@GraphQLTypeWiring(fieldName = "pageReferrerMetrics", typeName = "PageMetric")
public class PageReferrerDataFetcher
	extends BaseDataFetcher<List<PageReferrerMetric>> {

	@Override
	public List<PageReferrerMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		return _pageReferrerDog.getPageReferrerMetrics(searchQueryContext);
	}

	@Override
	protected AssetType getAssetType(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return AssetType.PAGE;
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}