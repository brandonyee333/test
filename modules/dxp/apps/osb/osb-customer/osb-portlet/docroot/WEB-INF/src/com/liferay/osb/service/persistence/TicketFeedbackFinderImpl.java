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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.impl.TicketFeedbackImpl;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
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
 * @author Mate Thurzo
 * @author Amos Fong
 */
public class TicketFeedbackFinderImpl
	extends BasePersistenceImpl<TicketFeedback>
	implements TicketFeedbackFinder {

	public static final String COUNT_BY_AE_CD_MD_S_S_C_S_R_R_R_R =
		TicketFeedbackFinder.class.getName() +
			".countByAE_CD_MD_S_S_C_S_R_R_R_R";

	public static final String FILTER_BY_CREATE_DATE =
		TicketFeedbackFinder.class.getName() + ".filterByCreateDate";

	public static final String FILTER_BY_SUBJECT =
		TicketFeedbackFinder.class.getName() + ".filterBySubject";

	public static final String FIND_BY_AE_CD_MD_S_S_C_S_R_R_R_R =
		TicketFeedbackFinder.class.getName() +
			".findByAE_CD_MD_S_S_C_S_R_R_R_R";

	public static final String JOIN_BY_ACCOUNT_CUSTOMER =
		TicketFeedbackFinder.class.getName() + ".joinByAccountCustomer";

	public static final String JOIN_BY_PARTNER_ENTRY =
		TicketFeedbackFinder.class.getName() + ".joinByPartnerEntry";

	public static final String JOIN_BY_PARTNER_WORKER =
		TicketFeedbackFinder.class.getName() + ".joinByPartnerWorker";

	public static final String JOIN_BY_SUPPORT_TEAM =
		TicketFeedbackFinder.class.getName() + ".joinBySupportTeam";

	public static final String JOIN_BY_TICKET_ENTRY_WORKER =
		TicketFeedbackFinder.class.getName() + ".joinByTicketEntryWorker";

	public int countByAE_CD_MD_S_S_C_S_R_R_R_R(
			String accountEntryName, Date createDateGT, Date createDateLT,
			Date modifiedDateGT, Date modifiedDateLT, Integer subject,
			Integer satisfied, String comments, Integer status,
			Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
			Integer[] ratings4, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String[] accountEntryNames = CustomSQLUtil.keywords(accountEntryName);
		String[] commentsArray = CustomSQLUtil.keywords(comments);

		return countByAE_CD_MD_S_S_C_S_R_R_R_R(
			accountEntryNames, createDateGT, createDateLT, modifiedDateGT,
			modifiedDateLT, subject, satisfied, commentsArray, status, ratings1,
			ratings2, ratings3, ratings4, params, andOperator);
	}

	public List<TicketFeedback> findByKeywords(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] accountEntryNames = null;
		String[] commentsArray = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			accountEntryNames = CustomSQLUtil.keywords(keywords);
			commentsArray = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		params.put("subject", TicketFeedbackConstants.SUBJECT_LIFERAY);

		return findByAE_CD_MD_S_S_C_S_R_R_R_R(
			accountEntryNames, null, null, null, null, null, null,
			commentsArray, null, null, null, null, null, params, andOperator,
			start, end, obc);
	}

	public int countByKeywords(
			String keywords, LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] accountEntryNames = null;
		String[] commentsArray = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			accountEntryNames = CustomSQLUtil.keywords(keywords);
			commentsArray = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		params.put("subject", TicketFeedbackConstants.SUBJECT_LIFERAY);

		return countByAE_CD_MD_S_S_C_S_R_R_R_R(
			accountEntryNames, null, null, null, null, null, null,
			commentsArray, null, null, null, null, null, params, andOperator);
	}

	public List<TicketFeedback> findByAE_CD_MD_S_S_C_S_R_R_R_R(
			String accountEntryName, Date createDateGT, Date createDateLT,
			Date modifiedDateGT, Date modifiedDateLT, Integer subject,
			Integer satisfied, String comments, Integer status,
			Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
			Integer[] ratings4, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] accountEntryNames = CustomSQLUtil.keywords(accountEntryName);
		String[] commentsArray = CustomSQLUtil.keywords(comments);

		return findByAE_CD_MD_S_S_C_S_R_R_R_R(
			accountEntryNames, createDateGT, createDateLT, modifiedDateGT,
			modifiedDateLT, subject, satisfied, commentsArray, status, ratings1,
			ratings2, ratings3, ratings4, params, andOperator, start, end, obc);
	}

	protected int countByAE_CD_MD_S_S_C_S_R_R_R_R(
			String[] accountEntryNames, Date createDateGT, Date createDateLT,
			Date modifiedDateGT, Date modifiedDateLT, Integer subject,
			Integer satisfied, String[] comments, Integer status,
			Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
			Integer[] ratings4, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Long userId = (Long)params.remove("accountEntryMembership");

		LinkedHashMap<String, Object> params1 =
			new LinkedHashMap<String, Object>(params);
		LinkedHashMap<String, Object> params2 =
			new LinkedHashMap<String, Object>(params1);

		if (userId != null) {
			params1.put("accountCustomer", userId);
			params2.put("partnerWorker", userId);
		}

		accountEntryNames = CustomSQLUtil.keywords(accountEntryNames);

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		comments = CustomSQLUtil.keywords(comments);

		String sql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		StringBundler sb = new StringBundler(4);

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(COUNT_BY_AE_CD_MD_S_S_C_S_R_R_R_R),
				accountEntryNames, comments, ratings1, ratings2, ratings3,
				ratings4, params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(COUNT_BY_AE_CD_MD_S_S_C_S_R_R_R_R),
					accountEntryNames, comments, ratings1, ratings2, ratings3,
					ratings4, params2, andOperator));
			sb.append(")");
		}

		sql = StringUtil.replace(sql, "[$SELECT_SQL$]", sb.toString());

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params1, accountEntryNames, createDateGT_TS,
				createDateLT_TS, null, null, subject, satisfied, comments,
				status, ratings1, ratings2, ratings3, ratings4);

			if (userId != null) {
				setJoin(
					qPos, params2, accountEntryNames, createDateGT_TS,
					createDateLT_TS, null, null, subject, satisfied, comments,
					status, ratings1, ratings2, ratings3, ratings4);
			}

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

	protected List<TicketFeedback> findByAE_CD_MD_S_S_C_S_R_R_R_R(
			String[] accountEntryNames, Date createDateGT, Date createDateLT,
			Date modifiedDateGT, Date modifiedDateLT, Integer subject,
			Integer satisfied, String[] comments, Integer status,
			Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
			Integer[] ratings4, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Long userId = (Long)params.remove("accountEntryMembership");

		LinkedHashMap<String, Object> params1 =
			new LinkedHashMap<String, Object>(params);
		LinkedHashMap<String, Object> params2 =
			new LinkedHashMap<String, Object>(params1);

		if (userId != null) {
			params1.put("accountCustomer", userId);
			params2.put("partnerWorker", userId);
		}

		accountEntryNames = CustomSQLUtil.keywords(accountEntryNames);

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		comments = CustomSQLUtil.keywords(comments);

		StringBundler sb = new StringBundler(4);

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(FIND_BY_AE_CD_MD_S_S_C_S_R_R_R_R),
				accountEntryNames, comments, ratings1, ratings2, ratings3,
				ratings4, params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(FIND_BY_AE_CD_MD_S_S_C_S_R_R_R_R),
					accountEntryNames, comments, ratings1, ratings2, ratings3,
					ratings4, params2, andOperator));
			sb.append(")");
		}

		String sql = sb.toString();

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TicketFeedback", TicketFeedbackImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params1, accountEntryNames, createDateGT_TS,
				createDateLT_TS, modifiedDateGT_TS, modifiedDateLT_TS, subject,
				satisfied, comments, status, ratings1, ratings2, ratings3,
				ratings4);

			if (userId != null) {
				setJoin(
					qPos, params2, accountEntryNames, createDateGT_TS,
					createDateLT_TS, modifiedDateGT_TS, modifiedDateLT_TS,
					subject, satisfied, comments, status, ratings1, ratings2,
					ratings3, ratings4);
			}

			return (List<TicketFeedback>)QueryUtil.list(
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

		if (key.equals("accountCustomer")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_CUSTOMER);
		}
		else if (key.equals("assignedPartnerEntry") ||
				 key.equals("partnerEntry")) {

			join = CustomSQLUtil.get(JOIN_BY_PARTNER_ENTRY);
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("assignedSupportTeam") ||
				 key.equals("supportTeam")) {

			join = CustomSQLUtil.get(JOIN_BY_SUPPORT_TEAM);
		}
		else if (key.equals("assignedTicketEntryWorker") ||
				 key.equals("ticketEntryWorker")) {

			join = CustomSQLUtil.get(JOIN_BY_TICKET_ENTRY_WORKER);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getRatings(
		Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
		Integer[] ratings4) {

		StringBundler sb = new StringBundler();

		if ((ratings1 != null) && (ratings1.length > 0) &&
			(ratings1[0] != null)) {

			sb.append(" [$AND_OR_CONNECTOR$] (OSB_TicketFeedback.rating1 = ?)");
		}

		if ((ratings2 != null) && (ratings2.length > 0) &&
			(ratings2[0] != null)) {

			sb.append(" [$AND_OR_CONNECTOR$] (OSB_TicketFeedback.rating2 = ?)");
		}

		if ((ratings3 != null) && (ratings3.length > 0) &&
			(ratings3[0] != null)) {

			sb.append(" [$AND_OR_CONNECTOR$] (OSB_TicketFeedback.rating3 = ?)");
		}

		if ((ratings4 != null) && (ratings4.length > 0) &&
			(ratings4[0] != null)) {

			sb.append(" [$AND_OR_CONNECTOR$] (OSB_TicketFeedback.rating4 = ?)");
		}

		return sb.toString();
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

		if (key.equals("accountCustomer")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_CUSTOMER);
		}
		else if (key.equals("assignedPartnerEntry")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_ENTRY);

			long[] valueArray = (long[])value;

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerWorker.partnerEntryId", true, valueArray);
		}
		else if (key.equals("assignedSupportTeam")) {
			join = CustomSQLUtil.get(JOIN_BY_SUPPORT_TEAM);

			long[] valueArray = (long[])value;

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_SupportWorker.supportTeamId", true, valueArray);
		}
		else if (key.equals("assignedTicketEntryWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_TICKET_ENTRY_WORKER);

			long[] valueArray = (long[])value;

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_TicketWorker.userId", true, valueArray);
		}
		else if (key.equals("createDate")) {
			join = CustomSQLUtil.get(FILTER_BY_CREATE_DATE);
		}
		else if (key.equals("partnerEntry")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_ENTRY);

			Long[] valueArray = (Long[])value;

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerWorker.partnerEntryId", true,
				ArrayUtil.toArray(valueArray));
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("subject")) {
			join = CustomSQLUtil.get(FILTER_BY_SUBJECT);
		}
		else if (key.equals("supportTeam")) {
			join = CustomSQLUtil.get(JOIN_BY_SUPPORT_TEAM);

			Long[] valueArray = (Long[])value;

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_SupportWorker.supportTeamId", true,
				ArrayUtil.toArray(valueArray));
		}
		else if (key.equals("ticketEntryWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_TICKET_ENTRY_WORKER);
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
		String sql, String[] names, String[] comments, Integer[] ratings1,
		Integer[] ratings2, Integer[] ratings3, Integer[] ratings4,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.name)", StringPool.LIKE, true, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.code_)", StringPool.LIKE, true, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketFeedback.comments)", StringPool.LIKE, false,
			comments);

		sql = StringUtil.replace(
			sql, "[$RATINGS$]",
			getRatings(ratings1, ratings2, ratings3, ratings4));

		if ((ratings1 != null) && (ratings1.length != 0) &&
			(ratings1[0] != null)) {

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_TicketFeedback.rating1", true,
				ArrayUtil.toArray(ratings1));
		}

		if ((ratings2 != null) && (ratings2.length != 0) &&
			(ratings2[0] != null)) {

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_TicketFeedback.rating2", true,
				ArrayUtil.toArray(ratings2));
		}

		if ((ratings3 != null) && (ratings3.length != 0) &&
			(ratings3[0] != null)) {

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_TicketFeedback.rating3", true,
				ArrayUtil.toArray(ratings3));
		}

		if ((ratings4 != null) && (ratings4.length != 0) &&
			(ratings4[0] != null)) {

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_TicketFeedback.rating4", true,
				ArrayUtil.toArray(ratings4));
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

			if (value instanceof Date[]) {
				Date[] valueArray = (Date[])value;

				for (int i = 0; i < valueArray.length; i++) {
					Timestamp timestamp = CalendarUtil.getTimestamp(
						valueArray[i]);

					qPos.add(timestamp);
				}
			}
			else if (value instanceof Integer) {
				Integer valueInt = (Integer)value;

				if (Validator.isNotNull(valueInt)) {
					qPos.add(valueInt);
				}
			}
			else if (value instanceof long[]) {
				long[] valueArray = (long[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (Validator.isNotNull(valueArray[i])) {
						qPos.add(valueArray[i]);
					}
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
		}
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params, String[] names,
		Timestamp createDateGT_TS, Timestamp createDateLT_TS,
		Timestamp modifiedDateGT_TS, Timestamp modifiedDateLT_TS,
		Integer subject, Integer satisfied, String[] comments, Integer status,
		Integer[] ratings1, Integer[] ratings2, Integer[] ratings3,
		Integer[] ratings4) {

		setJoin(qPos, params);

		qPos.add(names, 2);
		qPos.add(names, 2);
		qPos.add(createDateGT_TS);
		qPos.add(createDateGT_TS);
		qPos.add(createDateLT_TS);
		qPos.add(createDateLT_TS);
		qPos.add(modifiedDateGT_TS);
		qPos.add(modifiedDateGT_TS);
		qPos.add(modifiedDateLT_TS);
		qPos.add(modifiedDateLT_TS);
		qPos.add(subject);
		qPos.add(subject);
		qPos.add(satisfied);
		qPos.add(satisfied);
		qPos.add(comments, 2);
		qPos.add(status);
		qPos.add(status);

		if ((ratings1 != null) && (ratings1.length > 0) &&
			(ratings1[0] != null)) {

			qPos.add(ratings1);
		}

		if ((ratings2 != null) && (ratings2.length > 0) &&
			(ratings2[0] != null)) {

			qPos.add(ratings2);
		}

		if ((ratings3 != null) && (ratings3.length > 0) &&
			(ratings3[0] != null)) {

			qPos.add(ratings3);
		}

		if ((ratings4 != null) && (ratings4.length > 0) &&
			(ratings4[0] != null)) {

			qPos.add(ratings4);
		}
	}

}