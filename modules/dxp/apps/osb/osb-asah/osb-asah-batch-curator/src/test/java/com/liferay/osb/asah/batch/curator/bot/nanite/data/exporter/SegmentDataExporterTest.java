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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.io.ByteArrayOutputStream;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Alejo Ceballos
 */
public class SegmentDataExporterTest {

	@Test
	public void testExport() throws Exception {
		ReportHttp reportHttp = Mockito.mock(ReportHttp.class);

		Mockito.when(
			reportHttp.getSegmentsJSONObject(
				ArgumentMatchers.eq("0"), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("segments_report.json", this)
		);

		Mockito.when(
			reportHttp.getSegmentsJSONObject(
				ArgumentMatchers.eq("327968823603500666"),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("empty_report.json", this)
		);

		String[] actualSegmentsExportLines;

		try (ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream()) {

			SegmentDataExporter segmentDataExporter = new SegmentDataExporter(
				DateUtil.toUTCDate("2018-12-15T16:02:01.824Z"),
				new JsonFactory(), byteArrayOutputStream, reportHttp,
				DateUtil.newDate());

			segmentDataExporter.export();

			actualSegmentsExportLines = StringUtils.split(
				byteArrayOutputStream.toString(StandardCharsets.UTF_8), "\n");
		}

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"expected_segments_export.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONAssert.assertEquals(
				jsonArray.getJSONObject(i),
				new JSONObject(actualSegmentsExportLines[i]), false);
		}
	}

}