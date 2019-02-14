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

import com.liferay.watson.model.WatsonAddress;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the watson address service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonAddressPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonAddressPersistence
 * @generated
 */
@ProviderType
public class WatsonAddressUtil {
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
	public static void clearCache(WatsonAddress watsonAddress) {
		getPersistence().clearCache(watsonAddress);
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
	public static Map<Serializable, WatsonAddress> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonAddress update(WatsonAddress watsonAddress) {
		return getPersistence().update(watsonAddress);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonAddress update(WatsonAddress watsonAddress,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonAddress, serviceContext);
	}

	/**
	* Caches the watson address in the entity cache if it is enabled.
	*
	* @param watsonAddress the watson address
	*/
	public static void cacheResult(WatsonAddress watsonAddress) {
		getPersistence().cacheResult(watsonAddress);
	}

	/**
	* Caches the watson addresses in the entity cache if it is enabled.
	*
	* @param watsonAddresses the watson addresses
	*/
	public static void cacheResult(List<WatsonAddress> watsonAddresses) {
		getPersistence().cacheResult(watsonAddresses);
	}

	/**
	* Creates a new watson address with the primary key. Does not add the watson address to the database.
	*
	* @param watsonAddressId the primary key for the new watson address
	* @return the new watson address
	*/
	public static WatsonAddress create(long watsonAddressId) {
		return getPersistence().create(watsonAddressId);
	}

	/**
	* Removes the watson address with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address that was removed
	* @throws NoSuchAddressException if a watson address with the primary key could not be found
	*/
	public static WatsonAddress remove(long watsonAddressId)
		throws com.liferay.watson.exception.NoSuchAddressException {
		return getPersistence().remove(watsonAddressId);
	}

	public static WatsonAddress updateImpl(WatsonAddress watsonAddress) {
		return getPersistence().updateImpl(watsonAddress);
	}

	/**
	* Returns the watson address with the primary key or throws a <code>NoSuchAddressException</code> if it could not be found.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address
	* @throws NoSuchAddressException if a watson address with the primary key could not be found
	*/
	public static WatsonAddress findByPrimaryKey(long watsonAddressId)
		throws com.liferay.watson.exception.NoSuchAddressException {
		return getPersistence().findByPrimaryKey(watsonAddressId);
	}

	/**
	* Returns the watson address with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonAddressId the primary key of the watson address
	* @return the watson address, or <code>null</code> if a watson address with the primary key could not be found
	*/
	public static WatsonAddress fetchByPrimaryKey(long watsonAddressId) {
		return getPersistence().fetchByPrimaryKey(watsonAddressId);
	}

	/**
	* Returns all the watson addresses.
	*
	* @return the watson addresses
	*/
	public static List<WatsonAddress> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @return the range of watson addresses
	*/
	public static List<WatsonAddress> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson addresses
	*/
	public static List<WatsonAddress> findAll(int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson addresses
	* @param end the upper bound of the range of watson addresses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson addresses
	*/
	public static List<WatsonAddress> findAll(int start, int end,
		OrderByComparator<WatsonAddress> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson addresses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson addresses.
	*
	* @return the number of watson addresses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonAddressPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonAddressPersistence, WatsonAddressPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WatsonAddressPersistence.class);

		ServiceTracker<WatsonAddressPersistence, WatsonAddressPersistence> serviceTracker =
			new ServiceTracker<WatsonAddressPersistence, WatsonAddressPersistence>(bundle.getBundleContext(),
				WatsonAddressPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}