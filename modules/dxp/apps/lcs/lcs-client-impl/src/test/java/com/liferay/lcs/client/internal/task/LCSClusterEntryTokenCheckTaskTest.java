/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.BasePowerMockitoTestCase;
import com.liferay.lcs.client.internal.advisor.LCSClusterEntryTokenAdvisorImpl;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.platform.http.RESTClientTransportException;
import com.liferay.lcs.client.internal.platform.portal.LCSPortalClient;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.exception.LCSClientAuthenticationException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;

import java.net.UnknownHostException;

import java.util.concurrent.ExecutionException;

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
	{FileUtil.class, LCSClusterEntryTokenAdvisorImpl.class, LCSUtil.class}
)
@RunWith(PowerMockRunner.class)
public class LCSClusterEntryTokenCheckTaskTest
	extends BasePowerMockitoTestCase {

	@Before
	public void setUp() {
		mockStatic(FileUtil.class, LCSUtil.class);
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
		).processLCSClusterEntryToken();

		LCSPortalClient lcsPortalClient =
			mockLCSPortalClientIsAuthorizedThrowsException(
				new LCSClientAuthenticationException(
					"Test LCS authentication failure",
					new RESTClientTransportException.CommunicationFailure(
						"Test LCS portal communication failure",
						new ExecutionException(
							new UnknownHostException("Test")))));

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager,
				lcsPortalClient);

		lcsClusterEntryTokenCheckTask.run();

		verifyLCSClusterEntryTokenAdvisor(
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
		).processLCSClusterEntryToken();

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				lcsClusterEntryTokenAdvisorImpl, lcsEventManager,
				mockLCSPortalClientIsAuthorized(Boolean.FALSE));

		lcsClusterEntryTokenCheckTask.run();

		verifyLCSClusterEntryTokenAdvisor(
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

		verifyLCSClusterEntryTokenAdvisor(
			1, 1, LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, 1,
			lcsClusterEntryTokenAdvisorImpl);
	}

}