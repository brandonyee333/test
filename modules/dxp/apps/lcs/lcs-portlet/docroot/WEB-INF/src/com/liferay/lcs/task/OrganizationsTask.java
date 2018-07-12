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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo Garcia
 */
public class OrganizationsTask extends BasePortalModelTask {

	@Override
	protected List<Map<String, Object>> getModels(int start, int end) {
		List<Map<String, Object>> organizationMaps = new ArrayList<>();

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getOrganizations(start, end);

		for (Organization organization : organizations) {
			organizationMaps.add(getOrganizationMap(organization));
		}

		return organizationMaps;
	}

	@Override
	protected long getModelsCount() {
		return OrganizationLocalServiceUtil.getOrganizationsCount();
	}

	protected Map<String, Object> getOrganizationMap(
		Organization organization) {

		Map<String, Object> organizationMap = new HashMap<>();

		int organizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
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

}