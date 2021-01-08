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

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DummyRestControllerTest {

	@Test
	public void testDelete() {
		_validateResponse(
			Matchers.equalTo(4),
			_getValidatableResponse(Method.DELETE, "/dummy"));
	}

	@Test
	public void testGet() {
		_validateResponse(
			Matchers.equalTo(1), _getValidatableResponse(Method.GET, "/dummy"));
	}

	@Test
	public void testPatch() {
		_validateResponse(
			Matchers.equalTo(5),
			_getValidatableResponse(
				Method.PATCH, "/dummy",
				String.valueOf(JSONUtil.put("dummy", 5))));
	}

	@Test
	public void testPost() {
		_validateResponse(
			Matchers.equalTo(2),
			_getValidatableResponse(Method.POST, "/dummy?value=2"));
	}

	@Test
	public void testPut() {
		_validateResponse(
			Matchers.equalTo(3), _getValidatableResponse(Method.PUT, "/dummy"));
	}

	private ValidatableResponse _getValidatableResponse(
		Method method, String path) {

		return _getValidatableResponse(method, path, "");
	}

	private ValidatableResponse _getValidatableResponse(
		Method method, String path, String body) {

		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.body(body);
		requestSpecification.port(_serverPort);

		Response response = requestSpecification.request(method, path);

		return response.then();
	}

	private void _validateResponse(
		Matcher<?> matcher, ValidatableResponse validatableResponse) {

		validatableResponse.body(
			"dummy", matcher
		).contentType(
			ContentType.JSON
		);
	}

	@LocalServerPort
	private int _serverPort;

}