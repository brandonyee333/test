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

import com.liferay.osb.NoSuchExternalIdMapperException;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.impl.ExternalIdMapperImpl;
import com.liferay.osb.model.impl.ExternalIdMapperModelImpl;

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
 * The persistence implementation for the external ID mapper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperPersistence
 * @see ExternalIdMapperUtil
 * @generated
 */
public class ExternalIdMapperPersistenceImpl extends BasePersistenceImpl<ExternalIdMapper>
	implements ExternalIdMapperPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExternalIdMapperUtil} to access the external ID mapper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExternalIdMapperImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.CLASSPK_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T_EI = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI =
		new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.TYPE_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.EXTERNALID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T_EI = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the external ID mapper in the entity cache if it is enabled.
	 *
	 * @param externalIdMapper the external ID mapper
	 */
	public void cacheResult(ExternalIdMapper externalIdMapper) {
		EntityCacheUtil.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey(),
			externalIdMapper);

		externalIdMapper.resetOriginalValues();
	}

	/**
	 * Caches the external ID mappers in the entity cache if it is enabled.
	 *
	 * @param externalIdMappers the external ID mappers
	 */
	public void cacheResult(List<ExternalIdMapper> externalIdMappers) {
		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			if (EntityCacheUtil.getResult(
						ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
						ExternalIdMapperImpl.class,
						externalIdMapper.getPrimaryKey()) == null) {
				cacheResult(externalIdMapper);
			}
			else {
				externalIdMapper.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all external ID mappers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExternalIdMapperImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExternalIdMapperImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the external ID mapper.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExternalIdMapper externalIdMapper) {
		EntityCacheUtil.removeResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExternalIdMapper> externalIdMappers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			EntityCacheUtil.removeResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
				ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey());
		}
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	public ExternalIdMapper create(long externalIdMapperId) {
		ExternalIdMapper externalIdMapper = new ExternalIdMapperImpl();

		externalIdMapper.setNew(true);
		externalIdMapper.setPrimaryKey(externalIdMapperId);

		return externalIdMapper;
	}

	/**
	 * Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper remove(long externalIdMapperId)
		throws NoSuchExternalIdMapperException, SystemException {
		return remove(Long.valueOf(externalIdMapperId));
	}

	/**
	 * Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExternalIdMapper remove(Serializable primaryKey)
		throws NoSuchExternalIdMapperException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper externalIdMapper = (ExternalIdMapper)session.get(ExternalIdMapperImpl.class,
					primaryKey);

			if (externalIdMapper == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExternalIdMapperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(externalIdMapper);
		}
		catch (NoSuchExternalIdMapperException nsee) {
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
	protected ExternalIdMapper removeImpl(ExternalIdMapper externalIdMapper)
		throws SystemException {
		externalIdMapper = toUnwrappedModel(externalIdMapper);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, externalIdMapper);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(externalIdMapper);

		return externalIdMapper;
	}

	@Override
	public ExternalIdMapper updateImpl(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper, boolean merge)
		throws SystemException {
		externalIdMapper = toUnwrappedModel(externalIdMapper);

		boolean isNew = externalIdMapper.isNew();

		ExternalIdMapperModelImpl externalIdMapperModelImpl = (ExternalIdMapperModelImpl)externalIdMapper;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, externalIdMapper, merge);

			externalIdMapper.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ExternalIdMapperModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getOriginalClassNameId()),
						Long.valueOf(externalIdMapperModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getClassNameId()),
						Long.valueOf(externalIdMapperModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getOriginalClassNameId()),
						Long.valueOf(externalIdMapperModelImpl.getOriginalClassPK()),
						Integer.valueOf(externalIdMapperModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getClassNameId()),
						Long.valueOf(externalIdMapperModelImpl.getClassPK()),
						Integer.valueOf(externalIdMapperModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getOriginalClassNameId()),
						Integer.valueOf(externalIdMapperModelImpl.getOriginalType()),
						
						externalIdMapperModelImpl.getOriginalExternalId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T_EI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI,
					args);

				args = new Object[] {
						Long.valueOf(externalIdMapperModelImpl.getClassNameId()),
						Integer.valueOf(externalIdMapperModelImpl.getType()),
						
						externalIdMapperModelImpl.getExternalId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T_EI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI,
					args);
			}
		}

		EntityCacheUtil.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey(),
			externalIdMapper);

		return externalIdMapper;
	}

	protected ExternalIdMapper toUnwrappedModel(
		ExternalIdMapper externalIdMapper) {
		if (externalIdMapper instanceof ExternalIdMapperImpl) {
			return externalIdMapper;
		}

		ExternalIdMapperImpl externalIdMapperImpl = new ExternalIdMapperImpl();

		externalIdMapperImpl.setNew(externalIdMapper.isNew());
		externalIdMapperImpl.setPrimaryKey(externalIdMapper.getPrimaryKey());

		externalIdMapperImpl.setExternalIdMapperId(externalIdMapper.getExternalIdMapperId());
		externalIdMapperImpl.setCreateDate(externalIdMapper.getCreateDate());
		externalIdMapperImpl.setModifiedDate(externalIdMapper.getModifiedDate());
		externalIdMapperImpl.setClassNameId(externalIdMapper.getClassNameId());
		externalIdMapperImpl.setClassPK(externalIdMapper.getClassPK());
		externalIdMapperImpl.setType(externalIdMapper.getType());
		externalIdMapperImpl.setExternalId(externalIdMapper.getExternalId());

		return externalIdMapperImpl;
	}

	/**
	 * Returns the external ID mapper with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws com.liferay.portal.NoSuchModelException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExternalIdMapper findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the external ID mapper with the primary key or throws a {@link com.liferay.osb.NoSuchExternalIdMapperException} if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByPrimaryKey(long externalIdMapperId)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByPrimaryKey(externalIdMapperId);

		if (externalIdMapper == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					externalIdMapperId);
			}

			throw new NoSuchExternalIdMapperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				externalIdMapperId);
		}

		return externalIdMapper;
	}

	/**
	 * Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ExternalIdMapper fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByPrimaryKey(long externalIdMapperId)
		throws SystemException {
		ExternalIdMapper externalIdMapper = (ExternalIdMapper)EntityCacheUtil.getResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
				ExternalIdMapperImpl.class, externalIdMapperId);

		if (externalIdMapper == _nullExternalIdMapper) {
			return null;
		}

		if (externalIdMapper == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				externalIdMapper = (ExternalIdMapper)session.get(ExternalIdMapperImpl.class,
						Long.valueOf(externalIdMapperId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (externalIdMapper != null) {
					cacheResult(externalIdMapper);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
						ExternalIdMapperImpl.class, externalIdMapperId,
						_nullExternalIdMapper);
				}

				closeSession(session);
			}
		}

		return externalIdMapper;
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

		List<ExternalIdMapper> list = (List<ExternalIdMapper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExternalIdMapper externalIdMapper : list) {
				if ((classNameId != externalIdMapper.getClassNameId()) ||
						(classPK != externalIdMapper.getClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<ExternalIdMapper>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<ExternalIdMapper> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<ExternalIdMapper> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper[] findByC_C_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_C_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_C_PrevAndNext(session, externalIdMapper,
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

	protected ExternalIdMapper getByC_C_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<ExternalIdMapper> list = (List<ExternalIdMapper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExternalIdMapper externalIdMapper : list) {
				if ((classNameId != externalIdMapper.getClassNameId()) ||
						(classPK != externalIdMapper.getClassPK()) ||
						(type != externalIdMapper.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<ExternalIdMapper>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_C_T_First(classNameId,
				classPK, type, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<ExternalIdMapper> list = findByC_C_T(classNameId, classPK, type,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_C_T_Last(classNameId,
				classPK, type, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_T(classNameId, classPK, type);

		List<ExternalIdMapper> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper[] findByC_C_T_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_C_T_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExternalIdMapper getByC_C_T_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId) throws SystemException {
		return findByC_T_EI(classNameId, type, externalId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId, int start, int end) throws SystemException {
		return findByC_T_EI(classNameId, type, externalId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI;
			finderArgs = new Object[] { classNameId, type, externalId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T_EI;
			finderArgs = new Object[] {
					classNameId, type, externalId,
					
					start, end, orderByComparator
				};
		}

		List<ExternalIdMapper> list = (List<ExternalIdMapper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ExternalIdMapper externalIdMapper : list) {
				if ((classNameId != externalIdMapper.getClassNameId()) ||
						(type != externalIdMapper.getType()) ||
						!Validator.equals(externalId,
							externalIdMapper.getExternalId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

			if (externalId == null) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
			}
			else {
				if (externalId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(type);

				if (externalId != null) {
					qPos.add(externalId);
				}

				list = (List<ExternalIdMapper>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_T_EI_First(long classNameId, int type,
		String externalId, OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_T_EI_First(classNameId,
				type, externalId, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", externalId=");
		msg.append(externalId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_T_EI_First(long classNameId, int type,
		String externalId, OrderByComparator orderByComparator)
		throws SystemException {
		List<ExternalIdMapper> list = findByC_T_EI(classNameId, type,
				externalId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper findByC_T_EI_Last(long classNameId, int type,
		String externalId, OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = fetchByC_T_EI_Last(classNameId,
				type, externalId, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", externalId=");
		msg.append(externalId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper fetchByC_T_EI_Last(long classNameId, int type,
		String externalId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_T_EI(classNameId, type, externalId);

		List<ExternalIdMapper> list = findByC_T_EI(classNameId, type,
				externalId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ExternalIdMapper[] findByC_T_EI_PrevAndNext(
		long externalIdMapperId, long classNameId, int type, String externalId,
		OrderByComparator orderByComparator)
		throws NoSuchExternalIdMapperException, SystemException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_T_EI_PrevAndNext(session, externalIdMapper,
					classNameId, type, externalId, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_T_EI_PrevAndNext(session, externalIdMapper,
					classNameId, type, externalId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExternalIdMapper getByC_T_EI_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, int type,
		String externalId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

		query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

		if (externalId == null) {
			query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
		}
		else {
			if (externalId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
			}
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(type);

		if (externalId != null) {
			qPos.add(externalId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the external ID mappers.
	 *
	 * @return the external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExternalIdMapper> findAll(int start, int end,
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

		List<ExternalIdMapper> list = (List<ExternalIdMapper>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXTERNALIDMAPPER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXTERNALIDMAPPER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
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
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (ExternalIdMapper externalIdMapper : findByC_C(classNameId, classPK)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		for (ExternalIdMapper externalIdMapper : findByC_C_T(classNameId,
				classPK, type)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_T_EI(long classNameId, int type, String externalId)
		throws SystemException {
		for (ExternalIdMapper externalIdMapper : findByC_T_EI(classNameId,
				type, externalId)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Removes all the external ID mappers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ExternalIdMapper externalIdMapper : findAll()) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the number of matching external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_T_EI(long classNameId, int type, String externalId)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, type, externalId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_T_EI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

			if (externalId == null) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
			}
			else {
				if (externalId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(type);

				if (externalId != null) {
					qPos.add(externalId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_T_EI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXTERNALIDMAPPER);

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
	 * Initializes the external ID mapper persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.ExternalIdMapper")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ExternalIdMapper>> listenersList = new ArrayList<ModelListener<ExternalIdMapper>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<ExternalIdMapper>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ExternalIdMapperImpl.class.getName());
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
	private static final String _SQL_SELECT_EXTERNALIDMAPPER = "SELECT externalIdMapper FROM ExternalIdMapper externalIdMapper";
	private static final String _SQL_SELECT_EXTERNALIDMAPPER_WHERE = "SELECT externalIdMapper FROM ExternalIdMapper externalIdMapper WHERE ";
	private static final String _SQL_COUNT_EXTERNALIDMAPPER = "SELECT COUNT(externalIdMapper) FROM ExternalIdMapper externalIdMapper";
	private static final String _SQL_COUNT_EXTERNALIDMAPPER_WHERE = "SELECT COUNT(externalIdMapper) FROM ExternalIdMapper externalIdMapper WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "externalIdMapper.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "externalIdMapper.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "externalIdMapper.type = ?";
	private static final String _FINDER_COLUMN_C_T_EI_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_EI_TYPE_2 = "externalIdMapper.type = ? AND ";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_1 = "externalIdMapper.externalId IS NULL";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_2 = "externalIdMapper.externalId = ?";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_3 = "(externalIdMapper.externalId IS NULL OR externalIdMapper.externalId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "externalIdMapper.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExternalIdMapper exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExternalIdMapper exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExternalIdMapperPersistenceImpl.class);
	private static ExternalIdMapper _nullExternalIdMapper = new ExternalIdMapperImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ExternalIdMapper> toCacheModel() {
				return _nullExternalIdMapperCacheModel;
			}
		};

	private static CacheModel<ExternalIdMapper> _nullExternalIdMapperCacheModel = new CacheModel<ExternalIdMapper>() {
			public ExternalIdMapper toEntityModel() {
				return _nullExternalIdMapper;
			}
		};
}