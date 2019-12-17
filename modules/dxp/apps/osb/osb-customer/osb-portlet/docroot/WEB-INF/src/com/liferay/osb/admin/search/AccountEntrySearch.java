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

package com.liferay.osb.admin.search;

import com.liferay.osb.model.AccountEntry;
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
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.SUPPORT_REGION_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportRegionIds()));
	}

}