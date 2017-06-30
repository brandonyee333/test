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

import com.liferay.osb.model.TrainingWorker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TrainingWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingWorker
 * @generated
 */
public class TrainingWorkerCacheModel implements CacheModel<TrainingWorker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{trainingWorkerId=");
		sb.append(trainingWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", userProfileHistoryId=");
		sb.append(userProfileHistoryId);
		sb.append("}");

		return sb.toString();
	}

	public TrainingWorker toEntityModel() {
		TrainingWorkerImpl trainingWorkerImpl = new TrainingWorkerImpl();

		trainingWorkerImpl.setTrainingWorkerId(trainingWorkerId);
		trainingWorkerImpl.setUserId(userId);
		trainingWorkerImpl.setClassNameId(classNameId);
		trainingWorkerImpl.setClassPK(classPK);
		trainingWorkerImpl.setUserProfileHistoryId(userProfileHistoryId);

		trainingWorkerImpl.resetOriginalValues();

		return trainingWorkerImpl;
	}

	public long trainingWorkerId;
	public long userId;
	public long classNameId;
	public long classPK;
	public long userProfileHistoryId;
}