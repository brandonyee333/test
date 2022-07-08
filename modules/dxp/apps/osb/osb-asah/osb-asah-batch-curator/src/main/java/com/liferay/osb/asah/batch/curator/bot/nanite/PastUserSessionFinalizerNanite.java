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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.FinalizeUserSessionArm;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class PastUserSessionFinalizerNanite extends BaseNanite {

	public static void addUserSessionFinalizeDates(Date... dates) {
		Set<String> userSessionFinalizeDateStrings =
			_userSessionFinalizeDateStringsByProjectId.computeIfAbsent(
				ProjectIdThreadLocal.getProjectId(),
				projectId -> new HashSet<>());

		userSessionFinalizeDateStrings.addAll(
			ListUtil.map(Arrays.asList(dates), DateUtil::toUTCString));

		_userSessionFinalizeDateStringsByProjectId.put(
			ProjectIdThreadLocal.getProjectId(),
			userSessionFinalizeDateStrings);
	}

	public static Set<String> getUserSessionFinalizeDateStrings(
		String projectId) {

		return _userSessionFinalizeDateStringsByProjectId.getOrDefault(
			projectId, Collections.emptySet());
	}

	@Override
	public void run(JSONObject contextJSONObject) {
		if ((contextJSONObject == null) ||
			!contextJSONObject.has("dayDateString")) {

			throw new IllegalArgumentException(
				"Unable to process past user sessions");
		}

		String dayDateString = contextJSONObject.getString("dayDateString");

		Set<String> projectUserSessionFinalizeDateStrings =
			_userSessionFinalizeDateStringsByProjectId.computeIfAbsent(
				ProjectIdThreadLocal.getProjectId(),
				projectId -> new HashSet<>());

		try {
			projectUserSessionFinalizeDateStrings.add(dayDateString);

			_processDay(dayDateString);
		}
		catch (Exception exception) {
			_log.error(
				String.format(
					"Unable to finalize past user sessions for %s",
					dayDateString),
				exception);
		}
		finally {
			projectUserSessionFinalizeDateStrings.remove(dayDateString);
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private QueryBuilder _getQueryBuilder(
		String dayDateString, String userSessionId) {

		LocalDateTime localDateTime = DateUtil.toLocalDateTime(
			DateUtil.toUTCDate(dayDateString), _timeZoneDog.getZoneId());

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			BoolQueryBuilderUtil.should(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("finalized"))
			).should(
				QueryBuilders.termQuery("finalized", false)
			)
		).filter(
			QueryBuilders.rangeQuery(
				"lastEventDate"
			).gte(
				DateUtil.toUTCString(localDateTime)
			).lt(
				DateUtil.toUTCString(localDateTime.plusDays(1))
			)
		);

		if (userSessionId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					userSessionId
				));
		}

		return boolQueryBuilder;
	}

	private void _processDay(String dayDateString) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Start finalizing past user sessions for " + dayDateString);
		}

		long processDayStart = System.currentTimeMillis();

		String userSessionId = null;

		while (true) {
			long start = System.currentTimeMillis();

			JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
				"user-sessions", _getQueryBuilder(dayDateString, userSessionId),
				500);

			List<UserSession> userSessions = JSONUtil.toList(
				jsonArray,
				jsonObject -> _objectMapper.convertValue(
					jsonObject, UserSession.class));

			if (userSessions.isEmpty()) {
				break;
			}

			UserSession latestUserSession = userSessions.get(
				userSessions.size() - 1);

			userSessionId = latestUserSession.getId();

			userSessions.forEach(
				userSession -> {
					try {
						_finalizeUserSessionArm.processSession(userSession);
					}
					catch (Exception exception) {
						_log.error(exception.getMessage(), exception);
					}
				});

			if (_log.isDebugEnabled()) {
				Class<?> clazz = getClass();

				_log.debug(
					String.format(
						"%s processed %d past sessions in %d ms",
						clazz.getSimpleName(), userSessions.size(),
						System.currentTimeMillis() - start));
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Completed finalizing past user sessions for %s in %d ms",
					dayDateString,
					System.currentTimeMillis() - processDayStart));
		}
	}

	private static final Log _log = LogFactory.getLog(
		PastUserSessionFinalizerNanite.class);

	private static final Map<String, Set<String>>
		_userSessionFinalizeDateStringsByProjectId = new ConcurrentHashMap<>();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}