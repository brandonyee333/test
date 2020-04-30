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

	public List<Account> search(
			String filterString, int page, int pageSize, String sortString)
		throws Exception;

	public long searchCount(String filterString) throws Exception;

}