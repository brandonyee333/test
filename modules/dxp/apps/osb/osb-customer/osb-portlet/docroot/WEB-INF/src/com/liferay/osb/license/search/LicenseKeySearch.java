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

package com.liferay.osb.license.search;

import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class LicenseKeySearch extends SearchContainer<LicenseKey> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-license-keys-were-found";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("account");
			add("name");
			add("product");
			add("version");
			add("start-date");
			add("expiration-date");
		}
	};
	public static Map<String, String> orderableHeaders =
		new HashMap<String, String>() {
			{
				put("expiration-date", "expiration-date");
				put("start-date", "start-date");
			}
		};

	public LicenseKeySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new LicenseKeyDisplayTerms(portletRequest),
			new LicenseKeySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		LicenseKeyDisplayTerms displayTerms =
			(LicenseKeyDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACCOUNT_ENTRY_NAME,
			displayTerms.getAccountEntryName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACTIVE,
			String.valueOf(displayTerms.getActive()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_GT_DAY,
			String.valueOf(displayTerms.getCreateDateGTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getCreateDateGTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getCreateDateGTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_LT_DAY,
			String.valueOf(displayTerms.getCreateDateLTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getCreateDateLTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getCreateDateLTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_USER_ID,
			String.valueOf(displayTerms.getCreateUserId()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_USER_NAME,
			displayTerms.getCreateUserName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_GT_DAY,
			String.valueOf(displayTerms.getExpirationDateGTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_GT_MONTH,
			String.valueOf(displayTerms.getExpirationDateGTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_GT_YEAR,
			String.valueOf(displayTerms.getExpirationDateGTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_LT_DAY,
			String.valueOf(displayTerms.getExpirationDateLTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_LT_MONTH,
			String.valueOf(displayTerms.getExpirationDateLTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_LT_YEAR,
			String.valueOf(displayTerms.getExpirationDateLTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_GT_DAY,
			String.valueOf(displayTerms.getModifiedDateGTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_GT_MONTH,
			String.valueOf(displayTerms.getModifiedDateGTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_GT_YEAR,
			String.valueOf(displayTerms.getModifiedDateGTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_LT_DAY,
			String.valueOf(displayTerms.getModifiedDateLTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_LT_MONTH,
			String.valueOf(displayTerms.getModifiedDateLTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_LT_YEAR,
			String.valueOf(displayTerms.getModifiedDateLTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_USER_ID,
			String.valueOf(displayTerms.getModifiedUserId()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_USER_NAME,
			displayTerms.getModifiedUserName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.HOST_NAME, displayTerms.getHostName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.IP_ADDRESS, displayTerms.getIpAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.KEY, displayTerms.getKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.LICENSE_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getLicenseEntryIds()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.LICENSE_KEY_SET_NAME,
			displayTerms.getLicenseKeySetName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MAC_ADDRESS, displayTerms.getMacAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.OWNER, displayTerms.getOwner());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getProductEntryIds()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_ENTRY_NAME,
			displayTerms.getProductEntryName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_VERSIONS,
			ArrayUtil.toStringArray(displayTerms.getProductVersions()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.SERVER_ID, displayTerms.getServerId());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_GT_DAY,
			String.valueOf(displayTerms.getStartDateGTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_GT_MONTH,
			String.valueOf(displayTerms.getStartDateGTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_GT_YEAR,
			String.valueOf(displayTerms.getStartDateGTYear()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_LT_DAY,
			String.valueOf(displayTerms.getStartDateLTDay()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_LT_MONTH,
			String.valueOf(displayTerms.getStartDateLTMonth()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_LT_YEAR,
			String.valueOf(displayTerms.getStartDateLTYear()));

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-col",
					orderByCol);
				preferences.setValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-col",
					"start-date");
				orderByType = preferences.getValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-type",
					"desc");
			}

			OrderByComparator orderByComparator =
				LicenseUtil.getLicenseKeyOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LicenseKeySearch.class);

}