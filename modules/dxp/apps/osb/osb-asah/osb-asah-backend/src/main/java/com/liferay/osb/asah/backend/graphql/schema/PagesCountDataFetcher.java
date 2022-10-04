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

import com.liferay.osb.asah.backend.dog.PageDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "pagesCount", typeName = "QueryType")
public class PagesCountDataFetcher implements DataFetcher<Long> {

	@Override
	public Long get(DataFetchingEnvironment dataFetchingEnvironment) {
		String fromDateString = dataFetchingEnvironment.getArgument("fromDate");
		String toDateString = dataFetchingEnvironment.getArgument("toDate");

		return _pageDog.getViewsMetricValue(
			null, LocalDateTime.parse(fromDateString),
			LocalDateTime.parse(toDateString));
	}

	@Autowired
	private PageDog _pageDog;

}