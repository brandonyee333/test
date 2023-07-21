/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging.security;

import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.internal.security.DigitalSignatureImpl;
import com.liferay.lcs.messaging.security.exception.DigitalSignatureException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class DigitalSignatureTest {

	@Test
	public void test() throws DigitalSignatureException {
		DigitalSignatureImpl digitalSignatureImpl = new DigitalSignatureImpl();

		digitalSignatureImpl.setKeyName("localhost");
		digitalSignatureImpl.setKeyStorePath(
			"classpath:/com/liferay/lcs/messaging/security/dependencies" +
				"/keystore.jks");
		digitalSignatureImpl.setKeyStoreType("JKS");
		digitalSignatureImpl.setSigningAlgorithm("MD5withRSA");

		SendPortalPropertiesCommandMessage sendPortalPropertiesCommandMessage =
			new SendPortalPropertiesCommandMessage();

		sendPortalPropertiesCommandMessage.setHashCode("hashCode");
		sendPortalPropertiesCommandMessage.setKey("key");

		digitalSignatureImpl.signMessage(
			302, sendPortalPropertiesCommandMessage);

		boolean verify = digitalSignatureImpl.verifyMessage(
			302, sendPortalPropertiesCommandMessage);

		Assert.assertTrue(verify);
	}

}