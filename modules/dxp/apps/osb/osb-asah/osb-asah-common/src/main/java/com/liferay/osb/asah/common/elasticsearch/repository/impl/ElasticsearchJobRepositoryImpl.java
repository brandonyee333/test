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
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchJobRepositoryImpl
	extends BaseElasticsearchRepository<Job, Long> implements JobRepository {

	@Override
	public long countByNameContainingIgnoreCase(String name) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryUtil.buildSearchQueryBuilder("name.search", name));
	}

	@Override
	public List<Job> findByNameContainingIgnoreCase(
		String name, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryUtil.buildSearchQueryBuilder(
								"name.search", name));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public Optional<Job> findFirstByName(String name) {
		return findFirst(
			searchSourceBuilder -> searchSourceBuilder.query(
				QueryBuilders.termQuery("name", name)));
	}

	@Override
	protected String getCollectionName() {
		return "jobs";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}