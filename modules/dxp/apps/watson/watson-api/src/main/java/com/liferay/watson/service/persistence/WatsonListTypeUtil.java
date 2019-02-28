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
import com.liferay.watson.model.WatsonListType;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the watson list type service. This utility wraps <code>com.liferay.watson.service.persistence.impl.WatsonListTypePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypePersistence
 * @generated
 */
@ProviderType
public class WatsonListTypeUtil {

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
	public static void clearCache(WatsonListType watsonListType) {
		getPersistence().clearCache(watsonListType);
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
	public static Map<Serializable, WatsonListType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonListType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonListType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonListType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonListType> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonListType update(WatsonListType watsonListType) {
		return getPersistence().update(watsonListType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonListType update(
		WatsonListType watsonListType, ServiceContext serviceContext) {

		return getPersistence().update(watsonListType, serviceContext);
	}

	/**
	 * Caches the watson list type in the entity cache if it is enabled.
	 *
	 * @param watsonListType the watson list type
	 */
	public static void cacheResult(WatsonListType watsonListType) {
		getPersistence().cacheResult(watsonListType);
	}

	/**
	 * Caches the watson list types in the entity cache if it is enabled.
	 *
	 * @param watsonListTypes the watson list types
	 */
	public static void cacheResult(List<WatsonListType> watsonListTypes) {
		getPersistence().cacheResult(watsonListTypes);
	}

	/**
	 * Creates a new watson list type with the primary key. Does not add the watson list type to the database.
	 *
	 * @param watsonListTypeId the primary key for the new watson list type
	 * @return the new watson list type
	 */
	public static WatsonListType create(long watsonListTypeId) {
		return getPersistence().create(watsonListTypeId);
	}

	/**
	 * Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	public static WatsonListType remove(long watsonListTypeId)
		throws com.liferay.watson.exception.NoSuchListTypeException {

		return getPersistence().remove(watsonListTypeId);
	}

	public static WatsonListType updateImpl(WatsonListType watsonListType) {
		return getPersistence().updateImpl(watsonListType);
	}

	/**
	 * Returns the watson list type with the primary key or throws a <code>NoSuchListTypeException</code> if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type
	 * @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	 */
	public static WatsonListType findByPrimaryKey(long watsonListTypeId)
		throws com.liferay.watson.exception.NoSuchListTypeException {

		return getPersistence().findByPrimaryKey(watsonListTypeId);
	}

	/**
	 * Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	 */
	public static WatsonListType fetchByPrimaryKey(long watsonListTypeId) {
		return getPersistence().fetchByPrimaryKey(watsonListTypeId);
	}

	/**
	 * Returns all the watson list types.
	 *
	 * @return the watson list types
	 */
	public static List<WatsonListType> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @return the range of watson list types
	 */
	public static List<WatsonListType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list types
	 */
	public static List<WatsonListType> findAll(
		int start, int end,
		OrderByComparator<WatsonListType> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list types
	 */
	public static List<WatsonListType> findAll(
		int start, int end, OrderByComparator<WatsonListType> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the watson list types from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of watson list types.
	 *
	 * @return the number of watson list types
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WatsonListTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonListTypePersistence, WatsonListTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonListTypePersistence.class);

		ServiceTracker<WatsonListTypePersistence, WatsonListTypePersistence>
			serviceTracker =
				new ServiceTracker
					<WatsonListTypePersistence, WatsonListTypePersistence>(
						bundle.getBundleContext(),
						WatsonListTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}