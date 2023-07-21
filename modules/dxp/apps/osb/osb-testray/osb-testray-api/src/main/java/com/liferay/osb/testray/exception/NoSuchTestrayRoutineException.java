/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Ethan Bustad
 */
public class NoSuchTestrayRoutineException extends NoSuchModelException {

	public NoSuchTestrayRoutineException() {
	}

	public NoSuchTestrayRoutineException(String msg) {
		super(msg);
	}

	public NoSuchTestrayRoutineException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTestrayRoutineException(Throwable cause) {
		super(cause);
	}

}