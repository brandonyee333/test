/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model.impl;

import com.liferay.portal.kernel.util.Time;
import com.liferay.osb.model.AccountCallConstants;

import java.util.Date;

/**
 * @author Alan Zhang
 */
public class AccountCallImpl extends AccountCallBaseImpl {

	public AccountCallImpl() {
	}

	public int getCallLengthHours() {
		return (int)(getCallLength() / Time.HOUR);
	}

	public String getCallLengthLabel() {
		return Time.getSimpleDate(new Date(getCallLength()), "HH:mm:ss");
	}

	public int getCallLengthMinutes() {
		long remainingMinutes = getCallLength() % Time.HOUR;

		return (int)(remainingMinutes / Time.MINUTE);
	}

	public int getCallLengthSeconds() {
		long remainingSeconds = (getCallLength() % Time.HOUR) % Time.MINUTE;

		return (int)(remainingSeconds / Time.SECOND);
	}

	public String getTypeLabel() {
		if (getType() == AccountCallConstants.TYPE_INCOMING) {
			return "incoming";
		}
		else if (getType() == AccountCallConstants.TYPE_OUTGOING) {
			return "outgoing";
		}
		else if (getType() == AccountCallConstants.TYPE_STANDING) {
			return "standing";
		}
		else {
			return AccountCallConstants.NOT_AVAILABLE;
		}
	}

}