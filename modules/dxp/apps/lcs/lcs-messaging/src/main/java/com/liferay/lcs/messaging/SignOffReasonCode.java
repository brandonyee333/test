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