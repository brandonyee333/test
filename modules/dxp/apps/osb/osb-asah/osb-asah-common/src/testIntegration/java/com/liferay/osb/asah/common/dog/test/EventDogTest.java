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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public void testAddEvent() {
		Date date = DateUtil.newDayDate();

		Channel channel = _channelDog.addChannel("Test Channel");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", channel.getId(), date, 123456L,
			Collections.emptySet(), date, eventDefinition.getId(), 1L,
			"sessionId", "abcdef");

		Assertions.assertEquals(
			"analyticsEventId", event.getAnalyticsEventId());
		Assertions.assertEquals("Page", event.getApplicationId());
		Assertions.assertEquals(channel.getId(), event.getChannelId());
		Assertions.assertEquals(date, event.getCreateDate());
		Assertions.assertEquals(Long.valueOf(123456), event.getDataSourceId());
		Assertions.assertEquals(date, event.getEventDate());
		Assertions.assertEquals(
			eventDefinition.getId(), event.getEventDefinitionId());
		Assertions.assertEquals("sessionId", event.getSessionId());
		Assertions.assertEquals("abcdef", event.getUserId());

		Assertions.assertNotNull(event.getId());
	}

	@Test
	public void testAddEventWithAttribute() {
		Channel channel = _channelDog.addChannel("Test Channel");

		Date date = DateUtil.newDayDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttribute eventAttribute = new EventAttribute(
			null, eventAttributeDefinition.getId(), "987654321");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", channel.getId(), date, 1L,
			Collections.singleton(eventAttribute), date,
			eventDefinition.getId(), 1L, "sessionId", "abcdef");

		Assertions.assertEquals(
			eventAttributeDefinition.getId(),
			eventAttribute.getEventAttributeDefinitionId());
		Assertions.assertEquals("987654321", eventAttribute.getValue());

		Set<EventAttribute> eventAttributes = event.getEventAttributes();

		Assertions.assertEquals(
			1, eventAttributes.size(), eventAttributes.toString());

		Assertions.assertNotNull(eventAttribute.getId());
	}

	@Test
	public void testGetRecentEventAttributeValues() {
		Date date1 = DateUtil.newDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Channel channel = _channelDog.addChannel("Test Channel");

		Long eventAttributeDefinitionId = eventAttributeDefinition.getId();

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		EventAttribute eventAttribute = new EventAttribute(
			null, eventAttributeDefinitionId, "testValue1");

		eventAttribute.setEventDate(date1);

		_eventDog.addEvent(
			"analyticsEventId1", "Page", channel.getId(), date1, 1L,
			Collections.singleton(eventAttribute), date1,
			eventDefinition.getId(), 1L, "sessionId", "userId");

		Date date2 = DateUtil.newDate();

		eventAttribute = new EventAttribute(
			null, eventAttributeDefinitionId, "testValue2");

		eventAttribute.setEventDate(date2);

		_eventDog.addEvent(
			"analyticsEventId2", "Page", channel.getId(), date2, 1L,
			Collections.singleton(eventAttribute), date2,
			eventDefinition.getId(), 1L, "sessionId", "userId");

		Assertions.assertEquals(
			new ArrayList<EventAttributeValue>() {
				{
					add(new EventAttributeValue(date2, "testValue2"));
					add(new EventAttributeValue(date1, "testValue1"));
				}
			},
			_eventDog.getRecentEventAttributeValues(
				eventAttributeDefinition.getId(), 2));
	}

	@Test
	public void testSearchEvents() {
		Date date = DateUtil.newDayDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Channel channel = _channelDog.addChannel("Test Channel");

		Long eventAttributeDefinitionId = eventAttributeDefinition.getId();

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		_eventDog.addEvent(
			"analyticsEventId1", "Page", channel.getId(), date, 1L,
			new HashSet<EventAttribute>() {
				{
					add(
						new EventAttribute(
							null, eventAttributeDefinitionId, "testValue1"));
					add(
						new EventAttribute(
							null, eventAttributeDefinitionId, "testValue2"));
				}
			},
			date, eventDefinition.getId(), 1L, "sessionId", "userId");

		_eventDog.addEvent(
			"analyticsEventId2", "Page", channel.getId(), date, 1L,
			new HashSet<EventAttribute>() {
				{
					add(
						new EventAttribute(
							null, eventAttributeDefinitionId, "testValue1"));
					add(
						new EventAttribute(
							null, eventAttributeDefinitionId, "testValue2"));
				}
			},
			date, eventDefinition.getId(), 1L, "sessionId", "userId");

		List<Event> events = _eventDog.searchEvents(
			channel.getId(), 1L, null, 0, 50, TimeRange.LAST_24_HOURS);

		Assertions.assertEquals(2, events.size(), events.toString());

		events.forEach(
			event -> {
				Set<EventAttribute> eventAttributes =
					event.getEventAttributes();

				Assertions.assertEquals(
					2, eventAttributes.size(), eventAttributes.toString());
			});
	}

	@Test
	public void testUpdateEventsIndividualId() {
		Date date = DateUtil.newDayDate();

		Channel channel = _channelDog.addChannel("Test Channel");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		Event originalEvent = _eventDog.addEvent(
			"analyticsEventId", "Page", channel.getId(), date, 123456L,
			Collections.emptySet(), date, eventDefinition.getId(), 1L,
			"sessionId", "abcdef");

		Assertions.assertEquals(
			Long.valueOf(1), originalEvent.getIndividualId());

		_eventDog.updateEventsIndividualId(123456L, 2L, "abcdef");

		Event updatedEvent = _eventDog.fetchEvent(originalEvent.getId());

		Assertions.assertEquals(
			updatedEvent.getIndividualId(), Long.valueOf(2));
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}