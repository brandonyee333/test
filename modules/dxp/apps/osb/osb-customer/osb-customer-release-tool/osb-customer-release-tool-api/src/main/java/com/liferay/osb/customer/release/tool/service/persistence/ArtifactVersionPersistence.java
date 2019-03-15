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

import com.liferay.osb.customer.release.tool.exception.NoSuchArtifactVersionException;
import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the artifact version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionUtil
 * @generated
 */
@ProviderType
public interface ArtifactVersionPersistence
	extends BasePersistence<ArtifactVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtifactVersionUtil} to access the artifact version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ArtifactVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Returns the artifact version with the primary key or throws a <code>NoSuchArtifactVersionException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of artifact versions
	 */
	public java.util.List<ArtifactVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the artifact versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of artifact versions
	 */
	public java.util.List<ArtifactVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtifactVersion>
			orderByComparator,
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
	public Set<String> getBadColumnNames();

}