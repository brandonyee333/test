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

import com.liferay.osb.NoSuchTrainingWorkerException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.TrainingWorker;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.base.TrainingWorkerLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Wesley Gong
 */
public class TrainingWorkerLocalServiceImpl
	extends TrainingWorkerLocalServiceBaseImpl {

	public TrainingWorker addTrainingWorker(
			long userId, long classNameId, long classPK, long userProfileId)
		throws PortalException, SystemException {

		UserProfile userProfile = userProfilePersistence.fetchByPrimaryKey(
			userProfileId);

		if (userProfile == null) {
			User user = userPersistence.findByPrimaryKey(userId);

			userProfile = userProfileLocalService.addUserProfile(
				userId, user.getEmailAddress(), user.getFirstName(),
				user.getLastName(), StringPool.BLANK);
		}

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.addUserProfileHistory(
				userId, classNameId, classPK, userProfile.getEmailAddress(),
				userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getLegalEntityName());

		long trainingWorkerId = counterLocalService.increment();

		TrainingWorker trainingWorker = trainingWorkerPersistence.create(
			trainingWorkerId);

		trainingWorker.setUserId(userId);
		trainingWorker.setClassNameId(classNameId);
		trainingWorker.setClassPK(classPK);
		trainingWorker.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		trainingWorkerPersistence.update(trainingWorker, false);

		return trainingWorker;
	}

	public void addTrainingWorkers(
			long[] userIds, long classNameId, long classPK,
			Map<Long, Long> userProfileIds)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			Long userProfileId = userProfileIds.get(userId);

			if (userProfileId == null) {
				userProfileId = 0L;
			}

			TrainingWorker trainingWorker =
				trainingWorkerPersistence.fetchByU_C_C(
					userId, classNameId, classPK);

			if (trainingWorker == null) {
				addTrainingWorker(userId, classNameId, classPK, userProfileId);
			}
			else if (userProfileId > 0) {
				updateTrainingWorker(
					trainingWorker.getTrainingWorkerId(), userId, classNameId,
					classPK, userProfileId);
			}
		}
	}

	public void deleteTrainingWorkers(long userId) throws SystemException {
		trainingWorkerPersistence.removeByUserId(userId);
	}

	public void deleteTrainingWorkers(
			long[] userIds, long classNameId, long classPK)
		throws SystemException {

		for (long userId : userIds) {
			try {
				trainingWorkerPersistence.removeByU_C_C(
					userId, classNameId, classPK);
			}
			catch (NoSuchTrainingWorkerException nstwe) {
			}
		}
	}

	public TrainingWorker fetchTrainingWorker(
			long userId, long classNameId, long classPK)
		throws SystemException {

		return trainingWorkerPersistence.fetchByU_C_C(
			userId, classNameId, classPK);
	}

	public List<TrainingWorker> getTrainingWorkers(
			long classNameId, long classPK)
		throws SystemException {

		return trainingWorkerPersistence.findByC_C(classNameId, classPK);
	}

	public boolean hasTrainingWorker(
			long userId, long classNameId, long classPK)
		throws SystemException {

		TrainingWorker trainingWorker = trainingWorkerPersistence.fetchByU_C_C(
			userId, classNameId, classPK);

		if (trainingWorker == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public TrainingWorker updateTrainingWorker(
			long trainingWorkerId, long userId, long classNameId, long classPK,
			long userProfileId)
		throws PortalException, SystemException {

		UserProfile userProfile = userProfilePersistence.findByPrimaryKey(
			userProfileId);

		TrainingWorker trainingWorker =
			trainingWorkerPersistence.findByPrimaryKey(trainingWorkerId);

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.getUserProfileHistory(
				trainingWorker.getUserProfileHistoryId());

		if (AdminUtil.equalsByUserProfile(userProfile, userProfileHistory)) {
			return trainingWorker;
		}

		userProfileHistory =
			userProfileHistoryLocalService.addUserProfileHistory(
				userId, classNameId, classPK, userProfile.getEmailAddress(),
				userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getLegalEntityName());

		trainingWorker.setUserId(userId);
		trainingWorker.setClassNameId(classNameId);
		trainingWorker.setClassPK(classPK);
		trainingWorker.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		trainingWorkerPersistence.update(trainingWorker, false);

		return trainingWorker;
	}

}