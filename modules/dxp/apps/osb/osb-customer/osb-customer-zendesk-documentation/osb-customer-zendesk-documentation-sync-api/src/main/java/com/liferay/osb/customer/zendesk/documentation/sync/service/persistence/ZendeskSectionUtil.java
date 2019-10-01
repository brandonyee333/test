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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
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
 * The persistence utility for the zendesk section service. This utility wraps <code>com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskSectionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSectionPersistence
 * @generated
 */
public class ZendeskSectionUtil {

	/**
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
	public static void clearCache(ZendeskSection zendeskSection) {
		getPersistence().clearCache(zendeskSection);
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
	public static Map<Serializable, ZendeskSection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ZendeskSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ZendeskSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ZendeskSection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ZendeskSection> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ZendeskSection update(ZendeskSection zendeskSection) {
		return getPersistence().update(zendeskSection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ZendeskSection update(
		ZendeskSection zendeskSection, ServiceContext serviceContext) {

		return getPersistence().update(zendeskSection, serviceContext);
	}

	/**
	 * Returns all the zendesk sections where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @return the matching zendesk sections
	 */
	public static List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId) {

		return getPersistence().findByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns a range of all the zendesk sections where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @return the range of matching zendesk sections
	 */
	public static List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk sections where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zendesk sections
	 */
	public static List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		OrderByComparator<ZendeskSection> orderByComparator) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk sections where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching zendesk sections
	 */
	public static List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		OrderByComparator<ZendeskSection> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first zendesk section in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk section
	 * @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	 */
	public static ZendeskSection findByZendeskCategoryId_First(
			long zendeskCategoryId,
			OrderByComparator<ZendeskSection> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().findByZendeskCategoryId_First(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the first zendesk section in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	 */
	public static ZendeskSection fetchByZendeskCategoryId_First(
		long zendeskCategoryId,
		OrderByComparator<ZendeskSection> orderByComparator) {

		return getPersistence().fetchByZendeskCategoryId_First(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the last zendesk section in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk section
	 * @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	 */
	public static ZendeskSection findByZendeskCategoryId_Last(
			long zendeskCategoryId,
			OrderByComparator<ZendeskSection> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().findByZendeskCategoryId_Last(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the last zendesk section in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	 */
	public static ZendeskSection fetchByZendeskCategoryId_Last(
		long zendeskCategoryId,
		OrderByComparator<ZendeskSection> orderByComparator) {

		return getPersistence().fetchByZendeskCategoryId_Last(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the zendesk sections before and after the current zendesk section in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskSectionId the primary key of the current zendesk section
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zendesk section
	 * @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection[] findByZendeskCategoryId_PrevAndNext(
			long zendeskSectionId, long zendeskCategoryId,
			OrderByComparator<ZendeskSection> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().findByZendeskCategoryId_PrevAndNext(
			zendeskSectionId, zendeskCategoryId, orderByComparator);
	}

	/**
	 * Removes all the zendesk sections where zendeskCategoryId = &#63; from the database.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 */
	public static void removeByZendeskCategoryId(long zendeskCategoryId) {
		getPersistence().removeByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns the number of zendesk sections where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @return the number of matching zendesk sections
	 */
	public static int countByZendeskCategoryId(long zendeskCategoryId) {
		return getPersistence().countByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or throws a <code>NoSuchZendeskSectionException</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the matching zendesk section
	 * @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	 */
	public static ZendeskSection findByZCI_DK(
			long zendeskCategoryId, String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().findByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	 */
	public static ZendeskSection fetchByZCI_DK(
		long zendeskCategoryId, String documentationKey) {

		return getPersistence().fetchByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	 */
	public static ZendeskSection fetchByZCI_DK(
		long zendeskCategoryId, String documentationKey,
		boolean useFinderCache) {

		return getPersistence().fetchByZCI_DK(
			zendeskCategoryId, documentationKey, useFinderCache);
	}

	/**
	 * Removes the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; from the database.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the zendesk section that was removed
	 */
	public static ZendeskSection removeByZCI_DK(
			long zendeskCategoryId, String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().removeByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the number of zendesk sections where zendeskCategoryId = &#63; and documentationKey = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the number of matching zendesk sections
	 */
	public static int countByZCI_DK(
		long zendeskCategoryId, String documentationKey) {

		return getPersistence().countByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Caches the zendesk section in the entity cache if it is enabled.
	 *
	 * @param zendeskSection the zendesk section
	 */
	public static void cacheResult(ZendeskSection zendeskSection) {
		getPersistence().cacheResult(zendeskSection);
	}

	/**
	 * Caches the zendesk sections in the entity cache if it is enabled.
	 *
	 * @param zendeskSections the zendesk sections
	 */
	public static void cacheResult(List<ZendeskSection> zendeskSections) {
		getPersistence().cacheResult(zendeskSections);
	}

	/**
	 * Creates a new zendesk section with the primary key. Does not add the zendesk section to the database.
	 *
	 * @param zendeskSectionId the primary key for the new zendesk section
	 * @return the new zendesk section
	 */
	public static ZendeskSection create(long zendeskSectionId) {
		return getPersistence().create(zendeskSectionId);
	}

	/**
	 * Removes the zendesk section with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section that was removed
	 * @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection remove(long zendeskSectionId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().remove(zendeskSectionId);
	}

	public static ZendeskSection updateImpl(ZendeskSection zendeskSection) {
		return getPersistence().updateImpl(zendeskSection);
	}

	/**
	 * Returns the zendesk section with the primary key or throws a <code>NoSuchZendeskSectionException</code> if it could not be found.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section
	 * @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection findByPrimaryKey(long zendeskSectionId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskSectionException {

		return getPersistence().findByPrimaryKey(zendeskSectionId);
	}

	/**
	 * Returns the zendesk section with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskSectionId the primary key of the zendesk section
	 * @return the zendesk section, or <code>null</code> if a zendesk section with the primary key could not be found
	 */
	public static ZendeskSection fetchByPrimaryKey(long zendeskSectionId) {
		return getPersistence().fetchByPrimaryKey(zendeskSectionId);
	}

	/**
	 * Returns all the zendesk sections.
	 *
	 * @return the zendesk sections
	 */
	public static List<ZendeskSection> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the zendesk sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @return the range of zendesk sections
	 */
	public static List<ZendeskSection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zendesk sections
	 */
	public static List<ZendeskSection> findAll(
		int start, int end,
		OrderByComparator<ZendeskSection> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk sections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskSectionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk sections
	 * @param end the upper bound of the range of zendesk sections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of zendesk sections
	 */
	public static List<ZendeskSection> findAll(
		int start, int end, OrderByComparator<ZendeskSection> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the zendesk sections from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of zendesk sections.
	 *
	 * @return the number of zendesk sections
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ZendeskSectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ZendeskSectionPersistence, ZendeskSectionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ZendeskSectionPersistence.class);

		ServiceTracker<ZendeskSectionPersistence, ZendeskSectionPersistence>
			serviceTracker =
				new ServiceTracker
					<ZendeskSectionPersistence, ZendeskSectionPersistence>(
						bundle.getBundleContext(),
						ZendeskSectionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}