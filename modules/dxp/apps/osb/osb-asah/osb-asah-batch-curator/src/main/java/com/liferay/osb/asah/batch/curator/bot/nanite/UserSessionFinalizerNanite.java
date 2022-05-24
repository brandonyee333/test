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
import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import javax.annotation.PreDestroy;

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
 * @author André Miranda
 */
@Component
public class UserSessionFinalizerNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	public void run(boolean force) throws Exception {
		AsahMarker asahMarker = _getAsahMarker();

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		String lastSuccessfulSessionFinalizerDate =
			asahMarkerContextJSONObject.optString(
				"lastSuccessfulSessionFinalizerDate", "now-30m");

		String dateString = DateUtil.newDateString();

		String userSessionId = null;

		while (true) {
			long start = System.currentTimeMillis();

			JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
				"user-sessions",
				_getQueryBuilder(
					dateString, force, lastSuccessfulSessionFinalizerDate,
					userSessionId),
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

			String projectId = ProjectIdThreadLocal.getProjectId();

			for (UserSession userSession : userSessions) {
				_boundedExecutor.runAsync(
					() -> {
						try {
							ProjectIdThreadLocal.setProjectId(projectId);

							_finalizeUserSessionArm.processSession(userSession);
						}
						catch (Exception exception) {
							_log.error(exception.getMessage(), exception);
						}
					});
			}

			if (_log.isDebugEnabled()) {
				Class<?> clazz = getClass();

				_log.debug(
					String.format(
						"%s processed %d sessions in %d ms",
						clazz.getSimpleName(), userSessions.size(),
						System.currentTimeMillis() - start));
			}
		}

		asahMarkerContextJSONObject.put(
			"lastSuccessfulSessionFinalizerDate", dateString);

		_asahMarkerDog.updateAsahMarker(asahMarker);

		_boundedExecutor.awaitPendingTasks();
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		run(false);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private AsahMarker _getAsahMarker() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker("SessionNanite");

		if (asahMarker == null) {
			asahMarker = _asahMarkerDog.addAsahMarker(
				new AsahMarker("SessionNanite"));

			asahMarker.setIsNew(Boolean.FALSE);
		}

		return asahMarker;
	}

	private QueryBuilder _getQueryBuilder(
		String currentDateString, boolean force,
		String lastSuccessfulSessionFinalizerDateString, String userSessionId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("completed", false));

		if (!force) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).lt(
						"now-30m"
					)
				).should(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).lt(
						"now/d"
					).timeZone(
						_timeZoneDog.getTimeZoneId()
					)
				));
		}

		boolQueryBuilder = BoolQueryBuilderUtil.should(
			boolQueryBuilder
		).should(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("completed", true)
			).filter(
				BoolQueryBuilderUtil.should(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.existsQuery("finalized"))
				).should(
					QueryBuilders.termQuery("finalized", false)
				)
			).filter(
				QueryBuilders.rangeQuery(
					"modifiedDate"
				).gte(
					lastSuccessfulSessionFinalizerDateString
				).lt(
					currentDateString
				)
			)
		);

		if (userSessionId != null) {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(
				boolQueryBuilder
			).filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					userSessionId
				)
			);
		}

		return boolQueryBuilder;
	}

	private static final Log _log = LogFactory.getLog(
		UserSessionFinalizerNanite.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}