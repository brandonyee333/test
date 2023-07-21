/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.exception;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponLimitCategoriesException extends PortalException {

	public CouponLimitCategoriesException() {
	}

	public CouponLimitCategoriesException(String msg) {
		super(msg);
	}

	public CouponLimitCategoriesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CouponLimitCategoriesException(Throwable cause) {
		super(cause);
	}

	public List<Long> getCategoryIds() {
		return _categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		_categoryIds = categoryIds;
	}

	private List<Long> _categoryIds;

}