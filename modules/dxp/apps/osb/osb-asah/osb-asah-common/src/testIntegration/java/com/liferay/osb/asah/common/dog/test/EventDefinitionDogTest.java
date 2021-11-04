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
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.time.ZoneOffset;
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

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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
		Assert.assertEquals(dayDate, eventDefinition.getBlockedLastSeenDate());
		Assert.assertEquals(url, eventDefinition.getBlockedLastSeenURL());
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

		ZonedDateTime zonedDateTime = ZonedDateTime.of(
			2021, 4, 19, 12, 35, 0, 0, ZoneOffset.UTC);

		Assert.assertEquals(
			Date.from(zonedDateTime.toInstant()),
			eventDefinition.getBlockedLastSeenDate());

		Assert.assertEquals(
			"http://localhost:8089/web/guest/home",
			eventDefinition.getBlockedLastSeenURL());
	}

	@SQLResource(resourcePath = "test_block_event_definitions.sql")
	@Test
	public void testBlockEventDefinitions() {
		Page<EventDefinition> eventDefinitions =
			_eventDefinitionDog.getEventDefinitionsPage(
				false, null, null, 0, 3, Sort.asc("name"),
				EventDefinition.Type.CUSTOM);

		List<Long> eventDefinitionIds = ListUtil.map(
			eventDefinitions.getContent(), EventDefinition::getId);

		_eventDefinitionDog.blockEventDefinitions(eventDefinitionIds);

		ZonedDateTime addNotificationZonedDateTime = ZonedDateTime.of(
			2021, 4, 19, 0, 0, 0, 0, ZoneOffset.UTC);
		ZonedDateTime subscribedZonedDateTime = ZonedDateTime.of(
			2021, 3, 29, 0, 0, 0, 0, ZoneOffset.UTC);
		ZonedDateTime unsubscribedZonedDateTime = ZonedDateTime.of(
			2021, 2, 16, 0, 0, 0, 0, ZoneOffset.UTC);

		Map<String, Tuple2> expectedBlockedEventInfos =
			new HashMap<String, Tuple2>() {
				{
					put(
						"addNotification",
						Tuples.of(
							Date.from(addNotificationZonedDateTime.toInstant()),
							"http://localhost:8089/web/guest/home"));
					put(
						"subscribed",
						Tuples.of(
							Date.from(subscribedZonedDateTime.toInstant()),
							"http://localhost:80/web/guest/home"));
					put(
						"unsubscribed",
						Tuples.of(
							Date.from(unsubscribedZonedDateTime.toInstant()),
							"http://localhost:8087/web/guest/home"));
				}
			};

		for (Long eventDefinitionId : eventDefinitionIds) {
			EventDefinition eventDefinition =
				_eventDefinitionDog.getEventDefinition(eventDefinitionId);

			Assert.assertNull(eventDefinition.getDescription());
			Assert.assertNull(eventDefinition.getDisplayName());

			Tuple2 tuple2 = expectedBlockedEventInfos.get(
				eventDefinition.getName());

			Assert.assertEquals(
				tuple2.getT1(), eventDefinition.getBlockedLastSeenDate());

			Assert.assertEquals(
				tuple2.getT2(), eventDefinition.getBlockedLastSeenURL());
		}
	}

	@Test
	public void testCountEventDefinitions() {
		long count = _eventDefinitionDog.countEventDefinitions(
			false, null, null, EventDefinition.Type.DEFAULT);

		Assert.assertEquals(27, count);

		count = _eventDefinitionDog.countEventDefinitions(
			false, null, null, EventDefinition.Type.CUSTOM);

		Assert.assertEquals(0, count);
	}

	@Test
	public void testCountEventDefinitionsWithKeyword() {
		Assert.assertEquals(
			Long.valueOf(5),
			_eventDefinitionDog.countEventDefinitions(
				false, null, "page", EventDefinition.Type.DEFAULT));
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
	public void testGetDisplayedEventDefinitions() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, false, null, 0, 20, Sort.asc("name"),
				EventDefinition.Type.DEFAULT),
			new ArrayList<String>() {
				{
					add("assetClicked");
					add("assetDownloaded");
					add("assetSubmitted");
					add("assetViewed");
					add("blogClicked");
					add("blogViewed");
					add("ctaClicked");
					add("documentDownloaded");
					add("documentPreviewed");
					add("formSubmitted");
					add("formViewed");
					add("pageRead");
					add("pageViewed");
					add("webContentClicked");
					add("webContentViewed");
				}
			});
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
				false, null, null, 0, 5, Sort.asc("name"),
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
				false, null, "item", 0, 5, Sort.asc("name"),
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
				false, null, "Shopping", 0, 5, Sort.asc("name"),
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
				false, null, "applied", 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM),
			Collections.singletonList("codeApplied"));
	}

	@Test
	public void testGetHiddenEventDefinitions() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, true, null, 0, 20, Sort.asc("name"),
				EventDefinition.Type.DEFAULT),
			new ArrayList<String>() {
				{
					add("VOTE");
					add("assetDepthReached");
					add("blogDepthReached");
					add("fieldBlurred");
					add("fieldFocused");
					add("pageDepthReached");
					add("pageLoaded");
					add("pageUnloaded");
					add("posted");
					add("shared");
					add("tabBlurred");
					add("tabFocused");
				}
			});
	}

	@Test
	public void testHideEventDefinitions() {
		EventDefinition assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetClicked");
		EventDefinition assetDownloadedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetDownloaded");

		_eventDefinitionDog.hideEventDefinitions(
			Arrays.asList(
				assetClickedEventDefinition.getId(),
				assetDownloadedEventDefinition.getId()));

		assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetClicked");

		Assert.assertTrue(assetClickedEventDefinition.isHidden());

		assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetDownloaded");

		Assert.assertTrue(assetClickedEventDefinition.isHidden());
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
		Assert.assertNull(eventDefinition.getBlockedLastSeenDate());
		Assert.assertNull(eventDefinition.getBlockedLastSeenURL());
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
			Assert.assertNull(eventDefinition.getBlockedLastSeenDate());
			Assert.assertNull(eventDefinition.getBlockedLastSeenURL());
			Assert.assertNull(eventDefinition.getDescription());
			Assert.assertEquals(
				eventDefinition.getName(), eventDefinition.getDisplayName());
		}

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("reviewAdded");

		Assert.assertTrue(eventDefinition.isBlocked());
		Assert.assertNotNull(eventDefinition.getBlockedLastSeenDate());
		Assert.assertNotNull(eventDefinition.getBlockedLastSeenURL());
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
				true, null, null, 0, 5, Sort.asc("name"),
				EventDefinition.Type.CUSTOM);

		_eventDefinitionDog.unblockEventDefinitions(
			ListUtil.map(
				eventDefinitions.getContent(), EventDefinition::getId));
	}

	@Test
	public void testUnhideEventDefinitions() {
		EventDefinition assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetClicked");

		Assert.assertFalse(assetClickedEventDefinition.isHidden());

		_eventDefinitionDog.hideEventDefinitions(
			Arrays.asList(assetClickedEventDefinition.getId()));

		assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetClicked");

		Assert.assertTrue(assetClickedEventDefinition.isHidden());

		_eventDefinitionDog.unhideEventDefinitions(
			Arrays.asList(assetClickedEventDefinition.getId()));

		assetClickedEventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("assetClicked");

		Assert.assertFalse(assetClickedEventDefinition.isHidden());
	}

	@Test
	public void testUpdateEventDefinitionBlockedEventDefinition() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		eventDefinition1.setBlockedLastSeenDate(DateUtil.newDayDate());
		eventDefinition1.setBlockedLastSeenURL("testUrl");

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.updateEventDefinition(
				eventDefinition1.getBlockedLastSeenDate(),
				eventDefinition1.getBlockedLastSeenURL(), null, null,
				eventDefinition1.getId());

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
				null, null, newDescription, null, eventDefinition1.getId());

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
				null, null, null, newDisplayName, eventDefinition1.getId());

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