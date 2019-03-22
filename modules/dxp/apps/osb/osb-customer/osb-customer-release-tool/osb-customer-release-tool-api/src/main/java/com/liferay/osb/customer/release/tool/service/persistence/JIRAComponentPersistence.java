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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.exception.NoSuchJIRAComponentException;
import com.liferay.osb.customer.release.tool.model.JIRAComponent;
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
	 * Returns the jira component where remoteId = &#63; or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public JIRAComponent findByRemoteId(long remoteId)
		throws NoSuchJIRAComponentException;

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public JIRAComponent fetchByRemoteId(long remoteId);

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public JIRAComponent fetchByRemoteId(
		long remoteId, boolean retrieveFromCache);

	/**
	 * Removes the jira component where remoteId = &#63; from the database.
	 *
	 * @param remoteId the remote ID
	 * @return the jira component that was removed
	 */
	public JIRAComponent removeByRemoteId(long remoteId)
		throws NoSuchJIRAComponentException;

	/**
	 * Returns the number of jira components where remoteId = &#63;.
	 *
	 * @param remoteId the remote ID
	 * @return the number of matching jira components
	 */
	public int countByRemoteId(long remoteId);

	/**
	 * Returns all the jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the matching jira components
	 */
	public java.util.List<JIRAComponent> findByVisible(boolean visible);

	/**
	 * Returns a range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of matching jira components
	 */
	public java.util.List<JIRAComponent> findByVisible(
		boolean visible, int start, int end);

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jira components
	 */
	public java.util.List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jira components
	 */
	public java.util.List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public JIRAComponent findByVisible_First(
			boolean visible,
			com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
				orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public JIRAComponent fetchByVisible_First(
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator);

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	public JIRAComponent findByVisible_Last(
			boolean visible,
			com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
				orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	public JIRAComponent fetchByVisible_Last(
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator);

	/**
	 * Returns the jira components before and after the current jira component in the ordered set where visible = &#63;.
	 *
	 * @param jiraComponentId the primary key of the current jira component
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	public JIRAComponent[] findByVisible_PrevAndNext(
			long jiraComponentId, boolean visible,
			com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
				orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	 * Removes all the jira components where visible = &#63; from the database.
	 *
	 * @param visible the visible
	 */
	public void removeByVisible(boolean visible);

	/**
	 * Returns the number of jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the number of matching jira components
	 */
	public int countByVisible(boolean visible);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jira components
	 */
	public java.util.List<JIRAComponent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent>
			orderByComparator,
		boolean retrieveFromCache);

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

}