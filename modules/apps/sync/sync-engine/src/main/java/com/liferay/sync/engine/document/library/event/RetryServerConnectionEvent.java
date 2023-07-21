/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.handler.Handler;
import com.liferay.sync.engine.document.library.handler.RetryServerConnectionHandler;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class RetryServerConnectionEvent extends GetSyncContextEvent {

	public RetryServerConnectionEvent(
		long syncAccountId, Map<String, Object> parameters) {

		super(syncAccountId, parameters);

		_handler = new RetryServerConnectionHandler(this);
	}

	@Override
	public Handler<Void> getHandler() {
		return _handler;
	}

	@Override
	protected void processRequest() throws Exception {
		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		syncAccount.setState(SyncAccount.STATE_CONNECTING);

		SyncAccountService.update(syncAccount);

		super.processRequest();
	}

	private final Handler<Void> _handler;

}