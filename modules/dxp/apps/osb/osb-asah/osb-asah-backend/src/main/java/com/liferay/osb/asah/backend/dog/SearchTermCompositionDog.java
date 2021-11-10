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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SearchTermCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		String channelId, String dataSourceId, int size, int start,
		TimeRange timeRange) {

		List<Composition> compositions = new ArrayList<>();

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"pages",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"searchTerms"
					).field(
						"searchTerm.search"
					).order(
						BucketOrder.compound(
							BucketOrder.count(false), BucketOrder.key(true))
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

				CardinalityAggregationBuilder cardinalityAggregationBuilder =
					AggregationBuilders.cardinality("total");

				cardinalityAggregationBuilder.field("searchTerm.search");

				searchSourceBuilder.aggregation(cardinalityAggregationBuilder);

				BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
					_searchQueryHelper.createRangeQueryBuilder(
						"eventDate", timeRange, _timeZoneDog.getTimeZoneId())
				).filter(
					QueryBuilders.existsQuery("searchTerm")
				).mustNot(
					QueryBuilders.termQuery("searchTerm", "")
				);

				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "channelId", channelId);
				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "dataSourceId", dataSourceId);

				searchSourceBuilder.query(boolQueryBuilder);

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return new CompositionResultBag(compositions, 0, 0);
		}

		Terms terms = aggregations.get("searchTerms");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			compositions.add(
				new Composition(bucket.getDocCount(), bucket.getKeyAsString()));
		}

		InternalCardinality internalCardinality = aggregations.get("total");

		return new CompositionResultBag(
			compositions, internalCardinality.getValue(),
			HitsUtil.getTotalHitsCount(searchResponse.getHits()));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}