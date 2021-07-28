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

import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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

}