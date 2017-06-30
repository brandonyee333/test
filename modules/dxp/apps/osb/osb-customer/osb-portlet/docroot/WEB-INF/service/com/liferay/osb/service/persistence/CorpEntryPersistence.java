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

import com.liferay.osb.model.CorpEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpEntryPersistenceImpl
 * @see CorpEntryUtil
 * @generated
 */
public interface CorpEntryPersistence extends BasePersistence<CorpEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpEntryUtil} to access the corp entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the corp entry in the entity cache if it is enabled.
	*
	* @param corpEntry the corp entry
	*/
	public void cacheResult(com.liferay.osb.model.CorpEntry corpEntry);

	/**
	* Caches the corp entries in the entity cache if it is enabled.
	*
	* @param corpEntries the corp entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries);

	/**
	* Creates a new corp entry with the primary key. Does not add the corp entry to the database.
	*
	* @param corpEntryId the primary key for the new corp entry
	* @return the new corp entry
	*/
	public com.liferay.osb.model.CorpEntry create(long corpEntryId);

	/**
	* Removes the corp entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry that was removed
	* @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry remove(long corpEntryId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CorpEntry updateImpl(
		com.liferay.osb.model.CorpEntry corpEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry with the primary key or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry findByPrimaryKey(long corpEntryId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry, or <code>null</code> if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByPrimaryKey(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp entries where name = &#63;.
	*
	* @param name the name
	* @return the matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @return the range of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entries before and after the current corp entry in the ordered set where name = &#63;.
	*
	* @param corpEntryId the primary key of the current corp entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry[] findByName_PrevAndNext(
		long corpEntryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp entries.
	*
	* @return the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @return the range of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp entries where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp entry where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the corp entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp entries where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByDossieraAccountKey(java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp entries.
	*
	* @return the number of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp groups associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @return the corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp groups associated with the corp entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the corp entry
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @return the range of corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp groups associated with the corp entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the corp entry
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp groups associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @return the number of corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpGroupsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the corp group is associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @return <code>true</code> if the corp group is associated with the corp entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the corp entry has any corp groups associated with it.
	*
	* @param pk the primary key of the corp entry to check for associations with corp groups
	* @return <code>true</code> if the corp entry has any corp groups associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsCorpGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroup the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroup(long pk, com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the corp entry and its corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry to clear the associated corp groups from
	* @throws SystemException if a system exception occurred
	*/
	public void clearCorpGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroup the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpGroup(long pk,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups to be associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups to be associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException;
}