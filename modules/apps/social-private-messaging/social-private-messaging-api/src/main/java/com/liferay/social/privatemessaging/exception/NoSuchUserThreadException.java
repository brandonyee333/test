/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchUserThreadException extends NoSuchModelException {

	public NoSuchUserThreadException() {
	}

	public NoSuchUserThreadException(String msg) {
		super(msg);
	}

	public NoSuchUserThreadException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchUserThreadException(Throwable cause) {
		super(cause);
	}

}