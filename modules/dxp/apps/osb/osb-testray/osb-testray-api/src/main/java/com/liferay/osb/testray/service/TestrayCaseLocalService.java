/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayCase;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for TestrayCase. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseLocalServiceUtil
 * @see com.liferay.osb.testray.service.base.TestrayCaseLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayCaseLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TestrayCaseLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseLocalServiceUtil} to access the testray case local service. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayCaseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayBuildTestrayCase(long testrayBuildId,
		long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayBuildTestrayCases(long testrayBuildId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayComponentTestrayCase(long testrayComponentId,
		long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayComponentTestrayCases(long testrayComponentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayRequirementTestrayCase(long testrayRequirementId,
		long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayRequirementTestrayCases(long testrayRequirementId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySuiteTestrayCase(long testraySuiteId,
		long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySuiteTestrayCases(long testraySuiteId);

	/**
	* Adds the testray case to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCase the testray case
	* @return the testray case that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayCase addTestrayCase(TestrayCase testrayCase);

	/**
	* Creates a new testray case with the primary key. Does not add the testray case to the database.
	*
	* @param testrayCaseId the primary key for the new testray case
	* @return the new testray case
	*/
	public TestrayCase createTestrayCase(long testrayCaseId);

	/**
	* Deletes the testray case from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCase the testray case
	* @return the testray case that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayCase deleteTestrayCase(TestrayCase testrayCase);

	/**
	* Deletes the testray case with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseId the primary key of the testray case
	* @return the testray case that was removed
	* @throws PortalException if a testray case with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayCase deleteTestrayCase(long testrayCaseId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayCase fetchTestrayCase(long testrayCaseId);

	/**
	* Returns the testray case with the primary key.
	*
	* @param testrayCaseId the primary key of the testray case
	* @return the testray case
	* @throws PortalException if a testray case with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayCase getTestrayCase(long testrayCaseId)
		throws PortalException;

	/**
	* Updates the testray case in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayCase the testray case
	* @return the testray case that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayCase updateTestrayCase(TestrayCase testrayCase);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayBuildTestrayCasesCount(long testrayBuildId);

	/**
	* Returns the number of testray cases.
	*
	* @return the number of testray cases
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCasesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayComponentTestrayCasesCount(long testrayComponentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayRequirementTestrayCasesCount(long testrayRequirementId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestraySuiteTestrayCasesCount(long testraySuiteId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayBuildTestrayCases(long testrayBuildId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayBuildTestrayCases(long testrayBuildId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayBuildTestrayCases(long testrayBuildId,
		int start, int end, OrderByComparator<TestrayCase> orderByComparator);

	/**
	* Returns a range of all the testray cases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray cases
	* @param end the upper bound of the range of testray cases (not inclusive)
	* @return the range of testray cases
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayCases(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayComponentTestrayCases(
		long testrayComponentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayComponentTestrayCases(
		long testrayComponentId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayComponentTestrayCases(
		long testrayComponentId, int start, int end,
		OrderByComparator<TestrayCase> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayRequirementTestrayCases(
		long testrayRequirementId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayRequirementTestrayCases(
		long testrayRequirementId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestrayRequirementTestrayCases(
		long testrayRequirementId, int start, int end,
		OrderByComparator<TestrayCase> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestraySuiteTestrayCases(long testraySuiteId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestraySuiteTestrayCases(long testraySuiteId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCase> getTestraySuiteTestrayCases(long testraySuiteId,
		int start, int end, OrderByComparator<TestrayCase> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the testrayBuildIds of the testray builds associated with the testray case.
	*
	* @param testrayCaseId the testrayCaseId of the testray case
	* @return long[] the testrayBuildIds of testray builds associated with the testray case
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayBuildPrimaryKeys(long testrayCaseId);

	/**
	* Returns the testrayComponentIds of the testray components associated with the testray case.
	*
	* @param testrayCaseId the testrayCaseId of the testray case
	* @return long[] the testrayComponentIds of testray components associated with the testray case
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayComponentPrimaryKeys(long testrayCaseId);

	/**
	* Returns the testrayRequirementIds of the testray requirements associated with the testray case.
	*
	* @param testrayCaseId the testrayCaseId of the testray case
	* @return long[] the testrayRequirementIds of testray requirements associated with the testray case
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayRequirementPrimaryKeys(long testrayCaseId);

	/**
	* Returns the testraySuiteIds of the testray suites associated with the testray case.
	*
	* @param testrayCaseId the testrayCaseId of the testray case
	* @return long[] the testraySuiteIds of testray suites associated with the testray case
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestraySuitePrimaryKeys(long testrayCaseId);

	public void addTestrayBuildTestrayCase(long testrayBuildId,
		TestrayCase testrayCase);

	public void addTestrayBuildTestrayCase(long testrayBuildId,
		long testrayCaseId);

	public void addTestrayBuildTestrayCases(long testrayBuildId,
		List<TestrayCase> testrayCases);

	public void addTestrayBuildTestrayCases(long testrayBuildId,
		long[] testrayCaseIds);

	public void addTestrayComponentTestrayCase(long testrayComponentId,
		TestrayCase testrayCase);

	public void addTestrayComponentTestrayCase(long testrayComponentId,
		long testrayCaseId);

	public void addTestrayComponentTestrayCases(long testrayComponentId,
		List<TestrayCase> testrayCases);

	public void addTestrayComponentTestrayCases(long testrayComponentId,
		long[] testrayCaseIds);

	public void addTestrayRequirementTestrayCase(long testrayRequirementId,
		TestrayCase testrayCase);

	public void addTestrayRequirementTestrayCase(long testrayRequirementId,
		long testrayCaseId);

	public void addTestrayRequirementTestrayCases(long testrayRequirementId,
		List<TestrayCase> testrayCases);

	public void addTestrayRequirementTestrayCases(long testrayRequirementId,
		long[] testrayCaseIds);

	public void addTestraySuiteTestrayCase(long testraySuiteId,
		TestrayCase testrayCase);

	public void addTestraySuiteTestrayCase(long testraySuiteId,
		long testrayCaseId);

	public void addTestraySuiteTestrayCases(long testraySuiteId,
		List<TestrayCase> testrayCases);

	public void addTestraySuiteTestrayCases(long testraySuiteId,
		long[] testrayCaseIds);

	public void clearTestrayBuildTestrayCases(long testrayBuildId);

	public void clearTestrayComponentTestrayCases(long testrayComponentId);

	public void clearTestrayRequirementTestrayCases(long testrayRequirementId);

	public void clearTestraySuiteTestrayCases(long testraySuiteId);

	public void deleteTestrayBuildTestrayCase(long testrayBuildId,
		TestrayCase testrayCase);

	public void deleteTestrayBuildTestrayCase(long testrayBuildId,
		long testrayCaseId);

	public void deleteTestrayBuildTestrayCases(long testrayBuildId,
		List<TestrayCase> testrayCases);

	public void deleteTestrayBuildTestrayCases(long testrayBuildId,
		long[] testrayCaseIds);

	public void deleteTestrayComponentTestrayCase(long testrayComponentId,
		TestrayCase testrayCase);

	public void deleteTestrayComponentTestrayCase(long testrayComponentId,
		long testrayCaseId);

	public void deleteTestrayComponentTestrayCases(long testrayComponentId,
		List<TestrayCase> testrayCases);

	public void deleteTestrayComponentTestrayCases(long testrayComponentId,
		long[] testrayCaseIds);

	public void deleteTestrayRequirementTestrayCase(long testrayRequirementId,
		TestrayCase testrayCase);

	public void deleteTestrayRequirementTestrayCase(long testrayRequirementId,
		long testrayCaseId);

	public void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId, List<TestrayCase> testrayCases);

	public void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds);

	public void deleteTestraySuiteTestrayCase(long testraySuiteId,
		TestrayCase testrayCase);

	public void deleteTestraySuiteTestrayCase(long testraySuiteId,
		long testrayCaseId);

	public void deleteTestraySuiteTestrayCases(long testraySuiteId,
		List<TestrayCase> testrayCases);

	public void deleteTestraySuiteTestrayCases(long testraySuiteId,
		long[] testrayCaseIds);

	public void setTestrayBuildTestrayCases(long testrayBuildId,
		long[] testrayCaseIds);

	public void setTestrayComponentTestrayCases(long testrayComponentId,
		long[] testrayCaseIds);

	public void setTestrayRequirementTestrayCases(long testrayRequirementId,
		long[] testrayCaseIds);

	public void setTestraySuiteTestrayCases(long testraySuiteId,
		long[] testrayCaseIds);
}