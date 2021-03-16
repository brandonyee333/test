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

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author Vishal Reddy
 */
public abstract class BaseRestControllerTestCase {

	@Before
	public void setUp() throws Exception {
		_faroInfoIndividualDog.updateDynamicMemberships(
			JSONUtil.put(
				"filter", "(((demographics/age/value gt '50')))"
			).put(
				"id", "327968823603500655"
			),
			new Date());
	}

	protected ValidatableResponse getValidatableResponse(
		Method method, String path) {

		return _getValidatableResponse("", method, path);
	}

	protected void validateIndividuals(
		ValidatableResponse validatableResponse) {

		validateResponse(
			Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)),
			"_embedded.individuals", validatableResponse);

		validateResponse(
			Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)),
			"_embedded.individuals[0].dataSourceIndividualPKs[0].individualPKs",
			validatableResponse);

		validateResponse(
			Matchers.isA(String.class), "_embedded.individuals[0].dateCreated",
			validatableResponse);

		validateResponse(
			Matchers.isA(String.class), "_embedded.individuals[0].dateModified",
			validatableResponse);

		validateResponse(
			Matchers.notNullValue(),
			"_embedded.individuals[0].demographics.age", validatableResponse);

		validateResponse(
			Matchers.notNullValue(),
			"_embedded.individuals[0].demographics.email", validatableResponse);

		List<String> demographicsEmailFieldNames = new ArrayList<String>() {
			{
				add("context");
				add("dataSourceId");
				add("dataSourceName");
				add("dateModified");
				add("fieldType");
				add("name");
				add("ownerId");
				add("ownerType");
				add("sourceName");
				add("value");
			}
		};

		for (String demographicsEmailFieldName : demographicsEmailFieldNames) {
			validateResponse(
				Matchers.isA(String.class),
				"_embedded.individuals[0].demographics.email[0]." +
					demographicsEmailFieldName,
				validatableResponse);
		}

		validateResponse(
			Matchers.isA(String.class), "_embedded.individuals[0].id",
			validatableResponse);

		validateResponse(
			Matchers.hasItem("327968823603500655"),
			"_embedded.individuals[0].individualSegmentIds",
			validatableResponse);

		validateResponse(
			Matchers.isA(Integer.class), "page.number", validatableResponse);

		validateResponse(
			Matchers.isA(Integer.class), "page.size", validatableResponse);

		validateResponse(
			Matchers.isA(Integer.class), "page.totalElements",
			validatableResponse);

		validateResponse(
			Matchers.isA(Integer.class), "page.totalPages",
			validatableResponse);
	}

	protected void validateResponse(
		Matcher<?> matcher, String path,
		ValidatableResponse validatableResponse) {

		validatableResponse.body(
			path, matcher
		).contentType(
			ContentType.JSON
		);
	}

	private ValidatableResponse _getValidatableResponse(
		String body, Method method, String path) {

		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.body(body);
		requestSpecification.header(HeaderConstants.PROJECT_ID, "test");
		requestSpecification.port(_serverPort);

		Response response = requestSpecification.request(method, path);

		return response.then();
	}

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@LocalServerPort
	private int _serverPort;

}