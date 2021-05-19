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

import com.liferay.osb.asah.backend.rest.controller.SessionsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class SessionsRestControllerTest {

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_sessions_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetSessionValues() throws Exception {
		JSONAssert.assertEquals(
			JSONUtil.putAll("California", "Indiana"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getSessionValues(
						"context/region", "context/country eq 'United States'",
						0, 20, null)),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
		JSONAssert.assertEquals(
			JSONUtil.putAll("Budapest", "California", "Indiana", "Tokyo"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getSessionValues(
						"context/region", null, 0, 20, null)),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
		JSONAssert.assertEquals(
			JSONUtil.put("California"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getSessionValues(
						"context/region", null, 0, 20, "cal")),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
	}

	@Autowired
	private SessionsRestController _sessionsRestController;

}