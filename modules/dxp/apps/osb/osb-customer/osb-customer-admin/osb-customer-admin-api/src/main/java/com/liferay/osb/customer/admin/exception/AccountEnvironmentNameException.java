/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountEnvironmentNameException extends PortalException {

	public AccountEnvironmentNameException() {
	}

	public AccountEnvironmentNameException(String msg) {
		super(msg);
	}

	public AccountEnvironmentNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AccountEnvironmentNameException(Throwable cause) {
		super(cause);
	}

}