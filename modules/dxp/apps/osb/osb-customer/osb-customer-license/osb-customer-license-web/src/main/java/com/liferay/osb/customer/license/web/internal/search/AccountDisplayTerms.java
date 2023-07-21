/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplayTerms extends DisplayTerms {

	public static final String CODE = "code";

	public static final String DOSSIERA_ACCOUNT_KEY = "dossieraAccountKey";

	public static final String KORONEIKI_ACCOUNT_KEY = "koroneikiAccountKey";

	public static final String NAME = "name";

	public AccountDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		code = ParamUtil.getString(portletRequest, CODE);
		dossieraAccountKey = ParamUtil.getString(
			portletRequest, DOSSIERA_ACCOUNT_KEY);
		koroneikiAccountKey = ParamUtil.getString(
			portletRequest, KORONEIKI_ACCOUNT_KEY);
		name = ParamUtil.getString(portletRequest, NAME);
	}

	public String getCode() {
		return code;
	}

	public String getDossieraAccountKey() {
		return dossieraAccountKey;
	}

	public String getKoroneikiAccountKey() {
		return koroneikiAccountKey;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isAdvancedSearch() {
		if (super.isAdvancedSearch() || !isSearch()) {
			return true;
		}

		return false;
	}

	protected String code;
	protected String dossieraAccountKey;
	protected String koroneikiAccountKey;
	protected String name;

}