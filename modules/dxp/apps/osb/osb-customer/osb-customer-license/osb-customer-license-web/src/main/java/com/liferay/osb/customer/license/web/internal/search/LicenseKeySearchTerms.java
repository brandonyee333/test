/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.portal.kernel.util.Validator;

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

	public String getKoroneikiAccountKeyObj() {
		if (Validator.isNull(koroneikiAccountKey)) {
			return null;
		}

		return koroneikiAccountKey;
	}

	public String getKoroneikiProductPurchaseKeyObj() {
		if (Validator.isNull(koroneikiProductPurchaseKey)) {
			return null;
		}

		return koroneikiProductPurchaseKey;
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