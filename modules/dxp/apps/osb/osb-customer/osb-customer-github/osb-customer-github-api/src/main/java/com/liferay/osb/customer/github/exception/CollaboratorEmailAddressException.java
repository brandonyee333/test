/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class CollaboratorEmailAddressException extends PortalException {

	public CollaboratorEmailAddressException() {
	}

	public CollaboratorEmailAddressException(String msg) {
		super(msg);
	}

	public CollaboratorEmailAddressException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CollaboratorEmailAddressException(Throwable cause) {
		super(cause);
	}

}