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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopUserNotificationRecordException;
import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop user notification record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecordUtil
 * @generated
 */
@ProviderType
public interface LoopUserNotificationRecordPersistence
	extends BasePersistence<LoopUserNotificationRecord> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopUserNotificationRecordUtil} to access the loop user notification record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopUserNotificationRecord> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the loop user notification record in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationRecord the loop user notification record
	 */
	public void cacheResult(
		LoopUserNotificationRecord loopUserNotificationRecord);

	/**
	 * Caches the loop user notification records in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationRecords the loop user notification records
	 */
	public void cacheResult(
		java.util.List<LoopUserNotificationRecord> loopUserNotificationRecords);

	/**
	 * Creates a new loop user notification record with the primary key. Does not add the loop user notification record to the database.
	 *
	 * @param loopUserNotificationRecordId the primary key for the new loop user notification record
	 * @return the new loop user notification record
	 */
	public LoopUserNotificationRecord create(long loopUserNotificationRecordId);

	/**
	 * Removes the loop user notification record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record that was removed
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	public LoopUserNotificationRecord remove(long loopUserNotificationRecordId)
		throws NoSuchLoopUserNotificationRecordException;

	public LoopUserNotificationRecord updateImpl(
		LoopUserNotificationRecord loopUserNotificationRecord);

	/**
	 * Returns the loop user notification record with the primary key or throws a <code>NoSuchLoopUserNotificationRecordException</code> if it could not be found.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record
	 * @throws NoSuchLoopUserNotificationRecordException if a loop user notification record with the primary key could not be found
	 */
	public LoopUserNotificationRecord findByPrimaryKey(
			long loopUserNotificationRecordId)
		throws NoSuchLoopUserNotificationRecordException;

	/**
	 * Returns the loop user notification record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopUserNotificationRecordId the primary key of the loop user notification record
	 * @return the loop user notification record, or <code>null</code> if a loop user notification record with the primary key could not be found
	 */
	public LoopUserNotificationRecord fetchByPrimaryKey(
		long loopUserNotificationRecordId);

	/**
	 * Returns all the loop user notification records.
	 *
	 * @return the loop user notification records
	 */
	public java.util.List<LoopUserNotificationRecord> findAll();

	/**
	 * Returns a range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @return the range of loop user notification records
	 */
	public java.util.List<LoopUserNotificationRecord> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification records
	 */
	public java.util.List<LoopUserNotificationRecord> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<LoopUserNotificationRecord> orderByComparator);

	/**
	 * Returns an ordered range of all the loop user notification records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationRecordModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification records
	 * @param end the upper bound of the range of loop user notification records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop user notification records
	 */
	public java.util.List<LoopUserNotificationRecord> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<LoopUserNotificationRecord> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop user notification records from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop user notification records.
	 *
	 * @return the number of loop user notification records
	 */
	public int countAll();

}