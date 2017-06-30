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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.impl.AppEntryImpl;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Joan Kim
 * @author Ryan Schuhler
 */
public class AppEntryFinderImpl
	extends BasePersistenceImpl<AppEntry>
	implements AppEntryFinder {

	public static final String COUNT_BY_TITLE =
		AppEntryFinder.class.getName() + ".countByTitle";

	public static final String COUNT_BY_C_P_S =
		AppEntryFinder.class.getName() + ".countByC_P_S";

	public static final String FILTER_BY_DEVELOPER_ENTRY_ID =
		AppEntryFinder.class.getName() + ".filterByDeveloperEntryId";

	public static final String FILTER_BY_NOT_DEVELOPER_ENTRY_ID =
		AppEntryFinder.class.getName() + ".filterByNotDeveloperEntryId";

	public static final String FILTER_BY_PORTAL_REQUIRED =
		AppEntryFinder.class.getName() + ".filterByPortalRequired";

	public static final String FILTER_BY_STATUS =
		AppEntryFinder.class.getName() + ".filterByStatus";

	public static final String FIND_BY_TITLE =
		AppEntryFinder.class.getName() + ".findByTitle";

	public static final String FIND_BY_C_P_S =
		AppEntryFinder.class.getName() + ".findByC_P_S";

	public static final String JOIN_BY_APP_PACKAGE_COMPATIBILITY =
		AppEntryFinder.class.getName() + ".joinByAppPackageCompatibility";

	public static final String JOIN_BY_APP_VERSION_STATUS =
		AppEntryFinder.class.getName() + ".joinByAppVersionStatus";

	public static final String JOIN_BY_ASSET_CATEGORY =
		AppEntryFinder.class.getName() + ".joinByAssetCategory";

	public static final String JOIN_BY_ASSET_RECEIPT_LICENSE =
		AppEntryFinder.class.getName() + ".joinByAssetReceiptLicense";

	public int countByTitle(
			String title, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		title = CustomSQLUtil.keywords(title)[0];

		String sql = CustomSQLUtil.get(COUNT_BY_TITLE);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
		sql = setClassNameIds(sql);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(title);
			qPos.add(title);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByC_P_S(int compatibility, boolean prepackaged, int status)
		throws SystemException {

		String sql = CustomSQLUtil.get(COUNT_BY_C_P_S);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(compatibility);
			qPos.add(prepackaged);
			qPos.add(status);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AppEntry> findByTitle(
			String title, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		title = CustomSQLUtil.keywords(title)[0];

		String sql = CustomSQLUtil.get(FIND_BY_TITLE);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);
		sql = setClassNameIds(sql);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppEntry", AppEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(title);
			qPos.add(title);

			return (List<AppEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AppEntry> findByC_P_S(
			int compatibility, boolean prepackaged, int status)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_C_P_S);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppEntry", AppEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(compatibility);
			qPos.add(prepackaged);
			qPos.add(status);

			return (List<AppEntry>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value != null) {
				sb.append(getJoin(key, value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("appPackageCompatibility")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_PACKAGE_COMPATIBILITY);
		}
		else if (key.equals("appVersionStatus")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_VERSION_STATUS);
		}
		else if (key.equals("assetCategory")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_CATEGORY);
		}
		else if (key.equals("assetReceiptLicense")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_RECEIPT_LICENSE);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value != null) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("appPackageCompatibility")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_PACKAGE_COMPATIBILITY);
		}
		else if (key.equals("appVersionStatus")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_VERSION_STATUS);
		}
		else if (key.equals("assetCategory")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_CATEGORY);
		}
		else if (key.equals("assetReceiptLicense")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_RECEIPT_LICENSE);
		}
		else if (key.equals("developerEntryId")) {
			join = CustomSQLUtil.get(FILTER_BY_DEVELOPER_ENTRY_ID);
		}
		else if (key.equals("notDeveloperEntryId")) {
			join = CustomSQLUtil.get(FILTER_BY_NOT_DEVELOPER_ENTRY_ID);
		}
		else if (key.equals("portalRequired")) {
			join = CustomSQLUtil.get(FILTER_BY_PORTAL_REQUIRED);
		}
		else if (key.equals("status")) {
			join = CustomSQLUtil.get(FILTER_BY_STATUS);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected String setClassNameIds(String sql) {
		long classNameId = PortalUtil.getClassNameId(AppEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.APPENTRY$]",
			String.valueOf(classNameId));

		classNameId = PortalUtil.getClassNameId(AppVersion.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.APPVERSION$]",
			String.valueOf(classNameId));

		classNameId = PortalUtil.getClassNameId(CorpEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.CORPENTRY$]",
			String.valueOf(classNameId));

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value == null) {
				continue;
			}

			if (key.equals("appPackageCompatibility")) {
				Integer valueInteger = (Integer)value;

				qPos.add(valueInteger);
				qPos.add(PortalReleaseUtil.getBaseBuildNumber(valueInteger));
				qPos.add(valueInteger);
				qPos.add(true);
			}
			else if (value instanceof Boolean) {
				qPos.add((Boolean)value);
			}
			else if (value instanceof Integer) {
				qPos.add((Integer)value);
			}
			else if (value instanceof Long) {
				qPos.add((Long)value);
			}
			else if (value instanceof Long[]) {
				Long[] values = (Long[])value;

				for (Long curValue : values) {
					if (curValue != null) {
						qPos.add(curValue);
					}
				}
			}
			else if (value instanceof String) {
				qPos.add((String)value);
			}
		}
	}

}