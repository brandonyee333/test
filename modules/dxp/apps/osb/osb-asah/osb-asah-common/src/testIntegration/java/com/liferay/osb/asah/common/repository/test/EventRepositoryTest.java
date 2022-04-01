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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 * @author Matthew Kong
 */
@Import(JDBCTestConfiguration.class)
public class EventRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@AfterEach
	public void tearDown() {
		_preferenceDog.savePreference("time-zone-id", "UTC");
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testCountEventsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assertions.assertEquals(
			3,
			_eventRepository.countEvents(
				1L, 1L, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testCountEventsWithKeywordsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assertions.assertEquals(
			1,
			_eventRepository.countEvents(
				1L, 1L, "form", timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testCountTotalEvents() {
		Assertions.assertEquals(
			8L,
			_eventRepository.countTotalEvents(
				1L,
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testCountTotalEventsWithTimeZone() {
		Assertions.assertEquals(
			6L,
			_eventRepository.countTotalEvents(
				1L,
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				"America/Los_Angeles"));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testCountUniqueIndividuals() {
		Assertions.assertEquals(
			6L,
			_eventRepository.countUniqueIndividuals(
				1L,
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testCountUniqueIndividualsWithTimeZone() {
		Assertions.assertEquals(
			5L,
			_eventRepository.countUniqueIndividuals(
				1L,
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				"America/Los_Angeles"));
	}

	@SQLResource(
		resourcePath = "test_get_average_event_count_per_individual.sql"
	)
	@Test
	public void testGetAverageEventCountPerIndividual() {
		MatcherAssert.assertThat(
			BigDecimal.valueOf(2),
			Matchers.comparesEqualTo(
				_eventRepository.getAverageEventCountPerIndividual(
					1L,
					Collections.singletonList(
						new EventAnalysisFilter(
							"56789", AttributeType.EVENT,
							EventAttributeDefinition.DataType.DATE, null,
							"testDate", "between",
							Arrays.asList("2021-05-10", "2021-05-13"))),
					246810L,
					DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
					DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
					_timeZoneDog.getTimeZoneId())));
	}

	@SQLResource(
		resourcePath = "test_get_average_event_count_per_individual.sql"
	)
	@Test
	public void testGetAverageEventCountPerIndividualWithTimeZone() {
		MatcherAssert.assertThat(
			BigDecimal.valueOf(1.5),
			Matchers.comparesEqualTo(
				_eventRepository.getAverageEventCountPerIndividual(
					1L,
					Collections.singletonList(
						new EventAnalysisFilter(
							"56789", AttributeType.EVENT,
							EventAttributeDefinition.DataType.DATE, null,
							"testDate", "between",
							Arrays.asList("2021-05-10", "2021-05-13"))),
					246810L,
					DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
					DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
					"America/Los_Angeles")));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesAverage() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.AVERAGE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/blogs", "https://www.beryl.com/design",
				"https://www.beryl.com/products"
			},
			keys.toArray());

		Collection<Number> numbers = eventAttributeValues.values();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.valueOf(1.50), BigDecimal.valueOf(1.00),
				BigDecimal.valueOf(1.00)
			},
			numbers.toArray(new BigDecimal[0]), 2);
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesBoolean() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"67890", AttributeType.EVENT, 1,
					EventAttributeDefinition.DataType.BOOLEAN, null, null,
					"testMember", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new Boolean[] {true, false, null}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {6, 4, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountBoolean() {
		Assertions.assertEquals(
			3,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"67890", AttributeType.EVENT, null,
					EventAttributeDefinition.DataType.BOOLEAN, null, null,
					"testMember", "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountDate() {
		Assertions.assertEquals(
			5,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, null,
					EventAttributeDefinition.DataType.DATE, DateGrouping.MONTH,
					null, "testDate", "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountDateWithTimeZone() {
		Assertions.assertEquals(
			2,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, null,
					EventAttributeDefinition.DataType.DATE, DateGrouping.DAY,
					null, "testDate", "DESC"),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				"America/Los_Angeles"));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountDuration() {
		Assertions.assertEquals(
			6,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"78901", AttributeType.EVENT, 2000,
					EventAttributeDefinition.DataType.DURATION, null, null,
					"testDuration", "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountNumber() {
		Assertions.assertEquals(
			8,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 1,
					EventAttributeDefinition.DataType.NUMBER, null, null,
					"testRating", "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountString() {
		Assertions.assertEquals(
			3,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesDayGrouping() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.DAY,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"2021-5-13", "2021-5-10", "2019-5-10", "2020-5-13", "2021-1-13",
				"2021-5-1", null
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {4, 2, 1, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesDayGroupingWithTimeZone() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.DAY,
					null, "testDate", "DESC"),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, null,
						"testDate", "between",
						Arrays.asList("2021-05-10", "2021-06-01"))),
				246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				"America/Los_Angeles");

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {"2021-5-12", "2021-5-13"}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {5, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesDuration() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"78901", AttributeType.EVENT, 2000,
					EventAttributeDefinition.DataType.DURATION, null, null,
					"testDuration", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new BigInteger[] {
				BigInteger.valueOf(0), BigInteger.valueOf(2000),
				BigInteger.valueOf(4000), BigInteger.valueOf(8000),
				BigInteger.valueOf(10000), null
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {3, 3, 2, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesMonthGrouping() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.MONTH,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {"2021-5", "2019-5", "2020-5", "2021-1", null},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {7, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesNullValues() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"78901", AttributeType.EVENT, 10000,
					EventAttributeDefinition.DataType.DURATION, null, null,
					"testDuration", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new BigInteger[] {
				BigInteger.valueOf(0), BigInteger.valueOf(10000), null
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {9, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesNumber() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 3,
					EventAttributeDefinition.DataType.NUMBER, null, null,
					"testRating", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.ZERO, BigDecimal.valueOf(3), BigDecimal.valueOf(9)
			},
			keys.toArray(new BigDecimal[0]), 0);

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {6, 3, 2}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesTotal() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 4, 3}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesUnique() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.UNIQUE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 4, 2}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesWithBreakdownItem() {
		BreakdownItem breakdownItem = new BreakdownItem();

		breakdownItem.setEventAnalysisFilters(
			Collections.singletonList(
				new EventAnalysisFilter(
					"34567", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, null, "testCode",
					"eq", Collections.singletonList("400"))));

		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, breakdownItem, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/products",
				"https://www.beryl.com/design", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 2, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesYearGrouping() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.YEAR,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = eventAttributeValues.keySet();

		Assertions.assertArrayEquals(
			new Integer[] {2021, 2019, 2020, null}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {8, 1, 1, 1}, values.toArray());
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventsCountGroupByEventDateLast7Days() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			2, eventsCountGroupByEventDate.size(),
			eventsCountGroupByEventDate.toString());

		Collection<Integer> values = eventsCountGroupByEventDate.values();

		MatcherAssert.assertThat(
			new Integer[] {1, 2},
			Matchers.arrayContainingInAnyOrder(values.toArray(new Integer[0])));
	}

	@Disabled
	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			2, eventsCountGroupByEventDate.size(),
			eventsCountGroupByEventDate.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> expectedCountGroupByEventDate =
			new HashMap<String, Integer>() {
				{
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -3, null, zonedDateTime),
						2);
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -6, null, zonedDateTime),
						1);
				}
			};

		Assertions.assertEquals(
			expectedCountGroupByEventDate, eventsCountGroupByEventDate);
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventsCountGroupByEventDateLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			1, eventsCountGroupByEventDate.size(),
			eventsCountGroupByEventDate.toString());

		Collection<Integer> values = eventsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assertions.assertEquals(4, iterator.next());
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			1, eventsCountGroupByEventDate.size(),
			eventsCountGroupByEventDate.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> expectedEventsCountGroupByEventDate =
			Collections.singletonMap(
				_getLocalDateString(ChronoUnit.HOURS, null, -1, zonedDateTime),
				4);

		Assertions.assertEquals(
			expectedEventsCountGroupByEventDate, eventsCountGroupByEventDate);
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7Days() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			2, eventSessionsCountGroupByEventDate.size(),
			eventSessionsCountGroupByEventDate.toString());

		Collection<Integer> values =
			eventSessionsCountGroupByEventDate.values();

		MatcherAssert.assertThat(
			new Integer[] {1, 2},
			Matchers.arrayContainingInAnyOrder(values.toArray(new Integer[0])));
	}

	@Disabled
	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			2, eventSessionsCountGroupByEventDate.size(),
			eventSessionsCountGroupByEventDate.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> expectedEventSessionsCountGroupByEventDate =
			new HashMap<String, Integer>() {
				{
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -3, null, zonedDateTime),
						2);
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -6, null, zonedDateTime),
						1);
				}
			};

		Assertions.assertEquals(
			expectedEventSessionsCountGroupByEventDate,
			eventSessionsCountGroupByEventDate);
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			1, eventSessionsCountGroupByEventDate.size(),
			eventSessionsCountGroupByEventDate.toString());

		Collection<Integer> values =
			eventSessionsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assertions.assertEquals(2, iterator.next());
	}

	@SQLResource(
		resourcePath = "test_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(
			1, eventSessionsCountGroupByEventDate.size(),
			eventSessionsCountGroupByEventDate.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> expectedEventSessionsCountGroupByEventDate =
			Collections.singletonMap(
				_getLocalDateString(ChronoUnit.HOURS, null, -1, zonedDateTime),
				2);

		Assertions.assertEquals(
			expectedEventSessionsCountGroupByEventDate,
			eventSessionsCountGroupByEventDate);
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testSearchEventsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		List<BQEvent> events = _eventRepository.searchEvents(
			1L, 1L, null, PageRequest.of(0, 50, Sort.desc("eventDate")),
			timeRange.getEndLocalDateTime(), timeRange.getStartLocalDateTime(),
			_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(3, events.size(), events.toString());

		Stream<BQEvent> stream = events.stream();

		Assertions.assertArrayEquals(
			new String[] {"blogClicked", "formViewed", "assetClicked"},
			stream.map(
				bqEvent -> {
					Optional<EventDefinition> eventDefinitionOptional =
						_eventDefinitionRepository.findByName(
							bqEvent.getEventId());

					EventDefinition eventDefinition =
						eventDefinitionOptional.get();

					return eventDefinition.getName();
				}
			).toArray());
	}

	@Disabled
	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testSearchEventsWithKeywordsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		List<BQEvent> events = _eventRepository.searchEvents(
			1L, 1L, "form", PageRequest.of(0, 50),
			timeRange.getEndLocalDateTime(), timeRange.getStartLocalDateTime(),
			_timeZoneDog.getTimeZoneId());

		Assertions.assertEquals(1, events.size(), events.toString());
	}

	private void _assertBigDecimalEquals(
		BigDecimal[] expectedValues, BigDecimal[] actualValues, int scale) {

		for (int i = 0; i < actualValues.length; i++) {
			BigDecimal actualValue = actualValues[i];

			actualValue = actualValue.setScale(scale, RoundingMode.HALF_UP);

			Assertions.assertEquals(
				0, actualValue.compareTo(expectedValues[i]));
		}
	}

	private String _getLocalDateString(
		ChronoUnit chronoUnit, Integer deltaDays, Integer deltaHours,
		ZonedDateTime zonedDateTime) {

		if (deltaDays != null) {
			zonedDateTime = zonedDateTime.plusDays(deltaDays);
		}

		if (deltaHours != null) {
			zonedDateTime = zonedDateTime.plusHours(deltaHours);
		}

		if (chronoUnit != null) {
			zonedDateTime = zonedDateTime.truncatedTo(chronoUnit);
		}

		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

		return localDateTime.toString();
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private PreferenceDog _preferenceDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}