/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DocumentationImportException extends PortalException {

	public DocumentationImportException() {
	}

	public DocumentationImportException(String msg) {
		super(msg);
	}

	public DocumentationImportException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DocumentationImportException(Throwable cause) {
		super(cause);
	}

}