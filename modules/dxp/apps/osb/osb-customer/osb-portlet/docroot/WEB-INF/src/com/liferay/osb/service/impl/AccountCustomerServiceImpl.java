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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.base.AccountCustomerServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AccountCustomerServiceImpl extends AccountCustomerServiceBaseImpl {

	@JSONWebService
	public List<User> getCorpProjectAccountCustomerUsers(String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry =
			accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);

		if (accountEntry == null) {
			return Collections.emptyList();
		}

		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(
				accountEntry.getAccountEntryId());

		List<User> users = new ArrayList<>(accountCustomers.size());

		for (AccountCustomer accountCustomer : accountCustomers) {
			User user = userLocalService.getUser(accountCustomer.getUserId());

			users.add(user);
		}

		return users;
	}

	@JSONWebService
	public List<String> getCorpProjectAccountCustomerUUIDs(
			String corpProjectUuid)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry =
			accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);

		if (accountEntry == null) {
			return Collections.emptyList();
		}

		List<AccountCustomer> accountCustomers =
			accountCustomerPersistence.findByAccountEntryId(
				accountEntry.getAccountEntryId());

		List<String> uuids = new ArrayList<>(accountCustomers.size());

		for (AccountCustomer accountCustomer : accountCustomers) {
			User user = userLocalService.fetchUser(accountCustomer.getUserId());

			if (user != null) {
				uuids.add(user.getUuid());
			}
		}

		return uuids;
	}

	@JSONWebService
	public List<User> getCorpProjectIdAccountCustomerUsers(long corpProjectId)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		return getCorpProjectAccountCustomerUsers(corpProject.getUuid());
	}

	@JSONWebService
	public List<String> getCorpProjectIdAccountCustomerUUIDs(long corpProjectId)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		return getCorpProjectAccountCustomerUUIDs(corpProject.getUuid());
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}