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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectVersionException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the jira project version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.release.notes.jira.service.persistence.impl.JIRAProjectVersionPersistenceImpl
 * @see JIRAProjectVersionUtil
 * @generated
 */
@ProviderType
public interface JIRAProjectVersionPersistence extends BasePersistence<JIRAProjectVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAProjectVersionUtil} to access the jira project version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the jira project version in the entity cache if it is enabled.
	*
	* @param jiraProjectVersion the jira project version
	*/
	public void cacheResult(JIRAProjectVersion jiraProjectVersion);

	/**
	* Caches the jira project versions in the entity cache if it is enabled.
	*
	* @param jiraProjectVersions the jira project versions
	*/
	public void cacheResult(
		java.util.List<JIRAProjectVersion> jiraProjectVersions);

	/**
	* Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	*
	* @param jiraProjectVersionId the primary key for the new jira project version
	* @return the new jira project version
	*/
	public JIRAProjectVersion create(long jiraProjectVersionId);

	/**
	* Removes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version that was removed
	* @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	*/
	public JIRAProjectVersion remove(long jiraProjectVersionId)
		throws NoSuchJIRAProjectVersionException;

	public JIRAProjectVersion updateImpl(JIRAProjectVersion jiraProjectVersion);

	/**
	* Returns the jira project version with the primary key or throws a {@link NoSuchJIRAProjectVersionException} if it could not be found.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version
	* @throws NoSuchJIRAProjectVersionException if a jira project version with the primary key could not be found
	*/
	public JIRAProjectVersion findByPrimaryKey(long jiraProjectVersionId)
		throws NoSuchJIRAProjectVersionException;

	/**
	* Returns the jira project version with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version, or <code>null</code> if a jira project version with the primary key could not be found
	*/
	public JIRAProjectVersion fetchByPrimaryKey(long jiraProjectVersionId);

	@Override
	public java.util.Map<java.io.Serializable, JIRAProjectVersion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the jira project versions.
	*
	* @return the jira project versions
	*/
	public java.util.List<JIRAProjectVersion> findAll();

	/**
	* Returns a range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @return the range of jira project versions
	*/
	public java.util.List<JIRAProjectVersion> findAll(int start, int end);

	/**
	* Returns an ordered range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of jira project versions
	*/
	public java.util.List<JIRAProjectVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProjectVersion> orderByComparator);

	/**
	* Returns an ordered range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of jira project versions
	*/
	public java.util.List<JIRAProjectVersion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProjectVersion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the jira project versions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of jira project versions.
	*
	* @return the number of jira project versions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}