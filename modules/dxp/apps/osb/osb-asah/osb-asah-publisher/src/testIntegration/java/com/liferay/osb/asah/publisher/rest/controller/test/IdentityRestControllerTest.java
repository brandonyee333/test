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
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.publisher.spring.OSBAsahPublisherSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisEnabledTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

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
public class IdentityRestControllerTest {

	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddIndividual() {
		String emailAddress = StringUtils.lowerCase(
			RandomTestUtil.randomEmailAddress());

		_exchange(
			JSONUtil.put(
				"channelId", "1"
			).put(
				"dataSourceId", "345085929068798696"
			).put(
				"identity",
				JSONUtil.put(
					"email", emailAddress
				).put(
					"name", RandomTestUtil.randomFullName()
				)
			).put(
				"userId", RandomTestUtil.randomUUID()
			).toString());

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONObject messageJSONObject = new JSONObject(
			argumentCaptor.getValue());

		Assert.assertEquals(
			"345085929068798696", messageJSONObject.getString("dataSourceId"));
		Assert.assertEquals(
			DigestUtils.sha256Hex(emailAddress),
			messageJSONObject.getString("emailAddressHashed"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testEmailAddressHashed() {
		String emailAddressHashed =
			"47ff64395860b1d498241d907069f649b98c198a95b3ba5303b87094058590c1";

		_exchange(
			JSONUtil.put(
				"channelId", "1"
			).put(
				"dataSourceId", "345085929068798696"
			).put(
				"emailAddressHashed", emailAddressHashed
			).put(
				"userId", RandomTestUtil.randomUUID()
			).toString());

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONObject messageJSONObject = new JSONObject(
			argumentCaptor.getValue());

		Assert.assertEquals(
			"345085929068798696", messageJSONObject.getString("dataSourceId"));
		Assert.assertEquals(
			emailAddressHashed,
			messageJSONObject.getString("emailAddressHashed"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpdateIndividual1() {
		_testUpdateIndividual(
			"345085929068798696", RandomTestUtil.randomUUID());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpdateIndividual2() {
		_testUpdateIndividual(
			"345085929068798697", RandomTestUtil.randomUUID());
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
			"/identity", HttpMethod.POST, requestEntity, String.class);
	}

	private void _testUpdateIndividual(String dataSourceId, String userId) {
		_exchange(
			JSONUtil.put(
				"channelId", "1"
			).put(
				"dataSourceId", dataSourceId
			).put(
				"identity",
				JSONUtil.put(
					"email", "nina.simone@liferay.com"
				).put(
					"name", "Nina Simone"
				)
			).put(
				"userId", userId
			).toString());

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONObject messageJSONObject = new JSONObject(
			argumentCaptor.getValue());

		Assert.assertEquals(
			dataSourceId, messageJSONObject.getString("dataSourceId"));
		Assert.assertEquals(
			DigestUtils.sha256Hex("nina.simone@liferay.com"),
			messageJSONObject.getString("emailAddressHashed"));
	}

	@MockBean
	private MessageBus _messageBus;

	@Autowired
	private TestRestTemplate _testRestTemplate;

}