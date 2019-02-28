/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.watson.model.WatsonVehicleAudit;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the watson vehicle audit service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonVehicleAuditPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonVehicleAuditPersistence
 * @generated
 */
@ProviderType
public class WatsonVehicleAuditUtil {

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
	public static void clearCache(WatsonVehicleAudit watsonVehicleAudit) {
		getPersistence().clearCache(watsonVehicleAudit);
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
	public static Map<Serializable, WatsonVehicleAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonVehicleAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonVehicleAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonVehicleAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonVehicleAudit> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonVehicleAudit update(
		WatsonVehicleAudit watsonVehicleAudit) {

		return getPersistence().update(watsonVehicleAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonVehicleAudit update(
		WatsonVehicleAudit watsonVehicleAudit, ServiceContext serviceContext) {

		return getPersistence().update(watsonVehicleAudit, serviceContext);
	}

	/**
	 * Caches the watson vehicle audit in the entity cache if it is enabled.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 */
	public static void cacheResult(WatsonVehicleAudit watsonVehicleAudit) {
		getPersistence().cacheResult(watsonVehicleAudit);
	}

	/**
	 * Caches the watson vehicle audits in the entity cache if it is enabled.
	 *
	 * @param watsonVehicleAudits the watson vehicle audits
	 */
	public static void cacheResult(
		List<WatsonVehicleAudit> watsonVehicleAudits) {

		getPersistence().cacheResult(watsonVehicleAudits);
	}

	/**
	 * Creates a new watson vehicle audit with the primary key. Does not add the watson vehicle audit to the database.
	 *
	 * @param watsonVehicleAuditId the primary key for the new watson vehicle audit
	 * @return the new watson vehicle audit
	 */
	public static WatsonVehicleAudit create(long watsonVehicleAuditId) {
		return getPersistence().create(watsonVehicleAuditId);
	}

	/**
	 * Removes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	public static WatsonVehicleAudit remove(long watsonVehicleAuditId)
		throws com.liferay.watson.exception.NoSuchVehicleAuditException {

		return getPersistence().remove(watsonVehicleAuditId);
	}

	public static WatsonVehicleAudit updateImpl(
		WatsonVehicleAudit watsonVehicleAudit) {

		return getPersistence().updateImpl(watsonVehicleAudit);
	}

	/**
	 * Returns the watson vehicle audit with the primary key or throws a <code>NoSuchVehicleAuditException</code> if it could not be found.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit
	 * @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	 */
	public static WatsonVehicleAudit findByPrimaryKey(long watsonVehicleAuditId)
		throws com.liferay.watson.exception.NoSuchVehicleAuditException {

		return getPersistence().findByPrimaryKey(watsonVehicleAuditId);
	}

	/**
	 * Returns the watson vehicle audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit, or <code>null</code> if a watson vehicle audit with the primary key could not be found
	 */
	public static WatsonVehicleAudit fetchByPrimaryKey(
		long watsonVehicleAuditId) {

		return getPersistence().fetchByPrimaryKey(watsonVehicleAuditId);
	}

	/**
	 * Returns all the watson vehicle audits.
	 *
	 * @return the watson vehicle audits
	 */
	public static List<WatsonVehicleAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @return the range of watson vehicle audits
	 */
	public static List<WatsonVehicleAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson vehicle audits
	 */
	public static List<WatsonVehicleAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonVehicleAudit> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson vehicle audits
	 */
	public static List<WatsonVehicleAudit> findAll(
		int start, int end,
		OrderByComparator<WatsonVehicleAudit> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the watson vehicle audits from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of watson vehicle audits.
	 *
	 * @return the number of watson vehicle audits
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonVehicleAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonVehicleAuditPersistence, WatsonVehicleAuditPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonVehicleAuditPersistence.class);

		ServiceTracker
			<WatsonVehicleAuditPersistence, WatsonVehicleAuditPersistence>
				serviceTracker =
					new ServiceTracker
						<WatsonVehicleAuditPersistence,
						 WatsonVehicleAuditPersistence>(
							 bundle.getBundleContext(),
							 WatsonVehicleAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}