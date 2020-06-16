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
 * Provides the local service utility for TestrayFactorCategory. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayFactorCategoryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayFactorCategoryLocalService
 * @generated
 */
public class TestrayFactorCategoryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayFactorCategoryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray factor category to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was added
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
		addTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return getService().addTestrayFactorCategory(testrayFactorCategory);
	}

	/**
	 * Creates a new testray factor category with the primary key. Does not add the testray factor category to the database.
	 *
	 * @param testrayFactorCategoryId the primary key for the new testray factor category
	 * @return the new testray factor category
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
		createTestrayFactorCategory(long testrayFactorCategoryId) {

		return getService().createTestrayFactorCategory(
			testrayFactorCategoryId);
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
	 * Deletes the testray factor category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category that was removed
	 * @throws PortalException if a testray factor category with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
			deleteTestrayFactorCategory(long testrayFactorCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayFactorCategory(
			testrayFactorCategoryId);
	}

	/**
	 * Deletes the testray factor category from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
		deleteTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return getService().deleteTestrayFactorCategory(testrayFactorCategory);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>.
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

	public static com.liferay.osb.testray.model.TestrayFactorCategory
		fetchTestrayFactorCategory(long testrayFactorCategoryId) {

		return getService().fetchTestrayFactorCategory(testrayFactorCategoryId);
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
	 * Returns a range of all the testray factor categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor categories
	 * @param end the upper bound of the range of testray factor categories (not inclusive)
	 * @return the range of testray factor categories
	 */
	public static java.util.List
		<com.liferay.osb.testray.model.TestrayFactorCategory>
			getTestrayFactorCategories(int start, int end) {

		return getService().getTestrayFactorCategories(start, end);
	}

	/**
	 * Returns the number of testray factor categories.
	 *
	 * @return the number of testray factor categories
	 */
	public static int getTestrayFactorCategoriesCount() {
		return getService().getTestrayFactorCategoriesCount();
	}

	/**
	 * Returns the testray factor category with the primary key.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category
	 * @throws PortalException if a testray factor category with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
			getTestrayFactorCategory(long testrayFactorCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayFactorCategory(testrayFactorCategoryId);
	}

	/**
	 * Updates the testray factor category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayFactorCategory
		updateTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return getService().updateTestrayFactorCategory(testrayFactorCategory);
	}

	public static TestrayFactorCategoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayFactorCategoryLocalService, TestrayFactorCategoryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayFactorCategoryLocalService.class);

		ServiceTracker
			<TestrayFactorCategoryLocalService,
			 TestrayFactorCategoryLocalService> serviceTracker =
				new ServiceTracker
					<TestrayFactorCategoryLocalService,
					 TestrayFactorCategoryLocalService>(
						 bundle.getBundleContext(),
						 TestrayFactorCategoryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}