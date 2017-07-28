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
 * Provides a wrapper for {@link AuditActionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionLocalService
 * @generated
 */
@ProviderType
public class AuditActionLocalServiceWrapper implements AuditActionLocalService,
	ServiceWrapper<AuditActionLocalService> {
	public AuditActionLocalServiceWrapper(
		AuditActionLocalService auditActionLocalService) {
		_auditActionLocalService = auditActionLocalService;
	}

	/**
	* Adds the audit action to the database. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was added
	*/
	@Override
	public com.liferay.osb.model.AuditAction addAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return _auditActionLocalService.addAuditAction(auditAction);
	}

	/**
	* Creates a new audit action with the primary key. Does not add the audit action to the database.
	*
	* @param auditActionId the primary key for the new audit action
	* @return the new audit action
	*/
	@Override
	public com.liferay.osb.model.AuditAction createAuditAction(
		long auditActionId) {
		return _auditActionLocalService.createAuditAction(auditActionId);
	}

	/**
	* Deletes the audit action from the database. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was removed
	*/
	@Override
	public com.liferay.osb.model.AuditAction deleteAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return _auditActionLocalService.deleteAuditAction(auditAction);
	}

	/**
	* Deletes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action that was removed
	* @throws PortalException if a audit action with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AuditAction deleteAuditAction(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditActionLocalService.deleteAuditAction(auditActionId);
	}

	@Override
	public com.liferay.osb.model.AuditAction fetchAuditAction(
		long auditActionId) {
		return _auditActionLocalService.fetchAuditAction(auditActionId);
	}

	/**
	* Returns the audit action with the primary key.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action
	* @throws PortalException if a audit action with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AuditAction getAuditAction(long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditActionLocalService.getAuditAction(auditActionId);
	}

	/**
	* Updates the audit action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was updated
	*/
	@Override
	public com.liferay.osb.model.AuditAction updateAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return _auditActionLocalService.updateAuditAction(auditAction);
	}

	@Override
	public com.liferay.osb.model.AuditAction updateAuditAction(
		long classNameId, long classPK, long mappingClassPK, int action) {
		return _auditActionLocalService.updateAuditAction(classNameId, classPK,
			mappingClassPK, action);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _auditActionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditActionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _auditActionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditActionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditActionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of audit actions.
	*
	* @return the number of audit actions
	*/
	@Override
	public int getAuditActionsCount() {
		return _auditActionLocalService.getAuditActionsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditActionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _auditActionLocalService.getOSGiServiceIdentifier();
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of audit actions
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		int start, int end) {
		return _auditActionLocalService.getAuditActions(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		java.util.Date modifiedDate, long classNameId, boolean mapping,
		int action) {
		return _auditActionLocalService.getAuditActions(modifiedDate,
			classNameId, mapping, action);
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
		return _auditActionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _auditActionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteAuditActions(java.util.Date modifiedDate) {
		_auditActionLocalService.deleteAuditActions(modifiedDate);
	}

	@Override
	public AuditActionLocalService getWrappedService() {
		return _auditActionLocalService;
	}

	@Override
	public void setWrappedService(
		AuditActionLocalService auditActionLocalService) {
		_auditActionLocalService = auditActionLocalService;
	}

	private AuditActionLocalService _auditActionLocalService;
}