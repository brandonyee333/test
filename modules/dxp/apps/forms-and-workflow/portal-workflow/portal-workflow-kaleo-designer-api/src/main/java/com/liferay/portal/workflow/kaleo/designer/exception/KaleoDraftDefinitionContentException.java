/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Thrown when the system identifies invalid XML content in a Kaleo draft
 * definition.
 *
 * @author     Eduardo Lundgren
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException}
 */
@Deprecated
public class KaleoDraftDefinitionContentException extends PortalException {

	public KaleoDraftDefinitionContentException() {
	}

	public KaleoDraftDefinitionContentException(String msg) {
		super(msg);
	}

	public KaleoDraftDefinitionContentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public KaleoDraftDefinitionContentException(Throwable cause) {
		super(cause);
	}

}