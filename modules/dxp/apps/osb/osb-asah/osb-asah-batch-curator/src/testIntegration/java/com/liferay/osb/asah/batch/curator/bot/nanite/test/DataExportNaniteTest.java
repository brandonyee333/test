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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.DataExportNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.BaseReportDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.ReportDataExporterFactory;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataExportTaskDog;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.io.File;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Alejo Ceballos
 */
public class DataExportNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		_baseReportDataExporterMock = _createBaseReportDataExporterMock();
		_exportPath = Files.createTempDirectory("export");
	}

	@AfterEach
	public void tearDown() {
		File folder = _exportPath.toFile();

		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (!file.delete()) {
					_log.error(
						"Unable to delete file " + file.getAbsolutePath());
				}
			}
		}

		if (!folder.delete()) {
			_log.error("Unable to delete folder " + folder.getAbsolutePath());
		}
	}

	@EnumSource(
		names = {"ACCOUNT", "INDIVIDUAL", "SEGMENT"},
		value = DataExportTask.Type.class
	)
	@ParameterizedTest
	public void testRunDataExportTaskComplete(DataExportTask.Type type)
		throws Exception {

		DataExportTask dataExportTask = _createPendingDataExportTask(type);

		DataExportNanite dataExportNanite = _createDataExportNaniteByType(
			false, type);

		dataExportNanite.run(null);

		_assertDataExportTaskStatus(
			dataExportTask.getId(), DataExportTask.Status.COMPLETED);
	}

	@EnumSource(
		names = {"ACCOUNT", "INDIVIDUAL", "SEGMENT"},
		value = DataExportTask.Type.class
	)
	@ParameterizedTest
	public void testRunDataExportTaskWithError(DataExportTask.Type type)
		throws Exception {

		DataExportTask dataExportTask = _createPendingDataExportTask(type);

		DataExportNanite dataExportNanite = _createDataExportNaniteByType(
			true, type);

		dataExportNanite.run(null);

		_assertDataExportTaskStatus(
			dataExportTask.getId(), DataExportTask.Status.ERROR);
	}

	private void _assertDataExportTaskStatus(
		Long id, DataExportTask.Status expectedStatus) {

		Optional<DataExportTask> dataExportTaskOptional =
			_dataExportTaskRepository.findById(id);

		Assertions.assertTrue(dataExportTaskOptional.isPresent());

		DataExportTask dataExportTask = dataExportTaskOptional.get();

		MatcherAssert.assertThat(
			dataExportTask.getStartedDate(),
			Matchers.lessThanOrEqualTo(new Date()));

		if (expectedStatus == DataExportTask.Status.COMPLETED) {
			MatcherAssert.assertThat(
				dataExportTask.getCompletedDate(),
				Matchers.lessThanOrEqualTo(new Date()));
		}
		else {
			Assertions.assertNull(dataExportTask.getCompletedDate());
		}

		Assertions.assertEquals(expectedStatus, dataExportTask.getStatus());
	}

	private BaseReportDataExporter _createBaseReportDataExporterMock()
		throws Exception {

		BaseReportDataExporter baseReportDataExporterMock = Mockito.mock(
			BaseReportDataExporter.class);

		Mockito.doNothing(
		).when(
			baseReportDataExporterMock
		).export();

		return baseReportDataExporterMock;
	}

	private DataExportNanite _createDataExportNaniteByType(
			boolean raiseException, DataExportTask.Type type)
		throws Exception {

		ReportDataExporterFactory dataExporterFactoryMock = Mockito.mock(
			ReportDataExporterFactory.class);

		OngoingStubbing<BaseReportDataExporter> ongoingStubbing = Mockito.when(
			dataExporterFactoryMock.create(
				ArgumentMatchers.argThat(
					dataExportTask -> dataExportTask.getType(
					).equals(
						type
					)),
				ArgumentMatchers.any(OutputStream.class)));

		if (raiseException) {
			ongoingStubbing.thenThrow(Exception.class);
		}
		else {
			ongoingStubbing.thenReturn(_baseReportDataExporterMock);
		}

		DataExportNanite dataExportNanite = new DataExportNanite(
			_dataExportTaskDog, dataExporterFactoryMock);

		ReflectionTestUtils.setField(
			dataExportNanite, "_exportPath", _exportPath.toString());

		return dataExportNanite;
	}

	private DataExportTask _createPendingDataExportTask(
		DataExportTask.Type type) {

		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setCreateDate(DateUtil.newDate());
		dataExportTask.setFromDate(
			DateUtil.toUTCDate("2020-12-21T12:00:00.000Z"));
		dataExportTask.setStatus(DataExportTask.Status.PENDING);
		dataExportTask.setToDate(DateUtil.newDate());
		dataExportTask.setType(type);

		return _dataExportTaskRepository.save(dataExportTask);
	}

	private static final Log _log = LogFactory.getLog(
		DataExportNaniteTest.class);

	private BaseReportDataExporter _baseReportDataExporterMock;

	@Autowired
	private DataExportTaskDog _dataExportTaskDog;

	@Autowired
	private DataExportTaskRepository _dataExportTaskRepository;

	private Path _exportPath;

}