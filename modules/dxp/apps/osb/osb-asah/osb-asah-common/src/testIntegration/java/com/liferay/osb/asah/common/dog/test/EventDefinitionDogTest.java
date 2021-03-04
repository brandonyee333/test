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

import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;

import java.util.ArrayList;
import java.util.List;

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
public class EventDefinitionDogTest {

	@Test
	public void testAddDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", "testEvent", "custom");

		Assert.assertNotNull(eventDefinition);

		Assert.assertEquals(
			"Testing an event", eventDefinition.getDescription());
		Assert.assertEquals("Test Event", eventDefinition.getDisplayName());
		Assert.assertNotNull(eventDefinition.getId());
		Assert.assertEquals("testEvent", eventDefinition.getName());
		Assert.assertEquals("custom", eventDefinition.getType());
	}

	@Test
	public void testCountEventDefinitions() {
		Long count = _eventDefinitionDog.countEventDefinitions("default", null);

		Assert.assertEquals(Long.valueOf(24), count);

		count = _eventDefinitionDog.countEventDefinitions("custom", null);

		Assert.assertEquals(Long.valueOf(0), count);
	}

	@Test
	public void testCountEventDefinitionsWithKeyword() {
		Assert.assertEquals(
			Long.valueOf(4),
			_eventDefinitionDog.countEventDefinitions("default", "page"));
	}

	@Test
	public void testFetchEventDefinitionByDisplayName() {
		EventDefinition expectedEventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", "testEvent", "custom");

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
				"Testing an event", "Test Event", "testEvent", "custom");

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.fetchEventDefinitionByName("testEvent");

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testGetEventDefinition() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", "testEvent", "custom");

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.getEventDefinition(eventDefinition1.getId());

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Test
	public void testGetEventDefinitions() {
		_assertEventDefinitions(
			_eventDefinitionDog.getEventDefinitions("default", null, 0, 5),
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
			_eventDefinitionDog.getEventDefinitions("default", "field", 0, 5),
			new ArrayList<String>() {
				{
					add("fieldBlurred");
					add("fieldFocused");
				}
			});
	}

	private void _assertEventDefinitions(
		List<EventDefinition> actualEventDefinitions,
		List<String> expectedEventDefinitionNames) {

		Assert.assertEquals(
			actualEventDefinitions.toString(),
			expectedEventDefinitionNames.size(), actualEventDefinitions.size());

		for (EventDefinition actualEventDefinition : actualEventDefinitions) {
			Assert.assertTrue(
				expectedEventDefinitionNames.contains(
					actualEventDefinition.getName()));
		}
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}