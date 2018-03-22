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
 * Provides a wrapper for {@link SecurityPatchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchLocalService
 * @generated
 */
@ProviderType
public class SecurityPatchLocalServiceWrapper
	implements SecurityPatchLocalService,
		ServiceWrapper<SecurityPatchLocalService> {
	public SecurityPatchLocalServiceWrapper(
		SecurityPatchLocalService securityPatchLocalService) {
		_securityPatchLocalService = securityPatchLocalService;
	}

	/**
	* Adds the security patch to the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was added
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch addSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch) {
		return _securityPatchLocalService.addSecurityPatch(securityPatch);
	}

	@Override
	public com.liferay.osb.model.SecurityPatch addSecurityPatch(long userId,
		long accountEntryId, long ticketAttachmentId,
		java.lang.String portletId, int envLFR, java.lang.String name,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.addSecurityPatch(userId,
			accountEntryId, ticketAttachmentId, portletId, envLFR, name,
			fileName);
	}

	@Override
	public com.liferay.osb.model.SecurityPatch addSecurityPatch(long userId,
		long ticketAttachmentId, java.lang.String portletId, int envLFR,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.addSecurityPatch(userId,
			ticketAttachmentId, portletId, envLFR, fileName);
	}

	/**
	* Creates a new security patch with the primary key. Does not add the security patch to the database.
	*
	* @param securityPatchId the primary key for the new security patch
	* @return the new security patch
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch createSecurityPatch(
		long securityPatchId) {
		return _securityPatchLocalService.createSecurityPatch(securityPatchId);
	}

	/**
	* Deletes the security patch from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was removed
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch deleteSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch) {
		return _securityPatchLocalService.deleteSecurityPatch(securityPatch);
	}

	/**
	* Deletes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch that was removed
	* @throws PortalException if a security patch with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch deleteSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.deleteSecurityPatch(securityPatchId);
	}

	@Override
	public com.liferay.osb.model.SecurityPatch fetchSecurityPatch(
		long securityPatchId) {
		return _securityPatchLocalService.fetchSecurityPatch(securityPatchId);
	}

	/**
	* Returns the security patch with the primary key.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch
	* @throws PortalException if a security patch with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch getSecurityPatch(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.getSecurityPatch(securityPatchId);
	}

	/**
	* Updates the security patch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param securityPatch the security patch
	* @return the security patch that was updated
	*/
	@Override
	public com.liferay.osb.model.SecurityPatch updateSecurityPatch(
		com.liferay.osb.model.SecurityPatch securityPatch) {
		return _securityPatchLocalService.updateSecurityPatch(securityPatch);
	}

	@Override
	public com.liferay.osb.model.SecurityPatch updateSecurityPatch(
		long securityPatchId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.updateSecurityPatch(securityPatchId,
			name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _securityPatchLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _securityPatchLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _securityPatchLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _securityPatchLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of security patchs.
	*
	* @return the number of security patchs
	*/
	@Override
	public int getSecurityPatchsCount() {
		return _securityPatchLocalService.getSecurityPatchsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _securityPatchLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _securityPatchLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String getSecurityPatchName(int envLFR,
		com.liferay.osb.model.TicketAttachment ticketAttachment) {
		return _securityPatchLocalService.getSecurityPatchName(envLFR,
			ticketAttachment);
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
		return _securityPatchLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _securityPatchLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _securityPatchLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SecurityPatch> getSecurityPatches(
		long accountEntryId, java.lang.String portletId) {
		return _securityPatchLocalService.getSecurityPatches(accountEntryId,
			portletId);
	}

	/**
	* Returns a range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of security patchs
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SecurityPatch> getSecurityPatchs(
		int start, int end) {
		return _securityPatchLocalService.getSecurityPatchs(start, end);
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
		return _securityPatchLocalService.dynamicQueryCount(dynamicQuery);
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
		return _securityPatchLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteSecurityPatches(java.lang.String portletId) {
		_securityPatchLocalService.deleteSecurityPatches(portletId);
	}

	@Override
	public SecurityPatchLocalService getWrappedService() {
		return _securityPatchLocalService;
	}

	@Override
	public void setWrappedService(
		SecurityPatchLocalService securityPatchLocalService) {
		_securityPatchLocalService = securityPatchLocalService;
	}

	private SecurityPatchLocalService _securityPatchLocalService;
}