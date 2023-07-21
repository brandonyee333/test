/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class EventRequestImpl
	extends PortletRequestImpl implements EventRequest {

	@Override
	public Event getEvent() {
		return _event;
	}

	@Override
	public String getLifecycle() {
		return PortletRequest.EVENT_PHASE;
	}

	public void setEvent(Event event) {
		_event = event;
	}

	private Event _event;

}