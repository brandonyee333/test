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

package com.liferay.osb.asah.common.rest.controller.test;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BulkRestControllerTest {

	@Test
	public void testPost() {
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification = requestSpecification.body(
			String.valueOf(
				JSONUtil.put(
					"operations",
					JSONUtil.putAll(
						JSONUtil.put(
							"method", "DELETE"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"method", "GET"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"body", JSONUtil.put("dummy", 5)
						).put(
							"method", "PATCH"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"method", "POST"
						).put(
							"url", "/dummy?value=2"
						),
						JSONUtil.put(
							"method", "PUT"
						).put(
							"url", "/dummy"
						))))
		).contentType(
			"application/json"
		).header(
			HeaderConstants.PROJECT_ID, "test"
		);

		requestSpecification.port(_serverPort);

		Response response = requestSpecification.post("/bulk");

		ValidatableResponse validatableResponse = response.then();

		validatableResponse.body(
			"responses.dummy",
			Matchers.contains(
				Arrays.asList(
					Matchers.equalTo(4), Matchers.equalTo(1),
					Matchers.equalTo(5), Matchers.equalTo(2),
					Matchers.equalTo(3)))
		).contentType(
			ContentType.JSON
		);
	}

	@LocalServerPort
	private int _serverPort;

}