/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());

		sb.setLength(sb.length() - 1);

		sb.append(", deregister=");
		sb.append(_deregister);
		sb.append(", invalidateToken=");
		sb.append(_invalidateToken);
		sb.append(", reasonCode=");
		sb.append(_reasonCode);
		sb.append("}");

		return sb.toString();
	}

	private boolean _deregister;
	private boolean _invalidateToken;
	private int _reasonCode;

}