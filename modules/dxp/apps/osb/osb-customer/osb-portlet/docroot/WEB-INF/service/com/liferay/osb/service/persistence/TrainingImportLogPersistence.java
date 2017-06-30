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

import com.liferay.osb.model.TrainingImportLog;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training import log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingImportLogPersistenceImpl
 * @see TrainingImportLogUtil
 * @generated
 */
public interface TrainingImportLogPersistence extends BasePersistence<TrainingImportLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingImportLogUtil} to access the training import log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training import log in the entity cache if it is enabled.
	*
	* @param trainingImportLog the training import log
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingImportLog trainingImportLog);

	/**
	* Caches the training import logs in the entity cache if it is enabled.
	*
	* @param trainingImportLogs the training import logs
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingImportLog> trainingImportLogs);

	/**
	* Creates a new training import log with the primary key. Does not add the training import log to the database.
	*
	* @param trainingImportLogId the primary key for the new training import log
	* @return the new training import log
	*/
	public com.liferay.osb.model.TrainingImportLog create(
		long trainingImportLogId);

	/**
	* Removes the training import log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingImportLogId the primary key of the training import log
	* @return the training import log that was removed
	* @throws com.liferay.osb.NoSuchTrainingImportLogException if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog remove(
		long trainingImportLogId)
		throws com.liferay.osb.NoSuchTrainingImportLogException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingImportLog updateImpl(
		com.liferay.osb.model.TrainingImportLog trainingImportLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training import log with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingImportLogException} if it could not be found.
	*
	* @param trainingImportLogId the primary key of the training import log
	* @return the training import log
	* @throws com.liferay.osb.NoSuchTrainingImportLogException if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog findByPrimaryKey(
		long trainingImportLogId)
		throws com.liferay.osb.NoSuchTrainingImportLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training import log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingImportLogId the primary key of the training import log
	* @return the training import log, or <code>null</code> if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog fetchByPrimaryKey(
		long trainingImportLogId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training import logs where type = &#63;.
	*
	* @param type the type
	* @return the matching training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training import logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of training import logs
	* @param end the upper bound of the range of training import logs (not inclusive)
	* @return the range of matching training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training import logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of training import logs
	* @param end the upper bound of the range of training import logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training import log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training import log
	* @throws com.liferay.osb.NoSuchTrainingImportLogException if a matching training import log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog findByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingImportLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training import log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training import log, or <code>null</code> if a matching training import log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog fetchByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training import log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training import log
	* @throws com.liferay.osb.NoSuchTrainingImportLogException if a matching training import log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog findByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingImportLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training import log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training import log, or <code>null</code> if a matching training import log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog fetchByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training import logs before and after the current training import log in the ordered set where type = &#63;.
	*
	* @param trainingImportLogId the primary key of the current training import log
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training import log
	* @throws com.liferay.osb.NoSuchTrainingImportLogException if a training import log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingImportLog[] findByType_PrevAndNext(
		long trainingImportLogId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingImportLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training import logs.
	*
	* @return the training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training import logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training import logs
	* @param end the upper bound of the range of training import logs (not inclusive)
	* @return the range of training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training import logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training import logs
	* @param end the upper bound of the range of training import logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training import logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingImportLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training import logs where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training import logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training import logs where type = &#63;.
	*
	* @param type the type
	* @return the number of matching training import logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training import logs.
	*
	* @return the number of training import logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}