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

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.BasePowerMockitoTest;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.runnable.LCSThreadFactory;
import com.liferay.lcs.client.internal.task.HandshakeTask;
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
		TaskSchedulerServiceImpl.class, PortletClassLoaderUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class TaskSchedulerServiceImplTest extends BasePowerMockitoTest {

	@Before
	public void setUp() {
		_lcsClusterEntryTokenAdvisor = new LCSClusterEntryTokenAdvisorImpl();
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

		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_FAILED, _taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.HANDSHAKE_FAILED
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
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

		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.HANDSHAKE_FAILED, _taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.HANDSHAKE_FAILED
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeHandshakeTask", Boolean.TRUE
		);
	}

	@Test
	public void testResetIfNodeUnregistered() throws Exception {
		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			new LCSEventManager(), null, null);

		_taskSchedulerService.onLCSEvent(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_restart"
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Matchers.anyBoolean()
		);
	}

	@Test
	public void testResetIfTokenInvalidated() throws Exception {
		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			new LCSEventManager(), null, null);

		_taskSchedulerService.onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_restart"
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
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

		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, _taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
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

		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH,
			_taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
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

		_spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			lcsEventManager, lcsGatewayClient, handshakeTask);

		lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS,
			_taskSchedulerService);

		handshakeTask.run();

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.times(1)
		).invoke(
			"_executeLCSClusterEntryTokenCheckTask", Boolean.TRUE
		);

		verifyPrivate(
			_taskSchedulerService, Mockito.never()
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

	private void _spyTaskSchedulerServiceImplToDoNothingAfterOnEvent(
			LCSEventManager lcsEventManager, LCSGatewayClient lcsGatewayClient,
			HandshakeTask handshakeTask)
		throws Exception {

		_taskSchedulerService = spy(
			new TaskSchedulerServiceImpl(
				1000, handshakeTask, _lcsClusterEntryTokenAdvisor,
				_lcsConfigurationProvider, lcsEventManager, lcsGatewayClient,
				_lcsKeyAdvisor, _taskAdvisor, _threadFactory, _uptimeAdvisor));

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_cancelAllTasks"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_executeHandshakeTask",
			Matchers.anyBoolean()
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_executeLCSClusterEntryTokenCheckTask",
			Matchers.anyBoolean()
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_executeSignOffTask"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_onLCSGatewayServiceAvailable"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_onLCSGatewayServiceUnavailable"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			_taskSchedulerService, "_restart"
		);
	}

	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSConfigurationProvider _lcsConfigurationProvider;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private TaskAdvisor _taskAdvisor;
	private TaskSchedulerService _taskSchedulerService;
	private ThreadFactory _threadFactory;
	private UptimeAdvisor _uptimeAdvisor;

}