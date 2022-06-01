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

import com.liferay.osb.asah.batch.curator.bot.nanite.PastUserSessionFinalizerNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class PastUserSessionFinalizerNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_session_info_old_8.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testFinalizePastUserSessions() {
		String dayDateString = DateUtil.newDayDateString();

		_pastUserSessionFinalizerNanite.run(
			JSONUtil.put("dayDateString", DateUtil.addDays(dayDateString, -1)));

		_elasticsearchInvoker.refresh();

		_verifyUserSessionIds("366909399944215919", "366909399944215918");

		Assertions.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.termQuery("finalized", true)));

		_pastUserSessionFinalizerNanite.run(
			JSONUtil.put("dayDateString", DateUtil.addDays(dayDateString, -2)));

		_elasticsearchInvoker.refresh();

		_verifyUserSessionIds("366909399944215917");

		Assertions.assertEquals(
			3,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.termQuery("finalized", true)));

		_pastUserSessionFinalizerNanite.run(
			JSONUtil.put("dayDateString", DateUtil.addDays(dayDateString, -3)));

		_elasticsearchInvoker.refresh();

		_verifyUserSessionIds("366909399944215916");

		Assertions.assertEquals(
			4,
			_elasticsearchInvoker.count(
				"user-sessions", QueryBuilders.termQuery("finalized", true)));

		JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
			"user-sessions",
			QueryBuilders.termQuery("id", "366909399979215564"));

		if (userSessionJSONObject.has("finalized")) {
			Assertions.assertFalse(
				userSessionJSONObject.getBoolean("finalized"));
		}
	}

	private void _verifyUserSessionIds(String... finalizedUserSessionIds) {
		for (String finalizedUserSessionId : finalizedUserSessionIds) {
			JSONObject userSessionJSONObject = _elasticsearchInvoker.fetch(
				"user-sessions",
				QueryBuilders.termQuery("id", finalizedUserSessionId));

			Assertions.assertTrue(
				userSessionJSONObject.getBoolean("finalized"));
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private PastUserSessionFinalizerNanite _pastUserSessionFinalizerNanite;

}