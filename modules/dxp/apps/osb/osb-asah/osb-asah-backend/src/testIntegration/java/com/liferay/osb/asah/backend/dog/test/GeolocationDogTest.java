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

import com.liferay.osb.asah.backend.constants.DataConstants;
import com.liferay.osb.asah.backend.dog.GeolocationDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
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
public class GeolocationDogTest {

	@ElasticsearchIndex(
		name = "journals", resourcePath = "geolocation_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGeolocationMetrics() {
		List<Metric> geolocationMetrics = _geolocationDog.getGeolocationMetrics(
			JournalMetricType.VIEWS,
			new SearchQueryContext("1", AssetType.JOURNAL));

		Assert.assertEquals(
			geolocationMetrics.toString(), 3, geolocationMetrics.size());

		DogTestUtil.assertMetric(2, geolocationMetrics, "Australia");
		DogTestUtil.assertMetric(3, geolocationMetrics, "Brazil");
		DogTestUtil.assertMetric(2, geolocationMetrics, "Germany");
	}

	@ElasticsearchIndex(
		name = "forms", resourcePath = "forms_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUnknownGeolocationMetric() {
		List<Metric> geolocationMetrics = _geolocationDog.getGeolocationMetrics(
			FormMetricType.VIEWS,
			new SearchQueryContext("2", AssetType.FORM) {
				{
					setCountry(DataConstants.UNKNOWN);
				}
			});

		Assert.assertEquals(
			geolocationMetrics.toString(), 1, geolocationMetrics.size());

		DogTestUtil.assertMetric(1, geolocationMetrics, DataConstants.UNKNOWN);
	}

	@Autowired
	private GeolocationDog _geolocationDog;

}