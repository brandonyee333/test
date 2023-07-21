/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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