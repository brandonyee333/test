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
 */
public class AccountEnvironmentAttachmentSizeException extends PortalException {

	public static final int EMPTY_FILE = 1;

	public static final int EXCEEDS_LIMIT = 2;

	public AccountEnvironmentAttachmentSizeException() {
		super();
	}

	public AccountEnvironmentAttachmentSizeException(int type) {
		_type = type;
	}

	public AccountEnvironmentAttachmentSizeException(String msg) {
		super(msg);
	}

	public AccountEnvironmentAttachmentSizeException(
		String msg, Throwable cause) {

		super(msg, cause);
	}

	public AccountEnvironmentAttachmentSizeException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return _type;
	}

	private int _type;

}