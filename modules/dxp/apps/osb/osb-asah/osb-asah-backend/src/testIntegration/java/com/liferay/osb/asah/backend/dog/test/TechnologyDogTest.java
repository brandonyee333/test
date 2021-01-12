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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.TechnologyDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lino Alves
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class TechnologyDogTest {

	@ElasticsearchIndex(
		name = "journals", resourcePath = "technology_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBrowserMetrics() {
		List<Metric> browserMetrics = _technologyDog.getBrowserMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL) {
				{
					setTimeRange(TimeRange.LAST_7_DAYS);
				}
			});

		Assert.assertEquals(
			browserMetrics.toString(), 3, browserMetrics.size());
	}

	@ElasticsearchIndex(
		name = "journals", resourcePath = "technology_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testDeviceMetrics() {
		List<Metric> deviceMetrics = _technologyDog.getDeviceMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL) {
				{
					setTimeRange(TimeRange.LAST_7_DAYS);
				}
			});

		Assert.assertEquals(deviceMetrics.toString(), 2, deviceMetrics.size());

		DogTestUtil.assertMetric(5, deviceMetrics, "Desktop");
		DogTestUtil.assertMetric(2, deviceMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(2, deviceMetrics, "Desktop", "MacOS");
		DogTestUtil.assertMetric(1, deviceMetrics, "Desktop", "Windows");
		DogTestUtil.assertMetric(2, deviceMetrics, "Mobile");
		DogTestUtil.assertMetric(2, deviceMetrics, "Mobile", "Android");
		DogTestUtil.assertMetric(0, deviceMetrics, "Mobile", "Windows");
	}

	@Autowired
	private TechnologyDog _technologyDog;

}