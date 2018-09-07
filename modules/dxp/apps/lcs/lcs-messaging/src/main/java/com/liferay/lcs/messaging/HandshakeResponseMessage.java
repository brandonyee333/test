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

/**
 * @author Ivica Cardic
 */
public class HandshakeResponseMessage extends ResponseMessage {

	public int getLatestLCSPortletBuildNumber() {
		return _latestLCSPortletBuildNumber;
	}

	public String getNewKey() {
		return _newKey;
	}

	public boolean isHandshakeExpiredError() {
		return _handshakeExpiredError;
	}

	public void setHandshakeExpiredError(boolean handshakeExpiredError) {
		_handshakeExpiredError = handshakeExpiredError;
	}

	public void setLatestLCSPortletBuildNumber(
		int latestLCSPortletBuildNumber) {

		_latestLCSPortletBuildNumber = latestLCSPortletBuildNumber;
	}

	public void setNewKey(String newKey) {
		_newKey = newKey;
	}

	private boolean _handshakeExpiredError;
	private int _latestLCSPortletBuildNumber;
	private String _newKey;

}