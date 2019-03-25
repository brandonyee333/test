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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.BasePowerMockitoTest;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.portal.LCSPortalClient;
import com.liferay.lcs.client.internal.task.scheduler.TaskSchedulerServiceImpl;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.lcs.client.task.scheduler.TaskSchedulerService;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.net.UnknownHostException;

import java.util.concurrent.ExecutionException;

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
	{FileUtil.class, LCSClusterEntryTokenAdvisorImpl.class, LCSUtil.class}
)
@RunWith(PowerMockRunner.class)
public class LCSClusterEntryTokenCheckTaskTest extends BasePowerMockitoTest {

	@Before
	public void setUp() {
		mockStatic(FileUtil.class, LCSUtil.class);

		_taskSchedulerService = mock(TaskSchedulerServiceImpl.class);
	}

	@Test
	public void testCheckLCSClusterEntryTokenSuccess() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager,
				mockLCSPortalClientIsAuthorized(Boolean.TRUE));

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 0, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 0,
			lcsClusterEntryTokenAdvisorImpl);

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, 0,
			lcsClusterEntryTokenAdvisorImpl);

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS
		);
	}

	@Test
	public void testLCSClusterEntryAdvisorExceptionIsThrown() throws Exception {
		_testLCSClusterEntryAdvisorExceptionIsThrown(
			new IOException("Test lcs cluster entry token file IO exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED);
		_testLCSClusterEntryAdvisorExceptionIsThrown(
			new MissingLCSClusterEntryTokenException(
				"Test lcs cluster entry token file missing exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING);
		_testLCSClusterEntryAdvisorExceptionIsThrown(
			new MultipleLCSClusterEntryTokenException(
				"Test lcs cluster entry token file multiple token exception"),
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS);
	}

	@Test
	public void testLCSPortalClientCommunicationFailureExceptionIsThrown()
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSPortalClient lcsPortalClient =
			mockLCSPortalClientIsAuthorizedThrowsException(
				new JSONWebServiceTransportException.CommunicationFailure(
					"Test LCS portal communication failure",
					new ExecutionException(new UnknownHostException("Test"))));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager,
				lcsPortalClient);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			0, 0, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED, 0,
			lcsClusterEntryTokenAdvisorImpl);
	}

	@Test
	public void testLCSPortletNotAuthorized() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			new LCSClusterEntryToken()
		).when(
			lcsClusterEntryTokenAdvisorImpl
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager,
				mockLCSPortalClientIsAuthorized(Boolean.FALSE));

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 1,
			lcsClusterEntryTokenAdvisorImpl);
	}

	@Test
	public void testTokenIsDeletedIfExceptionThrown() throws Exception {
		LCSEventManager lcsEventManager = new LCSEventManager();

		LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl =
			spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete(lcsEventManager);

		doReturn(
			"test-file-name"
		).when(
			lcsClusterEntryTokenAdvisorImpl, "getLCSClusterEntryTokenFileName"
		);

		doThrow(
			new EncryptorException("Test encryptor exception")
		).when(
			lcsClusterEntryTokenAdvisorImpl, "decrypt",
			Matchers.any(byte[].class), Matchers.anyInt()
		);

		when(
			FileUtil.getBytes(Matchers.any(File.class))
		).thenReturn(
			new byte[0]
		);

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager, null);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 1,
			lcsClusterEntryTokenAdvisorImpl);
	}

	private void _testLCSClusterEntryAdvisorExceptionIsThrown(
			Exception exception, LCSEvent expectedLCSEvent)
		throws Exception {

		LCSEventManager lcsEventManager = new LCSEventManager();

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

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, expectedLCSEvent, 0, lcsClusterEntryTokenAdvisorImpl);
	}

	private void _verifyLCSClusterEntryTokenAdvisor(
			int maxNumberOfOnLCSEventInvocations,
			int maxNumberOfSpecificOnLCSEventInvocations,
			LCSEvent specificLCSEvent, int maxNumberOfDeleteMethodInvocations,
			LCSClusterEntryTokenAdvisorImpl lcsClusterEntryTokenAdvisorImpl)
		throws Exception {

		Mockito.verify(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfOnLCSEventInvocations)
		).onLCSEvent(
			Matchers.any(LCSEvent.class)
		);

		Mockito.verify(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfSpecificOnLCSEventInvocations)
		).onLCSEvent(
			specificLCSEvent
		);

		verifyPrivate(
			lcsClusterEntryTokenAdvisorImpl,
			Mockito.times(maxNumberOfDeleteMethodInvocations)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	private TaskSchedulerService _taskSchedulerService;

}