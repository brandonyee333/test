/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Ethan Bustad
 */
public class NoSuchTestrayFactorCategoryException extends NoSuchModelException {

	public NoSuchTestrayFactorCategoryException() {
	}

	public NoSuchTestrayFactorCategoryException(String msg) {
		super(msg);
	}

	public NoSuchTestrayFactorCategoryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTestrayFactorCategoryException(Throwable cause) {
		super(cause);
	}

}