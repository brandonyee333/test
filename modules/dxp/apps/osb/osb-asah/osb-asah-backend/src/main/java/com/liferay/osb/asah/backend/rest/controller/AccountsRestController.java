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

import com.liferay.osb.asah.backend.dto.AccountDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@RequestMapping("/accounts")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.AccountsRestController"
)
public class AccountsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public AccountDTO getAccountDTO(
		@PathVariable Long id, @RequestParam(required = false) Long channelId) {

		return new AccountDTO(_accountDog.getAccount(id, channelId));
	}

	@GetMapping(params = "!apply")
	public PageDTO<AccountDTO> getAccountDTOsPageDTO(
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<Account> accounts = _accountDog.searchAccountsPage(
			channelId, filterString, page, size, sorts);

		return _toPageDTO(accounts);
	}

	@GetMapping("/distribution")
	public String getAccountsDistribution(
			@RequestParam(required = false) String channelId,
			@RequestParam String fieldMappingId,
			@RequestParam(name = "filter", required = false) String
				filterString,
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
			@RequestParam(name = "filter", required = false) String
				filterString,
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
			@RequestParam(name = "filter", required = false) String
				filterString,
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
			@RequestParam(name = "filter", required = false) String
				filterString,
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

	private List<Long> _getIndividualSegmentIds(Long segmentId) {
		List<Long> individualIds = JSONUtil.toLongList(
			faroInfoElasticsearchInvoker.get(
				"individuals",
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))),
			"id");

		if (individualIds.isEmpty()) {
			return Collections.emptyList();
		}

		return _membershipDog.getActiveIndividualSegmentIds(individualIds);
	}

	private QueryBuilder _getIndividualSegmentsQueryBuilder(
			String accountId, String filterString)
		throws Exception {

		Segment segment = _segmentDog.fetchSegment(
			"Account: " + accountId, "INACTIVE");

		if (segment == null) {
			throw new Exception(
				"Unable to find individual segment associated with account " +
					accountId);
		}

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"id",
			ListUtil.map(
				_getIndividualSegmentIds(segment.getId()), String::valueOf));

		if (StringUtils.isEmpty(filterString)) {
			return queryBuilder;
		}

		return BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			FilterStringToQueryBuilderConverter.convert(filterString)
		);
	}

	private PageDTO<AccountDTO> _toPageDTO(
		AccountDTO accountDTO, Page<Account> accounts) {

		return new PageDTO<>(
			"_embedded", accountDTO, accounts.getNumber(), accounts.getSize(),
			accounts.getTotalElements(), accounts.getTotalPages());
	}

	private PageDTO<AccountDTO> _toPageDTO(Page<Account> accounts) {
		return _toPageDTO(new AccountDTO(accounts.getContent()), accounts);
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}