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

import com.liferay.osb.asah.backend.dto.EventAttributeDefinitionDTO;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
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
		DataFetchingEnvironment dataFetchingEnvironment) {

		String keyword = dataFetchingEnvironment.getArgument("keyword");

		EventAttributeDefinition.Type type =
			EventAttributeDefinition.Type.valueOf(
				dataFetchingEnvironment.getArgument("type"));

		Page<EventAttributeDefinition> eventAttributeDefinitionsPage =
			_eventAttributeDefinitionDog.getEventAttributeDefinitionsPage(
				keyword, dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"),
				Sort.of(dataFetchingEnvironment.getArgument("sort")), type);

		Stream<EventAttributeDefinition> stream =
			eventAttributeDefinitionsPage.stream();

		List<EventAttributeDefinitionDTO> eventAttributeDefinitionDTOs =
			stream.map(
				EventAttributeDefinitionDTO::new
			).collect(
				Collectors.toList()
			);

		return new ResultBag<>(
			eventAttributeDefinitionDTOs,
			eventAttributeDefinitionsPage.getTotalElements());
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

}