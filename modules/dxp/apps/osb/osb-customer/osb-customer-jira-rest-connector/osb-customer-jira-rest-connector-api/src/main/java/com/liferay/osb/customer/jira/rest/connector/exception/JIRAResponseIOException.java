/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.jira.rest.connector.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Noah Sherrill
 */
public class JIRAResponseIOException extends PortalException {

	public JIRAResponseIOException() {
	}

	public JIRAResponseIOException(String msg) {
		super(msg);
	}

	public JIRAResponseIOException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JIRAResponseIOException(Throwable cause) {
		super(cause);
	}

}