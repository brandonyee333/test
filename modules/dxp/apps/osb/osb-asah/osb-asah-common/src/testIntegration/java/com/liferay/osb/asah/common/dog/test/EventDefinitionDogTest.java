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
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
	public void testFetchEventDefinitionByName() {
		EventDefinition eventDefinition1 =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", "testEvent", "custom");

		EventDefinition eventDefinition2 =
			_eventDefinitionDog.fetchEventDefinitionByName("testEvent");

		Assert.assertEquals(eventDefinition1, eventDefinition2);
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}