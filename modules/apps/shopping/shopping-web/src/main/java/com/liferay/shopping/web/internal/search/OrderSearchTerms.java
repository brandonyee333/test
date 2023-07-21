/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.web.internal.search;

import com.liferay.shopping.model.ShoppingOrderConstants;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderSearchTerms extends OrderDisplayTerms {

	public OrderSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	@Override
	public String getStatus() {
		if (status == null) {
			return null;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[0])) {
			return ShoppingOrderConstants.STATUS_CHECKOUT;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[1])) {
			return ShoppingOrderConstants.STATUS_COMPLETED;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[2])) {
			return ShoppingOrderConstants.STATUS_DENIED;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[3])) {
			return ShoppingOrderConstants.STATUS_PENDING;
		}
		else if (status.equals(ShoppingOrderConstants.STATUSES[4])) {
			return ShoppingOrderConstants.STATUS_REFUNDED;
		}

		return null;
	}

}