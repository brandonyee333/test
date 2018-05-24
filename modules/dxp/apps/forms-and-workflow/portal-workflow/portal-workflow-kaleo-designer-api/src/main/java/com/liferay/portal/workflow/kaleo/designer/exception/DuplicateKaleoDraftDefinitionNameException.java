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
 * Thrown when the system identifies a non-unique Kaleo draft definition name.
 *
 * @author Eduardo Lundgren
 */
public class DuplicateKaleoDraftDefinitionNameException
	extends PortalException {

	public DuplicateKaleoDraftDefinitionNameException() {
	}

	public DuplicateKaleoDraftDefinitionNameException(String msg) {
		super(msg);
	}

	public DuplicateKaleoDraftDefinitionNameException(
		String msg, Throwable cause) {

		super(msg, cause);
	}

	public DuplicateKaleoDraftDefinitionNameException(Throwable cause) {
		super(cause);
	}

}