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

import com.liferay.osb.model.OfferingBundle;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the offering bundle service. This utility wraps {@link OfferingBundlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundlePersistence
 * @see OfferingBundlePersistenceImpl
 * @generated
 */
public class OfferingBundleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(OfferingBundle offeringBundle) {
		getPersistence().clearCache(offeringBundle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OfferingBundle update(OfferingBundle offeringBundle,
		boolean merge) throws SystemException {
		return getPersistence().update(offeringBundle, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OfferingBundle update(OfferingBundle offeringBundle,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(offeringBundle, merge, serviceContext);
	}

	/**
	* Caches the offering bundle in the entity cache if it is enabled.
	*
	* @param offeringBundle the offering bundle
	*/
	public static void cacheResult(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		getPersistence().cacheResult(offeringBundle);
	}

	/**
	* Caches the offering bundles in the entity cache if it is enabled.
	*
	* @param offeringBundles the offering bundles
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		getPersistence().cacheResult(offeringBundles);
	}

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public static com.liferay.osb.model.OfferingBundle create(
		long offeringBundleId) {
		return getPersistence().create(offeringBundleId);
	}

	/**
	* Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws com.liferay.osb.NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle remove(
		long offeringBundleId)
		throws com.liferay.osb.NoSuchOfferingBundleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(offeringBundleId);
	}

	public static com.liferay.osb.model.OfferingBundle updateImpl(
		com.liferay.osb.model.OfferingBundle offeringBundle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(offeringBundle, merge);
	}

	/**
	* Returns the offering bundle with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingBundleException} if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws com.liferay.osb.NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle findByPrimaryKey(
		long offeringBundleId)
		throws com.liferay.osb.NoSuchOfferingBundleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle fetchByPrimaryKey(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(offeringBundleId);
	}

	/**
	* Returns the offering bundle where name = &#63; or throws a {@link com.liferay.osb.NoSuchOfferingBundleException} if it could not be found.
	*
	* @param name the name
	* @return the matching offering bundle
	* @throws com.liferay.osb.NoSuchOfferingBundleException if a matching offering bundle could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle findByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchOfferingBundleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle fetchByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Returns all the offering bundles.
	*
	* @return the offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the offering bundle where name = &#63; from the database.
	*
	* @param name the name
	* @return the offering bundle that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle removeByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchOfferingBundleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByName(name);
	}

	/**
	* Removes all the offering bundles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering bundles where name = &#63;.
	*
	* @param name the name
	* @return the number of matching offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the offering definitions associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingDefinitions(pk);
	}

	/**
	* Returns a range of all the offering definitions associated with the offering bundle.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the offering bundle
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering definitions associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingDefinitions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions associated with the offering bundle.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the offering bundle
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering definitions associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getOfferingDefinitions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the number of offering definitions associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingDefinitionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingDefinitionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the offering definition is associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	* @return <code>true</code> if the offering definition is associated with the offering bundle; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOfferingDefinition(long pk,
		long offeringDefinitionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .containsOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Returns <code>true</code> if the offering bundle has any offering definitions associated with it.
	*
	* @param pk the primary key of the offering bundle to check for associations with offering definitions
	* @return <code>true</code> if the offering bundle has any offering definitions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOfferingDefinitions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsOfferingDefinitions(pk);
	}

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinition(long pk, long offeringDefinitionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingDefinition(pk, offeringDefinition);
	}

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingDefinitions(pk, offeringDefinitions);
	}

	/**
	* Clears all associations between the offering bundle and its offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle to clear the associated offering definitions from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOfferingDefinitions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearOfferingDefinitions(pk);
	}

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingDefinition(long pk,
		long offeringDefinitionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingDefinition(pk, offeringDefinitionPK);
	}

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingDefinition(pk, offeringDefinition);
	}

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingDefinitions(pk, offeringDefinitions);
	}

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions to be associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingDefinitions(long pk,
		long[] offeringDefinitionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setOfferingDefinitions(pk, offeringDefinitionPKs);
	}

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions to be associated with the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
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

	/**
	 * @deprecated
	 */
	public void setPersistence(OfferingBundlePersistence persistence) {
	}

	private static OfferingBundlePersistence _persistence;
}