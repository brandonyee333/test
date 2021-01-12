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

import com.liferay.osb.asah.backend.dog.IndividualMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.IndividualMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class IndividualMetricDogTest {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_histogram_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testIndividualMetric() {
		IndividualMetric individualMetric = _getIndividualMetric();

		Metric anonymousIndividualsMetric =
			individualMetric.getAnonymousIndividualsMetric();

		Assert.assertEquals(
			0, anonymousIndividualsMetric.getPreviousValue(), 0);
		Assert.assertEquals(1, anonymousIndividualsMetric.getValue(), 0);

		Metric knownIndividualsMetric =
			individualMetric.getKnownIndividualsMetric();

		Assert.assertEquals(1, knownIndividualsMetric.getPreviousValue(), 0);
		Assert.assertEquals(4, knownIndividualsMetric.getValue(), 0);

		Metric totalIndividualsMetric =
			individualMetric.getTotalIndividualsMetric();

		Assert.assertEquals(1, totalIndividualsMetric.getPreviousValue(), 0);
		Assert.assertEquals(5, totalIndividualsMetric.getValue(), 0);
	}

	private IndividualMetric _getIndividualMetric() {
		return _individualMetricDog.getIndividualMetric(
			new SearchQueryContext(AssetType.INDIVIDUAL_METRIC) {
				{
					setInterval("D");
					setRangeKey(30);
				}
			},
			new HashSet<String>() {
				{
					add(IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName());
					add(IndividualMetricType.KNOWN_INDIVIDUALS.getName());
					add(IndividualMetricType.TOTAL_INDIVIDUALS.getName());
				}
			});
	}

	@Autowired
	private IndividualMetricDog _individualMetricDog;

}