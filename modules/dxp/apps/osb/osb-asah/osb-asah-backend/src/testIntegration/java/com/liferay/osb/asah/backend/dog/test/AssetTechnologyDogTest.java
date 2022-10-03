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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.repository.CrudBQJournalRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lino Alves
 */
@Disabled
public class AssetTechnologyDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/technology_journal_info.json"
	)
	@Test
	public void testBrowserMetrics() {
		List<Metric> browserMetrics = _metricDog.getBrowserMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL) {
				{
					setTimeRange(TimeRange.LAST_7_DAYS);
				}
			});

		Assertions.assertEquals(
			3, browserMetrics.size(), browserMetrics.toString());
	}

	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/technology_journal_info.json"
	)
	@Test
	public void testDeviceMetrics() {
		List<Metric> deviceMetrics = _metricDog.getDeviceMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL) {
				{
					setTimeRange(TimeRange.LAST_7_DAYS);
				}
			});

		Assertions.assertEquals(
			2, deviceMetrics.size(), deviceMetrics.toString());

		DogTestUtil.assertMetric(5, deviceMetrics, "Desktop");
		DogTestUtil.assertMetric(2, deviceMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(2, deviceMetrics, "Desktop", "MacOS");
		DogTestUtil.assertMetric(1, deviceMetrics, "Desktop", "Windows");
		DogTestUtil.assertMetric(2, deviceMetrics, "Mobile");
		DogTestUtil.assertMetric(2, deviceMetrics, "Mobile", "Android");
		DogTestUtil.assertMetric(0, deviceMetrics, "Mobile", "Windows");
	}

	@Autowired
	private MetricDog _metricDog;

}