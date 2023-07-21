/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class NoSuchZendeskUserException extends PortalException {

	public NoSuchZendeskUserException() {
	}

	public NoSuchZendeskUserException(String msg) {
		super(msg);
	}

	public NoSuchZendeskUserException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchZendeskUserException(Throwable cause) {
		super(cause);
	}

}