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
 * The utility for the training customer local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingCustomerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCustomerLocalService
 * @see com.liferay.osb.service.base.TrainingCustomerLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingCustomerLocalServiceImpl
 * @generated
 */
public class TrainingCustomerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingCustomerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training customer to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer addTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingCustomer(trainingCustomer);
	}

	/**
	* Creates a new training customer with the primary key. Does not add the training customer to the database.
	*
	* @param trainingCustomerId the primary key for the new training customer
	* @return the new training customer
	*/
	public static com.liferay.osb.model.TrainingCustomer createTrainingCustomer(
		long trainingCustomerId) {
		return getService().createTrainingCustomer(trainingCustomerId);
	}

	/**
	* Deletes the training customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer that was removed
	* @throws PortalException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer deleteTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCustomer(trainingCustomerId);
	}

	/**
	* Deletes the training customer from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer deleteTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCustomer(trainingCustomer);
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

	public static com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingCustomer(trainingCustomerId);
	}

	/**
	* Returns the training customer with the primary key.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer
	* @throws PortalException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer getTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomer(trainingCustomerId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomers(start, end);
	}

	/**
	* Returns the number of training customers.
	*
	* @return the number of training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingCustomersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomersCount();
	}

	/**
	* Updates the training customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCustomer(trainingCustomer);
	}

	/**
	* Updates the training customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @param merge whether to merge the training customer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCustomer(trainingCustomer, merge);
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

	public static com.liferay.osb.model.TrainingCustomer addTrainingCustomer(
		long userId, long classNameId, long classPK, long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTrainingCustomer(userId, classNameId, classPK,
			userProfileId);
	}

	public static void addTrainingCustomers(long[] userIds, long classNameId,
		long classPK,
		java.util.Map<java.lang.Long, java.lang.Long> userProfileIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTrainingCustomers(userIds, classNameId, classPK, userProfileIds);
	}

	public static void deleteTrainingCustomers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTrainingCustomers(userId);
	}

	public static void deleteTrainingCustomers(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTrainingCustomers(classNameId, classPK);
	}

	public static void deleteTrainingCustomers(long[] userIds,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTrainingCustomers(userIds, classNameId, classPK);
	}

	public static com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingCustomer(userId, classNameId, classPK);
	}

	public static com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingCustomer(userId, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> getClassTrainingCustomers(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getClassTrainingCustomers(classNameId, classPK);
	}

	public static com.liferay.osb.model.TrainingCustomer getTrainingCustomer(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomer(userId, classNameId, classPK);
	}

	public static java.util.Map<java.lang.Long, java.lang.String> getTrainingCustomerBadgeNames(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomerBadgeNames(userId, classNameId);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers(
		long classNameId, long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCustomers(classNameId, classPK, statuses);
	}

	public static long getTrainingCustomersCount(long classNameId,
		long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTrainingCustomersCount(classNameId, classPK, statuses);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> getUserTrainingCustomers(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserTrainingCustomers(userId, classNameId);
	}

	public static int getUserTrainingCustomersCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserTrainingCustomersCount(userId);
	}

	public static boolean hasTrainingCustomer(long userId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTrainingCustomer(userId, classNameId, classPK);
	}

	public static boolean hasTrainingCustomerBadgeNames(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTrainingCustomerBadgeNames(userId);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, start, end);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> search(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, boolean andSearch, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(firstName, lastName, emailAddress, andSearch, start,
			end);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(firstName, lastName, emailAddress, andSearch);
	}

	public static void updateComments(long trainingCustomerId,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateComments(trainingCustomerId, comments);
	}

	public static com.liferay.osb.model.TrainingCustomer updateStatus(
		long trainingCustomerId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(trainingCustomerId, status);
	}

	public static java.util.List<com.liferay.osb.model.TrainingCustomer> updateStatuses(
		long[] trainingCustomerIds, long trainingEventId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatuses(trainingCustomerIds, trainingEventId, status);
	}

	public static com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		long trainingCustomerId, long userId, long classNameId, long classPK,
		long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingCustomer(trainingCustomerId, userId,
			classNameId, classPK, userProfileId);
	}

	public static com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		long creatorUserId, long trainingCustomerId, long classNameId,
		long classPK, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingCustomer(creatorUserId, trainingCustomerId,
			classNameId, classPK, emailAddress, firstName, lastName,
			legalEntityName);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingCustomerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingCustomerLocalService.class.getName());

			if (invokableLocalService instanceof TrainingCustomerLocalService) {
				_service = (TrainingCustomerLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingCustomerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingCustomerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingCustomerLocalService service) {
	}

	private static TrainingCustomerLocalService _service;
}