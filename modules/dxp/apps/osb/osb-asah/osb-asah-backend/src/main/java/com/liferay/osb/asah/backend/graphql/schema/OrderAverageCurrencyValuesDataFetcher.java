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
import com.liferay.osb.asah.backend.dto.CurrencyValueDTO;
import com.liferay.osb.asah.common.dog.CommerceDashboardDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.CurrencyValue;
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
@GraphQLTypeWiring(
	fieldName = "orderAverageCurrencyValues", typeName = "QueryType"
)
public class OrderAverageCurrencyValuesDataFetcher
	extends BaseDataFetcher<List<CurrencyValueDTO>> {

	@Override
	public List<CurrencyValueDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		DataFetchingFieldSelectionSet dataFetchingFieldSelectionSet =
			dataFetchingEnvironment.getSelectionSet();

		Map<String, List<Field>> dataFetchingFieldSelectionSetFields =
			dataFetchingFieldSelectionSet.get();

		Map<String, CurrencyValue> orderAverageCurrencyValues =
			_commerceDashboardDog.getOrderAverageCurrencyValues(
				Long.valueOf(dataFetchingEnvironment.getArgument("channelId")),
				dataFetchingFieldSelectionSetFields.containsKey("trend"),
				searchQueryContext.getTimeRange());

		return ListUtil.map(
			orderAverageCurrencyValues.values(), CurrencyValueDTO::new);
	}

	@Autowired
	private CommerceDashboardDog _commerceDashboardDog;

}