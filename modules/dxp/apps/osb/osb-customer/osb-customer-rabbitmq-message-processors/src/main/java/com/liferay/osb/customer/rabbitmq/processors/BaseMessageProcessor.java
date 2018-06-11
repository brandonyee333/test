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

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseMessageProcessor implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				message.trim());

			doProcess(jsonObject);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	protected Organization fetchOrganization(JSONObject jsonObject) {
		String uuid = jsonObject.getString("uuid");

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			Organization organization =
				organizationLocalService.fetchOrganizationByUuidAndCompanyId(
					uuid, company.getCompanyId());

			if (organization != null) {
				return organization;
			}
		}

		return null;
	}

	protected Role fetchRole(JSONObject jsonObject) {
		String uuid = jsonObject.getString("uuid");

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			Role role = roleLocalService.fetchRoleByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (role != null) {
				return role;
			}
		}

		return null;
	}

	protected User fetchUser(JSONObject jsonObject) {
		String uuid = jsonObject.getString("uuid");

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = userLocalService.fetchUserByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (user != null) {
				return user;
			}
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		this.companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	protected CompanyLocalService companyLocalService;
	protected JSONFactory jsonFactory;
	protected OrganizationLocalService organizationLocalService;
	protected RoleLocalService roleLocalService;
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageProcessor.class);

}