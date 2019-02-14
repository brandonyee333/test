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

import com.liferay.watson.model.WatsonIncidentRelAudit;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson incident rel audit service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonIncidentRelAuditPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAuditPersistence
 * @generated
 */
@ProviderType
public class WatsonIncidentRelAuditUtil {
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
	public static void clearCache(WatsonIncidentRelAudit watsonIncidentRelAudit) {
		getPersistence().clearCache(watsonIncidentRelAudit);
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
	public static Map<Serializable, WatsonIncidentRelAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonIncidentRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonIncidentRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonIncidentRelAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonIncidentRelAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonIncidentRelAudit update(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {
		return getPersistence().update(watsonIncidentRelAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonIncidentRelAudit update(
		WatsonIncidentRelAudit watsonIncidentRelAudit,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonIncidentRelAudit, serviceContext);
	}

	/**
	* Caches the watson incident rel audit in the entity cache if it is enabled.
	*
	* @param watsonIncidentRelAudit the watson incident rel audit
	*/
	public static void cacheResult(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {
		getPersistence().cacheResult(watsonIncidentRelAudit);
	}

	/**
	* Caches the watson incident rel audits in the entity cache if it is enabled.
	*
	* @param watsonIncidentRelAudits the watson incident rel audits
	*/
	public static void cacheResult(
		List<WatsonIncidentRelAudit> watsonIncidentRelAudits) {
		getPersistence().cacheResult(watsonIncidentRelAudits);
	}

	/**
	* Creates a new watson incident rel audit with the primary key. Does not add the watson incident rel audit to the database.
	*
	* @param watsonIncidentRelAuditId the primary key for the new watson incident rel audit
	* @return the new watson incident rel audit
	*/
	public static WatsonIncidentRelAudit create(long watsonIncidentRelAuditId) {
		return getPersistence().create(watsonIncidentRelAuditId);
	}

	/**
	* Removes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit that was removed
	* @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	*/
	public static WatsonIncidentRelAudit remove(long watsonIncidentRelAuditId)
		throws com.liferay.watson.exception.NoSuchIncidentRelAuditException {
		return getPersistence().remove(watsonIncidentRelAuditId);
	}

	public static WatsonIncidentRelAudit updateImpl(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {
		return getPersistence().updateImpl(watsonIncidentRelAudit);
	}

	/**
	* Returns the watson incident rel audit with the primary key or throws a <code>NoSuchIncidentRelAuditException</code> if it could not be found.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit
	* @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	*/
	public static WatsonIncidentRelAudit findByPrimaryKey(
		long watsonIncidentRelAuditId)
		throws com.liferay.watson.exception.NoSuchIncidentRelAuditException {
		return getPersistence().findByPrimaryKey(watsonIncidentRelAuditId);
	}

	/**
	* Returns the watson incident rel audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit, or <code>null</code> if a watson incident rel audit with the primary key could not be found
	*/
	public static WatsonIncidentRelAudit fetchByPrimaryKey(
		long watsonIncidentRelAuditId) {
		return getPersistence().fetchByPrimaryKey(watsonIncidentRelAuditId);
	}

	/**
	* Returns all the watson incident rel audits.
	*
	* @return the watson incident rel audits
	*/
	public static List<WatsonIncidentRelAudit> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @return the range of watson incident rel audits
	*/
	public static List<WatsonIncidentRelAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson incident rel audits
	*/
	public static List<WatsonIncidentRelAudit> findAll(int start, int end,
		OrderByComparator<WatsonIncidentRelAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson incident rel audits
	*/
	public static List<WatsonIncidentRelAudit> findAll(int start, int end,
		OrderByComparator<WatsonIncidentRelAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson incident rel audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson incident rel audits.
	*
	* @return the number of watson incident rel audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonIncidentRelAuditPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonIncidentRelAuditPersistence, WatsonIncidentRelAuditPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonIncidentRelAuditPersistence.class);

		ServiceTracker<WatsonIncidentRelAuditPersistence, WatsonIncidentRelAuditPersistence> serviceTracker =
			new ServiceTracker<WatsonIncidentRelAuditPersistence, WatsonIncidentRelAuditPersistence>(bundle.getBundleContext(),
				WatsonIncidentRelAuditPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}