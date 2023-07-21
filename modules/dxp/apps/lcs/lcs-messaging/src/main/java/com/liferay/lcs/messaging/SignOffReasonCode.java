/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Igor Beslic
 */
public enum SignOffReasonCode {

	INVALIDATE_TOKEN(1), MAINTENANCE_BREAK(3), RECONNECT(5),
	SUBSCRIPTION_CHANGED(7), UNDEFINED(0), UNREGISTER(9);

	public int getCode() {
		return _code;
	}

	private SignOffReasonCode(int code) {
		_code = code;
	}

	private final int _code;

}