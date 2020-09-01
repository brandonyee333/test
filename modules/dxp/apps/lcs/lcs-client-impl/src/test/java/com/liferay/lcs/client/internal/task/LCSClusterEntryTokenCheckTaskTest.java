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