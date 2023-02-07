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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.SessionsRestController;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class SessionsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@RepositoryResource(
		repositoryClass = BQSessionRepository.class,
		resourcePath = "osbasahcerebroinfo/user_sessions_info.json"
	)
	@Test
	public void testGetSessionValues() throws Exception {
		JSONAssert.assertEquals(
			JSONUtil.putAll("California", "Indiana"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getBQSessionFieldValuePageDTO(
						"context/region", "context/country eq 'United States'",
						0, 20, null)),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
		JSONAssert.assertEquals(
			JSONUtil.putAll("Budapest", "California", "Indiana", "Tokyo"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getBQSessionFieldValuePageDTO(
						"context/region", null, 0, 20, null)),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
		JSONAssert.assertEquals(
			JSONUtil.put("California"),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_sessionsRestController.getBQSessionFieldValuePageDTO(
						"context/region", null, 0, 20, "cal")),
				"JSONObject/_embedded", "JSONArray/session-values"),
			false);
	}

	@Autowired
	private SessionsRestController _sessionsRestController;

}