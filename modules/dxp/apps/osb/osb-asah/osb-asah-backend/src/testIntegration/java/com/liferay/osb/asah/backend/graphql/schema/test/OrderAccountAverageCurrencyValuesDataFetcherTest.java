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

package com.liferay.osb.asah.backend.graphql.schema.test;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dto.CurrencyValueDTO;
import com.liferay.osb.asah.backend.graphql.schema.OrderAccountAverageCurrencyValuesDataFetcher;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import graphql.language.Field;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Riccardo Ferrari
 */
public class OrderAccountAverageCurrencyValuesDataFetcherTest
	extends BaseOrderDataFetcherTestCase {

	@BQSQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGet() {
		List<CurrencyValueDTO> currencyValueDTOs =
			_orderAccountAverageCurrencyValuesDataFetcher.get(
				getDataFetchingEnvironment(
					Arrays.asList(
						new Field("currencyCode"), new Field("value"))),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_7_DAYS);
					}
				});

		assertCurrencyValueDTO(currencyValueDTOs, 2, false);
	}

	@BQSQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetWithTrend() {
		List<CurrencyValueDTO> currencyValueDTOs =
			_orderAccountAverageCurrencyValuesDataFetcher.get(
				getDataFetchingEnvironment(
					Arrays.asList(
						new Field("currencyCode"), new Field("trend"),
						new Field("value"))),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_7_DAYS);
					}
				});

		assertCurrencyValueDTO(currencyValueDTOs, 2, true);
	}

	@Autowired
	private OrderAccountAverageCurrencyValuesDataFetcher
		_orderAccountAverageCurrencyValuesDataFetcher;

}