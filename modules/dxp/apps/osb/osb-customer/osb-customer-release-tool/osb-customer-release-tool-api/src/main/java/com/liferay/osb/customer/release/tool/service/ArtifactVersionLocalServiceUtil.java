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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ArtifactVersion. This utility wraps
 * {@link com.liferay.osb.customer.release.tool.service.impl.ArtifactVersionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionLocalService
 * @see com.liferay.osb.customer.release.tool.service.base.ArtifactVersionLocalServiceBaseImpl
 * @see com.liferay.osb.customer.release.tool.service.impl.ArtifactVersionLocalServiceImpl
 * @generated
 */
@ProviderType
public class ArtifactVersionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.release.tool.service.impl.ArtifactVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the artifact version to the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was added
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion addArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return getService().addArtifactVersion(artifactVersion);
	}

	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion addArtifactVersion(
		long releaseAssetCategoryId, int owner, int repository,
		java.lang.String group, java.lang.String name,
		java.lang.String version, java.lang.String packaging) {
		return getService()
				   .addArtifactVersion(releaseAssetCategoryId, owner,
			repository, group, name, version, packaging);
	}

	/**
	* Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	*
	* @param artifactVersionId the primary key for the new artifact version
	* @return the new artifact version
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion createArtifactVersion(
		long artifactVersionId) {
		return getService().createArtifactVersion(artifactVersionId);
	}

	/**
	* Deletes the artifact version from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was removed
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion deleteArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return getService().deleteArtifactVersion(artifactVersion);
	}

	/**
	* Deletes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version that was removed
	* @throws PortalException if a artifact version with the primary key could not be found
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion deleteArtifactVersion(
		long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteArtifactVersion(artifactVersionId);
	}

	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion fetchArtifactVersion(
		long artifactVersionId) {
		return getService().fetchArtifactVersion(artifactVersionId);
	}

	/**
	* Returns the artifact version with the primary key.
	*
	* @param artifactVersionId the primary key of the artifact version
	* @return the artifact version
	* @throws PortalException if a artifact version with the primary key could not be found
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion getArtifactVersion(
		long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getArtifactVersion(artifactVersionId);
	}

	/**
	* Updates the artifact version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param artifactVersion the artifact version
	* @return the artifact version that was updated
	*/
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion updateArtifactVersion(
		com.liferay.osb.customer.release.tool.model.ArtifactVersion artifactVersion) {
		return getService().updateArtifactVersion(artifactVersion);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of artifact versions.
	*
	* @return the number of artifact versions
	*/
	public static int getArtifactVersionsCount() {
		return getService().getArtifactVersionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.customer.release.tool.model.ArtifactVersionRange> getArtifactVersionRanges(
		long fromReleaseAssetCategoryId, long toReleaseAssetCategoryId,
		int[] owners, java.lang.String keywords, boolean changesOnly)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getArtifactVersionRanges(fromReleaseAssetCategoryId,
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
	public static java.util.List<com.liferay.osb.customer.release.tool.model.ArtifactVersion> getArtifactVersions(
		int start, int end) {
		return getService().getArtifactVersions(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ArtifactVersionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArtifactVersionLocalService, ArtifactVersionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ArtifactVersionLocalService.class);
}