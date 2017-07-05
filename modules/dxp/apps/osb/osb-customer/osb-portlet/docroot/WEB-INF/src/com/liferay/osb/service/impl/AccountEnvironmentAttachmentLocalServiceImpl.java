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

import com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentAttachmentException;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.service.base.AccountEnvironmentAttachmentLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.document.library.kernel.store.DLStoreUtil;

import java.io.File;
import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * @author Lin Cui
 */
public class AccountEnvironmentAttachmentLocalServiceImpl
	extends AccountEnvironmentAttachmentLocalServiceBaseImpl {

	public AccountEnvironmentAttachment addAccountEnvironmentAttachment(
			long userId, long accountEnvironmentId,
			ObjectValuePair<String, File> fileOVP, int type)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		String fileName = fileOVP.getKey();
		File file = fileOVP.getValue();
		Date now = new Date();

		validate(0, accountEnvironmentId, fileName, file);

		long accountEnvironmentAttachmentId = counterLocalService.increment();

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			accountEnvironmentAttachmentPersistence.create(
				accountEnvironmentAttachmentId);

		accountEnvironmentAttachment.setUserId(user.getUserId());
		accountEnvironmentAttachment.setUserName(user.getFullName());
		accountEnvironmentAttachment.setCreateDate(now);
		accountEnvironmentAttachment.setModifiedDate(now);
		accountEnvironmentAttachment.setAccountEnvironmentId(
			accountEnvironmentId);
		accountEnvironmentAttachment.setFileName(fileName);
		accountEnvironmentAttachment.setFileSize(file.length());
		accountEnvironmentAttachment.setType(type);

		accountEnvironmentAttachmentPersistence.update(
			accountEnvironmentAttachment, false);

		deleteFile(accountEnvironmentAttachment.getFileDir(), fileName);

		addFile(accountEnvironmentAttachment.getFileDir(), fileName, file);

		return accountEnvironmentAttachment;
	}

	public void addAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException, SystemException {

		for (int i = 0; i < files.size(); i++) {
			addAccountEnvironmentAttachment(
				userId, accountEnvironmentId, files.get(i), types.get(i));
		}
	}

	public AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException, SystemException {

		accountEnvironmentAttachmentPersistence.remove(
			accountEnvironmentAttachment);

		deleteFile(
			accountEnvironmentAttachment.getFileDir(),
			accountEnvironmentAttachment.getFileName());

		return accountEnvironmentAttachment;
	}

	public AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
			long accountEnvironmentId, int type)
		throws SystemException {

		return accountEnvironmentAttachmentPersistence.fetchByAEI_T(
			accountEnvironmentId, type);
	}

	public List<AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
			long accountEnvironmentId)
		throws SystemException {

		return
			accountEnvironmentAttachmentPersistence.findByAccountEnvironmentId(
				accountEnvironmentId);
	}

	public File getFile(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException, SystemException {

		String filePath =
			accountEnvironmentAttachment.getFileDir() + StringPool.SLASH +
				accountEnvironmentAttachment.getFileName();

		return DLStoreUtil.getFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public InputStream getFileAsStream(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException, SystemException {

		String filePath =
			accountEnvironmentAttachment.getFileDir() + StringPool.SLASH +
				accountEnvironmentAttachment.getFileName();

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId, long accountEnvironmentId,
			ObjectValuePair<String, File> fileOVP, int type)
		throws PortalException, SystemException {

		String fileName = fileOVP.getKey();
		File file = fileOVP.getValue();

		validate(
			accountEnvironmentAttachmentId, accountEnvironmentId, fileName,
			file);

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			accountEnvironmentAttachmentPersistence.findByPrimaryKey(
				accountEnvironmentAttachmentId);

		String oldFileName = accountEnvironmentAttachment.getFileName();

		accountEnvironmentAttachment.setModifiedDate(new Date());
		accountEnvironmentAttachment.setFileName(fileName);
		accountEnvironmentAttachment.setFileSize(file.length());
		accountEnvironmentAttachment.setType(type);

		accountEnvironmentAttachmentPersistence.update(
			accountEnvironmentAttachment, false);

		deleteFile(accountEnvironmentAttachment.getFileDir(), oldFileName);

		addFile(accountEnvironmentAttachment.getFileDir(), fileName, file);

		return accountEnvironmentAttachment;
	}

	public void updateAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException, SystemException {

		for (int i = 0; i < types.size(); i++) {
			AccountEnvironmentAttachment accountEnvironmentAttachment =
				accountEnvironmentAttachmentPersistence.fetchByAEI_T(
					accountEnvironmentId, types.get(i));

			if (accountEnvironmentAttachment == null) {
				addAccountEnvironmentAttachment(
					userId, accountEnvironmentId, files.get(i), types.get(i));
			}
			else {
				updateAccountEnvironmentAttachment(
					accountEnvironmentAttachment.
						getAccountEnvironmentAttachmentId(),
					accountEnvironmentId, files.get(i), types.get(i));
			}
		}
	}

	protected void addFile(String fileDir, String fileName, File file)
		throws PortalException, SystemException {

		if (!DLStoreUtil.hasDirectory(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, fileDir)) {

			DLStoreUtil.addDirectory(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, fileDir);
		}

		DLStoreUtil.addFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			fileDir + StringPool.SLASH + fileName, file);
	}

	protected void deleteFile(String fileDir, String fileName)
		throws PortalException, SystemException {

		String filePath = fileDir + StringPool.SLASH + fileName;

		if (DLStoreUtil.hasFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath)) {

			DLStoreUtil.deleteFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
		}
	}

	protected void validate(
			long accountEnvironmentAttachmentId, long accountEnvironmentId,
			String fileName, File file)
		throws PortalException, SystemException {

		DLStoreUtil.validate(fileName, false);

		AccountEnvironmentAttachment accountEnvironmentAttachment =
			accountEnvironmentAttachmentPersistence.fetchByAEI_FN(
				accountEnvironmentId, fileName);

		if ((accountEnvironmentAttachment != null) &&
			(accountEnvironmentAttachment.getAccountEnvironmentAttachmentId() !=
				accountEnvironmentAttachmentId)) {

			throw new DuplicateAccountEnvironmentAttachmentException();
		}

		if (file.length() > 102400000) {
			throw new AccountEnvironmentAttachmentSizeException(
				AccountEnvironmentAttachmentSizeException.EXCEEDS_LIMIT);
		}
	}

}