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

package com.liferay.osb.asah.batch.curator.bot.scheduling.test;

import com.liferay.osb.asah.batch.curator.OSBAsahBatchCuratorSpringTestContext;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskManager;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskRunnable;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskScheduler;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author André Miranda
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class,
		ResetMocksTestExecutionListener.class
	}
)
public class AsahTaskManagerTest
	implements OSBAsahBatchCuratorSpringTestContext {

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteAsahTask() {
		_asahTaskManager.deleteAsahTask(450553576847486527L);

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assertions.assertEquals(3, asahTasks.size(), asahTasks.toString());
	}

	@Test
	public void testExecuteAsahTask1() {
		AsahTask asahTask = new AsahTask("DataControlNanite", null, "test");

		asahTask.setId(450553576847486527L);

		_asahTaskManager.executeAsahTask(asahTask, false);

		ArgumentCaptor<AsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(AsahTaskRunnable.class);

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).execute(
			argumentCaptor.capture()
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assertions.assertArrayEquals(
			new String[] {"DataControlNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assertions.assertEquals(
			Long.valueOf("450553576847486527"),
			asahTaskRunnable.getAsahTaskId());
		Assertions.assertEquals("test", asahTaskRunnable.getProjectId());
		Assertions.assertFalse(asahTaskRunnable.isForce());
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtask.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExecuteAsahTask2() {
		_asahTaskManager.executeAsahTask(450553576847486528L, false);

		ArgumentCaptor<AsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(AsahTaskRunnable.class);

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).executeUpdateDynamicMembershipsNanite(
			argumentCaptor.capture()
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assertions.assertArrayEquals(
			new String[] {"UpdateDynamicMembershipsNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assertions.assertEquals(
			Long.valueOf("450553576847486528"),
			asahTaskRunnable.getAsahTaskId());
		Assertions.assertEquals("test", asahTaskRunnable.getProjectId());
		Assertions.assertFalse(asahTaskRunnable.isForce());
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExecuteAsahTasks1() {
		_asahTaskManager.executeAsahTasks();

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(2)
		).execute(
			ArgumentMatchers.any(AsahTaskRunnable.class)
		);
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExecuteAsahTasks2() {
		_asahTaskManager.executeAsahTasks(
			Arrays.asList(450553576847486527L, 450553576847486529L), false);

		ArgumentCaptor<AsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(AsahTaskRunnable.class);

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).executeUpdateDynamicMembershipsNanite(
			argumentCaptor.capture()
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assertions.assertArrayEquals(
			new String[] {"UpdateDynamicMembershipsNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assertions.assertEquals(
			Long.valueOf("450553576847486529"),
			asahTaskRunnable.getAsahTaskId());
		Assertions.assertFalse(asahTaskRunnable.isForce());

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).execute(
			argumentCaptor.capture()
		);

		asahTaskRunnable = argumentCaptor.getValue();

		Assertions.assertArrayEquals(
			new String[] {"DataControlNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assertions.assertEquals(
			Long.valueOf("450553576847486527"),
			asahTaskRunnable.getAsahTaskId());
		Assertions.assertFalse(asahTaskRunnable.isForce());
	}

	@Test
	public void testScheduleAsahTaskFail() {
		Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _asahTaskManager.scheduleAsahTask(
				new AsahTask("Foo", null, null)));
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testScheduleAsahTasks() {
		_asahTaskManager.scheduleAsahTasks();

		ArgumentCaptor<AsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(AsahTaskRunnable.class);

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).schedule(
			ArgumentMatchers.eq("0 0 0 * * ?"), argumentCaptor.capture(),
			ArgumentMatchers.eq("450553576847486530")
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assertions.assertArrayEquals(
			new String[] {"DataControlNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assertions.assertEquals(
			Long.valueOf(450553576847486530L),
			asahTaskRunnable.getAsahTaskId());
		Assertions.assertEquals("test", asahTaskRunnable.getProjectId());
		Assertions.assertFalse(asahTaskRunnable.isForce());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AsahTaskManager _asahTaskManager;

	@MockBean
	private AsahTaskScheduler _asahTaskScheduler;

}