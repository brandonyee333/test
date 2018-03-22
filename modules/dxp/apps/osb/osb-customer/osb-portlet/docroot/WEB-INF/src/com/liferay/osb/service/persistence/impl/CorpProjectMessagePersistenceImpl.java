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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchCorpProjectMessageException;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.impl.CorpProjectMessageImpl;
import com.liferay.osb.model.impl.CorpProjectMessageModelImpl;
import com.liferay.osb.service.persistence.CorpProjectMessagePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the corp project message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessagePersistence
 * @see com.liferay.osb.service.persistence.CorpProjectMessageUtil
 * @generated
 */
@ProviderType
public class CorpProjectMessagePersistenceImpl extends BasePersistenceImpl<CorpProjectMessage>
	implements CorpProjectMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpProjectMessageUtil} to access the corp project message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpProjectMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CorpProjectMessageModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the corp project messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching corp project messages
	 */
	@Override
	public List<CorpProjectMessage> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByUuid(String uuid, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<CorpProjectMessage> findByUuid(String uuid, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CorpProjectMessage> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProjectMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CorpProjectMessage corpProjectMessage : list) {
					if (!Objects.equals(uuid, corpProjectMessage.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first corp project message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByUuid_First(String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByUuid_First(uuid,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the first corp project message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByUuid_First(String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		List<CorpProjectMessage> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByUuid_Last(String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByUuid_Last(uuid,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the last corp project message in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByUuid_Last(String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CorpProjectMessage> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp project messages before and after the current corp project message in the ordered set where uuid = &#63;.
	 *
	 * @param corpProjectMessageId the primary key of the current corp project message
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project message
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage[] findByUuid_PrevAndNext(
		long corpProjectMessageId, String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = findByPrimaryKey(corpProjectMessageId);

		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage[] array = new CorpProjectMessageImpl[3];

			array[0] = getByUuid_PrevAndNext(session, corpProjectMessage, uuid,
					orderByComparator, true);

			array[1] = corpProjectMessage;

			array[2] = getByUuid_PrevAndNext(session, corpProjectMessage, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProjectMessage getByUuid_PrevAndNext(Session session,
		CorpProjectMessage corpProjectMessage, String uuid,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProjectMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProjectMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the corp project messages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CorpProjectMessage corpProjectMessage : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Returns the number of corp project messages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching corp project messages
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "corpProjectMessage.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "corpProjectMessage.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(corpProjectMessage.uuid IS NULL OR corpProjectMessage.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CORPPROJECTID =
		new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCorpProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID =
		new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCorpProjectId",
			new String[] { Long.class.getName() },
			CorpProjectMessageModelImpl.CORPPROJECTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CORPPROJECTID = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorpProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the corp project messages where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the matching corp project messages
	 */
	@Override
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId) {
		return findByCorpProjectId(corpProjectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId,
		int start, int end) {
		return findByCorpProjectId(corpProjectId, start, end, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId,
		int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return findByCorpProjectId(corpProjectId, start, end,
			orderByComparator, true);
	}

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
	@Override
	public List<CorpProjectMessage> findByCorpProjectId(long corpProjectId,
		int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID;
			finderArgs = new Object[] { corpProjectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CORPPROJECTID;
			finderArgs = new Object[] {
					corpProjectId,
					
					start, end, orderByComparator
				};
		}

		List<CorpProjectMessage> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProjectMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CorpProjectMessage corpProjectMessage : list) {
					if ((corpProjectId != corpProjectMessage.getCorpProjectId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				if (!pagination) {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByCorpProjectId_First(long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByCorpProjectId_First(corpProjectId,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByCorpProjectId_First(long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		List<CorpProjectMessage> list = findByCorpProjectId(corpProjectId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByCorpProjectId_Last(long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByCorpProjectId_Last(corpProjectId,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("corpProjectId=");
		msg.append(corpProjectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByCorpProjectId_Last(long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		int count = countByCorpProjectId(corpProjectId);

		if (count == 0) {
			return null;
		}

		List<CorpProjectMessage> list = findByCorpProjectId(corpProjectId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp project messages before and after the current corp project message in the ordered set where corpProjectId = &#63;.
	 *
	 * @param corpProjectMessageId the primary key of the current corp project message
	 * @param corpProjectId the corp project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project message
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = findByPrimaryKey(corpProjectMessageId);

		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage[] array = new CorpProjectMessageImpl[3];

			array[0] = getByCorpProjectId_PrevAndNext(session,
					corpProjectMessage, corpProjectId, orderByComparator, true);

			array[1] = corpProjectMessage;

			array[2] = getByCorpProjectId_PrevAndNext(session,
					corpProjectMessage, corpProjectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProjectMessage getByCorpProjectId_PrevAndNext(
		Session session, CorpProjectMessage corpProjectMessage,
		long corpProjectId,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(corpProjectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProjectMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProjectMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the corp project messages where corpProjectId = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 */
	@Override
	public void removeByCorpProjectId(long corpProjectId) {
		for (CorpProjectMessage corpProjectMessage : findByCorpProjectId(
				corpProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Returns the number of corp project messages where corpProjectId = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @return the number of matching corp project messages
	 */
	@Override
	public int countByCorpProjectId(long corpProjectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CORPPROJECTID;

		Object[] finderArgs = new Object[] { corpProjectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CORPPROJECTID_CORPPROJECTID_2 = "corpProjectMessage.corpProjectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { Integer.class.getName() },
			CorpProjectMessageModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the corp project messages where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching corp project messages
	 */
	@Override
	public List<CorpProjectMessage> findByType(int type) {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByType(int type, int start, int end) {
		return findByType(type, start, end, null);
	}

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
	@Override
	public List<CorpProjectMessage> findByType(int type, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return findByType(type, start, end, orderByComparator, true);
	}

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
	@Override
	public List<CorpProjectMessage> findByType(int type, int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<CorpProjectMessage> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProjectMessage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CorpProjectMessage corpProjectMessage : list) {
					if ((type != corpProjectMessage.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (!pagination) {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByType_First(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByType_First(type,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the first corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByType_First(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		List<CorpProjectMessage> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByType_Last(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByType_Last(type,
				orderByComparator);

		if (corpProjectMessage != null) {
			return corpProjectMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectMessageException(msg.toString());
	}

	/**
	 * Returns the last corp project message in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByType_Last(int type,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		int count = countByType(type);

		if (count == 0) {
			return null;
		}

		List<CorpProjectMessage> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	 *
	 * @param corpProjectMessageId the primary key of the current corp project message
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project message
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type,
		OrderByComparator<CorpProjectMessage> orderByComparator)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = findByPrimaryKey(corpProjectMessageId);

		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage[] array = new CorpProjectMessageImpl[3];

			array[0] = getByType_PrevAndNext(session, corpProjectMessage, type,
					orderByComparator, true);

			array[1] = corpProjectMessage;

			array[2] = getByType_PrevAndNext(session, corpProjectMessage, type,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProjectMessage getByType_PrevAndNext(Session session,
		CorpProjectMessage corpProjectMessage, int type,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_TYPE_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProjectMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProjectMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the corp project messages where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeByType(int type) {
		for (CorpProjectMessage corpProjectMessage : findByType(type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Returns the number of corp project messages where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching corp project messages
	 */
	@Override
	public int countByType(int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

		Object[] finderArgs = new Object[] { type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "corpProjectMessage.type = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_T = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED,
			CorpProjectMessageImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CorpProjectMessageModelImpl.CORPPROJECTID_COLUMN_BITMASK |
			CorpProjectMessageModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the matching corp project message
	 * @throws NoSuchCorpProjectMessageException if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage findByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByC_T(corpProjectId, type);

		if (corpProjectMessage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("corpProjectId=");
			msg.append(corpProjectId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCorpProjectMessageException(msg.toString());
		}

		return corpProjectMessage;
	}

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type) {
		return fetchByC_T(corpProjectId, type, true);
	}

	/**
	 * Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	 */
	@Override
	public CorpProjectMessage fetchByC_T(long corpProjectId, int type,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { corpProjectId, type };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_T,
					finderArgs, this);
		}

		if (result instanceof CorpProjectMessage) {
			CorpProjectMessage corpProjectMessage = (CorpProjectMessage)result;

			if ((corpProjectId != corpProjectMessage.getCorpProjectId()) ||
					(type != corpProjectMessage.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				qPos.add(type);

				List<CorpProjectMessage> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_T, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CorpProjectMessagePersistenceImpl.fetchByC_T(long, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CorpProjectMessage corpProjectMessage = list.get(0);

					result = corpProjectMessage;

					cacheResult(corpProjectMessage);

					if ((corpProjectMessage.getCorpProjectId() != corpProjectId) ||
							(corpProjectMessage.getType() != type)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_T,
							finderArgs, corpProjectMessage);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CorpProjectMessage)result;
		}
	}

	/**
	 * Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the corp project message that was removed
	 */
	@Override
	public CorpProjectMessage removeByC_T(long corpProjectId, int type)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = findByC_T(corpProjectId, type);

		return remove(corpProjectMessage);
	}

	/**
	 * Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	 *
	 * @param corpProjectId the corp project ID
	 * @param type the type
	 * @return the number of matching corp project messages
	 */
	@Override
	public int countByC_T(long corpProjectId, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_T;

		Object[] finderArgs = new Object[] { corpProjectId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CORPPROJECTMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_C_T_CORPPROJECTID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(corpProjectId);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_CORPPROJECTID_2 = "corpProjectMessage.corpProjectId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TYPE_2 = "corpProjectMessage.type = ?";

	public CorpProjectMessagePersistenceImpl() {
		setModelClass(CorpProjectMessage.class);
	}

	/**
	 * Caches the corp project message in the entity cache if it is enabled.
	 *
	 * @param corpProjectMessage the corp project message
	 */
	@Override
	public void cacheResult(CorpProjectMessage corpProjectMessage) {
		entityCache.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey(),
			corpProjectMessage);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_T,
			new Object[] {
				corpProjectMessage.getCorpProjectId(),
				corpProjectMessage.getType()
			}, corpProjectMessage);

		corpProjectMessage.resetOriginalValues();
	}

	/**
	 * Caches the corp project messages in the entity cache if it is enabled.
	 *
	 * @param corpProjectMessages the corp project messages
	 */
	@Override
	public void cacheResult(List<CorpProjectMessage> corpProjectMessages) {
		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			if (entityCache.getResult(
						CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectMessageImpl.class,
						corpProjectMessage.getPrimaryKey()) == null) {
				cacheResult(corpProjectMessage);
			}
			else {
				corpProjectMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp project messages.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CorpProjectMessageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp project message.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpProjectMessage corpProjectMessage) {
		entityCache.removeResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CorpProjectMessageModelImpl)corpProjectMessage,
			true);
	}

	@Override
	public void clearCache(List<CorpProjectMessage> corpProjectMessages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpProjectMessage corpProjectMessage : corpProjectMessages) {
			entityCache.removeResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey());

			clearUniqueFindersCache((CorpProjectMessageModelImpl)corpProjectMessage,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CorpProjectMessageModelImpl corpProjectMessageModelImpl) {
		Object[] args = new Object[] {
				corpProjectMessageModelImpl.getCorpProjectId(),
				corpProjectMessageModelImpl.getType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_T, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_T, args,
			corpProjectMessageModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CorpProjectMessageModelImpl corpProjectMessageModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					corpProjectMessageModelImpl.getCorpProjectId(),
					corpProjectMessageModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, args);
		}

		if ((corpProjectMessageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					corpProjectMessageModelImpl.getOriginalCorpProjectId(),
					corpProjectMessageModelImpl.getOriginalType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_T, args);
		}
	}

	/**
	 * Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	 *
	 * @param corpProjectMessageId the primary key for the new corp project message
	 * @return the new corp project message
	 */
	@Override
	public CorpProjectMessage create(long corpProjectMessageId) {
		CorpProjectMessage corpProjectMessage = new CorpProjectMessageImpl();

		corpProjectMessage.setNew(true);
		corpProjectMessage.setPrimaryKey(corpProjectMessageId);

		String uuid = PortalUUIDUtil.generate();

		corpProjectMessage.setUuid(uuid);

		return corpProjectMessage;
	}

	/**
	 * Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message that was removed
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage remove(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException {
		return remove((Serializable)corpProjectMessageId);
	}

	/**
	 * Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message that was removed
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage remove(Serializable primaryKey)
		throws NoSuchCorpProjectMessageException {
		Session session = null;

		try {
			session = openSession();

			CorpProjectMessage corpProjectMessage = (CorpProjectMessage)session.get(CorpProjectMessageImpl.class,
					primaryKey);

			if (corpProjectMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpProjectMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpProjectMessage);
		}
		catch (NoSuchCorpProjectMessageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CorpProjectMessage removeImpl(
		CorpProjectMessage corpProjectMessage) {
		corpProjectMessage = toUnwrappedModel(corpProjectMessage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(corpProjectMessage)) {
				corpProjectMessage = (CorpProjectMessage)session.get(CorpProjectMessageImpl.class,
						corpProjectMessage.getPrimaryKeyObj());
			}

			if (corpProjectMessage != null) {
				session.delete(corpProjectMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (corpProjectMessage != null) {
			clearCache(corpProjectMessage);
		}

		return corpProjectMessage;
	}

	@Override
	public CorpProjectMessage updateImpl(CorpProjectMessage corpProjectMessage) {
		corpProjectMessage = toUnwrappedModel(corpProjectMessage);

		boolean isNew = corpProjectMessage.isNew();

		CorpProjectMessageModelImpl corpProjectMessageModelImpl = (CorpProjectMessageModelImpl)corpProjectMessage;

		if (Validator.isNull(corpProjectMessage.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			corpProjectMessage.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (corpProjectMessage.getCreateDate() == null)) {
			if (serviceContext == null) {
				corpProjectMessage.setCreateDate(now);
			}
			else {
				corpProjectMessage.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!corpProjectMessageModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				corpProjectMessage.setModifiedDate(now);
			}
			else {
				corpProjectMessage.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (corpProjectMessage.isNew()) {
				session.save(corpProjectMessage);

				corpProjectMessage.setNew(false);
			}
			else {
				corpProjectMessage = (CorpProjectMessage)session.merge(corpProjectMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CorpProjectMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { corpProjectMessageModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { corpProjectMessageModelImpl.getCorpProjectId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID,
				args);

			args = new Object[] { corpProjectMessageModelImpl.getType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpProjectMessageModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { corpProjectMessageModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpProjectMessageModelImpl.getOriginalCorpProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID,
					args);

				args = new Object[] {
						corpProjectMessageModelImpl.getCorpProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CORPPROJECTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CORPPROJECTID,
					args);
			}

			if ((corpProjectMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpProjectMessageModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { corpProjectMessageModelImpl.getType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}
		}

		entityCache.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectMessageImpl.class, corpProjectMessage.getPrimaryKey(),
			corpProjectMessage, false);

		clearUniqueFindersCache(corpProjectMessageModelImpl, false);
		cacheUniqueFindersCache(corpProjectMessageModelImpl);

		corpProjectMessage.resetOriginalValues();

		return corpProjectMessage;
	}

	protected CorpProjectMessage toUnwrappedModel(
		CorpProjectMessage corpProjectMessage) {
		if (corpProjectMessage instanceof CorpProjectMessageImpl) {
			return corpProjectMessage;
		}

		CorpProjectMessageImpl corpProjectMessageImpl = new CorpProjectMessageImpl();

		corpProjectMessageImpl.setNew(corpProjectMessage.isNew());
		corpProjectMessageImpl.setPrimaryKey(corpProjectMessage.getPrimaryKey());

		corpProjectMessageImpl.setUuid(corpProjectMessage.getUuid());
		corpProjectMessageImpl.setCorpProjectMessageId(corpProjectMessage.getCorpProjectMessageId());
		corpProjectMessageImpl.setUserId(corpProjectMessage.getUserId());
		corpProjectMessageImpl.setUserName(corpProjectMessage.getUserName());
		corpProjectMessageImpl.setCreateDate(corpProjectMessage.getCreateDate());
		corpProjectMessageImpl.setModifiedDate(corpProjectMessage.getModifiedDate());
		corpProjectMessageImpl.setCorpProjectId(corpProjectMessage.getCorpProjectId());
		corpProjectMessageImpl.setType(corpProjectMessage.getType());
		corpProjectMessageImpl.setSeverityLevel(corpProjectMessage.getSeverityLevel());
		corpProjectMessageImpl.setTitle(corpProjectMessage.getTitle());
		corpProjectMessageImpl.setContent(corpProjectMessage.getContent());
		corpProjectMessageImpl.setDisplayCP(corpProjectMessage.isDisplayCP());
		corpProjectMessageImpl.setDisplayLCS(corpProjectMessage.isDisplayLCS());
		corpProjectMessageImpl.setDisplayLESA(corpProjectMessage.isDisplayLESA());

		return corpProjectMessageImpl;
	}

	/**
	 * Returns the corp project message with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCorpProjectMessageException {
		CorpProjectMessage corpProjectMessage = fetchByPrimaryKey(primaryKey);

		if (corpProjectMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCorpProjectMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return corpProjectMessage;
	}

	/**
	 * Returns the corp project message with the primary key or throws a {@link NoSuchCorpProjectMessageException} if it could not be found.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message
	 * @throws NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage findByPrimaryKey(long corpProjectMessageId)
		throws NoSuchCorpProjectMessageException {
		return findByPrimaryKey((Serializable)corpProjectMessageId);
	}

	/**
	 * Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project message
	 * @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectMessageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CorpProjectMessage corpProjectMessage = (CorpProjectMessage)serializable;

		if (corpProjectMessage == null) {
			Session session = null;

			try {
				session = openSession();

				corpProjectMessage = (CorpProjectMessage)session.get(CorpProjectMessageImpl.class,
						primaryKey);

				if (corpProjectMessage != null) {
					cacheResult(corpProjectMessage);
				}
				else {
					entityCache.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectMessageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectMessageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return corpProjectMessage;
	}

	/**
	 * Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpProjectMessageId the primary key of the corp project message
	 * @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	 */
	@Override
	public CorpProjectMessage fetchByPrimaryKey(long corpProjectMessageId) {
		return fetchByPrimaryKey((Serializable)corpProjectMessageId);
	}

	@Override
	public Map<Serializable, CorpProjectMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CorpProjectMessage> map = new HashMap<Serializable, CorpProjectMessage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CorpProjectMessage corpProjectMessage = fetchByPrimaryKey(primaryKey);

			if (corpProjectMessage != null) {
				map.put(primaryKey, corpProjectMessage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectMessageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CorpProjectMessage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CORPPROJECTMESSAGE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CorpProjectMessage corpProjectMessage : (List<CorpProjectMessage>)q.list()) {
				map.put(corpProjectMessage.getPrimaryKeyObj(),
					corpProjectMessage);

				cacheResult(corpProjectMessage);

				uncachedPrimaryKeys.remove(corpProjectMessage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CorpProjectMessageModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectMessageImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the corp project messages.
	 *
	 * @return the corp project messages
	 */
	@Override
	public List<CorpProjectMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CorpProjectMessage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<CorpProjectMessage> findAll(int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<CorpProjectMessage> findAll(int start, int end,
		OrderByComparator<CorpProjectMessage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CorpProjectMessage> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProjectMessage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CORPPROJECTMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPPROJECTMESSAGE;

				if (pagination) {
					sql = sql.concat(CorpProjectMessageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProjectMessage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the corp project messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CorpProjectMessage corpProjectMessage : findAll()) {
			remove(corpProjectMessage);
		}
	}

	/**
	 * Returns the number of corp project messages.
	 *
	 * @return the number of corp project messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPPROJECTMESSAGE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CorpProjectMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the corp project message persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CorpProjectMessageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CORPPROJECTMESSAGE = "SELECT corpProjectMessage FROM CorpProjectMessage corpProjectMessage";
	private static final String _SQL_SELECT_CORPPROJECTMESSAGE_WHERE_PKS_IN = "SELECT corpProjectMessage FROM CorpProjectMessage corpProjectMessage WHERE corpProjectMessageId IN (";
	private static final String _SQL_SELECT_CORPPROJECTMESSAGE_WHERE = "SELECT corpProjectMessage FROM CorpProjectMessage corpProjectMessage WHERE ";
	private static final String _SQL_COUNT_CORPPROJECTMESSAGE = "SELECT COUNT(corpProjectMessage) FROM CorpProjectMessage corpProjectMessage";
	private static final String _SQL_COUNT_CORPPROJECTMESSAGE_WHERE = "SELECT COUNT(corpProjectMessage) FROM CorpProjectMessage corpProjectMessage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpProjectMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpProjectMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpProjectMessage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CorpProjectMessagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
}