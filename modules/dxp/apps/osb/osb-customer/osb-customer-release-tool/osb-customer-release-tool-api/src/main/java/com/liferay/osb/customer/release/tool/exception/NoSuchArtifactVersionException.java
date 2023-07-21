/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchArtifactVersionException extends NoSuchModelException {

	public NoSuchArtifactVersionException() {
	}

	public NoSuchArtifactVersionException(String msg) {
		super(msg);
	}

	public NoSuchArtifactVersionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchArtifactVersionException(Throwable cause) {
		super(cause);
	}

}