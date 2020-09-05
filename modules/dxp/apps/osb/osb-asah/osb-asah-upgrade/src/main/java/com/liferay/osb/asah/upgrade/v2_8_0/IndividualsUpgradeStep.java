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

package com.liferay.osb.asah.upgrade.v2_8_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoMembershipDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class IndividualsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		SearchResponse searchResponse = _elasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"duplicate_emails"
					).field(
						"demographics.email.value"
					).minDocCount(
						2
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return;
		}

		List<Aggregation> aggregationList = aggregations.asList();

		if (aggregationList.isEmpty()) {
			return;
		}

		Terms terms = aggregations.get("duplicate_emails");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			String name = bucket.getKeyAsString();

			JSONArray jsonArray = new JSONArray(
				_elasticsearchInvoker.get(
					"individuals",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.termQuery(
								"demographics.email.value", name));
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"dateCreated", SortOrder.ASC));
					}));

			JSONObject originalIndividualJSONObject = jsonArray.getJSONObject(
				0);

			for (int i = 1; i < jsonArray.length(); i++) {
				JSONObject individualJSONObject = jsonArray.getJSONObject(i);

				_mergeIndividual(
					originalIndividualJSONObject, individualJSONObject);

				_updateActivitiesAndActivityGroups(
					originalIndividualJSONObject,
					individualJSONObject.getString("id"));
				_updateOwnerIds(
					originalIndividualJSONObject.getString("id"),
					individualJSONObject.getString("id"));

				_elasticsearchInvoker.delete(
					"individuals", individualJSONObject);

				String individualId = individualJSONObject.getString("id");

				JSONArrayIterator.of(
					"memberships", _elasticsearchInvoker,
					membershipJSONObject -> {
						_faroInfoMembershipDog.deactivateMembership(
							DateUtil.newDateString(), individualId,
							membershipJSONObject.getString(
								"individualSegmentId"));

						return null;
					}
				).setQueryBuilder(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("individualId", individualId)
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					)
				).iterate();
			}

			originalIndividualJSONObject.put(
				"emailAddressHashed",
				DigestUtils.sha256Hex(StringUtils.lowerCase(name)));

			_elasticsearchInvoker.update(
				"individuals", originalIndividualJSONObject.getString("id"),
				originalIndividualJSONObject);
		}
	}

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private JSONArray _mergeActivitiesCounts(
		JSONArray activitiesCountsJSONArray1,
		JSONArray activitiesCountsJSONArray2) {

		if (activitiesCountsJSONArray1 == null) {
			activitiesCountsJSONArray1 = new JSONArray();
		}

		if (activitiesCountsJSONArray2 == null) {
			activitiesCountsJSONArray2 = new JSONArray();
		}

		for (int i = 0; i < activitiesCountsJSONArray1.length(); i++) {
			JSONObject activitiesCountJSONObject1 =
				activitiesCountsJSONArray1.getJSONObject(i);

			JSONObject activitiesCountJSONObject2 = JSONUtil.find(
				activitiesCountsJSONArray2, "channelId",
				activitiesCountJSONObject1.get("channelId"));

			if (activitiesCountJSONObject2 == null) {
				continue;
			}

			long count =
				activitiesCountJSONObject1.optLong("activitiesCount", 0) +
					activitiesCountJSONObject2.optLong("activitiesCount", 0);

			activitiesCountJSONObject1.put("activitiesCount", count);

			JSONUtil.removeValue(
				activitiesCountsJSONArray2, activitiesCountJSONObject2);
		}

		for (int i = 0; i < activitiesCountsJSONArray2.length(); i++) {
			activitiesCountsJSONArray1.put(activitiesCountsJSONArray2.get(i));
		}

		return activitiesCountsJSONArray1;
	}

	private JSONArray _mergeDataSourceIndividualPKs(
			JSONArray dataSourceIndividualPKsJSONArray1,
			JSONArray dataSourceIndividualPKsJSONArray2)
		throws Exception {

		if (dataSourceIndividualPKsJSONArray1 == null) {
			dataSourceIndividualPKsJSONArray1 = new JSONArray();
		}

		if (dataSourceIndividualPKsJSONArray2 == null) {
			dataSourceIndividualPKsJSONArray2 = new JSONArray();
		}

		for (int i = 0; i < dataSourceIndividualPKsJSONArray1.length(); i++) {
			JSONObject dataSourceIndividualPKsJSONObject1 =
				dataSourceIndividualPKsJSONArray1.getJSONObject(i);

			JSONObject dataSourceIndividualPKsJSONObject2 = JSONUtil.find(
				dataSourceIndividualPKsJSONArray2, "dataSourceId",
				dataSourceIndividualPKsJSONObject1.get("dataSourceId"));

			if (dataSourceIndividualPKsJSONObject2 == null) {
				continue;
			}

			JSONArray individualPKsJSONArray1 =
				dataSourceIndividualPKsJSONObject1.optJSONArray(
					"individualPKs");

			String[] individualPKs = JSONUtil.toStringArray(
				individualPKsJSONArray1);

			JSONArray individualPKsJSONArray2 =
				dataSourceIndividualPKsJSONObject2.optJSONArray(
					"individualPKs");

			if (individualPKsJSONArray2 != null) {
				for (int j = 0; j < individualPKsJSONArray2.length(); j++) {
					if (ArrayUtils.contains(
							individualPKs,
							individualPKsJSONArray2.getString(j))) {

						continue;
					}

					individualPKs = (String[])ArrayUtils.add(
						individualPKs, individualPKsJSONArray2.getString(j));
				}
			}

			dataSourceIndividualPKsJSONObject1.put(
				"individualPKs",
				JSONUtil.toJSONArray(
					individualPKs, individualPK -> individualPK));

			JSONUtil.removeValue(
				dataSourceIndividualPKsJSONArray2,
				dataSourceIndividualPKsJSONObject2);
		}

		for (int i = 0; i < dataSourceIndividualPKsJSONArray2.length(); i++) {
			dataSourceIndividualPKsJSONArray1.put(
				dataSourceIndividualPKsJSONArray2.get(i));
		}

		return dataSourceIndividualPKsJSONArray1;
	}

	private JSONObject _mergeDemographics(
		JSONObject demographicsJSONObject1, JSONObject demographicsJSONObject2,
		String ownerId) {

		if (demographicsJSONObject1 == null) {
			demographicsJSONObject1 = new JSONObject();
		}

		if (demographicsJSONObject2 == null) {
			demographicsJSONObject2 = new JSONObject();
		}

		Iterator<String> iterator = demographicsJSONObject2.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			JSONArray jsonArray = demographicsJSONObject2.getJSONArray(key);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				jsonObject.put("ownerId", ownerId);
			}
		}

		return JSONUtil.merge(demographicsJSONObject1, demographicsJSONObject2);
	}

	private JSONArray _mergeIds(
			JSONArray idsJSONArray1, JSONArray idsJSONArray2)
		throws Exception {

		if (idsJSONArray1 == null) {
			idsJSONArray1 = new JSONArray();
		}

		if (idsJSONArray2 == null) {
			idsJSONArray2 = new JSONArray();
		}

		String[] ids1 = JSONUtil.toStringArray(idsJSONArray1);
		String[] ids2 = JSONUtil.toStringArray(idsJSONArray2);

		for (String id : ids2) {
			if (ArrayUtils.contains(ids1, id)) {
				continue;
			}

			ids1 = (String[])ArrayUtils.add(ids1, id);
		}

		return JSONUtil.toJSONArray(ids1, id -> id);
	}

	private void _mergeIndividual(
			JSONObject individualJSONObject1, JSONObject individualJSONObject2)
		throws Exception {

		individualJSONObject1.put(
			"activitiesCount",
			individualJSONObject1.optLong("activitiesCount", 0) +
				individualJSONObject2.optLong("activitiesCount", 0));
		individualJSONObject1.put(
			"activitiesCounts",
			_mergeActivitiesCounts(
				individualJSONObject1.optJSONArray("activitiesCounts"),
				individualJSONObject2.optJSONArray("activitiesCounts")));
		individualJSONObject1.put(
			"channelIds",
			_mergeIds(
				individualJSONObject1.optJSONArray("channelIds"),
				individualJSONObject2.optJSONArray("channelIds")));
		individualJSONObject1.put(
			"custom",
			_mergeDemographics(
				individualJSONObject1.optJSONObject("custom"),
				individualJSONObject2.optJSONObject("custom"),
				individualJSONObject1.getString("id")));
		individualJSONObject1.put(
			"dataSourceIndividualPKs",
			_mergeDataSourceIndividualPKs(
				individualJSONObject1.optJSONArray("dataSourceIndividualPKs"),
				individualJSONObject2.optJSONArray("dataSourceIndividualPKs")));
		individualJSONObject1.put(
			"demographics",
			_mergeDemographics(
				individualJSONObject1.optJSONObject("demographics"),
				individualJSONObject2.optJSONObject("demographics"),
				individualJSONObject1.getString("id")));
		individualJSONObject1.put(
			"individualSegmentIds",
			_mergeIds(
				individualJSONObject1.optJSONArray("individualSegmentIds"),
				individualJSONObject2.optJSONArray("individualSegmentIds")));
		individualJSONObject1.put(
			"lastActivityDates",
			_mergeLastActivityDates(
				individualJSONObject1.optJSONArray("lastActivityDates"),
				individualJSONObject2.optJSONArray("lastActivityDates")));
		individualJSONObject1.putOpt(
			"lastEnrichmentDate",
			individualJSONObject2.optString("lastEnrichmentDate", null));

		JSONObject analyticsDataJSONObject =
			individualJSONObject2.optJSONObject("analyticsData");

		if (analyticsDataJSONObject != null) {
			Set<String> keys = analyticsDataJSONObject.keySet();

			if (!keys.isEmpty()) {
				individualJSONObject1.put(
					"analyticsData", analyticsDataJSONObject);
			}
		}
	}

	private JSONArray _mergeLastActivityDates(
			JSONArray lastActivityDatesJSONArray1,
			JSONArray lastActivityDatesJSONArray2)
		throws Exception {

		if (lastActivityDatesJSONArray1 == null) {
			lastActivityDatesJSONArray1 = new JSONArray();
		}

		if (lastActivityDatesJSONArray2 == null) {
			lastActivityDatesJSONArray2 = new JSONArray();
		}

		for (int i = 0; i < lastActivityDatesJSONArray1.length(); i++) {
			JSONObject lastActivityDatesJSONObject1 =
				lastActivityDatesJSONArray1.getJSONObject(i);

			JSONObject lastActivityDatesJSONObject2 = JSONUtil.find(
				lastActivityDatesJSONArray2, "channelId",
				lastActivityDatesJSONObject1.get("channelId"));

			if (lastActivityDatesJSONObject2 == null) {
				continue;
			}

			String lastActivityDate1 = lastActivityDatesJSONObject1.optString(
				"lastActivityDate", null);
			String lastActivityDate2 = lastActivityDatesJSONObject2.optString(
				"lastActivityDate", null);

			if ((lastActivityDate1 != null) && (lastActivityDate2 != null)) {
				Date date1 = DateUtil.toUTCDate(lastActivityDate1);
				Date date2 = DateUtil.toUTCDate(lastActivityDate2);

				if (date1.after(date2)) {
					lastActivityDatesJSONObject1.put(
						"lastActivityDate", lastActivityDate1);
				}
				else {
					lastActivityDatesJSONObject1.putOpt(
						"lastActivityDate", lastActivityDate2);
				}
			}

			JSONUtil.removeValue(
				lastActivityDatesJSONArray2, lastActivityDatesJSONObject2);
		}

		for (int i = 0; i < lastActivityDatesJSONArray2.length(); i++) {
			lastActivityDatesJSONArray1.put(lastActivityDatesJSONArray2.get(i));
		}

		return lastActivityDatesJSONArray1;
	}

	private void _updateActivitiesAndActivityGroups(
			JSONObject individualJSONObject, String oldOwnerId)
		throws Exception {

		JSONArrayIterator.of(
			"activities", _elasticsearchInvoker,
			activityJSONObject -> {
				try {
					_faroInfoActivityDog.updateOwnerId(
						activityJSONObject, individualJSONObject);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termQuery("ownerId", oldOwnerId)
		).iterate();

		_elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("ownerId", oldOwnerId), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.ownerId = params.ownerId",
				Collections.singletonMap(
					"ownerId", individualJSONObject.getString("id"))),
			"activity-groups");
	}

	private void _updateOwnerId(
			String collectionName, String fieldName, String newIndividualId,
			String oldIndividualId)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		JSONArrayIterator.of(
			collectionName, _elasticsearchInvoker,
			jsonObject -> elasticsearchBulkRequestBuilder.update(
				collectionName, jsonObject.put(fieldName, newIndividualId))
		).setQueryBuilder(
			QueryBuilders.termQuery(fieldName, oldIndividualId)
		).iterate();
	}

	private void _updateOwnerIds(String newIndividualId, String oldIndividualId)
		throws Exception {

		_updateOwnerId(
			"engagements", "ownerId", newIndividualId, oldIndividualId);
		_updateOwnerId("fields", "ownerId", newIndividualId, oldIndividualId);
		_updateOwnerId(
			"interests", "ownerId", newIndividualId, oldIndividualId);
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoMembershipDog _faroInfoMembershipDog;

}