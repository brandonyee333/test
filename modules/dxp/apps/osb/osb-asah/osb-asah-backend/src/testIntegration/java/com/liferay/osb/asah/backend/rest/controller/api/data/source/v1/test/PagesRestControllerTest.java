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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.PagesRestController;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.LocalDate;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public class PagesRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetReadCounts() {
		LocalDate localDate = LocalDate.now();

		JSONObject responseJSONObject = new JSONObject(
			_pagesRestController.getReadCounts(
				"https://liferay.com", localDate, Interval.DAY.getKey(),
				localDate.minusDays(2)));

		Assertions.assertEquals(3, responseJSONObject.get("value"));

		JSONObject histogramMetricJSONObject = (JSONObject)JSONUtil.getValue(
			responseJSONObject, "JSONArray/histogram", "Object/0");

		Assertions.assertTrue(histogramMetricJSONObject.has("key"));
		Assertions.assertTrue(histogramMetricJSONObject.has("value"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetReadsCount() {
		Assertions.assertEquals(
			"3", _pagesRestController.getReadsCount("https://liferay.com"));
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
				"https://liferay.com", localDate, Interval.DAY.getKey(),
				localDate.minusDays(2)));

		Assertions.assertEquals(6, responseJSONObject.get("value"));

		JSONObject histogramMetricJSONObject = (JSONObject)JSONUtil.getValue(
			responseJSONObject, "JSONArray/histogram", "Object/0");

		Assertions.assertTrue(histogramMetricJSONObject.has("key"));
		Assertions.assertTrue(histogramMetricJSONObject.has("value"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetViewsCount() {
		Assertions.assertEquals(
			"6", _pagesRestController.getViewsCount("https://liferay.com"));
	}

	@Autowired
	private PagesRestController _pagesRestController;

}