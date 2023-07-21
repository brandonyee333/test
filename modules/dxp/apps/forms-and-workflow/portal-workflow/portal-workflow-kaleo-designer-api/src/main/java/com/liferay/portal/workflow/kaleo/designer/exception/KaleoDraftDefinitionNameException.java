/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Thrown when the system identifies a missing Kaleo draft definition name.
 *
 * @author Eduardo Lundgren
 */
public class KaleoDraftDefinitionNameException extends PortalException {

	public KaleoDraftDefinitionNameException() {
	}

	public KaleoDraftDefinitionNameException(String msg) {
		super(msg);
	}

	public KaleoDraftDefinitionNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public KaleoDraftDefinitionNameException(Throwable cause) {
		super(cause);
	}

}