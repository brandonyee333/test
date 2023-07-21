/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.wsrp.exception.NoSuchConsumerException;
import com.liferay.wsrp.model.WSRPConsumer;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the wsrp consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerUtil
 * @generated
 */
@ProviderType
public interface WSRPConsumerPersistence extends BasePersistence<WSRPConsumer> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPConsumerUtil} to access the wsrp consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WSRPConsumer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the wsrp consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching wsrp consumers
	 */
	public java.util.List<WSRPConsumer> findByUuid(String uuid);

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
	public java.util.List<WSRPConsumer> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<WSRPConsumer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

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
	public java.util.List<WSRPConsumer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

	/**
	 * Returns the wsrp consumers before and after the current wsrp consumer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current wsrp consumer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public WSRPConsumer[] findByUuid_PrevAndNext(
			long wsrpConsumerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Removes all the wsrp consumers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of wsrp consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching wsrp consumers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching wsrp consumers
	 */
	public java.util.List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

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
	public java.util.List<WSRPConsumer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the first wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the last wsrp consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

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
	public WSRPConsumer[] findByUuid_C_PrevAndNext(
			long wsrpConsumerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Removes all the wsrp consumers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of wsrp consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching wsrp consumers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the wsrp consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching wsrp consumers
	 */
	public java.util.List<WSRPConsumer> findByCompanyId(long companyId);

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
	public java.util.List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

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
	public java.util.List<WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the first wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

	/**
	 * Returns the last wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer
	 * @throws NoSuchConsumerException if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Returns the last wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp consumer, or <code>null</code> if a matching wsrp consumer could not be found
	 */
	public WSRPConsumer fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

	/**
	 * Returns the wsrp consumers before and after the current wsrp consumer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current wsrp consumer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public WSRPConsumer[] findByCompanyId_PrevAndNext(
			long wsrpConsumerId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
				orderByComparator)
		throws NoSuchConsumerException;

	/**
	 * Removes all the wsrp consumers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of wsrp consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching wsrp consumers
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Caches the wsrp consumer in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumer the wsrp consumer
	 */
	public void cacheResult(WSRPConsumer wsrpConsumer);

	/**
	 * Caches the wsrp consumers in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumers the wsrp consumers
	 */
	public void cacheResult(java.util.List<WSRPConsumer> wsrpConsumers);

	/**
	 * Creates a new wsrp consumer with the primary key. Does not add the wsrp consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new wsrp consumer
	 * @return the new wsrp consumer
	 */
	public WSRPConsumer create(long wsrpConsumerId);

	/**
	 * Removes the wsrp consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer that was removed
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public WSRPConsumer remove(long wsrpConsumerId)
		throws NoSuchConsumerException;

	public WSRPConsumer updateImpl(WSRPConsumer wsrpConsumer);

	/**
	 * Returns the wsrp consumer with the primary key or throws a <code>NoSuchConsumerException</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer
	 * @throws NoSuchConsumerException if a wsrp consumer with the primary key could not be found
	 */
	public WSRPConsumer findByPrimaryKey(long wsrpConsumerId)
		throws NoSuchConsumerException;

	/**
	 * Returns the wsrp consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the wsrp consumer
	 * @return the wsrp consumer, or <code>null</code> if a wsrp consumer with the primary key could not be found
	 */
	public WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId);

	/**
	 * Returns all the wsrp consumers.
	 *
	 * @return the wsrp consumers
	 */
	public java.util.List<WSRPConsumer> findAll();

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
	public java.util.List<WSRPConsumer> findAll(int start, int end);

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
	public java.util.List<WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator);

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
	public java.util.List<WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPConsumer>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the wsrp consumers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of wsrp consumers.
	 *
	 * @return the number of wsrp consumers
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}