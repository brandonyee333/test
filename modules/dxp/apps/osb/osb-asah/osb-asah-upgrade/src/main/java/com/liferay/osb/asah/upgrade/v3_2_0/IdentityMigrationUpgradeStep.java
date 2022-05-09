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

package com.liferay.osb.asah.upgrade.v3_2_0;

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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
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

			WriteStream clientWriteStream =
				bigQueryWriteClient.createWriteStream(
					_buildCreateWriteStreamRequest(datasetName));

			try (JsonStreamWriter jsonStreamWriter =
					JsonStreamWriter.newBuilder(
						clientWriteStream.getName(),
						clientWriteStream.getTableSchema()
					).build()) {

				while (true) {
					JSONArray jsonArray = _getNextBatch(datasetName);

					if (jsonArray.isEmpty()) {
						break;
					}

					ApiFuture<AppendRowsResponse> apiFuture =
						jsonStreamWriter.append(jsonArray);

					ApiFutures.addCallback(
						apiFuture,
						new ApiFutureCallback<AppendRowsResponse>() {

							@Override
							public void onFailure(Throwable throwable) {
								_log.error(throwable, throwable);
							}

							@Override
							public void onSuccess(
								AppendRowsResponse appendRowsResponse) {

								if (_log.isInfoEnabled()) {
									_log.info(
										String.format(
											"%s: %d rows inserted into " +
												"identity table",
											datasetName, jsonArray.length()));
								}
							}

						},
						MoreExecutors.directExecutor());
				}

				FinalizeWriteStreamRequest finalizeWriteStreamRequest =
					FinalizeWriteStreamRequest.newBuilder(
					).setName(
						clientWriteStream.getName()
					).build();

				bigQueryWriteClient.finalizeWriteStream(
					finalizeWriteStreamRequest);
			}
		}
	}

	private CreateWriteStreamRequest _buildCreateWriteStreamRequest(
		String datasetName) {

		CreateWriteStreamRequest.Builder builder =
			CreateWriteStreamRequest.newBuilder();

		return builder.setParent(
			String.valueOf(
				TableName.of(_googleProjectId, datasetName, _TABLE_NAME))
		).setWriteStream(
			WriteStream.newBuilder(
			).setType(
				WriteStream.Type.COMMITTED
			).build()
		).build();
	}

	private JSONArray _getNextBatch(String projectId) {
		SearchSourceBuilder individualsSearchSourceBuilder =
			new SearchSourceBuilder();

		individualsSearchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("emailAddressHashed")
			).filter(
				QueryBuilders.existsQuery("firstEnrichmentDate")
			).filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					_lastIndividualId
				)
			));
		individualsSearchSourceBuilder.size(500);
		individualsSearchSourceBuilder.sort("id");

		SearchResponse individualsSearchResponse =
			_faroInfoElasticsearchInvoker.search(
				"individuals", individualsSearchSourceBuilder);

		SearchHits searchHits = individualsSearchResponse.getHits();

		SearchHit[] hits = searchHits.getHits();

		if (hits.length == 0) {
			return new JSONArray();
		}

		Map<String, JSONObject> identityMap = new HashMap<>();

		for (SearchHit searchHit : hits) {
			JSONObject individualJSONObject = new JSONObject(
				searchHit.getSourceAsString());

			JSONArray dataSourceIndividualPKs =
				individualJSONObject.getJSONArray("dataSourceIndividualPKs");

			String emailAddressHashed = individualJSONObject.getString(
				"emailAddressHashed");
			Date firstEnrichmentDate = DateUtil.toUTCDate(
				individualJSONObject.getString("firstEnrichmentDate"));

			for (int i = 0; i < dataSourceIndividualPKs.length(); i++) {
				JSONObject dataSourceIndividualPK =
					dataSourceIndividualPKs.getJSONObject(i);

				JSONArray individualPKJSONArray =
					dataSourceIndividualPK.getJSONArray("individualPKs");

				for (int j = 0; j < individualPKJSONArray.length(); j++) {
					identityMap.put(
						individualPKJSONArray.getString(j),
						JSONUtil.put(
							"createDate", firstEnrichmentDate
						).put(
							"emailAddressHashed", emailAddressHashed
						));
				}
			}
		}

		SearchHit lastSearchHit = hits[hits.length - 1];

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

		JSONArray jsonArray = new JSONArray();

		for (Terms.Bucket userIdBucket : userIdTerms.getBuckets()) {
			Aggregations dataSourceIdAggregations =
				userIdBucket.getAggregations();

			Terms dataSourceIdTerms = dataSourceIdAggregations.get(
				"dataSourceIds");

			String userId = userIdBucket.getKeyAsString();

			for (Terms.Bucket dataSourceIdBucket :
					dataSourceIdTerms.getBuckets()) {

				Aggregations channelIdAggregations =
					dataSourceIdBucket.getAggregations();

				Terms channelIdTerms = channelIdAggregations.get("channelIds");

				String dataSourceId = dataSourceIdBucket.getKeyAsString();

				for (Terms.Bucket channelIdBucket :
						channelIdTerms.getBuckets()) {

					String channelId = channelIdBucket.getKeyAsString();

					JSONObject identityJSONObject = identityMap.get(userId);

					jsonArray.put(
						JSONUtil.merge(
							identityJSONObject,
							JSONUtil.put(
								"channelId", Long.parseLong(channelId)
							).put(
								"dataSourceId", Long.parseLong(dataSourceId)
							).put(
								"id",
								DigestUtils.sha256Hex(
									String.join(
										"#", projectId, dataSourceId, channelId,
										identityJSONObject.getString(
											"emailAddressHashed")))
							).put(
								"projectId", projectId
							).put(
								"userId", userId
							)));
				}
			}
		}

		return jsonArray;
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_googleProjectId = bigQueryOptions.getProjectId();
	}

	private static final String _TABLE_NAME = "identity";

	private static final Log _log = LogFactory.getLog(
		BaseMigrationUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private String _googleProjectId;
	private String _lastIndividualId = "0";

}