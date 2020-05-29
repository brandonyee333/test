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

import com.liferay.osb.asah.backend.dog.SearchTermCompositionDog;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;

import java.util.Collections;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Rachael Koestartyo
 */
@ElasticsearchIndex(
	name = "pages", resourcePath = "pages-info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class SearchTermCompositionDogTest extends BaseCompositionDogTestCase {

	@Test
	public void testGetCompositionResultBag() {
		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0, TimeRange.of(90)),
			new LinkedHashMap<String, Long>() {
				{
					put("liferay", 3L);
					put("liferay dxp", 1L);
				}
			},
			3, 2, 4);
	}

	@Test
	public void testTimeRangeCompositionResultBag() {
		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0, TimeRange.of(0)),
			Collections.emptyMap(), 0, 0, 0);
		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0, TimeRange.of(30)),
			new LinkedHashMap<String, Long>() {
				{
					put("liferay", 3L);
					put("liferay dxp", 1L);
				}
			},
			3, 2, 4);
		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0, TimeRange.of(180)),
			new LinkedHashMap<String, Long>() {
				{
					put("liferay", 3L);
					put("liferay dxp", 2L);
				}
			},
			3, 2, 5);
		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0, TimeRange.of(365)),
			new LinkedHashMap<String, Long>() {
				{
					put("liferay", 4L);
					put("liferay dxp", 2L);
				}
			},
			4, 2, 6);

		LocalDate localDate = LocalDate.now();

		checkResults(
			_searchTermCompositionDog.getCompositionResultBag(
				"2", "333962835564819979", 10, 0,
				TimeRange.of(localDate.minusDays(3), localDate.minusDays(180))),
			new LinkedHashMap<String, Long>() {
				{
					put("liferay dxp", 1L);
				}
			},
			1, 1, 1);
	}

	@Autowired
	private SearchTermCompositionDog _searchTermCompositionDog;

}