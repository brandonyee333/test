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
import com.liferay.osb.asah.common.dog.AnalyticsEventStorageDog;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.postgresql.PostgreSQLTables;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahPostgreSQLSpring4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
public class AnalyticsEventStorageDogTest {

	@PostgreSQLTables(resourcePath = "test_store_blocked_event.sql")
	@Test
	public void testStoreBlockedEvent() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM);
		long expectedEventCount = _eventDog.countEvents(null);

		_storeAnalyticsEvent(
			"blockedEvent",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assert.assertEquals(
			expectedEventCount, _eventDog.countEvents(null), 0.0);
		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
	}

	@PostgreSQLTables(resourcePath = "test_store_definition_limit_reached.sql")
	@Test
	public void testStoreEventDefinitionLimitReached() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM);
		long expectedEventCount = _eventDog.countEvents(null);

		_storeAnalyticsEvent(
			"newEventDefinition",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assert.assertEquals(
			expectedEventCount, _eventDog.countEvents(null), 0.0);
		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
	}

	@Test
	public void testStoreExistingDefinitionNewAttributeDefinition() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null) +
				1;
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		long expectedEventCount =
			_eventDog.countEvents(eventDefinition.getId()) + 1;

		_storeAnalyticsEvent(
			"pageViewed",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.singletonMap("newTestEventAttribute", "testValue"));

		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
		Assert.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);

		Assert.assertEquals(
			Collections.singleton(eventDefinition.getId()),
			_getEventDefinitionIds("newTestEventAttribute"));
	}

	@Test
	public void testStoreExistingDefinitionNewAttributeDefinitionReference() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		long expectedEventCount =
			_eventDog.countEvents(eventDefinition.getId()) + 1;

		Set<Long> expectedEventDefinitionIds = _getEventDefinitionIds(
			"category");

		expectedEventDefinitionIds.add(eventDefinition.getId());

		_storeAnalyticsEvent(
			"pageViewed",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.singletonMap("category", "testValue"));

		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
		Assert.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assert.assertEquals(
			expectedEventDefinitionIds, _getEventDefinitionIds("category"));
	}

	@Test
	public void testStoreExistingEventDefinition() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		long expectedEventCount =
			_eventDog.countEvents(eventDefinition.getId()) + 1;

		_storeAnalyticsEvent(
			"pageViewed",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.singletonMap("title", "My First Site"));

		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
		Assert.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);
	}

	@Test
	public void testStoreNewEventDefinition() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM) + 1;

		_storeAnalyticsEvent(
			"newEventDefinition",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assert.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null),
			0.0);
		Assert.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, EventDefinition.Type.CUSTOM),
			0.0);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(
				"newEventDefinition");

		Assert.assertEquals(
			1, _eventDog.countEvents(eventDefinition.getId()), 0.0);
	}

	private Set<Long> _getEventDefinitionIds(
		String eventAttribributeDefinitionName) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				eventAttribributeDefinitionName);

		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions =
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions();

		Stream<EventDefinitionEventAttributeDefinition> stream =
			eventDefinitionEventAttributeDefinitions.stream();

		return stream.map(
			EventDefinitionEventAttributeDefinition::getEventDefinitionId
		).collect(
			Collectors.toSet()
		);
	}

	private void _storeAnalyticsEvent(
		String eventId, Map<String, String> eventContext,
		Map<String, String> eventProperties) {

		Channel channel = _channelDog.addChannel("Test Channel");

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId("Page");
		analyticsEvent.setChannelId(String.valueOf(channel.getId()));
		analyticsEvent.setCreateDate(DateUtil.newDayDate());
		analyticsEvent.setContext(eventContext);
		analyticsEvent.setDataSourceId("1");
		analyticsEvent.setEventId(eventId);
		analyticsEvent.setEventProperties(eventProperties);
		analyticsEvent.setIndividualId("1");
		analyticsEvent.setKnownIndividual(true);
		analyticsEvent.setProjectId("123456789");
		analyticsEvent.setSegmentNames(Collections.emptySet());

		UUID uuid = UUID.randomUUID();

		analyticsEvent.setUserId(uuid.toString());

		_analyticsEventStorageDog.store(analyticsEvent);
	}

	@Autowired
	private AnalyticsEventStorageDog _analyticsEventStorageDog;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}