/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.model.SyncDevice;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.util.JSONUtil;

/**
 * @author Shinn Lok
 */
public class RegisterSyncDeviceHandler extends BaseJSONHandler {

	public RegisterSyncDeviceHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncDevice syncDevice = JSONUtil.readValue(response, SyncDevice.class);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		syncAccount.setUuid(syncDevice.getUuid());

		SyncAccountService.update(syncAccount);
	}

}