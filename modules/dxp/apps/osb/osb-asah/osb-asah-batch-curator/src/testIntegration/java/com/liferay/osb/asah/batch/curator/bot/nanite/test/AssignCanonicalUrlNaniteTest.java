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

import com.liferay.osb.asah.batch.curator.bot.nanite.AssignCanonicalUrlNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AssignCanonicalUrlNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
	}

	@Test
	public void test() throws Exception {
		JSONArray activitiesJSONArray = new JSONArray();
		JSONArray analyticsEventsJSONArray = new JSONArray();
		JSONArray blogsJSONArray = new JSONArray();
		JSONArray journalsJSONArray = new JSONArray();
		JSONArray pageReferrersJSONArray = new JSONArray();
		JSONArray pagesJSONArray = new JSONArray();
		JSONArray visitedPagesJSONArray = new JSONArray();

		for (int i = 0; i < 1200; i++) {
			String uuid = RandomTestUtil.randomUUID();

			String url = "http://foo.com/" + uuid;

			activitiesJSONArray.put(
				JSONUtil.put("object", JSONUtil.put("url", url)));
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
						"urls",
						JSONUtil.putAll(url, RandomTestUtil.randomURL())));
				journalsJSONArray.put(
					JSONUtil.put(
						"urls",
						JSONUtil.putAll(url, RandomTestUtil.randomURL())));
				pageReferrersJSONArray.put(JSONUtil.put("url", url));
			}

			pagesJSONArray.put(JSONUtil.put("url", url));
			visitedPagesJSONArray.put(JSONUtil.put("url", url));
		}

		faroInfoElasticsearchInvoker.add("activities", activitiesJSONArray);
		_cerebroRawElasticsearchInvoker.add(
			"analytics-events", analyticsEventsJSONArray);
		_cerebroInfoElasticsearchInvoker.add("blogs", blogsJSONArray);
		_cerebroInfoElasticsearchInvoker.add("journals", journalsJSONArray);
		_cerebroInfoElasticsearchInvoker.add(
			"page-referrers", pageReferrersJSONArray);
		_cerebroInfoElasticsearchInvoker.add("pages", pagesJSONArray);
		faroInfoElasticsearchInvoker.add(
			"visited-pages", visitedPagesJSONArray);

		_assignCanonicalUrlNanite.run(null);

		Assert.assertEquals(
			activitiesJSONArray.length(),
			faroInfoElasticsearchInvoker.count(
				"activities",
				QueryBuilders.existsQuery("object.canonicalUrl")));
		Assert.assertEquals(
			blogsJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"blogs", QueryBuilders.existsQuery("canonicalUrls")));
		Assert.assertEquals(
			journalsJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"journals", QueryBuilders.existsQuery("canonicalUrls")));
		Assert.assertEquals(
			pageReferrersJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"page-referrers", QueryBuilders.existsQuery("canonicalUrl")));
		Assert.assertEquals(
			pagesJSONArray.length(),
			_cerebroInfoElasticsearchInvoker.count(
				"pages", QueryBuilders.existsQuery("canonicalUrl")));
		Assert.assertEquals(
			visitedPagesJSONArray.length(),
			faroInfoElasticsearchInvoker.count(
				"visited-pages", QueryBuilders.existsQuery("canonicalUrl")));
	}

	@Autowired
	private AssignCanonicalUrlNanite _assignCanonicalUrlNanite;

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}