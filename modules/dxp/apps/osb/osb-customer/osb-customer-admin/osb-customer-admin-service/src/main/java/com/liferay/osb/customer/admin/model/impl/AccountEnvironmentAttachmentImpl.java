/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;

/**
 * @author Lin Cui
 */
public class AccountEnvironmentAttachmentImpl
	extends AccountEnvironmentAttachmentBaseImpl {

	public AccountEnvironmentAttachmentImpl() {
	}

	public boolean fileExists() throws PortalException {
		String filePath = getFileDir() + StringPool.SLASH + getFileName();

		return DLStoreUtil.hasFile(
			OSBCustomerConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public int getContentLength() {
		if (getFileSize() > Integer.MAX_VALUE) {
			return 0;
		}

		return (int)getFileSize();
	}

	public String getFileDir() {
		return OSBCustomerConstants.ATTACHMENTS_DIR_ACCOUNT_ENVIRONMENT +
			getAccountEnvironmentId();
	}

}