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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.test;

import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.PagesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Shinn Lok
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class PagesRestControllerTest {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetReadCount() {
		Assert.assertEquals(
			"3",
			_pagesRestController.getReadCount("http://192.168.108.90:8080/"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetReadCounts() {
		LocalDate localDate = LocalDate.now();

		JSONObject responseJSONObject = new JSONObject(
			_pagesRestController.getReadCounts(
				localDate, Interval.DAY.getKey(), localDate.minusDays(2),
				"http://192.168.108.90:8080/"));

		Assert.assertEquals(3, responseJSONObject.get("value"));

		JSONObject histogramMetricJSONObject = (JSONObject)JSONUtil.getValue(
			responseJSONObject, "JSONArray/histogram", "Object/0");

		Assert.assertTrue(histogramMetricJSONObject.has("key"));
		Assert.assertTrue(histogramMetricJSONObject.has("value"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetViewCount() {
		Assert.assertEquals(
			"6",
			_pagesRestController.getViewCount("http://192.168.108.90:8080/"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetViewCounts() {
		LocalDate localDate = LocalDate.now();

		JSONObject responseJSONObject = new JSONObject(
			_pagesRestController.getViewCounts(
				localDate, Interval.DAY.getKey(), localDate.minusDays(2),
				"http://192.168.108.90:8080/"));

		Assert.assertEquals(6, responseJSONObject.get("value"));

		JSONObject histogramMetricJSONObject = (JSONObject)JSONUtil.getValue(
			responseJSONObject, "JSONArray/histogram", "Object/0");

		Assert.assertTrue(histogramMetricJSONObject.has("key"));
		Assert.assertTrue(histogramMetricJSONObject.has("value"));
	}

	@Autowired
	private PagesRestController _pagesRestController;

}