/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.model;

import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.osb.customer.metrics.sync.service.SyncStateLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class ZendeskBaseMetricsModel<T> implements MetricsModel<T> {

	@Override
	public boolean allowDeleteAll() {
		return false;
	}

	@Override
	public void deleteAll() throws Exception {
	}

	@Override
	public Map<String, Object> getAttributes(T model) {
		return null;
	}

	@Override
	public String[] getMappingTables() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getMappingValues(T model) {
		return null;
	}

	@Override
	public abstract Class getModelClass();

	@Override
	public String getModelName() {
		return null;
	}

	@Override
	public String getModelPrimaryKeyName() {
		return null;
	}

	@Override
	public boolean hasMapping() {
		return false;
	}

	@Override
	public void resyncAll() throws Exception {
		Class<?> clazz = getModelClass();

		SyncState syncState = syncStateLocalService.fetchSyncState(
			clazz.getName());

		syncState.setLastRunTime(0);

		syncStateLocalService.updateSyncState(syncState);
	}

	@Reference
	protected SyncStateLocalService syncStateLocalService;

}