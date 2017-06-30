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

package com.liferay.osb;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 * @author Haote Chou
 */
public class SalesforceAddressException extends PortalException {

	public static final int ADDRESS_CITY = 3;

	public static final int ADDRESS_COUNTRY = 2;

	public static final int ADDRESS_STREET = 1;

	public static final int ADDRESS_ZIP = 4;

	public static final int COMPANY_NAME = 8;

	public static final int EMAIL_ADDRESS = 5;

	public static final int LICENSE_TYPE = 6;

	public static final int USER_NAME = 7;

	public SalesforceAddressException() {
	}

	public SalesforceAddressException(int type) {
		_type = type;
	}

	public SalesforceAddressException(String msg) {
		super(msg);
	}

	public SalesforceAddressException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SalesforceAddressException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return _type;
	}

	private int _type;

}