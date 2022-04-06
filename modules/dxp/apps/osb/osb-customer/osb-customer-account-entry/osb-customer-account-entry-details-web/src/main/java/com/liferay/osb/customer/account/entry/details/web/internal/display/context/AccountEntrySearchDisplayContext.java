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

package com.liferay.osb.customer.account.entry.details.web.internal.display.context;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntrySearchDisplayContext {

	public AccountEntrySearchDisplayContext(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			ThemeDisplay themeDisplay, AccountReader accountReader,
			AccountWebService accountWebService,
			ProductWebService productWebService,
			TeamRoleWebService teamRoleWebService)
		throws PortalException {

		_portletRequest = portletRequest;
		_mimeResponse = mimeResponse;
		_themeDisplay = themeDisplay;
		_accountReader = accountReader;
		_accountWebService = accountWebService;
		_productWebService = productWebService;
		_teamRoleWebService = teamRoleWebService;
	}

	public SearchContainer getAccountsSearchContainer() throws Exception {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		String keywords = ParamUtil.getString(_portletRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			_portletRequest, _mimeResponse.createRenderURL(),
			Collections.emptyList(), "no-accounts-were-found");

		StringBundler sb = new StringBundler(17);

		if (Validator.isNotNull(keywords)) {
			sb.append("(contains(name, '");
			sb.append(keywords);
			sb.append("') or contains(code, '");
			sb.append(keywords);
			sb.append("'))");
		}

		if (!isAdminOrSubscriptionServices()) {
			User user = _themeDisplay.getUser();

			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append("(contactUuids/any(s:s eq '");
			sb.append(user.getUuid());
			sb.append("') or ");
			sb.append("assignedTeamKeyTeamRoleKeyContactUuidContactRoleKeys");
			sb.append("/any(s:contains(s, '");

			TeamRole teamRole = _teamRoleWebService.fetchTeamRole(
				TeamRole.Type.ACCOUNT.toString(),
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

			if (teamRole != null) {
				sb.append(teamRole.getKey());
				sb.append("*");
			}

			sb.append(user.getUuid());
			sb.append("')))");
		}

		String productFilter = getProductsFilter();

		if (Validator.isNotNull(productFilter)) {
			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append(productFilter);
		}

		int total = (int)_accountWebService.searchCount(
			StringPool.BLANK, sb.toString());

		searchContainer.setTotal(total);

		String sort = StringPool.BLANK;

		if (Validator.isNull(keywords)) {
			sort = "name";
		}

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), sort);

		searchContainer.setResults(accounts);

		_searchContainer = searchContainer;

		return searchContainer;
	}

	public String getState(String accountKey) throws Exception {
		return _accountReader.getState(accountKey);
	}

	public boolean isAdminOrSubscriptionServices() {
		if (RoleLocalServiceUtil.hasUserRole(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.ROLE_ADMINISTRATOR_ID)) {

			return true;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.
					ORGANIZATION_DIVISION_SUBSCRIPTION_SERVICES_ID)) {

			return true;
		}

		return false;
	}

	protected String getProductsFilter() throws Exception {
		List<Product> products = new ArrayList<>();

		Product product = _productWebService.fetchProductByName(
			ProductConstants.NAME_GOLD);

		if (product != null) {
			products.add(product);
		}

		product = _productWebService.fetchProductByName(
			ProductConstants.NAME_LIMITED);

		if (product != null) {
			products.add(product);
		}

		product = _productWebService.fetchProductByName(
			ProductConstants.NAME_PLATINUM);

		if (product != null) {
			products.add(product);
		}

		for (String name : ProductConstants.NAMES_PARTNERSHIP) {
			product = _productWebService.fetchProductByName(name);

			if (product != null) {
				products.add(product);
			}
		}

		if (products.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(7);

		sb.append("productKeys/any(s:s eq '");

		for (int i = 0; i < products.size(); i++) {
			Product curProduct = products.get(i);

			sb.append(curProduct.getKey());

			if ((i + 1) < products.size()) {
				sb.append("' or s eq '");
			}
		}

		sb.append("')");

		return sb.toString();
	}

	private final AccountReader _accountReader;
	private final AccountWebService _accountWebService;
	private final MimeResponse _mimeResponse;
	private final PortletRequest _portletRequest;
	private final ProductWebService _productWebService;
	private SearchContainer _searchContainer;
	private final TeamRoleWebService _teamRoleWebService;
	private final ThemeDisplay _themeDisplay;

}