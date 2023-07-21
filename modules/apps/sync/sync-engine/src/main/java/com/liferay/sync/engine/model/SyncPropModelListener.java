/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.liferay.sync.engine.lan.server.LanEngine;
import com.liferay.sync.engine.session.rate.limiter.RateLimiterManager;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncPropModelListener implements ModelListener<SyncProp> {

	@Override
	public void onCreate(SyncProp syncProp) {
		updateRateLimits(syncProp);
	}

	@Override
	public void onRemove(SyncProp syncProp) {
	}

	@Override
	public void onUpdate(
		SyncProp syncProp, Map<String, Object> originalValues) {

		updateLanSync(syncProp);

		updateRateLimits(syncProp);
	}

	protected void updateLanSync(SyncProp syncProp) {
		String key = syncProp.getKey();

		if (!key.equals(SyncProp.KEY_LAN_ENABLED)) {
			return;
		}

		if (Boolean.valueOf(syncProp.getValue())) {
			LanEngine.start();
		}
		else {
			LanEngine.stop();
		}
	}

	protected void updateRateLimits(SyncProp syncProp) {
		String key = syncProp.getKey();

		if (key.equals(SyncProp.KEY_GLOBAL_MAX_DOWNLOAD_RATE)) {
			RateLimiterManager.updateDownloadRateLimits();
		}

		if (key.equals(SyncProp.KEY_GLOBAL_MAX_UPLOAD_RATE)) {
			RateLimiterManager.updateUploadRateLimits();
		}
	}

}