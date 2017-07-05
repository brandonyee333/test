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

import com.liferay.osb.model.SupportWorkerSeverity;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the support worker severity service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SupportWorkerSeverityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverityPersistence
 * @see com.liferay.osb.service.persistence.impl.SupportWorkerSeverityPersistenceImpl
 * @generated
 */
@ProviderType
public class SupportWorkerSeverityUtil {
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
	public static void clearCache(SupportWorkerSeverity supportWorkerSeverity) {
		getPersistence().clearCache(supportWorkerSeverity);
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
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportWorkerSeverity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportWorkerSeverity update(
		SupportWorkerSeverity supportWorkerSeverity) {
		return getPersistence().update(supportWorkerSeverity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportWorkerSeverity update(
		SupportWorkerSeverity supportWorkerSeverity,
		ServiceContext serviceContext) {
		return getPersistence().update(supportWorkerSeverity, serviceContext);
	}

	/**
	* Returns all the support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the matching support worker severities
	*/
	public static List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId) {
		return getPersistence().findBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns a range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of matching support worker severities
	*/
	public static List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end);
	}

	/**
	* Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support worker severities
	*/
	public static List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the support worker severities where supportWorkerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportWorkerId the support worker ID
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support worker severities
	*/
	public static List<SupportWorkerSeverity> findBySupportWorkerId(
		long supportWorkerId, int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySupportWorkerId(supportWorkerId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	*/
	public static SupportWorkerSeverity findBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerSeverityException {
		return getPersistence()
				   .findBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the first support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	*/
	public static SupportWorkerSeverity fetchBySupportWorkerId_First(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return getPersistence()
				   .fetchBySupportWorkerId_First(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a matching support worker severity could not be found
	*/
	public static SupportWorkerSeverity findBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerSeverityException {
		return getPersistence()
				   .findBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the last support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support worker severity, or <code>null</code> if a matching support worker severity could not be found
	*/
	public static SupportWorkerSeverity fetchBySupportWorkerId_Last(
		long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return getPersistence()
				   .fetchBySupportWorkerId_Last(supportWorkerId,
			orderByComparator);
	}

	/**
	* Returns the support worker severities before and after the current support worker severity in the ordered set where supportWorkerId = &#63;.
	*
	* @param supportWorkerSeverityId the primary key of the current support worker severity
	* @param supportWorkerId the support worker ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public static SupportWorkerSeverity[] findBySupportWorkerId_PrevAndNext(
		long supportWorkerSeverityId, long supportWorkerId,
		OrderByComparator<SupportWorkerSeverity> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportWorkerSeverityException {
		return getPersistence()
				   .findBySupportWorkerId_PrevAndNext(supportWorkerSeverityId,
			supportWorkerId, orderByComparator);
	}

	/**
	* Removes all the support worker severities where supportWorkerId = &#63; from the database.
	*
	* @param supportWorkerId the support worker ID
	*/
	public static void removeBySupportWorkerId(long supportWorkerId) {
		getPersistence().removeBySupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the number of support worker severities where supportWorkerId = &#63;.
	*
	* @param supportWorkerId the support worker ID
	* @return the number of matching support worker severities
	*/
	public static int countBySupportWorkerId(long supportWorkerId) {
		return getPersistence().countBySupportWorkerId(supportWorkerId);
	}

	/**
	* Caches the support worker severity in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverity the support worker severity
	*/
	public static void cacheResult(SupportWorkerSeverity supportWorkerSeverity) {
		getPersistence().cacheResult(supportWorkerSeverity);
	}

	/**
	* Caches the support worker severities in the entity cache if it is enabled.
	*
	* @param supportWorkerSeverities the support worker severities
	*/
	public static void cacheResult(
		List<SupportWorkerSeverity> supportWorkerSeverities) {
		getPersistence().cacheResult(supportWorkerSeverities);
	}

	/**
	* Creates a new support worker severity with the primary key. Does not add the support worker severity to the database.
	*
	* @param supportWorkerSeverityId the primary key for the new support worker severity
	* @return the new support worker severity
	*/
	public static SupportWorkerSeverity create(long supportWorkerSeverityId) {
		return getPersistence().create(supportWorkerSeverityId);
	}

	/**
	* Removes the support worker severity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity that was removed
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public static SupportWorkerSeverity remove(long supportWorkerSeverityId)
		throws com.liferay.osb.exception.NoSuchSupportWorkerSeverityException {
		return getPersistence().remove(supportWorkerSeverityId);
	}

	public static SupportWorkerSeverity updateImpl(
		SupportWorkerSeverity supportWorkerSeverity) {
		return getPersistence().updateImpl(supportWorkerSeverity);
	}

	/**
	* Returns the support worker severity with the primary key or throws a {@link NoSuchSupportWorkerSeverityException} if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity
	* @throws NoSuchSupportWorkerSeverityException if a support worker severity with the primary key could not be found
	*/
	public static SupportWorkerSeverity findByPrimaryKey(
		long supportWorkerSeverityId)
		throws com.liferay.osb.exception.NoSuchSupportWorkerSeverityException {
		return getPersistence().findByPrimaryKey(supportWorkerSeverityId);
	}

	/**
	* Returns the support worker severity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportWorkerSeverityId the primary key of the support worker severity
	* @return the support worker severity, or <code>null</code> if a support worker severity with the primary key could not be found
	*/
	public static SupportWorkerSeverity fetchByPrimaryKey(
		long supportWorkerSeverityId) {
		return getPersistence().fetchByPrimaryKey(supportWorkerSeverityId);
	}

	public static java.util.Map<java.io.Serializable, SupportWorkerSeverity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the support worker severities.
	*
	* @return the support worker severities
	*/
	public static List<SupportWorkerSeverity> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @return the range of support worker severities
	*/
	public static List<SupportWorkerSeverity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support worker severities
	*/
	public static List<SupportWorkerSeverity> findAll(int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the support worker severities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportWorkerSeverityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker severities
	* @param end the upper bound of the range of support worker severities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support worker severities
	*/
	public static List<SupportWorkerSeverity> findAll(int start, int end,
		OrderByComparator<SupportWorkerSeverity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the support worker severities from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support worker severities.
	*
	* @return the number of support worker severities
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SupportWorkerSeverityPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportWorkerSeverityPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportWorkerSeverityPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportWorkerSeverityUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SupportWorkerSeverityPersistence _persistence;
}