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

package com.liferay.osb.testray.service;

import com.liferay.osb.testray.model.TestrayBuild;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for TestrayBuild. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayBuildLocalService
 * @generated
 */
public class TestrayBuildLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray build to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayBuildLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayBuild the testray build
	 * @return the testray build that was added
	 */
	public static TestrayBuild addTestrayBuild(TestrayBuild testrayBuild) {
		return getService().addTestrayBuild(testrayBuild);
	}

	public static void addTestrayCaseTestrayBuild(
		long testrayCaseId, long testrayBuildId) {

		getService().addTestrayCaseTestrayBuild(testrayCaseId, testrayBuildId);
	}

	public static void addTestrayCaseTestrayBuild(
		long testrayCaseId, TestrayBuild testrayBuild) {

		getService().addTestrayCaseTestrayBuild(testrayCaseId, testrayBuild);
	}

	public static void addTestrayCaseTestrayBuilds(
		long testrayCaseId, List<TestrayBuild> testrayBuilds) {

		getService().addTestrayCaseTestrayBuilds(testrayCaseId, testrayBuilds);
	}

	public static void addTestrayCaseTestrayBuilds(
		long testrayCaseId, long[] testrayBuildIds) {

		getService().addTestrayCaseTestrayBuilds(
			testrayCaseId, testrayBuildIds);
	}

	public static void clearTestrayCaseTestrayBuilds(long testrayCaseId) {
		getService().clearTestrayCaseTestrayBuilds(testrayCaseId);
	}

	/**
	 * Creates a new testray build with the primary key. Does not add the testray build to the database.
	 *
	 * @param testrayBuildId the primary key for the new testray build
	 * @return the new testray build
	 */
	public static TestrayBuild createTestrayBuild(long testrayBuildId) {
		return getService().createTestrayBuild(testrayBuildId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayBuildLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayBuildId the primary key of the testray build
	 * @return the testray build that was removed
	 * @throws PortalException if a testray build with the primary key could not be found
	 */
	public static TestrayBuild deleteTestrayBuild(long testrayBuildId)
		throws PortalException {

		return getService().deleteTestrayBuild(testrayBuildId);
	}

	/**
	 * Deletes the testray build from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayBuildLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayBuild the testray build
	 * @return the testray build that was removed
	 */
	public static TestrayBuild deleteTestrayBuild(TestrayBuild testrayBuild) {
		return getService().deleteTestrayBuild(testrayBuild);
	}

	public static void deleteTestrayCaseTestrayBuild(
		long testrayCaseId, long testrayBuildId) {

		getService().deleteTestrayCaseTestrayBuild(
			testrayCaseId, testrayBuildId);
	}

	public static void deleteTestrayCaseTestrayBuild(
		long testrayCaseId, TestrayBuild testrayBuild) {

		getService().deleteTestrayCaseTestrayBuild(testrayCaseId, testrayBuild);
	}

	public static void deleteTestrayCaseTestrayBuilds(
		long testrayCaseId, List<TestrayBuild> testrayBuilds) {

		getService().deleteTestrayCaseTestrayBuilds(
			testrayCaseId, testrayBuilds);
	}

	public static void deleteTestrayCaseTestrayBuilds(
		long testrayCaseId, long[] testrayBuildIds) {

		getService().deleteTestrayCaseTestrayBuilds(
			testrayCaseId, testrayBuildIds);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayBuildModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayBuildModelImpl</code>.
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

	public static TestrayBuild fetchTestrayBuild(long testrayBuildId) {
		return getService().fetchTestrayBuild(testrayBuildId);
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray build with the primary key.
	 *
	 * @param testrayBuildId the primary key of the testray build
	 * @return the testray build
	 * @throws PortalException if a testray build with the primary key could not be found
	 */
	public static TestrayBuild getTestrayBuild(long testrayBuildId)
		throws PortalException {

		return getService().getTestrayBuild(testrayBuildId);
	}

	/**
	 * Returns a range of all the testray builds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayBuildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray builds
	 * @param end the upper bound of the range of testray builds (not inclusive)
	 * @return the range of testray builds
	 */
	public static List<TestrayBuild> getTestrayBuilds(int start, int end) {
		return getService().getTestrayBuilds(start, end);
	}

	/**
	 * Returns the number of testray builds.
	 *
	 * @return the number of testray builds
	 */
	public static int getTestrayBuildsCount() {
		return getService().getTestrayBuildsCount();
	}

	/**
	 * Returns the testrayCaseIds of the testray cases associated with the testray build.
	 *
	 * @param testrayBuildId the testrayBuildId of the testray build
	 * @return long[] the testrayCaseIds of testray cases associated with the testray build
	 */
	public static long[] getTestrayCasePrimaryKeys(long testrayBuildId) {
		return getService().getTestrayCasePrimaryKeys(testrayBuildId);
	}

	public static List<TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId) {

		return getService().getTestrayCaseTestrayBuilds(testrayCaseId);
	}

	public static List<TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end) {

		return getService().getTestrayCaseTestrayBuilds(
			testrayCaseId, start, end);
	}

	public static List<TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestrayBuild> orderByComparator) {

		return getService().getTestrayCaseTestrayBuilds(
			testrayCaseId, start, end, orderByComparator);
	}

	public static int getTestrayCaseTestrayBuildsCount(long testrayCaseId) {
		return getService().getTestrayCaseTestrayBuildsCount(testrayCaseId);
	}

	public static boolean hasTestrayCaseTestrayBuild(
		long testrayCaseId, long testrayBuildId) {

		return getService().hasTestrayCaseTestrayBuild(
			testrayCaseId, testrayBuildId);
	}

	public static boolean hasTestrayCaseTestrayBuilds(long testrayCaseId) {
		return getService().hasTestrayCaseTestrayBuilds(testrayCaseId);
	}

	public static void setTestrayCaseTestrayBuilds(
		long testrayCaseId, long[] testrayBuildIds) {

		getService().setTestrayCaseTestrayBuilds(
			testrayCaseId, testrayBuildIds);
	}

	/**
	 * Updates the testray build in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayBuildLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayBuild the testray build
	 * @return the testray build that was updated
	 */
	public static TestrayBuild updateTestrayBuild(TestrayBuild testrayBuild) {
		return getService().updateTestrayBuild(testrayBuild);
	}

	public static TestrayBuildLocalService getService() {
		return _service;
	}

	public static void setService(TestrayBuildLocalService service) {
		_service = service;
	}

	private static volatile TestrayBuildLocalService _service;

}