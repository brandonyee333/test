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

package com.liferay.lcs.task.advisor;

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.command.Command;
import com.liferay.lcs.command.SendPatchesCommand;
import com.liferay.lcs.command.SendPortalPropertiesCommand;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.CommandMessageListener;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.internal.security.DigitalSignatureImpl;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.lcs.platform.gateway.impl.LCSGatewayServiceImpl;
import com.liferay.lcs.util.LCSPatcherUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
	{DigesterUtil.class, LCSPatcherUtil.class, LCSUtil.class, PropsUtil.class}
)
@RunWith(PowerMockRunner.class)
public class TaskAdvisorTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		mockStatic(DigesterUtil.class);

		when(
			DigesterUtil.digestHex(Matchers.eq("MD5"), Matchers.anyString())
		).thenReturn(
			"md5-mock-digest"
		);

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

		mockStatic(LCSUtil.class);

		when(
			LCSUtil.getLCSPortletBuildNumber()
		).thenReturn(
			500
		);

		mockStatic(PropsUtil.class);

		Properties properties = new Properties();

		properties.put("key1", "value1");

		when(
			PropsUtil.getProperties()
		).thenReturn(
			properties
		);

		_digitalSignature = mock(DigitalSignatureImpl.class);

		doReturn(
			Boolean.TRUE
		).when(
			_digitalSignature
		).verifyMessage(
			Matchers.eq(500), Matchers.any(Message.class)
		);

		_lcsGatewayService = mock(LCSGatewayServiceImpl.class);

		doNothing(
		).when(
			_lcsGatewayService
		).sendMessage(
			Matchers.any(Message.class)
		);
	}

	@Test
	public void testGetActiveServiceLabels() {
		Map<String, Command<? extends CommandMessage>> messageCommands =
			new HashMap<>();

		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor =
			new LCSClusterEntryTokenAdvisor();

		TaskAdvisor taskAdvisor = new TaskAdvisor();

		Command<? extends CommandMessage> sendPortalPropertiesCommand =
			new SendPortalPropertiesCommand(
				lcsClusterEntryTokenAdvisor, _lcsGatewayService, taskAdvisor);

		messageCommands.put(
			"com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage",
			sendPortalPropertiesCommand);

		Command<? extends CommandMessage> sendPatchesCommand =
			new SendPatchesCommand(_lcsGatewayService, taskAdvisor);

		messageCommands.put(
			"com.liferay.lcs.messaging.SendPatchesCommandMessage",
			sendPatchesCommand);

		CommandMessageListener commandMessageListener =
			new CommandMessageListener(
				messageCommands, _digitalSignature, _lcsGatewayService);

		com.liferay.portal.kernel.messaging.Message message =
			new com.liferay.portal.kernel.messaging.Message();

		message.setPayload(new SendPortalPropertiesCommandMessage());

		commandMessageListener.receive(message);

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

		message.setPayload(new SendPatchesCommandMessage());

		commandMessageListener.receive(message);

		activeServiceLabels = taskAdvisor.getActiveServiceLabels();

		Assert.assertTrue(
			"Fix packs management service present",
			activeServiceLabels.contains("Fix Packs Management"));
	}

	private DigitalSignature _digitalSignature;
	private LCSGatewayService _lcsGatewayService;

}