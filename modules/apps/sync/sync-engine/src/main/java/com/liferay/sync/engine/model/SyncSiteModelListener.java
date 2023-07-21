/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.service.SyncSiteService;

import java.util.Map;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class SyncSiteModelListener implements ModelListener<SyncSite> {

	@Override
	public void onCreate(SyncSite syncSite) {
	}

	@Override
	public void onRemove(SyncSite syncSite) {
		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncSite.getSyncAccountId());

		activeSyncSiteIds.remove(syncSite.getSyncSiteId());
	}

	@Override
	public void onUpdate(
		SyncSite syncSite, Map<String, Object> originalValues) {

		if (!originalValues.containsKey("active")) {
			return;
		}

		Set<Long> activeSyncSiteIds = SyncSiteService.getActiveSyncSiteIds(
			syncSite.getSyncAccountId());

		if ((boolean)originalValues.get("active")) {
			activeSyncSiteIds.remove(syncSite.getSyncSiteId());
		}
		else {
			activeSyncSiteIds.add(syncSite.getSyncSiteId());
		}
	}

}