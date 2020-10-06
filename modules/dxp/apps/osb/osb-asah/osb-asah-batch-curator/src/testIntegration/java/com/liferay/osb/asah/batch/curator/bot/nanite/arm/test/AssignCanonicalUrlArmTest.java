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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignCanonicalUrlArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignTempUrlsArm;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class AssignCanonicalUrlArmTest {

	@Test
	public void testAllIndices() {
		JSONArray activitiesJSONArray = new JSONArray();
		JSONArray analyticsEventsJSONArray = new JSONArray();
		JSONArray blogsJSONArray = new JSONArray();
		JSONArray journalsJSONArray = new JSONArray();
		JSONArray pageReferrersJSONArray = new JSONArray();
		JSONArray pagesJSONArray = new JSONArray();
		JSONArray visitedPagesJSONArray = new JSONArray();

		for (int i = 0; i < 200; i++) {
			String uuid = RandomTestUtil.randomUUID();

			String url = "http://foo.com/" + uuid;

			activitiesJSONArray.put(
				JSONUtil.put(
					"object",
					JSONUtil.put(
						"canonicalUrl", ""
					).put(
						"url", url
					)));
			analyticsEventsJSONArray.put(
				JSONUtil.put(
					"context",
					JSONUtil.put(
						"canonicalUrl", "http://foo.com/canonical/" + uuid
					).put(
						"url", url
					)));

			if ((RandomTestUtil.randomNumber() % 10) == 0) {
				blogsJSONArray.put(
					JSONUtil.put(
						"canonicalUrls", JSONUtil.putAll("")
					).put(
						"urls", JSONUtil.putAll(url, RandomTestUtil.randomURL())
					));
				journalsJSONArray.put(
					JSONUtil.put(
						"canonicalUrls", JSONUtil.putAll("")
					).put(
						"urls", JSONUtil.putAll(url, RandomTestUtil.randomURL())
					));
				pageReferrersJSONArray.put(
					JSONUtil.put(
						"canonicalUrl", ""
					).put(
						"url", url
					));
			}

			pagesJSONArray.put(
				JSONUtil.put(
					"canonicalUrl", ""
				).put(
					"url", url
				));
			visitedPagesJSONArray.put(
				JSONUtil.put(
					"canonicalUrl", ""
				).put(
					"url", url
				));
		}

		_faroInfoElasticsearchInvoker.add("activities", activitiesJSONArray);
		_cerebroRawElasticsearchInvoker.add(
			"analytics-events", analyticsEventsJSONArray);
		_cerebroInfoElasticsearchInvoker.add("blogs", blogsJSONArray);
		_cerebroInfoElasticsearchInvoker.add("journals", journalsJSONArray);
		_cerebroInfoElasticsearchInvoker.add(
			"page-referrers", pageReferrersJSONArray);
		_cerebroInfoElasticsearchInvoker.add("pages", pagesJSONArray);
		_faroInfoElasticsearchInvoker.add(
			"visited-pages", visitedPagesJSONArray);

		_assignTempUrlsArm.execute();

		_assignCanonicalUrlArm.execute();

		Assert.assertEquals(
			activitiesJSONArray.length(),
			_faroInfoElasticsearchInvoker.count(
				"activities",
				QueryBuilders.regexpQuery("object.canonicalUrl", ".+")));
		Assert.assertEquals(
			blogsJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"blogs",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("canonicalUrls", ""))));
		Assert.assertEquals(
			journalsJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"journals",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("canonicalUrls", ""))));
		Assert.assertEquals(
			pageReferrersJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"page-referrers",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("canonicalUrl", ""))));
		Assert.assertEquals(
			pagesJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"pages",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("canonicalUrl", ""))));
		Assert.assertEquals(
			visitedPagesJSONArray.length(),
			_faroInfoElasticsearchInvoker.count(
				"visited-pages",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("canonicalUrl", ""))));
	}

	@Autowired
	private AssignCanonicalUrlArm _assignCanonicalUrlArm;

	@Autowired
	private AssignTempUrlsArm _assignTempUrlsArm;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_RAW)
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}