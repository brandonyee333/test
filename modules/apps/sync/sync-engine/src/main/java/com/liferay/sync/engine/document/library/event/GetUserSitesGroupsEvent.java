/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.event.constants.EventURLPaths;
import com.liferay.sync.engine.document.library.handler.GetUserSitesGroupsHandler;
import com.liferay.sync.engine.document.library.handler.Handler;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetUserSitesGroupsEvent extends BaseEvent {

	public GetUserSitesGroupsEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, EventURLPaths.GET_USER_SITES_GROUPS, parameters);

		_handler = new GetUserSitesGroupsHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	private final Handler<Void> _handler;

}