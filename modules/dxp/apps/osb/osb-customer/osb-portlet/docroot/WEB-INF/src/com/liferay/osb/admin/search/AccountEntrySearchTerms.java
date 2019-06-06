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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
		StringBundler sb = new StringBundler(8);

		if (hasSearchableTierValues()) {
			sb.append("tier");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableStatusValues()) {
			sb.append("status");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableIndustryValues()) {
			sb.append("industry");
			sb.append(StringPool.COMMA);
		}

		if (hasSearchableSupportRegionValues()) {
			sb.append("support-region");
			sb.append(StringPool.COMMA);
		}

		return sb.toString();
	}

	public Long getCountryIdObj() {
		if (countryId == 0) {
			return null;
		}

		return Long.valueOf(countryId);
	}

	public Long getCreateUserIdObj() {
		if (createUserId == 0) {
			return null;
		}

		return Long.valueOf(createUserId);
	}

	public Long getModifiedUserIdObj() {
		if (modifiedUserId == 0) {
			return null;
		}

		return Long.valueOf(modifiedUserId);
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

		if ((accountWorkerRole > 0) && (accountWorkerUserId > 0)) {
			params.put(
				"accountWorker",
				new Long[] {accountWorkerUserId, accountWorkerRole});
		}

		long[] productEntryIds = getProductEntryIds();

		if (productEntryIds.length > 0) {
			params.put("productEntryIds", productEntryIds);
		}

		long[] supportRegionIds = getSupportRegionIds();

		if (supportRegionIds.length > 0) {
			params.put("supportRegionIds", supportRegionIds);
		}

		long[] supportResponseIds = getSupportResponseIds();

		if (supportResponseIds.length > 0) {
			params.put("supportResponseIds", supportResponseIds);
		}

		if (support == 3) {
			params.put("noSupport", StringPool.BLANK);

			return params;
		}
		else if (support == 1) {
			params.put("activeSupport", true);
		}
		else if (support == 2) {
			params.put("activeSupport", false);
		}

		if (hasExpiredSupport()) {
			params.put(
				"expiredSupport", new boolean[] {isExpiredSupport(), false});
		}

		if (hasTicketSupport()) {
			params.put("ticketSupport", isTicketSupport());
		}

		int[] types = getTypes();

		if (types.length > 0) {
			params.put("type", types);
		}

		return params;
	}

	public Long getRegionIdObj() {
		if (regionId == 0) {
			return null;
		}

		return Long.valueOf(regionId);
	}

	public boolean hasSearchableIndustryValues() {
		if (industries.length > 0) {
			return true;
		}

		return false;
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

	public boolean hasSearchableTierValues() {
		if (tiers.length > 0) {
			return true;
		}

		return false;
	}

	public boolean hasSearchableValues() {
		if (hasSearchableIndustryValues() || hasSearchableStatusValues() ||
			hasSearchableSupportRegionValues() || hasSearchableTierValues() ||
			Validator.isNotNull(keywords)) {

			return true;
		}

		return false;
	}

}