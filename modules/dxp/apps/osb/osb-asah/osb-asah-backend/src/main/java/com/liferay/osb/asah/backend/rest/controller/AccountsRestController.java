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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.rest.response.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping("/accounts")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.AccountsRestController"
)
public class AccountsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public String getAccount(
			@PathVariable String id,
			@RequestParam(required = false) String channelId)
		throws Exception {

		if (Objects.isNull(channelId)) {
			return toItemGetResponse("accounts", id);
		}

		JSONObject responseJSONObject = new JSONObject(
			toItemGetResponse("accounts", id));

		JSONObject activitiesCountJSONObject = JSONUtil.find(
			responseJSONObject.optJSONArray("activitiesCounts"), "channelId",
			channelId);

		if (activitiesCountJSONObject != null) {
			responseJSONObject.put(
				"activitiesCount",
				activitiesCountJSONObject.optInt("activitiesCount"));
		}
		else {
			responseJSONObject.put("activitiesCount", 0);
		}

		responseJSONObject.remove("activitiesCounts");

		JSONObject individualCountJSONObject = JSONUtil.find(
			responseJSONObject.optJSONArray("individualCounts"), "channelId",
			channelId);

		if (individualCountJSONObject != null) {
			responseJSONObject.put(
				"individualCount",
				individualCountJSONObject.optInt("individualCount"));
		}
		else {
			responseJSONObject.put("individualCount", 0);
		}

		responseJSONObject.remove("individualCounts");

		return responseJSONObject.toString();
	}

	@GetMapping(params = "!apply")
	public String getAccounts(
			@RequestParam(required = false) String channelId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		if (channelId == null) {
			return toCollectionGetResponse(
				"accounts", null, page,
				FilterStringToQueryBuilderConverter.convert(filterString), size,
				sorts);
		}

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();
		List<String> newSorts = new ArrayList<>();

		if (sorts != null) {
			List<Pair<String, SortOrder>> sortOrderPairs =
				SortBuilderUtil.getSortOrderPairs(sorts);

			for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
				if (StringUtils.equalsIgnoreCase(
						sortOrderPair.getKey(), "activitiesCount")) {

					fieldSortBuilders.add(
						SortBuilderUtil.buildSort(
							"activitiesCounts.activitiesCount",
							"activitiesCounts",
							QueryBuilders.termQuery(
								"activitiesCounts.channelId", channelId),
							sortOrderPair.getValue()));
				}
				else if (StringUtils.equalsIgnoreCase(
							sortOrderPair.getKey(), "individualCount")) {

					fieldSortBuilders.add(
						SortBuilderUtil.buildSort(
							"individualCounts.individualCount",
							"individualCounts",
							QueryBuilders.termQuery(
								"individualCounts.channelId", channelId),
							sortOrderPair.getValue()));
				}
				else {
					SortOrder sortOrder = sortOrderPair.getValue();

					newSorts.add(sortOrderPair.getKey());
					newSorts.add(sortOrder.toString());
				}
			}
		}

		String responseJSON = toCollectionGetResponse(
			"accounts", null, fieldSortBuilders, page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			newSorts.toArray(new String[0]));

		JSONObject responseJSONObject = _filterByChannelId(
			channelId, new JSONObject(responseJSON));

		return responseJSONObject.toString();
	}

	@GetMapping("/distribution")
	public String getAccountsDistribution(
			@RequestParam(required = false) String channelId,
			@RequestParam String fieldMappingId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(required = false) String individualSegmentId,
			@RequestParam(defaultValue = "10") int numberOfBins,
			@RequestParam(defaultValue = "100") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.fetch(
			"field-mappings", fieldMappingId);

		if (fieldMappingJSONObject == null) {
			throw new IllegalArgumentException(
				"Invalid field mapping ID " + fieldMappingId);
		}

		String ownerType = fieldMappingJSONObject.getString("ownerType");

		if (!ownerType.equals("account")) {
			throw new IllegalArgumentException(
				"Unable to use non-account field " +
					fieldMappingJSONObject.getString("fieldName") + " to " +
						"distribute accounts");
		}

		String fieldName =
			"organization." + fieldMappingJSONObject.getString("fieldName") +
				".value";

		String fieldType = fieldMappingJSONObject.getString("fieldType");

		TransformationJSONArrayFunction transformationJSONArrayFunction = null;

		if (fieldType.equals("Number")) {
			size = numberOfBins;

			transformationJSONArrayFunction =
				new NumbersDistributionTransformationJSONArrayFunction();
		}
		else {
			transformationJSONArrayFunction =
				new TermsAggregationTransformationJSONArrayFunction(
					null, fieldName,
					bucket -> JSONUtil.put(
						"count", bucket.getDocCount()
					).put(
						"values", JSONUtil.put(bucket.getKeyAsString())
					));
		}

		return toTransformationGetResponse(
			null, "accounts", faroInfoElasticsearchInvoker, 0,
			_getAccountsQueryBuilder(
				channelId, filterString, individualSegmentId),
			size,
			new HashMap<String, String>() {
				{
					put("count", "_count");
					put("name", "_key");
				}
			},
			sorts, fieldName, transformationJSONArrayFunction,
			"accounts-distribution-transformations");
	}

	@GetMapping(params = "apply")
	public String getAccountTransformations(
			@RequestParam String apply,
			@RequestParam(required = false) String channelId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if (StringUtils.isNotEmpty(channelId)) {
			if (queryBuilder != null) {
				queryBuilder = BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					QueryBuilders.nestedQuery(
						"individualCounts",
						QueryBuilders.termQuery(
							"individualCounts.channelId", channelId),
						ScoreMode.None)
				);
			}
			else {
				queryBuilder = QueryBuilders.nestedQuery(
					"individualCounts",
					QueryBuilders.termQuery(
						"individualCounts.channelId", channelId),
					ScoreMode.None);
			}
		}

		return toTransformationGetResponse(
			"accounts", page, queryBuilder, size, null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"account-transformations");
	}

	@GetMapping(params = "!apply", value = "/{id}/individual-segments")
	public String getIndividualSegments(
			@PathVariable String id,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"individual-segments", null, page,
			_getIndividualSegmentsQueryBuilder(id, filterString), size, sorts);
	}

	@GetMapping(params = "apply", value = "/{id}/individual-segments")
	public String getIndividualSegmentTransformations(
			@PathVariable String id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"individual-segments", page,
			_getIndividualSegmentsQueryBuilder(id, filterString), size, null,
			null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"individual-segment-transformations");
	}

	private JSONObject _filterByChannelId(
		String channelId, JSONObject responseJSONObject) {

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray accountsJSONArray = embeddedJSONObject.getJSONArray(
			"accounts");

		for (int i = 0; i < accountsJSONArray.length(); i++) {
			JSONObject accountJSONObject = accountsJSONArray.getJSONObject(i);

			JSONArray activitiesCountsJSONArray =
				accountJSONObject.optJSONArray("activitiesCounts");

			if (activitiesCountsJSONArray != null) {
				Map<String, JSONObject> activitiesCounts =
					JSONUtil.toJSONObjectMap(
						accountJSONObject.getJSONArray("activitiesCounts"),
						"channelId");

				JSONObject activitiesCountJSONObject =
					activitiesCounts.getOrDefault(
						channelId, JSONUtil.put("activitiesCount", 0));

				accountJSONObject.put(
					"activitiesCount",
					activitiesCountJSONObject.getInt("activitiesCount"));

				accountJSONObject.remove("activitiesCounts");
			}

			JSONArray individualCountsJSONArray =
				accountJSONObject.optJSONArray("individualCounts");

			if (individualCountsJSONArray != null) {
				Map<String, JSONObject> individualCounts =
					JSONUtil.toJSONObjectMap(
						accountJSONObject.getJSONArray("individualCounts"),
						"channelId");

				JSONObject individualCountJSONObject =
					individualCounts.getOrDefault(
						channelId, JSONUtil.put("individualCount", 0));

				accountJSONObject.put(
					"individualCount",
					individualCountJSONObject.getInt("individualCount"));

				accountJSONObject.remove("individualCounts");
			}
		}

		return responseJSONObject;
	}

	private QueryBuilder _getAccountsQueryBuilder(
			String channelId, String filterString, String individualSegmentId)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (StringUtils.isNotEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(filterString));
		}

		if (StringUtils.isEmpty(individualSegmentId) &&
			StringUtils.isEmpty(channelId)) {

			return boolQueryBuilder;
		}

		SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				NestedAggregationBuilder aggregationBuilder =
					AggregationBuilders.nested(
						"accounts", "dataSourceAccountPKs");

				aggregationBuilder.subAggregation(
					AggregationBuilders.terms(
						"accountPKs"
					).field(
						"dataSourceAccountPKs.accountPKs"
					).size(
						Integer.MAX_VALUE
					));

				searchSourceBuilder.aggregation(aggregationBuilder);

				BoolQueryBuilder individualsBoolQueryBuilder =
					QueryBuilders.boolQuery();

				BoolQueryBuilderUtil.filterTerm(
					individualsBoolQueryBuilder, "channelIds", channelId);
				BoolQueryBuilderUtil.filterTerm(
					individualsBoolQueryBuilder, "individualSegmentIds",
					individualSegmentId);

				searchSourceBuilder.query(individualsBoolQueryBuilder);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		InternalNested internalNested = aggregations.get("accounts");

		Aggregations nestedAggregations = internalNested.getAggregations();

		Terms terms = nestedAggregations.get("accountPKs");

		Set<String> accountPKs = new HashSet<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			accountPKs.add(bucket.getKeyAsString());
		}

		boolQueryBuilder.filter(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("accountPK", accountPKs)));

		return boolQueryBuilder;
	}

	private List<String> _getIndividualSegmentIds(
		JSONObject individualSegmentJSONObject) {

		List<String> individualSegmentIds = new ArrayList<>();

		List<String> individualIds = JSONUtil.toStringList(
			faroInfoElasticsearchInvoker.get(
				"individuals",
				QueryBuilders.termQuery(
					"individualSegmentIds",
					individualSegmentJSONObject.getString("id"))),
			"id");

		SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
			"memberships",
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
						QueryBuilders.termsQuery("individualId", individualIds)
					).filter(
						QueryBuilders.termQuery("status", "ACTIVE")
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("individualSegmentIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			individualSegmentIds.add(bucket.getKeyAsString());
		}

		return individualSegmentIds;
	}

	private QueryBuilder _getIndividualSegmentsQueryBuilder(
			String accountId, String filterString)
		throws Exception {

		JSONObject individualSegmentJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"individual-segments",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", "Account: " + accountId)
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));

		if (individualSegmentJSONObject == null) {
			throw new Exception(
				"Unable to find individual segment associated with account " +
					accountId);
		}

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"id", _getIndividualSegmentIds(individualSegmentJSONObject));

		if (StringUtils.isEmpty(filterString)) {
			return queryBuilder;
		}

		return BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			FilterStringToQueryBuilderConverter.convert(filterString)
		);
	}

}