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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.document.library.kernel.store.DLStoreUtil;

import java.io.File;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Amos Fong
 */
public class TicketAttachmentImpl extends TicketAttachmentBaseImpl {

	public TicketAttachmentImpl() {
	}

	public boolean fileExists() throws PortalException, SystemException {
		if ((_file != null) && _file.exists()) {
			return true;
		}

		return DLStoreUtil.hasFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, getFilePath());
	}

	public Set<String> getAvailableFileRepositoryIdsSet() {
		return SetUtil.fromArray(
			StringUtil.split(getAvailableFileRepositoryIds()));
	}

	public int getContentLength() {
		if (getFileSize() > Integer.MAX_VALUE) {
			return 0;
		}
		else {
			return (int)getFileSize();
		}
	}

	public File getFile() {
		return _file;
	}

	public String getFilePath() {
		StringBundler sb = new StringBundler(6);

		sb.append(OSBConstants.ATTACHMENTS_DIR_SUPPORT);
		sb.append(getTicketEntryId());
		sb.append(StringPool.SLASH);
		sb.append(getTicketAttachmentId());
		sb.append(StringPool.SLASH);
		sb.append(getFileName());

		return sb.toString();
	}

	public String getKey() {
		StringBundler sb = new StringBundler(3);

		sb.append(getUserId());
		sb.append(StringPool.UNDERLINE);

		Date createDate = DateUtils.truncate(getCreateDate(), Calendar.SECOND);

		sb.append(createDate.getTime());

		return sb.toString();
	}

	public TicketEntry getTicketEntry()
		throws PortalException, SystemException {

		return TicketEntryLocalServiceUtil.getTicketEntry(getTicketEntryId());
	}

	public String getTypeLabel() {
		return TicketAttachmentConstants.getTypeLabel(getType());
	}

	public String getVisibilityLabel() {
		return VisibilityConstants.toLabel(getVisibility());
	}

	public void setAvailableFileRepositoryIdsSet(
		Set<String> availableFileRepositoryIds) {

		setAvailableFileRepositoryIds(
			StringUtil.merge(availableFileRepositoryIds));
	}

	public void setFile(File file) {
		_file = file;
	}

	private File _file;

}