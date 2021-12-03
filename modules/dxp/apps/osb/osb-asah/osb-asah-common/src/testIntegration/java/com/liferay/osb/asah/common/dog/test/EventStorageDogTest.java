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
import com.liferay.osb.asah.common.dog.EventStorageDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventAttributeRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Leslie Wong
 */
@Import(JDBCTestConfiguration.class)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class,
		ResetMocksTestExecutionListener.class
	}
)
public class EventStorageDogTest implements OSBAsahCommonSpringTestContext {

	@BeforeEach
	public void setUp() {
		_channel = _channelDog.addChannel("Test Channel");
	}

	@Test
	public void testEventStorageTransactional() {
		Mockito.doThrow(
			new RuntimeException()
		).when(
			_eventDog
		).addEvent(
			ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
			ArgumentMatchers.anyLong(), ArgumentMatchers.any(Date.class),
			ArgumentMatchers.anyLong(), ArgumentMatchers.anySet(),
			ArgumentMatchers.any(Date.class), ArgumentMatchers.anyLong(),
			ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
			ArgumentMatchers.anyString()
		);

		try {
			_storeAnalyticsEvent(
				"newEventDefinition",
				Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
				Collections.singletonMap(
					"newEventAttributeDefinition", "testValue"));

			Assertions.fail("Storing analytics event did not fail");
		}
		catch (Exception exception) {
			Assertions.assertNull(
				_eventAttributeDefinitionDog.
					fetchEventAttributeDefinitionByName(
						"newEventAttributeDefinition"));
			Assertions.assertNull(
				_eventDefinitionDog.fetchEventDefinitionByName(
					"newEventDefinition"));
		}
	}

