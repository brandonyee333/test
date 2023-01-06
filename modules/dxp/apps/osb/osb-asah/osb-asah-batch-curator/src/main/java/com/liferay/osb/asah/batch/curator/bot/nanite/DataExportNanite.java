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

import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.BigQueryDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.SegmentDataExporter;
import com.liferay.osb.asah.common.dog.DataExportTaskDog;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.http.ReportHttp;

import java.io.File;
import java.io.FileOutputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jooq.DSLContext;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DataExportNanite extends BaseNanite {

	@Autowired
	public DataExportNanite(
		DataExportTaskDog dataExportTaskDog, DSLContext dslContext,
		ReportHttp reportHttp) {

		_dataExportTaskDog = dataExportTaskDog;
		_dslContext = dslContext;
		_reportHttp = reportHttp;
	}

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

	private void _runBigQueryDataExportTask(DataExportTask dataExportTask)
		throws Exception {

		DataExporter dataExporter = null;

		if (dataExportTask.getType() == DataExportTask.Type.INDIVIDUAL) {
			dataExporter = new BigQueryDataExporter(
				dataExportTask, "createDate", _dslContext, _exportPath,
				"Individual");
		}
		else if (dataExportTask.getType() == DataExportTask.Type.PAGE) {
			dataExporter = new BigQueryDataExporter(
				dataExportTask, "eventDate", _dslContext, _exportPath,
				"PageDaily");
		}
		else {
			throw new IllegalArgumentException(
				"Invalid data export task type: " + dataExportTask.getType());
		}

		dataExporter.export();
	}

	private void _runDataExportTask(DataExportTask dataExportTask) {
		_dataExportTaskDog.updateDataExportTask(
			dataExportTask.getId(), DataExportTask.Status.RUNNING);

		try {
			if (dataExportTask.getType() == DataExportTask.Type.SEGMENT) {
				_runSegmentDataExportTask(dataExportTask);
			}
			else {
				_runBigQueryDataExportTask(dataExportTask);
			}

			_dataExportTaskDog.updateDataExportTask(
				dataExportTask.getId(), DataExportTask.Status.COMPLETED);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			_dataExportTaskDog.updateDataExportTask(
				dataExportTask.getId(), DataExportTask.Status.ERROR);
		}
	}

	private void _runSegmentDataExportTask(DataExportTask dataExportTask)
		throws Exception {

		Path path = Paths.get(
			_exportPath,
			FilenameUtils.getName(dataExportTask.getId() + ".zip"));

		ZipOutputStream zipOutputStream = new ZipOutputStream(
			new FileOutputStream(path.toFile()));

		File file = new File("data.json");

		zipOutputStream.putNextEntry(new ZipEntry(file.getName()));

		DataExporter dataExporter = new SegmentDataExporter(
			dataExportTask.getFromDate(), _jsonFactory, zipOutputStream,
			_reportHttp, dataExportTask.getToDate());

		dataExporter.export();

		zipOutputStream.closeEntry();

		zipOutputStream.close();
	}

	private static final Log _log = LogFactory.getLog(DataExportNanite.class);

	private final DataExportTaskDog _dataExportTaskDog;
	private final DSLContext _dslContext;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	private final JsonFactory _jsonFactory = new JsonFactory();
	private final ReportHttp _reportHttp;

}