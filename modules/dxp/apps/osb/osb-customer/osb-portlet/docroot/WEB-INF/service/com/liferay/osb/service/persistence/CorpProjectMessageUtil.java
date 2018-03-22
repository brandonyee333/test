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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the corp project message service. This utility wraps {@link com.liferay.osb.service.persistence.impl.CorpProjectMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessagePersistence
 * @see com.liferay.osb.service.persistence.impl.CorpProjectMessagePersistenceImpl
 * @generated
 */
@ProviderType
public class CorpProjectMessageUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CorpProjectMessage corpProjectMessage) {
		getPersistence().clearCache(corpProjectMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpProjectMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CorpProjectMessage update(
		CorpProjectMessage corpProjectMessage) {
		return getPersistence().update(corpProjectMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CorpProjectMessage update(
		CorpProjectMessage corpProjectMessage, ServiceContext serviceContext) {
		return getPersistence().update(corpProjectMessage, serviceContext);
	}

	/**
	* Returns all the corp project messages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching corp project messages
	*/
	public static List<CorpProjectMessage> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByUuid_First(java.lang.String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where uuid = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage[] findByUuid_PrevAndNext(
		long corpProjectMessageId, java.lang.String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence()
				   .findByUuid_PrevAndNext(corpProjectMessageId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the corp project messages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of corp project messages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching corp project messages
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the matching corp project messages
	*/
	public static List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId) {
		return getPersistence().findByCorpProjectId(corpProjectId);
	}

	/**
	* Returns a range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end) {
		return getPersistence().findByCorpProjectId(corpProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence()
				   .findByCorpProjectId(corpProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCorpProjectId(corpProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByCorpProjectId_First(
		long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence()
				   .findByCorpProjectId_First(corpProjectId, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByCorpProjectId_First(
		long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence()
				   .fetchByCorpProjectId_First(corpProjectId, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByCorpProjectId_Last(
		long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence()
				   .findByCorpProjectId_Last(corpProjectId, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByCorpProjectId_Last(
		long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
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
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence()
				   .findByCorpProjectId_PrevAndNext(corpProjectMessageId,
			corpProjectId, orderByComparator);
	}

	/**
	* Removes all the corp project messages where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	*/
	public static void removeByCorpProjectId(long corpProjectId) {
		getPersistence().removeByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the number of corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching corp project messages
	*/
	public static int countByCorpProjectId(long corpProjectId) {
		return getPersistence().countByCorpProjectId(corpProjectId);
	}

	/**
	* Returns all the corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the matching corp project messages
	*/
	public static List<CorpProjectMessage> findByType(int type) {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByType(int type, int start,
		int end) {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByType(int type, int start,
		int end, OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public static List<CorpProjectMessage> findByType(int type, int start,
		int end, OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByType(type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByType_First(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByType_First(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByType_Last(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByType_Last(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence()
				   .findByType_PrevAndNext(corpProjectMessageId, type,
			orderByComparator);
	}

	/**
	* Removes all the corp project messages where type = &#63; from the database.
	*
	* @param type the type
	*/
	public static void removeByType(int type) {
		getPersistence().removeByType(type);
	}

	/**
	* Returns the number of corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the number of matching corp project messages
	*/
	public static int countByType(int type) {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public static CorpProjectMessage findByC_T(long corpProjectId, int type)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByC_T(corpProjectId, type);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByC_T(long corpProjectId, int type) {
		return getPersistence().fetchByC_T(corpProjectId, type);
	}

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public static CorpProjectMessage fetchByC_T(long corpProjectId, int type,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_T(corpProjectId, type, retrieveFromCache);
	}

	/**
	* Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the corp project message that was removed
	*/
	public static CorpProjectMessage removeByC_T(long corpProjectId, int type)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().removeByC_T(corpProjectId, type);
	}

	/**
	* Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the number of matching corp project messages
	*/
	public static int countByC_T(long corpProjectId, int type) {
		return getPersistence().countByC_T(corpProjectId, type);
	}

	/**
	* Caches the corp project message in the entity cache if it is enabled.
	*
	* @param corpProjectMessage the corp project message
	*/
	public static void cacheResult(CorpProjectMessage corpProjectMessage) {
		getPersistence().cacheResult(corpProjectMessage);
	}

	/**
	* Caches the corp project messages in the entity cache if it is enabled.
	*
	* @param corpProjectMessages the corp project messages
	*/
	public static void cacheResult(List<CorpProjectMessage> corpProjectMessages) {
		getPersistence().cacheResult(corpProjectMessages);
	}

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	public static CorpProjectMessage create(long corpProjectMessageId) {
		return getPersistence().create(corpProjectMessageId);
	}

	/**
	* Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage remove(long corpProjectMessageId)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().remove(corpProjectMessageId);
	}

	public static CorpProjectMessage updateImpl(
		CorpProjectMessage corpProjectMessage) {
		return getPersistence().updateImpl(corpProjectMessage);
	}

	/**
	* Returns the corp project message with the primary key or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage findByPrimaryKey(long corpProjectMessageId)
		throws com.liferay.osb.exception.NoSuchCorpProjectMessageException {
		return getPersistence().findByPrimaryKey(corpProjectMessageId);
	}

	/**
	* Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	*/
	public static CorpProjectMessage fetchByPrimaryKey(
		long corpProjectMessageId) {
		return getPersistence().fetchByPrimaryKey(corpProjectMessageId);
	}

	public static java.util.Map<java.io.Serializable, CorpProjectMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the corp project messages.
	*
	* @return the corp project messages
	*/
	public static List<CorpProjectMessage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	*/
	public static List<CorpProjectMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp project messages
	*/
	public static List<CorpProjectMessage> findAll(int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of corp project messages
	*/
	public static List<CorpProjectMessage> findAll(int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the corp project messages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static CorpProjectMessagePersistence _persistence;
}