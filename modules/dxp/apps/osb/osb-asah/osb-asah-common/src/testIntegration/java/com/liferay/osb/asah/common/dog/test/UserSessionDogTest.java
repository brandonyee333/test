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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class UserSessionDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_sessions.sql")
	@Test
	public void testFindByIds() {
		List<BQSession> bqSessions1 = _userSessionDog.findByIds(
			Arrays.asList("366909399944213421"));

		Assertions.assertEquals(1, bqSessions1.size());

		List<BQSession> bqSessions2 = _userSessionDog.findByIds(
			Arrays.asList("366909399944213421", "366909399944215919"));

		Assertions.assertEquals(2, bqSessions2.size());

		List<BQSession> bqSessions3 = _userSessionDog.findByIds(
			Arrays.asList(
				"366909399944213421", "366909399944215919",
				"366909399944215920"));

		Assertions.assertEquals(2, bqSessions3.size());
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "user_sessions_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetIndividualIds() {
		List<Long> individualIds1 = _userSessionDog.getIndividualIds(null);

		Assertions.assertEquals(3, individualIds1.size());

		List<Long> individualIds2 = _userSessionDog.getIndividualIds(
			"(id eq '366909399944215919')");

		Assertions.assertEquals(1, individualIds2.size());

		Assertions.assertEquals(
			Collections.emptyList(),
			_userSessionDog.getIndividualIds(
				"(country eq 'Germany' and completeDate gt 'last24Hours')"));

		List<Long> individualIds3 = _userSessionDog.getIndividualIds(
			"(browserName eq 'Firefox')");

		Assertions.assertEquals(3, individualIds3.size());

		Assertions.assertEquals(
			Collections.emptyList(),
			_userSessionDog.getIndividualIds(
				"(country eq 'Germany' and completeDate gt 'last24Hours')"));
	}

	@Autowired
	private UserSessionDog _userSessionDog;

}