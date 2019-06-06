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

		return Long.valueOf(accountEntryId);
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

		if (productEntryId > 0) {
			params.put("productEntryId", productEntryId);
		}

		return params;
	}

}