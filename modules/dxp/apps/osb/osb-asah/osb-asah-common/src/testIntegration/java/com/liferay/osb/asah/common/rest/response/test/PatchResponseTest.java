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

package com.liferay.osb.asah.common.rest.response.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.PatchResponse;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit5ClassRunner;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ExtendWith(OSBAsahSpringJUnit5ClassRunner.class)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class PatchResponseTest {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "patch_response_individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		PatchResponse patchResponse = new PatchResponse();

		patchResponse.setCollectionName("individuals");
		patchResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		patchResponse.setId("123");
		patchResponse.setJSON(
			JSONUtil.put(
				"floo", "blar"
			).put(
				"id", "123"
			).toString());

		// TODO Our PATCH responses actually behaves like a PUT. We should fix
		// this one day.

		JSONObject responseJSONObject = new JSONObject(patchResponse.respond());

		Assertions.assertEquals("blar", responseJSONObject.getString("floo"));
		Assertions.assertEquals("", responseJSONObject.optString("foo"));
		Assertions.assertNotNull(responseJSONObject.optString("id"));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			"individuals", "123");

		Assertions.assertEquals("blar", actualJSONObject.getString("floo"));
		Assertions.assertEquals("", actualJSONObject.optString("foo"));
		Assertions.assertNotNull(actualJSONObject.optString("id"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}