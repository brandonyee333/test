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

package com.liferay.lcs.command;

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSUtil;
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
public class SendPortalPropertiesCommandTest extends PowerMockito {

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
			"blacklist.key.1,blacklist.key.2"
		);

		SendPortalPropertiesCommand sendPortalPropertiesCommand =
			new SendPortalPropertiesCommand();

		sendPortalPropertiesCommand.setLCSClusterEntryTokenAdvisor(
			lcsClusterEntryTokenAdvisor);

		Properties properties =
			sendPortalPropertiesCommand.getSecurityInsensitivePropertiesKeys();

		for (String portalPropertiesInsensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_INSENSITIVE) {

			if (!properties.containsKey(portalPropertiesInsensitiveKey)) {
				Assert.fail();
			}
		}

		for (String portalPropertiesSensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_SENSITIVE) {

			if (properties.containsKey(portalPropertiesSensitiveKey)) {
				Assert.fail();
			}
		}

		Assert.assertTrue(
			"blacklist.key.1 present", properties.contains("blacklist.key.1"));
		Assert.assertTrue(
			"blacklist.key.2 present", properties.contains("blacklist.key.2"));
	}

	private final Properties _properties = new Properties();

}