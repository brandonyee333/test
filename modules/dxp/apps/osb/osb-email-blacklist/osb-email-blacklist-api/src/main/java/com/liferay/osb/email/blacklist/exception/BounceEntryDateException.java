/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Jamie Sammons
 */
public class BounceEntryDateException extends PortalException {

	public BounceEntryDateException() {
	}

	public BounceEntryDateException(String msg) {
		super(msg);
	}

	public BounceEntryDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BounceEntryDateException(Throwable cause) {
		super(cause);
	}

}