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
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Robson Pastor
 */
@Repository
public class ElasticsearchInterestRepositoryImpl
	extends BaseElasticsearchRepository<Interest, Long>
	implements InterestRepository {

	@Override
	public long countByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(
				null, Collections.singletonList(ownerId), ownerType, null,
				null));
	}

	@Override
	public void deleteByNameAndRecordedDateGreaterThanEqual(
		String name, Date recordedDate) {

		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
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
	public void deleteByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			_buildQueryBuilder(
				null, Collections.singletonList(ownerId), ownerType, null,
				null));
	}

	@Override
	public void deleteByOwnerTypeAndRecordedDateLessThanEqual(
		String ownerType, Date recordedDate) {

		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
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
	public List<Interest> findByOwnerIdAndOwnerType(
		@Nullable Long ownerId, String ownerType, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"ownerId", String.valueOf(ownerId))
							).filter(
								QueryBuilders.termQuery("ownerType", ownerType)
							));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Interest> findByOwnerTypeAndRecordedDate(
		Long interestId, String ownerType, Date recordedDate, int size) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"dateRecorded",
									DateUtil.toString(recordedDate))
							).filter(
								QueryBuilders.termQuery("ownerType", ownerType)
							));

						if (interestId != null) {
							searchSourceBuilder.searchAfter(
								new Object[] {interestId});
						}

						searchSourceBuilder.size(size);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort("id"));
					})));
	}

	@Override
	public Interest getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
		String name, Long ownerId, String ownerType, Date recordedDate) {

		return toEntity(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
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
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					_buildQueryBuilder(
						keyword, ownerIds, ownerType, recordedDate, score));
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
							pageable.getPageNumber() * pageable.getPageSize()
						).size(
							pageable.getPageSize()
						)
					));
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
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
	protected String getCollectionName() {
		return "interests";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(
		@Nullable String keywords, @Nullable List<Long> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}