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

package com.liferay.osb.asah.backend.rest.response;

import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.InternalSum;
import org.elasticsearch.search.aggregations.pipeline.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Leslie Wong
 */
public class VisitedPagesTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	public VisitedPagesTransformationJSONArrayFunction(
		MembershipDog membershipDog, String ownerId, String ownerType,
		boolean visitedPages) {

		_membershipDog = membershipDog;
		_ownerId = ownerId;
		_ownerType = ownerType;
		_visitedPages = visitedPages;
	}

	@Override
	public JSONArray apply(
			String collectionName, String computeFunctionString,
			ElasticsearchInvoker elasticsearchInvoker, int page, int size,
			List<Pair<String, SortOrder>> sortOrderPairs,
			String supportedFieldName, QueryBuilder queryBuilder)
		throws Exception {

		if ((sortOrderPairs == null) || sortOrderPairs.isEmpty()) {
			sortOrderPairs = Collections.singletonList(
				Pair.of("name", SortOrder.ASC));
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (StringUtils.isNotEmpty(_ownerId)) {
			if (StringUtils.isEmpty(_ownerType)) {
				throw new IllegalArgumentException(
					"Owner ID must be accompanied by owner type");
			}

			if (_ownerType.equals("account") ||
				_ownerType.equals("individual-segment")) {

				String individualSegmentId = _ownerId;

				if (_ownerType.equals("account")) {
					JSONObject individualSegmentJSONObject =
						elasticsearchInvoker.fetch(
							"individual-segments",
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"name", "Account: " + _ownerId)
							).filter(
								QueryBuilders.termQuery("status", "INACTIVE")
							));

					if (individualSegmentJSONObject == null) {
						return new JSONArray();
					}

					individualSegmentId = individualSegmentJSONObject.getString(
						"id");
				}

				List<Long> individualIds =
					_membershipDog.getActiveIndividualIds(
						Long.valueOf(individualSegmentId));

				boolQueryBuilder.filter(
					QueryBuilders.termsQuery(
						"ownerId", ListUtil.map(individualIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				);
			}
			else {
				boolQueryBuilder.filter(
					QueryBuilders.termQuery("ownerId", _ownerId));
			}
		}

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		Pair<String, SortOrder> primarySortOrderPair = sortOrderPairs.get(0);

		String primarySortField = primarySortOrderPair.getKey();

		boolean sortByVisitCount = primarySortField.equals("uniqueVisitsCount");

		Aggregations aggregations = _getAggregations(
			boolQueryBuilder, collectionName, elasticsearchInvoker, page,
			primarySortOrderPair, size, sortByVisitCount);

		Map<Pair<String, String>, Double> uniqueVisitsCounts = null;

		if (aggregations != null) {
			uniqueVisitsCounts = _getUniqueVisitsCount(aggregations);
		}
		else {
			if (_visitedPages) {
				return new JSONArray();
			}

			uniqueVisitsCounts = Collections.emptyMap();
		}

		JSONArray visitedPagesJSONArray = new JSONArray();

		QueryBuilder assetQueryBuilder = _getAssetQueryBuilder(
			collectionName, elasticsearchInvoker, queryBuilder,
			uniqueVisitsCounts);

		if (sortByVisitCount && _visitedPages) {
			InternalCardinality internalCardinality = aggregations.get(
				"totalElements");

			_totalElements = internalCardinality.getValue();

			Map<String, JSONObject> assetJSONObjects = new CaseInsensitiveMap(
				JSONUtil.toJSONObjectMap(
					elasticsearchInvoker.get("assets", assetQueryBuilder),
					"canonicalUrl"));

			for (Map.Entry<Pair<String, String>, Double> entry :
					uniqueVisitsCounts.entrySet()) {

				Pair<String, String> titleUrlPair = entry.getKey();

				JSONObject assetJSONObject = assetJSONObjects.get(
					titleUrlPair.getValue());

				visitedPagesJSONArray.put(
					JSONUtil.put(
						"canonicalUrl",
						assetJSONObject.getString("canonicalUrl")
					).put(
						"dataSourceId",
						assetJSONObject.getString("dataSourceId")
					).put(
						"title", assetJSONObject.getString("name")
					).put(
						"uniqueVisitsCount", entry.getValue()
					).put(
						"url", assetJSONObject.getString("url")
					));
			}

			return visitedPagesJSONArray;
		}

		Set<String> canonicalUrls = new HashSet<>();

		List<SortBuilder> sortBuilders = new ArrayList<>();

		for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
			String sortField = sortOrderPair.getKey();

			if (sortField.equals("uniqueVisitsCount")) {
				continue;
			}

			sortBuilders.add(
				SortBuilderUtil.fieldSort(sortField, sortOrderPair.getValue()));
		}

		JSONArray assetsJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"assets",
				searchSourceBuilder -> {
					if (!sortByVisitCount) {
						searchSourceBuilder.from(page * size);
						searchSourceBuilder.size(size);
					}

					for (SortBuilder sortBuilder : sortBuilders) {
						searchSourceBuilder.sort(sortBuilder);
					}

					searchSourceBuilder.query(assetQueryBuilder);
				}));

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			String canonicalUrl = assetJSONObject.getString("canonicalUrl");

			if (canonicalUrls.contains(canonicalUrl)) {
				continue;
			}

			double uniqueVisitsCount = 0;

			String assetTitle = assetJSONObject.getString("name");

			Pair<String, String> titleUrlPair = Pair.of(
				assetTitle.toLowerCase(), canonicalUrl.toLowerCase());

			if (uniqueVisitsCounts.containsKey(titleUrlPair)) {
				uniqueVisitsCount = uniqueVisitsCounts.get(titleUrlPair);
			}

			visitedPagesJSONArray.put(
				JSONUtil.put(
					"dataSourceId", assetJSONObject.getString("dataSourceId")
				).put(
					"title", assetJSONObject.getString("name")
				).put(
					"uniqueVisitsCount", uniqueVisitsCount
				).put(
					"url", assetJSONObject.getString("url")
				));

			canonicalUrls.add(canonicalUrl);
		}

		_totalElements = elasticsearchInvoker.count(
			"assets", assetQueryBuilder);

		return visitedPagesJSONArray;
	}

	@Override
	public long getTotalElements() {
		return _totalElements;
	}

	private Aggregations _getAggregations(
		BoolQueryBuilder boolQueryBuilder, String collectionName,
		ElasticsearchInvoker elasticsearchInvoker, int page,
		Pair<String, SortOrder> primarySortOrderPair, int size,
		boolean sortByVisitCount) {

		JSONObject osbAsahMarkerJSONObject = elasticsearchInvoker.fetch(
			"OSBAsahMarkers", "IndividualInterestScoresNanite");

		if (osbAsahMarkerJSONObject == null) {
			return null;
		}

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "day",
			osbAsahMarkerJSONObject.optString("lastSuccessfulDay", null));

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms("url");

				Script script = new Script(
					"doc['title'] + ' ' + doc['canonicalUrl']");

				termsAggregationBuilder.script(
					script
				).size(
					Integer.MAX_VALUE
				).subAggregation(
					AggregationBuilders.sum(
						"uniqueVisitsCountSum"
					).field(
						"uniqueVisitsCount"
					)
				);

				SortOrder sortOrder = SortOrder.ASC;

				if (sortByVisitCount) {
					sortOrder = primarySortOrderPair.getValue();
				}

				BucketSortPipelineAggregationBuilder
					bucketSortPipelineAggregationBuilder =
						PipelineAggregatorBuilders.bucketSort(
							"uniqueVisitsCountSort",
							Collections.singletonList(
								SortBuilderUtil.fieldSort(
									"uniqueVisitsCountSum", sortOrder)));

				if (sortByVisitCount && _visitedPages) {
					bucketSortPipelineAggregationBuilder.from(
						Math.max(0, page * size));
					bucketSortPipelineAggregationBuilder.size(size);

					searchSourceBuilder.aggregation(
						AggregationBuilders.cardinality(
							"totalElements"
						).script(
							script
						));
				}

				termsAggregationBuilder.subAggregation(
					bucketSortPipelineAggregationBuilder);

				searchSourceBuilder.aggregation(termsAggregationBuilder);

				if (boolQueryBuilder.hasClauses()) {
					searchSourceBuilder.query(boolQueryBuilder);
				}

				searchSourceBuilder.size(0);
			});

		return searchResponse.getAggregations();
	}

	private QueryBuilder _getAssetQueryBuilder(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		QueryBuilder queryBuilder,
		Map<Pair<String, String>, Double> uniqueVisitsCounts) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", "Page"));

		List<String> urls = new ArrayList<>();

		for (Pair<String, String> key : uniqueVisitsCounts.keySet()) {
			urls.add(key.getValue());
		}

		if (_visitedPages) {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery("canonicalUrl", urls));
		}
		else {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery(
					"canonicalUrl",
					_getUrls(
						collectionName, elasticsearchInvoker, queryBuilder,
						urls)));
		}

		return boolQueryBuilder;
	}

	private Map<Pair<String, String>, Double> _getUniqueVisitsCount(
		Aggregations aggregations) {

		Map<Pair<String, String>, Double> uniqueVisitsCounts =
			new LinkedHashMap<>();

		Terms terms = aggregations.get("url");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Matcher matcher = _pattern.matcher(bucket.getKeyAsString());

			if (matcher.matches()) {
				Aggregations uniqueVisitsCountAggregations =
					bucket.getAggregations();

				InternalSum uniqueVisitsCountSum =
					uniqueVisitsCountAggregations.get("uniqueVisitsCountSum");

				uniqueVisitsCounts.put(
					Pair.of(matcher.group("title"), matcher.group("url")),
					uniqueVisitsCountSum.getValue());
			}
		}

		return uniqueVisitsCounts;
	}

	private Set<String> _getUrls(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		QueryBuilder queryBuilder, List<String> visitedUrls) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		if (!visitedUrls.isEmpty()) {
			boolQueryBuilder.mustNot(
				QueryBuilders.termsQuery("url", visitedUrls));
		}

		Set<String> urls = new HashSet<>();

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms(
						"url"
					).field(
						"url"
					).size(
						Integer.MAX_VALUE
					);

				searchSourceBuilder.aggregation(termsAggregationBuilder);

				if (boolQueryBuilder.hasClauses()) {
					searchSourceBuilder.query(boolQueryBuilder);
				}

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("url");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			urls.add(bucket.getKeyAsString());
		}

		return urls;
	}

	private static final Pattern _pattern = Pattern.compile(
		"\\[(?<title>[^]]+)] \\[(?<url>[^]]+)]");

	private final MembershipDog _membershipDog;
	private final String _ownerId;
	private final String _ownerType;
	private long _totalElements;
	private final boolean _visitedPages;

}