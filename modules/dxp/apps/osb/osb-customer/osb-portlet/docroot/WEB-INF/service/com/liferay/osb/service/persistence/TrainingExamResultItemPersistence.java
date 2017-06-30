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

import com.liferay.osb.model.TrainingExamResultItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training exam result item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultItemPersistenceImpl
 * @see TrainingExamResultItemUtil
 * @generated
 */
public interface TrainingExamResultItemPersistence extends BasePersistence<TrainingExamResultItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingExamResultItemUtil} to access the training exam result item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training exam result item in the entity cache if it is enabled.
	*
	* @param trainingExamResultItem the training exam result item
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem);

	/**
	* Caches the training exam result items in the entity cache if it is enabled.
	*
	* @param trainingExamResultItems the training exam result items
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingExamResultItem> trainingExamResultItems);

	/**
	* Creates a new training exam result item with the primary key. Does not add the training exam result item to the database.
	*
	* @param trainingExamResultItemId the primary key for the new training exam result item
	* @return the new training exam result item
	*/
	public com.liferay.osb.model.TrainingExamResultItem create(
		long trainingExamResultItemId);

	/**
	* Removes the training exam result item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingExamResultItemId the primary key of the training exam result item
	* @return the training exam result item that was removed
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem remove(
		long trainingExamResultItemId)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingExamResultItem updateImpl(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result item with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingExamResultItemException} if it could not be found.
	*
	* @param trainingExamResultItemId the primary key of the training exam result item
	* @return the training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem findByPrimaryKey(
		long trainingExamResultItemId)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingExamResultItemId the primary key of the training exam result item
	* @return the training exam result item, or <code>null</code> if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem fetchByPrimaryKey(
		long trainingExamResultItemId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam result items where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultId(
		long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam result items where trainingExamResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultId the training exam result ID
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @return the range of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam result items where trainingExamResultId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultId the training exam result ID
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultId(
		long trainingExamResultId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result item in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem findByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result item in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result item, or <code>null</code> if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem fetchByTrainingExamResultId_First(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result item in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem findByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result item in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result item, or <code>null</code> if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem fetchByTrainingExamResultId_Last(
		long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result items before and after the current training exam result item in the ordered set where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultItemId the primary key of the current training exam result item
	* @param trainingExamResultId the training exam result ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem[] findByTrainingExamResultId_PrevAndNext(
		long trainingExamResultItemId, long trainingExamResultId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam result items where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @return the matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultSectionId(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam result items where trainingExamResultSectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @return the range of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultSectionId(
		long trainingExamResultSectionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam result items where trainingExamResultSectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findByTrainingExamResultSectionId(
		long trainingExamResultSectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result item in the ordered set where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem findByTrainingExamResultSectionId_First(
		long trainingExamResultSectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first training exam result item in the ordered set where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training exam result item, or <code>null</code> if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem fetchByTrainingExamResultSectionId_First(
		long trainingExamResultSectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result item in the ordered set where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem findByTrainingExamResultSectionId_Last(
		long trainingExamResultSectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last training exam result item in the ordered set where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training exam result item, or <code>null</code> if a matching training exam result item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem fetchByTrainingExamResultSectionId_Last(
		long trainingExamResultSectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training exam result items before and after the current training exam result item in the ordered set where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultItemId the primary key of the current training exam result item
	* @param trainingExamResultSectionId the training exam result section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training exam result item
	* @throws com.liferay.osb.NoSuchTrainingExamResultItemException if a training exam result item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingExamResultItem[] findByTrainingExamResultSectionId_PrevAndNext(
		long trainingExamResultItemId, long trainingExamResultSectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingExamResultItemException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training exam result items.
	*
	* @return the training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the training exam result items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @return the range of training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the training exam result items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training exam result items
	* @param end the upper bound of the range of training exam result items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingExamResultItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam result items where trainingExamResultId = &#63; from the database.
	*
	* @param trainingExamResultId the training exam result ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam result items where trainingExamResultSectionId = &#63; from the database.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTrainingExamResultSectionId(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training exam result items from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result items where trainingExamResultId = &#63;.
	*
	* @param trainingExamResultId the training exam result ID
	* @return the number of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingExamResultId(long trainingExamResultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result items where trainingExamResultSectionId = &#63;.
	*
	* @param trainingExamResultSectionId the training exam result section ID
	* @return the number of matching training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingExamResultSectionId(
		long trainingExamResultSectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training exam result items.
	*
	* @return the number of training exam result items
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}