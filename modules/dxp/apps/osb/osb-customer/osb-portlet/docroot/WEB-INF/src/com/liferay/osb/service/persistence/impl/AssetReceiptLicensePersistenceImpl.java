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

import com.liferay.osb.exception.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.impl.AssetReceiptLicenseImpl;
import com.liferay.osb.model.impl.AssetReceiptLicenseModelImpl;
import com.liferay.osb.service.persistence.AssetReceiptLicensePersistence;

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
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the asset receipt license service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicensePersistence
 * @see com.liferay.osb.service.persistence.AssetReceiptLicenseUtil
 * @generated
 */
@ProviderType
public class AssetReceiptLicensePersistenceImpl extends BasePersistenceImpl<AssetReceiptLicense>
	implements AssetReceiptLicensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetReceiptLicenseUtil} to access the asset receipt license persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetReceiptLicenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AssetReceiptLicenseModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the asset receipt licenses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of matching asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findByUuid(String uuid, int start,
		int end, OrderByComparator<AssetReceiptLicense> orderByComparator,
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

		List<AssetReceiptLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetReceiptLicense>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetReceiptLicense assetReceiptLicense : list) {
					if (!Objects.equals(uuid, assetReceiptLicense.getUuid())) {
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

			query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
				query.append(AssetReceiptLicenseModelImpl.ORDER_BY_JPQL);
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
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Returns the first asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license
	 * @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 */
	@Override
	public AssetReceiptLicense findByUuid_First(String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException {
		AssetReceiptLicense assetReceiptLicense = fetchByUuid_First(uuid,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the first asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 */
	@Override
	public AssetReceiptLicense fetchByUuid_First(String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		List<AssetReceiptLicense> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license
	 * @throws NoSuchAssetReceiptLicenseException if a matching asset receipt license could not be found
	 */
	@Override
	public AssetReceiptLicense findByUuid_Last(String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException {
		AssetReceiptLicense assetReceiptLicense = fetchByUuid_Last(uuid,
				orderByComparator);

		if (assetReceiptLicense != null) {
			return assetReceiptLicense;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetReceiptLicenseException(msg.toString());
	}

	/**
	 * Returns the last asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset receipt license, or <code>null</code> if a matching asset receipt license could not be found
	 */
	@Override
	public AssetReceiptLicense fetchByUuid_Last(String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssetReceiptLicense> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset receipt licenses before and after the current asset receipt license in the ordered set where uuid = &#63;.
	 *
	 * @param assetReceiptLicenseId the primary key of the current asset receipt license
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset receipt license
	 * @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense[] findByUuid_PrevAndNext(
		long assetReceiptLicenseId, String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator)
		throws NoSuchAssetReceiptLicenseException {
		AssetReceiptLicense assetReceiptLicense = findByPrimaryKey(assetReceiptLicenseId);

		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense[] array = new AssetReceiptLicenseImpl[3];

			array[0] = getByUuid_PrevAndNext(session, assetReceiptLicense,
					uuid, orderByComparator, true);

			array[1] = assetReceiptLicense;

			array[2] = getByUuid_PrevAndNext(session, assetReceiptLicense,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetReceiptLicense getByUuid_PrevAndNext(Session session,
		AssetReceiptLicense assetReceiptLicense, String uuid,
		OrderByComparator<AssetReceiptLicense> orderByComparator,
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

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE);

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
			query.append(AssetReceiptLicenseModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(assetReceiptLicense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetReceiptLicense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset receipt licenses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssetReceiptLicense assetReceiptLicense : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Returns the number of asset receipt licenses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset receipt licenses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTLICENSE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "assetReceiptLicense.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetReceiptLicense.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetReceiptLicense.uuid IS NULL OR assetReceiptLicense.uuid = '')";

	public AssetReceiptLicensePersistenceImpl() {
		setModelClass(AssetReceiptLicense.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the asset receipt license in the entity cache if it is enabled.
	 *
	 * @param assetReceiptLicense the asset receipt license
	 */
	@Override
	public void cacheResult(AssetReceiptLicense assetReceiptLicense) {
		entityCache.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey(),
			assetReceiptLicense);

		assetReceiptLicense.resetOriginalValues();
	}

	/**
	 * Caches the asset receipt licenses in the entity cache if it is enabled.
	 *
	 * @param assetReceiptLicenses the asset receipt licenses
	 */
	@Override
	public void cacheResult(List<AssetReceiptLicense> assetReceiptLicenses) {
		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			if (entityCache.getResult(
						AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptLicenseImpl.class,
						assetReceiptLicense.getPrimaryKey()) == null) {
				cacheResult(assetReceiptLicense);
			}
			else {
				assetReceiptLicense.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset receipt licenses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetReceiptLicenseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset receipt license.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetReceiptLicense assetReceiptLicense) {
		entityCache.removeResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetReceiptLicense> assetReceiptLicenses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			entityCache.removeResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptLicenseImpl.class,
				assetReceiptLicense.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	 *
	 * @param assetReceiptLicenseId the primary key for the new asset receipt license
	 * @return the new asset receipt license
	 */
	@Override
	public AssetReceiptLicense create(long assetReceiptLicenseId) {
		AssetReceiptLicense assetReceiptLicense = new AssetReceiptLicenseImpl();

		assetReceiptLicense.setNew(true);
		assetReceiptLicense.setPrimaryKey(assetReceiptLicenseId);

		String uuid = PortalUUIDUtil.generate();

		assetReceiptLicense.setUuid(uuid);

		return assetReceiptLicense;
	}

	/**
	 * Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license that was removed
	 * @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense remove(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException {
		return remove((Serializable)assetReceiptLicenseId);
	}

	/**
	 * Removes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license that was removed
	 * @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense remove(Serializable primaryKey)
		throws NoSuchAssetReceiptLicenseException {
		Session session = null;

		try {
			session = openSession();

			AssetReceiptLicense assetReceiptLicense = (AssetReceiptLicense)session.get(AssetReceiptLicenseImpl.class,
					primaryKey);

			if (assetReceiptLicense == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetReceiptLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetReceiptLicense);
		}
		catch (NoSuchAssetReceiptLicenseException nsee) {
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
	protected AssetReceiptLicense removeImpl(
		AssetReceiptLicense assetReceiptLicense) {
		assetReceiptLicense = toUnwrappedModel(assetReceiptLicense);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetReceiptLicense)) {
				assetReceiptLicense = (AssetReceiptLicense)session.get(AssetReceiptLicenseImpl.class,
						assetReceiptLicense.getPrimaryKeyObj());
			}

			if (assetReceiptLicense != null) {
				session.delete(assetReceiptLicense);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetReceiptLicense != null) {
			clearCache(assetReceiptLicense);
		}

		return assetReceiptLicense;
	}

	@Override
	public AssetReceiptLicense updateImpl(
		AssetReceiptLicense assetReceiptLicense) {
		assetReceiptLicense = toUnwrappedModel(assetReceiptLicense);

		boolean isNew = assetReceiptLicense.isNew();

		AssetReceiptLicenseModelImpl assetReceiptLicenseModelImpl = (AssetReceiptLicenseModelImpl)assetReceiptLicense;

		if (Validator.isNull(assetReceiptLicense.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetReceiptLicense.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetReceiptLicense.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetReceiptLicense.setCreateDate(now);
			}
			else {
				assetReceiptLicense.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!assetReceiptLicenseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetReceiptLicense.setModifiedDate(now);
			}
			else {
				assetReceiptLicense.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (assetReceiptLicense.isNew()) {
				session.save(assetReceiptLicense);

				assetReceiptLicense.setNew(false);
			}
			else {
				assetReceiptLicense = (AssetReceiptLicense)session.merge(assetReceiptLicense);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AssetReceiptLicenseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { assetReceiptLicenseModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((assetReceiptLicenseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetReceiptLicenseModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { assetReceiptLicenseModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptLicenseImpl.class, assetReceiptLicense.getPrimaryKey(),
			assetReceiptLicense, false);

		assetReceiptLicense.resetOriginalValues();

		return assetReceiptLicense;
	}

	protected AssetReceiptLicense toUnwrappedModel(
		AssetReceiptLicense assetReceiptLicense) {
		if (assetReceiptLicense instanceof AssetReceiptLicenseImpl) {
			return assetReceiptLicense;
		}

		AssetReceiptLicenseImpl assetReceiptLicenseImpl = new AssetReceiptLicenseImpl();

		assetReceiptLicenseImpl.setNew(assetReceiptLicense.isNew());
		assetReceiptLicenseImpl.setPrimaryKey(assetReceiptLicense.getPrimaryKey());

		assetReceiptLicenseImpl.setUuid(assetReceiptLicense.getUuid());
		assetReceiptLicenseImpl.setAssetReceiptLicenseId(assetReceiptLicense.getAssetReceiptLicenseId());
		assetReceiptLicenseImpl.setUserId(assetReceiptLicense.getUserId());
		assetReceiptLicenseImpl.setUserName(assetReceiptLicense.getUserName());
		assetReceiptLicenseImpl.setCreateDate(assetReceiptLicense.getCreateDate());
		assetReceiptLicenseImpl.setModifiedDate(assetReceiptLicense.getModifiedDate());
		assetReceiptLicenseImpl.setAssetReceiptId(assetReceiptLicense.getAssetReceiptId());
		assetReceiptLicenseImpl.setAssetLicenseId(assetReceiptLicense.getAssetLicenseId());
		assetReceiptLicenseImpl.setAssetEntryId(assetReceiptLicense.getAssetEntryId());
		assetReceiptLicenseImpl.setOwnerClassNameId(assetReceiptLicense.getOwnerClassNameId());
		assetReceiptLicenseImpl.setOwnerClassPK(assetReceiptLicense.getOwnerClassPK());
		assetReceiptLicenseImpl.setProductClassNameId(assetReceiptLicense.getProductClassNameId());
		assetReceiptLicenseImpl.setProductClassPK(assetReceiptLicense.getProductClassPK());
		assetReceiptLicenseImpl.setProductId(assetReceiptLicense.getProductId());
		assetReceiptLicenseImpl.setStartDate(assetReceiptLicense.getStartDate());
		assetReceiptLicenseImpl.setEndDate(assetReceiptLicense.getEndDate());
		assetReceiptLicenseImpl.setUsageType(assetReceiptLicense.getUsageType());
		assetReceiptLicenseImpl.setLicenseType(assetReceiptLicense.getLicenseType());
		assetReceiptLicenseImpl.setLicenseTypeAllotment(assetReceiptLicense.getLicenseTypeAllotment());
		assetReceiptLicenseImpl.setLicenseLifetime(assetReceiptLicense.getLicenseLifetime());

		return assetReceiptLicenseImpl;
	}

	/**
	 * Returns the asset receipt license with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license
	 * @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetReceiptLicenseException {
		AssetReceiptLicense assetReceiptLicense = fetchByPrimaryKey(primaryKey);

		if (assetReceiptLicense == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetReceiptLicenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetReceiptLicense;
	}

	/**
	 * Returns the asset receipt license with the primary key or throws a {@link NoSuchAssetReceiptLicenseException} if it could not be found.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license
	 * @throws NoSuchAssetReceiptLicenseException if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense findByPrimaryKey(long assetReceiptLicenseId)
		throws NoSuchAssetReceiptLicenseException {
		return findByPrimaryKey((Serializable)assetReceiptLicenseId);
	}

	/**
	 * Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt license
	 * @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptLicenseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssetReceiptLicense assetReceiptLicense = (AssetReceiptLicense)serializable;

		if (assetReceiptLicense == null) {
			Session session = null;

			try {
				session = openSession();

				assetReceiptLicense = (AssetReceiptLicense)session.get(AssetReceiptLicenseImpl.class,
						primaryKey);

				if (assetReceiptLicense != null) {
					cacheResult(assetReceiptLicense);
				}
				else {
					entityCache.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptLicenseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetReceiptLicenseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetReceiptLicense;
	}

	/**
	 * Returns the asset receipt license with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetReceiptLicenseId the primary key of the asset receipt license
	 * @return the asset receipt license, or <code>null</code> if a asset receipt license with the primary key could not be found
	 */
	@Override
	public AssetReceiptLicense fetchByPrimaryKey(long assetReceiptLicenseId) {
		return fetchByPrimaryKey((Serializable)assetReceiptLicenseId);
	}

	@Override
	public Map<Serializable, AssetReceiptLicense> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssetReceiptLicense> map = new HashMap<Serializable, AssetReceiptLicense>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssetReceiptLicense assetReceiptLicense = fetchByPrimaryKey(primaryKey);

			if (assetReceiptLicense != null) {
				map.put(primaryKey, assetReceiptLicense);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetReceiptLicenseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssetReceiptLicense)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSETRECEIPTLICENSE_WHERE_PKS_IN);

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

			for (AssetReceiptLicense assetReceiptLicense : (List<AssetReceiptLicense>)q.list()) {
				map.put(assetReceiptLicense.getPrimaryKeyObj(),
					assetReceiptLicense);

				cacheResult(assetReceiptLicense);

				uncachedPrimaryKeys.remove(assetReceiptLicense.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssetReceiptLicenseModelImpl.ENTITY_CACHE_ENABLED,
					AssetReceiptLicenseImpl.class, primaryKey, nullModel);
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
	 * Returns all the asset receipt licenses.
	 *
	 * @return the asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @return the range of asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findAll(int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the asset receipt licenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetReceiptLicenseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt licenses
	 * @param end the upper bound of the range of asset receipt licenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of asset receipt licenses
	 */
	@Override
	public List<AssetReceiptLicense> findAll(int start, int end,
		OrderByComparator<AssetReceiptLicense> orderByComparator,
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

		List<AssetReceiptLicense> list = null;

		if (retrieveFromCache) {
			list = (List<AssetReceiptLicense>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSETRECEIPTLICENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECEIPTLICENSE;

				if (pagination) {
					sql = sql.concat(AssetReceiptLicenseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssetReceiptLicense>)QueryUtil.list(q,
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
	 * Removes all the asset receipt licenses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetReceiptLicense assetReceiptLicense : findAll()) {
			remove(assetReceiptLicense);
		}
	}

	/**
	 * Returns the number of asset receipt licenses.
	 *
	 * @return the number of asset receipt licenses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECEIPTLICENSE);

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
		return AssetReceiptLicenseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset receipt license persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssetReceiptLicenseImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_ASSETRECEIPTLICENSE = "SELECT assetReceiptLicense FROM AssetReceiptLicense assetReceiptLicense";
	private static final String _SQL_SELECT_ASSETRECEIPTLICENSE_WHERE_PKS_IN = "SELECT assetReceiptLicense FROM AssetReceiptLicense assetReceiptLicense WHERE assetReceiptLicenseId IN (";
	private static final String _SQL_SELECT_ASSETRECEIPTLICENSE_WHERE = "SELECT assetReceiptLicense FROM AssetReceiptLicense assetReceiptLicense WHERE ";
	private static final String _SQL_COUNT_ASSETRECEIPTLICENSE = "SELECT COUNT(assetReceiptLicense) FROM AssetReceiptLicense assetReceiptLicense";
	private static final String _SQL_COUNT_ASSETRECEIPTLICENSE_WHERE = "SELECT COUNT(assetReceiptLicense) FROM AssetReceiptLicense assetReceiptLicense WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetReceiptLicense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetReceiptLicense exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetReceiptLicense exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AssetReceiptLicensePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}