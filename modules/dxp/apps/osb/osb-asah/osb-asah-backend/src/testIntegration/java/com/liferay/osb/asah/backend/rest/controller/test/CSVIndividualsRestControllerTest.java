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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.CSVIndividualsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class CSVIndividualsRestControllerTest {

	@Test
	public void testPostCSVIndividuals() {
		JSONArray jsonArray = JSONUtil.putAll(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"foo", "bar"
			),
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"foo2", "bar2"
			));

		_csvIndividualsRestController.postCSVIndividuals(jsonArray.toString());

		JSONArray csvIndividualsJSONArray = _elasticsearchInvoker.get(
			"csv-individuals");

		Assert.assertEquals(2, csvIndividualsJSONArray.length());

		JSONAssert.assertEquals(jsonArray, csvIndividualsJSONArray, false);

		_assertIds(csvIndividualsJSONArray);

		JSONArray asahTasksJSONArray = _elasticsearchInvoker.get(
			"OSBAsahTasks");

		Assert.assertEquals(1, asahTasksJSONArray.length());

		JSONAssert.assertEquals(
			JSONUtil.put(
				JSONUtil.put(
					"className", "CSVIndividualsNanite"
				).put(
					"context",
					JSONUtil.put(
						"dataSourceId", "123"
					).put(
						"type", "reprocess"
					)
				)),
			asahTasksJSONArray, false);

		_assertIds(asahTasksJSONArray);
	}

	private void _assertIds(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Assert.assertNotNull(jsonObject.getString("id"));
		}
	}

	@Autowired
	private CSVIndividualsRestController _csvIndividualsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}