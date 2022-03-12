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
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.json.JSONArray;

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
		DataSource dataSource = new DataSource();

		dataSource.setId(123L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

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

		Assertions.assertEquals(2, _csvIndividualRepository.count());

		_assertIds(_csvIndividualRepository.findAll());

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

	private void _assertIds(Iterable<CSVIndividual> csvIndividuals) {
		for (CSVIndividual csvIndividual : csvIndividuals) {
			Assertions.assertEquals(123L, csvIndividual.getDataSourceId());
			Assertions.assertNotNull(csvIndividual.getId());
		}
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

	@Autowired
	private CSVIndividualsRestController _csvIndividualsRestController;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}