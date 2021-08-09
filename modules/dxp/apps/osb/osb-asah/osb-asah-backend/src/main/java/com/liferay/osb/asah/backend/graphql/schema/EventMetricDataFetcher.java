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
import com.liferay.osb.asah.backend.model.EventMetric;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetchingEnvironment;

import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
@GraphQLTypeWiring(fieldName = "eventMetric", typeName = "QueryType")
public class EventMetricDataFetcher extends BaseDataFetcher<EventMetric> {

	@Override
	public EventMetric get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		return new EventMetric();
	}

}