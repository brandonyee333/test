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
 * @author Igor Beslic
 */
public class SignOffCommandMessage extends CommandMessage {

	public int getReasonCode() {
		return _reasonCode;
	}

	public boolean isDeregister() {
		return _deregister;
	}

	public boolean isInvalidateToken() {
		return _invalidateToken;
	}

	public void setDeregister(boolean deregister) {
		_deregister = deregister;
	}

	public void setInvalidateToken(boolean invalidateToken) {
		_invalidateToken = invalidateToken;
	}

	public void setReasonCode(int reasonCode) {
		_reasonCode = reasonCode;
	}

	private boolean _deregister;
	private boolean _invalidateToken;
	private int _reasonCode;

}