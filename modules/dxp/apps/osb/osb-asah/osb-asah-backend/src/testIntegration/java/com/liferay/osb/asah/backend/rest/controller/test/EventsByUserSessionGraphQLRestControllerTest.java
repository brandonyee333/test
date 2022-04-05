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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
public class EventsByUserSessionGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "events_by_session_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "events_by_sessions_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "events_by_session_query.graphql";
	}

	@BeforeEach
	public void setUp() throws Exception {
		_createEvent("assetClicked", "sessionId1");
		_createEvent("assetDownloaded", "sessionId1");
		_createEvent("pageViewed", "sessionId2");

		_createUserSession(
			"sessionId1", "2021-10-08T01:00:00.000Z",
			"2021-10-08T01:30:00.000Z");
		_createUserSession(
			"sessionId2", "2021-10-08T01:27:00.000Z",
			"2021-10-08T02:05:00.000Z");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_cerebroInfoElasticsearchInvoker.delete("user-sessions", "sessionId");
		_bqEventRepository.deleteAll();
	}

	private void _createEvent(String eventDefinitionName, String sessionId)
		throws Exception {

		_eventDog.addBQEvent(
			"Page", Collections.emptySet(), null, "canonicalUrlValue", 1L, null,
			null, null, null, new Date(), 1L, "pageDescriptionValue", null,
			new Date(), eventDefinitionName, null, RandomTestUtil.randomId(),
			"pageKeywordsValue", null, null, null, "referrerValue", null,
			sessionId, null, "pageTitleValue", "urlValue", "userId", null);
	}

	private void _createUserSession(
		String sessionId, String firstEventDateString,
		String lastEventDateString) {

		_cerebroInfoElasticsearchInvoker.add(
			"user-sessions",
			JSONUtil.put(
				"browserName", "Chrome"
			).put(
				"contentLanguageId", "contentLanguageId"
			).put(
				"devicePixelRatio", "1"
			).put(
				"deviceType", "Chrome"
			).put(
				"firstEventDate", firstEventDateString
			).put(
				"id", sessionId
			).put(
				"languageId", "pt-BR"
			).put(
				"lastEventDate", lastEventDateString
			).put(
				"screenHeight", "970"
			).put(
				"screenWidth", "1920"
			).put(
				"timezoneOffset", "-3"
			).put(
				"userAgent", "UserAgent"
			));
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

}