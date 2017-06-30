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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.osb.DuplicateTrainingLinkedUserException;
import com.liferay.osb.TrainingLinkedUserPrimaryUserIdException;
import com.liferay.osb.admin.util.TrainingEmailUtil;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.service.base.TrainingLinkedUserLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * @author Douglas Wong
 * @author Joan Kim
 */
public class TrainingLinkedUserLocalServiceImpl
	extends TrainingLinkedUserLocalServiceBaseImpl {

	public void addTrainingLinkedUsers(
			long primaryUserId, long oldPrimaryUserId, long[] userIds)
		throws PortalException, SystemException {

		List<TrainingLinkedUser> trainingLinkedUsers =
			updateTrainingLinkedUsers(primaryUserId, oldPrimaryUserId);

		if (trainingLinkedUsers.isEmpty()) {
			userIds = ArrayUtil.append(userIds, primaryUserId);
		}

		validate(primaryUserId, userIds);

		for (long userId : userIds) {
			TrainingLinkedUser trainingLinkedUser =
				trainingLinkedUserPersistence.fetchByUserId(userId);

			if (trainingLinkedUser == null) {
				long trainingLinkedUserId = counterLocalService.increment();

				trainingLinkedUser = trainingLinkedUserPersistence.create(
					trainingLinkedUserId);
			}

			trainingLinkedUser.setUserId(userId);
			trainingLinkedUser.setPrimaryUserId(primaryUserId);

			trainingLinkedUserPersistence.update(trainingLinkedUser, false);
		}

		TrainingEmailUtil.sendTrainingLinkedUserPrimaryUserMail(primaryUserId);

		TrainingEmailUtil.sendTrainingLinkedUserTrainingUsersMail(
			primaryUserId);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(primaryUserId);

		for (long userId : userIds) {
			indexer.reindex(userId);
		}
	}

	public TrainingLinkedUser fetchUserTrainingLinkedUser(long userId)
		throws SystemException {

		return trainingLinkedUserPersistence.fetchByUserId(userId);
	}

	public List<TrainingLinkedUser> getTrainingLinkedUsers(long primaryUserId)
		throws SystemException {

		return trainingLinkedUserPersistence.findByPrimaryUserId(primaryUserId);
	}

	public boolean isPrimaryTrainingLinkedUser(long userId)
		throws SystemException {

		TrainingLinkedUser trainingLinkedUser =
			trainingLinkedUserPersistence.fetchByUserId(userId);

		if ((trainingLinkedUser == null) ||
			(trainingLinkedUser.getPrimaryUserId() == userId)) {

			return true;
		}

		return false;
	}

	public void unsetTrainingLinkedUsers(long primaryUserId, long[] userIds)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			TrainingLinkedUser trainingLinkedUser =
				trainingLinkedUserPersistence.fetchByUserId(userId);

			if (trainingLinkedUser != null) {
				trainingLinkedUserPersistence.remove(trainingLinkedUser);
			}
		}

		List<TrainingLinkedUser> trainingLinkedUsers =
			trainingLinkedUserPersistence.findByPrimaryUserId(primaryUserId);

		if (trainingLinkedUsers.size() == 1) {
			TrainingLinkedUser trainingLinkedUser = trainingLinkedUsers.get(0);

			trainingLinkedUserPersistence.remove(trainingLinkedUser);
		}

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(primaryUserId);

		for (long userId : userIds) {
			indexer.reindex(userId);
		}
	}

	public void updateTrainingLinkedUsers(
			long primaryUserId, long oldPrimaryUserId, long[] userIds)
		throws PortalException, SystemException {

		List<TrainingLinkedUser> trainingLinkedUsers =
			updateTrainingLinkedUsers(primaryUserId, oldPrimaryUserId);

		validate(primaryUserId, userIds);

		trainingLinkedUsers = ListUtil.copy(trainingLinkedUsers);

		if (userIds.length > 1) {
			for (long userId : userIds) {
				TrainingLinkedUser trainingLinkedUser =
					trainingLinkedUserPersistence.fetchByUserId(userId);

				if (trainingLinkedUser == null) {
					long trainingLinkedUserId = counterLocalService.increment();

					trainingLinkedUser = trainingLinkedUserPersistence.create(
						trainingLinkedUserId);
				}

				trainingLinkedUser.setUserId(userId);
				trainingLinkedUser.setPrimaryUserId(primaryUserId);

				trainingLinkedUserPersistence.update(trainingLinkedUser, false);

				trainingLinkedUsers.remove(trainingLinkedUser);
			}
		}

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		for (TrainingLinkedUser trainingLinkedUser : trainingLinkedUsers) {
			trainingLinkedUserPersistence.remove(
				trainingLinkedUser.getTrainingLinkedUserId());

			indexer.reindex(trainingLinkedUser.getUserId());
		}

		for (long userId : userIds) {
			indexer.reindex(userId);
		}
	}

	protected List<TrainingLinkedUser> updateTrainingLinkedUsers(
			long primaryUserId, long oldPrimaryUserId)
		throws PortalException, SystemException {

		List<TrainingLinkedUser> trainingLinkedUsers =
			trainingLinkedUserPersistence.findByPrimaryUserId(oldPrimaryUserId);

		if (primaryUserId == oldPrimaryUserId) {
			return trainingLinkedUsers;
		}

		for (TrainingLinkedUser trainingLinkedUser : trainingLinkedUsers) {
			trainingLinkedUser.setPrimaryUserId(primaryUserId);

			trainingLinkedUserPersistence.update(trainingLinkedUser, false);
		}

		return trainingLinkedUsers;
	}

	protected void validate(long primaryUserId, long[] userIds)
		throws PortalException, SystemException {

		if ((userIds == null) || (userIds.length == 0)) {
			return;
		}

		if (!ArrayUtil.contains(userIds, primaryUserId)) {
			throw new TrainingLinkedUserPrimaryUserIdException();
		}

		for (long userId : userIds) {
			TrainingLinkedUser trainingLinkedUser =
				trainingLinkedUserPersistence.fetchByUserId(userId);

			if ((trainingLinkedUser == null) ||
				ArrayUtil.contains(
					userIds, trainingLinkedUser.getPrimaryUserId())) {

				continue;
			}

			throw new DuplicateTrainingLinkedUserException();
		}
	}

}