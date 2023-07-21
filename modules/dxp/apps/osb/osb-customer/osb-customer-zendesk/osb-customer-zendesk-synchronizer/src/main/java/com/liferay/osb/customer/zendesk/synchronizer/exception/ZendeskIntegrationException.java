/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer.exception;

import com.liferay.portal.kernel.exception.ModelListenerException;

/**
 * @author Jenny Chen
 */
public class ZendeskIntegrationException extends ModelListenerException {

	public ZendeskIntegrationException() {
	}

	public ZendeskIntegrationException(String msg) {
		super(msg);
	}

	public ZendeskIntegrationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ZendeskIntegrationException(Throwable cause) {
		super(cause);
	}

}