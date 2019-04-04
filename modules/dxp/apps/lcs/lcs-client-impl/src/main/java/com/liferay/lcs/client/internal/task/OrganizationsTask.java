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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(immediate = true, service = ScheduledTask.class)
public class OrganizationsTask extends BasePortalModelTask {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		setPauseInterval(lcsConfiguration.scheduledTaskPauseInterval());
		setPageSize(lcsConfiguration.scheduledTaskPageSize());
	}

	@Override
	protected List<Map<String, Object>> getModels(int start, int end) {
		List<Map<String, Object>> organizationMaps = new ArrayList<>();

		List<Organization> organizations =
			_organizationLocalService.getOrganizations(start, end);

		for (Organization organization : organizations) {
			organizationMaps.add(getOrganizationMap(organization));
		}

		return organizationMaps;
	}

	@Override
	protected long getModelsCount() {
		return _organizationLocalService.getOrganizationsCount();
	}

	protected Map<String, Object> getOrganizationMap(
		Organization organization) {

		Map<String, Object> organizationMap = new HashMap<>();

		int organizationUsersCount =
			_userLocalService.getOrganizationUsersCount(
				organization.getOrganizationId());

		organizationMap.put("companyId", organization.getCompanyId());
		organizationMap.put("name", organization.getName());
		organizationMap.put("organizationId", organization.getOrganizationId());
		organizationMap.put(
			"parentOrganizationId", organization.getParentOrganizationId());
		organizationMap.put("userCount", organizationUsersCount);
		organizationMap.put("uuid", organization.getUuid());

		return organizationMap;
	}

	@Override
	protected PortalModelMessage.Type getPortalModelType() {
		return PortalModelMessage.Type.ORGANIZATION;
	}

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private UserLocalService _userLocalService;

}