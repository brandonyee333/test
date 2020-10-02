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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the artifact version service. This utility wraps {@link com.liferay.osb.customer.release.tool.service.persistence.impl.ArtifactVersionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionPersistence
 * @see com.liferay.osb.customer.release.tool.service.persistence.impl.ArtifactVersionPersistenceImpl
 * @generated
 */
@ProviderType
public class ArtifactVersionUtil {
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
	public static void clearCache(ArtifactVersion artifactVersion) {
		getPersistence().clearCache(artifactVersion);
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
	public static List<ArtifactVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ArtifactVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ArtifactVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ArtifactVersion update(ArtifactVersion artifactVersion) {
		return getPersistence().update(artifactVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ArtifactVersion update(ArtifactVersion artifactVersion,
		ServiceContext serviceContext) {
		return getPersistence().update(artifactVersion, serviceContext);
	}

	/**
	* Returns all the artifact versions where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @return the matching artifact versions
	*/
	public static List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId) {
		return getPersistence()
				   .findByReleaseAssetCategoryId(releaseAssetCategoryId);
	}

	/**
	* Returns a range of all the artifact versions where releaseAssetCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @return the range of matching artifact versions
	*/
	public static List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end) {
		return getPersistence()
				   .findByReleaseAssetCategoryId(releaseAssetCategoryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the artifact versions where releaseAssetCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artifact versions
	*/
	public static List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator) {
		return getPersistence()
				   .findByReleaseAssetCategoryId(releaseAssetCategoryId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artifact versions where releaseAssetCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artifact versions
	*/
	public static List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByReleaseAssetCategoryId(releaseAssetCategoryId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public static ArtifactVersion findByReleaseAssetCategoryId_First(
		long releaseAssetCategoryId,
		OrderByComparator<ArtifactVersion> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence()
				   .findByReleaseAssetCategoryId_First(releaseAssetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the first artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public static ArtifactVersion fetchByReleaseAssetCategoryId_First(
		long releaseAssetCategoryId,
		OrderByComparator<ArtifactVersion> orderByComparator) {
		return getPersistence()
				   .fetchByReleaseAssetCategoryId_First(releaseAssetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public static ArtifactVersion findByReleaseAssetCategoryId_Last(
		long releaseAssetCategoryId,
		OrderByComparator<ArtifactVersion> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence()
				   .findByReleaseAssetCategoryId_Last(releaseAssetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the last artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public static ArtifactVersion fetchByReleaseAssetCategoryId_Last(
		long releaseAssetCategoryId,
		OrderByComparator<ArtifactVersion> orderByComparator) {
		return getPersistence()
				   .fetchByReleaseAssetCategoryId_Last(releaseAssetCategoryId,
			orderByComparator);
	}

	/**
	* Returns the artifact versions before and after the current artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param artifactVersionId the primary key of the current artifact version
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artifact version
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public static ArtifactVersion[] findByReleaseAssetCategoryId_PrevAndNext(
		long artifactVersionId, long releaseAssetCategoryId,
		OrderByComparator<ArtifactVersion> orderByComparator)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence()
				   .findByReleaseAssetCategoryId_PrevAndNext(artifactVersionId,
			releaseAssetCategoryId, orderByComparator);
	}

	/**
	* Removes all the artifact versions where releaseAssetCategoryId = &#63; from the database.
	*
	* @param releaseAssetCategoryId the release asset category ID
	*/
	public static void removeByReleaseAssetCategoryId(
		long releaseAssetCategoryId) {
		getPersistence().removeByReleaseAssetCategoryId(releaseAssetCategoryId);
	}

	/**
	* Returns the number of artifact versions where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @return the number of matching artifact versions
	*/
	public static int countByReleaseAssetCategoryId(long releaseAssetCategoryId) {
		return getPersistence()
				   .countByReleaseAssetCategoryId(releaseAssetCategoryId);
	}

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or throws a {@link NoSuchArtifactVersionException} if it could not be found.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public static ArtifactVersion findByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence()
				   .findByRACI_G_N(releaseAssetCategoryId, group, name);
	}

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public static ArtifactVersion fetchByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name) {
		return getPersistence()
				   .fetchByRACI_G_N(releaseAssetCategoryId, group, name);
	}

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public static ArtifactVersion fetchByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByRACI_G_N(releaseAssetCategoryId, group, name,
			retrieveFromCache);
	}

	/**
	* Removes the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; from the database.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the artifact version that was removed
	*/
	public static ArtifactVersion removeByRACI_G_N(
		long releaseAssetCategoryId, java.lang.String group,
		java.lang.String name)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence()
				   .removeByRACI_G_N(releaseAssetCategoryId, group, name);
	}

	/**
	* Returns the number of artifact versions where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the number of matching artifact versions
	*/
	public static int countByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name) {
		return getPersistence()
				   .countByRACI_G_N(releaseAssetCategoryId, group, name);
	}

	/**
	* Caches the artifact version in the entity cache if it is enabled.
	*
	* @param artifactVersion the artifact version
	*/
	public static void cacheResult(ArtifactVersion artifactVersion) {
		getPersistence().cacheResult(artifactVersion);
	}

	/**
	* Caches the artifact versions in the entity cache if it is enabled.
	*
	* @param artifactVersions the artifact versions
	*/
	public static void cacheResult(List<ArtifactVersion> artifactVersions) {
		getPersistence().cacheResult(artifactVersions);
	}

	/**
	* Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	*
	* @param artifactVersionId the primary key for the new artifact version
	* @return the new artifact version
	*/
	public static ArtifactVersion create(long artifactVersionId) {
		return getPersistence().create(artifactVersionId);
	}

	/**
	* Removes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version that was removed
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public static ArtifactVersion remove(long artifactVersionId)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence().remove(artifactVersionId);
	}

	public static ArtifactVersion updateImpl(ArtifactVersion artifactVersion) {
		return getPersistence().updateImpl(artifactVersion);
	}

	/**
	* Returns the artifact version with the primary key or throws a {@link NoSuchArtifactVersionException} if it could not be found.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public static ArtifactVersion findByPrimaryKey(long artifactVersionId)
		throws com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException {
		return getPersistence().findByPrimaryKey(artifactVersionId);
	}

	/**
	* Returns the artifact version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version, or <code>null</code> if a artifact version with the primary key could not be found
	*/
	public static ArtifactVersion fetchByPrimaryKey(long artifactVersionId) {
		return getPersistence().fetchByPrimaryKey(artifactVersionId);
	}

	public static java.util.Map<java.io.Serializable, ArtifactVersion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the artifact versions.
	*
	* @return the artifact versions
	*/
	public static List<ArtifactVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the artifact versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @return the range of artifact versions
	*/
	public static List<ArtifactVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the artifact versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of artifact versions
	*/
	public static List<ArtifactVersion> findAll(int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artifact versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of artifact versions
	*/
	public static List<ArtifactVersion> findAll(int start, int end,
		OrderByComparator<ArtifactVersion> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the artifact versions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of artifact versions.
	*
	* @return the number of artifact versions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ArtifactVersionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArtifactVersionPersistence, ArtifactVersionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ArtifactVersionPersistence.class);
}