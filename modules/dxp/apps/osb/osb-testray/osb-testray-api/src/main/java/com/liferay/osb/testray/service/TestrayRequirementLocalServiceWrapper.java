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
 * Provides a wrapper for {@link TestrayRequirementLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayRequirementLocalService
 * @generated
 */
@ProviderType
public class TestrayRequirementLocalServiceWrapper
	implements TestrayRequirementLocalService,
			   ServiceWrapper<TestrayRequirementLocalService> {

	public TestrayRequirementLocalServiceWrapper(
		TestrayRequirementLocalService testrayRequirementLocalService) {

		_testrayRequirementLocalService = testrayRequirementLocalService;
	}

	@Override
	public void addTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		_testrayRequirementLocalService.addTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	@Override
	public void addTestrayCaseTestrayRequirement(
		long testrayCaseId,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		_testrayRequirementLocalService.addTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	@Override
	public void addTestrayCaseTestrayRequirements(
		long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		_testrayRequirementLocalService.addTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirements);
	}

	@Override
	public void addTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		_testrayRequirementLocalService.addTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Adds the testray requirement to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
		addTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return _testrayRequirementLocalService.addTestrayRequirement(
			testrayRequirement);
	}

	@Override
	public void clearTestrayCaseTestrayRequirements(long testrayCaseId) {
		_testrayRequirementLocalService.clearTestrayCaseTestrayRequirements(
			testrayCaseId);
	}

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
		createTestrayRequirement(long testrayRequirementId) {

		return _testrayRequirementLocalService.createTestrayRequirement(
			testrayRequirementId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRequirementLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		_testrayRequirementLocalService.deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	@Override
	public void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		_testrayRequirementLocalService.deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	@Override
	public void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		_testrayRequirementLocalService.deleteTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirements);
	}

	@Override
	public void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		_testrayRequirementLocalService.deleteTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Deletes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
			deleteTestrayRequirement(long testrayRequirementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRequirementLocalService.deleteTestrayRequirement(
			testrayRequirementId);
	}

	/**
	 * Deletes the testray requirement from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
		deleteTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return _testrayRequirementLocalService.deleteTestrayRequirement(
			testrayRequirement);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayRequirementLocalService.dynamicQuery();
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

		return _testrayRequirementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRequirementLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRequirementLocalService.dynamicQuery(
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

		return _testrayRequirementLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayRequirementLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
		fetchTestrayRequirement(long testrayRequirementId) {

		return _testrayRequirementLocalService.fetchTestrayRequirement(
			testrayRequirementId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayRequirementLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayRequirementLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayRequirementLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRequirementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayCaseIds of the testray cases associated with the testray requirement.
	 *
	 * @param testrayRequirementId the testrayRequirementId of the testray requirement
	 * @return long[] the testrayCaseIds of testray cases associated with the testray requirement
	 */
	@Override
	public long[] getTestrayCasePrimaryKeys(long testrayRequirementId) {
		return _testrayRequirementLocalService.getTestrayCasePrimaryKeys(
			testrayRequirementId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayCaseTestrayRequirements(long testrayCaseId) {

		return _testrayRequirementLocalService.
			getTestrayCaseTestrayRequirements(testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayCaseTestrayRequirements(
			long testrayCaseId, int start, int end) {

		return _testrayRequirementLocalService.
			getTestrayCaseTestrayRequirements(testrayCaseId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayCaseTestrayRequirements(
			long testrayCaseId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayRequirement>
					orderByComparator) {

		return _testrayRequirementLocalService.
			getTestrayCaseTestrayRequirements(
				testrayCaseId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayCaseTestrayRequirementsCount(long testrayCaseId) {
		return _testrayRequirementLocalService.
			getTestrayCaseTestrayRequirementsCount(testrayCaseId);
	}

	/**
	 * Returns the testray requirement with the primary key.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
			getTestrayRequirement(long testrayRequirementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRequirementLocalService.getTestrayRequirement(
			testrayRequirementId);
	}

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
		getTestrayRequirements(int start, int end) {

		return _testrayRequirementLocalService.getTestrayRequirements(
			start, end);
	}

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	@Override
	public int getTestrayRequirementsCount() {
		return _testrayRequirementLocalService.getTestrayRequirementsCount();
	}

	@Override
	public boolean hasTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		return _testrayRequirementLocalService.hasTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	@Override
	public boolean hasTestrayCaseTestrayRequirements(long testrayCaseId) {
		return _testrayRequirementLocalService.
			hasTestrayCaseTestrayRequirements(testrayCaseId);
	}

	@Override
	public void setTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		_testrayRequirementLocalService.setTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Updates the testray requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRequirement
		updateTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return _testrayRequirementLocalService.updateTestrayRequirement(
			testrayRequirement);
	}

	@Override
	public TestrayRequirementLocalService getWrappedService() {
		return _testrayRequirementLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayRequirementLocalService testrayRequirementLocalService) {

		_testrayRequirementLocalService = testrayRequirementLocalService;
	}

	private TestrayRequirementLocalService _testrayRequirementLocalService;

}