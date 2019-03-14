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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.model.ModuleVersion;
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
 * The persistence utility for the module version service. This utility wraps <code>com.liferay.osb.customer.release.tool.service.persistence.impl.ModuleVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleVersionPersistence
 * @generated
 */
@ProviderType
public class ModuleVersionUtil {

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
	public static void clearCache(ModuleVersion moduleVersion) {
		getPersistence().clearCache(moduleVersion);
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
	public static Map<Serializable, ModuleVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ModuleVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ModuleVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ModuleVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ModuleVersion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ModuleVersion update(ModuleVersion moduleVersion) {
		return getPersistence().update(moduleVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ModuleVersion update(
		ModuleVersion moduleVersion, ServiceContext serviceContext) {

		return getPersistence().update(moduleVersion, serviceContext);
	}

	/**
	 * Caches the module version in the entity cache if it is enabled.
	 *
	 * @param moduleVersion the module version
	 */
	public static void cacheResult(ModuleVersion moduleVersion) {
		getPersistence().cacheResult(moduleVersion);
	}

	/**
	 * Caches the module versions in the entity cache if it is enabled.
	 *
	 * @param moduleVersions the module versions
	 */
	public static void cacheResult(List<ModuleVersion> moduleVersions) {
		getPersistence().cacheResult(moduleVersions);
	}

	/**
	 * Creates a new module version with the primary key. Does not add the module version to the database.
	 *
	 * @param moduleVersionId the primary key for the new module version
	 * @return the new module version
	 */
	public static ModuleVersion create(long moduleVersionId) {
		return getPersistence().create(moduleVersionId);
	}

	/**
	 * Removes the module version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version that was removed
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	public static ModuleVersion remove(long moduleVersionId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchModuleVersionException {

		return getPersistence().remove(moduleVersionId);
	}

	public static ModuleVersion updateImpl(ModuleVersion moduleVersion) {
		return getPersistence().updateImpl(moduleVersion);
	}

	/**
	 * Returns the module version with the primary key or throws a <code>NoSuchModuleVersionException</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version
	 * @throws NoSuchModuleVersionException if a module version with the primary key could not be found
	 */
	public static ModuleVersion findByPrimaryKey(long moduleVersionId)
		throws com.liferay.osb.customer.release.tool.exception.
			NoSuchModuleVersionException {

		return getPersistence().findByPrimaryKey(moduleVersionId);
	}

	/**
	 * Returns the module version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleVersionId the primary key of the module version
	 * @return the module version, or <code>null</code> if a module version with the primary key could not be found
	 */
	public static ModuleVersion fetchByPrimaryKey(long moduleVersionId) {
		return getPersistence().fetchByPrimaryKey(moduleVersionId);
	}

	/**
	 * Returns all the module versions.
	 *
	 * @return the module versions
	 */
	public static List<ModuleVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @return the range of module versions
	 */
	public static List<ModuleVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module versions
	 */
	public static List<ModuleVersion> findAll(
		int start, int end,
		OrderByComparator<ModuleVersion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the module versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module versions
	 * @param end the upper bound of the range of module versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module versions
	 */
	public static List<ModuleVersion> findAll(
		int start, int end, OrderByComparator<ModuleVersion> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the module versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of module versions.
	 *
	 * @return the number of module versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ModuleVersionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ModuleVersionPersistence, ModuleVersionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModuleVersionPersistence.class);

		ServiceTracker<ModuleVersionPersistence, ModuleVersionPersistence>
			serviceTracker =
				new ServiceTracker
					<ModuleVersionPersistence, ModuleVersionPersistence>(
						bundle.getBundleContext(),
						ModuleVersionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}