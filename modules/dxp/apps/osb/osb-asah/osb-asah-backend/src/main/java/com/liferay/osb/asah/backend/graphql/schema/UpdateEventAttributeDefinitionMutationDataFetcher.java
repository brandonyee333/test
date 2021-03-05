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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(
	fieldName = "updateEventAttributeDefinition", typeName = "MutationType"
)
public class UpdateEventAttributeDefinitionMutationDataFetcher
	implements DataFetcher<EventAttributeDefinitionDTO> {

	@Override
	public EventAttributeDefinitionDTO get(
		DataFetchingEnvironment environment) {

		return new EventAttributeDefinitionDTO(
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				EventAttributeDefinition.DataType.valueOf(
					environment.getArgument("dataType")),
				environment.getArgument("description"),
				environment.getArgument("displayName"),
				Long.valueOf(
					environment.getArgument("eventAttributeDefinitionId")),
				null, null));
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

}