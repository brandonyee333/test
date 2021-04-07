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
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(
	fieldName = "blockedCustomEventDefinitions", typeName = "QueryType"
)
public class BlockedCustomEventDefinitionBagDataFetcher
	implements DataFetcher<ResultBag<BlockedCustomEventDefinitionDTO>> {

	@Override
	public ResultBag<BlockedCustomEventDefinitionDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		String keyword = dataFetchingEnvironment.getArgument("keyword");

		Page<EventDefinition> eventDefinitionsPage =
			_eventDefinitionDog.getEventDefinitionsPage(
				true, keyword, dataFetchingEnvironment.getArgument("page"),
				dataFetchingEnvironment.getArgument("size"),
				Sort.of(dataFetchingEnvironment.getArgument("sort")),
				EventDefinition.Type.CUSTOM);

		return new ResultBag<>(
			ListUtil.map(
				eventDefinitionsPage.getContent(),
				BlockedCustomEventDefinitionDTO::new),
			eventDefinitionsPage.getTotalElements());
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}