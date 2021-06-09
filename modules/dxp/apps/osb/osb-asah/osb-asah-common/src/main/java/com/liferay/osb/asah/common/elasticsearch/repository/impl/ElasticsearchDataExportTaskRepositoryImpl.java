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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@Repository
public class ElasticsearchDataExportTaskRepositoryImpl
	extends BaseElasticsearchRepository<DataExportTask, Long>
	implements DataExportTaskRepository {

	@Override
	public List<DataExportTask> findByStatus(DataExportTask.Status status) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery("status", status.toString())));
	}

	@Override
	public DataExportTask findFirstByTypeOrderByIdDesc(
		DataExportTask.Type type) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				QueryBuilders.termQuery("type", type.toString()),
				SortBuilderUtil.fieldSort("id", SortOrder.DESC), null, null)
		).map(
			this::toEntity
		).orElse(
			null
		);
	}

	@Override
	protected String getCollectionName() {
		return "data-export-tasks";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}