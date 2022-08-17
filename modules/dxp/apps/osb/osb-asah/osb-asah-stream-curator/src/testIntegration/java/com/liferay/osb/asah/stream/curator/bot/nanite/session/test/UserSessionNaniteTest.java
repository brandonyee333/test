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

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.UserSessionNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahStreamCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(classes = OSBAsahStreamCuratorSpringBootApplication.class)
public class UserSessionNaniteTest extends BaseNaniteTestCase {

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_6.json"
	)
	@Test
	public void testAnalyticsEventsAreTimeDelimited() {
		runNanite();

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(4, userSessionsJSONArray.length());
	}

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_2.json"
	)
	@Test
	public void testCreateOpenSession() {
		runNanite();

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		Assertions.assertTrue(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertEquals(
			"0cbc8e60-99cd-11e9-9129-a75b6df1b957",
			userSessionJSONObject.getString("userId"));
	}

	@Disabled
	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info_old_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_3.json"
	)
	@Test
	public void testCreatePastSession() {
		runNanite();

		// TODO count user sessions

		Assertions.assertEquals(2, 0);

		// TODO get user session

		JSONObject userSessionJSONObject = new JSONObject();

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

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_1.json"
	)
	@Test
	public void testExpiredSession() {
		runNanite();

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

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

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_7.json"
	)
	@Test
	public void testMergeUserSessions1() {
		runNanite();

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		JSONAssert.assertEquals(
			JSONUtil.put("http://192.168.108.90:8089/about"),
			userSessionJSONObject.getJSONArray("canonicalUrls"), false);
		Assertions.assertEquals(
			"2022-06-29T09:24:11.000Z",
			userSessionJSONObject.getString("firstEventDate"));
		Assertions.assertFalse(userSessionJSONObject.getBoolean("finalized"));
		Assertions.assertEquals(
			"2022-06-29T11:00:59.000Z",
			userSessionJSONObject.getString("lastEventDate"));
		Assertions.assertEquals(
			0, userSessionJSONObject.getInt("interactionsCount"));
		Assertions.assertEquals(
			2, userSessionJSONObject.getInt("pageViewsCount"));
		JSONAssert.assertEquals(
			JSONUtil.put("http://192.168.108.90:8089/"),
			userSessionJSONObject.getJSONArray("referrers"), false);
		JSONAssert.assertEquals(
			JSONUtil.put("http://192.168.108.90:8089/about"),
			userSessionJSONObject.getJSONArray("urls"), false);
	}

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_8.json"
	)
	@Test
	public void testMergeUserSessions2() {
		runNanite();

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				"http://192.168.108.90:8089/about",
				"http://192.168.108.90:8089/contact",
				"http://192.168.108.90:8089/products"),
			userSessionJSONObject.getJSONArray("canonicalUrls"), false);
		Assertions.assertEquals(
			"2022-06-29T10:00:00.000Z",
			userSessionJSONObject.getString("firstEventDate"));
		Assertions.assertFalse(userSessionJSONObject.getBoolean("finalized"));
		Assertions.assertEquals(
			"2022-06-29T11:27:00.000Z",
			userSessionJSONObject.getString("lastEventDate"));
		Assertions.assertEquals(
			0, userSessionJSONObject.getInt("interactionsCount"));
		Assertions.assertEquals(
			4, userSessionJSONObject.getInt("pageViewsCount"));
		JSONAssert.assertEquals(
			JSONUtil.put("http://192.168.108.90:8089/"),
			userSessionJSONObject.getJSONArray("referrers"), false);
		JSONAssert.assertEquals(
			JSONUtil.putAll(
				"http://192.168.108.90:8089/about",
				"http://192.168.108.90:8089/contact",
				"http://192.168.108.90:8089/products"),
			userSessionJSONObject.getJSONArray("urls"), false);
	}

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_4.json"
	)
	@Test
	public void testPastEventUpdateCompletedSession() {

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		// TODO get user sessions

		userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(0);

		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertNotEquals(
			modifiedDate, userSessionJSONObject.getString("modifiedDate"));
	}

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_5.json"
	)
	@Test
	public void testPastEventUpdateOpenSession() {

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		// TODO get user sessions

		userSessionsJSONArray = new JSONArray();

		Assertions.assertEquals(1, userSessionsJSONArray.length());

		userSessionJSONObject = userSessionsJSONArray.getJSONObject(0);

		Assertions.assertFalse(userSessionJSONObject.getBoolean("bounced"));
		Assertions.assertFalse(userSessionJSONObject.getBoolean("completed"));
		Assertions.assertNotEquals(
			modifiedDate, userSessionJSONObject.getString("modifiedDate"));
	}

	// TODO get user sessions

	@Disabled
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_SESSION,
		resourcePath = "user_session_raw_2.json"
	)
	@Test
	public void testUpdateSession() {

		// TODO get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		JSONObject userSessionJSONObject = userSessionsJSONArray.getJSONObject(
			0);

		String modifiedDate = userSessionJSONObject.getString("modifiedDate");

		runNanite();

		// TODO get user sessions

		userSessionsJSONArray = new JSONArray();

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

	@Autowired
	private UserSessionNanite _userSessionNanite;

}