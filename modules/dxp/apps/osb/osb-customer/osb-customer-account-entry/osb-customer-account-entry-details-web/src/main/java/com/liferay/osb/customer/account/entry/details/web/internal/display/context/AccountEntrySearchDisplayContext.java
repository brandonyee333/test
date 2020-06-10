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
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
			ThemeDisplay themeDisplay, AccountWebService accountWebService)
		throws PortalException {

		_portletRequest = portletRequest;
		_mimeResponse = mimeResponse;
		_themeDisplay = themeDisplay;
		_accountWebService = accountWebService;
	}

	public SearchContainer getAccountsSearchContainer() throws Exception {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		String keywords = ParamUtil.getString(_portletRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			_portletRequest, _mimeResponse.createRenderURL(),
			Collections.emptyList(), "no-accounts-were-found");

		StringBundler sb = new StringBundler(9);

		if (Validator.isNotNull(keywords)) {
			sb.append("(name eq '");
			sb.append(keywords);
			sb.append("' or code eq '");
			sb.append(keywords);
			sb.append("')");
		}

		if (!isLiferayContractorOrg() && !isLiferayIncOrg()) {
			User user = _themeDisplay.getUser();

			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append("contactUuids/any(u:u eq '");
			sb.append(user.getUuid());
			sb.append("')");
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

	public boolean isLiferayContractorOrg() {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID)) {

			return true;
		}

		return false;
	}

	public boolean isLiferayIncOrg() {
		if (OrganizationLocalServiceUtil.hasUserOrganization(
				_themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	private final AccountWebService _accountWebService;
	private final MimeResponse _mimeResponse;
	private final PortletRequest _portletRequest;
	private SearchContainer _searchContainer;
	private final ThemeDisplay _themeDisplay;

}