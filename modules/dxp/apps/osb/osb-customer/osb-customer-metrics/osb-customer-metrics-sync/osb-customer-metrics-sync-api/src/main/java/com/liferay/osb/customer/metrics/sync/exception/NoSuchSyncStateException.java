/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchSyncStateException extends NoSuchModelException {

	public NoSuchSyncStateException() {
	}

	public NoSuchSyncStateException(String msg) {
		super(msg);
	}

	public NoSuchSyncStateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchSyncStateException(Throwable cause) {
		super(cause);
	}

}