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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonActivityAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson activity audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonActivityAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonActivityAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonActivityAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonActivityAuditUtil {
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
	public static void clearCache(WatsonActivityAudit watsonActivityAudit) {
		getPersistence().clearCache(watsonActivityAudit);
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
	public static List<WatsonActivityAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonActivityAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonActivityAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonActivityAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonActivityAudit update(
		WatsonActivityAudit watsonActivityAudit) {
		return getPersistence().update(watsonActivityAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonActivityAudit update(
		WatsonActivityAudit watsonActivityAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonActivityAudit, serviceContext);
	}

	/**
	* Caches the watson activity audit in the entity cache if it is enabled.
	*
	* @param watsonActivityAudit the watson activity audit
	*/
	public static void cacheResult(WatsonActivityAudit watsonActivityAudit) {
		getPersistence().cacheResult(watsonActivityAudit);
	}

	/**
	* Caches the watson activity audits in the entity cache if it is enabled.
	*
	* @param watsonActivityAudits the watson activity audits
	*/
	public static void cacheResult(
		List<WatsonActivityAudit> watsonActivityAudits) {
		getPersistence().cacheResult(watsonActivityAudits);
	}

	/**
	* Creates a new watson activity audit with the primary key. Does not add the watson activity audit to the database.
	*
	* @param watsonActivityAuditId the primary key for the new watson activity audit
	* @return the new watson activity audit
	*/
	public static WatsonActivityAudit create(long watsonActivityAuditId) {
		return getPersistence().create(watsonActivityAuditId);
	}

	/**
	* Removes the watson activity audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit that was removed
	* @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	*/
	public static WatsonActivityAudit remove(long watsonActivityAuditId)
		throws com.liferay.watson.exception.NoSuchActivityAuditException {
		return getPersistence().remove(watsonActivityAuditId);
	}

	public static WatsonActivityAudit updateImpl(
		WatsonActivityAudit watsonActivityAudit) {
		return getPersistence().updateImpl(watsonActivityAudit);
	}

	/**
	* Returns the watson activity audit with the primary key or throws a {@link NoSuchActivityAuditException} if it could not be found.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit
	* @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	*/
	public static WatsonActivityAudit findByPrimaryKey(
		long watsonActivityAuditId)
		throws com.liferay.watson.exception.NoSuchActivityAuditException {
		return getPersistence().findByPrimaryKey(watsonActivityAuditId);
	}

	/**
	* Returns the watson activity audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit, or <code>null</code> if a watson activity audit with the primary key could not be found
	*/
	public static WatsonActivityAudit fetchByPrimaryKey(
		long watsonActivityAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonActivityAuditId);
	}

	public static java.util.Map<java.io.Serializable, WatsonActivityAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson activity audits.
	*
	* @return the watson activity audits
	*/
	public static List<WatsonActivityAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @return the range of watson activity audits
	*/
	public static List<WatsonActivityAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson activity audits
	*/
	public static List<WatsonActivityAudit> findAll(int start, int end,
		OrderByComparator<WatsonActivityAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson activity audits
	*/
	public static List<WatsonActivityAudit> findAll(int start, int end,
		OrderByComparator<WatsonActivityAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson activity audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson activity audits.
	*
	* @return the number of watson activity audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonActivityAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonActivityAuditPersistence, WatsonActivityAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonActivityAuditPersistence.class);

		ServiceTracker<WatsonActivityAuditPersistence, WatsonActivityAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonActivityAuditPersistence, WatsonActivityAuditPersistence>(bundle.getBundleContext(),
				WatsonActivityAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}