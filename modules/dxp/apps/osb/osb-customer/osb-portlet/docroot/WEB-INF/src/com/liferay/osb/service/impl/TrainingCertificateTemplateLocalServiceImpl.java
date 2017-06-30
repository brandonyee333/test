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
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.RequiredTrainingCertificateTemplateException;
import com.liferay.osb.TrainingCertificateTemplateNameException;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.service.base.TrainingCertificateTemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * @author Wesley Gong
 */
public class TrainingCertificateTemplateLocalServiceImpl
	extends TrainingCertificateTemplateLocalServiceBaseImpl {

	public TrainingCertificateTemplate addTrainingCertificateTemplate(
			long userId, String name, String description, int type,
			String templateFileName, InputStream templateInputStream,
			String badgeFileName, InputStream badgeInputStream)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name);

		validateTemplateFile(templateFileName);

		if (badgeInputStream != null) {
			validateBadgeFile(badgeFileName);
		}

		long trainingCertificateTemplateId = counterLocalService.increment();

		TrainingCertificateTemplate trainingCertificateTemplate =
			trainingCertificateTemplatePersistence.create(
				trainingCertificateTemplateId);

		trainingCertificateTemplate.setUserId(user.getUserId());
		trainingCertificateTemplate.setUserName(user.getFullName());
		trainingCertificateTemplate.setCreateDate(now);
		trainingCertificateTemplate.setModifiedDate(now);
		trainingCertificateTemplate.setName(name);
		trainingCertificateTemplate.setDescription(description);
		trainingCertificateTemplate.setType(type);

		trainingCertificateTemplatePersistence.update(
			trainingCertificateTemplate, false);

		if (templateInputStream != null) {
			addFile(
				trainingCertificateTemplate,
				trainingCertificateTemplate.getTemplatesDir(), templateFileName,
				templateInputStream);
		}

		if (badgeInputStream != null) {
			addFile(
				trainingCertificateTemplate,
				trainingCertificateTemplate.getBadgesDir(), badgeFileName,
				badgeInputStream);
		}

		return trainingCertificateTemplate;
	}

	@Override
	public TrainingCertificateTemplate deleteTrainingCertificateTemplate(
			long trainingCertificateTemplateId)
		throws PortalException, SystemException {

		if ((trainingEventPersistence.countByTrainingCertificateTemplateId(
				trainingCertificateTemplateId) > 0) ||
			(trainingExamPersistence.countByTrainingCertificateTemplateId(
				trainingCertificateTemplateId) > 0)) {

			throw new RequiredTrainingCertificateTemplateException();
		}

		TrainingCertificateTemplate trainingCertificateTemplate =
			trainingCertificateTemplatePersistence.findByPrimaryKey(
				trainingCertificateTemplateId);

		deleteFile(trainingCertificateTemplate.getBadgesDir());

		deleteFile(trainingCertificateTemplate.getTemplatesDir());

		return trainingCertificateTemplatePersistence.remove(
			trainingCertificateTemplate);
	}

	public InputStream getTrainingCertificateTemplateAsStream(
			long trainingCertificateTemplateId)
		throws PortalException, SystemException {

		TrainingCertificateTemplate trainingCertificateTemplate =
			trainingCertificateTemplatePersistence.findByPrimaryKey(
				trainingCertificateTemplateId);

		String[] fileNames = DLStoreUtil.getFileNames(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
			trainingCertificateTemplate.getTemplatesDir());

		return DLStoreUtil.getFileAsStream(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM, fileNames[0]);
	}

	public List<TrainingCertificateTemplate> getTrainingCertificateTemplates(
			int type)
		throws SystemException {

		return trainingCertificateTemplatePersistence.findByType(type);
	}

	public List<TrainingCertificateTemplate> getTrainingCertificateTemplates(
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return trainingCertificateTemplatePersistence.findAll(start, end, obc);
	}

	public boolean hasCompletedTrainingCertificateTemplate(
			long userId, long classNameId, long trainingCertificateTemplateId)
		throws PortalException, SystemException {

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			TrainingCustomer trainingCustomer =
				trainingCustomerPersistence.findByU_C_C(
					userId, classNameId, trainingCertificateTemplateId);

			if ((trainingCustomer == null) ||
				(trainingCustomer.getStatus() !=
					TrainingCustomerConstants.STATUS_CERTIFIED)) {

				return false;
			}

			return true;
		}

		List<TrainingCustomer> trainingCustomers =
			trainingCustomerPersistence.findByU_C(userId, classNameId);

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			TrainingExamResult trainingExamResult =
				trainingExamResultPersistence.fetchByPrimaryKey(
					trainingCustomer.getClassPK());

			if (trainingExamResult == null) {
				continue;
			}

			TrainingExam trainingExam =
				trainingExamPersistence.findByPrimaryKey(
					trainingExamResult.getTrainingExamId());

			if ((trainingExam.getTrainingCertificateTemplateId() ==
					trainingCertificateTemplateId) &&
				trainingExamResult.hasPassedGrade()) {

				return true;
			}
		}

		return false;
	}

	public TrainingCertificateTemplate updateTrainingCertificateTemplate(
			long companyId, long trainingCertificateTemplateId, String name,
			String description, int type, String templateFileName,
			InputStream templateInputStream, String badgeFileName,
			InputStream badgeInputStream)
		throws PortalException, SystemException {

		validate(name);

		TrainingCertificateTemplate trainingCertificateTemplate =
			trainingCertificateTemplatePersistence.findByPrimaryKey(
				trainingCertificateTemplateId);

		trainingCertificateTemplate.setModifiedDate(new Date());
		trainingCertificateTemplate.setName(name);
		trainingCertificateTemplate.setDescription(description);
		trainingCertificateTemplate.setType(type);

		trainingCertificateTemplatePersistence.update(
			trainingCertificateTemplate, false);

		if (templateInputStream != null) {
			validateTemplateFile(templateFileName);

			updateFile(
				trainingCertificateTemplate,
				trainingCertificateTemplate.getTemplatesDir(), templateFileName,
				templateInputStream);
		}

		if (badgeInputStream != null) {
			validateBadgeFile(badgeFileName);

			updateFile(
				trainingCertificateTemplate,
				trainingCertificateTemplate.getBadgesDir(), badgeFileName,
				badgeInputStream);
		}

		return trainingCertificateTemplate;
	}

	protected void addFile(
			TrainingCertificateTemplate trainingCertificateTemplate,
			String directoryName, String fileName, InputStream inputStream)
		throws PortalException, SystemException {

		try {
			DLStoreUtil.addDirectory(
				CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
				directoryName);
		}
		catch (DuplicateDirectoryException dde) {
		}

		String fileLocation = directoryName.concat(
			StringPool.SLASH).concat(fileName);

		DLStoreUtil.addFile(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM, fileLocation,
			false, inputStream);
	}

	protected void deleteFile(String directoryName)
		throws PortalException, SystemException {

		try {
			DLStoreUtil.deleteDirectory(
				CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
				directoryName);
		}
		catch (NoSuchDirectoryException nsde) {
		}
	}

	protected void updateFile(
			TrainingCertificateTemplate trainingCertificateTemplate,
			String directoryName, String fileName, InputStream inputStream)
		throws PortalException, SystemException {

		deleteFile(directoryName);

		addFile(
			trainingCertificateTemplate, directoryName, fileName, inputStream);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new TrainingCertificateTemplateNameException();
		}
	}

	protected void validateBadgeFile(String fileName) throws PortalException {
		if (Validator.isNull(fileName)) {
			throw new FileNameException(fileName);
		}

		String fileExtension = FileUtil.getExtension(fileName);

		if (!fileExtension.equals("png")) {
			throw new FileExtensionException();
		}
	}

	protected void validateTemplateFile(String fileName)
		throws PortalException {

		if (Validator.isNull(fileName)) {
			throw new FileNameException(fileName);
		}

		String fileExtension = FileUtil.getExtension(fileName);

		if (!fileExtension.equals("pdf")) {
			throw new FileExtensionException();
		}
	}

}