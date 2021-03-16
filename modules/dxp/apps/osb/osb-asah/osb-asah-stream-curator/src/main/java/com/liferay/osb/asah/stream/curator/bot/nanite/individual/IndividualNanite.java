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

package com.liferay.osb.asah.stream.curator.bot.nanite.individual;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoSuppressionDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import io.prometheus.client.Counter;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class IndividualNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individuals";
	}

	@Override
	public long getInterval() {
		return DateUtil.SECOND;
	}

	@PostConstruct
	public void init() {
		String[] collections = JSONUtil.toStringArray(
			_elasticsearchIndexManager.getCollectionsJSONArray(
				WeDeployDataService.OSB_ASAH_CEREBRO_INFO));

		_collections = ArrayUtils.remove(
			collections, ArrayUtils.indexOf(collections, "user-sessions"));
	}

	@Override
	public void run() {
		List<String> messages = _messageSubscriber.pullMessages(50);

		if (messages.isEmpty()) {
			return;
		}

		for (String message : messages) {
			try {
				JSONObject messageJSONObject = new JSONObject(message);

				ProjectIdThreadLocal.setProjectId(
					messageJSONObject.getString("projectId"));

				if (!_faroInfoSuppressionDog.isSuppressed(
						null,
						messageJSONObject.getString("emailAddressHashed"))) {

					Long channelId = JSONUtil.optLong(
						null, messageJSONObject, "channelId");

					JSONObject individualJSONObject = _updateIndividual(
						messageJSONObject.getJSONObject("analyticsData"),
						channelId, messageJSONObject.getLong("dataSourceId"),
						messageJSONObject.getString("emailAddressHashed"),
						messageJSONObject.getString("userId"));

					_updatePagesAndAssets(
						channelId, messageJSONObject.getLong("dataSourceId"),
						individualJSONObject,
						messageJSONObject.getString("userId"));

					_updateUserSessions(
						messageJSONObject.getLong("dataSourceId"),
						individualJSONObject.getString("id"),
						messageJSONObject.getString("userId"));
				}

				_identityMessagesCount.inc();
			}
			catch (Exception e) {
				_log.error(e.getMessage(), e);
			}
			finally {
				ProjectIdThreadLocal.remove();
			}
		}
	}

	private List<String> _fetchIndividualSegmentNames(
		Long channelId, String individualId) {

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals", individualId);

		if (individualJSONObject == null) {
			return Collections.emptyList();
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery(
				"id",
				JSONUtil.toStringSet(
					individualJSONObject.getJSONArray("individualSegmentIds")))
		).filter(
			QueryBuilders.termQuery("status", "ACTIVE")
		);

		if (Objects.nonNull(channelId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelId", String.valueOf(channelId)));
		}

		return JSONUtil.toStringList(
			_faroInfoElasticsearchInvoker.get(
				"individual-segments", boolQueryBuilder),
			"name");
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

	private void _mergeIndividual(
			JSONObject analyticsDataJSONObject,
			JSONObject anonymousIndividualJSONObject, Long dataSourceId,
			JSONObject knownIndividualJSONObject, String userId)
		throws Exception {

		_faroInfoIndividualDog.addDataSourceIndividualPK(
			userId, dataSourceId, "LIFERAY", knownIndividualJSONObject);

		String dateString = DateUtil.newDateString();

		knownIndividualJSONObject.put(
			"activitiesCounts",
			_mergeActivitiesCounts(
				anonymousIndividualJSONObject.optJSONArray("activitiesCounts"),
				knownIndividualJSONObject.optJSONArray("activitiesCounts"))
		).put(
			"analyticsData", analyticsDataJSONObject
		).put(
			"dateModified", dateString
		).put(
			"lastEnrichmentDate", dateString
		);

		_faroInfoIndividualDog.updateIndividual(
			knownIndividualJSONObject.getString("id"),
			knownIndividualJSONObject, false);

		_faroInfoIndividualDog.deleteIndividual(
			new Date(), anonymousIndividualJSONObject.getString("id"));
	}

	private void _updateActivitiesAndActivityGroups(
			JSONObject individualJSONObject, String userId)
		throws Exception {

		JSONArrayIterator.of(
			"activities", _faroInfoElasticsearchInvoker,
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
			QueryBuilders.termQuery("userId", userId)
		).iterate();

		_activityGroupDog.updateActivityGroup(
			individualJSONObject.getLong("id"), userId);
	}

	private JSONObject _updateIndividual(
			JSONObject analyticsDataJSONObject, Long channelId,
			Long dataSourceId, String emailAddressHashed, String userId)
		throws Exception {

		JSONObject individualJSONObject1 =
			_faroInfoIndividualDog.getIndividualJSONObject(
				dataSourceId, userId);

		if ((individualJSONObject1 != null) &&
			Objects.equals(
				individualJSONObject1.optString("emailAddressHashed"),
				emailAddressHashed)) {

			return individualJSONObject1;
		}

		JSONObject individualJSONObject2 = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("emailAddressHashed", emailAddressHashed));

		if ((individualJSONObject1 != null) &&
			(individualJSONObject2 != null)) {

			_mergeIndividual(
				analyticsDataJSONObject, individualJSONObject1, dataSourceId,
				individualJSONObject2, userId);
		}
		else if ((individualJSONObject1 == null) &&
				 (individualJSONObject2 == null)) {

			DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

			if (Objects.isNull(channelId)) {
				channelId = dataSource.getChannelId();
			}

			return _faroInfoIndividualDog.addIndividual(
				analyticsDataJSONObject, channelId, dataSource,
				emailAddressHashed, userId);
		}
		else {
			DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

			if (individualJSONObject1 == null) {
				individualJSONObject1 = individualJSONObject2;
			}

			_faroInfoIndividualDog.addDataSourceIndividualPK(
				userId, dataSource.getId(), dataSource.getProviderType(),
				individualJSONObject1);

			individualJSONObject2 = _updateIndividual(
				analyticsDataJSONObject, emailAddressHashed,
				individualJSONObject1);
		}

		_updateActivitiesAndActivityGroups(individualJSONObject2, userId);

		return individualJSONObject2;
	}

	private JSONObject _updateIndividual(
		JSONObject analyticsDataJSONObject, String emailAddressHashed,
		JSONObject individualJSONObject) {

		String dateString = DateUtil.newDateString();

		return _faroInfoIndividualDog.updateIndividual(
			individualJSONObject.getString("id"),
			individualJSONObject.put(
				"analyticsData", analyticsDataJSONObject
			).put(
				"dateModified", dateString
			).put(
				"emailAddressHashed", emailAddressHashed
			),
			false);
	}

	private void _updatePagesAndAssets(
		Long channelId, Long dataSourceId, JSONObject individualJSONObject,
		String userId) {

		StringBuilder sb = new StringBuilder();

		sb.append("ctx._source.individualId = params.individualId;");

		Map<String, Object> params = new HashMap<>();

		String individualId = individualJSONObject.getString("id");

		params.put("individualId", individualId);

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if ((demographicsJSONObject != null) &&
			demographicsJSONObject.has("email")) {

			sb.append("ctx._source.knownIndividual = true;");
		}

		List<String> segmentNames = _fetchIndividualSegmentNames(
			channelId, individualId);

		if (!segmentNames.isEmpty()) {
			sb.append("ctx._source.segmentNames = params.segmentNames;");

			params.put("segmentNames", segmentNames);
		}

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("userId", userId)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				BoolQueryBuilderUtil.shouldNot(
					QueryBuilders.existsQuery("knownIndividual")
				).should(
					QueryBuilders.termQuery("knownIndividual", false)
				)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				sb.toString(), params),
			_collections);
	}

	private void _updateUserSessions(
		Long dataSourceId, String individualId, String userId) {

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.individualId = params.individualId;",
			Collections.singletonMap("individualId", individualId));

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("userId", userId)
			).mustNot(
				QueryBuilders.termQuery("individualId", individualId)
			),
			true, script, "user-sessions");
	}

	private static final Log _log = LogFactory.getLog(IndividualNanite.class);

	private static final Counter _identityMessagesCount =
		PrometheusUtil.counter(
			"stream_curator_identity_messages_count",
			"The number of identity messages processed");

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private String[] _collections;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoSuppressionDog _faroInfoSuppressionDog;

	@MessageSubscriber.Autowired(channel = Channel.IDENTITY_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}