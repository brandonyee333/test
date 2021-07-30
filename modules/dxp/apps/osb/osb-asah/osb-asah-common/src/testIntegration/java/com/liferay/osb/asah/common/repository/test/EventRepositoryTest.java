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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventRepositoryTest {

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesAverage() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.AVERAGE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/blogs", "https://www.beryl.com/products",
				"https://www.beryl.com/design"
			},
			keys.toArray());

		Collection<Number> numbers = eventAttributeValues.values();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.valueOf(1.50), BigDecimal.valueOf(1.33),
				BigDecimal.valueOf(1.00)
			},
			numbers.toArray(new BigDecimal[0]), 2);
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCount() {
		Assert.assertEquals(
			3,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesTotal() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {4, 4, 3}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesUnique() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.UNIQUE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {4, 3, 2}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesWithBreakdownItem() {
		BreakdownItem breakdownItem = new BreakdownItem();

		breakdownItem.setEventAnalysisFilters(
			Collections.singletonList(
				new EventAnalysisFilter(
					"34567", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "eq",
					Collections.singletonList("400"))));

		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, breakdownItem, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/products",
				"https://www.beryl.com/design", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {4, 2, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testSearchEventsLast24Hours() {
		List<Event> events = _eventRepository.searchEvents(
			1L, 1L, null, PageRequest.of(0, 50, Sort.desc("eventDate")),
			TimeRange.LAST_24_HOURS);

		Assert.assertEquals(events.toString(), 4, events.size());

		Stream<Event> stream = events.stream();

		Assert.assertArrayEquals(
			new String[] {
				"blogClicked", "formViewed", "assetClicked", "fieldBlurred"
			},
			stream.map(
				event -> {
					Optional<EventDefinition> eventDefinitionOptional =
						_eventDefinitionRepository.findById(
							event.getEventDefinitionId());

					EventDefinition eventDefinition =
						eventDefinitionOptional.get();

					return eventDefinition.getName();
				}
			).toArray());
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testSearchEventsWithKeywordsLast24Hours() {
		List<Event> events = _eventRepository.searchEvents(
			1L, 1L, "form", PageRequest.of(0, 50), TimeRange.LAST_24_HOURS);

		Assert.assertEquals(events.toString(), 1, events.size());
	}

	private void _assertBigDecimalEquals(
		BigDecimal[] expectedValues, BigDecimal[] actualValues, int scale) {

		for (int i = 0; i < actualValues.length; i++) {
			BigDecimal actualValue = actualValues[i];

			actualValue = actualValue.setScale(scale, RoundingMode.HALF_UP);

			Assert.assertEquals(0, actualValue.compareTo(expectedValues[i]));
		}
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventRepository _eventRepository;

}