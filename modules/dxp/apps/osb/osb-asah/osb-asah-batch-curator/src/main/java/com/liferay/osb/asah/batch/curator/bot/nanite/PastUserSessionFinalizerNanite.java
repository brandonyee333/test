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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class PastUserSessionFinalizerNanite extends BaseNanite {

	public void awaitPendingTasks() {
		_boundedExecutor.awaitPendingTasks();
	}

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) {
		List<String> dateStrings = _getDateStrings();

		if (dateStrings.isEmpty()) {
			return;
		}

		Set<String> projectDateStrings =
			_userSessionFinalizeDateStrings.computeIfAbsent(
				ProjectIdThreadLocal.getProjectId(),
				projectId -> new HashSet<>());

		for (String dateString : dateStrings) {
			if (projectDateStrings.size() >= _pastUserSessionFinalizeCount) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Reached concurrent capacity for processing past " +
							"sessions");
				}

				break;
			}

			if (projectDateStrings.contains(dateString)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"%s is already being processed. Skipping.",
							dateString));
				}

				continue;
			}

			String projectId = ProjectIdThreadLocal.getProjectId();

			_boundedExecutor.runAsync(
				() -> {
					try {
						projectDateStrings.add(dateString);

						ProjectIdThreadLocal.setProjectId(projectId);

						_processDay(dateString);
					}
					catch (Exception exception) {
						_log.error(
							String.format(
								"Unable to finalize past user sessions for %s",
								dateString),
							exception);
					}
					finally {
						projectDateStrings.remove(dateString);
					}
				});
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private List<String> _getDateStrings() {
		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"user-sessions",
			searchSourceBuilder -> {
				DateHistogramAggregationBuilder
					dateHistogramAggregationBuilder =
						AggregationBuilders.dateHistogram("sessions_by_day");

				dateHistogramAggregationBuilder.calendarInterval(
					DateHistogramInterval.DAY);
				dateHistogramAggregationBuilder.field("lastEventDate");
				dateHistogramAggregationBuilder.order(BucketOrder.key(false));
				dateHistogramAggregationBuilder.timeZone(
					_timeZoneDog.getZoneId());
				dateHistogramAggregationBuilder.minDocCount(1);

				searchSourceBuilder.aggregation(
					dateHistogramAggregationBuilder);

				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).lt(
							"now/d"
						)
					).filter(
						BoolQueryBuilderUtil.should(
							BoolQueryBuilderUtil.mustNot(
								QueryBuilders.existsQuery("finalized"))
						).should(
							QueryBuilders.termQuery("finalized", false)
						)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Histogram histogram = aggregations.get("sessions_by_day");

		List<String> dateStrings = new ArrayList<>();

		List<? extends Histogram.Bucket> buckets = histogram.getBuckets();

		for (Histogram.Bucket bucket : buckets) {
			dateStrings.add(bucket.getKeyAsString());
		}

		return dateStrings;
	}

	private QueryBuilder _getQueryBuilder(
		String dayDateString, String userSessionId) {

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
				dayDateString
			).lt(
				DateUtil.addDays(dayDateString, 1)
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
	}

	private static final Log _log = LogFactory.getLog(
		PastUserSessionFinalizerNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Value("${osb.asah.batch.curator.past.user.session.finalize.count:5}")
	private int _pastUserSessionFinalizeCount;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	private final Map<String, Set<String>> _userSessionFinalizeDateStrings =
		new ConcurrentHashMap<>();

}