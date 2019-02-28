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
import com.liferay.watson.model.WatsonChild;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the watson child service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonChildPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChildPersistence
 * @generated
 */
@ProviderType
public class WatsonChildUtil {

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
	public static void clearCache(WatsonChild watsonChild) {
		getPersistence().clearCache(watsonChild);
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
	public static Map<Serializable, WatsonChild> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonChild> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonChild> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonChild> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonChild> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonChild update(WatsonChild watsonChild) {
		return getPersistence().update(watsonChild);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonChild update(
		WatsonChild watsonChild, ServiceContext serviceContext) {

		return getPersistence().update(watsonChild, serviceContext);
	}

	/**
	 * Caches the watson child in the entity cache if it is enabled.
	 *
	 * @param watsonChild the watson child
	 */
	public static void cacheResult(WatsonChild watsonChild) {
		getPersistence().cacheResult(watsonChild);
	}

	/**
	 * Caches the watson childs in the entity cache if it is enabled.
	 *
	 * @param watsonChilds the watson childs
	 */
	public static void cacheResult(List<WatsonChild> watsonChilds) {
		getPersistence().cacheResult(watsonChilds);
	}

	/**
	 * Creates a new watson child with the primary key. Does not add the watson child to the database.
	 *
	 * @param watsonChildId the primary key for the new watson child
	 * @return the new watson child
	 */
	public static WatsonChild create(long watsonChildId) {
		return getPersistence().create(watsonChildId);
	}

	/**
	 * Removes the watson child with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child that was removed
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	public static WatsonChild remove(long watsonChildId)
		throws com.liferay.watson.exception.NoSuchChildException {

		return getPersistence().remove(watsonChildId);
	}

	public static WatsonChild updateImpl(WatsonChild watsonChild) {
		return getPersistence().updateImpl(watsonChild);
	}

	/**
	 * Returns the watson child with the primary key or throws a <code>NoSuchChildException</code> if it could not be found.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child
	 * @throws NoSuchChildException if a watson child with the primary key could not be found
	 */
	public static WatsonChild findByPrimaryKey(long watsonChildId)
		throws com.liferay.watson.exception.NoSuchChildException {

		return getPersistence().findByPrimaryKey(watsonChildId);
	}

	/**
	 * Returns the watson child with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child, or <code>null</code> if a watson child with the primary key could not be found
	 */
	public static WatsonChild fetchByPrimaryKey(long watsonChildId) {
		return getPersistence().fetchByPrimaryKey(watsonChildId);
	}

	/**
	 * Returns all the watson childs.
	 *
	 * @return the watson childs
	 */
	public static List<WatsonChild> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @return the range of watson childs
	 */
	public static List<WatsonChild> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson childs
	 */
	public static List<WatsonChild> findAll(
		int start, int end, OrderByComparator<WatsonChild> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson childs
	 */
	public static List<WatsonChild> findAll(
		int start, int end, OrderByComparator<WatsonChild> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the watson childs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of watson childs.
	 *
	 * @return the number of watson childs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonChildPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonChildPersistence, WatsonChildPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonChildPersistence.class);

		ServiceTracker<WatsonChildPersistence, WatsonChildPersistence>
			serviceTracker =
				new ServiceTracker
					<WatsonChildPersistence, WatsonChildPersistence>(
						bundle.getBundleContext(), WatsonChildPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}