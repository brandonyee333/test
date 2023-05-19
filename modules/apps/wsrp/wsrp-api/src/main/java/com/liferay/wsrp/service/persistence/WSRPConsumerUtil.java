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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wsrp.model.WSRPConsumer;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the wsrp consumer service. This utility wraps <code>com.liferay.wsrp.service.persistence.impl.WSRPConsumerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPersistence
 * @generated
 */
public class WSRPConsumerUtil {

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
	public static void clearCache(WSRPConsumer wsrpConsumer) {
		getPersistence().clearCache(wsrpConsumer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, WSRPConsumer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WSRPConsumer update(WSRPConsumer wsrpConsumer) {
		return getPersistence().update(wsrpConsumer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WSRPConsumer update(
		WSRPConsumer wsrpConsumer, ServiceContext serviceContext) {

		return getPersistence().update(wsrpConsumer, serviceContext);
	}

	/**
	 * Returns all the wsrp consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the wsrp consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @return the range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByUuid_First(
			String uuid, OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByUuid_First(
		String uuid, OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByUuid_Last(
			String uuid, OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByUuid_Last(
		String uuid, OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the wsrp consumers before and after the current wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current wsrp consumer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer[] findByUuid_PrevAndNext(
			long wsrpConsumerId, String uuid,
			OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_PrevAndNext(
			wsrpConsumerId, uuid, orderByComparator);
	}

	/**
	 * Removes all the wsrp consumers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of wsrp consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching wsrp consumers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @return the range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the wsrp consumers before and after the current wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current wsrp consumer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer[] findByUuid_C_PrevAndNext(
			long wsrpConsumerId, String uuid, long companyId,
			OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByUuid_C_PrevAndNext(
			wsrpConsumerId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the wsrp consumers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching wsrp consumers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the wsrp consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the wsrp consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @return the range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp consumers
	 */
	public static List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByCompanyId_First(
			long companyId, OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByCompanyId_First(
		long companyId, OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer findByCompanyId_Last(
			long companyId, OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public static WSRPConsumer fetchByCompanyId_Last(
		long companyId, OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the wsrp consumers before and after the current wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current wsrp consumer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer[] findByCompanyId_PrevAndNext(
			long wsrpConsumerId, long companyId,
			OrderByComparator<WSRPConsumer> orderByComparator)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByCompanyId_PrevAndNext(
			wsrpConsumerId, companyId, orderByComparator);
	}

	/**
	 * Removes all the wsrp consumers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of wsrp consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching wsrp consumers
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Caches the wsrp consumer in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumer the wsrp consumer
	 */
	public static void cacheResult(WSRPConsumer wsrpConsumer) {
		getPersistence().cacheResult(wsrpConsumer);
	}

	/**
	 * Caches the wsrp consumers in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumers the wsrp consumers
	 */
	public static void cacheResult(List<WSRPConsumer> wsrpConsumers) {
		getPersistence().cacheResult(wsrpConsumers);
	}

	/**
	 * Creates a new wsrp consumer with the primary key. Does not add the wsrp consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new wsrp consumer
	 * @return the new wsrp consumer
	 */
	public static WSRPConsumer create(long wsrpConsumerId) {
		return getPersistence().create(wsrpConsumerId);
	}

	/**
	 * Removes the wsrp consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer that was removed
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer remove(long wsrpConsumerId)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().remove(wsrpConsumerId);
	}

	public static WSRPConsumer updateImpl(WSRPConsumer wsrpConsumer) {
		return getPersistence().updateImpl(wsrpConsumer);
	}

	/**
	 * Returns the wsrp consumer with the primary key or throws a <code>NoSuchConsumerException</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer findByPrimaryKey(long wsrpConsumerId)
		throws com.liferay.wsrp.exception.NoSuchConsumerException {

		return getPersistence().findByPrimaryKey(wsrpConsumerId);
	}

	/**
	 * Returns the wsrp consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer, or <code>null</code> if a wsrp consumer with the primary key could not be found
	 */
	public static WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId) {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerId);
	}

	/**
	 * Returns all the wsrp consumers.
	 *
	 * @return the wsrp consumers
	 */
	public static List<WSRPConsumer> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the wsrp consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @return the range of wsrp consumers
	 */
	public static List<WSRPConsumer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wsrp consumers
	 */
	public static List<WSRPConsumer> findAll(
		int start, int end, OrderByComparator<WSRPConsumer> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the wsrp consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPConsumerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumers
	 * @param end the upper bound of the range of wsrp consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wsrp consumers
	 */
	public static List<WSRPConsumer> findAll(
		int start, int end, OrderByComparator<WSRPConsumer> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the wsrp consumers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of wsrp consumers.
	 *
	 * @return the number of wsrp consumers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WSRPConsumerPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(WSRPConsumerPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile WSRPConsumerPersistence _persistence;

}