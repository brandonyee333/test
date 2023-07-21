/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Raymond Augé
 */
public class LARFileException extends PortalException {

	public LARFileException() {
	}

	public LARFileException(String msg) {
		super(msg);
	}

	public LARFileException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LARFileException(Throwable cause) {
		super(cause);
	}

}