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

import com.liferay.lcs.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.platform.gateway.impl.LCSGatewayClientImpl;
import com.liferay.lcs.rest.client.LCSClusterEntryToken;
import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.task.scheduler.impl.TaskSchedulerServiceImpl;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.util.EncryptorException;

import java.io.File;
import java.io.IOException;

import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(
	{
		FileUtil.class, HandshakeTask.class, LCSClusterEntryTokenAdvisor.class,
		LCSUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class LCSAlertAdvisorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(FileUtil.class, LCSUtil.class);

		_lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		_lcsAlertAdvisor = spy(new LCSAlertAdvisor(_lcsGatewayClient));

		_taskSchedulerService = mock(TaskSchedulerServiceImpl.class);
	}

	@Test
	public void testErrorEnvironmentMismatch() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		_mockGetMessagesToReturnHandshakeResponseMessage(201);

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_ENVIRONMENT_MISMATCH));
	}

	@Test
	public void testErrorInvalidTokenIfEncryptorExceptionThrown()
		throws Exception {

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			"test-file-name"
		).when(
			_lcsClusterEntryTokenAdvisor, "getLCSClusterEntryTokenFileName"
		);

		doThrow(
			new EncryptorException("Test encryptor exception")
		).when(
			_lcsClusterEntryTokenAdvisor, "decrypt", Matchers.any(byte[].class),
			Matchers.anyInt()
		);

		when(
			FileUtil.getBytes(Matchers.any(File.class))
		).thenReturn(
			new byte[0]
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor,
				_taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidTokenLCSAlert() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		_mockGetMessagesToReturnHandshakeResponseMessage(200);

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidUserCredentialsLCSAlert() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		_mockGetMessagesToReturnHandshakeResponseMessage(202);

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid user credentials LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_USER_CREDENTIALS));
	}

	@Test
	public void testLCSClusterEntryAdvisorExceptionIsThrown() throws Exception {
		Exception[] exceptions = {
			new IOException("Test lcs cluster entry token file IO exception"),
			new MissingLCSClusterEntryTokenException(
				"Test lcs cluster entry token file missing exception"),
			new MultipleLCSClusterEntryTokenException(
				"Test lcs cluster entry token file multiple token exception")
		};

		for (Exception exception : exceptions) {
			_testLCSClusterEntryAdvisorExceptionIsThrown(exception);

			Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

			if (exception instanceof MissingLCSClusterEntryTokenException) {
				Assert.assertTrue(
					"Missing token LCS Alert expected",
					lcsAlerts.contains(LCSAlert.ERROR_MISSING_TOKEN));
			}
			else if (exception instanceof
						MultipleLCSClusterEntryTokenException) {

				Assert.assertTrue(
					"Multiple tokens LCS Alert expected",
					lcsAlerts.contains(LCSAlert.ERROR_MULTIPLE_TOKENS));
			}
		}
	}

	@Test
	public void testLCSPortletNotAuthorized() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			new LCSClusterEntryToken()
		).when(
			_lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		_mockLCSUtilIsLCSPortletAuthorizedToReturn(Boolean.FALSE);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor,
				_taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testSuccessValidToken() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			new LCSClusterEntryToken()
		).when(
			_lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		_mockLCSUtilIsLCSPortletAuthorizedToReturn(Boolean.TRUE);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor,
				_taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Valid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.SUCCESS_VALID_TOKEN));
	}

	@Test
	public void testWarningHandshakeFailedLCSAlert() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		_mockGetMessagesToReturnHandshakeExpiredResponseMessage();

		HandshakeTask handshakeTask = _spyHandshakeTask();

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = _lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Handshake failed LCS Alert expected",
			lcsAlerts.contains(LCSAlert.WARNING_HANDSHAKE_FAILED));
	}

	private HandshakeResponseMessage _createHandshakeResponseExpiredMessage() {
		HandshakeResponseMessage handshakeResponseMessage =
			new HandshakeResponseMessage();

		handshakeResponseMessage.setHandshakeExpiredError(true);
		handshakeResponseMessage.setKey("mock");

		return handshakeResponseMessage;
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

	private void _mockGetMessagesToReturnHandshakeExpiredResponseMessage()
		throws Exception {

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseExpiredMessage());
				}
			}
		).when(
			_lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);
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

	private void _mockLCSUtilIsLCSPortletAuthorizedToReturn(boolean returnValue)
		throws Exception {

		when(
			LCSUtil.isLCSPortletAuthorized(
				Matchers.anyString(), Matchers.anyString())
		).thenReturn(
			returnValue
		);
	}

	private void _mockLCSUtilToThrowJSONWebServiceException() throws Exception {
		doThrow(
			new JSONWebServiceTransportException.CommunicationFailure(
				"Test LCS portal communication failure",
				new ExecutionException(new UnknownHostException("Test")))
		).when(
			LCSUtil.class
		);

		LCSUtil.isLCSPortletAuthorized(
			Matchers.anyString(), Matchers.anyString());
	}

	private HandshakeTask _spyHandshakeTask() throws Exception {
		HandshakeTask handshakeTask = spy(
			new HandshakeTask(
				1L, _lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor,
				_lcsGatewayClient, mock(LCSKeyAdvisor.class),
				_taskSchedulerService, null, new UptimeAdvisor(null)));

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

	private void _testLCSClusterEntryAdvisorExceptionIsThrown(
			Exception exception)
		throws Exception {

		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doThrow(
			exception
		).when(
			_lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor,
				_taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();
	}

	private LCSAlertAdvisor _lcsAlertAdvisor;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSGatewayClient _lcsGatewayClient;
	private TaskSchedulerService _taskSchedulerService;

}