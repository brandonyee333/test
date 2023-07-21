/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.web.internal.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class VersionRangeException extends PortalException {

	public VersionRangeException() {
	}

	public VersionRangeException(String msg) {
		super(msg);
	}

	public VersionRangeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public VersionRangeException(Throwable cause) {
		super(cause);
	}

}