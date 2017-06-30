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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AccountAttachmentSizeException;
import com.liferay.osb.DuplicateAccountAttachmentException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.service.base.AccountAttachmentLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class AccountAttachmentLocalServiceImpl
	extends AccountAttachmentLocalServiceBaseImpl {

	public AccountAttachment addAccountAttachment(
			long userId, long accountEntryId, long accountProjectId,
			ObjectValuePair<String, File> fileOVP, int type)
		throws PortalException, SystemException {

		// Account attachment

		User user = userPersistence.findByPrimaryKey(userId);

		String fileName = fileOVP.getKey();
		File file = fileOVP.getValue();

		validate(accountEntryId, accountProjectId, fileName, file, type);

		long accountAttachmentId = counterLocalService.increment();

		AccountAttachment accountAttachment =
			accountAttachmentPersistence.create(accountAttachmentId);

		accountAttachment.setUserId(user.getUserId());
		accountAttachment.setUserName(user.getFullName());
		accountAttachment.setCreateDate(new Date());
		accountAttachment.setAccountEntryId(accountEntryId);
		accountAttachment.setAccountProjectId(accountProjectId);
		accountAttachment.setFileName(fileName);
		accountAttachment.setFileSize(file.length());
		accountAttachment.setType(type);

		accountAttachmentPersistence.update(accountAttachment, false);

		// File

		try {
			DLStoreUtil.addDirectory(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				accountAttachment.getFileDir());
		}
		catch (DuplicateDirectoryException dde) {
		}

		try {
			DLStoreUtil.addFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				accountAttachment.getFileDir() + "/" + fileName, file);
		}
		catch (DuplicateFileException dfe) {
		}

		return accountAttachment;
	}

	public List<AccountAttachment> addAccountAttachments(
			long userId, long accountEntryId, long accountProjectId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException, SystemException {

		List<AccountAttachment> accountAttachments =
			new ArrayList<AccountAttachment>();

		for (int i = 0; i < files.size(); i++) {
			ObjectValuePair<String, File> ovp = files.get(i);

			String fileName = ovp.getKey();

			if (Validator.isNull(fileName)) {
				continue;
			}

			AccountAttachment accountAttachment = addAccountAttachment(
				userId, accountEntryId, accountProjectId, ovp, types.get(i));

			accountAttachments.add(accountAttachment);
		}

		return accountAttachments;
	}

	@Override
	public AccountAttachment deleteAccountAttachment(
			AccountAttachment accountAttachment)
		throws PortalException, SystemException {

		// Account attachment

		accountAttachmentPersistence.remove(accountAttachment);

		// File

		try {
			String filePath =
				accountAttachment.getFileDir() + StringPool.SLASH +
					accountAttachment.getFileName();

			DLStoreUtil.deleteFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
		}
		catch (NoSuchFileException nsfe) {
		}

		return accountAttachment;
	}

	@Override
	public AccountAttachment deleteAccountAttachment(long accountAttachmentId)
		throws PortalException, SystemException {

		AccountAttachment accountAttachment =
			accountAttachmentPersistence.fetchByPrimaryKey(accountAttachmentId);

		return deleteAccountAttachment(accountAttachment);
	}

	public void deleteAccountAttachments(
			long accountEntryId, long accountProjectId)
		throws PortalException, SystemException {

		List<AccountAttachment> accountAttachments = getAccountAttachments(
			accountEntryId, accountProjectId);

		for (AccountAttachment accountAttachment : accountAttachments) {
			deleteAccountAttachment(accountAttachment);
		}
	}

	public void deleteAccountAttachments(
			long accountEntryId, long accountProjectId, int type)
		throws PortalException, SystemException {

		List<AccountAttachment> accountAttachments = getAccountAttachments(
			accountEntryId, accountProjectId, type);

		for (AccountAttachment accountAttachment : accountAttachments) {
			deleteAccountAttachment(accountAttachment);
		}
	}

	public List<AccountAttachment> getAccountAttachments(long accountEntryId)
		throws SystemException {

		return accountAttachmentPersistence.findByAccountEntryId(
			accountEntryId);
	}

	public List<AccountAttachment> getAccountAttachments(
			long accountEntryId, long accountProjectId)
		throws SystemException {

		return accountAttachmentPersistence.findByAEI_API(
			accountEntryId, accountProjectId);
	}

	public List<AccountAttachment> getAccountAttachments(
			long accountEntryId, long accountProjectId, int type)
		throws SystemException {

		return accountAttachmentPersistence.findByAEI_API_T(
			accountEntryId, accountProjectId, type);
	}

	public InputStream getFileAsStream(AccountAttachment accountAttachment)
		throws PortalException, SystemException {

		String filePath =
			accountAttachment.getFileDir() + StringPool.SLASH +
				accountAttachment.getFileName();

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	protected void validate(
			long accountEntryId, long accountProjectId, String fileName,
			File file, int type)
		throws PortalException, SystemException {

		accountEntryPersistence.findByPrimaryKey(accountEntryId);

		if (accountAttachmentPersistence.countByAEI_API_FN_T(
				accountEntryId, accountProjectId, fileName, type) > 0) {

			throw new DuplicateAccountAttachmentException();
		}

		if (file.length() > 102400000) {
			throw new AccountAttachmentSizeException();
		}
	}

}