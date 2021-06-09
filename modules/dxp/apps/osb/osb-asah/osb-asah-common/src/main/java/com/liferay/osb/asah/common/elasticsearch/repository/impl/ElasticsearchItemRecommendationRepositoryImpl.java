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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.ItemRecommendation;
import com.liferay.osb.asah.common.repository.ItemRecommendationRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchItemRecommendationRepositoryImpl
	extends BaseElasticsearchRepository<ItemRecommendation, String>
	implements ItemRecommendationRepository {

	@Override
	public long countByJobId(Long jobId) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryBuilders.termQuery("jobId", jobId.toString()));
	}

	@Override
	public void deleteByJobId(Long jobId) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termQuery("jobId", jobId.toString()), true,
			getCollectionName());
	}

	@Override
	public List<ItemRecommendation> findByJobId(Long jobId, Pageable pageable) {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.termQuery("jobId", jobId.toString()));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "recommended-items";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}