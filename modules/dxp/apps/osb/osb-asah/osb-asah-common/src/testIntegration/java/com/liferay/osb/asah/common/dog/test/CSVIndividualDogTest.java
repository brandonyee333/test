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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.CSVIndividualDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

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
public class CSVIndividualDogTest {

	@Test
	public void testAddCSVIndividuals() {
		_csvIndividualDog.addCSVIndividuals(
			Arrays.asList(new CSVIndividual(123L), new CSVIndividual(123L)));

		List<CSVIndividual> csvIndividuals =
			_csvIndividualDog.getCSVIndividuals(123L, 0, 20, Sort.asc("id"));

		Assert.assertEquals(
			csvIndividuals.toString(), 2, csvIndividuals.size());

		csvIndividuals.forEach(
			csvIndividual -> Assert.assertNotNull(csvIndividual.getId()));

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
			asahTask.getContextJSONObject(), false);
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

}