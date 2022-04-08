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

import com.liferay.osb.asah.common.dog.EventPropertyDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.ResultBag;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 */
@Component
@GraphQLTypeWiring(fieldName = "eventAttributeValues", typeName = "QueryType")
public class EventAttributeValueBagDataFetcher
	implements DataFetcher<ResultBag<String>> {

	@Override
	public ResultBag<String> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Page<String> bqEventPropertyValuePage =
			_eventPropertyDog.getBQEventPropertyValuePage(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				Long.valueOf(
					dataFetchingEnvironment.getArgument(
						"eventAttributeDefinitionId")),
				Long.valueOf(
					dataFetchingEnvironment.getArgument("eventDefinitionId")),
				dataFetchingEnvironment.getArgument("keywords"),
				dataFetchingEnvironment.getArgument("size"),
				dataFetchingEnvironment.getArgument("start"));

		return new ResultBag<>(
			bqEventPropertyValuePage.getContent(),
			bqEventPropertyValuePage.getTotalElements());
	}

	@Autowired
	private EventPropertyDog _eventPropertyDog;

}