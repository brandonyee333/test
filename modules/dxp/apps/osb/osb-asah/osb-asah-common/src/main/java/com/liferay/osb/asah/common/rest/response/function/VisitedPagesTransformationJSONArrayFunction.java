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

import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;
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
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Sort;

/**
 * @author Leslie Wong
 */
public class VisitedPagesTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	public VisitedPagesTransformationJSONArrayFunction(
		AsahMarkerDog asahMarkerDog, AssetDog assetDog,
		MembershipDog membershipDog, Long ownerId, String ownerType,
		SegmentDog segmentDog, boolean visitedPages) {

		_asahMarkerDog = asahMarkerDog;
		_assetDog = assetDog;
		_membershipDog = membershipDog;
		_ownerId = ownerId;
		_ownerType = ownerType;
		_segmentDog = segmentDog;
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

		if (Objects.nonNull(_ownerId)) {
			if (StringUtils.isEmpty(_ownerType)) {
				throw new IllegalArgumentException(
					"Owner ID must be accompanied by owner type");
			}

			if (_ownerType.equals("account") ||
				_ownerType.equals("individual-segment")) {

				Long segmentId = _ownerId;

				if (_ownerType.equals("account")) {
					Segment segment = _segmentDog.fetchSegment(
						"Account: " + _ownerId, "INACTIVE");

					if (segment == null) {
						return new JSONArray();
					}

					segmentId = segment.getId();
				}

				List<Long> individualIds =
					_membershipDog.getActiveIndividualIds(segmentId);

				boolQueryBuilder.filter(
					QueryBuilders.termsQuery(
						"ownerId", ListUtil.map(individualIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				);
			}
			else {
				boolQueryBuilder.filter(
					QueryBuilders.termQuery(
						"ownerId", String.valueOf(_ownerId)));
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

		Collection<String> canonicalUrls = _getCanonicalUrls(
			collectionName, elasticsearchInvoker, queryBuilder,
			uniqueVisitsCounts);

		if (sortByVisitCount && _visitedPages) {
			InternalCardinality internalCardinality = aggregations.get(
				"totalElements");

			_totalElements = internalCardinality.getValue();

			Map<String, Asset> assetJSONObjects = new CaseInsensitiveMap(
				_assetDog.getAssets("Page", canonicalUrls));

			for (Map.Entry<Pair<String, String>, Double> entry :
					uniqueVisitsCounts.entrySet()) {

				Pair<String, String> titleUrlPair = entry.getKey();

				Asset asset = assetJSONObjects.get(titleUrlPair.getValue());

				visitedPagesJSONArray.put(
					JSONUtil.put(
						"canonicalUrl", asset.getCanonicalURL()
					).put(
						"dataSourceId", String.valueOf(asset.getDataSourceId())
					).put(
						"title", asset.getTitle()
					).put(
						"uniqueVisitsCount", entry.getValue()
					).put(
						"url", asset.getURL()
					));
			}

			return visitedPagesJSONArray;
		}

		Set<String> canonicalUrlsSet = new HashSet<>();

		List<Asset> assets = _assetDog.getAssets(
			"Page", canonicalUrls, page, size, _getSort(sortOrderPairs));

		for (Asset asset : assets) {
			if (canonicalUrlsSet.contains(asset.getCanonicalURL())) {
				continue;
			}

			double uniqueVisitsCount = 0;

			Pair<String, String> titleUrlPair = Pair.of(
				StringUtils.lowerCase(asset.getTitle()),
				StringUtils.lowerCase(asset.getCanonicalURL()));

			if (uniqueVisitsCounts.containsKey(titleUrlPair)) {
				uniqueVisitsCount = uniqueVisitsCounts.get(titleUrlPair);
			}

			visitedPagesJSONArray.put(
				JSONUtil.put(
					"dataSourceId", String.valueOf(asset.getDataSourceId())
				).put(
					"title", asset.getTitle()
				).put(
					"uniqueVisitsCount", uniqueVisitsCount
				).put(
					"url", asset.getURL()
				));

			canonicalUrlsSet.add(asset.getCanonicalURL());
		}

		_totalElements = _assetDog.getAssetsCount("Page", canonicalUrls);

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

		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"IndividualInterestScoresNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker == null) {
			return null;
		}

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "day",
			asahMarkerContextJSONObject.optString("lastSuccessfulDay", null));

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

	private Collection<String> _getCanonicalUrls(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		QueryBuilder queryBuilder,
		Map<Pair<String, String>, Double> uniqueVisitsCounts) {

		List<String> urls = new ArrayList<>();

		for (Pair<String, String> key : uniqueVisitsCounts.keySet()) {
			urls.add(key.getValue());
		}

		if (_visitedPages) {
			return urls;
		}

		return _getUrls(
			collectionName, elasticsearchInvoker, queryBuilder, urls);
	}

	private Sort _getSort(List<Pair<String, SortOrder>> sortOrderPairs) {
		Stream<Pair<String, SortOrder>> stream = sortOrderPairs.stream();

		return Sort.by(
			stream.filter(
				sortOrderPair -> !StringUtils.equals(
					sortOrderPair.getKey(), "uniqueVisitsCount")
			).map(
				sortOrderPair -> {
					if (sortOrderPair.getValue() == SortOrder.ASC) {
						return Sort.Order.asc(sortOrderPair.getKey());
					}

					return Sort.Order.desc(sortOrderPair.getKey());
				}
			).collect(
				Collectors.toList()
			));
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

	private final AsahMarkerDog _asahMarkerDog;
	private final AssetDog _assetDog;
	private final MembershipDog _membershipDog;
	private final Long _ownerId;
	private final String _ownerType;
	private final SegmentDog _segmentDog;
	private long _totalElements;
	private final boolean _visitedPages;

}