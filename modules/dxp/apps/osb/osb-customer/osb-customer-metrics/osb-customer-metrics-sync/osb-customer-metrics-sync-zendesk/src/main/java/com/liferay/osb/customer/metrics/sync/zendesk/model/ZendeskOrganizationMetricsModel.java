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
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class ZendeskOrganizationMetricsModel
	implements MetricsModel<ZendeskOrganization> {

	@Override
	public boolean allowDeleteAll() {
		return false;
	}

	@Override
	public void deleteAll() throws Exception {
	}

	@Override
	public Map<String, Object> getAttributes(ZendeskOrganization model) {
		return null;
	}

	@Override
	public String[] getMappingTables() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getMappingValues(
		ZendeskOrganization model) {

		return null;
	}

	@Override
	public Class getModelClass() {
		return ZendeskOrganization.class;
	}

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
		SyncState syncState = _syncStateLocalService.fetchSyncState(
			ZendeskOrganization.class.getName());

		syncState.setLastRunTime(0);

		_syncStateLocalService.updateSyncState(syncState);
	}

	@Reference
	private SyncStateLocalService _syncStateLocalService;

}