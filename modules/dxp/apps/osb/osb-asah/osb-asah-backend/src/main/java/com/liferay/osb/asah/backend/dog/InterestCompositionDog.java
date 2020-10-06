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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class InterestCompositionDog {

	public CompositionResultBag getAccountCompositionResultBag(
		String accountId, boolean active, String channelId, String keywords,
		int size, Sort sort, int start) {

		JSONObject individualSegmentJSONObject =
			_faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", "Account: " + accountId)
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));

		return getIndividualSegmentCompositionResultBag(
			active, channelId, individualSegmentJSONObject.getString("id"),
			keywords, size, sort, start);
	}

	public CompositionResultBag getIndividualCompositionResultBag(
		String channelId, String keywords, int size, Sort sort, int start) {

		List<String> individualIds = _getIndividualIds(false, channelId, null);

		return _getCompositionResultBag(
			_getInterestQueryBuilder(individualIds, keywords), size, sort,
			start, individualIds.size());
	}

	public CompositionResultBag getIndividualSegmentCompositionResultBag(
		boolean active, String channelId, String individualSegmentId,
		String keywords, int size, Sort sort, int start) {

		List<String> individualIds = _getIndividualIds(
			active, channelId, individualSegmentId);

		if (individualIds.isEmpty()) {
			return new CompositionResultBag(Collections.emptyList(), 0, 0);
		}

		return _getCompositionResultBag(
			_getInterestQueryBuilder(individualIds, keywords), size, sort,
			start, individualIds.size());
	}

	private boolean _calculateMaxCount(Sort sort, int start) {
		if (!StringUtils.equals(sort.getColumn(), "count") ||
			StringUtils.equals(sort.getType(), "ASC") || (start != 0)) {

			return true;
		}

		return false;
	}

	private BucketOrder _getBucketOrder(Sort sort) {
		boolean asc = true;

		if (StringUtils.equals(sort.getType(), "DESC")) {
			asc = false;
		}

		if (StringUtils.equals(sort.getColumn(), "count")) {
			return BucketOrder.compound(
				BucketOrder.count(asc), BucketOrder.key(true));
		}

		return BucketOrder.compound(
			BucketOrder.key(asc), BucketOrder.count(false));
	}

	private CompositionResultBag _getCompositionResultBag(
		QueryBuilder queryBuilder, int size, Sort sort, int start,
		long totalCount) {

		List<Composition> compositions = new ArrayList<>();

		boolean calculateMax = _calculateMaxCount(sort, start);

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"interests",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"userInterests"
					).field(
						"name"
					).order(
						_getBucketOrder(sort)
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						PipelineAggregatorBuilders.bucketSort(
							"paginate", null
						).from(
							start
						).size(
							size
						)
					));

				if (calculateMax) {
					searchSourceBuilder.aggregation(
						AggregationBuilders.terms(
							"maxCount"
						).field(
							"name"
						).order(
							BucketOrder.count(false)
						).size(
							Integer.MAX_VALUE
						).subAggregation(
							PipelineAggregatorBuilders.bucketSort(
								"paginate", null
							).from(
								0
							).size(
								1
							)
						));
				}

				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"total"
					).field(
						"name"
					));
				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return new CompositionResultBag(compositions, 0, 0);
		}

		Terms terms = aggregations.get("userInterests");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			compositions.add(
				new Composition(bucket.getDocCount(), bucket.getKeyAsString()));
		}

		InternalCardinality internalCardinality = aggregations.get("total");

		if (calculateMax) {
			long maxCount = 0;

			Terms maxCountTerms = aggregations.get("maxCount");

			List<? extends Terms.Bucket> buckets = maxCountTerms.getBuckets();

			if (!buckets.isEmpty()) {
				Terms.Bucket bucket = buckets.get(0);

				maxCount = bucket.getDocCount();
			}

			return new CompositionResultBag(
				maxCount, compositions, internalCardinality.getValue(),
				totalCount);
		}

		return new CompositionResultBag(
			compositions, internalCardinality.getValue(), totalCount);
	}

	private List<String> _getIndividualIds(
		boolean active, String channelId, String individualSegmentId) {

		List<String> individualIds = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelIds", channelId);

		if (active) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"engagementScore"
				).gt(
					0
				));
		}

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "individualSegmentIds", individualSegmentId);

		try {
			JSONArrayIterator.of(
				"individuals", _faroInfoElasticsearchInvoker,
				individualJSONObject -> {
					individualIds.add(individualJSONObject.getString("id"));

					return null;
				}
			).setQueryBuilder(
				boolQueryBuilder
			).iterate();
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to get individuals", e);
		}

		return individualIds;
	}

	private QueryBuilder _getInterestQueryBuilder(
		List<String> individualIds, String keywords) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerType", "individual"));

		BoolQueryBuilderUtil.filterTerms(
			boolQueryBuilder, "ownerId", individualIds);

		JSONObject osbAsahMarkerJSONObject =
			_faroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", "IndividualInterestScoresNanite");

		if (osbAsahMarkerJSONObject != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "dateRecorded",
				osbAsahMarkerJSONObject.optString("lastSuccessfulDay", null));
		}

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "name",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"name", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		return _getInterestThresholdQueryBuilder(boolQueryBuilder);
	}

	private QueryBuilder _getInterestThresholdQueryBuilder(
		QueryBuilder queryBuilder) {

		JSONObject osbAsahMarkerJSONObject =
			_faroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", "InterestThresholdScoreNanite");

		if ((osbAsahMarkerJSONObject == null) ||
			!osbAsahMarkerJSONObject.has("score")) {

			return queryBuilder;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				"score"
			).gte(
				osbAsahMarkerJSONObject.getDouble("score")
			));

		if (queryBuilder == null) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(queryBuilder);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}