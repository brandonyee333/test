/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

/**
 * @author Marcellus Tavares
 */
public class RequiredWorkflowDefinitionException extends WorkflowException {

	public RequiredWorkflowDefinitionException() {
	}

	public RequiredWorkflowDefinitionException(String msg) {
		super(msg);
	}

	public RequiredWorkflowDefinitionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredWorkflowDefinitionException(Throwable cause) {
		super(cause);
	}

}