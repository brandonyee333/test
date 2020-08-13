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

package com.liferay.osb.asah.backend.dog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.DataExportTask;
import com.liferay.osb.asah.common.model.DataExportTaskStatus;
import com.liferay.osb.asah.common.model.DataExportTaskType;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.lucene.search.TotalHits;

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

		TotalHits totalHits = searchHits.getTotalHits();

		if (totalHits.value == 0) {
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

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}