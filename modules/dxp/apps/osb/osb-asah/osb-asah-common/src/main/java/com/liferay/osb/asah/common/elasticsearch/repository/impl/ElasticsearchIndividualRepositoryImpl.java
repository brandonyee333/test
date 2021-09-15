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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchIndividualRepositoryImpl
	extends BaseElasticsearchRepository<Individual, Long>
	implements IndividualRepository {

	@Override
	public long countByIdAfter(Long individualId) {
		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(
			QueryBuilders.rangeQuery(
				"id"
			).gt(
				individualId
			));
		searchSourceBuilder.size(0);
		searchSourceBuilder.sort(SortBuilders.fieldSort("id"));

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(), searchSourceBuilder);

		return HitsUtil.getTotalHitsCount(searchResponse.getHits());
	}

	@Override
	public long countByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(individualIds, keywords));
	}

	public long countByQueryAndSegmentId(
		@Nullable String query, @Nullable Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (Objects.nonNull(segmentId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId)));
		}

		if (StringUtils.isNotEmpty(query)) {
			String[] individualDemographicsSearchableFieldNames =
				_getIndividualDemographicsSearchableFieldNames();

			if (individualDemographicsSearchableFieldNames != null) {
				boolQueryBuilder.filter(
					QueryBuilders.multiMatchQuery(
						query, individualDemographicsSearchableFieldNames));
			}
		}

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public long countIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getQueryBuilder(
				channelId, filterString, includeAnonymousUsers,
				segmentChannelId, segmentId));
	}

	@Override
	public long countKnownIndividuals(List<Long> ids) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termsQuery(
					"id", SetUtil.map(ids, String::valueOf))
			));
	}

	@Override
	public long countKnownIndividuals(Long segmentId) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))
			));
	}

	@Override
	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, @Nullable String filterString,
		@Nullable Long individualId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (!StringUtils.isEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(
					filterString,
					_faroInfoIndividualsFilterStringConverterHelper));
		}

		if (individualId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("id", String.valueOf(individualId)));
		}

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public boolean
		existsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId(
			@Nullable Long channelId, @Nullable String filterString,
			Boolean includeAnonymousUsers, @Nullable Long individualId) {

		QueryBuilder queryBuilder = _getQueryBuilder(
			channelId, filterString, includeAnonymousUsers, null, null);

		if (individualId != null) {
			queryBuilder = BoolQueryBuilderUtil.filter(
				queryBuilder
			).filter(
				QueryBuilders.termQuery("id", String.valueOf(individualId))
			);
		}

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(), queryBuilder);
	}

	@Override
	public boolean existsByFilterStringAndId(
		@Nullable String filterString, @Nullable Long individualId) {

		return existsByChannelIdAndFilterStringAndId(
			null, filterString, individualId);
	}

	@Override
	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.nested(
						"activitiesCounts", "activitiesCounts"
					).subAggregation(
						AggregationBuilders.terms(
							"channelId"
						).field(
							"activitiesCounts.channelId"
						).size(
							Integer.MAX_VALUE
						).subAggregation(
							AggregationBuilders.sum(
								"activitiesCount"
							).field(
								"activitiesCounts.activitiesCount"
							)
						)
					));
				searchSourceBuilder.query(
					_getQueryBuilder(includeAnonymousUsers, segmentId));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return Collections.emptyList();
		}

		JSONArray jsonArray = new JSONArray();

		InternalNested internalNested = aggregations.get("activitiesCounts");

		Aggregations internalAggregations = internalNested.getAggregations();

		Terms terms = internalAggregations.get("channelId");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			Aggregations bucketAggregations = termsBucket.getAggregations();

			Sum sum = bucketAggregations.get("activitiesCount");

			int activitiesCount = 0;

			if (sum != null) {
				activitiesCount = (int)sum.getValue();
			}

			jsonArray.put(
				JSONUtil.put(
					"activitiesCount", activitiesCount
				).put(
					"channelId", termsBucket.getKeyAsString()
				));
		}

		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				return new Individual.ActivitiesCount(
					new IndividualChannel(
						jsonObject.getLong("activitiesCount"),
						jsonObject.getLong("channelId"), null, null));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Individual> findAnonymousByCreateDateAndLastActivityDate(
		String dateString, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"dateCreated"
					).lt(
						dateString
					)
				).mustNot(
					QueryBuilders.nestedQuery(
						"lastActivityDates",
						QueryBuilders.rangeQuery(
							"lastActivityDates.lastActivityDate"
						).gt(
							dateString
						),
						ScoreMode.None)
				).mustNot(
					QueryBuilders.existsQuery("demographics.email")
				)));
	}

	@Override
	public List<Individual> findByAnySegmentIds(Long segmentId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))));
	}

	@Override
	public Optional<Individual>
		findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
			Long associatedId, Long dataSourceId, String fieldName,
			String individualPK) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					BoolQueryBuilderUtil.filter(
						QueryBuilders.nestedQuery(
							"dataSourceIndividualPKs",
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.dataSourceId",
									String.valueOf(dataSourceId))
							).filter(
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.individualPKs",
									individualPK)
							),
							ScoreMode.None)
					).mustNot(
						QueryBuilders.termQuery(fieldName, associatedId)
					))));
	}

	@Override
	public List<Individual> findByChannelIdAndSegmentId(
		@Nullable Long channelId, @Nullable Long segmentId) {

		BoolQueryBuilder individualsBoolQueryBuilder =
			QueryBuilders.boolQuery();

		if (channelId != null) {
			BoolQueryBuilderUtil.filterTerm(
				individualsBoolQueryBuilder, "channelIds",
				String.valueOf(channelId));
		}

		if (segmentId != null) {
			BoolQueryBuilderUtil.filterTerm(
				individualsBoolQueryBuilder, "individualSegmentIds",
				String.valueOf(segmentId));
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), individualsBoolQueryBuilder));
	}

	@Override
	public List<Individual> findByDataSourceId(
		Long dataSourceId, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.nestedQuery(
								"dataSourceIndividualPKs",
								QueryBuilders.termQuery(
									"dataSourceIndividualPKs.dataSourceId",
									String.valueOf(dataSourceId)),
								ScoreMode.None));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public Optional<Individual> findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.nestedQuery(
						"dataSourceIndividualPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"dataSourceIndividualPKs.dataSourceId",
								String.valueOf(dataSourceId))
						).filter(
							QueryBuilders.termsQuery(
								"dataSourceIndividualPKs.individualPKs",
								individualPK)
						),
						ScoreMode.None))));
	}

	@Override
	public Optional<Individual> findByEmailAddress(String emailAddress) {
		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.termQuery(
						"demographics.email.value", emailAddress))));
	}

	@Override
	public Optional<Individual> findByEmailAddressHashed(
		String emailAddressHashed) {

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					QueryBuilders.termQuery(
						"emailAddressHashed", emailAddressHashed))));
	}

	@Override
	public Optional<Individual> findByEmailAddressOrEmailAddressHashed(
		@Nullable String emailAddress, @Nullable String emailAddressHashed) {

		if (StringUtils.isBlank(emailAddress)) {
			return findByEmailAddressHashed(emailAddressHashed);
		}

		return Optional.ofNullable(
			toEntity(
				_faroInfoElasticsearchInvoker.fetch(
					getCollectionName(),
					BoolQueryBuilderUtil.should(
						QueryBuilders.termQuery(
							"demographics.email.value", emailAddress)
					).should(
						QueryBuilders.termQuery(
							"emailAddressHashed",
							DigestUtils.sha256Hex(emailAddress))
					))));
	}

	@Override
	public List<Individual> findByIdAfter(
		Long individualId, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.rangeQuery(
								"id"
							).gt(
								individualId
							));
						searchSourceBuilder.sort(SortBuilders.fieldSort("id"));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Individual> findByIdsInAndKeywords(
		List<Long> individualIds, @Nullable String keywords,
		Pageable pageable) {

		List<Individual> individuals = new LinkedList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			_buildSearchSourceBuilder(individualIds, keywords, pageable));

		SearchHits searchHits = searchResponse.getHits();

		for (SearchHit searchHit : searchHits) {
			individuals.add(
				_objectMapper.convertValue(
					searchHit.getSourceAsMap(), Individual.class));
		}

		return individuals;
	}

	@Override
	public List<Individual> findByQueryAndSegmentId(
		@Nullable String query, @Nullable Long segmentId, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (Objects.nonNull(segmentId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId)));
		}

		if (StringUtils.isNotEmpty(query)) {
			String[] individualDemographicsSearchableFieldNames =
				_getIndividualDemographicsSearchableFieldNames();

			if (individualDemographicsSearchableFieldNames != null) {
				boolQueryBuilder.filter(
					QueryBuilders.multiMatchQuery(
						query, individualDemographicsSearchableFieldNames));
			}
		}

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.from(
							pageable.getPageNumber() * pageable.getPageSize());
						searchSourceBuilder.query(boolQueryBuilder);
						searchSourceBuilder.size(pageable.getPageSize());
					})));
	}

	@Override
	public List<Long> findIdsByAnySegmentIds(Long segmentId) {
		return JSONUtil.toLongList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))),
			"id");
	}

	@Override
	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"channelIds"
					).field(
						"channelIds"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					_getQueryBuilder(includeAnonymousUsers, segmentId));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return Collections.emptyMap();
		}

		Map<Long, Long> individualCounts = new HashMap<>();

		Terms terms = aggregations.get("channelIds");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			individualCounts.put(
				Long.valueOf(termsBucket.getKeyAsString()),
				termsBucket.getDocCount());
		}

		return individualCounts;
	}

	@Override
	public List<Long> findKnownIndividualIds(
		@Nullable String filterString, Long segmentId) {

		List<Long> individualIds = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("demographics.email")
		).filter(
			QueryBuilders.termsQuery(
				"individualSegmentIds", String.valueOf(segmentId))
		);

		if (StringUtils.isNotEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(filterString));
		}

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"ids"
					).field(
						"id"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("ids");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			individualIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return individualIds;
	}

	@Override
	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, @Nullable String filterString,
		Pageable pageable) {

		fieldName = "demographics." + fieldName + ".value";

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

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoIndividualsFilterStringConverterHelper);

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
			"individuals-distribution-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray individualsDistributionTransformationsJSONArray =
			embeddedJSONObject.getJSONArray(
				"individuals-distribution-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			individualsDistributionTransformationsJSONArray);

		return stream.map(
			object -> objectMapper.convertValue(object, Distribution.class)
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = _getQueryBuilder(
			channelId, filterString, includeAnonymousUsers, segmentChannelId,
			segmentId);

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
			"individual-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray individualTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("individual-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			individualTransformationsJSONArray);

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
	public <S extends Individual> S save(S individual) {
		JSONObject jsonObject = toJSONObject(individual);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		if (CollectionUtils.isNotEmpty(activitiesCounts)) {
			JSONArray activitiesCountsJSONArray = new JSONArray();

			for (Individual.ActivitiesCount activityCount : activitiesCounts) {
				if (!Objects.isNull(activityCount.getActivitiesCount())) {
					activitiesCountsJSONArray.put(
						JSONUtil.put(
							"activitiesCount",
							activityCount.getActivitiesCount()
						).put(
							"channelId",
							String.valueOf(activityCount.getChannelId())
						));
				}
			}

			jsonObject.put("activitiesCounts", activitiesCountsJSONArray);
		}

		Set<Long> channelIds = individual.getChannelIds();

		if (CollectionUtils.isNotEmpty(channelIds)) {
			try {
				jsonObject.put(
					"channelIds",
					JSONUtil.toJSONArray(
						new ArrayList<>(channelIds), String::valueOf));
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
			individual.getDataSourceAccountPKs();

		if (CollectionUtils.isNotEmpty(dataSourceAccountPKs)) {
			JSONArray dataSourceAccountPKsJSONArray = new JSONArray();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				if (CollectionUtils.isNotEmpty(
						dataSourceAccountPK.getAccountPKs())) {

					try {
						dataSourceAccountPKsJSONArray.put(
							JSONUtil.put(
								"accountPKs",
								new JSONArray(
									objectMapper.writeValueAsString(
										dataSourceAccountPK.getAccountPKs()))
							).put(
								"dataSourceId",
								String.valueOf(
									dataSourceAccountPK.getDataSourceId())
							));
					}
					catch (JsonProcessingException jsonProcessingException) {
						throw new RuntimeException(jsonProcessingException);
					}
				}
			}

			jsonObject.put(
				"dataSourceAccountPKs", dataSourceAccountPKsJSONArray);
		}

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		if (CollectionUtils.isNotEmpty(dataSourceIndividualPKs)) {
			JSONArray dataSourceIndividualPKsJSONArray = new JSONArray();

			for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
					dataSourceIndividualPKs) {

				if (CollectionUtils.isNotEmpty(
						dataSourceIndividualPK.getIndividualPKs())) {

					try {
						dataSourceIndividualPKsJSONArray.put(
							JSONUtil.put(
								"dataSourceId",
								String.valueOf(
									dataSourceIndividualPK.getDataSourceId())
							).put(
								"individualPKs",
								new JSONArray(
									objectMapper.writeValueAsString(
										dataSourceIndividualPK.
											getIndividualPKs()))
							));
					}
					catch (JsonProcessingException jsonProcessingException) {
						throw new RuntimeException(jsonProcessingException);
					}
				}
			}

			jsonObject.put(
				"dataSourceIndividualPKs", dataSourceIndividualPKsJSONArray);
		}

		Set<Individual.LastActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		if (CollectionUtils.isNotEmpty(lastActivityDates)) {
			JSONArray lastActivityDatesJSONArray = new JSONArray();

			for (Individual.LastActivityDate lastActivityDate :
					lastActivityDates) {

				if (!Objects.isNull(lastActivityDate.getLastActivityDate())) {
					lastActivityDatesJSONArray.put(
						JSONUtil.put(
							"channelId",
							String.valueOf(lastActivityDate.getChannelId())
						).put(
							"lastActivityDate",
							DateUtil.toUTCString(
								lastActivityDate.getLastActivityDate())
						));
				}
			}

			jsonObject.put("lastActivityDates", lastActivityDatesJSONArray);
		}

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		jsonObject = _faroInfoElasticsearchInvoker.upsert(
			getCollectionName(), jsonObject);

		Set<Field> customFields = individual.getCustomFields();

		if (CollectionUtils.isNotEmpty(customFields)) {
			JSONObject customJSONObject = new JSONObject();

			for (Field customField : customFields) {
				JSONObject customFieldJSONObject = objectMapper.convertValue(
					customField, JSONObject.class);

				customFieldJSONObject.remove("id");

				customFieldJSONObject.put(
					"ownerId", jsonObject.getString("id"));

				customJSONObject.put(
					customField.getName(), JSONUtil.put(customFieldJSONObject));
			}

			jsonObject.put("custom", customJSONObject);
		}
		else {
			jsonObject.remove("custom");
		}

		Set<Field> fields = individual.getFields();

		if (CollectionUtils.isNotEmpty(fields)) {
			JSONObject demographicsJSONObject = new JSONObject();

			for (Field field : fields) {
				JSONObject fieldJSONObject = objectMapper.convertValue(
					field, JSONObject.class);

				fieldJSONObject.remove("id");

				fieldJSONObject.put("ownerId", jsonObject.getString("id"));

				demographicsJSONObject.put(
					field.getName(), JSONUtil.put(fieldJSONObject));
			}

			jsonObject.put("demographics", demographicsJSONObject);
		}
		else {
			jsonObject.remove("demographics");
		}

		return (S)toEntity(
			_faroInfoElasticsearchInvoker.replace(
				getCollectionName(), jsonObject));
	}

	@Override
	public <S extends Individual> Iterable<S> saveAll(Iterable<S> entities) {
		Stream<S> stream = StreamSupport.stream(entities.spliterator(), false);

		return stream.map(
			this::save
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();
		List<String> newSorts = new ArrayList<>();

		String[] sorts = _getSorts(pageable.getSort());

		if ((channelId != null) && (sorts != null)) {
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
							sortOrderPair.getKey(), "lastActivityDate")) {

					fieldSortBuilders.add(
						SortBuilderUtil.buildSort(
							"lastActivityDates.lastActivityDate",
							"lastActivityDates",
							QueryBuilders.termQuery(
								"lastActivityDates.channelId", channelId),
							sortOrderPair.getValue()));
				}
				else {
					SortOrder sortOrder = sortOrderPair.getValue();

					newSorts.add(sortOrderPair.getKey());
					newSorts.add(sortOrder.toString());
				}
			}
		}

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_getQueryBuilder(
								channelId, filterString, includeAnonymousUsers,
								segmentChannelId, segmentId));

						searchSourceBuilder.from(
							pageable.getPageNumber() * pageable.getPageSize());
						searchSourceBuilder.size(pageable.getPageSize());

						for (FieldSortBuilder fieldSortBuilder :
								fieldSortBuilders) {

							searchSourceBuilder.sort(fieldSortBuilder);
						}

						if (!newSorts.isEmpty()) {
							List<Pair<String, SortOrder>> sortOrderPairs =
								SortBuilderUtil.getSortOrderPairs(
									newSorts.toArray(new String[0]));

							for (Pair<String, SortOrder> sortOrderPair :
									sortOrderPairs) {

								searchSourceBuilder.sort(
									SortBuilderUtil.fieldSort(
										sortOrderPair.getKey(),
										sortOrderPair.getValue()));
							}
						}
						else {
							Stream.of(
								pageable.getSort()
							).flatMap(
								Sort::stream
							).forEach(
								sort -> {
									SortOrder sortOrder = SortOrder.ASC;

									if (sort.isDescending()) {
										sortOrder = SortOrder.DESC;
									}

									searchSourceBuilder.sort(
										SortBuilderUtil.fieldSort(
											sort.getProperty(), sortOrder));
								}
							);
						}
					})));
	}

	@Override
	public List<Individual> searchIndividuals(
		String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							FilterStringToQueryBuilderConverter.convert(
								filterString,
								_faroInfoIndividualsFilterStringConverterHelper));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public void updateAssociatedIds(
		String fieldName, Set<Long> ids, Long individualId) {

		_faroInfoElasticsearchInvoker.update(
			getCollectionName(), String.valueOf(individualId),
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_getScriptSource(fieldName),
				Collections.singletonMap(fieldName, ids)));
	}

	@Override
	protected String getCollectionName() {
		return "individuals";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@Override
	protected Individual toEntity(JSONObject jsonObject) {
		Individual individual = _objectMapper.convertValue(
			jsonObject, Individual.class);

		if ((jsonObject == null) || !jsonObject.has("demographics")) {
			return individual;
		}

		JSONObject demographicsJSONObject = jsonObject.getJSONObject(
			"demographics");

		Map<String, Object> map = demographicsJSONObject.toMap();

		Collection<Object> values = map.values();

		Stream<Object> stream = values.stream();

		individual.setFields(
			stream.map(
				value -> {
					List<Object> list = (List<Object>)value;

					return _objectMapper.convertValue(list.get(0), Field.class);
				}
			).collect(
				Collectors.toSet()
			));

		return individual;
	}

	private QueryBuilder _buildQueryBuilder(
		List<Long> individualIds, String keywords) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("demographics.email")
		).filter(
			QueryBuilders.termsQuery("id", individualIds)
		);

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				_getKeywordsQueryBuilder(
					keywords, "demographics.email.value",
					"demographics.familyName.value",
					"demographics.givenName.value"));
		}

		return boolQueryBuilder;
	}

	private QueryBuilder _buildQueryBuilder(String context, String ownerType) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (!StringUtils.isBlank(context)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("context", context));
		}

		if (!StringUtils.isBlank(context)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("ownerType", ownerType));
		}

		return boolQueryBuilder;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		List<Long> individualIds, String keywords, Pageable pageable) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.from(pageable.getPageNumber());
		searchSourceBuilder.query(_buildQueryBuilder(individualIds, keywords));
		searchSourceBuilder.size(pageable.getPageSize());
		searchSourceBuilder.sort(
			SortBuilderUtil.fieldSort(
				"demographics.givenName.value", SortOrder.ASC, "keyword"));

		return searchSourceBuilder;
	}

	private String[] _getIndividualDemographicsSearchableFieldNames() {
		JSONArray fieldMappingsJSONArray = new JSONArray(
			_faroInfoElasticsearchInvoker.get(
				"field-mappings",
				searchSourceBuilder -> {
					searchSourceBuilder.from(0);
					searchSourceBuilder.query(
						_buildQueryBuilder("demographics", "individual"));
					searchSourceBuilder.size(20);
				}));

		if (fieldMappingsJSONArray.length() == 0) {
			return null;
		}

		List<String> searchableFieldNames = new ArrayList<>();

		for (int i = 0; i < fieldMappingsJSONArray.length(); i++) {
			JSONObject fieldMappingJSONObject =
				fieldMappingsJSONArray.getJSONObject(i);

			if (Objects.equals(
					fieldMappingJSONObject.getString("fieldType"), "Text")) {

				searchableFieldNames.add(
					String.format(
						"demographics.%s.value.searchable",
						fieldMappingJSONObject.getString("fieldName")));
			}
		}

		return searchableFieldNames.toArray(new String[0]);
	}

	private QueryBuilder _getKeywordsQueryBuilder(
		String keywords, String... fieldNames) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (String fieldName : fieldNames) {
			boolQueryBuilder.should(
				QueryBuilders.queryStringQuery(
					String.format(
						"%s:*%s*", fieldName,
						QueryUtil.escapeKeywords(keywords)))
			).should(
				QueryBuilders.matchQuery(
					fieldName, keywords
				).fuzziness(
					Fuzziness.AUTO
				)
			);
		}

		return boolQueryBuilder;
	}

	private QueryBuilder _getQueryBuilder(
		boolean includeAnonymousUsers, Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentIds", String.valueOf(segmentId)));

		if (!includeAnonymousUsers) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("demographics.email"));
		}

		return boolQueryBuilder;
	}

	private QueryBuilder _getQueryBuilder(
		Long channelId, String filterString, Boolean includeAnonymousUsers,
		Long segmentChannelId, Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = null;

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoIndividualsFilterStringConverterHelper);

		if (queryBuilder == null) {
			boolQueryBuilder = new BoolQueryBuilder();
		}
		else {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(queryBuilder);
		}

		if (segmentChannelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelIds", segmentChannelId));
		}
		else if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelIds", channelId));
		}

		if (segmentId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("individualSegmentIds", segmentId));
		}

		if (BooleanUtils.toBoolean(includeAnonymousUsers)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			QueryBuilders.existsQuery("demographics.email"));
	}

	private String _getScriptSource(String fieldName) {
		StringBuilder sb = new StringBuilder();

		sb.append("ctx._source.");
		sb.append(fieldName);
		sb.append(" = params.");
		sb.append(fieldName);
		sb.append(";");

		return sb.toString();
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
		ElasticsearchIndividualRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}