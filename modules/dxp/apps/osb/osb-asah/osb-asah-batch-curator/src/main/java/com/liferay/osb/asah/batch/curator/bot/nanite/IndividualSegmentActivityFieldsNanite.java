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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualSegmentActivityFieldsNanite extends BaseNanite {

	public void run() throws Exception {
		int page = 0;

		List<Segment> segments = _segmentDog.getSegments(page, 500);

		while (!segments.isEmpty()) {
			for (Segment segment : segments) {
				try {
					process(segment);
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}

			segments = _segmentDog.getSegments(++page, 500);
		}
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		boolean locked = _reentrantLock.tryLock();

		if (!locked) {
			return;
		}

		try {
			while (_analyticsConfigured.getOrDefault(
						ProjectIdThreadLocal.getProjectId(), false)) {

				run();

				Thread.sleep(DateUtil.MINUTE);
			}

			_cleanUp();
		}
		finally {
			_reentrantLock.unlock();
		}
	}

	public void setAnalyticsConfigured(boolean analyticsConfigured) {
		_analyticsConfigured.put(
			ProjectIdThreadLocal.getProjectId(), analyticsConfigured);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected void process(Segment segment) {
		Long channelId = segment.getChannelId();
		String name = segment.getName();
		Long segmentId = segment.getId();

		if ((channelId == null) && !name.startsWith("Account: ")) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Skipping segment due missing channel ID " + segmentId);
			}

			return;
		}

		if (segment.getActivitiesCount() == null) {
			segment.setActivitiesCount(0L);

			_segmentDog.updateSegment(segment, segmentId);
		}

		boolean includeAnonymousUsers = BooleanUtils.toBoolean(
			segment.getIncludeAnonymousUsers());

		long activitiesCount = _getActivitiesCount(
			channelId, includeAnonymousUsers, segmentId);
		String lastActivityDateString = _getLastActivityDateString(
			channelId, includeAnonymousUsers, segmentId);

		if ((activitiesCount == segment.getActivitiesCount()) &&
			Objects.nonNull(segment.getLastActivityDate()) &&
			Objects.equals(
				lastActivityDateString,
				DateUtil.toUTCString(segment.getLastActivityDate()))) {

			return;
		}

		segment.setActivitiesCount(activitiesCount);

		if (Objects.nonNull(lastActivityDateString)) {
			segment.setLastActivityDate(
				DateUtil.toUTCDate(lastActivityDateString));
		}

		_segmentDog.replaceSegment(segment);
	}

	private void _cleanUp() {
		_segmentDog.updateSegments(0L);
	}

	private long _getActivitiesCount(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		if (channelId == null) {
			searchSourceBuilder.aggregation(
				AggregationBuilders.nested(
					"activitiesCounts", "activitiesCounts"
				).subAggregation(
					AggregationBuilders.sum(
						"activitiesCountSum"
					).field(
						"activitiesCounts.activitiesCount"
					)
				));
		}
		else {
			searchSourceBuilder.aggregation(
				AggregationBuilders.nested(
					"activitiesCounts", "activitiesCounts"
				).subAggregation(
					AggregationBuilders.filter(
						"filteredActivities",
						QueryBuilders.termQuery(
							"activitiesCounts.channelId",
							String.valueOf(channelId))
					).subAggregation(
						AggregationBuilders.sum(
							"activitiesCountSum"
						).field(
							"activitiesCounts.activitiesCount"
						)
					)
				));
		}

		searchSourceBuilder.query(
			_getQueryBuilder(channelId, includeAnonymousUsers, segmentId));
		searchSourceBuilder.size(0);

		SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
			"individuals", searchSourceBuilder);

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return 0;
		}

		Nested nested = aggregations.get("activitiesCounts");

		Aggregations nestedAggregations = nested.getAggregations();

		Sum sum = null;

		if (channelId != null) {
			Filter filter = nestedAggregations.get("filteredActivities");

			Aggregations filterAggregations = filter.getAggregations();

			sum = filterAggregations.get("activitiesCountSum");
		}
		else {
			sum = nestedAggregations.get("activitiesCountSum");
		}

		if (sum == null) {
			return 0;
		}

		return (long)sum.getValue();
	}

	private String _getLastActivityDateString(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		JSONArray individualsJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"individuals",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						_getQueryBuilder(
							channelId, includeAnonymousUsers, segmentId));
					searchSourceBuilder.size(1);

					QueryBuilder queryBuilder = null;

					if (channelId != null) {
						queryBuilder = QueryBuilders.termQuery(
							"lastActivityDates.channelId",
							String.valueOf(channelId));
					}

					searchSourceBuilder.sort(
						SortBuilderUtil.buildSort(
							"lastActivityDates.lastActivityDate",
							"lastActivityDates", queryBuilder, SortOrder.DESC));
				}));

		if (individualsJSONArray.length() == 0) {
			return null;
		}

		JSONObject individualJSONObject = individualsJSONArray.getJSONObject(0);

		JSONArray lastActivityDatesJSONArray =
			individualJSONObject.optJSONArray("lastActivityDates");

		JSONObject channelLastActivityDateJSONObject = JSONUtil.find(
			lastActivityDatesJSONArray, "channelId", String.valueOf(channelId));

		if (channelLastActivityDateJSONObject == null) {
			return null;
		}

		return channelLastActivityDateJSONObject.optString(
			"lastActivityDate", null);
	}

	private QueryBuilder _getQueryBuilder(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentIds", String.valueOf(segmentId)));

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (!includeAnonymousUsers) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		return boolQueryBuilder;
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentActivityFieldsNanite.class);

	private final Map<String, Boolean> _analyticsConfigured = new HashMap<>();
	private final ReentrantLock _reentrantLock = new ReentrantLock();

	@Autowired
	private SegmentDog _segmentDog;

}