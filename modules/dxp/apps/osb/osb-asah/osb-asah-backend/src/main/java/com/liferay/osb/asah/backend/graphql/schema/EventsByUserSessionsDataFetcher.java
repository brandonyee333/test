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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dto.EventsByUserSessionDTO;
import com.liferay.osb.asah.backend.dto.UserSessionDTO;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.Tuple2;

import graphql.schema.DataFetchingEnvironment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
@GraphQLTypeWiring(fieldName = "eventsByUserSessions", typeName = "QueryType")
public class EventsByUserSessionsDataFetcher
	extends BaseDataFetcher<EventsByUserSessionDTO> {

	@Override
	public EventsByUserSessionDTO get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		Comparator<UserSessionDTO> comparator = Comparator.comparing(
			UserSessionDTO::getCreateDate);

		Map<BQSession, List<Tuple2<BQEvent, EventDefinition>>> tuple2s =
			_eventDog.searchBQEventsGroupByUserSessionId(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				Long.valueOf(dataFetchingEnvironment.getArgument("entityId")),
				dataFetchingEnvironment.getArgument("keywords"),
				dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"),
				searchQueryContext.getTimeRange());

		Set<Map.Entry<BQSession, List<Tuple2<BQEvent, EventDefinition>>>>
			entrySet = tuple2s.entrySet();

		Stream<Map.Entry<BQSession, List<Tuple2<BQEvent, EventDefinition>>>>
			stream = entrySet.stream();

		return new EventsByUserSessionDTO(
			stream.map(
				entry -> new UserSessionDTO(
					entry.getValue(), entry.getKey(), _objectMapper)
			).sorted(
				comparator.reversed()
			).collect(
				Collectors.toList()
			),
			_eventDog.countBQEvents(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				Long.valueOf(dataFetchingEnvironment.getArgument("entityId")),
				dataFetchingEnvironment.getArgument("keywords"),
				searchQueryContext.getTimeRange()));
	}

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private ObjectMapper _objectMapper;

}