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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.AnalyticsEventsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AnalyticsEventsRestControllerTest {

	@ElasticsearchIndex(
		name = "analytics-events", resourcePath = "analytics-events.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_RAW
	)
	@Test
	public void testGetAnalyticsEventValues() throws Exception {

		// Filter

		JSONAssert.assertEquals(
			JSONUtil.putAll("California", "Indiana"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_analyticsEventsRestController.getAnalyticsEventValues(
						"context/region", "context/country eq 'United States'",
						0, 20, null)),
				"JSONObject/_embedded", "JSONArray/analytics-event-values"),
			false);

		// No search

		JSONAssert.assertEquals(
			JSONUtil.putAll("Budapest", "California", "Indiana", "Tokyo"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_analyticsEventsRestController.getAnalyticsEventValues(
						"context/region", null, 0, 20, null)),
				"JSONObject/_embedded", "JSONArray/analytics-event-values"),
			false);

		// Search

		JSONAssert.assertEquals(
			JSONUtil.put("California"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_analyticsEventsRestController.getAnalyticsEventValues(
						"context/region", null, 0, 20, "cal")),
				"JSONObject/_embedded", "JSONArray/analytics-event-values"),
			false);
	}

	@Autowired
	private AnalyticsEventsRestController _analyticsEventsRestController;

}