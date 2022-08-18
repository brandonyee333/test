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
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.rest.response.ItemGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Vishal Reddy
 */
public class ItemGetResponseTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/item_get_response_individuals.json"
	)
	@Test
	public void testRespondWithDefaultProperties() throws Exception {
		ItemGetResponse itemGetResponse = new ItemGetResponse();

		itemGetResponse.setCollectionName("individuals");
		itemGetResponse.setElasticsearchInvoker(_elasticsearchInvoker);
		itemGetResponse.setId("123");

		JSONObject responseJSONObject = new JSONObject(
			itemGetResponse.respond());

		Assertions.assertFalse(responseJSONObject.isNull("demographics"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}