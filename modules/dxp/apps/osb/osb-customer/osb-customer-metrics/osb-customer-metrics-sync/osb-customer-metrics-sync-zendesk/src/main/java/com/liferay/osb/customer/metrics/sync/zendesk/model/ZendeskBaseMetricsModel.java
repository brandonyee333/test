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

package com.liferay.osb.customer.metrics.sync.zendesk.model;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
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