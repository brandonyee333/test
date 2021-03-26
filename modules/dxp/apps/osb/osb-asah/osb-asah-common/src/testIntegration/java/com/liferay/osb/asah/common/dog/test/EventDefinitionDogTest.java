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
import com.liferay.osb.asah.common.model.BlockedEventDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.postgresql.PostgreSQLTables;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahPostgreSQLSpring4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
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

	@PostgreSQLTables(resourcePath = "test_add_definition_limit_reached.sql")
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
	public void testFetchEventDefinitionByDisplayNameNonExistent() {
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

	@Test
	public void testGetEventDefinitionsWithKeyword() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitionsPage(
				false, "field", 0, 5, Sort.asc("name"),
				EventDefinition.Type.DEFAULT),
			new ArrayList<String>() {
				{
					add("fieldBlurred");
					add("fieldFocused");
				}
			});
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