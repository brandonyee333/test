/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging.security;

import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.security.exception.DigitalSignatureException;

/**
 * @author  Igor Beslic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public interface DigitalSignature {

	public String getSignature(int buildNumber, String value);

	public void signMessage(int buildNumber, Message message);

	public void signMessage(Message message);

	public boolean verifyMessage(int buildNumber, Message message)
		throws DigitalSignatureException;

	public boolean verifyValue(String value, String signature)
		throws DigitalSignatureException;

}