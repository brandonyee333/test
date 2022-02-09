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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.sort.FieldSortBuilder;
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
 * @author Robson Pastor
 */
@Repository
public class ElasticsearchInterestRepositoryImpl implements InterestRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, Double score) {

		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(),
			_buildQueryBuilder(filterHelper, null, null, null, null, score));
	}

	@Override
	public long countByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(),
			_buildQueryBuilder(
				null, null, Collections.singletonList(ownerId), ownerType, null,
				null));
	}

	@Override
	public long countInterestDistributions(
		@Nullable String keyword, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"uniqueInterests"
					).field(
						"name"
					).precisionThreshold(
						40000
					));
				searchSourceBuilder.query(
					_buildQueryBuilder(
						null, keyword, ownerIds, ownerType, recordedDate,
						score));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (_isEmpty(aggregations)) {
			return 0;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		InternalCardinality internalCardinality =
			(InternalCardinality)aggregationsList.get(0);

		return internalCardinality.getValue();
	}

	@Override
	public void delete(Interest interest) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), String.valueOf(interest.getId()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends Interest> interests) {
		Stream<? extends Interest> stream = StreamSupport.stream(
			interests.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					Interest::getId
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
	public void deleteByNameAndRecordedDateGreaterThanEqual(
		String name, Date recordedDate) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).gte(
					DateUtil.toString(recordedDate)
				)
			).filter(
				QueryBuilders.termQuery("name", name)
			));
	}

	@Override
	public void deleteByOwnerIdInAndOwnerType(
		List<Long> ownerIds, String ownerType) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			_buildQueryBuilder(null, null, ownerIds, ownerType, null, null));
	}

	@Override
	public void deleteByOwnerTypeAndRecordedDate(
		String ownerType, Date recordedDate) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dateRecorded", DateUtil.toString(recordedDate))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public void deleteByOwnerTypeAndRecordedDateLessThanEqual(
		String ownerType, Date recordedDate) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).lte(
					DateUtil.toString(recordedDate)
				)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public boolean existsById(Long id) {
		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(), id.toString());
	}

	@Override
	public Iterable<Interest> findAll() {
		return findAll(Sort.by("id"));
	}

	@Override
	public Page<Interest> findAll(Pageable pageable) {
		return PageableExecutionUtils.getPage(
			_toInterests(
				_faroInfoElasticsearchInvoker.get(
					_getCollectionName(),
					_getFieldSortBuilders(pageable.getSort()),
					_getFrom(pageable), pageable.getPageSize())),
			pageable, () -> count());
	}

	@Override
	public Iterable<Interest> findAll(Sort sort) {
		return _toInterests(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(sort)));
	}

	@Override
	public Iterable<Interest> findAllById(Iterable<Long> ids) {
		Stream<Long> stream = StreamSupport.stream(ids.spliterator(), false);

		return _toInterests(
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
	public List<Interest> findByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, Double score, Pageable pageable) {

		return _toInterests(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(pageable.getSort()),
				_getFrom(pageable),
				_buildQueryBuilder(filterHelper, null, null, null, null, score),
				pageable.getPageSize()));
	}

	@Override
	public Optional<Interest> findById(Long id) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(), id.toString())
		).map(
			this::_toInterest
		);
	}

	@Override
	public List<Interest>
		findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
			String name, Long ownerId, String ownerType, Date fromRecordedDate,
			Date toRecordedDate) {

		return _toInterests(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"dateRecorded"
					).gte(
						DateUtil.toString(fromRecordedDate)
					).lte(
						DateUtil.toString(toRecordedDate)
					)
				).filter(
					QueryBuilders.termQuery("name", name)
				).filter(
					QueryBuilders.termQuery("ownerId", ownerId)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<Interest> findByOwnerIdAndOwnerType(
		@Nullable Long ownerId, String ownerType, Pageable pageable) {

		return _toInterests(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(pageable.getSort()),
				_getFrom(pageable),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Interest> findByOwnerTypeAndRecordedDate(
		Long interestId, String ownerType, Date recordedDate, int size) {

		return _toInterests(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), SortBuilderUtil.fieldSort("id"),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						interestId
					)
				).filter(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"dateRecorded", DateUtil.toString(recordedDate))
					).filter(
						QueryBuilders.termQuery("ownerType", ownerType)
					)
				),
				size));
	}

	@Override
	public Interest getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
		String name, Long ownerId, String ownerType, Date recordedDate) {

		return _toInterest(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("name", name)
				).filter(
					QueryBuilders.termQuery("ownerId", ownerId)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				).filter(
					QueryBuilders.termQuery(
						"dateRecorded", DateUtil.toString(recordedDate))
				)));
	}

	@Override
	public List<Distribution> getInterestDistributions(
		@Nullable String keyword, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score, Pageable pageable) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"userInterests"
					).field(
						"name"
					).order(
						_getBucketOrder(pageable.getSort())
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						PipelineAggregatorBuilders.bucketSort(
							"paginate", null
						).from(
							_getFrom(pageable)
						).size(
							pageable.getPageSize()
						)
					));
				searchSourceBuilder.query(
					_buildQueryBuilder(
						null, keyword, ownerIds, ownerType, recordedDate,
						score));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (_isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Terms terms = aggregations.get("userInterests");

		List<Distribution> distributions = new ArrayList<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			distributions.add(
				new Distribution(
					(int)bucket.getDocCount(),
					Collections.singletonList(bucket.getKeyAsString())));
		}

		return distributions;
	}

	@Override
	public List<String> getTopNamesByOwnerIdAndOwnerType(
		Long ownerId, String ownerType, int size) {

		return JSONUtil.toStringList(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				SortBuilderUtil.fieldSort("score", SortOrder.DESC),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("ownerId", ownerId)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				),
				size),
			"name");
	}

	@Override
	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			searchSourceBuilder -> {
				DateHistogramAggregationBuilder aggregationBuilder =
					AggregationBuilders.dateHistogram(period);

				aggregationBuilder.dateHistogramInterval(
					_getDateHistogramInterval(period)
				).extendedBounds(
					new ExtendedBounds(
						DateUtil.toString(fromDate), DateUtil.toString(toDate))
				).field(
					"dateRecorded"
				).minDocCount(
					0
				).subAggregation(
					AggregationBuilders.avg(
						"scoreAvg"
					).field(
						"score"
					)
				).subAggregation(
					AggregationBuilders.sum(
						"viewsSum"
					).field(
						"views"
					)
				);

				searchSourceBuilder.aggregation(aggregationBuilder);

				QueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
					QueryBuilders.rangeQuery(
						"dateRecorded"
					).gte(
						DateUtil.toString(fromDate)
					)
				).filter(
					QueryBuilders.rangeQuery(
						"dateRecorded"
					).lte(
						DateUtil.toString(toDate)
					)
				).filter(
					_buildQueryBuilder(
						filterHelper, null, null, null, null, null)
				);

				searchSourceBuilder.query(boolQueryBuilder);

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		List<Map<String, Object>> transformations = new ArrayList<>();

		InternalDateHistogram internalDateHistogram = aggregations.get(period);

		for (InternalDateHistogram.Bucket bucket :
				internalDateHistogram.getBuckets()) {

			Aggregations innerAggregations = bucket.getAggregations();

			transformations.add(
				new HashMap<String, Object>() {
					{
						put("intervalInitDate", bucket.getKeyAsString());
						put(
							"scoreAvg",
							_getAggregationValue(
								innerAggregations, "scoreAvg"));
						put("totalElements", bucket.getDocCount());
						put(
							"viewsSum",
							_getAggregationValue(
								innerAggregations, "viewsSum"));
					}
				});
		}

		return transformations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends Interest> S save(S interest) {
		JSONObject jsonObject = _toJSONObject(interest);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)_toInterest(
			_faroInfoElasticsearchInvoker.add(
				_getCollectionName(), jsonObject));
	}

	@Override
	public <S extends Interest> Iterable<S> saveAll(Iterable<S> interests) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		interests.forEach(
			interest -> {
				JSONObject jsonObject = _toJSONObject(interest);

				String id = jsonObject.optString(
					"id", _timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)_toInterest(jsonObject));
			});

		_faroInfoElasticsearchInvoker.add(_getCollectionName(), jsonArray);

		return list;
	}

	private QueryBuilder _buildQueryBuilder(
		@Nullable FilterHelper filterHelper, @Nullable String keywords,
		@Nullable List<Long> ownerIds, @Nullable String ownerType,
		@Nullable Date recordedDate, @Nullable Double score) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if ((filterHelper != null) &&
			!StringUtils.isEmpty(filterHelper.getFilterString())) {

			boolQueryBuilder.filter(filterHelper.getQueryBuilder());
		}

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "name",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"name", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		if (ownerIds != null) {
			BoolQueryBuilderUtil.filterTerms(
				boolQueryBuilder, "ownerId", ownerIds);
		}

		if (ownerType != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "ownerType", ownerType);
		}

		if (recordedDate != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "dateRecorded",
				DateUtil.toString(recordedDate));
		}

		if (score != null) {
			boolQueryBuilder.must(
				QueryBuilders.rangeQuery(
					"score"
				).gte(
					score
				));
		}

		return boolQueryBuilder;
	}

	private double _getAggregationValue(
		Aggregations aggregations, String name) {

		NumericMetricsAggregation.SingleValue singleValue = aggregations.get(
			name);

		if ((singleValue == null) || Double.isNaN(singleValue.value()) ||
			(singleValue.value() < 0)) {

			return 0;
		}

		return singleValue.value();
	}

	private BucketOrder _getBucketOrder(Sort sort) {
		List<BucketOrder> bucketOrders = new ArrayList<>();

		sort.forEach(
			order -> {
				if (StringUtils.equals(order.getProperty(), "count")) {
					bucketOrders.add(BucketOrder.count(order.isAscending()));
				}
				else {
					bucketOrders.add(BucketOrder.key(order.isAscending()));
				}
			});

		return BucketOrder.compound(bucketOrders);
	}

	private String _getCollectionName() {
		return "interests";
	}

	private DateHistogramInterval _getDateHistogramInterval(
		String computeFunctionString) {

		if (computeFunctionString.equals("day")) {
			return DateHistogramInterval.DAY;
		}

		if (computeFunctionString.equals("hour")) {
			return DateHistogramInterval.HOUR;
		}

		if (computeFunctionString.equals("month")) {
			return DateHistogramInterval.MONTH;
		}

		if (computeFunctionString.equals("week")) {
			return DateHistogramInterval.WEEK;
		}

		throw new IllegalArgumentException(
			"Unsupported compute function: " + computeFunctionString);
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

	private int _getFrom(Pageable pageable) {
		return (int)pageable.getOffset();
	}

	private boolean _isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		return aggregationsList.isEmpty();
	}

	private Interest _toInterest(JSONObject jsonObject) {
		return _objectMapper.convertValue(jsonObject, Interest.class);
	}

	private List<Interest> _toInterests(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toInterest((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private JSONObject _toJSONObject(Interest interest) {
		return _objectMapper.convertValue(interest, JSONObject.class);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}