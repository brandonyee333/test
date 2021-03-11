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

import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskManager;
import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskRunnable;
import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskScheduler;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class OSBAsahTaskManagerTest {

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteOSBAsahTask() {
		_osbAsahTaskManager.deleteOSBAsahTask(450553576847486527L);

		Assert.assertEquals(
			3,
			_elasticsearchInvoker.count(
				"OSBAsahTasks", QueryBuilders.matchAllQuery()));
	}

	@Test
	public void testExecuteOSBAsahTask1() {
		AsahTask asahTask = new AsahTask("DataControlNanite", null, "test");

		asahTask.setId(450553576847486527L);

		_osbAsahTaskManager.executeOSBAsahTask(asahTask, false);

		ArgumentCaptor<OSBAsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(OSBAsahTaskRunnable.class);

		Mockito.verify(
			_osbAsahTaskScheduler, Mockito.times(1)
		).execute(
			argumentCaptor.capture()
		);

		OSBAsahTaskRunnable osbAsahTaskRunnable = argumentCaptor.getValue();

		Assert.assertArrayEquals(
			new String[] {"DataControlNanite"},
			osbAsahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486527", osbAsahTaskRunnable.getOSBAsahTaskId());
		Assert.assertEquals("test", osbAsahTaskRunnable.getProjectId());
		Assert.assertFalse(osbAsahTaskRunnable.isForce());
	}

	@Test
	public void testExecuteOSBAsahTask2() {
		AsahTask asahTask = new AsahTask(
			"UpdateDynamicMembershipsNanite", null, "test");

		asahTask.setId(450553576847486528L);

		_osbAsahTaskManager.executeOSBAsahTask(asahTask, false);

		ArgumentCaptor<OSBAsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(OSBAsahTaskRunnable.class);

		Mockito.verify(
			_osbAsahTaskScheduler, Mockito.times(1)
		).executeUpdateDynamicMembershipsNanite(
			argumentCaptor.capture()
		);

		OSBAsahTaskRunnable osbAsahTaskRunnable = argumentCaptor.getValue();

		Assert.assertArrayEquals(
			new String[] {"UpdateDynamicMembershipsNanite"},
			osbAsahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486528", osbAsahTaskRunnable.getOSBAsahTaskId());
		Assert.assertEquals("test", osbAsahTaskRunnable.getProjectId());
		Assert.assertFalse(osbAsahTaskRunnable.isForce());
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExecuteOSBAsahTasks() {
		_osbAsahTaskManager.executeOSBAsahTasks();

		Mockito.verify(
			_osbAsahTaskScheduler, Mockito.times(3)
		).execute(
			Mockito.any(OSBAsahTaskRunnable.class)
		);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScheduleOSBAsahTaskFail() {
		_osbAsahTaskManager.scheduleOSBAsahTask(
			new AsahTask("Foo", null, null));
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testScheduleOSBAsahTasks() {
		_osbAsahTaskManager.scheduleOSBAsahTasks();

		ArgumentCaptor<OSBAsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(OSBAsahTaskRunnable.class);

		Mockito.verify(
			_osbAsahTaskScheduler, Mockito.times(1)
		).schedule(
			Mockito.eq("0 0 0 * * ?"), argumentCaptor.capture(),
			Mockito.eq("450553576847486530")
		);

		OSBAsahTaskRunnable osbAsahTaskRunnable = argumentCaptor.getValue();

		Assert.assertArrayEquals(
			new String[] {"DataControlNanite"},
			osbAsahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486530", osbAsahTaskRunnable.getOSBAsahTaskId());
		Assert.assertEquals("test", osbAsahTaskRunnable.getProjectId());
		Assert.assertFalse(osbAsahTaskRunnable.isForce());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private OSBAsahTaskManager _osbAsahTaskManager;

	@MockBean
	private OSBAsahTaskScheduler _osbAsahTaskScheduler;

}