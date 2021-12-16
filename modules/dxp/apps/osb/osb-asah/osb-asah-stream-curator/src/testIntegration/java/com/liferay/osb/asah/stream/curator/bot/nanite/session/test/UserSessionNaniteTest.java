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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.UserSessionNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahStreamCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(classes = OSBAsahStreamCuratorSpringBootApplication.class)
public class UserSessionNaniteTest extends BaseNaniteTestCase {

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_6.json"
	)
	@Test
	public void testAnalyticsEventsAreTimeDelimited() {
		runNanite();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assertions.assertEquals(4, userSessionsJSONArray.length());
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_2.json"
	)
	@Test
	public void testCreateOpenSession() {
		runNanite();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assertions.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_3.json"
	)
	@Test
	public void testCreatePastSession() {
		runNanite();

		Assertions.assertEquals(
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

		Assertions.assertNotNull(
			userSessionJSONObject,
			"Past events did not create completed session");
		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			"http://192.168.108.90:8089/about",
			userSessionJSONObject.getString("entryPage"));
		Assertions.assertNotNull(
			userSessionJSONObject.optString("modifiedDate"),
			"Modified date was not added to user session");
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_1.json"
	)
	@Test
	public void testExpiredSession() {
		runNanite();

		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions", Arrays.asList(SortBuilderUtil.fieldSort("id")));

		Assertions.assertEquals(2, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assertions.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(1);

		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_4.json"
	)
	@Test
	public void testPastEventUpdateCompletedSession() {
		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		userSessionsJSONArray = _elasticsearchInvoker.get("user-sessions");

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(0);

		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertNotEquals(
			modifiedDate, userSessionJSONObject.getString("modifiedDate"));
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_5.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_5.json"
	)
	@Test
	public void testPastEventUpdateOpenSession() {
		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		userSessionsJSONArray = _elasticsearchInvoker.get("user-sessions");

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(0);

		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertFalse(userSessionJSONObject.getBoolean("completed"));
		Assertions.assertNotEquals(
			modifiedDate, userSessionJSONObject.getString("modifiedDate"));
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_2.json"
	)
	@Test
	public void testUpdateSession() {
		JSONArray userSessionsJSONArray = _elasticsearchInvoker.get(
			"user-sessions");

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		userSessionsJSONArray = _elasticsearchInvoker.get("user-sessions");

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(0);

		Assertions.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertNotEquals(
			modifiedDate, userSessionJSONObject.getString("modifiedDate"));
		Assertions.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));
	}

	@Override
	protected Nanite getNanite() {
		return _userSessionNanite;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private UserSessionNanite _userSessionNanite;

}