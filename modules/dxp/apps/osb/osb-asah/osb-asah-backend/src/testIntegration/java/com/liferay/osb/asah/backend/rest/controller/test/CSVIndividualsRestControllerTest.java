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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.CSVIndividualsRestController;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class CSVIndividualsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testPostCSVIndividuals() throws Exception {
		JSONArray jsonArray = JSONUtil.putAll(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"dataSourceIndividualPK", RandomTestUtil.randomUUID()
			).put(
				"fields", JSONUtil.put("foo", "bar")
			),
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"dataSourceIndividualPK", RandomTestUtil.randomUUID()
			).put(
				"fields", JSONUtil.put("foo2", "bar2")
			));

		_csvIndividualsRestController.postCSVIndividuals(jsonArray.toString());

		JSONArray csvIndividualsJSONArray = _elasticsearchInvoker.get(
			"csv-individuals");

		Assertions.assertEquals(2, csvIndividualsJSONArray.length());

		JSONAssert.assertEquals(jsonArray, csvIndividualsJSONArray, false);

		_assertIds(csvIndividualsJSONArray);

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		Assertions.assertEquals(
			"CSVIndividualsNanite", asahTask.getClassName());
		Assertions.assertNull(asahTask.getCronExpression());
		Assertions.assertNotNull(asahTask.getId());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"type", "reprocess"
			),
			asahTask.getContextJSONObject(), false);
	}

	private void _assertIds(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Assertions.assertNotNull(jsonObject.getString("id"));
		}
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CSVIndividualsRestController _csvIndividualsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}