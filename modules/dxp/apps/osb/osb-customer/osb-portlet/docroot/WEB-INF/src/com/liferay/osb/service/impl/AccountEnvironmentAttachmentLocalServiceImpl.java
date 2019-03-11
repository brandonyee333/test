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

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentAttachmentException;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.service.base.AccountEnvironmentAttachmentLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;

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
		throws PortalException {

		User user = userLocalService.getUser(userId);
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
			accountEnvironmentAttachment);

		deleteFile(accountEnvironmentAttachment.getFileDir(), fileName);

		addFile(accountEnvironmentAttachment.getFileDir(), fileName, file);

		return accountEnvironmentAttachment;
	}

	public void addAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException {

		for (int i = 0; i < files.size(); i++) {
			addAccountEnvironmentAttachment(
				userId, accountEnvironmentId, files.get(i), types.get(i));
		}
	}

	public AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

		accountEnvironmentAttachmentPersistence.remove(
			accountEnvironmentAttachment);

		deleteFile(
			accountEnvironmentAttachment.getFileDir(),
			accountEnvironmentAttachment.getFileName());

		return accountEnvironmentAttachment;
	}

	public AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentId, int type) {

		return accountEnvironmentAttachmentPersistence.fetchByAEI_T(
			accountEnvironmentId, type);
	}

	public List<AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		long accountEnvironmentId) {

		return accountEnvironmentAttachmentPersistence.
			findByAccountEnvironmentId(accountEnvironmentId);
	}

	public File getFile(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

		String filePath =
			accountEnvironmentAttachment.getFileDir() + StringPool.SLASH +
				accountEnvironmentAttachment.getFileName();

		return DLStoreUtil.getFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public InputStream getFileAsStream(
			AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException {

		String filePath =
			accountEnvironmentAttachment.getFileDir() + StringPool.SLASH +
				accountEnvironmentAttachment.getFileName();

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, filePath);
	}

	public AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
			long accountEnvironmentAttachmentId, long accountEnvironmentId,
			ObjectValuePair<String, File> fileOVP, int type)
		throws PortalException {

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
			accountEnvironmentAttachment);

		deleteFile(accountEnvironmentAttachment.getFileDir(), oldFileName);

		addFile(accountEnvironmentAttachment.getFileDir(), fileName, file);

		return accountEnvironmentAttachment;
	}

	public void updateAccountEnvironmentAttachments(
			long userId, long accountEnvironmentId,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException {

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
		throws PortalException {

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
		throws PortalException {

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
		throws PortalException {

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