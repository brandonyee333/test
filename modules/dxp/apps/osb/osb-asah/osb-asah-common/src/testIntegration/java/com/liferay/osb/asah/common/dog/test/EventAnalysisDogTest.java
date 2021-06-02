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
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
public class EventAnalysisDogTest {

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisAverage() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.AVERAGE, 1L, 246810L,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(20, eventAnalysis.getTotalEvents());
		Assert.assertEquals(2, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisTotal() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.TOTAL, 2L, 246810L,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(7, eventAnalysis.getTotalEvents());
		Assert.assertEquals(7, eventAnalysis.getValue());
	}

	@SQLResource(resourcePath = "test_get_event_analysis.sql")
	@Test
	public void testGetEventAnalysisUnique() {
		EventAnalysis eventAnalysis = _eventAnalysisDog.getEventAnalysis(
			AnalysisType.UNIQUE, 1L, 246810L,
			TimeRange.of(
				LocalDate.parse("2021-06-01"), LocalDate.parse("2021-05-15")));

		Assert.assertEquals(1, eventAnalysis.getCount());
		Assert.assertEquals(0, eventAnalysis.getPage());
		Assert.assertEquals(20, eventAnalysis.getTotalEvents());
		Assert.assertEquals(10, eventAnalysis.getValue());
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}