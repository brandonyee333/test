/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.util;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncLanClient;
import com.liferay.sync.engine.model.SyncProp;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncPropService;
import com.liferay.sync.engine.service.SyncSiteService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Dennis Ju
 */
public class LanClientUtil {

	public static SyncLanClient createSyncLanClient(int port) throws Exception {
		SyncLanClient syncLanClient = new SyncLanClient();

		Map<String, Set<Long>> endpoints = new HashMap<>();

		for (SyncAccount syncAccount : SyncAccountService.findAll()) {
			if (!syncAccount.isActive() || !syncAccount.isLanEnabled()) {
				continue;
			}

			endpoints.put(
				syncAccount.getLanServerUuid(),
				SyncSiteService.getActiveGroupIds(
					syncAccount.getSyncAccountId()));
		}

		syncLanClient.setEndpoints(endpoints);

		syncLanClient.setPort(port);
		syncLanClient.setSyncLanClientUuid(getSyncLanClientUuid());

		return syncLanClient;
	}

	public static String getSNIHostname(String lanServerUuid) {
		return lanServerUuid + ".com";
	}

	public static String getSyncLanClientUuid() throws Exception {
		String syncLanClientUuid = SyncPropService.getValue(
			SyncProp.KEY_SYNC_LAN_CLIENT_UUID);

		if (syncLanClientUuid == null) {
			syncLanClientUuid = String.valueOf(UUID.randomUUID());

			SyncPropService.updateSyncProp(
				SyncProp.KEY_SYNC_LAN_CLIENT_UUID, syncLanClientUuid);
		}

		return syncLanClientUuid;
	}

}