/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchMeetupsEntryException extends NoSuchModelException {

	public NoSuchMeetupsEntryException() {
	}

	public NoSuchMeetupsEntryException(String msg) {
		super(msg);
	}

	public NoSuchMeetupsEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMeetupsEntryException(Throwable cause) {
		super(cause);
	}

}