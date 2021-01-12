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

package com.liferay.osb.asah.backend.dog.experiment.test;

import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataDog;
import com.liferay.osb.asah.backend.dog.experiment.ExperimentDataPoint;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ExperimentDataDogTest {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testFetchDichotomousDataPointBounceMetric() {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		ExperimentDataPoint<Double> experimentDataPoint =
			_experimentDataDog.fetchDichotomousDataPoint(
				null, null, null, PageMetricType.BOUNCE_RATE, null,
				TimeRange.of(localDate.minusDays(1), localDate.minusDays(1)),
				"1");

		Assert.assertEquals(4, experimentDataPoint.getTrials());
		Assert.assertEquals(3.0, experimentDataPoint.getValue(), 0);
	}

	@Autowired
	private ExperimentDataDog _experimentDataDog;

}