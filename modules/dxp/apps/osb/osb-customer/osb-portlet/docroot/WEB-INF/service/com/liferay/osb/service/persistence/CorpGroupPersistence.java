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

import com.liferay.osb.model.CorpGroup;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroupPersistenceImpl
 * @see CorpGroupUtil
 * @generated
 */
public interface CorpGroupPersistence extends BasePersistence<CorpGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpGroupUtil} to access the corp group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the corp group in the entity cache if it is enabled.
	*
	* @param corpGroup the corp group
	*/
	public void cacheResult(com.liferay.osb.model.CorpGroup corpGroup);

	/**
	* Caches the corp groups in the entity cache if it is enabled.
	*
	* @param corpGroups the corp groups
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups);

	/**
	* Creates a new corp group with the primary key. Does not add the corp group to the database.
	*
	* @param corpGroupId the primary key for the new corp group
	* @return the new corp group
	*/
	public com.liferay.osb.model.CorpGroup create(long corpGroupId);

	/**
	* Removes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group that was removed
	* @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup remove(long corpGroupId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CorpGroup updateImpl(
		com.liferay.osb.model.CorpGroup corpGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp group with the primary key or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup findByPrimaryKey(long corpGroupId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group, or <code>null</code> if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup fetchByPrimaryKey(long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp groups where name = &#63;.
	*
	* @param name the name
	* @return the matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp groups where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @return the range of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp groups where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp groups before and after the current corp group in the ordered set where name = &#63;.
	*
	* @param corpGroupId the primary key of the current corp group
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup[] findByName_PrevAndNext(
		long corpGroupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp group where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp groups.
	*
	* @return the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @return the range of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp groups where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp group where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp group that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp groups where name = &#63;.
	*
	* @param name the name
	* @return the number of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp groups where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp groups.
	*
	* @return the number of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp entries associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @return the corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp entries associated with the corp group.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the corp group
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @return the range of corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp entries associated with the corp group.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the corp group
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp entries associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @return the number of corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpEntriesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the corp entry is associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @return <code>true</code> if the corp entry is associated with the corp group; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the corp group has any corp entries associated with it.
	*
	* @param pk the primary key of the corp group to check for associations with corp entries
	* @return <code>true</code> if the corp group has any corp entries associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsCorpEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntry the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntry(long pk, com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the corp group and its corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group to clear the associated corp entries from
	* @throws SystemException if a system exception occurred
	*/
	public void clearCorpEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntry the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpEntry(long pk,
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public void removeCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries to be associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries to be associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException;
}