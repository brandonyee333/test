/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Ethan Bustad
 */
public class NoSuchTestraySubtaskException extends NoSuchModelException {

	public NoSuchTestraySubtaskException() {
	}

	public NoSuchTestraySubtaskException(String msg) {
		super(msg);
	}

	public NoSuchTestraySubtaskException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTestraySubtaskException(Throwable cause) {
		super(cause);
	}

}