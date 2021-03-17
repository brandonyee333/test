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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoCSVIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

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
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoCSVIndividualDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddCSVIndividuals() {
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

		_faroInfoCSVIndividualDog.addCSVIndividuals(jsonArray);

		JSONArray csvIndividualsJSONArray = faroInfoElasticsearchInvoker.get(
			"csv-individuals");

		Assert.assertEquals(2, csvIndividualsJSONArray.length());

		JSONAssert.assertEquals(jsonArray, csvIndividualsJSONArray, false);

		_assertIds(csvIndividualsJSONArray);

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());

		AsahTask asahTask = asahTasks.get(0);

		Assert.assertEquals("CSVIndividualsNanite", asahTask.getClassName());
		Assert.assertNotNull(asahTask.getId());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"type", "reprocess"
			),
			new JSONObject(asahTask.getContext()), false);
	}

	private void _assertIds(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Assert.assertNotNull(jsonObject.getString("id"));
		}
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FaroInfoCSVIndividualDog _faroInfoCSVIndividualDog;

}