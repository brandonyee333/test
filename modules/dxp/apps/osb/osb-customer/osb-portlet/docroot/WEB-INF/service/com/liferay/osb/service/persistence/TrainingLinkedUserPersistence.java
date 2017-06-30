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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.TrainingLinkedUser;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training linked user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLinkedUserPersistenceImpl
 * @see TrainingLinkedUserUtil
 * @generated
 */
public interface TrainingLinkedUserPersistence extends BasePersistence<TrainingLinkedUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingLinkedUserUtil} to access the training linked user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training linked user in the entity cache if it is enabled.
	*
	* @param trainingLinkedUser the training linked user
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser);

	/**
	* Caches the training linked users in the entity cache if it is enabled.
	*
	* @param trainingLinkedUsers the training linked users
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingLinkedUser> trainingLinkedUsers);

	/**
	* Creates a new training linked user with the primary key. Does not add the training linked user to the database.
	*
	* @param trainingLinkedUserId the primary key for the new training linked user
	* @return the new training linked user
	*/
	public com.liferay.osb.model.TrainingLinkedUser create(
		long trainingLinkedUserId);

	/**
	* Removes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user that was removed
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser remove(
		long trainingLinkedUserId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingLinkedUser updateImpl(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked user with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser findByPrimaryKey(
		long trainingLinkedUserId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user, or <code>null</code> if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryKey(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked user where userId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser findByUserId(long userId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser fetchByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser fetchByUserId(long userId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training linked users where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @return the matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training linked users where primaryUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param primaryUserId the primary user ID
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @return the range of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training linked users where primaryUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param primaryUserId the primary user ID
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser findByPrimaryUserId_First(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryUserId_First(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser findByPrimaryUserId_Last(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryUserId_Last(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training linked users before and after the current training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param trainingLinkedUserId the primary key of the current training linked user
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser[] findByPrimaryUserId_PrevAndNext(
		long trainingLinkedUserId, long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training linked users.
	*
	* @return the training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training linked users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @return the range of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training linked users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training linked user where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the training linked user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingLinkedUser removeByUserId(long userId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training linked users where primaryUserId = &#63; from the database.
	*
	* @param primaryUserId the primary user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPrimaryUserId(long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training linked users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training linked users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training linked users where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @return the number of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public int countByPrimaryUserId(long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training linked users.
	*
	* @return the number of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}