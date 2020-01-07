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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.wsrp.exception.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the wsrp producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerUtil
 * @generated
 */
@ProviderType
public interface WSRPProducerPersistence extends BasePersistence<WSRPProducer> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPProducerUtil} to access the wsrp producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WSRPProducer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the wsrp producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid(String uuid);

	/**
	 * Returns a range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer[] findByUuid_PrevAndNext(
			long wsrpProducerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Removes all the wsrp producers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of wsrp producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching wsrp producers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchProducerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByUUID_G(String uuid, long groupId)
		throws NoSuchProducerException;

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the wsrp producer where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the wsrp producer that was removed
	 */
	public WSRPProducer removeByUUID_G(String uuid, long groupId)
		throws NoSuchProducerException;

	/**
	 * Returns the number of wsrp producers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching wsrp producers
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer[] findByUuid_C_PrevAndNext(
			long wsrpProducerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Removes all the wsrp producers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching wsrp producers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the wsrp producers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	public java.util.List<WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the first wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the last wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	public WSRPProducer findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Returns the last wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	public WSRPProducer fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer[] findByCompanyId_PrevAndNext(
			long wsrpProducerId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
				orderByComparator)
		throws NoSuchProducerException;

	/**
	 * Removes all the wsrp producers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of wsrp producers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching wsrp producers
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Caches the wsrp producer in the entity cache if it is enabled.
	 *
	 * @param wsrpProducer the wsrp producer
	 */
	public void cacheResult(WSRPProducer wsrpProducer);

	/**
	 * Caches the wsrp producers in the entity cache if it is enabled.
	 *
	 * @param wsrpProducers the wsrp producers
	 */
	public void cacheResult(java.util.List<WSRPProducer> wsrpProducers);

	/**
	 * Creates a new wsrp producer with the primary key. Does not add the wsrp producer to the database.
	 *
	 * @param wsrpProducerId the primary key for the new wsrp producer
	 * @return the new wsrp producer
	 */
	public WSRPProducer create(long wsrpProducerId);

	/**
	 * Removes the wsrp producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer that was removed
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer remove(long wsrpProducerId)
		throws NoSuchProducerException;

	public WSRPProducer updateImpl(WSRPProducer wsrpProducer);

	/**
	 * Returns the wsrp producer with the primary key or throws a <code>NoSuchProducerException</code> if it could not be found.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws NoSuchProducerException;

	/**
	 * Returns the wsrp producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer, or <code>null</code> if a wsrp producer with the primary key could not be found
	 */
	public WSRPProducer fetchByPrimaryKey(long wsrpProducerId);

	/**
	 * Returns all the wsrp producers.
	 *
	 * @return the wsrp producers
	 */
	public java.util.List<WSRPProducer> findAll();

	/**
	 * Returns a range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of wsrp producers
	 */
	public java.util.List<WSRPProducer> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wsrp producers
	 */
	public java.util.List<WSRPProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WSRPProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of wsrp producers
	 */
	public java.util.List<WSRPProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the wsrp producers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of wsrp producers.
	 *
	 * @return the number of wsrp producers
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}