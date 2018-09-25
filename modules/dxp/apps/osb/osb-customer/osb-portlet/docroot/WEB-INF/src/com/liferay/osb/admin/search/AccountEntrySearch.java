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

	public static final String EMPTY_RESULTS_MESSAGE = "no-projects-were-found";

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("name");
		headerNames.add("code");
		headerNames.add("partner");
		headerNames.add("support-end-date");
	}

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
			AccountEntryDisplayTerms.CITY, displayTerms.getCity());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.COUNTRY_ID,
			String.valueOf(displayTerms.getCountryId()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_GT_DAY,
			String.valueOf(displayTerms.getCreateDateGTDay()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getCreateDateGTMonth()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getCreateDateGTYear()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_LT_DAY,
			String.valueOf(displayTerms.getCreateDateLTDay()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getCreateDateLTMonth()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getCreateDateLTYear()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.CREATE_USER_ID,
			String.valueOf(displayTerms.getCreateUserId()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.EXPIRED_SUPPORT,
			String.valueOf(displayTerms.expiredSupport));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.INDUSTRIES,
			ArrayUtil.toStringArray(displayTerms.getIndustries()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.INSTRUCTIONS,
			displayTerms.getInstructions());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_GT_DAY,
			String.valueOf(displayTerms.getModifiedDateGTDay()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_GT_MONTH,
			String.valueOf(displayTerms.getModifiedDateGTMonth()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_GT_YEAR,
			String.valueOf(displayTerms.getModifiedDateGTYear()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_LT_DAY,
			String.valueOf(displayTerms.getModifiedDateLTDay()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_LT_MONTH,
			String.valueOf(displayTerms.getModifiedDateLTMonth()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_DATE_LT_YEAR,
			String.valueOf(displayTerms.getModifiedDateLTYear()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.MODIFIED_USER_ID,
			String.valueOf(displayTerms.getModifiedUserId()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.NOTES, displayTerms.getNotes());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.PARTNER_ENTRY_CODE,
			displayTerms.getPartnerEntryCode());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.PARTNER_MANAGED_SUPPORT,
			String.valueOf(displayTerms.getPartnerManagedSupport()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.PRODUCT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getProductEntryIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.REGION_ID,
			String.valueOf(displayTerms.getRegionId()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.STATUSES,
			ArrayUtil.toStringArray(displayTerms.getStatuses()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.STREET, displayTerms.getStreet());
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.SUPPORT,
			String.valueOf(displayTerms.getSupport()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.SUPPORT_REGION_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportRegionIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.SUPPORT_RESPONSE_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportResponseIds()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.TICKET_SUPPORT,
			String.valueOf(displayTerms.getTicketSupport()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.TIERS,
			ArrayUtil.toStringArray(displayTerms.getTiers()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.TYPES,
			ArrayUtil.toStringArray(displayTerms.getTypes()));
		iteratorURL.setParameter(
			AccountEntryDisplayTerms.ZIP, displayTerms.getZip());
	}

}