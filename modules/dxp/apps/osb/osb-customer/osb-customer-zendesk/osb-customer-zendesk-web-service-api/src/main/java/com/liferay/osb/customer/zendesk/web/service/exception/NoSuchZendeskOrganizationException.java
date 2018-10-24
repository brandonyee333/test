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

package com.liferay.osb.customer.zendesk.web.service.exception;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Jenny Chen
 */
@ProviderType
public class NoSuchZendeskOrganizationException extends PortalException {

	public NoSuchZendeskOrganizationException() {
	}

	public NoSuchZendeskOrganizationException(String msg) {
		super(msg);
	}

	public NoSuchZendeskOrganizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchZendeskOrganizationException(Throwable cause) {
		super(cause);
	}

}