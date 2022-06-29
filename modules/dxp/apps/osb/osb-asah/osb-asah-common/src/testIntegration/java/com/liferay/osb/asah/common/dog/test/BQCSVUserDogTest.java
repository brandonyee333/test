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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.BQCSVUserDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class BQCSVUserDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddBQCSVUsers() {
		DataSource dataSource = new DataSource();

		dataSource.setId(123L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

		_bqCSVUserDog.addBQCSVUsers(
			Arrays.asList(new BQCSVUser(123L), new BQCSVUser(123L)));

		List<BQCSVUser> bqCSVUsers = _bqCSVUserDog.getBQCSVUsers(
			123L, 0, 20, Sort.asc("id"));

		Assertions.assertEquals(2, bqCSVUsers.size(), bqCSVUsers.toString());

		bqCSVUsers.forEach(
			bqCSVUser -> Assertions.assertNotNull(bqCSVUser.getId()));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		Assertions.assertEquals("CSVUsersNanite", asahTask.getClassName());
		Assertions.assertNotNull(asahTask.getId());

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
	private BQCSVUserDog _bqCSVUserDog;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}