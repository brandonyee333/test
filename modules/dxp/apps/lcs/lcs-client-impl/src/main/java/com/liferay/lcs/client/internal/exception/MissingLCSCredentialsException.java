/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Ivica Cardic
 */
public class MissingLCSCredentialsException extends SystemException {

	public MissingLCSCredentialsException() {
	}

	public MissingLCSCredentialsException(String msg) {
		super(msg);
	}

	public MissingLCSCredentialsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MissingLCSCredentialsException(Throwable cause) {
		super(cause);
	}

}