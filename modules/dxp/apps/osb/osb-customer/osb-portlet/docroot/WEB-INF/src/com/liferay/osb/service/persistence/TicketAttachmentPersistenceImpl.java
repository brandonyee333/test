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

import com.liferay.osb.NoSuchTicketAttachmentException;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.impl.TicketAttachmentImpl;
import com.liferay.osb.model.impl.TicketAttachmentModelImpl;

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
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the ticket attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @see TicketAttachmentUtil
 * @generated
 */
public class TicketAttachmentPersistenceImpl extends BasePersistenceImpl<TicketAttachment>
	implements TicketAttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TicketAttachmentUtil} to access the ticket attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TicketAttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByType",
			new String[] {
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { Integer.class.getName() },
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TYPE = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByType",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_TEI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCD_TEI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_TEI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCD_TEI",
			new String[] { Date.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCD_T",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_T = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCD_T",
			new String[] { Date.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_TSI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() },
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TICKETSOLUTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_TSI = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_TSI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_DD",
			new String[] {
				Integer.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByT_DD",
			new String[] { Integer.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.USERID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TEI_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TEI_V_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_TEI_FN_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTEI_FN_V_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.FILENAME_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_FN_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_FN_V_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			TicketAttachmentModelImpl.TICKETENTRYID_COLUMN_BITMASK |
			TicketAttachmentModelImpl.TYPE_COLUMN_BITMASK |
			TicketAttachmentModelImpl.VISIBILITY_COLUMN_BITMASK |
			TicketAttachmentModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEI_T_V_S = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S =
		new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTEI_T_V_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED,
			TicketAttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the ticket attachment in the entity cache if it is enabled.
	 *
	 * @param ticketAttachment the ticket attachment
	 */
	public void cacheResult(TicketAttachment ticketAttachment) {
		EntityCacheUtil.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
			new Object[] {
				Long.valueOf(ticketAttachment.getTicketEntryId()),
				
			ticketAttachment.getFileName(),
				Integer.valueOf(ticketAttachment.getVisibility()),
				Integer.valueOf(ticketAttachment.getStatus())
			}, ticketAttachment);

		ticketAttachment.resetOriginalValues();
	}

	/**
	 * Caches the ticket attachments in the entity cache if it is enabled.
	 *
	 * @param ticketAttachments the ticket attachments
	 */
	public void cacheResult(List<TicketAttachment> ticketAttachments) {
		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if (EntityCacheUtil.getResult(
						TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						TicketAttachmentImpl.class,
						ticketAttachment.getPrimaryKey()) == null) {
				cacheResult(ticketAttachment);
			}
			else {
				ticketAttachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ticket attachments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TicketAttachmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TicketAttachmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ticket attachment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TicketAttachment ticketAttachment) {
		EntityCacheUtil.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(ticketAttachment);
	}

	@Override
	public void clearCache(List<TicketAttachment> ticketAttachments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			EntityCacheUtil.removeResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey());

			clearUniqueFindersCache(ticketAttachment);
		}
	}

	protected void cacheUniqueFindersCache(TicketAttachment ticketAttachment) {
		if (ticketAttachment.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(ticketAttachment.getTicketEntryId()),
					
					ticketAttachment.getFileName(),
					Integer.valueOf(ticketAttachment.getVisibility()),
					Integer.valueOf(ticketAttachment.getStatus())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args,
				ticketAttachment);
		}
		else {
			TicketAttachmentModelImpl ticketAttachmentModelImpl = (TicketAttachmentModelImpl)ticketAttachment;

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TEI_FN_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachment.getTicketEntryId()),
						
						ticketAttachment.getFileName(),
						Integer.valueOf(ticketAttachment.getVisibility()),
						Integer.valueOf(ticketAttachment.getStatus())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
					args, ticketAttachment);
			}
		}
	}

	protected void clearUniqueFindersCache(TicketAttachment ticketAttachment) {
		TicketAttachmentModelImpl ticketAttachmentModelImpl = (TicketAttachmentModelImpl)ticketAttachment;

		Object[] args = new Object[] {
				Long.valueOf(ticketAttachment.getTicketEntryId()),
				
				ticketAttachment.getFileName(),
				Integer.valueOf(ticketAttachment.getVisibility()),
				Integer.valueOf(ticketAttachment.getStatus())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args);

		if ((ticketAttachmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEI_FN_V_S.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
					
					ticketAttachmentModelImpl.getOriginalFileName(),
					Integer.valueOf(ticketAttachmentModelImpl.getOriginalVisibility()),
					Integer.valueOf(ticketAttachmentModelImpl.getOriginalStatus())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S, args);
		}
	}

	/**
	 * Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	 *
	 * @param ticketAttachmentId the primary key for the new ticket attachment
	 * @return the new ticket attachment
	 */
	public TicketAttachment create(long ticketAttachmentId) {
		TicketAttachment ticketAttachment = new TicketAttachmentImpl();

		ticketAttachment.setNew(true);
		ticketAttachment.setPrimaryKey(ticketAttachmentId);

		return ticketAttachment;
	}

	/**
	 * Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment that was removed
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment remove(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException, SystemException {
		return remove(Long.valueOf(ticketAttachmentId));
	}

	/**
	 * Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment that was removed
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketAttachment remove(Serializable primaryKey)
		throws NoSuchTicketAttachmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TicketAttachment ticketAttachment = (TicketAttachment)session.get(TicketAttachmentImpl.class,
					primaryKey);

			if (ticketAttachment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ticketAttachment);
		}
		catch (NoSuchTicketAttachmentException nsee) {
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
	protected TicketAttachment removeImpl(TicketAttachment ticketAttachment)
		throws SystemException {
		ticketAttachment = toUnwrappedModel(ticketAttachment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ticketAttachment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(ticketAttachment);

		return ticketAttachment;
	}

	@Override
	public TicketAttachment updateImpl(
		com.liferay.osb.model.TicketAttachment ticketAttachment, boolean merge)
		throws SystemException {
		ticketAttachment = toUnwrappedModel(ticketAttachment);

		boolean isNew = ticketAttachment.isNew();

		TicketAttachmentModelImpl ticketAttachmentModelImpl = (TicketAttachmentModelImpl)ticketAttachment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ticketAttachment, merge);

			ticketAttachment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TicketAttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] {
						Integer.valueOf(ticketAttachmentModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketSolutionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);

				args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getTicketEntryId()),
						Long.valueOf(ticketAttachmentModelImpl.getTicketSolutionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_TSI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalType()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getType()),
						Integer.valueOf(ticketAttachmentModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getOriginalUserId()),
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalVisibility()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getUserId()),
						Long.valueOf(ticketAttachmentModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getVisibility()),
						Integer.valueOf(ticketAttachmentModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TEI_V_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S,
					args);
			}

			if ((ticketAttachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getOriginalTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalType()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalVisibility()),
						Integer.valueOf(ticketAttachmentModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_V_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S,
					args);

				args = new Object[] {
						Long.valueOf(ticketAttachmentModelImpl.getTicketEntryId()),
						Integer.valueOf(ticketAttachmentModelImpl.getType()),
						Integer.valueOf(ticketAttachmentModelImpl.getVisibility()),
						Integer.valueOf(ticketAttachmentModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TEI_T_V_S,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S,
					args);
			}
		}

		EntityCacheUtil.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
			TicketAttachmentImpl.class, ticketAttachment.getPrimaryKey(),
			ticketAttachment);

		clearUniqueFindersCache(ticketAttachment);
		cacheUniqueFindersCache(ticketAttachment);

		return ticketAttachment;
	}

	protected TicketAttachment toUnwrappedModel(
		TicketAttachment ticketAttachment) {
		if (ticketAttachment instanceof TicketAttachmentImpl) {
			return ticketAttachment;
		}

		TicketAttachmentImpl ticketAttachmentImpl = new TicketAttachmentImpl();

		ticketAttachmentImpl.setNew(ticketAttachment.isNew());
		ticketAttachmentImpl.setPrimaryKey(ticketAttachment.getPrimaryKey());

		ticketAttachmentImpl.setTicketAttachmentId(ticketAttachment.getTicketAttachmentId());
		ticketAttachmentImpl.setUserId(ticketAttachment.getUserId());
		ticketAttachmentImpl.setUserName(ticketAttachment.getUserName());
		ticketAttachmentImpl.setCreateDate(ticketAttachment.getCreateDate());
		ticketAttachmentImpl.setTicketEntryId(ticketAttachment.getTicketEntryId());
		ticketAttachmentImpl.setTicketSolutionId(ticketAttachment.getTicketSolutionId());
		ticketAttachmentImpl.setReleaseNotesId(ticketAttachment.getReleaseNotesId());
		ticketAttachmentImpl.setFileName(ticketAttachment.getFileName());
		ticketAttachmentImpl.setFileSize(ticketAttachment.getFileSize());
		ticketAttachmentImpl.setType(ticketAttachment.getType());
		ticketAttachmentImpl.setVisibility(ticketAttachment.getVisibility());
		ticketAttachmentImpl.setExtractedText(ticketAttachment.getExtractedText());
		ticketAttachmentImpl.setAvailableFileRepositoryIds(ticketAttachment.getAvailableFileRepositoryIds());
		ticketAttachmentImpl.setReplicate(ticketAttachment.isReplicate());
		ticketAttachmentImpl.setDeleteDate(ticketAttachment.getDeleteDate());
		ticketAttachmentImpl.setStatus(ticketAttachment.getStatus());

		return ticketAttachmentImpl;
	}

	/**
	 * Returns the ticket attachment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws com.liferay.portal.NoSuchModelException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketAttachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket attachment with the primary key or throws a {@link com.liferay.osb.NoSuchTicketAttachmentException} if it could not be found.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByPrimaryKey(ticketAttachmentId);

		if (ticketAttachment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					ticketAttachmentId);
			}

			throw new NoSuchTicketAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ticketAttachmentId);
		}

		return ticketAttachment;
	}

	/**
	 * Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket attachment
	 * @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TicketAttachment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByPrimaryKey(long ticketAttachmentId)
		throws SystemException {
		TicketAttachment ticketAttachment = (TicketAttachment)EntityCacheUtil.getResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
				TicketAttachmentImpl.class, ticketAttachmentId);

		if (ticketAttachment == _nullTicketAttachment) {
			return null;
		}

		if (ticketAttachment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				ticketAttachment = (TicketAttachment)session.get(TicketAttachmentImpl.class,
						Long.valueOf(ticketAttachmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (ticketAttachment != null) {
					cacheResult(ticketAttachment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TicketAttachmentModelImpl.ENTITY_CACHE_ENABLED,
						TicketAttachmentImpl.class, ticketAttachmentId,
						_nullTicketAttachment);
				}

				closeSession(session);
			}
		}

		return ticketAttachment;
	}

	/**
	 * Returns all the ticket attachments where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int type)
		throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((type != ticketAttachment.getType())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByType_First(int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByType_First(type,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByType_First(int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByType_Last(int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByType_Last(type,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByType_Last(int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<TicketAttachment> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByType_PrevAndNext(long ticketAttachmentId,
		int type, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByType_PrevAndNext(session, ticketAttachment, type,
					orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByType_PrevAndNext(session, ticketAttachment, type,
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

	protected TicketAttachment getByType_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int[] types)
		throws SystemException {
		return findByType(types, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int[] types, int start, int end)
		throws SystemException {
		return findByType(types, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByType(int[] types, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] { StringUtil.merge(types) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(types),
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if (!ArrayUtil.contains(types, ticketAttachment.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_5);

					if ((i + 1) < types.length) {
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
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (types != null) {
					qPos.add(types);
				}

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId) throws SystemException {
		return findByCD_TEI(createDate, ticketEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end) throws SystemException {
		return findByCD_TEI(createDate, ticketEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_TEI(Date createDate,
		long ticketEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_TEI;
		finderArgs = new Object[] {
				createDate, ticketEntryId,
				
				start, end, orderByComparator
			};

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if (!Validator.equals(createDate,
							ticketAttachment.getCreateDate()) ||
						(ticketEntryId != ticketAttachment.getTicketEntryId())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
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

				qPos.add(ticketEntryId);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByCD_TEI_First(Date createDate,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByCD_TEI_First(createDate,
				ticketEntryId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByCD_TEI_First(Date createDate,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketAttachment> list = findByCD_TEI(createDate, ticketEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByCD_TEI_Last(Date createDate,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByCD_TEI_Last(createDate,
				ticketEntryId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByCD_TEI_Last(Date createDate,
		long ticketEntryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCD_TEI(createDate, ticketEntryId);

		List<TicketAttachment> list = findByCD_TEI(createDate, ticketEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByCD_TEI_PrevAndNext(
		long ticketAttachmentId, Date createDate, long ticketEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByCD_TEI_PrevAndNext(session, ticketAttachment,
					createDate, ticketEntryId, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByCD_TEI_PrevAndNext(session, ticketAttachment,
					createDate, ticketEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByCD_TEI_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, Date createDate, long ticketEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		qPos.add(ticketEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_T(Date createDate, int type)
		throws SystemException {
		return findByCD_T(createDate, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_T(Date createDate, int type,
		int start, int end) throws SystemException {
		return findByCD_T(createDate, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where createDate &lt; &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByCD_T(Date createDate, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CD_T;
		finderArgs = new Object[] {
				createDate, type,
				
				start, end, orderByComparator
			};

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if (!Validator.equals(createDate,
							ticketAttachment.getCreateDate()) ||
						(type != ticketAttachment.getType())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_T_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CD_T_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
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

				qPos.add(type);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByCD_T_First(Date createDate, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByCD_T_First(createDate, type,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByCD_T_First(Date createDate, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByCD_T(createDate, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByCD_T_Last(Date createDate, int type,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByCD_T_Last(createDate, type,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByCD_T_Last(Date createDate, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCD_T(createDate, type);

		List<TicketAttachment> list = findByCD_T(createDate, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param createDate the create date
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByCD_T_PrevAndNext(long ticketAttachmentId,
		Date createDate, int type, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByCD_T_PrevAndNext(session, ticketAttachment,
					createDate, type, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByCD_T_PrevAndNext(session, ticketAttachment,
					createDate, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByCD_T_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, Date createDate, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CD_T_CREATEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_CD_T_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_CD_T_TYPE_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (createDate != null) {
			qPos.add(CalendarUtil.getTimestamp(createDate));
		}

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId) throws SystemException {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end) throws SystemException {
		return findByTEI_TSI(ticketEntryId, ticketSolutionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] { ticketEntryId, ticketSolutionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_TSI;
			finderArgs = new Object[] {
					ticketEntryId, ticketSolutionId,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						(ticketSolutionId != ticketAttachment.getTicketSolutionId())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(ticketSolutionId);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_TSI_First(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketAttachment> list = findByTEI_TSI(ticketEntryId,
				ticketSolutionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_TSI_Last(ticketEntryId,
				ticketSolutionId, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", ticketSolutionId=");
		msg.append(ticketSolutionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_TSI(ticketEntryId, ticketSolutionId);

		List<TicketAttachment> list = findByTEI_TSI(ticketEntryId,
				ticketSolutionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByTEI_TSI_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, long ticketSolutionId,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_TSI_PrevAndNext(session, ticketAttachment,
					ticketEntryId, ticketSolutionId, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_TSI_PrevAndNext(session, ticketAttachment,
					ticketEntryId, ticketSolutionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_TSI_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId,
		long ticketSolutionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(ticketSolutionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status)
		throws SystemException {
		return findByTEI_S(ticketEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status,
		int start, int end) throws SystemException {
		return findByTEI_S(ticketEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_S(long ticketEntryId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] { ticketEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_S;
			finderArgs = new Object[] {
					ticketEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(status);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_S_First(long ticketEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_S_First(ticketEntryId,
				status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_S_First(long ticketEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByTEI_S(ticketEntryId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_S_Last(long ticketEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_S_Last(ticketEntryId,
				status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_S_Last(long ticketEntryId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTEI_S(ticketEntryId, status);

		List<TicketAttachment> list = findByTEI_S(ticketEntryId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByTEI_S_PrevAndNext(long ticketAttachmentId,
		long ticketEntryId, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, status, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate)
		throws SystemException {
		return findByT_DD(type, deleteDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end) throws SystemException {
		return findByT_DD(type, deleteDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int type, Date deleteDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD;
		finderArgs = new Object[] {
				type, deleteDate,
				
				start, end, orderByComparator
			};

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((type != ticketAttachment.getType()) ||
						!Validator.equals(deleteDate,
							ticketAttachment.getDeleteDate())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_T_DD_TYPE_2);

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (deleteDate != null) {
					qPos.add(CalendarUtil.getTimestamp(deleteDate));
				}

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByT_DD_First(int type, Date deleteDate,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByT_DD_First(type, deleteDate,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", deleteDate=");
		msg.append(deleteDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByT_DD_First(int type, Date deleteDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByT_DD(type, deleteDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByT_DD_Last(int type, Date deleteDate,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByT_DD_Last(type, deleteDate,
				orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(", deleteDate=");
		msg.append(deleteDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByT_DD_Last(int type, Date deleteDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByT_DD(type, deleteDate);

		List<TicketAttachment> list = findByT_DD(type, deleteDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param type the type
	 * @param deleteDate the delete date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByT_DD_PrevAndNext(long ticketAttachmentId,
		int type, Date deleteDate, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByT_DD_PrevAndNext(session, ticketAttachment, type,
					deleteDate, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByT_DD_PrevAndNext(session, ticketAttachment, type,
					deleteDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByT_DD_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, int type, Date deleteDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_T_DD_TYPE_2);

		if (deleteDate == null) {
			query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		if (deleteDate != null) {
			qPos.add(CalendarUtil.getTimestamp(deleteDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate)
		throws SystemException {
		return findByT_DD(types, deleteDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate,
		int start, int end) throws SystemException {
		return findByT_DD(types, deleteDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByT_DD(int[] types, Date deleteDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_DD;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] { StringUtil.merge(types), deleteDate };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(types), deleteDate,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if (!ArrayUtil.contains(types, ticketAttachment.getType()) ||
						!Validator.equals(deleteDate,
							ticketAttachment.getDeleteDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_T_DD_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_4);
			}
			else {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_5);
			}

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (types != null) {
					qPos.add(types);
				}

				if (deleteDate != null) {
					qPos.add(CalendarUtil.getTimestamp(deleteDate));
				}

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status) throws SystemException {
		return findByTEI_T_S(ticketEntryId, type, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status, int start, int end) throws SystemException {
		return findByTEI_T_S(ticketEntryId, type, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_S(long ticketEntryId, int type,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_S;
			finderArgs = new Object[] { ticketEntryId, type, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_S;
			finderArgs = new Object[] {
					ticketEntryId, type, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						(type != ticketAttachment.getType()) ||
						(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(status);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_T_S_First(long ticketEntryId, int type,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_T_S_First(ticketEntryId,
				type, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_T_S_First(long ticketEntryId, int type,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<TicketAttachment> list = findByTEI_T_S(ticketEntryId, type,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_T_S_Last(long ticketEntryId, int type,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_T_S_Last(ticketEntryId,
				type, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_T_S_Last(long ticketEntryId, int type,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_T_S(ticketEntryId, type, status);

		List<TicketAttachment> list = findByTEI_T_S(ticketEntryId, type,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByTEI_T_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_T_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, status, orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_T_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TicketAttachment getByTEI_T_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int type,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status)
		throws SystemException {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end)
		throws SystemException {
		return findByU_TEI_V_S(userId, ticketEntryId, visibility, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByU_TEI_V_S(long userId,
		long ticketEntryId, int visibility, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_TEI_V_S;
			finderArgs = new Object[] { userId, ticketEntryId, visibility, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_TEI_V_S;
			finderArgs = new Object[] {
					userId, ticketEntryId, visibility, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((userId != ticketAttachment.getUserId()) ||
						(ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						(visibility != ticketAttachment.getVisibility()) ||
						(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

				qPos.add(status);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByU_TEI_V_S_First(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByU_TEI_V_S_First(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByU_TEI_V_S_Last(userId,
				ticketEntryId, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByU_TEI_V_S_Last(long userId,
		long ticketEntryId, int visibility, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_TEI_V_S(userId, ticketEntryId, visibility, status);

		List<TicketAttachment> list = findByU_TEI_V_S(userId, ticketEntryId,
				visibility, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByU_TEI_V_S_PrevAndNext(
		long ticketAttachmentId, long userId, long ticketEntryId,
		int visibility, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByU_TEI_V_S_PrevAndNext(session, ticketAttachment,
					userId, ticketEntryId, visibility, status,
					orderByComparator, true);

			array[1] = ticketAttachment;

			array[2] = getByU_TEI_V_S_PrevAndNext(session, ticketAttachment,
					userId, ticketEntryId, visibility, status,
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

	protected TicketAttachment getByU_TEI_V_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long userId, long ticketEntryId,
		int visibility, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

		query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(ticketEntryId);

		qPos.add(visibility);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or throws a {@link com.liferay.osb.NoSuchTicketAttachmentException} if it could not be found.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_FN_V_S(ticketEntryId,
				fileName, visibility, status);

		if (ticketAttachment == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ticketEntryId=");
			msg.append(ticketEntryId);

			msg.append(", fileName=");
			msg.append(fileName);

			msg.append(", visibility=");
			msg.append(visibility);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTicketAttachmentException(msg.toString());
		}

		return ticketAttachment;
	}

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status) throws SystemException {
		return fetchByTEI_FN_V_S(ticketEntryId, fileName, visibility, status,
			true);
	}

	/**
	 * Returns the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, fileName, visibility, status
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
					finderArgs, this);
		}

		if (result instanceof TicketAttachment) {
			TicketAttachment ticketAttachment = (TicketAttachment)result;

			if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
					!Validator.equals(fileName, ticketAttachment.getFileName()) ||
					(visibility != ticketAttachment.getVisibility()) ||
					(status != ticketAttachment.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_STATUS_2);

			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (fileName != null) {
					qPos.add(fileName);
				}

				qPos.add(visibility);

				qPos.add(status);

				List<TicketAttachment> list = q.list();

				result = list;

				TicketAttachment ticketAttachment = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
						finderArgs, list);
				}
				else {
					ticketAttachment = list.get(0);

					cacheResult(ticketAttachment);

					if ((ticketAttachment.getTicketEntryId() != ticketEntryId) ||
							(ticketAttachment.getFileName() == null) ||
							!ticketAttachment.getFileName().equals(fileName) ||
							(ticketAttachment.getVisibility() != visibility) ||
							(ticketAttachment.getStatus() != status)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
							finderArgs, ticketAttachment);
					}
				}

				return ticketAttachment;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TEI_FN_V_S,
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
				return (TicketAttachment)result;
			}
		}
	}

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status) throws SystemException {
		return findByTEI_T_V_S(ticketEntryId, type, visibility, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status, int start, int end)
		throws SystemException {
		return findByTEI_T_V_S(ticketEntryId, type, visibility, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId, int type,
		int visibility, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEI_T_V_S;
			finderArgs = new Object[] { ticketEntryId, type, visibility, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S;
			finderArgs = new Object[] {
					ticketEntryId, type, visibility, status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						(type != ticketAttachment.getType()) ||
						(visibility != ticketAttachment.getVisibility()) ||
						(status != ticketAttachment.getStatus())) {
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

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(visibility);

				qPos.add(status);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_T_V_S_First(long ticketEntryId, int type,
		int visibility, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_T_V_S_First(ticketEntryId,
				type, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the first ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_T_V_S_First(long ticketEntryId,
		int type, int visibility, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<TicketAttachment> list = findByTEI_T_V_S(ticketEntryId, type,
				visibility, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment findByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = fetchByTEI_T_V_S_Last(ticketEntryId,
				type, visibility, status, orderByComparator);

		if (ticketAttachment != null) {
			return ticketAttachment;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ticketEntryId=");
		msg.append(ticketEntryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", visibility=");
		msg.append(visibility);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTicketAttachmentException(msg.toString());
	}

	/**
	 * Returns the last ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment fetchByTEI_T_V_S_Last(long ticketEntryId, int type,
		int visibility, int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTEI_T_V_S(ticketEntryId, type, visibility, status);

		List<TicketAttachment> list = findByTEI_T_V_S(ticketEntryId, type,
				visibility, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws com.liferay.osb.NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment[] findByTEI_T_V_S_PrevAndNext(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int status, OrderByComparator orderByComparator)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByPrimaryKey(ticketAttachmentId);

		Session session = null;

		try {
			session = openSession();

			TicketAttachment[] array = new TicketAttachmentImpl[3];

			array[0] = getByTEI_T_V_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, visibility, status, orderByComparator,
					true);

			array[1] = ticketAttachment;

			array[2] = getByTEI_T_V_S_PrevAndNext(session, ticketAttachment,
					ticketEntryId, type, visibility, status, orderByComparator,
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

	protected TicketAttachment getByTEI_T_V_S_PrevAndNext(Session session,
		TicketAttachment ticketAttachment, long ticketEntryId, int type,
		int visibility, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

		query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

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
			query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ticketEntryId);

		qPos.add(type);

		qPos.add(visibility);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ticketAttachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TicketAttachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @return the matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status) throws SystemException {
		return findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end)
		throws SystemException {
		return findByTEI_T_V_S(ticketEntryId, types, visibilities, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findByTEI_T_V_S(long ticketEntryId,
		int[] types, int[] visibilities, int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEI_T_V_S;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types),
					StringUtil.merge(visibilities), status
				};
		}
		else {
			finderArgs = new Object[] {
					ticketEntryId, StringUtil.merge(types),
					StringUtil.merge(visibilities), status,
					
					start, end, orderByComparator
				};
		}

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TicketAttachment ticketAttachment : list) {
				if ((ticketEntryId != ticketAttachment.getTicketEntryId()) ||
						!ArrayUtil.contains(types, ticketAttachment.getType()) ||
						!ArrayUtil.contains(visibilities,
							ticketAttachment.getVisibility()) ||
						(status != ticketAttachment.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (types != null) {
					qPos.add(types);
				}

				if (visibilities != null) {
					qPos.add(visibilities);
				}

				qPos.add(status);

				list = (List<TicketAttachment>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the ticket attachments.
	 *
	 * @return the ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public List<TicketAttachment> findAll(int start, int end,
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

		List<TicketAttachment> list = (List<TicketAttachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TICKETATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TICKETATTACHMENT.concat(TicketAttachmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TicketAttachment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TicketAttachment>)QueryUtil.list(q,
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
	 * Removes all the ticket attachments where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(int type) throws SystemException {
		for (TicketAttachment ticketAttachment : findByType(type)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCD_TEI(Date createDate, long ticketEntryId)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByCD_TEI(createDate,
				ticketEntryId)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where createDate &lt; &#63; and type = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCD_T(Date createDate, int type)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByCD_T(createDate, type)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByTEI_TSI(ticketEntryId,
				ticketSolutionId)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_S(long ticketEntryId, int status)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByTEI_S(ticketEntryId,
				status)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where type = &#63; and deleteDate &lt; &#63; from the database.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_DD(int type, Date deleteDate)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByT_DD(type, deleteDate)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_T_S(long ticketEntryId, int type, int status)
		throws SystemException {
		for (TicketAttachment ticketAttachment : findByTEI_T_S(ticketEntryId,
				type, status)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) throws SystemException {
		for (TicketAttachment ticketAttachment : findByU_TEI_V_S(userId,
				ticketEntryId, visibility, status)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes the ticket attachment where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the ticket attachment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TicketAttachment removeByTEI_FN_V_S(long ticketEntryId,
		String fileName, int visibility, int status)
		throws NoSuchTicketAttachmentException, SystemException {
		TicketAttachment ticketAttachment = findByTEI_FN_V_S(ticketEntryId,
				fileName, visibility, status);

		return remove(ticketAttachment);
	}

	/**
	 * Removes all the ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63; from the database.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status) throws SystemException {
		for (TicketAttachment ticketAttachment : findByTEI_T_V_S(
				ticketEntryId, type, visibility, status)) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Removes all the ticket attachments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TicketAttachment ticketAttachment : findAll()) {
			remove(ticketAttachment);
		}
	}

	/**
	 * Returns the number of ticket attachments where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(int type) throws SystemException {
		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where type = any &#63;.
	 *
	 * @param types the types
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(int[] types) throws SystemException {
		Object[] finderArgs = new Object[] { StringUtil.merge(types) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TYPE_TYPE_5);

					if ((i + 1) < types.length) {
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

				if (types != null) {
					qPos.add(types);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TYPE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where createDate &lt; &#63; and ticketEntryId = &#63;.
	 *
	 * @param createDate the create date
	 * @param ticketEntryId the ticket entry ID
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCD_TEI(Date createDate, long ticketEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { createDate, ticketEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_TEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CD_TEI_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_TEI_TICKETENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
				}

				qPos.add(ticketEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_TEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where createDate &lt; &#63; and type = &#63;.
	 *
	 * @param createDate the create date
	 * @param type the type
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCD_T(Date createDate, int type) throws SystemException {
		Object[] finderArgs = new Object[] { createDate, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CD_T_CREATEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_CD_T_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_CD_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (createDate != null) {
					qPos.add(CalendarUtil.getTimestamp(createDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_CD_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param ticketSolutionId the ticket solution ID
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, ticketSolutionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_TSI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(ticketSolutionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_TSI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_S(long ticketEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where type = &#63; and deleteDate &lt; &#63;.
	 *
	 * @param type the type
	 * @param deleteDate the delete date
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_DD(int type, Date deleteDate) throws SystemException {
		Object[] finderArgs = new Object[] { type, deleteDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_T_DD_TYPE_2);

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				if (deleteDate != null) {
					qPos.add(CalendarUtil.getTimestamp(deleteDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where type = any &#63; and deleteDate &lt; &#63;.
	 *
	 * @param types the types
	 * @param deleteDate the delete date
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_DD(int[] types, Date deleteDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { StringUtil.merge(types), deleteDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_T_DD_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (deleteDate == null) {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_4);
			}
			else {
				query.append(_FINDER_COLUMN_T_DD_DELETEDATE_5);
			}

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (types != null) {
					qPos.add(types);
				}

				if (deleteDate != null) {
					qPos.add(CalendarUtil.getTimestamp(deleteDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_T_DD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T_S(long ticketEntryId, int type, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { ticketEntryId, type, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_T_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_T_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where userId = &#63; and ticketEntryId = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param ticketEntryId the ticket entry ID
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_TEI_V_S(long userId, long ticketEntryId,
		int visibility, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, ticketEntryId, visibility, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_TEI_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_U_TEI_V_S_USERID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_U_TEI_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(ticketEntryId);

				qPos.add(visibility);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TEI_V_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and fileName = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param fileName the file name
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_FN_V_S(long ticketEntryId, String fileName,
		int visibility, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, fileName, visibility, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2);

			if (fileName == null) {
				query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_1);
			}
			else {
				if (fileName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEI_FN_V_S_FILENAME_2);
				}
			}

			query.append(_FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_FN_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (fileName != null) {
					qPos.add(fileName);
				}

				qPos.add(visibility);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_FN_V_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = &#63; and visibility = &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param type the type
	 * @param visibility the visibility
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T_V_S(long ticketEntryId, int type, int visibility,
		int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, type, visibility, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEI_T_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2);

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				qPos.add(type);

				qPos.add(visibility);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEI_T_V_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments where ticketEntryId = &#63; and type = any &#63; and visibility = any &#63; and status = &#63;.
	 *
	 * @param ticketEntryId the ticket entry ID
	 * @param types the types
	 * @param visibilities the visibilities
	 * @param status the status
	 * @return the number of matching ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTEI_T_V_S(long ticketEntryId, int[] types,
		int[] visibilities, int status) throws SystemException {
		Object[] finderArgs = new Object[] {
				ticketEntryId, StringUtil.merge(types),
				StringUtil.merge(visibilities), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TICKETATTACHMENT_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_5);

			conjunctionable = true;

			if ((types == null) || (types.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_V_S_TYPE_5);

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((visibilities == null) || (visibilities.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < visibilities.length; i++) {
					query.append(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_5);

					if ((i + 1) < visibilities.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_TEI_T_V_S_STATUS_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ticketEntryId);

				if (types != null) {
					qPos.add(types);
				}

				if (visibilities != null) {
					qPos.add(visibilities);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_TEI_T_V_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of ticket attachments.
	 *
	 * @return the number of ticket attachments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TICKETATTACHMENT);

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
	 * Initializes the ticket attachment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.TicketAttachment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TicketAttachment>> listenersList = new ArrayList<ModelListener<TicketAttachment>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<TicketAttachment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TicketAttachmentImpl.class.getName());
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
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TICKETATTACHMENT = "SELECT ticketAttachment FROM TicketAttachment ticketAttachment";
	private static final String _SQL_SELECT_TICKETATTACHMENT_WHERE = "SELECT ticketAttachment FROM TicketAttachment ticketAttachment WHERE ";
	private static final String _SQL_COUNT_TICKETATTACHMENT = "SELECT COUNT(ticketAttachment) FROM TicketAttachment ticketAttachment";
	private static final String _SQL_COUNT_TICKETATTACHMENT_WHERE = "SELECT COUNT(ticketAttachment) FROM TicketAttachment ticketAttachment WHERE ";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "ticketAttachment.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TYPE_TYPE_2) + ")";
	private static final String _FINDER_COLUMN_CD_TEI_CREATEDATE_1 = "ticketAttachment.createDate < NULL AND ";
	private static final String _FINDER_COLUMN_CD_TEI_CREATEDATE_2 = "ticketAttachment.createDate < ? AND ";
	private static final String _FINDER_COLUMN_CD_TEI_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ?";
	private static final String _FINDER_COLUMN_CD_T_CREATEDATE_1 = "ticketAttachment.createDate < NULL AND ";
	private static final String _FINDER_COLUMN_CD_T_CREATEDATE_2 = "ticketAttachment.createDate < ? AND ";
	private static final String _FINDER_COLUMN_CD_T_TYPE_2 = "ticketAttachment.type = ?";
	private static final String _FINDER_COLUMN_TEI_TSI_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_TSI_TICKETSOLUTIONID_2 = "ticketAttachment.ticketSolutionId = ?";
	private static final String _FINDER_COLUMN_TEI_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_S_STATUS_2 = "ticketAttachment.status = ?";
	private static final String _FINDER_COLUMN_T_DD_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_T_DD_TYPE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_DD_TYPE_2) + ")";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_1 = "ticketAttachment.deleteDate < NULL";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_2 = "ticketAttachment.deleteDate < ?";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_DD_DELETEDATE_1) + ")";
	private static final String _FINDER_COLUMN_T_DD_DELETEDATE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_T_DD_DELETEDATE_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_S_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_S_STATUS_2 = "ticketAttachment.status = ?";
	private static final String _FINDER_COLUMN_U_TEI_V_S_USERID_2 = "ticketAttachment.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_U_TEI_V_S_STATUS_2 = "ticketAttachment.status = ?";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_1 = "ticketAttachment.fileName IS NULL AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_2 = "ticketAttachment.fileName = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_FILENAME_3 = "(ticketAttachment.fileName IS NULL OR ticketAttachment.fileName = ?) AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_TEI_FN_V_S_STATUS_2 = "ticketAttachment.status = ?";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2 = "ticketAttachment.ticketEntryId = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_V_S_TICKETENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TYPE_2 = "ticketAttachment.type = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_TYPE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_V_S_TYPE_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2 = "ticketAttachment.visibility = ? AND ";
	private static final String _FINDER_COLUMN_TEI_T_V_S_VISIBILITY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_V_S_VISIBILITY_2) + ")";
	private static final String _FINDER_COLUMN_TEI_T_V_S_STATUS_2 = "ticketAttachment.status = ?";
	private static final String _FINDER_COLUMN_TEI_T_V_S_STATUS_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_TEI_T_V_S_STATUS_2) + ")";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "ticketAttachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TicketAttachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TicketAttachment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TicketAttachmentPersistenceImpl.class);
	private static TicketAttachment _nullTicketAttachment = new TicketAttachmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TicketAttachment> toCacheModel() {
				return _nullTicketAttachmentCacheModel;
			}
		};

	private static CacheModel<TicketAttachment> _nullTicketAttachmentCacheModel = new CacheModel<TicketAttachment>() {
			public TicketAttachment toEntityModel() {
				return _nullTicketAttachment;
			}
		};
}