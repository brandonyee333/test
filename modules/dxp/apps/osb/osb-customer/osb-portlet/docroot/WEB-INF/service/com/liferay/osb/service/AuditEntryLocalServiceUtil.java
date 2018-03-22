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
 * Provides the local service utility for AuditEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.AuditEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalService
 * @see com.liferay.osb.service.base.AuditEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AuditEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class AuditEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AuditEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was added
	*/
	public static com.liferay.osb.model.AuditEntry addAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return getService().addAuditEntry(auditEntry);
	}

	public static com.liferay.osb.model.AuditEntry addAuditEntry(long userId,
		java.lang.String userName, java.util.Date createDate, long classNameId,
		long classPK, long auditSetId, long fieldClassNameId,
		long fieldClassPK, int action, int field, int visibility,
		java.lang.String oldLabel, java.lang.String oldValue,
		java.lang.String newLabel, java.lang.String newValue)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAuditEntry(userId, userName, createDate, classNameId,
			classPK, auditSetId, fieldClassNameId, fieldClassPK, action, field,
			visibility, oldLabel, oldValue, newLabel, newValue);
	}

	public static com.liferay.osb.model.AuditEntry addAuditEntry(long userId,
		java.lang.String userName, java.util.Date createDate, long classNameId,
		long classPK, long auditSetId, long fieldClassNameId,
		long fieldClassPK, int action, int field, int visibility,
		java.lang.String oldLabel, java.lang.String oldValue,
		java.lang.String newLabel, java.lang.String newValue, boolean i18n,
		boolean trackChange)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAuditEntry(userId, userName, createDate, classNameId,
			classPK, auditSetId, fieldClassNameId, fieldClassPK, action, field,
			visibility, oldLabel, oldValue, newLabel, newValue, i18n,
			trackChange);
	}

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditEntryId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public static com.liferay.osb.model.AuditEntry createAuditEntry(
		long auditEntryId) {
		return getService().createAuditEntry(auditEntryId);
	}

	/**
	* Deletes the audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was removed
	*/
	public static com.liferay.osb.model.AuditEntry deleteAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return getService().deleteAuditEntry(auditEntry);
	}

	/**
	* Deletes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.AuditEntry deleteAuditEntry(
		long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAuditEntry(auditEntryId);
	}

	public static com.liferay.osb.model.AuditEntry fetchAuditEntry(
		long auditEntryId) {
		return getService().fetchAuditEntry(auditEntryId);
	}

	/**
	* Returns the audit entry with the primary key.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.AuditEntry getAuditEntry(
		long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAuditEntry(auditEntryId);
	}

	public static com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long classNameId, long classPK, int field, int action) {
		return getService()
				   .getLastAuditEntry(classNameId, classPK, field, action);
	}

	public static com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long fieldClassNameId, long fieldClassPK, int field) {
		return getService()
				   .getLastAuditEntry(fieldClassNameId, fieldClassPK, field);
	}

	/**
	* Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was updated
	*/
	public static com.liferay.osb.model.AuditEntry updateAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return getService().updateAuditEntry(auditEntry);
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
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	public static int getAuditEntriesCount() {
		return getService().getAuditEntriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	*/
	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		int start, int end) {
		return getService().getAuditEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		java.util.Date createDate, long classNameId) {
		return getService().getAuditEntries(createDate, classNameId);
	}

	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		long classNameId, long classPK, int[] visibilities) {
		return getService().getAuditEntries(classNameId, classPK, visibilities);
	}

	public static java.util.List<java.util.List<com.liferay.osb.model.AuditEntry>> getAuditEntrySets(
		long classNameId, long classPK, int[] visibilities) {
		return getService().getAuditEntrySets(classNameId, classPK, visibilities);
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

	public static long getNextAuditSetId(java.lang.String className,
		long classPK) {
		return getService().getNextAuditSetId(className, classPK);
	}

	public static long getNextAuditSetId(long classNameId, long classPK) {
		return getService().getNextAuditSetId(classNameId, classPK);
	}

	public static void clearService() {
		_service = null;
	}

	public static AuditEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AuditEntryLocalService.class.getName());

			if (invokableLocalService instanceof AuditEntryLocalService) {
				_service = (AuditEntryLocalService)invokableLocalService;
			}
			else {
				_service = new AuditEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AuditEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AuditEntryLocalService _service;
}