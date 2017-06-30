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
 * The utility for the audit entry local service. This utility wraps {@link com.liferay.osb.service.impl.AuditEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalService
 * @see com.liferay.osb.service.base.AuditEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AuditEntryLocalServiceImpl
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry addAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAuditEntry(auditEntry);
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
	* Deletes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws PortalException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry deleteAuditEntry(
		long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAuditEntry(auditEntryId);
	}

	/**
	* Deletes the audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry deleteAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAuditEntry(auditEntry);
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

	public static com.liferay.osb.model.AuditEntry fetchAuditEntry(
		long auditEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAuditEntry(auditEntryId);
	}

	/**
	* Returns the audit entry with the primary key.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry
	* @throws PortalException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry getAuditEntry(
		long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntry(auditEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntries(start, end);
	}

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getAuditEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntriesCount();
	}

	/**
	* Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry updateAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAuditEntry(auditEntry);
	}

	/**
	* Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @param merge whether to merge the audit entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the audit entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AuditEntry updateAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAuditEntry(auditEntry, merge);
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

	public static com.liferay.osb.model.AuditEntry addAuditEntry(long userId,
		java.lang.String userName, java.util.Date createDate, long classNameId,
		long classPK, long auditSetId, long fieldClassNameId,
		long fieldClassPK, int action, int field, int visibility,
		java.lang.String oldLabel, java.lang.String oldValue,
		java.lang.String newLabel, java.lang.String newValue)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAuditEntry(userId, userName, createDate, classNameId,
			classPK, auditSetId, fieldClassNameId, fieldClassPK, action, field,
			visibility, oldLabel, oldValue, newLabel, newValue, i18n,
			trackChange);
	}

	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		java.util.Date createDate, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntries(createDate, classNameId);
	}

	public static java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		long classNameId, long classPK, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntries(classNameId, classPK, visibilities);
	}

	public static java.util.List<java.util.List<com.liferay.osb.model.AuditEntry>> getAuditEntrySets(
		long classNameId, long classPK, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAuditEntrySets(classNameId, classPK, visibilities);
	}

	public static com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long fieldClassNameId, long fieldClassPK, int field)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastAuditEntry(fieldClassNameId, fieldClassPK, field);
	}

	public static com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long classNameId, long classPK, int field, int action)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastAuditEntry(classNameId, classPK, field, action);
	}

	public static long getNextAuditSetId(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNextAuditSetId(classNameId, classPK);
	}

	public static long getNextAuditSetId(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNextAuditSetId(className, classPK);
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

	/**
	 * @deprecated
	 */
	public void setService(AuditEntryLocalService service) {
	}

	private static AuditEntryLocalService _service;
}