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

import com.liferay.osb.exception.NoSuchAssetLicenseException;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.impl.AssetLicenseImpl;
import com.liferay.osb.model.impl.AssetLicenseModelImpl;
import com.liferay.osb.service.persistence.AssetLicensePersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the asset license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicensePersistence
 * @see com.liferay.osb.service.persistence.AssetLicenseUtil
 * @generated
 */
@ProviderType
public class AssetLicensePersistenceImpl extends BasePersistenceImpl<AssetLicense>
	implements AssetLicensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetLicenseUtil} to access the asset license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetLicenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<AssetLicense> orderByComparator,
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

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK())) {
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

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_First(classNameId, classPK,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetLicense, classNameId,
					classPK, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_PrevAndNext(session, assetLicense, classNameId,
					classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (AssetLicense assetLicense : findByC_C(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetLicense.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status) {
		return findByC_C_S(classNameId, classPK, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end) {
		return findByC_C_S(classNameId, classPK, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C_S(classNameId, classPK, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] { classNameId, classPK, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S;
			finderArgs = new Object[] {
					classNameId, classPK, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK()) ||
							(status != assetLicense.getStatus())) {
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

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(status);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_S_First(classNameId, classPK,
				status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C_S(classNameId, classPK, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_S_Last(classNameId, classPK,
				status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C_S(classNameId, classPK, status);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C_S(classNameId, classPK, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, status, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK, int status,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 */
	@Override
	public void removeByC_C_S(long classNameId, long classPK, int status) {
		for (AssetLicense assetLicense : findByC_C_S(classNameId, classPK,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param status the status
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C_S(long classNameId, long classPK, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_S;

		Object[] finderArgs = new Object[] { classNameId, classPK, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_STATUS_2 = "assetLicense.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) {
		return findByC_C_UT_LT(classNameId, classPK, usageType, licenseType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType, int start, int end) {
		return findByC_C_UT_LT(classNameId, classPK, usageType, licenseType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C_UT_LT(classNameId, classPK, usageType, licenseType,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK()) ||
							(usageType != assetLicense.getUsageType()) ||
							(licenseType != assetLicense.getLicenseType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_First(classNameId,
				classPK, usageType, licenseType, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_First(long classNameId, long classPK,
		int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_Last(classNameId, classPK,
				usageType, licenseType, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_Last(long classNameId, long classPK,
		int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C_UT_LT(classNameId, classPK, usageType,
				licenseType);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_UT_LT_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
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

	protected AssetLicense getByC_C_UT_LT_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 */
	@Override
	public void removeByC_C_UT_LT(long classNameId, long classPK,
		int usageType, int licenseType) {
		for (AssetLicense assetLicense : findByC_C_UT_LT(classNameId, classPK,
				usageType, licenseType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C_UT_LT(long classNameId, long classPK, int usageType,
		int licenseType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_UT_LT;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

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

	private static final String _FINDER_COLUMN_C_C_UT_LT_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LICENSETYPE_2 = "assetLicense.licenseType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT_LTA",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType, long licenseTypeAllotment) {
		return findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end) {
		return findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C_UT_LT_LTA(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK()) ||
							(usageType != assetLicense.getUsageType()) ||
							(licenseType != assetLicense.getLicenseType()) ||
							(licenseTypeAllotment != assetLicense.getLicenseTypeAllotment())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_First(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_LTA_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C_UT_LT_LTA(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_Last(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_LTA_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C_UT_LT_LTA(classNameId, classPK, usageType,
				licenseType, licenseTypeAllotment);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C_UT_LT_LTA(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_UT_LT_LTA_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_LTA_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_LTA_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_UT_LT_LTA_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(licenseTypeAllotment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 */
	@Override
	public void removeByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment) {
		for (AssetLicense assetLicense : findByC_C_UT_LT_LTA(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C_UT_LT_LTA(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType,
				licenseTypeAllotment
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

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

	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_LICENSETYPEALLOTMENT_2 =
		"assetLicense.licenseTypeAllotment = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_UT_LT_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) {
		return findByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status, int start, int end) {
		return findByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C_UT_LT_S(classNameId, classPK, usageType, licenseType,
			status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType, status
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK()) ||
							(usageType != assetLicense.getUsageType()) ||
							(licenseType != assetLicense.getLicenseType()) ||
							(status != assetLicense.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(status);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_S_First(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_S_First(classNameId,
				classPK, usageType, licenseType, status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_S_First(long classNameId,
		long classPK, int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C_UT_LT_S(classNameId, classPK,
				usageType, licenseType, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_S_Last(classNameId,
				classPK, usageType, licenseType, status, orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_S_Last(long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C_UT_LT_S(classNameId, classPK, usageType,
				licenseType, status);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C_UT_LT_S(classNameId, classPK,
				usageType, licenseType, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_UT_LT_S_PrevAndNext(long assetLicenseId,
		long classNameId, long classPK, int usageType, int licenseType,
		int status, OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType, status,
					orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType, status,
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

	protected AssetLicense getByC_C_UT_LT_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, int status,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 */
	@Override
	public void removeByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) {
		for (AssetLicense assetLicense : findByC_C_UT_LT_S(classNameId,
				classPK, usageType, licenseType, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param status the status
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C_UT_LT_S(long classNameId, long classPK,
		int usageType, int licenseType, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_UT_LT_S;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_C_UT_LT_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_S_STATUS_2 = "assetLicense.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S =
		new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, AssetLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName()
			},
			AssetLicenseModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetLicenseModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetLicenseModelImpl.USAGETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPE_COLUMN_BITMASK |
			AssetLicenseModelImpl.LICENSETYPEALLOTMENT_COLUMN_BITMASK |
			AssetLicenseModelImpl.STATUS_COLUMN_BITMASK |
			AssetLicenseModelImpl.LIFETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S = new FinderPath(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_C_UT_LT_LTA_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Long.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @return the matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status) {
		return findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end) {
		return findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset licenses
	 */
	@Override
	public List<AssetLicense> findByC_C_UT_LT_LTA_S(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status, int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S;
			finderArgs = new Object[] {
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status,
					
					start, end, orderByComparator
				};
		}

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetLicense assetLicense : list) {
					if ((classNameId != assetLicense.getClassNameId()) ||
							(classPK != assetLicense.getClassPK()) ||
							(usageType != assetLicense.getUsageType()) ||
							(licenseType != assetLicense.getLicenseType()) ||
							(licenseTypeAllotment != assetLicense.getLicenseTypeAllotment()) ||
							(status != assetLicense.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				qPos.add(status);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_S_First(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_LTA_S_First(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		List<AssetLicense> list = findByC_C_UT_LT_LTA_S(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license
	 * @throws NoSuchAssetLicenseException if a matching asset license could not be found
	 */
	@Override
	public AssetLicense findByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByC_C_UT_LT_LTA_S_Last(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status,
				orderByComparator);

		if (assetLicense != null) {
			return assetLicense;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", usageType=");
		msg.append(usageType);

		msg.append(", licenseType=");
		msg.append(licenseType);

		msg.append(", licenseTypeAllotment=");
		msg.append(licenseTypeAllotment);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset license, or <code>null</code> if a matching asset license could not be found
	 */
	@Override
	public AssetLicense fetchByC_C_UT_LT_LTA_S_Last(long classNameId,
		long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator) {
		int count = countByC_C_UT_LT_LTA_S(classNameId, classPK, usageType,
				licenseType, licenseTypeAllotment, status);

		if (count == 0) {
			return null;
		}

		List<AssetLicense> list = findByC_C_UT_LT_LTA_S(classNameId, classPK,
				usageType, licenseType, licenseTypeAllotment, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset licenses before and after the current asset license in the ordered set where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param assetLicenseId the primary key of the current asset license
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense[] findByC_C_UT_LT_LTA_S_PrevAndNext(
		long assetLicenseId, long classNameId, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = findByPrimaryKey(assetLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetLicense[] array = new AssetLicenseImpl[3];

			array[0] = getByC_C_UT_LT_LTA_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status, orderByComparator, true);

			array[1] = assetLicense;

			array[2] = getByC_C_UT_LT_LTA_S_PrevAndNext(session, assetLicense,
					classNameId, classPK, usageType, licenseType,
					licenseTypeAllotment, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetLicense getByC_C_UT_LT_LTA_S_PrevAndNext(Session session,
		AssetLicense assetLicense, long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status,
		OrderByComparator<AssetLicense> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

		query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

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
			query.append(AssetLicenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(usageType);

		qPos.add(licenseType);

		qPos.add(licenseTypeAllotment);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 */
	@Override
	public void removeByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status) {
		for (AssetLicense assetLicense : findByC_C_UT_LT_LTA_S(classNameId,
				classPK, usageType, licenseType, licenseTypeAllotment, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses where classNameId = &#63; and classPK = &#63; and usageType = &#63; and licenseType = &#63; and licenseTypeAllotment = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param usageType the usage type
	 * @param licenseType the license type
	 * @param licenseTypeAllotment the license type allotment
	 * @param status the status
	 * @return the number of matching asset licenses
	 */
	@Override
	public int countByC_C_UT_LT_LTA_S(long classNameId, long classPK,
		int usageType, int licenseType, long licenseTypeAllotment, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, usageType, licenseType,
				licenseTypeAllotment, status
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_ASSETLICENSE_WHERE);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2);

			query.append(_FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(usageType);

				qPos.add(licenseType);

				qPos.add(licenseTypeAllotment);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSNAMEID_2 = "assetLicense.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_CLASSPK_2 = "assetLicense.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_USAGETYPE_2 = "assetLicense.usageType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPE_2 = "assetLicense.licenseType = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_LICENSETYPEALLOTMENT_2 =
		"assetLicense.licenseTypeAllotment = ? AND ";
	private static final String _FINDER_COLUMN_C_C_UT_LT_LTA_S_STATUS_2 = "assetLicense.status = ?";

	public AssetLicensePersistenceImpl() {
		setModelClass(AssetLicense.class);
	}

	/**
	 * Caches the asset license in the entity cache if it is enabled.
	 *
	 * @param assetLicense the asset license
	 */
	@Override
	public void cacheResult(AssetLicense assetLicense) {
		entityCache.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey(), assetLicense);

		assetLicense.resetOriginalValues();
	}

	/**
	 * Caches the asset licenses in the entity cache if it is enabled.
	 *
	 * @param assetLicenses the asset licenses
	 */
	@Override
	public void cacheResult(List<AssetLicense> assetLicenses) {
		for (AssetLicense assetLicense : assetLicenses) {
			if (entityCache.getResult(
						AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetLicenseImpl.class, assetLicense.getPrimaryKey()) == null) {
				cacheResult(assetLicense);
			}
			else {
				assetLicense.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset licenses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetLicenseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset license.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetLicense assetLicense) {
		entityCache.removeResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetLicense> assetLicenses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetLicense assetLicense : assetLicenses) {
			entityCache.removeResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetLicenseImpl.class, assetLicense.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset license with the primary key. Does not add the asset license to the database.
	 *
	 * @param assetLicenseId the primary key for the new asset license
	 * @return the new asset license
	 */
	@Override
	public AssetLicense create(long assetLicenseId) {
		AssetLicense assetLicense = new AssetLicenseImpl();

		assetLicense.setNew(true);
		assetLicense.setPrimaryKey(assetLicenseId);

		return assetLicense;
	}

	/**
	 * Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license that was removed
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense remove(long assetLicenseId)
		throws NoSuchAssetLicenseException {
		return remove((Serializable)assetLicenseId);
	}

	/**
	 * Removes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license that was removed
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense remove(Serializable primaryKey)
		throws NoSuchAssetLicenseException {
		Session session = null;

		try {
			session = openSession();

			AssetLicense assetLicense = (AssetLicense)session.get(AssetLicenseImpl.class,
					primaryKey);

			if (assetLicense == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetLicense);
		}
		catch (NoSuchAssetLicenseException nsee) {
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
	protected AssetLicense removeImpl(AssetLicense assetLicense) {
		assetLicense = toUnwrappedModel(assetLicense);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetLicense)) {
				assetLicense = (AssetLicense)session.get(AssetLicenseImpl.class,
						assetLicense.getPrimaryKeyObj());
			}

			if (assetLicense != null) {
				session.delete(assetLicense);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetLicense != null) {
			clearCache(assetLicense);
		}

		return assetLicense;
	}

	@Override
	public AssetLicense updateImpl(AssetLicense assetLicense) {
		assetLicense = toUnwrappedModel(assetLicense);

		boolean isNew = assetLicense.isNew();

		AssetLicenseModelImpl assetLicenseModelImpl = (AssetLicenseModelImpl)assetLicense;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetLicense.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetLicense.setCreateDate(now);
			}
			else {
				assetLicense.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!assetLicenseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetLicense.setModifiedDate(now);
			}
			else {
				assetLicense.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (assetLicense.isNew()) {
				session.save(assetLicense);

				assetLicense.setNew(false);
			}
			else {
				assetLicense = (AssetLicense)session.merge(assetLicense);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetLicenseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
				args);

			args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK(),
					assetLicenseModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
				args);

			args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK(),
					assetLicenseModelImpl.getUsageType(),
					assetLicenseModelImpl.getLicenseType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT,
				args);

			args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK(),
					assetLicenseModelImpl.getUsageType(),
					assetLicenseModelImpl.getLicenseType(),
					assetLicenseModelImpl.getLicenseTypeAllotment()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA,
				args);

			args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK(),
					assetLicenseModelImpl.getUsageType(),
					assetLicenseModelImpl.getLicenseType(),
					assetLicenseModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S,
				args);

			args = new Object[] {
					assetLicenseModelImpl.getClassNameId(),
					assetLicenseModelImpl.getClassPK(),
					assetLicenseModelImpl.getUsageType(),
					assetLicenseModelImpl.getLicenseType(),
					assetLicenseModelImpl.getLicenseTypeAllotment(),
					assetLicenseModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK(),
						assetLicenseModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK(),
						assetLicenseModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK(),
						assetLicenseModelImpl.getOriginalUsageType(),
						assetLicenseModelImpl.getOriginalLicenseType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK(),
						assetLicenseModelImpl.getUsageType(),
						assetLicenseModelImpl.getLicenseType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK(),
						assetLicenseModelImpl.getOriginalUsageType(),
						assetLicenseModelImpl.getOriginalLicenseType(),
						assetLicenseModelImpl.getOriginalLicenseTypeAllotment()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK(),
						assetLicenseModelImpl.getUsageType(),
						assetLicenseModelImpl.getLicenseType(),
						assetLicenseModelImpl.getLicenseTypeAllotment()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK(),
						assetLicenseModelImpl.getOriginalUsageType(),
						assetLicenseModelImpl.getOriginalLicenseType(),
						assetLicenseModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK(),
						assetLicenseModelImpl.getUsageType(),
						assetLicenseModelImpl.getLicenseType(),
						assetLicenseModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_S,
					args);
			}

			if ((assetLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetLicenseModelImpl.getOriginalClassNameId(),
						assetLicenseModelImpl.getOriginalClassPK(),
						assetLicenseModelImpl.getOriginalUsageType(),
						assetLicenseModelImpl.getOriginalLicenseType(),
						assetLicenseModelImpl.getOriginalLicenseTypeAllotment(),
						assetLicenseModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S,
					args);

				args = new Object[] {
						assetLicenseModelImpl.getClassNameId(),
						assetLicenseModelImpl.getClassPK(),
						assetLicenseModelImpl.getUsageType(),
						assetLicenseModelImpl.getLicenseType(),
						assetLicenseModelImpl.getLicenseTypeAllotment(),
						assetLicenseModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_UT_LT_LTA_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_UT_LT_LTA_S,
					args);
			}
		}

		entityCache.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetLicenseImpl.class, assetLicense.getPrimaryKey(), assetLicense,
			false);

		assetLicense.resetOriginalValues();

		return assetLicense;
	}

	protected AssetLicense toUnwrappedModel(AssetLicense assetLicense) {
		if (assetLicense instanceof AssetLicenseImpl) {
			return assetLicense;
		}

		AssetLicenseImpl assetLicenseImpl = new AssetLicenseImpl();

		assetLicenseImpl.setNew(assetLicense.isNew());
		assetLicenseImpl.setPrimaryKey(assetLicense.getPrimaryKey());

		assetLicenseImpl.setAssetLicenseId(assetLicense.getAssetLicenseId());
		assetLicenseImpl.setUserId(assetLicense.getUserId());
		assetLicenseImpl.setCreateDate(assetLicense.getCreateDate());
		assetLicenseImpl.setModifiedDate(assetLicense.getModifiedDate());
		assetLicenseImpl.setClassNameId(assetLicense.getClassNameId());
		assetLicenseImpl.setClassPK(assetLicense.getClassPK());
		assetLicenseImpl.setLicenseId(assetLicense.getLicenseId());
		assetLicenseImpl.setName(assetLicense.getName());
		assetLicenseImpl.setRequiredVersion(assetLicense.getRequiredVersion());
		assetLicenseImpl.setUsageType(assetLicense.getUsageType());
		assetLicenseImpl.setLicenseType(assetLicense.getLicenseType());
		assetLicenseImpl.setLicenseTypeAllotment(assetLicense.getLicenseTypeAllotment());
		assetLicenseImpl.setLifetime(assetLicense.getLifetime());
		assetLicenseImpl.setStatus(assetLicense.getStatus());

		return assetLicenseImpl;
	}

	/**
	 * Returns the asset license with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetLicenseException {
		AssetLicense assetLicense = fetchByPrimaryKey(primaryKey);

		if (assetLicense == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetLicense;
	}

	/**
	 * Returns the asset license with the primary key or throws a {@link NoSuchAssetLicenseException} if it could not be found.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license
	 * @throws NoSuchAssetLicenseException if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense findByPrimaryKey(long assetLicenseId)
		throws NoSuchAssetLicenseException {
		return findByPrimaryKey((Serializable)assetLicenseId);
	}

	/**
	 * Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset license
	 * @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetLicenseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetLicense assetLicense = (AssetLicense)serializable;

		if (assetLicense == null) {
			Session session = null;

			try {
				session = openSession();

				assetLicense = (AssetLicense)session.get(AssetLicenseImpl.class,
						primaryKey);

				if (assetLicense != null) {
					cacheResult(assetLicense);
				}
				else {
					entityCache.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetLicenseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetLicenseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetLicense;
	}

	/**
	 * Returns the asset license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetLicenseId the primary key of the asset license
	 * @return the asset license, or <code>null</code> if a asset license with the primary key could not be found
	 */
	@Override
	public AssetLicense fetchByPrimaryKey(long assetLicenseId) {
		return fetchByPrimaryKey((Serializable)assetLicenseId);
	}

	@Override
	public Map<Serializable, AssetLicense> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetLicense> map = new HashMap<Serializable, AssetLicense>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetLicense assetLicense = fetchByPrimaryKey(primaryKey);

			if (assetLicense != null) {
				map.put(primaryKey, assetLicense);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetLicenseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssetLicense)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETLICENSE_WHERE_PKS_IN);

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

			for (AssetLicense assetLicense : (List<AssetLicense>)q.list()) {
				map.put(assetLicense.getPrimaryKeyObj(), assetLicense);

				cacheResult(assetLicense);

				uncachedPrimaryKeys.remove(assetLicense.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetLicenseImpl.class, primaryKey, nullModel);
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
	 * Returns all the asset licenses.
	 *
	 * @return the asset licenses
	 */
	@Override
	public List<AssetLicense> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @return the range of asset licenses
	 */
	@Override
	public List<AssetLicense> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset licenses
	 */
	@Override
	public List<AssetLicense> findAll(int start, int end,
		OrderByComparator<AssetLicense> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset licenses
	 * @param end the upper bound of the range of asset licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset licenses
	 */
	@Override
	public List<AssetLicense> findAll(int start, int end,
		OrderByComparator<AssetLicense> orderByComparator,
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

		List<AssetLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetLicense>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETLICENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLICENSE;

				if (pagination) {
					sql = sql.concat(AssetLicenseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetLicense>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the asset licenses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetLicense assetLicense : findAll()) {
			remove(assetLicense);
		}
	}

	/**
	 * Returns the number of asset licenses.
	 *
	 * @return the number of asset licenses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETLICENSE);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetLicenseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset license persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetLicenseImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ASSETLICENSE = "SELECT assetLicense FROM AssetLicense assetLicense";
	private static final String _SQL_SELECT_ASSETLICENSE_WHERE_PKS_IN = "SELECT assetLicense FROM AssetLicense assetLicense WHERE assetLicenseId IN (";
	private static final String _SQL_SELECT_ASSETLICENSE_WHERE = "SELECT assetLicense FROM AssetLicense assetLicense WHERE ";
	private static final String _SQL_COUNT_ASSETLICENSE = "SELECT COUNT(assetLicense) FROM AssetLicense assetLicense";
	private static final String _SQL_COUNT_ASSETLICENSE_WHERE = "SELECT COUNT(assetLicense) FROM AssetLicense assetLicense WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetLicense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetLicense exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetLicense exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetLicensePersistenceImpl.class);
}