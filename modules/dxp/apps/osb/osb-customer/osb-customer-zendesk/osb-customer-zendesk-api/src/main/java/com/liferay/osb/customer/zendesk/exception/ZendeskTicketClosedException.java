/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ZendeskTicketClosedException extends PortalException {

	public ZendeskTicketClosedException() {
	}

	public ZendeskTicketClosedException(String msg) {
		super(msg);
	}

	public ZendeskTicketClosedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ZendeskTicketClosedException(Throwable cause) {
		super(cause);
	}

}