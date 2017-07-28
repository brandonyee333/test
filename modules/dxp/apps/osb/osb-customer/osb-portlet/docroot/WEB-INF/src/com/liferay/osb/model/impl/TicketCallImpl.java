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

import com.liferay.osb.model.TicketCallConstants;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;

/**
 * @author Sharon Li
 */
public class TicketCallImpl extends TicketCallBaseImpl {

	public TicketCallImpl() {
	}

	public String getCallLengthLabel() {
		return Time.getSimpleDate(new Date(getCallLength()), "HH:mm:ss");
	}

	public String getTypeLabel() {
		if (getType() == TicketCallConstants.TYPE_INCOMING) {
			return "incoming";
		}
		else if (getType() == TicketCallConstants.TYPE_OUTGOING) {
			return "outgoing";
		}
		else {
			return TicketCallConstants.NOT_AVAILABLE;
		}
	}

}