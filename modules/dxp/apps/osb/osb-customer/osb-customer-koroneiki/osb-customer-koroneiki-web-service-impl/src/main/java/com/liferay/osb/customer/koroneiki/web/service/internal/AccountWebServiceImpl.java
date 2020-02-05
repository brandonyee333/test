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

package com.liferay.osb.customer.koroneiki.web.service.internal;

import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = AccountWebService.class
)
public class AccountWebServiceImpl implements AccountWebService {

	public Account fetchAccount(String accountKey) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_accountResource.getAccountHttpResponse(accountKey);

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return AccountSerDes.toDTO(httpResponse.getContent());
	}

	public Account getAccount(String accountKey) throws Exception {
		return _accountResource.getAccount(accountKey);
	}

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception {

		Page<Account> accountsPage =
			_accountResource.getAccountByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(page, pageSize));

		if ((accountsPage != null) && (accountsPage.getItems() != null)) {
			return new ArrayList<>(accountsPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<Account> getContactAccountsByUuid(
			String contactUuid, int page, int pageSize)
		throws Exception {

		Page<Account> accountsPage =
			_accountResource.getContactByUuidContactUuidAccountsPage(
				contactUuid, Pagination.of(page, pageSize));

		if ((accountsPage != null) && (accountsPage.getItems() != null)) {
			return new ArrayList<>(accountsPage.getItems());
		}

		return Collections.emptyList();
	}

	public Account getAccountContactsContactRoles(String accountKey) 
		throws Exception {

		return _nestedFieldsAccountResource.getAccount(accountKey);
	}

	public List<Account> search(
			String filterString, int page, int pageSize, String sortString)
		throws Exception {

		Page<Account> accountsPage = _accountResource.getAccountsPage(
			StringPool.BLANK, filterString, Pagination.of(page, pageSize),
			sortString);

		if ((accountsPage != null) && (accountsPage.getItems() != null)) {
			return new ArrayList<>(accountsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String filterString) throws Exception {
		Page<Account> accountsPage = _accountResource.getAccountsPage(
			StringPool.BLANK, filterString, Pagination.of(1, 1),
			StringPool.BLANK);

		if (accountsPage != null) {
			return accountsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		AccountResource.Builder builder = AccountResource.builder();

		_accountResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();

		_nestedFieldsAccountResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields", "contacts,contacts.contactRoles"
		).build();
	}

	private AccountResource _accountResource;
	private AccountResource _nestedFieldsAccountResource;

}