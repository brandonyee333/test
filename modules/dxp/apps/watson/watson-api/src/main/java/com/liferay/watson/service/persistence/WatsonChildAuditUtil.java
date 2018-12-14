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

import com.liferay.watson.model.WatsonChildAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson child audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonChildAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChildAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonChildAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonChildAuditUtil {
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
	public static void clearCache(WatsonChildAudit watsonChildAudit) {
		getPersistence().clearCache(watsonChildAudit);
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
	public static Map<Serializable, WatsonChildAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonChildAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonChildAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonChildAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonChildAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonChildAudit update(WatsonChildAudit watsonChildAudit) {
		return getPersistence().update(watsonChildAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonChildAudit update(WatsonChildAudit watsonChildAudit,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonChildAudit, serviceContext);
	}

	/**
	* Caches the watson child audit in the entity cache if it is enabled.
	*
	* @param watsonChildAudit the watson child audit
	*/
	public static void cacheResult(WatsonChildAudit watsonChildAudit) {
		getPersistence().cacheResult(watsonChildAudit);
	}

	/**
	* Caches the watson child audits in the entity cache if it is enabled.
	*
	* @param watsonChildAudits the watson child audits
	*/
	public static void cacheResult(List<WatsonChildAudit> watsonChildAudits) {
		getPersistence().cacheResult(watsonChildAudits);
	}

	/**
	* Creates a new watson child audit with the primary key. Does not add the watson child audit to the database.
	*
	* @param watsonChildAuditId the primary key for the new watson child audit
	* @return the new watson child audit
	*/
	public static WatsonChildAudit create(long watsonChildAuditId) {
		return getPersistence().create(watsonChildAuditId);
	}

	/**
	* Removes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit that was removed
	* @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	*/
	public static WatsonChildAudit remove(long watsonChildAuditId)
		throws com.liferay.watson.exception.NoSuchChildAuditException {
		return getPersistence().remove(watsonChildAuditId);
	}

	public static WatsonChildAudit updateImpl(WatsonChildAudit watsonChildAudit) {
		return getPersistence().updateImpl(watsonChildAudit);
	}

	/**
	* Returns the watson child audit with the primary key or throws a {@link NoSuchChildAuditException} if it could not be found.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit
	* @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	*/
	public static WatsonChildAudit findByPrimaryKey(long watsonChildAuditId)
		throws com.liferay.watson.exception.NoSuchChildAuditException {
		return getPersistence().findByPrimaryKey(watsonChildAuditId);
	}

	/**
	* Returns the watson child audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit, or <code>null</code> if a watson child audit with the primary key could not be found
	*/
	public static WatsonChildAudit fetchByPrimaryKey(long watsonChildAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonChildAuditId);
	}

	/**
	* Returns all the watson child audits.
	*
	* @return the watson child audits
	*/
	public static List<WatsonChildAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @return the range of watson child audits
	*/
	public static List<WatsonChildAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson child audits
	*/
	public static List<WatsonChildAudit> findAll(int start, int end,
		OrderByComparator<WatsonChildAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson child audits
	*/
	public static List<WatsonChildAudit> findAll(int start, int end,
		OrderByComparator<WatsonChildAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson child audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson child audits.
	*
	* @return the number of watson child audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonChildAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonChildAuditPersistence, WatsonChildAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonChildAuditPersistence.class);

		ServiceTracker<WatsonChildAuditPersistence, WatsonChildAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonChildAuditPersistence, WatsonChildAuditPersistence>(bundle.getBundleContext(),
				WatsonChildAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}