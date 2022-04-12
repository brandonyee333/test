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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataExportTaskDog;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Date;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejo Ceballos
 */
public class DataExportTaskDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddDataExportTask() {
		Date todayDate = DateUtil.newDayDate();

		Date tomorrowDate = DateUtil.addDays(todayDate, 1);

		DataExportTask dataExportTask1 = _daDataExportTaskDog.addDataExportTask(
			todayDate, tomorrowDate, DataExportTask.Type.PAGE);

		MatcherAssert.assertThat(
			dataExportTask1.getCreateDate(),
			Matchers.lessThanOrEqualTo(new Date()));
		Assertions.assertEquals(dataExportTask1.getFromDate(), todayDate);
		Assertions.assertEquals(
			dataExportTask1.getStatus(), DataExportTask.Status.PENDING);
		Assertions.assertEquals(dataExportTask1.getToDate(), tomorrowDate);
		Assertions.assertEquals(
			dataExportTask1.getType(), DataExportTask.Type.PAGE);

		DataExportTask dataExportTask2 = _daDataExportTaskDog.getDataExportTask(
			dataExportTask1.getId());

		Assertions.assertNotNull(dataExportTask2);
		Assertions.assertEquals(dataExportTask1, dataExportTask2);
	}

	@Autowired
	private DataExportTaskDog _daDataExportTaskDog;

}