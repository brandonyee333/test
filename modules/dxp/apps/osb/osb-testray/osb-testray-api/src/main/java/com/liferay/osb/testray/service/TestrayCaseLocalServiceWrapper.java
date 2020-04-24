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
 * Provides a wrapper for {@link TestrayCaseLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayCaseLocalService
 * @generated
 */
@ProviderType
public class TestrayCaseLocalServiceWrapper
	implements TestrayCaseLocalService,
			   ServiceWrapper<TestrayCaseLocalService> {

	public TestrayCaseLocalServiceWrapper(
		TestrayCaseLocalService testrayCaseLocalService) {

		_testrayCaseLocalService = testrayCaseLocalService;
	}

	@Override
	public void addTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		_testrayCaseLocalService.addTestrayBuildTestrayCase(
			testrayBuildId, testrayCaseId);
	}

	@Override
	public void addTestrayBuildTestrayCase(
		long testrayBuildId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.addTestrayBuildTestrayCase(
			testrayBuildId, testrayCase);
	}

	@Override
	public void addTestrayBuildTestrayCases(
		long testrayBuildId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.addTestrayBuildTestrayCases(
			testrayBuildId, testrayCases);
	}

	@Override
	public void addTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		_testrayCaseLocalService.addTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	/**
	 * Adds the testray case to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase addTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return _testrayCaseLocalService.addTestrayCase(testrayCase);
	}

	@Override
	public void addTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		_testrayCaseLocalService.addTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	@Override
	public void addTestrayComponentTestrayCase(
		long testrayComponentId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.addTestrayComponentTestrayCase(
			testrayComponentId, testrayCase);
	}

	@Override
	public void addTestrayComponentTestrayCases(
		long testrayComponentId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.addTestrayComponentTestrayCases(
			testrayComponentId, testrayCases);
	}

	@Override
	public void addTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		_testrayCaseLocalService.addTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	@Override
	public void addTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		_testrayCaseLocalService.addTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	@Override
	public void addTestrayRequirementTestrayCase(
		long testrayRequirementId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.addTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCase);
	}

	@Override
	public void addTestrayRequirementTestrayCases(
		long testrayRequirementId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.addTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCases);
	}

	@Override
	public void addTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		_testrayCaseLocalService.addTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	@Override
	public void addTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		_testrayCaseLocalService.addTestraySuiteTestrayCase(
			testraySuiteId, testrayCaseId);
	}

	@Override
	public void addTestraySuiteTestrayCase(
		long testraySuiteId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.addTestraySuiteTestrayCase(
			testraySuiteId, testrayCase);
	}

	@Override
	public void addTestraySuiteTestrayCases(
		long testraySuiteId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.addTestraySuiteTestrayCases(
			testraySuiteId, testrayCases);
	}

	@Override
	public void addTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		_testrayCaseLocalService.addTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
	}

	@Override
	public void clearTestrayBuildTestrayCases(long testrayBuildId) {
		_testrayCaseLocalService.clearTestrayBuildTestrayCases(testrayBuildId);
	}

	@Override
	public void clearTestrayComponentTestrayCases(long testrayComponentId) {
		_testrayCaseLocalService.clearTestrayComponentTestrayCases(
			testrayComponentId);
	}

	@Override
	public void clearTestrayRequirementTestrayCases(long testrayRequirementId) {
		_testrayCaseLocalService.clearTestrayRequirementTestrayCases(
			testrayRequirementId);
	}

	@Override
	public void clearTestraySuiteTestrayCases(long testraySuiteId) {
		_testrayCaseLocalService.clearTestraySuiteTestrayCases(testraySuiteId);
	}

	/**
	 * Creates a new testray case with the primary key. Does not add the testray case to the database.
	 *
	 * @param testrayCaseId the primary key for the new testray case
	 * @return the new testray case
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase createTestrayCase(
		long testrayCaseId) {

		return _testrayCaseLocalService.createTestrayCase(testrayCaseId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		_testrayCaseLocalService.deleteTestrayBuildTestrayCase(
			testrayBuildId, testrayCaseId);
	}

	@Override
	public void deleteTestrayBuildTestrayCase(
		long testrayBuildId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.deleteTestrayBuildTestrayCase(
			testrayBuildId, testrayCase);
	}

	@Override
	public void deleteTestrayBuildTestrayCases(
		long testrayBuildId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.deleteTestrayBuildTestrayCases(
			testrayBuildId, testrayCases);
	}

	@Override
	public void deleteTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		_testrayCaseLocalService.deleteTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	/**
	 * Deletes the testray case with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case that was removed
	 * @throws PortalException if a testray case with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase deleteTestrayCase(
			long testrayCaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseLocalService.deleteTestrayCase(testrayCaseId);
	}

	/**
	 * Deletes the testray case from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase deleteTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return _testrayCaseLocalService.deleteTestrayCase(testrayCase);
	}

	@Override
	public void deleteTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		_testrayCaseLocalService.deleteTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	@Override
	public void deleteTestrayComponentTestrayCase(
		long testrayComponentId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.deleteTestrayComponentTestrayCase(
			testrayComponentId, testrayCase);
	}

	@Override
	public void deleteTestrayComponentTestrayCases(
		long testrayComponentId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.deleteTestrayComponentTestrayCases(
			testrayComponentId, testrayCases);
	}

	@Override
	public void deleteTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		_testrayCaseLocalService.deleteTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	@Override
	public void deleteTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		_testrayCaseLocalService.deleteTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	@Override
	public void deleteTestrayRequirementTestrayCase(
		long testrayRequirementId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.deleteTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCase);
	}

	@Override
	public void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.deleteTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCases);
	}

	@Override
	public void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		_testrayCaseLocalService.deleteTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	@Override
	public void deleteTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		_testrayCaseLocalService.deleteTestraySuiteTestrayCase(
			testraySuiteId, testrayCaseId);
	}

	@Override
	public void deleteTestraySuiteTestrayCase(
		long testraySuiteId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		_testrayCaseLocalService.deleteTestraySuiteTestrayCase(
			testraySuiteId, testrayCase);
	}

	@Override
	public void deleteTestraySuiteTestrayCases(
		long testraySuiteId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		_testrayCaseLocalService.deleteTestraySuiteTestrayCases(
			testraySuiteId, testrayCases);
	}

	@Override
	public void deleteTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		_testrayCaseLocalService.deleteTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayCaseLocalService.dynamicQuery();
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

		return _testrayCaseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseLocalService.dynamicQuery(
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

		return _testrayCaseLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayCaseLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayCase fetchTestrayCase(
		long testrayCaseId) {

		return _testrayCaseLocalService.fetchTestrayCase(testrayCaseId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayCaseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayCaseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayCaseLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayBuildIds of the testray builds associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayBuildIds of testray builds associated with the testray case
	 */
	@Override
	public long[] getTestrayBuildPrimaryKeys(long testrayCaseId) {
		return _testrayCaseLocalService.getTestrayBuildPrimaryKeys(
			testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(long testrayBuildId) {

		return _testrayCaseLocalService.getTestrayBuildTestrayCases(
			testrayBuildId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(long testrayBuildId, int start, int end) {

		return _testrayCaseLocalService.getTestrayBuildTestrayCases(
			testrayBuildId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(
			long testrayBuildId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return _testrayCaseLocalService.getTestrayBuildTestrayCases(
			testrayBuildId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayBuildTestrayCasesCount(long testrayBuildId) {
		return _testrayCaseLocalService.getTestrayBuildTestrayCasesCount(
			testrayBuildId);
	}

	/**
	 * Returns the testray case with the primary key.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case
	 * @throws PortalException if a testray case with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase getTestrayCase(
			long testrayCaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseLocalService.getTestrayCase(testrayCaseId);
	}

	/**
	 * Returns a range of all the testray cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray cases
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(int start, int end) {

		return _testrayCaseLocalService.getTestrayCases(start, end);
	}

	/**
	 * Returns the number of testray cases.
	 *
	 * @return the number of testray cases
	 */
	@Override
	public int getTestrayCasesCount() {
		return _testrayCaseLocalService.getTestrayCasesCount();
	}

	/**
	 * Returns the testrayComponentIds of the testray components associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayComponentIds of testray components associated with the testray case
	 */
	@Override
	public long[] getTestrayComponentPrimaryKeys(long testrayCaseId) {
		return _testrayCaseLocalService.getTestrayComponentPrimaryKeys(
			testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(long testrayComponentId) {

		return _testrayCaseLocalService.getTestrayComponentTestrayCases(
			testrayComponentId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(
			long testrayComponentId, int start, int end) {

		return _testrayCaseLocalService.getTestrayComponentTestrayCases(
			testrayComponentId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(
			long testrayComponentId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return _testrayCaseLocalService.getTestrayComponentTestrayCases(
			testrayComponentId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayComponentTestrayCasesCount(long testrayComponentId) {
		return _testrayCaseLocalService.getTestrayComponentTestrayCasesCount(
			testrayComponentId);
	}

	/**
	 * Returns the testrayRequirementIds of the testray requirements associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayRequirementIds of testray requirements associated with the testray case
	 */
	@Override
	public long[] getTestrayRequirementPrimaryKeys(long testrayCaseId) {
		return _testrayCaseLocalService.getTestrayRequirementPrimaryKeys(
			testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(long testrayRequirementId) {

		return _testrayCaseLocalService.getTestrayRequirementTestrayCases(
			testrayRequirementId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(
			long testrayRequirementId, int start, int end) {

		return _testrayCaseLocalService.getTestrayRequirementTestrayCases(
			testrayRequirementId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(
			long testrayRequirementId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return _testrayCaseLocalService.getTestrayRequirementTestrayCases(
			testrayRequirementId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayRequirementTestrayCasesCount(
		long testrayRequirementId) {

		return _testrayCaseLocalService.getTestrayRequirementTestrayCasesCount(
			testrayRequirementId);
	}

	/**
	 * Returns the testraySuiteIds of the testray suites associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testraySuiteIds of testray suites associated with the testray case
	 */
	@Override
	public long[] getTestraySuitePrimaryKeys(long testrayCaseId) {
		return _testrayCaseLocalService.getTestraySuitePrimaryKeys(
			testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(long testraySuiteId) {

		return _testrayCaseLocalService.getTestraySuiteTestrayCases(
			testraySuiteId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(long testraySuiteId, int start, int end) {

		return _testrayCaseLocalService.getTestraySuiteTestrayCases(
			testraySuiteId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(
			long testraySuiteId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return _testrayCaseLocalService.getTestraySuiteTestrayCases(
			testraySuiteId, start, end, orderByComparator);
	}

	@Override
	public int getTestraySuiteTestrayCasesCount(long testraySuiteId) {
		return _testrayCaseLocalService.getTestraySuiteTestrayCasesCount(
			testraySuiteId);
	}

	@Override
	public boolean hasTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		return _testrayCaseLocalService.hasTestrayBuildTestrayCase(
			testrayBuildId, testrayCaseId);
	}

	@Override
	public boolean hasTestrayBuildTestrayCases(long testrayBuildId) {
		return _testrayCaseLocalService.hasTestrayBuildTestrayCases(
			testrayBuildId);
	}

	@Override
	public boolean hasTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		return _testrayCaseLocalService.hasTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	@Override
	public boolean hasTestrayComponentTestrayCases(long testrayComponentId) {
		return _testrayCaseLocalService.hasTestrayComponentTestrayCases(
			testrayComponentId);
	}

	@Override
	public boolean hasTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		return _testrayCaseLocalService.hasTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	@Override
	public boolean hasTestrayRequirementTestrayCases(
		long testrayRequirementId) {

		return _testrayCaseLocalService.hasTestrayRequirementTestrayCases(
			testrayRequirementId);
	}

	@Override
	public boolean hasTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		return _testrayCaseLocalService.hasTestraySuiteTestrayCase(
			testraySuiteId, testrayCaseId);
	}

	@Override
	public boolean hasTestraySuiteTestrayCases(long testraySuiteId) {
		return _testrayCaseLocalService.hasTestraySuiteTestrayCases(
			testraySuiteId);
	}

	@Override
	public void setTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		_testrayCaseLocalService.setTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	@Override
	public void setTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		_testrayCaseLocalService.setTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	@Override
	public void setTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		_testrayCaseLocalService.setTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	@Override
	public void setTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		_testrayCaseLocalService.setTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
	}

	/**
	 * Updates the testray case in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCase updateTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return _testrayCaseLocalService.updateTestrayCase(testrayCase);
	}

	@Override
	public TestrayCaseLocalService getWrappedService() {
		return _testrayCaseLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayCaseLocalService testrayCaseLocalService) {

		_testrayCaseLocalService = testrayCaseLocalService;
	}

	private TestrayCaseLocalService _testrayCaseLocalService;

}