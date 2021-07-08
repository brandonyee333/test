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

import com.liferay.osb.asah.backend.dto.BlockedCustomEventDefinitionDTO;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavates
 */
@Component
@GraphQLTypeWiring(
	fieldName = "unhideBlockedEventDefinitions", typeName = "MutationType"
)
public class UnhideBlockedEventDefinitionsMutationDataFetcher
	implements DataFetcher<List<BlockedCustomEventDefinitionDTO>> {

	@Override
	public List<BlockedCustomEventDefinitionDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<Long> blockedEventDefinitionIds = _getBlockedEventDefinitionIds(
			dataFetchingEnvironment);

		_eventDefinitionDog.unhideEventDefinitions(blockedEventDefinitionIds);

		return ListUtil.map(
			_eventDefinitionDog.fetchEventDefinitions(
				blockedEventDefinitionIds),
			BlockedCustomEventDefinitionDTO::new);
	}

	private List<Long> _getBlockedEventDefinitionIds(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<String> blockedEventDefinitionIds =
			dataFetchingEnvironment.getArgument("blockedEventDefinitionIds");

		return ListUtil.map(blockedEventDefinitionIds, Long::valueOf);
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}