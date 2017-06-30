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

import com.liferay.osb.model.TrainingImportLog;
import com.liferay.osb.service.base.TrainingImportLogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;
import java.util.List;

/**
 * @author Val Nagy
 */
public class TrainingImportLogLocalServiceImpl
	extends TrainingImportLogLocalServiceBaseImpl {

	public TrainingImportLog addTrainingImportLog(int type)
		throws PortalException, SystemException {

		long trainingImportLogId = counterLocalService.increment();

		TrainingImportLog trainingImportLog =
			trainingImportLogPersistence.create(trainingImportLogId);

		trainingImportLog.setType(type);
		trainingImportLog.setImportDate(new Date());

		trainingImportLogPersistence.update(trainingImportLog, false);

		return trainingImportLog;
	}

	public List<TrainingImportLog> getTrainingImportLogs(
			int type, int start, int end)
		throws SystemException {

		return trainingImportLogPersistence.findByType(type, start, end);
	}

}