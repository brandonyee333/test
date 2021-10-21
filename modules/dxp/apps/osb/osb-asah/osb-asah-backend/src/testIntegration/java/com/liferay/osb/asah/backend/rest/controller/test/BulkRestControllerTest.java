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

import com.liferay.osb.asah.backend.rest.controller.BulkRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.Http;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class BulkRestControllerTest {

	@Test
	public void testPost() {
		Mockito.doAnswer(
			invocationOnMock -> "[\"1\"]"
		).when(
			_http
		).exchange(
			ArgumentMatchers.any(), ArgumentMatchers.contains("dummy"),
			ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any()
		);

		String responses = _bulkRestController.post(
			String.valueOf(
				JSONUtil.put(
					"operations",
					JSONUtil.putAll(
						JSONUtil.put(
							"method", "DELETE"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"method", "GET"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"body", JSONUtil.put("dummy", 5)
						).put(
							"method", "PATCH"
						).put(
							"url", "/dummy"
						),
						JSONUtil.put(
							"method", "POST"
						).put(
							"url", "/dummy?value=2"
						),
						JSONUtil.put(
							"method", "PUT"
						).put(
							"url", "/dummy"
						)))));

		Assert.assertEquals(
			"{\"responses\":[[\"1\"],[\"1\"],[\"1\"],[\"1\"],[\"1\"]]}",
			responses);
	}

	@Autowired
	private BulkRestController _bulkRestController;

	@MockBean
	private Http _http;

}