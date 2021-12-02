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
import com.liferay.osb.asah.backend.dto.EventAnalysisDTO;
import com.liferay.osb.asah.backend.graphql.schema.CreateEventAnalysisMutationDataFetcher;
import com.liferay.osb.asah.backend.graphql.schema.DeleteEventAnalysisMutationDataFetcher;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.repository.EventAnalysisRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class DeleteEventAnalysisMutationDataFetcherTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@AfterEach
	public void tearDown() {
		_eventAnalysisRepository.deleteAll();
	}

	@Test
	public void testGet() {
		EventAnalysisDTO eventAnalysisDTO =
			_createEventAnalysisMutationDataFetcher.get(
				_getDataFetchingEnvironment());

		Assertions.assertEquals(1, _eventAnalysisRepository.count());

		_deleteEventAnalysisMutationDataFetcher.get(
			_getDeleteDataFetchingEnvironment(eventAnalysisDTO.getId()));

		Assertions.assertEquals(0, _eventAnalysisRepository.count());
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment() {
		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("analysisType", "TOTAL");
		arguments.put("channelId", "1");
		arguments.put("compareToPrevious", Boolean.FALSE);
		arguments.put(
			"eventAnalysisBreakdowns",
			Collections.singletonList(
				new HashMap<String, Object>() {
					{
						put("attributeId", "100");
						put("attributeType", AttributeType.EVENT);
						put("binSize", 10);
						put("dataType", "STRING");
						put("dateGrouping", DateGrouping.MONTH);
						put("sortType", "ASC");
					}
				}));
		arguments.put(
			"eventAnalysisFilters",
			Collections.singletonList(
				new HashMap<String, Object>() {
					{
						put("attributeId", "100");
						put("attributeType", AttributeType.EVENT);
						put("dataType", "STRING");
						put("operator", "eq");
						put("values", Arrays.asList("one", "two", "three"));
					}
				}));
		arguments.put("eventDefinitionId", "10");
		arguments.put("name", "Analysis 1");
		arguments.put("rangeKey", 1);
		arguments.put("userId", "20");
		arguments.put("userName", "Test");

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	private DataFetchingEnvironment _getDeleteDataFetchingEnvironment(
		String eventAnalysisId) {

		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put(
			"eventAnalysisIds", Collections.singletonList(eventAnalysisId));

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private CreateEventAnalysisMutationDataFetcher
		_createEventAnalysisMutationDataFetcher;

	@Autowired
	private DeleteEventAnalysisMutationDataFetcher
		_deleteEventAnalysisMutationDataFetcher;

	@Autowired
	private EventAnalysisRepository _eventAnalysisRepository;

}