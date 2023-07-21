/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.service.impl;

import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.osb.customer.metrics.sync.service.base.SyncStateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class SyncStateLocalServiceImpl extends SyncStateLocalServiceBaseImpl {

	public SyncState addSyncState(String modelName) {
		long syncStateId = counterLocalService.increment();

		SyncState syncState = syncStatePersistence.create(syncStateId);

		syncState.setModelName(modelName);
		syncState.setLastRunTime(0);

		return syncStatePersistence.update(syncState);
	}

	public SyncState fetchSyncState(String modelName) {
		return syncStatePersistence.fetchByModelName(modelName);
	}

	public SyncState updateSyncState(String modelName, long lastRunTime)
		throws PortalException {

		SyncState syncState = syncStatePersistence.findByModelName(modelName);

		syncState.setLastRunTime(lastRunTime);

		return syncStatePersistence.update(syncState);
	}

}