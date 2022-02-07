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
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchIndividualRepositoryImpl
	implements IndividualRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public long
		countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
			Long channelId, @Nullable Date endLastActivityDate,
			@Nullable Date endPreviousActivityDate, List<Long> segmentIds,
			@Nullable Date startLastActivityDate,
			@Nullable Date startPreviousActivityDate) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery(
				"channelIds",
				Collections.singletonList(String.valueOf(channelId)))
		).filter(
			QueryBuilders.termsQuery(
				"individualSegmentIds",
				ListUtil.map(segmentIds, String::valueOf))
		);

		BoolQueryBuilder lastActivityDatesBoolQueryBuilder = null;

		if (!Objects.isNull(endLastActivityDate) &&
			!Objects.isNull(startLastActivityDate)) {

			lastActivityDatesBoolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.nestedQuery(
					"lastActivityDates",
					QueryBuilders.termQuery(
						"lastActivityDates.channelId", channelId),
					ScoreMode.None)
			).filter(
				QueryBuilders.nestedQuery(
					"lastActivityDates",
					QueryBuilders.rangeQuery(
						"lastActivityDates.lastActivityDate"
					).gte(
						DateUtil.toUTCString(startLastActivityDate)
					).lte(
						DateUtil.toUTCString(endLastActivityDate)
					),
					ScoreMode.None)
			);
		}

		BoolQueryBuilder previousActivityDatesBoolQueryBuilder = null;

		if (!Objects.isNull(endPreviousActivityDate) &&
			!Objects.isNull(startPreviousActivityDate)) {

			NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(
				"previousActivityDates",
				QueryBuilders.rangeQuery(
					"previousActivityDates.lastActivityDate"
				).gte(
					DateUtil.toUTCString(startPreviousActivityDate)
				).lte(
					DateUtil.toUTCString(endPreviousActivityDate)
				),
				ScoreMode.None);

			previousActivityDatesBoolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.nestedQuery(
					"previousActivityDates",
					QueryBuilders.termQuery(
						"previousActivityDates.channelId", channelId),
					ScoreMode.None)
			).filter(
				nestedQueryBuilder
			);
		}

		if (!Objects.isNull(lastActivityDatesBoolQueryBuilder) &&
			!Objects.isNull(previousActivityDatesBoolQueryBuilder)) {

			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					lastActivityDatesBoolQueryBuilder
				).should(
					previousActivityDatesBoolQueryBuilder
				));
		}
		else if (!Objects.isNull(lastActivityDatesBoolQueryBuilder)) {
			boolQueryBuilder.filter(lastActivityDatesBoolQueryBuilder);
		}
		else if (!Objects.isNull(previousActivityDatesBoolQueryBuilder)) {
			boolQueryBuilder.filter(previousActivityDatesBoolQueryBuilder);
		}

		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), boolQueryBuilder);
	}

	@Override
	public long countByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (Objects.nonNull(segmentId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId)));
		}

		if (StringUtils.isNotEmpty(query)) {
			boolQueryBuilder.filter(
				QueryBuilders.multiMatchQuery(
					query, fieldNames.toArray(new String[0])));
		}

		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), boolQueryBuilder);
	}

	@Override
	public long countByIdAfter(Long id) {
		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(
			QueryBuilders.rangeQuery(
				"id"
			).gt(
				id
			));
		searchSourceBuilder.size(0);
		searchSourceBuilder.sort(SortBuilders.fieldSort("id"));

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(), searchSourceBuilder);

		return HitsUtil.getTotalHitsCount(searchResponse.getHits());
	}

	@Override
	public long countByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords) {

		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), _buildQueryBuilder(ids, keywords));
	}

	@Override
	public long countIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId) {

		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(),
			_getBoolQueryBuilder(
				channelId, filterHelper.getQueryBuilder(),
				includeAnonymousUsers, segmentChannelId, segmentId));
	}

	@Override
	public long countKnownIndividualsByAnySegmentIds(Long segmentId) {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))
			));
	}

	@Override
	public long countKnownIndividualsByIdIn(List<Long> ids) {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termsQuery(
					"id", SetUtil.map(ids, String::valueOf))
			));
	}

	@Override
	public void delete(Individual individual) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), String.valueOf(individual.getId()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends Individual> individuals) {
		Stream<? extends Individual> stream = StreamSupport.stream(
			individuals.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					Individual::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public void deleteById(Long id) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), id.toString());
	}

	@Override
	public void deleteByIdIn(List<Long> ids) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				Stream.of(
					ids
				).flatMap(
					List::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public boolean existsByChannelIdAndFilterStringAndId(
		@Nullable Long channelId, FilterHelper filterHelper,
		@Nullable Long id) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (filterHelper.getQueryBuilder() != null) {
			boolQueryBuilder.filter(filterHelper.getQueryBuilder());
		}

		if (id != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("id", String.valueOf(id)));
		}

		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(), boolQueryBuilder);
	}

	@Override
	public boolean existsByFilterStringAndId(
		FilterHelper filterHelper, @Nullable Long id) {

		return existsByChannelIdAndFilterStringAndId(null, filterHelper, id);
	}

	@Override
	public boolean existsById(Long id) {
		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(), id.toString());
	}

	@Override
	public List<String> findAccountPKsByChannelIdAndSegmentId(
		Long channelId, Long segmentId) {

		List<String> accountPKs = new ArrayList<>();

		if ((channelId == null) && (segmentId == null)) {
			return accountPKs;
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (channelId != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "channelIds", String.valueOf(channelId));
		}

		if (segmentId != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "individualSegmentIds",
				String.valueOf(segmentId));
		}

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.nested(
						"dataSourceAccountPKs", "dataSourceAccountPKs"
					).subAggregation(
						AggregationBuilders.terms(
							"accountPKs"
						).field(
							"dataSourceAccountPKs.accountPKs"
						).size(
							Integer.MAX_VALUE
						)
					));
				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (_isEmpty(aggregations)) {
			return accountPKs;
		}

		InternalNested internalNested = aggregations.get(
			"dataSourceAccountPKs");

		Aggregations internalAggregations = internalNested.getAggregations();

		Terms terms = internalAggregations.get("accountPKs");

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			accountPKs.add(termsBucket.getKeyAsString());
		}

		return accountPKs;
	}

	@Override
	public List<Individual.ActivitiesCount> findActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
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

		if (_isEmpty(aggregations)) {
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
						jsonObject.getLong("channelId"), null, null, null));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public Iterable<Individual> findAll() {
		return findAll(Sort.by("id"));
	}

	@Override
	public Page<Individual> findAll(Pageable pageable) {
		return PageableExecutionUtils.getPage(
			_toIndividuals(
				_faroInfoElasticsearchInvoker.get(
					_getCollectionName(),
					_getFieldSortBuilders(pageable.getSort()),
					(int)pageable.getOffset(), pageable.getPageSize())),
			pageable, this::count);
	}

	@Override
	public Iterable<Individual> findAll(Sort sort) {
		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(sort)));
	}

	@Override
	public Iterable<Individual> findAllById(Iterable<Long> ids) {
		Stream<Long> stream = StreamSupport.stream(ids.spliterator(), false);

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				QueryBuilders.termsQuery(
					"id",
					stream.map(
						String::valueOf
					).collect(
						Collectors.toList()
					))));
	}

	@Override
	public List<Individual> findAnonymousByCreateDateAndLastActivityDateAndId(
		Date date, @Nullable Long id, int size) {

		String dateString = DateUtil.toUTCString(date);

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), SortBuilderUtil.fieldSort("id"),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"dateCreated"
					).lt(
						dateString
					)
				).filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						id
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
				),
				size));
	}

	@Override
	public Individual findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
		Long associatedId, Long dataSourceId, String fieldName,
		String individualPK) {

		return _toIndividual(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
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
				)));
	}

	@Override
	public List<Individual> findByChannelIdAndFilterStringAndIdIn(
		Long channelId, FilterHelper filterHelper, List<Long> ids) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (filterHelper.getQueryBuilder() != null) {
			boolQueryBuilder.filter(filterHelper.getQueryBuilder());
		}

		if (ids != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery(
					"id", ListUtil.map(ids, String::valueOf)));
		}

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), boolQueryBuilder));
	}

	@Override
	public Individual findByDataSourceIdAndIndividualPK(
		Long dataSourceId, String individualPK) {

		return _toIndividual(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
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
					ScoreMode.None)));
	}

	@Override
	public Individual findByEmailAddress(String emailAddress) {
		return _toIndividual(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
				QueryBuilders.termQuery(
					"demographics.email.value", emailAddress)));
	}

	@Override
	public Individual findByEmailAddressHashed(String emailAddressHashed) {
		return _toIndividual(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
				QueryBuilders.termQuery(
					"emailAddressHashed", emailAddressHashed)));
	}

	@Override
	public List<Individual> findByFieldNamesAndQueryAndSegmentId(
		List<String> fieldNames, @Nullable String query,
		@Nullable Long segmentId, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (Objects.nonNull(segmentId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId)));
		}

		if (StringUtils.isNotEmpty(query)) {
			boolQueryBuilder.filter(
				QueryBuilders.multiMatchQuery(
					query, fieldNames.toArray(new String[0])));
		}

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(pageable.getSort()),
				(int)pageable.getOffset(), boolQueryBuilder,
				pageable.getPageSize()));
	}

	@Override
	public Optional<Individual> findById(Long id) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(), id.toString())
		).map(
			this::_toIndividual
		);
	}

	@Override
	public List<Individual> findByIdAfter(Long id, Pageable pageable) {
		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(pageable.getSort()),
				(int)pageable.getOffset(),
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					id
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Individual> findByIdInAndKeywords(
		List<Long> ids, @Nullable String keywords, Pageable pageable) {

		List<Individual> individuals = new LinkedList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			_buildSearchSourceBuilder(ids, keywords, pageable));

		SearchHits searchHits = searchResponse.getHits();

		for (SearchHit searchHit : searchHits) {
			individuals.add(
				_objectMapper.convertValue(
					searchHit.getSourceAsMap(), Individual.class));
		}

		return individuals;
	}

	@Override
	public List<Individual> findBySegmentIds(Long segmentId) {
		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))));
	}

	@Override
	public List<Long>
		findIdsByAnyChannelIdsAndLastActivityDateAfterAndAnySegmentIds(
			@Nullable Long channelId, @Nullable Date lastActivityDate,
			@Nullable Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		if (!Objects.isNull(channelId)) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "channelIds", String.valueOf(channelId));
		}

		if (!Objects.isNull(lastActivityDate)) {
			boolQueryBuilder.filter(
				QueryBuilders.nestedQuery(
					"lastActivityDates",
					QueryBuilders.rangeQuery(
						"lastActivityDates.lastActivityDate"
					).gt(
						DateUtil.toUTCString(lastActivityDate)
					),
					ScoreMode.None));
		}

		if (!Objects.isNull(segmentId)) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "individualSegmentIds",
				String.valueOf(segmentId));
		}

		return JSONUtil.toLongList(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), new String[] {"id"}, boolQueryBuilder),
			"id");
	}

	@Override
	public List<Long> findIdsByAnySegmentIds(Long segmentId) {
		return JSONUtil.toLongList(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), new String[] {"id"},
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId))),
			"id");
	}

	@Override
	public Map<Long, Long> findIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
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

		if (_isEmpty(aggregations)) {
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
		FilterHelper filterHelper, Long segmentId) {

		List<Long> ids = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("demographics.email")
		).filter(
			QueryBuilders.termsQuery(
				"individualSegmentIds", String.valueOf(segmentId))
		);

		if (StringUtils.isNotEmpty(filterHelper.getFilterString())) {
			boolQueryBuilder.filter(filterHelper.getQueryBuilder());
		}

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
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

		if (_isEmpty(aggregations)) {
			return ids;
		}

		Terms terms = aggregations.get("ids");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			ids.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return ids;
	}

	@Override
	public List<Distribution> getIndividualDistributions(
		String fieldName, String fieldType, FilterHelper filterHelper,
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

		transformationGetResponse.setCollectionName(_getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		if (filterHelper.getQueryBuilder() != null) {
			transformationGetResponse.setQueryBuilder(
				filterHelper.getQueryBuilder());
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
			object -> _objectMapper.convertValue(object, Distribution.class)
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Transformation> getIndividualTransformations(
		String apply, @Nullable Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, @Nullable Long segmentChannelId,
		@Nullable Long segmentId, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(_getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		BoolQueryBuilder boolQueryBuilder = _getBoolQueryBuilder(
			channelId, filterHelper.getQueryBuilder(), includeAnonymousUsers,
			segmentChannelId, segmentId);

		if (boolQueryBuilder != null) {
			transformationGetResponse.setQueryBuilder(boolQueryBuilder);
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
	public void removeSegmentId(Long segmentId) {
		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery(
				"individualSegmentIds", String.valueOf(segmentId)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualSegmentIds.removeIf(segmentId -> " +
					"String.valueOf(segmentId).equals(params.segmentId))",
				Collections.singletonMap(
					"segmentId", String.valueOf(segmentId))),
			_getCollectionName());
	}

	@Override
	public void removeSegmentIdByIdIn(Set<Long> ids, Long segmentId) {
		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termsQuery("id", SetUtil.map(ids, String::valueOf)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualSegmentIds.removeIf(segmentId -> " +
					"String.valueOf(segmentId).equals(params.segmentId))",
				Collections.singletonMap(
					"segmentId", String.valueOf(segmentId))),
			_getCollectionName());
	}

	@Override
	public void removeSegmentIds(List<Long> segmentIds) {
		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termsQuery(
				"individualSegmentIds",
				ListUtil.map(segmentIds, String::valueOf)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualSegmentIds.removeIf(segmentId -> " +
					"params.segmentIds.contains(String.valueOf(segmentId)))",
				Collections.singletonMap(
					"segmentIds", ListUtil.map(segmentIds, String::valueOf))),
			_getCollectionName());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends Individual> S save(S individual) {
		JSONObject jsonObject = _toJSONObject(individual);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)_toIndividual(
			_faroInfoElasticsearchInvoker.add(
				_getCollectionName(), jsonObject));
	}

	@Override
	public <S extends Individual> Iterable<S> saveAll(Iterable<S> individuals) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		individuals.forEach(
			individual -> {
				JSONObject jsonObject = _toJSONObject(individual);

				String id = jsonObject.optString(
					"id", _timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)_toIndividual(jsonObject));
			});

		_faroInfoElasticsearchInvoker.add(_getCollectionName(), jsonArray);

		return list;
	}

	@Override
	public List<Individual> searchIndividuals(
		FilterHelper filterHelper, Pageable pageable) {

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(pageable.getSort()),
				(int)pageable.getOffset(), filterHelper.getQueryBuilder(),
				pageable.getPageSize()));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper,
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

		if (!newSorts.isEmpty()) {
			List<Pair<String, SortOrder>> sortOrderPairs =
				SortBuilderUtil.getSortOrderPairs(
					newSorts.toArray(new String[0]));

			for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
				fieldSortBuilders.add(
					SortBuilderUtil.fieldSort(
						sortOrderPair.getKey(), sortOrderPair.getValue()));
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

					String property = sort.getProperty();

					property = property.replace('/', '.');

					fieldSortBuilders.add(
						SortBuilderUtil.fieldSort(property, sortOrder));
				}
			);
		}

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), fieldSortBuilders,
				(int)pageable.getOffset(),
				_getBoolQueryBuilder(
					channelId, filterHelper.getQueryBuilder(),
					includeAnonymousUsers, segmentChannelId, segmentId),
				pageable.getPageSize()));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, List<Long> ids,
		Boolean includeAnonymousUsers) {

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					_getBoolQueryBuilder(
						channelId, filterHelper.getQueryBuilder(),
						includeAnonymousUsers, null, null)
				).filter(
					QueryBuilders.termsQuery(
						"id", ListUtil.map(ids, String::valueOf))
				)));
	}

	@Override
	public List<Individual> searchIndividuals(
		@Nullable Long channelId, FilterHelper filterHelper, @Nullable Long id,
		Boolean includeAnonymousUsers, int size) {

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), SortBuilderUtil.fieldSort("id"),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						id
					)
				).filter(
					_getBoolQueryBuilder(
						channelId, filterHelper.getQueryBuilder(),
						includeAnonymousUsers, null, null)
				),
				size));
	}

	@Override
	public List<Individual> searchIndividuals(
		Long dataSourceId, Long id, int size) {

		return _toIndividuals(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), SortBuilderUtil.fieldSort("id"),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						id
					)
				).filter(
					QueryBuilders.nestedQuery(
						"dataSourceIndividualPKs",
						QueryBuilders.termQuery(
							"dataSourceIndividualPKs.dataSourceId",
							String.valueOf(dataSourceId)),
						ScoreMode.None)
				),
				size));
	}

	@Override
	public void updateAssociatedIds(String fieldName, Set<Long> ids, Long id) {
		_faroInfoElasticsearchInvoker.update(
			_getCollectionName(), String.valueOf(id),
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				_getScriptSource(fieldName),
				Collections.singletonMap(fieldName, ids)));
	}

	@Override
	public void updateDataSourceNameByDataSourceId(
		Long dataSourceId, String dataSourceName) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"dataSourceIndividualPKs.dataSourceId",
							String.valueOf(dataSourceId))),
					ScoreMode.None)),
			true,
			ScriptUtil.createScript(
				getClass(), "individual_script.painless",
				Collections.singletonMap("dataSourceName", dataSourceName)),
			_getCollectionName());
	}

	private QueryBuilder _buildQueryBuilder(List<Long> ids, String keywords) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery("id", ListUtil.map(ids, String::valueOf)));

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				_getKeywordsQueryBuilder(
					keywords, "demographics.email.value",
					"demographics.familyName.value",
					"demographics.givenName.value"));
		}

		return boolQueryBuilder;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		List<Long> ids, String keywords, Pageable pageable) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.from(pageable.getPageNumber());
		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				_buildQueryBuilder(ids, keywords)
			));
		searchSourceBuilder.size(pageable.getPageSize());
		searchSourceBuilder.sort(
			SortBuilderUtil.fieldSort(
				"demographics.givenName.value", SortOrder.ASC, "keyword"));

		return searchSourceBuilder;
	}

	private BoolQueryBuilder _getBoolQueryBuilder(
		Long channelId, QueryBuilder queryBuilder,
		Boolean includeAnonymousUsers, Long segmentChannelId, Long segmentId) {

		BoolQueryBuilder boolQueryBuilder = null;

		if (queryBuilder == null) {
			boolQueryBuilder = new BoolQueryBuilder();
		}
		else {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(queryBuilder);
		}

		if (segmentChannelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(segmentChannelId)));
		}
		else if (channelId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelIds", String.valueOf(channelId)));
		}

		if (segmentId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", String.valueOf(segmentId)));
		}

		if (BooleanUtils.toBoolean(includeAnonymousUsers)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			QueryBuilders.existsQuery("demographics.email"));
	}

	private String _getCollectionName() {
		return "individuals";
	}

	private Set<Field> _getFields(JSONObject jsonObject, String key) {
		JSONObject demographicsJSONObject = jsonObject.getJSONObject(key);

		Map<String, Object> map = demographicsJSONObject.toMap();

		Collection<Object> values = map.values();

		Stream<Object> stream = values.stream();

		return stream.map(
			value -> {
				List<Object> list = (List<Object>)value;

				return _objectMapper.convertValue(list.get(0), Field.class);
			}
		).collect(
			Collectors.toSet()
		);
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(Sort sort) {
		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		if ((sort == null) || sort.isUnsorted()) {
			fieldSortBuilders.add(SortBuilderUtil.fieldSort("id"));

			return fieldSortBuilders;
		}

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

			fieldName = fieldName.replace('/', '.');

			SortOrder sortOrder = SortOrder.ASC;

			if (order.isDescending()) {
				sortOrder = SortOrder.DESC;
			}

			fieldSortBuilders.add(
				SortBuilderUtil.fieldSort(fieldName, sortOrder));
		}

		return fieldSortBuilders;
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

	private boolean _isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		return aggregationsList.isEmpty();
	}

	private Individual _toIndividual(JSONObject jsonObject) {
		Individual individual = _objectMapper.convertValue(
			jsonObject, Individual.class);

		if (jsonObject == null) {
			return individual;
		}

		Map<Long, Long> activitiesCountsMap = new HashMap<>();

		if (jsonObject.has("activitiesCounts")) {
			JSONArray activitiesCountsJSONArray = jsonObject.getJSONArray(
				"activitiesCounts");

			for (int i = 0; i < activitiesCountsJSONArray.length(); i++) {
				JSONObject activitiesCountJSONObject =
					activitiesCountsJSONArray.getJSONObject(i);

				long activitiesCount = activitiesCountJSONObject.optLong(
					"activitiesCount");
				long channelId = activitiesCountJSONObject.optLong("channelId");

				if ((activitiesCount > 0) && (channelId > 0)) {
					activitiesCountsMap.put(channelId, activitiesCount);
				}
			}
		}

		Map<Long, Date> lastActivityDatesMap = new HashMap<>();

		if (jsonObject.has("lastActivityDates")) {
			JSONArray lastActivityDatesJSONArray = jsonObject.getJSONArray(
				"lastActivityDates");

			_updateActivityDatesMap(
				lastActivityDatesJSONArray, lastActivityDatesMap);
		}

		Map<Long, Date> previousActivityDatesMap = new HashMap<>();

		if (jsonObject.has("previousActivityDates")) {
			JSONArray previousActivityDatesJSONArray = jsonObject.getJSONArray(
				"previousActivityDates");

			_updateActivityDatesMap(
				previousActivityDatesJSONArray, previousActivityDatesMap);
		}

		Map<Long, IndividualChannel> individualChannelsMap = new HashMap<>();

		if (!activitiesCountsMap.isEmpty() || !lastActivityDatesMap.isEmpty() ||
			!previousActivityDatesMap.isEmpty()) {

			for (Map.Entry<Long, Long> entry : activitiesCountsMap.entrySet()) {
				IndividualChannel individualChannel = new IndividualChannel(
					entry.getValue(), entry.getKey(), individual.getId(),
					lastActivityDatesMap.get(entry.getKey()),
					previousActivityDatesMap.get(entry.getKey()));

				lastActivityDatesMap.remove(entry.getKey());
				previousActivityDatesMap.remove(entry.getKey());

				individualChannelsMap.put(entry.getKey(), individualChannel);
			}

			for (Map.Entry<Long, Date> entry :
					lastActivityDatesMap.entrySet()) {

				if (individualChannelsMap.containsKey(entry.getKey())) {
					IndividualChannel individualChannel =
						individualChannelsMap.get(entry.getKey());

					individualChannel.setLastActivityDate(entry.getValue());
					individualChannel.setPreviousActivityDate(
						previousActivityDatesMap.get(entry.getKey()));
				}
				else {
					individualChannelsMap.put(
						entry.getKey(),
						new IndividualChannel(
							0L, entry.getKey(), individual.getId(),
							entry.getValue(),
							previousActivityDatesMap.get(entry.getKey())));
				}

				previousActivityDatesMap.remove(entry.getKey());
			}

			for (Map.Entry<Long, Date> entry :
					previousActivityDatesMap.entrySet()) {

				if (individualChannelsMap.containsKey(entry.getKey())) {
					IndividualChannel individualChannel =
						individualChannelsMap.get(entry.getKey());

					individualChannel.setPreviousActivityDate(
						previousActivityDatesMap.get(entry.getKey()));
				}
				else {
					individualChannelsMap.put(
						entry.getKey(),
						new IndividualChannel(
							0L, entry.getKey(), individual.getId(), null,
							entry.getValue()));
				}
			}
		}

		individual.setIndividualChannels(
			new HashSet<>(individualChannelsMap.values()));

		if (jsonObject.has("dataSourceAccountPKs")) {
			JSONArray dataSourceAccountPKsJSONArray = jsonObject.getJSONArray(
				"dataSourceAccountPKs");

			for (int i = 0; i < dataSourceAccountPKsJSONArray.length(); i++) {
				JSONObject dataSourceAccountPKJSONObject =
					dataSourceAccountPKsJSONArray.getJSONObject(i);

				JSONArray accountPKsJSONArray =
					dataSourceAccountPKJSONObject.getJSONArray("accountPKs");

				individual.addDataSourceIndividual(
					new DataSourceIndividual(
						SetUtil.map(
							accountPKsJSONArray.toList(), String::valueOf),
						dataSourceAccountPKJSONObject.getLong("dataSourceId"),
						individual.getId(), null));
			}
		}

		if (jsonObject.has("dataSourceIndividualPKs")) {
			JSONArray dataSourceIndividualPKsJSONArray =
				jsonObject.getJSONArray("dataSourceIndividualPKs");

			Set<DataSourceIndividual> dataSourceIndividuals =
				individual.getDataSourceIndividuals();

			Stream<DataSourceIndividual> stream =
				dataSourceIndividuals.stream();

			Map<Long, DataSourceIndividual> dataSourceIndividualsMap =
				stream.collect(
					Collectors.toMap(
						DataSourceIndividual::getDataSourceId,
						Function.identity()));

			for (int i = 0; i < dataSourceIndividualPKsJSONArray.length();
				 i++) {

				JSONObject dataSourceIndividualPKJSONObject =
					dataSourceIndividualPKsJSONArray.getJSONObject(i);

				Set<String> individualPKs = null;

				if (dataSourceIndividualPKJSONObject.has("individualPKs")) {
					individualPKs = JSONUtil.toStringSet(
						dataSourceIndividualPKJSONObject.getJSONArray(
							"individualPKs"));
				}

				DataSourceIndividual dataSourceIndividual =
					dataSourceIndividualsMap.get(
						dataSourceIndividualPKJSONObject.getLong(
							"dataSourceId"));

				if (dataSourceIndividual != null) {
					dataSourceIndividual.setIndividualPKs(individualPKs);
				}
				else {
					dataSourceIndividualsMap.put(
						dataSourceIndividualPKJSONObject.getLong(
							"dataSourceId"),
						new DataSourceIndividual(
							null,
							dataSourceIndividualPKJSONObject.getLong(
								"dataSourceId"),
							individual.getId(), individualPKs));
				}
			}

			individual.setDataSourceIndividuals(
				new HashSet<>(dataSourceIndividualsMap.values()));
		}

		if (jsonObject.has("demographics")) {
			individual.setFields(_getFields(jsonObject, "demographics"));
		}

		if (jsonObject.has("custom")) {
			individual.setCustomFields(_getFields(jsonObject, "custom"));
		}

		return individual;
	}

	private List<Individual> _toIndividuals(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toIndividual((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private JSONObject _toJSONObject(Individual individual) {
		JSONObject jsonObject = _objectMapper.convertValue(
			individual, JSONObject.class);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		if (CollectionUtils.isNotEmpty(activitiesCounts)) {
			JSONArray activitiesCountsJSONArray = new JSONArray();

			for (Individual.ActivitiesCount activityCount : activitiesCounts) {
				if ((activityCount != null) &&
					(activityCount.getActivitiesCount() != null) &&
					(activityCount.getChannelId() != null)) {

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
									_objectMapper.writeValueAsString(
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
									_objectMapper.writeValueAsString(
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

		_updateJSONObjectActivityDates(
			"lastActivityDates", individual.getLastActivityDates(), jsonObject);
		_updateJSONObjectActivityDates(
			"previousActivityDates", individual.getPreviousActivityDates(),
			jsonObject);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		_updateIndividualFieldsJsonObject(
			individual.getCustomFields(), "custom", jsonObject);
		_updateIndividualFieldsJsonObject(
			individual.getFields(), "demographics", jsonObject);

		return jsonObject;
	}

	private void _updateActivityDatesMap(
		JSONArray lastActivityDatesJSONArray,
		Map<Long, Date> lastActivityDatesMap) {

		for (int i = 0; i < lastActivityDatesJSONArray.length(); i++) {
			JSONObject lastActivityDatesJSONObject =
				lastActivityDatesJSONArray.optJSONObject(i);

			if (lastActivityDatesJSONObject == null) {
				continue;
			}

			long channelId = lastActivityDatesJSONObject.optLong("channelId");
			String lastActivityDate = lastActivityDatesJSONObject.optString(
				"lastActivityDate", null);

			if ((channelId > 0) && (lastActivityDate != null)) {
				lastActivityDatesMap.put(
					channelId, DateUtil.toUTCDate(lastActivityDate));
			}
		}
	}

	private void _updateIndividualFieldsJsonObject(
		Set<Field> individualFields, String jsonAttributeName,
		JSONObject jsonObject) {

		if (CollectionUtils.isNotEmpty(individualFields)) {
			JSONObject individualFieldJSONObject = new JSONObject();

			for (Field field : individualFields) {
				JSONObject fieldJSONObject = _objectMapper.convertValue(
					field, JSONObject.class);

				fieldJSONObject.remove("id");

				fieldJSONObject.put("ownerId", jsonObject.getString("id"));

				individualFieldJSONObject.put(
					field.getName(), JSONUtil.put(fieldJSONObject));
			}

			jsonObject.put(jsonAttributeName, individualFieldJSONObject);
		}
		else {
			jsonObject.remove(jsonAttributeName);
		}
	}

	private void _updateJSONObjectActivityDates(
		String activityDatesAttributeName,
		Set<Individual.ActivityDate> activityDates, JSONObject jsonObject) {

		if (CollectionUtils.isNotEmpty(activityDates)) {
			JSONArray activityDatesJSONArray = new JSONArray();

			for (Individual.ActivityDate activityDate : activityDates) {
				if ((activityDate != null) &&
					(activityDate.getActivityDate() != null) &&
					(activityDate.getChannelId() != null)) {

					activityDatesJSONArray.put(
						JSONUtil.put(
							"channelId",
							String.valueOf(activityDate.getChannelId())
						).put(
							"lastActivityDate",
							DateUtil.toUTCString(activityDate.getActivityDate())
						));
				}
			}

			jsonObject.put(activityDatesAttributeName, activityDatesJSONArray);
		}
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchIndividualRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}