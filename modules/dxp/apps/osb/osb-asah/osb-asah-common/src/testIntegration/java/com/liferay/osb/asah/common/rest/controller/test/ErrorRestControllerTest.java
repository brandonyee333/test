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

import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorRestControllerTest {

	@Test
	public void test() {
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.port(_serverPort);

		Response response = requestSpecification.request(
			Method.GET, "/" + RandomTestUtil.randomUUID());

		ValidatableResponse validatableResponse = response.then();

		validatableResponse.body(
			"error", Matchers.equalTo("Not Found")
		).body(
			"message",
			Matchers.equalTo("Encountered error with status code 404")
		).body(
			"status", Matchers.equalTo(404)
		).contentType(
			ContentType.JSON
		);
	}

	@LocalServerPort
	private int _serverPort;

}