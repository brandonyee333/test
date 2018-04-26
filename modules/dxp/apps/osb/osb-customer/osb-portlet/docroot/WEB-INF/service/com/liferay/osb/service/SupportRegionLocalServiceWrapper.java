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
 * Provides a wrapper for {@link SupportRegionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalService
 * @generated
 */
@ProviderType
public class SupportRegionLocalServiceWrapper
	implements SupportRegionLocalService,
		ServiceWrapper<SupportRegionLocalService> {
	public SupportRegionLocalServiceWrapper(
		SupportRegionLocalService supportRegionLocalService) {
		_supportRegionLocalService = supportRegionLocalService;
	}

	@Override
	public boolean hasAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		return _supportRegionLocalService.hasAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	@Override
	public boolean hasAccountEntrySupportRegions(long accountEntryId) {
		return _supportRegionLocalService.hasAccountEntrySupportRegions(accountEntryId);
	}

	@Override
	public boolean hasPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		return _supportRegionLocalService.hasPartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	@Override
	public boolean hasPartnerEntrySupportRegions(long partnerEntryId) {
		return _supportRegionLocalService.hasPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* Adds the support region to the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was added
	*/
	@Override
	public com.liferay.osb.model.SupportRegion addSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return _supportRegionLocalService.addSupportRegion(supportRegion);
	}

	@Override
	public com.liferay.osb.model.SupportRegion addSupportRegion(long userId,
		java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.addSupportRegion(userId, name,
			description, timeZoneId);
	}

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	@Override
	public com.liferay.osb.model.SupportRegion createSupportRegion(
		long supportRegionId) {
		return _supportRegionLocalService.createSupportRegion(supportRegionId);
	}

	/**
	* Deletes the support region from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportRegion deleteSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return _supportRegionLocalService.deleteSupportRegion(supportRegion);
	}

	/**
	* Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws PortalException if a support region with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportRegion deleteSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.deleteSupportRegion(supportRegionId);
	}

	@Override
	public com.liferay.osb.model.SupportRegion fetchSupportRegion(
		long supportRegionId) {
		return _supportRegionLocalService.fetchSupportRegion(supportRegionId);
	}

	@Override
	public com.liferay.osb.model.SupportRegion fetchSupportRegionByName(
		java.lang.String name) {
		return _supportRegionLocalService.fetchSupportRegionByName(name);
	}

	/**
	* Returns the support region with the primary key.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws PortalException if a support region with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportRegion getSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.getSupportRegion(supportRegionId);
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return _supportRegionLocalService.updateSupportRegion(supportRegion);
	}

	@Override
	public com.liferay.osb.model.SupportRegion updateSupportRegion(
		long supportRegionId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.updateSupportRegion(supportRegionId,
			name, description, timeZoneId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportRegionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportRegionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportRegionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportRegionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getAccountEntrySupportRegionsCount(long accountEntryId) {
		return _supportRegionLocalService.getAccountEntrySupportRegionsCount(accountEntryId);
	}

	@Override
	public int getPartnerEntrySupportRegionsCount(long partnerEntryId) {
		return _supportRegionLocalService.getPartnerEntrySupportRegionsCount(partnerEntryId);
	}

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	*/
	@Override
	public int getSupportRegionsCount() {
		return _supportRegionLocalService.getSupportRegionsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportRegionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportRegionLocalService.getOSGiServiceIdentifier();
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportRegionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId) {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end) {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return _supportRegionLocalService.getAccountEntrySupportRegions(accountEntryId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId) {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end) {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return _supportRegionLocalService.getPartnerEntrySupportRegions(partnerEntryId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		int start, int end) {
		return _supportRegionLocalService.getSupportRegions(start, end);
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
		return _supportRegionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportRegionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the accountEntryIds of the account entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the accountEntryIds of account entries associated with the support region
	*/
	@Override
	public long[] getAccountEntryPrimaryKeys(long supportRegionId) {
		return _supportRegionLocalService.getAccountEntryPrimaryKeys(supportRegionId);
	}

	/**
	* Returns the partnerEntryIds of the partner entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the partnerEntryIds of partner entries associated with the support region
	*/
	@Override
	public long[] getPartnerEntryPrimaryKeys(long supportRegionId) {
		return _supportRegionLocalService.getPartnerEntryPrimaryKeys(supportRegionId);
	}

	@Override
	public void addAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		_supportRegionLocalService.addAccountEntrySupportRegion(accountEntryId,
			supportRegion);
	}

	@Override
	public void addAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		_supportRegionLocalService.addAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	@Override
	public void addAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		_supportRegionLocalService.addAccountEntrySupportRegions(accountEntryId,
			supportRegions);
	}

	@Override
	public void addAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.addAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	@Override
	public void addPartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		_supportRegionLocalService.addPartnerEntrySupportRegion(partnerEntryId,
			supportRegion);
	}

	@Override
	public void addPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		_supportRegionLocalService.addPartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	@Override
	public void addPartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		_supportRegionLocalService.addPartnerEntrySupportRegions(partnerEntryId,
			supportRegions);
	}

	@Override
	public void addPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.addPartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	@Override
	public void clearAccountEntrySupportRegions(long accountEntryId) {
		_supportRegionLocalService.clearAccountEntrySupportRegions(accountEntryId);
	}

	@Override
	public void clearPartnerEntrySupportRegions(long partnerEntryId) {
		_supportRegionLocalService.clearPartnerEntrySupportRegions(partnerEntryId);
	}

	@Override
	public void deleteAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		_supportRegionLocalService.deleteAccountEntrySupportRegion(accountEntryId,
			supportRegion);
	}

	@Override
	public void deleteAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		_supportRegionLocalService.deleteAccountEntrySupportRegion(accountEntryId,
			supportRegionId);
	}

	@Override
	public void deleteAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		_supportRegionLocalService.deleteAccountEntrySupportRegions(accountEntryId,
			supportRegions);
	}

	@Override
	public void deleteAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.deleteAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	@Override
	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		_supportRegionLocalService.deletePartnerEntrySupportRegion(partnerEntryId,
			supportRegion);
	}

	@Override
	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		_supportRegionLocalService.deletePartnerEntrySupportRegion(partnerEntryId,
			supportRegionId);
	}

	@Override
	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		_supportRegionLocalService.deletePartnerEntrySupportRegions(partnerEntryId,
			supportRegions);
	}

	@Override
	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.deletePartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	@Override
	public void setAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.setAccountEntrySupportRegions(accountEntryId,
			supportRegionIds);
	}

	@Override
	public void setPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		_supportRegionLocalService.setPartnerEntrySupportRegions(partnerEntryId,
			supportRegionIds);
	}

	@Override
	public SupportRegionLocalService getWrappedService() {
		return _supportRegionLocalService;
	}

	@Override
	public void setWrappedService(
		SupportRegionLocalService supportRegionLocalService) {
		_supportRegionLocalService = supportRegionLocalService;
	}

	private SupportRegionLocalService _supportRegionLocalService;
}