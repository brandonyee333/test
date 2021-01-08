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

import com.liferay.osb.asah.backend.dog.SiteInterestCompositionDog;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Rachael Koestartyo
 */
@ElasticsearchIndex(
	name = "assets", resourcePath = "assets-info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "user-sessions", resourcePath = "user-sessions-info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class SiteInterestCompositionDogTest extends BaseCompositionDogTestCase {

	@Test
	public void testGetCompositionResultBagLast7Days() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 7, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("holistic roi", 1L);
				}
			},
			1, 1, 1);
	}

	@Test
	public void testGetCompositionResultBagLast24Hours() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 0, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("engineer", 1L);
					put("intuitive", 1L);
					put("models", 1L);
				}
			},
			1, 3, 1);
	}

	@Test
	public void testGetCompositionResultBagLast28Days() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 28, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling action-items", 1L);
					put("holistic roi", 1L);
				}
			},
			1, 2, 2);
	}

	@Test
	public void testGetCompositionResultBagLast30Days() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 30, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling action-items", 1L);
					put("holistic roi", 1L);
				}
			},
			1, 2, 2);
	}

	@Test
	public void testGetCompositionResultBagLast90Days() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 90, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling action-items", 1L);
					put("holistic roi", 1L);
				}
			},
			1, 2, 2);
	}

	@Test
	public void testGetCompositionResultBagYesterday() {
		checkResults(
			_siteInterestCompositionDog.getCompositionResultBag(
				"1", "355524992631037473", 1, 10, 0),
			new LinkedHashMap<String, Long>() {
				{
					put("holistic roi", 1L);
				}
			},
			1, 1, 1);
	}

	@Autowired
	private SiteInterestCompositionDog _siteInterestCompositionDog;

}