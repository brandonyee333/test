/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.pingback;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author     Marcellus Tavares
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class InvalidSourceURIException extends PortalException {

	public InvalidSourceURIException() {
	}

	public InvalidSourceURIException(String msg) {
		super(msg);
	}

	public InvalidSourceURIException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidSourceURIException(Throwable cause) {
		super(cause);
	}

}