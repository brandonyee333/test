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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchCorpProjectMessageException;
import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp project message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.CorpProjectMessagePersistenceImpl
 * @see CorpProjectMessageUtil
 * @generated
 */
@ProviderType
public interface CorpProjectMessagePersistence extends BasePersistence<CorpProjectMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpProjectMessageUtil} to access the corp project message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the corp project messages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns an ordered range of all the corp project messages where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the first corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the last corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the last corp project message in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where uuid = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage[] findByUuid_PrevAndNext(
		long corpProjectMessageId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Removes all the corp project messages where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of corp project messages where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching corp project messages
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId);

	/**
	* Returns a range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end);

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByCorpProjectId_First(long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByCorpProjectId_First(long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByCorpProjectId_Last(long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByCorpProjectId_Last(long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Removes all the corp project messages where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	*/
	public void removeByCorpProjectId(long corpProjectId);

	/**
	* Returns the number of corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching corp project messages
	*/
	public int countByCorpProjectId(long corpProjectId);

	/**
	* Returns all the corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByType(int type);

	/**
	* Returns a range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByType(int type, int start,
		int end);

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByType(int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp project messages
	*/
	public java.util.List<CorpProjectMessage> findByType(int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException;

	/**
	* Removes all the corp project messages where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeByType(int type);

	/**
	* Returns the number of corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the number of matching corp project messages
	*/
	public int countByType(int type);

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message
	* @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	*/
	public CorpProjectMessage findByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type);

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	*/
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type,
		boolean retrieveFromCache);

	/**
	* Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the corp project message that was removed
	*/
	public CorpProjectMessage removeByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the number of matching corp project messages
	*/
	public int countByC_T(long corpProjectId, int type);

	/**
	* Caches the corp project message in the entity cache if it is enabled.
	*
	* @param corpProjectMessage the corp project message
	*/
	public void cacheResult(CorpProjectMessage corpProjectMessage);

	/**
	* Caches the corp project messages in the entity cache if it is enabled.
	*
	* @param corpProjectMessages the corp project messages
	*/
	public void cacheResult(
		java.util.List<CorpProjectMessage> corpProjectMessages);

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	public CorpProjectMessage create(long corpProjectMessageId);

	/**
	* Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage remove(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException;

	public CorpProjectMessage updateImpl(CorpProjectMessage corpProjectMessage);

	/**
	* Returns the corp project message with the primary key or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage findByPrimaryKey(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException;

	/**
	* Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	*/
	public CorpProjectMessage fetchByPrimaryKey(long corpProjectMessageId);

	@Override
	public java.util.Map<java.io.Serializable, CorpProjectMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the corp project messages.
	*
	* @return the corp project messages
	*/
	public java.util.List<CorpProjectMessage> findAll();

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	*/
	public java.util.List<CorpProjectMessage> findAll(int start, int end);

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp project messages
	*/
	public java.util.List<CorpProjectMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator);

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of corp project messages
	*/
	public java.util.List<CorpProjectMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the corp project messages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}