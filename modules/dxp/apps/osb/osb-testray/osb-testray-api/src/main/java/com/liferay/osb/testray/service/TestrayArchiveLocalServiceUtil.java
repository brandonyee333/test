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

import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

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
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayArchiveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was added
	 */
	public static TestrayArchive addTestrayArchive(
		TestrayArchive testrayArchive) {

		return getService().addTestrayArchive(testrayArchive);
	}

	/**
	 * Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	 *
	 * @param testrayArchiveId the primary key for the new testray archive
	 * @return the new testray archive
	 */
	public static TestrayArchive createTestrayArchive(long testrayArchiveId) {
		return getService().createTestrayArchive(testrayArchiveId);
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
	 * Deletes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayArchiveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	public static TestrayArchive deleteTestrayArchive(long testrayArchiveId)
		throws PortalException {

		return getService().deleteTestrayArchive(testrayArchiveId);
	}

	/**
	 * Deletes the testray archive from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayArchiveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was removed
	 */
	public static TestrayArchive deleteTestrayArchive(
		TestrayArchive testrayArchive) {

		return getService().deleteTestrayArchive(testrayArchive);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>.
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

	public static TestrayArchive fetchTestrayArchive(long testrayArchiveId) {
		return getService().fetchTestrayArchive(testrayArchiveId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.osb.testray.model.TestrayArchiveCompressedDataBlobModel
			getCompressedDataBlobModel(Serializable primaryKey) {

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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray archive with the primary key.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	public static TestrayArchive getTestrayArchive(long testrayArchiveId)
		throws PortalException {

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
	public static List<TestrayArchive> getTestrayArchives(int start, int end) {
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

	public static InputStream openCompressedDataInputStream(
		long testrayArchiveId) {

		return getService().openCompressedDataInputStream(testrayArchiveId);
	}

	/**
	 * Updates the testray archive in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayArchiveLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was updated
	 */
	public static TestrayArchive updateTestrayArchive(
		TestrayArchive testrayArchive) {

		return getService().updateTestrayArchive(testrayArchive);
	}

	public static TestrayArchiveLocalService getService() {
		return _service;
	}

	public static void setService(TestrayArchiveLocalService service) {
		_service = service;
	}

	private static volatile TestrayArchiveLocalService _service;

}