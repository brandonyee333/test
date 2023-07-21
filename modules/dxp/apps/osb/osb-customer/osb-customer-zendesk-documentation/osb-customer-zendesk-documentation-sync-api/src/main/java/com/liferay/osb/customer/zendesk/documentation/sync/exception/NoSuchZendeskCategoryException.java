/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchZendeskCategoryException extends NoSuchModelException {

	public NoSuchZendeskCategoryException() {
	}

	public NoSuchZendeskCategoryException(String msg) {
		super(msg);
	}

	public NoSuchZendeskCategoryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchZendeskCategoryException(Throwable cause) {
		super(cause);
	}

}