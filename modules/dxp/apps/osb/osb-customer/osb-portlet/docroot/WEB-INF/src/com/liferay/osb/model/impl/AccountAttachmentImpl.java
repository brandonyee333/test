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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountAttachmentConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.document.library.kernel.store.DLStoreUtil;

/**
 * @author Brent Krone-Schmidt
 */
public class AccountAttachmentImpl extends AccountAttachmentBaseImpl {

	public AccountAttachmentImpl() {
	}

	public boolean fileExists() throws PortalException, SystemException {
		String filePath = getFileDir() + StringPool.SLASH + getFileName();

		return DLStoreUtil.hasFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public int getContentLength() {
		if (getFileSize() > Integer.MAX_VALUE) {
			return 0;
		}
		else {
			return (int)getFileSize();
		}
	}

	public String getFileDir() {
		return OSBConstants.ATTACHMENTS_DIR_ACCOUNT_ENTRY + getAccountEntryId();
	}

	public String getTypeLabel() {
		return AccountAttachmentConstants.getTypeLabel(getType());
	}

}