/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryStartDateException extends PortalException {

	public MeetupsEntryStartDateException() {
	}

	public MeetupsEntryStartDateException(String msg) {
		super(msg);
	}

	public MeetupsEntryStartDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MeetupsEntryStartDateException(Throwable cause) {
		super(cause);
	}

}