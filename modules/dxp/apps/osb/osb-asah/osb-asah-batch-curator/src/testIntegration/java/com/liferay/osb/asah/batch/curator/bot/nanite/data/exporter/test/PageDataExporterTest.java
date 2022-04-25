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
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.PageDataExporter;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.io.ByteArrayOutputStream;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Marcellus Tavares
 */
public class PageDataExporterTest
	implements OSBAsahBatchCuratorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testCannotBeInstantiatedWithoutInvoker() {
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> new PageDataExporter(
				DateUtil.toUTCDate("2022-01-04T11:00:00.000Z"),
				new JsonFactory(), new ByteArrayOutputStream(),
				DateUtil.toUTCDate("2022-01-06T13:00:00.000Z"), null));
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExport() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		PageDataExporter pageDataExporter = new PageDataExporter(
			DateUtil.toUTCDate("2022-01-04T11:00:00.000Z"), new JsonFactory(),
			byteArrayOutputStream,
			DateUtil.toUTCDate("2022-01-06T13:00:00.000Z"),
			_elasticsearchInvoker);

		pageDataExporter.export();

		String[] actualPagesExportLines = StringUtils.split(
			byteArrayOutputStream.toString(StandardCharsets.UTF_8.toString()),
			"\n");

		byteArrayOutputStream.close();

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/expected_pages_export_1.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONAssert.assertEquals(
				jsonArray.getJSONObject(i),
				new JSONObject(actualPagesExportLines[i]), false);
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}