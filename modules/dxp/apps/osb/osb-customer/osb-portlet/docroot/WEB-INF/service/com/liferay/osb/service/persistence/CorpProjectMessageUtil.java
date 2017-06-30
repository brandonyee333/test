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

import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the corp project message service. This utility wraps {@link CorpProjectMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessagePersistence
 * @see CorpProjectMessagePersistenceImpl
 * @generated
 */
public class CorpProjectMessageUtil {
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
	public static void clearCache(CorpProjectMessage corpProjectMessage) {
		getPersistence().clearCache(corpProjectMessage);
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
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CorpProjectMessage update(
		CorpProjectMessage corpProjectMessage, boolean merge)
		throws SystemException {
		return getPersistence().update(corpProjectMessage, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CorpProjectMessage update(
		CorpProjectMessage corpProjectMessage, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(corpProjectMessage, merge, serviceContext);
	}

	/**
	* Caches the corp project message in the entity cache if it is enabled.
	*
	* @param corpProjectMessage the corp project message
	*/
	public static void cacheResult(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage) {
		getPersistence().cacheResult(corpProjectMessage);
	}

	/**
	* Caches the corp project messages in the entity cache if it is enabled.
	*
	* @param corpProjectMessages the corp project messages
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CorpProjectMessage> corpProjectMessages) {
		getPersistence().cacheResult(corpProjectMessages);
	}

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	public static com.liferay.osb.model.CorpProjectMessage create(
		long corpProjectMessageId) {
		return getPersistence().create(corpProjectMessageId);
	}

	/**
	* Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage remove(
		long corpProjectMessageId)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(corpProjectMessageId);
	}

	public static com.liferay.osb.model.CorpProjectMessage updateImpl(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(corpProjectMessage, merge);
	}

	/**
	* Returns the corp project message with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByPrimaryKey(
		long corpProjectMessageId)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(corpProjectMessageId);
	}

	/**
	* Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByPrimaryKey(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(corpProjectMessageId);
	}

	/**
	* Returns all the corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCorpProjectId(corpProjectId);
	}

	/**
	* Returns a range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCorpProjectId(corpProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCorpProjectId(corpProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByCorpProjectId_First(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCorpProjectId_First(corpProjectId, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByCorpProjectId_First(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCorpProjectId_First(corpProjectId, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByCorpProjectId_Last(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCorpProjectId_Last(corpProjectId, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByCorpProjectId_Last(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCorpProjectId_Last(corpProjectId, orderByComparator);
	}

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCorpProjectId_PrevAndNext(corpProjectMessageId,
			corpProjectId, orderByComparator);
	}

	/**
	* Returns all the corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(corpProjectMessageId, type,
			orderByComparator);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage findByC_T(
		long corpProjectId, int type)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_T(corpProjectId, type);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByC_T(
		long corpProjectId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_T(corpProjectId, type);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage fetchByC_T(
		long corpProjectId, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_T(corpProjectId, type, retrieveFromCache);
	}

	/**
	* Returns all the corp project messages.
	*
	* @return the corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the corp project messages where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCorpProjectId(long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCorpProjectId(corpProjectId);
	}

	/**
	* Removes all the corp project messages where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the corp project message that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage removeByC_T(
		long corpProjectId, int type)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_T(corpProjectId, type);
	}

	/**
	* Removes all the corp project messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCorpProjectId(long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the number of corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_T(long corpProjectId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_T(corpProjectId, type);
	}

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CorpProjectMessagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpProjectMessagePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpProjectMessagePersistence.class.getName());

			ReferenceRegistry.registerReference(CorpProjectMessageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CorpProjectMessagePersistence persistence) {
	}

	private static CorpProjectMessagePersistence _persistence;
}