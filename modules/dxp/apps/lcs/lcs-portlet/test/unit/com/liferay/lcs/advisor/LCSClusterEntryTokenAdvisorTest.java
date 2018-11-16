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

package com.liferay.lcs.advisor;

import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.platform.gateway.impl.LCSGatewayClientImpl;
import com.liferay.lcs.runnable.LCSThreadFactory;
import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.task.scheduler.impl.TaskSchedulerServiceImpl;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.util.portlet.PortletProps;

import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(
	{
		ConfigurationFactoryUtil.class, FileUtil.class, HandshakeTask.class,
		LCSClusterEntryTokenAdvisor.class, PortletClassLoaderUtil.class,
		PortletProps.class, PortletPropsValues.class
	}
)
@RunWith(PowerMockRunner.class)
public class LCSClusterEntryTokenAdvisorTest extends PowerMockito {

	@Before
	public void setUp() {
		_lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		doReturn(
			Boolean.TRUE
		).when(
			_lcsGatewayClient
		).isAvailable();

		_lcsKeyAdvisor = mock(LCSKeyAdvisor.class);

		doReturn(
			"aaaa-bbbb-cccc-dddd"
		).when(
			_lcsKeyAdvisor
		).getKey();

		_taskSchedulerService = mock(TaskSchedulerServiceImpl.class);
		_threadFactory = new LCSThreadFactory();
		_uptimeAdvisor = mock(UptimeAdvisor.class);

		mockStatic(
			ConfigurationFactoryUtil.class, FileUtil.class,
			PortletClassLoaderUtil.class, PortletProps.class,
			PortletPropsValues.class);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode200()
		throws Exception {

		_mockGetMessagesToReturnHandshakeResponseMessage(200);

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID
		);

		verifyPrivate(
			_lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode201()
		throws Exception {

		_mockGetMessagesToReturnHandshakeResponseMessage(201);

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH
		);

		verifyPrivate(
			_lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode202()
		throws Exception {

		_mockGetMessagesToReturnHandshakeResponseMessage(202);

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS
		);

		verifyPrivate(
			_lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsNotDeletedIfGatewayUnavailable() throws Exception {
		_mockSendMessageToThrowJSONWebServiceException();

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.HANDSHAKE_FAILED
		);

		verifyPrivate(
			_lcsClusterEntryTokenAdvisor, Mockito.never()
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	private HandshakeResponseMessage _createHandshakeResponseMessage(
		int errorCode) {

		HandshakeResponseMessage handshakeResponseMessage =
			new HandshakeResponseMessage();

		handshakeResponseMessage.setErrorCode(errorCode);
		handshakeResponseMessage.setErrorMessage(
			"{\"errorCode\": " + errorCode +
				", \"errorDescription\": \"Test\", \"status\": 400}");
		handshakeResponseMessage.setKey("mock");

		return handshakeResponseMessage;
	}

	private void _mockGetMessagesToReturnHandshakeResponseMessage(int errorCode)
		throws Exception {

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseMessage(errorCode));
				}
			}
		).when(
			_lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);
	}

	private void _mockSendMessageToThrowJSONWebServiceException()
		throws Exception {

		doThrow(
			new JSONWebServiceTransportException.CommunicationFailure(
				"Test gateway communication failure",
				new ExecutionException(new UnknownHostException("Test")))
		).when(
			_lcsGatewayClient
		).sendMessage(
			Matchers.any(Message.class)
		);
	}

	private HandshakeTask _spyHandshakeTask() throws Exception {
		HandshakeTask handshakeTask = spy(
			new HandshakeTask(
				1L, mock(LCSAlertAdvisor.class), _lcsClusterEntryTokenAdvisor,
				_lcsGatewayClient, _lcsKeyAdvisor, _taskSchedulerService,
				_threadFactory, _uptimeAdvisor));

		doReturn(
			new HandshakeMessage()
		).when(
			handshakeTask, "_createHandshakeMessage"
		);

		return handshakeTask;
	}

	private void _spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete()
		throws Exception {

		_lcsClusterEntryTokenAdvisor = spy(new LCSClusterEntryTokenAdvisor());

		doNothing(
		).when(
			_lcsClusterEntryTokenAdvisor, "_deleteLCSCLusterEntryTokenFile"
		);
	}

	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSGatewayClient _lcsGatewayClient;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private TaskSchedulerService _taskSchedulerService;
	private ThreadFactory _threadFactory;
	private UptimeAdvisor _uptimeAdvisor;

}