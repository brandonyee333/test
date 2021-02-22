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
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventAttributeDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.Event;
import com.liferay.osb.asah.common.model.EventAttribute;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
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
public class EventAttributeDogTest {

	@Test
	public void testAddEventAttribute() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		Channel channel = _channelDog.addChannel("Test Channel");

		Date date = DateUtil.newDayDate();

		Event event = _eventDog.addEvent(
			"analyticsEventId", "Page", channel.getId(), date, "dataSourceId",
			date, eventDefinition.getId(), "abcdef");

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttribute eventAttribute = _eventAttributeDog.addEventAttribute(
			eventAttributeDefinition.getId(), "987654321", event.getId());

		Assert.assertEquals(
			eventAttributeDefinition.getId(),
			eventAttribute.getEventAttributeDefinitionId());
		Assert.assertEquals("987654321", eventAttribute.getAttributeValue());
		Assert.assertEquals(event.getId(), eventAttribute.getEventId());

		Assert.assertNotNull(eventAttribute.getId());
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventAttributeDog _eventAttributeDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}