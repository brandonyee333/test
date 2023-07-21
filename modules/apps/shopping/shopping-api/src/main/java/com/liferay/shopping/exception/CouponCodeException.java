/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponCodeException extends PortalException {

	public CouponCodeException() {
	}

	public CouponCodeException(String msg) {
		super(msg);
	}

	public CouponCodeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CouponCodeException(Throwable cause) {
		super(cause);
	}

}