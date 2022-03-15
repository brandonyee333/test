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

import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Inácio Nery
 */
@Import(JDBCTestConfiguration.class)
public class DataExportTaskRepositoryTest
	extends BaseRepositoryTestCase<DataExportTask, Long> {

	@BeforeEach
	public void setUp() {
		DataExportTask dataExportTask1 = new DataExportTask();

		dataExportTask1.setCompletedDate(new Date());
		dataExportTask1.setCreateDate(new Date());
		dataExportTask1.setStartedDate(new Date());
		dataExportTask1.setStatus(DataExportTask.Status.COMPLETED);
		dataExportTask1.setType(DataExportTask.Type.PAGE);

		DataExportTask dataExportTask2 = new DataExportTask();

		dataExportTask2.setCompletedDate(new Date());
		dataExportTask2.setCreateDate(new Date());
		dataExportTask2.setStartedDate(new Date());
		dataExportTask2.setStatus(DataExportTask.Status.ERROR);
		dataExportTask2.setType(DataExportTask.Type.PAGE);

		setUpRepository(dataExportTask1, dataExportTask2);
	}

	@Test
	public void testFindByStatus() {
		List<DataExportTask> dataExportTasks =
			_dataExportTaskRepository.findByStatus(
				DataExportTask.Status.COMPLETED);

		Assertions.assertEquals(
			1, dataExportTasks.size(), dataExportTasks.toString());

		Assertions.assertEquals(entityModels.get(0), dataExportTasks.get(0));
	}

	@Test
	public void testFindFirstByTypeOrderByIdDesc() {
		DataExportTask dataExportTask =
			_dataExportTaskRepository.findFirstByTypeOrderByIdDesc(
				DataExportTask.Type.PAGE);

		Assertions.assertEquals(entityModels.get(1), dataExportTask);
	}

	@Override
	protected PagingAndSortingRepository<DataExportTask, Long>
		getPagingAndSortingRepository() {

		return _dataExportTaskRepository;
	}

	@Autowired
	private DataExportTaskRepository _dataExportTaskRepository;

}