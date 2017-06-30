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

import com.liferay.osb.NoSuchOfferingDefinitionException;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.model.impl.OfferingDefinitionImpl;
import com.liferay.osb.model.impl.OfferingDefinitionModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the offering definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionPersistence
 * @see OfferingDefinitionUtil
 * @generated
 */
public class OfferingDefinitionPersistenceImpl extends BasePersistenceImpl<OfferingDefinition>
	implements OfferingDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfferingDefinitionUtil} to access the offering definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfferingDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] { Long.class.getName() },
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCTENTRYID = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTRESPONSEID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportResponseId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportResponseId", new String[] { Long.class.getName() },
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportResponseId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEI_SRI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() },
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPEI_SRI_PD_L_UL_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.PRODUCTDESCRIPTION_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.LICENSES_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.UNLIMITEDLICENSES_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTTICKETS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPEI_SRI_PD_L_UL_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the offering definition in the entity cache if it is enabled.
	 *
	 * @param offeringDefinition the offering definition
	 */
	public void cacheResult(OfferingDefinition offeringDefinition) {
		EntityCacheUtil.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey(),
			offeringDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
			new Object[] {
				Long.valueOf(offeringDefinition.getProductEntryId()),
				Long.valueOf(offeringDefinition.getSupportResponseId()),
				
			offeringDefinition.getProductDescription(),
				Boolean.valueOf(offeringDefinition.getLicenses()),
				Boolean.valueOf(offeringDefinition.getUnlimitedLicenses()),
				Boolean.valueOf(offeringDefinition.getSupportTickets())
			}, offeringDefinition);

		offeringDefinition.resetOriginalValues();
	}

	/**
	 * Caches the offering definitions in the entity cache if it is enabled.
	 *
	 * @param offeringDefinitions the offering definitions
	 */
	public void cacheResult(List<OfferingDefinition> offeringDefinitions) {
		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			if (EntityCacheUtil.getResult(
						OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						OfferingDefinitionImpl.class,
						offeringDefinition.getPrimaryKey()) == null) {
				cacheResult(offeringDefinition);
			}
			else {
				offeringDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offering definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OfferingDefinitionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OfferingDefinitionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingDefinition offeringDefinition) {
		EntityCacheUtil.removeResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(offeringDefinition);
	}

	@Override
	public void clearCache(List<OfferingDefinition> offeringDefinitions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			EntityCacheUtil.removeResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey());

			clearUniqueFindersCache(offeringDefinition);
		}
	}

	protected void cacheUniqueFindersCache(
		OfferingDefinition offeringDefinition) {
		if (offeringDefinition.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(offeringDefinition.getProductEntryId()),
					Long.valueOf(offeringDefinition.getSupportResponseId()),
					
					offeringDefinition.getProductDescription(),
					Boolean.valueOf(offeringDefinition.getLicenses()),
					Boolean.valueOf(offeringDefinition.getUnlimitedLicenses()),
					Boolean.valueOf(offeringDefinition.getSupportTickets())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
				args, offeringDefinition);
		}
		else {
			OfferingDefinitionModelImpl offeringDefinitionModelImpl = (OfferingDefinitionModelImpl)offeringDefinition;

			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringDefinition.getProductEntryId()),
						Long.valueOf(offeringDefinition.getSupportResponseId()),
						
						offeringDefinition.getProductDescription(),
						Boolean.valueOf(offeringDefinition.getLicenses()),
						Boolean.valueOf(offeringDefinition.getUnlimitedLicenses()),
						Boolean.valueOf(offeringDefinition.getSupportTickets())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
					args, offeringDefinition);
			}
		}
	}

	protected void clearUniqueFindersCache(
		OfferingDefinition offeringDefinition) {
		OfferingDefinitionModelImpl offeringDefinitionModelImpl = (OfferingDefinitionModelImpl)offeringDefinition;

		Object[] args = new Object[] {
				Long.valueOf(offeringDefinition.getProductEntryId()),
				Long.valueOf(offeringDefinition.getSupportResponseId()),
				
				offeringDefinition.getProductDescription(),
				Boolean.valueOf(offeringDefinition.getLicenses()),
				Boolean.valueOf(offeringDefinition.getUnlimitedLicenses()),
				Boolean.valueOf(offeringDefinition.getSupportTickets())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
			args);

		if ((offeringDefinitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(offeringDefinitionModelImpl.getOriginalProductEntryId()),
					Long.valueOf(offeringDefinitionModelImpl.getOriginalSupportResponseId()),
					
					offeringDefinitionModelImpl.getOriginalProductDescription(),
					Boolean.valueOf(offeringDefinitionModelImpl.getOriginalLicenses()),
					Boolean.valueOf(offeringDefinitionModelImpl.getOriginalUnlimitedLicenses()),
					Boolean.valueOf(offeringDefinitionModelImpl.getOriginalSupportTickets())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
				args);
		}
	}

	/**
	 * Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	 *
	 * @param offeringDefinitionId the primary key for the new offering definition
	 * @return the new offering definition
	 */
	public OfferingDefinition create(long offeringDefinitionId) {
		OfferingDefinition offeringDefinition = new OfferingDefinitionImpl();

		offeringDefinition.setNew(true);
		offeringDefinition.setPrimaryKey(offeringDefinitionId);

		return offeringDefinition;
	}

	/**
	 * Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition that was removed
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition remove(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException, SystemException {
		return remove(Long.valueOf(offeringDefinitionId));
	}

	/**
	 * Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition that was removed
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingDefinition remove(Serializable primaryKey)
		throws NoSuchOfferingDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OfferingDefinition offeringDefinition = (OfferingDefinition)session.get(OfferingDefinitionImpl.class,
					primaryKey);

			if (offeringDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferingDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(offeringDefinition);
		}
		catch (NoSuchOfferingDefinitionException nsee) {
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
	protected OfferingDefinition removeImpl(
		OfferingDefinition offeringDefinition) throws SystemException {
		offeringDefinition = toUnwrappedModel(offeringDefinition);

		try {
			clearOfferingBundles.clear(offeringDefinition.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, offeringDefinition);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(offeringDefinition);

		return offeringDefinition;
	}

	@Override
	public OfferingDefinition updateImpl(
		com.liferay.osb.model.OfferingDefinition offeringDefinition,
		boolean merge) throws SystemException {
		offeringDefinition = toUnwrappedModel(offeringDefinition);

		boolean isNew = offeringDefinition.isNew();

		OfferingDefinitionModelImpl offeringDefinitionModelImpl = (OfferingDefinitionModelImpl)offeringDefinition;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, offeringDefinition, merge);

			offeringDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OfferingDefinitionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getOriginalProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getProductEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);
			}

			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getOriginalSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID,
					args);

				args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID,
					args);
			}

			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getOriginalProductEntryId()),
						Long.valueOf(offeringDefinitionModelImpl.getOriginalSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI,
					args);

				args = new Object[] {
						Long.valueOf(offeringDefinitionModelImpl.getProductEntryId()),
						Long.valueOf(offeringDefinitionModelImpl.getSupportResponseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI,
					args);
			}
		}

		EntityCacheUtil.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey(),
			offeringDefinition);

		clearUniqueFindersCache(offeringDefinition);
		cacheUniqueFindersCache(offeringDefinition);

		return offeringDefinition;
	}

	protected OfferingDefinition toUnwrappedModel(
		OfferingDefinition offeringDefinition) {
		if (offeringDefinition instanceof OfferingDefinitionImpl) {
			return offeringDefinition;
		}

		OfferingDefinitionImpl offeringDefinitionImpl = new OfferingDefinitionImpl();

		offeringDefinitionImpl.setNew(offeringDefinition.isNew());
		offeringDefinitionImpl.setPrimaryKey(offeringDefinition.getPrimaryKey());

		offeringDefinitionImpl.setOfferingDefinitionId(offeringDefinition.getOfferingDefinitionId());
		offeringDefinitionImpl.setUserId(offeringDefinition.getUserId());
		offeringDefinitionImpl.setUserName(offeringDefinition.getUserName());
		offeringDefinitionImpl.setCreateDate(offeringDefinition.getCreateDate());
		offeringDefinitionImpl.setModifiedDate(offeringDefinition.getModifiedDate());
		offeringDefinitionImpl.setProductEntryId(offeringDefinition.getProductEntryId());
		offeringDefinitionImpl.setSupportResponseId(offeringDefinition.getSupportResponseId());
		offeringDefinitionImpl.setProductDescription(offeringDefinition.getProductDescription());
		offeringDefinitionImpl.setLicenses(offeringDefinition.isLicenses());
		offeringDefinitionImpl.setUnlimitedLicenses(offeringDefinition.isUnlimitedLicenses());
		offeringDefinitionImpl.setMaxConcurrentUsers(offeringDefinition.getMaxConcurrentUsers());
		offeringDefinitionImpl.setMaxUsers(offeringDefinition.getMaxUsers());
		offeringDefinitionImpl.setSupportTickets(offeringDefinition.isSupportTickets());

		return offeringDefinitionImpl;
	}

	/**
	 * Returns the offering definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition
	 * @throws com.liferay.portal.NoSuchModelException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering definition with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingDefinitionException} if it could not be found.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByPrimaryKey(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByPrimaryKey(offeringDefinitionId);

		if (offeringDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					offeringDefinitionId);
			}

			throw new NoSuchOfferingDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				offeringDefinitionId);
		}

		return offeringDefinition;
	}

	/**
	 * Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OfferingDefinition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByPrimaryKey(long offeringDefinitionId)
		throws SystemException {
		OfferingDefinition offeringDefinition = (OfferingDefinition)EntityCacheUtil.getResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				OfferingDefinitionImpl.class, offeringDefinitionId);

		if (offeringDefinition == _nullOfferingDefinition) {
			return null;
		}

		if (offeringDefinition == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				offeringDefinition = (OfferingDefinition)session.get(OfferingDefinitionImpl.class,
						Long.valueOf(offeringDefinitionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (offeringDefinition != null) {
					cacheResult(offeringDefinition);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						OfferingDefinitionImpl.class, offeringDefinitionId,
						_nullOfferingDefinition);
				}

				closeSession(session);
			}
		}

		return offeringDefinition;
	}

	/**
	 * Returns all the offering definitions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByProductEntryId(long productEntryId)
		throws SystemException {
		return findByProductEntryId(productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByProductEntryId(long productEntryId,
		int start, int end) throws SystemException {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] { productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] {
					productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = (List<OfferingDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingDefinition offeringDefinition : list) {
				if ((productEntryId != offeringDefinition.getProductEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				list = (List<OfferingDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByProductEntryId_First(productEntryId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByProductEntryId_First(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<OfferingDefinition> list = findByProductEntryId(productEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByProductEntryId_Last(productEntryId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByProductEntryId(productEntryId);

		List<OfferingDefinition> list = findByProductEntryId(productEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition[] findByProductEntryId_PrevAndNext(
		long offeringDefinitionId, long productEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(session,
					offeringDefinition, productEntryId, orderByComparator, true);

			array[1] = offeringDefinition;

			array[2] = getByProductEntryId_PrevAndNext(session,
					offeringDefinition, productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingDefinition getByProductEntryId_PrevAndNext(
		Session session, OfferingDefinition offeringDefinition,
		long productEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering definitions where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @return the matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId) throws SystemException {
		return findBySupportResponseId(supportResponseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end) throws SystemException {
		return findBySupportResponseId(supportResponseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID;
			finderArgs = new Object[] { supportResponseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTRESPONSEID;
			finderArgs = new Object[] {
					supportResponseId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = (List<OfferingDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingDefinition offeringDefinition : list) {
				if ((supportResponseId != offeringDefinition.getSupportResponseId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportResponseId);

				list = (List<OfferingDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findBySupportResponseId_First(
		long supportResponseId, OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchBySupportResponseId_First(supportResponseId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchBySupportResponseId_First(
		long supportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		List<OfferingDefinition> list = findBySupportResponseId(supportResponseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findBySupportResponseId_Last(
		long supportResponseId, OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchBySupportResponseId_Last(supportResponseId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchBySupportResponseId_Last(
		long supportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySupportResponseId(supportResponseId);

		List<OfferingDefinition> list = findBySupportResponseId(supportResponseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition[] findBySupportResponseId_PrevAndNext(
		long offeringDefinitionId, long supportResponseId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getBySupportResponseId_PrevAndNext(session,
					offeringDefinition, supportResponseId, orderByComparator,
					true);

			array[1] = offeringDefinition;

			array[2] = getBySupportResponseId_PrevAndNext(session,
					offeringDefinition, supportResponseId, orderByComparator,
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

	protected OfferingDefinition getBySupportResponseId_PrevAndNext(
		Session session, OfferingDefinition offeringDefinition,
		long supportResponseId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportResponseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @return the matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId) throws SystemException {
		return findByPEI_SRI(productEntryId, supportResponseId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end) throws SystemException {
		return findByPEI_SRI(productEntryId, supportResponseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI;
			finderArgs = new Object[] { productEntryId, supportResponseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI;
			finderArgs = new Object[] {
					productEntryId, supportResponseId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = (List<OfferingDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingDefinition offeringDefinition : list) {
				if ((productEntryId != offeringDefinition.getProductEntryId()) ||
						(supportResponseId != offeringDefinition.getSupportResponseId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				list = (List<OfferingDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByPEI_SRI_First(long productEntryId,
		long supportResponseId, OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_First(productEntryId,
				supportResponseId, orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByPEI_SRI_First(long productEntryId,
		long supportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		List<OfferingDefinition> list = findByPEI_SRI(productEntryId,
				supportResponseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByPEI_SRI_Last(long productEntryId,
		long supportResponseId, OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_Last(productEntryId,
				supportResponseId, orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByPEI_SRI_Last(long productEntryId,
		long supportResponseId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPEI_SRI(productEntryId, supportResponseId);

		List<OfferingDefinition> list = findByPEI_SRI(productEntryId,
				supportResponseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition[] findByPEI_SRI_PrevAndNext(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		OrderByComparator orderByComparator)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getByPEI_SRI_PrevAndNext(session, offeringDefinition,
					productEntryId, supportResponseId, orderByComparator, true);

			array[1] = offeringDefinition;

			array[2] = getByPEI_SRI_PrevAndNext(session, offeringDefinition,
					productEntryId, supportResponseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingDefinition getByPEI_SRI_PrevAndNext(Session session,
		OfferingDefinition offeringDefinition, long productEntryId,
		long supportResponseId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		qPos.add(supportResponseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @return the matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds) throws SystemException {
		return findByPEI_SRI(productEntryIds, supportResponseIds,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds, int start, int end)
		throws SystemException {
		return findByPEI_SRI(productEntryIds, supportResponseIds, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(productEntryIds),
					StringUtil.merge(supportResponseIds)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(productEntryIds),
					StringUtil.merge(supportResponseIds),
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = (List<OfferingDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OfferingDefinition offeringDefinition : list) {
				if (!ArrayUtil.contains(productEntryIds,
							offeringDefinition.getProductEntryId()) ||
						!ArrayUtil.contains(supportResponseIds,
							offeringDefinition.getSupportResponseId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			boolean conjunctionable = false;

			if ((productEntryIds == null) || (productEntryIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < productEntryIds.length; i++) {
					query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_5);

					if ((i + 1) < productEntryIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((supportResponseIds == null) ||
					(supportResponseIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < supportResponseIds.length; i++) {
					query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_5);

					if ((i + 1) < supportResponseIds.length) {
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

			else {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (productEntryIds != null) {
					qPos.add(productEntryIds);
				}

				if (supportResponseIds != null) {
					qPos.add(supportResponseIds);
				}

				list = (List<OfferingDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or throws a {@link com.liferay.osb.NoSuchOfferingDefinitionException} if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the matching offering definition
	 * @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition findByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_PD_L_UL_ST(productEntryId,
				supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets);

		if (offeringDefinition == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("productEntryId=");
			msg.append(productEntryId);

			msg.append(", supportResponseId=");
			msg.append(supportResponseId);

			msg.append(", productDescription=");
			msg.append(productDescription);

			msg.append(", licenses=");
			msg.append(licenses);

			msg.append(", unlimitedLicenses=");
			msg.append(unlimitedLicenses);

			msg.append(", supportTickets=");
			msg.append(supportTickets);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOfferingDefinitionException(msg.toString());
		}

		return offeringDefinition;
	}

	/**
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws SystemException {
		return fetchByPEI_SRI_PD_L_UL_ST(productEntryId, supportResponseId,
			productDescription, licenses, unlimitedLicenses, supportTickets,
			true);
	}

	/**
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				productEntryId, supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
					finderArgs, this);
		}

		if (result instanceof OfferingDefinition) {
			OfferingDefinition offeringDefinition = (OfferingDefinition)result;

			if ((productEntryId != offeringDefinition.getProductEntryId()) ||
					(supportResponseId != offeringDefinition.getSupportResponseId()) ||
					!Validator.equals(productDescription,
						offeringDefinition.getProductDescription()) ||
					(licenses != offeringDefinition.getLicenses()) ||
					(unlimitedLicenses != offeringDefinition.getUnlimitedLicenses()) ||
					(supportTickets != offeringDefinition.getSupportTickets())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2);

			if (productDescription == null) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1);
			}
			else {
				if (productDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2);
				}
			}

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2);

			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				if (productDescription != null) {
					qPos.add(productDescription);
				}

				qPos.add(licenses);

				qPos.add(unlimitedLicenses);

				qPos.add(supportTickets);

				List<OfferingDefinition> list = q.list();

				result = list;

				OfferingDefinition offeringDefinition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
						finderArgs, list);
				}
				else {
					offeringDefinition = list.get(0);

					cacheResult(offeringDefinition);

					if ((offeringDefinition.getProductEntryId() != productEntryId) ||
							(offeringDefinition.getSupportResponseId() != supportResponseId) ||
							(offeringDefinition.getProductDescription() == null) ||
							!offeringDefinition.getProductDescription()
												   .equals(productDescription) ||
							(offeringDefinition.getLicenses() != licenses) ||
							(offeringDefinition.getUnlimitedLicenses() != unlimitedLicenses) ||
							(offeringDefinition.getSupportTickets() != supportTickets)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
							finderArgs, offeringDefinition);
					}
				}

				return offeringDefinition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
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
				return (OfferingDefinition)result;
			}
		}
	}

	/**
	 * Returns all the offering definitions.
	 *
	 * @return the offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OfferingDefinition> findAll(int start, int end,
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

		List<OfferingDefinition> list = (List<OfferingDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OFFERINGDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGDEFINITION.concat(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
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
	 * Removes all the offering definitions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByProductEntryId(long productEntryId)
		throws SystemException {
		for (OfferingDefinition offeringDefinition : findByProductEntryId(
				productEntryId)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Removes all the offering definitions where supportResponseId = &#63; from the database.
	 *
	 * @param supportResponseId the support response ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportResponseId(long supportResponseId)
		throws SystemException {
		for (OfferingDefinition offeringDefinition : findBySupportResponseId(
				supportResponseId)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Removes all the offering definitions where productEntryId = &#63; and supportResponseId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPEI_SRI(long productEntryId, long supportResponseId)
		throws SystemException {
		for (OfferingDefinition offeringDefinition : findByPEI_SRI(
				productEntryId, supportResponseId)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Removes the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the offering definition that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OfferingDefinition removeByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException, SystemException {
		OfferingDefinition offeringDefinition = findByPEI_SRI_PD_L_UL_ST(productEntryId,
				supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets);

		return remove(offeringDefinition);
	}

	/**
	 * Removes all the offering definitions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OfferingDefinition offeringDefinition : findAll()) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByProductEntryId(long productEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering definitions where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @return the number of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportResponseId(long supportResponseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportResponseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportResponseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @return the number of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_SRI(long productEntryId, long supportResponseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { productEntryId, supportResponseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PEI_SRI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_SRI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @return the number of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_SRI(long[] productEntryIds, long[] supportResponseIds)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(productEntryIds),
				StringUtil.merge(supportResponseIds)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			boolean conjunctionable = false;

			if ((productEntryIds == null) || (productEntryIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < productEntryIds.length; i++) {
					query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_5);

					if ((i + 1) < productEntryIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((supportResponseIds == null) ||
					(supportResponseIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < supportResponseIds.length; i++) {
					query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_5);

					if ((i + 1) < supportResponseIds.length) {
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

				if (productEntryIds != null) {
					qPos.add(productEntryIds);
				}

				if (supportResponseIds != null) {
					qPos.add(supportResponseIds);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the number of matching offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				productEntryId, supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2);

			if (productDescription == null) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1);
			}
			else {
				if (productDescription.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2);
				}
			}

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				if (productDescription != null) {
					qPos.add(productDescription);
				}

				qPos.add(licenses);

				qPos.add(unlimitedLicenses);

				qPos.add(supportTickets);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of offering definitions.
	 *
	 * @return the number of offering definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGDEFINITION);

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
	 * Returns all the offering bundles associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @return the offering bundles associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk) throws SystemException {
		return getOfferingBundles(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the offering bundles associated with the offering definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the offering definition
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of offering bundles associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end) throws SystemException {
		return getOfferingBundles(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_OFFERINGBUNDLES = new FinderPath(com.liferay.osb.model.impl.OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			com.liferay.osb.model.impl.OfferingBundleImpl.class,
			OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"getOfferingBundles",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_OFFERINGBUNDLES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the offering bundles associated with the offering definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the offering definition
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering bundles associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.OfferingBundle> list = (List<com.liferay.osb.model.OfferingBundle>)FinderCacheUtil.getResult(FINDER_PATH_GET_OFFERINGBUNDLES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETOFFERINGBUNDLES.concat(ORDER_BY_CLAUSE)
												 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETOFFERINGBUNDLES.concat(com.liferay.osb.model.impl.OfferingBundleModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_OfferingBundle",
					com.liferay.osb.model.impl.OfferingBundleImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.OfferingBundle>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_OFFERINGBUNDLES,
						finderArgs);
				}
				else {
					offeringBundlePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_OFFERINGBUNDLES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_OFFERINGBUNDLES_SIZE = new FinderPath(com.liferay.osb.model.impl.OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			Long.class,
			OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"getOfferingBundlesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_OFFERINGBUNDLES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of offering bundles associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @return the number of offering bundles associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public int getOfferingBundlesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_OFFERINGBUNDLES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETOFFERINGBUNDLESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_OFFERINGBUNDLES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_OFFERINGBUNDLE = new FinderPath(com.liferay.osb.model.impl.OfferingBundleModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS,
			Boolean.class,
			OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME,
			"containsOfferingBundle",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the offering bundle is associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 * @return <code>true</code> if the offering bundle is associated with the offering definition; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOfferingBundle(long pk, long offeringBundlePK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, offeringBundlePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_OFFERINGBUNDLE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsOfferingBundle.contains(pk,
							offeringBundlePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_OFFERINGBUNDLE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the offering definition has any offering bundles associated with it.
	 *
	 * @param pk the primary key of the offering definition to check for associations with offering bundles
	 * @return <code>true</code> if the offering definition has any offering bundles associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOfferingBundles(long pk) throws SystemException {
		if (getOfferingBundlesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingBundle(long pk, long offeringBundlePK)
		throws SystemException {
		try {
			addOfferingBundle.add(pk, offeringBundlePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundle the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws SystemException {
		try {
			addOfferingBundle.add(pk, offeringBundle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingBundles(long pk, long[] offeringBundlePKs)
		throws SystemException {
		try {
			for (long offeringBundlePK : offeringBundlePKs) {
				addOfferingBundle.add(pk, offeringBundlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public void addOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws SystemException {
		try {
			for (com.liferay.osb.model.OfferingBundle offeringBundle : offeringBundles) {
				addOfferingBundle.add(pk, offeringBundle.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the offering definition and its offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition to clear the associated offering bundles from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearOfferingBundles(long pk) throws SystemException {
		try {
			clearOfferingBundles.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingBundle(long pk, long offeringBundlePK)
		throws SystemException {
		try {
			removeOfferingBundle.remove(pk, offeringBundlePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundle the offering bundle
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws SystemException {
		try {
			removeOfferingBundle.remove(pk, offeringBundle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingBundles(long pk, long[] offeringBundlePKs)
		throws SystemException {
		try {
			for (long offeringBundlePK : offeringBundlePKs) {
				removeOfferingBundle.remove(pk, offeringBundlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles
	 * @throws SystemException if a system exception occurred
	 */
	public void removeOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws SystemException {
		try {
			for (com.liferay.osb.model.OfferingBundle offeringBundle : offeringBundles) {
				removeOfferingBundle.remove(pk, offeringBundle.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles to be associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public void setOfferingBundles(long pk, long[] offeringBundlePKs)
		throws SystemException {
		try {
			Set<Long> offeringBundlePKSet = SetUtil.fromArray(offeringBundlePKs);

			List<com.liferay.osb.model.OfferingBundle> offeringBundles = getOfferingBundles(pk);

			for (com.liferay.osb.model.OfferingBundle offeringBundle : offeringBundles) {
				if (!offeringBundlePKSet.remove(offeringBundle.getPrimaryKey())) {
					removeOfferingBundle.remove(pk,
						offeringBundle.getPrimaryKey());
				}
			}

			for (Long offeringBundlePK : offeringBundlePKSet) {
				addOfferingBundle.add(pk, offeringBundlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles to be associated with the offering definition
	 * @throws SystemException if a system exception occurred
	 */
	public void setOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws SystemException {
		try {
			long[] offeringBundlePKs = new long[offeringBundles.size()];

			for (int i = 0; i < offeringBundles.size(); i++) {
				com.liferay.osb.model.OfferingBundle offeringBundle = offeringBundles.get(i);

				offeringBundlePKs[i] = offeringBundle.getPrimaryKey();
			}

			setOfferingBundles(pk, offeringBundlePKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(OfferingDefinitionModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_NAME);
		}
	}

	/**
	 * Initializes the offering definition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.OfferingDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OfferingDefinition>> listenersList = new ArrayList<ModelListener<OfferingDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<OfferingDefinition>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsOfferingBundle = new ContainsOfferingBundle();

		addOfferingBundle = new AddOfferingBundle();
		clearOfferingBundles = new ClearOfferingBundles();
		removeOfferingBundle = new RemoveOfferingBundle();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OfferingDefinitionImpl.class.getName());
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
	protected ContainsOfferingBundle containsOfferingBundle;
	protected AddOfferingBundle addOfferingBundle;
	protected ClearOfferingBundles clearOfferingBundles;
	protected RemoveOfferingBundle removeOfferingBundle;

	protected class ContainsOfferingBundle {
		protected ContainsOfferingBundle() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSOFFERINGBUNDLE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long offeringDefinitionId,
			long offeringBundleId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(offeringDefinitionId),
						new Long(offeringBundleId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddOfferingBundle {
		protected AddOfferingBundle() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_OfferingBundles_OfferingDefinitions (offeringDefinitionId, offeringBundleId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long offeringDefinitionId, long offeringBundleId)
			throws SystemException {
			if (!containsOfferingBundle.contains(offeringDefinitionId,
						offeringBundleId)) {
				ModelListener<com.liferay.osb.model.OfferingBundle>[] offeringBundleListeners =
					offeringBundlePersistence.getListeners();

				for (ModelListener<OfferingDefinition> listener : listeners) {
					listener.onBeforeAddAssociation(offeringDefinitionId,
						com.liferay.osb.model.OfferingBundle.class.getName(),
						offeringBundleId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
					listener.onBeforeAddAssociation(offeringBundleId,
						OfferingDefinition.class.getName(), offeringDefinitionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(offeringDefinitionId),
						new Long(offeringBundleId)
					});

				for (ModelListener<OfferingDefinition> listener : listeners) {
					listener.onAfterAddAssociation(offeringDefinitionId,
						com.liferay.osb.model.OfferingBundle.class.getName(),
						offeringBundleId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
					listener.onAfterAddAssociation(offeringBundleId,
						OfferingDefinition.class.getName(), offeringDefinitionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearOfferingBundles {
		protected ClearOfferingBundles() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringDefinitionId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long offeringDefinitionId)
			throws SystemException {
			ModelListener<com.liferay.osb.model.OfferingBundle>[] offeringBundleListeners =
				offeringBundlePersistence.getListeners();

			List<com.liferay.osb.model.OfferingBundle> offeringBundles = null;

			if ((listeners.length > 0) || (offeringBundleListeners.length > 0)) {
				offeringBundles = getOfferingBundles(offeringDefinitionId);

				for (com.liferay.osb.model.OfferingBundle offeringBundle : offeringBundles) {
					for (ModelListener<OfferingDefinition> listener : listeners) {
						listener.onBeforeRemoveAssociation(offeringDefinitionId,
							com.liferay.osb.model.OfferingBundle.class.getName(),
							offeringBundle.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
						listener.onBeforeRemoveAssociation(offeringBundle.getPrimaryKey(),
							OfferingDefinition.class.getName(),
							offeringDefinitionId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(offeringDefinitionId) });

			if ((listeners.length > 0) || (offeringBundleListeners.length > 0)) {
				for (com.liferay.osb.model.OfferingBundle offeringBundle : offeringBundles) {
					for (ModelListener<OfferingDefinition> listener : listeners) {
						listener.onAfterRemoveAssociation(offeringDefinitionId,
							com.liferay.osb.model.OfferingBundle.class.getName(),
							offeringBundle.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
						listener.onAfterRemoveAssociation(offeringBundle.getPrimaryKey(),
							OfferingDefinition.class.getName(),
							offeringDefinitionId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveOfferingBundle {
		protected RemoveOfferingBundle() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringDefinitionId = ? AND offeringBundleId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long offeringDefinitionId, long offeringBundleId)
			throws SystemException {
			if (containsOfferingBundle.contains(offeringDefinitionId,
						offeringBundleId)) {
				ModelListener<com.liferay.osb.model.OfferingBundle>[] offeringBundleListeners =
					offeringBundlePersistence.getListeners();

				for (ModelListener<OfferingDefinition> listener : listeners) {
					listener.onBeforeRemoveAssociation(offeringDefinitionId,
						com.liferay.osb.model.OfferingBundle.class.getName(),
						offeringBundleId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
					listener.onBeforeRemoveAssociation(offeringBundleId,
						OfferingDefinition.class.getName(), offeringDefinitionId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(offeringDefinitionId),
						new Long(offeringBundleId)
					});

				for (ModelListener<OfferingDefinition> listener : listeners) {
					listener.onAfterRemoveAssociation(offeringDefinitionId,
						com.liferay.osb.model.OfferingBundle.class.getName(),
						offeringBundleId);
				}

				for (ModelListener<com.liferay.osb.model.OfferingBundle> listener : offeringBundleListeners) {
					listener.onAfterRemoveAssociation(offeringBundleId,
						OfferingDefinition.class.getName(), offeringDefinitionId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_OFFERINGDEFINITION = "SELECT offeringDefinition FROM OfferingDefinition offeringDefinition";
	private static final String _SQL_SELECT_OFFERINGDEFINITION_WHERE = "SELECT offeringDefinition FROM OfferingDefinition offeringDefinition WHERE ";
	private static final String _SQL_COUNT_OFFERINGDEFINITION = "SELECT COUNT(offeringDefinition) FROM OfferingDefinition offeringDefinition";
	private static final String _SQL_COUNT_OFFERINGDEFINITION_WHERE = "SELECT COUNT(offeringDefinition) FROM OfferingDefinition offeringDefinition WHERE ";
	private static final String _SQL_GETOFFERINGBUNDLES = "SELECT {OSB_OfferingBundle.*} FROM OSB_OfferingBundle INNER JOIN OSB_OfferingBundles_OfferingDefinitions ON (OSB_OfferingBundles_OfferingDefinitions.offeringBundleId = OSB_OfferingBundle.offeringBundleId) WHERE (OSB_OfferingBundles_OfferingDefinitions.offeringDefinitionId = ?)";
	private static final String _SQL_GETOFFERINGBUNDLESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringDefinitionId = ?";
	private static final String _SQL_CONTAINSOFFERINGBUNDLE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_OfferingBundles_OfferingDefinitions WHERE offeringDefinitionId = ? AND offeringBundleId = ?";
	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 = "offeringDefinition.productEntryId = ?";
	private static final String _FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2 =
		"offeringDefinition.supportResponseId = ?";
	private static final String _FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2 = "offeringDefinition.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2 = "offeringDefinition.supportResponseId = ?";
	private static final String _FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2) + ")";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2 =
		"offeringDefinition.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2 =
		"offeringDefinition.supportResponseId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1 =
		"offeringDefinition.productDescription IS NULL AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2 =
		"offeringDefinition.productDescription = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3 =
		"(offeringDefinition.productDescription IS NULL OR offeringDefinition.productDescription = ?) AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2 = "offeringDefinition.licenses = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2 =
		"offeringDefinition.unlimitedLicenses = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2 =
		"offeringDefinition.supportTickets = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingDefinition exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OfferingDefinitionPersistenceImpl.class);
	private static OfferingDefinition _nullOfferingDefinition = new OfferingDefinitionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OfferingDefinition> toCacheModel() {
				return _nullOfferingDefinitionCacheModel;
			}
		};

	private static CacheModel<OfferingDefinition> _nullOfferingDefinitionCacheModel =
		new CacheModel<OfferingDefinition>() {
			public OfferingDefinition toEntityModel() {
				return _nullOfferingDefinition;
			}
		};
}