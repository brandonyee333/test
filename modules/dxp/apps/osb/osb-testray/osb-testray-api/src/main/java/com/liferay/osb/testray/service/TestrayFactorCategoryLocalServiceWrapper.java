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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayFactorCategoryLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayFactorCategoryLocalService
 * @generated
 */
@ProviderType
public class TestrayFactorCategoryLocalServiceWrapper
	implements TestrayFactorCategoryLocalService,
			   ServiceWrapper<TestrayFactorCategoryLocalService> {

	public TestrayFactorCategoryLocalServiceWrapper(
		TestrayFactorCategoryLocalService testrayFactorCategoryLocalService) {

		_testrayFactorCategoryLocalService = testrayFactorCategoryLocalService;
	}

	/**
	 * Adds the testray factor category to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
		addTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return _testrayFactorCategoryLocalService.addTestrayFactorCategory(
			testrayFactorCategory);
	}

	/**
	 * Creates a new testray factor category with the primary key. Does not add the testray factor category to the database.
	 *
	 * @param testrayFactorCategoryId the primary key for the new testray factor category
	 * @return the new testray factor category
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
		createTestrayFactorCategory(long testrayFactorCategoryId) {

		return _testrayFactorCategoryLocalService.createTestrayFactorCategory(
			testrayFactorCategoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorCategoryLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray factor category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category that was removed
	 * @throws PortalException if a testray factor category with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
			deleteTestrayFactorCategory(long testrayFactorCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorCategoryLocalService.deleteTestrayFactorCategory(
			testrayFactorCategoryId);
	}

	/**
	 * Deletes the testray factor category from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
		deleteTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return _testrayFactorCategoryLocalService.deleteTestrayFactorCategory(
			testrayFactorCategory);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayFactorCategoryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _testrayFactorCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _testrayFactorCategoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _testrayFactorCategoryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _testrayFactorCategoryLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _testrayFactorCategoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
		fetchTestrayFactorCategory(long testrayFactorCategoryId) {

		return _testrayFactorCategoryLocalService.fetchTestrayFactorCategory(
			testrayFactorCategoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayFactorCategoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayFactorCategoryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayFactorCategoryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorCategoryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the testray factor categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor categories
	 * @param end the upper bound of the range of testray factor categories (not inclusive)
	 * @return the range of testray factor categories
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayFactorCategory>
		getTestrayFactorCategories(int start, int end) {

		return _testrayFactorCategoryLocalService.getTestrayFactorCategories(
			start, end);
	}

	/**
	 * Returns the number of testray factor categories.
	 *
	 * @return the number of testray factor categories
	 */
	@Override
	public int getTestrayFactorCategoriesCount() {
		return _testrayFactorCategoryLocalService.
			getTestrayFactorCategoriesCount();
	}

	/**
	 * Returns the testray factor category with the primary key.
	 *
	 * @param testrayFactorCategoryId the primary key of the testray factor category
	 * @return the testray factor category
	 * @throws PortalException if a testray factor category with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
			getTestrayFactorCategory(long testrayFactorCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorCategoryLocalService.getTestrayFactorCategory(
			testrayFactorCategoryId);
	}

	/**
	 * Updates the testray factor category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorCategory the testray factor category
	 * @return the testray factor category that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorCategory
		updateTestrayFactorCategory(
			com.liferay.osb.testray.model.TestrayFactorCategory
				testrayFactorCategory) {

		return _testrayFactorCategoryLocalService.updateTestrayFactorCategory(
			testrayFactorCategory);
	}

	@Override
	public TestrayFactorCategoryLocalService getWrappedService() {
		return _testrayFactorCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayFactorCategoryLocalService testrayFactorCategoryLocalService) {

		_testrayFactorCategoryLocalService = testrayFactorCategoryLocalService;
	}

	private TestrayFactorCategoryLocalService
		_testrayFactorCategoryLocalService;

}