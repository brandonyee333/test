/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.web.internal.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntrySearchTerms extends AccountEntryDisplayTerms {

	public AccountEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public String getActiveFilters() {
		StringBundler sb = new StringBundler(2);

		if (hasSearchableStatusValues()) {
			sb.append("status");
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (!isAdvancedSearch()) {
			return params;
		}

		long[] accountEnvironmentEnvASIds = getAccountEnvironmentEnvASIds();

		if (accountEnvironmentEnvASIds.length > 0) {
			params.put(
				"accountEnvironmentEnvASIds", accountEnvironmentEnvASIds);
		}

		long[] accountEnvironmentEnvDBIds = getAccountEnvironmentEnvDBIds();

		if (accountEnvironmentEnvDBIds.length > 0) {
			params.put(
				"accountEnvironmentEnvDBIds", accountEnvironmentEnvDBIds);
		}

		long[] accountEnvironmentEnvJVMIds = getAccountEnvironmentEnvJVMIds();

		if (accountEnvironmentEnvJVMIds.length > 0) {
			params.put(
				"accountEnvironmentEnvJVMIds", accountEnvironmentEnvJVMIds);
		}

		long[] accountEnvironmentEnvLFRIds = getAccountEnvironmentEnvLFRIds();

		if (accountEnvironmentEnvLFRIds.length > 0) {
			params.put(
				"accountEnvironmentEnvLFRIds", accountEnvironmentEnvLFRIds);
		}

		long[] accountEnvironmentEnvOSIds = getAccountEnvironmentEnvOSIds();

		if (accountEnvironmentEnvOSIds.length > 0) {
			params.put(
				"accountEnvironmentEnvOSIds", accountEnvironmentEnvOSIds);
		}

		long[] productEntryIds = getProductEntryIds();

		if (productEntryIds.length > 0) {
			params.put("productEntryIds", productEntryIds);
		}

		return params;
	}

	public boolean hasSearchableStatusValues() {
		if (statuses.length > 0) {
			return true;
		}

		return false;
	}

	public boolean hasSearchableValues() {
		if (hasSearchableStatusValues() || Validator.isNotNull(keywords)) {
			return true;
		}

		return false;
	}

}