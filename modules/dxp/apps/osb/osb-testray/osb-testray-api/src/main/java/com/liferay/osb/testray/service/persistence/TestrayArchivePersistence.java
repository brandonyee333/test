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

package com.liferay.osb.testray.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.exception.NoSuchTestrayArchiveException;
import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the testray archive service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayArchiveUtil
 * @generated
 */
@ProviderType
public interface TestrayArchivePersistence
	extends BasePersistence<TestrayArchive> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayArchiveUtil} to access the testray archive persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, TestrayArchive> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Returns the testray archive with the primary key or throws a <code>NoSuchTestrayArchiveException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of testray archives
	 */
	public java.util.List<TestrayArchive> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayArchive>
			orderByComparator);

	/**
	 * Returns an ordered range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestrayArchiveModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of testray archives
	 */
	public java.util.List<TestrayArchive> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestrayArchive>
			orderByComparator,
		boolean useFinderCache);

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