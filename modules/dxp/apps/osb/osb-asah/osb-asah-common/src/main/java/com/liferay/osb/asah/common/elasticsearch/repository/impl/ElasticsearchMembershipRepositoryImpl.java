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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.repository.MembershipRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchMembershipRepositoryImpl
	extends BaseElasticsearchRepository<Membership>
	implements MembershipRepository {

	@Override
	public long countByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"individualId",
					ListUtil.map(individualIds, String::valueOf))
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentId", String.valueOf(individualSegmentId))
			).filter(
				QueryBuilders.termQuery("status", status)
			));
	}

	@Override
	public long countByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualSegmentId", String.valueOf(individualSegmentId))
			).filter(
				QueryBuilders.termQuery("status", status)
			));
	}

	@Override
	public void deleteByIndividualSegmentId(Long individualSegmentId) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			QueryBuilders.termQuery(
				"individualSegmentId", String.valueOf(individualSegmentId)));
	}

	@Override
	public boolean existsByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status) {

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"individualId", String.valueOf(individualId))
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentId", String.valueOf(individualSegmentId))
			).filter(
				QueryBuilders.termQuery("status", status)
			));
	}

	@Override
	public Membership findByIndividualIdAndIndividualSegmentIdAndStatus(
		Long individualId, Long individualSegmentId, String status) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualId", String.valueOf(individualId))
				).filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId))
				).filter(
					QueryBuilders.termQuery("status", status)
				))
		).map(
			this::toEntity
		).orElse(
			null
		);
	}

	@Override
	public List<Membership> findByIndividualIdAndIndividualSegmentIdInAndStatus(
		Long individualId, List<Long> individualSegmentIds, String status) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualId", String.valueOf(individualId))
				).filter(
					QueryBuilders.termsQuery(
						"individualSegmentId",
						ListUtil.map(individualSegmentIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery("status", status)
				)));
	}

	@Override
	public List<Membership> findByIndividualIdAndStatus(
		Long individualId, String status) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualId", String.valueOf(individualId))
				).filter(
					QueryBuilders.termQuery("status", status)
				)));
	}

	@Override
	public List<Membership> findByIndividualIdInAndIndividualSegmentIdAndStatus(
		List<Long> individualIds, Long individualSegmentId, String status,
		Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termsQuery(
						"individualId",
						ListUtil.map(individualIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId))
				).filter(
					QueryBuilders.termQuery("status", status)
				));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				sb.append(order.getProperty());
				sb.append(",");

				if (order.isAscending()) {
					sb.append("asc");
				}
				else {
					sb.append("desc");
				}

				sorts.add(sb.toString());
			}

			collectionGetResponse.setSorts(sorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public List<Membership> findByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId))
				).filter(
					QueryBuilders.termQuery("status", status)
				)));
	}

	@Override
	public List<Membership> findByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId",
						String.valueOf(individualSegmentId))
				).filter(
					QueryBuilders.termQuery("status", status)
				));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				sb.append(order.getProperty());
				sb.append(",");

				if (order.isAscending()) {
					sb.append("asc");
				}
				else {
					sb.append("desc");
				}

				sorts.add(sb.toString());
			}

			collectionGetResponse.setSorts(sorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public List<Long> findIndividualIdByIndividualSegmentIdAndStatus(
		Long individualSegmentId, String status) {

		List<Long> individualIds = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"individualIds"
					).field(
						"individualId"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"individualSegmentId",
							String.valueOf(individualSegmentId))
					).filter(
						QueryBuilders.termQuery("status", status)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("individualIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			individualIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return individualIds;
	}

	@Override
	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		List<Long> individualSegmentIds, int max, int min, boolean ascending) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"individualIds"
					).field(
						"individualId"
					).minDocCount(
						min
					).order(
						BucketOrder.compound(
							BucketOrder.count(ascending), BucketOrder.key(true))
					).size(
						Integer.MAX_VALUE
					));

				searchSourceBuilder.query(
					QueryBuilders.termsQuery(
						"individualSegmentId",
						ListUtil.map(individualSegmentIds, String::valueOf)));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("individualIds");

		List<Long> individualIds = new ArrayList<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			if (ascending && (bucket.getDocCount() > max)) {
				break;
			}

			individualIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return individualIds;
	}

	@Override
	public List<Long> findIndividualSegmentIdByIndividualIdAndStatus(
		Long individualId, String status) {

		return JSONUtil.toLongList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.fetchSource(
							"individualSegmentId", null);
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"individualId",
									String.valueOf(individualId))
							).filter(
								QueryBuilders.termQuery("status", status)
							));
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort("individualSegmentId"));
					})),
			"individualSegmentId");
	}

	@Override
	public List<Long> findIndividualSegmentIdByIndividualIdInAndStatus(
		List<Long> individualIds, String status) {

		List<Long> individualSegmentIds = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"individualSegmentIds"
					).field(
						"individualSegmentId"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"individualId",
							ListUtil.map(individualIds, String::valueOf))
					).filter(
						QueryBuilders.termQuery("status", status)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("individualSegmentIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			individualSegmentIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return individualSegmentIds;
	}

	@Override
	public List<Long> findTop20IndividualSegmentIdByIndividualId(
		Long individualId) {

		return JSONUtil.toLongList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.fetchSource(
							"individualSegmentId", null);
						searchSourceBuilder.query(
							QueryBuilders.termQuery(
								"individualId", String.valueOf(individualId)));
						searchSourceBuilder.size(20);
					})),
			"individualSegmentId");
	}

	@Override
	protected String getCollectionName() {
		return "memberships";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchMembershipRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}