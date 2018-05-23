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

package com.liferay.lcs.security;

import com.liferay.lcs.messaging.Message;

/**
 * @author  Igor Beslic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public interface DigitalSignature {

	public void signMessage(int buildNumber, Message message);

	public void signMessage(Message message);

	public boolean verifyMessage(int buildNumber, Message message);

	public boolean verifyMessage(Message message);

}