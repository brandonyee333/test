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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.storage.v1.AppendRowsResponse;
import com.google.cloud.bigquery.storage.v1.BigQueryWriteClient;
import com.google.cloud.bigquery.storage.v1.CreateWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.FinalizeWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.JsonStreamWriter;
import com.google.cloud.bigquery.storage.v1.TableName;
import com.google.cloud.bigquery.storage.v1.WriteStream;
import com.google.common.util.concurrent.MoreExecutors;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class IdentityMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		String datasetName = ProjectIdThreadLocal.getProjectId();

		try (BigQueryWriteClient bigQueryWriteClient =
				BigQueryWriteClient.create()) {

			WriteStream identityWriteStream =
				bigQueryWriteClient.createWriteStream(
					_buildCreateWriteStreamRequest(
						datasetName, _IDENTITY_TABLE_NAME));

			WriteStream identityActivityWriteStream =
				bigQueryWriteClient.createWriteStream(
					_buildCreateWriteStreamRequest(
						datasetName, _IDENTITY_ACTIVITY_TABLE_NAME));

			try (JsonStreamWriter identityJsonStreamWriter =
					JsonStreamWriter.newBuilder(
						identityWriteStream.getName(),
						identityWriteStream.getTableSchema()
					).build();
				JsonStreamWriter identityActivityJsonStreamWriter =
					JsonStreamWriter.newBuilder(
						identityActivityWriteStream.getName(),
						identityActivityWriteStream.getTableSchema()
					).build()) {

				while (true) {
					Collection<JSONObject> identityCollection = _getNextBatch(
						datasetName);

					if (identityCollection.isEmpty()) {
						break;
					}

					JSONArray identityJSONArray = _getIdentityActivities(
						identityCollection);

					_addCallBack(
						identityJsonStreamWriter.append(
							new JSONArray(identityCollection)),
						datasetName, identityCollection.size(),
						_IDENTITY_TABLE_NAME);

					if (!identityJSONArray.isEmpty()) {
						_addCallBack(
							identityActivityJsonStreamWriter.append(
								identityJSONArray),
							datasetName, identityJSONArray.length(),
							_IDENTITY_ACTIVITY_TABLE_NAME);
					}
				}
			}

			_finalizeWriteStreamRequest(
				bigQueryWriteClient, identityWriteStream);

			_finalizeWriteStreamRequest(
				bigQueryWriteClient, identityActivityWriteStream);
		}
	}

	private void _addCallBack(
		ApiFuture<AppendRowsResponse> apiFuture, String datasetName,
		long rowCount, String tableName) {

		ApiFutures.addCallback(
			apiFuture,
			new ApiFutureCallback<AppendRowsResponse>() {

				@Override
				public void onFailure(Throwable throwable) {
					_log.error(throwable, throwable);
				}

				@Override
				public void onSuccess(AppendRowsResponse appendRowsResponse) {
					if (_log.isInfoEnabled()) {
						_log.info(
							String.format(
								"%s: %d rows inserted into %s table",
								datasetName, rowCount, tableName));
					}
				}

			},
			MoreExecutors.directExecutor());
	}

	private CreateWriteStreamRequest _buildCreateWriteStreamRequest(
		String datasetName, String tableName) {

		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();
		CreateWriteStreamRequest.Builder builder =
			CreateWriteStreamRequest.newBuilder();

		return builder.setParent(
			String.valueOf(
				TableName.of(
					bigQueryOptions.getProjectId(), datasetName, tableName))
		).setWriteStream(
			WriteStream.newBuilder(
			).setType(
				WriteStream.Type.COMMITTED
			).build()
		).build();
	}

	private void _finalizeWriteStreamRequest(
		BigQueryWriteClient bigQueryWriteClient, WriteStream writeStream) {

		FinalizeWriteStreamRequest finalizeWriteStreamRequest =
			FinalizeWriteStreamRequest.newBuilder(
			).setName(
				writeStream.getName()
			).build();

		bigQueryWriteClient.finalizeWriteStream(finalizeWriteStreamRequest);
	}

	private JSONArray _getIdentityActivities(
		Collection<JSONObject> identities) {

		JSONArray jsonArray = new JSONArray();

		for (JSONObject identityJSONObject : identities) {
			if (identityJSONObject.has("identityActivities")) {
				JSONArray identityActivityJSONArray =
					(JSONArray)identityJSONObject.remove("identityActivities");

				identityActivityJSONArray.forEach(jsonArray::put);
			}
		}

		return jsonArray;
	}

	private Collection<JSONObject> _getNextBatch(String projectId) {
		SearchSourceBuilder individualsSearchSourceBuilder =
			new SearchSourceBuilder();

		individualsSearchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					_lastIndividualId
				)));
		individualsSearchSourceBuilder.size(500);
		individualsSearchSourceBuilder.sort("id");

		SearchResponse individualsSearchResponse =
			_faroInfoElasticsearchInvoker.search(
				"individuals", individualsSearchSourceBuilder);

		SearchHits searchHits = individualsSearchResponse.getHits();

		SearchHit[] searchHitsArray = searchHits.getHits();

		if (searchHitsArray.length == 0) {
			return Collections.emptyList();
		}

		Map<String, JSONObject> identityMap = new HashMap<>();

		for (SearchHit searchHit : searchHitsArray) {
			JSONObject individualJSONObject = new JSONObject(
				searchHit.getSourceAsString());

			JSONArray dataSourceIndividualPKs =
				individualJSONObject.getJSONArray("dataSourceIndividualPKs");

			Date createDate = DateUtil.toUTCDate(
				individualJSONObject.optString(
					"firstEnrichmentDate",
					individualJSONObject.getString("dateCreated")));

			String individualId = individualJSONObject.optString(
				"emailAddressHashed", null);

			for (int i = 0; i < dataSourceIndividualPKs.length(); i++) {
				JSONObject dataSourceIndividualPK =
					dataSourceIndividualPKs.getJSONObject(i);

				JSONArray individualPKJSONArray =
					dataSourceIndividualPK.getJSONArray("individualPKs");

				for (int j = 0; j < individualPKJSONArray.length(); j++) {
					String userId = individualPKJSONArray.getString(j);

					identityMap.put(
						userId,
						JSONUtil.put(
							"createDate", createDate
						).put(
							"individualId", individualId
						).put(
							"projectId", projectId
						).put(
							"userId", userId
						));
				}
			}
		}

		SearchHit lastSearchHit = searchHitsArray[searchHitsArray.length - 1];

		_lastIndividualId = lastSearchHit.getId();

		SearchSourceBuilder userSessionsSearchSourceBuilder =
			new SearchSourceBuilder();

		userSessionsSearchSourceBuilder.aggregation(
			AggregationBuilders.terms(
				"userIds"
			).field(
				"userId"
			).size(
				Integer.MAX_VALUE
			).subAggregation(
				AggregationBuilders.terms(
					"dataSourceIds"
				).field(
					"dataSourceId"
				).size(
					Integer.MAX_VALUE
				).subAggregation(
					AggregationBuilders.terms(
						"channelIds"
					).field(
						"channelId"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						AggregationBuilders.terms(
							"firstEventDates"
						).field(
							"firstEventDate"
						).size(
							Integer.MAX_VALUE
						)
					)
				)
			));

		userSessionsSearchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("userId", identityMap.keySet())));
		userSessionsSearchSourceBuilder.size(0);

		SearchResponse userSessionsSearchResponse =
			_cerebroInfoElasticsearchInvoker.search(
				"user-sessions", userSessionsSearchSourceBuilder);

		Aggregations userIdAggregations =
			userSessionsSearchResponse.getAggregations();

		Terms userIdTerms = userIdAggregations.get("userIds");

		for (Terms.Bucket userIdBucket : userIdTerms.getBuckets()) {
			String userId = userIdBucket.getKeyAsString();

			JSONObject identityJSONObject = identityMap.get(userId);

			if (!identityJSONObject.has("individualId")) {
				continue;
			}

			String individualId = identityJSONObject.getString("individualId");

			Aggregations dataSourceIdAggregations =
				userIdBucket.getAggregations();

			Terms dataSourceIdTerms = dataSourceIdAggregations.get(
				"dataSourceIds");

			List<JSONObject> identityActivities = new ArrayList<>();

			for (Terms.Bucket dataSourceIdBucket :
					dataSourceIdTerms.getBuckets()) {

				Aggregations channelIdAggregations =
					dataSourceIdBucket.getAggregations();

				Terms channelIdTerms = channelIdAggregations.get("channelIds");

				String dataSourceId = dataSourceIdBucket.getKeyAsString();

				for (Terms.Bucket channelIdBucket :
						channelIdTerms.getBuckets()) {

					String channelId = channelIdBucket.getKeyAsString();

					Aggregations firstEventDateAggregations =
						channelIdBucket.getAggregations();

					Terms firstEventDateTerms = firstEventDateAggregations.get(
						"firstEventDates");

					for (Terms.Bucket firstEventDateBucket :
							firstEventDateTerms.getBuckets()) {

						identityActivities.add(
							JSONUtil.put(
								"channelId", Long.parseLong(channelId)
							).put(
								"createDate",
								DateUtil.toUTCDate(
									firstEventDateBucket.getKeyAsString())
							).put(
								"dataSourceId", Long.parseLong(dataSourceId)
							).put(
								"id",
								String.join(
									"#", projectId, userId, dataSourceId,
									channelId)
							).put(
								"identityId", userId
							).put(
								"individualId", individualId
							));
					}
				}
			}

			identityJSONObject.put("identityActivities", identityActivities);
		}

		return identityMap.values();
	}

	private static final String _IDENTITY_ACTIVITY_TABLE_NAME =
		"identityactivity";

	private static final String _IDENTITY_TABLE_NAME = "identity";

	private static final Log _log = LogFactory.getLog(
		IdentityMigrationUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private String _lastIndividualId = "0";

}