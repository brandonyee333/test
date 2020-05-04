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

package com.liferay.osb.asah.dxp.extractor.configuration.test;

import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.dxp.extractor.configuration.impl.DXPExtractorConfigurationManagerImpl;
import com.liferay.osb.asah.dxp.extractor.spring.OSBAsahDXPExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahDXPExtractorSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DXPExtractorConfigurationManagerImplTest {

	@Test
	public void testGetState_Portal6_LiferayVersionInvalid() {
		Mockito.doAnswer(
			invocationOnMock -> new ResponseEntity<>("6.2", HttpStatus.OK)
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject(),
			Mockito.anyString(), Mockito.anyString()
		);

		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Basic Authentication", _DATA_SOURCE_NAME, _URL);

		Assert.assertEquals(
			"LIFERAY_VERSION_INVALID",
			_dxpExtractorConfigurationManagerImpl.getState(
				dataSourceJSONObject.toString()));
	}

	@Test
	public void testGetState_Portal7_CredentialsInvalid() {
		Mockito.doAnswer(
			invocationOnMock -> new ResponseEntity<String>(HttpStatus.FORBIDDEN)
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject(),
			Mockito.anyString(), Mockito.anyString()
		);

		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Basic Authentication", _DATA_SOURCE_NAME, _URL);

		Assert.assertEquals(
			"CREDENTIALS_INVALID",
			_dxpExtractorConfigurationManagerImpl.getState(
				dataSourceJSONObject.toString()));
	}

	@Test
	public void testGetState_Portal7_CredentialsValid() {
		Mockito.doAnswer(
			invocationOnMock -> new ResponseEntity<>("7.0", HttpStatus.OK)
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject(),
			Mockito.anyString(), Mockito.anyString()
		);

		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Basic Authentication", _DATA_SOURCE_NAME, _URL);

		Assert.assertEquals(
			"CREDENTIALS_VALID",
			_dxpExtractorConfigurationManagerImpl.getState(
				dataSourceJSONObject.toString()));
	}

	@Test
	public void testGetState_Portal7_UndefinedError() {
		Mockito.doAnswer(
			invocationOnMock -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
		).when(
			_http
		).exchangeResponseEntity(
			Mockito.anyString(), Mockito.anyString(),
			Mockito.any(HttpMethod.class), Mockito.anyObject(),
			Mockito.anyString(), Mockito.anyString()
		);

		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Basic Authentication", _DATA_SOURCE_NAME, _URL);

		Assert.assertEquals(
			"UNDEFINED_ERROR",
			_dxpExtractorConfigurationManagerImpl.getState(
				dataSourceJSONObject.toString()));
	}

	@TestConfiguration
	public static class DXPExtractorConfigurationManagerImplTestConfiguration {

		@Bean
		@Primary
		public Http http() {
			return Mockito.spy(Http.class);
		}

	}

	private static final String _DATA_SOURCE_NAME =
		RandomTestUtil.randomMultipleWordString(5, 20);

	private static final String _URL = RandomTestUtil.randomURL();

	@Autowired
	private DXPExtractorConfigurationManagerImpl
		_dxpExtractorConfigurationManagerImpl;

	@Autowired
	private Http _http;

}