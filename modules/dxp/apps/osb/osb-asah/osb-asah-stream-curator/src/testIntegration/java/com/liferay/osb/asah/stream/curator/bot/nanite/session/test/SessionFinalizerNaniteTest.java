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

package com.liferay.osb.asah.stream.curator.bot.nanite.session.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.SessionFinalizerNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class SessionFinalizerNaniteTest {

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionMultipleInteractions() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assert.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(226090544, sessionJSONObject.getLong("duration"));
		Assert.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));

		JSONObject pageJSONObject = elasticsearchInvoker.fetch(
			"pages", "372189498023251289");

		Assert.assertEquals(
			sessionJSONObject.getLong("id"),
			pageJSONObject.getLong("sessionId"));

		Assert.assertEquals(0, pageJSONObject.getLong("bounce"));
		Assert.assertEquals(1, pageJSONObject.getLong("entrances"));
		Assert.assertEquals(1, pageJSONObject.getLong("exits"));
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionMultiplePageVisits() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assert.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(270481, sessionJSONObject.getLong("duration"));

		JSONObject entryPageJSONObject = elasticsearchInvoker.fetch(
			"pages", "372250348521977621");

		Assert.assertEquals(1, entryPageJSONObject.getInt("entrances"));

		JSONObject exitPageJSONObject = elasticsearchInvoker.fetch(
			"pages", "372250474665065927");

		Assert.assertEquals(1, exitPageJSONObject.getInt("exits"));
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionSingleInteraction() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			2,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.termQuery("completed", true));

		Assert.assertTrue(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(0, sessionJSONObject.getLong("duration"));
		Assert.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));
		Assert.assertEquals(
			sessionJSONObject.getString("entryPage"),
			sessionJSONObject.getString("exitPage"));

		JSONArray pageJSONArray = elasticsearchInvoker.get("pages");

		Assert.assertEquals(1, pageJSONArray.length());

		JSONObject pageJSONObject = pageJSONArray.getJSONObject(0);

		Assert.assertEquals(1, pageJSONObject.getLong("bounce"));
		Assert.assertEquals(1, pageJSONObject.getLong("entrances"));
		Assert.assertEquals(1, pageJSONObject.getLong("exits"));
		Assert.assertEquals(
			sessionJSONObject.getLong("id"),
			pageJSONObject.getLong("sessionId"));
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_6.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_9.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionUpdatesAssets() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"pages",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						QueryBuilders.termQuery(
							"sessionId",
							userSessionJSONObject.getString("id")));
					searchSourceBuilder.sort("firstEventDate", SortOrder.ASC);
				}));

		Assert.assertEquals(3, pagesJSONArray.length());

		Map<String, Long> timeOnPages = new HashMap<String, Long>() {
			{
				put("Test Page 1", 300000L);
				put("Test Page 2", 120000L);
				put("Test Page 3", 600000L);
			}
		};

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			long timeOnPage = timeOnPages.get(
				pageJSONObject.getString("title"));

			Assert.assertEquals(
				timeOnPage, pageJSONObject.getLong("timeOnPage"));
		}

		JSONObject entryPageJSONObject = pagesJSONArray.getJSONObject(0);

		Assert.assertEquals(1, entryPageJSONObject.getInt("entrances"));

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(1);

		Assert.assertEquals(1, exitPageJSONObject.getInt("exits"));
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_7.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_10.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdatePageViews() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = elasticsearchInvoker.get(
			"pages",
			QueryBuilders.termQuery(
				"sessionId", userSessionJSONObject.getString("id")));

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			Assert.assertTrue(pageJSONObject.getInt("views") > 0);
		}
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_8.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_11.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdateTimeOnPageSinglePage() {
		_sessionFinalizerNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = elasticsearchInvoker.get(
			"pages",
			QueryBuilders.termQuery(
				"sessionId", userSessionJSONObject.getString("id")));

		Assert.assertEquals(1, pagesJSONArray.length());

		JSONObject pageJSONObject = pagesJSONArray.getJSONObject(0);

		Assert.assertEquals(120000, pageJSONObject.getLong("timeOnPage"));
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private SessionFinalizerNanite _sessionFinalizerNanite;

}