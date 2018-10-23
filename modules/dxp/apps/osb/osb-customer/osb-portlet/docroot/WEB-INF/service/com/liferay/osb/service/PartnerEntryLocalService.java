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

import com.liferay.osb.model.PartnerEntry;

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

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for PartnerEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryLocalServiceUtil
 * @see com.liferay.osb.service.base.PartnerEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PartnerEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerEntryLocalServiceUtil} to access the partner entry local service. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionPartnerEntries(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId);

	/**
	* Adds the partner entry to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PartnerEntry addPartnerEntry(PartnerEntry partnerEntry);

	public PartnerEntry addPartnerEntry(long userId, long parentPartnerEntryId,
		java.lang.String dossieraAccountKey, java.lang.String jiraProjectKey,
		java.lang.String code, java.lang.String notes, long[] supportRegionIds)
		throws PortalException;

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public PartnerEntry createPartnerEntry(long partnerEntryId);

	/**
	* Deletes the partner entry from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PartnerEntry deletePartnerEntry(PartnerEntry partnerEntry);

	/**
	* Deletes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PartnerEntry deletePartnerEntry(long partnerEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerEntry fetchPartnerEntry(java.lang.String dossieraAccountKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerEntry fetchPartnerEntry(long partnerEntryId);

	/**
	* Returns the partner entry with the primary key.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerEntry getPartnerEntry(long partnerEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PartnerEntry getPartnerEntryByCode(java.lang.String code)
		throws PortalException;

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PartnerEntry updatePartnerEntry(PartnerEntry partnerEntry);

	public PartnerEntry updatePartnerEntry(long userId, long partnerEntryId,
		java.lang.String dossieraAccountKey, java.lang.String jiraProjectKey,
		java.lang.String code, java.lang.String notes, int status,
		long[] supportRegionIds) throws PortalException;

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

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPartnerEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportRegionPartnerEntriesCount(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String code, int[] statuses,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<PartnerEntry> getChildPartnerEntries(long partnerEntryId,
		boolean recursive);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> getPartnerEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> getUserPartnerEntries(long userId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> search(java.lang.String code, int[] statuses,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PartnerEntry> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end);

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
	* Returns the supportRegionIds of the support regions associated with the partner entry.
	*
	* @param partnerEntryId the partnerEntryId of the partner entry
	* @return long[] the supportRegionIds of support regions associated with the partner entry
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getSupportRegionPrimaryKeys(long partnerEntryId);

	public void addSupportRegionPartnerEntries(long supportRegionId,
		List<PartnerEntry> partnerEntries);

	public void addSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds);

	public void addSupportRegionPartnerEntry(long supportRegionId,
		PartnerEntry partnerEntry);

	public void addSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId);

	public void clearSupportRegionPartnerEntries(long supportRegionId);

	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		List<PartnerEntry> partnerEntries);

	public void deleteSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds);

	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		PartnerEntry partnerEntry);

	public void deleteSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId);

	public void setSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds);
}