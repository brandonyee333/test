/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Jamie Sammons
 */
public class NoSuchBlacklistEntryException extends NoSuchModelException {

	public NoSuchBlacklistEntryException() {
	}

	public NoSuchBlacklistEntryException(String msg) {
		super(msg);
	}

	public NoSuchBlacklistEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchBlacklistEntryException(Throwable cause) {
		super(cause);
	}

}