/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Igor Beslic
 */
public class InvalidLCSClusterEntryException extends PortalException {

	public InvalidLCSClusterEntryException() {
	}

	public InvalidLCSClusterEntryException(String msg) {
		super(msg);
	}

	public InvalidLCSClusterEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidLCSClusterEntryException(Throwable cause) {
		super(cause);
	}

}