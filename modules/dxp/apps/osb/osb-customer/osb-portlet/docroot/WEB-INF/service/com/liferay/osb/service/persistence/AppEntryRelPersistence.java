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

import com.liferay.osb.model.AppEntryRel;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app entry rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryRelPersistenceImpl
 * @see AppEntryRelUtil
 * @generated
 */
public interface AppEntryRelPersistence extends BasePersistence<AppEntryRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppEntryRelUtil} to access the app entry rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the app entry rel in the entity cache if it is enabled.
	*
	* @param appEntryRel the app entry rel
	*/
	public void cacheResult(com.liferay.osb.model.AppEntryRel appEntryRel);

	/**
	* Caches the app entry rels in the entity cache if it is enabled.
	*
	* @param appEntryRels the app entry rels
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AppEntryRel> appEntryRels);

	/**
	* Creates a new app entry rel with the primary key. Does not add the app entry rel to the database.
	*
	* @param appEntryRelId the primary key for the new app entry rel
	* @return the new app entry rel
	*/
	public com.liferay.osb.model.AppEntryRel create(long appEntryRelId);

	/**
	* Removes the app entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryRelId the primary key of the app entry rel
	* @return the app entry rel that was removed
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel remove(long appEntryRelId)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntryRel updateImpl(
		com.liferay.osb.model.AppEntryRel appEntryRel, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rel with the primary key or throws a {@link com.liferay.osb.NoSuchAppEntryRelException} if it could not be found.
	*
	* @param appEntryRelId the primary key of the app entry rel
	* @return the app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByPrimaryKey(
		long appEntryRelId)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appEntryRelId the primary key of the app entry rel
	* @return the app entry rel, or <code>null</code> if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByPrimaryKey(
		long appEntryRelId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entry rels where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entry rels where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @return the range of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entry rels where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry rel in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry rel in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry rel in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry rel in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rels before and after the current app entry rel in the ordered set where uuid = &#63;.
	*
	* @param appEntryRelId the primary key of the current app entry rel
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel[] findByUuid_PrevAndNext(
		long appEntryRelId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @return the matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByAEI1_T(
		long appEntryId1, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @return the range of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByAEI1_T(
		long appEntryId1, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findByAEI1_T(
		long appEntryId1, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByAEI1_T_First(
		long appEntryId1, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByAEI1_T_First(
		long appEntryId1, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByAEI1_T_Last(
		long appEntryId1, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByAEI1_T_Last(
		long appEntryId1, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rels before and after the current app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryRelId the primary key of the current app entry rel
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel[] findByAEI1_T_PrevAndNext(
		long appEntryRelId, long appEntryId1, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAppEntryRelException} if it could not be found.
	*
	* @param appEntryId1 the app entry id1
	* @param appEntryId2 the app entry id2
	* @param type the type
	* @return the matching app entry rel
	* @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel findByAEI1_AEI2_T(
		long appEntryId1, long appEntryId2, int type)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appEntryId1 the app entry id1
	* @param appEntryId2 the app entry id2
	* @param type the type
	* @return the matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByAEI1_AEI2_T(
		long appEntryId1, long appEntryId2, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appEntryId1 the app entry id1
	* @param appEntryId2 the app entry id2
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel fetchByAEI1_AEI2_T(
		long appEntryId1, long appEntryId2, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the app entry rels.
	*
	* @return the app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @return the range of app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the app entry rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entry rels
	* @param end the upper bound of the range of app entry rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppEntryRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entry rels where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entry rels where appEntryId1 = &#63; and type = &#63; from the database.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI1_T(long appEntryId1, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; from the database.
	*
	* @param appEntryId1 the app entry id1
	* @param appEntryId2 the app entry id2
	* @param type the type
	* @return the app entry rel that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntryRel removeByAEI1_AEI2_T(
		long appEntryId1, long appEntryId2, int type)
		throws com.liferay.osb.NoSuchAppEntryRelException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the app entry rels from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entry rels where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entry rels where appEntryId1 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param type the type
	* @return the number of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI1_T(long appEntryId1, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entry rels where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63;.
	*
	* @param appEntryId1 the app entry id1
	* @param appEntryId2 the app entry id2
	* @param type the type
	* @return the number of matching app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI1_AEI2_T(long appEntryId1, long appEntryId2, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entry rels.
	*
	* @return the number of app entry rels
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}