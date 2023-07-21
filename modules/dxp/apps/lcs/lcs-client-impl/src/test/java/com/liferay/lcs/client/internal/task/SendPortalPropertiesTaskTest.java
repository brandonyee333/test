/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest({LCSUtil.class, PropsUtil.class})
@RunWith(PowerMockRunner.class)
public class SendPortalPropertiesTaskTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LCSUtil.class);

		mockStatic(PropsUtil.class);

		for (String portalPropertiesInsensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_INSENSITIVE) {

			_properties.put(
				portalPropertiesInsensitiveKey, portalPropertiesInsensitiveKey);
			_properties.put("test.key.i." + System.currentTimeMillis(), "test");
			_properties.put(
				"test.key.i." + System.currentTimeMillis() + ".password",
				"test");
		}

		for (String portalPropertiesSensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_SENSITIVE) {

			_properties.put(
				portalPropertiesSensitiveKey, portalPropertiesSensitiveKey);
			_properties.put("test.key.s." + System.currentTimeMillis(), "test");
			_properties.put(
				"test.key.s." + System.currentTimeMillis() + ".password",
				"test");
		}

		_properties.put("blacklist.key.1", "blacklist.key.value.1");
		_properties.put("blacklist.key.2", "blacklist.key.value.2");
		_properties.put("blacklist.key.3", "blacklist.key.value.3");
		_properties.put("blacklist.key.4", "blacklist.key.value.4");

		when(
			PropsUtil.getProperties()
		).thenReturn(
			_properties
		);
	}

	@Test
	public void testGetSecurityInsensitivePropertiesKeys() {
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor = mock(
			LCSClusterEntryTokenAdvisor.class);

		when(
			lcsClusterEntryTokenAdvisor.getPortalPropertiesBlacklist()
		).thenReturn(
			"blacklist.key.1,blacklist.key.3"
		);

		SendPortalPropertiesTask sendPortalPropertiesTask =
			new SendPortalPropertiesTask(
				lcsClusterEntryTokenAdvisor, null,
				new SendPortalPropertiesCommandMessage());

		Properties properties =
			sendPortalPropertiesTask.getSecurityInsensitivePropertiesKeys();

		for (String portalPropertiesInsensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_INSENSITIVE) {

			Assert.assertTrue(
				"contains key " + portalPropertiesInsensitiveKey,
				properties.containsKey(portalPropertiesInsensitiveKey));
		}

		for (String portalPropertiesSensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_SENSITIVE) {

			Assert.assertFalse(
				"must not contain key " + portalPropertiesSensitiveKey,
				properties.containsKey(portalPropertiesSensitiveKey));
		}

		Assert.assertFalse(
			"must not contain key blacklist.key.1",
			properties.containsKey("blacklist.key.1"));
		Assert.assertTrue(
			"contains key blacklist.key.2",
			properties.containsKey("blacklist.key.2"));
		Assert.assertFalse(
			"must not contain key blacklist.key.3",
			properties.containsKey("blacklist.key.3"));
		Assert.assertTrue(
			"contains key blacklist.key.4",
			properties.containsKey("blacklist.key.4"));
	}

	private final Properties _properties = new Properties();

}