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
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

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
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class AssignCanonicalUrlArmTest {

	@Before
	public void setUp() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testAllIndices() {
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

		_assignCanonicalUrlArm.execute();

		Assert.assertEquals(
			activitiesJSONArray.length(),
			_faroInfoElasticsearchInvoker.count(
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
			_faroInfoElasticsearchInvoker.count(
				"visited-pages", QueryBuilders.existsQuery("canonicalUrl")));
	}

	@Test
	public void testAssetMissingTempUrls() {
		String canonicalUrl =
			"http://foo.com/canonical/" + RandomTestUtil.randomUUID();
		String url = "http://foo.com/?foo=" + RandomTestUtil.randomUUID();

		_cerebroRawElasticsearchInvoker.add(
			"analytics-events",
			JSONUtil.put(
				"context",
				JSONUtil.put(
					"canonicalUrl", canonicalUrl
				).put(
					"url", url
				)));
		_cerebroInfoElasticsearchInvoker.add(
			"blogs", JSONUtil.put("urls", JSONUtil.putAll(url)));
		_cerebroInfoElasticsearchInvoker.add("pages", JSONUtil.put("url", url));

		_assignCanonicalUrlArm.execute();

		JSONArray blogsJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"blogs");

		Assert.assertEquals(1, blogsJSONArray.length());

		JSONObject blogJSONObject = blogsJSONArray.getJSONObject(0);

		JSONArray canonicalUrlsJSONArray = blogJSONObject.getJSONArray(
			"canonicalUrls");

		Assert.assertEquals(1, canonicalUrlsJSONArray.length());
		Assert.assertEquals(canonicalUrl, canonicalUrlsJSONArray.getString(0));
	}

	@Test
	public void testAssetSameCanonicalUrl() {
		JSONArray analyticsEventsJSONArray = new JSONArray();
		JSONArray urlsJSONArray = new JSONArray();

		String canonicalUrl =
			"http://foo.com/canonical/" + RandomTestUtil.randomUUID();

		for (int i = 0; i < 1200; i++) {
			String url = "http://foo.com/?foo=" + RandomTestUtil.randomUUID();

			analyticsEventsJSONArray.put(
				JSONUtil.put(
					"context",
					JSONUtil.put(
						"canonicalUrl", canonicalUrl
					).put(
						"url", url
					)));

			urlsJSONArray.put(url);
		}

		_cerebroRawElasticsearchInvoker.add(
			"analytics-events", analyticsEventsJSONArray);
		_cerebroInfoElasticsearchInvoker.add(
			"blogs",
			JSONUtil.put(
				"tempUrls", urlsJSONArray
			).put(
				"urls", urlsJSONArray
			));

		_assignCanonicalUrlArm.execute();

		JSONArray blogsJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"blogs");

		Assert.assertEquals(1, blogsJSONArray.length());

		JSONObject blogJSONObject = blogsJSONArray.getJSONObject(0);

		JSONArray canonicalUrlsJSONArray = blogJSONObject.getJSONArray(
			"canonicalUrls");

		Assert.assertEquals(1, canonicalUrlsJSONArray.length());
		Assert.assertEquals(canonicalUrl, canonicalUrlsJSONArray.getString(0));
	}

	@Autowired
	private AssignCanonicalUrlArm _assignCanonicalUrlArm;

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}