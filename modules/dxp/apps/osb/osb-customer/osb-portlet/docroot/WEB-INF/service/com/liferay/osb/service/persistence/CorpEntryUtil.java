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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the corp entry service. This utility wraps {@link CorpEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpEntryPersistence
 * @see CorpEntryPersistenceImpl
 * @generated
 */
public class CorpEntryUtil {
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
	public static void clearCache(CorpEntry corpEntry) {
		getPersistence().clearCache(corpEntry);
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
	public static List<CorpEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CorpEntry update(CorpEntry corpEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(corpEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CorpEntry update(CorpEntry corpEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(corpEntry, merge, serviceContext);
	}

	/**
	* Caches the corp entry in the entity cache if it is enabled.
	*
	* @param corpEntry the corp entry
	*/
	public static void cacheResult(com.liferay.osb.model.CorpEntry corpEntry) {
		getPersistence().cacheResult(corpEntry);
	}

	/**
	* Caches the corp entries in the entity cache if it is enabled.
	*
	* @param corpEntries the corp entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CorpEntry> corpEntries) {
		getPersistence().cacheResult(corpEntries);
	}

	/**
	* Creates a new corp entry with the primary key. Does not add the corp entry to the database.
	*
	* @param corpEntryId the primary key for the new corp entry
	* @return the new corp entry
	*/
	public static com.liferay.osb.model.CorpEntry create(long corpEntryId) {
		return getPersistence().create(corpEntryId);
	}

	/**
	* Removes the corp entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry that was removed
	* @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry remove(long corpEntryId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(corpEntryId);
	}

	public static com.liferay.osb.model.CorpEntry updateImpl(
		com.liferay.osb.model.CorpEntry corpEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(corpEntry, merge);
	}

	/**
	* Returns the corp entry with the primary key or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry findByPrimaryKey(
		long corpEntryId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(corpEntryId);
	}

	/**
	* Returns the corp entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry, or <code>null</code> if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByPrimaryKey(
		long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(corpEntryId);
	}

	/**
	* Returns all the corp entries where name = &#63;.
	*
	* @param name the name
	* @return the matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last corp entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

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
	public static com.liferay.osb.model.CorpEntry[] findByName_PrevAndNext(
		long corpEntryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(corpEntryId, name, orderByComparator);
	}

	/**
	* Returns the corp entry where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByOrganizationId(organizationId);
	}

	/**
	* Returns the corp entry where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrganizationId(organizationId, retrieveFromCache);
	}

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching corp entry
	* @throws com.liferay.osb.NoSuchCorpEntryException if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the corp entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp entry, or <code>null</code> if a matching corp entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDossieraAccountKey(dossieraAccountKey,
			retrieveFromCache);
	}

	/**
	* Returns all the corp entries.
	*
	* @return the corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the corp entries where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Removes the corp entry where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Removes the corp entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the corp entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchCorpEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Removes all the corp entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of corp entries where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Returns the number of corp entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the number of corp entries.
	*
	* @return the number of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the corp groups associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @return the corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpGroups(pk);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpGroups(pk, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpGroups(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of corp groups associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @return the number of corp groups associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpGroupsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getCorpGroupsSize(pk);
	}

	/**
	* Returns <code>true</code> if the corp group is associated with the corp entry.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @return <code>true</code> if the corp group is associated with the corp entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsCorpGroup(pk, corpGroupPK);
	}

	/**
	* Returns <code>true</code> if the corp entry has any corp groups associated with it.
	*
	* @param pk the primary key of the corp entry to check for associations with corp groups
	* @return <code>true</code> if the corp entry has any corp groups associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsCorpGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsCorpGroups(pk);
	}

	/**
	* Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpGroup(pk, corpGroupPK);
	}

	/**
	* Adds an association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroup the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpGroup(long pk,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpGroup(pk, corpGroup);
	}

	/**
	* Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpGroups(pk, corpGroupPKs);
	}

	/**
	* Adds an association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addCorpGroups(pk, corpGroups);
	}

	/**
	* Clears all associations between the corp entry and its corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry to clear the associated corp groups from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearCorpGroups(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearCorpGroups(pk);
	}

	/**
	* Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPK the primary key of the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpGroup(long pk, long corpGroupPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpGroup(pk, corpGroupPK);
	}

	/**
	* Removes the association between the corp entry and the corp group. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroup the corp group
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpGroup(long pk,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpGroup(pk, corpGroup);
	}

	/**
	* Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpGroups(pk, corpGroupPKs);
	}

	/**
	* Removes the association between the corp entry and the corp groups. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static void removeCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeCorpGroups(pk, corpGroups);
	}

	/**
	* Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroupPKs the primary keys of the corp groups to be associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setCorpGroups(long pk, long[] corpGroupPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setCorpGroups(pk, corpGroupPKs);
	}

	/**
	* Sets the corp groups associated with the corp entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the corp entry
	* @param corpGroups the corp groups to be associated with the corp entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setCorpGroups(long pk,
		java.util.List<com.liferay.osb.model.CorpGroup> corpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setCorpGroups(pk, corpGroups);
	}

	public static CorpEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(CorpEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CorpEntryPersistence persistence) {
	}

	private static CorpEntryPersistence _persistence;
}