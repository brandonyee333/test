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
import com.liferay.osb.asah.batch.curator.bot.nanite.PastUserSessionFinalizerNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.FinalizeUserSessionArm;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.dog.UserSessionDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
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

	@AfterEach
	public void tearDown() {
		for (Project project : _projectDog.getProjects()) {
			String projectId = project.getId();

			if (projectId == null) {
				continue;
			}

			try {
				ProjectIdThreadLocal.setProjectId(projectId);

				_cerebroInfoElasticsearchInvoker.deleteByQuery(
					QueryBuilders.matchAllQuery(), true, "user-sessions");

				_preferenceDog.savePreference("time-zone-id", "UTC");
			}
			finally {
				ProjectIdThreadLocal.remove();
			}

			if (!projectId.equals("test")) {
				_projectDog.deleteProject(projectId);
			}
		}
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

	@Test
	public void testGetPastUserSessionDates() {
		_projectDog.addProject(new Project("test1"));

		_addUserSession(
			"test1", "2021-12-20T01:22:49.000Z", "2022-01-04T05:43:21.000Z",
			"2022-01-04T11:23:00.000Z", "2022-02-17T06:00:44.000Z",
			"2022-03-14T00:00:00.000Z", "2022-05-11T14:19:00.000Z");

		_projectDog.addProject(new Project("test2"));

		_addUserSession(
			"test2", "2022-01-01T00:00:00.000Z", "2022-01-19T00:00:00.000Z");

		Map<Date, Long> pastUserSessionDates =
			_finalizeUserSessionArm.getPastUserSessionDates("test1");

		Assertions.assertEquals(
			new TreeMap<Date, Long>(Comparator.reverseOrder()) {
				{
					put(DateUtil.toUTCDate("2021-12-20T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-01-04T00:00:00.000Z"), 2L);
					put(DateUtil.toUTCDate("2022-02-17T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-03-14T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-05-11T00:00:00.000Z"), 1L);
				}
			},
			pastUserSessionDates);

		Set<Date> keySet = pastUserSessionDates.keySet();

		Stream<Date> stream = keySet.stream();

		List<String> pastUserSessionDateStrings = stream.map(
			DateUtil::toUTCString
		).collect(
			Collectors.toList()
		);

		Assertions.assertEquals(
			"[2022-05-11T00:00:00.000Z, 2022-03-14T00:00:00.000Z, " +
				"2022-02-17T00:00:00.000Z, 2022-01-04T00:00:00.000Z, " +
					"2021-12-20T00:00:00.000Z]",
			pastUserSessionDateStrings.toString());
	}

	@Test
	public void testGetPastUserSessionDatesToFinalize() {
		_projectDog.addProject(new Project("test1"));

		_addUserSession(
			"test1", "2022-01-04T07:11:04.000Z", "2022-01-04T11:23:00.000Z",
			"2022-01-04T16:23:00.000Z", "2022-03-14T10:38:27.000Z");

		_projectDog.addProject(new Project("test2"));

		_addUserSession(
			"test2", "2022-01-04T05:43:21.000Z", "2022-02-01T00:00:00.000Z");

		_projectDog.addProject(new Project("test3"));

		_addUserSession(
			"test3", "2022-01-04T07:20:00.000Z", "2022-01-04T09:14:48.000Z",
			"2022-03-14T00:00:00.000Z");

		Map<Date, List<String>> finalizablePastUserSessions =
			_finalizeUserSessionArm.getFinalizablePastUserSessions();

		Assertions.assertEquals(
			new TreeMap<Date, List<String>>(Collections.reverseOrder()) {
				{
					put(
						DateUtil.toUTCDate("2022-03-14T00:00:00.000Z"),
						new ArrayList<String>() {
							{
								add("test1");
								add("test3");
							}
						});

					put(
						DateUtil.toUTCDate("2022-02-01T00:00:00.000Z"),
						Collections.singletonList("test2"));

					put(
						DateUtil.toUTCDate("2022-01-04T00:00:00.000Z"),
						new ArrayList<String>() {
							{
								add("test1");
								add("test3");
								add("test2");
							}
						});
				}
			},
			finalizablePastUserSessions);

		Set<Date> keySet = finalizablePastUserSessions.keySet();

		Stream<Date> stream = keySet.stream();

		List<String> pastUserSessionDateStrings = stream.map(
			DateUtil::toUTCString
		).collect(
			Collectors.toList()
		);

		Assertions.assertEquals(
			"[2022-03-14T00:00:00.000Z, 2022-02-01T00:00:00.000Z, " +
				"2022-01-04T00:00:00.000Z]",
			pastUserSessionDateStrings.toString());
	}

	@Test
	public void testGetPastUserSessionDatesWithTimeZone() {
		_projectDog.addProject(new Project("test"));

		try {
			ProjectIdThreadLocal.setProjectId("test");

			_preferenceDog.savePreference(
				"time-zone-id", "America/Los_Angeles");
		}
		finally {
			ProjectIdThreadLocal.remove();
		}

		_addUserSession(
			"test", "2021-12-20T01:22:49.000Z", "2022-01-04T05:43:21.000Z",
			"2022-01-04T11:23:00.000Z", "2022-02-17T06:00:44.000Z",
			"2022-03-14T00:00:00.000Z", "2022-05-11T14:19:00.000Z");

		Assertions.assertEquals(
			new TreeMap<Date, Long>(Comparator.reverseOrder()) {
				{
					put(DateUtil.toUTCDate("2021-12-19T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-01-04T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-01-03T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-02-16T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-03-13T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-05-11T00:00:00.000Z"), 1L);
				}
			},
			_finalizeUserSessionArm.getPastUserSessionDates("test"));
	}

	@Test
	public void testGetUserSessionDatesIsFiltered() {
		_projectDog.addProject(new Project("test1"));

		_addUserSession(
			"test1", "2021-12-20T01:22:49.000Z", "2022-01-04T05:43:21.000Z",
			"2022-01-04T11:23:00.000Z", "2022-02-17T06:00:44.000Z",
			"2022-03-14T00:00:00.000Z", "2022-05-11T14:19:00.000Z");

		try {
			ProjectIdThreadLocal.setProjectId("test1");

			PastUserSessionFinalizerNanite.addUserSessionFinalizeDates(
				DateUtil.toUTCDate("2022-01-04T00:00:00.000Z"),
				DateUtil.toUTCDate("2022-03-14T00:00:00.000Z"));
		}
		finally {
			ProjectIdThreadLocal.remove();
		}

		Map<Date, Long> pastUserSessionDates =
			_finalizeUserSessionArm.getPastUserSessionDates("test1");

		Assertions.assertEquals(
			new TreeMap<Date, Long>(Comparator.reverseOrder()) {
				{
					put(DateUtil.toUTCDate("2021-12-20T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-02-17T00:00:00.000Z"), 1L);
					put(DateUtil.toUTCDate("2022-05-11T00:00:00.000Z"), 1L);
				}
			},
			pastUserSessionDates);

		Set<Date> keySet = pastUserSessionDates.keySet();

		Stream<Date> stream = keySet.stream();

		List<String> pastUserSessionDateStrings = stream.map(
			DateUtil::toUTCString
		).collect(
			Collectors.toList()
		);

		Assertions.assertEquals(
			"[2022-05-11T00:00:00.000Z, 2022-02-17T00:00:00.000Z, " +
				"2021-12-20T00:00:00.000Z]",
			pastUserSessionDateStrings.toString());
	}

	private void _addUserSession(String projectId, String... dateStrings) {
		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			for (String dateString : dateStrings) {
				UserSession userSession = new UserSession();

				userSession.setCompleted(true);
				userSession.setFinalized(false);
				userSession.setLastEventDate(DateUtil.toUTCDate(dateString));

				_cerebroInfoElasticsearchInvoker.add(
					"user-sessions",
					_objectMapper.convertValue(userSession, JSONObject.class));
			}
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceDog _preferenceDog;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Autowired
	private UserSessionDog _userSessionDog;

}