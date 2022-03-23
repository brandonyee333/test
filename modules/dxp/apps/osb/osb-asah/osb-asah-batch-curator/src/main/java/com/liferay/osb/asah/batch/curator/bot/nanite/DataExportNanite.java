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
import com.liferay.osb.asah.common.dog.DataExportTaskDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		List<DataExportTask> dataExportTasks =
			_dataExportTaskDog.getDataExportTasks(
				DataExportTask.Status.PENDING);

		dataExportTasks.forEach(this::_runDataExportTask);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private DataExporter _createDataExporter(
			OutputStream outputStream, DataExportTask.Type type)
		throws Exception {

		if (type == DataExportTask.Type.ACCOUNT) {
			return new AccountDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}

		if (type == DataExportTask.Type.INDIVIDUAL) {
			return new IndividualDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}

		if (type == DataExportTask.Type.PAGE) {
			return new PageDataExporter(
				_jsonFactory, outputStream, _cerebroInfoElasticsearchInvoker);
		}

		if (type == DataExportTask.Type.SEGMENT) {
			return new SegmentDataExporter(
				_jsonFactory, outputStream, _reportHttp);
		}

		throw new IllegalArgumentException("Invalid data export task type");
	}

	private void _runDataExportTask(DataExportTask dataExportTask) {
		_dataExportTaskDog.updateDataExportTask(
			dataExportTask.getId(), DataExportTask.Status.RUNNING);

		try (OutputStream outputStream = new FileOutputStream(
				_exportPath + "/" + dataExportTask.getId() + ".json")) {

			DataExporter dataExporter = _createDataExporter(
				outputStream, dataExportTask.getType());

			dataExporter.export();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			_dataExportTaskDog.updateDataExportTask(
				dataExportTask.getId(), DataExportTask.Status.ERROR);

			return;
		}

		_dataExportTaskDog.updateDataExportTask(
			dataExportTask.getId(), DataExportTask.Status.COMPLETED);
	}

	private static final Log _log = LogFactory.getLog(DataExportNanite.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataExportTaskDog _dataExportTaskDog;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	private final JsonFactory _jsonFactory = new JsonFactory();

	@Autowired
	private ReportHttp _reportHttp;

}