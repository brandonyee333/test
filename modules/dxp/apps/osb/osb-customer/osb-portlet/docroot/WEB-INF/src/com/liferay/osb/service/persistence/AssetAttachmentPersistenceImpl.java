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

import com.liferay.osb.NoSuchAssetAttachmentException;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.impl.AssetAttachmentImpl;
import com.liferay.osb.model.impl.AssetAttachmentModelImpl;

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
 * The persistence implementation for the asset attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAttachmentPersistence
 * @see AssetAttachmentUtil
 * @generated
 */
public class AssetAttachmentPersistenceImpl extends BasePersistenceImpl<AssetAttachment>
	implements AssetAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetAttachmentUtil} to access the asset attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetAttachmentModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAttachmentModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AssetAttachmentModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAttachmentModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTC_C_C = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtC_C_C",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTC_C_C = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtC_C_C",
			new String[] {
				Date.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F_T = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_T =
		new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			AssetAttachmentModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AssetAttachmentModelImpl.CLASSPK_COLUMN_BITMASK |
			AssetAttachmentModelImpl.FILENAME_COLUMN_BITMASK |
			AssetAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_F_T = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED,
			AssetAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the asset attachment in the entity cache if it is enabled.
	 *
	 * @param assetAttachment the asset attachment
	 */
	public void cacheResult(AssetAttachment assetAttachment) {
		EntityCacheUtil.putResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentImpl.class, assetAttachment.getPrimaryKey(),
			assetAttachment);

		assetAttachment.resetOriginalValues();
	}

	/**
	 * Caches the asset attachments in the entity cache if it is enabled.
	 *
	 * @param assetAttachments the asset attachments
	 */
	public void cacheResult(List<AssetAttachment> assetAttachments) {
		for (AssetAttachment assetAttachment : assetAttachments) {
			if (EntityCacheUtil.getResult(
						AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AssetAttachmentImpl.class,
						assetAttachment.getPrimaryKey()) == null) {
				cacheResult(assetAttachment);
			}
			else {
				assetAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset attachments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetAttachmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetAttachmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset attachment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetAttachment assetAttachment) {
		EntityCacheUtil.removeResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentImpl.class, assetAttachment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetAttachment> assetAttachments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetAttachment assetAttachment : assetAttachments) {
			EntityCacheUtil.removeResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AssetAttachmentImpl.class, assetAttachment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset attachment with the primary key. Does not add the asset attachment to the database.
	 *
	 * @param assetAttachmentId the primary key for the new asset attachment
	 * @return the new asset attachment
	 */
	public AssetAttachment create(long assetAttachmentId) {
		AssetAttachment assetAttachment = new AssetAttachmentImpl();

		assetAttachment.setNew(true);
		assetAttachment.setPrimaryKey(assetAttachmentId);

		return assetAttachment;
	}

	/**
	 * Removes the asset attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetAttachmentId the primary key of the asset attachment
	 * @return the asset attachment that was removed
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment remove(long assetAttachmentId)
		throws NoSuchAssetAttachmentException, SystemException {
		return remove(Long.valueOf(assetAttachmentId));
	}

	/**
	 * Removes the asset attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset attachment
	 * @return the asset attachment that was removed
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAttachment remove(Serializable primaryKey)
		throws NoSuchAssetAttachmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetAttachment assetAttachment = (AssetAttachment)session.get(AssetAttachmentImpl.class,
					primaryKey);

			if (assetAttachment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetAttachment);
		}
		catch (NoSuchAssetAttachmentException nsee) {
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
	protected AssetAttachment removeImpl(AssetAttachment assetAttachment)
		throws SystemException {
		assetAttachment = toUnwrappedModel(assetAttachment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, assetAttachment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(assetAttachment);

		return assetAttachment;
	}

	@Override
	public AssetAttachment updateImpl(
		com.liferay.osb.model.AssetAttachment assetAttachment, boolean merge)
		throws SystemException {
		assetAttachment = toUnwrappedModel(assetAttachment);

		boolean isNew = assetAttachment.isNew();

		AssetAttachmentModelImpl assetAttachmentModelImpl = (AssetAttachmentModelImpl)assetAttachment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetAttachment, merge);

			assetAttachment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((assetAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassPK()),
						Integer.valueOf(assetAttachmentModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getClassPK()),
						Integer.valueOf(assetAttachmentModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((assetAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getOriginalClassPK()),
						
						assetAttachmentModelImpl.getOriginalFileName(),
						Integer.valueOf(assetAttachmentModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_T,
					args);

				args = new Object[] {
						Long.valueOf(assetAttachmentModelImpl.getClassNameId()),
						Long.valueOf(assetAttachmentModelImpl.getClassPK()),
						
						assetAttachmentModelImpl.getFileName(),
						Integer.valueOf(assetAttachmentModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_T,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AssetAttachmentImpl.class, assetAttachment.getPrimaryKey(),
			assetAttachment);

		return assetAttachment;
	}

	protected AssetAttachment toUnwrappedModel(AssetAttachment assetAttachment) {
		if (assetAttachment instanceof AssetAttachmentImpl) {
			return assetAttachment;
		}

		AssetAttachmentImpl assetAttachmentImpl = new AssetAttachmentImpl();

		assetAttachmentImpl.setNew(assetAttachment.isNew());
		assetAttachmentImpl.setPrimaryKey(assetAttachment.getPrimaryKey());

		assetAttachmentImpl.setAssetAttachmentId(assetAttachment.getAssetAttachmentId());
		assetAttachmentImpl.setUserId(assetAttachment.getUserId());
		assetAttachmentImpl.setCreateDate(assetAttachment.getCreateDate());
		assetAttachmentImpl.setClassNameId(assetAttachment.getClassNameId());
		assetAttachmentImpl.setClassPK(assetAttachment.getClassPK());
		assetAttachmentImpl.setFileName(assetAttachment.getFileName());
		assetAttachmentImpl.setType(assetAttachment.getType());
		assetAttachmentImpl.setRank(assetAttachment.getRank());

		return assetAttachmentImpl;
	}

	/**
	 * Returns the asset attachment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset attachment
	 * @return the asset attachment
	 * @throws com.liferay.portal.NoSuchModelException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAttachmentException} if it could not be found.
	 *
	 * @param assetAttachmentId the primary key of the asset attachment
	 * @return the asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByPrimaryKey(long assetAttachmentId)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByPrimaryKey(assetAttachmentId);

		if (assetAttachment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetAttachmentId);
			}

			throw new NoSuchAssetAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetAttachmentId);
		}

		return assetAttachment;
	}

	/**
	 * Returns the asset attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset attachment
	 * @return the asset attachment, or <code>null</code> if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetAttachment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the asset attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetAttachmentId the primary key of the asset attachment
	 * @return the asset attachment, or <code>null</code> if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByPrimaryKey(long assetAttachmentId)
		throws SystemException {
		AssetAttachment assetAttachment = (AssetAttachment)EntityCacheUtil.getResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AssetAttachmentImpl.class, assetAttachmentId);

		if (assetAttachment == _nullAssetAttachment) {
			return null;
		}

		if (assetAttachment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				assetAttachment = (AssetAttachment)session.get(AssetAttachmentImpl.class,
						Long.valueOf(assetAttachmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (assetAttachment != null) {
					cacheResult(assetAttachment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AssetAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AssetAttachmentImpl.class, assetAttachmentId,
						_nullAssetAttachment);
				}

				closeSession(session);
			}
		}

		return assetAttachment;
	}

	/**
	 * Returns all the asset attachments where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @return the range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C(long classNameId, long classPK,
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

		List<AssetAttachment> list = (List<AssetAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAttachment assetAttachment : list) {
				if ((classNameId != assetAttachment.getClassNameId()) ||
						(classPK != assetAttachment.getClassPK())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<AssetAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<AssetAttachment> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_Last(classNameId, classPK,
				orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<AssetAttachment> list = findByC_C(classNameId, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetAttachmentId the primary key of the current asset attachment
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment[] findByC_C_PrevAndNext(long assetAttachmentId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = findByPrimaryKey(assetAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AssetAttachment[] array = new AssetAttachmentImpl[3];

			array[0] = getByC_C_PrevAndNext(session, assetAttachment,
					classNameId, classPK, orderByComparator, true);

			array[1] = assetAttachment;

			array[2] = getByC_C_PrevAndNext(session, assetAttachment,
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

	protected AssetAttachment getByC_C_PrevAndNext(Session session,
		AssetAttachment assetAttachment, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

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
			query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_T(long classNameId, long classPK,
		int type) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @return the range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_T(long classNameId, long classPK,
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

		List<AssetAttachment> list = (List<AssetAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAttachment assetAttachment : list) {
				if ((classNameId != assetAttachment.getClassNameId()) ||
						(classPK != assetAttachment.getClassPK()) ||
						(type != assetAttachment.getType())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
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

				list = (List<AssetAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_T_First(classNameId,
				classPK, type, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
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

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetAttachment> list = findByC_C_T(classNameId, classPK, type, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_T_Last(classNameId,
				classPK, type, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
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

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_T(classNameId, classPK, type);

		List<AssetAttachment> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param assetAttachmentId the primary key of the current asset attachment
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment[] findByC_C_T_PrevAndNext(long assetAttachmentId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = findByPrimaryKey(assetAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AssetAttachment[] array = new AssetAttachmentImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, assetAttachment,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = assetAttachment;

			array[2] = getByC_C_T_PrevAndNext(session, assetAttachment,
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

	protected AssetAttachment getByC_C_T_PrevAndNext(Session session,
		AssetAttachment assetAttachment, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

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

		else {
			query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(assetAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByLtC_C_C(Date createDate,
		long classNameId, long classPK) throws SystemException {
		return findByLtC_C_C(createDate, classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @return the range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByLtC_C_C(Date createDate,
		long classNameId, long classPK, int start, int end)
		throws SystemException {
		return findByLtC_C_C(createDate, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByLtC_C_C(Date createDate,
		long classNameId, long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTC_C_C;
		finderArgs = new Object[] {
				createDate, classNameId, classPK,
				
				start, end, orderByComparator
			};

		List<AssetAttachment> list = (List<AssetAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAttachment assetAttachment : list) {
				if (!Validator.equals(createDate,
							assetAttachment.getCreateDate()) ||
						(classNameId != assetAttachment.getClassNameId()) ||
						(classPK != assetAttachment.getClassPK())) {
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
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_LTC_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_LTC_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<AssetAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByLtC_C_C_First(Date createDate,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByLtC_C_C_First(createDate,
				classNameId, classPK, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the first asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByLtC_C_C_First(Date createDate,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetAttachment> list = findByLtC_C_C(createDate, classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByLtC_C_C_Last(Date createDate,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByLtC_C_C_Last(createDate,
				classNameId, classPK, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the last asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByLtC_C_C_Last(Date createDate,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByLtC_C_C(createDate, classNameId, classPK);

		List<AssetAttachment> list = findByLtC_C_C(createDate, classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset attachments before and after the current asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param assetAttachmentId the primary key of the current asset attachment
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment[] findByLtC_C_C_PrevAndNext(long assetAttachmentId,
		Date createDate, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = findByPrimaryKey(assetAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AssetAttachment[] array = new AssetAttachmentImpl[3];

			array[0] = getByLtC_C_C_PrevAndNext(session, assetAttachment,
					createDate, classNameId, classPK, orderByComparator, true);

			array[1] = assetAttachment;

			array[2] = getByLtC_C_C_PrevAndNext(session, assetAttachment,
					createDate, classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetAttachment getByLtC_C_C_PrevAndNext(Session session,
		AssetAttachment assetAttachment, Date createDate, long classNameId,
		long classPK, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_LTC_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_LTC_C_C_CLASSPK_2);

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
			query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_F_T(long classNameId, long classPK,
		String fileName, int type) throws SystemException {
		return findByC_C_F_T(classNameId, classPK, fileName, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @return the range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_F_T(long classNameId, long classPK,
		String fileName, int type, int start, int end)
		throws SystemException {
		return findByC_C_F_T(classNameId, classPK, fileName, type, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findByC_C_F_T(long classNameId, long classPK,
		String fileName, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F_T;
			finderArgs = new Object[] { classNameId, classPK, fileName, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F_T;
			finderArgs = new Object[] {
					classNameId, classPK, fileName, type,
					
					start, end, orderByComparator
				};
		}

		List<AssetAttachment> list = (List<AssetAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetAttachment assetAttachment : list) {
				if ((classNameId != assetAttachment.getClassNameId()) ||
						(classPK != assetAttachment.getClassPK()) ||
						!Validator.equals(fileName,
							assetAttachment.getFileName()) ||
						(type != assetAttachment.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_T_CLASSPK_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_C_F_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (fileName != null) {
					qPos.add(fileName);
				}

				qPos.add(type);

				list = (List<AssetAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_F_T_First(long classNameId, long classPK,
		String fileName, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_F_T_First(classNameId,
				classPK, fileName, type, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", fileName=");
		msg.append(fileName);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_F_T_First(long classNameId, long classPK,
		String fileName, int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetAttachment> list = findByC_C_F_T(classNameId, classPK,
				fileName, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment findByC_C_F_T_Last(long classNameId, long classPK,
		String fileName, int type, OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = fetchByC_C_F_T_Last(classNameId,
				classPK, fileName, type, orderByComparator);

		if (assetAttachment != null) {
			return assetAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", fileName=");
		msg.append(fileName);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetAttachmentException(msg.toString());
	}

	/**
	 * Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment fetchByC_C_F_T_Last(long classNameId, long classPK,
		String fileName, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_F_T(classNameId, classPK, fileName, type);

		List<AssetAttachment> list = findByC_C_F_T(classNameId, classPK,
				fileName, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param assetAttachmentId the primary key of the current asset attachment
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset attachment
	 * @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssetAttachment[] findByC_C_F_T_PrevAndNext(long assetAttachmentId,
		long classNameId, long classPK, String fileName, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAssetAttachmentException, SystemException {
		AssetAttachment assetAttachment = findByPrimaryKey(assetAttachmentId);

		Session session = null;

		try {
			session = openSession();

			AssetAttachment[] array = new AssetAttachmentImpl[3];

			array[0] = getByC_C_F_T_PrevAndNext(session, assetAttachment,
					classNameId, classPK, fileName, type, orderByComparator,
					true);

			array[1] = assetAttachment;

			array[2] = getByC_C_F_T_PrevAndNext(session, assetAttachment,
					classNameId, classPK, fileName, type, orderByComparator,
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

	protected AssetAttachment getByC_C_F_T_PrevAndNext(Session session,
		AssetAttachment assetAttachment, long classNameId, long classPK,
		String fileName, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_C_C_F_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_F_T_CLASSPK_2);

		if (fileName == null) {
			query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_1);
		}
		else {
			if (fileName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_2);
			}
		}

		query.append(_FINDER_COLUMN_C_C_F_T_TYPE_2);

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
			query.append(AssetAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (fileName != null) {
			qPos.add(fileName);
		}

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the asset attachments.
	 *
	 * @return the asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @return the range of asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset attachments
	 * @param end the upper bound of the range of asset attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssetAttachment> findAll(int start, int end,
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

		List<AssetAttachment> list = (List<AssetAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETATTACHMENT.concat(AssetAttachmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssetAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetAttachment>)QueryUtil.list(q,
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
	 * Removes all the asset attachments where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (AssetAttachment assetAttachment : findByC_C(classNameId, classPK)) {
			remove(assetAttachment);
		}
	}

	/**
	 * Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		for (AssetAttachment assetAttachment : findByC_C_T(classNameId,
				classPK, type)) {
			remove(assetAttachment);
		}
	}

	/**
	 * Removes all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLtC_C_C(Date createDate, long classNameId, long classPK)
		throws SystemException {
		for (AssetAttachment assetAttachment : findByLtC_C_C(createDate,
				classNameId, classPK)) {
			remove(assetAttachment);
		}
	}

	/**
	 * Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_F_T(long classNameId, long classPK,
		String fileName, int type) throws SystemException {
		for (AssetAttachment assetAttachment : findByC_C_F_T(classNameId,
				classPK, fileName, type)) {
			remove(assetAttachment);
		}
	}

	/**
	 * Removes all the asset attachments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssetAttachment assetAttachment : findAll()) {
			remove(assetAttachment);
		}
	}

	/**
	 * Returns the number of asset attachments where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETATTACHMENT_WHERE);

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
	 * Returns the number of asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETATTACHMENT_WHERE);

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
	 * Returns the number of asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLtC_C_C(Date createDate, long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { createDate, classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTC_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_LTC_C_C_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_LTC_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_LTC_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTC_C_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param fileName the file name
	 * @param type the type
	 * @return the number of matching asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_F_T(long classNameId, long classPK, String fileName,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, fileName, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_F_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ASSETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_T_CLASSPK_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_C_F_T_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_C_F_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (fileName != null) {
					qPos.add(fileName);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_F_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of asset attachments.
	 *
	 * @return the number of asset attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETATTACHMENT);

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
	 * Initializes the asset attachment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AssetAttachment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetAttachment>> listenersList = new ArrayList<ModelListener<AssetAttachment>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AssetAttachment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetAttachmentImpl.class.getName());
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
	private static final String _SQL_SELECT_ASSETATTACHMENT = "SELECT assetAttachment FROM AssetAttachment assetAttachment";
	private static final String _SQL_SELECT_ASSETATTACHMENT_WHERE = "SELECT assetAttachment FROM AssetAttachment assetAttachment WHERE ";
	private static final String _SQL_COUNT_ASSETATTACHMENT = "SELECT COUNT(assetAttachment) FROM AssetAttachment assetAttachment";
	private static final String _SQL_COUNT_ASSETATTACHMENT_WHERE = "SELECT COUNT(assetAttachment) FROM AssetAttachment assetAttachment WHERE ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "assetAttachment.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "assetAttachment.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "assetAttachment.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "assetAttachment.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "assetAttachment.type = ?";
	private static final String _FINDER_COLUMN_LTC_C_C_CREATEDATE_1 = "assetAttachment.createDate < NULL AND ";
	private static final String _FINDER_COLUMN_LTC_C_C_CREATEDATE_2 = "assetAttachment.createDate < ? AND ";
	private static final String _FINDER_COLUMN_LTC_C_C_CLASSNAMEID_2 = "assetAttachment.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_LTC_C_C_CLASSPK_2 = "assetAttachment.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_F_T_CLASSNAMEID_2 = "assetAttachment.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_T_CLASSPK_2 = "assetAttachment.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_T_FILENAME_1 = "assetAttachment.fileName IS NULL AND ";
	private static final String _FINDER_COLUMN_C_C_F_T_FILENAME_2 = "assetAttachment.fileName = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_T_FILENAME_3 = "(assetAttachment.fileName IS NULL OR assetAttachment.fileName = ?) AND ";
	private static final String _FINDER_COLUMN_C_C_F_T_TYPE_2 = "assetAttachment.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetAttachment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetAttachmentPersistenceImpl.class);
	private static AssetAttachment _nullAssetAttachment = new AssetAttachmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetAttachment> toCacheModel() {
				return _nullAssetAttachmentCacheModel;
			}
		};

	private static CacheModel<AssetAttachment> _nullAssetAttachmentCacheModel = new CacheModel<AssetAttachment>() {
			public AssetAttachment toEntityModel() {
				return _nullAssetAttachment;
			}
		};
}