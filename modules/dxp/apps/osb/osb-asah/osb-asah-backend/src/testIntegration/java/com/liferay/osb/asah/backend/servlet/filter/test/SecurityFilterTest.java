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

package com.liferay.osb.asah.backend.servlet.filter.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@TestPropertySource(properties = "osb.asah.security.enabled=true")
@WebMvcTest(secure = false)
public class SecurityFilterTest {

	@Test
	public void testGet() throws Exception {
		_testGet(
			"/data-sources",
			DigestUtils.sha256Hex(
				_osbAsahFaroBackendSecurityToken.concat("http://localhost")));
	}

	@Test
	public void testGetAPI() throws Exception {
		String uuid = String.valueOf(UUID.randomUUID());

		String securitySignature = DigestUtils.sha256Hex(
			uuid.concat("http://localhost"));

		_elasticsearchInvoker.add(
			"data-sources",
			JSONUtil.put("faroBackendSecuritySignature", securitySignature));

		_testGet("/api/1.0/individual-segments", securitySignature);
	}

	private void _expectStatusForbidden(ResultActions resultActions)
		throws Exception {

		StatusResultMatchers statusResultMatchers = status();

		resultActions.andExpect(statusResultMatchers.isForbidden());
	}

	private void _expectStatusOK(ResultActions resultActions) throws Exception {
		StatusResultMatchers statusResultMatchers = status();

		resultActions.andExpect(statusResultMatchers.isOk());
	}

	private void _testGet(String url, String securitySignature)
		throws Exception {

		MockHttpServletRequestBuilder mockHttpServletRequestBuilder1 = get(url);

		mockHttpServletRequestBuilder1.header(
			"OSB-Asah-Faro-Backend-Security-Signature", securitySignature);

		_expectStatusOK(_mockMvc.perform(mockHttpServletRequestBuilder1));

		MockHttpServletRequestBuilder mockHttpServletRequestBuilder2 = get(url);

		mockHttpServletRequestBuilder2.header(
			"OSB-Asah-Faro-Backend-Security-Signature",
			RandomTestUtil.randomUUID());

		_expectStatusForbidden(
			_mockMvc.perform(mockHttpServletRequestBuilder2));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private MockMvc _mockMvc;

	@Value("${osb.asah.security.token}")
	private String _osbAsahFaroBackendSecurityToken;

	@MockBean
	private SpringBootMetricsCollector _springBootMetricsCollector;

}