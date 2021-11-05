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

package com.liferay.osb.customer.ticket.exception;

import com.liferay.osb.customer.ticket.repository.FileRepository;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class FileRepositoryConnectionException extends PortalException {

	public FileRepositoryConnectionException() {
	}

	public FileRepositoryConnectionException(FileRepository fileRepository) {
		_fileRepository = fileRepository;
	}

	public FileRepositoryConnectionException(String msg) {
		super(msg);
	}

	public FileRepositoryConnectionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FileRepositoryConnectionException(Throwable throwable) {
		super(throwable);
	}

	public FileRepository getFileRepository() {
		return _fileRepository;
	}

	private FileRepository _fileRepository;

}