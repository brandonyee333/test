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

package com.liferay.osb.asah.common.rest.response.function;

import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Matthew Kong
 */
public class ActivitiesAssetTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	public ActivitiesAssetTransformationJSONArrayFunction(
		AssetDog assetDog, DataSourceDog dataSourceDog) {

		_assetDog = assetDog;
		_dataSourceDog = dataSourceDog;
	}

	@Override
	public JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker, int page, int size,
		List<Pair<String, SortOrder>> sortOrderPairs, String supportedFieldName,
		QueryBuilder queryBuilder) {

		JSONArray jsonArray = new JSONArray();

		if (size == 0) {
			return jsonArray;
		}

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms(
						"assets"
					).field(
						"object.id"
					).order(
						_getBucketOrder(sortOrderPairs)
					).size(
						(page + 1) * size
					).subAggregation(
						PipelineAggregatorBuilders.bucketSort(
							"paginate", null
						).from(
							Math.max(0, page * size)
						).size(
							size
						)
					);

				searchSourceBuilder.aggregation(termsAggregationBuilder);

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);

				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"totalElements"
					).field(
						"object.id"
					));
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("assets");

		Set<Long> assetIds = new HashSet();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			assetIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		List<Asset> assets = _assetDog.getAssets(assetIds);

		Stream<Asset> stream = assets.stream();

		Map<String, Asset> assetsMap = stream.collect(
			Collectors.toMap(
				asset -> String.valueOf(asset.getId()), Function.identity()));

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Asset asset = assetsMap.get(bucket.getKeyAsString());

			DataSource dataSource = _dataSourceDog.fetchDataSource(
				asset.getDataSourceId());

			if (dataSource == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find data source with ID " +
							asset.getDataSourceId());
				}

				continue;
			}

			jsonArray.put(
				JSONUtil.put(
					"count", bucket.getDocCount()
				).put(
					"dataSourceAssetPK", asset.getDataSourceAssetPK()
				).put(
					"dataSourceName", dataSource.getName()
				).put(
					"id", String.valueOf(asset.getId())
				).put(
					"name", asset.getTitle()
				));
		}

		InternalCardinality internalCardinality = aggregations.get(
			"totalElements");

		_totalElements = internalCardinality.getValue();

		return jsonArray;
	}

	@Override
	public long getTotalElements() {
		return _totalElements;
	}

	private BucketOrder _getBucketOrder(
		List<Pair<String, SortOrder>> sortOrderPairs) {

		for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
			if (Objects.equals(sortOrderPair.getKey(), "count")) {
				return BucketOrder.count(
					Objects.equals(sortOrderPair.getValue(), SortOrder.ASC));
			}
		}

		return BucketOrder.count(false);
	}

	private static final Log _log = LogFactory.getLog(
		ActivitiesAssetTransformationJSONArrayFunction.class);

	private final AssetDog _assetDog;
	private final DataSourceDog _dataSourceDog;
	private long _totalElements;

}