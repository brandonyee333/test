/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class SignOffCommandMessageTest {

	@Test
	public void testToString() {
		SignOffCommandMessage signOffCommandMessage =
			new SignOffCommandMessage();

		signOffCommandMessage.setDeregister(true);
		signOffCommandMessage.setInvalidateToken(false);

		SignOffReasonCode signOffReasonCode = SignOffReasonCode.UNREGISTER;

		signOffCommandMessage.setReasonCode(signOffReasonCode.getCode());

		String toString = signOffCommandMessage.toString();

		Assert.assertTrue(
			"toString should produce content in predictable format",
			toString.matches("\\{(\\w+=.+,{1}\\s)+\\w+=.+\\}"));

		Assert.assertTrue(
			"toString should contain reasonCode attribute",
			toString.contains("reasonCode"));
	}

}