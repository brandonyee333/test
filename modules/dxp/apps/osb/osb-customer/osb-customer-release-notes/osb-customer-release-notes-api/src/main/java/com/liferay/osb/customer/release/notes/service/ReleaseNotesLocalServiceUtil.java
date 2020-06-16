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

package com.liferay.osb.customer.release.notes.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ReleaseNotes. This utility wraps
 * <code>com.liferay.osb.customer.release.notes.service.impl.ReleaseNotesLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesLocalService
 * @generated
 */
public class ReleaseNotesLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.notes.service.impl.ReleaseNotesLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
			addReleaseNotes(long userId, String name, String jiraIssueKeys)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addReleaseNotes(userId, name, jiraIssueKeys);
	}

	/**
	 * Adds the release notes to the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotes the release notes
	 * @return the release notes that was added
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		addReleaseNotes(
			com.liferay.osb.customer.release.notes.model.ReleaseNotes
				releaseNotes) {

		return getService().addReleaseNotes(releaseNotes);
	}

	/**
	 * Creates a new release notes with the primary key. Does not add the release notes to the database.
	 *
	 * @param releaseNotesId the primary key for the new release notes
	 * @return the new release notes
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		createReleaseNotes(long releaseNotesId) {

		return getService().createReleaseNotes(releaseNotesId);
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

	/**
	 * Deletes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes that was removed
	 * @throws PortalException if a release notes with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
			deleteReleaseNotes(long releaseNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteReleaseNotes(releaseNotesId);
	}

	/**
	 * Deletes the release notes from the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotes the release notes
	 * @return the release notes that was removed
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		deleteReleaseNotes(
			com.liferay.osb.customer.release.notes.model.ReleaseNotes
				releaseNotes) {

		return getService().deleteReleaseNotes(releaseNotes);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl</code>.
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

	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		fetchReleaseNotes(long releaseNotesId) {

		return getService().fetchReleaseNotes(releaseNotesId);
	}

	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		fetchReleaseNotes(String name) {

		return getService().fetchReleaseNotes(name);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the release notes with the primary key.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes
	 * @throws PortalException if a release notes with the primary key could not be found
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
			getReleaseNotes(long releaseNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getReleaseNotes(releaseNotesId);
	}

	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
			getReleaseNotesByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getReleaseNotesByUuid(uuid);
	}

	/**
	 * Returns a range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of release noteses
	 */
	public static java.util.List
		<com.liferay.osb.customer.release.notes.model.ReleaseNotes>
			getReleaseNoteses(int start, int end) {

		return getService().getReleaseNoteses(start, end);
	}

	/**
	 * Returns the number of release noteses.
	 *
	 * @return the number of release noteses
	 */
	public static int getReleaseNotesesCount() {
		return getService().getReleaseNotesesCount();
	}

	public static java.util.List
		<com.liferay.osb.customer.release.notes.model.ReleaseNotes> search(
			String name, int start, int end) {

		return getService().search(name, start, end);
	}

	public static int searchCount(String name) {
		return getService().searchCount(name);
	}

	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
			updateReleaseNotes(
				long releaseNotesId, String name, String jiraIssueKeys)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateReleaseNotes(
			releaseNotesId, name, jiraIssueKeys);
	}

	/**
	 * Updates the release notes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotes the release notes
	 * @return the release notes that was updated
	 */
	public static com.liferay.osb.customer.release.notes.model.ReleaseNotes
		updateReleaseNotes(
			com.liferay.osb.customer.release.notes.model.ReleaseNotes
				releaseNotes) {

		return getService().updateReleaseNotes(releaseNotes);
	}

	public static ReleaseNotesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ReleaseNotesLocalService, ReleaseNotesLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReleaseNotesLocalService.class);

		ServiceTracker<ReleaseNotesLocalService, ReleaseNotesLocalService>
			serviceTracker =
				new ServiceTracker
					<ReleaseNotesLocalService, ReleaseNotesLocalService>(
						bundle.getBundleContext(),
						ReleaseNotesLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}