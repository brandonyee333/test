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
import com.liferay.osb.asah.backend.dog.IndividualMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.IndividualMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matthew Kong
 */
public class IndividualMetricDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_histogram_info.json"
	)
	@Test
	public void testIndividualMetric() {
		IndividualMetric individualMetric = _getIndividualMetric();

		Metric anonymousIndividualsMetric =
			individualMetric.getAnonymousIndividualsMetric();

		Assertions.assertEquals(
			0, anonymousIndividualsMetric.getPreviousValue(), 0);
		Assertions.assertEquals(1, anonymousIndividualsMetric.getValue(), 0);

		Metric knownIndividualsMetric =
			individualMetric.getKnownIndividualsMetric();

		Assertions.assertEquals(
			1, knownIndividualsMetric.getPreviousValue(), 0);
		Assertions.assertEquals(4, knownIndividualsMetric.getValue(), 0);

		Metric totalIndividualsMetric =
			individualMetric.getTotalIndividualsMetric();

		Assertions.assertEquals(
			1, totalIndividualsMetric.getPreviousValue(), 0);
		Assertions.assertEquals(5, totalIndividualsMetric.getValue(), 0);
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