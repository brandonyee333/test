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

package com.liferay.osb.license.search;

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class LicenseKeySearchTerms extends LicenseKeyDisplayTerms {

	public LicenseKeySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
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

		if (_assetReceiptLicense) {
			params.put("assetReceiptLicense", 1L);
		}

		if (_orderEntry) {
			params.put("orderEntry", 1L);
		}

		if (!isAdvancedSearch()) {
			return params;
		}

		if (active != null) {
			params.put("active", active);
		}

		return params;
	}

	public void setAssetReceiptLicense(boolean assetReceiptLicense) {
		_assetReceiptLicense = assetReceiptLicense;
	}

	public void setOrderEntry(boolean orderEntry) {
		_orderEntry = orderEntry;
	}

	private boolean _assetReceiptLicense;
	private boolean _orderEntry;

}