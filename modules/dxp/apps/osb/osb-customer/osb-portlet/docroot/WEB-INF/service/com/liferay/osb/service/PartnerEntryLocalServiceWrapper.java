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
 * This class is a wrapper for {@link PartnerEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerEntryLocalService
 * @generated
 */
public class PartnerEntryLocalServiceWrapper implements PartnerEntryLocalService,
	ServiceWrapper<PartnerEntryLocalService> {
	public PartnerEntryLocalServiceWrapper(
		PartnerEntryLocalService partnerEntryLocalService) {
		_partnerEntryLocalService = partnerEntryLocalService;
	}

	/**
	* Adds the partner entry to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry addPartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.addPartnerEntry(partnerEntry);
	}

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public com.liferay.osb.model.PartnerEntry createPartnerEntry(
		long partnerEntryId) {
		return _partnerEntryLocalService.createPartnerEntry(partnerEntryId);
	}

	/**
	* Deletes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws PortalException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.deletePartnerEntry(partnerEntryId);
	}

	/**
	* Deletes the partner entry from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.deletePartnerEntry(partnerEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _partnerEntryLocalService.dynamicQuery();
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _partnerEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _partnerEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.fetchPartnerEntry(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws PortalException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getPartnerEntry(partnerEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getPartnerEntries(start, end);
	}

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public int getPartnerEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getPartnerEntriesCount();
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.updatePartnerEntry(partnerEntry);
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @param merge whether to merge the partner entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the partner entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.updatePartnerEntry(partnerEntry, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.addSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.addSupportRegionPartnerEntry(supportRegionId,
			partnerEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.addSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> PartnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.addSupportRegionPartnerEntries(supportRegionId,
			PartnerEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportRegionPartnerEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.clearSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntry(supportRegionId,
			partnerEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> PartnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.deleteSupportRegionPartnerEntries(supportRegionId,
			PartnerEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getSupportRegionPartnerEntries(supportRegionId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportRegionPartnerEntriesCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getSupportRegionPartnerEntriesCount(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.hasSupportRegionPartnerEntry(supportRegionId,
			partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportRegionPartnerEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.hasSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerEntryLocalService.setSupportRegionPartnerEntries(supportRegionId,
			partnerEntryIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _partnerEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_partnerEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.PartnerEntry addPartnerEntry(long userId,
		long parentPartnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String code, java.lang.String notes, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.addPartnerEntry(userId,
			parentPartnerEntryId, dossieraAccountKey, code, notes,
			supportRegionIds);
	}

	public com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.fetchPartnerEntry(dossieraAccountKey);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		long partnerEntryId, boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getChildPartnerEntries(partnerEntryId,
			recursive);
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getPartnerEntryByCode(code);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> getUserPartnerEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.getUserPartnerEntries(userId, start,
			end);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.search(code, statuses, params,
			andOperator, start, end);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.search(keywords, params, start, end);
	}

	public int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.searchCount(code, statuses, params,
			andOperator);
	}

	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.searchCount(keywords, params);
	}

	public com.liferay.osb.model.PartnerEntry updatePartnerEntry(long userId,
		long partnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String code, java.lang.String notes, int status,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryLocalService.updatePartnerEntry(userId,
			partnerEntryId, dossieraAccountKey, code, notes, status,
			supportRegionIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PartnerEntryLocalService getWrappedPartnerEntryLocalService() {
		return _partnerEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPartnerEntryLocalService(
		PartnerEntryLocalService partnerEntryLocalService) {
		_partnerEntryLocalService = partnerEntryLocalService;
	}

	public PartnerEntryLocalService getWrappedService() {
		return _partnerEntryLocalService;
	}

	public void setWrappedService(
		PartnerEntryLocalService partnerEntryLocalService) {
		_partnerEntryLocalService = partnerEntryLocalService;
	}

	private PartnerEntryLocalService _partnerEntryLocalService;
}