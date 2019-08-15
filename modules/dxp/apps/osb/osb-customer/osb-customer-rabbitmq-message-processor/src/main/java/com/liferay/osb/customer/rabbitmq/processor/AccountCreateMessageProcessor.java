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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=koroneiki.account.create",
	service = AccountCreateMessageProcessor.class
)
public class AccountCreateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		String accountKey = jsonObject.getString("key");

		CorpProject corpProject =
			CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(accountKey);

		if (corpProject != null) {
			return;
		}

		String name = jsonObject.getString("name");
		String dossieraProjectKey = StringPool.BLANK;
		String salesforceProjectKey = StringPool.BLANK;

		JSONArray jsonArray = jsonObject.getJSONArray("externalLinks");

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject externalLinkJSONObject = jsonArray.getJSONObject(i);

				String domain = externalLinkJSONObject.getString("domain");
				String entityName = externalLinkJSONObject.getString(
					"entityName");

				if (domain.equals("dossiera") && entityName.equals("project")) {
					dossieraProjectKey = externalLinkJSONObject.getString(
						"entityId");
				}
				else if (domain.equals("salesforce") &&
						 entityName.equals("project")) {

					salesforceProjectKey = externalLinkJSONObject.getString(
						"entityId");
				}
			}
		}

		Organization organization = _addOrganization(name);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAttribute(
			"corpProjectId", GetterUtil.getLong(accountKey.substring(4)));
		serviceContext.setUuid(accountKey);

		CorpProjectLocalServiceUtil.addCorpProject(
			OSBCustomerConstants.USER_DEFAULT_USER_ID, dossieraProjectKey,
			salesforceProjectKey, name, organization.getOrganizationId(),
			serviceContext);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private Organization _addOrganization(String name) throws PortalException {
		Organization organization = organizationLocalService.addOrganization(
			OSBCustomerConstants.USER_DEFAULT_USER_ID,
			OSBCustomerConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID,
			name, OrganizationConstants.TYPE_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, new ServiceContext());

		ExpandoBridge expandoBridge = organization.getExpandoBridge();

		expandoBridge.setAttribute("remote", true, false);

		return organization;
	}

}