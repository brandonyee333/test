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

import com.liferay.osb.model.SupportRegion;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SupportRegion. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalServiceUtil
 * @see com.liferay.osb.service.base.SupportRegionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportRegionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SupportRegionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportRegionLocalServiceUtil} to access the support region local service. Add custom service methods to {@link com.liferay.osb.service.impl.SupportRegionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportRegions(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPartnerEntrySupportRegions(long partnerEntryId);

	/**
	* Adds the support region to the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportRegion addSupportRegion(SupportRegion supportRegion);

	public SupportRegion addSupportRegion(long userId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId)
		throws PortalException;

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	public SupportRegion createSupportRegion(long supportRegionId);

	/**
	* Deletes the support region from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportRegion deleteSupportRegion(SupportRegion supportRegion);

	/**
	* Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws PortalException if a support region with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportRegion deleteSupportRegion(long supportRegionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportRegion fetchSupportRegion(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportRegion fetchSupportRegionByName(java.lang.String name);

	/**
	* Returns the support region with the primary key.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws PortalException if a support region with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportRegion getSupportRegion(long supportRegionId)
		throws PortalException;

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportRegion updateSupportRegion(SupportRegion supportRegion);

	public SupportRegion updateSupportRegion(long supportRegionId,
		java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountEntrySupportRegionsCount(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPartnerEntrySupportRegionsCount(long partnerEntryId);

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportRegionsCount();

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end,
		OrderByComparator<SupportRegion> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end,
		OrderByComparator<SupportRegion> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportRegion> getSupportRegions(int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the accountEntryIds of the account entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the accountEntryIds of account entries associated with the support region
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getAccountEntryPrimaryKeys(long supportRegionId);

	/**
	* Returns the partnerEntryIds of the partner entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the partnerEntryIds of partner entries associated with the support region
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getPartnerEntryPrimaryKeys(long supportRegionId);

	public void addAccountEntrySupportRegion(long accountEntryId,
		SupportRegion supportRegion);

	public void addAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId);

	public void addAccountEntrySupportRegions(long accountEntryId,
		List<SupportRegion> supportRegions);

	public void addAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds);

	public void addPartnerEntrySupportRegion(long partnerEntryId,
		SupportRegion supportRegion);

	public void addPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId);

	public void addPartnerEntrySupportRegions(long partnerEntryId,
		List<SupportRegion> supportRegions);

	public void addPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds);

	public void clearAccountEntrySupportRegions(long accountEntryId);

	public void clearPartnerEntrySupportRegions(long partnerEntryId);

	public void deleteAccountEntrySupportRegion(long accountEntryId,
		SupportRegion supportRegion);

	public void deleteAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId);

	public void deleteAccountEntrySupportRegions(long accountEntryId,
		List<SupportRegion> supportRegions);

	public void deleteAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds);

	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		SupportRegion supportRegion);

	public void deletePartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId);

	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		List<SupportRegion> supportRegions);

	public void deletePartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds);

	public void setAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds);

	public void setPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds);
}