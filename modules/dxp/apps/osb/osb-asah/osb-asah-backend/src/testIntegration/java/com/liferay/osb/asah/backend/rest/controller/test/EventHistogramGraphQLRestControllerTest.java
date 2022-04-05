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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
public class EventHistogramGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "event_histogram_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "event_histogram_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "event_histogram_query.graphql";
	}

	@BeforeEach
	public void setUp() throws Exception {
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

		_timeZone = TimeZone.getDefault();

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		_createEvent(
			eventAttributeDefinitions,
			DateUtil.toUTCDate("2021-07-01T00:00:00.000Z"), "assetClicked");
		_createEvent(
			eventAttributeDefinitions,
			DateUtil.toUTCDate("2021-07-02T00:00:00.000Z"), "assetDownloaded");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_bqEventRepository.deleteAll();

		TimeZone.setDefault(_timeZone);
	}

	private void _createEvent(
			List<EventAttributeDefinition> eventAttributeDefinitions,
			Date eventDate, String eventDefinitionName)
		throws Exception {

		Stream<EventAttributeDefinition> stream =
			eventAttributeDefinitions.stream();

		_eventDog.addBQEvent(
			"Page",
			stream.map(
				eventAttributeDefinition -> new BQEventProperty(
					null, eventDefinitionName,
					eventAttributeDefinition.getName() + "Value")
			).collect(
				Collectors.toSet()
			),
			1L, eventDate, 1L, eventDate, eventDefinitionName,
			RandomTestUtil.randomId(), "sessionId", "userId");
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

	private TimeZone _timeZone;

}