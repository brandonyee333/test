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

import com.liferay.watson.model.WatsonListTypeRelAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson list type rel audit service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonListTypeRelAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAuditPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonListTypeRelAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonListTypeRelAuditUtil {
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
	public static void clearCache(WatsonListTypeRelAudit watsonListTypeRelAudit) {
		getPersistence().clearCache(watsonListTypeRelAudit);
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
	public static Map<Serializable, WatsonListTypeRelAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonListTypeRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonListTypeRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonListTypeRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonListTypeRelAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonListTypeRelAudit update(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {
		return getPersistence().update(watsonListTypeRelAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonListTypeRelAudit update(
		WatsonListTypeRelAudit watsonListTypeRelAudit,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonListTypeRelAudit, serviceContext);
	}

	/**
	* Caches the watson list type rel audit in the entity cache if it is enabled.
	*
	* @param watsonListTypeRelAudit the watson list type rel audit
	*/
	public static void cacheResult(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {
		getPersistence().cacheResult(watsonListTypeRelAudit);
	}

	/**
	* Caches the watson list type rel audits in the entity cache if it is enabled.
	*
	* @param watsonListTypeRelAudits the watson list type rel audits
	*/
	public static void cacheResult(
		List<WatsonListTypeRelAudit> watsonListTypeRelAudits) {
		getPersistence().cacheResult(watsonListTypeRelAudits);
	}

	/**
	* Creates a new watson list type rel audit with the primary key. Does not add the watson list type rel audit to the database.
	*
	* @param watsonListTypeRelAuditId the primary key for the new watson list type rel audit
	* @return the new watson list type rel audit
	*/
	public static WatsonListTypeRelAudit create(long watsonListTypeRelAuditId) {
		return getPersistence().create(watsonListTypeRelAuditId);
	}

	/**
	* Removes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	* @return the watson list type rel audit that was removed
	* @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	*/
	public static WatsonListTypeRelAudit remove(long watsonListTypeRelAuditId)
		throws com.liferay.watson.exception.NoSuchListTypeRelAuditException {
		return getPersistence().remove(watsonListTypeRelAuditId);
	}

	public static WatsonListTypeRelAudit updateImpl(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {
		return getPersistence().updateImpl(watsonListTypeRelAudit);
	}

	/**
	* Returns the watson list type rel audit with the primary key or throws a {@link NoSuchListTypeRelAuditException} if it could not be found.
	*
	* @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	* @return the watson list type rel audit
	* @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	*/
	public static WatsonListTypeRelAudit findByPrimaryKey(
		long watsonListTypeRelAuditId)
		throws com.liferay.watson.exception.NoSuchListTypeRelAuditException {
		return getPersistence().findByPrimaryKey(watsonListTypeRelAuditId);
	}

	/**
	* Returns the watson list type rel audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	* @return the watson list type rel audit, or <code>null</code> if a watson list type rel audit with the primary key could not be found
	*/
	public static WatsonListTypeRelAudit fetchByPrimaryKey(
		long watsonListTypeRelAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonListTypeRelAuditId);
	}

	/**
	* Returns all the watson list type rel audits.
	*
	* @return the watson list type rel audits
	*/
	public static List<WatsonListTypeRelAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson list type rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rel audits
	* @param end the upper bound of the range of watson list type rel audits (not inclusive)
	* @return the range of watson list type rel audits
	*/
	public static List<WatsonListTypeRelAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson list type rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rel audits
	* @param end the upper bound of the range of watson list type rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson list type rel audits
	*/
	public static List<WatsonListTypeRelAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRelAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson list type rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonListTypeRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type rel audits
	* @param end the upper bound of the range of watson list type rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson list type rel audits
	*/
	public static List<WatsonListTypeRelAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeRelAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson list type rel audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson list type rel audits.
	*
	* @return the number of watson list type rel audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonListTypeRelAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonListTypeRelAuditPersistence, WatsonListTypeRelAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonListTypeRelAuditPersistence.class);

		ServiceTracker<WatsonListTypeRelAuditPersistence, WatsonListTypeRelAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonListTypeRelAuditPersistence, WatsonListTypeRelAuditPersistence>(bundle.getBundleContext(),
				WatsonListTypeRelAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}