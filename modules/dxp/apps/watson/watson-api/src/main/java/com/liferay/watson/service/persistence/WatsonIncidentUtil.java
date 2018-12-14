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

import com.liferay.watson.model.WatsonIncident;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson incident service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonIncidentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonIncidentPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonIncidentUtil {
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
	public static void clearCache(WatsonIncident watsonIncident) {
		getPersistence().clearCache(watsonIncident);
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
	public static Map<Serializable, WatsonIncident> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonIncident> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonIncident> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonIncident> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonIncident> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonIncident update(WatsonIncident watsonIncident) {
		return getPersistence().update(watsonIncident);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonIncident update(WatsonIncident watsonIncident,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonIncident, serviceContext);
	}

	/**
	* Caches the watson incident in the entity cache if it is enabled.
	*
	* @param watsonIncident the watson incident
	*/
	public static void cacheResult(WatsonIncident watsonIncident) {
		getPersistence().cacheResult(watsonIncident);
	}

	/**
	* Caches the watson incidents in the entity cache if it is enabled.
	*
	* @param watsonIncidents the watson incidents
	*/
	public static void cacheResult(List<WatsonIncident> watsonIncidents) {
		getPersistence().cacheResult(watsonIncidents);
	}

	/**
	* Creates a new watson incident with the primary key. Does not add the watson incident to the database.
	*
	* @param watsonIncidentId the primary key for the new watson incident
	* @return the new watson incident
	*/
	public static WatsonIncident create(long watsonIncidentId) {
		return getPersistence().create(watsonIncidentId);
	}

	/**
	* Removes the watson incident with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonIncidentId the primary key of the watson incident
	* @return the watson incident that was removed
	* @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	*/
	public static WatsonIncident remove(long watsonIncidentId)
		throws com.liferay.watson.exception.NoSuchIncidentException {
		return getPersistence().remove(watsonIncidentId);
	}

	public static WatsonIncident updateImpl(WatsonIncident watsonIncident) {
		return getPersistence().updateImpl(watsonIncident);
	}

	/**
	* Returns the watson incident with the primary key or throws a {@link NoSuchIncidentException} if it could not be found.
	*
	* @param watsonIncidentId the primary key of the watson incident
	* @return the watson incident
	* @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	*/
	public static WatsonIncident findByPrimaryKey(long watsonIncidentId)
		throws com.liferay.watson.exception.NoSuchIncidentException {
		return getPersistence().findByPrimaryKey(watsonIncidentId);
	}

	/**
	* Returns the watson incident with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonIncidentId the primary key of the watson incident
	* @return the watson incident, or <code>null</code> if a watson incident with the primary key could not be found
	*/
	public static WatsonIncident fetchByPrimaryKey(long watsonIncidentId) {
		return getPersistence().fetchByPrimaryKey(watsonIncidentId);
	}

	/**
	* Returns all the watson incidents.
	*
	* @return the watson incidents
	*/
	public static List<WatsonIncident> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson incidents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incidents
	* @param end the upper bound of the range of watson incidents (not inclusive)
	* @return the range of watson incidents
	*/
	public static List<WatsonIncident> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson incidents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incidents
	* @param end the upper bound of the range of watson incidents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson incidents
	*/
	public static List<WatsonIncident> findAll(int start, int end,
		OrderByComparator<WatsonIncident> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson incidents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incidents
	* @param end the upper bound of the range of watson incidents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson incidents
	*/
	public static List<WatsonIncident> findAll(int start, int end,
		OrderByComparator<WatsonIncident> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson incidents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson incidents.
	*
	* @return the number of watson incidents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonIncidentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonIncidentPersistence, WatsonIncidentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonIncidentPersistence.class);

		ServiceTracker<WatsonIncidentPersistence, WatsonIncidentPersistence> serviceTracker =
			new ServiceTracker<WatsonIncidentPersistence, WatsonIncidentPersistence>(bundle.getBundleContext(),
				WatsonIncidentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}