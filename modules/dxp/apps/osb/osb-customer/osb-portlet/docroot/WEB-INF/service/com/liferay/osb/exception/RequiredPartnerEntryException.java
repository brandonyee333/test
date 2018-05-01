/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.exception;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class RequiredPartnerEntryException extends PortalException {

	public static final int REFERENCED_ACCOUNT_ENTRY = 1;

	public static final int REFERENCED_PARTNER_ENTRY = 2;

	public static final int REFERENCED_PARTNER_WORKER = 3;

	public RequiredPartnerEntryException() {
	}

	public RequiredPartnerEntryException(int type) {
		_type = type;
	}

	public RequiredPartnerEntryException(String msg) {
		super(msg);
	}

	public RequiredPartnerEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredPartnerEntryException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return _type;
	}

	private int _type;

}