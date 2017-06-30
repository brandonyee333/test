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

import com.liferay.osb.model.TrainingExamResultItemConstants;
import com.liferay.osb.model.TrainingExamResultSection;
import com.liferay.osb.service.TrainingExamResultSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultItemImpl extends TrainingExamResultItemBaseImpl {

	public TrainingExamResultItemImpl() {
	}

	public String getScoreResult() {
		return TrainingExamResultItemConstants.getScoreLabel(getScore());
	}

	public String getSectionKey() throws PortalException, SystemException {
		TrainingExamResultSection trainingExamResultSection =
			TrainingExamResultSectionLocalServiceUtil.
				getTrainingExamResultSection(getTrainingExamResultSectionId());

		return trainingExamResultSection.getSectionKey();
	}

	public String getStatusLabel() {
		return TrainingExamResultItemConstants.getStatusLabel(getStatus());
	}

}