/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchZendeskSectionException extends NoSuchModelException {

	public NoSuchZendeskSectionException() {
	}

	public NoSuchZendeskSectionException(String msg) {
		super(msg);
	}

	public NoSuchZendeskSectionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchZendeskSectionException(Throwable cause) {
		super(cause);
	}

}