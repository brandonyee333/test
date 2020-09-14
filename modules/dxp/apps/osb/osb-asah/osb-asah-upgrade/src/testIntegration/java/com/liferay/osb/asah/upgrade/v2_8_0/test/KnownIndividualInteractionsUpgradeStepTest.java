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

package com.liferay.osb.asah.upgrade.v2_8_0.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_8_0.KnownIndividualInteractionsUpgradeStep;

import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class KnownIndividualInteractionsUpgradeStepTest {

	@Before
	public void setUp() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testUpgrade() {
		JSONArray blogsJSONArray = new JSONArray();
		JSONArray individualsJSONArray = new JSONArray();
		JSONArray pagesJSONArray = new JSONArray();

		for (int i = 0; i < 2500; i++) {
			String individualId = RandomTestUtil.randomUUID();

			individualsJSONArray.put(
				JSONUtil.put(
					"demographics",
					JSONUtil.put(
						"email",
						JSONUtil.put(
							"value",
							RandomTestUtil.randomUUID() + "@liferay.com"))
				).put(
					"id", individualId
				));

			for (int j = 0; j < 5; j++) {
				blogsJSONArray.put(
					JSONUtil.put(
						"channelIds", JSONUtil.put("1")
					).put(
						"individualId", individualId
					).put(
						"knownIndividual", RandomUtils.nextBoolean()
					));
				pagesJSONArray.put(
					JSONUtil.put(
						"channelIds", JSONUtil.put("1")
					).put(
						"individualId", individualId
					).put(
						"knownIndividual", RandomUtils.nextBoolean()
					));
			}
		}

		for (int i = 0; i < 2500; i++) {
			String individualId = RandomTestUtil.randomUUID();

			individualsJSONArray.put(JSONUtil.put("id", individualId));

			for (int j = 0; j < 5; j++) {
				blogsJSONArray.put(
					JSONUtil.put(
						"channelIds", JSONUtil.put("2")
					).put(
						"individualId", individualId
					).put(
						"knownIndividual", RandomUtils.nextBoolean()
					));
				pagesJSONArray.put(
					JSONUtil.put(
						"channelIds", JSONUtil.put("2")
					).put(
						"individualId", individualId
					).put(
						"knownIndividual", RandomUtils.nextBoolean()
					));
			}
		}

		_cerebroInfoElasticsearchInvoker.add("blogs", blogsJSONArray);
		_cerebroInfoElasticsearchInvoker.add("pages", pagesJSONArray);
		_faroInfoElasticsearchInvoker.add("individuals", individualsJSONArray);

		_knownKnownIndividualInteractionsUpgradeStep.upgrade("");

		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"blogs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("channelIds", "1")
				).filter(
					QueryBuilders.termQuery("knownIndividual", false)
				)));

		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"blogs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("channelIds", "2")
				).filter(
					QueryBuilders.termQuery("knownIndividual", true)
				)));
	}

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private KnownIndividualInteractionsUpgradeStep
		_knownKnownIndividualInteractionsUpgradeStep;

}