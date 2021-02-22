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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.Event;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
public class EventDogTest {

	@Test
	public void testAddEvent() {
		Date date = DateUtil.newDayDate();

		Channel channel = _channelDog.addChannel("Test Channel");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", channel.getId(), date, "123456", date,
			eventDefinition.getId(), "abcdef");

		Assert.assertEquals("analyticsEventId", event.getAnalyticsEventId());
		Assert.assertEquals("Page", event.getApplicationId());
		Assert.assertEquals(channel.getId(), event.getChannelId());
		Assert.assertEquals(date, event.getCreateDate());
		Assert.assertEquals("123456", event.getDataSourceId());
		Assert.assertEquals(date, event.getEventDate());
		Assert.assertEquals(
			eventDefinition.getId(), event.getEventDefinitionId());
		Assert.assertEquals("abcdef", event.getUserId());

		Assert.assertNotNull(event.getId());
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}