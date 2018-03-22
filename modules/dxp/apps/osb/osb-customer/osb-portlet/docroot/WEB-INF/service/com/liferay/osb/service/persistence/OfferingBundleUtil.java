/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.OfferingBundle;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the offering bundle service. This utility wraps {@link com.liferay.osb.service.persistence.impl.OfferingBundlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundlePersistence
 * @see com.liferay.osb.service.persistence.impl.OfferingBundlePersistenceImpl
 * @generated
 */
@ProviderType
public class OfferingBundleUtil {
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
	public static void clearCache(OfferingBundle offeringBundle) {
		getPersistence().clearCache(offeringBundle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OfferingBundle update(OfferingBundle offeringBundle) {
		return getPersistence().update(offeringBundle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OfferingBundle update(OfferingBundle offeringBundle,
		ServiceContext serviceContext) {
		return getPersistence().update(offeringBundle, serviceContext);
	}

	/**
	* Returns the offering bundle where name = &#63; or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	*
	* @param name the name
	* @return the matching offering bundle
	* @throws NoSuchOfferingBundleException if a matching offering bundle could not be found
	*/
	public static OfferingBundle findByName(java.lang.String name)
		throws com.liferay.osb.exception.NoSuchOfferingBundleException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	*/
	public static OfferingBundle fetchByName(java.lang.String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	*/
	public static OfferingBundle fetchByName(java.lang.String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Removes the offering bundle where name = &#63; from the database.
	*
	* @param name the name
	* @return the offering bundle that was removed
	*/
	public static OfferingBundle removeByName(java.lang.String name)
		throws com.liferay.osb.exception.NoSuchOfferingBundleException {
		return getPersistence().removeByName(name);
	}

	/**
	* Returns the number of offering bundles where name = &#63;.
	*
	* @param name the name
	* @return the number of matching offering bundles
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Caches the offering bundle in the entity cache if it is enabled.
	*
	* @param offeringBundle the offering bundle
	*/
	public static void cacheResult(OfferingBundle offeringBundle) {
		getPersistence().cacheResult(offeringBundle);
	}

	/**
	* Caches the offering bundles in the entity cache if it is enabled.
	*
	* @param offeringBundles the offering bundles
	*/
	public static void cacheResult(List<OfferingBundle> offeringBundles) {
		getPersistence().cacheResult(offeringBundles);
	}

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public static OfferingBundle create(long offeringBundleId) {
		return getPersistence().create(offeringBundleId);
	}

	/**
	* Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	*/
	public static OfferingBundle remove(long offeringBundleId)
		throws com.liferay.osb.exception.NoSuchOfferingBundleException {
		return getPersistence().remove(offeringBundleId);
	}

	public static OfferingBundle updateImpl(OfferingBundle offeringBundle) {
		return getPersistence().updateImpl(offeringBundle);
	}

	/**
	* Returns the offering bundle with the primary key or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	*/
	public static OfferingBundle findByPrimaryKey(long offeringBundleId)
		throws com.liferay.osb.exception.NoSuchOfferingBundleException {
		return getPersistence().findByPrimaryKey(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	*/
	public static OfferingBundle fetchByPrimaryKey(long offeringBundleId) {
		return getPersistence().fetchByPrimaryKey(offeringBundleId);
	}

	public static java.util.Map<java.io.Serializable, OfferingBundle> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the offering bundles.
	*
	* @return the offering bundles
	*/
	public static List<OfferingBundle> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	*/
	public static List<OfferingBundle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering bundles
	*/
	public static List<OfferingBundle> findAll(int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of offering bundles
	*/
	public static List<OfferingBundle> findAll(int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the offering bundles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return long[] of the primaryKeys of offering definitions associated with the offering bundle
	*/
	public static long[] getOfferingDefinitionPrimaryKeys(long pk) {
		return getPersistence().getOfferingDefinitionPrimaryKeys(pk);
	}

	/**
	* Returns all the offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the offering definitions associated with the offering bundle
	*/
	public static List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk) {
		return getPersistence().getOfferingDefinitions(pk);
	}

	/**
	* Returns a range of all the offering definitions associated with the offering bundle.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the offering bundle
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering definitions associated with the offering bundle
	*/
	public static List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end) {
		return getPersistence().getOfferingDefinitions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions associated with the offering bundle.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the offering bundle
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering definitions associated with the offering bundle
	*/
	public static List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .getOfferingDefinitions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the number of offering definitions associated with the offering bundle
	*/
	public static int getOfferingDefinitionsSize(long pk) {
		return getPersistence().getOfferingDefinitionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the offering definition is associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	* @return <code>true</code> if the offering definition is associated with the offering bundle; <code>false</code> otherwise
	*/
	public static boolean containsOfferingDefinition(long pk,
		long offeringDefinitionPK) {
		return getPersistence()
				   .containsOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Returns <code>true</code> if the offering bundle has any offering definitions associated with it.
	*
	* @param pk the primary key of the offering bundle to check for associations with offering definitions
	* @return <code>true</code> if the offering bundle has any offering definitions associated with it; <code>false</code> otherwise
	*/
	public static boolean containsOfferingDefinitions(long pk) {
		return getPersistence().containsOfferingDefinitions(pk);
	}

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	*/
	public static void addOfferingDefinition(long pk, long offeringDefinitionPK) {
		getPersistence().addOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	*/
	public static void addOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		getPersistence().addOfferingDefinition(pk, offeringDefinition);
	}

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	*/
	public static void addOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs) {
		getPersistence().addOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	*/
	public static void addOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getPersistence().addOfferingDefinitions(pk, offeringDefinitions);
	}

	/**
	* Clears all associations between the offering bundle and its offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle to clear the associated offering definitions from
	*/
	public static void clearOfferingDefinitions(long pk) {
		getPersistence().clearOfferingDefinitions(pk);
	}

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	*/
	public static void removeOfferingDefinition(long pk,
		long offeringDefinitionPK) {
		getPersistence().removeOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	*/
	public static void removeOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		getPersistence().removeOfferingDefinition(pk, offeringDefinition);
	}

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	*/
	public static void removeOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs) {
		getPersistence().removeOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	*/
	public static void removeOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getPersistence().removeOfferingDefinitions(pk, offeringDefinitions);
	}

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions to be associated with the offering bundle
	*/
	public static void setOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs) {
		getPersistence().setOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions to be associated with the offering bundle
	*/
	public static void setOfferingDefinitions(long pk,
		List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getPersistence().setOfferingDefinitions(pk, offeringDefinitions);
	}

	public static OfferingBundlePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OfferingBundlePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					OfferingBundlePersistence.class.getName());

			ReferenceRegistry.registerReference(OfferingBundleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static OfferingBundlePersistence _persistence;
}