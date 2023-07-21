/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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