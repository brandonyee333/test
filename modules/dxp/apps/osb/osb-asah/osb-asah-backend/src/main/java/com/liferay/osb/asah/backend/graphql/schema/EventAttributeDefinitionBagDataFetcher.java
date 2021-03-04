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

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dto.EventAttributeDefinitionDTO;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.ResultBag;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(
	fieldName = "eventAttributeDefinitions", typeName = "QueryType"
)
public class EventAttributeDefinitionBagDataFetcher
	implements DataFetcher<ResultBag<EventAttributeDefinitionDTO>> {

	@Override
	public ResultBag<EventAttributeDefinitionDTO> get(
		DataFetchingEnvironment environment) {

		String keyword = environment.getArgument("keyword");

		Map<String, String> sort = environment.getArgument("sort");

		List<EventAttributeDefinition> eventDefinitions =
			_eventAttributeDefinitionDog.getEventAttributeDefinitions(
				keyword, environment.getArgument("page"),
				environment.getArgument("size"), sort.get("column"),
				sort.get("type"));

		Stream<EventAttributeDefinition> stream = eventDefinitions.stream();

		List<EventAttributeDefinitionDTO> eventAttributeDefinitionDTOs =
			stream.map(
				EventAttributeDefinitionDTO::new
			).collect(
				Collectors.toList()
			);

		return new ResultBag<>(
			eventAttributeDefinitionDTOs,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				keyword));
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

}