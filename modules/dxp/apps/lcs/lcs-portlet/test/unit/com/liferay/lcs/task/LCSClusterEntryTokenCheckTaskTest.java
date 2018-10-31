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

package com.liferay.lcs.task;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.rest.client.LCSClusterEntryToken;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.task.scheduler.impl.TaskSchedulerServiceImpl;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.util.EncryptorException;

import java.io.File;
import java.io.IOException;

import java.net.UnknownHostException;

import java.util.concurrent.ExecutionException;

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
	{FileUtil.class, LCSClusterEntryTokenAdvisor.class, LCSUtil.class}
)
@RunWith(PowerMockRunner.class)
public class LCSClusterEntryTokenCheckTaskTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(FileUtil.class, LCSUtil.class);

		_lcsAlertAdvisor = mock(LCSAlertAdvisor.class);
		_taskSchedulerService = mock(TaskSchedulerServiceImpl.class);
	}

	@Test
	public void testCheckLCSClusterEntryTokenSuccess() throws Exception {
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
				_lcsClusterEntryTokenAdvisor, _taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 0, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 0);

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, 0);

		Mockito.verify(
			_taskSchedulerService
		).onLCSEvent(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS
		);
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
				_lcsClusterEntryTokenAdvisor, _taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 1);
	}

	@Test
	public void testLCSUtilExceptionIsThrown() throws Exception {
		_spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete();

		doReturn(
			new LCSClusterEntryToken()
		).when(
			_lcsClusterEntryTokenAdvisor
		).processLCSClusterEntryToken(
			Matchers.anyInt()
		);

		_mockLCSUtilToThrowJSONWebServiceException();

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsClusterEntryTokenAdvisor, _taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED, 0);
	}

	@Test
	public void testTokenIsDeletedIfExceptionThrown() throws Exception {
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
				_lcsClusterEntryTokenAdvisor, _taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 1);
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

	private void _spyLCSClusterEntryTokenAdvisorToDoNothingOnDelete()
		throws Exception {

		_lcsClusterEntryTokenAdvisor = spy(
			new LCSClusterEntryTokenAdvisor(_lcsAlertAdvisor));

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
				_lcsClusterEntryTokenAdvisor, _taskSchedulerService);

		lcsClusterEntryTokenCheckTask.run();

		_verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED, 0);
	}

	private void _verifyLCSClusterEntryTokenAdvisor(
			int maxNumberOfOnLCSEventInvocations,
			int maxNumberOfSpecificOnLCSEventInvocations,
			LCSEvent specificLCSEvent, int maxNumberOfDeleteMethodInvocations)
		throws Exception {

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor,
			Mockito.times(maxNumberOfOnLCSEventInvocations)
		).onLCSEvent(
			Matchers.any(LCSEvent.class)
		);

		Mockito.verify(
			_lcsClusterEntryTokenAdvisor,
			Mockito.times(maxNumberOfSpecificOnLCSEventInvocations)
		).onLCSEvent(
			specificLCSEvent
		);

		verifyPrivate(
			_lcsClusterEntryTokenAdvisor,
			Mockito.times(maxNumberOfDeleteMethodInvocations)
		).invoke(
			"_deleteLCSCLusterEntryTokenFile"
		);
	}

	private LCSAlertAdvisor _lcsAlertAdvisor;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private TaskSchedulerService _taskSchedulerService;

}