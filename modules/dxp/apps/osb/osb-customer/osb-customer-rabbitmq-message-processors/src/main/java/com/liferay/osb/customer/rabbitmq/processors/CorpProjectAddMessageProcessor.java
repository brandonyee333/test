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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.corpproject.add",
	service = CorpProjectAddMessageProcessor.class
)
public class CorpProjectAddMessageProcessor extends BaseMessageProcessor {

	protected Organization addOrganization(long userId, JSONObject jsonObject)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(jsonObject.getString("uuid"));

		Organization organization = organizationLocalService.addOrganization(
			userId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			jsonObject.getString("name"),
			OrganizationConstants.TYPE_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, serviceContext);

		ExpandoBridge expandoBridge = organization.getExpandoBridge();

		expandoBridge.setAttribute("remote", true, false);

		return organization;
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		CorpProject corpProject =
			CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
				jsonObject.getString("uuid"));

		if (corpProject != null) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject);

		if (user == null) {
			user = addUser(userJSONObject);
		}

		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		Organization organization = fetchOrganization(organizationJSONObject);

		if (organization == null) {
			organization = addOrganization(
				user.getUserId(), organizationJSONObject);
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(
			new Date(jsonObject.getLong("createDate")));
		serviceContext.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		serviceContext.setUuid(jsonObject.getString("uuid"));

		CorpProjectLocalServiceUtil.addCorpProject(
			user.getUserId(), jsonObject.getString("dossieraProjectKey"),
			jsonObject.getString("salesforceProjectKey"),
			jsonObject.getString("name"), organization.getOrganizationId(),
			serviceContext);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}