/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountEnvironmentAttachmentSizeException extends PortalException {

	public static final int EMPTY_FILE = 1;

	public static final int EXCEEDS_LIMIT = 2;

	public AccountEnvironmentAttachmentSizeException() {
	}

	public AccountEnvironmentAttachmentSizeException(int type) {
		_type = type;
	}

	public AccountEnvironmentAttachmentSizeException(
		int type, String fileName) {

		_type = type;
		_fileName = fileName;
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

	public String getFileName() {
		return _fileName;
	}

	public int getType() {
		return _type;
	}

	private String _fileName;
	private int _type;

}