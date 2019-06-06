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