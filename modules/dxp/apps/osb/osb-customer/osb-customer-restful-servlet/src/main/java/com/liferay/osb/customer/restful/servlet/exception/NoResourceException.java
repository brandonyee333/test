/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.restful.servlet.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public class NoResourceException extends PortalException {

	public NoResourceException() {
	}

	public NoResourceException(String msg) {
		super(msg);
	}

	public NoResourceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoResourceException(Throwable cause) {
		super(cause);
	}

}