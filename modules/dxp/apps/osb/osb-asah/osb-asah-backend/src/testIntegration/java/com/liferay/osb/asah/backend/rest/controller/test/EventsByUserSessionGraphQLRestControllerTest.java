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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventsByUserSessionGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "events_by_session_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "events_by_sessions_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "events_by_session_query.graphql";
	}

	@Before
	public void setUp() {
		List<EventAttributeDefinition> eventAttributeDefinitions =
			Arrays.asList(
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("canonicalUrl"),
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("pageDescription"),
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("pageKeywords"),
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("pageTitle"),
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("referrer"),
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName("url"));

		_createEvent(eventAttributeDefinitions, "assetClicked");
		_createEvent(eventAttributeDefinitions, "assetDownloaded");

		_cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			JSONUtil.put(
				"browserName", "Chrome"
			).put(
				"contentLanguageId", "contentLanguageId"
			).put(
				"deviceType", "Chrome"
			).put(
				"id", "sessionId"
			).put(
				"languageId", "pt-BR"
			).put(
				"timezoneOffset", "-3"
			));
	}

	@After
	public void tearDown() throws Exception {
		_cerebroInfoElasticsearchInvoker.delete("user-sessions", "sessionId");
		_eventRepository.deleteAll();
	}

	private void _createEvent(
		List<EventAttributeDefinition> eventAttributeDefinitions,
		String eventDefinitionName) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(eventDefinitionName);

		Stream<EventAttributeDefinition> stream =
			eventAttributeDefinitions.stream();

		_eventDog.addEvent(
			RandomTestUtil.randomId(), "Page", 1L, new Date(), 1L,
			stream.map(
				eventAttributeDefinition -> new EventAttribute(
					null, eventAttributeDefinition.getId(),
					eventAttributeDefinition.getName() + "Value")
			).collect(
				Collectors.toSet()
			),
			new Date(), eventDefinition.getId(), 1L, "sessionId", "userId");
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

}