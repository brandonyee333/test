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
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(
	fieldName = "unhideEventDefinitions", typeName = "MutationType"
)
public class UnhideEventDefinitionsMutationDataFetcher
	implements DataFetcher<List<EventDefinitionDTO>> {

	@Override
	public List<EventDefinitionDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<Long> eventDefinitionIds = _getEventDefinitionIds(
			dataFetchingEnvironment);

		_eventDefinitionDog.unhideEventDefinitions(
			ListUtil.map(eventDefinitionIds, Long::valueOf));

		return ListUtil.map(
			_eventDefinitionDog.fetchEventDefinitions(eventDefinitionIds),
			EventDefinitionDTO::new);
	}

	private List<Long> _getEventDefinitionIds(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<String> eventDefinitionIds = dataFetchingEnvironment.getArgument(
			"eventDefinitionIds");

		return ListUtil.map(eventDefinitionIds, Long::valueOf);
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}