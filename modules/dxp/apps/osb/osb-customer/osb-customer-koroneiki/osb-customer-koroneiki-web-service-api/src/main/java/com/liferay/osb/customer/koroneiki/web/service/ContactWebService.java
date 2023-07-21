/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ContactWebService {

	public Contact fetchContactByUuid(String uuid) throws Exception;

	public List<Contact> getAccountContacts(
			String accountKey, int page, int pageSize)
		throws Exception;

	public List<Contact> getAccountCustomerContacts(
			String accountKey, int page, int pageSize)
		throws Exception;

	public List<Contact> getAccountWorkerContacts(
			String accountKey, int page, int pageSize)
		throws Exception;

	public List<Contact> getTeamContacts(String teamKey, int page, int pageSize)
		throws Exception;

	public List<Contact> search(String filterString, int page, int pageSize)
		throws Exception;

	public long searchCount(String filterString) throws Exception;

}