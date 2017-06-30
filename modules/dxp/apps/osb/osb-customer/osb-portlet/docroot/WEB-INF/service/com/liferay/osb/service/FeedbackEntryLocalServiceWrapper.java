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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedbackEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedbackEntryLocalService
 * @generated
 */
public class FeedbackEntryLocalServiceWrapper
	implements FeedbackEntryLocalService,
		ServiceWrapper<FeedbackEntryLocalService> {
	public FeedbackEntryLocalServiceWrapper(
		FeedbackEntryLocalService feedbackEntryLocalService) {
		_feedbackEntryLocalService = feedbackEntryLocalService;
	}

	/**
	* Adds the feedback entry to the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry addFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.addFeedbackEntry(feedbackEntry);
	}

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	public com.liferay.osb.model.FeedbackEntry createFeedbackEntry(
		long feedbackEntryId) {
		return _feedbackEntryLocalService.createFeedbackEntry(feedbackEntryId);
	}

	/**
	* Deletes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws PortalException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.deleteFeedbackEntry(feedbackEntryId);
	}

	/**
	* Deletes the feedback entry from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.deleteFeedbackEntry(feedbackEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _feedbackEntryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.FeedbackEntry fetchFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.fetchFeedbackEntry(feedbackEntryId);
	}

	/**
	* Returns the feedback entry with the primary key.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws PortalException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.getFeedbackEntry(feedbackEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.getPersistedModel(primaryKeyObj);
	}

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
	public java.util.List<com.liferay.osb.model.FeedbackEntry> getFeedbackEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.getFeedbackEntries(start, end);
	}

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public int getFeedbackEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.getFeedbackEntriesCount();
	}

	/**
	* Updates the feedback entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.updateFeedbackEntry(feedbackEntry);
	}

	/**
	* Updates the feedback entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @param merge whether to merge the feedback entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the feedback entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.updateFeedbackEntry(feedbackEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _feedbackEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_feedbackEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _feedbackEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.FeedbackEntry addFeedbackEntry(long userId,
		long classNameId, long classPK, int satisfied, java.lang.String pageURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.addFeedbackEntry(userId, classNameId,
			classPK, satisfied, pageURL);
	}

	public com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		long feedbackEntryId, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntryLocalService.updateFeedbackEntry(feedbackEntryId,
			comments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public FeedbackEntryLocalService getWrappedFeedbackEntryLocalService() {
		return _feedbackEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedFeedbackEntryLocalService(
		FeedbackEntryLocalService feedbackEntryLocalService) {
		_feedbackEntryLocalService = feedbackEntryLocalService;
	}

	public FeedbackEntryLocalService getWrappedService() {
		return _feedbackEntryLocalService;
	}

	public void setWrappedService(
		FeedbackEntryLocalService feedbackEntryLocalService) {
		_feedbackEntryLocalService = feedbackEntryLocalService;
	}

	private FeedbackEntryLocalService _feedbackEntryLocalService;
}