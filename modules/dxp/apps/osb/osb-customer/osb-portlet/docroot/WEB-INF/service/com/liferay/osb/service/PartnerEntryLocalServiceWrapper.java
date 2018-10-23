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
 * Provides a wrapper for {@link PartnerEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryLocalService
 * @generated
 */
@ProviderType
public class PartnerEntryLocalServiceWrapper implements PartnerEntryLocalService,
	ServiceWrapper<PartnerEntryLocalService> {
	public PartnerEntryLocalServiceWrapper(
		PartnerEntryLocalService partnerEntryLocalService) {
		_partnerEntryLocalService = partnerEntryLocalService;
	}

	@Override
	public boolean hasSupportRegionPartnerEntries(long supportRegionId) {
		return _partnerEntryLocalService.hasSupportRegionPartnerEntries(supportRegionId);
	}

	@Override
	public boolean hasSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		return _partnerEntryLocalService.hasSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	/**
	* Adds the partner entry to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was added
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry addPartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return _partnerEntryLocalService.addPartnerEntry(partnerEntry);
	}

	@Override
	public com.liferay.osb.model.PartnerEntry addPartnerEntry(long userId,
		long parentPartnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String jiraProjectKey, java.lang.String code,
		java.lang.String notes, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.addPartnerEntry(userId,
			parentPartnerEntryId, dossieraAccountKey, jiraProjectKey, code,
			notes, supportRegionIds);
	}

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry createPartnerEntry(
		long partnerEntryId) {
		return _partnerEntryLocalService.createPartnerEntry(partnerEntryId);
	}

	/**
	* Deletes the partner entry from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was removed
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return _partnerEntryLocalService.deletePartnerEntry(partnerEntry);
	}

	/**
	* Deletes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.deletePartnerEntry(partnerEntryId);
	}

	@Override
	public com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		java.lang.String dossieraAccountKey) {
		return _partnerEntryLocalService.fetchPartnerEntry(dossieraAccountKey);
	}

	@Override
	public com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		long partnerEntryId) {
		return _partnerEntryLocalService.fetchPartnerEntry(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.getPartnerEntry(partnerEntryId);
	}

	@Override
	public com.liferay.osb.model.PartnerEntry getPartnerEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.getPartnerEntryByCode(code);
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was updated
	*/
	@Override
	public com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return _partnerEntryLocalService.updatePartnerEntry(partnerEntry);
	}

	@Override
	public com.liferay.osb.model.PartnerEntry updatePartnerEntry(long userId,
		long partnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String jiraProjectKey, java.lang.String code,
		java.lang.String notes, int status, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.updatePartnerEntry(userId,
			partnerEntryId, dossieraAccountKey, jiraProjectKey, code, notes,
			status, supportRegionIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _partnerEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _partnerEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _partnerEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	*/
	@Override
	public int getPartnerEntriesCount() {
		return _partnerEntryLocalService.getPartnerEntriesCount();
	}

	@Override
	public int getSupportRegionPartnerEntriesCount(long supportRegionId) {
		return _partnerEntryLocalService.getSupportRegionPartnerEntriesCount(supportRegionId);
	}

	@Override
	public int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return _partnerEntryLocalService.searchCount(code, statuses, params,
			andOperator);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _partnerEntryLocalService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _partnerEntryLocalService.getOSGiServiceIdentifier();
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		long partnerEntryId, boolean recursive) {
		return _partnerEntryLocalService.getChildPartnerEntries(partnerEntryId,
			recursive);
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		int start, int end) {
		return _partnerEntryLocalService.getPartnerEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId) {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end) {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.PartnerEntry> orderByComparator) {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> getUserPartnerEntries(
		long userId, int start, int end) {
		return _partnerEntryLocalService.getUserPartnerEntries(userId, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return _partnerEntryLocalService.search(code, statuses, params,
			andOperator, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return _partnerEntryLocalService.search(keywords, params, start, end);
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
		return _partnerEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _partnerEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the supportRegionIds of the support regions associated with the partner entry.
	*
	* @param partnerEntryId the partnerEntryId of the partner entry
	* @return long[] the supportRegionIds of support regions associated with the partner entry
	*/
	@Override
	public long[] getSupportRegionPrimaryKeys(long partnerEntryId) {
		return _partnerEntryLocalService.getSupportRegionPrimaryKeys(partnerEntryId);
	}

	@Override
	public void addSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		_partnerEntryLocalService.addSupportRegionPartnerEntries(supportRegionId,
			partnerEntries);
	}

	@Override
	public void addSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		_partnerEntryLocalService.addSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	@Override
	public void addSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		_partnerEntryLocalService.addSupportRegionPartnerEntry(supportRegionId,
			partnerEntry);
	}

	@Override
	public void addSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		_partnerEntryLocalService.addSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	@Override
	public void clearSupportRegionPartnerEntries(long supportRegionId) {
		_partnerEntryLocalService.clearSupportRegionPartnerEntries(supportRegionId);
	}

	@Override
	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntries(supportRegionId,
			partnerEntries);
	}

	@Override
	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	@Override
	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntry(supportRegionId,
			partnerEntry);
	}

	@Override
	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	@Override
	public void setSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		_partnerEntryLocalService.setSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	@Override
	public PartnerEntryLocalService getWrappedService() {
		return _partnerEntryLocalService;
	}

	@Override
	public void setWrappedService(
		PartnerEntryLocalService partnerEntryLocalService) {
		_partnerEntryLocalService = partnerEntryLocalService;
	}

	private PartnerEntryLocalService _partnerEntryLocalService;
}