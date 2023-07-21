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
public class CouponLimitSKUsException extends PortalException {

	public CouponLimitSKUsException() {
	}

	public CouponLimitSKUsException(String msg) {
		super(msg);
	}

	public CouponLimitSKUsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CouponLimitSKUsException(Throwable cause) {
		super(cause);
	}

	public List<String> getSkus() {
		return _skus;
	}

	public void setSkus(List<String> skus) {
		_skus = skus;
	}

	private List<String> _skus;

}