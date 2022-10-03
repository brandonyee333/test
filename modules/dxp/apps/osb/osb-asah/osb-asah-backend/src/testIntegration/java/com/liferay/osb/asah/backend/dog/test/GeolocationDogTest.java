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
import com.liferay.osb.asah.backend.constants.DataConstants;
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.repository.CrudBQFormRepository;
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
public class GeolocationDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/geolocation_journal_info.json"
	)
	@Test
	public void testGeolocationMetrics() {
		List<Metric> geolocationMetrics = _metricDog.getGeolocationMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL));

		Assertions.assertEquals(
			3, geolocationMetrics.size(), geolocationMetrics.toString());

		DogTestUtil.assertMetric(2, geolocationMetrics, "Australia");
		DogTestUtil.assertMetric(3, geolocationMetrics, "Brazil");
		DogTestUtil.assertMetric(2, geolocationMetrics, "Germany");
	}

	@Disabled
	@RepositoryResource(
		repositoryClass = CrudBQFormRepository.class,
		resourcePath = "osbasahcerebroinfo/forms_info.json"
	)
	@Test
	public void testUnknownGeolocationMetric() {
		List<Metric> geolocationMetrics = _metricDog.getGeolocationMetrics(
			FormMetricType.VIEWS,
			new SearchQueryContext("2", AssetType.FORM) {
				{
					setCountry(DataConstants.UNKNOWN);
				}
			});

		Assertions.assertEquals(
			1, geolocationMetrics.size(), geolocationMetrics.toString());

		DogTestUtil.assertMetric(1, geolocationMetrics, DataConstants.UNKNOWN);
	}

	@Autowired
	private MetricDog _metricDog;

}