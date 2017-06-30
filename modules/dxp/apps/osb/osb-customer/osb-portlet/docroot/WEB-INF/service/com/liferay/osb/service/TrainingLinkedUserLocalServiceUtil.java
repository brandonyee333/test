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
 * The utility for the training linked user local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingLinkedUserLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLinkedUserLocalService
 * @see com.liferay.osb.service.base.TrainingLinkedUserLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingLinkedUserLocalServiceImpl
 * @generated
 */
public class TrainingLinkedUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingLinkedUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training linked user to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser addTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingLinkedUser(trainingLinkedUser);
	}

	/**
	* Creates a new training linked user with the primary key. Does not add the training linked user to the database.
	*
	* @param trainingLinkedUserId the primary key for the new training linked user
	* @return the new training linked user
	*/
	public static com.liferay.osb.model.TrainingLinkedUser createTrainingLinkedUser(
		long trainingLinkedUserId) {
		return getService().createTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Deletes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user that was removed
	* @throws PortalException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser deleteTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Deletes the training linked user from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser deleteTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingLinkedUser(trainingLinkedUser);
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

	public static com.liferay.osb.model.TrainingLinkedUser fetchTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingLinkedUser(trainingLinkedUserId);
	}

	/**
	* Returns the training linked user with the primary key.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user
	* @throws PortalException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser getTrainingLinkedUser(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingLinkedUser(trainingLinkedUserId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

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
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> getTrainingLinkedUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingLinkedUsers(start, end);
	}

	/**
	* Returns the number of training linked users.
	*
	* @return the number of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingLinkedUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingLinkedUsersCount();
	}

	/**
	* Updates the training linked user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @return the training linked user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser updateTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingLinkedUser(trainingLinkedUser);
	}

	/**
	* Updates the training linked user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUser the training linked user
	* @param merge whether to merge the training linked user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training linked user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser updateTrainingLinkedUser(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingLinkedUser(trainingLinkedUser, merge);
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

	public static void addTrainingLinkedUsers(long primaryUserId,
		long oldPrimaryUserId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTrainingLinkedUsers(primaryUserId, oldPrimaryUserId, userIds);
	}

	public static com.liferay.osb.model.TrainingLinkedUser fetchUserTrainingLinkedUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserTrainingLinkedUser(userId);
	}

	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> getTrainingLinkedUsers(
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingLinkedUsers(primaryUserId);
	}

	public static boolean isPrimaryTrainingLinkedUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().isPrimaryTrainingLinkedUser(userId);
	}

	public static void unsetTrainingLinkedUsers(long primaryUserId,
		long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsetTrainingLinkedUsers(primaryUserId, userIds);
	}

	public static void updateTrainingLinkedUsers(long primaryUserId,
		long oldPrimaryUserId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateTrainingLinkedUsers(primaryUserId, oldPrimaryUserId, userIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingLinkedUserLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingLinkedUserLocalService.class.getName());

			if (invokableLocalService instanceof TrainingLinkedUserLocalService) {
				_service = (TrainingLinkedUserLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingLinkedUserLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingLinkedUserLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingLinkedUserLocalService service) {
	}

	private static TrainingLinkedUserLocalService _service;
}