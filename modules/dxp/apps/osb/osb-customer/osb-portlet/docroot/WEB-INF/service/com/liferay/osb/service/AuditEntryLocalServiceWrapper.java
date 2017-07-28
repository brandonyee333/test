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
 * Provides a wrapper for {@link AuditEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalService
 * @generated
 */
@ProviderType
public class AuditEntryLocalServiceWrapper implements AuditEntryLocalService,
	ServiceWrapper<AuditEntryLocalService> {
	public AuditEntryLocalServiceWrapper(
		AuditEntryLocalService auditEntryLocalService) {
		_auditEntryLocalService = auditEntryLocalService;
	}

	/**
	* Adds the audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was added
	*/
	@Override
	public com.liferay.osb.model.AuditEntry addAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return _auditEntryLocalService.addAuditEntry(auditEntry);
	}

	@Override
	public com.liferay.osb.model.AuditEntry addAuditEntry(long userId,
		java.lang.String userName, java.util.Date createDate, long classNameId,
		long classPK, long auditSetId, long fieldClassNameId,
		long fieldClassPK, int action, int field, int visibility,
		java.lang.String oldLabel, java.lang.String oldValue,
		java.lang.String newLabel, java.lang.String newValue)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.addAuditEntry(userId, userName,
			createDate, classNameId, classPK, auditSetId, fieldClassNameId,
			fieldClassPK, action, field, visibility, oldLabel, oldValue,
			newLabel, newValue);
	}

	@Override
	public com.liferay.osb.model.AuditEntry addAuditEntry(long userId,
		java.lang.String userName, java.util.Date createDate, long classNameId,
		long classPK, long auditSetId, long fieldClassNameId,
		long fieldClassPK, int action, int field, int visibility,
		java.lang.String oldLabel, java.lang.String oldValue,
		java.lang.String newLabel, java.lang.String newValue, boolean i18n,
		boolean trackChange)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.addAuditEntry(userId, userName,
			createDate, classNameId, classPK, auditSetId, fieldClassNameId,
			fieldClassPK, action, field, visibility, oldLabel, oldValue,
			newLabel, newValue, i18n, trackChange);
	}

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditEntryId the primary key for the new audit entry
	* @return the new audit entry
	*/
	@Override
	public com.liferay.osb.model.AuditEntry createAuditEntry(long auditEntryId) {
		return _auditEntryLocalService.createAuditEntry(auditEntryId);
	}

	/**
	* Deletes the audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was removed
	*/
	@Override
	public com.liferay.osb.model.AuditEntry deleteAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return _auditEntryLocalService.deleteAuditEntry(auditEntry);
	}

	/**
	* Deletes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AuditEntry deleteAuditEntry(long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.deleteAuditEntry(auditEntryId);
	}

	@Override
	public com.liferay.osb.model.AuditEntry fetchAuditEntry(long auditEntryId) {
		return _auditEntryLocalService.fetchAuditEntry(auditEntryId);
	}

	/**
	* Returns the audit entry with the primary key.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AuditEntry getAuditEntry(long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.getAuditEntry(auditEntryId);
	}

	@Override
	public com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long classNameId, long classPK, int field, int action) {
		return _auditEntryLocalService.getLastAuditEntry(classNameId, classPK,
			field, action);
	}

	@Override
	public com.liferay.osb.model.AuditEntry getLastAuditEntry(
		long fieldClassNameId, long fieldClassPK, int field) {
		return _auditEntryLocalService.getLastAuditEntry(fieldClassNameId,
			fieldClassPK, field);
	}

	/**
	* Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was updated
	*/
	@Override
	public com.liferay.osb.model.AuditEntry updateAuditEntry(
		com.liferay.osb.model.AuditEntry auditEntry) {
		return _auditEntryLocalService.updateAuditEntry(auditEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _auditEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _auditEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	@Override
	public int getAuditEntriesCount() {
		return _auditEntryLocalService.getAuditEntriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _auditEntryLocalService.getOSGiServiceIdentifier();
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
		return _auditEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _auditEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _auditEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		int start, int end) {
		return _auditEntryLocalService.getAuditEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		java.util.Date createDate, long classNameId) {
		return _auditEntryLocalService.getAuditEntries(createDate, classNameId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AuditEntry> getAuditEntries(
		long classNameId, long classPK, int[] visibilities) {
		return _auditEntryLocalService.getAuditEntries(classNameId, classPK,
			visibilities);
	}

	@Override
	public java.util.List<java.util.List<com.liferay.osb.model.AuditEntry>> getAuditEntrySets(
		long classNameId, long classPK, int[] visibilities) {
		return _auditEntryLocalService.getAuditEntrySets(classNameId, classPK,
			visibilities);
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
		return _auditEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _auditEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public long getNextAuditSetId(java.lang.String className, long classPK) {
		return _auditEntryLocalService.getNextAuditSetId(className, classPK);
	}

	@Override
	public long getNextAuditSetId(long classNameId, long classPK) {
		return _auditEntryLocalService.getNextAuditSetId(classNameId, classPK);
	}

	@Override
	public AuditEntryLocalService getWrappedService() {
		return _auditEntryLocalService;
	}

	@Override
	public void setWrappedService(AuditEntryLocalService auditEntryLocalService) {
		_auditEntryLocalService = auditEntryLocalService;
	}

	private AuditEntryLocalService _auditEntryLocalService;
}