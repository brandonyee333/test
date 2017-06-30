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

import com.liferay.osb.model.TrainingImportLog;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingImportLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingImportLog
 * @generated
 */
public class TrainingImportLogCacheModel implements CacheModel<TrainingImportLog>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{trainingImportLogId=");
		sb.append(trainingImportLogId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", importDate=");
		sb.append(importDate);
		sb.append("}");

		return sb.toString();
	}

	public TrainingImportLog toEntityModel() {
		TrainingImportLogImpl trainingImportLogImpl = new TrainingImportLogImpl();

		trainingImportLogImpl.setTrainingImportLogId(trainingImportLogId);
		trainingImportLogImpl.setType(type);

		if (importDate == Long.MIN_VALUE) {
			trainingImportLogImpl.setImportDate(null);
		}
		else {
			trainingImportLogImpl.setImportDate(new Date(importDate));
		}

		trainingImportLogImpl.resetOriginalValues();

		return trainingImportLogImpl;
	}

	public long trainingImportLogId;
	public int type;
	public long importDate;
}