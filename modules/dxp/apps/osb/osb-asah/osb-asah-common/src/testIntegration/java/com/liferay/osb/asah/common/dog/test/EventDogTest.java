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

package com.liferay.osb.asah.common.dog.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.BQEventPropertyValue;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Leslie Wong
 */
@Import(JDBCTestConfiguration.class)
public class EventDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testGetRecentEventAttributeValues() throws Exception {
		Date date1 = DateUtil.newDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Channel channel = _channelDog.addChannel("Test Channel");

		BQEventProperty bqEventProperty = new BQEventProperty(
			null, "viewDuration", "testValue1");

		bqEventProperty.setEventDate(date1);

		_eventDog.addBQEvent(
			"Page", Collections.singleton(bqEventProperty), channel.getId(),
			date1, 1L, date1, "pageUnloaded", "analyticsEventId1", 1L,
			"sessionId", "userId");

		Date date2 = DateUtil.newDate();

		bqEventProperty = new BQEventProperty(
			null, "viewDuration", "testValue2");

		bqEventProperty.setEventDate(date2);

		_eventDog.addBQEvent(
			"Page", Collections.singleton(bqEventProperty), channel.getId(),
			date2, 1L, date2, "pageUnloaded", "analyticsEventId2", 1L,
			"sessionId", "userId");

		Assertions.assertEquals(
			new ArrayList<BQEventPropertyValue>() {
				{
					add(new BQEventPropertyValue(date2, "testValue2"));
					add(new BQEventPropertyValue(date1, "testValue1"));
				}
			},
			_eventDog.getRecentEventPropertyValues(
				eventAttributeDefinition.getId(), 2));
	}

	@Test
	public void testSearchEvents() throws Exception {
		Date date = DateUtil.newDayDate();

		Channel channel = _channelDog.addChannel("Test Channel");

		_eventDog.addBQEvent(
			"Page",
			new HashSet<BQEventProperty>() {
				{
					add(
						new BQEventProperty(
							null, "viewDuration", "testValue1"));
					add(
						new BQEventProperty(
							null, "viewDuration", "testValue2"));
				}
			},
			channel.getId(), date, 1L, date, "pageViewed", "analyticsEventId1",
			1L, "sessionId", "userId");

		_eventDog.addBQEvent(
			"Page",
			new HashSet<BQEventProperty>() {
				{
					add(
						new BQEventProperty(
							null, "viewDuration", "testValue1"));
					add(
						new BQEventProperty(
							null, "viewDuration", "testValue2"));
				}
			},
			channel.getId(), date, 1L, date, "pageViewed", "analyticsEventId2",
			1L, "sessionId", "userId");

		List<BQEvent> bqEvents = _eventDog.searchEvents(
			channel.getId(), null, null, 0, 50, TimeRange.LAST_24_HOURS);

		Assertions.assertEquals(2, bqEvents.size(), bqEvents.toString());

		bqEvents.forEach(
			bqEvent -> {
				try {
					List<BQEventProperty> bqEventProperties =
						_objectMapper.readValue(
							bqEvent.getEventProperties(),
							new TypeReference<List<BQEventProperty>>() {
							});

					Assertions.assertEquals(
						2, bqEventProperties.size(),
						bqEventProperties.toString());
				}
				catch (JsonProcessingException jsonProcessingException) {
					Assertions.fail("Could not read event properties");
				}
			});
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private ObjectMapper _objectMapper;

}