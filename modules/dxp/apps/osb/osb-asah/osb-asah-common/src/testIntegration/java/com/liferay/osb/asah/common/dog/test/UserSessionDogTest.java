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
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

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

	@Disabled
	@RepositoryResource(
		repositoryClass = BQSessionRepository.class,
		resourcePath = "osbasahcerebroinfo/user_sessions_info.json"
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

	@SQLResource(resourcePath = "test_bq_sessions_field_values.sql")
	@Test
	public void testSearchBQSessionsFieldValues() {
		Page<String> searchBQSessionsFieldValues =
			_userSessionDog.searchBQSessionsFieldValues(
				"browserName", null, 0, 10, null);

		Assertions.assertEquals(
			2, searchBQSessionsFieldValues.getTotalElements());

		List<String> content = searchBQSessionsFieldValues.getContent();

		Assertions.assertEquals(Arrays.asList("Chrome", "Firefox"), content);

		searchBQSessionsFieldValues =
			_userSessionDog.searchBQSessionsFieldValues(
				"browserName", null, 0, 10, "Chro");

		Assertions.assertEquals(
			1, searchBQSessionsFieldValues.getTotalElements());

		content = searchBQSessionsFieldValues.getContent();

		Assertions.assertEquals(Arrays.asList("Chrome"), content);

		searchBQSessionsFieldValues =
			_userSessionDog.searchBQSessionsFieldValues(
				"referrer", null, 0, 10, null);

		Assertions.assertEquals(
			4, searchBQSessionsFieldValues.getTotalElements());

		content = searchBQSessionsFieldValues.getContent();

		Assertions.assertEquals(
			Arrays.asList(
				"http://192.168.118.3:7400/",
				"http://192.168.118.3:7400/c/portal/logout",
				"http://192.168.118.3:7400/web/forms/shared/-/form/44224?" +
					"p_p_state=pop_up&p_p_auth=V2Mnn7B1",
				"http://192.168.118.3:7400/web/guest/home"),
			content);

		searchBQSessionsFieldValues =
			_userSessionDog.searchBQSessionsFieldValues(
				"referrer", null, 0, 10, "http://192.168.118.3:7400/web/forms");

		Assertions.assertEquals(
			1, searchBQSessionsFieldValues.getTotalElements());

		content = searchBQSessionsFieldValues.getContent();

		Assertions.assertEquals(
			Arrays.asList(
				"http://192.168.118.3:7400/web/forms/shared/-/form/44224?" +
					"p_p_state=pop_up&p_p_auth=V2Mnn7B1"),
			content);
	}

	@Autowired
	private UserSessionDog _userSessionDog;

}