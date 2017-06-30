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

import com.liferay.osb.NoSuchTrainingEventException;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.impl.TrainingEventImpl;
import com.liferay.osb.model.impl.TrainingEventModelImpl;

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
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.dynamicdatalists.service.persistence.DDLRecordSetPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the training event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingEventPersistence
 * @see TrainingEventUtil
 * @generated
 */
public class TrainingEventPersistenceImpl extends BasePersistenceImpl<TrainingEvent>
	implements TrainingEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrainingEventUtil} to access the training event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrainingEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_DDLRECORDSETID = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByDDLRecordSetId", new String[] { Long.class.getName() },
			TrainingEventModelImpl.DDLRECORDSETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DDLRECORDSETID = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDDLRecordSetId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTrainingCertificateTemplateId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTrainingCertificateTemplateId",
			new String[] { Long.class.getName() },
			TrainingEventModelImpl.TRAININGCERTIFICATETEMPLATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCertificateTemplateId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCOURSEID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTrainingCourseId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCOURSEID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTrainingCourseId", new String[] { Long.class.getName() },
			TrainingEventModelImpl.TRAININGCOURSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGCOURSEID = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingCourseId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGLOCATIONID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTrainingLocationId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGLOCATIONID =
		new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTrainingLocationId", new String[] { Long.class.getName() },
			TrainingEventModelImpl.TRAININGLOCATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRAININGLOCATIONID = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTrainingLocationId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_SD = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_SD",
			new String[] {
				Integer.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_SD = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByT_SD",
			new String[] { Integer.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TC_T_SD = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTC_T_SD",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TC_T_SD = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTC_T_SD",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED,
			TrainingEventImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the training event in the entity cache if it is enabled.
	 *
	 * @param trainingEvent the training event
	 */
	public void cacheResult(TrainingEvent trainingEvent) {
		EntityCacheUtil.putResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventImpl.class, trainingEvent.getPrimaryKey(),
			trainingEvent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
			new Object[] { Long.valueOf(trainingEvent.getDDLRecordSetId()) },
			trainingEvent);

		trainingEvent.resetOriginalValues();
	}

	/**
	 * Caches the training events in the entity cache if it is enabled.
	 *
	 * @param trainingEvents the training events
	 */
	public void cacheResult(List<TrainingEvent> trainingEvents) {
		for (TrainingEvent trainingEvent : trainingEvents) {
			if (EntityCacheUtil.getResult(
						TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
						TrainingEventImpl.class, trainingEvent.getPrimaryKey()) == null) {
				cacheResult(trainingEvent);
			}
			else {
				trainingEvent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all training events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TrainingEventImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TrainingEventImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the training event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrainingEvent trainingEvent) {
		EntityCacheUtil.removeResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventImpl.class, trainingEvent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(trainingEvent);
	}

	@Override
	public void clearCache(List<TrainingEvent> trainingEvents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrainingEvent trainingEvent : trainingEvents) {
			EntityCacheUtil.removeResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
				TrainingEventImpl.class, trainingEvent.getPrimaryKey());

			clearUniqueFindersCache(trainingEvent);
		}
	}

	protected void cacheUniqueFindersCache(TrainingEvent trainingEvent) {
		if (trainingEvent.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(trainingEvent.getDDLRecordSetId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
				args, trainingEvent);
		}
		else {
			TrainingEventModelImpl trainingEventModelImpl = (TrainingEventModelImpl)trainingEvent;

			if ((trainingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DDLRECORDSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingEvent.getDDLRecordSetId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
					args, trainingEvent);
			}
		}
	}

	protected void clearUniqueFindersCache(TrainingEvent trainingEvent) {
		TrainingEventModelImpl trainingEventModelImpl = (TrainingEventModelImpl)trainingEvent;

		Object[] args = new Object[] {
				Long.valueOf(trainingEvent.getDDLRecordSetId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID, args);

		if ((trainingEventModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DDLRECORDSETID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(trainingEventModelImpl.getOriginalDDLRecordSetId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
				args);
		}
	}

	/**
	 * Creates a new training event with the primary key. Does not add the training event to the database.
	 *
	 * @param trainingEventId the primary key for the new training event
	 * @return the new training event
	 */
	public TrainingEvent create(long trainingEventId) {
		TrainingEvent trainingEvent = new TrainingEventImpl();

		trainingEvent.setNew(true);
		trainingEvent.setPrimaryKey(trainingEventId);

		return trainingEvent;
	}

	/**
	 * Removes the training event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trainingEventId the primary key of the training event
	 * @return the training event that was removed
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent remove(long trainingEventId)
		throws NoSuchTrainingEventException, SystemException {
		return remove(Long.valueOf(trainingEventId));
	}

	/**
	 * Removes the training event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the training event
	 * @return the training event that was removed
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingEvent remove(Serializable primaryKey)
		throws NoSuchTrainingEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TrainingEvent trainingEvent = (TrainingEvent)session.get(TrainingEventImpl.class,
					primaryKey);

			if (trainingEvent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrainingEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trainingEvent);
		}
		catch (NoSuchTrainingEventException nsee) {
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
	protected TrainingEvent removeImpl(TrainingEvent trainingEvent)
		throws SystemException {
		trainingEvent = toUnwrappedModel(trainingEvent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, trainingEvent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(trainingEvent);

		return trainingEvent;
	}

	@Override
	public TrainingEvent updateImpl(
		com.liferay.osb.model.TrainingEvent trainingEvent, boolean merge)
		throws SystemException {
		trainingEvent = toUnwrappedModel(trainingEvent);

		boolean isNew = trainingEvent.isNew();

		TrainingEventModelImpl trainingEventModelImpl = (TrainingEventModelImpl)trainingEvent;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, trainingEvent, merge);

			trainingEvent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TrainingEventModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((trainingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getOriginalTrainingCertificateTemplateId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID,
					args);

				args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getTrainingCertificateTemplateId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID,
					args);
			}

			if ((trainingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCOURSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getOriginalTrainingCourseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCOURSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCOURSEID,
					args);

				args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getTrainingCourseId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGCOURSEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCOURSEID,
					args);
			}

			if ((trainingEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGLOCATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getOriginalTrainingLocationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGLOCATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGLOCATIONID,
					args);

				args = new Object[] {
						Long.valueOf(trainingEventModelImpl.getTrainingLocationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRAININGLOCATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGLOCATIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
			TrainingEventImpl.class, trainingEvent.getPrimaryKey(),
			trainingEvent);

		clearUniqueFindersCache(trainingEvent);
		cacheUniqueFindersCache(trainingEvent);

		return trainingEvent;
	}

	protected TrainingEvent toUnwrappedModel(TrainingEvent trainingEvent) {
		if (trainingEvent instanceof TrainingEventImpl) {
			return trainingEvent;
		}

		TrainingEventImpl trainingEventImpl = new TrainingEventImpl();

		trainingEventImpl.setNew(trainingEvent.isNew());
		trainingEventImpl.setPrimaryKey(trainingEvent.getPrimaryKey());

		trainingEventImpl.setTrainingEventId(trainingEvent.getTrainingEventId());
		trainingEventImpl.setUserId(trainingEvent.getUserId());
		trainingEventImpl.setUserName(trainingEvent.getUserName());
		trainingEventImpl.setCreateDate(trainingEvent.getCreateDate());
		trainingEventImpl.setModifiedDate(trainingEvent.getModifiedDate());
		trainingEventImpl.setDDLRecordSetId(trainingEvent.getDDLRecordSetId());
		trainingEventImpl.setPartnerEntryId(trainingEvent.getPartnerEntryId());
		trainingEventImpl.setTrainingCertificateTemplateId(trainingEvent.getTrainingCertificateTemplateId());
		trainingEventImpl.setTrainingCourseId(trainingEvent.getTrainingCourseId());
		trainingEventImpl.setTrainingLocationId(trainingEvent.getTrainingLocationId());
		trainingEventImpl.setName(trainingEvent.getName());
		trainingEventImpl.setEmailAddress(trainingEvent.getEmailAddress());
		trainingEventImpl.setPortalMinorVersion(trainingEvent.getPortalMinorVersion());
		trainingEventImpl.setType(trainingEvent.getType());
		trainingEventImpl.setLanguageId(trainingEvent.getLanguageId());
		trainingEventImpl.setLocalizedSlides(trainingEvent.isLocalizedSlides());
		trainingEventImpl.setTimeZoneId(trainingEvent.getTimeZoneId());
		trainingEventImpl.setStartDate(trainingEvent.getStartDate());
		trainingEventImpl.setEndDate(trainingEvent.getEndDate());
		trainingEventImpl.setAddressId(trainingEvent.getAddressId());
		trainingEventImpl.setMaxCustomers(trainingEvent.getMaxCustomers());
		trainingEventImpl.setEnrollmentURL(trainingEvent.getEnrollmentURL());

		return trainingEventImpl;
	}

	/**
	 * Returns the training event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the training event
	 * @return the training event
	 * @throws com.liferay.portal.NoSuchModelException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training event with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	 *
	 * @param trainingEventId the primary key of the training event
	 * @return the training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByPrimaryKey(long trainingEventId)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByPrimaryKey(trainingEventId);

		if (trainingEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + trainingEventId);
			}

			throw new NoSuchTrainingEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				trainingEventId);
		}

		return trainingEvent;
	}

	/**
	 * Returns the training event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the training event
	 * @return the training event, or <code>null</code> if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrainingEvent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the training event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trainingEventId the primary key of the training event
	 * @return the training event, or <code>null</code> if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByPrimaryKey(long trainingEventId)
		throws SystemException {
		TrainingEvent trainingEvent = (TrainingEvent)EntityCacheUtil.getResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
				TrainingEventImpl.class, trainingEventId);

		if (trainingEvent == _nullTrainingEvent) {
			return null;
		}

		if (trainingEvent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				trainingEvent = (TrainingEvent)session.get(TrainingEventImpl.class,
						Long.valueOf(trainingEventId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (trainingEvent != null) {
					cacheResult(trainingEvent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TrainingEventModelImpl.ENTITY_CACHE_ENABLED,
						TrainingEventImpl.class, trainingEventId,
						_nullTrainingEvent);
				}

				closeSession(session);
			}
		}

		return trainingEvent;
	}

	/**
	 * Returns the training event where DDLRecordSetId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingEventException} if it could not be found.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByDDLRecordSetId(long DDLRecordSetId)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByDDLRecordSetId(DDLRecordSetId);

		if (trainingEvent == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("DDLRecordSetId=");
			msg.append(DDLRecordSetId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTrainingEventException(msg.toString());
		}

		return trainingEvent;
	}

	/**
	 * Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByDDLRecordSetId(long DDLRecordSetId)
		throws SystemException {
		return fetchByDDLRecordSetId(DDLRecordSetId, true);
	}

	/**
	 * Returns the training event where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByDDLRecordSetId(long DDLRecordSetId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { DDLRecordSetId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
					finderArgs, this);
		}

		if (result instanceof TrainingEvent) {
			TrainingEvent trainingEvent = (TrainingEvent)result;

			if ((DDLRecordSetId != trainingEvent.getDDLRecordSetId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2);

			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(DDLRecordSetId);

				List<TrainingEvent> list = q.list();

				result = list;

				TrainingEvent trainingEvent = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
						finderArgs, list);
				}
				else {
					trainingEvent = list.get(0);

					cacheResult(trainingEvent);

					if ((trainingEvent.getDDLRecordSetId() != DDLRecordSetId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
							finderArgs, trainingEvent);
					}
				}

				return trainingEvent;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
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
				return (TrainingEvent)result;
			}
		}
	}

	/**
	 * Returns all the training events where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @return the matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		return findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events where trainingCertificateTemplateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end)
		throws SystemException {
		return findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events where trainingCertificateTemplateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID;
			finderArgs = new Object[] { trainingCertificateTemplateId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCERTIFICATETEMPLATEID;
			finderArgs = new Object[] {
					trainingCertificateTemplateId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingEvent trainingEvent : list) {
				if ((trainingCertificateTemplateId != trainingEvent.getTrainingCertificateTemplateId())) {
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

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCertificateTemplateId);

				list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingCertificateTemplateId_First(trainingCertificateTemplateId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCertificateTemplateId=");
		msg.append(trainingCertificateTemplateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the first training event in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingCertificateTemplateId_First(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingEvent> list = findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingCertificateTemplateId_Last(trainingCertificateTemplateId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCertificateTemplateId=");
		msg.append(trainingCertificateTemplateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the last training event in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingCertificateTemplateId_Last(
		long trainingCertificateTemplateId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTrainingCertificateTemplateId(trainingCertificateTemplateId);

		List<TrainingEvent> list = findByTrainingCertificateTemplateId(trainingCertificateTemplateId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training events before and after the current training event in the ordered set where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingEventId the primary key of the current training event
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent[] findByTrainingCertificateTemplateId_PrevAndNext(
		long trainingEventId, long trainingCertificateTemplateId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByPrimaryKey(trainingEventId);

		Session session = null;

		try {
			session = openSession();

			TrainingEvent[] array = new TrainingEventImpl[3];

			array[0] = getByTrainingCertificateTemplateId_PrevAndNext(session,
					trainingEvent, trainingCertificateTemplateId,
					orderByComparator, true);

			array[1] = trainingEvent;

			array[2] = getByTrainingCertificateTemplateId_PrevAndNext(session,
					trainingEvent, trainingCertificateTemplateId,
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

	protected TrainingEvent getByTrainingCertificateTemplateId_PrevAndNext(
		Session session, TrainingEvent trainingEvent,
		long trainingCertificateTemplateId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

		query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

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
			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(trainingCertificateTemplateId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training events where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @return the matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCourseId(long trainingCourseId)
		throws SystemException {
		return findByTrainingCourseId(trainingCourseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events where trainingCourseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCourseId the training course ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCourseId(long trainingCourseId,
		int start, int end) throws SystemException {
		return findByTrainingCourseId(trainingCourseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events where trainingCourseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCourseId the training course ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingCourseId(long trainingCourseId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGCOURSEID;
			finderArgs = new Object[] { trainingCourseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGCOURSEID;
			finderArgs = new Object[] {
					trainingCourseId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingEvent trainingEvent : list) {
				if ((trainingCourseId != trainingEvent.getTrainingCourseId())) {
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

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCOURSEID_TRAININGCOURSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCourseId);

				list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training event in the ordered set where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingCourseId_First(long trainingCourseId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingCourseId_First(trainingCourseId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCourseId=");
		msg.append(trainingCourseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the first training event in the ordered set where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingCourseId_First(long trainingCourseId,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingEvent> list = findByTrainingCourseId(trainingCourseId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training event in the ordered set where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingCourseId_Last(long trainingCourseId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingCourseId_Last(trainingCourseId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCourseId=");
		msg.append(trainingCourseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the last training event in the ordered set where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingCourseId_Last(long trainingCourseId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTrainingCourseId(trainingCourseId);

		List<TrainingEvent> list = findByTrainingCourseId(trainingCourseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training events before and after the current training event in the ordered set where trainingCourseId = &#63;.
	 *
	 * @param trainingEventId the primary key of the current training event
	 * @param trainingCourseId the training course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent[] findByTrainingCourseId_PrevAndNext(
		long trainingEventId, long trainingCourseId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByPrimaryKey(trainingEventId);

		Session session = null;

		try {
			session = openSession();

			TrainingEvent[] array = new TrainingEventImpl[3];

			array[0] = getByTrainingCourseId_PrevAndNext(session,
					trainingEvent, trainingCourseId, orderByComparator, true);

			array[1] = trainingEvent;

			array[2] = getByTrainingCourseId_PrevAndNext(session,
					trainingEvent, trainingCourseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingEvent getByTrainingCourseId_PrevAndNext(Session session,
		TrainingEvent trainingEvent, long trainingCourseId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

		query.append(_FINDER_COLUMN_TRAININGCOURSEID_TRAININGCOURSEID_2);

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
			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(trainingCourseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training events where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @return the matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingLocationId(long trainingLocationId)
		throws SystemException {
		return findByTrainingLocationId(trainingLocationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events where trainingLocationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingLocationId the training location ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end) throws SystemException {
		return findByTrainingLocationId(trainingLocationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events where trainingLocationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingLocationId the training location ID
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTrainingLocationId(
		long trainingLocationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRAININGLOCATIONID;
			finderArgs = new Object[] { trainingLocationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRAININGLOCATIONID;
			finderArgs = new Object[] {
					trainingLocationId,
					
					start, end, orderByComparator
				};
		}

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingEvent trainingEvent : list) {
				if ((trainingLocationId != trainingEvent.getTrainingLocationId())) {
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

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGLOCATIONID_TRAININGLOCATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingLocationId);

				list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training event in the ordered set where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingLocationId_First(
		long trainingLocationId, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingLocationId_First(trainingLocationId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingLocationId=");
		msg.append(trainingLocationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the first training event in the ordered set where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingLocationId_First(
		long trainingLocationId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingEvent> list = findByTrainingLocationId(trainingLocationId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training event in the ordered set where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTrainingLocationId_Last(
		long trainingLocationId, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTrainingLocationId_Last(trainingLocationId,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingLocationId=");
		msg.append(trainingLocationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the last training event in the ordered set where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTrainingLocationId_Last(
		long trainingLocationId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTrainingLocationId(trainingLocationId);

		List<TrainingEvent> list = findByTrainingLocationId(trainingLocationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training events before and after the current training event in the ordered set where trainingLocationId = &#63;.
	 *
	 * @param trainingEventId the primary key of the current training event
	 * @param trainingLocationId the training location ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent[] findByTrainingLocationId_PrevAndNext(
		long trainingEventId, long trainingLocationId,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByPrimaryKey(trainingEventId);

		Session session = null;

		try {
			session = openSession();

			TrainingEvent[] array = new TrainingEventImpl[3];

			array[0] = getByTrainingLocationId_PrevAndNext(session,
					trainingEvent, trainingLocationId, orderByComparator, true);

			array[1] = trainingEvent;

			array[2] = getByTrainingLocationId_PrevAndNext(session,
					trainingEvent, trainingLocationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingEvent getByTrainingLocationId_PrevAndNext(
		Session session, TrainingEvent trainingEvent, long trainingLocationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

		query.append(_FINDER_COLUMN_TRAININGLOCATIONID_TRAININGLOCATIONID_2);

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
			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(trainingLocationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training events where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @return the matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByT_SD(int type, Date startDate)
		throws SystemException {
		return findByT_SD(type, startDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events where type = &#63; and startDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByT_SD(int type, Date startDate, int start,
		int end) throws SystemException {
		return findByT_SD(type, startDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events where type = &#63; and startDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByT_SD(int type, Date startDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_SD;
		finderArgs = new Object[] { type, startDate, start, end, orderByComparator };

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingEvent trainingEvent : list) {
				if ((type != trainingEvent.getType()) ||
						!Validator.equals(startDate,
							trainingEvent.getStartDate())) {
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

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_T_SD_TYPE_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_T_SD_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_T_SD_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByT_SD_First(int type, Date startDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByT_SD_First(type, startDate,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the first training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByT_SD_First(int type, Date startDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<TrainingEvent> list = findByT_SD(type, startDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByT_SD_Last(int type, Date startDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByT_SD_Last(type, startDate,
				orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the last training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByT_SD_Last(int type, Date startDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByT_SD(type, startDate);

		List<TrainingEvent> list = findByT_SD(type, startDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training events before and after the current training event in the ordered set where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingEventId the primary key of the current training event
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent[] findByT_SD_PrevAndNext(long trainingEventId,
		int type, Date startDate, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByPrimaryKey(trainingEventId);

		Session session = null;

		try {
			session = openSession();

			TrainingEvent[] array = new TrainingEventImpl[3];

			array[0] = getByT_SD_PrevAndNext(session, trainingEvent, type,
					startDate, orderByComparator, true);

			array[1] = trainingEvent;

			array[2] = getByT_SD_PrevAndNext(session, trainingEvent, type,
					startDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingEvent getByT_SD_PrevAndNext(Session session,
		TrainingEvent trainingEvent, int type, Date startDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

		query.append(_FINDER_COLUMN_T_SD_TYPE_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_T_SD_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_T_SD_STARTDATE_2);
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
			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @return the matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTC_T_SD(long trainingCourseId, int type,
		Date startDate) throws SystemException {
		return findByTC_T_SD(trainingCourseId, type, startDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTC_T_SD(long trainingCourseId, int type,
		Date startDate, int start, int end) throws SystemException {
		return findByTC_T_SD(trainingCourseId, type, startDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findByTC_T_SD(long trainingCourseId, int type,
		Date startDate, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TC_T_SD;
		finderArgs = new Object[] {
				trainingCourseId, type, startDate,
				
				start, end, orderByComparator
			};

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TrainingEvent trainingEvent : list) {
				if ((trainingCourseId != trainingEvent.getTrainingCourseId()) ||
						(type != trainingEvent.getType()) ||
						!Validator.equals(startDate,
							trainingEvent.getStartDate())) {
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

			query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TC_T_SD_TRAININGCOURSEID_2);

			query.append(_FINDER_COLUMN_TC_T_SD_TYPE_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCourseId);

				qPos.add(type);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
				}

				list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTC_T_SD_First(long trainingCourseId, int type,
		Date startDate, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTC_T_SD_First(trainingCourseId,
				type, startDate, orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCourseId=");
		msg.append(trainingCourseId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the first training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTC_T_SD_First(long trainingCourseId, int type,
		Date startDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<TrainingEvent> list = findByTC_T_SD(trainingCourseId, type,
				startDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent findByTC_T_SD_Last(long trainingCourseId, int type,
		Date startDate, OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = fetchByTC_T_SD_Last(trainingCourseId,
				type, startDate, orderByComparator);

		if (trainingEvent != null) {
			return trainingEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("trainingCourseId=");
		msg.append(trainingCourseId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTrainingEventException(msg.toString());
	}

	/**
	 * Returns the last training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching training event, or <code>null</code> if a matching training event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent fetchByTC_T_SD_Last(long trainingCourseId, int type,
		Date startDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTC_T_SD(trainingCourseId, type, startDate);

		List<TrainingEvent> list = findByTC_T_SD(trainingCourseId, type,
				startDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the training events before and after the current training event in the ordered set where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingEventId the primary key of the current training event
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next training event
	 * @throws com.liferay.osb.NoSuchTrainingEventException if a training event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent[] findByTC_T_SD_PrevAndNext(long trainingEventId,
		long trainingCourseId, int type, Date startDate,
		OrderByComparator orderByComparator)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByPrimaryKey(trainingEventId);

		Session session = null;

		try {
			session = openSession();

			TrainingEvent[] array = new TrainingEventImpl[3];

			array[0] = getByTC_T_SD_PrevAndNext(session, trainingEvent,
					trainingCourseId, type, startDate, orderByComparator, true);

			array[1] = trainingEvent;

			array[2] = getByTC_T_SD_PrevAndNext(session, trainingEvent,
					trainingCourseId, type, startDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrainingEvent getByTC_T_SD_PrevAndNext(Session session,
		TrainingEvent trainingEvent, long trainingCourseId, int type,
		Date startDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRAININGEVENT_WHERE);

		query.append(_FINDER_COLUMN_TC_T_SD_TRAININGCOURSEID_2);

		query.append(_FINDER_COLUMN_TC_T_SD_TYPE_2);

		if (startDate == null) {
			query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_2);
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
			query.append(TrainingEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(trainingCourseId);

		qPos.add(type);

		if (startDate != null) {
			qPos.add(CalendarUtil.getTimestamp(startDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trainingEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrainingEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the training events.
	 *
	 * @return the training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the training events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @return the range of training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the training events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of training events
	 * @param end the upper bound of the range of training events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of training events
	 * @throws SystemException if a system exception occurred
	 */
	public List<TrainingEvent> findAll(int start, int end,
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

		List<TrainingEvent> list = (List<TrainingEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRAININGEVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRAININGEVENT.concat(TrainingEventModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TrainingEvent>)QueryUtil.list(q, getDialect(),
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
	 * Removes the training event where DDLRecordSetId = &#63; from the database.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the training event that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TrainingEvent removeByDDLRecordSetId(long DDLRecordSetId)
		throws NoSuchTrainingEventException, SystemException {
		TrainingEvent trainingEvent = findByDDLRecordSetId(DDLRecordSetId);

		return remove(trainingEvent);
	}

	/**
	 * Removes all the training events where trainingCertificateTemplateId = &#63; from the database.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		for (TrainingEvent trainingEvent : findByTrainingCertificateTemplateId(
				trainingCertificateTemplateId)) {
			remove(trainingEvent);
		}
	}

	/**
	 * Removes all the training events where trainingCourseId = &#63; from the database.
	 *
	 * @param trainingCourseId the training course ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTrainingCourseId(long trainingCourseId)
		throws SystemException {
		for (TrainingEvent trainingEvent : findByTrainingCourseId(
				trainingCourseId)) {
			remove(trainingEvent);
		}
	}

	/**
	 * Removes all the training events where trainingLocationId = &#63; from the database.
	 *
	 * @param trainingLocationId the training location ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTrainingLocationId(long trainingLocationId)
		throws SystemException {
		for (TrainingEvent trainingEvent : findByTrainingLocationId(
				trainingLocationId)) {
			remove(trainingEvent);
		}
	}

	/**
	 * Removes all the training events where type = &#63; and startDate &gt; &#63; from the database.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_SD(int type, Date startDate)
		throws SystemException {
		for (TrainingEvent trainingEvent : findByT_SD(type, startDate)) {
			remove(trainingEvent);
		}
	}

	/**
	 * Removes all the training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63; from the database.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTC_T_SD(long trainingCourseId, int type, Date startDate)
		throws SystemException {
		for (TrainingEvent trainingEvent : findByTC_T_SD(trainingCourseId,
				type, startDate)) {
			remove(trainingEvent);
		}
	}

	/**
	 * Removes all the training events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TrainingEvent trainingEvent : findAll()) {
			remove(trainingEvent);
		}
	}

	/**
	 * Returns the number of training events where DDLRecordSetId = &#63;.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDDLRecordSetId(long DDLRecordSetId)
		throws SystemException {
		Object[] finderArgs = new Object[] { DDLRecordSetId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(DDLRecordSetId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events where trainingCertificateTemplateId = &#63;.
	 *
	 * @param trainingCertificateTemplateId the training certificate template ID
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) throws SystemException {
		Object[] finderArgs = new Object[] { trainingCertificateTemplateId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCertificateTemplateId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCERTIFICATETEMPLATEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events where trainingCourseId = &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingCourseId(long trainingCourseId)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingCourseId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGCOURSEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGCOURSEID_TRAININGCOURSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCourseId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGCOURSEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events where trainingLocationId = &#63;.
	 *
	 * @param trainingLocationId the training location ID
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTrainingLocationId(long trainingLocationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingLocationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRAININGLOCATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TRAININGLOCATIONID_TRAININGLOCATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingLocationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRAININGLOCATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events where type = &#63; and startDate &gt; &#63;.
	 *
	 * @param type the type
	 * @param startDate the start date
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_SD(int type, Date startDate) throws SystemException {
		Object[] finderArgs = new Object[] { type, startDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_SD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_T_SD_TYPE_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_T_SD_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_T_SD_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_SD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events where trainingCourseId = &#63; and type = &#63; and startDate &gt; &#63;.
	 *
	 * @param trainingCourseId the training course ID
	 * @param type the type
	 * @param startDate the start date
	 * @return the number of matching training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTC_T_SD(long trainingCourseId, int type, Date startDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { trainingCourseId, type, startDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TC_T_SD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TRAININGEVENT_WHERE);

			query.append(_FINDER_COLUMN_TC_T_SD_TRAININGCOURSEID_2);

			query.append(_FINDER_COLUMN_TC_T_SD_TYPE_2);

			if (startDate == null) {
				query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_TC_T_SD_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(trainingCourseId);

				qPos.add(type);

				if (startDate != null) {
					qPos.add(CalendarUtil.getTimestamp(startDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TC_T_SD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of training events.
	 *
	 * @return the number of training events
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRAININGEVENT);

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
	 * Initializes the training event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TrainingEvent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TrainingEvent>> listenersList = new ArrayList<ModelListener<TrainingEvent>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TrainingEvent>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TrainingEventImpl.class.getName());
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
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = DDLRecordSetPersistence.class)
	protected DDLRecordSetPersistence ddlRecordSetPersistence;
	private static final String _SQL_SELECT_TRAININGEVENT = "SELECT trainingEvent FROM TrainingEvent trainingEvent";
	private static final String _SQL_SELECT_TRAININGEVENT_WHERE = "SELECT trainingEvent FROM TrainingEvent trainingEvent WHERE ";
	private static final String _SQL_COUNT_TRAININGEVENT = "SELECT COUNT(trainingEvent) FROM TrainingEvent trainingEvent";
	private static final String _SQL_COUNT_TRAININGEVENT_WHERE = "SELECT COUNT(trainingEvent) FROM TrainingEvent trainingEvent WHERE ";
	private static final String _FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2 = "trainingEvent.DDLRecordSetId = ?";
	private static final String _FINDER_COLUMN_TRAININGCERTIFICATETEMPLATEID_TRAININGCERTIFICATETEMPLATEID_2 =
		"trainingEvent.trainingCertificateTemplateId = ?";
	private static final String _FINDER_COLUMN_TRAININGCOURSEID_TRAININGCOURSEID_2 =
		"trainingEvent.trainingCourseId = ?";
	private static final String _FINDER_COLUMN_TRAININGLOCATIONID_TRAININGLOCATIONID_2 =
		"trainingEvent.trainingLocationId = ?";
	private static final String _FINDER_COLUMN_T_SD_TYPE_2 = "trainingEvent.type = ? AND ";
	private static final String _FINDER_COLUMN_T_SD_STARTDATE_1 = "trainingEvent.startDate > NULL";
	private static final String _FINDER_COLUMN_T_SD_STARTDATE_2 = "trainingEvent.startDate > ?";
	private static final String _FINDER_COLUMN_TC_T_SD_TRAININGCOURSEID_2 = "trainingEvent.trainingCourseId = ? AND ";
	private static final String _FINDER_COLUMN_TC_T_SD_TYPE_2 = "trainingEvent.type = ? AND ";
	private static final String _FINDER_COLUMN_TC_T_SD_STARTDATE_1 = "trainingEvent.startDate > NULL";
	private static final String _FINDER_COLUMN_TC_T_SD_STARTDATE_2 = "trainingEvent.startDate > ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trainingEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrainingEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrainingEvent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TrainingEventPersistenceImpl.class);
	private static TrainingEvent _nullTrainingEvent = new TrainingEventImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TrainingEvent> toCacheModel() {
				return _nullTrainingEventCacheModel;
			}
		};

	private static CacheModel<TrainingEvent> _nullTrainingEventCacheModel = new CacheModel<TrainingEvent>() {
			public TrainingEvent toEntityModel() {
				return _nullTrainingEvent;
			}
		};
}