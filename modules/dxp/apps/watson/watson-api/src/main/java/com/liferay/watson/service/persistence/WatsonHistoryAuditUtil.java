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

import com.liferay.watson.model.WatsonHistoryAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson history audit service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonHistoryAuditPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryAuditPersistence
 * @generated
 */
@ProviderType
public class WatsonHistoryAuditUtil {
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
	public static void clearCache(WatsonHistoryAudit watsonHistoryAudit) {
		getPersistence().clearCache(watsonHistoryAudit);
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
	public static Map<Serializable, WatsonHistoryAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonHistoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonHistoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonHistoryAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonHistoryAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonHistoryAudit update(
		WatsonHistoryAudit watsonHistoryAudit) {
		return getPersistence().update(watsonHistoryAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonHistoryAudit update(
		WatsonHistoryAudit watsonHistoryAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonHistoryAudit, serviceContext);
	}

	/**
	* Caches the watson history audit in the entity cache if it is enabled.
	*
	* @param watsonHistoryAudit the watson history audit
	*/
	public static void cacheResult(WatsonHistoryAudit watsonHistoryAudit) {
		getPersistence().cacheResult(watsonHistoryAudit);
	}

	/**
	* Caches the watson history audits in the entity cache if it is enabled.
	*
	* @param watsonHistoryAudits the watson history audits
	*/
	public static void cacheResult(List<WatsonHistoryAudit> watsonHistoryAudits) {
		getPersistence().cacheResult(watsonHistoryAudits);
	}

	/**
	* Creates a new watson history audit with the primary key. Does not add the watson history audit to the database.
	*
	* @param watsonHistoryAuditId the primary key for the new watson history audit
	* @return the new watson history audit
	*/
	public static WatsonHistoryAudit create(long watsonHistoryAuditId) {
		return getPersistence().create(watsonHistoryAuditId);
	}

	/**
	* Removes the watson history audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonHistoryAuditId the primary key of the watson history audit
	* @return the watson history audit that was removed
	* @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	*/
	public static WatsonHistoryAudit remove(long watsonHistoryAuditId)
		throws com.liferay.watson.exception.NoSuchHistoryAuditException {
		return getPersistence().remove(watsonHistoryAuditId);
	}

	public static WatsonHistoryAudit updateImpl(
		WatsonHistoryAudit watsonHistoryAudit) {
		return getPersistence().updateImpl(watsonHistoryAudit);
	}

	/**
	* Returns the watson history audit with the primary key or throws a <code>NoSuchHistoryAuditException</code> if it could not be found.
	*
	* @param watsonHistoryAuditId the primary key of the watson history audit
	* @return the watson history audit
	* @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	*/
	public static WatsonHistoryAudit findByPrimaryKey(long watsonHistoryAuditId)
		throws com.liferay.watson.exception.NoSuchHistoryAuditException {
		return getPersistence().findByPrimaryKey(watsonHistoryAuditId);
	}

	/**
	* Returns the watson history audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonHistoryAuditId the primary key of the watson history audit
	* @return the watson history audit, or <code>null</code> if a watson history audit with the primary key could not be found
	*/
	public static WatsonHistoryAudit fetchByPrimaryKey(
		long watsonHistoryAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonHistoryAuditId);
	}

	/**
	* Returns all the watson history audits.
	*
	* @return the watson history audits
	*/
	public static List<WatsonHistoryAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson history audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson history audits
	* @param end the upper bound of the range of watson history audits (not inclusive)
	* @return the range of watson history audits
	*/
	public static List<WatsonHistoryAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson history audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson history audits
	* @param end the upper bound of the range of watson history audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson history audits
	*/
	public static List<WatsonHistoryAudit> findAll(int start, int end,
		OrderByComparator<WatsonHistoryAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson history audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson history audits
	* @param end the upper bound of the range of watson history audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson history audits
	*/
	public static List<WatsonHistoryAudit> findAll(int start, int end,
		OrderByComparator<WatsonHistoryAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson history audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson history audits.
	*
	* @return the number of watson history audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonHistoryAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonHistoryAuditPersistence, WatsonHistoryAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonHistoryAuditPersistence.class);

		ServiceTracker<WatsonHistoryAuditPersistence, WatsonHistoryAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonHistoryAuditPersistence, WatsonHistoryAuditPersistence>(bundle.getBundleContext(),
				WatsonHistoryAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}