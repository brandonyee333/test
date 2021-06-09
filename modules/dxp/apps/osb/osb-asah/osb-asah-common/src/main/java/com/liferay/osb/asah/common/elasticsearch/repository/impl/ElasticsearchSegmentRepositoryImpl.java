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
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public long countAccountSegments(
		@Nullable String filterString, @Nullable List<Long> segmentIds) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getSegmentsQueryBuilder(filterString, segmentIds));
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
		String filterString) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.termQuery(
				"referencedAssetDataSourceIds", dataSourceId)
		).should(
			QueryBuilders.termsQuery(
				"referencedFieldMappingIds", dataSourceFieldMappingIds)
		);

		if (!StringUtils.isEmpty(filterString)) {
			boolQueryBuilder = BoolQueryBuilderUtil.filter(
				boolQueryBuilder
			).filter(
				FilterStringToQueryBuilderConverter.convert(filterString)
			);
		}

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public long countSegments(List<Long> channelIds, String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getSegmentsQueryBuilder(channelIds, filterString));
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
	public List<Segment> findAll(Pageable pageable) {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> setSearchSourceBuilderPage(
						searchSourceBuilder, pageable))));
	}

	@Override
	public List<Segment> findByIdAfter(Long id, Pageable pageable) {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.rangeQuery(
								"id"
							).gt(
								id
							));
						searchSourceBuilder.sort(SortBuilders.fieldSort("id"));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
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

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
			referencedAssetDataSourceIds, null, state, type);
	}

	@Override
	public List<Segment>
		findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
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
	public List<Segment> findByReferencedFieldMappingIdsInAndStateNotAndType(
		List<Long> referencedFieldMappingIds, String state, Segment.Type type) {

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
			null, referencedFieldMappingIds, state, type);
	}

	@Override
	public List<Segment> findByStateNotAndType(
		String state, Segment.Type type) {

		return findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
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

		if (aggregations == null) {
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
		String apply, String filterString, Pageable pageable,
		List<Long> segmentIds) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = _getSegmentsQueryBuilder(
			filterString, segmentIds);

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
	public List<Segment> searchAccountSegments(
		@Nullable String filterString, Pageable pageable,
		@Nullable List<Long> segmentIds) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_getSegmentsQueryBuilder(filterString, segmentIds));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Segment> searchDynamicSegments(
		String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						QueryBuilder queryBuilder =
							QueryBuilders.matchAllQuery();

						if (filterString != null) {
							queryBuilder =
								FilterStringToQueryBuilderConverter.convert(
									filterString);
						}

						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								queryBuilder
							).filter(
								QueryBuilders.termQuery(
									"segmentType", "DYNAMIC")
							));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Segment> searchPreviewDisabledSegments(
		List<Long> dataSourceFieldMappingIds, Long dataSourceId,
		String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						BoolQueryBuilder boolQueryBuilder =
							BoolQueryBuilderUtil.should(
								QueryBuilders.termQuery(
									"referencedAssetDataSourceIds",
									dataSourceId)
							).should(
								QueryBuilders.termsQuery(
									"referencedFieldMappingIds",
									dataSourceFieldMappingIds)
							);

						if (!StringUtils.isEmpty(filterString)) {
							boolQueryBuilder = BoolQueryBuilderUtil.filter(
								boolQueryBuilder
							).filter(
								FilterStringToQueryBuilderConverter.convert(
									filterString)
							);
						}

						searchSourceBuilder.query(boolQueryBuilder);

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Segment> searchSegments(
		DXPEntity.Type dxpEntityType, Long id, String state,
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
						String.valueOf(id))
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
		List<Long> channelIds, String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_getSegmentsQueryBuilder(channelIds, filterString));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Segment> searchSegments(
		String filter, String state, String status, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.regexpQuery("filter", filter)
							).filter(
								QueryBuilders.termQuery("state", state)
							).filter(
								QueryBuilders.termQuery("status", status)
							));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
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
		List<Long> channelIds, String filterString) {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

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

	private QueryBuilder _getSegmentsQueryBuilder(
		String filterString, List<Long> segmentIds) {

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"id", ListUtil.map(segmentIds, String::valueOf));

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
		ElasticsearchSegmentRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}