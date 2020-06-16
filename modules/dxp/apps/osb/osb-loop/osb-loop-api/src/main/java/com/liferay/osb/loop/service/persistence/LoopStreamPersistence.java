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

import com.liferay.osb.loop.exception.NoSuchLoopStreamException;
import com.liferay.osb.loop.model.LoopStream;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the loop stream service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamUtil
 * @generated
 */
@ProviderType
public interface LoopStreamPersistence extends BasePersistence<LoopStream> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopStreamUtil} to access the loop stream persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LoopStream> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the loop stream in the entity cache if it is enabled.
	 *
	 * @param loopStream the loop stream
	 */
	public void cacheResult(LoopStream loopStream);

	/**
	 * Caches the loop streams in the entity cache if it is enabled.
	 *
	 * @param loopStreams the loop streams
	 */
	public void cacheResult(java.util.List<LoopStream> loopStreams);

	/**
	 * Creates a new loop stream with the primary key. Does not add the loop stream to the database.
	 *
	 * @param loopStreamId the primary key for the new loop stream
	 * @return the new loop stream
	 */
	public LoopStream create(long loopStreamId);

	/**
	 * Removes the loop stream with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream that was removed
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	public LoopStream remove(long loopStreamId)
		throws NoSuchLoopStreamException;

	public LoopStream updateImpl(LoopStream loopStream);

	/**
	 * Returns the loop stream with the primary key or throws a <code>NoSuchLoopStreamException</code> if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream
	 * @throws NoSuchLoopStreamException if a loop stream with the primary key could not be found
	 */
	public LoopStream findByPrimaryKey(long loopStreamId)
		throws NoSuchLoopStreamException;

	/**
	 * Returns the loop stream with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStreamId the primary key of the loop stream
	 * @return the loop stream, or <code>null</code> if a loop stream with the primary key could not be found
	 */
	public LoopStream fetchByPrimaryKey(long loopStreamId);

	/**
	 * Returns all the loop streams.
	 *
	 * @return the loop streams
	 */
	public java.util.List<LoopStream> findAll();

	/**
	 * Returns a range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @return the range of loop streams
	 */
	public java.util.List<LoopStream> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop streams
	 */
	public java.util.List<LoopStream> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStream>
			orderByComparator);

	/**
	 * Returns an ordered range of all the loop streams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopStreamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop streams
	 * @param end the upper bound of the range of loop streams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop streams
	 */
	public java.util.List<LoopStream> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStream>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the loop streams from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of loop streams.
	 *
	 * @return the number of loop streams
	 */
	public int countAll();

}