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

import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dto.EventDefinitionDTO;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

		String eventType = dataFetchingEnvironment.getArgument("eventType");
		String keyword = dataFetchingEnvironment.getArgument("keyword");

		Map<String, String> sort = dataFetchingEnvironment.getArgument("sort");

		String sortColumn = sort.get("column");

		if (!sortColumn.equals("name") && !sortColumn.equals("displayName")) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to sort event definitions by " + sortColumn);
		}

		List<EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitions(
				eventType, keyword, dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"), sortColumn,
				sort.get("type"));

		Stream<EventDefinition> stream = eventDefinitions.stream();

		List<EventDefinitionDTO> eventDefinitionDTOs = stream.map(
			EventDefinitionDTO::new
		).collect(
			Collectors.toList()
		);

		return new ResultBag<>(
			eventDefinitionDTOs,
			_eventDefinitionDog.countEventDefinitions(eventType, keyword));
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}