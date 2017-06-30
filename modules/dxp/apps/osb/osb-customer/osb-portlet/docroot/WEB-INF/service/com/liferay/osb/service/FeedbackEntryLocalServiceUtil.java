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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the feedback entry local service. This utility wraps {@link com.liferay.osb.service.impl.FeedbackEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntryLocalService
 * @see com.liferay.osb.service.base.FeedbackEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.FeedbackEntryLocalServiceImpl
 * @generated
 */
public class FeedbackEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.FeedbackEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the feedback entry to the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry addFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFeedbackEntry(feedbackEntry);
	}

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	public static com.liferay.osb.model.FeedbackEntry createFeedbackEntry(
		long feedbackEntryId) {
		return getService().createFeedbackEntry(feedbackEntryId);
	}

	/**
	* Deletes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws PortalException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFeedbackEntry(feedbackEntryId);
	}

	/**
	* Deletes the feedback entry from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry deleteFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFeedbackEntry(feedbackEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.FeedbackEntry fetchFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFeedbackEntry(feedbackEntryId);
	}

	/**
	* Returns the feedback entry with the primary key.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws PortalException if a feedback entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFeedbackEntry(feedbackEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.FeedbackEntry> getFeedbackEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFeedbackEntries(start, end);
	}

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getFeedbackEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFeedbackEntriesCount();
	}

	/**
	* Updates the feedback entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @return the feedback entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFeedbackEntry(feedbackEntry);
	}

	/**
	* Updates the feedback entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntry the feedback entry
	* @param merge whether to merge the feedback entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the feedback entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		com.liferay.osb.model.FeedbackEntry feedbackEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFeedbackEntry(feedbackEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.FeedbackEntry addFeedbackEntry(
		long userId, long classNameId, long classPK, int satisfied,
		java.lang.String pageURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addFeedbackEntry(userId, classNameId, classPK, satisfied,
			pageURL);
	}

	public static com.liferay.osb.model.FeedbackEntry updateFeedbackEntry(
		long feedbackEntryId, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFeedbackEntry(feedbackEntryId, comments);
	}

	public static void clearService() {
		_service = null;
	}

	public static FeedbackEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					FeedbackEntryLocalService.class.getName());

			if (invokableLocalService instanceof FeedbackEntryLocalService) {
				_service = (FeedbackEntryLocalService)invokableLocalService;
			}
			else {
				_service = new FeedbackEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(FeedbackEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(FeedbackEntryLocalService service) {
	}

	private static FeedbackEntryLocalService _service;
}