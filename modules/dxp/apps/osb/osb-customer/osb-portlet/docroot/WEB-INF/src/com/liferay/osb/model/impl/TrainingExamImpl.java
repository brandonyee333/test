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
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Haote Chou
 */
public class TrainingExamImpl extends TrainingExamBaseImpl {

	public TrainingExamImpl() {
	}

	public String getTestKey() throws PortalException, SystemException {
		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				PortalUtil.getClassNameId(TrainingExam.class),
				getTrainingExamId(), ExternalIdMapperConstants.TYPE_KRYTERION);

		if (externalIdMappers.isEmpty()) {
			externalIdMappers =
				ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
					PortalUtil.getClassNameId(TrainingExam.class),
					getTrainingExamId(),
					ExternalIdMapperConstants.TYPE_PROMETRIC);
		}

		ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

		return externalIdMapper.getExternalId();
	}

	public TrainingCertificateTemplate getTrainingCertificateTemplate()
		throws PortalException, SystemException {

		return TrainingCertificateTemplateLocalServiceUtil.
			getTrainingCertificateTemplate(getTrainingCertificateTemplateId());
	}

}