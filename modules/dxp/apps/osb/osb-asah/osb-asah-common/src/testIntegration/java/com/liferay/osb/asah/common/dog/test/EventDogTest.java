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
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventDogTest {

	@Test
	public void testAddEvent() {
		Date date = DateUtil.newDayDate();

		Channel channel = _channelDog.addChannel("Test Channel");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", "http://localhost:8080",
			channel.getId(), date, "123456", Collections.emptySet(), date,
			eventDefinition.getId(), 1L, "http://localhost:8080/test",
			"abcdef");

		Assert.assertEquals("analyticsEventId", event.getAnalyticsEventId());
		Assert.assertEquals("Page", event.getApplicationId());
		Assert.assertEquals("http://localhost:8080", event.getCanonicalURL());
		Assert.assertEquals(channel.getId(), event.getChannelId());
		Assert.assertEquals(date, event.getCreateDate());
		Assert.assertEquals("123456", event.getDataSourceId());
		Assert.assertEquals(date, event.getEventDate());
		Assert.assertEquals(
			eventDefinition.getId(), event.getEventDefinitionId());
		Assert.assertEquals("http://localhost:8080/test", event.getURL());
		Assert.assertEquals("abcdef", event.getUserId());

		Assert.assertNotNull(event.getId());
	}

	@Test
	public void testAddEventWithAttribute() {
		Channel channel = _channelDog.addChannel("Test Channel");

		Date date = DateUtil.newDayDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttribute eventAttribute = new EventAttribute(
			"987654321", eventAttributeDefinition.getId());

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", "http://localhost:8080",
			channel.getId(), date, "dataSourceId",
			Collections.singleton(eventAttribute), date,
			eventDefinition.getId(), 1L, "http://localhost:8080", "abcdef");

		Assert.assertEquals(
			eventAttributeDefinition.getId(),
			eventAttribute.getEventAttributeDefinitionId());
		Assert.assertEquals("987654321", eventAttribute.getAttributeValue());

		Set<EventAttribute> eventAttributes = event.getEventAttributes();

		Assert.assertEquals(
			eventAttributes.toString(), 1, eventAttributes.size());

		Assert.assertNotNull(eventAttribute.getId());
	}

	@Test
	public void testGetRecentEventAttributeValues() {
		Date date = DateUtil.newDayDate();

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Channel channel = _channelDog.addChannel("Test Channel");

		Long eventAttributeDefinitionId = eventAttributeDefinition.getId();

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		_eventDog.addEvent(
			"analyticsEventId", "Page", "http://localhost:8080",
			channel.getId(), date, "dataSourceId",
			new HashSet<EventAttribute>() {
				{
					add(
						new EventAttribute(
							"testValue1", eventAttributeDefinitionId));
					add(
						new EventAttribute(
							"testValue2", eventAttributeDefinitionId));
				}
			},
			date, eventDefinition.getId(), 1L, "http://localhost:8080",
			"userId");

		Assert.assertEquals(
			new ArrayList<EventAttributeValue>() {
				{
					add(new EventAttributeValue("testValue1", date));
					add(new EventAttributeValue("testValue2", date));
				}
			},
			_eventDog.getRecentEventAttributeValues(
				eventAttributeDefinition.getId(), 2));
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