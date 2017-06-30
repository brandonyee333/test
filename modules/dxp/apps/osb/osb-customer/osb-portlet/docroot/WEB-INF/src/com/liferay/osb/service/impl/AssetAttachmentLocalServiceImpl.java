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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.AssetAttachmentFileNameException;
import com.liferay.osb.AssetAttachmentSizeException;
import com.liferay.osb.DuplicateAssetAttachmentFileNameException;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.base.AssetAttachmentLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class AssetAttachmentLocalServiceImpl
	extends AssetAttachmentLocalServiceBaseImpl {

	public AssetAttachment addAssetAttachment(
			long userId, long classNameId, long classPK, String fileName,
			int type, int rank, byte[] bytes)
		throws PortalException, SystemException {

		return addAssetAttachment(
			userId, classNameId, classPK, fileName, type, rank, null, bytes);
	}

	public AssetAttachment addAssetAttachment(
			long userId, long classNameId, long classPK, String fileName,
			int type, int rank, File file)
		throws PortalException, SystemException {

		return addAssetAttachment(
			userId, classNameId, classPK, fileName, type, rank, file, null);
	}

	public AssetAttachment addAssetAttachment(
			long userId, long classNameId, long classPK, String fileName,
			int type, int rank, File file, byte[] bytes)
		throws PortalException, SystemException {

		validate(classNameId, classPK, fileName, type);

		long assetAttachmentId = counterLocalService.increment();

		AssetAttachment assetAttachment = assetAttachmentPersistence.create(
			assetAttachmentId);

		assetAttachment.setUserId(userId);
		assetAttachment.setCreateDate(new Date());
		assetAttachment.setClassNameId(classNameId);
		assetAttachment.setClassPK(classPK);
		assetAttachment.setFileName(fileName);
		assetAttachment.setType(type);
		assetAttachment.setRank(rank);

		assetAttachmentPersistence.update(assetAttachment, false);

		try {
			DLStoreUtil.addDirectory(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				assetAttachment.getDir());
		}
		catch (DuplicateDirectoryException dde) {
		}

		if (file != null) {
			DLStoreUtil.addFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				assetAttachment.getPath(), file);
		}
		else {
			DLStoreUtil.addFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				assetAttachment.getPath(), bytes);
		}

		return assetAttachment;
	}

	public AssetAttachment addAssetAttachment(
			long userId, String className, long classPK, String fileName,
			int type, int rank, byte[] bytes)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return addAssetAttachment(
			userId, classNameId, classPK, fileName, type, rank, bytes);
	}

	public AssetAttachment addAssetAttachment(
			long userId, String className, long classPK, String fileName,
			int type, int rank, File file)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return addAssetAttachment(
			userId, classNameId, classPK, fileName, type, rank, file);
	}

	public AssetAttachment copyAssetAttachment(long assetAttachmentId)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		return copyAssetAttachment(
			assetAttachmentId, assetAttachment.getClassNameId(),
			assetAttachment.getClassPK());
	}

	public AssetAttachment copyAssetAttachment(
			long assetAttachmentId, long classNameId, long classPK)
		throws PortalException, SystemException {

		// Asset attachment

		AssetAttachment assetAttachment =
			assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		long newAssetAttachmentId = counterLocalService.increment();

		AssetAttachment newAssetAttachment = assetAttachmentPersistence.create(
			newAssetAttachmentId);

		newAssetAttachment.setUserId(assetAttachment.getUserId());
		newAssetAttachment.setCreateDate(new Date());
		newAssetAttachment.setClassNameId(classNameId);
		newAssetAttachment.setClassPK(classPK);
		newAssetAttachment.setFileName(assetAttachment.getFileName());
		newAssetAttachment.setType(assetAttachment.getType());

		assetAttachmentPersistence.update(newAssetAttachment, false);

		// File

		try {
			DLStoreUtil.addDirectory(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				newAssetAttachment.getDir());
		}
		catch (DuplicateDirectoryException dde) {
		}

		DLStoreUtil.addFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			newAssetAttachment.getPath(), assetAttachment.getFileAsStream());

		return newAssetAttachment;
	}

	public AssetAttachment copyAssetAttachment(
			long assetAttachmentId, String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return copyAssetAttachment(assetAttachmentId, classNameId, classPK);
	}

	@Override
	public AssetAttachment deleteAssetAttachment(
			AssetAttachment assetAttachment)
		throws PortalException, SystemException {

		// Asset attachment

		assetAttachmentPersistence.remove(assetAttachment);

		// File

		try {
			DLStoreUtil.deleteFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				assetAttachment.getPath());
		}
		catch (NoSuchFileException nsfe) {
		}

		return assetAttachment;
	}

	@Override
	public AssetAttachment deleteAssetAttachment(long assetAttachmentId)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		return deleteAssetAttachment(assetAttachment);
	}

	public void deleteAssetAttachments(
			Date createDate, long classNameId, long classPK)
		throws PortalException, SystemException {

		List<AssetAttachment> assetAttachments =
			assetAttachmentPersistence.findByLtC_C_C(
				createDate, classNameId, classPK);

		for (AssetAttachment assetAttachment : assetAttachments) {
			deleteAssetAttachment(assetAttachment);
		}
	}

	public void deleteAssetAttachments(String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<AssetAttachment> assetAttachments =
			assetAttachmentPersistence.findByC_C(classNameId, classPK);

		for (AssetAttachment assetAttachment : assetAttachments) {
			deleteAssetAttachment(assetAttachment);
		}
	}

	public AssetAttachment fetchAssetAttachment(long assetAttachmentId)
		throws SystemException {

		return assetAttachmentPersistence.fetchByPrimaryKey(assetAttachmentId);
	}

	public List<AssetAttachment> getAssetAttachments(
			String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetAttachmentPersistence.findByC_C(classNameId, classPK);
	}

	public List<AssetAttachment> getAssetAttachments(
			String className, long classPK, int type, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetAttachmentPersistence.findByC_C_T(
			classNameId, classPK, type, start, end, obc);
	}

	public int getAssetAttachmentsCount(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetAttachmentPersistence.countByC_C(classNameId, classPK);
	}

	public int getAssetAttachmentsCount(
			String className, long classPK, int type)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetAttachmentPersistence.countByC_C_T(
			classNameId, classPK, type);
	}

	public AssetAttachment updateAssetAttachment(
			long assetAttachmentId, byte[] bytes)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		try {
			DLStoreUtil.deleteFile(
				OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
				assetAttachment.getPath());
		}
		catch (NoSuchFileException nsfe) {
		}

		DLStoreUtil.addFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			assetAttachment.getPath(), bytes);

		return assetAttachment;
	}

	public AssetAttachment updateAssetAttachment(
			long assetAttachmentId, File file)
		throws PortalException, SystemException {

		assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		byte[] bytes = null;

		try {
			bytes = FileUtil.getBytes(file);
		}
		catch (IOException ioe) {
			throw new AssetAttachmentSizeException(ioe);
		}

		return updateAssetAttachment(assetAttachmentId, bytes);
	}

	public AssetAttachment updateAssetAttachment(
			long assetAttachmentId, String className, long classPK, int rank)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentPersistence.findByPrimaryKey(assetAttachmentId);

		if ((assetAttachment.getClassNameId() ==
				AssetAttachmentConstants.CLASS_NAME_ID_DEFAULT) &&
			(assetAttachment.getClassPK() ==
				AssetAttachmentConstants.CLASS_PK_DEFAULT)) {

			long classNameId = PortalUtil.getClassNameId(className);

			assetAttachment.setClassNameId(classNameId);

			assetAttachment.setClassPK(classPK);
		}

		assetAttachment.setRank(rank);

		assetAttachmentPersistence.update(assetAttachment, false);

		return assetAttachment;
	}

	protected boolean isValidFileName(String fileName) {
		if (fileName == null) {
			return false;
		}

		return true;
	}

	protected void validate(
			long classNameId, long classPK, String fileName, int type)
		throws PortalException, SystemException {

		if (type == AssetAttachmentConstants.TYPE_PACKAGE_PLUGIN) {
			int count = assetAttachmentPersistence.countByC_C_F_T(
				classNameId, classPK, fileName, type);

			if (count > 0) {
				throw new DuplicateAssetAttachmentFileNameException();
			}

			if (!isValidFileName(fileName)) {
				throw new AssetAttachmentFileNameException();
			}
		}
	}

}