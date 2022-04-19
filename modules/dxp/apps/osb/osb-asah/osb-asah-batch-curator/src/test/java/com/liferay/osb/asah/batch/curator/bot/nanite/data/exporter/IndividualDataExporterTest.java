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
 * @author Marcellus Tavares
 */
public class IndividualDataExporterTest {

	@Test
	public void testExport() throws Exception {
		ReportHttp reportHttp = Mockito.mock(ReportHttp.class);

		Mockito.when(
			reportHttp.getIndividualsJSONObject(
				ArgumentMatchers.eq("0"), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())
		).thenReturn(
			ResourceUtil.readResourceToJSONObject(
				"individuals_report.json", this)
		);

		Mockito.when(
			reportHttp.getIndividualsJSONObject(
				ArgumentMatchers.eq("379649990292756725"),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("empty_report.json", this)
		);

		String[] actualIndividualsExportLines;

		try (ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream()) {

			IndividualDataExporter individualDataExporter =
				new IndividualDataExporter(
					DateUtil.toUTCDate("2019-01-01T12:00:00.000Z"),
					new JsonFactory(), byteArrayOutputStream, reportHttp,
					DateUtil.newDate());

			individualDataExporter.export();

			actualIndividualsExportLines = StringUtils.split(
				byteArrayOutputStream.toString(
					StandardCharsets.UTF_8.toString()),
				"\n");
		}

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"expected_individuals_export.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONAssert.assertEquals(
				jsonArray.getJSONObject(i),
				new JSONObject(actualIndividualsExportLines[i]), false);
		}
	}

}