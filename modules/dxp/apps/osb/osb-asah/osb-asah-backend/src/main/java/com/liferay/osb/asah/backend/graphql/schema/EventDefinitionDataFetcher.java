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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(fieldName = "eventDefinition", typeName = "QueryType")
public class EventDefinitionDataFetcher
	implements DataFetcher<EventDefinitionDTO> {

	@Override
	public EventDefinitionDTO get(DataFetchingEnvironment environment) {
		int id = environment.getArgument("id");
		String name = environment.getArgument("name");

		EventDefinition eventDefinition;

		if (name != null) {
			eventDefinition = _eventDefinitionDog.getEventDefinitionByName(
				name);
		}
		else {
			eventDefinition = _eventDefinitionDog.getEventDefinition(
				Long.valueOf(id));
		}

		return new EventDefinitionDTO(eventDefinition);
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}