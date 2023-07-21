/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * Thrown when the system is unable to find a required Kaleo draft definition.
 *
 * @author Eduardo Lundgren
 */
public class NoSuchKaleoDraftDefinitionException extends NoSuchModelException {

	public NoSuchKaleoDraftDefinitionException() {
	}

	public NoSuchKaleoDraftDefinitionException(String msg) {
		super(msg);
	}

	public NoSuchKaleoDraftDefinitionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchKaleoDraftDefinitionException(Throwable cause) {
		super(cause);
	}

}