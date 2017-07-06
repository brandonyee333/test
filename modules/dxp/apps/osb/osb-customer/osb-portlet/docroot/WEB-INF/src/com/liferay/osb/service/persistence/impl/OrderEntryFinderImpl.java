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

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.service.persistence.OrderEntryFinder;
import com.liferay.osb.service.persistence.OrderEntryUtil;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.impl.OrderEntryImpl;
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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Alan Zhang
 */
public class OrderEntryFinderImpl
	extends OrderEntryFinderBaseImpl implements OrderEntryFinder {

	public static final String COUNT_BY_U_CD_MU_MD_AE_PO_S_SD_P_ASD =
		OrderEntryFinder.class.getName() +
			".countByU_CD_MU_MD_AE_PO_S_SD_P_ASD";

	public static final String FIND_BY_U_CD_MU_MD_AE_PO_S_SD_P_ASD =
		OrderEntryFinder.class.getName() + ".findByU_CD_MU_MD_AE_PO_S_SD_P_ASD";

	public static final String JOIN_BY_PRODUCT_ENTRY =
		OrderEntryFinder.class.getName() + ".joinByProductEntry";

	public int countByKeywords(
			String keywords, LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] purchaseOrderKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			purchaseOrderKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			null, null, null, null, null, null, null, purchaseOrderKeys,
			new int[0], null, null, null, null, null, params, andOperator);
	}

	public int countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			Long accountEntryId, String purchaseOrderKey, int[] statuses,
			Date startDateGT, Date startDateLT, Boolean prorated,
			Date actualStartDateGT, Date actualStartDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator)
		throws SystemException {

		String[] purchaseOrderKeys = CustomSQLUtil.keywords(purchaseOrderKey);

		return countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryId, purchaseOrderKeys,
			statuses, startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator);
	}

	public List<OrderEntry> findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			Long accountEntryId, String purchaseOrderKey, int[] statuses,
			Date startDateGT, Date startDateLT, Boolean prorated,
			Date actualStartDateGT, Date actualStartDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] purchaseOrderKeys = CustomSQLUtil.keywords(purchaseOrderKey);

		return findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryId, purchaseOrderKeys,
			statuses, startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator, start, end, obc);
	}

	public List<OrderEntry> findByKeywords(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] purchaseOrderKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			purchaseOrderKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			null, null, null, null, null, null, null, purchaseOrderKeys,
			new int[0], null, null, null, null, null, params, andOperator,
			start, end, obc);
	}

	protected int countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			Long accountEntryId, String[] purchaseOrderKeys, int[] statuses,
			Date startDateGT, Date startDateLT, Boolean prorated,
			Date actualStartDateGT, Date actualStartDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		purchaseOrderKeys = CustomSQLUtil.keywords(purchaseOrderKeys);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		Timestamp actualStartDateGT_TS = CalendarUtil.getTimestamp(
			actualStartDateGT);
		Timestamp actualStartDateLT_TS = CalendarUtil.getTimestamp(
			actualStartDateLT);

		String sql = CustomSQLUtil.get(COUNT_BY_U_CD_MU_MD_AE_PO_S_SD_P_ASD);

		sql = replaceSQL(
			sql, createUserId, modifiedUserId, accountEntryId,
			purchaseOrderKeys, statuses, prorated, params, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS,
				accountEntryId, purchaseOrderKeys, statuses, startDateGT_TS,
				startDateLT_TS, prorated, actualStartDateGT_TS,
				actualStartDateLT_TS, params);

			Iterator<Long> itr = q.list().iterator();

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

	protected List<OrderEntry> findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			Long accountEntryId, String[] purchaseOrderKeys, int[] statuses,
			Date startDateGT, Date startDateLT, Boolean prorated,
			Date actualStartDateGT, Date actualStartDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		purchaseOrderKeys = CustomSQLUtil.keywords(purchaseOrderKeys);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		Timestamp actualStartDateGT_TS = CalendarUtil.getTimestamp(
			actualStartDateGT);
		Timestamp actualStartDateLT_TS = CalendarUtil.getTimestamp(
			actualStartDateLT);

		String sql = CustomSQLUtil.get(FIND_BY_U_CD_MU_MD_AE_PO_S_SD_P_ASD);

		sql = replaceSQL(
			sql, createUserId, modifiedUserId, accountEntryId,
			purchaseOrderKeys, statuses, prorated, params, andOperator);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_OrderEntry", OrderEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS,
				accountEntryId, purchaseOrderKeys, statuses, startDateGT_TS,
				startDateLT_TS, prorated, actualStartDateGT_TS,
				actualStartDateLT_TS, params);

			return (List<OrderEntry>)QueryUtil.list(
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
		if (params.isEmpty()) {
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

		if (key.equals("productEntryId")) {
			join = CustomSQLUtil.get(JOIN_BY_PRODUCT_ENTRY);
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

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("productEntryId")) {
			join = CustomSQLUtil.get(JOIN_BY_PRODUCT_ENTRY);
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

	protected String replaceSQL(
		String sql, Long createUserId, Long modifiedUserId, Long accountEntryId,
		String[] purchaseOrderKeys, int[] statuses, Boolean prorated,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		if (createUserId == null) {
			sql = StringUtil.replace(sql, _USER_ID_SQL, StringPool.BLANK);
		}

		if (modifiedUserId == null) {
			sql = StringUtil.replace(
				sql, _MODIFIED_USER_ID_SQL, StringPool.BLANK);
		}

		if (accountEntryId == null) {
			sql = StringUtil.replace(
				sql, _ACCOUNT_ENTRY_ID_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OrderEntry.status", false, statuses);

		if (prorated == null) {
			sql = StringUtil.replace(sql, _PRORATED_SQL, StringPool.BLANK);
		}

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
		}
	}

	protected void setJoin(
		QueryPos qPos, Long createUserId, Timestamp createDateGT_TS,
		Timestamp createDateLT_TS, Long modifiedUserId,
		Timestamp modifiedDateGT_TS, Timestamp modifiedDateLT_TS,
		Long accountEntryId, String[] purchaseOrderKeys, int[] statuses,
		Timestamp startDateGT_TS, Timestamp startDateLT_TS, Boolean prorated,
		Timestamp actualStartDateGT_TS, Timestamp actualStartDateLT_TS,
		LinkedHashMap<String, Object> params) {

		setJoin(qPos, params);

		if (createUserId != null) {
			qPos.add(createUserId);
		}

		qPos.add(createDateGT_TS);
		qPos.add(createDateGT_TS);
		qPos.add(createDateLT_TS);
		qPos.add(createDateLT_TS);

		if (modifiedUserId != null) {
			qPos.add(modifiedUserId);
		}

		qPos.add(modifiedDateGT_TS);
		qPos.add(modifiedDateGT_TS);
		qPos.add(modifiedDateLT_TS);
		qPos.add(modifiedDateLT_TS);

		if (accountEntryId != null) {
			qPos.add(accountEntryId);
		}

		qPos.add(purchaseOrderKeys, 2);
		qPos.add(statuses);
		qPos.add(startDateGT_TS);
		qPos.add(startDateGT_TS);
		qPos.add(startDateLT_TS);
		qPos.add(startDateLT_TS);

		if (prorated != null) {
			qPos.add(prorated);
		}

		qPos.add(actualStartDateGT_TS);
		qPos.add(actualStartDateGT_TS);
		qPos.add(actualStartDateLT_TS);
		qPos.add(actualStartDateLT_TS);
	}

	private static final String _ACCOUNT_ENTRY_ID_SQL =
		"(OSB_OrderEntry.accountEntryId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _MODIFIED_USER_ID_SQL =
		"(OSB_OrderEntry.modifiedUserId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _PRORATED_SQL =
		"(OSB_OrderEntry.prorated = ?) [$AND_OR_CONNECTOR$]";

	private static final String _USER_ID_SQL =
		"(OSB_OrderEntry.userId = ?) [$AND_OR_CONNECTOR$]";

}