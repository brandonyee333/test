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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class OfferingDefinitionDisplayTerms extends DisplayTerms {

	public static final String PRODUCT_ENTRY_IDS = "productEntryIds";

	public static final String SUPPORT_RESPONSE_IDS = "supportResponseIds";

	public OfferingDefinitionDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);
		supportResponseIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_RESPONSE_IDS);
	}

	public long[] getProductEntryIds() {
		if (ArrayUtil.contains(productEntryIds, 0)) {
			return new long[0];
		}

		return productEntryIds;
	}

	public long[] getSupportResponseIds() {
		if (ArrayUtil.contains(supportResponseIds, 0)) {
			return new long[0];
		}

		return supportResponseIds;
	}

	protected long[] productEntryIds;
	protected long[] supportResponseIds;

}