/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Bruno Farache
 */
public class TokenAuthEntryDeviceException extends PortalException {

	public TokenAuthEntryDeviceException() {
	}

	public TokenAuthEntryDeviceException(String msg) {
		super(msg);
	}

	public TokenAuthEntryDeviceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TokenAuthEntryDeviceException(Throwable cause) {
		super(cause);
	}

}