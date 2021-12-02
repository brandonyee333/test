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
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.repository.EventAnalysisRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class CreateEventAnalysisMutationDataFetcherTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_eventDefinition = _eventDefinitionRepository.save(
			_createEventDefinition(10));
	}

	@AfterEach
	public void tearDown() {
		_eventAnalysisRepository.deleteAll();

		_eventDefinitionRepository.delete(_eventDefinition);
	}

	@Test
	public void testGet() {
		EventAnalysisDTO eventAnalysisDTO =
			_createEventAnalysisMutationDataFetcher.get(
				_getDataFetchingEnvironment());

		Assertions.assertNotNull(eventAnalysisDTO.getId());

		List<EventAnalysisDTO.EventAnalysisBreakdownDTO>
			eventAnalysisBreakdownDTOs =
				eventAnalysisDTO.getEventAnalysisBreakdownDTOs();

		Assertions.assertEquals(1, eventAnalysisBreakdownDTOs.size());

		List<EventAnalysisDTO.EventAnalysisFilterDTO> eventAnalysisFilterDTOs =
			eventAnalysisDTO.getEventAnalysisFilterDTOs();

		Assertions.assertEquals(1, eventAnalysisFilterDTOs.size());
	}

	private EventDefinition _createEventDefinition(int index) {
		EventDefinition eventDefinition = new EventDefinition();

		eventDefinition.setBlocked(false);
		eventDefinition.setHidden(false);
		eventDefinition.setName("Event Definition " + index);
		eventDefinition.setType(EventDefinition.Type.CUSTOM);

		return eventDefinition;
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
		arguments.put(
			"eventDefinitionId", String.valueOf(_eventDefinition.getId()));
		arguments.put("name", "Analysis 1");
		arguments.put("rangeKey", 1);
		arguments.put("userId", "20");
		arguments.put("userName", "Test");

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private CreateEventAnalysisMutationDataFetcher
		_createEventAnalysisMutationDataFetcher;

	@Autowired
	private EventAnalysisRepository _eventAnalysisRepository;

	private EventDefinition _eventDefinition;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}