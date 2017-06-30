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
 * The utility for the user profile history local service. This utility wraps {@link com.liferay.osb.service.impl.UserProfileHistoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileHistoryLocalService
 * @see com.liferay.osb.service.base.UserProfileHistoryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.UserProfileHistoryLocalServiceImpl
 * @generated
 */
public class UserProfileHistoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.UserProfileHistoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user profile history to the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistory the user profile history
	* @return the user profile history that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory addUserProfileHistory(
		com.liferay.osb.model.UserProfileHistory userProfileHistory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserProfileHistory(userProfileHistory);
	}

	/**
	* Creates a new user profile history with the primary key. Does not add the user profile history to the database.
	*
	* @param userProfileHistoryId the primary key for the new user profile history
	* @return the new user profile history
	*/
	public static com.liferay.osb.model.UserProfileHistory createUserProfileHistory(
		long userProfileHistoryId) {
		return getService().createUserProfileHistory(userProfileHistoryId);
	}

	/**
	* Deletes the user profile history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history that was removed
	* @throws PortalException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory deleteUserProfileHistory(
		long userProfileHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUserProfileHistory(userProfileHistoryId);
	}

	/**
	* Deletes the user profile history from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistory the user profile history
	* @return the user profile history that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory deleteUserProfileHistory(
		com.liferay.osb.model.UserProfileHistory userProfileHistory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUserProfileHistory(userProfileHistory);
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

	public static com.liferay.osb.model.UserProfileHistory fetchUserProfileHistory(
		long userProfileHistoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserProfileHistory(userProfileHistoryId);
	}

	/**
	* Returns the user profile history with the primary key.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history
	* @throws PortalException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory getUserProfileHistory(
		long userProfileHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserProfileHistory(userProfileHistoryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user profile histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @return the range of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> getUserProfileHistories(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserProfileHistories(start, end);
	}

	/**
	* Returns the number of user profile histories.
	*
	* @return the number of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserProfileHistoriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserProfileHistoriesCount();
	}

	/**
	* Updates the user profile history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistory the user profile history
	* @return the user profile history that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory updateUserProfileHistory(
		com.liferay.osb.model.UserProfileHistory userProfileHistory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserProfileHistory(userProfileHistory);
	}

	/**
	* Updates the user profile history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistory the user profile history
	* @param merge whether to merge the user profile history with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user profile history that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory updateUserProfileHistory(
		com.liferay.osb.model.UserProfileHistory userProfileHistory,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserProfileHistory(userProfileHistory, merge);
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

	public static com.liferay.osb.model.UserProfileHistory addUserProfileHistory(
		long userId, long classNameId, long classPK,
		java.lang.String emailAddress, java.lang.String firstName,
		java.lang.String lastName, java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addUserProfileHistory(userId, classNameId, classPK,
			emailAddress, firstName, lastName, legalEntityName);
	}

	public static java.util.List<com.liferay.osb.model.UserProfileHistory> getClassUserProfileHistories(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getClassUserProfileHistories(classNameId, classPK);
	}

	public static java.util.List<com.liferay.osb.model.UserProfileHistory> getUserUserProfileHistories(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserProfileHistories(userId, classNameId);
	}

	public static void clearService() {
		_service = null;
	}

	public static UserProfileHistoryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UserProfileHistoryLocalService.class.getName());

			if (invokableLocalService instanceof UserProfileHistoryLocalService) {
				_service = (UserProfileHistoryLocalService)invokableLocalService;
			}
			else {
				_service = new UserProfileHistoryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(UserProfileHistoryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(UserProfileHistoryLocalService service) {
	}

	private static UserProfileHistoryLocalService _service;
}