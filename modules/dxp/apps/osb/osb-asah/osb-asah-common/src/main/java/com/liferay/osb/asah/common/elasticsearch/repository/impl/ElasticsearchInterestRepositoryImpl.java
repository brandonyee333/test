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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
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
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public void deleteByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
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
		Long ownerId, String ownerType, Pageable pageable) {

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
	protected String getCollectionName() {
		return "interests";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}