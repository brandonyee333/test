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

import com.liferay.osb.asah.batch.curator.bot.nanite.UserSessionFinalizerNanite;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class UserSessionFinalizerNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testExpiredSessionMultipleInteractions() throws Exception {
		_userSessionFinalizerNanite.run(null);

		_elasticsearchInvoker.refresh();

		Assertions.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assertions.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assertions.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assertions.assertEquals(
			226090544, sessionJSONObject.getLong("duration"));
		Assertions.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));

		JSONObject pageJSONObject = _elasticsearchInvoker.fetch(
			"pages", "372189498023251289");

		Assertions.assertEquals(
			sessionJSONObject.getLong("id"),
			pageJSONObject.getLong("sessionId"));

		Assertions.assertEquals(0, pageJSONObject.getLong("bounce"));
		Assertions.assertEquals(1, pageJSONObject.getLong("entrances"));
		Assertions.assertEquals(1, pageJSONObject.getLong("exits"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testExpiredSessionMultiplePageVisits() throws Exception {
		_userSessionFinalizerNanite.run(null);

		Assertions.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		Assertions.assertFalse(sessionJSONObject.getBoolean("bounced"));
		Assertions.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assertions.assertEquals(270481, sessionJSONObject.getLong("duration"));

		JSONObject entryPageJSONObject = _elasticsearchInvoker.fetch(
			"pages", "372250348521977621");

		Assertions.assertEquals(1, entryPageJSONObject.getInt("entrances"));

		JSONObject exitPageJSONObject = _elasticsearchInvoker.fetch(
			"pages", "372250474665065927");

		Assertions.assertEquals(1, exitPageJSONObject.getInt("exits"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testExpiredSessionSingleInteraction() throws Exception {
		_userSessionFinalizerNanite.run(null);

		_elasticsearchInvoker.refresh();

		Assertions.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject sessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.termQuery("completed", true));

		Assertions.assertTrue(sessionJSONObject.getBoolean("bounced"));
		Assertions.assertTrue(sessionJSONObject.getBoolean("completed"));
		Assertions.assertEquals(0, sessionJSONObject.getLong("duration"));
		Assertions.assertEquals(
			"expired", sessionJSONObject.getString("completeReason"));
		Assertions.assertEquals(
			sessionJSONObject.getString("entryPage"),
			sessionJSONObject.getString("exitPage"));

		JSONArray pageJSONArray = _elasticsearchInvoker.get("pages");

		Assertions.assertEquals(1, pageJSONArray.length());

		JSONObject pageJSONObject = pageJSONArray.getJSONObject(0);

		Assertions.assertEquals(1, pageJSONObject.getLong("bounce"));
		Assertions.assertEquals(1, pageJSONObject.getLong("entrances"));
		Assertions.assertEquals(1, pageJSONObject.getLong("exits"));
		Assertions.assertEquals(
			sessionJSONObject.getLong("id"),
			pageJSONObject.getLong("sessionId"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testExpiredSessionUpdatesAssets() throws Exception {
		_userSessionFinalizerNanite.run(null);

		_elasticsearchInvoker.refresh();

		Assertions.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
			"pages",
			Arrays.asList(
				SortBuilderUtil.fieldSort("firstEventDate", SortOrder.ASC)),
			QueryBuilders.termQuery(
				"sessionId", userSessionJSONObject.getString("id")));

		Assertions.assertEquals(3, pagesJSONArray.length());

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

			Assertions.assertEquals(
				timeOnPage, pageJSONObject.getLong("timeOnPage"));
		}

		JSONObject entryPageJSONObject = pagesJSONArray.getJSONObject(0);

		Assertions.assertEquals(1, entryPageJSONObject.getInt("entrances"));

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(1);

		Assertions.assertEquals(1, exitPageJSONObject.getInt("exits"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_7.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_7.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testFinalizeCompletedSession() throws Exception {
		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215919");

		String modifiedDate1 = userSessionJSONObject.getString("modifiedDate");

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215920");

		String modifiedDate2 = userSessionJSONObject.getString("modifiedDate");

		_userSessionFinalizerNanite.run(null);

		_elasticsearchInvoker.refresh();

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215919");

		Assertions.assertNotNull(userSessionJSONObject);
		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			43994000, userSessionJSONObject.getInt("duration"));
		Assertions.assertEquals(
			"http://192.168.108.90:8089/page1",
			userSessionJSONObject.getString("exitPage"));
		Assertions.assertNotEquals(
			modifiedDate1, userSessionJSONObject.getString("modifiedDate"));

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
			"pages",
			Arrays.asList(
				SortBuilderUtil.fieldSort("lastEventDate", SortOrder.ASC)),
			QueryBuilders.termQuery("sessionId", "366909399944215919"));

		Assertions.assertEquals(2, pagesJSONArray.length());

		JSONObject entryPageJSONObject = pagesJSONArray.getJSONObject(0);

		Assertions.assertEquals(0, entryPageJSONObject.getInt("bounce"));
		Assertions.assertEquals(1, entryPageJSONObject.getInt("entrances"));
		Assertions.assertEquals(0, entryPageJSONObject.getInt("exits"));

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(1);

		Assertions.assertEquals(0, exitPageJSONObject.getInt("bounce"));
		Assertions.assertEquals(0, exitPageJSONObject.getInt("entrances"));
		Assertions.assertEquals(1, exitPageJSONObject.getInt("exits"));
		Assertions.assertEquals(
			entryPageJSONObject.getString("sessionId"),
			exitPageJSONObject.getString("sessionId"));

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215920");

		Assertions.assertNotNull(userSessionJSONObject);
		Assertions.assertEquals(0, userSessionJSONObject.getInt("duration"));
		Assertions.assertEquals(
			modifiedDate2, userSessionJSONObject.getString("modifiedDate"));

		userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", "366909399944215921");

		Assertions.assertNotNull(userSessionJSONObject);
		Assertions.assertFalse(userSessionJSONObject.getBoolean("completed"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testUpdatePageViews() throws Exception {
		_userSessionFinalizerNanite.run(null);

		Assertions.assertEquals(
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

			Assertions.assertTrue(pageJSONObject.getInt("views") > 0);
		}
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_6.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_6.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahcerebroinfo/osbasahmarkers.json"
	)
	@Test
	public void testUpdateTimeOnPageSinglePage() throws Exception {
		_userSessionFinalizerNanite.run(null);

		_elasticsearchInvoker.refresh();

		Assertions.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions", QueryBuilders.matchAllQuery());

		JSONArray pagesJSONArray = _elasticsearchInvoker.get(
			"pages",
			QueryBuilders.termQuery(
				"sessionId", userSessionJSONObject.getString("id")));

		Assertions.assertEquals(1, pagesJSONArray.length());

		JSONObject pageJSONObject = pagesJSONArray.getJSONObject(0);

		Assertions.assertEquals(120000, pageJSONObject.getLong("timeOnPage"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private UserSessionFinalizerNanite _userSessionFinalizerNanite;

}