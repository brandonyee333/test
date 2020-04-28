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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayFactorOptionLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayFactorOptionLocalService
 * @generated
 */
public class TestrayFactorOptionLocalServiceWrapper
	implements ServiceWrapper<TestrayFactorOptionLocalService>,
			   TestrayFactorOptionLocalService {

	public TestrayFactorOptionLocalServiceWrapper(
		TestrayFactorOptionLocalService testrayFactorOptionLocalService) {

		_testrayFactorOptionLocalService = testrayFactorOptionLocalService;
	}

	/**
	 * Adds the testray factor option to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOption the testray factor option
	 * @return the testray factor option that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
		addTestrayFactorOption(
			com.liferay.osb.testray.model.TestrayFactorOption
				testrayFactorOption) {

		return _testrayFactorOptionLocalService.addTestrayFactorOption(
			testrayFactorOption);
	}

	/**
	 * Creates a new testray factor option with the primary key. Does not add the testray factor option to the database.
	 *
	 * @param testrayFactorOptionId the primary key for the new testray factor option
	 * @return the new testray factor option
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
		createTestrayFactorOption(long testrayFactorOptionId) {

		return _testrayFactorOptionLocalService.createTestrayFactorOption(
			testrayFactorOptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorOptionLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray factor option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option that was removed
	 * @throws PortalException if a testray factor option with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
			deleteTestrayFactorOption(long testrayFactorOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorOptionLocalService.deleteTestrayFactorOption(
			testrayFactorOptionId);
	}

	/**
	 * Deletes the testray factor option from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOption the testray factor option
	 * @return the testray factor option that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
		deleteTestrayFactorOption(
			com.liferay.osb.testray.model.TestrayFactorOption
				testrayFactorOption) {

		return _testrayFactorOptionLocalService.deleteTestrayFactorOption(
			testrayFactorOption);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayFactorOptionLocalService.dynamicQuery();
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

		return _testrayFactorOptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorOptionModelImpl</code>.
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

		return _testrayFactorOptionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorOptionModelImpl</code>.
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

		return _testrayFactorOptionLocalService.dynamicQuery(
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

		return _testrayFactorOptionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayFactorOptionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
		fetchTestrayFactorOption(long testrayFactorOptionId) {

		return _testrayFactorOptionLocalService.fetchTestrayFactorOption(
			testrayFactorOptionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayFactorOptionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayFactorOptionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayFactorOptionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorOptionLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the testray factor option with the primary key.
	 *
	 * @param testrayFactorOptionId the primary key of the testray factor option
	 * @return the testray factor option
	 * @throws PortalException if a testray factor option with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
			getTestrayFactorOption(long testrayFactorOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayFactorOptionLocalService.getTestrayFactorOption(
			testrayFactorOptionId);
	}

	/**
	 * Returns a range of all the testray factor options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayFactorOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray factor options
	 * @param end the upper bound of the range of testray factor options (not inclusive)
	 * @return the range of testray factor options
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayFactorOption>
		getTestrayFactorOptions(int start, int end) {

		return _testrayFactorOptionLocalService.getTestrayFactorOptions(
			start, end);
	}

	/**
	 * Returns the number of testray factor options.
	 *
	 * @return the number of testray factor options
	 */
	@Override
	public int getTestrayFactorOptionsCount() {
		return _testrayFactorOptionLocalService.getTestrayFactorOptionsCount();
	}

	/**
	 * Updates the testray factor option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayFactorOption the testray factor option
	 * @return the testray factor option that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayFactorOption
		updateTestrayFactorOption(
			com.liferay.osb.testray.model.TestrayFactorOption
				testrayFactorOption) {

		return _testrayFactorOptionLocalService.updateTestrayFactorOption(
			testrayFactorOption);
	}

	@Override
	public TestrayFactorOptionLocalService getWrappedService() {
		return _testrayFactorOptionLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayFactorOptionLocalService testrayFactorOptionLocalService) {

		_testrayFactorOptionLocalService = testrayFactorOptionLocalService;
	}

	private TestrayFactorOptionLocalService _testrayFactorOptionLocalService;

}