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
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ArrayUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchAccountRepositoryImpl
	extends BaseElasticsearchRepository<Account, Long>
	implements AccountRepository {

	@Override
	public long countAccounts(
		@Nullable Set<String> accountPKs, @Nullable String filterString) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _getQueryBuilder(accountPKs, filterString));
	}

	@Override
	public List<Account> findAllByDataSourceId(Long dataSourceId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))));
	}

	@Override
	public Optional<Account> findByAccountPK(String accountPK) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				QueryBuilders.termQuery("accountPK", accountPK))
		).map(
			this::toEntity
		);
	}

	@Override
	public Optional<Account> findByAccountPKAndDataSourceId(
		String accountPK, Long dataSourceId) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("accountPK", accountPK)
				).filter(
					QueryBuilders.termQuery("dataSourceId", dataSourceId)
				))
		).map(
			this::toEntity
		);
	}

	@Override
	public List<Distribution> getAccountDistributions(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable String filterString, @Nullable Long individualSegmentId,
		Pageable pageable) {

		fieldName = "organization." + fieldName + ".value";

		TransformationJSONArrayFunction transformationJSONArrayFunction = null;

		if (fieldType.equals("Number")) {
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

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = _getAccountsQueryBuilder(
			channelId, filterString, individualSegmentId);

		if (queryBuilder != null) {
			transformationGetResponse.setQueryBuilder(queryBuilder);
		}

		transformationGetResponse.setSize(pageable.getPageSize());
		transformationGetResponse.setSorts(
			new HashMap<String, String>() {
				{
					put("count", "_count");
					put("name", "_key");
				}
			},
			_getSorts(pageable.getSort()));
		transformationGetResponse.setSupportedFieldName(fieldName);
		transformationGetResponse.setTransformationJSONArrayFunction(
			transformationJSONArrayFunction);
		transformationGetResponse.setTransformationName(
			"accounts-distribution-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray accountsDistributionTransformationsJSONArray =
			embeddedJSONObject.getJSONArray(
				"accounts-distribution-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			accountsDistributionTransformationsJSONArray);

		return stream.map(
			object -> objectMapper.convertValue(object, Distribution.class)
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Transformation> getAccountTransformations(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if ((queryBuilder != null) && (channelId != null)) {
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
		else if (channelId != null) {
			queryBuilder = QueryBuilders.nestedQuery(
				"individualCounts",
				QueryBuilders.termQuery(
					"individualCounts.channelId", channelId),
				ScoreMode.None);
		}

		if (queryBuilder != null) {
			transformationGetResponse.setQueryBuilder(queryBuilder);
		}

		transformationGetResponse.setSize(pageable.getPageSize());
		transformationGetResponse.setSorts(
			new HashMap<String, String>() {
				{
					put("terms", "_key");
					put("totalElements", "_count");
				}
			},
			_getSorts(pageable.getSort()));
		transformationGetResponse.setTransformationJSONArrayFunction(
			new TermsAggregationTransformationJSONArrayFunction(apply, null));
		transformationGetResponse.setTransformationName(
			"account-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray accountTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("account-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			accountTransformationsJSONArray);

		return stream.map(
			object -> {
				JSONObject curJSONObject = (JSONObject)object;

				return new Transformation(
					JSONUtil.toMap(curJSONObject.getJSONObject("terms")),
					curJSONObject.getInt("totalElements"));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public <S extends Account> S save(S account) {
		JSONObject jsonObject = toJSONObject(account);

		Set<Account.AccountActivityCount> activitiesCounts =
			account.getActivitiesCounts();

		if (CollectionUtils.isNotEmpty(activitiesCounts)) {
			JSONArray activitiesCountsJSONArray = new JSONArray();

			for (Account.AccountActivityCount activityCount :
					activitiesCounts) {

				activitiesCountsJSONArray.put(
					JSONUtil.put(
						"activitiesCount", activityCount.getActivitiesCount()
					).put(
						"channelId", activityCount.getChannelId()
					));
			}

			jsonObject.put("activitiesCounts", activitiesCountsJSONArray);
		}

		Set<Account.AccountIndividualCount> individualCounts =
			account.getIndividualCounts();

		if (CollectionUtils.isNotEmpty(individualCounts)) {
			JSONArray individualsCountsJSONArray = new JSONArray();

			for (Account.AccountIndividualCount individualCount :
					individualCounts) {

				individualsCountsJSONArray.put(
					JSONUtil.put(
						"channelId", individualCount.getChannelId()
					).put(
						"individualCount", individualCount.getIndividualCount()
					));
			}

			jsonObject.put("individualCounts", individualsCountsJSONArray);
		}

		Set<Field> fields = account.getFields();

		if (CollectionUtils.isNotEmpty(fields)) {
			JSONObject organizationJSONObject = new JSONObject();

			for (Field field : fields) {
				organizationJSONObject.put(
					field.getName(),
					JSONUtil.put(
						objectMapper.convertValue(field, JSONObject.class)));
			}

			jsonObject.put("organization", organizationJSONObject);
		}

		if ((account.getId() != null) &&
			_faroInfoElasticsearchInvoker.exists(
				getCollectionName(), String.valueOf(account.getId()))) {

			jsonObject = _faroInfoElasticsearchInvoker.update(
				getCollectionName(), jsonObject);
		}
		else {
			jsonObject = _faroInfoElasticsearchInvoker.add(
				getCollectionName(), jsonObject);
		}

		return (S)toEntity(jsonObject);
	}

	@Override
	public List<Account> searchAccounts(
		@Nullable Set<String> accountPKs, @Nullable Long channelId,
		@Nullable String filterString, Pageable pageable,
		@Nullable Sort segmentSort) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);

			QueryBuilder queryBuilder = _getQueryBuilder(
				accountPKs, filterString);

			if (queryBuilder != null) {
				collectionGetResponse.setQueryBuilder(queryBuilder);
			}

			if (pageable.isPaged()) {
				collectionGetResponse.setPage(pageable.getPageNumber());
				collectionGetResponse.setSize(pageable.getPageSize());
			}

			String[] sorts = _getSorts(pageable.getSort());

			collectionGetResponse.setSorts(sorts);

			if (channelId == null) {
				JSONObject jsonObject = new JSONObject(
					collectionGetResponse.respond());

				JSONObject embeddedJSONObject = jsonObject.getJSONObject(
					"_embedded");

				return toList(
					embeddedJSONObject.getJSONArray(getCollectionName()));
			}

			List<String> newSorts = new ArrayList<>();

			if (ArrayUtil.isNotEmpty(sorts)) {
				List<Pair<String, SortOrder>> sortOrderPairs =
					SortBuilderUtil.getSortOrderPairs(sorts);

				for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
					if (Objects.equals("id", sortOrderPair.getKey())) {
						continue;
					}

					SortOrder sortOrder = sortOrderPair.getValue();

					newSorts.add(sortOrderPair.getKey());
					newSorts.add(sortOrder.toString());
				}
			}

			List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

			String[] segmentSorts = _getSorts(segmentSort);

			if (ArrayUtil.isNotEmpty(segmentSorts)) {
				List<Pair<String, SortOrder>> sortOrderPairs =
					SortBuilderUtil.getSortOrderPairs(segmentSorts);

				for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
					if (Objects.equals("id", sortOrderPair.getKey())) {
						continue;
					}

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

			collectionGetResponse.setFieldSortBuilders(fieldSortBuilders);
			collectionGetResponse.setSorts(newSorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject responseJSONObject = _filterByChannelId(
				String.valueOf(channelId), jsonObject);

			JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
				"_embedded");

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	protected String getCollectionName() {
		return "accounts";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
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
		Long channelId, String filterString, Long individualSegmentId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (StringUtils.isNotEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(filterString));
		}

		if ((channelId == null) && (individualSegmentId == null)) {
			return boolQueryBuilder;
		}

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
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

				if (channelId != null) {
					BoolQueryBuilderUtil.filterTerm(
						individualsBoolQueryBuilder, "channelIds",
						String.valueOf(channelId));
				}

				if (individualSegmentId != null) {
					BoolQueryBuilderUtil.filterTerm(
						individualsBoolQueryBuilder, "individualSegmentIds",
						String.valueOf(individualSegmentId));
				}

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
			QueryBuilders.termsQuery("accountPK", accountPKs));

		return boolQueryBuilder;
	}

	private QueryBuilder _getQueryBuilder(
		Set<String> accountPKs, String filterString) {

		if ((accountPKs == null) || accountPKs.isEmpty()) {
			return FilterStringToQueryBuilderConverter.convert(filterString);
		}

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"accountPK", accountPKs);

		if (StringUtils.isEmpty(filterString)) {
			return queryBuilder;
		}

		return BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			FilterStringToQueryBuilderConverter.convert(filterString)
		);
	}

	private String[] _getSorts(Sort sort) {
		if (sort == null) {
			return null;
		}

		List<String> sorts = new LinkedList<>();

		for (Sort.Order order : sort) {
			sorts.add(order.getProperty());

			if (order.isAscending()) {
				sorts.add("asc");
			}
			else {
				sorts.add("desc");
			}
		}

		return sorts.toArray(new String[0]);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchAccountRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}