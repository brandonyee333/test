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
import com.liferay.lcs.task.scheduler.impl.TaskSchedulerServiceImpl;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Set;

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
	}

	@Test
	public void testErrorEnvironmentMismatch() throws Exception {
		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsAlertAdvisor,
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(),
			_mockGetMessagesToReturnHandshakeResponseMessage(201));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_ENVIRONMENT_MISMATCH));
	}

	@Test
	public void testErrorInvalidTokenIfEncryptorExceptionThrown()
		throws Exception {

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			"test-file-name"
		).when(
			lcsClusterEntryTokenAdvisor, "getLCSClusterEntryTokenFileName"
		);

		doThrow(
			new EncryptorException("Test encryptor exception")
		).when(
			lcsClusterEntryTokenAdvisor, "decrypt", Matchers.any(byte[].class),
			Matchers.anyInt()
		);

		when(
			FileUtil.getBytes(Matchers.any(File.class))
		).thenReturn(
			new byte[0]
		);

		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsAlertAdvisor, lcsClusterEntryTokenAdvisor,
				mock(TaskSchedulerServiceImpl.class));

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidTokenLCSAlert() throws Exception {
		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsAlertAdvisor,
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(),
			_mockGetMessagesToReturnHandshakeResponseMessage(200));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidUserCredentialsLCSAlert() throws Exception {
		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsAlertAdvisor,
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(),
			_mockGetMessagesToReturnHandshakeResponseMessage(202));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid user credentials LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_USER_CREDENTIALS));
	}

	@Test
	public void testHandshakeSuccessLCSAlert() throws Exception {
		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		lcsAlertAdvisor.add(LCSAlert.WARNING_HANDSHAKE_FAILED);

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsAlertAdvisor, mock(LCSClusterEntryTokenAdvisor.class),
			_mockGetMessagesToReturnHandshakeSuccessMessage());

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertFalse(
			"Handshake failed LCS Alert not expected",
			lcsAlerts.contains(LCSAlert.WARNING_HANDSHAKE_FAILED));
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
			LCSAlertAdvisor lcsAlertAdvisor = spy(
				new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

			_testLCSClusterEntryAdvisorExceptionIsThrown(
				exception, lcsAlertAdvisor);

			Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

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
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		_mockLCSUtilIsLCSPortletAuthorizedToReturn(Boolean.FALSE);

		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsAlertAdvisor, lcsClusterEntryTokenAdvisor,
				mock(TaskSchedulerServiceImpl.class));

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testSuccessValidToken() throws Exception {
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		_mockLCSUtilIsLCSPortletAuthorizedToReturn(Boolean.TRUE);

		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsAlertAdvisor, lcsClusterEntryTokenAdvisor,
				mock(TaskSchedulerServiceImpl.class));

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Valid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.SUCCESS_VALID_TOKEN));
	}

	@Test
	public void testWarningHandshakeFailedLCSAlert() throws Exception {
		LCSAlertAdvisor lcsAlertAdvisor = spy(
			new LCSAlertAdvisor(mock(LCSGatewayClientImpl.class)));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsAlertAdvisor,
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(),
			_mockGetMessagesToReturnHandshakeExpiredResponseMessage());

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

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

	private HandshakeResponseMessage _createHandshakeResponseSuccessMessage() {
		HandshakeResponseMessage handshakeResponseMessage =
			new HandshakeResponseMessage();

		handshakeResponseMessage.setHandshakeExpiredError(false);
		handshakeResponseMessage.setLatestLCSPortletBuildNumber(500);
		handshakeResponseMessage.setKey("mock");

		return handshakeResponseMessage;
	}

	private LCSGatewayClient
			_mockGetMessagesToReturnHandshakeExpiredResponseMessage()
		throws Exception {

		LCSGatewayClient lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseExpiredMessage());
				}
			}
		).when(
			lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);

		return lcsGatewayClient;
	}

	private LCSGatewayClient _mockGetMessagesToReturnHandshakeResponseMessage(
			int errorCode)
		throws Exception {

		LCSGatewayClient lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseMessage(errorCode));
				}
			}
		).when(
			lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);

		return lcsGatewayClient;
	}

	private LCSGatewayClient _mockGetMessagesToReturnHandshakeSuccessMessage()
		throws Exception {

		LCSGatewayClient lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		doReturn(
			new ArrayList<Message>() {
				{
					add(_createHandshakeResponseSuccessMessage());
				}
			}
		).when(
			lcsGatewayClient
		).getMessages(
			Matchers.anyString()
		);

		return lcsGatewayClient;
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

	private HandshakeTask _spyHandshakeTask(
			LCSAlertAdvisor lcsAlertAdvisor,
			LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
			LCSGatewayClient lcsGatewayClient)
		throws Exception {

		HandshakeTask handshakeTask = spy(
			new HandshakeTask(
				1L, lcsAlertAdvisor, lcsClusterEntryTokenAdvisor,
				lcsGatewayClient, mock(LCSKeyAdvisor.class),
				mock(TaskSchedulerServiceImpl.class), null,
				new UptimeAdvisor(null)));

		doReturn(
			new HandshakeMessage()
		).when(
			handshakeTask, "_createHandshakeMessage"
		);

		doNothing(
		).when(
			handshakeTask, "_submitLCSPortletBuildNumberCheck",
			Matchers.anyInt()
		);

		return handshakeTask;
	}

	private LCSClusterEntryTokenAdvisor
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete()
		throws Exception {

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor = spy(
			new LCSClusterEntryTokenAdvisor());

		doNothing(
		).when(
			lcsClusterEntryTokenAdvisor, "_deleteLCSCLusterEntryTokenFile"
		);

		return lcsClusterEntryTokenAdvisor;
	}

	private void _testLCSClusterEntryAdvisorExceptionIsThrown(
			Exception exception, LCSAlertAdvisor lcsAlertAdvisor)
		throws Exception {

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doThrow(
			exception
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsAlertAdvisor, lcsClusterEntryTokenAdvisor,
				mock(TaskSchedulerServiceImpl.class));

		lcsClusterEntryTokenCheckTask.run();
	}

}