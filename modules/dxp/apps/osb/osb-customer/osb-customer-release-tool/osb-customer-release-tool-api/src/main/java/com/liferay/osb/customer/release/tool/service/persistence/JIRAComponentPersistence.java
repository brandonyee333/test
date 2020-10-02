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

package com.liferay.osb.customer.release.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.exception.NoSuchJIRAComponentException;
import com.liferay.osb.customer.release.tool.model.JIRAComponent;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the jira component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.release.tool.service.persistence.impl.JIRAComponentPersistenceImpl
 * @see JIRAComponentUtil
 * @generated
 */
@ProviderType
public interface JIRAComponentPersistence extends BasePersistence<JIRAComponent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAComponentUtil} to access the jira component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the jira components where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @return the matching jira components
	*/
	public java.util.List<JIRAComponent> findByRemoteProject(
		java.lang.String remoteProject);

	/**
	* Returns a range of all the jira components where remoteProject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @return the range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRemoteProject(
		java.lang.String remoteProject, int start, int end);

	/**
	* Returns an ordered range of all the jira components where remoteProject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRemoteProject(
		java.lang.String remoteProject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns an ordered range of all the jira components where remoteProject = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRemoteProject(
		java.lang.String remoteProject, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first jira component in the ordered set where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jira component
	* @throws NoSuchJIRAComponentException if a matching jira component could not be found
	*/
	public JIRAComponent findByRemoteProject_First(
		java.lang.String remoteProject,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Returns the first jira component in the ordered set where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRemoteProject_First(
		java.lang.String remoteProject,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns the last jira component in the ordered set where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jira component
	* @throws NoSuchJIRAComponentException if a matching jira component could not be found
	*/
	public JIRAComponent findByRemoteProject_Last(
		java.lang.String remoteProject,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Returns the last jira component in the ordered set where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRemoteProject_Last(
		java.lang.String remoteProject,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns the jira components before and after the current jira component in the ordered set where remoteProject = &#63;.
	*
	* @param jiraComponentId the primary key of the current jira component
	* @param remoteProject the remote project
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next jira component
	* @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	*/
	public JIRAComponent[] findByRemoteProject_PrevAndNext(
		long jiraComponentId, java.lang.String remoteProject,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Removes all the jira components where remoteProject = &#63; from the database.
	*
	* @param remoteProject the remote project
	*/
	public void removeByRemoteProject(java.lang.String remoteProject);

	/**
	* Returns the number of jira components where remoteProject = &#63;.
	*
	* @param remoteProject the remote project
	* @return the number of matching jira components
	*/
	public int countByRemoteProject(java.lang.String remoteProject);

	/**
	* Returns the jira component where remoteId = &#63; and remoteProject = &#63; or throws a {@link NoSuchJIRAComponentException} if it could not be found.
	*
	* @param remoteId the remote ID
	* @param remoteProject the remote project
	* @return the matching jira component
	* @throws NoSuchJIRAComponentException if a matching jira component could not be found
	*/
	public JIRAComponent findByRI_RP(long remoteId,
		java.lang.String remoteProject) throws NoSuchJIRAComponentException;

	/**
	* Returns the jira component where remoteId = &#63; and remoteProject = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param remoteId the remote ID
	* @param remoteProject the remote project
	* @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRI_RP(long remoteId,
		java.lang.String remoteProject);

	/**
	* Returns the jira component where remoteId = &#63; and remoteProject = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param remoteId the remote ID
	* @param remoteProject the remote project
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRI_RP(long remoteId,
		java.lang.String remoteProject, boolean retrieveFromCache);

	/**
	* Removes the jira component where remoteId = &#63; and remoteProject = &#63; from the database.
	*
	* @param remoteId the remote ID
	* @param remoteProject the remote project
	* @return the jira component that was removed
	*/
	public JIRAComponent removeByRI_RP(long remoteId,
		java.lang.String remoteProject) throws NoSuchJIRAComponentException;

	/**
	* Returns the number of jira components where remoteId = &#63; and remoteProject = &#63;.
	*
	* @param remoteId the remote ID
	* @param remoteProject the remote project
	* @return the number of matching jira components
	*/
	public int countByRI_RP(long remoteId, java.lang.String remoteProject);

	/**
	* Returns all the jira components where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @return the matching jira components
	*/
	public java.util.List<JIRAComponent> findByRP_V(
		java.lang.String remoteProject, boolean visible);

	/**
	* Returns a range of all the jira components where remoteProject = &#63; and visible = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @return the range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRP_V(
		java.lang.String remoteProject, boolean visible, int start, int end);

	/**
	* Returns an ordered range of all the jira components where remoteProject = &#63; and visible = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRP_V(
		java.lang.String remoteProject, boolean visible, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns an ordered range of all the jira components where remoteProject = &#63; and visible = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching jira components
	*/
	public java.util.List<JIRAComponent> findByRP_V(
		java.lang.String remoteProject, boolean visible, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jira component
	* @throws NoSuchJIRAComponentException if a matching jira component could not be found
	*/
	public JIRAComponent findByRP_V_First(java.lang.String remoteProject,
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Returns the first jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRP_V_First(java.lang.String remoteProject,
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns the last jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jira component
	* @throws NoSuchJIRAComponentException if a matching jira component could not be found
	*/
	public JIRAComponent findByRP_V_Last(java.lang.String remoteProject,
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Returns the last jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	*/
	public JIRAComponent fetchByRP_V_Last(java.lang.String remoteProject,
		boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns the jira components before and after the current jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	*
	* @param jiraComponentId the primary key of the current jira component
	* @param remoteProject the remote project
	* @param visible the visible
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next jira component
	* @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	*/
	public JIRAComponent[] findByRP_V_PrevAndNext(long jiraComponentId,
		java.lang.String remoteProject, boolean visible,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException;

	/**
	* Removes all the jira components where remoteProject = &#63; and visible = &#63; from the database.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	*/
	public void removeByRP_V(java.lang.String remoteProject, boolean visible);

	/**
	* Returns the number of jira components where remoteProject = &#63; and visible = &#63;.
	*
	* @param remoteProject the remote project
	* @param visible the visible
	* @return the number of matching jira components
	*/
	public int countByRP_V(java.lang.String remoteProject, boolean visible);

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
	* Returns the jira component with the primary key or throws a {@link NoSuchJIRAComponentException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, JIRAComponent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of jira components
	*/
	public java.util.List<JIRAComponent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator);

	/**
	* Returns an ordered range of all the jira components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of jira components
	*/
	public java.util.List<JIRAComponent> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAComponent> orderByComparator,
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