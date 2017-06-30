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
 * This class is a wrapper for {@link SupportRegionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportRegionLocalService
 * @generated
 */
public class SupportRegionLocalServiceWrapper
	implements SupportRegionLocalService,
		ServiceWrapper<SupportRegionLocalService> {
	public SupportRegionLocalServiceWrapper(
		SupportRegionLocalService supportRegionLocalService) {
		_supportRegionLocalService = supportRegionLocalService;
	}

	/**
	* Adds the support region to the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion addSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.addSupportRegion(supportRegion);
	}

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	public com.liferay.osb.model.SupportRegion createSupportRegion(
		long supportRegionId) {
		return _supportRegionLocalService.createSupportRegion(supportRegionId);
	}

	/**
	* Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws PortalException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion deleteSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.deleteSupportRegion(supportRegionId);
	}

	/**
	* Deletes the support region from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion deleteSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.deleteSupportRegion(supportRegion);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportRegionLocalService.dynamicQuery();
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery);
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _supportRegionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SupportRegion fetchSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.fetchSupportRegion(supportRegionId);
	}

	/**
	* Returns the support region with the primary key.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws PortalException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion getSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportRegion(supportRegionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportRegions(start, end);
	}

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportRegionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportRegionsCount();
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.updateSupportRegion(supportRegion);
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @param merge whether to merge the support region with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.updateSupportRegion(supportRegion,
			merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addAccountEntrySupportRegion(accountEntryId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addAccountEntrySupportRegions(accountEntryId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearAccountEntrySupportRegions(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.clearAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteAccountEntrySupportRegion(accountEntryId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteAccountEntrySupportRegions(accountEntryId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountEntrySupportRegionsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getAccountEntrySupportRegionsCount(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasAccountEntrySupportRegions(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.setAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addPartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addPartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addPartnerEntrySupportRegion(partnerEntryId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addPartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addPartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addPartnerEntrySupportRegions(partnerEntryId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearPartnerEntrySupportRegions(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.clearPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deletePartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deletePartnerEntrySupportRegion(partnerEntryId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deletePartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deletePartnerEntrySupportRegions(partnerEntryId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getPartnerEntrySupportRegionsCount(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getPartnerEntrySupportRegionsCount(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasPartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasPartnerEntrySupportRegions(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.setPartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addSupportTeamSupportRegion(supportTeamId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addSupportTeamSupportRegion(supportTeamId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addSupportTeamSupportRegions(supportTeamId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.addSupportTeamSupportRegions(supportTeamId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportTeamSupportRegions(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.clearSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteSupportTeamSupportRegion(supportTeamId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteSupportTeamSupportRegion(supportTeamId,
			supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteSupportTeamSupportRegions(supportTeamId,
			supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.deleteSupportTeamSupportRegions(supportTeamId,
			SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportTeamSupportRegions(supportTeamId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportTeamSupportRegions(supportTeamId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportTeamSupportRegionsCount(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.getSupportTeamSupportRegionsCount(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasSupportTeamSupportRegion(supportTeamId,
			supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportTeamSupportRegions(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.hasSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportRegionLocalService.setSupportTeamSupportRegions(supportTeamId,
			supportRegionIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportRegionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportRegionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportRegionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.SupportRegion addSupportRegion(long userId,
		java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.addSupportRegion(userId, name,
			description, timeZoneId);
	}

	public com.liferay.osb.model.SupportRegion fetchSupportRegionByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.fetchSupportRegionByName(name);
	}

	public com.liferay.osb.model.SupportRegion updateSupportRegion(
		long supportRegionId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportRegionLocalService.updateSupportRegion(supportRegionId,
			name, description, timeZoneId, supportTeamIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportRegionLocalService getWrappedSupportRegionLocalService() {
		return _supportRegionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportRegionLocalService(
		SupportRegionLocalService supportRegionLocalService) {
		_supportRegionLocalService = supportRegionLocalService;
	}

	public SupportRegionLocalService getWrappedService() {
		return _supportRegionLocalService;
	}

	public void setWrappedService(
		SupportRegionLocalService supportRegionLocalService) {
		_supportRegionLocalService = supportRegionLocalService;
	}

	private SupportRegionLocalService _supportRegionLocalService;
}