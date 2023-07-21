/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountEnvironmentEnvSearchException extends PortalException {

	public AccountEnvironmentEnvSearchException() {
	}

	public AccountEnvironmentEnvSearchException(String msg) {
		super(msg);
	}

	public AccountEnvironmentEnvSearchException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AccountEnvironmentEnvSearchException(Throwable cause) {
		super(cause);
	}

}