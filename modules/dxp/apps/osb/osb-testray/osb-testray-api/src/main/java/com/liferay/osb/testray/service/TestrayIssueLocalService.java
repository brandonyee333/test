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

import com.liferay.osb.testray.model.TestrayIssue;

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
 * Provides the local service interface for TestrayIssue. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestrayIssueLocalServiceUtil
 * @see com.liferay.osb.testray.service.base.TestrayIssueLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayIssueLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TestrayIssueLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayIssueLocalServiceUtil} to access the testray issue local service. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayIssueLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseResultTestrayIssue(long testrayCaseResultId,
		long testrayIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseResultTestrayIssues(long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySubtaskTestrayIssue(long testraySubtaskId,
		long testrayIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySubtaskTestrayIssues(long testraySubtaskId);

	/**
	* Adds the testray issue to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayIssue the testray issue
	* @return the testray issue that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayIssue addTestrayIssue(TestrayIssue testrayIssue);

	/**
	* Creates a new testray issue with the primary key. Does not add the testray issue to the database.
	*
	* @param testrayIssueId the primary key for the new testray issue
	* @return the new testray issue
	*/
	public TestrayIssue createTestrayIssue(long testrayIssueId);

	/**
	* Deletes the testray issue from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayIssue the testray issue
	* @return the testray issue that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayIssue deleteTestrayIssue(TestrayIssue testrayIssue);

	/**
	* Deletes the testray issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayIssueId the primary key of the testray issue
	* @return the testray issue that was removed
	* @throws PortalException if a testray issue with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayIssue deleteTestrayIssue(long testrayIssueId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayIssue fetchTestrayIssue(long testrayIssueId);

	/**
	* Returns the testray issue with the primary key.
	*
	* @param testrayIssueId the primary key of the testray issue
	* @return the testray issue
	* @throws PortalException if a testray issue with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayIssue getTestrayIssue(long testrayIssueId)
		throws PortalException;

	/**
	* Updates the testray issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayIssue the testray issue
	* @return the testray issue that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayIssue updateTestrayIssue(TestrayIssue testrayIssue);

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
	public int getTestrayCaseResultTestrayIssuesCount(long testrayCaseResultId);

	/**
	* Returns the number of testray issues.
	*
	* @return the number of testray issues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayIssuesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestraySubtaskTestrayIssuesCount(long testraySubtaskId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<TestrayIssue> getTestrayCaseResultTestrayIssues(
		long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestrayIssue> orderByComparator);

	/**
	* Returns a range of all the testray issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray issues
	* @param end the upper bound of the range of testray issues (not inclusive)
	* @return the range of testray issues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestrayIssues(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestraySubtaskTestrayIssues(
		long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestraySubtaskTestrayIssues(
		long testraySubtaskId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayIssue> getTestraySubtaskTestrayIssues(
		long testraySubtaskId, int start, int end,
		OrderByComparator<TestrayIssue> orderByComparator);

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
	* Returns the testrayCaseResultIds of the testray case results associated with the testray issue.
	*
	* @param testrayIssueId the testrayIssueId of the testray issue
	* @return long[] the testrayCaseResultIds of testray case results associated with the testray issue
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayCaseResultPrimaryKeys(long testrayIssueId);

	/**
	* Returns the testraySubtaskIds of the testray subtasks associated with the testray issue.
	*
	* @param testrayIssueId the testrayIssueId of the testray issue
	* @return long[] the testraySubtaskIds of testray subtasks associated with the testray issue
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestraySubtaskPrimaryKeys(long testrayIssueId);

	public void addTestrayCaseResultTestrayIssue(long testrayCaseResultId,
		TestrayIssue testrayIssue);

	public void addTestrayCaseResultTestrayIssue(long testrayCaseResultId,
		long testrayIssueId);

	public void addTestrayCaseResultTestrayIssues(long testrayCaseResultId,
		List<TestrayIssue> testrayIssues);

	public void addTestrayCaseResultTestrayIssues(long testrayCaseResultId,
		long[] testrayIssueIds);

	public void addTestraySubtaskTestrayIssue(long testraySubtaskId,
		TestrayIssue testrayIssue);

	public void addTestraySubtaskTestrayIssue(long testraySubtaskId,
		long testrayIssueId);

	public void addTestraySubtaskTestrayIssues(long testraySubtaskId,
		List<TestrayIssue> testrayIssues);

	public void addTestraySubtaskTestrayIssues(long testraySubtaskId,
		long[] testrayIssueIds);

	public void clearTestrayCaseResultTestrayIssues(long testrayCaseResultId);

	public void clearTestraySubtaskTestrayIssues(long testraySubtaskId);

	public void deleteTestrayCaseResultTestrayIssue(long testrayCaseResultId,
		TestrayIssue testrayIssue);

	public void deleteTestrayCaseResultTestrayIssue(long testrayCaseResultId,
		long testrayIssueId);

	public void deleteTestrayCaseResultTestrayIssues(long testrayCaseResultId,
		List<TestrayIssue> testrayIssues);

	public void deleteTestrayCaseResultTestrayIssues(long testrayCaseResultId,
		long[] testrayIssueIds);

	public void deleteTestraySubtaskTestrayIssue(long testraySubtaskId,
		TestrayIssue testrayIssue);

	public void deleteTestraySubtaskTestrayIssue(long testraySubtaskId,
		long testrayIssueId);

	public void deleteTestraySubtaskTestrayIssues(long testraySubtaskId,
		List<TestrayIssue> testrayIssues);

	public void deleteTestraySubtaskTestrayIssues(long testraySubtaskId,
		long[] testrayIssueIds);

	public void setTestrayCaseResultTestrayIssues(long testrayCaseResultId,
		long[] testrayIssueIds);

	public void setTestraySubtaskTestrayIssues(long testraySubtaskId,
		long[] testrayIssueIds);
}