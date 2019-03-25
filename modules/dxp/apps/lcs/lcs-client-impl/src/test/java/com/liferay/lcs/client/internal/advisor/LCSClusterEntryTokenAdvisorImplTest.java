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

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.BasePowerMockitoTest;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.runnable.LCSThreadFactory;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.IOException;

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
		LCSClusterEntryTokenAdvisorImpl.class, LCSUtil.class,
		PortletClassLoaderUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class LCSClusterEntryTokenAdvisorImplTest extends BasePowerMockitoTest {

	@Before
	public void setUp() {
		_lcsKeyAdvisor = mock(LCSKeyAdvisor.class);

		doReturn(
			"aaaa-bbbb-cccc-dddd"
		).when(
			_lcsKeyAdvisor
		).getKey();

		_threadFactory = new LCSThreadFactory();
		_uptimeAdvisor = mock(UptimeAdvisor.class);

		mockStatic(
			ConfigurationFactoryUtil.class, FileUtil.class, LCSUtil.class,
			PortletClassLoaderUtil.class);
	}

	@Test
	public void testNoReactionIfExceptionIsThrown() throws Exception {
		_testNoReactionIfExceptionIsThrown(
			new IOException("Test lcs cluster entry token file IO exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED);
		_testNoReactionIfExceptionIsThrown(
			new MissingLCSClusterEntryTokenException(
				"Test lcs cluster entry token file missing exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING);
		_testNoReactionIfExceptionIsThrown(
			new MultipleLCSClusterEntryTokenException(
				"Test lcs cluster entry token file multiple token exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode200()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				200, lcsEventManager);

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		handshakeTask.run();

		Mockito.verify(
			lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID
		);

		verifyPrivate(
			lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode201()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				201, lcsEventManager);

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		handshakeTask.run();

		Mockito.verify(
			lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH
		);

		verifyPrivate(
			lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsDeletedIfHandshakeExceptionErrorCode202()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spyGetMessagesToReturnHandshakeResponseMessage(
				202, lcsEventManager);

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		handshakeTask.run();

		Mockito.verify(
			lcsClusterEntryTokenAdvisor
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS
		);

		verifyPrivate(
			lcsClusterEntryTokenAdvisor, Mockito.times(1)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	@Test
	public void testTokenIsNotDeletedIfGatewayUnavailable() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSGatewayClient lcsGatewayClient =
			spySendMessageToThrowLCSGatewayException(lcsEventManager);

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisor =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		HandshakeTask handshakeTask = spyHandshakeTask(
			lcsEventManager, lcsGatewayClient, _lcsKeyAdvisor, _threadFactory,
			_uptimeAdvisor);

		handshakeTask.run();

		verifyPrivate(
			lcsClusterEntryTokenAdvisor, Mockito.never()
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	private void _testNoReactionIfExceptionIsThrown(
			Exception exception, LCSEvent expectedLCSEvent)
		throws Exception {

		LCSEventManager lcsEventManager = spy(new LCSEventManager());

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doThrow(
			exception
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager, null);

		lcsClusterEntryTokenCheckTask.run();

		verifyLCSClusterEntryTokenAdvisor(
			0, 0, expectedLCSEvent, 0, lcsClusterEntryTokenAdvisorImpl);

		Mockito.verify(
			lcsEventManager, Mockito.times(1)
		).publish(
			expectedLCSEvent
		);
	}

	private LCSKeyAdvisor _lcsKeyAdvisor;
	private ThreadFactory _threadFactory;
	private UptimeAdvisor _uptimeAdvisor;

}