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

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class OrderEntrySearchTerms extends OrderEntryDisplayTerms {

	public OrderEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public Long getAccountEntryIdObj() {
		if (accountEntryId == 0) {
			return null;
		}
		else {
			return new Long(accountEntryId);
		}
	}

	public Long getCreateUserIdObj() {
		if (createUserId == 0) {
			return null;
		}
		else {
			return new Long(createUserId);
		}
	}

	public Long getModifiedUserIdObj() {
		if (modifiedUserId == 0) {
			return null;
		}
		else {
			return new Long(modifiedUserId);
		}
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (!isAdvancedSearch()) {
			return params;
		}

		if (productEntryId > 0) {
			params.put("productEntryId", productEntryId);
		}

		return params;
	}

}