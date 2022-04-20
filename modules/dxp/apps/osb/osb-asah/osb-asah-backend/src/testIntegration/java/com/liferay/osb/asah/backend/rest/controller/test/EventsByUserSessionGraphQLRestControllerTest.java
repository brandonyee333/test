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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
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
		_createBQEvent("assetClicked", "sessionId1");
		_createBQEvent("assetDownloaded", "sessionId1");
		_createBQEvent("pageViewed", "sessionId2");

		_createUserSession("sessionId1", "2021-10-08T01:00:00.000Z");
		_createUserSession("sessionId2", "2021-10-08T01:27:00.000Z");
	}

	@AfterEach
	public void tearDown() {
		_bqEventRepository.deleteAll();
		_bqSessionRepository.deleteAll();
	}

	private void _createBQEvent(String eventDefinitionName, String sessionId)
		throws Exception {

		_eventDog.addBQEvent(
			"Page", Collections.emptySet(), "Chrome", "canonicalUrlValue", 1L,
			null, "contentLanguageId",
			JSONUtil.put(
				"devicePixelRatio", "1"
			).put(
				"screenHeight", "970"
			).put(
				"screenWidth", "1920"
			).put(
				"userAgent", "UserAgent"
			).toString(),
			null, new Date(), 1L, "pageDescriptionValue", "Chrome", new Date(),
			eventDefinitionName, null, RandomTestUtil.randomId(),
			"pageKeywordsValue", "pt-BR", null, null, "referrerValue", null,
			sessionId, "-3", "pageTitleValue", "urlValue", "userId", null);
	}

	private void _createUserSession(
		String sessionId, String firstEventDateString) {

		_userSessionDog.addBQSession(
			1L, sessionId, null, DateUtil.toUTCDate(firstEventDateString));
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}