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

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class EventAttributeDefinitionDogTest {

	@Test
	public void testAddEventAttributeDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				"string", "Testing attribute definition",
				"Test Attribute Defintion", eventDefinition.getId(),
				"testAttributeDefinition");

		Assert.assertEquals("string", eventAttributeDefinition.getDataType());
		Assert.assertEquals(
			"Testing attribute definition",
			eventAttributeDefinition.getDescription());
		Assert.assertEquals(
			"Test Attribute Defintion",
			eventAttributeDefinition.getDisplayName());

		List<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions = new ArrayList<>(
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions());

		EventDefinitionEventAttributeDefinition
			eventDefinitionEventAttributeDefinition =
				eventDefinitionEventAttributeDefinitions.get(0);

		Assert.assertEquals(
			eventDefinition.getId(),
			eventDefinitionEventAttributeDefinition.getEventDefinitionId());

		Assert.assertNotNull(eventAttributeDefinition.getId());
	}

	@Test
	public void testFetchEventAttributeDefinitionByName() {
		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Assert.assertNotNull(eventAttributeDefinition);

		Assert.assertEquals("duration", eventAttributeDefinition.getDataType());
		Assert.assertNull(eventAttributeDefinition.getDescription());
		Assert.assertNull(eventAttributeDefinition.getDisplayName());

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageUnloaded");

		List<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions = new ArrayList<>(
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions());

		EventDefinitionEventAttributeDefinition
			eventDefinitionEventAttributeDefinition =
				eventDefinitionEventAttributeDefinitions.get(0);

		Assert.assertEquals(
			eventDefinition.getId(),
			eventDefinitionEventAttributeDefinition.getEventDefinitionId());

		Assert.assertEquals("viewDuration", eventAttributeDefinition.getName());
	}

	@Test
	public void testGetEventAttributeDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				"string", "Testing attribute definition",
				"Test Attribute Defintion", eventDefinition.getId(),
				"testAttributeDefinition");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.getEventAttributeDefinition(
				eventAttributeDefinition1.getId());

		Assert.assertEquals(
			eventAttributeDefinition1, eventAttributeDefinition2);
	}

	@Test
	public void testUpdateEventAttributeDefinitionDataType() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				"string", null, null, eventAttributeDefinition1.getId(), null,
				null);

		Assert.assertNotEquals(
			eventAttributeDefinition1.getDataType(),
			eventAttributeDefinition2.getDataType());
		Assert.assertEquals(
			eventAttributeDefinition1.getDescription(),
			eventAttributeDefinition2.getDescription());
		Assert.assertEquals(
			eventAttributeDefinition1.getDisplayName(),
			eventAttributeDefinition2.getDisplayName());
		Assert.assertEquals(
			eventAttributeDefinition1.
				getEventDefinitionEventAttributeDefinitions(),
			eventAttributeDefinition2.
				getEventDefinitionEventAttributeDefinitions());
		Assert.assertEquals(
			eventAttributeDefinition1.getId(),
			eventAttributeDefinition2.getId());
		Assert.assertEquals(
			eventAttributeDefinition1.getName(),
			eventAttributeDefinition2.getName());
	}

	@Test
	public void testUpdateEventAttributeDefinitionDescription() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				null, "New Description", null,
				eventAttributeDefinition1.getId(), null, null);

		Assert.assertEquals(
			eventAttributeDefinition1.getDataType(),
			eventAttributeDefinition2.getDataType());
		Assert.assertNotEquals(
			eventAttributeDefinition1.getDescription(),
			eventAttributeDefinition2.getDescription());
		Assert.assertEquals(
			eventAttributeDefinition1.getDisplayName(),
			eventAttributeDefinition2.getDisplayName());
		Assert.assertEquals(
			eventAttributeDefinition1.
				getEventDefinitionEventAttributeDefinitions(),
			eventAttributeDefinition2.
				getEventDefinitionEventAttributeDefinitions());
		Assert.assertEquals(
			eventAttributeDefinition1.getId(),
			eventAttributeDefinition2.getId());
		Assert.assertEquals(
			eventAttributeDefinition1.getName(),
			eventAttributeDefinition2.getName());
	}

	@Test
	public void testUpdateEventAttributeDefinitionDisplayName() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				null, null, "New Test Display Name",
				eventAttributeDefinition1.getId(), null, null);

		Assert.assertEquals(
			eventAttributeDefinition1.getDataType(),
			eventAttributeDefinition2.getDataType());
		Assert.assertEquals(
			eventAttributeDefinition1.getDescription(),
			eventAttributeDefinition2.getDescription());
		Assert.assertNotEquals(
			eventAttributeDefinition1.getDisplayName(),
			eventAttributeDefinition2.getDisplayName());
		Assert.assertEquals(
			eventAttributeDefinition1.
				getEventDefinitionEventAttributeDefinitions(),
			eventAttributeDefinition2.
				getEventDefinitionEventAttributeDefinitions());
		Assert.assertEquals(
			eventAttributeDefinition1.getId(),
			eventAttributeDefinition2.getId());
		Assert.assertEquals(
			eventAttributeDefinition1.getName(),
			eventAttributeDefinition2.getName());
	}

	@Test
	public void testUpdateEventAttributeDefinitionEventDefinitionEventAttributeDefinitions() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				null, null, null, eventAttributeDefinition1.getId(),
				Collections.singleton(
					new EventDefinitionEventAttributeDefinition(
						eventDefinition.getId())),
				null);

		Assert.assertEquals(
			eventAttributeDefinition1.getDataType(),
			eventAttributeDefinition2.getDataType());
		Assert.assertEquals(
			eventAttributeDefinition1.getDescription(),
			eventAttributeDefinition2.getDescription());
		Assert.assertEquals(
			eventAttributeDefinition1.getDisplayName(),
			eventAttributeDefinition2.getDisplayName());
		Assert.assertNotEquals(
			eventAttributeDefinition1.
				getEventDefinitionEventAttributeDefinitions(),
			eventAttributeDefinition2.
				getEventDefinitionEventAttributeDefinitions());
		Assert.assertEquals(
			eventAttributeDefinition1.getId(),
			eventAttributeDefinition2.getId());
		Assert.assertEquals(
			eventAttributeDefinition1.getName(),
			eventAttributeDefinition2.getName());
	}

	@Test
	public void testUpdateEventAttributeDefinitionName() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				null, null, null, eventAttributeDefinition1.getId(), null,
				"newAttributeName");

		Assert.assertEquals(
			eventAttributeDefinition1.getDataType(),
			eventAttributeDefinition2.getDataType());
		Assert.assertEquals(
			eventAttributeDefinition1.getDescription(),
			eventAttributeDefinition2.getDescription());
		Assert.assertEquals(
			eventAttributeDefinition1.getDisplayName(),
			eventAttributeDefinition2.getDisplayName());
		Assert.assertEquals(
			eventAttributeDefinition1.
				getEventDefinitionEventAttributeDefinitions(),
			eventAttributeDefinition2.
				getEventDefinitionEventAttributeDefinitions());
		Assert.assertEquals(
			eventAttributeDefinition1.getId(),
			eventAttributeDefinition2.getId());
		Assert.assertNotEquals(
			eventAttributeDefinition1.getName(),
			eventAttributeDefinition2.getName());
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}