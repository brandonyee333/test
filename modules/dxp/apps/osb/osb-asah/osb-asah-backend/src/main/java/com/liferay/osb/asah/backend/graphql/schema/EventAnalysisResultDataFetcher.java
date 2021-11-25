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
import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.EventAnalysisResult;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "eventAnalysis", typeName = "QueryType")
public class EventAnalysisResultDataFetcher
	extends BaseDataFetcher<EventAnalysisResult> {

	@Override
	public EventAnalysisResult get(
		DataFetchingEnvironment environment,
		SearchQueryContext searchQueryContext) {

		List<EventAnalysisBreakdown> eventAnalysisBreakdowns = ListUtil.map(
			environment.getArgument("eventAnalysisBreakdowns"),
			eventAnalysisBreakdown -> new EventAnalysisBreakdown(
				(Map<String, Object>)eventAnalysisBreakdown));
		List<EventAnalysisFilter> eventAnalysisFilters = ListUtil.map(
			environment.getArgument("eventAnalysisFilters"),
			eventAnalysisFilter -> new EventAnalysisFilter(
				(Map<String, Object>)eventAnalysisFilter));

		return _eventAnalysisDog.getEventAnalysisResult(
			AnalysisType.valueOf(environment.getArgument("analysisType")),
			Long.valueOf(environment.getArgument("channelId")),
			environment.getArgument("compareToPrevious"),
			eventAnalysisBreakdowns, eventAnalysisFilters,
			Long.valueOf(environment.getArgument("eventDefinitionId")),
			environment.getArgument("page"), environment.getArgument("size"),
			searchQueryContext.getTimeRange());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}