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

package com.liferay.osb.loop.asset.sharing.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.asset.sharing.exception.NoSuchEntryException;
import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl;
import com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl;
import com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryPK;
import com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the asset sharing entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryPersistence
 * @see com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryUtil
 * @generated
 */
@ProviderType
public class AssetSharingEntryPersistenceImpl extends BasePersistenceImpl<AssetSharingEntry>
	implements AssetSharingEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetSharingEntryUtil} to access the asset sharing entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetSharingEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetSharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<AssetSharingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AssetSharingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetSharingEntry assetSharingEntry : list) {
					if ((classNameId != assetSharingEntry.getClassNameId()) ||
							(classPK != assetSharingEntry.getClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
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
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		List<AssetSharingEntry> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<AssetSharingEntry> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry[] findByC_C_PrevAndNext(
		AssetSharingEntryPK assetSharingEntryPK, long classNameId,
		long classPK, OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = findByPrimaryKey(assetSharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			AssetSharingEntry[] array = new AssetSharingEntryImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetSharingEntry,
					classNameId, classPK, orderByComparator, true);

			array[1] = assetSharingEntry;

			array[2] = getByC_C_PrevAndNext(session, assetSharingEntry,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetSharingEntry getByC_C_PrevAndNext(Session session,
		AssetSharingEntry assetSharingEntry, long classNameId, long classPK,
		OrderByComparator<AssetSharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetSharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetSharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (AssetSharingEntry assetSharingEntry : findByC_C(classNameId,
				classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetSharingEntry);
		}
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching asset sharing entries
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetSharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetSharingEntry.id.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetSharingEntryModelImpl.SHAREDTOCLASSNAMEID_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.SHAREDTOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByS_S(long sharedToClassNameId,
		long sharedToClassPK) {
		return findByS_S(sharedToClassNameId, sharedToClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByS_S(long sharedToClassNameId,
		long sharedToClassPK, int start, int end) {
		return findByS_S(sharedToClassNameId, sharedToClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByS_S(long sharedToClassNameId,
		long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		return findByS_S(sharedToClassNameId, sharedToClassPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByS_S(long sharedToClassNameId,
		long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] { sharedToClassNameId, sharedToClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_S;
			finderArgs = new Object[] {
					sharedToClassNameId, sharedToClassPK,
					
					start, end, orderByComparator
				};
		}

		List<AssetSharingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AssetSharingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetSharingEntry assetSharingEntry : list) {
					if ((sharedToClassNameId != assetSharingEntry.getSharedToClassNameId()) ||
							(sharedToClassPK != assetSharingEntry.getSharedToClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sharedToClassNameId);

				qPos.add(sharedToClassPK);

				if (!pagination) {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
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
	 * Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByS_S_First(long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByS_S_First(sharedToClassNameId,
				sharedToClassPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append(", sharedToClassPK=");
		msg.append(sharedToClassPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByS_S_First(long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		List<AssetSharingEntry> list = findByS_S(sharedToClassNameId,
				sharedToClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByS_S_Last(long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByS_S_Last(sharedToClassNameId,
				sharedToClassPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append(", sharedToClassPK=");
		msg.append(sharedToClassPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByS_S_Last(long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		int count = countByS_S(sharedToClassNameId, sharedToClassPK);

		if (count == 0) {
			return null;
		}

		List<AssetSharingEntry> list = findByS_S(sharedToClassNameId,
				sharedToClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry[] findByS_S_PrevAndNext(
		AssetSharingEntryPK assetSharingEntryPK, long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = findByPrimaryKey(assetSharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			AssetSharingEntry[] array = new AssetSharingEntryImpl[3];

			array[0] = getByS_S_PrevAndNext(session, assetSharingEntry,
					sharedToClassNameId, sharedToClassPK, orderByComparator,
					true);

			array[1] = assetSharingEntry;

			array[2] = getByS_S_PrevAndNext(session, assetSharingEntry,
					sharedToClassNameId, sharedToClassPK, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetSharingEntry getByS_S_PrevAndNext(Session session,
		AssetSharingEntry assetSharingEntry, long sharedToClassNameId,
		long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSPK_2);

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
			query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(sharedToClassNameId);

		qPos.add(sharedToClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetSharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetSharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 */
	@Override
	public void removeByS_S(long sharedToClassNameId, long sharedToClassPK) {
		for (AssetSharingEntry assetSharingEntry : findByS_S(
				sharedToClassNameId, sharedToClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetSharingEntry);
		}
	}

	/**
	 * Returns the number of asset sharing entries where sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the number of matching asset sharing entries
	 */
	@Override
	public int countByS_S(long sharedToClassNameId, long sharedToClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_S;

		Object[] finderArgs = new Object[] { sharedToClassNameId, sharedToClassPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_S_S_SHAREDTOCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sharedToClassNameId);

				qPos.add(sharedToClassPK);

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

	private static final String _FINDER_COLUMN_S_S_SHAREDTOCLASSNAMEID_2 = "assetSharingEntry.id.sharedToClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_S_S_SHAREDTOCLASSPK_2 = "assetSharingEntry.id.sharedToClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetSharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.SHAREDTOCLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @return the matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId) {
		return findByC_C_S(classNameId, classPK, sharedToClassNameId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId, int start, int end) {
		return findByC_C_S(classNameId, classPK, sharedToClassNameId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		return findByC_C_S(classNameId, classPK, sharedToClassNameId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] { classNameId, classPK, sharedToClassNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] {
					classNameId, classPK, sharedToClassNameId,
					
					start, end, orderByComparator
				};
		}

		List<AssetSharingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AssetSharingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetSharingEntry assetSharingEntry : list) {
					if ((classNameId != assetSharingEntry.getClassNameId()) ||
							(classPK != assetSharingEntry.getClassPK()) ||
							(sharedToClassNameId != assetSharingEntry.getSharedToClassNameId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_SHAREDTOCLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(sharedToClassNameId);

				if (!pagination) {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
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
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_C_S_First(long classNameId, long classPK,
		long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_C_S_First(classNameId,
				classPK, sharedToClassNameId, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_C_S_First(long classNameId, long classPK,
		long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		List<AssetSharingEntry> list = findByC_C_S(classNameId, classPK,
				sharedToClassNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_C_S_Last(long classNameId, long classPK,
		long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_C_S_Last(classNameId,
				classPK, sharedToClassNameId, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_C_S_Last(long classNameId, long classPK,
		long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		int count = countByC_C_S(classNameId, classPK, sharedToClassNameId);

		if (count == 0) {
			return null;
		}

		List<AssetSharingEntry> list = findByC_C_S(classNameId, classPK,
				sharedToClassNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry[] findByC_C_S_PrevAndNext(
		AssetSharingEntryPK assetSharingEntryPK, long classNameId,
		long classPK, long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = findByPrimaryKey(assetSharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			AssetSharingEntry[] array = new AssetSharingEntryImpl[3];

			array[0] = getByC_C_S_PrevAndNext(session, assetSharingEntry,
					classNameId, classPK, sharedToClassNameId,
					orderByComparator, true);

			array[1] = assetSharingEntry;

			array[2] = getByC_C_S_PrevAndNext(session, assetSharingEntry,
					classNameId, classPK, sharedToClassNameId,
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

	protected AssetSharingEntry getByC_C_S_PrevAndNext(Session session,
		AssetSharingEntry assetSharingEntry, long classNameId, long classPK,
		long sharedToClassNameId,
		OrderByComparator<AssetSharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_S_SHAREDTOCLASSNAMEID_2);

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
			query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(sharedToClassNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetSharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetSharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 */
	@Override
	public void removeByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId) {
		for (AssetSharingEntry assetSharingEntry : findByC_C_S(classNameId,
				classPK, sharedToClassNameId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetSharingEntry);
		}
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and classPK = &#63; and sharedToClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param sharedToClassNameId the shared to class name ID
	 * @return the number of matching asset sharing entries
	 */
	@Override
	public int countByC_C_S(long classNameId, long classPK,
		long sharedToClassNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_S;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, sharedToClassNameId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_SHAREDTOCLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(sharedToClassNameId);

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

	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 = "assetSharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 = "assetSharingEntry.id.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_SHAREDTOCLASSNAMEID_2 = "assetSharingEntry.id.sharedToClassNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED,
			AssetSharingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetSharingEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.SHAREDTOCLASSNAMEID_COLUMN_BITMASK |
			AssetSharingEntryModelImpl.SHAREDTOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S_S = new FinderPath(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S_S",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_S_S(long classNameId,
		long sharedToClassNameId, long sharedToClassPK) {
		return findByC_S_S(classNameId, sharedToClassNameId, sharedToClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_S_S(long classNameId,
		long sharedToClassNameId, long sharedToClassPK, int start, int end) {
		return findByC_S_S(classNameId, sharedToClassNameId, sharedToClassPK,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_S_S(long classNameId,
		long sharedToClassNameId, long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		return findByC_S_S(classNameId, sharedToClassNameId, sharedToClassPK,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findByC_S_S(long classNameId,
		long sharedToClassNameId, long sharedToClassPK, int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S;
			finderArgs = new Object[] {
					classNameId, sharedToClassNameId, sharedToClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_S;
			finderArgs = new Object[] {
					classNameId, sharedToClassNameId, sharedToClassPK,
					
					start, end, orderByComparator
				};
		}

		List<AssetSharingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AssetSharingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetSharingEntry assetSharingEntry : list) {
					if ((classNameId != assetSharingEntry.getClassNameId()) ||
							(sharedToClassNameId != assetSharingEntry.getSharedToClassNameId()) ||
							(sharedToClassPK != assetSharingEntry.getSharedToClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(sharedToClassNameId);

				qPos.add(sharedToClassPK);

				if (!pagination) {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
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
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_S_S_First(long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_S_S_First(classNameId,
				sharedToClassNameId, sharedToClassPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append(", sharedToClassPK=");
		msg.append(sharedToClassPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_S_S_First(long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		List<AssetSharingEntry> list = findByC_S_S(classNameId,
				sharedToClassNameId, sharedToClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry
	 * @throws NoSuchEntryException if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry findByC_S_S_Last(long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByC_S_S_Last(classNameId,
				sharedToClassNameId, sharedToClassPK, orderByComparator);

		if (assetSharingEntry != null) {
			return assetSharingEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", sharedToClassNameId=");
		msg.append(sharedToClassNameId);

		msg.append(", sharedToClassPK=");
		msg.append(sharedToClassPK);

		msg.append("}");

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset sharing entry, or <code>null</code> if a matching asset sharing entry could not be found
	 */
	@Override
	public AssetSharingEntry fetchByC_S_S_Last(long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		int count = countByC_S_S(classNameId, sharedToClassNameId,
				sharedToClassPK);

		if (count == 0) {
			return null;
		}

		List<AssetSharingEntry> list = findByC_S_S(classNameId,
				sharedToClassNameId, sharedToClassPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset sharing entries before and after the current asset sharing entry in the ordered set where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param assetSharingEntryPK the primary key of the current asset sharing entry
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry[] findByC_S_S_PrevAndNext(
		AssetSharingEntryPK assetSharingEntryPK, long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = findByPrimaryKey(assetSharingEntryPK);

		Session session = null;

		try {
			session = openSession();

			AssetSharingEntry[] array = new AssetSharingEntryImpl[3];

			array[0] = getByC_S_S_PrevAndNext(session, assetSharingEntry,
					classNameId, sharedToClassNameId, sharedToClassPK,
					orderByComparator, true);

			array[1] = assetSharingEntry;

			array[2] = getByC_S_S_PrevAndNext(session, assetSharingEntry,
					classNameId, sharedToClassNameId, sharedToClassPK,
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

	protected AssetSharingEntry getByC_S_S_PrevAndNext(Session session,
		AssetSharingEntry assetSharingEntry, long classNameId,
		long sharedToClassNameId, long sharedToClassPK,
		OrderByComparator<AssetSharingEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETSHARINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSPK_2);

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
			query.append(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(sharedToClassNameId);

		qPos.add(sharedToClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetSharingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetSharingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 */
	@Override
	public void removeByC_S_S(long classNameId, long sharedToClassNameId,
		long sharedToClassPK) {
		for (AssetSharingEntry assetSharingEntry : findByC_S_S(classNameId,
				sharedToClassNameId, sharedToClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetSharingEntry);
		}
	}

	/**
	 * Returns the number of asset sharing entries where classNameId = &#63; and sharedToClassNameId = &#63; and sharedToClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param sharedToClassNameId the shared to class name ID
	 * @param sharedToClassPK the shared to class pk
	 * @return the number of matching asset sharing entries
	 */
	@Override
	public int countByC_S_S(long classNameId, long sharedToClassNameId,
		long sharedToClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S_S;

		Object[] finderArgs = new Object[] {
				classNameId, sharedToClassNameId, sharedToClassPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETSHARINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_S_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_S_S_SHAREDTOCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(sharedToClassNameId);

				qPos.add(sharedToClassPK);

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

	private static final String _FINDER_COLUMN_C_S_S_CLASSNAMEID_2 = "assetSharingEntry.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_S_SHAREDTOCLASSNAMEID_2 = "assetSharingEntry.id.sharedToClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_S_SHAREDTOCLASSPK_2 = "assetSharingEntry.id.sharedToClassPK = ?";

	public AssetSharingEntryPersistenceImpl() {
		setModelClass(AssetSharingEntry.class);
	}

	/**
	 * Caches the asset sharing entry in the entity cache if it is enabled.
	 *
	 * @param assetSharingEntry the asset sharing entry
	 */
	@Override
	public void cacheResult(AssetSharingEntry assetSharingEntry) {
		entityCache.putResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryImpl.class, assetSharingEntry.getPrimaryKey(),
			assetSharingEntry);

		assetSharingEntry.resetOriginalValues();
	}

	/**
	 * Caches the asset sharing entries in the entity cache if it is enabled.
	 *
	 * @param assetSharingEntries the asset sharing entries
	 */
	@Override
	public void cacheResult(List<AssetSharingEntry> assetSharingEntries) {
		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			if (entityCache.getResult(
						AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetSharingEntryImpl.class,
						assetSharingEntry.getPrimaryKey()) == null) {
				cacheResult(assetSharingEntry);
			}
			else {
				assetSharingEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset sharing entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetSharingEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset sharing entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetSharingEntry assetSharingEntry) {
		entityCache.removeResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryImpl.class, assetSharingEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetSharingEntry> assetSharingEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			entityCache.removeResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetSharingEntryImpl.class, assetSharingEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	 *
	 * @param assetSharingEntryPK the primary key for the new asset sharing entry
	 * @return the new asset sharing entry
	 */
	@Override
	public AssetSharingEntry create(AssetSharingEntryPK assetSharingEntryPK) {
		AssetSharingEntry assetSharingEntry = new AssetSharingEntryImpl();

		assetSharingEntry.setNew(true);
		assetSharingEntry.setPrimaryKey(assetSharingEntryPK);

		return assetSharingEntry;
	}

	/**
	 * Removes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry that was removed
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry remove(AssetSharingEntryPK assetSharingEntryPK)
		throws NoSuchEntryException {
		return remove((Serializable)assetSharingEntryPK);
	}

	/**
	 * Removes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset sharing entry
	 * @return the asset sharing entry that was removed
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry remove(Serializable primaryKey)
		throws NoSuchEntryException {
		Session session = null;

		try {
			session = openSession();

			AssetSharingEntry assetSharingEntry = (AssetSharingEntry)session.get(AssetSharingEntryImpl.class,
					primaryKey);

			if (assetSharingEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetSharingEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected AssetSharingEntry removeImpl(AssetSharingEntry assetSharingEntry) {
		assetSharingEntry = toUnwrappedModel(assetSharingEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetSharingEntry)) {
				assetSharingEntry = (AssetSharingEntry)session.get(AssetSharingEntryImpl.class,
						assetSharingEntry.getPrimaryKeyObj());
			}

			if (assetSharingEntry != null) {
				session.delete(assetSharingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetSharingEntry != null) {
			clearCache(assetSharingEntry);
		}

		return assetSharingEntry;
	}

	@Override
	public AssetSharingEntry updateImpl(AssetSharingEntry assetSharingEntry) {
		assetSharingEntry = toUnwrappedModel(assetSharingEntry);

		boolean isNew = assetSharingEntry.isNew();

		AssetSharingEntryModelImpl assetSharingEntryModelImpl = (AssetSharingEntryModelImpl)assetSharingEntry;

		Session session = null;

		try {
			session = openSession();

			if (assetSharingEntry.isNew()) {
				session.save(assetSharingEntry);

				assetSharingEntry.setNew(false);
			}
			else {
				assetSharingEntry = (AssetSharingEntry)session.merge(assetSharingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetSharingEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetSharingEntryModelImpl.getClassNameId(),
					assetSharingEntryModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
				args);

			args = new Object[] {
					assetSharingEntryModelImpl.getSharedToClassNameId(),
					assetSharingEntryModelImpl.getSharedToClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
				args);

			args = new Object[] {
					assetSharingEntryModelImpl.getClassNameId(),
					assetSharingEntryModelImpl.getClassPK(),
					assetSharingEntryModelImpl.getSharedToClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
				args);

			args = new Object[] {
					assetSharingEntryModelImpl.getClassNameId(),
					assetSharingEntryModelImpl.getSharedToClassNameId(),
					assetSharingEntryModelImpl.getSharedToClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetSharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetSharingEntryModelImpl.getOriginalClassNameId(),
						assetSharingEntryModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						assetSharingEntryModelImpl.getClassNameId(),
						assetSharingEntryModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((assetSharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetSharingEntryModelImpl.getOriginalSharedToClassNameId(),
						assetSharingEntryModelImpl.getOriginalSharedToClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);

				args = new Object[] {
						assetSharingEntryModelImpl.getSharedToClassNameId(),
						assetSharingEntryModelImpl.getSharedToClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_S,
					args);
			}

			if ((assetSharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetSharingEntryModelImpl.getOriginalClassNameId(),
						assetSharingEntryModelImpl.getOriginalClassPK(),
						assetSharingEntryModelImpl.getOriginalSharedToClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);

				args = new Object[] {
						assetSharingEntryModelImpl.getClassNameId(),
						assetSharingEntryModelImpl.getClassPK(),
						assetSharingEntryModelImpl.getSharedToClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);
			}

			if ((assetSharingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetSharingEntryModelImpl.getOriginalClassNameId(),
						assetSharingEntryModelImpl.getOriginalSharedToClassNameId(),
						assetSharingEntryModelImpl.getOriginalSharedToClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S,
					args);

				args = new Object[] {
						assetSharingEntryModelImpl.getClassNameId(),
						assetSharingEntryModelImpl.getSharedToClassNameId(),
						assetSharingEntryModelImpl.getSharedToClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_S,
					args);
			}
		}

		entityCache.putResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
			AssetSharingEntryImpl.class, assetSharingEntry.getPrimaryKey(),
			assetSharingEntry, false);

		assetSharingEntry.resetOriginalValues();

		return assetSharingEntry;
	}

	protected AssetSharingEntry toUnwrappedModel(
		AssetSharingEntry assetSharingEntry) {
		if (assetSharingEntry instanceof AssetSharingEntryImpl) {
			return assetSharingEntry;
		}

		AssetSharingEntryImpl assetSharingEntryImpl = new AssetSharingEntryImpl();

		assetSharingEntryImpl.setNew(assetSharingEntry.isNew());
		assetSharingEntryImpl.setPrimaryKey(assetSharingEntry.getPrimaryKey());

		assetSharingEntryImpl.setClassNameId(assetSharingEntry.getClassNameId());
		assetSharingEntryImpl.setClassPK(assetSharingEntry.getClassPK());
		assetSharingEntryImpl.setSharedToClassNameId(assetSharingEntry.getSharedToClassNameId());
		assetSharingEntryImpl.setSharedToClassPK(assetSharingEntry.getSharedToClassPK());

		return assetSharingEntryImpl;
	}

	/**
	 * Returns the asset sharing entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset sharing entry
	 * @return the asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {
		AssetSharingEntry assetSharingEntry = fetchByPrimaryKey(primaryKey);

		if (assetSharingEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetSharingEntry;
	}

	/**
	 * Returns the asset sharing entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry
	 * @throws NoSuchEntryException if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry findByPrimaryKey(
		AssetSharingEntryPK assetSharingEntryPK) throws NoSuchEntryException {
		return findByPrimaryKey((Serializable)assetSharingEntryPK);
	}

	/**
	 * Returns the asset sharing entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset sharing entry
	 * @return the asset sharing entry, or <code>null</code> if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
				AssetSharingEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetSharingEntry assetSharingEntry = (AssetSharingEntry)serializable;

		if (assetSharingEntry == null) {
			Session session = null;

			try {
				session = openSession();

				assetSharingEntry = (AssetSharingEntry)session.get(AssetSharingEntryImpl.class,
						primaryKey);

				if (assetSharingEntry != null) {
					cacheResult(assetSharingEntry);
				}
				else {
					entityCache.putResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
						AssetSharingEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetSharingEntryModelImpl.ENTITY_CACHE_ENABLED,
					AssetSharingEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetSharingEntry;
	}

	/**
	 * Returns the asset sharing entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry, or <code>null</code> if a asset sharing entry with the primary key could not be found
	 */
	@Override
	public AssetSharingEntry fetchByPrimaryKey(
		AssetSharingEntryPK assetSharingEntryPK) {
		return fetchByPrimaryKey((Serializable)assetSharingEntryPK);
	}

	@Override
	public Map<Serializable, AssetSharingEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetSharingEntry> map = new HashMap<Serializable, AssetSharingEntry>();

		for (Serializable primaryKey : primaryKeys) {
			AssetSharingEntry assetSharingEntry = fetchByPrimaryKey(primaryKey);

			if (assetSharingEntry != null) {
				map.put(primaryKey, assetSharingEntry);
			}
		}

		return map;
	}

	/**
	 * Returns all the asset sharing entries.
	 *
	 * @return the asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findAll(int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset sharing entries
	 */
	@Override
	public List<AssetSharingEntry> findAll(int start, int end,
		OrderByComparator<AssetSharingEntry> orderByComparator,
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

		List<AssetSharingEntry> list = null;

		if (retrieveFromCache) {
			list = (List<AssetSharingEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETSHARINGENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETSHARINGENTRY;

				if (pagination) {
					sql = sql.concat(AssetSharingEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetSharingEntry>)QueryUtil.list(q,
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
	 * Removes all the asset sharing entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetSharingEntry assetSharingEntry : findAll()) {
			remove(assetSharingEntry);
		}
	}

	/**
	 * Returns the number of asset sharing entries.
	 *
	 * @return the number of asset sharing entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETSHARINGENTRY);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetSharingEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset sharing entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetSharingEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ASSETSHARINGENTRY = "SELECT assetSharingEntry FROM AssetSharingEntry assetSharingEntry";
	private static final String _SQL_SELECT_ASSETSHARINGENTRY_WHERE = "SELECT assetSharingEntry FROM AssetSharingEntry assetSharingEntry WHERE ";
	private static final String _SQL_COUNT_ASSETSHARINGENTRY = "SELECT COUNT(assetSharingEntry) FROM AssetSharingEntry assetSharingEntry";
	private static final String _SQL_COUNT_ASSETSHARINGENTRY_WHERE = "SELECT COUNT(assetSharingEntry) FROM AssetSharingEntry assetSharingEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetSharingEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetSharingEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetSharingEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetSharingEntryPersistenceImpl.class);
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"classNameId", "classPK", "sharedToClassNameId",
				"sharedToClassPK"
			});
}