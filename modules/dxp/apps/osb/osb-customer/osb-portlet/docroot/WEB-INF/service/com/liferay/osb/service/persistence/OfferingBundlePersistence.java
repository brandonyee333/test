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

import com.liferay.osb.exception.NoSuchOfferingBundleException;
import com.liferay.osb.model.OfferingBundle;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the offering bundle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.OfferingBundlePersistenceImpl
 * @see OfferingBundleUtil
 * @generated
 */
@ProviderType
public interface OfferingBundlePersistence extends BasePersistence<OfferingBundle> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingBundleUtil} to access the offering bundle persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the offering bundle where name = &#63; or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	*
	* @param name the name
	* @return the matching offering bundle
	* @throws NoSuchOfferingBundleException if a matching offering bundle could not be found
	*/
	public OfferingBundle findByName(java.lang.String name)
		throws NoSuchOfferingBundleException;

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	*/
	public OfferingBundle fetchByName(java.lang.String name);

	/**
	* Returns the offering bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching offering bundle, or <code>null</code> if a matching offering bundle could not be found
	*/
	public OfferingBundle fetchByName(java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the offering bundle where name = &#63; from the database.
	*
	* @param name the name
	* @return the offering bundle that was removed
	*/
	public OfferingBundle removeByName(java.lang.String name)
		throws NoSuchOfferingBundleException;

	/**
	* Returns the number of offering bundles where name = &#63;.
	*
	* @param name the name
	* @return the number of matching offering bundles
	*/
	public int countByName(java.lang.String name);

	/**
	* Caches the offering bundle in the entity cache if it is enabled.
	*
	* @param offeringBundle the offering bundle
	*/
	public void cacheResult(OfferingBundle offeringBundle);

	/**
	* Caches the offering bundles in the entity cache if it is enabled.
	*
	* @param offeringBundles the offering bundles
	*/
	public void cacheResult(java.util.List<OfferingBundle> offeringBundles);

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public OfferingBundle create(long offeringBundleId);

	/**
	* Removes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	*/
	public OfferingBundle remove(long offeringBundleId)
		throws NoSuchOfferingBundleException;

	public OfferingBundle updateImpl(OfferingBundle offeringBundle);

	/**
	* Returns the offering bundle with the primary key or throws a {@link NoSuchOfferingBundleException} if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws NoSuchOfferingBundleException if a offering bundle with the primary key could not be found
	*/
	public OfferingBundle findByPrimaryKey(long offeringBundleId)
		throws NoSuchOfferingBundleException;

	/**
	* Returns the offering bundle with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle, or <code>null</code> if a offering bundle with the primary key could not be found
	*/
	public OfferingBundle fetchByPrimaryKey(long offeringBundleId);

	@Override
	public java.util.Map<java.io.Serializable, OfferingBundle> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the offering bundles.
	*
	* @return the offering bundles
	*/
	public java.util.List<OfferingBundle> findAll();

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
	public java.util.List<OfferingBundle> findAll(int start, int end);

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
	public java.util.List<OfferingBundle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingBundle> orderByComparator);

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
	public java.util.List<OfferingBundle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingBundle> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the offering bundles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return long[] of the primaryKeys of offering definitions associated with the offering bundle
	*/
	public long[] getOfferingDefinitionPrimaryKeys(long pk);

	/**
	* Returns all the offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the offering definitions associated with the offering bundle
	*/
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk);

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
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingDefinition> orderByComparator);

	/**
	* Returns the number of offering definitions associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @return the number of offering definitions associated with the offering bundle
	*/
	public int getOfferingDefinitionsSize(long pk);

	/**
	* Returns <code>true</code> if the offering definition is associated with the offering bundle.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	* @return <code>true</code> if the offering definition is associated with the offering bundle; <code>false</code> otherwise
	*/
	public boolean containsOfferingDefinition(long pk, long offeringDefinitionPK);

	/**
	* Returns <code>true</code> if the offering bundle has any offering definitions associated with it.
	*
	* @param pk the primary key of the offering bundle to check for associations with offering definitions
	* @return <code>true</code> if the offering bundle has any offering definitions associated with it; <code>false</code> otherwise
	*/
	public boolean containsOfferingDefinitions(long pk);

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	*/
	public void addOfferingDefinition(long pk, long offeringDefinitionPK);

	/**
	* Adds an association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	*/
	public void addOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition);

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	*/
	public void addOfferingDefinitions(long pk, long[] offeringDefinitionPKs);

	/**
	* Adds an association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	*/
	public void addOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions);

	/**
	* Clears all associations between the offering bundle and its offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle to clear the associated offering definitions from
	*/
	public void clearOfferingDefinitions(long pk);

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPK the primary key of the offering definition
	*/
	public void removeOfferingDefinition(long pk, long offeringDefinitionPK);

	/**
	* Removes the association between the offering bundle and the offering definition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinition the offering definition
	*/
	public void removeOfferingDefinition(long pk,
		com.liferay.osb.model.OfferingDefinition offeringDefinition);

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions
	*/
	public void removeOfferingDefinitions(long pk, long[] offeringDefinitionPKs);

	/**
	* Removes the association between the offering bundle and the offering definitions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions
	*/
	public void removeOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions);

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitionPKs the primary keys of the offering definitions to be associated with the offering bundle
	*/
	public void setOfferingDefinitions(long pk, long[] offeringDefinitionPKs);

	/**
	* Sets the offering definitions associated with the offering bundle, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering bundle
	* @param offeringDefinitions the offering definitions to be associated with the offering bundle
	*/
	public void setOfferingDefinitions(long pk,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions);
}