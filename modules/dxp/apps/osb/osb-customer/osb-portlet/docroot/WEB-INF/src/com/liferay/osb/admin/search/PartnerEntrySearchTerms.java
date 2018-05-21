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