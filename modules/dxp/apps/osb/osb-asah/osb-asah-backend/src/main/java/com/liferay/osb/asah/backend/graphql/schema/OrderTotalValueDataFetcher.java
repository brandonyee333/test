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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dto.OrderTotalValueDTO;
import com.liferay.osb.asah.common.dog.CommerceDashboardDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.OrderTotalValue;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.language.Field;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
@GraphQLTypeWiring(fieldName = "orderTotalValue", typeName = "QueryType")
public class OrderTotalValueDataFetcher
	extends BaseDataFetcher<List<OrderTotalValueDTO>> {

	@Override
	public List<OrderTotalValueDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		DataFetchingFieldSelectionSet dataFetchingFieldSelectionSet =
			dataFetchingEnvironment.getSelectionSet();

		Map<String, List<Field>> dataFetchingFieldSelectionSetFileds =
			dataFetchingFieldSelectionSet.get();

		Map<String, OrderTotalValue> orderTotalValue =
			_commerceDashboardDog.getOrderTotalValues(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				dataFetchingFieldSelectionSetFileds.containsKey("trend"),
				searchQueryContext.getTimeRange());

		return ListUtil.map(orderTotalValue.values(), OrderTotalValueDTO::new);
	}

	@Autowired
	private CommerceDashboardDog _commerceDashboardDog;

}