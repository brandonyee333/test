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