	@SQLResource(resourcePath = "test_store_blocked_event.sql")
	@Test
	public void testStoreBlockedEvent() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM);
		long expectedEventCount = _eventDog.countEvents(null);

		_storeAnalyticsEvent(
			"blockedEvent",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assertions.assertEquals(
			expectedEventCount, _eventDog.countEvents(null), 0.0);
		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
	}

	@Test
	public void testStoreEventAttributes() {
		EventDefinition eventDefinition = new EventDefinition();

		eventDefinition.setBlocked(false);
		eventDefinition.setDisplayName("test");
		eventDefinition.setHidden(false);
		eventDefinition.setName("test");
		eventDefinition.setType(EventDefinition.Type.DEFAULT);

		eventDefinition = _eventDefinitionRepository.save(eventDefinition);

		EventAttributeDefinition eventAttributeDefinition =
			new EventAttributeDefinition();

		eventAttributeDefinition.setDataType(
			EventAttributeDefinition.DataType.STRING);
		eventAttributeDefinition.setDisplayName("eventAttributeDefinitionTest");
		eventAttributeDefinition.setEventDefinitionEventAttributeDefinitions(
			Collections.singleton(
				new EventDefinitionEventAttributeDefinition(
					eventDefinition.getId(), null)));
		eventAttributeDefinition.setName("eventAttributeDefinitionTest");
		eventAttributeDefinition.setType(EventAttributeDefinition.Type.LOCAL);

		_eventAttributeDefinitionRepository.save(eventAttributeDefinition);

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId("Page");
		analyticsEvent.setChannelId(String.valueOf(_channel.getId()));
		analyticsEvent.setCreateDate(DateUtil.newDayDate());
		analyticsEvent.setContext(Collections.emptyMap());
		analyticsEvent.setDataSourceId("1");
		analyticsEvent.setEventId("testEvent");
		analyticsEvent.setEventProperties(
			Collections.singletonMap(
				"eventAttributeDefinitionTest", "testValue"));
		analyticsEvent.setId("1");
		analyticsEvent.setIndividualId("1");
		analyticsEvent.setKnownIndividual(true);
		analyticsEvent.setProjectId("123456789");
		analyticsEvent.setSegmentNames(Collections.emptySet());
		analyticsEvent.setUserId(RandomTestUtil.randomUUID());

		Set<EventAttribute> eventAttributes =
			_eventStorageDog.storeEventAttributes(
				analyticsEvent, eventDefinition.getId());

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			eventAttributes.stream(
			).filter(
				eventAttribute -> eventAttribute.getValue() != null
			).map(
				eventAttribute ->
					_eventAttributeDefinitionDog.getEventAttributeDefinition(
						eventAttribute.getEventAttributeDefinitionId())
			).findFirst();

		eventAttributeDefinition = eventAttributeDefinitionOptional.get();

		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions =
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions();

		Long eventDefinitionId = eventDefinition.getId();

		Optional<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitionsOptional =
				eventDefinitionEventAttributeDefinitions.stream(
				).filter(
					eventDefinitionEventAttributeDefinition ->
						eventDefinitionId.equals(
							eventDefinitionEventAttributeDefinition.
								getEventDefinitionId())
				).findFirst();

		EventDefinitionEventAttributeDefinition
			eventDefinitionEventAttributeDefinition =
				eventDefinitionEventAttributeDefinitionsOptional.get();

		Assertions.assertEquals(
			"testValue",
			eventDefinitionEventAttributeDefinition.getSampleValue());
	}

	@SQLResource(resourcePath = "test_store_definition_limit_reached.sql")
	@Test
	public void testStoreEventDefinitionLimitReached() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM);
		long expectedEventCount = _eventDog.countEvents(null);

		_storeAnalyticsEvent(
			"newEventDefinition",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assertions.assertEquals(
			expectedEventCount, _eventDog.countEvents(null), 0.0);
		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
	}

	@Test
	public void testStoreExistingDefinitionNewAttributeDefinition() {
		long eventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);

		long expectedEventAttributeDefinitionCount =
			eventAttributeDefinitionCount + 1;

		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		long expectedEventCount =
			_eventDog.countEvents(eventDefinition.getId()) + 1;

		Event event = _storeAnalyticsEvent(
			"pageViewed",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.singletonMap("newTestEventAttribute", "testValue"));

		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
		Assertions.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assertions.assertEquals(
			Collections.singleton(eventDefinition.getId()),
			_getEventDefinitionIds("newTestEventAttribute"));
		Assertions.assertNotNull(event.getSessionId());
	}

	@Test
	public void testStoreExistingDefinitionNewAttributeDefinitionReference() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM);

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

		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
		Assertions.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);
		Assertions.assertEquals(
			expectedEventDefinitionIds, _getEventDefinitionIds("category"));
	}

	@Test
	public void testStoreExistingEventDefinition() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);
		long expectedEventDefinitionCount =
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		long expectedEventCount =
			_eventDog.countEvents(eventDefinition.getId()) + 1;

		_storeAnalyticsEvent(
			"pageViewed",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.singletonMap("title", "My First Site"));

		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
		Assertions.assertEquals(
			expectedEventCount, _eventDog.countEvents(eventDefinition.getId()),
			0.0);
		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);
	}

	@Test
	public void testStoreGlobalEventAttributes() {
		Event actualEvent = _storeAnalyticsEvent(
			"pageViewed",
			new HashMap<String, String>() {
				{
					put("canonicalUrl", "https://www.liferay.com");
					put("description", "Welcome to Liferay Inc.");
					put("keywords", "portal,dxp");
					put("referrer", "google.com");
					put("title", "Liferay");
					put("url", "https://www.liferay.com");
				}
			},
			Collections.emptyMap());

		Set<EventAttribute> actualEventAttributes =
			actualEvent.getEventAttributes();

		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"canonicalUrl"),
			"https://www.liferay.com");
		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"pageDescription"),
			"Welcome to Liferay Inc.");
		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"pageKeywords"),
			"portal,dxp");
		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"referrer"),
			"google.com");
		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"pageTitle"),
			"Liferay");
		_assertEventAttributeValue(
			actualEventAttributes,
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"url"),
			"https://www.liferay.com");
	}

	@Test
	public void testStoreNewEventDefinition() {
		long expectedEventAttributeDefinitionCount =
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL);

		long eventDefinitionCount = _eventDefinitionDog.countEventDefinitions(
			false, null, null, EventDefinition.Type.CUSTOM);

		long expectedEventDefinitionCount = eventDefinitionCount + 1;

		_storeAnalyticsEvent(
			"newEventDefinition",
			Collections.singletonMap("canonicalUrl", "http://127.0.0.1"),
			Collections.emptyMap());

		Assertions.assertEquals(
			expectedEventAttributeDefinitionCount,
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				null, null, EventAttributeDefinition.Type.LOCAL),
			0.0);
		Assertions.assertEquals(
			expectedEventDefinitionCount,
			_eventDefinitionDog.countEventDefinitions(
				false, null, null, EventDefinition.Type.CUSTOM),
			0.0);

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(
				"newEventDefinition");

		Assertions.assertEquals(
			1, _eventDog.countEvents(eventDefinition.getId()), 0.0);
	}

	private void _assertEventAttributeValue(
		Set<EventAttribute> actualEventAttributes,
		EventAttributeDefinition expectedEventAttributeDefinition,
		String expectedEventAttributeValue) {

		Stream<EventAttribute> stream = actualEventAttributes.stream();

		Map<Long, EventAttribute> actualEventAttributesMap = stream.collect(
			Collectors.toMap(
				EventAttribute::getEventAttributeDefinitionId,
				Function.identity()));

		EventAttribute actualEventAttribute = actualEventAttributesMap.get(
			expectedEventAttributeDefinition.getId());

		Assertions.assertNotNull(actualEventAttribute);
		Assertions.assertEquals(
			expectedEventAttributeValue, actualEventAttribute.getValue());
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

	private Event _storeAnalyticsEvent(
		String eventId, Map<String, String> eventContext,
		Map<String, String> eventProperties) {

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId("Page");
		analyticsEvent.setChannelId(String.valueOf(_channel.getId()));
		analyticsEvent.setCreateDate(DateUtil.newDayDate());
		analyticsEvent.setContext(eventContext);
		analyticsEvent.setDataSourceId("1");
		analyticsEvent.setEventId(eventId);
		analyticsEvent.setEventProperties(eventProperties);
		analyticsEvent.setId("1");
		analyticsEvent.setIndividualId("1");
		analyticsEvent.setKnownIndividual(true);
		analyticsEvent.setProjectId("123456789");
		analyticsEvent.setSegmentNames(Collections.emptySet());
		analyticsEvent.setUserId(RandomTestUtil.randomUUID());

		return _eventStorageDog.store(
			analyticsEvent, RandomTestUtil.randomId());
	}

	private Channel _channel;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

	@Autowired
	private EventAttributeRepository _eventAttributeRepository;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@SpyBean
	private EventDog _eventDog;

	@Autowired
	private EventStorageDog _eventStorageDog;

}