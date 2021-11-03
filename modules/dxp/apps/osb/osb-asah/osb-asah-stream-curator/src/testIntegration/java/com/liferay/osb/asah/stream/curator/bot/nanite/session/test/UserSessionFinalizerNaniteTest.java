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
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.UserSessionFinalizerNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
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
@SpringBootTest(
	classes = OSBAsahCuratorSpringBootApplication.class,
	properties = "osb.asah.user.session.events.storage.path:/tmp/user_session_events.snappy.parquet"
)
public class UserSessionFinalizerNaniteTest extends BaseNaniteTestCase {

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionMultipleInteractions() {
		runNanite();

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assert.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(226090544, sessionJSONObject.getLong("duration"));
		Assert.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));

		JSONObject pageJSONObject = _elasticsearchInvoker.fetch(
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
		name = "user-sessions", resourcePath = "user_session_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionMultiplePageVisits() {
		runNanite();

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assert.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(270481, sessionJSONObject.getLong("duration"));

		JSONObject entryPageJSONObject = _elasticsearchInvoker.fetch(
			"pages", "372250348521977621");

		Assert.assertEquals(1, entryPageJSONObject.getInt("entrances"));

		JSONObject exitPageJSONObject = _elasticsearchInvoker.fetch(
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
		name = "user-sessions", resourcePath = "user_session_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionSingleInteraction() {
		runNanite();

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.termQuery("completed", true));

		Assert.assertTrue(sessionJSONObject.getBoolean("bounced"));
		Assert.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assert.assertEquals(0, sessionJSONObject.getLong("duration"));
		Assert.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));
		Assert.assertEquals(
			sessionJSONObject.getString("entryPage"),
			sessionJSONObject.getString("exitPage"));

		JSONArray pageJSONArray = _elasticsearchInvoker.get("pages");

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
		name = "user-sessions", resourcePath = "user_session_info_old_9.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExpiredSessionUpdatesAssets() {
		runNanite();

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = new JSONArray(
			_elasticsearchInvoker.get(
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
		name = "osbasahmarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_9.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_12.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testFinalizeCompletedSession() {
		ProjectIdThreadLocal.setProjectId("test");

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215919");

		String modifiedDate1 = userSessionJSONObject.getString("modifiedDate");

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215920");

		String modifiedDate2 = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215919");

		Assert.assertNotNull(userSessionJSONObject);
		Assert.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(43994000, userSessionJSONObject.getInt("duration"));
		Assert.assertEquals(
			"http://192.168.108.90:8089/page1",
			userSessionJSONObject.getString("exitPage"));
		Assert.assertNotEquals(
			modifiedDate1, userSessionJSONObject.getString("modifiedDate"));

		JSONArray pagesJSONArray = new JSONArray(
			_elasticsearchInvoker.get(
				"pages",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						QueryBuilders.termQuery(
							"sessionId", "366909399944215919"));
					searchSourceBuilder.sort("lastEventDate", SortOrder.ASC);
				}));

		Assert.assertEquals(2, pagesJSONArray.length());

		JSONObject entryPageJSONObject = pagesJSONArray.getJSONObject(0);

		Assert.assertEquals(0, entryPageJSONObject.getInt("bounce"));
		Assert.assertEquals(1, entryPageJSONObject.getInt("entrances"));
		Assert.assertEquals(0, entryPageJSONObject.getInt("exits"));

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(1);

		Assert.assertEquals(0, exitPageJSONObject.getInt("bounce"));
		Assert.assertEquals(0, exitPageJSONObject.getInt("entrances"));
		Assert.assertEquals(1, exitPageJSONObject.getInt("exits"));
		Assert.assertEquals(
			entryPageJSONObject.getString("sessionId"),
			exitPageJSONObject.getString("sessionId"));

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215920");

		Assert.assertNotNull(userSessionJSONObject);
		Assert.assertEquals(0, userSessionJSONObject.getInt("duration"));
		Assert.assertEquals(
			modifiedDate2, userSessionJSONObject.getString("modifiedDate"));

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215921");

		Assert.assertNotNull(userSessionJSONObject);
		Assert.assertFalse(userSessionJSONObject.getBoolean("completed"));
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
		name = "user-sessions", resourcePath = "user_session_info_old_10.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdatePageViews() {
		runNanite();

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
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
		name = "user-sessions", resourcePath = "user_session_info_old_11.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUpdateTimeOnPageSinglePage() {
		runNanite();

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
			"pages",
			QueryBuilders.termQuery(
				"sessionId", userSessionJSONObject.getString("id")));

		Assert.assertEquals(1, pagesJSONArray.length());

		JSONObject pageJSONObject = pagesJSONArray.getJSONObject(0);

		Assert.assertEquals(120000, pageJSONObject.getLong("timeOnPage"));
	}

	@Override
	protected Nanite getNanite() {
		return _userSessionFinalizerNanite;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private UserSessionFinalizerNanite _userSessionFinalizerNanite;

}