/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.servlet.filters;

import com.liferay.util.axis.AxisCleanUpFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {
		HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=(osgi.http.whiteboard.context.name=wsrp-service)",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_DISPATCHER + "=" + HttpWhiteboardConstants.DISPATCHER_FORWARD,
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_DISPATCHER + "=" + HttpWhiteboardConstants.DISPATCHER_REQUEST,
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_NAME + "=com.liferay.wsrp.servlet.filters.WSRPAxisCleanUpFilter",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN + "=/*"
	},
	service = Filter.class
)
public class WSRPAxisCleanUpFilter extends AxisCleanUpFilter {
}