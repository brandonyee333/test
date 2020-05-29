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