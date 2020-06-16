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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayArchiveException;
import com.liferay.osb.testray.model.TestrayArchive;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the testray archive service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.testray.service.persistence.impl.TestrayArchivePersistenceImpl
 * @see TestrayArchiveUtil
 * @generated
 */
@ProviderType
public interface TestrayArchivePersistence extends BasePersistence<TestrayArchive> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayArchiveUtil} to access the testray archive persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the testray archive in the entity cache if it is enabled.
	*
	* @param testrayArchive the testray archive
	*/
	public void cacheResult(TestrayArchive testrayArchive);

	/**
	* Caches the testray archives in the entity cache if it is enabled.
	*
	* @param testrayArchives the testray archives
	*/
	public void cacheResult(java.util.List<TestrayArchive> testrayArchives);

	/**
	* Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	*
	* @param testrayArchiveId the primary key for the new testray archive
	* @return the new testray archive
	*/
	public TestrayArchive create(long testrayArchiveId);

	/**
	* Removes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayArchiveId the primary key of the testray archive
	* @return the testray archive that was removed
	* @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	*/
	public TestrayArchive remove(long testrayArchiveId)
		throws NoSuchTestrayArchiveException;

	public TestrayArchive updateImpl(TestrayArchive testrayArchive);

	/**
	* Returns the testray archive with the primary key or throws a {@link NoSuchTestrayArchiveException} if it could not be found.
	*
	* @param testrayArchiveId the primary key of the testray archive
	* @return the testray archive
	* @throws NoSuchTestrayArchiveException if a testray archive with the primary key could not be found
	*/
	public TestrayArchive findByPrimaryKey(long testrayArchiveId)
		throws NoSuchTestrayArchiveException;

	/**
	* Returns the testray archive with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param testrayArchiveId the primary key of the testray archive
	* @return the testray archive, or <code>null</code> if a testray archive with the primary key could not be found
	*/
	public TestrayArchive fetchByPrimaryKey(long testrayArchiveId);

	@Override
	public java.util.Map<java.io.Serializable, TestrayArchive> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the testray archives.
	*
	* @return the testray archives
	*/
	public java.util.List<TestrayArchive> findAll();

	/**
	* Returns a range of all the testray archives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray archives
	* @param end the upper bound of the range of testray archives (not inclusive)
	* @return the range of testray archives
	*/
	public java.util.List<TestrayArchive> findAll(int start, int end);

	/**
	* Returns an ordered range of all the testray archives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray archives
	* @param end the upper bound of the range of testray archives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of testray archives
	*/
	public java.util.List<TestrayArchive> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayArchive> orderByComparator);

	/**
	* Returns an ordered range of all the testray archives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TestrayArchiveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray archives
	* @param end the upper bound of the range of testray archives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of testray archives
	*/
	public java.util.List<TestrayArchive> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayArchive> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the testray archives from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of testray archives.
	*
	* @return the number of testray archives
	*/
	public int countAll();
}