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

import com.liferay.osb.asah.backend.dto.EventAnalysisDTO;
import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "createEventAnalysis", typeName = "MutationType")
public class CreateEventAnalysisMutationDataFetcher
	implements DataFetcher<EventAnalysisDTO> {

	@Override
	public EventAnalysisDTO get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<EventAnalysisBreakdown> eventAnalysisBreakdowns = ListUtil.map(
			dataFetchingEnvironment.getArgument("eventAnalysisBreakdowns"),
			eventAnalysisBreakdown -> new EventAnalysisBreakdown(
				(Map<String, Object>)eventAnalysisBreakdown));
		List<EventAnalysisFilter> eventAnalysisFilters = ListUtil.map(
			dataFetchingEnvironment.getArgument("eventAnalysisFilters"),
			eventAnalysisFilter -> new EventAnalysisFilter(
				(Map<String, Object>)eventAnalysisFilter));

		TimeRange timeRange = null;

		if (dataFetchingEnvironment.containsArgument("rangeEnd") &&
			dataFetchingEnvironment.containsArgument("rangeStart")) {

			timeRange = TimeRange.of(
				LocalDate.parse(
					dataFetchingEnvironment.getArgument("rangeEnd")),
				LocalDate.parse(
					dataFetchingEnvironment.getArgument("rangeStart")));
		}
		else if (dataFetchingEnvironment.containsArgument("rangeKey")) {
			timeRange = TimeRange.of(
				(int)dataFetchingEnvironment.getArgument("rangeKey"));
		}

		return new EventAnalysisDTO(
			_eventAnalysisDog.addEventAnalysis(
				AnalysisType.valueOf(
					dataFetchingEnvironment.getArgument("analysisType")),
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				dataFetchingEnvironment.getArgument("compareToPrevious"),
				eventAnalysisBreakdowns, eventAnalysisFilters,
				Long.valueOf(
					dataFetchingEnvironment.getArgument("eventDefinitionId")),
				timeRange,
				Long.valueOf(dataFetchingEnvironment.getArgument("userId")),
				dataFetchingEnvironment.getArgument("userName")));
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}