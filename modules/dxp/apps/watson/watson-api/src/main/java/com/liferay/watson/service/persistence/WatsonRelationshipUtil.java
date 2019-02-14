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

import com.liferay.watson.model.WatsonRelationship;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson relationship service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonRelationshipPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonRelationshipPersistence
 * @generated
 */
@ProviderType
public class WatsonRelationshipUtil {
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
	public static void clearCache(WatsonRelationship watsonRelationship) {
		getPersistence().clearCache(watsonRelationship);
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
	public static Map<Serializable, WatsonRelationship> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonRelationship update(
		WatsonRelationship watsonRelationship) {
		return getPersistence().update(watsonRelationship);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonRelationship update(
		WatsonRelationship watsonRelationship, ServiceContext serviceContext) {
		return getPersistence().update(watsonRelationship, serviceContext);
	}

	/**
	* Caches the watson relationship in the entity cache if it is enabled.
	*
	* @param watsonRelationship the watson relationship
	*/
	public static void cacheResult(WatsonRelationship watsonRelationship) {
		getPersistence().cacheResult(watsonRelationship);
	}

	/**
	* Caches the watson relationships in the entity cache if it is enabled.
	*
	* @param watsonRelationships the watson relationships
	*/
	public static void cacheResult(List<WatsonRelationship> watsonRelationships) {
		getPersistence().cacheResult(watsonRelationships);
	}

	/**
	* Creates a new watson relationship with the primary key. Does not add the watson relationship to the database.
	*
	* @param watsonRelationshipId the primary key for the new watson relationship
	* @return the new watson relationship
	*/
	public static WatsonRelationship create(long watsonRelationshipId) {
		return getPersistence().create(watsonRelationshipId);
	}

	/**
	* Removes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship that was removed
	* @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	*/
	public static WatsonRelationship remove(long watsonRelationshipId)
		throws com.liferay.watson.exception.NoSuchRelationshipException {
		return getPersistence().remove(watsonRelationshipId);
	}

	public static WatsonRelationship updateImpl(
		WatsonRelationship watsonRelationship) {
		return getPersistence().updateImpl(watsonRelationship);
	}

	/**
	* Returns the watson relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship
	* @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	*/
	public static WatsonRelationship findByPrimaryKey(long watsonRelationshipId)
		throws com.liferay.watson.exception.NoSuchRelationshipException {
		return getPersistence().findByPrimaryKey(watsonRelationshipId);
	}

	/**
	* Returns the watson relationship with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship, or <code>null</code> if a watson relationship with the primary key could not be found
	*/
	public static WatsonRelationship fetchByPrimaryKey(
		long watsonRelationshipId) {
		return getPersistence().fetchByPrimaryKey(watsonRelationshipId);
	}

	/**
	* Returns all the watson relationships.
	*
	* @return the watson relationships
	*/
	public static List<WatsonRelationship> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @return the range of watson relationships
	*/
	public static List<WatsonRelationship> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson relationships
	*/
	public static List<WatsonRelationship> findAll(int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson relationships
	*/
	public static List<WatsonRelationship> findAll(int start, int end,
		OrderByComparator<WatsonRelationship> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson relationships from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson relationships.
	*
	* @return the number of watson relationships
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonRelationshipPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonRelationshipPersistence, WatsonRelationshipPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonRelationshipPersistence.class);

		ServiceTracker<WatsonRelationshipPersistence, WatsonRelationshipPersistence> serviceTracker =
			new ServiceTracker<WatsonRelationshipPersistence, WatsonRelationshipPersistence>(bundle.getBundleContext(),
				WatsonRelationshipPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}