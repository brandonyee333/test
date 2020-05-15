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

package com.liferay.osb.asah.common.dxp.extractor.dog.test;

import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.dxp.extractor.spring.OSBAsahDXPExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Vishal Reddy
 */
@ActiveProfiles({"DXPExtractorConfigurationDogTest", "test"})
@Ignore
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = OSBAsahDXPExtractorSpringBootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class DXPExtractorConfigurationDogTest {

	@Before
	public void setUp() {
		_dataSourceJSONObject = JSONUtil.put(
			"credentials",
			JSONUtil.put(
				"login", "abc"
			).put(
				"password", "123"
			).put(
				"type", "Basic Authentication"
			)
		).put(
			"id", "234"
		).put(
			"provider", JSONUtil.put("analyticsConfiguration", new JSONObject())
		).put(
			"state", "CREDENTIALS_VALID"
		).put(
			"url", "http://localhost:8080"
		);
	}

	@Test
	public void testGetState() {
		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_dxpExtractorConfigurationDog.getState(_dataSourceJSONObject));
	}

	@Test
	public void testSetUpDataSource() {
		_dxpExtractorConfigurationDog.setUpDataSource(_dataSourceJSONObject);

		Assert.assertNotEquals(
			"ANALYTICS_CLIENT_CONFIGURATION_FAILURE",
			_dataSourceJSONObject.getString("state"));
	}

	@Test
	public void testSetUpDataSourceDXPForbiddenStatus() {
		Mockito.doAnswer(
			invocationOnMock -> new ResponseEntity<>("", HttpStatus.FORBIDDEN)
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.any(String.class)
		);

		_dxpExtractorConfigurationDog.setUpDataSource(_dataSourceJSONObject);

		Assert.assertEquals(
			"ANALYTICS_CLIENT_CONFIGURATION_FAILURE",
			_dataSourceJSONObject.getString("state"));
	}

	private JSONObject _dataSourceJSONObject;

	@InjectMocks
	private final DXPExtractorConfigurationDog _dxpExtractorConfigurationDog =
		new DXPExtractorConfigurationDog();

	@Autowired
	@Spy
	private Http _http;

}