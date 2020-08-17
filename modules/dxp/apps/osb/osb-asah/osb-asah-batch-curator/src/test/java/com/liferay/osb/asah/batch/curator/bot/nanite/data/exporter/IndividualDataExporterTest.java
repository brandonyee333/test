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

import com.liferay.osb.asah.common.http.ReportHttp;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.io.ByteArrayOutputStream;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;

import org.mockito.Matchers;
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
			reportHttp.getIndividualsJSONObject(Matchers.eq("0"))
		).thenReturn(
			ResourceUtil.readResourceToJSONObject(
				"individuals_report.json", this)
		);

		Mockito.when(
			reportHttp.getIndividualsJSONObject(
				Matchers.eq("379649990292756725"))
		).thenReturn(
			ResourceUtil.readResourceToJSONObject("empty_report.json", this)
		);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		IndividualDataExporter individualDataExporter =
			new IndividualDataExporter(
				new JsonFactory(), byteArrayOutputStream, reportHttp);

		individualDataExporter.export();

		String[] actualIndividualsExportLines = StringUtils.split(
			byteArrayOutputStream.toString("UTF-8"), "\n");

		byteArrayOutputStream.close();

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"expected_individuals_export.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONAssert.assertEquals(
				jsonArray.getJSONObject(i),
				new JSONObject(actualIndividualsExportLines[i]), false);
		}
	}

}