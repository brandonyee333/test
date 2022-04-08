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
import com.liferay.osb.asah.backend.dto.EventAttributeValueDTO;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(
	fieldName = "recentValues", typeName = "EventAttributeDefinition"
)
public class EventAttributeValuesDataFetcher
	implements DataFetcher<List<EventAttributeValueDTO>> {

	@Override
	public List<EventAttributeValueDTO> get(
		DataFetchingEnvironment environment) {

		EventAttributeDefinitionDTO eventAttributeDefinitionDTO =
			environment.getSource();

		return ListUtil.map(
			_eventDog.getRecentBQEventPropertyValues(
				Long.valueOf(eventAttributeDefinitionDTO.getId()), 10),
			EventAttributeValueDTO::new);
	}

	@Autowired
	private EventDog _eventDog;

}