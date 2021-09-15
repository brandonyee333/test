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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EventAnalysisDogTest {

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisAverage() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.AVERAGE, 1L, false, Collections.emptyList(),
			Collections.emptyList(), 246810L, 0, 1,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());

		BigDecimal value = BigDecimal.valueOf(2);

		Assert.assertEquals(
			0, value.compareTo((BigDecimal)eventAnalysis.getValue()));
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownAverage() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.AVERAGE, 1L, true,
			Collections.singletonList(eventAnalysisBreakdown),
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_average.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownBoolean() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"67890", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.BOOLEAN, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_boolean.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownDayGrouping() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"56789", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.DATE, DateGrouping.DAY,
				"DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_day_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownDuration() throws Exception {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.UNIQUE, 1L, true,
			Collections.singletonList(
				new EventAnalysisBreakdown(
					"34567", AttributeType.EVENT, 100,
					EventAttributeDefinition.DataType.DURATION, null, "DESC")),
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_duration.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownMonthGrouping() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"56789", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.DATE, DateGrouping.MONTH,
				"DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_month_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownNullValues() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"45678", AttributeType.EVENT, 1,
				EventAttributeDefinition.DataType.DURATION, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_null_values.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownNumber() throws Exception {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			Collections.singletonList(
				new EventAnalysisBreakdown(
					"45678", AttributeType.EVENT, 0.5,
					EventAttributeDefinition.DataType.NUMBER, null, "DESC")),
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_number.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownTotal() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"23456", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown3 =
			new EventAnalysisBreakdown(
				"34567", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
					add(eventAnalysisBreakdown3);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_total.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownUnique() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"34567", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"23456", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown3 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.UNIQUE, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
					add(eventAnalysisBreakdown3);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_unique.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterAfter() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
			Collections.singletonList(
				new EventAnalysisFilter(
					"56789", AttributeType.EVENT,
					EventAttributeDefinition.DataType.DATE, "gt",
					Collections.singletonList("2021-03-14"))),
			246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-10")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(9L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterBefore() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
			Collections.singletonList(
				new EventAnalysisFilter(
					"56789", AttributeType.EVENT,
					EventAttributeDefinition.DataType.DATE, "lt",
					Collections.singletonList("2021-03-14"))),
			246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-10")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(5L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterBetween() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
			Collections.singletonList(
				new EventAnalysisFilter(
					"56789", AttributeType.EVENT,
					EventAttributeDefinition.DataType.DATE, "between",
					Arrays.asList("2020-01-01", "2021-05-10"))),
			246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-10")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(7L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterIs() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
			Collections.singletonList(
				new EventAnalysisFilter(
					"56789", AttributeType.EVENT,
					EventAttributeDefinition.DataType.DATE, "eq",
					Collections.singletonList("2021-05-13"))),
			246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-10")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(6L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithFilter() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "ASC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"23456", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "ASC");
		EventAnalysisBreakdown eventAnalysisBreakdown3 =
			new EventAnalysisBreakdown(
				"34567", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "ASC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
					add(eventAnalysisBreakdown3);
				}
			},
			Collections.singletonList(
				new EventAnalysisFilter(
					"12345", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "eq",
					Collections.singletonList("https://www.beryl.com/design"))),
			246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_with_filter.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownYearGrouping() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"56789", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.DATE, DateGrouping.YEAR,
				"DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 1L, true,
			new ArrayList<EventAnalysisBreakdown>() {
				{
					add(eventAnalysisBreakdown1);
					add(eventAnalysisBreakdown2);
				}
			},
			Collections.emptyList(), 246810L, 0, 10,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_year_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysis, JSONObject.class), true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_with_filter.sql")
	@Test
	public void testGetEventAnalysisMultipleFilters() {
		EventAnalysisFilter eventAnalysisFilter1 = new EventAnalysisFilter(
			"12345", AttributeType.EVENT,
			EventAttributeDefinition.DataType.STRING, "contains",
			Collections.singletonList("test"));
		EventAnalysisFilter eventAnalysisFilter2 = new EventAnalysisFilter(
			"12345", AttributeType.EVENT,
			EventAttributeDefinition.DataType.STRING, "contains",
			Collections.singletonList("This"));
		EventAnalysisFilter eventAnalysisFilter3 = new EventAnalysisFilter(
			"23456", AttributeType.EVENT,
			EventAttributeDefinition.DataType.NUMBER, "between",
			new ArrayList<String>() {
				{
					add("200");
					add("300");
				}
			});

		List<EventAnalysisFilter> eventAnalysisFilters =
			new ArrayList<EventAnalysisFilter>() {
				{
					add(eventAnalysisFilter1);
					add(eventAnalysisFilter2);
					add(eventAnalysisFilter3);
				}
			};

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.UNIQUE, 1L, false, Collections.emptyList(),
			eventAnalysisFilters, 246810L, 0, 1,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-18")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(1L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_with_filter.sql")
	@Test
	public void testGetEventAnalysisSingleFilter() {
		EventAnalysisFilter eventAnalysisFilter = new EventAnalysisFilter(
			"12345", AttributeType.EVENT,
			EventAttributeDefinition.DataType.STRING, "contains",
			Collections.singletonList("should"));

		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.AVERAGE, 1L, false, Collections.emptyList(),
			Collections.singletonList(eventAnalysisFilter), 246810L, 0, 1,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-19")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());

		BigDecimal value = BigDecimal.valueOf(1);

		Assert.assertEquals(
			0, value.compareTo((BigDecimal)eventAnalysis.getValue()));
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisTotal() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 2L, false, Collections.emptyList(),
			Collections.emptyList(), 246810L, 0, 1,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(7L, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisUnique() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.UNIQUE, 1L, false, Collections.emptyList(),
			Collections.emptyList(), 246810L, 0, 1,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(10L, eventAnalysis.getValue());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceDog _preferenceDog;

}