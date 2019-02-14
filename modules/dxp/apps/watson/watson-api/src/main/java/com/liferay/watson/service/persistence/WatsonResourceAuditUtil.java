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

import com.liferay.watson.model.WatsonResourceAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson resource audit service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonResourceAuditPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonResourceAuditPersistence
 * @generated
 */
@ProviderType
public class WatsonResourceAuditUtil {
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
	public static void clearCache(WatsonResourceAudit watsonResourceAudit) {
		getPersistence().clearCache(watsonResourceAudit);
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
	public static Map<Serializable, WatsonResourceAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonResourceAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonResourceAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonResourceAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonResourceAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonResourceAudit update(
		WatsonResourceAudit watsonResourceAudit) {
		return getPersistence().update(watsonResourceAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonResourceAudit update(
		WatsonResourceAudit watsonResourceAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonResourceAudit, serviceContext);
	}

	/**
	* Caches the watson resource audit in the entity cache if it is enabled.
	*
	* @param watsonResourceAudit the watson resource audit
	*/
	public static void cacheResult(WatsonResourceAudit watsonResourceAudit) {
		getPersistence().cacheResult(watsonResourceAudit);
	}

	/**
	* Caches the watson resource audits in the entity cache if it is enabled.
	*
	* @param watsonResourceAudits the watson resource audits
	*/
	public static void cacheResult(
		List<WatsonResourceAudit> watsonResourceAudits) {
		getPersistence().cacheResult(watsonResourceAudits);
	}

	/**
	* Creates a new watson resource audit with the primary key. Does not add the watson resource audit to the database.
	*
	* @param watsonResourceAuditId the primary key for the new watson resource audit
	* @return the new watson resource audit
	*/
	public static WatsonResourceAudit create(long watsonResourceAuditId) {
		return getPersistence().create(watsonResourceAuditId);
	}

	/**
	* Removes the watson resource audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit that was removed
	* @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	*/
	public static WatsonResourceAudit remove(long watsonResourceAuditId)
		throws com.liferay.watson.exception.NoSuchResourceAuditException {
		return getPersistence().remove(watsonResourceAuditId);
	}

	public static WatsonResourceAudit updateImpl(
		WatsonResourceAudit watsonResourceAudit) {
		return getPersistence().updateImpl(watsonResourceAudit);
	}

	/**
	* Returns the watson resource audit with the primary key or throws a <code>NoSuchResourceAuditException</code> if it could not be found.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit
	* @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	*/
	public static WatsonResourceAudit findByPrimaryKey(
		long watsonResourceAuditId)
		throws com.liferay.watson.exception.NoSuchResourceAuditException {
		return getPersistence().findByPrimaryKey(watsonResourceAuditId);
	}

	/**
	* Returns the watson resource audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit, or <code>null</code> if a watson resource audit with the primary key could not be found
	*/
	public static WatsonResourceAudit fetchByPrimaryKey(
		long watsonResourceAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonResourceAuditId);
	}

	/**
	* Returns all the watson resource audits.
	*
	* @return the watson resource audits
	*/
	public static List<WatsonResourceAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @return the range of watson resource audits
	*/
	public static List<WatsonResourceAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson resource audits
	*/
	public static List<WatsonResourceAudit> findAll(int start, int end,
		OrderByComparator<WatsonResourceAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson resource audits
	*/
	public static List<WatsonResourceAudit> findAll(int start, int end,
		OrderByComparator<WatsonResourceAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson resource audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson resource audits.
	*
	* @return the number of watson resource audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonResourceAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonResourceAuditPersistence, WatsonResourceAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonResourceAuditPersistence.class);

		ServiceTracker<WatsonResourceAuditPersistence, WatsonResourceAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonResourceAuditPersistence, WatsonResourceAuditPersistence>(bundle.getBundleContext(),
				WatsonResourceAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}