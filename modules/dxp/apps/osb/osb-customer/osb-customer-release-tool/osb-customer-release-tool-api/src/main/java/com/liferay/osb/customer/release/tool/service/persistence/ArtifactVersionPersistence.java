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

import com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException;
import com.liferay.osb.customer.release.tool.model.ArtifactVersion;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the artifact version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.release.tool.service.persistence.impl.ArtifactVersionPersistenceImpl
 * @see ArtifactVersionUtil
 * @generated
 */
@ProviderType
public interface ArtifactVersionPersistence extends BasePersistence<ArtifactVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtifactVersionUtil} to access the artifact version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the artifact versions where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @return the matching artifact versions
	*/
	public java.util.List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId);

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
	public java.util.List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end);

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
	public java.util.List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator);

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
	public java.util.List<ArtifactVersion> findByReleaseAssetCategoryId(
		long releaseAssetCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public ArtifactVersion findByReleaseAssetCategoryId_First(
		long releaseAssetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator)
		throws NoSuchArtifactVersionException;

	/**
	* Returns the first artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public ArtifactVersion fetchByReleaseAssetCategoryId_First(
		long releaseAssetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator);

	/**
	* Returns the last artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public ArtifactVersion findByReleaseAssetCategoryId_Last(
		long releaseAssetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator)
		throws NoSuchArtifactVersionException;

	/**
	* Returns the last artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public ArtifactVersion fetchByReleaseAssetCategoryId_Last(
		long releaseAssetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator);

	/**
	* Returns the artifact versions before and after the current artifact version in the ordered set where releaseAssetCategoryId = &#63;.
	*
	* @param artifactVersionId the primary key of the current artifact version
	* @param releaseAssetCategoryId the release asset category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artifact version
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public ArtifactVersion[] findByReleaseAssetCategoryId_PrevAndNext(
		long artifactVersionId, long releaseAssetCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator)
		throws NoSuchArtifactVersionException;

	/**
	* Removes all the artifact versions where releaseAssetCategoryId = &#63; from the database.
	*
	* @param releaseAssetCategoryId the release asset category ID
	*/
	public void removeByReleaseAssetCategoryId(long releaseAssetCategoryId);

	/**
	* Returns the number of artifact versions where releaseAssetCategoryId = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @return the number of matching artifact versions
	*/
	public int countByReleaseAssetCategoryId(long releaseAssetCategoryId);

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or throws a {@link NoSuchArtifactVersionException} if it could not be found.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the matching artifact version
	* @throws NoSuchArtifactVersionException if a matching artifact version could not be found
	*/
	public ArtifactVersion findByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name)
		throws NoSuchArtifactVersionException;

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public ArtifactVersion fetchByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name);

	/**
	* Returns the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artifact version, or <code>null</code> if a matching artifact version could not be found
	*/
	public ArtifactVersion fetchByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the artifact version where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63; from the database.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the artifact version that was removed
	*/
	public ArtifactVersion removeByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name)
		throws NoSuchArtifactVersionException;

	/**
	* Returns the number of artifact versions where releaseAssetCategoryId = &#63; and group = &#63; and name = &#63;.
	*
	* @param releaseAssetCategoryId the release asset category ID
	* @param group the group
	* @param name the name
	* @return the number of matching artifact versions
	*/
	public int countByRACI_G_N(long releaseAssetCategoryId,
		java.lang.String group, java.lang.String name);

	/**
	* Caches the artifact version in the entity cache if it is enabled.
	*
	* @param artifactVersion the artifact version
	*/
	public void cacheResult(ArtifactVersion artifactVersion);

	/**
	* Caches the artifact versions in the entity cache if it is enabled.
	*
	* @param artifactVersions the artifact versions
	*/
	public void cacheResult(java.util.List<ArtifactVersion> artifactVersions);

	/**
	* Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	*
	* @param artifactVersionId the primary key for the new artifact version
	* @return the new artifact version
	*/
	public ArtifactVersion create(long artifactVersionId);

	/**
	* Removes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version that was removed
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public ArtifactVersion remove(long artifactVersionId)
		throws NoSuchArtifactVersionException;

	public ArtifactVersion updateImpl(ArtifactVersion artifactVersion);

	/**
	* Returns the artifact version with the primary key or throws a {@link NoSuchArtifactVersionException} if it could not be found.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version
	* @throws NoSuchArtifactVersionException if a artifact version with the primary key could not be found
	*/
	public ArtifactVersion findByPrimaryKey(long artifactVersionId)
		throws NoSuchArtifactVersionException;

	/**
	* Returns the artifact version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version, or <code>null</code> if a artifact version with the primary key could not be found
	*/
	public ArtifactVersion fetchByPrimaryKey(long artifactVersionId);

	@Override
	public java.util.Map<java.io.Serializable, ArtifactVersion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the artifact versions.
	*
	* @return the artifact versions
	*/
	public java.util.List<ArtifactVersion> findAll();

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
	public java.util.List<ArtifactVersion> findAll(int start, int end);

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
	public java.util.List<ArtifactVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator);

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
	public java.util.List<ArtifactVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the artifact versions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of artifact versions.
	*
	* @return the number of artifact versions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}