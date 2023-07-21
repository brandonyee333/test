/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.update",
	service = OrganizationUpdateMessageSubscriber.class
)
public class OrganizationUpdateMessageSubscriber extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		Organization organization = fetchOrganization(jsonObject);

		if (organization == null) {
			return;
		}

		String name = organization.getName();

		if (name.startsWith(EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {
			return;
		}

		Group group = organization.getGroup();

		organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(),
			jsonObject.getString("name"), organization.getType(),
			organization.getRegionId(), organization.getCountryId(),
			organization.getStatusId(), organization.getComments(), true, null,
			group.isSite(), null);
	}

}