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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchFeedbackEntryException;
import com.liferay.osb.model.FeedbackEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the feedback entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.FeedbackEntryPersistenceImpl
 * @see FeedbackEntryUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(FeedbackEntry feedbackEntry);

	/**
	* Caches the feedback entries in the entity cache if it is enabled.
	*
	* @param feedbackEntries the feedback entries
	*/
	public void cacheResult(java.util.List<FeedbackEntry> feedbackEntries);

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	public FeedbackEntry create(long feedbackEntryId);

	/**
	* Removes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	*/
	public FeedbackEntry remove(long feedbackEntryId)
		throws NoSuchFeedbackEntryException;

	public FeedbackEntry updateImpl(FeedbackEntry feedbackEntry);

	/**
	* Returns the feedback entry with the primary key or throws a {@link NoSuchFeedbackEntryException} if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	*/
	public FeedbackEntry findByPrimaryKey(long feedbackEntryId)
		throws NoSuchFeedbackEntryException;

	/**
	* Returns the feedback entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry, or <code>null</code> if a feedback entry with the primary key could not be found
	*/
	public FeedbackEntry fetchByPrimaryKey(long feedbackEntryId);

	@Override
	public java.util.Map<java.io.Serializable, FeedbackEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the feedback entries.
	*
	* @return the feedback entries
	*/
	public java.util.List<FeedbackEntry> findAll();

	/**
	* Returns a range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @return the range of feedback entries
	*/
	public java.util.List<FeedbackEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of feedback entries
	*/
	public java.util.List<FeedbackEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FeedbackEntry> orderByComparator);

	/**
	* Returns an ordered range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of feedback entries
	*/
	public java.util.List<FeedbackEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FeedbackEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the feedback entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	*/
	public int countAll();
}