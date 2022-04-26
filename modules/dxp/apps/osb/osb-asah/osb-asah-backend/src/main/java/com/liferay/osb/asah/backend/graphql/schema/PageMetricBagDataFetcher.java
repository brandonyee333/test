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

import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.page.PageDog;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;

import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(fieldName = "pages", typeName = "QueryType")
public class PageMetricBagDataFetcher extends BaseDataFetcher<ResultBag> {

	@Override
	public ResultBag get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ResultBag<AssetMetric> resultBag = new ResultBag<>();

		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		int page = start / size;

		Page<PageVisitorBehaviorMetric> pageVisitorBehaviorMetricPage =
			_pageDog.getPageVisitorBehaviorMetricPage(
				NumberUtils.createLong(searchQueryContext.getChannelId()), page,
				size,
				_createSort(
					searchQueryContext.getAssetType(),
					dataFetchingEnvironment.getArgument("sort")),
				searchQueryContext.getTimeRange());

		Stream<PageVisitorBehaviorMetric> stream =
			pageVisitorBehaviorMetricPage.get();

		List<PageMetric> pageMetrics = stream.map(
			PageMetric::new
		).collect(
			Collectors.toList()
		);

		resultBag.setResults(new ArrayList<>(pageMetrics));

		resultBag.setTotal(pageVisitorBehaviorMetricPage.getTotalElements());

		return resultBag;
	}

	private Sort _createSort(AssetType assetType, Map<String, String> sort) {
		MetricType metricType = _metricTypeDog.getMetricType(
			assetType, sort.get("column"));

		String sortField = metricType.getAggregationName();

		if (metricType.equals(PageMetricType.AVG_TIME_ON_PAGE)) {
			sortField = "avgtimeonpage";
		}
		else if (metricType.equals(PageMetricType.BOUNCE_RATE)) {
			sortField = "bouncerate";
		}
		else if (metricType.equals(PageMetricType.EXIT_RATE)) {
			sortField = "exitrate";
		}

		return new Sort(sortField, sort.get("type"));
	}

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private PageDog _pageDog;

}