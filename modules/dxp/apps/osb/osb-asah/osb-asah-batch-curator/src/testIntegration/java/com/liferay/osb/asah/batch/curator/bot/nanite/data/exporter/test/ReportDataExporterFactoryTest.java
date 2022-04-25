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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.test;

import com.liferay.osb.asah.batch.curator.OSBAsahBatchCuratorSpringTestContext;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.AccountDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.BaseReportDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.IndividualDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.PageDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.ReportDataExporterFactory;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.SegmentDataExporter;
import com.liferay.osb.asah.common.entity.DataExportTask;

import java.io.ByteArrayOutputStream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Alejo Ceballos
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = DependencyInjectionTestExecutionListener.class
)
public class ReportDataExporterFactoryTest
	implements OSBAsahBatchCuratorSpringTestContext {

	@EnumSource(
		names = {"ACCOUNT", "INDIVIDUAL", "SEGMENT"},
		value = DataExportTask.Type.class
	)
	@ParameterizedTest
	public void testCreateReportDataExporterForAccountIndividualAndSegment(
			DataExportTask.Type type)
		throws Exception {

		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setType(type);

		BaseReportDataExporter baseReportDataExporter =
			_reportDataExporterFactory.create(
				dataExportTask, new ByteArrayOutputStream());

		_assertMatchInstance(baseReportDataExporter, type);
	}

	@Test
	public void testCreateReportDataExporterForPage() throws Exception {
		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setType(DataExportTask.Type.PAGE);

		BaseReportDataExporter baseReportDataExporter =
			_injectedReportDataExporterFactory.create(
				dataExportTask, new ByteArrayOutputStream());

		_assertMatchInstance(baseReportDataExporter, DataExportTask.Type.PAGE);
	}

	@Test
	public void testCreateReportDataExporterForPageWithoutInvokerBreaks() {
		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setType(DataExportTask.Type.PAGE);

		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportDataExporterFactory.create(
				dataExportTask, new ByteArrayOutputStream()));
	}

	private void _assertMatchInstance(
		BaseReportDataExporter baseReportDataExporter,
		DataExportTask.Type expectedType) {

		Class<?> dataExporterClass = null;

		if (expectedType == DataExportTask.Type.ACCOUNT) {
			dataExporterClass = AccountDataExporter.class;
		}
		else if (expectedType == DataExportTask.Type.INDIVIDUAL) {
			dataExporterClass = IndividualDataExporter.class;
		}
		else if (expectedType == DataExportTask.Type.PAGE) {
			dataExporterClass = PageDataExporter.class;
		}
		else if (expectedType == DataExportTask.Type.SEGMENT) {
			dataExporterClass = SegmentDataExporter.class;
		}

		MatcherAssert.assertThat(
			baseReportDataExporter, Matchers.instanceOf(dataExporterClass));
	}

	@Autowired
	private ReportDataExporterFactory _injectedReportDataExporterFactory;

	private final ReportDataExporterFactory _reportDataExporterFactory =
		new ReportDataExporterFactory(null);

}