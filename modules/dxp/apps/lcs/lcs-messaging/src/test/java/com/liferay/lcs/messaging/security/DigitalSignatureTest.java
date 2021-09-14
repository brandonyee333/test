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