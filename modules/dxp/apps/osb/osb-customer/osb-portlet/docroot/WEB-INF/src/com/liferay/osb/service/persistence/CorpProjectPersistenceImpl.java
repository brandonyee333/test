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

import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.model.impl.CorpProjectModelImpl;

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
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the corp project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistence
 * @see CorpProjectUtil
 * @generated
 */
public class CorpProjectPersistenceImpl extends BasePersistenceImpl<CorpProject>
	implements CorpProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpProjectUtil} to access the corp project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossieraProjectKey",
			new String[] { String.class.getName() },
			CorpProjectModelImpl.DOSSIERAPROJECTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraProjectKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ORGANIZATIONID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOrganizationId",
			new String[] { Long.class.getName() },
			CorpProjectModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the corp project in the entity cache if it is enabled.
	 *
	 * @param corpProject the corp project
	 */
	public void cacheResult(CorpProject corpProject) {
		EntityCacheUtil.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
			new Object[] { corpProject.getDossieraProjectKey() }, corpProject);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
			new Object[] { Long.valueOf(corpProject.getOrganizationId()) },
			corpProject);

		corpProject.resetOriginalValues();
	}

	/**
	 * Caches the corp projects in the entity cache if it is enabled.
	 *
	 * @param corpProjects the corp projects
	 */
	public void cacheResult(List<CorpProject> corpProjects) {
		for (CorpProject corpProject : corpProjects) {
			if (EntityCacheUtil.getResult(
						CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, corpProject.getPrimaryKey()) == null) {
				cacheResult(corpProject);
			}
			else {
				corpProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp projects.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CorpProjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CorpProjectImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp project.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpProject corpProject) {
		EntityCacheUtil.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(corpProject);
	}

	@Override
	public void clearCache(List<CorpProject> corpProjects) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpProject corpProject : corpProjects) {
			EntityCacheUtil.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, corpProject.getPrimaryKey());

			clearUniqueFindersCache(corpProject);
		}
	}

	protected void cacheUniqueFindersCache(CorpProject corpProject) {
		if (corpProject.isNew()) {
			Object[] args = new Object[] { corpProject.getDossieraProjectKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
				args, corpProject);

			args = new Object[] { Long.valueOf(corpProject.getOrganizationId()) };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args, corpProject);
		}
		else {
			CorpProjectModelImpl corpProjectModelImpl = (CorpProjectModelImpl)corpProject;

			if ((corpProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { corpProject.getDossieraProjectKey() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
					args, corpProject);
			}

			if ((corpProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(corpProject.getOrganizationId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					args, corpProject);
			}
		}
	}

	protected void clearUniqueFindersCache(CorpProject corpProject) {
		CorpProjectModelImpl corpProjectModelImpl = (CorpProjectModelImpl)corpProject;

		Object[] args = new Object[] { corpProject.getDossieraProjectKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
			args);

		if ((corpProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					corpProjectModelImpl.getOriginalDossieraProjectKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
				args);
		}

		args = new Object[] { Long.valueOf(corpProject.getOrganizationId()) };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);

		if ((corpProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(corpProjectModelImpl.getOriginalOrganizationId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
				args);
		}
	}

	/**
	 * Creates a new corp project with the primary key. Does not add the corp project to the database.
	 *
	 * @param corpProjectId the primary key for the new corp project
	 * @return the new corp project
	 */
	public CorpProject create(long corpProjectId) {
		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setNew(true);
		corpProject.setPrimaryKey(corpProjectId);

		return corpProject;
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject remove(long corpProjectId)
		throws NoSuchCorpProjectException, SystemException {
		return remove(Long.valueOf(corpProjectId));
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProject remove(Serializable primaryKey)
		throws NoSuchCorpProjectException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CorpProject corpProject = (CorpProject)session.get(CorpProjectImpl.class,
					primaryKey);

			if (corpProject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpProject);
		}
		catch (NoSuchCorpProjectException nsee) {
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
	protected CorpProject removeImpl(CorpProject corpProject)
		throws SystemException {
		corpProject = toUnwrappedModel(corpProject);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, corpProject);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(corpProject);

		return corpProject;
	}

	@Override
	public CorpProject updateImpl(
		com.liferay.osb.model.CorpProject corpProject, boolean merge)
		throws SystemException {
		corpProject = toUnwrappedModel(corpProject);

		boolean isNew = corpProject.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, corpProject, merge);

			corpProject.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CorpProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject);

		clearUniqueFindersCache(corpProject);
		cacheUniqueFindersCache(corpProject);

		return corpProject;
	}

	protected CorpProject toUnwrappedModel(CorpProject corpProject) {
		if (corpProject instanceof CorpProjectImpl) {
			return corpProject;
		}

		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		corpProjectImpl.setNew(corpProject.isNew());
		corpProjectImpl.setPrimaryKey(corpProject.getPrimaryKey());

		corpProjectImpl.setCorpProjectId(corpProject.getCorpProjectId());
		corpProjectImpl.setUserId(corpProject.getUserId());
		corpProjectImpl.setUserName(corpProject.getUserName());
		corpProjectImpl.setCreateDate(corpProject.getCreateDate());
		corpProjectImpl.setModifiedDate(corpProject.getModifiedDate());
		corpProjectImpl.setDossieraProjectKey(corpProject.getDossieraProjectKey());
		corpProjectImpl.setSalesforceProjectKey(corpProject.getSalesforceProjectKey());
		corpProjectImpl.setName(corpProject.getName());
		corpProjectImpl.setOrganizationId(corpProject.getOrganizationId());

		return corpProjectImpl;
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project
	 * @throws com.liferay.portal.NoSuchModelException if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject findByPrimaryKey(long corpProjectId)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = fetchByPrimaryKey(corpProjectId);

		if (corpProject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + corpProjectId);
			}

			throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				corpProjectId);
		}

		return corpProject;
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CorpProject fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByPrimaryKey(long corpProjectId)
		throws SystemException {
		CorpProject corpProject = (CorpProject)EntityCacheUtil.getResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, corpProjectId);

		if (corpProject == _nullCorpProject) {
			return null;
		}

		if (corpProject == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				corpProject = (CorpProject)session.get(CorpProjectImpl.class,
						Long.valueOf(corpProjectId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (corpProject != null) {
					cacheResult(corpProject);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, corpProjectId, _nullCorpProject);
				}

				closeSession(session);
			}
		}

		return corpProject;
	}

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the matching corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject findByDossieraProjectKey(String dossieraProjectKey)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = fetchByDossieraProjectKey(dossieraProjectKey);

		if (corpProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraProjectKey=");
			msg.append(dossieraProjectKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpProjectException(msg.toString());
		}

		return corpProject;
	}

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByDossieraProjectKey(String dossieraProjectKey)
		throws SystemException {
		return fetchByDossieraProjectKey(dossieraProjectKey, true);
	}

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByDossieraProjectKey(String dossieraProjectKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { dossieraProjectKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
					finderArgs, this);
		}

		if (result instanceof CorpProject) {
			CorpProject corpProject = (CorpProject)result;

			if (!Validator.equals(dossieraProjectKey,
						corpProject.getDossieraProjectKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			if (dossieraProjectKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1);
			}
			else {
				if (dossieraProjectKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (dossieraProjectKey != null) {
					qPos.add(dossieraProjectKey);
				}

				List<CorpProject> list = q.list();

				result = list;

				CorpProject corpProject = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
						finderArgs, list);
				}
				else {
					corpProject = list.get(0);

					cacheResult(corpProject);

					if ((corpProject.getDossieraProjectKey() == null) ||
							!corpProject.getDossieraProjectKey()
											.equals(dossieraProjectKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
							finderArgs, corpProject);
					}
				}

				return corpProject;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
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
				return (CorpProject)result;
			}
		}
	}

	/**
	 * Returns all the corp projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findByName(String name) throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findByName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
		finderArgs = new Object[] { name, start, end, orderByComparator };

		List<CorpProject> list = (List<CorpProject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CorpProject corpProject : list) {
				if (!Validator.equals(name, corpProject.getName())) {
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

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

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

				if (name != null) {
					qPos.add(name);
				}

				list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = fetchByName_First(name, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the first corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<CorpProject> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = fetchByName_Last(name, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the last corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		List<CorpProject> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp projects before and after the current corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param corpProjectId the primary key of the current corp project
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject[] findByName_PrevAndNext(long corpProjectId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = findByPrimaryKey(corpProjectId);

		Session session = null;

		try {
			session = openSession();

			CorpProject[] array = new CorpProjectImpl[3];

			array[0] = getByName_PrevAndNext(session, corpProject, name,
					orderByComparator, true);

			array[1] = corpProject;

			array[2] = getByName_PrevAndNext(session, corpProject, name,
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

	protected CorpProject getByName_PrevAndNext(Session session,
		CorpProject corpProject, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECT_WHERE);

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

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the corp project where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp project
	 * @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject findByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = fetchByOrganizationId(organizationId);

		if (corpProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCorpProjectException(msg.toString());
		}

		return corpProject;
	}

	/**
	 * Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByOrganizationId(long organizationId)
		throws SystemException {
		return fetchByOrganizationId(organizationId, true);
	}

	/**
	 * Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs, this);
		}

		if (result instanceof CorpProject) {
			CorpProject corpProject = (CorpProject)result;

			if ((organizationId != corpProject.getOrganizationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				List<CorpProject> list = q.list();

				result = list;

				CorpProject corpProject = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
						finderArgs, list);
				}
				else {
					corpProject = list.get(0);

					cacheResult(corpProject);

					if ((corpProject.getOrganizationId() != organizationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
							finderArgs, corpProject);
					}
				}

				return corpProject;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
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
				return (CorpProject)result;
			}
		}
	}

	/**
	 * Returns all the corp projects.
	 *
	 * @return the corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<CorpProject> findAll(int start, int end,
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

		List<CorpProject> list = (List<CorpProject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CORPPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPPROJECT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes the corp project where dossieraProjectKey = &#63; from the database.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the corp project that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject removeByDossieraProjectKey(String dossieraProjectKey)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = findByDossieraProjectKey(dossieraProjectKey);

		return remove(corpProject);
	}

	/**
	 * Removes all the corp projects where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByName(String name) throws SystemException {
		for (CorpProject corpProject : findByName(name)) {
			remove(corpProject);
		}
	}

	/**
	 * Removes the corp project where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the corp project that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CorpProject removeByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException, SystemException {
		CorpProject corpProject = findByOrganizationId(organizationId);

		return remove(corpProject);
	}

	/**
	 * Removes all the corp projects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CorpProject corpProject : findAll()) {
			remove(corpProject);
		}
	}

	/**
	 * Returns the number of corp projects where dossieraProjectKey = &#63;.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the number of matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDossieraProjectKey(String dossieraProjectKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { dossieraProjectKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			if (dossieraProjectKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1);
			}
			else {
				if (dossieraProjectKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (dossieraProjectKey != null) {
					qPos.add(dossieraProjectKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp projects where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationId(long organizationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of corp projects.
	 *
	 * @return the number of corp projects
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPPROJECT);

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
	 * Initializes the corp project persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.CorpProject")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CorpProject>> listenersList = new ArrayList<ModelListener<CorpProject>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<CorpProject>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CorpProjectImpl.class.getName());
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
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	private static final String _SQL_SELECT_CORPPROJECT = "SELECT corpProject FROM CorpProject corpProject";
	private static final String _SQL_SELECT_CORPPROJECT_WHERE = "SELECT corpProject FROM CorpProject corpProject WHERE ";
	private static final String _SQL_COUNT_CORPPROJECT = "SELECT COUNT(corpProject) FROM CorpProject corpProject";
	private static final String _SQL_COUNT_CORPPROJECT_WHERE = "SELECT COUNT(corpProject) FROM CorpProject corpProject WHERE ";
	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1 =
		"corpProject.dossieraProjectKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2 =
		"corpProject.dossieraProjectKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3 =
		"(corpProject.dossieraProjectKey IS NULL OR corpProject.dossieraProjectKey = ?)";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "corpProject.name LIKE NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "corpProject.name LIKE ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(corpProject.name IS NULL OR corpProject.name LIKE ?)";
	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "corpProject.organizationId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpProject exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CorpProjectPersistenceImpl.class);
	private static CorpProject _nullCorpProject = new CorpProjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CorpProject> toCacheModel() {
				return _nullCorpProjectCacheModel;
			}
		};

	private static CacheModel<CorpProject> _nullCorpProjectCacheModel = new CacheModel<CorpProject>() {
			public CorpProject toEntityModel() {
				return _nullCorpProject;
			}
		};
}