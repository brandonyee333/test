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
		StringBundler sb = new StringBundler(4);

		if (hasSearchableStatusValues()) {
			sb.append("status");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableSupportRegionValues()) {
			sb.append("support-region");
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

		long[] supportRegionIds = getSupportRegionIds();

		if (supportRegionIds.length > 0) {
			params.put("supportRegionIds", supportRegionIds);
		}

		return params;
	}

	public boolean hasSearchableStatusValues() {
		if (statuses.length > 0) {
			return true;
		}

		return false;
	}

	public boolean hasSearchableSupportRegionValues() {
		if (supportRegionIds.length > 0) {
			return true;
		}

		return false;
	}

	public boolean hasSearchableValues() {
		if (hasSearchableStatusValues() || hasSearchableSupportRegionValues() ||
			Validator.isNotNull(keywords)) {

			return true;
		}

		return false;
	}

}