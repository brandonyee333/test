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

package com.liferay.osb.asah.common.http.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.http.impl.ReportHttpImpl;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.Http;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

/**
 * @author Alejo Ceballos
 */
public class ReportHttpTest implements OSBAsahCommonSpringTestContext {

	@BeforeAll
	public static void setUpClass() {
		Mockito.when(
			_httpMock.exchange(
				Mockito.anyString(), Mockito.anyString(),
				Mockito.any(HttpMethod.class), Mockito.isNull())
		).thenAnswer(
			(Answer<String>)invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"result", invocation.getArguments()[1]);

				return jsonObject.toString();
			}
		);
	}

	@Test
	public void testGetAccountsJSONObject() {
		ReportHttp reportHttp = new ReportHttpImpl(_httpMock);

		JSONObject jsonObject = reportHttp.getAccountsJSONObject(
			"0", "2022-03-01T12:00:00.000Z", "2022-03-31T12:00:00.000Z");

		Assertions.assertEquals(
			jsonObject.get("result"),
			"/reports/accounts?after=0&fromDate=2022-03-01T12:00:00.000Z&" +
				"toDate=2022-03-31T12:00:00.000Z");
	}

	@Test
	public void testGetIndividualsJSONObject() {
		ReportHttp reportHttp = new ReportHttpImpl(_httpMock);

		JSONObject jsonObject = reportHttp.getIndividualsJSONObject(
			"0", "2022-03-01T12:00:00.000Z", "2022-03-31T12:00:00.000Z");

		Assertions.assertEquals(
			jsonObject.get("result"),
			"/reports/individuals?after=0&fromDate=2022-03-01T12:00:00.000Z&" +
				"toDate=2022-03-31T12:00:00.000Z");
	}

	@Test
	public void testGetSegmentsJSONObject() {
		ReportHttp reportHttp = new ReportHttpImpl(_httpMock);

		JSONObject jsonObject = reportHttp.getSegmentsJSONObject(
			"0", "2022-03-01T12:00:00.000Z", "2022-03-31T12:00:00.000Z");

		Assertions.assertEquals(
			jsonObject.get("result"),
			"/reports/segments?after=0&fromDate=2022-03-01T12:00:00.000Z&" +
				"toDate=2022-03-31T12:00:00.000Z");
	}

	@Test
	public void testInstance() {
		MatcherAssert.assertThat(
			_reportHttp, Matchers.instanceOf(ReportHttpImpl.class));
	}

	private static final Http _httpMock = Mockito.mock(Http.class);

	@Autowired
	private ReportHttp _reportHttp;

}