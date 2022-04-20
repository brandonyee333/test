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
import com.liferay.osb.asah.backend.dto.BQEventDTO;
import com.liferay.osb.asah.backend.dto.EventsByUserSessionDTO;
import com.liferay.osb.asah.backend.dto.UserSessionDTO;
import com.liferay.osb.asah.backend.graphql.schema.EventsByUserSessionsDataFetcher;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
public class EventsByUserSessionsDataFetcherTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		_createEvent("assetClicked");
		_createEvent("assetDownloaded");

		_userSessionDog.addBQSession(1L, "sessionId", new Date(), new Date());
	}

	@AfterEach
	public void tearDown() {
		_bqEventRepository.deleteAll();
		_bqSessionRepository.deleteAll();
	}

	@Test
	public void testGet() {
		EventsByUserSessionDTO eventsByUserSessionDTO =
			_eventsByUserSessionsDataFetcher.get(
				_getDataFetchingEnvironment(),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_24_HOURS);
					}
				});

		Assertions.assertEquals(2, eventsByUserSessionDTO.getTotalEvents());

		List<UserSessionDTO> userSessionDTOs =
			eventsByUserSessionDTO.getUserSessionDTOs();

		Assertions.assertEquals(
			1, userSessionDTOs.size(), userSessionDTOs.toString());

		UserSessionDTO userSessionDTO = userSessionDTOs.get(0);

		List<BQEventDTO> bqEventDTOs = userSessionDTO.getBQEventDTOs();

		Assertions.assertEquals(2, bqEventDTOs.size(), bqEventDTOs.toString());

		Assertions.assertEquals("pt-BR", userSessionDTO.getLanguageId());

		bqEventDTOs.forEach(
			bqEventDTO -> Assertions.assertEquals(
				"canonicalUrlValue", bqEventDTO.getCanonicalUrl()));
	}

	private void _createEvent(String eventDefinitionName) throws Exception {
		_eventDog.addBQEvent(
			"Page",
			new HashSet<BQEventProperty>() {
				{
					add(
						new BQEventProperty(
							null, "viewDuration", "viewDurationValue"));
				}
			},
			null, "canonicalUrlValue", 1L, null, null, "{}", null, new Date(),
			1L, null, null, new Date(), eventDefinitionName, null,
			RandomTestUtil.randomId(), null, "pt-BR", null, null, null, null,
			"sessionId", null, null, null, "userId", null);
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment() {
		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("channelId", "1");
		arguments.put("entityId", "1");
		arguments.put("page", 0);
		arguments.put("size", 10);

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private EventsByUserSessionsDataFetcher _eventsByUserSessionsDataFetcher;

	@Autowired
	private UserSessionDog _userSessionDog;

}