/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.metrics.sync.service.impl;

import com.liferay.osb.customer.metrics.sync.exception.NoSuchSyncStateException;
import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.osb.customer.metrics.sync.service.base.SyncStateLocalServiceBaseImpl;

/**
 * @author Kyle Bischof
 */
public class SyncStateLocalServiceImpl extends SyncStateLocalServiceBaseImpl {

	public SyncState addSyncState(String model) {
		long syncStateId = counterLocalService.increment();

		SyncState syncState = syncStatePersistence.create(syncStateId);

		syncState.setLastRunTime(0);
		syncState.setModel(model);

		return syncStatePersistence.update(syncState);
	}

	public SyncState fetchSyncState(String model)
		throws NoSuchSyncStateException {

		return syncStatePersistence.fetchByModel(model);
	}

	public SyncState updateSyncState(String model, long lastRunTime)
		throws NoSuchSyncStateException {

		SyncState syncState = syncStatePersistence.findByModel(model);

		syncState.setLastRunTime(lastRunTime);

		return syncStatePersistence.update(syncState);
	}

}