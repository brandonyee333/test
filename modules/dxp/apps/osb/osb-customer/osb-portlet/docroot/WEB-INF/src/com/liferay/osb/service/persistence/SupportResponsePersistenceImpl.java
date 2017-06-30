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

import com.liferay.osb.NoSuchSupportResponseException;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.impl.SupportResponseImpl;
import com.liferay.osb.model.impl.SupportResponseModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the support response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponsePersistence
 * @see SupportResponseUtil
 * @generated
 */
public class SupportResponsePersistenceImpl extends BasePersistenceImpl<SupportResponse>
	implements SupportResponsePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportResponseUtil} to access the support response persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportResponseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportResponseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLEVEL =
		new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySupportLevel",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL =
		new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportLevel",
			new String[] { Integer.class.getName() },
			SupportResponseModelImpl.SUPPORTLEVEL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLEVEL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLevel",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED,
			SupportResponseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the support response in the entity cache if it is enabled.
	 *
	 * @param supportResponse the support response
	 */
	public void cacheResult(SupportResponse supportResponse) {
		EntityCacheUtil.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey(),
			supportResponse);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportResponse.getName() }, supportResponse);

		supportResponse.resetOriginalValues();
	}

	/**
	 * Caches the support responses in the entity cache if it is enabled.
	 *
	 * @param supportResponses the support responses
	 */
	public void cacheResult(List<SupportResponse> supportResponses) {
		for (SupportResponse supportResponse : supportResponses) {
			if (EntityCacheUtil.getResult(
						SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
						SupportResponseImpl.class,
						supportResponse.getPrimaryKey()) == null) {
				cacheResult(supportResponse);
			}
			else {
				supportResponse.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support responses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportResponseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportResponseImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support response.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportResponse supportResponse) {
		EntityCacheUtil.removeResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(supportResponse);
	}

	@Override
	public void clearCache(List<SupportResponse> supportResponses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportResponse supportResponse : supportResponses) {
			EntityCacheUtil.removeResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
				SupportResponseImpl.class, supportResponse.getPrimaryKey());

			clearUniqueFindersCache(supportResponse);
		}
	}

	protected void cacheUniqueFindersCache(SupportResponse supportResponse) {
		if (supportResponse.isNew()) {
			Object[] args = new Object[] { supportResponse.getName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
				supportResponse);
		}
		else {
			SupportResponseModelImpl supportResponseModelImpl = (SupportResponseModelImpl)supportResponse;

			if ((supportResponseModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { supportResponse.getName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
					supportResponse);
			}
		}
	}

	protected void clearUniqueFindersCache(SupportResponse supportResponse) {
		SupportResponseModelImpl supportResponseModelImpl = (SupportResponseModelImpl)supportResponse;

		Object[] args = new Object[] { supportResponse.getName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

		if ((supportResponseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			args = new Object[] { supportResponseModelImpl.getOriginalName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support response with the primary key. Does not add the support response to the database.
	 *
	 * @param supportResponseId the primary key for the new support response
	 * @return the new support response
	 */
	public SupportResponse create(long supportResponseId) {
		SupportResponse supportResponse = new SupportResponseImpl();

		supportResponse.setNew(true);
		supportResponse.setPrimaryKey(supportResponseId);

		return supportResponse;
	}

	/**
	 * Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response that was removed
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse remove(long supportResponseId)
		throws NoSuchSupportResponseException, SystemException {
		return remove(Long.valueOf(supportResponseId));
	}

	/**
	 * Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response that was removed
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportResponse remove(Serializable primaryKey)
		throws NoSuchSupportResponseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportResponse supportResponse = (SupportResponse)session.get(SupportResponseImpl.class,
					primaryKey);

			if (supportResponse == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportResponseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportResponse);
		}
		catch (NoSuchSupportResponseException nsee) {
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
	protected SupportResponse removeImpl(SupportResponse supportResponse)
		throws SystemException {
		supportResponse = toUnwrappedModel(supportResponse);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportResponse);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportResponse);

		return supportResponse;
	}

	@Override
	public SupportResponse updateImpl(
		com.liferay.osb.model.SupportResponse supportResponse, boolean merge)
		throws SystemException {
		supportResponse = toUnwrappedModel(supportResponse);

		boolean isNew = supportResponse.isNew();

		SupportResponseModelImpl supportResponseModelImpl = (SupportResponseModelImpl)supportResponse;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportResponse, merge);

			supportResponse.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportResponseModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((supportResponseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(supportResponseModelImpl.getOriginalSupportLevel())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL,
					args);

				args = new Object[] {
						Integer.valueOf(supportResponseModelImpl.getSupportLevel())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL,
					args);
			}
		}

		EntityCacheUtil.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
			SupportResponseImpl.class, supportResponse.getPrimaryKey(),
			supportResponse);

		clearUniqueFindersCache(supportResponse);
		cacheUniqueFindersCache(supportResponse);

		return supportResponse;
	}

	protected SupportResponse toUnwrappedModel(SupportResponse supportResponse) {
		if (supportResponse instanceof SupportResponseImpl) {
			return supportResponse;
		}

		SupportResponseImpl supportResponseImpl = new SupportResponseImpl();

		supportResponseImpl.setNew(supportResponse.isNew());
		supportResponseImpl.setPrimaryKey(supportResponse.getPrimaryKey());

		supportResponseImpl.setSupportResponseId(supportResponse.getSupportResponseId());
		supportResponseImpl.setUserId(supportResponse.getUserId());
		supportResponseImpl.setUserName(supportResponse.getUserName());
		supportResponseImpl.setCreateDate(supportResponse.getCreateDate());
		supportResponseImpl.setModifiedDate(supportResponse.getModifiedDate());
		supportResponseImpl.setName(supportResponse.getName());
		supportResponseImpl.setSupportLevel(supportResponse.getSupportLevel());
		supportResponseImpl.setSeverity1Response(supportResponse.getSeverity1Response());
		supportResponseImpl.setSeverity1Resolution(supportResponse.getSeverity1Resolution());
		supportResponseImpl.setSeverity2Response(supportResponse.getSeverity2Response());
		supportResponseImpl.setSeverity2Resolution(supportResponse.getSeverity2Resolution());
		supportResponseImpl.setSeverity3Response(supportResponse.getSeverity3Response());
		supportResponseImpl.setSeverity3Resolution(supportResponse.getSeverity3Resolution());

		return supportResponseImpl;
	}

	/**
	 * Returns the support response with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response
	 * @throws com.liferay.portal.NoSuchModelException if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportResponse findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support response with the primary key or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse findByPrimaryKey(long supportResponseId)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = fetchByPrimaryKey(supportResponseId);

		if (supportResponse == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + supportResponseId);
			}

			throw new NoSuchSupportResponseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportResponseId);
		}

		return supportResponse;
	}

	/**
	 * Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support response
	 * @return the support response, or <code>null</code> if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportResponse fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportResponseId the primary key of the support response
	 * @return the support response, or <code>null</code> if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse fetchByPrimaryKey(long supportResponseId)
		throws SystemException {
		SupportResponse supportResponse = (SupportResponse)EntityCacheUtil.getResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
				SupportResponseImpl.class, supportResponseId);

		if (supportResponse == _nullSupportResponse) {
			return null;
		}

		if (supportResponse == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportResponse = (SupportResponse)session.get(SupportResponseImpl.class,
						Long.valueOf(supportResponseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportResponse != null) {
					cacheResult(supportResponse);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportResponseModelImpl.ENTITY_CACHE_ENABLED,
						SupportResponseImpl.class, supportResponseId,
						_nullSupportResponse);
				}

				closeSession(session);
			}
		}

		return supportResponse;
	}

	/**
	 * Returns the support response where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support response
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse findByName(String name)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = fetchByName(name);

		if (supportResponse == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSupportResponseException(msg.toString());
		}

		return supportResponse;
	}

	/**
	 * Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support response, or <code>null</code> if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse fetchByName(String name) throws SystemException {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching support response, or <code>null</code> if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse fetchByName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportResponse) {
			SupportResponse supportResponse = (SupportResponse)result;

			if (!Validator.equals(name, supportResponse.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			query.append(SupportResponseModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				List<SupportResponse> list = q.list();

				result = list;

				SupportResponse supportResponse = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					supportResponse = list.get(0);

					cacheResult(supportResponse);

					if ((supportResponse.getName() == null) ||
							!supportResponse.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportResponse);
					}
				}

				return supportResponse;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (SupportResponse)result;
			}
		}
	}

	/**
	 * Returns all the support responses where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @return the matching support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findBySupportLevel(int supportLevel)
		throws SystemException {
		return findBySupportLevel(supportLevel, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support responses where supportLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLevel the support level
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @return the range of matching support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findBySupportLevel(int supportLevel,
		int start, int end) throws SystemException {
		return findBySupportLevel(supportLevel, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support responses where supportLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLevel the support level
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findBySupportLevel(int supportLevel,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLEVEL;
			finderArgs = new Object[] { supportLevel };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLEVEL;
			finderArgs = new Object[] {
					supportLevel,
					
					start, end, orderByComparator
				};
		}

		List<SupportResponse> list = (List<SupportResponse>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportResponse supportResponse : list) {
				if ((supportLevel != supportResponse.getSupportLevel())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SupportResponseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLevel);

				list = (List<SupportResponse>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support response
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse findBySupportLevel_First(int supportLevel,
		OrderByComparator orderByComparator)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = fetchBySupportLevel_First(supportLevel,
				orderByComparator);

		if (supportResponse != null) {
			return supportResponse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLevel=");
		msg.append(supportLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportResponseException(msg.toString());
	}

	/**
	 * Returns the first support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support response, or <code>null</code> if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse fetchBySupportLevel_First(int supportLevel,
		OrderByComparator orderByComparator) throws SystemException {
		List<SupportResponse> list = findBySupportLevel(supportLevel, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support response
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse findBySupportLevel_Last(int supportLevel,
		OrderByComparator orderByComparator)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = fetchBySupportLevel_Last(supportLevel,
				orderByComparator);

		if (supportResponse != null) {
			return supportResponse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLevel=");
		msg.append(supportLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportResponseException(msg.toString());
	}

	/**
	 * Returns the last support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support response, or <code>null</code> if a matching support response could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse fetchBySupportLevel_Last(int supportLevel,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportLevel(supportLevel);

		List<SupportResponse> list = findBySupportLevel(supportLevel,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support responses before and after the current support response in the ordered set where supportLevel = &#63;.
	 *
	 * @param supportResponseId the primary key of the current support response
	 * @param supportLevel the support level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support response
	 * @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse[] findBySupportLevel_PrevAndNext(
		long supportResponseId, int supportLevel,
		OrderByComparator orderByComparator)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = findByPrimaryKey(supportResponseId);

		Session session = null;

		try {
			session = openSession();

			SupportResponse[] array = new SupportResponseImpl[3];

			array[0] = getBySupportLevel_PrevAndNext(session, supportResponse,
					supportLevel, orderByComparator, true);

			array[1] = supportResponse;

			array[2] = getBySupportLevel_PrevAndNext(session, supportResponse,
					supportLevel, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportResponse getBySupportLevel_PrevAndNext(Session session,
		SupportResponse supportResponse, int supportLevel,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTRESPONSE_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

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
			query.append(SupportResponseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportLevel);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportResponse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportResponse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the support responses.
	 *
	 * @return the support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support responses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @return the range of support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support responses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support responses
	 * @param end the upper bound of the range of support responses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support responses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportResponse> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SupportResponse> list = (List<SupportResponse>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTRESPONSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTRESPONSE.concat(SupportResponseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportResponse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportResponse>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the support response where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support response that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SupportResponse removeByName(String name)
		throws NoSuchSupportResponseException, SystemException {
		SupportResponse supportResponse = findByName(name);

		return remove(supportResponse);
	}

	/**
	 * Removes all the support responses where supportLevel = &#63; from the database.
	 *
	 * @param supportLevel the support level
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportLevel(int supportLevel)
		throws SystemException {
		for (SupportResponse supportResponse : findBySupportLevel(supportLevel)) {
			remove(supportResponse);
		}
	}

	/**
	 * Removes all the support responses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportResponse supportResponse : findAll()) {
			remove(supportResponse);
		}
	}

	/**
	 * Returns the number of support responses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support responses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTRESPONSE_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support responses where supportLevel = &#63;.
	 *
	 * @param supportLevel the support level
	 * @return the number of matching support responses
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportLevel(int supportLevel) throws SystemException {
		Object[] finderArgs = new Object[] { supportLevel };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTRESPONSE_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLevel);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTLEVEL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support responses.
	 *
	 * @return the number of support responses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTRESPONSE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the support response persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportResponse")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportResponse>> listenersList = new ArrayList<ModelListener<SupportResponse>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportResponse>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SupportResponseImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SUPPORTRESPONSE = "SELECT supportResponse FROM SupportResponse supportResponse";
	private static final String _SQL_SELECT_SUPPORTRESPONSE_WHERE = "SELECT supportResponse FROM SupportResponse supportResponse WHERE ";
	private static final String _SQL_COUNT_SUPPORTRESPONSE = "SELECT COUNT(supportResponse) FROM SupportResponse supportResponse";
	private static final String _SQL_COUNT_SUPPORTRESPONSE_WHERE = "SELECT COUNT(supportResponse) FROM SupportResponse supportResponse WHERE ";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportResponse.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportResponse.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportResponse.name IS NULL OR supportResponse.name = ?)";
	private static final String _FINDER_COLUMN_SUPPORTLEVEL_SUPPORTLEVEL_2 = "supportResponse.supportLevel = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportResponse.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportResponse exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportResponse exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportResponsePersistenceImpl.class);
	private static SupportResponse _nullSupportResponse = new SupportResponseImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportResponse> toCacheModel() {
				return _nullSupportResponseCacheModel;
			}
		};

	private static CacheModel<SupportResponse> _nullSupportResponseCacheModel = new CacheModel<SupportResponse>() {
			public SupportResponse toEntityModel() {
				return _nullSupportResponse;
			}
		};
}