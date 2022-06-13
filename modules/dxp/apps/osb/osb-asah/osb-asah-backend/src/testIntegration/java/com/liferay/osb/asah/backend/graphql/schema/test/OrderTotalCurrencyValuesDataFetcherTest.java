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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dto.CurrencyValueDTO;
import com.liferay.osb.asah.backend.graphql.schema.OrderTotalCurrencyValuesDataFetcher;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import graphql.language.Field;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Riccardo Ferrari
 */
@Import(JDBCTestConfiguration.class)
public class OrderTotalCurrencyValuesDataFetcherTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = new DataSource("Liferay Italy");

		dataSource.setCredentialType("Token Authentication");
		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(123L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		dataSource = _dataSourceRepository.save(dataSource);

		Channel channel = new Channel("channel");

		channel.setId(11L);
		channel.setIsNew(true);

		channel.addChannelDataSource(
			new ChannelDataSource(dataSource.getId(), null));

		_channelRepository.save(channel);
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGet() {
		List<CurrencyValueDTO> currencyValueDTOs =
			_orderTotalCurrencyValuesDataFetcher.get(
				_getDataFetchingEnvironment(
					Arrays.asList(
						new Field("currencyCode"), new Field("value"))),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_7_DAYS);
					}
				});

		Assertions.assertNotNull(currencyValueDTOs);

		Assertions.assertEquals(2, currencyValueDTOs.size());

		CurrencyValueDTO currencyValueDTO = currencyValueDTOs.get(0);

		Assertions.assertNull(currencyValueDTO.getTrend());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetWithTrend() {
		List<CurrencyValueDTO> currencyValueDTOs =
			_orderTotalCurrencyValuesDataFetcher.get(
				_getDataFetchingEnvironment(
					Arrays.asList(
						new Field("currencyCode"), new Field("trend"),
						new Field("value"))),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_7_DAYS);
					}
				});

		Assertions.assertNotNull(currencyValueDTOs);

		Assertions.assertEquals(2, currencyValueDTOs.size());

		CurrencyValueDTO currencyValueDTO = currencyValueDTOs.get(0);

		Assertions.assertNotNull(currencyValueDTO.getTrend());
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment(
		List<Field> fields) {

		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("channelId", "11");
		arguments.put("rangeKey", 7);

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		Stream<Field> stream = fields.stream();

		dataFetchingEnvironmentBuilder.selectionSet(
			() -> stream.collect(
				Collectors.toMap(Field::getName, Arrays::asList)));

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private OrderTotalCurrencyValuesDataFetcher
		_orderTotalCurrencyValuesDataFetcher;

}