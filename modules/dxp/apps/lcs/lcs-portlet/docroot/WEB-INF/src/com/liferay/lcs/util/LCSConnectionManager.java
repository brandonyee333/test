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

package com.liferay.lcs.util;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.Message;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSConnectionManager {

	public void deleteMessages(String key) throws JSONWebServiceException;

	public Map<String, String> getLCSConnectionMetadata();

	public List<Message> getMessages(String key) throws JSONWebServiceException;

	public boolean isLCSGatewayAvailable();

	public boolean isReady();

	public boolean isShutdownRequested();

	public void onHandshakeSuccess();

	public void onPortletDeployed();

	public void putLCSConnectionMetadata(String key, String value);

	public void sendMessage(Message message)
		throws CompressionException, JSONWebServiceException;

	public void setLCSGatewayAvailable(boolean lcsGatewayAvailable);

	public void setReady(boolean ready);

	public void setShutdownRequested(boolean shutdownRequested);

	public Future<?> start();

	public Future<?> stop(
		boolean delayReconnect, boolean reconnect,
		boolean serverManuallyShutdown);

}