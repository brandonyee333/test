/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
public class PartnerEntrySearchTerms extends PartnerEntryDisplayTerms {

	public PartnerEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public String getActiveFilters() {
		StringBundler sb = new StringBundler(6);

		if (hasSearchableManagingSupportValues()) {
			sb.append("first-line-support");
			sb.append(StringPool.COMMA);
		}

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

		if (hasChildPartnerEntries()) {
			if (isChildPartnerEntries()) {
				params.put("childPartnerEntry", Boolean.TRUE);
			}
			else {
				params.put("childPartnerEntry", Boolean.FALSE);
			}
		}

		if (hasManagingSupport()) {
			if (isManagingSupport()) {
				params.put("managingSupport", Boolean.TRUE);
			}
			else {
				params.put("managingSupport", Boolean.FALSE);
			}
		}

		long[] supportRegionIds = getSupportRegionIds();

		if (supportRegionIds.length > 0) {
			params.put("supportRegionIds", supportRegionIds);
		}

		return params;
	}

	public boolean hasSearchableManagingSupportValues() {
		if (managingSupport != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableStatusValues() {
		if (statuses.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableSupportRegionValues() {
		if (supportRegionIds.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasSearchableValues() {
		if (hasSearchableManagingSupportValues() ||
			hasSearchableStatusValues() || hasSearchableSupportRegionValues() ||
			Validator.isNotNull(keywords)) {

			return true;
		}
		else {
			return false;
		}
	}

}