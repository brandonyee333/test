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

package com.liferay.osb.customer.release.notes.jira.service;

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for JIRAProjectVersion. This utility wraps
 * <code>com.liferay.osb.customer.release.notes.jira.service.impl.JIRAProjectVersionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersionLocalService
 * @generated
 */
public class JIRAProjectVersionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.release.notes.jira.service.impl.JIRAProjectVersionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the jira project version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JIRAProjectVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was added
	 */
	public static JIRAProjectVersion addJIRAProjectVersion(
		JIRAProjectVersion jiraProjectVersion) {

		return getService().addJIRAProjectVersion(jiraProjectVersion);
	}

	/**
	 * Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	 *
	 * @param jiraProjectVersionId the primary key for the new jira project version
	 * @return the new jira project version
	 */
	public static JIRAProjectVersion createJIRAProjectVersion(
		long jiraProjectVersionId) {

		return getService().createJIRAProjectVersion(jiraProjectVersionId);
	}

	/**
	 * Deletes the jira project version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JIRAProjectVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was removed
	 */
	public static JIRAProjectVersion deleteJIRAProjectVersion(
		JIRAProjectVersion jiraProjectVersion) {

		return getService().deleteJIRAProjectVersion(jiraProjectVersion);
	}

	/**
	 * Deletes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JIRAProjectVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version that was removed
	 * @throws PortalException if a jira project version with the primary key could not be found
	 */
	public static JIRAProjectVersion deleteJIRAProjectVersion(
			long jiraProjectVersionId)
		throws PortalException {

		return getService().deleteJIRAProjectVersion(jiraProjectVersionId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static JIRAProjectVersion fetchJIRAProjectVersion(
		long jiraProjectVersionId) {

		return getService().fetchJIRAProjectVersion(jiraProjectVersionId);
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

	public static List<JIRAProjectVersion> getJIRAProjectJIRAProjectVersion(
		long jiraProjectId, String name) {

		return getService().getJIRAProjectJIRAProjectVersion(
			jiraProjectId, name);
	}

	/**
	 * Returns the jira project version with the primary key.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version
	 * @throws PortalException if a jira project version with the primary key could not be found
	 */
	public static JIRAProjectVersion getJIRAProjectVersion(
			long jiraProjectVersionId)
		throws PortalException {

		return getService().getJIRAProjectVersion(jiraProjectVersionId);
	}

	/**
	 * Returns a range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @return the range of jira project versions
	 */
	public static List<JIRAProjectVersion> getJIRAProjectVersions(
		int start, int end) {

		return getService().getJIRAProjectVersions(start, end);
	}

	/**
	 * Returns the number of jira project versions.
	 *
	 * @return the number of jira project versions
	 */
	public static int getJIRAProjectVersionsCount() {
		return getService().getJIRAProjectVersionsCount();
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the jira project version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect JIRAProjectVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was updated
	 */
	public static JIRAProjectVersion updateJIRAProjectVersion(
		JIRAProjectVersion jiraProjectVersion) {

		return getService().updateJIRAProjectVersion(jiraProjectVersion);
	}

	public static JIRAProjectVersionLocalService getService() {
		return _service;
	}

	public static void setService(JIRAProjectVersionLocalService service) {
		_service = service;
	}

	private static volatile JIRAProjectVersionLocalService _service;

}