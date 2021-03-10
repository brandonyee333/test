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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.DataExportTask;
import com.liferay.osb.asah.common.model.DataExportTaskStatus;
import com.liferay.osb.asah.common.model.DataExportTaskType;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class DataExportTaskDog {

	public DataExportTask addDataExportTask(String dataExportTaskTypeValue) {
		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setCreatedDate(new Date());
		dataExportTask.setDataExportTaskStatus(DataExportTaskStatus.PENDING);
		dataExportTask.setDataExportTaskType(
			DataExportTaskType.valueOf(dataExportTaskTypeValue));

		JSONObject dataExportTaskJSONObject = _faroInfoElasticsearchInvoker.add(
			"data-export-tasks",
			_objectMapper.convertValue(dataExportTask, JSONObject.class));

		dataExportTask.setId(dataExportTaskJSONObject.optString("id"));

		return dataExportTask;
	}

	public DataExportTask fetchLastDataExportTask(
		String dataExportTaskTypeValue) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		DataExportTaskType dataExportTaskType = DataExportTaskType.valueOf(
			dataExportTaskTypeValue);

		searchSourceBuilder.query(
			QueryBuilders.termQuery("type", dataExportTaskType.toString()));

		searchSourceBuilder.size(1);
		searchSourceBuilder.sort(
			SortBuilderUtil.fieldSort("id", SortOrder.DESC));

		SearchHits searchHits = _dataDog.querySearchHits(
			"data-export-tasks", _faroInfoElasticsearchInvoker,
			searchSourceBuilder);

		if (!HitsUtil.hasHits(searchHits)) {
			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		try {
			return _objectMapper.readValue(
				searchHit.getSourceAsString(), DataExportTask.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	public File getDataExportTaskFile(String dataExportTaskId) {
		return new File(_exportPath + "/" + dataExportTaskId + ".json");
	}

	@Autowired
	private DataDog _dataDog;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}