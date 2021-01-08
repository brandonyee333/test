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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.arm.FinalizeSessionArm;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class FinalizeSessionArmTest {

	@Before
	public void setUp() {
		Mockito.when(
			_elasticsearchInvokerFactory.forCerebroInfo()
		).thenReturn(
			Mockito.mock(ElasticsearchInvoker.class)
		);

		_finalizeSessionArm.init();
	}

	@Ignore
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
		userSession.setInteractions(Collections.emptyList());
		userSession.setLastEventDate(eventDate);
		userSession.setUserId("1");

		_finalizeSessionArm.processSession(userSession);

		Assert.assertTrue(userSession.getCompleted());
		Assert.assertEquals("inactivity", userSession.getCompleteReason());
	}

	@MockBean
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FinalizeSessionArm _finalizeSessionArm;

}