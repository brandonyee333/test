/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class RequiredAccountEntryException extends PortalException {

	public RequiredAccountEntryException() {
	}

	public RequiredAccountEntryException(String msg) {
		super(msg);
	}

	public RequiredAccountEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredAccountEntryException(Throwable cause) {
		super(cause);
	}

}