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

import com.liferay.watson.model.WatsonAddressAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson address audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonAddressAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonAddressAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonAddressAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonAddressAuditUtil {
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
	public static void clearCache(WatsonAddressAudit watsonAddressAudit) {
		getPersistence().clearCache(watsonAddressAudit);
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
	public static List<WatsonAddressAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonAddressAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonAddressAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonAddressAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonAddressAudit update(
		WatsonAddressAudit watsonAddressAudit) {
		return getPersistence().update(watsonAddressAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonAddressAudit update(
		WatsonAddressAudit watsonAddressAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonAddressAudit, serviceContext);
	}

	/**
	* Caches the watson address audit in the entity cache if it is enabled.
	*
	* @param watsonAddressAudit the watson address audit
	*/
	public static void cacheResult(WatsonAddressAudit watsonAddressAudit) {
		getPersistence().cacheResult(watsonAddressAudit);
	}

	/**
	* Caches the watson address audits in the entity cache if it is enabled.
	*
	* @param watsonAddressAudits the watson address audits
	*/
	public static void cacheResult(List<WatsonAddressAudit> watsonAddressAudits) {
		getPersistence().cacheResult(watsonAddressAudits);
	}

	/**
	* Creates a new watson address audit with the primary key. Does not add the watson address audit to the database.
	*
	* @param watsonAddressAuditId the primary key for the new watson address audit
	* @return the new watson address audit
	*/
	public static WatsonAddressAudit create(long watsonAddressAuditId) {
		return getPersistence().create(watsonAddressAuditId);
	}

	/**
	* Removes the watson address audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonAddressAuditId the primary key of the watson address audit
	* @return the watson address audit that was removed
	* @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	*/
	public static WatsonAddressAudit remove(long watsonAddressAuditId)
		throws com.liferay.watson.exception.NoSuchAddressAuditException {
		return getPersistence().remove(watsonAddressAuditId);
	}

	public static WatsonAddressAudit updateImpl(
		WatsonAddressAudit watsonAddressAudit) {
		return getPersistence().updateImpl(watsonAddressAudit);
	}

	/**
	* Returns the watson address audit with the primary key or throws a {@link NoSuchAddressAuditException} if it could not be found.
	*
	* @param watsonAddressAuditId the primary key of the watson address audit
	* @return the watson address audit
	* @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	*/
	public static WatsonAddressAudit findByPrimaryKey(long watsonAddressAuditId)
		throws com.liferay.watson.exception.NoSuchAddressAuditException {
		return getPersistence().findByPrimaryKey(watsonAddressAuditId);
	}

	/**
	* Returns the watson address audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonAddressAuditId the primary key of the watson address audit
	* @return the watson address audit, or <code>null</code> if a watson address audit with the primary key could not be found
	*/
	public static WatsonAddressAudit fetchByPrimaryKey(
		long watsonAddressAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonAddressAuditId);
	}

	public static java.util.Map<java.io.Serializable, WatsonAddressAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson address audits.
	*
	* @return the watson address audits
	*/
	public static List<WatsonAddressAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson address audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson address audits
	* @param end the upper bound of the range of watson address audits (not inclusive)
	* @return the range of watson address audits
	*/
	public static List<WatsonAddressAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson address audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson address audits
	* @param end the upper bound of the range of watson address audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson address audits
	*/
	public static List<WatsonAddressAudit> findAll(int start, int end,
		OrderByComparator<WatsonAddressAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson address audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson address audits
	* @param end the upper bound of the range of watson address audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson address audits
	*/
	public static List<WatsonAddressAudit> findAll(int start, int end,
		OrderByComparator<WatsonAddressAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson address audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson address audits.
	*
	* @return the number of watson address audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonAddressAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonAddressAuditPersistence, WatsonAddressAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonAddressAuditPersistence.class);

		ServiceTracker<WatsonAddressAuditPersistence, WatsonAddressAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonAddressAuditPersistence, WatsonAddressAuditPersistence>(bundle.getBundleContext(),
				WatsonAddressAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}