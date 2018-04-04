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

package com.liferay.osb.community.akismet.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.akismet.exception.NoSuchAkismetException;
import com.liferay.osb.community.akismet.model.Akismet;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the akismet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see com.liferay.osb.community.akismet.service.persistence.impl.AkismetPersistenceImpl
 * @see AkismetUtil
 * @generated
 */
@ProviderType
public interface AkismetPersistence extends BasePersistence<Akismet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AkismetUtil} to access the akismet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the akismets where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching akismets
	*/
	public java.util.List<Akismet> findByLtModifiedDate(Date modifiedDate);

	/**
	* Returns a range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of matching akismets
	*/
	public java.util.List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching akismets
	*/
	public java.util.List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator);

	/**
	* Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching akismets
	*/
	public java.util.List<Akismet> findByLtModifiedDate(Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public Akismet findByLtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException;

	/**
	* Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public Akismet fetchByLtModifiedDate_First(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator);

	/**
	* Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public Akismet findByLtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException;

	/**
	* Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public Akismet fetchByLtModifiedDate_Last(Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator);

	/**
	* Returns the akismets before and after the current akismet in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param akismetId the primary key of the current akismet
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next akismet
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public Akismet[] findByLtModifiedDate_PrevAndNext(long akismetId,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException;

	/**
	* Removes all the akismets where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public void removeByLtModifiedDate(Date modifiedDate);

	/**
	* Returns the number of akismets where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching akismets
	*/
	public int countByLtModifiedDate(Date modifiedDate);

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchAkismetException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching akismet
	* @throws NoSuchAkismetException if a matching akismet could not be found
	*/
	public Akismet findByC_C(long classNameId, long classPK)
		throws NoSuchAkismetException;

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public Akismet fetchByC_C(long classNameId, long classPK);

	/**
	* Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	*/
	public Akismet fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the akismet where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the akismet that was removed
	*/
	public Akismet removeByC_C(long classNameId, long classPK)
		throws NoSuchAkismetException;

	/**
	* Returns the number of akismets where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching akismets
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Caches the akismet in the entity cache if it is enabled.
	*
	* @param akismet the akismet
	*/
	public void cacheResult(Akismet akismet);

	/**
	* Caches the akismets in the entity cache if it is enabled.
	*
	* @param akismets the akismets
	*/
	public void cacheResult(java.util.List<Akismet> akismets);

	/**
	* Creates a new akismet with the primary key. Does not add the akismet to the database.
	*
	* @param akismetId the primary key for the new akismet
	* @return the new akismet
	*/
	public Akismet create(long akismetId);

	/**
	* Removes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet that was removed
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public Akismet remove(long akismetId) throws NoSuchAkismetException;

	public Akismet updateImpl(Akismet akismet);

	/**
	* Returns the akismet with the primary key or throws a {@link NoSuchAkismetException} if it could not be found.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet
	* @throws NoSuchAkismetException if a akismet with the primary key could not be found
	*/
	public Akismet findByPrimaryKey(long akismetId)
		throws NoSuchAkismetException;

	/**
	* Returns the akismet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet, or <code>null</code> if a akismet with the primary key could not be found
	*/
	public Akismet fetchByPrimaryKey(long akismetId);

	@Override
	public java.util.Map<java.io.Serializable, Akismet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the akismets.
	*
	* @return the akismets
	*/
	public java.util.List<Akismet> findAll();

	/**
	* Returns a range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of akismets
	*/
	public java.util.List<Akismet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of akismets
	*/
	public java.util.List<Akismet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator);

	/**
	* Returns an ordered range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of akismets
	*/
	public java.util.List<Akismet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Akismet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the akismets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of akismets.
	*
	* @return the number of akismets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}