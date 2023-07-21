/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AccountSearch extends SearchContainer<Account> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-accounts-were-found";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("name");
			add("code");
		}
	};

	public AccountSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new AccountDisplayTerms(portletRequest),
			new AccountSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		AccountDisplayTerms displayTerms =
			(AccountDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AccountDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			AccountDisplayTerms.KORONEIKI_ACCOUNT_KEY,
			displayTerms.getKoroneikiAccountKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.DOSSIERA_ACCOUNT_KEY,
			displayTerms.getDossieraAccountKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.NAME, displayTerms.getName());
	}

}