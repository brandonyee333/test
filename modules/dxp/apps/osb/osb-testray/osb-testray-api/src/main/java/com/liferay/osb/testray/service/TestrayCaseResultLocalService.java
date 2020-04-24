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

import com.liferay.osb.testray.model.TestrayCaseResult;
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
 * Provides the local service interface for TestrayCaseResult. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TestrayCaseResultLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayCaseResultLocalServiceUtil} to access the testray case result local service. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the testray case result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestrayCaseResult addTestrayCaseResult(
		TestrayCaseResult testrayCaseResult);

	public void addTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId);

	public void addTestrayIssueTestrayCaseResult(
		long testrayIssueId, TestrayCaseResult testrayCaseResult);

	public void addTestrayIssueTestrayCaseResults(
		long testrayIssueId, List<TestrayCaseResult> testrayCaseResults);

	public void addTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds);

	public void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId);

	public void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, TestrayCaseResult testrayCaseResult);

	public void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, List<TestrayCaseResult> testrayCaseResults);

	public void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds);

	public void clearTestrayIssueTestrayCaseResults(long testrayIssueId);

	public void clearTestraySubtaskTestrayCaseResults(long testraySubtaskId);

	/**
	 * Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	 *
	 * @param testrayCaseResultId the primary key for the new testray case result
	 * @return the new testray case result
	 */
	@Transactional(enabled = false)
	public TestrayCaseResult createTestrayCaseResult(long testrayCaseResultId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result that was removed
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestrayCaseResult deleteTestrayCaseResult(long testrayCaseResultId)
		throws PortalException;

	/**
	 * Deletes the testray case result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestrayCaseResult deleteTestrayCaseResult(
		TestrayCaseResult testrayCaseResult);

	public void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId);

	public void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId, TestrayCaseResult testrayCaseResult);

	public void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId, List<TestrayCaseResult> testrayCaseResults);

	public void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds);

	public void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId);

	public void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, TestrayCaseResult testrayCaseResult);

	public void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, List<TestrayCaseResult> testrayCaseResults);

	public void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public TestrayCaseResult fetchTestrayCaseResult(long testrayCaseResultId);

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
	 * Returns the testray case result with the primary key.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayCaseResult getTestrayCaseResult(long testrayCaseResultId)
		throws PortalException;

	/**
	 * Returns a range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray case results
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestrayCaseResults(int start, int end);

	/**
	 * Returns the number of testray case results.
	 *
	 * @return the number of testray case results
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCaseResultsCount();

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testrayIssueIds of testray issues associated with the testray case result
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayIssuePrimaryKeys(long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayIssueTestrayCaseResultsCount(long testrayIssueId);

	/**
	 * Returns the testraySubtaskIds of the testray subtasks associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testraySubtaskIds of testray subtasks associated with the testray case result
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestraySubtaskPrimaryKeys(long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, int start, int end,
		OrderByComparator<TestrayCaseResult> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestraySubtaskTestrayCaseResultsCount(long testraySubtaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayIssueTestrayCaseResults(long testrayIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestraySubtaskTestrayCaseResults(long testraySubtaskId);

	public void setTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds);

	public void setTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds);

	/**
	 * Updates the testray case result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestrayCaseResult updateTestrayCaseResult(
		TestrayCaseResult testrayCaseResult);

}