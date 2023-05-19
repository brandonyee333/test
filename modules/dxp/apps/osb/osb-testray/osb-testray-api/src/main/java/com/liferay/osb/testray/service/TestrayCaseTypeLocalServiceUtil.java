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

import com.liferay.osb.testray.model.TestrayCaseType;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for TestrayCaseType. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayCaseTypeLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseTypeLocalService
 * @generated
 */
public class TestrayCaseTypeLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayCaseTypeLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray case type to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayCaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was added
	 */
	public static TestrayCaseType addTestrayCaseType(
		TestrayCaseType testrayCaseType) {

		return getService().addTestrayCaseType(testrayCaseType);
	}

	public static void addTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		getService().addTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	public static void addTestrayTaskTestrayCaseType(
		long testrayTaskId, TestrayCaseType testrayCaseType) {

		getService().addTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseType);
	}

	public static void addTestrayTaskTestrayCaseTypes(
		long testrayTaskId, List<TestrayCaseType> testrayCaseTypes) {

		getService().addTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypes);
	}

	public static void addTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		getService().addTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
	}

	public static void clearTestrayTaskTestrayCaseTypes(long testrayTaskId) {
		getService().clearTestrayTaskTestrayCaseTypes(testrayTaskId);
	}

	/**
	 * Creates a new testray case type with the primary key. Does not add the testray case type to the database.
	 *
	 * @param testrayCaseTypeId the primary key for the new testray case type
	 * @return the new testray case type
	 */
	public static TestrayCaseType createTestrayCaseType(
		long testrayCaseTypeId) {

		return getService().createTestrayCaseType(testrayCaseTypeId);
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
	 * Deletes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayCaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws PortalException if a testray case type with the primary key could not be found
	 */
	public static TestrayCaseType deleteTestrayCaseType(long testrayCaseTypeId)
		throws PortalException {

		return getService().deleteTestrayCaseType(testrayCaseTypeId);
	}

	/**
	 * Deletes the testray case type from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayCaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was removed
	 */
	public static TestrayCaseType deleteTestrayCaseType(
		TestrayCaseType testrayCaseType) {

		return getService().deleteTestrayCaseType(testrayCaseType);
	}

	public static void deleteTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		getService().deleteTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	public static void deleteTestrayTaskTestrayCaseType(
		long testrayTaskId, TestrayCaseType testrayCaseType) {

		getService().deleteTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseType);
	}

	public static void deleteTestrayTaskTestrayCaseTypes(
		long testrayTaskId, List<TestrayCaseType> testrayCaseTypes) {

		getService().deleteTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypes);
	}

	public static void deleteTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		getService().deleteTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>.
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

	public static TestrayCaseType fetchTestrayCaseType(long testrayCaseTypeId) {
		return getService().fetchTestrayCaseType(testrayCaseTypeId);
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
	 * Returns the testray case type with the primary key.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type
	 * @throws PortalException if a testray case type with the primary key could not be found
	 */
	public static TestrayCaseType getTestrayCaseType(long testrayCaseTypeId)
		throws PortalException {

		return getService().getTestrayCaseType(testrayCaseTypeId);
	}

	/**
	 * Returns a range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray case types
	 */
	public static List<TestrayCaseType> getTestrayCaseTypes(
		int start, int end) {

		return getService().getTestrayCaseTypes(start, end);
	}

	/**
	 * Returns the number of testray case types.
	 *
	 * @return the number of testray case types
	 */
	public static int getTestrayCaseTypesCount() {
		return getService().getTestrayCaseTypesCount();
	}

	/**
	 * Returns the testrayTaskIds of the testray tasks associated with the testray case type.
	 *
	 * @param testrayCaseTypeId the testrayCaseTypeId of the testray case type
	 * @return long[] the testrayTaskIds of testray tasks associated with the testray case type
	 */
	public static long[] getTestrayTaskPrimaryKeys(long testrayCaseTypeId) {
		return getService().getTestrayTaskPrimaryKeys(testrayCaseTypeId);
	}

	public static List<TestrayCaseType> getTestrayTaskTestrayCaseTypes(
		long testrayTaskId) {

		return getService().getTestrayTaskTestrayCaseTypes(testrayTaskId);
	}

	public static List<TestrayCaseType> getTestrayTaskTestrayCaseTypes(
		long testrayTaskId, int start, int end) {

		return getService().getTestrayTaskTestrayCaseTypes(
			testrayTaskId, start, end);
	}

	public static List<TestrayCaseType> getTestrayTaskTestrayCaseTypes(
		long testrayTaskId, int start, int end,
		OrderByComparator<TestrayCaseType> orderByComparator) {

		return getService().getTestrayTaskTestrayCaseTypes(
			testrayTaskId, start, end, orderByComparator);
	}

	public static int getTestrayTaskTestrayCaseTypesCount(long testrayTaskId) {
		return getService().getTestrayTaskTestrayCaseTypesCount(testrayTaskId);
	}

	public static boolean hasTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		return getService().hasTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	public static boolean hasTestrayTaskTestrayCaseTypes(long testrayTaskId) {
		return getService().hasTestrayTaskTestrayCaseTypes(testrayTaskId);
	}

	public static void setTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		getService().setTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
	}

	/**
	 * Updates the testray case type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayCaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was updated
	 */
	public static TestrayCaseType updateTestrayCaseType(
		TestrayCaseType testrayCaseType) {

		return getService().updateTestrayCaseType(testrayCaseType);
	}

	public static TestrayCaseTypeLocalService getService() {
		return _service;
	}

	public static void setService(TestrayCaseTypeLocalService service) {
		_service = service;
	}

	private static volatile TestrayCaseTypeLocalService _service;

}