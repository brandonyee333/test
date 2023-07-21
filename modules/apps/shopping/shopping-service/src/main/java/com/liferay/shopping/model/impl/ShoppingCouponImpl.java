/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.util.CalendarUtil;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCouponImpl extends ShoppingCouponBaseImpl {

	@Override
	public boolean hasValidDateRange() {
		if (hasValidStartDate() && hasValidEndDate()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasValidEndDate() {
		if (getEndDate() != null) {
			Date now = new Date();

			if (now.after(getEndDate())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean hasValidStartDate() {
		if (CalendarUtil.beforeByDay(new Date(), getStartDate())) {
			return false;
		}

		return true;
	}

}