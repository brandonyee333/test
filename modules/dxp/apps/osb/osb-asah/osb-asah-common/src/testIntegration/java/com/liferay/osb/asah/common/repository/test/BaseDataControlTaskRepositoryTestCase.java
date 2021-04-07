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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.model.DataControlTask;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.repository.DataControlTaskRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDataControlTaskRepositoryTestCase
	extends BaseRepositoryTestCase<DataControlTask> {

	@Before
	public void setUp() {
		DataControlTask dataControlTask = new DataControlTask();

		dataControlTask.setBatchId(123456L);
		dataControlTask.setCreateDate(new Date());
		dataControlTask.setEmailAddress("joe.bloggs@liferay.com");
		dataControlTask.setOwnerId("1");
		dataControlTask.setStatus(
			String.valueOf(DataControlTaskStatus.PENDING));
		dataControlTask.setType(String.valueOf(DataControlTaskType.ACCESS));

		setUpRepository(dataControlTask);

		_dataControlTask = entityModels.get(0);
	}

	@Test
	public void testSearchDataControlTasksByEmailAddress() {
		List<DataControlTask> dataControlTasks =
			_dataControlTaskRepository.searchDataControlTasks(
				null, "joe", null, null, null, PageRequest.of(0, 10));

		Assert.assertEquals(
			dataControlTasks.toString(), 1, dataControlTasks.size());
		Assert.assertEquals(_dataControlTask, dataControlTasks.get(0));
	}

	@Test
	public void testSearchDataControlTasksByStatuses() {
		List<DataControlTask> dataControlTasks =
			_dataControlTaskRepository.searchDataControlTasks(
				null, null, null,
				Arrays.asList(String.valueOf(DataControlTaskStatus.PENDING)),
				null, PageRequest.of(0, 10));

		Assert.assertEquals(
			dataControlTasks.toString(), 1, dataControlTasks.size());
		Assert.assertEquals(_dataControlTask, dataControlTasks.get(0));
	}

	@Override
	protected CrudRepository<DataControlTask, Long> getCrudRepository() {
		return _dataControlTaskRepository;
	}

	private DataControlTask _dataControlTask;

	@Autowired
	private DataControlTaskRepository _dataControlTaskRepository;

}