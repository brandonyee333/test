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

package com.liferay.osb.asah.publisher.rest.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.publisher.spring.OSBAsahPublisherSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisEnabledTestConfiguration;

import org.assertj.core.api.Assertions;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author Inácio Nery
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		OSBAsahPublisherSpringBootApplication.class,
		OSBAsahRedisEnabledTestConfiguration.class
	},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class DXPEntitiesRestControllerTest {

	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddDXPEntities() throws Exception {
		ResponseEntity<String> responseEntity = _exchange(
			ResourceUtil.readResourceToString(
				"dependencies/analytics_message.json", this));

		Assertions.assertThat(
			responseEntity.getBody()
		).isEqualTo(
			"[]"
		);

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(6)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONArray jsonArray = new JSONArray();

		for (String message : argumentCaptor.getAllValues()) {
			JSONObject messageJSONObject = new JSONObject(message);

			jsonArray.put(messageJSONObject);
		}

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/dxp_entities_message.json", this),
			jsonArray, false);
	}

	private <T> ResponseEntity<String> _exchange(T body) {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.PROJECT_ID, "test");
		httpHeaders.add(HttpHeaders.COOKIE, "ANONYMOUS_USER_ID=111111");
		httpHeaders.add(HttpHeaders.USER_AGENT, "Google Chrome");
		httpHeaders.add("X-Forwarded-For", "localhost");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<T> requestEntity = new HttpEntity<>(body, httpHeaders);

		return _testRestTemplate.exchange(
			"/dxp-entities", HttpMethod.POST, requestEntity, String.class);
	}

	@MockBean
	private MessageBus _messageBus;

	@Autowired
	private TestRestTemplate _testRestTemplate;

}