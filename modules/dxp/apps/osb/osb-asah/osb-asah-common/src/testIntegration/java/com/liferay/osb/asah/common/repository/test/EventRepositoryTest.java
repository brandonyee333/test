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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.entity.Event;
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
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.hamcrest.Matchers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventRepositoryTest {

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testCountEventsLast24Hours() {
		Assert.assertEquals(
			Integer.valueOf(3),
			_eventRepository.countEvents(
				1L, 1L, null, TimeRange.LAST_24_HOURS));
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testCountEventsWithKeywordsLast24Hours() {
		Assert.assertEquals(
			Integer.valueOf(1),
			_eventRepository.countEvents(
				1L, 1L, "form", TimeRange.LAST_24_HOURS));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesAverage() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.AVERAGE, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, "DESC"),
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
	public void testGetEventAttributeValuesBoolean() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"67890", AttributeType.EVENT, 1,
					EventAttributeDefinition.DataType.BOOLEAN, null, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new Boolean[] {true, false, null}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {6, 4, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountBoolean() {
		Assert.assertEquals(
			3,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"67890", AttributeType.EVENT, null,
					EventAttributeDefinition.DataType.BOOLEAN, null, "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountDate() {
		Assert.assertEquals(
			5,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, null,
					EventAttributeDefinition.DataType.DATE, DateGrouping.MONTH,
					"DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountDuration() {
		Assert.assertEquals(
			4,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"34567", AttributeType.EVENT, 100,
					EventAttributeDefinition.DataType.DURATION, null, "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountNumber() {
		Assert.assertEquals(
			9,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 0.5,
					EventAttributeDefinition.DataType.NUMBER, null, "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesCountString() {
		Assert.assertEquals(
			3,
			_eventRepository.getEventAttributeValuesCount(
				1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, "DESC"),
				null, 246810L,
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0))));
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
					"DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {
				"2021-5-13", "2021-5-10", "2019-5-10", "2020-5-13", "2021-1-13",
				"2021-5-1", null
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(
			new Integer[] {4, 2, 1, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesDuration() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"34567", AttributeType.EVENT, 100,
					EventAttributeDefinition.DataType.DURATION, null, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new BigInteger[] {
				BigInteger.valueOf(400), BigInteger.valueOf(200),
				BigInteger.valueOf(300), BigInteger.valueOf(500)
			},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {8, 1, 1, 1}, values.toArray());
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
					"DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new String[] {"2021-5", "2019-5", "2020-5", "2021-1", null},
			keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(
			new Integer[] {7, 1, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesNullValues() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 1,
					EventAttributeDefinition.DataType.DURATION, null, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new BigInteger[] {null, BigInteger.valueOf(2)}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {10, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesNumber() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 2.5,
					EventAttributeDefinition.DataType.NUMBER, null, "DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		_assertBigDecimalEquals(
			new BigDecimal[] {
				BigDecimal.ZERO, BigDecimal.valueOf(2.5),
				BigDecimal.valueOf(7.5), BigDecimal.valueOf(5)
			},
			keys.toArray(new BigDecimal[0]), 2);

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {6, 2, 2, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesTotal() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, "DESC"),
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
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, "DESC"),
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
					"12345", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.STRING, null, "DESC"),
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

	@SQLResource(resourcePath = "test_event_attribute_values.sql")
	@Test
	public void testGetEventAttributeValuesYearGrouping() {
		Map<Object, Number> eventAttributeValues =
			_eventRepository.getEventAttributeValues(
				AnalysisType.TOTAL, null, 1L,
				new EventAnalysisBreakdown(
					"56789", AttributeType.EVENT, 0,
					EventAttributeDefinition.DataType.DATE, DateGrouping.YEAR,
					"DESC"),
				null, 246810L, PageRequest.of(0, 10),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 6, 1, 23, 59)),
				DateUtil.toUTCDate(LocalDateTime.of(2021, 5, 15, 0, 0)));

		Set<Object> keys = eventAttributeValues.keySet();

		Assert.assertArrayEquals(
			new Integer[] {2021, 2019, 2020, null}, keys.toArray());

		Collection<Number> values = eventAttributeValues.values();

		Assert.assertArrayEquals(new Integer[] {8, 1, 1, 1}, values.toArray());
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventsCountGroupByEventDateLast7Days() {
		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.DAY, null, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			eventsCountGroupByEventDate.toString(), 2,
			eventsCountGroupByEventDate.size());

		Collection<Integer> values = eventsCountGroupByEventDate.values();

		Assert.assertThat(
			new Integer[] {1, 2},
			Matchers.arrayContainingInAnyOrder(values.toArray(new Integer[0])));
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.DAY, null, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			eventsCountGroupByEventDate.toString(), 2,
			eventsCountGroupByEventDate.size());

		Map<String, Integer> expectedCountGroupByEventDate =
			new HashMap<String, Integer>() {
				{
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -2, null, zonedDateTime),
						2);
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -5, null, zonedDateTime),
						1);
				}
			};

		for (Map.Entry<String, Integer> entry :
				expectedCountGroupByEventDate.entrySet()) {

			if (eventsCountGroupByEventDate.containsKey(entry.getKey())) {
				Assert.assertEquals(
					entry.getValue(),
					eventsCountGroupByEventDate.get(entry.getKey()));

				eventsCountGroupByEventDate.remove(entry.getKey());
			}
		}

		Assert.assertTrue(eventsCountGroupByEventDate.isEmpty());
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventsCountGroupByEventDateLast24Hours() {
		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			eventsCountGroupByEventDate.toString(), 1,
			eventsCountGroupByEventDate.size());

		Collection<Integer> values = eventsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assert.assertEquals(Integer.valueOf(4), iterator.next());
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> eventsCountGroupByEventDate =
			_eventRepository.getEventsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			eventsCountGroupByEventDate.toString(), 1,
			eventsCountGroupByEventDate.size());

		Map<String, Integer> expectedEventsCountGroupByEventDate =
			Collections.singletonMap(
				_getLocalDateString(ChronoUnit.HOURS, null, -1, zonedDateTime),
				4);

		Assert.assertEquals(
			expectedEventsCountGroupByEventDate, eventsCountGroupByEventDate);
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7Days() {
		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.DAY, null, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			eventSessionsCountGroupByEventDate.toString(), 2,
			eventSessionsCountGroupByEventDate.size());

		Collection<Integer> values =
			eventSessionsCountGroupByEventDate.values();

		Assert.assertThat(
			new Integer[] {1, 2},
			Matchers.arrayContainingInAnyOrder(values.toArray(new Integer[0])));
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast7DaysWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.DAY, null, TimeRange.LAST_7_DAYS);

		Assert.assertEquals(
			eventSessionsCountGroupByEventDate.toString(), 2,
			eventSessionsCountGroupByEventDate.size());

		Map<String, Integer> expectedEventSessionsCountGroupByEventDate =
			new HashMap<String, Integer>() {
				{
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -2, null, zonedDateTime),
						2);
					put(
						_getLocalDateString(
							ChronoUnit.DAYS, -5, null, zonedDateTime),
						1);
				}
			};

		Assert.assertEquals(
			expectedEventSessionsCountGroupByEventDate,
			eventSessionsCountGroupByEventDate);
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24Hours() {
		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			eventSessionsCountGroupByEventDate.toString(), 1,
			eventSessionsCountGroupByEventDate.size());

		Collection<Integer> values =
			eventSessionsCountGroupByEventDate.values();

		Iterator<Integer> iterator = values.iterator();

		Assert.assertEquals(Integer.valueOf(2), iterator.next());
	}

	@SQLResource(resourcePath = "test_event_count_grouped_by_event_date.sql")
	@Test
	public void testGetEventSessionsCountGroupByEventDateLast24HoursWithTimeZone() {
		_preferenceDog.savePreference("time-zone-id", "America/Los_Angeles");

		ZonedDateTime zonedDateTime = ZonedDateTime.now(
			_timeZoneDog.getZoneId());

		Map<String, Integer> eventSessionsCountGroupByEventDate =
			_eventRepository.getEventSessionsCountGroupByEventDate(
				1L, null, Interval.HOUR, null, TimeRange.LAST_24_HOURS);

		Assert.assertEquals(
			eventSessionsCountGroupByEventDate.toString(), 1,
			eventSessionsCountGroupByEventDate.size());

		Map<String, Integer> expectedEventSessionsCountGroupByEventDate =
			Collections.singletonMap(
				_getLocalDateString(ChronoUnit.HOURS, null, -1, zonedDateTime),
				2);

		Assert.assertEquals(
			expectedEventSessionsCountGroupByEventDate,
			eventSessionsCountGroupByEventDate);
	}

	@SQLResource(resourcePath = "test_events.sql")
	@Test
	public void testSearchEventsLast24Hours() {
		List<Event> events = _eventRepository.searchEvents(
			1L, 1L, null, PageRequest.of(0, 50, Sort.desc("eventDate")),
			TimeRange.LAST_24_HOURS);

		Assert.assertEquals(events.toString(), 3, events.size());

		Stream<Event> stream = events.stream();

		Assert.assertArrayEquals(
			new String[] {"blogClicked", "formViewed", "assetClicked"},
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