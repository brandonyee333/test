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

package com.liferay.osb.service.impl;

import com.liferay.document.library.kernel.exception.DuplicateDirectoryException;
import com.liferay.document.library.kernel.exception.DuplicateFileException;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.exception.AccountAttachmentSizeException;
import com.liferay.osb.exception.DuplicateAccountAttachmentException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.service.base.AccountAttachmentLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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
		throws PortalException {

		// Account attachment

		User user = userLocalService.getUser(userId);

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

		accountAttachment = accountAttachmentPersistence.update(
			accountAttachment);

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
		throws PortalException {

		List<AccountAttachment> accountAttachments = new ArrayList<>();

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
		throws PortalException {

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
		throws PortalException {

		AccountAttachment accountAttachment =
			accountAttachmentPersistence.fetchByPrimaryKey(accountAttachmentId);

		return deleteAccountAttachment(accountAttachment);
	}

	public void deleteAccountAttachments(
			long accountEntryId, long accountProjectId)
		throws PortalException {

		List<AccountAttachment> accountAttachments = getAccountAttachments(
			accountEntryId, accountProjectId);

		for (AccountAttachment accountAttachment : accountAttachments) {
			deleteAccountAttachment(accountAttachment);
		}
	}

	public List<AccountAttachment> getAccountAttachments(long accountEntryId) {
		return accountAttachmentPersistence.findByAccountEntryId(
			accountEntryId);
	}

	public List<AccountAttachment> getAccountAttachments(
		long accountEntryId, long accountProjectId) {

		return accountAttachmentPersistence.findByAEI_API(
			accountEntryId, accountProjectId);
	}

	public List<AccountAttachment> getAccountAttachments(
		long accountEntryId, long accountProjectId, int type) {

		return accountAttachmentPersistence.findByAEI_API_T(
			accountEntryId, accountProjectId, type);
	}

	public InputStream getFileAsStream(AccountAttachment accountAttachment)
		throws PortalException {

		String filePath =
			accountAttachment.getFileDir() + StringPool.SLASH +
				accountAttachment.getFileName();

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	protected void validate(
			long accountEntryId, long accountProjectId, String fileName,
			File file, int type)
		throws PortalException {

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