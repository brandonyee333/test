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

import com.liferay.osb.model.OfferingEntry;

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
 * Provides the local service interface for OfferingEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryLocalServiceUtil
 * @see com.liferay.osb.service.base.OfferingEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OfferingEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingEntryLocalServiceUtil} to access the offering entry local service. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasActiveSupportOfferingEntry(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasActiveTrialOfferingEntry(long userId);

	/**
	* Adds the offering entry to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingEntry addOfferingEntry(OfferingEntry offeringEntry);

	public OfferingEntry addOfferingEntry(long userId, long accountEntryId,
		long orderEntryId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity, int status) throws PortalException;

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public OfferingEntry createOfferingEntry(long offeringEntryId);

	/**
	* Deletes the offering entry from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingEntry deleteOfferingEntry(OfferingEntry offeringEntry)
		throws PortalException;

	/**
	* Deletes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingEntry deleteOfferingEntry(long offeringEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingEntry fetchOfferingEntry(long offeringEntryId);

	/**
	* Returns the offering entry with the primary key.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingEntry getOfferingEntry(long offeringEntryId)
		throws PortalException;

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingEntry updateOfferingEntry(OfferingEntry offeringEntry);

	public OfferingEntry updateOfferingEntry(long userId, long offeringEntryId,
		long accountEntryId, long orderEntryId, long productEntryId,
		long supportResponseId, java.lang.String productDescription, int type,
		int version, boolean licenses, long licenseLifetime,
		long maxConcurrentUsers, long maxUsers, boolean supportTickets,
		long supportLifetime, int sizing, int quantity)
		throws PortalException;

	public OfferingEntry updateStatus(long userId, long offeringEntryId,
		int status) throws PortalException;

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
	public int getAccountEntryOfferingEntriesCount(long accountEntryId);

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long userId, long accountEntryId, int[] types,
		int[] statuses, int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId);

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingEntry> getOfferingEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingEntry> getOrderEntryOfferingEntries(long orderEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingEntry> search(long userId, long accountEntryId,
		int[] types, int[] statuses, int supportEndDateGTDay,
		int supportEndDateGTMonth, int supportEndDateGTYear,
		int supportEndDateLTDay, int supportEndDateLTMonth,
		int supportEndDateLTYear,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc);

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

	public void checkOfferingEntries() throws java.lang.Exception;
}