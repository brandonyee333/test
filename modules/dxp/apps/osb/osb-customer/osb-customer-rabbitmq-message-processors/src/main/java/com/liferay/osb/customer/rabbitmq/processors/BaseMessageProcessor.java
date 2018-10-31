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
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

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

	protected User addUser(JSONObject jsonObject) throws PortalException {
		User remoteUser = RemoteUserLocalServiceUtil.translate(jsonObject);

		Company company = companyLocalService.getCompany(
			remoteUser.getCompanyId());

		User defaultUser = company.getDefaultUser();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(remoteUser.getCreateDate());
		serviceContext.setUuid(remoteUser.getUuid());

		User user = userLocalService.addUser(
			defaultUser.getUserId(), remoteUser.getCompanyId(), true,
			StringPool.BLANK, StringPool.BLANK, false,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(), 0,
			StringPool.BLANK, remoteUser.getLocale(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(), 0, 0, false,
			0, 1, 1970, StringPool.BLANK, new long[0],
			remoteUser.getOrganizationIds(), remoteUser.getRoleIds(),
			new long[0], false, serviceContext);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		return user;
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	protected Organization fetchOrganization(JSONObject jsonObject) {
		if (jsonObject == null) {
			return null;
		}

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
		if (jsonObject == null) {
			return null;
		}

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
		if (jsonObject == null) {
			return null;
		}

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
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
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
	protected void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {

		this.userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	protected ClassNameLocalService classNameLocalService;
	protected CompanyLocalService companyLocalService;
	protected JSONFactory jsonFactory;
	protected OrganizationLocalService organizationLocalService;
	protected RoleLocalService roleLocalService;
	protected UserGroupRoleLocalService userGroupRoleLocalService;
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageProcessor.class);

}