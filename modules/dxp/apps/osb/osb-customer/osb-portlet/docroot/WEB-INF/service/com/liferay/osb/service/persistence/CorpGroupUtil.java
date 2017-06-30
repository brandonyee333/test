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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the corp group service. This utility wraps {@link CorpGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroupPersistence
 * @see CorpGroupPersistenceImpl
 * @generated
 */
public class CorpGroupUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(CorpGroup corpGroup) {
		getPersistence().clearCache(corpGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CorpGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CorpGroup update(CorpGroup corpGroup, boolean merge)
		throws SystemException {
		return getPersistence().update(corpGroup, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CorpGroup update(CorpGroup corpGroup, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(corpGroup, merge, serviceContext);
	}

	/**
	* Caches the corp group in the entity cache if it is enabled.
	*
	* @param corpGroup the corp group
	*/
	public static void cacheResult(com.liferay.osb.model.CorpGroup corpGroup) {
		getPersistence().cacheResult(corpGroup);
	}

	/**
	* Caches the corp groups in the entity cache if it is enabled.
	*
	* @param corpGroups the corp groups
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups) {
		getPersistence().cacheResult(corpGroups);
	}

	/**
	* Creates a new corp group with the primary key. Does not add the corp group to the database.
	*
	* @param corpGroupId the primary key for the new corp group
	* @return the new corp group
	*/
	public static com.liferay.osb.model.CorpGroup create(long corpGroupId) {
		return getPersistence().create(corpGroupId);
	}

	/**
	* Removes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group that was removed
	* @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup remove(long corpGroupId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(corpGroupId);
	}

	public static com.liferay.osb.model.CorpGroup updateImpl(
		com.liferay.osb.model.CorpGroup corpGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(corpGroup, merge);
	}

	/**
	* Returns the corp group with the primary key or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup findByPrimaryKey(
		long corpGroupId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(corpGroupId);
	}

	/**
	* Returns the corp group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group, or <code>null</code> if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup fetchByPrimaryKey(
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(corpGroupId);
	}

	/**
	* Returns all the corp groups where name = &#63;.
	*
	* @param name the name
	* @return the matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last corp group in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpGroup[] findByName_PrevAndNext(
		long corpGroupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(corpGroupId, name, orderByComparator);
	}

	/**
	* Returns the corp group where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpGroupException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp group
	* @throws com.liferay.osb.NoSuchCorpGroupException if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByOrganizationId(organizationId);
	}

	/**
	* Returns the corp group where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp group, or <code>null</code> if a matching corp group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrganizationId(organizationId, retrieveFromCache);
	}

	/**
	* Returns all the corp groups.
	*
	* @return the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the corp groups where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Removes the corp group where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp group that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Removes all the corp groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp groups where name = &#63;.
	*
	* @param name the name
	* @return the number of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of corp groups where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Returns the number of corp groups.
	*
	* @return the number of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the corp entries associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @return the corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpEntries(pk);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpEntries(pk, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpEntries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of corp entries associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @return the number of corp entries associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpEntriesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpEntriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the corp entry is associated with the corp group.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @return <code>true</code> if the corp entry is associated with the corp group; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsCorpEntry(pk, corpEntryPK);
	}

	/**
	* Returns <code>true</code> if the corp group has any corp entries associated with it.
	*
	* @param pk the primary key of the corp group to check for associations with corp entries
	* @return <code>true</code> if the corp group has any corp entries associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsCorpEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsCorpEntries(pk);
	}

	/**
	* Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpEntry(pk, corpEntryPK);
	}

	/**
	* Adds an association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntry the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntry(long pk,
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpEntry(pk, corpEntry);
	}

	/**
	* Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpEntries(pk, corpEntryPKs);
	}

	/**
	* Adds an association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpEntries(pk, corpEntries);
	}

	/**
	* Clears all associations between the corp group and its corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group to clear the associated corp entries from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearCorpEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearCorpEntries(pk);
	}

	/**
	* Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPK the primary key of the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpEntry(long pk, long corpEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpEntry(pk, corpEntryPK);
	}

	/**
	* Removes the association between the corp group and the corp entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntry the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpEntry(long pk,
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpEntry(pk, corpEntry);
	}

	/**
	* Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpEntries(pk, corpEntryPKs);
	}

	/**
	* Removes the association between the corp group and the corp entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpEntries(pk, corpEntries);
	}

	/**
	* Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntryPKs the primary keys of the corp entries to be associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void setCorpEntries(long pk, long[] corpEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setCorpEntries(pk, corpEntryPKs);
	}

	/**
	* Sets the corp entries associated with the corp group, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp group
	* @param corpEntries the corp entries to be associated with the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void setCorpEntries(long pk,
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setCorpEntries(pk, corpEntries);
	}

	public static CorpGroupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpGroupPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpGroupPersistence.class.getName());

			ReferenceRegistry.registerReference(CorpGroupUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CorpGroupPersistence persistence) {
	}

	private static CorpGroupPersistence _persistence;
}