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

import com.liferay.osb.testray.model.TestrayProductVersion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for TestrayProductVersion. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayProductVersionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayProductVersionLocalService
 * @generated
 */
public class TestrayProductVersionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayProductVersionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray product version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayProductVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was added
	 */
	public static TestrayProductVersion addTestrayProductVersion(
		TestrayProductVersion testrayProductVersion) {

		return getService().addTestrayProductVersion(testrayProductVersion);
	}

	/**
	 * Creates a new testray product version with the primary key. Does not add the testray product version to the database.
	 *
	 * @param testrayProductVersionId the primary key for the new testray product version
	 * @return the new testray product version
	 */
	public static TestrayProductVersion createTestrayProductVersion(
		long testrayProductVersionId) {

		return getService().createTestrayProductVersion(
			testrayProductVersionId);
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
	 * Deletes the testray product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayProductVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version that was removed
	 * @throws PortalException if a testray product version with the primary key could not be found
	 */
	public static TestrayProductVersion deleteTestrayProductVersion(
			long testrayProductVersionId)
		throws PortalException {

		return getService().deleteTestrayProductVersion(
			testrayProductVersionId);
	}

	/**
	 * Deletes the testray product version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayProductVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was removed
	 */
	public static TestrayProductVersion deleteTestrayProductVersion(
		TestrayProductVersion testrayProductVersion) {

		return getService().deleteTestrayProductVersion(testrayProductVersion);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>.
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

	public static TestrayProductVersion fetchTestrayProductVersion(
		long testrayProductVersionId) {

		return getService().fetchTestrayProductVersion(testrayProductVersionId);
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
	 * Returns the testray product version with the primary key.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version
	 * @throws PortalException if a testray product version with the primary key could not be found
	 */
	public static TestrayProductVersion getTestrayProductVersion(
			long testrayProductVersionId)
		throws PortalException {

		return getService().getTestrayProductVersion(testrayProductVersionId);
	}

	/**
	 * Returns a range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @return the range of testray product versions
	 */
	public static List<TestrayProductVersion> getTestrayProductVersions(
		int start, int end) {

		return getService().getTestrayProductVersions(start, end);
	}

	/**
	 * Returns the number of testray product versions.
	 *
	 * @return the number of testray product versions
	 */
	public static int getTestrayProductVersionsCount() {
		return getService().getTestrayProductVersionsCount();
	}

	/**
	 * Updates the testray product version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayProductVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was updated
	 */
	public static TestrayProductVersion updateTestrayProductVersion(
		TestrayProductVersion testrayProductVersion) {

		return getService().updateTestrayProductVersion(testrayProductVersion);
	}

	public static TestrayProductVersionLocalService getService() {
		return _service;
	}

	public static void setService(TestrayProductVersionLocalService service) {
		_service = service;
	}

	private static volatile TestrayProductVersionLocalService _service;

}