/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class AccountCustomerRemovalException extends PortalException {

	public AccountCustomerRemovalException() {
	}

	public AccountCustomerRemovalException(String msg) {
		super(msg);
	}

	public AccountCustomerRemovalException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AccountCustomerRemovalException(Throwable cause) {
		super(cause);
	}

}