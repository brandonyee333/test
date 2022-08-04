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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.rest.response.PutResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Vishal Reddy
 */
public class PutResponseTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/put_response_individuals.json"
	)
	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		PutResponse putResponse = new PutResponse();

		putResponse.setCollectionName("individuals");
		putResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		putResponse.setId("123");
		putResponse.setJSON(
			JSONUtil.put(
				"floo", "blar"
			).toString());

		// TODO Our PUT responses actually behaves like a PATCH. We should fix
		// this one day.

		JSONObject responseJSONObject = new JSONObject(putResponse.respond());

		Assertions.assertEquals("blar", responseJSONObject.getString("floo"));
		Assertions.assertEquals("bar", responseJSONObject.getString("foo"));
		Assertions.assertNotNull(responseJSONObject.optString("id"));

		JSONObject actualJSONObject = _elasticsearchInvoker.get(
			"individuals", "123");

		Assertions.assertEquals("blar", actualJSONObject.getString("floo"));
		Assertions.assertEquals("bar", actualJSONObject.getString("foo"));
		Assertions.assertNotNull(actualJSONObject.optString("id"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}