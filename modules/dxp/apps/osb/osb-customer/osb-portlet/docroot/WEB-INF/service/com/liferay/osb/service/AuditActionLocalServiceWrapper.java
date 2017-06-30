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
 * This class is a wrapper for {@link AuditActionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AuditActionLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction addAuditAction(
		com.liferay.osb.model.AuditAction auditAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.addAuditAction(auditAction);
	}

	/**
	* Creates a new audit action with the primary key. Does not add the audit action to the database.
	*
	* @param auditActionId the primary key for the new audit action
	* @return the new audit action
	*/
	public com.liferay.osb.model.AuditAction createAuditAction(
		long auditActionId) {
		return _auditActionLocalService.createAuditAction(auditActionId);
	}

	/**
	* Deletes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action that was removed
	* @throws PortalException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction deleteAuditAction(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.deleteAuditAction(auditActionId);
	}

	/**
	* Deletes the audit action from the database. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction deleteAuditAction(
		com.liferay.osb.model.AuditAction auditAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.deleteAuditAction(auditAction);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditActionLocalService.dynamicQuery();
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery);
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _auditActionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _auditActionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AuditAction fetchAuditAction(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.fetchAuditAction(auditActionId);
	}

	/**
	* Returns the audit action with the primary key.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action
	* @throws PortalException if a audit action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction getAuditAction(long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.getAuditAction(auditActionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the audit actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit actions
	* @param end the upper bound of the range of audit actions (not inclusive)
	* @return the range of audit actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.getAuditActions(start, end);
	}

	/**
	* Returns the number of audit actions.
	*
	* @return the number of audit actions
	* @throws SystemException if a system exception occurred
	*/
	public int getAuditActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.getAuditActionsCount();
	}

	/**
	* Updates the audit action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction updateAuditAction(
		com.liferay.osb.model.AuditAction auditAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.updateAuditAction(auditAction);
	}

	/**
	* Updates the audit action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @param merge whether to merge the audit action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the audit action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditAction updateAuditAction(
		com.liferay.osb.model.AuditAction auditAction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.updateAuditAction(auditAction, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _auditActionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_auditActionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditActionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void deleteAuditActions(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		_auditActionLocalService.deleteAuditActions(modifiedDate);
	}

	public java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		java.util.Date modifiedDate, long classNameId, boolean mapping,
		int action) throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.getAuditActions(modifiedDate,
			classNameId, mapping, action);
	}

	public com.liferay.osb.model.AuditAction updateAuditAction(
		long classNameId, long classPK, long mappingClassPK, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditActionLocalService.updateAuditAction(classNameId, classPK,
			mappingClassPK, action);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AuditActionLocalService getWrappedAuditActionLocalService() {
		return _auditActionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAuditActionLocalService(
		AuditActionLocalService auditActionLocalService) {
		_auditActionLocalService = auditActionLocalService;
	}

	public AuditActionLocalService getWrappedService() {
		return _auditActionLocalService;
	}

	public void setWrappedService(
		AuditActionLocalService auditActionLocalService) {
		_auditActionLocalService = auditActionLocalService;
	}

	private AuditActionLocalService _auditActionLocalService;
}