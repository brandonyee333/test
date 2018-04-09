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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonPersonAudit;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson person audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonPersonAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonPersonAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonPersonAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonPersonAuditUtil {
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
	public static void clearCache(WatsonPersonAudit watsonPersonAudit) {
		getPersistence().clearCache(watsonPersonAudit);
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
	public static List<WatsonPersonAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonPersonAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonPersonAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonPersonAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonPersonAudit update(WatsonPersonAudit watsonPersonAudit) {
		return getPersistence().update(watsonPersonAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonPersonAudit update(
		WatsonPersonAudit watsonPersonAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonPersonAudit, serviceContext);
	}

	/**
	* Caches the watson person audit in the entity cache if it is enabled.
	*
	* @param watsonPersonAudit the watson person audit
	*/
	public static void cacheResult(WatsonPersonAudit watsonPersonAudit) {
		getPersistence().cacheResult(watsonPersonAudit);
	}

	/**
	* Caches the watson person audits in the entity cache if it is enabled.
	*
	* @param watsonPersonAudits the watson person audits
	*/
	public static void cacheResult(List<WatsonPersonAudit> watsonPersonAudits) {
		getPersistence().cacheResult(watsonPersonAudits);
	}

	/**
	* Creates a new watson person audit with the primary key. Does not add the watson person audit to the database.
	*
	* @param watsonPersonAuditId the primary key for the new watson person audit
	* @return the new watson person audit
	*/
	public static WatsonPersonAudit create(long watsonPersonAuditId) {
		return getPersistence().create(watsonPersonAuditId);
	}

	/**
	* Removes the watson person audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonPersonAuditId the primary key of the watson person audit
	* @return the watson person audit that was removed
	* @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	*/
	public static WatsonPersonAudit remove(long watsonPersonAuditId)
		throws com.liferay.watson.exception.NoSuchPersonAuditException {
		return getPersistence().remove(watsonPersonAuditId);
	}

	public static WatsonPersonAudit updateImpl(
		WatsonPersonAudit watsonPersonAudit) {
		return getPersistence().updateImpl(watsonPersonAudit);
	}

	/**
	* Returns the watson person audit with the primary key or throws a {@link NoSuchPersonAuditException} if it could not be found.
	*
	* @param watsonPersonAuditId the primary key of the watson person audit
	* @return the watson person audit
	* @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	*/
	public static WatsonPersonAudit findByPrimaryKey(long watsonPersonAuditId)
		throws com.liferay.watson.exception.NoSuchPersonAuditException {
		return getPersistence().findByPrimaryKey(watsonPersonAuditId);
	}

	/**
	* Returns the watson person audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonPersonAuditId the primary key of the watson person audit
	* @return the watson person audit, or <code>null</code> if a watson person audit with the primary key could not be found
	*/
	public static WatsonPersonAudit fetchByPrimaryKey(long watsonPersonAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonPersonAuditId);
	}

	public static java.util.Map<java.io.Serializable, WatsonPersonAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson person audits.
	*
	* @return the watson person audits
	*/
	public static List<WatsonPersonAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson person audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson person audits
	* @param end the upper bound of the range of watson person audits (not inclusive)
	* @return the range of watson person audits
	*/
	public static List<WatsonPersonAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson person audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson person audits
	* @param end the upper bound of the range of watson person audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson person audits
	*/
	public static List<WatsonPersonAudit> findAll(int start, int end,
		OrderByComparator<WatsonPersonAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson person audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonPersonAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson person audits
	* @param end the upper bound of the range of watson person audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson person audits
	*/
	public static List<WatsonPersonAudit> findAll(int start, int end,
		OrderByComparator<WatsonPersonAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson person audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson person audits.
	*
	* @return the number of watson person audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonPersonAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonPersonAuditPersistence, WatsonPersonAuditPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WatsonPersonAuditPersistence.class);
}