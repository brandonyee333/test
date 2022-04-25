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
import com.liferay.osb.asah.backend.dog.page.PageDog;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetchingEnvironment;

import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(fieldName = "page", typeName = "QueryType")
public class PageMetricDataFetcher extends BaseDataFetcher<PageMetric> {

	@Override
	public PageMetric get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		PageVisitorBehaviorMetric pageVisitorBehaviorMetric =
			_pageDog.getPageVisitorBehaviorMetric(
				searchQueryContext.getCanonicalUrl(),
				NumberUtils.createLong(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(),
				searchQueryContext.getTitle());

		return new PageMetric(pageVisitorBehaviorMetric);
	}

	@Autowired
	private PageDog _pageDog;

}