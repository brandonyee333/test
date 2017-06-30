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
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.service.base.TrainingExamLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Calvin Keum
 * @author Haote Chou
 */
public class TrainingExamLocalServiceImpl
	extends TrainingExamLocalServiceBaseImpl {

	public TrainingExam addTrainingExam(String name, String testKey)
		throws SystemException {

		long trainingExamId = counterLocalService.increment();

		externalIdMapperLocalService.addExternalIdMapper(
			PortalUtil.getClassNameId(TrainingExam.class), trainingExamId,
			ExternalIdMapperConstants.TYPE_KRYTERION, testKey);

		TrainingExam trainingExam = trainingExamPersistence.create(
			trainingExamId);

		trainingExam.setName(name);

		return trainingExamPersistence.update(trainingExam, false);
	}

	public List<TrainingExam> getTrainingExams(String name)
		throws SystemException {

		return trainingExamPersistence.findByName(name);
	}

	public TrainingExam updateTrainingExam(
			long trainingExamId, long trainingCertificateTemplateId)
		throws PortalException, SystemException {

		TrainingExam trainingExam = trainingExamPersistence.findByPrimaryKey(
			trainingExamId);

		trainingExam.setTrainingCertificateTemplateId(
			trainingCertificateTemplateId);

		return trainingExamPersistence.update(trainingExam, false);
	}

}