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

import com.liferay.osb.asah.backend.dog.EventAnalysisDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.EventAnalysis;

import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "eventAnalysis", typeName = "QueryType")
public class EventAnalysisDataFetcher extends BaseDataFetcher<EventAnalysis> {

	@Override
	public EventAnalysis get(
		DataFetchingEnvironment environment,
		SearchQueryContext searchQueryContext) {

		return _eventAnalysisDog.getEventAnalysis(
			AnalysisType.valueOf(environment.getArgument("analysisType")),
			Long.valueOf(environment.getArgument("channelId")),
			Long.valueOf(environment.getArgument("eventDefinitionId")),
			searchQueryContext.getTimeRange());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}