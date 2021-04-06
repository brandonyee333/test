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
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.postgresql.PostgreSQLTables;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class EventAttributeDefinitionDogTest {

	@Test
	public void testAddEventAttributeDefinition() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				"Testing attribute definition", "Test Attribute Definition",
				eventDefinition.getId(), "testAttributeDefinition",
				"testValue");

		Assert.assertEquals(
			EventAttributeDefinition.DataType.STRING,
			eventAttributeDefinition.getDataType());
		Assert.assertEquals(
			"Testing attribute definition",
			eventAttributeDefinition.getDescription());
		Assert.assertEquals(
			"Test Attribute Definition",
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
	public void testAddEventAttributeDefinitionNoDisplayName() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				"Testing attribute definition", null, eventDefinition.getId(),
				"testAttributeDefinition", "testValue");

		Assert.assertEquals(
			EventAttributeDefinition.DataType.STRING,
			eventAttributeDefinition.getDataType());
		Assert.assertEquals(
			"Testing attribute definition",
			eventAttributeDefinition.getDescription());
		Assert.assertEquals(
			"testAttributeDefinition",
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
	public void testCountEventAttributeDefinitions() {
		Assert.assertEquals(
			Long.valueOf(30),
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(null));
	}

	@Test
	public void testCountEventAttributeDefinitionsWithKeyword() {
		Assert.assertEquals(
			Long.valueOf(2),
			_eventAttributeDefinitionDog.countEventAttributeDefinitions(
				"file"));
	}

	@Test
	public void testFetchEventAttributeDefinitionByDisplayName() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		EventAttributeDefinition expectedEventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				null, "Test Event Attribute", eventDefinition.getId(),
				"testEventAttribute", "Sample Value");

		Assert.assertEquals(
			expectedEventAttributeDefinition,
			_eventAttributeDefinitionDog.
				fetchEventAttributeDefinitionByDisplayName(
					"Test Event Attribute"));
	}

	@Test
	public void testFetchEventAttributeDefinitionByDisplayNameIgnoreCase() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.addEventDefinition(
				"Testing an event", "Test Event", null, "testEvent",
				EventDefinition.Type.CUSTOM, null);

		EventAttributeDefinition expectedEventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				null, "Test Event Attribute", eventDefinition.getId(),
				"testEventAttribute", "Sample Value");

		Assert.assertEquals(
			expectedEventAttributeDefinition,
			_eventAttributeDefinitionDog.
				fetchEventAttributeDefinitionByDisplayName(
					"test event attribute"));
	}

	@Test
	public void testFetchEventAttributeDefinitionByDisplayNameNonExistent() {
		Assert.assertNull(
			_eventAttributeDefinitionDog.
				fetchEventAttributeDefinitionByDisplayName("Does Not Exist"));
	}

	@Test
	public void testFetchEventAttributeDefinitionByName() {
		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		Assert.assertNotNull(eventAttributeDefinition);

		Assert.assertEquals(
			EventAttributeDefinition.DataType.DURATION,
			eventAttributeDefinition.getDataType());
		Assert.assertNull(eventAttributeDefinition.getDescription());
		Assert.assertEquals(
			"viewDuration", eventAttributeDefinition.getDisplayName());

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

		EventAttributeDefinition expectedEventAttributeDefinition =
			_eventAttributeDefinitionDog.addEventAttributeDefinition(
				"Testing attribute definition", "Test Attribute Defintion",
				eventDefinition.getId(), "testAttributeDefinition",
				"testValue");

		Assert.assertEquals(
			expectedEventAttributeDefinition,
			_eventAttributeDefinitionDog.getEventAttributeDefinition(
				expectedEventAttributeDefinition.getId()));
	}

	@Test
	public void testGetEventAttributeDefinitionDataTypeBoolean() {
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "FALSE"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "False"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "false"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "TRUE"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "True"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "true"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "maybe"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "no"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.BOOLEAN,
			_eventAttributeDefinitionDog.getDataType("name", "yes"));
	}

	@Test
	public void testGetEventAttributeDefinitionDataTypeDate() {
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.DATE,
			_eventAttributeDefinitionDog.getDataType(
				"name", "2020-12-12T09:20:00.Z"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.DATE,
			_eventAttributeDefinitionDog.getDataType(
				"name", "12/31/2020T09:30:00Z"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.DATE,
			_eventAttributeDefinitionDog.getDataType(
				"name", "2020 12 31 09:30:10+0130"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.DATE,
			_eventAttributeDefinitionDog.getDataType(
				"name", "2020-12-31T09:30:00.000Z"));
	}

	@Test
	public void testGetEventAttributeDefinitionDataTypeDuration() {
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.DURATION,
			_eventAttributeDefinitionDog.getDataType("name", "10000"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.DURATION,
			_eventAttributeDefinitionDog.getDataType("viewDuration", "-10000"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.DURATION,
			_eventAttributeDefinitionDog.getDataType("viewDuration", "10000"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetEventAttributeDefinitionDataTypeNull() {
		_eventAttributeDefinitionDog.getDataType("name", null);
	}

	@Test
	public void testGetEventAttributeDefinitionDataTypeNumber() {
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "123a"));
		Assert.assertNotEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "abc"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "00000000000"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "30293094040"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "30293094040"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "-1234"));
		Assert.assertEquals(
			EventAttributeDefinition.DataType.NUMBER,
			_eventAttributeDefinitionDog.getDataType("name", "1234.95"));
	}

	@Test
	public void testGetEventAttributeDefinitions() {
		_assertEventAttributeDefinitions(
			_eventAttributeDefinitionDog.getEventAttributeDefinitionsPage(
				null, 0, 5, Sort.asc("name")),
			new HashMap<String, EventAttributeDefinition.DataType>() {
				{
					put("articleId", EventAttributeDefinition.DataType.STRING);
					put("assetId", EventAttributeDefinition.DataType.STRING);
					put("category", EventAttributeDefinition.DataType.STRING);
					put("className", EventAttributeDefinition.DataType.STRING);
					put("classPK", EventAttributeDefinition.DataType.STRING);
				}
			});
	}

	@Test
	public void testGetEventAttributeDefinitionsByEventDefinitionId() {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		List<EventAttributeDefinition> actualEventAttributeDefinitions =
			_eventAttributeDefinitionDog.
				getEventAttributeDefinitionsByEventDefinitionId(
					eventDefinition.getId());

		Stream<EventAttributeDefinition> stream =
			actualEventAttributeDefinitions.stream();

		Assert.assertEquals(
			new HashSet<String>() {
				{
					add("formId");
					add("page");
					add("title");
				}
			},
			stream.map(
				EventAttributeDefinition::getName
			).collect(
				Collectors.toSet()
			));
	}

	@PostgreSQLTables(
		resourcePath = "test_get_event_attribute_definitions_with_keyword.sql"
	)
	@Test
	public void testGetEventAttributeDefinitionsWithKeywordMatchDescription() {
		_assertEventAttributeDefinitions(
			_eventAttributeDefinitionDog.getEventAttributeDefinitionsPage(
				"seller", 0, 5, Sort.asc("name")),
			new HashMap<String, EventAttributeDefinition.DataType>() {
				{
					put(
						"itemDescription",
						EventAttributeDefinition.DataType.STRING);
				}
			});
	}

	@PostgreSQLTables(
		resourcePath = "test_get_event_attribute_definitions_with_keyword.sql"
	)
	@Test
	public void testGetEventAttributeDefinitionsWithKeywordMatchDisplayName() {
		_assertEventAttributeDefinitions(
			_eventAttributeDefinitionDog.getEventAttributeDefinitionsPage(
				"number of", 0, 5, Sort.asc("name")),
			new HashMap<String, EventAttributeDefinition.DataType>() {
				{
					put(
						"itemQuantity",
						EventAttributeDefinition.DataType.NUMBER);
					put(
						"numberOfReviews",
						EventAttributeDefinition.DataType.NUMBER);
				}
			});
	}

	@PostgreSQLTables(
		resourcePath = "test_get_event_attribute_definitions_with_keyword.sql"
	)
	@Test
	public void testGetEventAttributeDefinitionsWithKeywordMatchName() {
		_assertEventAttributeDefinitions(
			_eventAttributeDefinitionDog.getEventAttributeDefinitionsPage(
				"ity", 0, 5, Sort.asc("name")),
			new HashMap<String, EventAttributeDefinition.DataType>() {
				{
					put(
						"itemQuality",
						EventAttributeDefinition.DataType.STRING);
					put(
						"itemQuantity",
						EventAttributeDefinition.DataType.NUMBER);
				}
			});
	}

	@Test
	public void testUpdateEventAttributeDefinitionDataType() {
		EventAttributeDefinition eventAttributeDefinition1 =
			_eventAttributeDefinitionDog.fetchEventAttributeDefinitionByName(
				"viewDuration");

		EventAttributeDefinition eventAttributeDefinition2 =
			_eventAttributeDefinitionDog.updateEventAttributeDefinition(
				EventAttributeDefinition.DataType.STRING, null, null,
				eventAttributeDefinition1.getId(), null, null);

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
						eventDefinition.getId(), "testValue")),
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

	private void _assertEventAttributeDefinitions(
		Page<EventAttributeDefinition> actualEventAttributeDefinitions,
		Map<String, EventAttributeDefinition.DataType>
			expectedEventDefinitionAttributeDataTypes) {

		Assert.assertEquals(
			actualEventAttributeDefinitions.toString(),
			expectedEventDefinitionAttributeDataTypes.size(),
			actualEventAttributeDefinitions.getNumberOfElements());

		for (EventAttributeDefinition actualEventAttributeDefinition :
				actualEventAttributeDefinitions) {

			EventAttributeDefinition.DataType expectedDataType =
				expectedEventDefinitionAttributeDataTypes.get(
					actualEventAttributeDefinition.getName());

			Assert.assertEquals(
				expectedDataType, actualEventAttributeDefinition.getDataType());
		}
	}

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

}