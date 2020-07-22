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

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void receive(Message message) {
		try {
			try {
				JSONObject jsonObject = jsonFactory.createJSONObject(
					(String)message.getPayload());

				doReceive(jsonObject);
			}
			catch (JSONException jsone) {
				JSONArray jsonArray = jsonFactory.createJSONArray(
					(String)message.getPayload());

				for (int i = 0; i < jsonArray.length(); i++) {
					doReceive(jsonArray.getJSONObject(i));
				}
			}
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected User addKoroneikiUser(JSONObject jsonObject)
		throws PortalException {

		User defaultUser = userLocalService.getUser(
			OSBCustomerConstants.USER_DEFAULT_USER_ID);

		Locale locale = LocaleUtil.fromLanguageId(
			jsonObject.getString("languageId"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(jsonObject.getString("key"));

		return userLocalService.addUser(
			defaultUser.getUserId(), defaultUser.getCompanyId(), true,
			StringPool.BLANK, StringPool.BLANK, true, StringPool.BLANK,
			jsonObject.getString("emailAddress"), 0, StringPool.BLANK, locale,
			jsonObject.getString("firstName"),
			jsonObject.getString("middleName"),
			jsonObject.getString("lastName"), 0, 0, false, 0, 1, 1970,
			StringPool.BLANK, new long[0], new long[0], new long[0],
			new long[0], false, serviceContext);
	}

	protected User addWebUser(JSONObject jsonObject) throws PortalException {
		/*
		TODO
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
		*/

		return null;
	}

	protected abstract void doReceive(JSONObject jsonObject) throws Exception;

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

	protected Organization fetchOrganization(String name) {
		if (Validator.isNull(name)) {
			return null;
		}

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			Organization organization =
				organizationLocalService.fetchOrganization(
					company.getCompanyId(), name);

			if (organization != null) {
				return organization;
			}
		}

		return null;
	}

	protected Role fetchRole(String uuid) {
		if (Validator.isNull(uuid)) {
			return null;
		}

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

	protected User fetchUser(String uuid) {
		if (Validator.isNull(uuid)) {
			return null;
		}

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

	protected User fetchUserByEmailAddress(String emailAddress) {
		if (Validator.isNull(emailAddress)) {
			return null;
		}

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = userLocalService.fetchUserByEmailAddress(
				company.getCompanyId(), emailAddress);

			if (user != null) {
				return user;
			}
		}

		return null;
	}

	@Reference
	protected ClassNameLocalService classNameLocalService;

	@Reference
	protected CompanyLocalService companyLocalService;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

	@Reference
	protected UserGroupRoleLocalService userGroupRoleLocalService;

	@Reference(target = "(provider=web)")
	protected UserIdentityProvider userIdentityProvider;

	@Reference
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}