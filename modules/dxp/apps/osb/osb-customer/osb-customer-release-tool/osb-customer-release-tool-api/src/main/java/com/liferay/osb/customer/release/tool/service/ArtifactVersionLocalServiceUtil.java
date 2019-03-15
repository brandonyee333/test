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

package com.liferay.osb.customer.release.tool.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ArtifactVersion. This utility wraps
 * <code>com.liferay.osb.customer.release.tool.service.impl.ArtifactVersionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionLocalService
 * @generated
 */
@ProviderType
public class ArtifactVersionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.tool.service.impl.ArtifactVersionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the artifact version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param artifactVersion the artifact version
	 * @return the artifact version that was added
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
		addArtifactVersion(
			com.liferay.osb.customer.release.tool.model.ArtifactVersion
				artifactVersion) {

		return getService().addArtifactVersion(artifactVersion);
	}

	/**
	 * Creates a new artifact version with the primary key. Does not add the artifact version to the database.
	 *
	 * @param artifactVersionId the primary key for the new artifact version
	 * @return the new artifact version
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
		createArtifactVersion(long artifactVersionId) {

		return getService().createArtifactVersion(artifactVersionId);
	}

	/**
	 * Deletes the artifact version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artifactVersion the artifact version
	 * @return the artifact version that was removed
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
		deleteArtifactVersion(
			com.liferay.osb.customer.release.tool.model.ArtifactVersion
				artifactVersion) {

		return getService().deleteArtifactVersion(artifactVersion);
	}

	/**
	 * Deletes the artifact version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param artifactVersionId the primary key of the artifact version
	 * @return the artifact version that was removed
	 * @throws PortalException if a artifact version with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
			deleteArtifactVersion(long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteArtifactVersion(artifactVersionId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
		fetchArtifactVersion(long artifactVersionId) {

		return getService().fetchArtifactVersion(artifactVersionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the artifact version with the primary key.
	 *
	 * @param artifactVersionId the primary key of the artifact version
	 * @return the artifact version
	 * @throws PortalException if a artifact version with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
			getArtifactVersion(long artifactVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getArtifactVersion(artifactVersionId);
	}

	/**
	 * Returns a range of all the artifact versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of artifact versions
	 * @param end the upper bound of the range of artifact versions (not inclusive)
	 * @return the range of artifact versions
	 */
	public static java.util.List
		<com.liferay.osb.customer.release.tool.model.ArtifactVersion>
			getArtifactVersions(int start, int end) {

		return getService().getArtifactVersions(start, end);
	}

	/**
	 * Returns the number of artifact versions.
	 *
	 * @return the number of artifact versions
	 */
	public static int getArtifactVersionsCount() {
		return getService().getArtifactVersionsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the artifact version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param artifactVersion the artifact version
	 * @return the artifact version that was updated
	 */
	public static com.liferay.osb.customer.release.tool.model.ArtifactVersion
		updateArtifactVersion(
			com.liferay.osb.customer.release.tool.model.ArtifactVersion
				artifactVersion) {

		return getService().updateArtifactVersion(artifactVersion);
	}

	public static ArtifactVersionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ArtifactVersionLocalService, ArtifactVersionLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ArtifactVersionLocalService.class);

		ServiceTracker<ArtifactVersionLocalService, ArtifactVersionLocalService>
			serviceTracker =
				new ServiceTracker
					<ArtifactVersionLocalService, ArtifactVersionLocalService>(
						bundle.getBundleContext(),
						ArtifactVersionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}