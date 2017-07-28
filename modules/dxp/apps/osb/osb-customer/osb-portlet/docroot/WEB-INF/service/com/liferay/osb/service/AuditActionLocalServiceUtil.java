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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for AuditAction. This utility wraps
 * {@link com.liferay.osb.service.impl.AuditActionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuditActionLocalService
 * @see com.liferay.osb.service.base.AuditActionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AuditActionLocalServiceImpl
 * @generated
 */
@ProviderType
public class AuditActionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AuditActionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the audit action to the database. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was added
	*/
	public static com.liferay.osb.model.AuditAction addAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return getService().addAuditAction(auditAction);
	}

	/**
	* Creates a new audit action with the primary key. Does not add the audit action to the database.
	*
	* @param auditActionId the primary key for the new audit action
	* @return the new audit action
	*/
	public static com.liferay.osb.model.AuditAction createAuditAction(
		long auditActionId) {
		return getService().createAuditAction(auditActionId);
	}

	/**
	* Deletes the audit action from the database. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was removed
	*/
	public static com.liferay.osb.model.AuditAction deleteAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return getService().deleteAuditAction(auditAction);
	}

	/**
	* Deletes the audit action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action that was removed
	* @throws PortalException if a audit action with the primary key could not be found
	*/
	public static com.liferay.osb.model.AuditAction deleteAuditAction(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAuditAction(auditActionId);
	}

	public static com.liferay.osb.model.AuditAction fetchAuditAction(
		long auditActionId) {
		return getService().fetchAuditAction(auditActionId);
	}

	/**
	* Returns the audit action with the primary key.
	*
	* @param auditActionId the primary key of the audit action
	* @return the audit action
	* @throws PortalException if a audit action with the primary key could not be found
	*/
	public static com.liferay.osb.model.AuditAction getAuditAction(
		long auditActionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAuditAction(auditActionId);
	}

	/**
	* Updates the audit action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditAction the audit action
	* @return the audit action that was updated
	*/
	public static com.liferay.osb.model.AuditAction updateAuditAction(
		com.liferay.osb.model.AuditAction auditAction) {
		return getService().updateAuditAction(auditAction);
	}

	public static com.liferay.osb.model.AuditAction updateAuditAction(
		long classNameId, long classPK, long mappingClassPK, int action) {
		return getService()
				   .updateAuditAction(classNameId, classPK, mappingClassPK,
			action);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of audit actions.
	*
	* @return the number of audit actions
	*/
	public static int getAuditActionsCount() {
		return getService().getAuditActionsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		int start, int end) {
		return getService().getAuditActions(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AuditAction> getAuditActions(
		java.util.Date modifiedDate, long classNameId, boolean mapping,
		int action) {
		return getService()
				   .getAuditActions(modifiedDate, classNameId, mapping, action);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteAuditActions(java.util.Date modifiedDate) {
		getService().deleteAuditActions(modifiedDate);
	}

	public static void clearService() {
		_service = null;
	}

	public static AuditActionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AuditActionLocalService.class.getName());

			if (invokableLocalService instanceof AuditActionLocalService) {
				_service = (AuditActionLocalService)invokableLocalService;
			}
			else {
				_service = new AuditActionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AuditActionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AuditActionLocalService _service;
}