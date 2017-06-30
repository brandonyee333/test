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
 * This class is a wrapper for {@link TrainingCustomerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCustomerLocalService
 * @generated
 */
public class TrainingCustomerLocalServiceWrapper
	implements TrainingCustomerLocalService,
		ServiceWrapper<TrainingCustomerLocalService> {
	public TrainingCustomerLocalServiceWrapper(
		TrainingCustomerLocalService trainingCustomerLocalService) {
		_trainingCustomerLocalService = trainingCustomerLocalService;
	}

	/**
	* Adds the training customer to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer addTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.addTrainingCustomer(trainingCustomer);
	}

	/**
	* Creates a new training customer with the primary key. Does not add the training customer to the database.
	*
	* @param trainingCustomerId the primary key for the new training customer
	* @return the new training customer
	*/
	public com.liferay.osb.model.TrainingCustomer createTrainingCustomer(
		long trainingCustomerId) {
		return _trainingCustomerLocalService.createTrainingCustomer(trainingCustomerId);
	}

	/**
	* Deletes the training customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer that was removed
	* @throws PortalException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer deleteTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.deleteTrainingCustomer(trainingCustomerId);
	}

	/**
	* Deletes the training customer from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer deleteTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.deleteTrainingCustomer(trainingCustomer);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _trainingCustomerLocalService.dynamicQuery();
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
		return _trainingCustomerLocalService.dynamicQuery(dynamicQuery);
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
		return _trainingCustomerLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _trainingCustomerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _trainingCustomerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.fetchTrainingCustomer(trainingCustomerId);
	}

	/**
	* Returns the training customer with the primary key.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer
	* @throws PortalException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer getTrainingCustomer(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomer(trainingCustomerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomers(start, end);
	}

	/**
	* Returns the number of training customers.
	*
	* @return the number of training customers
	* @throws SystemException if a system exception occurred
	*/
	public int getTrainingCustomersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomersCount();
	}

	/**
	* Updates the training customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @return the training customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateTrainingCustomer(trainingCustomer);
	}

	/**
	* Updates the training customer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomer the training customer
	* @param merge whether to merge the training customer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training customer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		com.liferay.osb.model.TrainingCustomer trainingCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateTrainingCustomer(trainingCustomer,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingCustomerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingCustomerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingCustomerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.TrainingCustomer addTrainingCustomer(
		long userId, long classNameId, long classPK, long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.addTrainingCustomer(userId,
			classNameId, classPK, userProfileId);
	}

	public void addTrainingCustomers(long[] userIds, long classNameId,
		long classPK,
		java.util.Map<java.lang.Long, java.lang.Long> userProfileIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomerLocalService.addTrainingCustomers(userIds,
			classNameId, classPK, userProfileIds);
	}

	public void deleteTrainingCustomers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomerLocalService.deleteTrainingCustomers(userId);
	}

	public void deleteTrainingCustomers(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomerLocalService.deleteTrainingCustomers(classNameId,
			classPK);
	}

	public void deleteTrainingCustomers(long[] userIds, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomerLocalService.deleteTrainingCustomers(userIds,
			classNameId, classPK);
	}

	public com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.fetchTrainingCustomer(userId,
			classNameId, classPK);
	}

	public com.liferay.osb.model.TrainingCustomer fetchTrainingCustomer(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.fetchTrainingCustomer(userId, obc);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> getClassTrainingCustomers(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getClassTrainingCustomers(classNameId,
			classPK);
	}

	public com.liferay.osb.model.TrainingCustomer getTrainingCustomer(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomer(userId,
			classNameId, classPK);
	}

	public java.util.Map<java.lang.Long, java.lang.String> getTrainingCustomerBadgeNames(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomerBadgeNames(userId,
			classNameId);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers(
		long classNameId, long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomers(classNameId,
			classPK, statuses);
	}

	public long getTrainingCustomersCount(long classNameId, long classPK,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getTrainingCustomersCount(classNameId,
			classPK, statuses);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> getUserTrainingCustomers(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getUserTrainingCustomers(userId,
			classNameId);
	}

	public int getUserTrainingCustomersCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.getUserTrainingCustomersCount(userId);
	}

	public boolean hasTrainingCustomer(long userId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.hasTrainingCustomer(userId,
			classNameId, classPK);
	}

	public boolean hasTrainingCustomerBadgeNames(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.hasTrainingCustomerBadgeNames(userId);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.search(keywords, start, end);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> search(
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String emailAddress, boolean andSearch, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.search(firstName, lastName,
			emailAddress, andSearch, start, end);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.searchCount(keywords);
	}

	public int searchCount(java.lang.String firstName,
		java.lang.String lastName, java.lang.String emailAddress,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.searchCount(firstName, lastName,
			emailAddress, andSearch);
	}

	public void updateComments(long trainingCustomerId,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomerLocalService.updateComments(trainingCustomerId,
			comments);
	}

	public com.liferay.osb.model.TrainingCustomer updateStatus(
		long trainingCustomerId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateStatus(trainingCustomerId,
			status);
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> updateStatuses(
		long[] trainingCustomerIds, long trainingEventId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateStatuses(trainingCustomerIds,
			trainingEventId, status);
	}

	public com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		long trainingCustomerId, long userId, long classNameId, long classPK,
		long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateTrainingCustomer(trainingCustomerId,
			userId, classNameId, classPK, userProfileId);
	}

	public com.liferay.osb.model.TrainingCustomer updateTrainingCustomer(
		long creatorUserId, long trainingCustomerId, long classNameId,
		long classPK, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomerLocalService.updateTrainingCustomer(creatorUserId,
			trainingCustomerId, classNameId, classPK, emailAddress, firstName,
			lastName, legalEntityName);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingCustomerLocalService getWrappedTrainingCustomerLocalService() {
		return _trainingCustomerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingCustomerLocalService(
		TrainingCustomerLocalService trainingCustomerLocalService) {
		_trainingCustomerLocalService = trainingCustomerLocalService;
	}

	public TrainingCustomerLocalService getWrappedService() {
		return _trainingCustomerLocalService;
	}

	public void setWrappedService(
		TrainingCustomerLocalService trainingCustomerLocalService) {
		_trainingCustomerLocalService = trainingCustomerLocalService;
	}

	private TrainingCustomerLocalService _trainingCustomerLocalService;
}