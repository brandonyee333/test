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

import com.liferay.osb.asah.backend.dog.ActivityDog;
import com.liferay.osb.asah.backend.dto.ActivityDTO;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "activities", typeName = "QueryType")
public class ActivityBagDataFetcher
	implements DataFetcher<ResultBag<ActivityDTO>> {

	@Override
	public ResultBag<ActivityDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Function<Map<String, Object>, PropertyFilter> mapperFunction =
			propertyFilterMap -> {
				PropertyFilter propertyFilter = PropertyFilter.of(
					propertyFilterMap);

				propertyFilter.setPropertyNamespace("eventContext.");

				return propertyFilter;
			};

		List<PropertyFilter> eventContextPropertyFilters = ListUtil.map(
			dataFetchingEnvironment.getArgument("eventContextPropertyFilters"),
			mapperFunction);

		return _activityDog.getActivityResultBag(
			dataFetchingEnvironment.getArgument("applicationId"),
			eventContextPropertyFilters,
			dataFetchingEnvironment.getArgument("eventId"),
			dataFetchingEnvironment.getArgument("rangeKey"),
			dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("start"));
	}

	@Autowired
	private ActivityDog _activityDog;

}