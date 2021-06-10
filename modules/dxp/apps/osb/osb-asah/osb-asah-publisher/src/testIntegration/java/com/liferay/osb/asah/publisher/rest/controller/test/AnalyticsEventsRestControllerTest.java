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
import com.liferay.osb.asah.publisher.spring.OSBAsahPublisherSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisEnabledTestConfiguration;

import org.assertj.core.api.Assertions;

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
import org.springframework.http.HttpStatus;
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
public class AnalyticsEventsRestControllerTest {

	@Before
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void testGetStatusCode400() throws Exception {
		ResponseEntity<String> responseEntity = _exchange(
			ResourceUtil.readResourceToString(
				"dependencies/analytics_events_message_invalid_syntax.json",
				this));

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(400)
		);
	}

	@Test
	public void testGetStatusCode500() throws Exception {
		Mockito.doThrow(
			RuntimeException.class
		).when(
			_messageBus
		).sendMessage(
			ArgumentMatchers.any(), ArgumentMatchers.anyString()
		);

		ResponseEntity<String> responseEntity = _exchange(
			ResourceUtil.readResourceToString(
				"dependencies/analytics_events_message.json", this));

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(500)
		);
	}

	@Test
	public void testPushAnalyticsEventsMessage() throws Exception {
		String body = ResourceUtil.readResourceToString(
			"dependencies/analytics_events_message.json", this);

		ResponseEntity<String> responseEntity = _exchange(body);

		Assertions.assertThat(
			responseEntity.getBody()
		).isEqualTo(
			"[]"
		);

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONAssert.assertEquals(body, argumentCaptor.getValue(), false);
	}

	@Test
	public void testPushAnalyticsEventsMessageIfDuplicate() throws Exception {
		String body = ResourceUtil.readResourceToString(
			"dependencies/analytics_events_message_with_id.json", this);

		_exchange(body);

		for (int i = 0; i <= 2; i++) {
			ResponseEntity<String> responseEntity = _exchange(body);

			Assertions.assertThat(
				responseEntity.getBody()
			).startsWith(
				"Duplicate Message"
			);
		}
	}

	@Test
	public void testPushAnalyticsEventsMessageIfEmptyEvents() throws Exception {
		ResponseEntity<String> responseEntity = _exchange(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/analytics_events_message_empty_events.json",
					this)));

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(200)
		);

		Mockito.verify(
			_messageBus, Mockito.never()
		).sendMessage(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@Test
	public void testPushAnalyticsEventsMessageIfInvalidDataSourceId()
		throws Exception {

		ResponseEntity<String> responseEntity = _exchange(
			ResourceUtil.readResourceToString(
				"dependencies" +
					"/analytics_events_message_invalid_data_source_id.json",
				this));

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(400)
		);
	}

	@Test
	public void testPushAnalyticsEventsMessageIfInvalidEvents()
		throws Exception {

		ResponseEntity<String> responseEntity = _exchange(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/analytics_events_message_invalid_events.json",
					this)));

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(200)
		);

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies" +
					"/analytics_events_message_invalid_events_removed.json",
				this),
			argumentCaptor.getValue(), false);
	}

	@Test
	public void testPushAnalyticsEventsMessageIfInvalidXForwardedFor()
		throws Exception {

		String body = ResourceUtil.readResourceToString(
			"dependencies/analytics_events_message.json", this);

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.PROJECT_ID, "test");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		ResponseEntity<String> responseEntity = _exchange(body, httpHeaders);

		Assertions.assertThat(
			responseEntity.getBody()
		).isEqualTo(
			"[]"
		);

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_messageBus, Mockito.times(1)
		).sendMessage(
			ArgumentMatchers.any(), argumentCaptor.capture()
		);

		JSONAssert.assertEquals(body, argumentCaptor.getValue(), false);
	}

	private <T> ResponseEntity<String> _exchange(T body) {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.PROJECT_ID, "test");
		httpHeaders.add(HttpHeaders.COOKIE, "ANONYMOUS_USER_ID=111111");
		httpHeaders.add(HttpHeaders.USER_AGENT, "Google Chrome");
		httpHeaders.add("X-Forwarded-For", "localhost");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		return _exchange(body, httpHeaders);
	}

	private <T> ResponseEntity<String> _exchange(
		T body, HttpHeaders httpHeaders) {

		HttpEntity<T> requestEntity = new HttpEntity<>(body, httpHeaders);

		return _testRestTemplate.exchange(
			"/", HttpMethod.POST, requestEntity, String.class);
	}

	@MockBean
	private MessageBus _messageBus;

	@Autowired
	private TestRestTemplate _testRestTemplate;

}