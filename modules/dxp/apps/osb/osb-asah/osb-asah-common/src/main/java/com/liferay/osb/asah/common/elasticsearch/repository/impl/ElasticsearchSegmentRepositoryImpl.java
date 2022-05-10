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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@Repository
public class ElasticsearchSegmentRepositoryImpl
	extends BaseElasticsearchRepository<Segment, Long>
	implements SegmentRepository {

	@Override
	public long countByCreateDateBetweenAndIdAfter(
		Date fromDate, Date toDate, Long id) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateCreated"
				).gte(
					DateUtil.toString(fromDate)
				).lte(
					DateUtil.toString(toDate)
				)
			).filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					id
				)
			));
		searchSourceBuilder.size(0);
		searchSourceBuilder.sort(SortBuilders.fieldSort("id"));

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(), searchSourceBuilder);

		return HitsUtil.getTotalHitsCount(searchResponse.getHits());
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
			getCollectionName(), searchSourceBuilder);

		return HitsUtil.getTotalHitsCount(searchResponse.getHits());
	}

	@Override
	public long countPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.termQuery(
				"referencedAssetDataSourceIds", dataSourceId)
		).should(
			QueryBuilders.termsQuery(
				"referencedFieldMappingIds", dataSourceFieldMappingIds)
		);

		if (!StringUtils.isEmpty(filterHelper.getFilterString())) {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(
				boolQueryBuilder
			).filter(
				filterHelper.getQueryBuilder()
			);
		}

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public long countSegments(
		FilterHelper filterHelper, List<Long> segmentIds) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getSegmentsQueryBuilder(filterHelper, segmentIds));
	}

	@Override
	public long countSegments(
		List<Long> channelIds, FilterHelper filterHelper) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getSegmentsQueryBuilder(channelIds, filterHelper));
	}

	@Override
	public void deleteByChannelIdIn(Set<Long> channelIds) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"channelId",
				Stream.of(
					channelIds
				).flatMap(
					Set::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, getCollectionName());
	}

	@Override
	public boolean existsByName(String name) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(), QueryBuilders.termQuery("name", name));
	}

	@Override
	public List<Segment> findByChannelIdIn(
		Set<Long> channelIds, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				QueryBuilders.termsQuery(
					"channelId",
					Stream.of(
						channelIds
					).flatMap(
						Set::stream
					).map(
						String::valueOf
					).collect(
						Collectors.toList()
					)),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> findByChannelIdIsNotNullOrNameStartingWith(
		String name, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				BoolQueryBuilderUtil.should(
					QueryBuilders.existsQuery("channelId")
				).should(
					QueryBuilders.prefixQuery("name", name)
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> findByCreateDateBetweenAndIdAfter(
		Date fromDate, Date toDate, Long id, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"dateCreated"
					).gte(
						DateUtil.toString(fromDate)
					).lte(
						DateUtil.toString(toDate)
					)
				).filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						id
					)
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> findByIdAfter(Long id, Pageable pageable) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					id
				),
				pageable.getPageSize()));
	}

	@Override
	public Optional<Segment> findByNameAndStatus(String name, String status) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", name)
				).filter(
					QueryBuilders.termQuery("status", status)
				))
		).map(
			this::toEntity
		);
	}

	@Override
	public List<Segment> findByReferencedAssetDataSourceIdsAndStateNotAndType(
		Long referencedAssetDataSourceIds, String state, Segment.Type type) {

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
			referencedAssetDataSourceIds, null, state, type);
	}

	@Override
	public List<Segment>
		findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
			Long referencedAssetDataSourceIds,
			List<Long> referencedFieldMappingIds, String state,
			Segment.Type type) {

		BoolQueryBuilder innerBoolQueryBuilder = QueryBuilders.boolQuery();

		if (referencedAssetDataSourceIds != null) {
			innerBoolQueryBuilder.should(
				QueryBuilders.termQuery(
					"referencedAssetDataSourceIds",
					String.valueOf(referencedAssetDataSourceIds)));
		}

		if ((referencedFieldMappingIds != null) &&
			!referencedFieldMappingIds.isEmpty()) {

			innerBoolQueryBuilder.should(
				QueryBuilders.termsQuery(
					"referencedFieldMappingIds", referencedFieldMappingIds));
		}

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (state != null) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("state", state)));
		}

		if (type != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("segmentType", type.toString()));
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				boolQueryBuilder.filter(innerBoolQueryBuilder)));
	}

	@Override
	public List<Segment> findByReferencedFieldMappingIdInAndStateNotAndType(
		List<Long> referencedFieldMappingIds, String state, Segment.Type type) {

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
			null, referencedFieldMappingIds, state, type);
	}

	@Override
	public List<Segment> findByStateNotAndType(
		String state, Segment.Type type) {

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
			null, null, state, type);
	}

	@Override
	public List<Long> findIdByNameInAndStatus(
		List<String> names, String status) {

		List<Long> ids = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"ids"
					).field(
						"id"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery("name", names)
					).filter(
						QueryBuilders.termQuery("status", status)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return ids;
		}

		Terms terms = aggregations.get("ids");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			ids.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return ids;
	}

	@Override
	public List<String> findNameByChannelIdAndIdInAndStatus(
		Long channelId, List<Long> ids, String status) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("channelId", String.valueOf(channelId))
		).filter(
			QueryBuilders.termsQuery("id", ListUtil.map(ids, String::valueOf))
		).filter(
			QueryBuilders.termQuery("status", status)
		);

		return JSONUtil.toStringList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), boolQueryBuilder),
			"name");
	}

	@Override
	public List<String> findNameByIdInAndStatus(List<Long> ids, String status) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery("id", ListUtil.map(ids, String::valueOf))
		).filter(
			QueryBuilders.termQuery("status", status)
		);

		return JSONUtil.toStringList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), boolQueryBuilder),
			"name");
	}

	@Override
	public List<Long> findReferencedAssetIds() {
		List<Long> referencedAssetIds = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"referencedAssetIds"
					).field(
						"referencedAssetIds"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return referencedAssetIds;
		}

		Terms terms = aggregations.get("referencedAssetIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			referencedAssetIds.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return referencedAssetIds;
	}

	@Override
	public List<Transformation> getSegmentTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable,
		List<Long> segmentIds) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = _getSegmentsQueryBuilder(
			filterHelper, segmentIds);

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
			"individual-segment-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray individualSegmentTransformationsJSONArray =
			embeddedJSONObject.getJSONArray(
				"individual-segment-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			individualSegmentTransformationsJSONArray);

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
	public List<Segment> searchDynamicSegments(
		FilterHelper filterHelper, Pageable pageable) {

		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

		if (filterHelper.getFilterString() != null) {
			queryBuilder = filterHelper.getQueryBuilder();
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					QueryBuilders.termQuery("segmentType", "DYNAMIC")
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> searchDynamicSegments(
		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs,
		FilterHelper filterHelper, @Nullable Boolean includeAnonymousUsers,
		Pageable pageable, Set<Long> segmentIds) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("segmentType", "DYNAMIC"));

		if (filterHelper.getFilterString() != null) {
			boolQueryBuilder = boolQueryBuilder.filter(
				filterHelper.getQueryBuilder());
		}

		if (CollectionUtils.isEmpty(dataSourceAccountPKs)) {
			boolQueryBuilder.mustNot(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.prefixQuery(
						"filter", "((dataSourceAccountPKs/accountPKs eq '")
				).filter(
					QueryBuilders.prefixQuery("name", "Account: ")
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				));
		}
		else {
			List<String> filterStrings = new ArrayList<>();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				Set<String> accountPKs = dataSourceAccountPK.getAccountPKs();

				for (String accountPK : accountPKs) {
					filterStrings.add(
						"((dataSourceAccountPKs/accountPKs eq '" + accountPK +
							"'))");
				}
			}

			boolQueryBuilder = BoolQueryBuilderUtil.should(
				BoolQueryBuilderUtil.filter(
					boolQueryBuilder
				).filter(
					QueryBuilders.termQuery("status", "ACTIVE")
				)
			).should(
				BoolQueryBuilderUtil.filter(
					boolQueryBuilder
				).filter(
					QueryBuilders.termsQuery("filter", filterStrings)
				).filter(
					QueryBuilders.termQuery("status", "INACTIVE")
				)
			);
		}

		if (includeAnonymousUsers != null) {
			if (!includeAnonymousUsers) {
				boolQueryBuilder.mustNot(
					QueryBuilders.termQuery("includeAnonymousUsers", true));
			}
			else {
				boolQueryBuilder.filter(
					QueryBuilders.termQuery("includeAnonymousUsers", true));
			}
		}

		if (CollectionUtils.isNotEmpty(segmentIds)) {
			boolQueryBuilder.should(QueryBuilders.termsQuery("id", segmentIds));
		}

		BoolQueryBuilder finalBoolQueryBuilder = boolQueryBuilder;

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable), finalBoolQueryBuilder,
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		FilterHelper filterHelper, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.termQuery(
				"referencedAssetDataSourceIds", dataSourceId)
		).should(
			QueryBuilders.termsQuery(
				"referencedFieldMappingIds", dataSourceFieldMappingIds)
		);

		if (!StringUtils.isEmpty(filterHelper.getFilterString())) {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(
				boolQueryBuilder
			).filter(
				filterHelper.getQueryBuilder()
			);
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable), boolQueryBuilder, pageable.getPageSize()));
	}

	@Override
	public List<Segment> searchSegments(
		FilterHelper filterHelper, @Nullable List<Long> segmentIds,
		Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				_getSegmentsQueryBuilder(filterHelper, segmentIds),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> searchSegments(
		List<Long> channelIds, FilterHelper filterHelper, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				_getSegmentsQueryBuilder(channelIds, filterHelper),
				pageable.getPageSize()));
	}

	@Override
	public List<Segment> searchSegments(
		Long dxpEntityId, DXPEntity.Type dxpEntityType, String state,
		Segment.Type type) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("segmentType", type.toString())
				).filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termQuery("state", state))
				).filter(
					QueryBuilders.termsQuery(
						dxpEntityType.getIndividualSegmentFieldName(),
						String.valueOf(dxpEntityId))
				));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				getFrom(pageable),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.regexpQuery("filter", filter)
				).filter(
					QueryBuilders.termQuery("state", state)
				).filter(
					QueryBuilders.termQuery("status", status)
				),
				pageable.getPageSize()));
	}

	@Override
	public void updateActivitiesCountAndRemoveLastActivityDate(
		Long activitiesCount) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.matchAllQuery(), true,
			new Script(
				"ctx._source.remove('lastActivityDate');" +
					"ctx._source.activitiesCount = " + activitiesCount),
			getCollectionName());
	}

	@Override
	protected String getCollectionName() {
		return "individual-segments";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _getSegmentsQueryBuilder(
		FilterHelper filterHelper, List<Long> segmentIds) {

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"id", ListUtil.map(segmentIds, String::valueOf));

		if (StringUtils.isEmpty(filterHelper.getFilterString())) {
			return queryBuilder;
		}

		return BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			filterHelper.getQueryBuilder()
		);
	}

	private QueryBuilder _getSegmentsQueryBuilder(
		List<Long> channelIds, FilterHelper filterHelper) {

		QueryBuilder queryBuilder = filterHelper.getQueryBuilder();

		if (channelIds.isEmpty()) {
			return queryBuilder;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery("channelId", channelIds));

		if (queryBuilder == null) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(queryBuilder);
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
		ElasticsearchSegmentRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}