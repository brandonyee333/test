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
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.util.SetUtil;
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
public class BQEventRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@AfterEach
	public void tearDown() {
		_preferenceDog.savePreference("time-zone-id", "UTC");
	}

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testCountBQEventsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assertions.assertEquals(
			3,
			_bqEventRepository.countBQEvents(
				1L, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				SetUtil.of("1")));
	}

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testCountBQEventsWithKeywordsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Assertions.assertEquals(
			1,
			_bqEventRepository.countBQEvents(
				1L, "form", timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				SetUtil.of("1")));
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testCountTotalBQEvents() {
		Assertions.assertEquals(
			8L,
			_bqEventRepository.countTotalBQEvents(
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

	@SQLResource(resourcePath = "test_bq_event_global_property_values.sql")
	@Test
	public void testCountTotalBQEventsWithOnlyGlobalAttributes() {
		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findByName("pageTitle");

		Assertions.assertTrue(eventAttributeDefinitionOptional.isPresent());

		EventAttributeDefinition eventAttributeDefinition =
			eventAttributeDefinitionOptional.get();

		Assertions.assertEquals(
			1,
			_bqEventRepository.countTotalBQEvents(
				1L,
				Collections.singletonList(
					new EventAnalysisFilter(
						String.valueOf(eventAttributeDefinition.getId()),
						AttributeType.EVENT,
						EventAttributeDefinition.DataType.STRING, null,
						"pageTitle", "contains", Arrays.asList("test"))),
				246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 10, 0, 0)),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testCountTotalBQEventsWithTimeZone() {
		Assertions.assertEquals(
			6L,
			_bqEventRepository.countTotalBQEvents(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testCountUniqueIndividuals() {
		Assertions.assertEquals(
			6L,
			_bqEventRepository.countUniqueIndividuals(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testCountUniqueIndividualsWithTimeZone() {
		Assertions.assertEquals(
			5L,
			_bqEventRepository.countUniqueIndividuals(
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
		resourcePath = "test_get_average_bq_event_count_per_individual.sql"
	)
	@Test
	public void testGetAverageBQEventCountPerIndividual() {
		MatcherAssert.assertThat(
			BigDecimal.valueOf(2),
			Matchers.comparesEqualTo(
				_bqEventRepository.getAverageBQEventCountPerIndividual(
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
		resourcePath = "test_get_average_bq_event_count_per_individual.sql"
	)
	@Test
	public void testGetAverageBQEventCountPerIndividualWithTimeZone() {
		MatcherAssert.assertThat(
			BigDecimal.valueOf(1.5),
			Matchers.comparesEqualTo(
				_bqEventRepository.getAverageBQEventCountPerIndividual(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesAverage() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.AVERAGE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/blogs", "https://www.beryl.com/design",
				"https://www.beryl.com/products"
			},
			keys.toArray());

		Collection<Number> numbers = bqEventPropertyValues.values();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.valueOf(1.50), BigDecimal.valueOf(1.00),
				BigDecimal.valueOf(1.00)
			},
			numbers.toArray(new BigDecimal[0]), 2);
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesBoolean() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"67890", AttributeType.EVENT, 1,
					EventAttributeDefinition.DataType.BOOLEAN, null, null,
					"testMember", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new Boolean[] {true, false, null}, keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {6, 4, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesCountBoolean() {
		Assertions.assertEquals(
			3,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesCountDate() {
		Assertions.assertEquals(
			5,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesCountDateWithTimeZone() {
		Assertions.assertEquals(
			2,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesCountDuration() {
		Assertions.assertEquals(
			6,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesCountNumber() {
		Assertions.assertEquals(
			8,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesDayGroupingWithTimeZone() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
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

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {"2021-5-12", "2021-5-13"}, keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {5, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesDuration() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"78901", AttributeType.EVENT, 2000,
					EventAttributeDefinition.DataType.DURATION, null, null,
					"testDuration", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new BigInteger[] {
				BigInteger.valueOf(0), BigInteger.valueOf(2000),
				BigInteger.valueOf(4000), BigInteger.valueOf(8000),
				BigInteger.valueOf(10000), null
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {3, 3, 2, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesMonthGrouping() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.MONTH,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {"2021-5", "2019-5", "2020-5", "2021-1", null},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {7, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesNullValues() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"78901", AttributeType.EVENT, 10000,
					EventAttributeDefinition.DataType.DURATION, null, null,
					"testDuration", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new BigInteger[] {
				BigInteger.valueOf(0), BigInteger.valueOf(10000), null
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {9, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesNumber() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 3,
					EventAttributeDefinition.DataType.NUMBER, null, null,
					"testRating", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.ZERO, BigDecimal.valueOf(3), BigDecimal.valueOf(9)
			},
			keys.toArray(new BigDecimal[0]), 0);

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {6, 3, 2}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesTotal() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 4, 3}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesUnique() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.UNIQUE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/design",
				"https://www.beryl.com/products", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 4, 2}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesWithBreakdownItem() {
		BreakdownItem breakdownItem = new BreakdownItem();

		breakdownItem.setEventAnalysisFilters(
			Collections.singletonList(
				new EventAnalysisFilter(
					"34567", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, null, "testCode",
					"eq", Collections.singletonList("400"))));

		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, breakdownItem, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, null,
					"testUrl", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"https://www.beryl.com/products",
				"https://www.beryl.com/design", "https://www.beryl.com/blogs"
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(new Integer[] {4, 2, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetBQEventPropertyValuesYearGrouping() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.YEAR,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new Integer[] {2021, 2019, 2020, null}, keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {8, 1, 1, 1}, values.toArray());
	}

	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetBQEventsCountGroupByEventDateLast7Days() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> bqEventsCountGroupByEventDate =
			_bqEventRepository.getBQEventsCountGroupByEventDate(
				1L, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

		Assertions.assertEquals(
			2, bqEventsCountGroupByEventDate.size(),
			bqEventsCountGroupByEventDate.toString());

		Collection<Integer> values = bqEventsCountGroupByEventDate.values();

		MatcherAssert.assertThat(
			new Integer[] {1, 2},
			Matchers.arrayContainingInAnyOrder(values.toArray(new Integer[0])));
	}

	@Disabled
	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetBQEventsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> bqEventsCountGroupByEventDate =
			_bqEventRepository.getBQEventsCountGroupByEventDate(
				1L, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

		Assertions.assertEquals(
			2, bqEventsCountGroupByEventDate.size(),
			bqEventsCountGroupByEventDate.toString());

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
			expectedCountGroupByEventDate, bqEventsCountGroupByEventDate);
	}

	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetBQEventsCountGroupByEventDateLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> bqEventsCountGroupByEventDate =
			_bqEventRepository.getBQEventsCountGroupByEventDate(
				1L, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

		Assertions.assertEquals(
			1, bqEventsCountGroupByEventDate.size(),
			bqEventsCountGroupByEventDate.toString());

		Collection<Integer> values = bqEventsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assertions.assertEquals(4, iterator.next());
	}

	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetBQEventsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> bqEventsCountGroupByEventDate =
			_bqEventRepository.getBQEventsCountGroupByEventDate(
				1L, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

		Assertions.assertEquals(
			1, bqEventsCountGroupByEventDate.size(),
			bqEventsCountGroupByEventDate.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> expectedBQEventsCountGroupByEventDate =
			Collections.singletonMap(
				_getLocalDateString(ChronoUnit.HOURS, null, -1, zonedDateTime),
				4);

		Assertions.assertEquals(
			expectedBQEventsCountGroupByEventDate,
			bqEventsCountGroupByEventDate);
	}

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetEventPropertyValuesCountString() {
		Assertions.assertEquals(
			3,
			_bqEventRepository.getBQEventPropertyValuesCount(
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

	@SQLResource(resourcePath = "test_bq_event_property_values.sql")
	@Test
	public void testGetEventPropertyValuesDayGrouping() {
		Map<Object, Number> bqEventPropertyValues =
			_bqEventRepository.getBQEventPropertyValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.DAY,
					null, "testDate", "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)),
				_timeZoneDog.getTimeZoneId());

		Set<Object> keys = bqEventPropertyValues.keySet();

		Assertions.assertArrayEquals(
			new String[] {
				"2021-5-13", "2021-5-10", "2019-5-10", "2020-5-13", "2021-1-13",
				"2021-5-1", null
			},
			keys.toArray());

		Collection<Number> values = bqEventPropertyValues.values();

		Assertions.assertArrayEquals(
			new Integer[] {4, 2, 1, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7Days() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_bqEventRepository.getEventSessionsCountGroupByEventDate(
				1L, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

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
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_7_days.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_bqEventRepository.getEventSessionsCountGroupByEventDate(
				1L, Interval.DAY, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

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
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_bqEventRepository.getEventSessionsCountGroupByEventDate(
				1L, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

		Assertions.assertEquals(
			1, eventSessionsCountGroupByEventDate.size(),
			eventSessionsCountGroupByEventDate.toString());

		Collection<Integer> values =
			eventSessionsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assertions.assertEquals(2, iterator.next());
	}

	@SQLResource(
		resourcePath = "test_bq_event_count_grouped_by_event_date_last_24_hours.sql"
	)
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_bqEventRepository.getEventSessionsCountGroupByEventDate(
				1L, Interval.HOUR, null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				Collections.emptySet());

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

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testSearchBQEventsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		List<BQEvent> bqEvents = _bqEventRepository.searchBQEvents(
			1L, null, PageRequest.of(0, 50, Sort.desc("eventDate")),
			timeRange.getEndLocalDateTime(), timeRange.getStartLocalDateTime(),
			_timeZoneDog.getTimeZoneId(), SetUtil.of("1"));

		Assertions.assertEquals(3, bqEvents.size(), bqEvents.toString());

		Stream<BQEvent> stream = bqEvents.stream();

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
	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testSearchBQEventsWithKeywordsLast24Hours() {
		TimeRange timeRange = TimeRange.LAST_24_HOURS;

		List<BQEvent> bqEvents = _bqEventRepository.searchBQEvents(
			1L, "form", PageRequest.of(0, 50), timeRange.getEndLocalDateTime(),
			timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
			SetUtil.of("1"));

		Assertions.assertEquals(1, bqEvents.size(), bqEvents.toString());
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
	private BQEventRepository _bqEventRepository;

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private PreferenceDog _preferenceDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}