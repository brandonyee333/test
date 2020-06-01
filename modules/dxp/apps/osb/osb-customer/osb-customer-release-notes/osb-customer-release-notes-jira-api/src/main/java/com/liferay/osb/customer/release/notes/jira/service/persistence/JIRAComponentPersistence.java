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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAComponentException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the jira component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentUtil
 * @generated
 */
@ProviderType
public interface JIRAComponentPersistence
	extends BasePersistence<JIRAComponent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAComponentUtil} to access the jira component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, JIRAComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the jira component in the entity cache if it is enabled.
	 *
	 * @param jiraComponent the jira component
	 */
	public void cacheResult(JIRAComponent jiraComponent);

	/**
	 * Caches the jira components in the entity cache if it is enabled.
	 *
	 * @param jiraComponents the jira components
	 */
	public void cacheResult(java.util.List<JIRAComponent> jiraComponents);

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	public JIRAComponent create(long jiraComponentId);

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public JIRAComponent remove(long jiraComponentId)
		throws NoSuchJIRAComponentException;

	public JIRAComponent updateImpl(JIRAComponent jiraComponent);

	/**
	 * Returns the jira component with the primary key or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public JIRAComponent findByPrimaryKey(long jiraComponentId)
		throws NoSuchJIRAComponentException;

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	public JIRAComponent fetchByPrimaryKey(long jiraComponentId);

	/**
	 * Returns all the jira components.
	 *
	 * @return the jira components
	 */
	public java.util.List<JIRAComponent> findAll();

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	public java.util.List<JIRAComponent> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira components
	 */
	public java.util.List<JIRAComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira components
	 */
	public java.util.List<JIRAComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the jira components from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of jira components.
	 *
	 * @return the number of jira components
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}