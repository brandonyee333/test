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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.SessionNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

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
public class SessionNaniteTest {

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_6.json"
	)
	@Test
	public void testAnalyticsEventsAreTimeDelimited() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(4, userSessionsJSONArray.length());
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_2.json"
	)
	@Test
	public void testCreateOpenSession() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assert.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));

		JSONArray interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(1, interactionsJSONArray.length());

		JSONObject interactionJSONObject = interactionsJSONArray.getJSONObject(
			0);

		Assert.assertEquals(
			"blogViewed", interactionJSONObject.getString("eventId"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_6.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_3.json"
	)
	@Test
	public void testCreatePastSession() {
		_sessionNanite.run();

		Assert.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.matchAllQuery()));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"userId", "0cbc8e60-99cd-11e9-9129-a75b6df1b957")
			).filter(
				QueryBuilders.termQuery("completed", true)
			));

		Assert.assertNotNull(
			"Past events did not create completed session",
			userSessionJSONObject);
		Assert.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(305000, userSessionJSONObject.getInt("duration"));
		Assert.assertEquals(
			"http://192.168.108.90:8089/about",
			userSessionJSONObject.getString("entryPage"));
		Assert.assertEquals(
			"http://192.168.108.90:8089/about",
			userSessionJSONObject.getString("exitPage"));

		JSONArray interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(3, interactionsJSONArray.length());

		JSONObject pageJSONObject = _elasticsearchInvoker.fetch(
			"pages", QueryBuilders.matchAllQuery());

		Assert.assertEquals(0, pageJSONObject.getInt("bounce"));
		Assert.assertEquals(1, pageJSONObject.getInt("entrances"));
		Assert.assertEquals(1, pageJSONObject.getInt("exits"));
		Assert.assertEquals(
			userSessionJSONObject.getString("id"),
			pageJSONObject.getString("sessionId"));
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_1.json"
	)
	@Test
	public void testExpiredSession() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(2, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assert.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));

		JSONArray interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(1, interactionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(1);

		Assert.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));

		interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(3, interactionsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_7.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_4.json"
	)
	@Test
	public void testPastEventUpdateCompletedSession() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assert.assertFalse(userSessionJSONObject.getBoolean("bounced"));

		Assert.assertEquals(
			"http://192.168.108.90:8089/page1",
			userSessionJSONObject.getString("exitPage"));

		JSONArray pagesJSONArray = new JSONArray(
			_elasticsearchInvoker.get(
				"pages",
				searchSourceBuilder -> searchSourceBuilder.sort(
					"lastEventDate", SortOrder.ASC)));

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
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_8.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_5.json"
	)
	@Test
	public void testPastEventUpdateOpenSession() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assert.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertFalse(userSessionJSONObject.getBoolean("completed"));
		Assert.assertNull(userSessionJSONObject.optString("exitPage", null));

		JSONArray interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(3, interactionsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "session_raw_2.json"
	)
	@Test
	public void testUpdateSession() {
		_sessionNanite.run();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assert.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assert.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));

		JSONArray interactionsJSONArray = userSessionJSONObject.getJSONArray(
			"interactions");

		Assert.assertEquals(2, interactionsJSONArray.length());

		JSONObject interactionJSONObject = interactionsJSONArray.getJSONObject(
			1);

		Assert.assertEquals(
			"blogViewed", interactionJSONObject.getString("eventId"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private SessionNanite _sessionNanite;

}