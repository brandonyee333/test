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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayArchive. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayArchiveLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayArchiveLocalService
 * @generated
 */
public class TestrayArchiveLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayArchiveLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray archive to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was added
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
		addTestrayArchive(
			com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return getService().addTestrayArchive(testrayArchive);
	}

	/**
	 * Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	 *
	 * @param testrayArchiveId the primary key for the new testray archive
	 * @return the new testray archive
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
		createTestrayArchive(long testrayArchiveId) {

		return getService().createTestrayArchive(testrayArchiveId);
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
	 * Deletes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
			deleteTestrayArchive(long testrayArchiveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayArchive(testrayArchiveId);
	}

	/**
	 * Deletes the testray archive from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
		deleteTestrayArchive(
			com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return getService().deleteTestrayArchive(testrayArchive);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>.
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

	public static com.liferay.osb.testray.model.TestrayArchive
		fetchTestrayArchive(long testrayArchiveId) {

		return getService().fetchTestrayArchive(testrayArchiveId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.osb.testray.model.TestrayArchiveCompressedDataBlobModel
			getCompressedDataBlobModel(java.io.Serializable primaryKey) {

		return getService().getCompressedDataBlobModel(primaryKey);
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
	 * Returns the testray archive with the primary key.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
			getTestrayArchive(long testrayArchiveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayArchive(testrayArchiveId);
	}

	/**
	 * Returns a range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @return the range of testray archives
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayArchive>
		getTestrayArchives(int start, int end) {

		return getService().getTestrayArchives(start, end);
	}

	/**
	 * Returns the number of testray archives.
	 *
	 * @return the number of testray archives
	 */
	public static int getTestrayArchivesCount() {
		return getService().getTestrayArchivesCount();
	}

	public static java.io.InputStream openCompressedDataInputStream(
		long testrayArchiveId) {

		return getService().openCompressedDataInputStream(testrayArchiveId);
	}

	/**
	 * Updates the testray archive in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayArchive
		updateTestrayArchive(
			com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return getService().updateTestrayArchive(testrayArchive);
	}

	public static TestrayArchiveLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayArchiveLocalService, TestrayArchiveLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayArchiveLocalService.class);

		ServiceTracker<TestrayArchiveLocalService, TestrayArchiveLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayArchiveLocalService, TestrayArchiveLocalService>(
						bundle.getBundleContext(),
						TestrayArchiveLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}