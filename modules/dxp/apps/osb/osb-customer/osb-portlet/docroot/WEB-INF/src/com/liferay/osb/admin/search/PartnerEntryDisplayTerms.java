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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class PartnerEntryDisplayTerms extends DisplayTerms {

	public static final String CHILD_PARTNER_ENTRIES = "childPartnerEntries";

	public static final String CODE = "code";

	public static final String MANAGING_SUPPORT = "managingSupport";

	public static final String STATUSES = "statuses";

	public static final String SUPPORT_REGION_IDS = "supportRegionIds";

	public PartnerEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		String childPartnerEntriesString = ParamUtil.getString(
			portletRequest, CHILD_PARTNER_ENTRIES);

		if (Validator.isNotNull(childPartnerEntriesString)) {
			childPartnerEntries = GetterUtil.getBoolean(
				childPartnerEntriesString);
		}

		code = ParamUtil.getString(portletRequest, CODE);

		String managingSupportString = ParamUtil.getString(
			portletRequest, MANAGING_SUPPORT);

		if (Validator.isNotNull(managingSupportString)) {
			managingSupport = GetterUtil.getBoolean(managingSupportString);
		}

		statuses = ParamUtil.getIntegerValues(portletRequest, STATUSES);
		supportRegionIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_REGION_IDS);
	}

	public Boolean getChildPartnerEntries() {
		return childPartnerEntries;
	}

	public String getCode() {
		return code;
	}

	public Boolean getManagingSupport() {
		return managingSupport;
	}

	public int[] getStatuses() {
		return statuses;
	}

	public long[] getSupportRegionIds() {
		if (ArrayUtil.contains(supportRegionIds, 0)) {
			return new long[0];
		}

		return supportRegionIds;
	}

	public Boolean hasChildPartnerEntries() {
		if (childPartnerEntries == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasManagingSupport() {
		if (managingSupport == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public Boolean isChildPartnerEntries() {
		if (childPartnerEntries == null) {
			return false;
		}
		else {
			return childPartnerEntries.booleanValue();
		}
	}

	public boolean isManagingSupport() {
		if (managingSupport == null) {
			return false;
		}

		return managingSupport.booleanValue();
	}

	protected Boolean childPartnerEntries;
	protected String code;
	protected Boolean managingSupport;
	protected int[] statuses;
	protected long[] supportRegionIds;

}