/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchWallEntryException extends NoSuchModelException {

	public NoSuchWallEntryException() {
	}

	public NoSuchWallEntryException(String msg) {
		super(msg);
	}

	public NoSuchWallEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchWallEntryException(Throwable cause) {
		super(cause);
	}

}