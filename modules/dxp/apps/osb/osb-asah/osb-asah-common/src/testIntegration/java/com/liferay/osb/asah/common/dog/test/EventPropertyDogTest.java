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
import com.liferay.osb.asah.common.dog.EventPropertyDog;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Alejo Ceballos
 */
public class EventPropertyDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testFindAttributeValuesByRelationshipIdsAndKeywords()
		throws Exception {

		Channel channel = _channelDog.addChannel("Test Channel");

		Date date = DateUtil.newDayDate();

		Set<BQEventProperty> bqEventProperties =
			new HashSet<BQEventProperty>() {
				{
					add(
						new BQEventProperty(
							null, "viewDuration", "Event Attribute Value 1"));
					add(
						new BQEventProperty(
							null, "viewDuration", "event attribute value 2"));
					add(
						new BQEventProperty(
							null, "viewDuration", "EVENT ATTRIBUTE VALUE 3"));
					add(
						new BQEventProperty(
							null, "viewDuration", "EvEnT AtTrIbuTe VaLuE 4"));
					add(
						new BQEventProperty(
							null, "viewDuration", "EvEnT AtTrIbuTe VaLuE 1"));
					add(
						new BQEventProperty(
							null, "viewDuration", "A totally different value"));
				}
			};

		_eventDog.addBQEvent(
			"Page", bqEventProperties, channel.getId(), date, 1L, date,
			"pageUnloaded", "analyticsEventId", "sessionId", "abcdef");

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		Page<String> eventAttributeValuePage =
			_eventPropertyDog.getEventPropertyValuePage(
				channel.getId(), eventAttributeDefinition.getId(),
				eventDefinition.getId(), "Attribute Value", 100, 0);

		Assertions.assertEquals(4, eventAttributeValuePage.getTotalElements());

		List<String> eventAttributeValues =
			eventAttributeValuePage.getContent();

		Assertions.assertEquals(4, eventAttributeValues.size());

		for (String value :
				Arrays.asList(
					"event attribute value 4", "event attribute value 3",
					"event attribute value 2", "event attribute value 1")) {

			Assertions.assertTrue(eventAttributeValues.contains(value));
		}

		eventAttributeValuePage = _eventPropertyDog.getEventPropertyValuePage(
			channel.getId(), eventAttributeDefinition.getId(),
			eventDefinition.getId(), "Attribute Value", 3, 1);

		Assertions.assertEquals(4, eventAttributeValuePage.getTotalElements());

		eventAttributeValues = eventAttributeValuePage.getContent();

		Assertions.assertEquals(1, eventAttributeValues.size());
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
	private EventPropertyDog _eventPropertyDog;

}