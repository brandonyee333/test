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

package com.liferay.lcs.client.internal.task.scheduler;

import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.BasePowerMockitoTestCase;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.runnable.LCSThreadFactory;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.task.UptimeTask;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.task.advisor.TaskAdvisor;
import com.liferay.lcs.client.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ScheduleTasksCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(
	{
		ConfigurationFactoryUtil.class, FileUtil.class, HandshakeTask.class,
		LCSClusterEntryTokenAdvisorImpl.class, TaskSchedulerServiceImpl.class,
		PortletClassLoaderUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class TaskSchedulerServiceImplTest extends BasePowerMockitoTestCase {

	@Before
	public void setUp() {
		_lcsConfigurationProvider = mock(LCSConfigurationProvider.class);

		_lcsKeyAdvisor = mock(LCSKeyAdvisor.class);

		doReturn(
			"aaaa-bbbb-cccc-dddd"
		).when(
			_lcsKeyAdvisor
		).getKey();

		_taskAdvisor = mock(TaskAdvisor.class);
		_threadFactory = new LCSThreadFactory();
		_uptimeAdvisor = mock(UptimeAdvisor.class);

		mockStatic(
			ConfigurationFactoryUtil.class, FileUtil.class,
			PortletClassLoaderUtil.class);
	}

	@Test
	public void testHandshakeRestartedIfGatewayUnavailable() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spySendMessageToThrowLCSGatewayException(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_FAILED, taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			taskSchedulerService
		).onLCSEvent(
			LCSEvent.HANDSHAKE_FAILED
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeHandshakeTask", Boolean.TRUE
		);
	}

	@Test
	public void testHandshakeRestartedIfHandshakeResponseNeverReceived()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			_mockGetMessagesToReturnCommandMessages(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_FAILED, taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			taskSchedulerService
		).onLCSEvent(
			LCSEvent.HANDSHAKE_FAILED
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeHandshakeTask", Boolean.TRUE
		);
	}

	@Test
	public void testResetIfNodeUnregistered() throws Exception {
		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			new LCSEventManager(), null, null);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				new LCSEventManager(), null, null);

		taskSchedulerService.onLCSEvent(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_restart"
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);
	}

	@Test
	public void testResetIfTokenInvalidated() throws Exception {
		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				new LCSEventManager(), null, null);

		taskSchedulerService.onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_restart"
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);
	}

	@Test
	public void testTokenCheckRestartedIfHandshakeExceptionErrorCode200()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				200, lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID
		);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeHandshakeTask", Matchers.anyBoolean()
		);
	}

	@Test
	public void testTokenCheckRestartedIfHandshakeExceptionErrorCode201()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				201, lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH,
			taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH
		);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeHandshakeTask", Matchers.anyBoolean()
		);
	}

	@Test
	public void testTokenCheckRestartedIfHandshakeExceptionErrorCode202()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				202, lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		TaskSchedulerService taskSchedulerService =
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS,
			taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS
		);

		verifyPrivate(
			taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			taskSchedulerService, Mockito.never()
		).invoke(
			"_executeHandshakeTask", Matchers.anyBoolean()
		);
	}

	private LCSGatewayClient _mockGetMessagesToReturnCommandMessages(
			LCSEventManager lcsEventManager)
		throws Exception {

		LCSGatewayClient lcsGatewayClient = spy(
			new LCSGatewayClientImpl(lcsEventManager));

		doReturn(
			Boolean.TRUE
		).when(
			lcsGatewayClient
		).isAvailable();

		doReturn(
			new ArrayList<Message>() {
				{
					add(new ScheduleTasksCommandMessage());
					add(new SendInstallationEnvironmentCommandMessage());
					add(new SendPortalPropertiesCommandMessage());
				}
			}
		).when(
			lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);

		return lcsGatewayClient;
	}

	private TaskSchedulerService
			_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
				LCSEventManager lcsEventManager,
				LCSGatewayClient lcsGatewayClient, HandshakeTask handshakeTask)
		throws Exception {

		TaskSchedulerServiceImpl taskSchedulerServiceImpl = spy(
			new TaskSchedulerServiceImpl(
				1000, handshakeTask, mock(LCSClusterEntryTokenCheckTask.class),
				_lcsConfigurationProvider, lcsEventManager, lcsGatewayClient,
				_lcsKeyAdvisor, _taskAdvisor, _threadFactory,
				new UptimeTask()));

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_cancelAllTasks"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_executeHandshakeTask",
			Matchers.anyBoolean()
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_executeLCSClusterEntryTokenCheckTask",
			Matchers.anyBoolean()
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_executeSignOffTask"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_onLCSGatewayServiceAvailable"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_onLCSGatewayServiceUnavailable"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			taskSchedulerServiceImpl, "_restart"
		);

		return taskSchedulerServiceImpl;
	}

	private LCSConfigurationProvider _lcsConfigurationProvider;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private TaskAdvisor _taskAdvisor;
	private ThreadFactory _threadFactory;
	private UptimeAdvisor _uptimeAdvisor;

}