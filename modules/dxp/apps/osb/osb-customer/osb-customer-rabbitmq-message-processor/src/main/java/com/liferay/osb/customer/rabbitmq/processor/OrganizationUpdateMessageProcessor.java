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

package com.liferay.osb.customer.rabbitmq.processor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.organization.update",
	service = OrganizationUpdateMessageProcessor.class
)
public class OrganizationUpdateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Organization organization = fetchOrganization(jsonObject);

		if (organization == null) {
			return;
		}

		String name = jsonObject.getString("name");

		Group group = organization.getGroup();

		organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(),
			organization.getComments(), true, null, group.isSite(), null);
	}

}