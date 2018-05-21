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

package com.liferay.osb.model.impl;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.model.AccountAttachmentConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brent Krone-Schmidt
 */
public class AccountAttachmentImpl extends AccountAttachmentBaseImpl {

	public AccountAttachmentImpl() {
	}

	public boolean fileExists() throws PortalException {
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