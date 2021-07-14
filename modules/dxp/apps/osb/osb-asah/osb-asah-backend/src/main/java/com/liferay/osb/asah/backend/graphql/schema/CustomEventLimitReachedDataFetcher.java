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

import com.liferay.osb.asah.common.constants.EventDefinitionConstants;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
@GraphQLTypeWiring(
	fieldName = "customEventLimitReached", typeName = "QueryType"
)
public class CustomEventLimitReachedDataFetcher
	implements DataFetcher<Boolean> {

	@Override
	public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
		Long blockedEventDefinitionsCount =
			_eventDefinitionDog.countEventDefinitions(
				true, EventDefinition.BlockedReasonType.THRESHOLD_OVERFLOW,
				null, null, EventDefinition.Type.CUSTOM);
		Long eventDefinitionsCount = _eventDefinitionDog.countEventDefinitions(
			false, null, null, EventDefinition.Type.CUSTOM);

		return (blockedEventDefinitionsCount > 0) &&
			   (eventDefinitionsCount >=
				   EventDefinitionConstants.EVENT_DEFINITION_THRESHOLD);
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}