/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.jira.rest.connector.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Jenny Chen
 */
public class JIRAComponentProjectException extends PortalException {

	public JIRAComponentProjectException() {
	}

	public JIRAComponentProjectException(String msg) {
		super(msg);
	}

	public JIRAComponentProjectException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JIRAComponentProjectException(Throwable cause) {
		super(cause);
	}

}