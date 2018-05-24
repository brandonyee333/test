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