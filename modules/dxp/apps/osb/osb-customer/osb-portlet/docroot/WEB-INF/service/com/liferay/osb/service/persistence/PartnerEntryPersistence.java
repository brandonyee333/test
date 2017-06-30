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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the partner entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryPersistenceImpl
 * @see PartnerEntryUtil
 * @generated
 */
public interface PartnerEntryPersistence extends BasePersistence<PartnerEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerEntryUtil} to access the partner entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the partner entry in the entity cache if it is enabled.
	*
	* @param partnerEntry the partner entry
	*/
	public void cacheResult(com.liferay.osb.model.PartnerEntry partnerEntry);

	/**
	* Caches the partner entries in the entity cache if it is enabled.
	*
	* @param partnerEntries the partner entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries);

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public com.liferay.osb.model.PartnerEntry create(long partnerEntryId);

	/**
	* Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry remove(long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.PartnerEntry updateImpl(
		com.liferay.osb.model.PartnerEntry partnerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry with the primary key or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry findByPrimaryKey(
		long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByPrimaryKey(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entries before and after the current partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param partnerEntryId the primary key of the current partner entry
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where code = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry findByCode(java.lang.String code)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByCode(java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry fetchByCode(
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the partner entries.
	*
	* @return the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PartnerEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByParentPartnerEntryId(long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the partner entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the partner entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PartnerEntry removeByCode(
		java.lang.String code)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the partner entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByParentPartnerEntryId(long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDossieraAccountKey(java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCode(java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the number of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportRegionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the support region is associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the partner entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the partner entry has any support regions associated with it.
	*
	* @param pk the primary key of the partner entry to check for associations with support regions
	* @return <code>true</code> if the partner entry has any support regions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the partner entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry to clear the associated support regions from
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions to be associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;
}