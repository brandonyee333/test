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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.InterestTopicsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Victor Oliveira
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class InterestTopicsNaniteTest extends BaseNaniteTestCase {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void test() throws Exception {
		_interestTopicsNanite.run(null);

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray interestTopics = elasticsearchInvoker.get("interest-topics");

		Assert.assertTrue(interestTopics.length() > 0);

		JSONObject jsonObject = interestTopics.getJSONObject(0);

		Assert.assertTrue(StringUtils.isNotEmpty(jsonObject.getString("term")));
		Assert.assertTrue(
			StringUtils.isNotEmpty(jsonObject.getString("termType")));
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked-keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testBlockedKeywords() throws Exception {
		_interestTopicsNanite.run(null);

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray interestTopics = elasticsearchInvoker.get("interest-topics");

		Assert.assertFalse(_matchAny(interestTopics, "java"));
		Assert.assertFalse(_matchAny(interestTopics, "jquery"));
		Assert.assertTrue(_matchAny(interestTopics, "php"));
	}

	private boolean _matchAny(JSONArray jsonArray, String term) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if (Objects.equals(jsonObject.getString("term"), term)) {
				return true;
			}
		}

		return false;
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private InterestTopicsNanite _interestTopicsNanite;

}