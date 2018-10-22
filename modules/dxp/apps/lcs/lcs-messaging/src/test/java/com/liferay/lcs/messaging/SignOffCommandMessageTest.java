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