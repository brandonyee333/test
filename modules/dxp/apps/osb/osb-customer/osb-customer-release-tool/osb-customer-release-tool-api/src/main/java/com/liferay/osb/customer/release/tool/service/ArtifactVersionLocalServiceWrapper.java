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

package com.liferay.osb.customer.release.tool.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ArtifactVersionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionLocalService
 * @generated
 */
@ProviderType
public class ArtifactVersionLocalServiceWrapper
	implements ArtifactVersionLocalService,
		ServiceWrapper<ArtifactVersionLocalService> {
	public ArtifactVersionLocalServiceWrapper(
		ArtifactVersionLocalService artifactVersionLocalService) {
		_artifactVersionLocalService = artifactVersionLocalService;
	}

	/**
	* Adds the artifact version to the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was added
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion addArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return _artifactVersionLocalService.addArtifactVersion(artifactVersion);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion addArtifactVersion(
		long releaseAssetCategoryId, int owner, int repository,
		java.lang.String group, java.lang.String name,
		java.lang.String version, java.lang.String packaging) {
		return _artifactVersionLocalService.addArtifactVersion(releaseAssetCategoryId,
			owner, repository, group, name, version, packaging);
	}

	/**
	* Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	*
	* @param artifactVersionId the primary key for the new artifact version
	* @return the new artifact version
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion createArtifactVersion(
		long artifactVersionId) {
		return _artifactVersionLocalService.createArtifactVersion(artifactVersionId);
	}

	/**
	* Deletes the artifact version from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was removed
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion deleteArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return _artifactVersionLocalService.deleteArtifactVersion(artifactVersion);
	}

	/**
	* Deletes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version that was removed
	* @throws PortalException if a artifact version with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion deleteArtifactVersion(
		long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artifactVersionLocalService.deleteArtifactVersion(artifactVersionId);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion fetchArtifactVersion(
		long artifactVersionId) {
		return _artifactVersionLocalService.fetchArtifactVersion(artifactVersionId);
	}

	/**
	* Returns the artifact version with the primary key.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version
	* @throws PortalException if a artifact version with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion getArtifactVersion(
		long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artifactVersionLocalService.getArtifactVersion(artifactVersionId);
	}

	/**
	* Updates the artifact version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was updated
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.ArtifactVersion updateArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return _artifactVersionLocalService.updateArtifactVersion(artifactVersion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _artifactVersionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _artifactVersionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _artifactVersionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artifactVersionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artifactVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of artifact versions.
	*
	* @return the number of artifact versions
	*/
	@Override
	public int getArtifactVersionsCount() {
		return _artifactVersionLocalService.getArtifactVersionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _artifactVersionLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _artifactVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _artifactVersionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _artifactVersionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.tool.model.ArtifactVersionRange> getArtifactVersionRanges(
		long fromReleaseAssetCategoryId, long toReleaseAssetCategoryId,
		int[] owners, java.lang.String keywords, boolean changesOnly)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artifactVersionLocalService.getArtifactVersionRanges(fromReleaseAssetCategoryId,
			toReleaseAssetCategoryId, owners, keywords, changesOnly);
	}

	/**
	* Returns a range of all the artifact versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artifact versions
	* @param end the upper bound of the range of artifact versions (not inclusive)
	* @return the range of artifact versions
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.release.tool.model.ArtifactVersion> getArtifactVersions(
		int start, int end) {
		return _artifactVersionLocalService.getArtifactVersions(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _artifactVersionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _artifactVersionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ArtifactVersionLocalService getWrappedService() {
		return _artifactVersionLocalService;
	}

	@Override
	public void setWrappedService(
		ArtifactVersionLocalService artifactVersionLocalService) {
		_artifactVersionLocalService = artifactVersionLocalService;
	}

	private ArtifactVersionLocalService _artifactVersionLocalService;
}