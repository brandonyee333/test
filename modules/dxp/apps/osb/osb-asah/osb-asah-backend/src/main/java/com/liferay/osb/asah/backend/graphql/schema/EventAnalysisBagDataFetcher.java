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
import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "eventAnalyses", typeName = "QueryType")
public class EventAnalysisBagDataFetcher
	implements DataFetcher<ResultBag<EventAnalysisDTO>> {

	@Override
	public ResultBag<EventAnalysisDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Page<EventAnalysis> eventAnalysesPage =
			_eventAnalysisDog.getEventAnalysesPage(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				dataFetchingEnvironment.getArgument("keyword"),
				dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"),
				Sort.of(dataFetchingEnvironment.getArgument("sort")));

		Stream<EventAnalysis> stream = eventAnalysesPage.stream();

		List<EventAnalysisDTO> eventAnalysisDTOs = stream.map(
			EventAnalysisDTO::new
		).collect(
			Collectors.toList()
		);

		return new ResultBag<>(
			eventAnalysisDTOs, eventAnalysesPage.getTotalElements());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}