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

package com.liferay.lcs.client.internal.task.advisor;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.command.SendPatchesCommand;
import com.liferay.lcs.client.internal.command.SendPortalPropertiesCommand;
import com.liferay.lcs.client.internal.command.advisor.CommandAdvisor;
import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.internal.platform.gateway.LCSGatewayClientImpl;
import com.liferay.lcs.client.internal.task.CommandMessageCheckTask;
import com.liferay.lcs.client.internal.task.CommandQueueCheckTask;
import com.liferay.lcs.client.internal.util.LCSPatcherUtil;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.internal.security.DigitalSignatureImpl;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.messaging.security.exception.DigitalSignatureException;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
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
 * @author Ivica Cartdic
 */
@PrepareForTest(
	{DigesterUtil.class, LCSPatcherUtil.class, LCSUtil.class, PropsUtil.class}
)
@RunWith(PowerMockRunner.class)
public class TaskAdvisorImplTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_mockDigesterUtil();

		_mockDigitalSignature();

		_mockLCSConfigurationProvider();

		_mockLCSGatewayClient();

		_mockLCSKeyAdvisor();

		_mockLCSPatcherUtil();

		_mockPropsUtil();
	}

	@Test
	public void testGetActiveServiceLabels() throws Exception {
		TaskAdvisorImpl taskAdvisor = new TaskAdvisorImpl();

		SendPatchesCommand sendPatchesCommand = new SendPatchesCommand(
			_lcsGatewayClient, taskAdvisor);

		SendPortalPropertiesCommand sendPortalPropertiesCommand =
			new SendPortalPropertiesCommand(
				mock(LCSClusterEntryTokenAdvisor.class), _lcsGatewayClient,
				taskAdvisor);

		CommandAdvisor commandAdvisor = new CommandAdvisor(
			_digitalSignature, _lcsConfigurationProvider, _lcsGatewayClient,
			_lcsKeyAdvisor, sendPatchesCommand, sendPortalPropertiesCommand);

		ClusterMasterExecutor mockClusterMasterExecutor = mock(
			ClusterMasterExecutor.class);

		CommandQueue commandQueue = new CommandQueue();

		CommandMessageCheckTask commandMessageCheckTask =
			new CommandMessageCheckTask(
				mockClusterMasterExecutor, commandQueue, _lcsGatewayClient,
				_lcsKeyAdvisor);

		CommandQueueCheckTask commandQueueCheckTask = new CommandQueueCheckTask(
			mockClusterMasterExecutor, commandAdvisor, commandQueue,
			_lcsGatewayClient, _lcsKeyAdvisor);

		when(
			_lcsGatewayClient.getMessages(Mockito.anyString())
		).thenReturn(
			Collections.singletonList(new SendPortalPropertiesCommandMessage())
		);

		commandMessageCheckTask.run();
		commandQueueCheckTask.run();

		Set<String> activeServiceLabels = taskAdvisor.getActiveServiceLabels();

		Assert.assertTrue(
			"Portal properties analysis service present",
			activeServiceLabels.contains("Portal Properties Analysis"));

		Assert.assertFalse(
			"Fix packs management service absent",
			activeServiceLabels.contains("Fix Packs Management"));
		Assert.assertFalse(
			"Portal analytics service absent",
			activeServiceLabels.contains("Portal Analytics"));
		Assert.assertFalse(
			"Subscription management service absent",
			activeServiceLabels.contains("Subscription Management"));

		when(
			_lcsGatewayClient.getMessages(Mockito.anyString())
		).thenReturn(
			Collections.singletonList(new SendPatchesCommandMessage())
		);

		commandMessageCheckTask.run();
		commandQueueCheckTask.run();

		activeServiceLabels = taskAdvisor.getActiveServiceLabels();

		Assert.assertTrue(
			"Fix packs management service present",
			activeServiceLabels.contains("Fix Packs Management"));
	}

	private void _mockDigesterUtil() {
		mockStatic(DigesterUtil.class);

		when(
			DigesterUtil.digestHex(Matchers.eq("MD5"), Matchers.anyString())
		).thenReturn(
			"md5-mock-digest"
		);
	}

	private void _mockDigitalSignature() throws DigitalSignatureException {
		_digitalSignature = mock(DigitalSignatureImpl.class);

		doReturn(
			Boolean.TRUE
		).when(
			_digitalSignature
		).verifyMessage(
			Matchers.eq(LCSClientConstants.LCS_CLIENT_BUILD_NUMBER),
			Matchers.any(Message.class)
		);
	}

	private void _mockLCSConfigurationProvider() {
		_lcsConfigurationProvider = mock(LCSConfigurationProvider.class);

		LCSConfiguration lcsConfiguration = mock(LCSConfiguration.class);

		doReturn(
			lcsConfiguration
		).when(
			_lcsConfigurationProvider
		).getLCSConfiguration();
	}

	private void _mockLCSGatewayClient() {
		_lcsGatewayClient = mock(LCSGatewayClientImpl.class);

		doReturn(
			true
		).when(
			_lcsGatewayClient
		).isAvailable();
	}

	private void _mockLCSKeyAdvisor() {
		_lcsKeyAdvisor = mock(LCSKeyAdvisor.class);

		when(
			_lcsKeyAdvisor.getKey()
		).thenReturn(
			"key1"
		);
	}

	private void _mockLCSPatcherUtil() {
		mockStatic(LCSPatcherUtil.class);

		when(
			LCSPatcherUtil.isConfigured()
		).thenReturn(
			Boolean.TRUE
		);

		when(
			LCSPatcherUtil.getInstalledPatches()
		).thenReturn(
			new String[] {
				"LPS-2001", "LPS-2001", "LPS-2003", "LPS-2004", "LPS-2005",
				"LPS-2006", "LPS-2007", "LPS-2009", "LPS-2010", "LPS-2011"
			}
		);

		when(
			LCSPatcherUtil.getPatchingToolVersion()
		).thenReturn(
			2011
		);
	}

	private void _mockPropsUtil() {
		mockStatic(PropsUtil.class);

		Properties properties = new Properties();

		properties.put("key1", "value1");

		when(
			PropsUtil.getProperties()
		).thenReturn(
			properties
		);
	}

	private DigitalSignature _digitalSignature;
	private LCSConfigurationProvider _lcsConfigurationProvider;
	private LCSGatewayClient _lcsGatewayClient;
	private LCSKeyAdvisor _lcsKeyAdvisor;

}