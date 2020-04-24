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
 * Provides a wrapper for {@link TestrayProductVersionLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayProductVersionLocalService
 * @generated
 */
@ProviderType
public class TestrayProductVersionLocalServiceWrapper
	implements TestrayProductVersionLocalService,
			   ServiceWrapper<TestrayProductVersionLocalService> {

	public TestrayProductVersionLocalServiceWrapper(
		TestrayProductVersionLocalService testrayProductVersionLocalService) {

		_testrayProductVersionLocalService = testrayProductVersionLocalService;
	}

	/**
	 * Adds the testray product version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
		addTestrayProductVersion(
			com.liferay.osb.testray.model.TestrayProductVersion
				testrayProductVersion) {

		return _testrayProductVersionLocalService.addTestrayProductVersion(
			testrayProductVersion);
	}

	/**
	 * Creates a new testray product version with the primary key. Does not add the testray product version to the database.
	 *
	 * @param testrayProductVersionId the primary key for the new testray product version
	 * @return the new testray product version
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
		createTestrayProductVersion(long testrayProductVersionId) {

		return _testrayProductVersionLocalService.createTestrayProductVersion(
			testrayProductVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayProductVersionLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version that was removed
	 * @throws PortalException if a testray product version with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
			deleteTestrayProductVersion(long testrayProductVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayProductVersionLocalService.deleteTestrayProductVersion(
			testrayProductVersionId);
	}

	/**
	 * Deletes the testray product version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
		deleteTestrayProductVersion(
			com.liferay.osb.testray.model.TestrayProductVersion
				testrayProductVersion) {

		return _testrayProductVersionLocalService.deleteTestrayProductVersion(
			testrayProductVersion);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayProductVersionLocalService.dynamicQuery();
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

		return _testrayProductVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayProductVersionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayProductVersionLocalService.dynamicQuery(
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

		return _testrayProductVersionLocalService.dynamicQueryCount(
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

		return _testrayProductVersionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
		fetchTestrayProductVersion(long testrayProductVersionId) {

		return _testrayProductVersionLocalService.fetchTestrayProductVersion(
			testrayProductVersionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayProductVersionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayProductVersionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayProductVersionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayProductVersionLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the testray product version with the primary key.
	 *
	 * @param testrayProductVersionId the primary key of the testray product version
	 * @return the testray product version
	 * @throws PortalException if a testray product version with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
			getTestrayProductVersion(long testrayProductVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayProductVersionLocalService.getTestrayProductVersion(
			testrayProductVersionId);
	}

	/**
	 * Returns a range of all the testray product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayProductVersionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray product versions
	 * @param end the upper bound of the range of testray product versions (not inclusive)
	 * @return the range of testray product versions
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayProductVersion>
		getTestrayProductVersions(int start, int end) {

		return _testrayProductVersionLocalService.getTestrayProductVersions(
			start, end);
	}

	/**
	 * Returns the number of testray product versions.
	 *
	 * @return the number of testray product versions
	 */
	@Override
	public int getTestrayProductVersionsCount() {
		return _testrayProductVersionLocalService.
			getTestrayProductVersionsCount();
	}

	/**
	 * Updates the testray product version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayProductVersion the testray product version
	 * @return the testray product version that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayProductVersion
		updateTestrayProductVersion(
			com.liferay.osb.testray.model.TestrayProductVersion
				testrayProductVersion) {

		return _testrayProductVersionLocalService.updateTestrayProductVersion(
			testrayProductVersion);
	}

	@Override
	public TestrayProductVersionLocalService getWrappedService() {
		return _testrayProductVersionLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayProductVersionLocalService testrayProductVersionLocalService) {

		_testrayProductVersionLocalService = testrayProductVersionLocalService;
	}

	private TestrayProductVersionLocalService
		_testrayProductVersionLocalService;

}