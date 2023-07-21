/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface AccountWebService {

	public Account fetchAccount(String accountKey) throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public Account getAccountContactsContactRoles(String accountKey)
		throws Exception;

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public List<Account> getContactAccountsByUuid(
			String contactUuid, int page, int pageSize)
		throws Exception;

	public List<Account> getTeamAssignedAccounts(
			String teamKey, int page, int pageSize)
		throws Exception;

	public List<Account> search(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, String filterString)
		throws Exception;

	public void unassignContactRoles(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public Account updateAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

}