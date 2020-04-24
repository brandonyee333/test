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

import com.liferay.osb.testray.model.TestraySubtask;
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
 * Provides the local service interface for TestraySubtask. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestraySubtaskLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TestraySubtaskLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestraySubtaskLocalServiceUtil} to access the testray subtask local service. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestraySubtaskLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId);

	public void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, TestraySubtask testraySubtask);

	public void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, List<TestraySubtask> testraySubtasks);

	public void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds);

	public void addTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId);

	public void addTestrayIssueTestraySubtask(
		long testrayIssueId, TestraySubtask testraySubtask);

	public void addTestrayIssueTestraySubtasks(
		long testrayIssueId, List<TestraySubtask> testraySubtasks);

	public void addTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds);

	/**
	 * Adds the testray subtask to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestraySubtask addTestraySubtask(TestraySubtask testraySubtask);

	public void clearTestrayCaseResultTestraySubtasks(long testrayCaseResultId);

	public void clearTestrayIssueTestraySubtasks(long testrayIssueId);

	/**
	 * Creates a new testray subtask with the primary key. Does not add the testray subtask to the database.
	 *
	 * @param testraySubtaskId the primary key for the new testray subtask
	 * @return the new testray subtask
	 */
	@Transactional(enabled = false)
	public TestraySubtask createTestraySubtask(long testraySubtaskId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId);

	public void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, TestraySubtask testraySubtask);

	public void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, List<TestraySubtask> testraySubtasks);

	public void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds);

	public void deleteTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId);

	public void deleteTestrayIssueTestraySubtask(
		long testrayIssueId, TestraySubtask testraySubtask);

	public void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId, List<TestraySubtask> testraySubtasks);

	public void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds);

	/**
	 * Deletes the testray subtask with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask that was removed
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestraySubtask deleteTestraySubtask(long testraySubtaskId)
		throws PortalException;

	/**
	 * Deletes the testray subtask from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestraySubtask deleteTestraySubtask(TestraySubtask testraySubtask);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

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
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestraySubtask fetchTestraySubtask(long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the testrayCaseResultIds of the testray case results associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayCaseResultIds of testray case results associated with the testray subtask
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayCaseResultPrimaryKeys(long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, int start, int end,
		OrderByComparator<TestraySubtask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCaseResultTestraySubtasksCount(
		long testrayCaseResultId);

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayIssueIds of testray issues associated with the testray subtask
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayIssuePrimaryKeys(long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayIssueTestraySubtasks(
		long testrayIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayIssueTestraySubtasks(
		long testrayIssueId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestrayIssueTestraySubtasks(
		long testrayIssueId, int start, int end,
		OrderByComparator<TestraySubtask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayIssueTestraySubtasksCount(long testrayIssueId);

	/**
	 * Returns the testray subtask with the primary key.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestraySubtask getTestraySubtask(long testraySubtaskId)
		throws PortalException;

	/**
	 * Returns a range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray subtasks
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySubtask> getTestraySubtasks(int start, int end);

	/**
	 * Returns the number of testray subtasks.
	 *
	 * @return the number of testray subtasks
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestraySubtasksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayIssueTestraySubtasks(long testrayIssueId);

	public void setTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds);

	public void setTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds);

	/**
	 * Updates the testray subtask in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestraySubtask updateTestraySubtask(TestraySubtask testraySubtask);

}