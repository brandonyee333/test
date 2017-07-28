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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FeedbackEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntryLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry addFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry) {
		return _feedbackEntryLocalService.addFeedbackEntry(feedbackEntry);
	}

	@Override
	public com.liferay.osb.model.FeedbackEntry addFeedbackEntry(long userId,
		long classNameId, long classPK, int satisfied, java.lang.String pageURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.addFeedbackEntry(userId, classNameId,
			classPK, satisfied, pageURL);
	}

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry createFeedbackEntry(
		long feedbackEntryId) {
		return _feedbackEntryLocalService.createFeedbackEntry(feedbackEntryId);
	}

	/**
	* Deletes the feedback entry from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was removed
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry) {
		return _feedbackEntryLocalService.deleteFeedbackEntry(feedbackEntry);
	}

	/**
	* Deletes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws PortalException if a feedback entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.deleteFeedbackEntry(feedbackEntryId);
	}

	@Override
	public com.liferay.osb.model.FeedbackEntry fetchFeedbackEntry(
		long feedbackEntryId) {
		return _feedbackEntryLocalService.fetchFeedbackEntry(feedbackEntryId);
	}

	/**
	* Returns the feedback entry with the primary key.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws PortalException if a feedback entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.getFeedbackEntry(feedbackEntryId);
	}

	/**
	* Updates the feedback entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was updated
	*/
	@Override
	public com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry) {
		return _feedbackEntryLocalService.updateFeedbackEntry(feedbackEntry);
	}

	@Override
	public com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		long feedbackEntryId, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.updateFeedbackEntry(feedbackEntryId,
			comments);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _feedbackEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _feedbackEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _feedbackEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _feedbackEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	*/
	@Override
	public int getFeedbackEntriesCount() {
		return _feedbackEntryLocalService.getFeedbackEntriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _feedbackEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _feedbackEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _feedbackEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @return the range of feedback entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.FeedbackEntry> getFeedbackEntries(
		int start, int end) {
		return _feedbackEntryLocalService.getFeedbackEntries(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _feedbackEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _feedbackEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public FeedbackEntryLocalService getWrappedService() {
		return _feedbackEntryLocalService;
	}

	@Override
	public void setWrappedService(
		FeedbackEntryLocalService feedbackEntryLocalService) {
		_feedbackEntryLocalService = feedbackEntryLocalService;
	}

	private FeedbackEntryLocalService _feedbackEntryLocalService;
}