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

package com.liferay.lcs.client.internal.platform.gateway;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 */
public class MockLCSGatewayClientImpl implements LCSGatewayClient {

	public MockLCSGatewayClientImpl(LCSEventManager lcsEventManager) {
		_lcsEventManager = lcsEventManager;

		_receivedMessages = new ArrayList<>();
		_sentMessages = new ArrayList<>();
	}

	public void addMockMessage(Message message) {
		_receivedMessages.add(message);
	}

	@Override
	public void deleteMessages(String key) throws LCSGatewayException {
		_notifyLCSEventManager();
	}

	@Override
	public long getLastHandshakeSuccess() {
		return 0;
	}

	@Override
	public long getLastMessageReceived() {
		return 0;
	}

	@Override
	public long getLastMessageSent() {
		return 0;
	}

	@Override
	public List<Message> getMessages(
			boolean checkGatewayAvailability, String key)
		throws LCSGatewayException {

		_notifyLCSEventManager();

		return new ArrayList<>(_receivedMessages);
	}

	@Override
	public List<Message> getMessages(String key) throws LCSGatewayException {
		return getMessages(true, key);
	}

	public boolean hasSentMessage(Message message) {
		if (_sentMessages.contains(message)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAvailable() {
		return false;
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
	}

	@Override
	public void sendMessage(boolean checkGatewayAvailability, Message message) {
		_notifyLCSEventManager();

		_sentMessages.add(message);
	}

	@Override
	public void sendMessage(Message message) {
		sendMessage(true, message);
	}

	public void setLCSEventToEmitOnInteraction(LCSEvent lcsEvent) {
		_lcsEvent = lcsEvent;
	}

	private void _notifyLCSEventManager() {
		if (_lcsEvent != null) {
			_lcsEventManager.publish(_lcsEvent);
		}
	}

	private LCSEvent _lcsEvent;
	private final LCSEventManager _lcsEventManager;
	private final List<Message> _receivedMessages;
	private final List<Message> _sentMessages;

}