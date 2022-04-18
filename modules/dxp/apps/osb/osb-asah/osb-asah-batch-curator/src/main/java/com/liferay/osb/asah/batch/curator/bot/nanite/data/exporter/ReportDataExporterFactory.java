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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.http.ReportHttp;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 */
@Component
public class ReportDataExporterFactory {

	@Autowired
	public ReportDataExporterFactory(ReportHttp reportHttp) {
		_reportHttp = reportHttp;
	}

	public BaseReportDataExporter create(
			DataExportTask dataExportTask, OutputStream outputStream)
		throws Exception {

		if (dataExportTask.getType() == DataExportTask.Type.ACCOUNT) {
			return new AccountDataExporter(
				dataExportTask.getFromDate(), _jsonFactory, outputStream,
				_reportHttp, dataExportTask.getToDate());
		}

		if (dataExportTask.getType() == DataExportTask.Type.INDIVIDUAL) {
			return new IndividualDataExporter(
				dataExportTask.getFromDate(), _jsonFactory, outputStream,
				_reportHttp, dataExportTask.getToDate());
		}

		if (dataExportTask.getType() == DataExportTask.Type.SEGMENT) {
			return new SegmentDataExporter(
				dataExportTask.getFromDate(), _jsonFactory, outputStream,
				_reportHttp, dataExportTask.getToDate());
		}

		throw new IllegalArgumentException(
			String.format(
				"Invalid data export task type: %s", dataExportTask.getType()));
	}

	private final JsonFactory _jsonFactory = new JsonFactory();
	private final ReportHttp _reportHttp;

}