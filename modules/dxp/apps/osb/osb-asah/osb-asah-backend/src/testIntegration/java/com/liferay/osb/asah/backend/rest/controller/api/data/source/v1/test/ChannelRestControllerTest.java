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

import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author Riccardo Ferrari
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = OSBAsahBackendSpringBootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ChannelRestControllerTest
	implements OSBAsahTestExecutionListenersContext {

	@Test
	public void testPostDataSource() {
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.header(HeaderConstants.PROJECT_ID, "test");
		requestSpecification.port(_serverPort);

		Response response = requestSpecification.body(
			JSONUtil.put("name", "Test Channel")
		).request(
			Method.POST, "/api/1.0/channels"
		);

		ValidatableResponse validatableResponse = response.then();

		validatableResponse.body(
			".", Matchers.hasSize(1)
		).body(
			"[0].name", Matchers.equalTo("Test Channel")
		).body(
			"[0].id", Matchers.anything()
		).contentType(
			ContentType.JSON
		);
	}

	@Test
	public void testPostDataSources() throws Exception {
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.header(HeaderConstants.PROJECT_ID, "test");
		requestSpecification.port(_serverPort);

		List<JSONObject> groups = Arrays.asList(
			JSONUtil.put(
				"id", String.valueOf(RandomTestUtil.randomNumber())
			).put(
				"name", RandomTestUtil.randomString()
			),
			JSONUtil.put(
				"id", String.valueOf(RandomTestUtil.randomNumber())
			).put(
				"name", RandomTestUtil.randomString()
			));

		Response response = requestSpecification.body(
			JSONUtil.put(
				"channelType", "multiple"
			).put(
				"dataSourceId", String.valueOf(RandomTestUtil.randomNumber())
			).put(
				"groups", JSONUtil.toJSONArray(groups, t -> t)
			).toString()
		).request(
			Method.POST, "/api/1.0/channels"
		);

		ValidatableResponse validatableResponse = response.then();

		validatableResponse.body(
			".", Matchers.hasSize(2)
		).contentType(
			ContentType.JSON
		);
	}

	@LocalServerPort
	private int _serverPort;

}