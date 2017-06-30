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
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.impl.AssetReceiptImpl;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Joan Kim
 * @author Ryan Park
 */
public class AssetReceiptFinderImpl
	extends BasePersistenceImpl<AssetReceipt> implements AssetReceiptFinder {

	public static final String COUNT_BY_PRODUCT_CLASS_NAME_ID =
		AssetReceiptFinder.class.getName() + ".countByProductClassNameId";

	public static final String FILTER_BY_ASSET_ENTRY_ID =
		AssetReceiptFinder.class.getName() + ".filterByAssetEntryId";

	public static final String FILTER_BY_CREATE_DATE_GT =
		AssetReceiptFinder.class.getName() + ".filterByCreateDateGT";

	public static final String FILTER_BY_CREATE_DATE_LT =
		AssetReceiptFinder.class.getName() + ".filterByCreateDateLT";

	public static final String FILTER_BY_OWNER_CLASS_NAME_ID =
		AssetReceiptFinder.class.getName() + ".filterByOwnerClassNameId";

	public static final String FILTER_BY_OWNER_CLASS_PK =
		AssetReceiptFinder.class.getName() + ".filterByOwnerClassPK";

	public static final String FIND_BY_PRODUCT_CLASS_NAME_ID =
		AssetReceiptFinder.class.getName() + ".findByProductClassNameId";

	public static final String JOIN_BY_APP_ENTRY =
		AssetReceiptFinder.class.getName() + ".joinByAssetEntryTitle";

	public static final String JOIN_BY_ASSET_CATEGORY_IDS =
		AssetReceiptFinder.class.getName() + ".joinByAssetCategoryIds";

	public static final String JOIN_BY_COMPATIBILITY =
		AssetReceiptFinder.class.getName() + ".joinByCompatibility";

	public static final String JOIN_BY_LICENSE_TYPE =
		AssetReceiptFinder.class.getName() + ".joinByLicenseType";

	public int countByProductClassNameId(
			Long productClassNameId, LinkedHashMap<String, Object> params)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		String assetEntryTitle = (String)params.get("assetEntryTitle");

		if (Validator.isNotNull(assetEntryTitle)) {
			assetEntryTitle = CustomSQLUtil.keywords(assetEntryTitle)[0];

			params.put("assetEntryTitle", assetEntryTitle);
		}

		String sql = CustomSQLUtil.get(COUNT_BY_PRODUCT_CLASS_NAME_ID);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, true);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(productClassNameId);
			qPos.add(productClassNameId);

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

	public List<AssetReceipt> findByProductClassNameId(
			Long productClassNameId, LinkedHashMap<String, Object> params,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		String assetEntryTitle = (String)params.get("assetEntryTitle");

		if (Validator.isNotNull(assetEntryTitle)) {
			assetEntryTitle = CustomSQLUtil.keywords(assetEntryTitle)[0];

			params.put("assetEntryTitle", assetEntryTitle);
		}

		String sql = CustomSQLUtil.get(FIND_BY_PRODUCT_CLASS_NAME_ID);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, true);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AssetReceipt", AssetReceiptImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(productClassNameId);
			qPos.add(productClassNameId);

			return (List<AssetReceipt>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getJoin(key, value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("assetEntryTitle")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_ENTRY);
		}
		else if (key.equals("assetCategoryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_CATEGORY_IDS);
		}
		else if (key.equals("compatibility")) {
			join = CustomSQLUtil.get(JOIN_BY_COMPATIBILITY);
		}
		else if (key.equals("licenseType")) {
			join = CustomSQLUtil.get(JOIN_BY_LICENSE_TYPE);
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
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("assetEntryId")) {
			join = CustomSQLUtil.get(FILTER_BY_ASSET_ENTRY_ID);
		}
		else if (key.equals("assetEntryTitle")) {
			join = CustomSQLUtil.get(JOIN_BY_APP_ENTRY);
		}
		else if (key.equals("assetCategoryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_ASSET_CATEGORY_IDS);
		}
		else if (key.equals("compatibility")) {
			join = CustomSQLUtil.get(JOIN_BY_COMPATIBILITY);
		}
		else if (key.equals("createDateGT")) {
			join = CustomSQLUtil.get(FILTER_BY_CREATE_DATE_GT);
		}
		else if (key.equals("createDateLT")) {
			join = CustomSQLUtil.get(FILTER_BY_CREATE_DATE_LT);
		}
		else if (key.equals("licenseType")) {
			join = CustomSQLUtil.get(JOIN_BY_LICENSE_TYPE);
		}
		else if (key.equals("ownerClassNameId")) {
			join = CustomSQLUtil.get(FILTER_BY_OWNER_CLASS_NAME_ID);
		}
		else if (key.equals("ownerClassPK")) {
			join = CustomSQLUtil.get(FILTER_BY_OWNER_CLASS_PK);
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

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (key.equals("endDateGT") || key.equals("endDateLT")) {
				continue;
			}

			if (key.equals("compatibility")) {
				int compatibility = (Integer)value;

				qPos.add(compatibility);
				qPos.add(PortalReleaseUtil.getBaseBuildNumber(compatibility));
				qPos.add(compatibility);
			}
			else if (key.equals("licenseType")) {
				qPos.add((Integer)value);

				Timestamp endDateGT_TS = CalendarUtil.getTimestamp(
					(Date)params.get("endDateGT"));

				qPos.add(endDateGT_TS);
				qPos.add(endDateGT_TS);

				Timestamp endDateLT_TS = CalendarUtil.getTimestamp(
					(Date)params.get("endDateLT"));

				qPos.add(endDateLT_TS);
				qPos.add(endDateLT_TS);
			}
			else if (value instanceof Date) {
				Date valueDate = (Date)value;

				if (Validator.isNotNull(valueDate)) {
					qPos.add(valueDate);
				}
			}
			else if (value instanceof Integer) {
				Integer valueInteger = (Integer)value;

				if (Validator.isNotNull(valueInteger)) {
					qPos.add(valueInteger);
				}
			}
			else if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (Validator.isNotNull(valueArray[i])) {
						qPos.add(valueArray[i]);
					}
				}
			}
			else if (value instanceof String) {
				String valueString = (String)value;

				if (Validator.isNotNull(valueString)) {
					qPos.add(valueString);
				}
			}
		}
	}

}