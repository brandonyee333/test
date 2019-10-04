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

package com.liferay.osb.exception;

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