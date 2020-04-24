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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopStreamException;
import com.liferay.osb.loop.model.LoopStream;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop stream service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopStreamPersistenceImpl
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
	* Returns the loop stream with the primary key or throws a {@link NoSuchLoopStreamException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, LoopStream> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop streams
	* @param end the upper bound of the range of loop streams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop streams
	*/
	public java.util.List<LoopStream> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStream> orderByComparator);

	/**
	* Returns an ordered range of all the loop streams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop streams
	* @param end the upper bound of the range of loop streams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop streams
	*/
	public java.util.List<LoopStream> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopStream> orderByComparator,
		boolean retrieveFromCache);

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