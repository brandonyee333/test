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

import com.liferay.osb.model.FeedbackEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the feedback entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntryPersistenceImpl
 * @see FeedbackEntryUtil
 * @generated
 */
public interface FeedbackEntryPersistence extends BasePersistence<FeedbackEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FeedbackEntryUtil} to access the feedback entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the feedback entry in the entity cache if it is enabled.
	*
	* @param feedbackEntry the feedback entry
	*/
	public void cacheResult(com.liferay.osb.model.FeedbackEntry feedbackEntry);

	/**
	* Caches the feedback entries in the entity cache if it is enabled.
	*
	* @param feedbackEntries the feedback entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.FeedbackEntry> feedbackEntries);

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	public com.liferay.osb.model.FeedbackEntry create(long feedbackEntryId);

	/**
	* Removes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws com.liferay.osb.NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry remove(long feedbackEntryId)
		throws com.liferay.osb.NoSuchFeedbackEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.FeedbackEntry updateImpl(
		com.liferay.osb.model.FeedbackEntry feedbackEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the feedback entry with the primary key or throws a {@link com.liferay.osb.NoSuchFeedbackEntryException} if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws com.liferay.osb.NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry findByPrimaryKey(
		long feedbackEntryId)
		throws com.liferay.osb.NoSuchFeedbackEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the feedback entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry, or <code>null</code> if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry fetchByPrimaryKey(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the feedback entries.
	*
	* @return the feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.FeedbackEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @return the range of feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.FeedbackEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.FeedbackEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the feedback entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}