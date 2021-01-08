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

package com.liferay.osb.asah.backend.rest.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.liferay.osb.asah.backend.graphql.GraphQLRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@TestPropertySource(properties = "osb.asah.security.enabled=false")
@WebMvcTest(secure = false, value = GraphQLRestController.class)
public abstract class BaseGraphQLRestControllerTestCase {

	public abstract String getBodyPath();

	public abstract String getExpectedResultPath();

	public abstract String getQueryPath();

	@Before
	public void setUp() {
		elasticsearchInvoker = _elasticsearchInvokerFactory.forCerebroInfo();
	}

	@Test
	public void testQuery() throws Exception {
		String body = ResourceUtil.readResourceToString(
			"dependencies/" + getBodyPath(), this);

		JSONObject bodyJSONObject = new JSONObject(body);

		String query = ResourceUtil.readResourceToString(
			"dependencies/" + getQueryPath(), this);

		bodyJSONObject.put("query", query);

		JSONObject responseJSONObject = _request(bodyJSONObject);

		Assert.assertFalse(responseJSONObject.has("errors"));

		String expectedResultPath = getExpectedResultPath();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/" + expectedResultPath, this),
			responseJSONObject, false);
	}

	protected ElasticsearchInvoker elasticsearchInvoker;

	private void _expectContentTypeJSON(ResultActions resultActions)
		throws Exception {

		ContentResultMatchers contentResultMatchers = content();

		resultActions.andExpect(
			contentResultMatchers.contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	private void _expectStatusOK(ResultActions resultActions) throws Exception {
		StatusResultMatchers statusResultMatchers = status();

		resultActions.andExpect(statusResultMatchers.isOk());
	}

	private JSONObject _request(JSONObject jsonObject) throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post(
			"/graphql");

		mockHttpServletRequestBuilder.content(jsonObject.toString());
		mockHttpServletRequestBuilder.contentType(
			MediaType.APPLICATION_JSON_UTF8);

		ResultActions resultActions = _mockMvc.perform(
			mockHttpServletRequestBuilder);

		_expectContentTypeJSON(resultActions);
		_expectStatusOK(resultActions);

		MvcResult mvcResult = resultActions.andReturn();

		MockHttpServletResponse mockHttpServletResponse =
			mvcResult.getResponse();

		return new JSONObject(mockHttpServletResponse.getContentAsString());
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private MockMvc _mockMvc;

	@MockBean
	private SpringBootMetricsCollector _springBootMetricsCollector;

}