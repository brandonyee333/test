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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
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
			userId,
			OSBCustomerConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID,
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

		serviceContext.setAttribute(
			"corpProjectId", jsonObject.getLong("corpProjectId"));
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