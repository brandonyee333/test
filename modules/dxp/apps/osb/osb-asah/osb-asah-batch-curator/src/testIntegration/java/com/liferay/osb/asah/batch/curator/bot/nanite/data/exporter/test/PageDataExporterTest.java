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

import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.PageDataExporter;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class PageDataExporterTest {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testExport() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		PageDataExporter pageDataExporter = new PageDataExporter(
			new JsonFactory(), byteArrayOutputStream,
			_elasticsearchInvokerFactory.forCerebroInfo());

		pageDataExporter.export();

		String[] actualPagesExportLines = StringUtils.split(
			byteArrayOutputStream.toString("UTF-8"), "\n");

		byteArrayOutputStream.close();

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/expected_pages_export.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONAssert.assertEquals(
				jsonArray.getJSONObject(i),
				new JSONObject(actualPagesExportLines[i]), false);
		}
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}