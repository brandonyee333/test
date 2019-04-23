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

package com.liferay.lcs.client.internal.advisor;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.alert.LCSAlert;
import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.BasePowerMockitoTest;
import com.liferay.lcs.client.internal.alert.advisor.LCSAlertAdvisorImpl;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.portal.kernel.service.CompanyLocalService;
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

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(
	{
		FileUtil.class, HandshakeTask.class,
		LCSClusterEntryTokenAdvisorImpl.class, LCSUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class LCSAlertAdvisorImplTest extends BasePowerMockitoTest {

	@Before
	public void setUp() {
		mockStatic(FileUtil.class, LCSUtil.class);
	}

	@Test
	public void testErrorEnvironmentMismatch() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsEventManager,
			spyGetMessagesToReturnHandshakeResponseMessage(
				201, lcsEventManager));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_ENVIRONMENT_MISMATCH));
	}

	@Test
	public void testErrorInvalidTokenIfEncryptorExceptionThrown()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

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

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisor, lcsEventManager, null);

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidTokenLCSAlert() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsEventManager,
			spyGetMessagesToReturnHandshakeResponseMessage(
				200, lcsEventManager));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testErrorInvalidUserCredentialsLCSAlert() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsEventManager,
			spyGetMessagesToReturnHandshakeResponseMessage(
				202, lcsEventManager));

		handshakeTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Invalid user credentials LCS Alert expected",
			lcsAlerts.contains(LCSAlert.ERROR_INVALID_USER_CREDENTIALS));
	}

	@Test
	public void testHandshakeSuccessLCSAlert() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		lcsAlertAdvisor.add(LCSAlert.WARNING_HANDSHAKE_FAILED);

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsEventManager, _mockGetMessagesToReturnHandshakeSuccessMessage());

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
			LCSEventManager lcsEventManager = new LCSEventManager();

			LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
				new LCSAlertAdvisorImpl(lcsEventManager));

			_testLCSClusterEntryAdvisorExceptionIsThrown(
				exception, lcsEventManager);

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
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken();

		LCSAlertAdvisorImpl lcsAlertAdvisor = new LCSAlertAdvisorImpl(
			lcsEventManager);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisor, lcsEventManager,
				mockLCSPortalClientIsAuthorized(Boolean.FALSE));

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(lcsAlerts.contains(LCSAlert.ERROR_INVALID_TOKEN));
	}

	@Test
	public void testSuccessValidToken() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken();

		LCSAlertAdvisorImpl lcsAlertAdvisor = new LCSAlertAdvisorImpl(
			lcsEventManager);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisor, lcsEventManager,
				mockLCSPortalClientIsAuthorized(Boolean.TRUE));

		lcsClusterEntryTokenCheckTask.run();

		Set<LCSAlert> lcsAlerts = lcsAlertAdvisor.getLCSAlerts();

		Assert.assertTrue(
			"Valid token LCS Alert expected",
			lcsAlerts.contains(LCSAlert.SUCCESS_VALID_TOKEN));
	}

	@Test
	public void testWarningHandshakeFailedLCSAlert() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSAlertAdvisorImpl lcsAlertAdvisor = spy(
			new LCSAlertAdvisorImpl(lcsEventManager));

		HandshakeTask handshakeTask = _spyHandshakeTask(
			lcsEventManager,
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

	private HandshakeTask _spyHandshakeTask(
			LCSEventManager lcsEventManager, LCSGatewayClient lcsGatewayClient)
		throws Exception {

		CompanyLocalService companyLocalService = mock(
			CompanyLocalService.class);
		LCSAlertAdvisor lcsAlertAdvisor = new LCSAlertAdvisorImpl();

		HandshakeTask handshakeTask = spy(
			new HandshakeTask(
				companyLocalService, lcsAlertAdvisor, lcsEventManager,
				lcsGatewayClient, mock(LCSKeyAdvisor.class), null,
				new UptimeAdvisor(lcsEventManager, null, null)));

		doReturn(
			new HandshakeMessage()
		).when(
			handshakeTask, "_createHandshakeMessage"
		);

		// Skip JavaParser, will fix

		doNothing(
		).when(
			handshakeTask, "_checkLCSClientBuildNumber",
			Matchers.anyInt()
		);

		return handshakeTask;
	}

	private void _testLCSClusterEntryAdvisorExceptionIsThrown(
			Exception exception, LCSEventManager lcsEventManager)
		throws Exception {

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doThrow(
			exception
		).when(
			lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken();

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisor, lcsEventManager, null);

		lcsClusterEntryTokenCheckTask.run();
	}

}