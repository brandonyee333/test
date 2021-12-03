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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.EventAnalysisResult;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventAnalysisRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class EventAnalysisDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_event_analysis.sql")
	@Test
	public void testEventAnalysis() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.addEventAnalysis(
			AnalysisType.TOTAL, 1L, false,
			Collections.singletonList(
				new EventAnalysisBreakdown(
					"1", AttributeType.EVENT, 10,
					EventAttributeDefinition.DataType.STRING, DateGrouping.DAY,
					"ASC")),
			Collections.singletonList(
				new EventAnalysisFilter(
					"1", AttributeType.EVENT,
					EventAttributeDefinition.DataType.STRING, "eq",
					Collections.singletonList("value"))),
			246810L, "Event Analysis",
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")),
			100L, "Test");

		Assertions.assertEquals(1, _eventAnalysisRepository.count());
		Assertions.assertNotNull(eventAnalysis);
		Assertions.assertNotNull(eventAnalysis.getId());

		EventAnalysis updatedEventAnalysis =
			_eventAnalysisDog.updateEventAnalysis(
				AnalysisType.TOTAL, 1L, false,
				Collections.singletonList(
					new EventAnalysisBreakdown(
						"1", AttributeType.EVENT, 10,
						EventAttributeDefinition.DataType.STRING,
						DateGrouping.DAY, "ASC")),
				Collections.singletonList(
					new EventAnalysisFilter(
						"1", AttributeType.EVENT,
						EventAttributeDefinition.DataType.STRING, "eq",
						Collections.singletonList("value"))),
				eventAnalysis.getId(), 246810L, "Event Analysis Update",
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")),
				101L, "Test Test");

		Assertions.assertNotNull(updatedEventAnalysis);
		Assertions.assertNotNull(updatedEventAnalysis.getId());
		Assertions.assertEquals(
			100L, updatedEventAnalysis.getCreatedByUserId());
		Assertions.assertEquals(
			"Test", updatedEventAnalysis.getCreatedByUserName());
		Assertions.assertEquals(
			101L, updatedEventAnalysis.getModifiedByUserId());
		Assertions.assertEquals(
			"Test Test", updatedEventAnalysis.getModifiedByUserName());
		Assertions.assertEquals(
			"Event Analysis Update", updatedEventAnalysis.getName());

		_eventAnalysisDog.deleteEventAnalyses(
			Collections.singletonList(updatedEventAnalysis.getId()));

		Assertions.assertEquals(0, _eventAnalysisRepository.count());
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisAverage() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.AVERAGE, 1L, false, Collections.emptyList(),
				Collections.emptyList(), 246810L, 0, 1,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());

		BigDecimal value = BigDecimal.valueOf(2);

		Assertions.assertEquals(
			0, value.compareTo((BigDecimal)eventAnalysisResult.getValue()));
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownAverage() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.AVERAGE, 1L, true,
				Collections.singletonList(eventAnalysisBreakdown),
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_average.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				new ArrayList<EventAnalysisBreakdown>() {
					{
						add(eventAnalysisBreakdown1);
						add(eventAnalysisBreakdown2);
					}
				},
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_boolean.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				new ArrayList<EventAnalysisBreakdown>() {
					{
						add(eventAnalysisBreakdown1);
						add(eventAnalysisBreakdown2);
					}
				},
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_day_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownDuration() throws Exception {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.UNIQUE, 1L, true,
				Collections.singletonList(
					new EventAnalysisBreakdown(
						"34567", AttributeType.EVENT, 2000,
						EventAttributeDefinition.DataType.DURATION, null,
						"DESC")),
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_duration.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				new ArrayList<EventAnalysisBreakdown>() {
					{
						add(eventAnalysisBreakdown1);
						add(eventAnalysisBreakdown2);
					}
				},
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_month_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownNullValues() throws Exception {
		EventAnalysisBreakdown eventAnalysisBreakdown1 =
			new EventAnalysisBreakdown(
				"78901", AttributeType.EVENT, 2000,
				EventAttributeDefinition.DataType.DURATION, null, "DESC");
		EventAnalysisBreakdown eventAnalysisBreakdown2 =
			new EventAnalysisBreakdown(
				"12345", AttributeType.EVENT, 0,
				EventAttributeDefinition.DataType.STRING, null, "DESC");

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				new ArrayList<EventAnalysisBreakdown>() {
					{
						add(eventAnalysisBreakdown1);
						add(eventAnalysisBreakdown2);
					}
				},
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_null_values.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownNumber() throws Exception {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				Collections.singletonList(
					new EventAnalysisBreakdown(
						"45678", AttributeType.EVENT, 2,
						EventAttributeDefinition.DataType.NUMBER, null,
						"DESC")),
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_number.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
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
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_total.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
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
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_event_analysis_breakdown_unique.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterAfter() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, "gt",
						Collections.singletonList("2021-03-14"))),
				246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-10")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(9L, eventAnalysisResult.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterBefore() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, "lt",
						Collections.singletonList("2021-03-14"))),
				246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-10")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(5L, eventAnalysisResult.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterBetween() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, "between",
						Arrays.asList("2020-01-01", "2021-05-10"))),
				246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-10")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(7L, eventAnalysisResult.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_breakdown.sql")
	@Test
	public void testGetEventAnalysisBreakdownWithDateFilterIs() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true, Collections.emptyList(),
				Collections.singletonList(
					new EventAnalysisFilter(
						"56789", AttributeType.EVENT,
						EventAttributeDefinition.DataType.DATE, "eq",
						Collections.singletonList("2021-05-13"))),
				246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-10")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(6L, eventAnalysisResult.getValue());
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
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
						Collections.singletonList(
							"https://www.beryl.com/design"))),
				246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_with_filter.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 1L, true,
				new ArrayList<EventAnalysisBreakdown>() {
					{
						add(eventAnalysisBreakdown1);
						add(eventAnalysisBreakdown2);
					}
				},
				Collections.emptyList(), 246810L, 0, 10,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_event_analysis_breakdown_year_grouping.json",
				this),
			_objectMapper.convertValue(eventAnalysisResult, JSONObject.class),
			true);
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

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.UNIQUE, 1L, false, Collections.emptyList(),
				eventAnalysisFilters, 246810L, 0, 1,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-18")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(1L, eventAnalysisResult.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis_with_filter.sql")
	@Test
	public void testGetEventAnalysisSingleFilter() {
		EventAnalysisFilter eventAnalysisFilter = new EventAnalysisFilter(
			"12345", AttributeType.EVENT,
			EventAttributeDefinition.DataType.STRING, "contains",
			Collections.singletonList("should"));

		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.AVERAGE, 1L, false, Collections.emptyList(),
				Collections.singletonList(eventAnalysisFilter), 246810L, 0, 1,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-19")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());

		BigDecimal value = BigDecimal.valueOf(1);

		Assertions.assertEquals(
			0, value.compareTo((BigDecimal)eventAnalysisResult.getValue()));
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisTotal() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.TOTAL, 2L, false, Collections.emptyList(),
				Collections.emptyList(), 246810L, 0, 1,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(7L, eventAnalysisResult.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisUnique() {
		EventAnalysisResult eventAnalysisResult =
			_eventAnalysisDog.getEventAnalysisResult(
				AnalysisType.UNIQUE, 1L, false, Collections.emptyList(),
				Collections.emptyList(), 246810L, 0, 1,
				TimeRange.of(
					LocalDate.parse("2021-06-01"),
					LocalDate.parse("2021-05-15")));

		Assertions.assertEquals(1, eventAnalysisResult.getCount());
		Assertions.assertEquals(0, eventAnalysisResult.getPage());
		Assertions.assertEquals(10L, eventAnalysisResult.getValue());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

	@Autowired
	private EventAnalysisRepository _eventAnalysisRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}