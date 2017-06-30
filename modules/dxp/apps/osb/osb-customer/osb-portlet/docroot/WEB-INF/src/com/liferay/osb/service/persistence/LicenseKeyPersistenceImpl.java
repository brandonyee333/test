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

import com.liferay.osb.NoSuchLicenseKeyException;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.impl.LicenseKeyImpl;
import com.liferay.osb.model.impl.LicenseKeyModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.PhonePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistence
 * @see LicenseKeyUtil
 * @generated
 */
public class LicenseKeyPersistenceImpl extends BasePersistenceImpl<LicenseKey>
	implements LicenseKeyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LicenseKeyUtil} to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LicenseKeyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LICENSEKEYSETID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLicenseKeySetId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLicenseKeySetId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.LICENSEKEYSETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LICENSEKEYSETID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLicenseKeySetId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTENTRYID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfferingEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfferingEntryId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OFFERINGENTRYID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOfferingEntryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrderEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrderEntryId",
			new String[] { Long.class.getName() },
			LicenseKeyModelImpl.ORDERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORDERENTRYID = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrderEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_AEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LicenseKeyModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AEI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_CI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_O",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_O",
			new String[] { Long.class.getName(), String.class.getName() },
			LicenseKeyModelImpl.ORDERENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.OWNER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_O = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_O",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPI_SI",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPI_SI",
			new String[] { String.class.getName(), String.class.getName() },
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PI_SI = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPI_SI",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_ARLI_PI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_ARLI_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_ARLI_PI =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_ARLI_PI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_CI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEN_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTENTRYNAME_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEN_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_PI_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ARLI_PI_SI_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLI_PI_SI_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_LET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.LICENSEENTRYTYPE_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OEI_LET_C_A = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTLET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTLET_C_A =
		new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	public void cacheResult(LicenseKey licenseKey) {
		EntityCacheUtil.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey(), licenseKey);

		licenseKey.resetOriginalValues();
	}

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	public void cacheResult(List<LicenseKey> licenseKeies) {
		for (LicenseKey licenseKey : licenseKeies) {
			if (EntityCacheUtil.getResult(
						LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeyImpl.class, licenseKey.getPrimaryKey()) == null) {
				cacheResult(licenseKey);
			}
			else {
				licenseKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license keies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LicenseKeyImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LicenseKeyImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKey licenseKey) {
		EntityCacheUtil.removeResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKey> licenseKeies) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKey licenseKey : licenseKeies) {
			EntityCacheUtil.removeResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyImpl.class, licenseKey.getPrimaryKey());
		}
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	public LicenseKey create(long licenseKeyId) {
		LicenseKey licenseKey = new LicenseKeyImpl();

		licenseKey.setNew(true);
		licenseKey.setPrimaryKey(licenseKeyId);

		return licenseKey;
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException, SystemException {
		return remove(Long.valueOf(licenseKeyId));
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key that was removed
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKey remove(Serializable primaryKey)
		throws NoSuchLicenseKeyException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LicenseKey licenseKey = (LicenseKey)session.get(LicenseKeyImpl.class,
					primaryKey);

			if (licenseKey == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(licenseKey);
		}
		catch (NoSuchLicenseKeyException nsee) {
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
	protected LicenseKey removeImpl(LicenseKey licenseKey)
		throws SystemException {
		licenseKey = toUnwrappedModel(licenseKey);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, licenseKey);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(licenseKey);

		return licenseKey;
	}

	@Override
	public LicenseKey updateImpl(com.liferay.osb.model.LicenseKey licenseKey,
		boolean merge) throws SystemException {
		licenseKey = toUnwrappedModel(licenseKey);

		boolean isNew = licenseKey.isNew();

		LicenseKeyModelImpl licenseKeyModelImpl = (LicenseKeyModelImpl)licenseKey;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, licenseKey, merge);

			licenseKey.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LicenseKeyModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalLicenseKeySetId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getLicenseKeySetId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOfferingEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOfferingEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOrderEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOrderEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalUserId()),
						Long.valueOf(licenseKeyModelImpl.getOriginalAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getUserId()),
						Long.valueOf(licenseKeyModelImpl.getAccountEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalAssetReceiptLicenseId()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getAssetReceiptLicenseId()),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOfferingEntryId()),
						Long.valueOf(licenseKeyModelImpl.getOriginalClusterId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_CI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOfferingEntryId()),
						Long.valueOf(licenseKeyModelImpl.getClusterId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_CI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOrderEntryId()),
						
						licenseKeyModelImpl.getOriginalOwner()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_O, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOrderEntryId()),
						
						licenseKeyModelImpl.getOwner()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_O, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalProductId(),
						
						licenseKeyModelImpl.getOriginalServerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PI_SI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getProductId(),
						
						licenseKeyModelImpl.getServerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PI_SI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalAssetReceiptLicenseId()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getAssetReceiptLicenseId()),
						Boolean.valueOf(licenseKeyModelImpl.getComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOfferingEntryId()),
						Long.valueOf(licenseKeyModelImpl.getOriginalClusterId()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_CI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOfferingEntryId()),
						Long.valueOf(licenseKeyModelImpl.getClusterId()),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_CI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOfferingEntryId()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOfferingEntryId()),
						Boolean.valueOf(licenseKeyModelImpl.getComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						licenseKeyModelImpl.getOriginalProductEntryName(),
						
						licenseKeyModelImpl.getOriginalServerId(),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEN_SI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A,
					args);

				args = new Object[] {
						licenseKeyModelImpl.getProductEntryName(),
						
						licenseKeyModelImpl.getServerId(),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEN_SI_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalAssetReceiptLicenseId()),
						
						licenseKeyModelImpl.getOriginalProductId(),
						
						licenseKeyModelImpl.getOriginalServerId(),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getAssetReceiptLicenseId()),
						
						licenseKeyModelImpl.getProductId(),
						
						licenseKeyModelImpl.getServerId(),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOriginalOfferingEntryId()),
						
						licenseKeyModelImpl.getOriginalLicenseEntryType(),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getOriginalActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A,
					args);

				args = new Object[] {
						Long.valueOf(licenseKeyModelImpl.getOfferingEntryId()),
						
						licenseKeyModelImpl.getLicenseEntryType(),
						Boolean.valueOf(licenseKeyModelImpl.getComplimentary()),
						Boolean.valueOf(licenseKeyModelImpl.getActive())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A,
					args);
			}
		}

		EntityCacheUtil.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyImpl.class, licenseKey.getPrimaryKey(), licenseKey);

		return licenseKey;
	}

	protected LicenseKey toUnwrappedModel(LicenseKey licenseKey) {
		if (licenseKey instanceof LicenseKeyImpl) {
			return licenseKey;
		}

		LicenseKeyImpl licenseKeyImpl = new LicenseKeyImpl();

		licenseKeyImpl.setNew(licenseKey.isNew());
		licenseKeyImpl.setPrimaryKey(licenseKey.getPrimaryKey());

		licenseKeyImpl.setLicenseKeyId(licenseKey.getLicenseKeyId());
		licenseKeyImpl.setUserId(licenseKey.getUserId());
		licenseKeyImpl.setUserName(licenseKey.getUserName());
		licenseKeyImpl.setCreateDate(licenseKey.getCreateDate());
		licenseKeyImpl.setModifiedUserId(licenseKey.getModifiedUserId());
		licenseKeyImpl.setModifiedUserName(licenseKey.getModifiedUserName());
		licenseKeyImpl.setModifiedDate(licenseKey.getModifiedDate());
		licenseKeyImpl.setLicenseKeySetId(licenseKey.getLicenseKeySetId());
		licenseKeyImpl.setAssetReceiptLicenseId(licenseKey.getAssetReceiptLicenseId());
		licenseKeyImpl.setAccountEntryId(licenseKey.getAccountEntryId());
		licenseKeyImpl.setOrderEntryId(licenseKey.getOrderEntryId());
		licenseKeyImpl.setOfferingEntryId(licenseKey.getOfferingEntryId());
		licenseKeyImpl.setLicenseEntryId(licenseKey.getLicenseEntryId());
		licenseKeyImpl.setProductEntryId(licenseKey.getProductEntryId());
		licenseKeyImpl.setSupportResponseId(licenseKey.getSupportResponseId());
		licenseKeyImpl.setAccountEntryName(licenseKey.getAccountEntryName());
		licenseKeyImpl.setLicenseEntryName(licenseKey.getLicenseEntryName());
		licenseKeyImpl.setLicenseEntryType(licenseKey.getLicenseEntryType());
		licenseKeyImpl.setLicenseVersion(licenseKey.getLicenseVersion());
		licenseKeyImpl.setProductEntryName(licenseKey.getProductEntryName());
		licenseKeyImpl.setProductId(licenseKey.getProductId());
		licenseKeyImpl.setProductVersion(licenseKey.getProductVersion());
		licenseKeyImpl.setProductVersionLabel(licenseKey.getProductVersionLabel());
		licenseKeyImpl.setClusterId(licenseKey.getClusterId());
		licenseKeyImpl.setOwner(licenseKey.getOwner());
		licenseKeyImpl.setMaxServers(licenseKey.getMaxServers());
		licenseKeyImpl.setMaxConcurrentUsers(licenseKey.getMaxConcurrentUsers());
		licenseKeyImpl.setMaxUsers(licenseKey.getMaxUsers());
		licenseKeyImpl.setMaxHttpSessions(licenseKey.getMaxHttpSessions());
		licenseKeyImpl.setDescription(licenseKey.getDescription());
		licenseKeyImpl.setHostName(licenseKey.getHostName());
		licenseKeyImpl.setIpAddresses(licenseKey.getIpAddresses());
		licenseKeyImpl.setMacAddresses(licenseKey.getMacAddresses());
		licenseKeyImpl.setServerId(licenseKey.getServerId());
		licenseKeyImpl.setKey(licenseKey.getKey());
		licenseKeyImpl.setStartDate(licenseKey.getStartDate());
		licenseKeyImpl.setExpirationDate(licenseKey.getExpirationDate());
		licenseKeyImpl.setAdditionalInfo(licenseKey.getAdditionalInfo());
		licenseKeyImpl.setComplimentary(licenseKey.isComplimentary());
		licenseKeyImpl.setActive(licenseKey.isActive());

		return licenseKeyImpl;
	}

	/**
	 * Returns the license key with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key
	 * @throws com.liferay.portal.NoSuchModelException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license key with the primary key or throws a {@link com.liferay.osb.NoSuchLicenseKeyException} if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByPrimaryKey(licenseKeyId);

		if (licenseKey == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + licenseKeyId);
			}

			throw new NoSuchLicenseKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				licenseKeyId);
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByPrimaryKey(long licenseKeyId)
		throws SystemException {
		LicenseKey licenseKey = (LicenseKey)EntityCacheUtil.getResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyImpl.class, licenseKeyId);

		if (licenseKey == _nullLicenseKey) {
			return null;
		}

		if (licenseKey == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				licenseKey = (LicenseKey)session.get(LicenseKeyImpl.class,
						Long.valueOf(licenseKeyId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (licenseKey != null) {
					cacheResult(licenseKey);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeyImpl.class, licenseKeyId, _nullLicenseKey);
				}

				closeSession(session);
			}
		}

		return licenseKey;
	}

	/**
	 * Returns all the license keies where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByUserId(long userId, int start, int end,
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

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((userId != licenseKey.getUserId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByUserId_First(userId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByUserId_Last(userId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<LicenseKey> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByUserId_PrevAndNext(long licenseKeyId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByUserId_PrevAndNext(session, licenseKey, userId,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByUserId_PrevAndNext(session, licenseKey, userId,
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

	protected LicenseKey getByUserId_PrevAndNext(Session session,
		LicenseKey licenseKey, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

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

		else {
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId)
		throws SystemException {
		return findByLicenseKeySetId(licenseKeySetId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end) throws SystemException {
		return findByLicenseKeySetId(licenseKeySetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LICENSEKEYSETID;
			finderArgs = new Object[] { licenseKeySetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LICENSEKEYSETID;
			finderArgs = new Object[] {
					licenseKeySetId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((licenseKeySetId != licenseKey.getLicenseKeySetId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(licenseKeySetId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByLicenseKeySetId_First(long licenseKeySetId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByLicenseKeySetId_First(licenseKeySetId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("licenseKeySetId=");
		msg.append(licenseKeySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByLicenseKeySetId_First(long licenseKeySetId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByLicenseKeySetId(licenseKeySetId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByLicenseKeySetId_Last(licenseKeySetId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("licenseKeySetId=");
		msg.append(licenseKeySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLicenseKeySetId(licenseKeySetId);

		List<LicenseKey> list = findByLicenseKeySetId(licenseKeySetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByLicenseKeySetId_PrevAndNext(long licenseKeyId,
		long licenseKeySetId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByLicenseKeySetId_PrevAndNext(session, licenseKey,
					licenseKeySetId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByLicenseKeySetId_PrevAndNext(session, licenseKey,
					licenseKeySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByLicenseKeySetId_PrevAndNext(Session session,
		LicenseKey licenseKey, long licenseKeySetId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(licenseKeySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByAccountEntryId(long accountEntryId)
		throws SystemException {
		return findByAccountEntryId(accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end) throws SystemException {
		return findByAccountEntryId(accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] { accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTENTRYID;
			finderArgs = new Object[] {
					accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((accountEntryId != licenseKey.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByAccountEntryId_First(accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByAccountEntryId(accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByAccountEntryId_Last(accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccountEntryId(accountEntryId);

		List<LicenseKey> list = findByAccountEntryId(accountEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByAccountEntryId_PrevAndNext(long licenseKeyId,
		long accountEntryId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(session, licenseKey,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByAccountEntryId_PrevAndNext(session, licenseKey,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByAccountEntryId_PrevAndNext(Session session,
		LicenseKey licenseKey, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		return findByOfferingEntryId(offeringEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end) throws SystemException {
		return findByOfferingEntryId(offeringEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] { offeringEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OFFERINGENTRYID;
			finderArgs = new Object[] {
					offeringEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOfferingEntryId_First(offeringEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOfferingEntryId(offeringEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOfferingEntryId_Last(offeringEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOfferingEntryId(offeringEntryId);

		List<LicenseKey> list = findByOfferingEntryId(offeringEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOfferingEntryId_PrevAndNext(long licenseKeyId,
		long offeringEntryId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOfferingEntryId_PrevAndNext(session, licenseKey,
					offeringEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOfferingEntryId_PrevAndNext(session, licenseKey,
					offeringEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOfferingEntryId_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOrderEntryId(long orderEntryId)
		throws SystemException {
		return findByOrderEntryId(orderEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOrderEntryId(long orderEntryId, int start,
		int end) throws SystemException {
		return findByOrderEntryId(orderEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where orderEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOrderEntryId(long orderEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] { orderEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORDERENTRYID;
			finderArgs = new Object[] {
					orderEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((orderEntryId != licenseKey.getOrderEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOrderEntryId_First(long orderEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOrderEntryId_First(orderEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOrderEntryId_First(long orderEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOrderEntryId(orderEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOrderEntryId_Last(long orderEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOrderEntryId_Last(orderEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOrderEntryId_Last(long orderEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOrderEntryId(orderEntryId);

		List<LicenseKey> list = findByOrderEntryId(orderEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param orderEntryId the order entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOrderEntryId_PrevAndNext(long licenseKeyId,
		long orderEntryId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOrderEntryId_PrevAndNext(session, licenseKey,
					orderEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOrderEntryId_PrevAndNext(session, licenseKey,
					orderEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOrderEntryId_PrevAndNext(Session session,
		LicenseKey licenseKey, long orderEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		return findByU_AEI(userId, accountEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId,
		int start, int end) throws SystemException {
		return findByU_AEI(userId, accountEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_AEI;
			finderArgs = new Object[] { userId, accountEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_AEI;
			finderArgs = new Object[] {
					userId, accountEntryId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((userId != licenseKey.getUserId()) ||
						(accountEntryId != licenseKey.getAccountEntryId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByU_AEI_First(long userId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByU_AEI_First(userId, accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByU_AEI_First(long userId, long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByU_AEI(userId, accountEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByU_AEI_Last(long userId, long accountEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByU_AEI_Last(userId, accountEntryId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", accountEntryId=");
		msg.append(accountEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByU_AEI_Last(long userId, long accountEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_AEI(userId, accountEntryId);

		List<LicenseKey> list = findByU_AEI(userId, accountEntryId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByU_AEI_PrevAndNext(long licenseKeyId, long userId,
		long accountEntryId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_AEI_PrevAndNext(session, licenseKey, userId,
					accountEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByU_AEI_PrevAndNext(session, licenseKey, userId,
					accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByU_AEI_PrevAndNext(Session session,
		LicenseKey licenseKey, long userId, long accountEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_U_AEI_USERID_2);

		query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(accountEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active) throws SystemException {
		return findByARLI_A(assetReceiptLicenseId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end) throws SystemException {
		return findByARLI_A(assetReceiptLicenseId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_A;
			finderArgs = new Object[] { assetReceiptLicenseId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_A_First(assetReceiptLicenseId,
				active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		List<LicenseKey> list = findByARLI_A(assetReceiptLicenseId, active, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_A_Last(assetReceiptLicenseId,
				active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByARLI_A(assetReceiptLicenseId, active);

		List<LicenseKey> list = findByARLI_A(assetReceiptLicenseId, active,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByARLI_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

		query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId)
		throws SystemException {
		return findByOEI_CI(offeringEntryId, clusterId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId,
		int start, int end) throws SystemException {
		return findByOEI_CI(offeringEntryId, clusterId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI;
			finderArgs = new Object[] { offeringEntryId, clusterId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI;
			finderArgs = new Object[] {
					offeringEntryId, clusterId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(clusterId != licenseKey.getClusterId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_CI_First(long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_CI_First(offeringEntryId, clusterId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_CI_First(long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOEI_CI(offeringEntryId, clusterId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_CI_Last(long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_CI_Last(offeringEntryId, clusterId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_CI_Last(long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOEI_CI(offeringEntryId, clusterId);

		List<LicenseKey> list = findByOEI_CI(offeringEntryId, clusterId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_CI_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_CI_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, long clusterId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(clusterId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner)
		throws SystemException {
		return findByOEI_O(orderEntryId, owner, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner,
		int start, int end) throws SystemException {
		return findByOEI_O(orderEntryId, owner, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_O(long orderEntryId, String owner,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_O;
			finderArgs = new Object[] { orderEntryId, owner };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_O;
			finderArgs = new Object[] {
					orderEntryId, owner,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((orderEntryId != licenseKey.getOrderEntryId()) ||
						!Validator.equals(owner, licenseKey.getOwner())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

			if (owner == null) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
			}
			else {
				if (owner.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				if (owner != null) {
					qPos.add(owner);
				}

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_O_First(long orderEntryId, String owner,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_O_First(orderEntryId, owner,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", owner=");
		msg.append(owner);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_O_First(long orderEntryId, String owner,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOEI_O(orderEntryId, owner, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_O_Last(long orderEntryId, String owner,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_O_Last(orderEntryId, owner,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("orderEntryId=");
		msg.append(orderEntryId);

		msg.append(", owner=");
		msg.append(owner);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_O_Last(long orderEntryId, String owner,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOEI_O(orderEntryId, owner);

		List<LicenseKey> list = findByOEI_O(orderEntryId, owner, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_O_PrevAndNext(long licenseKeyId,
		long orderEntryId, String owner, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_O_PrevAndNext(session, licenseKey,
					orderEntryId, owner, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_O_PrevAndNext(session, licenseKey,
					orderEntryId, owner, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_O_PrevAndNext(Session session,
		LicenseKey licenseKey, long orderEntryId, String owner,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

		if (owner == null) {
			query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
		}
		else {
			if (owner.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
			}
			else {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
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

		else {
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(orderEntryId);

		if (owner != null) {
			qPos.add(owner);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPI_SI(String productId, String serverId)
		throws SystemException {
		return findByPI_SI(productId, serverId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPI_SI(String productId, String serverId,
		int start, int end) throws SystemException {
		return findByPI_SI(productId, serverId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPI_SI(String productId, String serverId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PI_SI;
			finderArgs = new Object[] { productId, serverId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PI_SI;
			finderArgs = new Object[] {
					productId, serverId,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if (!Validator.equals(productId, licenseKey.getProductId()) ||
						!Validator.equals(serverId, licenseKey.getServerId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			if (productId == null) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (productId != null) {
					qPos.add(productId);
				}

				if (serverId != null) {
					qPos.add(serverId);
				}

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByPI_SI_First(String productId, String serverId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByPI_SI_First(productId, serverId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByPI_SI_First(String productId, String serverId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByPI_SI(productId, serverId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByPI_SI_Last(String productId, String serverId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByPI_SI_Last(productId, serverId,
				orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByPI_SI_Last(String productId, String serverId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPI_SI(productId, serverId);

		List<LicenseKey> list = findByPI_SI(productId, serverId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByPI_SI_PrevAndNext(long licenseKeyId,
		String productId, String serverId, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPI_SI_PrevAndNext(session, licenseKey, productId,
					serverId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPI_SI_PrevAndNext(session, licenseKey, productId,
					serverId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByPI_SI_PrevAndNext(Session session,
		LicenseKey licenseKey, String productId, String serverId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		if (productId == null) {
			query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
		}
		else {
			if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}
		}

		if (serverId == null) {
			query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
		}
		else {
			if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
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

		else {
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (productId != null) {
			qPos.add(productId);
		}

		if (serverId != null) {
			qPos.add(serverId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId) throws SystemException {
		return findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId, int start, int end)
		throws SystemException {
		return findByU_ARLI_PI(userId, assetReceiptLicenseId, productId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, String productId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_ARLI_PI;
		finderArgs = new Object[] {
				userId, assetReceiptLicenseId, productId,
				
				start, end, orderByComparator
			};

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((userId != licenseKey.getUserId()) ||
						(assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
						!Validator.equals(productId, licenseKey.getProductId())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

			query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assetReceiptLicenseId);

				if (productId != null) {
					qPos.add(productId);
				}

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByU_ARLI_PI_First(userId,
				assetReceiptLicenseId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByU_ARLI_PI(userId, assetReceiptLicenseId,
				productId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByU_ARLI_PI_Last(userId,
				assetReceiptLicenseId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, String productId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_ARLI_PI(userId, assetReceiptLicenseId, productId);

		List<LicenseKey> list = findByU_ARLI_PI(userId, assetReceiptLicenseId,
				productId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByU_ARLI_PI_PrevAndNext(long licenseKeyId,
		long userId, long assetReceiptLicenseId, String productId,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_ARLI_PI_PrevAndNext(session, licenseKey, userId,
					assetReceiptLicenseId, productId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByU_ARLI_PI_PrevAndNext(session, licenseKey, userId,
					assetReceiptLicenseId, productId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByU_ARLI_PI_PrevAndNext(Session session,
		LicenseKey licenseKey, long userId, long assetReceiptLicenseId,
		String productId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

		query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

		if (productId == null) {
			query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
		}
		else {
			if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
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

		else {
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(assetReceiptLicenseId);

		if (productId != null) {
			qPos.add(productId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) throws SystemException {
		return findByARLI_C_A(assetReceiptLicenseId, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end)
		throws SystemException {
		return findByARLI_C_A(assetReceiptLicenseId, complimentary, active,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_C_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, complimentary, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_C_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
						(complimentary != licenseKey.getComplimentary()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(complimentary);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_C_A_First(assetReceiptLicenseId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_C_A_Last(assetReceiptLicenseId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByARLI_C_A(assetReceiptLicenseId, complimentary, active);

		List<LicenseKey> list = findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByARLI_C_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_C_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_C_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, complimentary, active,
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

	protected LicenseKey getByARLI_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

		query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active) throws SystemException {
		return findByOEI_CI_A(offeringEntryId, clusterId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end)
		throws SystemException {
		return findByOEI_CI_A(offeringEntryId, clusterId, active, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_CI_A;
			finderArgs = new Object[] { offeringEntryId, clusterId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_CI_A;
			finderArgs = new Object[] {
					offeringEntryId, clusterId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(clusterId != licenseKey.getClusterId()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_CI_A_First(offeringEntryId,
				clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		List<LicenseKey> list = findByOEI_CI_A(offeringEntryId, clusterId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_CI_A_Last(long offeringEntryId, long clusterId,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_CI_A_Last(offeringEntryId,
				clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", clusterId=");
		msg.append(clusterId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_CI_A_Last(long offeringEntryId,
		long clusterId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOEI_CI_A(offeringEntryId, clusterId, active);

		List<LicenseKey> list = findByOEI_CI_A(offeringEntryId, clusterId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_CI_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_A_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_A_PrevAndNext(session, licenseKey,
					offeringEntryId, clusterId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_CI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, long clusterId,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

		query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(clusterId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active) throws SystemException {
		return findByOEI_C_A(offeringEntryId, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end)
		throws SystemException {
		return findByOEI_C_A(offeringEntryId, complimentary, active, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_C_A;
			finderArgs = new Object[] { offeringEntryId, complimentary, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A;
			finderArgs = new Object[] {
					offeringEntryId, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(complimentary != licenseKey.getComplimentary()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(complimentary);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_C_A_First(offeringEntryId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOEI_C_A(offeringEntryId, complimentary,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_C_A_Last(offeringEntryId,
				complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOEI_C_A(offeringEntryId, complimentary, active);

		List<LicenseKey> list = findByOEI_C_A(offeringEntryId, complimentary,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, complimentary, active, orderByComparator,
					true);

			array[1] = licenseKey;

			array[2] = getByOEI_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, complimentary, active, orderByComparator,
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

	protected LicenseKey getByOEI_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, boolean complimentary,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

		query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active) throws SystemException {
		return findByOEI_C_A(offeringEntryIds, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end)
		throws SystemException {
		return findByOEI_C_A(offeringEntryIds, complimentary, active, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_C_A;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(offeringEntryIds), complimentary, active
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(offeringEntryIds), complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if (!ArrayUtil.contains(offeringEntryIds,
							licenseKey.getOfferingEntryId()) ||
						(complimentary != licenseKey.getComplimentary()) ||
						(active != licenseKey.getActive())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean conjunctionable = false;

			if ((offeringEntryIds == null) || (offeringEntryIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < offeringEntryIds.length; i++) {
					query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_5);

					if ((i + 1) < offeringEntryIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (offeringEntryIds != null) {
					qPos.add(offeringEntryIds);
				}

				qPos.add(complimentary);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active) throws SystemException {
		return findByPEN_SI_A(productEntryName, serverId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active, int start, int end)
		throws SystemException {
		return findByPEN_SI_A(productEntryName, serverId, active, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByPEN_SI_A(String productEntryName,
		String serverId, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEN_SI_A;
			finderArgs = new Object[] { productEntryName, serverId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEN_SI_A;
			finderArgs = new Object[] {
					productEntryName, serverId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if (!Validator.equals(productEntryName,
							licenseKey.getProductEntryName()) ||
						!Validator.equals(serverId, licenseKey.getServerId()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			if (productEntryName == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
			}
			else {
				if (productEntryName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
				}
			}

			query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (productEntryName != null) {
					qPos.add(productEntryName);
				}

				if (serverId != null) {
					qPos.add(serverId);
				}

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByPEN_SI_A_First(String productEntryName,
		String serverId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByPEN_SI_A_First(productEntryName,
				serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryName=");
		msg.append(productEntryName);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByPEN_SI_A_First(String productEntryName,
		String serverId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		List<LicenseKey> list = findByPEN_SI_A(productEntryName, serverId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByPEN_SI_A_Last(String productEntryName,
		String serverId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByPEN_SI_A_Last(productEntryName,
				serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryName=");
		msg.append(productEntryName);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByPEN_SI_A_Last(String productEntryName,
		String serverId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPEN_SI_A(productEntryName, serverId, active);

		List<LicenseKey> list = findByPEN_SI_A(productEntryName, serverId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByPEN_SI_A_PrevAndNext(long licenseKeyId,
		String productEntryName, String serverId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPEN_SI_A_PrevAndNext(session, licenseKey,
					productEntryName, serverId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPEN_SI_A_PrevAndNext(session, licenseKey,
					productEntryName, serverId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByPEN_SI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, String productEntryName, String serverId,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		if (productEntryName == null) {
			query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
		}
		else {
			if (productEntryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
			}
		}

		if (serverId == null) {
			query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
		}
		else {
			if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
			}
		}

		query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (productEntryName != null) {
			qPos.add(productEntryName);
		}

		if (serverId != null) {
			qPos.add(serverId);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active)
		throws SystemException {
		return findByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active, int start, int end)
		throws SystemException {
		return findByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ARLI_PI_SI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, productId, serverId, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ARLI_PI_SI_A;
			finderArgs = new Object[] {
					assetReceiptLicenseId, productId, serverId, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((assetReceiptLicenseId != licenseKey.getAssetReceiptLicenseId()) ||
						!Validator.equals(productId, licenseKey.getProductId()) ||
						!Validator.equals(serverId, licenseKey.getServerId()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
				}
			}

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				if (productId != null) {
					qPos.add(productId);
				}

				if (serverId != null) {
					qPos.add(serverId);
				}

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_PI_SI_A_First(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_PI_SI_A_First(assetReceiptLicenseId,
				productId, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_PI_SI_A_First(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByARLI_PI_SI_A_Last(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByARLI_PI_SI_A_Last(assetReceiptLicenseId,
				productId, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetReceiptLicenseId=");
		msg.append(assetReceiptLicenseId);

		msg.append(", productId=");
		msg.append(productId);

		msg.append(", serverId=");
		msg.append(serverId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByARLI_PI_SI_A_Last(long assetReceiptLicenseId,
		String productId, String serverId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByARLI_PI_SI_A(assetReceiptLicenseId, productId,
				serverId, active);

		List<LicenseKey> list = findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByARLI_PI_SI_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, String productId, String serverId,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLI_PI_SI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, productId, serverId, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLI_PI_SI_A_PrevAndNext(session, licenseKey,
					assetReceiptLicenseId, productId, serverId, active,
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

	protected LicenseKey getByARLI_PI_SI_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long assetReceiptLicenseId, String productId,
		String serverId, boolean active, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

		if (productId == null) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
		}
		else {
			if (productId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
			}
			else {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
			}
		}

		if (serverId == null) {
			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
		}
		else {
			if (serverId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
			}
		}

		query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetReceiptLicenseId);

		if (productId != null) {
			qPos.add(productId);
		}

		if (serverId != null) {
			qPos.add(serverId);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		return findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end) throws SystemException {
		return findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OEI_LET_C_A;
			finderArgs = new Object[] {
					offeringEntryId, licenseEntryType, complimentary, active
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_LET_C_A;
			finderArgs = new Object[] {
					offeringEntryId, licenseEntryType, complimentary, active,
					
					start, end, orderByComparator
				};
		}

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						!Validator.equals(licenseEntryType,
							licenseKey.getLicenseEntryType()) ||
						(complimentary != licenseKey.getComplimentary()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
			}
			else {
				if (licenseEntryType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
				}
			}

			query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (licenseEntryType != null) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_LET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_LET_C_A_First(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_LET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_LET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_LET_C_A_Last(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_LET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOEI_LET_C_A(offeringEntryId, licenseEntryType,
				complimentary, active);

		List<LicenseKey> list = findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_LET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_LET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_LET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
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

	protected LicenseKey getByOEI_LET_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

		if (licenseEntryType == null) {
			query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
		}
		else {
			if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
			}
		}

		query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (licenseEntryType != null) {
			qPos.add(licenseEntryType);
		}

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		return findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end) throws SystemException {
		return findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OEI_NOTLET_C_A;
		finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active,
				
				start, end, orderByComparator
			};

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (LicenseKey licenseKey : list) {
				if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						!Validator.equals(licenseEntryType,
							licenseKey.getLicenseEntryType()) ||
						(complimentary != licenseKey.getComplimentary()) ||
						(active != licenseKey.getActive())) {
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

			query.append(_SQL_SELECT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
			}
			else {
				if (licenseEntryType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
				}
			}

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (licenseEntryType != null) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_NotLET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_First(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_NotLET_C_A_First(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<LicenseKey> list = findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey findByOEI_NotLET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_Last(offeringEntryId,
				licenseEntryType, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("offeringEntryId=");
		msg.append(offeringEntryId);

		msg.append(", licenseEntryType=");
		msg.append(licenseEntryType);

		msg.append(", complimentary=");
		msg.append(complimentary);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLicenseKeyException(msg.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey fetchByOEI_NotLET_C_A_Last(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
				complimentary, active);

		List<LicenseKey> list = findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws com.liferay.osb.NoSuchLicenseKeyException if a license key with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchLicenseKeyException, SystemException {
		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_NotLET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
					orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_NotLET_C_A_PrevAndNext(session, licenseKey,
					offeringEntryId, licenseEntryType, complimentary, active,
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

	protected LicenseKey getByOEI_NotLET_C_A_PrevAndNext(Session session,
		LicenseKey licenseKey, long offeringEntryId, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LICENSEKEY_WHERE);

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

		if (licenseEntryType == null) {
			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
		}
		else {
			if (licenseEntryType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}
		}

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

		query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

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
			query.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(offeringEntryId);

		if (licenseEntryType != null) {
			qPos.add(licenseEntryType);
		}

		qPos.add(complimentary);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(licenseKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LicenseKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license keies
	 * @throws SystemException if a system exception occurred
	 */
	public List<LicenseKey> findAll(int start, int end,
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

		List<LicenseKey> list = (List<LicenseKey>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LICENSEKEY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEY.concat(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<LicenseKey>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the license keies where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (LicenseKey licenseKey : findByUserId(userId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where licenseKeySetId = &#63; from the database.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLicenseKeySetId(long licenseKeySetId)
		throws SystemException {
		for (LicenseKey licenseKey : findByLicenseKeySetId(licenseKeySetId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountEntryId(long accountEntryId)
		throws SystemException {
		for (LicenseKey licenseKey : findByAccountEntryId(accountEntryId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		for (LicenseKey licenseKey : findByOfferingEntryId(offeringEntryId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where orderEntryId = &#63; from the database.
	 *
	 * @param orderEntryId the order entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOrderEntryId(long orderEntryId)
		throws SystemException {
		for (LicenseKey licenseKey : findByOrderEntryId(orderEntryId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		for (LicenseKey licenseKey : findByU_AEI(userId, accountEntryId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARLI_A(long assetReceiptLicenseId, boolean active)
		throws SystemException {
		for (LicenseKey licenseKey : findByARLI_A(assetReceiptLicenseId, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_CI(long offeringEntryId, long clusterId)
		throws SystemException {
		for (LicenseKey licenseKey : findByOEI_CI(offeringEntryId, clusterId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_O(long orderEntryId, String owner)
		throws SystemException {
		for (LicenseKey licenseKey : findByOEI_O(orderEntryId, owner)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPI_SI(String productId, String serverId)
		throws SystemException {
		for (LicenseKey licenseKey : findByPI_SI(productId, serverId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		String productId) throws SystemException {
		for (LicenseKey licenseKey : findByU_ARLI_PI(userId,
				assetReceiptLicenseId, productId)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) throws SystemException {
		for (LicenseKey licenseKey : findByARLI_C_A(assetReceiptLicenseId,
				complimentary, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) throws SystemException {
		for (LicenseKey licenseKey : findByOEI_CI_A(offeringEntryId, clusterId,
				active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active) throws SystemException {
		for (LicenseKey licenseKey : findByOEI_C_A(offeringEntryId,
				complimentary, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPEN_SI_A(String productEntryName, String serverId,
		boolean active) throws SystemException {
		for (LicenseKey licenseKey : findByPEN_SI_A(productEntryName, serverId,
				active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active)
		throws SystemException {
		for (LicenseKey licenseKey : findByARLI_PI_SI_A(assetReceiptLicenseId,
				productId, serverId, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		for (LicenseKey licenseKey : findByOEI_LET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		for (LicenseKey licenseKey : findByOEI_NotLET_C_A(offeringEntryId,
				licenseEntryType, complimentary, active)) {
			remove(licenseKey);
		}
	}

	/**
	 * Removes all the license keies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LicenseKey licenseKey : findAll()) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

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
	 * Returns the number of license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLicenseKeySetId(long licenseKeySetId)
		throws SystemException {
		Object[] finderArgs = new Object[] { licenseKeySetId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(licenseKeySetId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LICENSEKEYSETID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountEntryId(long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOfferingEntryId(long offeringEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { offeringEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OFFERINGENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where orderEntryId = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrderEntryId(long orderEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { orderEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORDERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AEI(long userId, long accountEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, accountEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_AEI_USERID_2);

			query.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARLI_A(long assetReceiptLicenseId, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { assetReceiptLicenseId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ARLI_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ARLI_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_CI(long offeringEntryId, long clusterId)
		throws SystemException {
		Object[] finderArgs = new Object[] { offeringEntryId, clusterId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OEI_CI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OEI_CI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	 *
	 * @param orderEntryId the order entry ID
	 * @param owner the owner
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_O(long orderEntryId, String owner)
		throws SystemException {
		Object[] finderArgs = new Object[] { orderEntryId, owner };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OEI_O,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_O_ORDERENTRYID_2);

			if (owner == null) {
				query.append(_FINDER_COLUMN_OEI_O_OWNER_1);
			}
			else {
				if (owner.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_O_OWNER_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_O_OWNER_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(orderEntryId);

				if (owner != null) {
					qPos.add(owner);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OEI_O,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPI_SI(String productId, String serverId)
		throws SystemException {
		Object[] finderArgs = new Object[] { productId, serverId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PI_SI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			if (productId == null) {
				query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PI_SI_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (productId != null) {
					qPos.add(productId);
				}

				if (serverId != null) {
					qPos.add(serverId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PI_SI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		String productId) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, assetReceiptLicenseId, productId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_ARLI_PI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_U_ARLI_PI_USERID_2);

			query.append(_FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(assetReceiptLicenseId);

				if (productId != null) {
					qPos.add(productId);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_ARLI_PI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) throws SystemException {
		Object[] finderArgs = new Object[] {
				assetReceiptLicenseId, complimentary, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ARLI_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_ARLI_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ARLI_C_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) throws SystemException {
		Object[] finderArgs = new Object[] { offeringEntryId, clusterId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OEI_CI_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			query.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(clusterId);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OEI_CI_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active) throws SystemException {
		Object[] finderArgs = new Object[] {
				offeringEntryId, complimentary, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OEI_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OEI_C_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_C_A(long[] offeringEntryIds, boolean complimentary,
		boolean active) throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(offeringEntryIds), complimentary, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean conjunctionable = false;

			if ((offeringEntryIds == null) || (offeringEntryIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < offeringEntryIds.length; i++) {
					query.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_5);

					if ((i + 1) < offeringEntryIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (offeringEntryIds != null) {
					qPos.add(offeringEntryIds);
				}

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_C_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEN_SI_A(String productEntryName, String serverId,
		boolean active) throws SystemException {
		Object[] finderArgs = new Object[] { productEntryName, serverId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PEN_SI_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			if (productEntryName == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1);
			}
			else {
				if (productEntryName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
				}
			}

			query.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (productEntryName != null) {
					qPos.add(productEntryName);
				}

				if (serverId != null) {
					qPos.add(serverId);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEN_SI_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseId the asset receipt license ID
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByARLI_PI_SI_A(long assetReceiptLicenseId,
		String productId, String serverId, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				assetReceiptLicenseId, productId, serverId, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2);

			if (productId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1);
			}
			else {
				if (productId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2);
				}
			}

			if (serverId == null) {
				query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1);
			}
			else {
				if (serverId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2);
				}
			}

			query.append(_FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetReceiptLicenseId);

				if (productId != null) {
					qPos.add(productId);
				}

				if (serverId != null) {
					qPos.add(serverId);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ARLI_PI_SI_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_LET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1);
			}
			else {
				if (licenseEntryType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
				}
			}

			query.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (licenseEntryType != null) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OEI_LET_C_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOEI_NotLET_C_A(long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTLET_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LICENSEKEY_WHERE);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			if (licenseEntryType == null) {
				query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1);
			}
			else {
				if (licenseEntryType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
				}
			}

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			query.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(offeringEntryId);

				if (licenseEntryType != null) {
					qPos.add(licenseEntryType);
				}

				qPos.add(complimentary);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_OEI_NOTLET_C_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LICENSEKEY);

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
	 * Initializes the license key persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.LicenseKey")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LicenseKey>> listenersList = new ArrayList<ModelListener<LicenseKey>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<LicenseKey>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LicenseKeyImpl.class.getName());
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
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = PhonePersistence.class)
	protected PhonePersistence phonePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private static final String _SQL_SELECT_LICENSEKEY = "SELECT licenseKey FROM LicenseKey licenseKey";
	private static final String _SQL_SELECT_LICENSEKEY_WHERE = "SELECT licenseKey FROM LicenseKey licenseKey WHERE ";
	private static final String _SQL_COUNT_LICENSEKEY = "SELECT COUNT(licenseKey) FROM LicenseKey licenseKey";
	private static final String _SQL_COUNT_LICENSEKEY_WHERE = "SELECT COUNT(licenseKey) FROM LicenseKey licenseKey WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "licenseKey.userId = ?";
	private static final String _FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2 =
		"licenseKey.licenseKeySetId = ?";
	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 = "licenseKey.accountEntryId = ?";
	private static final String _FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2 =
		"licenseKey.offeringEntryId = ?";
	private static final String _FINDER_COLUMN_ORDERENTRYID_ORDERENTRYID_2 = "licenseKey.orderEntryId = ?";
	private static final String _FINDER_COLUMN_U_AEI_USERID_2 = "licenseKey.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 = "licenseKey.accountEntryId = ?";
	private static final String _FINDER_COLUMN_ARLI_A_ASSETRECEIPTLICENSEID_2 = "licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_CLUSTERID_2 = "licenseKey.clusterId = ?";
	private static final String _FINDER_COLUMN_OEI_O_ORDERENTRYID_2 = "licenseKey.orderEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_1 = "licenseKey.owner IS NULL";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_2 = "licenseKey.owner = ?";
	private static final String _FINDER_COLUMN_OEI_O_OWNER_3 = "(licenseKey.owner IS NULL OR licenseKey.owner = ?)";
	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_1 = "licenseKey.productId IS NULL AND ";
	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_2 = "licenseKey.productId = ? AND ";
	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = ?) AND ";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_1 = "licenseKey.serverId IS NULL";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_2 = "licenseKey.serverId = ?";
	private static final String _FINDER_COLUMN_PI_SI_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = ?)";
	private static final String _FINDER_COLUMN_U_ARLI_PI_USERID_2 = "licenseKey.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_ARLI_PI_ASSETRECEIPTLICENSEID_2 =
		"licenseKey.assetReceiptLicenseId > ? AND ";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_1 = "licenseKey.productId IS NULL";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_2 = "licenseKey.productId = ?";
	private static final String _FINDER_COLUMN_U_ARLI_PI_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = ?)";
	private static final String _FINDER_COLUMN_ARLI_C_A_ASSETRECEIPTLICENSEID_2 = "licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_C_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_A_CLUSTERID_2 = "licenseKey.clusterId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_CI_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2) + ")";
	private static final String _FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2) + ")";
	private static final String _FINDER_COLUMN_OEI_C_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_C_A_ACTIVE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_OEI_C_A_ACTIVE_2) + ")";
	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_1 = "licenseKey.productEntryName IS NULL AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2 = "licenseKey.productEntryName = ? AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3 = "(licenseKey.productEntryName IS NULL OR licenseKey.productEntryName = ?) AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_1 = "licenseKey.serverId IS NULL AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_2 = "licenseKey.serverId = ? AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = ?) AND ";
	private static final String _FINDER_COLUMN_PEN_SI_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_ASSETRECEIPTLICENSEID_2 =
		"licenseKey.assetReceiptLicenseId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_1 = "licenseKey.productId IS NULL AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_2 = "licenseKey.productId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_PRODUCTID_3 = "(licenseKey.productId IS NULL OR licenseKey.productId = ?) AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_1 = "licenseKey.serverId IS NULL AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_2 = "licenseKey.serverId = ? AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_SERVERID_3 = "(licenseKey.serverId IS NULL OR licenseKey.serverId = ?) AND ";
	private static final String _FINDER_COLUMN_ARLI_PI_SI_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_1 = "licenseKey.licenseEntryType IS NULL AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2 = "licenseKey.licenseEntryType = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3 = "(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType = ?) AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2 = "licenseKey.active = ?";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2 = "licenseKey.offeringEntryId = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_1 =
		"licenseKey.licenseEntryType IS NOT NULL AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2 =
		"licenseKey.licenseEntryType != ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3 =
		"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType != ?) AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2 = "licenseKey.complimentary = ? AND ";
	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2 = "licenseKey.active = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKey.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LicenseKey exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LicenseKey exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LicenseKeyPersistenceImpl.class);
	private static LicenseKey _nullLicenseKey = new LicenseKeyImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<LicenseKey> toCacheModel() {
				return _nullLicenseKeyCacheModel;
			}
		};

	private static CacheModel<LicenseKey> _nullLicenseKeyCacheModel = new CacheModel<LicenseKey>() {
			public LicenseKey toEntityModel() {
				return _nullLicenseKey;
			}
		};
}