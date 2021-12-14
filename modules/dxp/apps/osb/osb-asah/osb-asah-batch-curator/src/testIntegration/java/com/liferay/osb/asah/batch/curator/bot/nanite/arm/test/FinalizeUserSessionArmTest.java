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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.OSBAsahBatchCuratorSpringTestContext;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.FinalizeUserSessionArm;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class FinalizeUserSessionArmTest
	implements OSBAsahBatchCuratorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_cerebroInfoElasticsearchInvoker.save(
			"user-sessions", JSONUtil.put("id", "1"));
	}

	@Test
	public void testCompleteReasonInactivity() throws Exception {
		OffsetDateTime nowOffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC);

		OffsetDateTime nowMinus30MinutesOffsetDateTime =
			nowOffsetDateTime.minusMinutes(30);

		Date eventDate = Date.from(nowMinus30MinutesOffsetDateTime.toInstant());

		UserSession userSession = new UserSession();

		userSession.setBounced(true);
		userSession.setCompleted(false);
		userSession.setDataSourceId("1");
		userSession.setFirstEventDate(eventDate);
		userSession.setId("1");
		userSession.setLastEventDate(eventDate);
		userSession.setUserId("1");

		_finalizeUserSessionArm.processSession(userSession);

		userSession = _objectMapper.convertValue(
			_cerebroInfoElasticsearchInvoker.get("user-sessions", "1"),
			UserSession.class);

		Assertions.assertTrue(userSession.getCompleted());
		Assertions.assertEquals("inactivity", userSession.getCompleteReason());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

}