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

package com.liferay.lcs.platform.gateway;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.task.TaskStateListener;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSGatewayService extends TaskStateListener {

	public void deleteMessages(String key) throws JSONWebServiceException;

	public long getLastHandshakeSuccess();

	public long getLastMessageReceived();

	public long getLastMessageSent();

	public List<Message> getMessages(String key) throws JSONWebServiceException;

	public boolean isAvailable();

	public void registerLCSGatewayStateListener(
		LCSGatewayStateListener lcsGatewayStateListener);

	public void sendMessage(Message message)
		throws CompressionException, JSONWebServiceException;

}