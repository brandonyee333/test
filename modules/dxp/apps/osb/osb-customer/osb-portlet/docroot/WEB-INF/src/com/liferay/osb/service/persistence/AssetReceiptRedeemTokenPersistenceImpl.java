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

import com.liferay.osb.NoSuchAssetReceiptRedeemTokenException;
import com.liferay.osb.model.AssetReceiptRedeemToken;
import com.liferay.osb.model.impl.AssetReceiptRedeemTokenImpl;
import com.liferay.osb.model.impl.AssetReceiptRedeemTokenModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the asset receipt redeem token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptRedeemTokenPersistence
 * @see AssetReceiptRedeemTokenUtil
 * @generated
 */
public class AssetReceiptRedeemTokenPersistenceImpl extends BasePersistenceImpl<AssetReceiptRedeemToken>
	implements AssetReceiptRedeemTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetReceiptRedeemTokenUtil} to access the asset receipt redeem token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetReceiptRedeemTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_TOKEN = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByToken", new String[] { String.class.getName() },
			AssetReceiptRedeemTokenModelImpl.TOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TOKEN = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByToken",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_REA_RD = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByREA_RD",
			new String[] { String.class.getName(), Date.class.getName() },
			AssetReceiptRedeemTokenModelImpl.REDEEMEMAILADDRESS_COLUMN_BITMASK |
			AssetReceiptRedeemTokenModelImpl.REDEEMDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REA_RD = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByREA_RD",
			new String[] { String.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset receipt redeem token in the entity cache if it is enabled.
	 *
	 * @param assetReceiptRedeemToken the asset receipt redeem token
	 */
	public void cacheResult(AssetReceiptRedeemToken assetReceiptRedeemToken) {
		EntityCacheUtil.putResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class,
			assetReceiptRedeemToken.getPrimaryKey(), assetReceiptRedeemToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TOKEN,
			new Object[] { assetReceiptRedeemToken.getToken() },
			assetReceiptRedeemToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REA_RD,
			new Object[] {
				assetReceiptRedeemToken.getRedeemEmailAddress(),
				
			assetReceiptRedeemToken.getRedeemDate()
			}, assetReceiptRedeemToken);

		assetReceiptRedeemToken.resetOriginalValues();
	}

	/**
	 * Caches the asset receipt redeem tokens in the entity cache if it is enabled.
	 *
	 * @param assetReceiptRedeemTokens the asset receipt redeem tokens
	 */
	public void cacheResult(
		List<AssetReceiptRedeemToken> assetReceiptRedeemTokens) {
		for (AssetReceiptRedeemToken assetReceiptRedeemToken : assetReceiptRedeemTokens) {
			if (EntityCacheUtil.getResult(
						AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptRedeemTokenImpl.class,
						assetReceiptRedeemToken.getPrimaryKey()) == null) {
				cacheResult(assetReceiptRedeemToken);
			}
			else {
				assetReceiptRedeemToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset receipt redeem tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetReceiptRedeemTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetReceiptRedeemTokenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset receipt redeem token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetReceiptRedeemToken assetReceiptRedeemToken) {
		EntityCacheUtil.removeResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class,
			assetReceiptRedeemToken.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetReceiptRedeemToken);
	}

	@Override
	public void clearCache(
		List<AssetReceiptRedeemToken> assetReceiptRedeemTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetReceiptRedeemToken assetReceiptRedeemToken : assetReceiptRedeemTokens) {
			EntityCacheUtil.removeResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptRedeemTokenImpl.class,
				assetReceiptRedeemToken.getPrimaryKey());

			clearUniqueFindersCache(assetReceiptRedeemToken);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetReceiptRedeemToken assetReceiptRedeemToken) {
		if (assetReceiptRedeemToken.isNew()) {
			Object[] args = new Object[] { assetReceiptRedeemToken.getToken() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TOKEN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TOKEN, args,
				assetReceiptRedeemToken);

			args = new Object[] {
					assetReceiptRedeemToken.getRedeemEmailAddress(),
					
					assetReceiptRedeemToken.getRedeemDate()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REA_RD, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REA_RD, args,
				assetReceiptRedeemToken);
		}
		else {
			AssetReceiptRedeemTokenModelImpl assetReceiptRedeemTokenModelImpl = (AssetReceiptRedeemTokenModelImpl)assetReceiptRedeemToken;

			if ((assetReceiptRedeemTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { assetReceiptRedeemToken.getToken() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TOKEN, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TOKEN, args,
					assetReceiptRedeemToken);
			}

			if ((assetReceiptRedeemTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_REA_RD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetReceiptRedeemToken.getRedeemEmailAddress(),
						
						assetReceiptRedeemToken.getRedeemDate()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REA_RD, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REA_RD, args,
					assetReceiptRedeemToken);
			}
		}
	}

	protected void clearUniqueFindersCache(
		AssetReceiptRedeemToken assetReceiptRedeemToken) {
		AssetReceiptRedeemTokenModelImpl assetReceiptRedeemTokenModelImpl = (AssetReceiptRedeemTokenModelImpl)assetReceiptRedeemToken;

		Object[] args = new Object[] { assetReceiptRedeemToken.getToken() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TOKEN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TOKEN, args);

		if ((assetReceiptRedeemTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TOKEN.getColumnBitmask()) != 0) {
			args = new Object[] {
					assetReceiptRedeemTokenModelImpl.getOriginalToken()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TOKEN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TOKEN, args);
		}

		args = new Object[] {
				assetReceiptRedeemToken.getRedeemEmailAddress(),
				
				assetReceiptRedeemToken.getRedeemDate()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REA_RD, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REA_RD, args);

		if ((assetReceiptRedeemTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_REA_RD.getColumnBitmask()) != 0) {
			args = new Object[] {
					assetReceiptRedeemTokenModelImpl.getOriginalRedeemEmailAddress(),
					
					assetReceiptRedeemTokenModelImpl.getOriginalRedeemDate()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REA_RD, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REA_RD, args);
		}
	}

	/**
	 * Creates a new asset receipt redeem token with the primary key. Does not add the asset receipt redeem token to the database.
	 *
	 * @param AssetReceiptRedeemTokenId the primary key for the new asset receipt redeem token
	 * @return the new asset receipt redeem token
	 */
	public AssetReceiptRedeemToken create(long AssetReceiptRedeemTokenId) {
		AssetReceiptRedeemToken assetReceiptRedeemToken = new AssetReceiptRedeemTokenImpl();

		assetReceiptRedeemToken.setNew(true);
		assetReceiptRedeemToken.setPrimaryKey(AssetReceiptRedeemTokenId);

		return assetReceiptRedeemToken;
	}

	/**
	 * Removes the asset receipt redeem token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken remove(long AssetReceiptRedeemTokenId)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		return remove(Long.valueOf(AssetReceiptRedeemTokenId));
	}

	/**
	 * Removes the asset receipt redeem token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token that was removed
	 * @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptRedeemToken remove(Serializable primaryKey)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetReceiptRedeemToken assetReceiptRedeemToken = (AssetReceiptRedeemToken)session.get(AssetReceiptRedeemTokenImpl.class,
					primaryKey);

			if (assetReceiptRedeemToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetReceiptRedeemTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetReceiptRedeemToken);
		}
		catch (NoSuchAssetReceiptRedeemTokenException nsee) {
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
	protected AssetReceiptRedeemToken removeImpl(
		AssetReceiptRedeemToken assetReceiptRedeemToken)
		throws SystemException {
		assetReceiptRedeemToken = toUnwrappedModel(assetReceiptRedeemToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetReceiptRedeemToken);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetReceiptRedeemToken);

		return assetReceiptRedeemToken;
	}

	@Override
	public AssetReceiptRedeemToken updateImpl(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken,
		boolean merge) throws SystemException {
		assetReceiptRedeemToken = toUnwrappedModel(assetReceiptRedeemToken);

		boolean isNew = assetReceiptRedeemToken.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetReceiptRedeemToken, merge);

			assetReceiptRedeemToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetReceiptRedeemTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
			AssetReceiptRedeemTokenImpl.class,
			assetReceiptRedeemToken.getPrimaryKey(), assetReceiptRedeemToken);

		clearUniqueFindersCache(assetReceiptRedeemToken);
		cacheUniqueFindersCache(assetReceiptRedeemToken);

		return assetReceiptRedeemToken;
	}

	protected AssetReceiptRedeemToken toUnwrappedModel(
		AssetReceiptRedeemToken assetReceiptRedeemToken) {
		if (assetReceiptRedeemToken instanceof AssetReceiptRedeemTokenImpl) {
			return assetReceiptRedeemToken;
		}

		AssetReceiptRedeemTokenImpl assetReceiptRedeemTokenImpl = new AssetReceiptRedeemTokenImpl();

		assetReceiptRedeemTokenImpl.setNew(assetReceiptRedeemToken.isNew());
		assetReceiptRedeemTokenImpl.setPrimaryKey(assetReceiptRedeemToken.getPrimaryKey());

		assetReceiptRedeemTokenImpl.setAssetReceiptRedeemTokenId(assetReceiptRedeemToken.getAssetReceiptRedeemTokenId());
		assetReceiptRedeemTokenImpl.setUserId(assetReceiptRedeemToken.getUserId());
		assetReceiptRedeemTokenImpl.setUserName(assetReceiptRedeemToken.getUserName());
		assetReceiptRedeemTokenImpl.setCreateDate(assetReceiptRedeemToken.getCreateDate());
		assetReceiptRedeemTokenImpl.setClassNameId(assetReceiptRedeemToken.getClassNameId());
		assetReceiptRedeemTokenImpl.setClassPK(assetReceiptRedeemToken.getClassPK());
		assetReceiptRedeemTokenImpl.setRedeemEmailAddress(assetReceiptRedeemToken.getRedeemEmailAddress());
		assetReceiptRedeemTokenImpl.setRedeemDate(assetReceiptRedeemToken.getRedeemDate());
		assetReceiptRedeemTokenImpl.setToken(assetReceiptRedeemToken.getToken());

		return assetReceiptRedeemTokenImpl;
	}

	/**
	 * Returns the asset receipt redeem token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token
	 * @throws com.liferay.portal.NoSuchModelException if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptRedeemToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt redeem token with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	 *
	 * @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token
	 * @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken findByPrimaryKey(
		long AssetReceiptRedeemTokenId)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = fetchByPrimaryKey(AssetReceiptRedeemTokenId);

		if (assetReceiptRedeemToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					AssetReceiptRedeemTokenId);
			}

			throw new NoSuchAssetReceiptRedeemTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				AssetReceiptRedeemTokenId);
		}

		return assetReceiptRedeemToken;
	}

	/**
	 * Returns the asset receipt redeem token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token, or <code>null</code> if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetReceiptRedeemToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset receipt redeem token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	 * @return the asset receipt redeem token, or <code>null</code> if a asset receipt redeem token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken fetchByPrimaryKey(
		long AssetReceiptRedeemTokenId) throws SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = (AssetReceiptRedeemToken)EntityCacheUtil.getResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
				AssetReceiptRedeemTokenImpl.class, AssetReceiptRedeemTokenId);

		if (assetReceiptRedeemToken == _nullAssetReceiptRedeemToken) {
			return null;
		}

		if (assetReceiptRedeemToken == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetReceiptRedeemToken = (AssetReceiptRedeemToken)session.get(AssetReceiptRedeemTokenImpl.class,
						Long.valueOf(AssetReceiptRedeemTokenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetReceiptRedeemToken != null) {
					cacheResult(assetReceiptRedeemToken);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetReceiptRedeemTokenModelImpl.ENTITY_CACHE_ENABLED,
						AssetReceiptRedeemTokenImpl.class,
						AssetReceiptRedeemTokenId, _nullAssetReceiptRedeemToken);
				}

				closeSession(session);
			}
		}

		return assetReceiptRedeemToken;
	}

	/**
	 * Returns the asset receipt redeem token where token = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	 *
	 * @param token the token
	 * @return the matching asset receipt redeem token
	 * @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken findByToken(String token)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = fetchByToken(token);

		if (assetReceiptRedeemToken == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("token=");
			msg.append(token);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetReceiptRedeemTokenException(msg.toString());
		}

		return assetReceiptRedeemToken;
	}

	/**
	 * Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param token the token
	 * @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken fetchByToken(String token)
		throws SystemException {
		return fetchByToken(token, true);
	}

	/**
	 * Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param token the token
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken fetchByToken(String token,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { token };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TOKEN,
					finderArgs, this);
		}

		if (result instanceof AssetReceiptRedeemToken) {
			AssetReceiptRedeemToken assetReceiptRedeemToken = (AssetReceiptRedeemToken)result;

			if (!Validator.equals(token, assetReceiptRedeemToken.getToken())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_ASSETRECEIPTREDEEMTOKEN_WHERE);

			if (token == null) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_1);
			}
			else {
				if (token.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
				}
				else {
					query.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (token != null) {
					qPos.add(token);
				}

				List<AssetReceiptRedeemToken> list = q.list();

				result = list;

				AssetReceiptRedeemToken assetReceiptRedeemToken = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TOKEN,
						finderArgs, list);
				}
				else {
					assetReceiptRedeemToken = list.get(0);

					cacheResult(assetReceiptRedeemToken);

					if ((assetReceiptRedeemToken.getToken() == null) ||
							!assetReceiptRedeemToken.getToken().equals(token)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TOKEN,
							finderArgs, assetReceiptRedeemToken);
					}
				}

				return assetReceiptRedeemToken;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TOKEN,
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
				return (AssetReceiptRedeemToken)result;
			}
		}
	}

	/**
	 * Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	 *
	 * @param redeemEmailAddress the redeem email address
	 * @param redeemDate the redeem date
	 * @return the matching asset receipt redeem token
	 * @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken findByREA_RD(String redeemEmailAddress,
		Date redeemDate)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = fetchByREA_RD(redeemEmailAddress,
				redeemDate);

		if (assetReceiptRedeemToken == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("redeemEmailAddress=");
			msg.append(redeemEmailAddress);

			msg.append(", redeemDate=");
			msg.append(redeemDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetReceiptRedeemTokenException(msg.toString());
		}

		return assetReceiptRedeemToken;
	}

	/**
	 * Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param redeemEmailAddress the redeem email address
	 * @param redeemDate the redeem date
	 * @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken fetchByREA_RD(String redeemEmailAddress,
		Date redeemDate) throws SystemException {
		return fetchByREA_RD(redeemEmailAddress, redeemDate, true);
	}

	/**
	 * Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param redeemEmailAddress the redeem email address
	 * @param redeemDate the redeem date
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken fetchByREA_RD(String redeemEmailAddress,
		Date redeemDate, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { redeemEmailAddress, redeemDate };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_REA_RD,
					finderArgs, this);
		}

		if (result instanceof AssetReceiptRedeemToken) {
			AssetReceiptRedeemToken assetReceiptRedeemToken = (AssetReceiptRedeemToken)result;

			if (!Validator.equals(redeemEmailAddress,
						assetReceiptRedeemToken.getRedeemEmailAddress()) ||
					!Validator.equals(redeemDate,
						assetReceiptRedeemToken.getRedeemDate())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ASSETRECEIPTREDEEMTOKEN_WHERE);

			if (redeemEmailAddress == null) {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_1);
			}
			else {
				if (redeemEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_2);
				}
			}

			if (redeemDate == null) {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (redeemEmailAddress != null) {
					qPos.add(redeemEmailAddress);
				}

				if (redeemDate != null) {
					qPos.add(CalendarUtil.getTimestamp(redeemDate));
				}

				List<AssetReceiptRedeemToken> list = q.list();

				result = list;

				AssetReceiptRedeemToken assetReceiptRedeemToken = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REA_RD,
						finderArgs, list);
				}
				else {
					assetReceiptRedeemToken = list.get(0);

					cacheResult(assetReceiptRedeemToken);

					if ((assetReceiptRedeemToken.getRedeemEmailAddress() == null) ||
							!assetReceiptRedeemToken.getRedeemEmailAddress()
														.equals(redeemEmailAddress) ||
							(assetReceiptRedeemToken.getRedeemDate() == null) ||
							!assetReceiptRedeemToken.getRedeemDate()
														.equals(redeemDate)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REA_RD,
							finderArgs, assetReceiptRedeemToken);
					}
				}

				return assetReceiptRedeemToken;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REA_RD,
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
				return (AssetReceiptRedeemToken)result;
			}
		}
	}

	/**
	 * Returns all the asset receipt redeem tokens.
	 *
	 * @return the asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptRedeemToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset receipt redeem tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt redeem tokens
	 * @param end the upper bound of the range of asset receipt redeem tokens (not inclusive)
	 * @return the range of asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptRedeemToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset receipt redeem tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset receipt redeem tokens
	 * @param end the upper bound of the range of asset receipt redeem tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetReceiptRedeemToken> findAll(int start, int end,
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

		List<AssetReceiptRedeemToken> list = (List<AssetReceiptRedeemToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETRECEIPTREDEEMTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETRECEIPTREDEEMTOKEN;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetReceiptRedeemToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetReceiptRedeemToken>)QueryUtil.list(q,
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
	 * Removes the asset receipt redeem token where token = &#63; from the database.
	 *
	 * @param token the token
	 * @return the asset receipt redeem token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken removeByToken(String token)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = findByToken(token);

		return remove(assetReceiptRedeemToken);
	}

	/**
	 * Removes the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; from the database.
	 *
	 * @param redeemEmailAddress the redeem email address
	 * @param redeemDate the redeem date
	 * @return the asset receipt redeem token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AssetReceiptRedeemToken removeByREA_RD(String redeemEmailAddress,
		Date redeemDate)
		throws NoSuchAssetReceiptRedeemTokenException, SystemException {
		AssetReceiptRedeemToken assetReceiptRedeemToken = findByREA_RD(redeemEmailAddress,
				redeemDate);

		return remove(assetReceiptRedeemToken);
	}

	/**
	 * Removes all the asset receipt redeem tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetReceiptRedeemToken assetReceiptRedeemToken : findAll()) {
			remove(assetReceiptRedeemToken);
		}
	}

	/**
	 * Returns the number of asset receipt redeem tokens where token = &#63;.
	 *
	 * @param token the token
	 * @return the number of matching asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByToken(String token) throws SystemException {
		Object[] finderArgs = new Object[] { token };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TOKEN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETRECEIPTREDEEMTOKEN_WHERE);

			if (token == null) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_1);
			}
			else {
				if (token.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
				}
				else {
					query.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (token != null) {
					qPos.add(token);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TOKEN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt redeem tokens where redeemEmailAddress = &#63; and redeemDate = &#63;.
	 *
	 * @param redeemEmailAddress the redeem email address
	 * @param redeemDate the redeem date
	 * @return the number of matching asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByREA_RD(String redeemEmailAddress, Date redeemDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { redeemEmailAddress, redeemDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REA_RD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETRECEIPTREDEEMTOKEN_WHERE);

			if (redeemEmailAddress == null) {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_1);
			}
			else {
				if (redeemEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_2);
				}
			}

			if (redeemDate == null) {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_REA_RD_REDEEMDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (redeemEmailAddress != null) {
					qPos.add(redeemEmailAddress);
				}

				if (redeemDate != null) {
					qPos.add(CalendarUtil.getTimestamp(redeemDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REA_RD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset receipt redeem tokens.
	 *
	 * @return the number of asset receipt redeem tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETRECEIPTREDEEMTOKEN);

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
	 * Initializes the asset receipt redeem token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetReceiptRedeemToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetReceiptRedeemToken>> listenersList = new ArrayList<ModelListener<AssetReceiptRedeemToken>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetReceiptRedeemToken>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetReceiptRedeemTokenImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETRECEIPTREDEEMTOKEN = "SELECT assetReceiptRedeemToken FROM AssetReceiptRedeemToken assetReceiptRedeemToken";
	private static final String _SQL_SELECT_ASSETRECEIPTREDEEMTOKEN_WHERE = "SELECT assetReceiptRedeemToken FROM AssetReceiptRedeemToken assetReceiptRedeemToken WHERE ";
	private static final String _SQL_COUNT_ASSETRECEIPTREDEEMTOKEN = "SELECT COUNT(assetReceiptRedeemToken) FROM AssetReceiptRedeemToken assetReceiptRedeemToken";
	private static final String _SQL_COUNT_ASSETRECEIPTREDEEMTOKEN_WHERE = "SELECT COUNT(assetReceiptRedeemToken) FROM AssetReceiptRedeemToken assetReceiptRedeemToken WHERE ";
	private static final String _FINDER_COLUMN_TOKEN_TOKEN_1 = "assetReceiptRedeemToken.token IS NULL";
	private static final String _FINDER_COLUMN_TOKEN_TOKEN_2 = "assetReceiptRedeemToken.token = ?";
	private static final String _FINDER_COLUMN_TOKEN_TOKEN_3 = "(assetReceiptRedeemToken.token IS NULL OR assetReceiptRedeemToken.token = ?)";
	private static final String _FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_1 = "assetReceiptRedeemToken.redeemEmailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_2 = "assetReceiptRedeemToken.redeemEmailAddress = ? AND ";
	private static final String _FINDER_COLUMN_REA_RD_REDEEMEMAILADDRESS_3 = "(assetReceiptRedeemToken.redeemEmailAddress IS NULL OR assetReceiptRedeemToken.redeemEmailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_REA_RD_REDEEMDATE_1 = "assetReceiptRedeemToken.redeemDate IS NULL";
	private static final String _FINDER_COLUMN_REA_RD_REDEEMDATE_2 = "assetReceiptRedeemToken.redeemDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetReceiptRedeemToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetReceiptRedeemToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetReceiptRedeemToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetReceiptRedeemTokenPersistenceImpl.class);
	private static AssetReceiptRedeemToken _nullAssetReceiptRedeemToken = new AssetReceiptRedeemTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetReceiptRedeemToken> toCacheModel() {
				return _nullAssetReceiptRedeemTokenCacheModel;
			}
		};

	private static CacheModel<AssetReceiptRedeemToken> _nullAssetReceiptRedeemTokenCacheModel =
		new CacheModel<AssetReceiptRedeemToken>() {
			public AssetReceiptRedeemToken toEntityModel() {
				return _nullAssetReceiptRedeemToken;
			}
		};
}