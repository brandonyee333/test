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

import com.liferay.osb.model.TrainingWorker;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingWorkerPersistenceImpl
 * @see TrainingWorkerUtil
 * @generated
 */
public interface TrainingWorkerPersistence extends BasePersistence<TrainingWorker> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingWorkerUtil} to access the training worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training worker in the entity cache if it is enabled.
	*
	* @param trainingWorker the training worker
	*/
	public void cacheResult(com.liferay.osb.model.TrainingWorker trainingWorker);

	/**
	* Caches the training workers in the entity cache if it is enabled.
	*
	* @param trainingWorkers the training workers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingWorker> trainingWorkers);

	/**
	* Creates a new training worker with the primary key. Does not add the training worker to the database.
	*
	* @param trainingWorkerId the primary key for the new training worker
	* @return the new training worker
	*/
	public com.liferay.osb.model.TrainingWorker create(long trainingWorkerId);

	/**
	* Removes the training worker with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingWorkerId the primary key of the training worker
	* @return the training worker that was removed
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker remove(long trainingWorkerId)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingWorker updateImpl(
		com.liferay.osb.model.TrainingWorker trainingWorker, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training worker with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingWorkerException} if it could not be found.
	*
	* @param trainingWorkerId the primary key of the training worker
	* @return the training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByPrimaryKey(
		long trainingWorkerId)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training worker with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingWorkerId the primary key of the training worker
	* @return the training worker, or <code>null</code> if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByPrimaryKey(
		long trainingWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @return the range of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training workers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training worker in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training workers before and after the current training worker in the ordered set where userId = &#63;.
	*
	* @param trainingWorkerId the primary key of the current training worker
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker[] findByUserId_PrevAndNext(
		long trainingWorkerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training workers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training workers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @return the range of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training workers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training workers before and after the current training worker in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param trainingWorkerId the primary key of the current training worker
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a training worker with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker[] findByC_C_PrevAndNext(
		long trainingWorkerId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingWorkerException} if it could not be found.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training worker
	* @throws com.liferay.osb.NoSuchTrainingWorkerException if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker findByU_C_C(long userId,
		long classNameId, long classPK)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByU_C_C(long userId,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training worker, or <code>null</code> if a matching training worker could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker fetchByU_C_C(long userId,
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training workers.
	*
	* @return the training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @return the range of training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training workers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training workers
	* @param end the upper bound of the range of training workers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training workers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingWorker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training workers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training workers where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training worker where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the training worker that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingWorker removeByU_C_C(long userId,
		long classNameId, long classPK)
		throws com.liferay.osb.NoSuchTrainingWorkerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training workers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training workers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training workers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training workers where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching training workers
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training workers.
	*
	* @return the number of training workers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}