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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingExamResultConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingExamLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Haote Chou
 */
public class TrainingExamResultImpl extends TrainingExamResultBaseImpl {

	public TrainingExamResultImpl() {
	}

	public String getCandidateKey() throws PortalException, SystemException {
		User user = getUser();

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(User.class), user.getUserId(),
				ExternalIdMapperConstants.TYPE_KRYTERION);

		if (externalIdMappers.size() == 0) {
			externalIdMappers =
				ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
					PortalUtil.getClassNameId(User.class), user.getUserId(),
					ExternalIdMapperConstants.TYPE_PROMETRIC);
		}

		ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

		return externalIdMapper.getExternalId();
	}

	public String getResult() {
		int recordType = getRecordType();

		if (recordType == TrainingExamResultConstants.RECORD_TYPE_CANCEL) {
			return "test-cancelled";
		}
		else if (recordType == TrainingExamResultConstants.RECORD_TYPE_EXPIRE) {
			return "test-expired";
		}
		else if (recordType ==
					TrainingExamResultConstants.RECORD_TYPE_NO_SHOW) {

			return "no-show";
		}

		return TrainingExamResultConstants.getGradeLabel(getGrade());
	}

	public String getTrainingCertificateKey()
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer = getTrainingCustomer();

		if (trainingCustomer == null) {
			return StringPool.BLANK;
		}

		return trainingCustomer.getTrainingCertificateKey();
	}

	public TrainingCertificateTemplate getTrainingCertificateTemplate()
		throws PortalException, SystemException {

		TrainingExam trainingExam = getTrainingExam();

		return trainingExam.getTrainingCertificateTemplate();
	}

	public TrainingCustomer getTrainingCustomer()
		throws PortalException, SystemException {

		List<TrainingCustomer> trainingCustomers =
			TrainingCustomerLocalServiceUtil.getClassTrainingCustomers(
				PortalUtil.getClassNameId(TrainingExamResult.class),
				getTrainingExamResultId());

		if (trainingCustomers.isEmpty()) {
			return null;
		}

		return trainingCustomers.get(0);
	}

	public TrainingExam getTrainingExam()
		throws PortalException, SystemException {

		return TrainingExamLocalServiceUtil.getTrainingExam(
			getTrainingExamId());
	}

	public User getUser() throws PortalException, SystemException {
		TrainingCustomer trainingCustomer = getTrainingCustomer();

		return trainingCustomer.getUser();
	}

	public boolean hasPassedGrade() {
		if (getGrade() == TrainingExamResultConstants.GRADE_PASSED) {
			return true;
		}

		return false;
	}

}