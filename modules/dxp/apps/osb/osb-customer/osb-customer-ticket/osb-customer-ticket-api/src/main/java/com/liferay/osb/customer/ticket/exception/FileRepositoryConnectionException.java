/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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