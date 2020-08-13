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
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class AcquisitionCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		String acquisitionType, String channelId, String dataSourceId, int size,
		int start, TimeRange timeRange) {

		List<Composition> compositions = new ArrayList<>();

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"user-sessions",
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms(
						"userSessions"
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
					);

				CardinalityAggregationBuilder cardinalityAggregationBuilder =
					AggregationBuilders.cardinality("total");

				BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
					_searchQueryHelper.createRangeQueryBuilder(
						"lastEventDate", timeRange));

				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "channelId", channelId);
				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "dataSourceId", dataSourceId);

				if (acquisitionType.equals("CHANNEL") ||
					acquisitionType.equals("REFERRER")) {

					String fieldName = null;

					if (acquisitionType.equals("CHANNEL")) {
						fieldName = "acquisition.channel";
					}
					else {
						fieldName = "acquisition.source";

						boolQueryBuilder.filter(
							QueryBuilders.termQuery(
								"acquisition.medium", "referral"));
					}

					cardinalityAggregationBuilder.field(fieldName);
					termsAggregationBuilder.field(fieldName);
				}
				else if (acquisitionType.equals("SOURCE_MEDIUM")) {
					Script script = new Script(
						"doc['acquisition.source'] + ' ' + " +
							"doc['acquisition.medium']");

					cardinalityAggregationBuilder.script(script);
					termsAggregationBuilder.script(script);
				}

				searchSourceBuilder.aggregation(cardinalityAggregationBuilder);
				searchSourceBuilder.aggregation(termsAggregationBuilder);
				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return new CompositionResultBag(compositions, 0, 0);
		}

		Terms terms = aggregations.get("userSessions");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			String name = bucket.getKeyAsString();

			if (acquisitionType.equals("SOURCE_MEDIUM")) {
				Matcher matcher = _pattern.matcher(name);

				if (matcher.matches()) {
					String source = matcher.group("source");

					if (StringUtils.isEmpty(source)) {
						source = "(direct)";
					}

					String medium = matcher.group("medium");

					if (StringUtils.isEmpty(medium)) {
						medium = "(none)";
					}

					name = source + " / " + medium;
				}
			}

			compositions.add(new Composition(bucket.getDocCount(), name));
		}

		InternalCardinality internalCardinality = aggregations.get("total");

		return new CompositionResultBag(
			compositions, internalCardinality.getValue(),
			HitsUtil.getTotalHitsCount(searchResponse.getHits()));
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
	}

	private static final Pattern _pattern = Pattern.compile(
		"\\[(?<source>[^]]*)] \\[(?<medium>[^]]*)]");

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}