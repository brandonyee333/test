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

package com.liferay.osb.customer.zendesk.documentation.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the zendesk category service. This utility wraps <code>com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskCategoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryPersistence
 * @generated
 */
@ProviderType
public class ZendeskCategoryUtil {

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
	public static void clearCache(ZendeskCategory zendeskCategory) {
		getPersistence().clearCache(zendeskCategory);
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
	public static Map<Serializable, ZendeskCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ZendeskCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ZendeskCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ZendeskCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ZendeskCategory> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ZendeskCategory update(ZendeskCategory zendeskCategory) {
		return getPersistence().update(zendeskCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ZendeskCategory update(
		ZendeskCategory zendeskCategory, ServiceContext serviceContext) {

		return getPersistence().update(zendeskCategory, serviceContext);
	}

	/**
	 * Returns the zendesk category where documentationKey = &#63; or throws a <code>NoSuchZendeskCategoryException</code> if it could not be found.
	 *
	 * @param documentationKey the documentation key
	 * @return the matching zendesk category
	 * @throws NoSuchZendeskCategoryException if a matching zendesk category could not be found
	 */
	public static ZendeskCategory findByDocumentationKey(
			String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskCategoryException {

		return getPersistence().findByDocumentationKey(documentationKey);
	}

	/**
	 * Returns the zendesk category where documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentationKey the documentation key
	 * @return the matching zendesk category, or <code>null</code> if a matching zendesk category could not be found
	 */
	public static ZendeskCategory fetchByDocumentationKey(
		String documentationKey) {

		return getPersistence().fetchByDocumentationKey(documentationKey);
	}

	/**
	 * Returns the zendesk category where documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentationKey the documentation key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching zendesk category, or <code>null</code> if a matching zendesk category could not be found
	 */
	public static ZendeskCategory fetchByDocumentationKey(
		String documentationKey, boolean retrieveFromCache) {

		return getPersistence().fetchByDocumentationKey(
			documentationKey, retrieveFromCache);
	}

	/**
	 * Removes the zendesk category where documentationKey = &#63; from the database.
	 *
	 * @param documentationKey the documentation key
	 * @return the zendesk category that was removed
	 */
	public static ZendeskCategory removeByDocumentationKey(
			String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskCategoryException {

		return getPersistence().removeByDocumentationKey(documentationKey);
	}

	/**
	 * Returns the number of zendesk categories where documentationKey = &#63;.
	 *
	 * @param documentationKey the documentation key
	 * @return the number of matching zendesk categories
	 */
	public static int countByDocumentationKey(String documentationKey) {
		return getPersistence().countByDocumentationKey(documentationKey);
	}

	/**
	 * Caches the zendesk category in the entity cache if it is enabled.
	 *
	 * @param zendeskCategory the zendesk category
	 */
	public static void cacheResult(ZendeskCategory zendeskCategory) {
		getPersistence().cacheResult(zendeskCategory);
	}

	/**
	 * Caches the zendesk categories in the entity cache if it is enabled.
	 *
	 * @param zendeskCategories the zendesk categories
	 */
	public static void cacheResult(List<ZendeskCategory> zendeskCategories) {
		getPersistence().cacheResult(zendeskCategories);
	}

	/**
	 * Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	 *
	 * @param zendeskCategoryId the primary key for the new zendesk category
	 * @return the new zendesk category
	 */
	public static ZendeskCategory create(long zendeskCategoryId) {
		return getPersistence().create(zendeskCategoryId);
	}

	/**
	 * Removes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category that was removed
	 * @throws NoSuchZendeskCategoryException if a zendesk category with the primary key could not be found
	 */
	public static ZendeskCategory remove(long zendeskCategoryId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskCategoryException {

		return getPersistence().remove(zendeskCategoryId);
	}

	public static ZendeskCategory updateImpl(ZendeskCategory zendeskCategory) {
		return getPersistence().updateImpl(zendeskCategory);
	}

	/**
	 * Returns the zendesk category with the primary key or throws a <code>NoSuchZendeskCategoryException</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category
	 * @throws NoSuchZendeskCategoryException if a zendesk category with the primary key could not be found
	 */
	public static ZendeskCategory findByPrimaryKey(long zendeskCategoryId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskCategoryException {

		return getPersistence().findByPrimaryKey(zendeskCategoryId);
	}

	/**
	 * Returns the zendesk category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category, or <code>null</code> if a zendesk category with the primary key could not be found
	 */
	public static ZendeskCategory fetchByPrimaryKey(long zendeskCategoryId) {
		return getPersistence().fetchByPrimaryKey(zendeskCategoryId);
	}

	/**
	 * Returns all the zendesk categories.
	 *
	 * @return the zendesk categories
	 */
	public static List<ZendeskCategory> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @return the range of zendesk categories
	 */
	public static List<ZendeskCategory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zendesk categories
	 */
	public static List<ZendeskCategory> findAll(
		int start, int end,
		OrderByComparator<ZendeskCategory> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of zendesk categories
	 */
	public static List<ZendeskCategory> findAll(
		int start, int end,
		OrderByComparator<ZendeskCategory> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the zendesk categories from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of zendesk categories.
	 *
	 * @return the number of zendesk categories
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ZendeskCategoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ZendeskCategoryPersistence, ZendeskCategoryPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ZendeskCategoryPersistence.class);

		ServiceTracker<ZendeskCategoryPersistence, ZendeskCategoryPersistence>
			serviceTracker =
				new ServiceTracker
					<ZendeskCategoryPersistence, ZendeskCategoryPersistence>(
						bundle.getBundleContext(),
						ZendeskCategoryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}