/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.web.internal.search;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AccountEntrySearch extends SearchContainer<AccountEntry> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-accounts-were-found";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("name");
			add("code");
			add("partner");
			add("support-end-date");
		}
	};

	public AccountEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new AccountEntryDisplayTerms(portletRequest),
			new AccountEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		AccountEntryDisplayTerms displayTerms =
			(AccountEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ACCOUNT_ENVIRONMENT_ENV_AS_IDS,
			ArrayUtil.toStringArray(
				displayTerms.getAccountEnvironmentEnvASIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ACCOUNT_ENVIRONMENT_ENV_DB_IDS,
			ArrayUtil.toStringArray(
				displayTerms.getAccountEnvironmentEnvDBIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ACCOUNT_ENVIRONMENT_ENV_JVM_IDS,
			ArrayUtil.toStringArray(
				displayTerms.getAccountEnvironmentEnvJVMIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ACCOUNT_ENVIRONMENT_ENV_LFR_IDS,
			ArrayUtil.toStringArray(
				displayTerms.getAccountEnvironmentEnvLFRIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ACCOUNT_ENVIRONMENT_ENV_OS_IDS,
			ArrayUtil.toStringArray(
				displayTerms.getAccountEnvironmentEnvOSIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.KORONEIKI_ACCOUNT_KEY,
			displayTerms.getKoroneikiAccountKey());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.DOSSIERA_ACCOUNT_KEY,
			displayTerms.getDossieraAccountKey());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.INSTRUCTIONS,
			displayTerms.getInstructions());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.PRODUCT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getProductEntryIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.STATUSES,
			ArrayUtil.toStringArray(displayTerms.getStatuses()));
	}

}