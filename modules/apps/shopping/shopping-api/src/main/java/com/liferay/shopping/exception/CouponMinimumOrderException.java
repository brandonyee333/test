/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Huang Jie
 */
public class CouponMinimumOrderException extends PortalException {

	public CouponMinimumOrderException() {
	}

	public CouponMinimumOrderException(String msg) {
		super(msg);
	}

	public CouponMinimumOrderException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CouponMinimumOrderException(Throwable cause) {
		super(cause);
	}

}