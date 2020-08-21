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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.AccountDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.IndividualDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.PageDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.SegmentDataExporter;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.DataExportTaskStatus;
import com.liferay.osb.asah.common.model.DataExportTaskType;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DataExportNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONArrayIterator.of(
			"data-export-tasks", faroInfoElasticsearchInvoker,
			this::_runDataExportTask
		).setQueryBuilder(
			QueryBuilders.termQuery(
				"status", DataExportTaskStatus.PENDING.toString())
		).setStopOnExceptions(
			false
		).iterate();
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private DataExporter _createDataExporter(
			DataExportTaskType dataExportTaskType, OutputStream outputStream)
		throws Exception {

		if (dataExportTaskType == DataExportTaskType.ACCOUNT) {
			return new AccountDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}
		else if (dataExportTaskType == DataExportTaskType.INDIVIDUAL) {
			return new IndividualDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}
		else if (dataExportTaskType == DataExportTaskType.PAGE) {
			return new PageDataExporter(
				_jsonFactory, outputStream,
				_elasticsearchInvokerFactory.forCerebroInfo());
		}
		else if (dataExportTaskType == DataExportTaskType.SEGMENT) {
			return new SegmentDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}

		throw new IllegalArgumentException("Invalid data export task type");
	}

	private JSONObject _runDataExportTask(JSONObject dataExportTaskJSONObject) {
		_updateDataExportTaskStatus(
			dataExportTaskJSONObject, DataExportTaskStatus.RUNNING);

		try (OutputStream outputStream = new FileOutputStream(
				_exportPath + "/" + dataExportTaskJSONObject.getString("id") +
					".json")) {

			DataExporter dataExporter = _createDataExporter(
				DataExportTaskType.valueOf(
					dataExportTaskJSONObject.getString("type")),
				outputStream);

			dataExporter.export();
		}
		catch (Exception e) {
			_log.error(e, e);

			_updateDataExportTaskStatus(
				dataExportTaskJSONObject, DataExportTaskStatus.ERROR);

			return dataExportTaskJSONObject;
		}

		_updateDataExportTaskStatus(
			dataExportTaskJSONObject, DataExportTaskStatus.COMPLETED);

		return dataExportTaskJSONObject;
	}

	private void _updateDataExportTaskStatus(
		JSONObject dataExportTaskJSONObject,
		DataExportTaskStatus dataExportTaskStatus) {

		if (dataExportTaskStatus == DataExportTaskStatus.COMPLETED) {
			dataExportTaskJSONObject.put(
				"completedDate", DateUtil.newUTCDateString());
		}
		else if (dataExportTaskStatus == DataExportTaskStatus.RUNNING) {
			dataExportTaskJSONObject.put(
				"startedDate", DateUtil.newUTCDateString());
		}

		dataExportTaskJSONObject.put("status", dataExportTaskStatus.toString());

		faroInfoElasticsearchInvoker.update(
			"data-export-tasks", dataExportTaskJSONObject);
	}

	private static final Log _log = LogFactory.getLog(DataExportNanite.class);

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	private final JsonFactory _jsonFactory = new JsonFactory();

	@Autowired
	private ReportHttp _reportHttp;

}