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

import com.liferay.watson.model.WatsonListTypeAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson list type audit service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonListTypeAuditPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeAuditPersistence
 * @generated
 */
@ProviderType
public class WatsonListTypeAuditUtil {
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
	public static void clearCache(WatsonListTypeAudit watsonListTypeAudit) {
		getPersistence().clearCache(watsonListTypeAudit);
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
	public static Map<Serializable, WatsonListTypeAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonListTypeAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonListTypeAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonListTypeAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonListTypeAudit update(
		WatsonListTypeAudit watsonListTypeAudit) {
		return getPersistence().update(watsonListTypeAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonListTypeAudit update(
		WatsonListTypeAudit watsonListTypeAudit, ServiceContext serviceContext) {
		return getPersistence().update(watsonListTypeAudit, serviceContext);
	}

	/**
	* Caches the watson list type audit in the entity cache if it is enabled.
	*
	* @param watsonListTypeAudit the watson list type audit
	*/
	public static void cacheResult(WatsonListTypeAudit watsonListTypeAudit) {
		getPersistence().cacheResult(watsonListTypeAudit);
	}

	/**
	* Caches the watson list type audits in the entity cache if it is enabled.
	*
	* @param watsonListTypeAudits the watson list type audits
	*/
	public static void cacheResult(
		List<WatsonListTypeAudit> watsonListTypeAudits) {
		getPersistence().cacheResult(watsonListTypeAudits);
	}

	/**
	* Creates a new watson list type audit with the primary key. Does not add the watson list type audit to the database.
	*
	* @param watsonListTypeAuditId the primary key for the new watson list type audit
	* @return the new watson list type audit
	*/
	public static WatsonListTypeAudit create(long watsonListTypeAuditId) {
		return getPersistence().create(watsonListTypeAuditId);
	}

	/**
	* Removes the watson list type audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonListTypeAuditId the primary key of the watson list type audit
	* @return the watson list type audit that was removed
	* @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	*/
	public static WatsonListTypeAudit remove(long watsonListTypeAuditId)
		throws com.liferay.watson.exception.NoSuchListTypeAuditException {
		return getPersistence().remove(watsonListTypeAuditId);
	}

	public static WatsonListTypeAudit updateImpl(
		WatsonListTypeAudit watsonListTypeAudit) {
		return getPersistence().updateImpl(watsonListTypeAudit);
	}

	/**
	* Returns the watson list type audit with the primary key or throws a <code>NoSuchListTypeAuditException</code> if it could not be found.
	*
	* @param watsonListTypeAuditId the primary key of the watson list type audit
	* @return the watson list type audit
	* @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	*/
	public static WatsonListTypeAudit findByPrimaryKey(
		long watsonListTypeAuditId)
		throws com.liferay.watson.exception.NoSuchListTypeAuditException {
		return getPersistence().findByPrimaryKey(watsonListTypeAuditId);
	}

	/**
	* Returns the watson list type audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonListTypeAuditId the primary key of the watson list type audit
	* @return the watson list type audit, or <code>null</code> if a watson list type audit with the primary key could not be found
	*/
	public static WatsonListTypeAudit fetchByPrimaryKey(
		long watsonListTypeAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonListTypeAuditId);
	}

	/**
	* Returns all the watson list type audits.
	*
	* @return the watson list type audits
	*/
	public static List<WatsonListTypeAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson list type audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type audits
	* @param end the upper bound of the range of watson list type audits (not inclusive)
	* @return the range of watson list type audits
	*/
	public static List<WatsonListTypeAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson list type audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type audits
	* @param end the upper bound of the range of watson list type audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson list type audits
	*/
	public static List<WatsonListTypeAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson list type audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list type audits
	* @param end the upper bound of the range of watson list type audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson list type audits
	*/
	public static List<WatsonListTypeAudit> findAll(int start, int end,
		OrderByComparator<WatsonListTypeAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson list type audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson list type audits.
	*
	* @return the number of watson list type audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonListTypeAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonListTypeAuditPersistence, WatsonListTypeAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonListTypeAuditPersistence.class);

		ServiceTracker<WatsonListTypeAuditPersistence, WatsonListTypeAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonListTypeAuditPersistence, WatsonListTypeAuditPersistence>(bundle.getBundleContext(),
				WatsonListTypeAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}