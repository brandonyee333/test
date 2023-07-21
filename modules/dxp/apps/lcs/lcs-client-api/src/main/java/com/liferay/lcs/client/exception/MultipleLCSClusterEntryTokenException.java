/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Matija Petanjek
 */
public class MultipleLCSClusterEntryTokenException extends PortalException {

	public MultipleLCSClusterEntryTokenException() {
	}

	public MultipleLCSClusterEntryTokenException(String msg) {
		super(msg);
	}

	public MultipleLCSClusterEntryTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MultipleLCSClusterEntryTokenException(Throwable cause) {
		super(cause);
	}

}