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

package com.liferay.lcs.messaging.bus;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public interface LCSMessageBusService {

	public boolean registerLCSMessageListener(
		String destinationName, LCSMessageListener lcsMessageListener);

	public void sendMessage(
		String destinationName, Map<String, String> metadata, String payload,
		String responseDestinationName);

	public void sendMessage(String destinationName, String payload);

	public boolean unregisterLCSMessageListener(
		String destinationName, LCSMessageListener lcsMessageListener);

}