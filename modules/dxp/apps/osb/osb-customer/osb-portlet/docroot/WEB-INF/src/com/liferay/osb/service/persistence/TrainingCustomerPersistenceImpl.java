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

import com.liferay.osb.NoSuchTrainingCustomerException;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.impl.TrainingCustomerImpl;
import com.liferay.osb.model.impl.TrainingCustomerModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the training customer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCustomerPersistence
 * @see TrainingCustomerUtil
 * @generated
 */
public class TrainingCustomerPersistenceImpl extends BasePersistenceImpl<TrainingCustomer>
	implements TrainingCustomerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingCustomerUtil} to access the training customer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingCustomerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TrainingCustomerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			TrainingCustomerModelImpl.USERID_COLUMN_BITMASK |
			TrainingCustomerModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			TrainingCustomerModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TrainingCustomerModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_C_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			TrainingCustomerModelImpl.USERID_COLUMN_BITMASK |
			TrainingCustomerModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TrainingCustomerModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TrainingCustomerModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			TrainingCustomerModelImpl.CLASSPK_COLUMN_BITMASK |
			TrainingCustomerModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_S = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_S = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_C_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED,
			TrainingCustomerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training customer in the entity cache if it is enabled.
	 *
	 * @param trainingCustomer the training customer
	 */
	public void cacheResult(TrainingCustomer trainingCustomer) {
		EntityCacheUtil.putResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerImpl.class, trainingCustomer.getPrimaryKey(),
			trainingCustomer);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
			new Object[] {
				Long.valueOf(trainingCustomer.getUserId()),
				Long.valueOf(trainingCustomer.getClassNameId()),
				Long.valueOf(trainingCustomer.getClassPK())
			}, trainingCustomer);

		trainingCustomer.resetOriginalValues();
	}

	/**
	 * Caches the training customers in the entity cache if it is enabled.
	 *
	 * @param trainingCustomers the training customers
	 */
	public void cacheResult(List<TrainingCustomer> trainingCustomers) {
		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			if (EntityCacheUtil.getResult(
						TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
						TrainingCustomerImpl.class,
						trainingCustomer.getPrimaryKey()) == null) {
				cacheResult(trainingCustomer);
			}
			else {
				trainingCustomer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training customers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingCustomerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingCustomerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training customer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingCustomer trainingCustomer) {
		EntityCacheUtil.removeResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerImpl.class, trainingCustomer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingCustomer);
	}

	@Override
	public void clearCache(List<TrainingCustomer> trainingCustomers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			EntityCacheUtil.removeResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
				TrainingCustomerImpl.class, trainingCustomer.getPrimaryKey());

			clearUniqueFindersCache(trainingCustomer);
		}
	}

	protected void cacheUniqueFindersCache(TrainingCustomer trainingCustomer) {
		if (trainingCustomer.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(trainingCustomer.getUserId()),
					Long.valueOf(trainingCustomer.getClassNameId()),
					Long.valueOf(trainingCustomer.getClassPK())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C, args,
				trainingCustomer);
		}
		else {
			TrainingCustomerModelImpl trainingCustomerModelImpl = (TrainingCustomerModelImpl)trainingCustomer;

			if ((trainingCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCustomer.getUserId()),
						Long.valueOf(trainingCustomer.getClassNameId()),
						Long.valueOf(trainingCustomer.getClassPK())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C, args,
					trainingCustomer);
			}
		}
	}

	protected void clearUniqueFindersCache(TrainingCustomer trainingCustomer) {
		TrainingCustomerModelImpl trainingCustomerModelImpl = (TrainingCustomerModelImpl)trainingCustomer;

		Object[] args = new Object[] {
				Long.valueOf(trainingCustomer.getUserId()),
				Long.valueOf(trainingCustomer.getClassNameId()),
				Long.valueOf(trainingCustomer.getClassPK())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C, args);

		if ((trainingCustomerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(trainingCustomerModelImpl.getOriginalUserId()),
					Long.valueOf(trainingCustomerModelImpl.getOriginalClassNameId()),
					Long.valueOf(trainingCustomerModelImpl.getOriginalClassPK())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C, args);
		}
	}

	/**
	 * Creates a new training customer with the primary key. Does not add the training customer to the database.
	 *
	 * @param trainingCustomerId the primary key for the new training customer
	 * @return the new training customer
	 */
	public TrainingCustomer create(long trainingCustomerId) {
		TrainingCustomer trainingCustomer = new TrainingCustomerImpl();

		trainingCustomer.setNew(true);
		trainingCustomer.setPrimaryKey(trainingCustomerId);

		return trainingCustomer;
	}

	/**
	 * Removes the training customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingCustomerId the primary key of the training customer
	 * @return the training customer that was removed
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer remove(long trainingCustomerId)
		throws NoSuchTrainingCustomerException, SystemException {
		return remove(Long.valueOf(trainingCustomerId));
	}

	/**
	 * Removes the training customer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training customer
	 * @return the training customer that was removed
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCustomer remove(Serializable primaryKey)
		throws NoSuchTrainingCustomerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingCustomer trainingCustomer = (TrainingCustomer)session.get(TrainingCustomerImpl.class,
					primaryKey);

			if (trainingCustomer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingCustomer);
		}
		catch (NoSuchTrainingCustomerException nsee) {
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
	protected TrainingCustomer removeImpl(TrainingCustomer trainingCustomer)
		throws SystemException {
		trainingCustomer = toUnwrappedModel(trainingCustomer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingCustomer);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingCustomer);

		return trainingCustomer;
	}

	@Override
	public TrainingCustomer updateImpl(
		com.liferay.osb.model.TrainingCustomer trainingCustomer, boolean merge)
		throws SystemException {
		trainingCustomer = toUnwrappedModel(trainingCustomer);

		boolean isNew = trainingCustomer.isNew();

		TrainingCustomerModelImpl trainingCustomerModelImpl = (TrainingCustomerModelImpl)trainingCustomer;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingCustomer, merge);

			trainingCustomer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingCustomerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((trainingCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getOriginalUserId()),
						Long.valueOf(trainingCustomerModelImpl.getOriginalClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);

				args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getUserId()),
						Long.valueOf(trainingCustomerModelImpl.getClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C,
					args);
			}

			if ((trainingCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getOriginalClassNameId()),
						Long.valueOf(trainingCustomerModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getClassNameId()),
						Long.valueOf(trainingCustomerModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((trainingCustomerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getOriginalClassNameId()),
						Long.valueOf(trainingCustomerModelImpl.getOriginalClassPK()),
						Integer.valueOf(trainingCustomerModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);

				args = new Object[] {
						Long.valueOf(trainingCustomerModelImpl.getClassNameId()),
						Long.valueOf(trainingCustomerModelImpl.getClassPK()),
						Integer.valueOf(trainingCustomerModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_S,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
			TrainingCustomerImpl.class, trainingCustomer.getPrimaryKey(),
			trainingCustomer);

		clearUniqueFindersCache(trainingCustomer);
		cacheUniqueFindersCache(trainingCustomer);

		return trainingCustomer;
	}

	protected TrainingCustomer toUnwrappedModel(
		TrainingCustomer trainingCustomer) {
		if (trainingCustomer instanceof TrainingCustomerImpl) {
			return trainingCustomer;
		}

		TrainingCustomerImpl trainingCustomerImpl = new TrainingCustomerImpl();

		trainingCustomerImpl.setNew(trainingCustomer.isNew());
		trainingCustomerImpl.setPrimaryKey(trainingCustomer.getPrimaryKey());

		trainingCustomerImpl.setTrainingCustomerId(trainingCustomer.getTrainingCustomerId());
		trainingCustomerImpl.setUserId(trainingCustomer.getUserId());
		trainingCustomerImpl.setClassNameId(trainingCustomer.getClassNameId());
		trainingCustomerImpl.setClassPK(trainingCustomer.getClassPK());
		trainingCustomerImpl.setUserProfileHistoryId(trainingCustomer.getUserProfileHistoryId());
		trainingCustomerImpl.setComments(trainingCustomer.getComments());
		trainingCustomerImpl.setStatus(trainingCustomer.getStatus());

		return trainingCustomerImpl;
	}

	/**
	 * Returns the training customer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training customer
	 * @return the training customer
	 * @throws com.liferay.portal.NoSuchModelException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCustomer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training customer with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCustomerException} if it could not be found.
	 *
	 * @param trainingCustomerId the primary key of the training customer
	 * @return the training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByPrimaryKey(long trainingCustomerId)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByPrimaryKey(trainingCustomerId);

		if (trainingCustomer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					trainingCustomerId);
			}

			throw new NoSuchTrainingCustomerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingCustomerId);
		}

		return trainingCustomer;
	}

	/**
	 * Returns the training customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training customer
	 * @return the training customer, or <code>null</code> if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingCustomer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training customer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingCustomerId the primary key of the training customer
	 * @return the training customer, or <code>null</code> if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByPrimaryKey(long trainingCustomerId)
		throws SystemException {
		TrainingCustomer trainingCustomer = (TrainingCustomer)EntityCacheUtil.getResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
				TrainingCustomerImpl.class, trainingCustomerId);

		if (trainingCustomer == _nullTrainingCustomer) {
			return null;
		}

		if (trainingCustomer == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingCustomer = (TrainingCustomer)session.get(TrainingCustomerImpl.class,
						Long.valueOf(trainingCustomerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingCustomer != null) {
					cacheResult(trainingCustomer);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingCustomerModelImpl.ENTITY_CACHE_ENABLED,
						TrainingCustomerImpl.class, trainingCustomerId,
						_nullTrainingCustomer);
				}

				closeSession(session);
			}
		}

		return trainingCustomer;
	}

	/**
	 * Returns all the training customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingCustomer trainingCustomer : list) {
				if ((userId != trainingCustomer.getUserId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

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

				qPos.add(userId);

				list = (List<TrainingCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByUserId_First(userId,
				orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the first training customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingCustomer> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByUserId_Last(userId,
				orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the last training customer in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<TrainingCustomer> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training customers before and after the current training customer in the ordered set where userId = &#63;.
	 *
	 * @param trainingCustomerId the primary key of the current training customer
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer[] findByUserId_PrevAndNext(
		long trainingCustomerId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = findByPrimaryKey(trainingCustomerId);

		Session session = null;

		try {
			session = openSession();

			TrainingCustomer[] array = new TrainingCustomerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, trainingCustomer,
					userId, orderByComparator, true);

			array[1] = trainingCustomer;

			array[2] = getByUserId_PrevAndNext(session, trainingCustomer,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingCustomer getByUserId_PrevAndNext(Session session,
		TrainingCustomer trainingCustomer, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training customers where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByU_C(long userId, long classNameId)
		throws SystemException {
		return findByU_C(userId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByU_C(long userId, long classNameId,
		int start, int end) throws SystemException {
		return findByU_C(userId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers where userId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByU_C(long userId, long classNameId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] { userId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C;
			finderArgs = new Object[] {
					userId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingCustomer trainingCustomer : list) {
				if ((userId != trainingCustomer.getUserId()) ||
						(classNameId != trainingCustomer.getClassNameId())) {
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

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

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

				qPos.add(userId);

				qPos.add(classNameId);

				list = (List<TrainingCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByU_C_First(long userId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByU_C_First(userId,
				classNameId, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the first training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByU_C_First(long userId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingCustomer> list = findByU_C(userId, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByU_C_Last(long userId, long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByU_C_Last(userId,
				classNameId, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the last training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByU_C_Last(long userId, long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_C(userId, classNameId);

		List<TrainingCustomer> list = findByU_C(userId, classNameId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training customers before and after the current training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	 *
	 * @param trainingCustomerId the primary key of the current training customer
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer[] findByU_C_PrevAndNext(long trainingCustomerId,
		long userId, long classNameId, OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = findByPrimaryKey(trainingCustomerId);

		Session session = null;

		try {
			session = openSession();

			TrainingCustomer[] array = new TrainingCustomerImpl[3];

			array[0] = getByU_C_PrevAndNext(session, trainingCustomer, userId,
					classNameId, orderByComparator, true);

			array[1] = trainingCustomer;

			array[2] = getByU_C_PrevAndNext(session, trainingCustomer, userId,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingCustomer getByU_C_PrevAndNext(Session session,
		TrainingCustomer trainingCustomer, long userId, long classNameId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

		query.append(_FINDER_COLUMN_U_C_USERID_2);

		query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

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

		qPos.add(userId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training customers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C(long classNameId, long classPK,
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

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingCustomer trainingCustomer : list) {
				if ((classNameId != trainingCustomer.getClassNameId()) ||
						(classPK != trainingCustomer.getClassPK())) {
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

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

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

				list = (List<TrainingCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingCustomer> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_C(classNameId, classPK);

		List<TrainingCustomer> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training customers before and after the current training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param trainingCustomerId the primary key of the current training customer
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer[] findByC_C_PrevAndNext(long trainingCustomerId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = findByPrimaryKey(trainingCustomerId);

		Session session = null;

		try {
			session = openSession();

			TrainingCustomer[] array = new TrainingCustomerImpl[3];

			array[0] = getByC_C_PrevAndNext(session, trainingCustomer,
					classNameId, classPK, orderByComparator, true);

			array[1] = trainingCustomer;

			array[2] = getByC_C_PrevAndNext(session, trainingCustomer,
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

	protected TrainingCustomer getByC_C_PrevAndNext(Session session,
		TrainingCustomer trainingCustomer, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(trainingCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCustomerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByU_C_C(long userId, long classNameId,
		long classPK) throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByU_C_C(userId, classNameId,
				classPK);

		if (trainingCustomer == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingCustomerException(msg.toString());
		}

		return trainingCustomer;
	}

	/**
	 * Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByU_C_C(long userId, long classNameId,
		long classPK) throws SystemException {
		return fetchByU_C_C(userId, classNameId, classPK, true);
	}

	/**
	 * Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByU_C_C(long userId, long classNameId,
		long classPK, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_C_C,
					finderArgs, this);
		}

		if (result instanceof TrainingCustomer) {
			TrainingCustomer trainingCustomer = (TrainingCustomer)result;

			if ((userId != trainingCustomer.getUserId()) ||
					(classNameId != trainingCustomer.getClassNameId()) ||
					(classPK != trainingCustomer.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<TrainingCustomer> list = q.list();

				result = list;

				TrainingCustomer trainingCustomer = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
						finderArgs, list);
				}
				else {
					trainingCustomer = list.get(0);

					cacheResult(trainingCustomer);

					if ((trainingCustomer.getUserId() != userId) ||
							(trainingCustomer.getClassNameId() != classNameId) ||
							(trainingCustomer.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_C_C,
							finderArgs, trainingCustomer);
					}
				}

				return trainingCustomer;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_C_C,
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
				return (TrainingCustomer)result;
			}
		}
	}

	/**
	 * Returns all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @return the matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int status) throws SystemException {
		return findByC_C_S(classNameId, classPK, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end) throws SystemException {
		return findByC_C_S(classNameId, classPK, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingCustomer trainingCustomer : list) {
				if ((classNameId != trainingCustomer.getClassNameId()) ||
						(classPK != trainingCustomer.getClassPK()) ||
						(status != trainingCustomer.getStatus())) {
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

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_S_STATUS_2);

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

				qPos.add(status);

				list = (List<TrainingCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByC_C_S_First(classNameId,
				classPK, status, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
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

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByC_C_S_First(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingCustomer> list = findByC_C_S(classNameId, classPK, status,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer findByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = fetchByC_C_S_Last(classNameId,
				classPK, status, orderByComparator);

		if (trainingCustomer != null) {
			return trainingCustomer;
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

		throw new NoSuchTrainingCustomerException(msg.toString());
	}

	/**
	 * Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer fetchByC_C_S_Last(long classNameId, long classPK,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_S(classNameId, classPK, status);

		List<TrainingCustomer> list = findByC_C_S(classNameId, classPK, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training customers before and after the current training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param trainingCustomerId the primary key of the current training customer
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training customer
	 * @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer[] findByC_C_S_PrevAndNext(long trainingCustomerId,
		long classNameId, long classPK, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = findByPrimaryKey(trainingCustomerId);

		Session session = null;

		try {
			session = openSession();

			TrainingCustomer[] array = new TrainingCustomerImpl[3];

			array[0] = getByC_C_S_PrevAndNext(session, trainingCustomer,
					classNameId, classPK, status, orderByComparator, true);

			array[1] = trainingCustomer;

			array[2] = getByC_C_S_PrevAndNext(session, trainingCustomer,
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

	protected TrainingCustomer getByC_C_S_PrevAndNext(Session session,
		TrainingCustomer trainingCustomer, long classNameId, long classPK,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingCustomer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingCustomer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param statuses the statuses
	 * @return the matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int[] statuses) throws SystemException {
		return findByC_C_S(classNameId, classPK, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param statuses the statuses
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int[] statuses, int start, int end) throws SystemException {
		return findByC_C_S(classNameId, classPK, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param statuses the statuses
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findByC_C_S(long classNameId, long classPK,
		int[] statuses, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingCustomer trainingCustomer : list) {
				if ((classNameId != trainingCustomer.getClassNameId()) ||
						(classPK != trainingCustomer.getClassPK()) ||
						!ArrayUtil.contains(statuses,
							trainingCustomer.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TRAININGCUSTOMER_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_C_C_S_STATUS_5);

					if ((i + 1) < statuses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
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

				qPos.add(classPK);

				if (statuses != null) {
					qPos.add(statuses);
				}

				list = (List<TrainingCustomer>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the training customers.
	 *
	 * @return the training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @return the range of training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training customers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training customers
	 * @param end the upper bound of the range of training customers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training customers
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingCustomer> findAll(int start, int end,
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

		List<TrainingCustomer> list = (List<TrainingCustomer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGCUSTOMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGCUSTOMER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingCustomer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingCustomer>)QueryUtil.list(q,
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
	 * Removes all the training customers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (TrainingCustomer trainingCustomer : findByUserId(userId)) {
			remove(trainingCustomer);
		}
	}

	/**
	 * Removes all the training customers where userId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_C(long userId, long classNameId)
		throws SystemException {
		for (TrainingCustomer trainingCustomer : findByU_C(userId, classNameId)) {
			remove(trainingCustomer);
		}
	}

	/**
	 * Removes all the training customers where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (TrainingCustomer trainingCustomer : findByC_C(classNameId, classPK)) {
			remove(trainingCustomer);
		}
	}

	/**
	 * Removes the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the training customer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingCustomer removeByU_C_C(long userId, long classNameId,
		long classPK) throws NoSuchTrainingCustomerException, SystemException {
		TrainingCustomer trainingCustomer = findByU_C_C(userId, classNameId,
				classPK);

		return remove(trainingCustomer);
	}

	/**
	 * Removes all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_S(long classNameId, long classPK, int status)
		throws SystemException {
		for (TrainingCustomer trainingCustomer : findByC_C_S(classNameId,
				classPK, status)) {
			remove(trainingCustomer);
		}
	}

	/**
	 * Removes all the training customers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingCustomer trainingCustomer : findAll()) {
			remove(trainingCustomer);
		}
	}

	/**
	 * Returns the number of training customers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training customers where userId = &#63; and classNameId = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C(long userId, long classNameId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training customers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

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
	 * Returns the number of training customers where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C_C(long userId, long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param status the status
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_S(long classNameId, long classPK, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param statuses the statuses
	 * @return the number of matching training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_S(long classNameId, long classPK, int[] statuses)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				classNameId, classPK, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TRAININGCUSTOMER_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_S_CLASSNAMEID_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_C_C_S_CLASSPK_5);

			conjunctionable = true;

			if ((statuses == null) || (statuses.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < statuses.length; i++) {
					query.append(_FINDER_COLUMN_C_C_S_STATUS_5);

					if ((i + 1) < statuses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (statuses != null) {
					qPos.add(statuses);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training customers.
	 *
	 * @return the number of training customers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGCUSTOMER);

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
	 * Initializes the training customer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingCustomer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingCustomer>> listenersList = new ArrayList<ModelListener<TrainingCustomer>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingCustomer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingCustomerImpl.class.getName());
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
	private static final String _SQL_SELECT_TRAININGCUSTOMER = "SELECT trainingCustomer FROM TrainingCustomer trainingCustomer";
	private static final String _SQL_SELECT_TRAININGCUSTOMER_WHERE = "SELECT trainingCustomer FROM TrainingCustomer trainingCustomer WHERE ";
	private static final String _SQL_COUNT_TRAININGCUSTOMER = "SELECT COUNT(trainingCustomer) FROM TrainingCustomer trainingCustomer";
	private static final String _SQL_COUNT_TRAININGCUSTOMER_WHERE = "SELECT COUNT(trainingCustomer) FROM TrainingCustomer trainingCustomer WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "trainingCustomer.userId = ?";
	private static final String _FINDER_COLUMN_U_C_USERID_2 = "trainingCustomer.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_CLASSNAMEID_2 = "trainingCustomer.classNameId = ?";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "trainingCustomer.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "trainingCustomer.classPK = ?";
	private static final String _FINDER_COLUMN_U_C_C_USERID_2 = "trainingCustomer.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSNAMEID_2 = "trainingCustomer.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_CLASSPK_2 = "trainingCustomer.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_2 = "trainingCustomer.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSNAMEID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_S_CLASSNAMEID_2) + ")";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_2 = "trainingCustomer.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_S_CLASSPK_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_S_CLASSPK_2) + ")";
	private static final String _FINDER_COLUMN_C_C_S_STATUS_2 = "trainingCustomer.status = ?";
	private static final String _FINDER_COLUMN_C_C_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_C_C_S_STATUS_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingCustomer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingCustomer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingCustomer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingCustomerPersistenceImpl.class);
	private static TrainingCustomer _nullTrainingCustomer = new TrainingCustomerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingCustomer> toCacheModel() {
				return _nullTrainingCustomerCacheModel;
			}
		};

	private static CacheModel<TrainingCustomer> _nullTrainingCustomerCacheModel = new CacheModel<TrainingCustomer>() {
			public TrainingCustomer toEntityModel() {
				return _nullTrainingCustomer;
			}
		};
}