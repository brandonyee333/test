/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchAssetEntrySetException extends NoSuchModelException {

	public NoSuchAssetEntrySetException() {
	}

	public NoSuchAssetEntrySetException(String msg) {
		super(msg);
	}

	public NoSuchAssetEntrySetException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchAssetEntrySetException(Throwable cause) {
		super(cause);
	}

}