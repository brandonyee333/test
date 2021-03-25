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

import com.liferay.osb.asah.backend.dto.EventDefinitionDTO;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.EventDefinition;
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
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(fieldName = "eventDefinitions", typeName = "QueryType")
public class EventDefinitionBagDataFetcher
	implements DataFetcher<ResultBag<EventDefinitionDTO>> {

	@Override
	public ResultBag<EventDefinitionDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		boolean blocked = false;

		if (dataFetchingEnvironment.containsArgument("blocked")) {
			blocked = Boolean.parseBoolean(
				dataFetchingEnvironment.getArgument("blocked"));
		}

		EventDefinition.Type type = EventDefinition.Type.valueOf(
			dataFetchingEnvironment.getArgument("eventType"));
		String keyword = dataFetchingEnvironment.getArgument("keyword");

		Page<EventDefinition> eventDefinitionsPage =
			_eventDefinitionDog.getEventDefinitionsPage(
				blocked, keyword, dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"),
				Sort.of(dataFetchingEnvironment.getArgument("sort")), type);

		Stream<EventDefinition> stream = eventDefinitionsPage.stream();

		List<EventDefinitionDTO> eventDefinitionDTOs = stream.map(
			EventDefinitionDTO::new
		).collect(
			Collectors.toList()
		);

		return new ResultBag<>(
			eventDefinitionDTOs, eventDefinitionsPage.getTotalElements());
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}