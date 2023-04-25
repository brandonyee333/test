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

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.batch.curator.OSBAsahBatchCuratorSpringTestContext;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.PostgreSQLDataExporter;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import org.jooq.DSLContext;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Thiago Buarque
 */
public class PostgreSQLDataExporterTest
	implements OSBAsahBatchCuratorSpringTestContext {

	@BeforeEach
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");
	}

	@AfterEach
	public void tearDown() {
		_segmentRepository.deleteAll();
	}

	@Test
	public void testExportSegmentData() {
		Segment segment1 = new Segment();

		segment1.setAuthorName("Test Test");
		segment1.setCreateDate(DateUtil.toUTCDate("2023-04-02T16:02:01.824Z"));
		segment1.setFilter("(demographics/birthday/value lt '2023-04-20')");
		segment1.setId(12345L);
		segment1.setIsNew(true);
		segment1.setName("Segment 1");
		segment1.setState("READY");
		segment1.setStatus("ACTIVE");
		segment1.setType(Segment.Type.DYNAMIC);

		Segment segment2 = new Segment();

		segment2.setAuthorName("Test Test");
		segment2.setCreateDate(DateUtil.toUTCDate("2023-04-03T03:02:01.824Z"));
		segment2.setFilter("(demographics/emailAddress/value ne null)");
		segment2.setId(67890L);
		segment2.setIsNew(true);
		segment2.setName("Segment 2");
		segment2.setState("READY");
		segment2.setStatus("ACTIVE");
		segment2.setType(Segment.Type.DYNAMIC);

		_segmentRepository.saveAll(Arrays.asList(segment1, segment2));

		DataExportTask dataExportTask = _createDataExportTask(
			DateUtil.toUTCDate("2023-04-01T00:00:00.000Z"), 1L,
			DateUtil.toUTCDate("2023-04-28T23:23:59.000Z"));

		String fileName = String.valueOf(dataExportTask.getId());

		Assertions.assertDoesNotThrow(
			() -> {
				_exportSegmentData(dataExportTask, fileName);

				_extractJSONFileFromZip(fileName);

				try (FileInputStream fileInputStream = new FileInputStream(
						"data.json")) {

					JSONArray jsonArray = new JSONArray(
						IOUtils.toString(
							fileInputStream, StandardCharsets.UTF_8));

					JSONAssert.assertEquals(
						ResourceUtil.readResourceToJSONArray(
							"dependencies/expected_segments_export.json", this),
						jsonArray, true);
				}
				finally {
					File file1 = new File(fileName + ".zip");

					file1.delete();

					File file2 = new File("data.json");

					file2.delete();
				}
			});
	}

	private DataExportTask _createDataExportTask(
		Date fromDate, long id, Date toDate) {

		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setFromDate(fromDate);
		dataExportTask.setId(id);
		dataExportTask.setToDate(toDate);

		return dataExportTask;
	}

	private void _exportSegmentData(
			DataExportTask dataExportTask, String fileName)
		throws Exception, IOException {

		OutputStream zipOutputStream = _getZipOutputStream(fileName);

		DataExporter dataExporter = new PostgreSQLDataExporter(
			dataExportTask, "createDate", _dslContext, _jsonFactory,
			zipOutputStream, "segment");

		dataExporter.export();

		zipOutputStream.close();
	}

	private void _extractJSONFileFromZip(String fileName) throws IOException {
		try (ZipInputStream zipInputStream = new ZipInputStream(
				new FileInputStream(fileName + ".zip"),
				StandardCharsets.UTF_8)) {

			ZipEntry entry = zipInputStream.getNextEntry();

			FileOutputStream fileOutputStream = new FileOutputStream(
				entry.getName());

			while (zipInputStream.available() != 0) {
				fileOutputStream.write(zipInputStream.read());
			}

			zipInputStream.closeEntry();
		}
	}

	private OutputStream _getZipOutputStream(String fileName)
		throws IOException {

		ZipOutputStream zipOutputStream = new ZipOutputStream(
			new FileOutputStream(fileName + ".zip"));

		File file = new File("data.json");

		zipOutputStream.putNextEntry(new ZipEntry(file.getName()));

		return zipOutputStream;
	}

	@Autowired
	private DSLContext _dslContext;

	private final JsonFactory _jsonFactory = new JsonFactory();

	@Autowired
	private SegmentRepository _segmentRepository;

}