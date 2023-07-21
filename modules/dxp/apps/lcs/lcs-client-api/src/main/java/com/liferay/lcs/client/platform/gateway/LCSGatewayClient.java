/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.gateway;

import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.messaging.Message;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSGatewayClient extends LCSEventListener {

	public void deleteMessages(String key) throws LCSGatewayException;

	public long getLastHandshakeSuccess();

	public long getLastMessageReceived();

	public long getLastMessageSent();

	public List<Message> getMessages(
			boolean checkGatewayAvailability, String key)
		throws LCSGatewayException;

	public List<Message> getMessages(String key) throws LCSGatewayException;

	/**
	 * @return     <code>true</code> if access to LCS Gateway is available,
	 *             otherwise <code>false</code>
	 * @deprecated As of Mueller (7.2.x), see {@link
	 *             LCSAlertAdvisor#getLCSAlerts()}
	 */
	@Deprecated
	public boolean isAvailable();

	public void sendMessage(boolean checkGatewayAvailability, Message message)
		throws CompressionException, LCSGatewayException;

	public void sendMessage(Message message)
		throws CompressionException, LCSGatewayException;

}