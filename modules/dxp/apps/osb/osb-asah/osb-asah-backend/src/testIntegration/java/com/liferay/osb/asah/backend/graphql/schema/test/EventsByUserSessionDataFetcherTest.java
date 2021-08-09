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
import com.liferay.osb.asah.backend.dto.EventDTO;
import com.liferay.osb.asah.backend.dto.UserSessionDTO;
import com.liferay.osb.asah.backend.graphql.schema.EventsByUserSessionDataFetcher;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class EventsByUserSessionDataFetcherTest {

	@Before
	public void setUp() {
		EventAttributeDefinition canonicalUrlEventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"canonicalUrl");
		EventAttributeDefinition viewDurationEventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		_createEvent(
			"assetClicked", canonicalUrlEventAttributeDefinition.getId(),
			viewDurationEventAttributeDefinition.getId());
		_createEvent(
			"assetDownloaded", canonicalUrlEventAttributeDefinition.getId(),
			viewDurationEventAttributeDefinition.getId());

		_cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			JSONUtil.put(
				"id", "sessionId"
			).put(
				"languageId", "pt-BR"
			));
	}

	@After
	public void tearDown() throws Exception {
		_cerebroInfoElasticsearchInvoker.delete("user-sessions", "sessionId");
		_eventRepository.deleteAll();
	}

	@Test
	public void testGet() {
		List<UserSessionDTO> userSessionDTOs =
			_eventsByUserSessionDataFetcher.get(
				_getDataFetchingEnvironment(),
				new SearchQueryContext() {
					{
						setTimeRange(TimeRange.LAST_24_HOURS);
					}
				});

		Assert.assertEquals(
			userSessionDTOs.toString(), 1, userSessionDTOs.size());

		UserSessionDTO userSessionDTO = userSessionDTOs.get(0);

		Assert.assertEquals("pt-BR", userSessionDTO.getLanguageId());

		List<EventDTO> eventDTOs = userSessionDTO.getEventDTOs();

		Assert.assertEquals(eventDTOs.toString(), 2, eventDTOs.size());

		eventDTOs.forEach(
			eventDTO -> Assert.assertEquals(
				"canonicalUrlValue", eventDTO.getCanonicalUrl()));
	}

	private void _createEvent(
		String eventDefinitionName, Long globalEventAttributeDefinitionId,
		Long localEventAttributeDefinitionId) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(eventDefinitionName);

		_eventDog.addEvent(
			RandomTestUtil.randomId(), "Page", 1L, new Date(), 1L,
			new HashSet<EventAttribute>() {
				{
					add(
						new EventAttribute(
							null, globalEventAttributeDefinitionId,
							"canonicalUrlValue"));
					add(
						new EventAttribute(
							null, localEventAttributeDefinitionId,
							"viewDurationValue"));
				}
			},
			new Date(), eventDefinition.getId(), 1L, "sessionId", "userId");
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private EventsByUserSessionDataFetcher _eventsByUserSessionDataFetcher;

}