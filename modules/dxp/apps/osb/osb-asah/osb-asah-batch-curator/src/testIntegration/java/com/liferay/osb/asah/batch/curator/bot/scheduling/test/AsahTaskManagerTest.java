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

import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskManager;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskRunnable;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskScheduler;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
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
public class AsahTaskManagerTest {

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteAsahTask() {
		_asahTaskManager.deleteAsahTask(450553576847486527L);

		Assert.assertEquals(
			3,
			_elasticsearchInvoker.count(
				"OSBAsahTasks", QueryBuilders.matchAllQuery()));
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

		Assert.assertArrayEquals(
			new String[] {"DataControlNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486527", asahTaskRunnable.getAsahTaskId());
		Assert.assertEquals("test", asahTaskRunnable.getProjectId());
		Assert.assertFalse(asahTaskRunnable.isForce());
	}

	@Test
	public void testExecuteAsahTask2() {
		AsahTask asahTask = new AsahTask(
			"UpdateDynamicMembershipsNanite", null, "test");

		asahTask.setId(450553576847486528L);

		_asahTaskManager.executeAsahTask(asahTask, false);

		ArgumentCaptor<AsahTaskRunnable> argumentCaptor =
			ArgumentCaptor.forClass(AsahTaskRunnable.class);

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(1)
		).executeUpdateDynamicMembershipsNanite(
			argumentCaptor.capture()
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assert.assertArrayEquals(
			new String[] {"UpdateDynamicMembershipsNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486528", asahTaskRunnable.getAsahTaskId());
		Assert.assertEquals("test", asahTaskRunnable.getProjectId());
		Assert.assertFalse(asahTaskRunnable.isForce());
	}

	@ElasticsearchIndex(
		name = "OSBAsahTasks", resourcePath = "osbasahtasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExecuteAsahTasks() {
		_asahTaskManager.executeAsahTasks();

		Mockito.verify(
			_asahTaskScheduler, Mockito.times(3)
		).execute(
			Mockito.any(AsahTaskRunnable.class)
		);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScheduleAsahTaskFail() {
		_asahTaskManager.scheduleAsahTask(new AsahTask("Foo", null, null));
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
			Mockito.eq("0 0 0 * * ?"), argumentCaptor.capture(),
			Mockito.eq("450553576847486530")
		);

		AsahTaskRunnable asahTaskRunnable = argumentCaptor.getValue();

		Assert.assertArrayEquals(
			new String[] {"DataControlNanite"},
			asahTaskRunnable.getNaniteClassNames());
		Assert.assertEquals(
			"450553576847486530", asahTaskRunnable.getAsahTaskId());
		Assert.assertEquals("test", asahTaskRunnable.getProjectId());
		Assert.assertFalse(asahTaskRunnable.isForce());
	}

	@Autowired
	private AsahTaskManager _asahTaskManager;

	@MockBean
	private AsahTaskScheduler _asahTaskScheduler;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}