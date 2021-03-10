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
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.repository.AsahTaskRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONArray;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author André Miranda
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchAsahTaskRepositoryImpl
	extends BaseElasticsearchRepository<AsahTask>
	implements AsahTaskRepository {

	@Override
	public List<AsahTask> findByCronExpressionIsNotNull() {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(BoolQueryBuilderUtil.filter(
							QueryBuilders.existsQuery("cronExpression")));
						searchSourceBuilder.size(ELASTICSEARCH_MAX_SIZE);
					})));
	}

	@Override
	public List<AsahTask> findByCronExpressionIsNull() {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(BoolQueryBuilderUtil.mustNot(
							QueryBuilders.existsQuery("cronExpression")));
						searchSourceBuilder.size(ELASTICSEARCH_MAX_SIZE);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "OSBAsahTasks";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}