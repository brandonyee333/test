/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.base.AccountCustomerServiceBaseImpl;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
public class AccountCustomerServiceImpl extends AccountCustomerServiceBaseImpl {

	@JSONWebService
	public List<User> getCorpProjectAccountCustomerUsers(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry =
			accountEntryLocalService.fetchCorpProjectAccountEntry(
				corpProjectUuid);

		if (accountEntry == null) {
			return Collections.emptyList();
		}

		List<User> users = new ArrayList<>();

		PermissionChecker permissionChecker = getPermissionChecker();

		try {
			StringBundler sb = new StringBundler(3);

			sb.append("customerAccountKeys/any(s:s eq '");
			sb.append(accountEntry.getKoroneikiAccountKey());
			sb.append("')");

			List<Contact> contacts = _contactWebService.search(
				sb.toString(), 1, 1000);

			for (Contact contact : contacts) {
				User user = userLocalService.fetchUserByEmailAddress(
					permissionChecker.getCompanyId(),
					contact.getEmailAddress());

				if (user != null) {
					users.add(user);
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return users;
	}

	@JSONWebService
	public List<String> getCorpProjectAccountCustomerUUIDs(
			String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry =
			accountEntryLocalService.fetchCorpProjectAccountEntry(
				corpProjectUuid);

		if (accountEntry == null) {
			return Collections.emptyList();
		}

		List<String> uuids = new ArrayList<>();

		PermissionChecker permissionChecker = getPermissionChecker();

		try {
			StringBundler sb = new StringBundler(3);

			sb.append("customerAccountKeys/any(s:s eq '");
			sb.append(accountEntry.getKoroneikiAccountKey());
			sb.append("')");

			List<Contact> contacts = _contactWebService.search(
				sb.toString(), 1, 1000);

			for (Contact contact : contacts) {
				User user = userLocalService.fetchUserByEmailAddress(
					permissionChecker.getCompanyId(),
					contact.getEmailAddress());

				if (user != null) {
					uuids.add(user.getUuid());
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return uuids;
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

	@ServiceReference(type = ContactWebService.class)
	private ContactWebService _contactWebService;

}