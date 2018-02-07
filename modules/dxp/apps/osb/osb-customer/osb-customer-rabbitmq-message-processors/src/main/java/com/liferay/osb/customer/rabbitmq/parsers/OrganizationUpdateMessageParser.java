/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.rabbitmq.parsers;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.util.PortalInstances;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"routing.key=entity.organization.update"}
)
public class OrganizationUpdateMessageParser implements MessageParser {

	public OrganizationUpdateMessageParser() {
		_companyId = PortalInstances.getDefaultCompanyId();
	}

	public void parse(String message) {
		try {
			updateOrganization(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected void updateOrganization(String message) throws Exception {
		JSONObject jsonObject = _jsonFactory.createJSONObject(message.trim());

		String uuid = jsonObject.getString(FIELD_UUID);

		Organization organization =
			_organizationLocalService.fetchOrganizationByUuidAndCompanyId(
				uuid, _companyId);

		if (organization == null) {
			return;
		}

		String name = jsonObject.getString(FIELD_NAME);

		Group group = organization.getGroup();

		_organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(),
			organization.getComments(), true, null, group.isSite(), null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationUpdateMessageParser.class);

	private final long _companyId;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private OrganizationLocalService _organizationLocalService;

}