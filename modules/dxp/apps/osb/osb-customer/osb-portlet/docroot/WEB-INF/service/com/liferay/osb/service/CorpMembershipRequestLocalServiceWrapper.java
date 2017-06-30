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
 * This class is a wrapper for {@link CorpMembershipRequestLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpMembershipRequestLocalService
 * @generated
 */
public class CorpMembershipRequestLocalServiceWrapper
	implements CorpMembershipRequestLocalService,
		ServiceWrapper<CorpMembershipRequestLocalService> {
	public CorpMembershipRequestLocalServiceWrapper(
		CorpMembershipRequestLocalService corpMembershipRequestLocalService) {
		_corpMembershipRequestLocalService = corpMembershipRequestLocalService;
	}

	/**
	* Adds the corp membership request to the database. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequest the corp membership request
	* @return the corp membership request that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest addCorpMembershipRequest(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.addCorpMembershipRequest(corpMembershipRequest);
	}

	/**
	* Creates a new corp membership request with the primary key. Does not add the corp membership request to the database.
	*
	* @param corpMembershipRequestId the primary key for the new corp membership request
	* @return the new corp membership request
	*/
	public com.liferay.osb.model.CorpMembershipRequest createCorpMembershipRequest(
		long corpMembershipRequestId) {
		return _corpMembershipRequestLocalService.createCorpMembershipRequest(corpMembershipRequestId);
	}

	/**
	* Deletes the corp membership request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request that was removed
	* @throws PortalException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest deleteCorpMembershipRequest(
		long corpMembershipRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.deleteCorpMembershipRequest(corpMembershipRequestId);
	}

	/**
	* Deletes the corp membership request from the database. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequest the corp membership request
	* @return the corp membership request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest deleteCorpMembershipRequest(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.deleteCorpMembershipRequest(corpMembershipRequest);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _corpMembershipRequestLocalService.dynamicQuery();
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
		return _corpMembershipRequestLocalService.dynamicQuery(dynamicQuery);
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
		return _corpMembershipRequestLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _corpMembershipRequestLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _corpMembershipRequestLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.CorpMembershipRequest fetchCorpMembershipRequest(
		long corpMembershipRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.fetchCorpMembershipRequest(corpMembershipRequestId);
	}

	/**
	* Returns the corp membership request with the primary key.
	*
	* @param corpMembershipRequestId the primary key of the corp membership request
	* @return the corp membership request
	* @throws PortalException if a corp membership request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest getCorpMembershipRequest(
		long corpMembershipRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequest(corpMembershipRequestId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp membership requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp membership requests
	* @param end the upper bound of the range of corp membership requests (not inclusive)
	* @return the range of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> getCorpMembershipRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequests(start,
			end);
	}

	/**
	* Returns the number of corp membership requests.
	*
	* @return the number of corp membership requests
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpMembershipRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequestsCount();
	}

	/**
	* Updates the corp membership request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequest the corp membership request
	* @return the corp membership request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest updateCorpMembershipRequest(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.updateCorpMembershipRequest(corpMembershipRequest);
	}

	/**
	* Updates the corp membership request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpMembershipRequest the corp membership request
	* @param merge whether to merge the corp membership request with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp membership request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpMembershipRequest updateCorpMembershipRequest(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.updateCorpMembershipRequest(corpMembershipRequest,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpMembershipRequestLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpMembershipRequestLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpMembershipRequestLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.osb.model.CorpMembershipRequest addCorpMembershipRequest(
		long userId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.addCorpMembershipRequest(userId,
			corpEntryId);
	}

	public com.liferay.osb.model.CorpMembershipRequest fetchCorpMembershipRequest(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.fetchCorpMembershipRequest(key);
	}

	public com.liferay.osb.model.CorpMembershipRequest getCorpMembershipRequest(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequest(key);
	}

	public java.util.List<com.liferay.osb.model.CorpMembershipRequest> getCorpMembershipRequests(
		long corpEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequests(corpEntryId,
			statuses, start, end);
	}

	public int getCorpMembershipRequestsCount(long corpEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.getCorpMembershipRequestsCount(corpEntryId,
			statuses);
	}

	public boolean hasPendingRequest(long userId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.hasPendingRequest(userId,
			corpEntryId);
	}

	public com.liferay.osb.model.CorpMembershipRequest updateStatus(
		long corpMembershipRequestId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestLocalService.updateStatus(corpMembershipRequestId,
			status);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpMembershipRequestLocalService getWrappedCorpMembershipRequestLocalService() {
		return _corpMembershipRequestLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpMembershipRequestLocalService(
		CorpMembershipRequestLocalService corpMembershipRequestLocalService) {
		_corpMembershipRequestLocalService = corpMembershipRequestLocalService;
	}

	public CorpMembershipRequestLocalService getWrappedService() {
		return _corpMembershipRequestLocalService;
	}

	public void setWrappedService(
		CorpMembershipRequestLocalService corpMembershipRequestLocalService) {
		_corpMembershipRequestLocalService = corpMembershipRequestLocalService;
	}

	private CorpMembershipRequestLocalService _corpMembershipRequestLocalService;
}