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

import com.liferay.osb.asah.backend.dog.AcquisitionCompositionDog;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "user-sessions", resourcePath = "user_sessions_info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class AcquisitionCompositionDogTest extends BaseCompositionDogTestCase {

	@Test
	public void testGetChannelCompositionResultBag() {
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"CHANNEL", "1", "355524992631037473", 10, 0, TimeRange.of(90)),
			new LinkedHashMap<String, Long>() {
				{
					put("referral", 2L);
				}
			},
			2, 1, 2);
	}

	@Test
	public void testReferrerCompositionResultBag() {
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"REFERRER", "1", "355524992631037473", 10, 0, TimeRange.of(90)),
			new LinkedHashMap<String, Long>() {
				{
					put("www.liferay.com", 1L);
					put("loop.liferay.com", 1L);
				}
			},
			1, 2, 2);
	}

	@Test
	public void testSourceMediumCompositionResultBag() {
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"SOURCE_MEDIUM", "1", "355524992631037473", 10, 0,
				TimeRange.of(90)),
			new LinkedHashMap<String, Long>() {
				{
					put("www.liferay.com / referral", 1L);
					put("loop.liferay.com / referral", 1L);
				}
			},
			1, 2, 2);
	}

	@Test
	public void testTimeRangeCompositionResultBag() {
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"CHANNEL", "1", "355524992631037473", 10, 0, TimeRange.of(30)),
			new LinkedHashMap<String, Long>() {
				{
					put("referral", 2L);
				}
			},
			2, 1, 2);
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"CHANNEL", "1", "355524992631037473", 10, 0, TimeRange.of(180)),
			new LinkedHashMap<String, Long>() {
				{
					put("referral", 3L);
				}
			},
			3, 1, 3);
		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"CHANNEL", "1", "355524992631037473", 10, 0, TimeRange.of(365)),
			new LinkedHashMap<String, Long>() {
				{
					put("referral", 4L);
				}
			},
			4, 1, 4);

		LocalDate localDate = LocalDate.now();

		checkResults(
			_acquisitionCompositionDog.getCompositionResultBag(
				"CHANNEL", "1", "355524992631037473", 10, 0,
				TimeRange.of(localDate.minusDays(4), localDate.minusDays(80))),
			new LinkedHashMap<String, Long>() {
				{
					put("referral", 1L);
				}
			},
			1, 1, 1);
	}

	@Autowired
	private AcquisitionCompositionDog _acquisitionCompositionDog;

}