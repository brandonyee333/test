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

import com.liferay.osb.service.persistence.TicketEntryFinder;
import com.liferay.osb.service.persistence.TicketEntryUtil;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.util.OSBCustomSQLUtil;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class TicketEntryFinderImpl
	extends TicketEntryFinderBaseImpl implements TicketEntryFinder {

	public static final String COUNT_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W =
		TicketEntryFinder.class.getName() +
			".countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W";

	public static final String FIND_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W =
		TicketEntryFinder.class.getName() +
			".findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W";

	public static final String FILTER_BY_ACCOUNT_ENTRY =
		TicketEntryFinder.class.getName() + ".filterByAccountEntry";

	public static final String JOIN_BY_ACCOUNT_CUSTOMER =
		TicketEntryFinder.class.getName() + ".joinByAccountCustomer";

	public static final String JOIN_BY_ACCOUNT_CUSTOMER_VISIBILITY =
		TicketEntryFinder.class.getName() + ".joinByAccountCustomerVisibility";

	public static final String FIND_BY_TICKET_COMMENT =
		TicketEntryFinder.class.getName() + ".findByTicketComment";

	public static final String JOIN_BY_ACCOUNT_WORKER =
		TicketEntryFinder.class.getName() + ".joinByAccountWorker";

	public static final String JOIN_BY_PARTNER_ENTRY =
		TicketEntryFinder.class.getName() + ".joinByPartnerEntry";

	public static final String JOIN_BY_PARTNER_WORKER =
		TicketEntryFinder.class.getName() + ".joinByPartnerWorker";

	public static final String JOIN_BY_PARTNER_WORKER_VISIBILITY =
		TicketEntryFinder.class.getName() + ".joinByPartnerWorkerVisibility";

	public static final String JOIN_BY_PENDING_TYPES =
		TicketEntryFinder.class.getName() + ".joinByPendingTypes";

	public static final String JOIN_BY_PRIMARY_TICKET_WORKER =
		TicketEntryFinder.class.getName() + ".joinByPrimaryTicketWorker";

	public static final String JOIN_BY_PRODUCT_ENTRY =
		TicketEntryFinder.class.getName() + ".joinByProductEntry";

	public static final String JOIN_BY_SUBSCRIPTION =
		TicketEntryFinder.class.getName() + ".joinBySubscription";

	public static final String JOIN_BY_SUPPORT_TEAM =
		TicketEntryFinder.class.getName() + ".joinBySupportTeam";

	public static final String JOIN_BY_TICKET_WORKER =
		TicketEntryFinder.class.getName() + ".joinByTicketWorker";

	public static final String JOIN_BY_TICKET_WORKER_COUNT =
		TicketEntryFinder.class.getName() + ".joinByTicketWorkerCount";

	public int countByKeywords(
			String keywords, LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] names = null;
		String[] subjects = null;
		String[] descriptions = null;
		String[] bodies = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			subjects = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
			bodies = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			0, names, new int[0], null, null, null, subjects, descriptions,
			bodies, new int[0], new int[0], new int[0], new int[0], new long[0],
			new long[0], new long[0], new long[0], new long[0], new int[0],
			new int[0], null, null, null, null, null, null, null, null, params,
			andOperator);
	}

	public int countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			long reportedByUserId, String name, int[] accountEntryTiers,
			Boolean satisfiedDueDate, Date createDateGT, Date createDateLT,
			String subject, String description, String body, int[] statuses,
			int[] severities, int[] weights, int[] escalationLevels,
			long[] envOS, long[] envDB, long[] envJVM, long[] envAS,
			long[] envLFR, int[] components, int[] resolutions,
			Date closedDateGT, Date closedDateLT, Date dueDateGT,
			Date dueDateLT, Date customerModifiedDateGT,
			Date customerModifiedDateLT, Date workerModifiedDateGT,
			Date workerModifiedDateLT, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] subjects = CustomSQLUtil.keywords(subject);
		String[] descriptions = CustomSQLUtil.keywords(description);
		String[] bodies = CustomSQLUtil.keywords(body);

		return countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			reportedByUserId, names, accountEntryTiers, satisfiedDueDate,
			createDateGT, createDateLT, subjects, descriptions, bodies,
			statuses, severities, weights, escalationLevels, envOS, envDB,
			envJVM, envAS, envLFR, components, resolutions, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, customerModifiedDateGT,
			customerModifiedDateLT, workerModifiedDateGT, workerModifiedDateLT,
			params, andOperator);
	}

	public List<TicketEntry> findByKeywords(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] names = null;
		String[] subjects = null;
		String[] descriptions = null;
		String[] bodies = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			subjects = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
			bodies = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			0, names, new int[0], null, null, null, subjects, descriptions,
			bodies, new int[0], new int[0], new int[0], new int[0], new long[0],
			new long[0], new long[0], new long[0], new long[0], new int[0],
			new int[0], null, null, null, null, null, null, null, null, params,
			andOperator, start, end, obc);
	}

	public List<TicketEntry> findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			long reportedByUserId, String name, int[] accountEntryTiers,
			Boolean satisfiedDueDate, Date createDateGT, Date createDateLT,
			String subject, String description, String body, int[] statuses,
			int[] severities, int[] weights, int[] escalationLevels,
			long[] envOS, long[] envDB, long[] envJVM, long[] envAS,
			long[] envLFR, int[] components, int[] resolutions,
			Date closedDateGT, Date closedDateLT, Date dueDateGT,
			Date dueDateLT, Date customerModifiedDateGT,
			Date customerModifiedDateLT, Date workerModifiedDateGT,
			Date workerModifiedDateLT, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] subjects = CustomSQLUtil.keywords(subject);
		String[] descriptions = CustomSQLUtil.keywords(description);
		String[] bodies = CustomSQLUtil.keywords(body);

		return findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			reportedByUserId, names, accountEntryTiers, satisfiedDueDate,
			createDateGT, createDateLT, subjects, descriptions, bodies,
			statuses, severities, weights, escalationLevels, envOS, envDB,
			envJVM, envAS, envLFR, components, resolutions, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, customerModifiedDateGT,
			customerModifiedDateLT, workerModifiedDateGT, workerModifiedDateLT,
			params, andOperator, start, end, obc);
	}

	protected int countByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			long reportedByUserId, String[] names, int[] accountEntryTiers,
			Boolean satisfiedDueDate, Date createDateGT, Date createDateLT,
			String[] subjects, String[] descriptions, String[] bodies,
			int[] statuses, int[] severities, int[] weights,
			int[] escalationLevels, long[] envOS, long[] envDB, long[] envJVM,
			long[] envAS, long[] envLFR, int[] components, int[] resolutions,
			Date closedDateGT, Date closedDateLT, Date dueDateGT,
			Date dueDateLT, Date customerModifiedDateGT,
			Date customerModifiedDateLT, Date workerModifiedDateGT,
			Date workerModifiedDateLT, LinkedHashMap<String, Object> params,
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
			params1.put("accountCustomerUserIds", new long[] {userId});
			params2.put("partnerWorkerUserIds", new long[] {userId});
		}

		names = CustomSQLUtil.keywords(names);

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		subjects = CustomSQLUtil.keywords(subjects);
		descriptions = CustomSQLUtil.keywords(descriptions);
		bodies = CustomSQLUtil.keywords(bodies);

		Timestamp closedDateGT_TS = CalendarUtil.getTimestamp(closedDateGT);
		Timestamp closedDateLT_TS = CalendarUtil.getTimestamp(closedDateLT);

		Timestamp dueDateGT_TS = CalendarUtil.getTimestamp(dueDateGT);
		Timestamp dueDateLT_TS = CalendarUtil.getTimestamp(dueDateLT);

		Timestamp customerModifiedDateGT_TS = CalendarUtil.getTimestamp(
			customerModifiedDateGT);
		Timestamp customerModifiedDateLT_TS = CalendarUtil.getTimestamp(
			customerModifiedDateLT);

		Timestamp workerModifiedDateGT_TS = CalendarUtil.getTimestamp(
			workerModifiedDateGT);
		Timestamp workerModifiedDateLT_TS = CalendarUtil.getTimestamp(
			workerModifiedDateLT);

		String sql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		StringBundler sb = new StringBundler();

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(COUNT_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W),
				names, accountEntryTiers, satisfiedDueDate, subjects,
				descriptions, bodies, statuses, severities, weights,
				escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
				components, resolutions, params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(
						COUNT_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W),
					names, accountEntryTiers, satisfiedDueDate, subjects,
					descriptions, bodies, statuses, severities, weights,
					escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
					components, resolutions, params2, andOperator));
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
				qPos, params1, reportedByUserId, names, accountEntryTiers,
				createDateGT_TS, createDateLT_TS, subjects, descriptions,
				bodies, statuses, severities, weights, escalationLevels, envOS,
				envDB, envJVM, envAS, envLFR, components, resolutions,
				closedDateGT_TS, closedDateLT_TS, dueDateGT_TS, dueDateLT_TS,
				customerModifiedDateGT_TS, customerModifiedDateLT_TS,
				workerModifiedDateGT_TS, workerModifiedDateLT_TS);

			if (userId != null) {
				setJoin(
					qPos, params2, reportedByUserId, names, accountEntryTiers,
					createDateGT_TS, createDateLT_TS, subjects, descriptions,
					bodies, statuses, severities, weights, escalationLevels,
					envOS, envDB, envJVM, envAS, envLFR, components,
					resolutions, closedDateGT_TS, closedDateLT_TS, dueDateGT_TS,
					dueDateLT_TS, customerModifiedDateGT_TS,
					customerModifiedDateLT_TS, workerModifiedDateGT_TS,
					workerModifiedDateLT_TS);
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

	protected List<TicketEntry> findByU_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W(
			long reportedByUserId, String[] names, int[] accountEntryTiers,
			Boolean satisfiedDueDate, Date createDateGT, Date createDateLT,
			String[] subjects, String[] descriptions, String[] bodies,
			int[] statuses, int[] severities, int[] weights,
			int[] escalationLevels, long[] envOS, long[] envDB, long[] envJVM,
			long[] envAS, long[] envLFR, int[] components, int[] resolutions,
			Date closedDateGT, Date closedDateLT, Date dueDateGT,
			Date dueDateLT, Date customerModifiedDateGT,
			Date customerModifiedDateLT, Date workerModifiedDateGT,
			Date workerModifiedDateLT, LinkedHashMap<String, Object> params,
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
			params1.put("accountCustomerUserIds", new long[] {userId});
			params2.put("partnerWorkerUserIds", new long[] {userId});
		}

		names = CustomSQLUtil.keywords(names);

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		subjects = CustomSQLUtil.keywords(subjects);
		descriptions = CustomSQLUtil.keywords(descriptions);
		bodies = CustomSQLUtil.keywords(bodies);

		Timestamp closedDateGT_TS = CalendarUtil.getTimestamp(closedDateGT);
		Timestamp closedDateLT_TS = CalendarUtil.getTimestamp(closedDateLT);

		Timestamp dueDateGT_TS = CalendarUtil.getTimestamp(dueDateGT);
		Timestamp dueDateLT_TS = CalendarUtil.getTimestamp(dueDateLT);

		Timestamp customerModifiedDateGT_TS = CalendarUtil.getTimestamp(
			customerModifiedDateGT);
		Timestamp customerModifiedDateLT_TS = CalendarUtil.getTimestamp(
			customerModifiedDateLT);

		Timestamp workerModifiedDateGT_TS = CalendarUtil.getTimestamp(
			workerModifiedDateGT);
		Timestamp workerModifiedDateLT_TS = CalendarUtil.getTimestamp(
			workerModifiedDateLT);

		StringBuilder sb = new StringBuilder();

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(FIND_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W),
				names, accountEntryTiers, satisfiedDueDate, subjects,
				descriptions, bodies, statuses, severities, weights,
				escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
				components, resolutions, params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(
						FIND_BY_U_A_S_C_S_D_T_S_S_W_E_E_C_R_C_D_C_W),
					names, accountEntryTiers, satisfiedDueDate, subjects,
					descriptions, bodies, statuses, severities, weights,
					escalationLevels, envOS, envDB, envJVM, envAS, envLFR,
					components, resolutions, params2, andOperator));
			sb.append(")");
		}

		String sql = sb.toString();

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("ticketEntryId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params1, reportedByUserId, names, accountEntryTiers,
				createDateGT_TS, createDateLT_TS, subjects, descriptions,
				bodies, statuses, severities, weights, escalationLevels, envOS,
				envDB, envJVM, envAS, envLFR, components, resolutions,
				closedDateGT_TS, closedDateLT_TS, dueDateGT_TS, dueDateLT_TS,
				customerModifiedDateGT_TS, customerModifiedDateLT_TS,
				workerModifiedDateGT_TS, workerModifiedDateLT_TS);

			if (userId != null) {
				setJoin(
					qPos, params2, reportedByUserId, names, accountEntryTiers,
					createDateGT_TS, createDateLT_TS, subjects, descriptions,
					bodies, statuses, severities, weights, escalationLevels,
					envOS, envDB, envJVM, envAS, envLFR, components,
					resolutions, closedDateGT_TS, closedDateLT_TS, dueDateGT_TS,
					dueDateLT_TS, customerModifiedDateGT_TS,
					customerModifiedDateLT_TS, workerModifiedDateGT_TS,
					workerModifiedDateLT_TS);
			}

			List<Long> ticketEntryIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<TicketEntry> ticketEntries = new ArrayList<TicketEntry>(
				ticketEntryIds.size());

			for (Long ticketEntryId : ticketEntryIds) {
				TicketEntry ticketEntry = TicketEntryUtil.findByPrimaryKey(
					ticketEntryId);

				ticketEntries.add(ticketEntry);
			}

			return ticketEntries;
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

		if (key.equals("accountCustomerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_CUSTOMER);
		}
		else if (key.equals("accountWorkers")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_WORKER);
		}
		else if (key.equals("partnerEntryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_ENTRY);
		}
		else if (key.equals("partnerWorkerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("pendingTypes")) {
			join = CustomSQLUtil.get(JOIN_BY_PENDING_TYPES);
		}
		else if (key.equals("primaryTicketWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_PRIMARY_TICKET_WORKER);
		}
		else if (key.equals("productEntryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PRODUCT_ENTRY);
		}
		else if (key.equals("subscription")) {
			join = CustomSQLUtil.get(JOIN_BY_SUBSCRIPTION);
		}
		else if (key.equals("supportTeamIds")) {
			join = CustomSQLUtil.get(JOIN_BY_SUPPORT_TEAM);
		}
		else if (key.equals("ticketWorkerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_TICKET_WORKER);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getStatusSQL(
		int[] statuses, LinkedHashMap<String, Object> params) {

		if (ArrayUtil.isEmpty(statuses)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler((statuses.length * 2) + 3);

		sb.append("(");

		for (int i = 0; i < statuses.length; i++) {
			if (i > 0) {
				sb.append(" OR ");
			}

			sb.append(_STATUS_SQL);
		}

		if (params.containsKey("pendingCustomer")) {
			sb.append(" OR ");
			sb.append(_PENDING_CUSTOMER_SQL);
		}

		sb.append(") [$AND_OR_CONNECTOR$]");

		return sb.toString();
	}

	protected String getTicketComment(
		String[] bodies, LinkedHashMap<String, Object> params) {

		if (ArrayUtil.isEmpty(bodies) || (bodies[0] == null)) {
			return StringPool.BLANK;
		}

		String sql = "OSB_TicketEntry.ticketEntryId IN (".concat(
			CustomSQLUtil.get(FIND_BY_TICKET_COMMENT)).concat(") OR ");

		String commentVisibility = StringPool.BLANK;

		if (params.containsKey("accountCustomerUserIds")) {
			Object value = params.get("accountCustomerUserIds");

			if (Validator.isNotNull(value)) {
				commentVisibility = getWhere(
					"accountCustomerVisibility", value);
			}
		}
		else if (params.containsKey("partnerWorkerUserIds")) {
			Object value = params.get("partnerWorkerUserIds");

			if (Validator.isNotNull(value)) {
				commentVisibility = getWhere("partnerWorkerVisibility", value);
			}
		}

		return StringUtil.replace(
			sql, "[$COMMENT_VISIBILITY$]", commentVisibility);
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value) || key.equals("ticketWorkerCount")) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountCustomerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_CUSTOMER);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountCustomer.userId", true, (long[])value);
		}
		else if (key.equals("accountEntryIds")) {
			join = CustomSQLUtil.get(FILTER_BY_ACCOUNT_ENTRY);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.accountEntryId", true, (long[])value);
		}
		else if (key.equals("accountWorkers")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_WORKER);

			long[][] valueDoubleArray = (long[][])value;

			join = OSBCustomSQLUtil.replaceAllKeywords(
				join, valueDoubleArray.length);
		}
		else if (key.equals("accountCustomerVisibility")) {
			join = CustomSQLUtil.get(JOIN_BY_ACCOUNT_CUSTOMER_VISIBILITY);
		}
		else if (key.equals("partnerEntryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_ENTRY);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerWorker.partnerEntryId", true, (long[])value);
		}
		else if (key.equals("partnerWorkerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_WORKER);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerWorker.userId", true, (long[])value);
		}
		else if (key.equals("partnerWorkerVisibility")) {
			join = CustomSQLUtil.get(JOIN_BY_PARTNER_WORKER_VISIBILITY);
		}
		else if (key.equals("pendingTypes")) {
			join = CustomSQLUtil.get(JOIN_BY_PENDING_TYPES);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_TicketFlag.type_", true, (int[])value);
		}
		else if (key.equals("primaryTicketWorker")) {
			join = CustomSQLUtil.get(JOIN_BY_PRIMARY_TICKET_WORKER);
		}
		else if (key.equals("productEntryIds")) {
			join = CustomSQLUtil.get(JOIN_BY_PRODUCT_ENTRY);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_TicketEntry.productEntryId", true, (long[])value);
		}
		else if (key.equals("subscription")) {
			join = CustomSQLUtil.get(JOIN_BY_SUBSCRIPTION);
		}
		else if (key.equals("supportTeamIds")) {
			join = CustomSQLUtil.get(JOIN_BY_SUPPORT_TEAM);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_SupportWorker.supportTeamId", true, (long[])value);
		}
		else if (key.equals("ticketWorkerUserIds")) {
			join = CustomSQLUtil.get(JOIN_BY_TICKET_WORKER);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_TicketWorker.userId", true, (long[])value);
		}
		else if (key.equals("ticketWorkerCount")) {
			join = CustomSQLUtil.get(JOIN_BY_TICKET_WORKER_COUNT);
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
		String sql, String[] names, int[] accountEntryTiers,
		Boolean satisfiedDueDate, String[] subjects, String[] descriptions,
		String[] bodies, int[] statuses, int[] severities, int[] weights,
		int[] escalationLevels, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolutions,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.name)", StringPool.LIKE, true, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.code_)", StringPool.LIKE, true, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AccountEntry.tier", false, accountEntryTiers);

		if (satisfiedDueDate == null) {
			sql = StringUtil.replace(
				sql, _SATISFIED_DUE_DATE_SQL, StringPool.BLANK);
		}
		else {
			if (satisfiedDueDate) {
				sql = StringUtil.replace(
					sql, "[$SATISFIED_DUE_DATE_COMPARATOR$]", "<");
			}
			else {
				sql = StringUtil.replace(
					sql, "[$SATISFIED_DUE_DATE_COMPARATOR$]", ">");
			}
		}

		sql = StringUtil.replace(
			sql, "[$TICKET_COMMENT$]", getTicketComment(bodies, params));
		sql = StringUtil.replace(
			sql, "[$TICKET_STATUS$]", getStatusSQL(statuses, params));
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketEntry.subject)", StringPool.LIKE, true,
			subjects);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketEntry.description)", StringPool.LIKE, true,
			descriptions);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketComment.body)", StringPool.LIKE, true,
			bodies);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.severity", false, severities);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.weight", false, weights);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.escalationLevel", false, escalationLevels);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.envOS", false, envOS);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.envDB", false, envDB);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.envJVM", false, envJVM);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.envAS", false, envAS);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.envLFR", false, envLFR);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.component", false, components);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_TicketEntry.resolution", false, resolutions);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		long classNameId = PortalUtil.getClassNameId(TicketEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.TICKETENTRY$]",
			String.valueOf(classNameId));

		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			if (key.equals("primaryTicketWorker")) {
				Object value = entry.getValue();

				if (value instanceof Long) {
					qPos.add(value);
					qPos.add((Boolean)null);
					qPos.add((Boolean)null);
				}
				else {
					Object[] valueArray = (Object[])value;

					qPos.add(valueArray[0]);
					qPos.add(valueArray[1]);
					qPos.add(valueArray[1]);
				}
			}
			else if (key.equals("ticketWorkerCount")) {
				Long valueLong = (Long)entry.getValue();

				qPos.add(valueLong);
			}
			else {
				Object value = entry.getValue();

				if (value instanceof int[]) {
					int[] valueArray = (int[])value;

					for (int i = 0; i < valueArray.length; i++) {
						if (Validator.isNotNull(valueArray[i])) {
							qPos.add(valueArray[i]);
						}
					}
				}
				else if (value instanceof Integer[]) {
					Integer[] valueArray = (Integer[])value;

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
				else if (value instanceof long[]) {
					long[] valueArray = (long[])value;

					for (int i = 0; i < valueArray.length; i++) {
						if (Validator.isNotNull(valueArray[i])) {
							qPos.add(valueArray[i]);
						}
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
				else if (value instanceof long[][]) {
					long[][] valueDoubleArray = (long[][])value;

					for (long[] valueArray : valueDoubleArray) {
						for (long valueLong : valueArray) {
							qPos.add(valueLong);
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

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params,
		long reportedByUserId, String[] names, int[] accountEntryTiers,
		Timestamp createDateGT_TS, Timestamp createDateLT_TS, String[] subjects,
		String[] descriptions, String[] bodies, int[] statuses,
		int[] severities, int[] weights, int[] escalationLevels, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolutions, Timestamp closedDateGT_TS,
		Timestamp closedDateLT_TS, Timestamp dueDateGT_TS,
		Timestamp dueDateLT_TS, Timestamp customerModifiedDateGT_TS,
		Timestamp customerModifiedDateLT_TS, Timestamp workerModifiedDateGT_TS,
		Timestamp workerModifiedDateLT_TS) {

		setJoin(qPos, params);

		qPos.add(names, 2);
		qPos.add(names, 2);
		qPos.add(accountEntryTiers);
		qPos.add(reportedByUserId);
		qPos.add(reportedByUserId);
		qPos.add(createDateGT_TS);
		qPos.add(createDateGT_TS);
		qPos.add(createDateLT_TS);
		qPos.add(createDateLT_TS);
		setTicketComment(qPos, bodies, params);
		qPos.add(subjects, 2);
		qPos.add(descriptions, 2);
		qPos.add(statuses);
		qPos.add(severities);
		qPos.add(weights);
		qPos.add(escalationLevels);
		qPos.add(envOS);
		qPos.add(envDB);
		qPos.add(envJVM);
		qPos.add(envAS);
		qPos.add(envLFR);
		qPos.add(components);
		qPos.add(resolutions);
		qPos.add(closedDateGT_TS);
		qPos.add(closedDateGT_TS);
		qPos.add(closedDateLT_TS);
		qPos.add(closedDateLT_TS);
		qPos.add(dueDateGT_TS);
		qPos.add(dueDateGT_TS);
		qPos.add(dueDateLT_TS);
		qPos.add(dueDateLT_TS);
		qPos.add(customerModifiedDateGT_TS);
		qPos.add(customerModifiedDateGT_TS);
		qPos.add(customerModifiedDateLT_TS);
		qPos.add(customerModifiedDateLT_TS);
		qPos.add(workerModifiedDateGT_TS);
		qPos.add(workerModifiedDateGT_TS);
		qPos.add(workerModifiedDateLT_TS);
		qPos.add(workerModifiedDateLT_TS);
	}

	protected void setTicketComment(
		QueryPos qPos, String[] bodies, LinkedHashMap<String, Object> params) {

		if (ArrayUtil.isEmpty(bodies) || (bodies[0] == null)) {
			return;
		}

		if (params.containsKey("partnerWorkerUserIds")) {
			Object value = params.get("partnerWorkerUserIds");

			if (Validator.isNotNull(value)) {
				long[] valueArray = (long[])value;

				for (int i = 0; i < valueArray.length; i++) {
					qPos.add(valueArray[i]);
				}
			}
		}

		qPos.add(bodies, 2);
	}

	private static final String _PENDING_CUSTOMER_SQL =
		"((OSB_TicketEntry.status = 33000) AND " +
			"(OSB_TicketEntry.resolution = 32003))";

	private static final String _SATISFIED_DUE_DATE_SQL =
		"(OSB_TicketEntry.closedDate [$SATISFIED_DUE_DATE_COMPARATOR$] " +
			"OSB_TicketEntry.dueDate) [$AND_OR_CONNECTOR$]";

	private static final String _STATUS_SQL = "(OSB_TicketEntry.status = ?)";

}