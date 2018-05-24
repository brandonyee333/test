/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.designer.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Thrown when the system identifies invalid XML content in a Kaleo draft
 * definition.
 *
 * @author     Eduardo Lundgren
 * @deprecated As of 1.0.0, replaced by {@link
 *             #com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException}
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