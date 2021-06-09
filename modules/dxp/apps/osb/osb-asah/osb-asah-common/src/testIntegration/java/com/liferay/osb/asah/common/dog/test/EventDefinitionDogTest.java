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
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.entity.BlockedEventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventDefinitionDogTest {

	@Test
	public void testAddDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertNotNull(eventDefinition);
		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("Test Event", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition.getType());
		Assert.assertFalse(eventDefinition.isBlocked());
	}

	@Test
	public void testAddDefinitionDuplicateDisplayName() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent1",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals("Test Event", eventDefinition1.getDisplayName());

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent2",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals(
			"Test Event (1)", eventDefinition2.getDisplayName());

		EventDefinition eventDefinition3 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent3",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals(
			"Test Event (2)", eventDefinition3.getDisplayName());
	}

	@SQLResource(resourcePath = "test_add_definition_limit_reached.sql")
	@Test
	public void testAddDefinitionLimitReached() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event 1", null, "testEvent1",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertNotNull(eventDefinition);
		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("Test Event 1", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent1", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition.getType());
		Assert.assertFalse(eventDefinition.isBlocked());

		Date dayDate = DateUtil.newDayDate();
		String url = "http://localhost:8080";

		eventDefinition = _eventDefinitionDog.addEventDefinition(
			"Testing an event", "Test Event 2", dayDate, "testEvent2",
			EventDefinition.Type.CUSTOM, url);

		Assert.assertNotNull(eventDefinition);
		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("Test Event 2", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent2", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition.getType());
		Assert.assertTrue(eventDefinition.isBlocked());
		Assert.assertEquals(
			new BlockedEventDefinition(dayDate, url),
			eventDefinition.getBlockedEventDefinition());
	}

	@Test
	public void testAddDefinitionNoDisplayName1() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", null, null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertNotNull(eventDefinition);
		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("testEvent", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition.getType());
		Assert.assertFalse(eventDefinition.isBlocked());
	}

	@Test
	public void testAddDefinitionNoDisplayName2() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertNotNull(eventDefinition);
		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("testEvent", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, eventDefinition.getType());
		Assert.assertFalse(eventDefinition.isBlocked());
	}

	@Test
	public void testAddDefinitionNoDisplayName3() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", null, null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals("testEvent", eventDefinition1.getDisplayName());

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", null, null, "TestEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals("TestEvent (1)", eventDefinition2.getDisplayName());
	}

	@SQLResource(resourcePath = "test_block_event_definition.sql")
	@Test
	public void testBlockEventDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("subscribed");

		Assert.assertNotNull(eventDefinition);

		_eventDefinitionDog.blockEventDefinitions(
			Collections.singletonList(eventDefinition.getId()));

		eventDefinition = _eventDefinitionDog.fetchEventDefinitionByName(
			"subscribed");

		Assert.assertNull(eventDefinition.getDescription());
		Assert.assertNull(eventDefinition.getDisplayName());

		BlockedEventDefinition blockedEventDefinition =
			eventDefinition.getBlockedEventDefinition();

		Assert.assertNotNull(blockedEventDefinition);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(
			2021, 4, 19, 12, 35, 0, 0, ZoneId.of("UTC"));

		Assert.assertEquals(
			Date.from(zonedDateTime.toInstant()),
			blockedEventDefinition.getLastSeenDate());

		Assert.assertEquals(
			"http://localhost:8089/web/guest/home",
			blockedEventDefinition.getLastSeenURL());
	}

	@SQLResource(resourcePath = "test_block_event_definitions.sql")
	@Test
	public void testBlockEventDefinitions() {
		Page<EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitionsPage(
				false, null, 0, 3, Sort.asc("name"),
				EventDefinition.Type.CUSTOM);

		List<Long> eventDefinitionIds = ListUtil.map(
			eventDefinitions.getContent(), EventDefinition::getId);

		_eventDefinitionDog.blockEventDefinitions(eventDefinitionIds);

		ZonedDateTime addNotificationZonedDateTime = ZonedDateTime.of(
			2021, 4, 19, 0, 0, 0, 0, ZoneId.of("UTC"));
		ZonedDateTime subscribedZonedDateTime = ZonedDateTime.of(
			2021, 3, 29, 0, 0, 0, 0, ZoneId.of("UTC"));
		ZonedDateTime unsubscribedZonedDateTime = ZonedDateTime.of(
			2021, 2, 16, 0, 0, 0, 0, ZoneId.of("UTC"));

		Map<String, BlockedEventDefinition> expectedBlockedEventDefinitions =
			new HashMap<String, BlockedEventDefinition>() {
				{
					put(
						"addNotification",
						new BlockedEventDefinition(
							Date.from(addNotificationZonedDateTime.toInstant()),
							"http://localhost:8089/web/guest/home"));
					put(
						"subscribed",
						new BlockedEventDefinition(
							Date.from(subscribedZonedDateTime.toInstant()),
							"http://localhost:80/web/guest/home"));
					put(
						"unsubscribed",
						new BlockedEventDefinition(
							Date.from(unsubscribedZonedDateTime.toInstant()),
							"http://localhost:8087/web/guest/home"));
				}
			};

		for (Long eventDefinitionId : eventDefinitionIds) {
			EventDefinition eventDefinition =
				_eventDefinitionDog.getEventDefinition(eventDefinitionId);

			Assert.assertNull(eventDefinition.getDescription());
			Assert.assertNull(eventDefinition.getDisplayName());

			Assert.assertEquals(
				expectedBlockedEventDefinitions.get(eventDefinition.getName()),
				eventDefinition.getBlockedEventDefinition());
		}
	}

	@Test
	public void testCountEventDefinitions() {
		Long count = _eventDefinitionDog.countEventDefinitions(
			false, null, EventDefinition.Type.DEFAULT);

		Assert.assertEquals(Long.valueOf(24), count);

		count = _eventDefinitionDog.countEventDefinitions(
			false, null, EventDefinition.Type.CUSTOM);

		Assert.assertEquals(Long.valueOf(0), count);
	}

	@Test
	public void testCountEventDefinitionsWithKeyword() {
		Assert.assertEquals(
			Long.valueOf(4),
			_eventDefinitionDog.countEventDefinitions(
				false, "page", EventDefinition.Type.DEFAULT));
	}

	@Test
	public void testFetchEventDefinitionByDisplayName() {
		EventDefinition expectedEventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals(
			expectedEventDefinition,
			_eventDefinitionDog.fetchEventDefinitionByDisplayName(
				"Test Event"));
	}

	@Test
	public void testFetchEventDefinitionByDisplayNameIgnoreCase() {
		EventDefinition expectedEventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		Assert.assertEquals(
			expectedEventDefinition,
			_eventDefinitionDog.fetchEventDefinitionByDisplayName(
				"test event"));
	}

	@Test
	public void testFetchEventDefinitionByDisplayNameNonexistent() {
		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByDisplayName(
				"Does Not Exist"));
	}

	@Test
	public void testFetchEventDefinitionByName() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.fetchEventDefinitionByName("testEvent");

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testGetEventDefinition() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.getEventDefinition(eventDefinition1.getId());

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testGetEventDefinitions() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, null, 0, 5, Sort.asc("name"),
				EventDefinition.Type.DEFAULT),
			new ArrayList<String>() {
				{
					add("assetClicked");
					add("assetDownloaded");
					add("assetDepthReached");
					add("assetSubmitted");
					add("assetViewed");
				}
			});
	}

	@SQLResource(resourcePath = "test_get_event_definitions_with_keyword.sql")
	@Test
	public void testGetEventDefinitionsWithKeywordMatchDescription() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, "item", 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM),
			new ArrayList<String>() {
				{
					add("addedToCart");
					add("removedFromCart");
				}
			});
	}

	@SQLResource(resourcePath = "test_get_event_definitions_with_keyword.sql")
	@Test
	public void testGetEventDefinitionsWithKeywordMatchDisplayName() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, "Shopping", 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM),
			new ArrayList<String>() {
				{
					add("addedToCart");
					add("removedFromCart");
				}
			});
	}

	@SQLResource(resourcePath = "test_get_event_definitions_with_keyword.sql")
	@Test
	public void testGetEventDefinitionsWithKeywordMatchName() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, "applied", 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM),
			Collections.singletonList("codeApplied"));
	}

	@SQLResource(resourcePath = "test_unblock_event_definition.sql")
	@Test
	public void testUnblockEventDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("subscribed");

		Long eventDefinitionId = eventDefinition.getId();

		_eventDefinitionDog.unblockEventDefinitions(
			Collections.singletonList(eventDefinitionId));

		eventDefinition = _eventDefinitionDog.getEventDefinition(
			eventDefinitionId);

		Assert.assertFalse(eventDefinition.isBlocked());
		Assert.assertNull(eventDefinition.getBlockedEventDefinition());
		Assert.assertNull(eventDefinition.getDescription());
		Assert.assertEquals("subscribed", eventDefinition.getDisplayName());
	}

	@SQLResource(
		resourcePath = "test_unblock_event_definition_limit_overflow.sql"
	)
	@Test(expected = OSBAsahException.class)
	public void testUnblockEventDefinitionLimitOverflow() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("subscribed100");

		Long eventDefinitionId = eventDefinition.getId();

		_eventDefinitionDog.unblockEventDefinitions(
			Collections.singletonList(eventDefinitionId));
	}

	@SQLResource(resourcePath = "test_unblock_event_definitions.sql")
	@Test
	public void testUnblockEventDefinitions() {
		List<Long> eventDefinitionIds = ListUtil.map(
			Arrays.asList(
				"addedToCart", "addedToWishList", "checkedOut",
				"orderCancelled"),
			name -> {
				EventDefinition eventDefinition =
					_eventDefinitionDog.fetchEventDefinitionByName(name);

				return eventDefinition.getId();
			});

		_eventDefinitionDog.unblockEventDefinitions(eventDefinitionIds);

		for (Long eventDefinitionId : eventDefinitionIds) {
			EventDefinition eventDefinition =
				_eventDefinitionDog.getEventDefinition(eventDefinitionId);

			Assert.assertFalse(eventDefinition.isBlocked());
			Assert.assertNull(eventDefinition.getBlockedEventDefinition());
			Assert.assertNull(eventDefinition.getDescription());
			Assert.assertEquals(
				eventDefinition.getName(), eventDefinition.getDisplayName());
		}

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("reviewAdded");

		Assert.assertTrue(eventDefinition.isBlocked());
		Assert.assertNotNull(eventDefinition.getBlockedEventDefinition());
		Assert.assertNull(eventDefinition.getDescription());
		Assert.assertNull(eventDefinition.getDisplayName());
	}

	@SQLResource(
		resourcePath = "test_unblock_event_definitions_limit_overflow.sql"
	)
	@Test(expected = OSBAsahException.class)
	public void testUnblockEventDefinitionsLimitOverflow() {
		Page<EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitionsPage(
				true, null, 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM);

		_eventDefinitionDog.unblockEventDefinitions(
			ListUtil.map(
				eventDefinitions.getContent(), EventDefinition::getId));
	}

	@Test
	public void testUpdateEventDefinitionBlockedEventDefinition() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		BlockedEventDefinition blockedEventDefinition =
			new BlockedEventDefinition(DateUtil.newDayDate(), "testUrl");

		eventDefinition1.setBlockedEventDefinition(blockedEventDefinition);

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.updateEventDefinition(
				blockedEventDefinition, null, null, eventDefinition1.getId());

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testUpdateEventDefinitionDescription() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		String newDescription = RandomTestUtil.randomString();

		eventDefinition1.setDescription(newDescription);

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.updateEventDefinition(
				null, newDescription, null, eventDefinition1.getId());

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testUpdateEventDefinitionDisplayName() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		String newDisplayName = RandomTestUtil.randomString();

		eventDefinition1.setDisplayName(newDisplayName);

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.updateEventDefinition(
				null, null, newDisplayName, eventDefinition1.getId());

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	private void _assertEventDefinitions(
		Page<EventDefinition> actualEventDefinitionPage,
		List<String> expectedEventDefinitionNames) {

		Assert.assertEquals(
			actualEventDefinitionPage.toString(),
			expectedEventDefinitionNames.size(),
			actualEventDefinitionPage.getNumberOfElements());

		for (EventDefinition actualEventDefinition :
				actualEventDefinitionPage) {

			Assert.assertTrue(
				expectedEventDefinitionNames.contains(
					actualEventDefinition.getName()));
		}
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}