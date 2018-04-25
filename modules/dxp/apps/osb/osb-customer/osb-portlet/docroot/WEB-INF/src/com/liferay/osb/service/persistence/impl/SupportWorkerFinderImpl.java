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

import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.impl.SupportWorkerImpl;
import com.liferay.osb.service.persistence.SupportWorkerFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Brent Krone-Schmidt
 */
public class SupportWorkerFinderImpl
	extends SupportWorkerFinderBaseImpl implements SupportWorkerFinder {

	public static final String COUNT_BY_SL_FN_MN_LN_SN_EA_STN =
		SupportWorkerFinder.class.getName() + ".countBySL_FN_MN_LN_SN_EA_STN";

	public static final String FIND_BY_SUPPORT_TEAM_ID =
		SupportWorkerFinder.class.getName() + ".findBySupportTeamId";

	public static final String FIND_BY_R_STT_SRI =
		SupportWorkerFinder.class.getName() + ".findByR_STT_SRI";

	public static final String FIND_BY_SL_FN_MN_LN_SN_EA_STN =
		SupportWorkerFinder.class.getName() + ".findBySL_FN_MN_LN_SN_EA_STN";

	public static final String JOIN_BY_ACCOUNT_TIER =
		SupportWorkerFinder.class.getName() + ".joinByAccountTier";

	public static final String JOIN_BY_LOCATION_SUPPORT_REGION =
		SupportWorkerFinder.class.getName() + ".joinByLocationSupportRegion";

	public static final String JOIN_BY_SUPPORT_REGION =
		SupportWorkerFinder.class.getName() + ".joinBySupportRegion";

	public static final String JOIN_BY_SUPPORT_TEAM =
		SupportWorkerFinder.class.getName() + ".joinBySupportTeam";

	public static final String JOIN_BY_SUPPORT_TEAM_ACCOUNT_ENTRY =
		SupportWorkerFinder.class.getName() + ".joinBySupportTeamAccountEntry";

	public static final String JOIN_BY_SUPPORT_TEAM_LANGUAGE =
		SupportWorkerFinder.class.getName() + ".joinBySupportTeamLanguage";

	public static final String JOIN_BY_SUPPORT_TEAM_TYPE =
		SupportWorkerFinder.class.getName() + ".joinBySupportTeamType";

	public static final String JOIN_BY_USER_ID =
		SupportWorkerFinder.class.getName() + ".joinByUserId";

	public int countByKeywords(long supportLaborId, String keywords) {
		String[] emailAddresses = null;
		String[] firstNames = null;
		String[] middleNames = null;
		String[] lastNames = null;
		String[] screenNames = null;
		String[] supportTeamNames = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			emailAddresses = CustomSQLUtil.keywords(keywords);
			firstNames = CustomSQLUtil.keywords(keywords);
			middleNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			screenNames = CustomSQLUtil.keywords(keywords);
			supportTeamNames = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, firstNames, middleNames, lastNames, screenNames,
			emailAddresses, supportTeamNames, andOperator);
	}

	public int countBySL_FN_MN_LN_SN_EA_STN(
		long supportLaborId, String[] firstNames, String[] middleNames,
		String[] lastNames, String[] screenNames, String[] emailAddresses,
		String[] supportTeamNames, boolean andOperator) {

		emailAddresses = CustomSQLUtil.keywords(emailAddresses);
		firstNames = CustomSQLUtil.keywords(firstNames);
		middleNames = CustomSQLUtil.keywords(middleNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		screenNames = CustomSQLUtil.keywords(screenNames);
		supportTeamNames = CustomSQLUtil.keywords(supportTeamNames);

		String sql = CustomSQLUtil.get(
			getClass(), COUNT_BY_SL_FN_MN_LN_SN_EA_STN);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.emailAddress)", StringPool.LIKE, false,
			emailAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.firstName)", StringPool.LIKE, false, firstNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.middleName)", StringPool.LIKE, false,
			middleNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.lastName)", StringPool.LIKE, false, lastNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.screenName)", StringPool.LIKE, false,
			screenNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_SupportTeam.name)", StringPool.LIKE, true,
			supportTeamNames);

		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(supportLaborId);
			qPos.add(supportLaborId);
			qPos.add(emailAddresses, 2);
			qPos.add(firstNames, 2);
			qPos.add(middleNames, 2);
			qPos.add(lastNames, 2);
			qPos.add(screenNames, 2);
			qPos.add(supportTeamNames, 2);

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

	public List<SupportWorker> findByKeywords(
		long supportLaborId, String keywords, int start, int end,
		OrderByComparator obc) {

		String[] emailAddresses = null;
		String[] firstNames = null;
		String[] middleNames = null;
		String[] lastNames = null;
		String[] screenNames = null;
		String[] supportTeamNames = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			emailAddresses = CustomSQLUtil.keywords(keywords);
			firstNames = CustomSQLUtil.keywords(keywords);
			middleNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			screenNames = CustomSQLUtil.keywords(keywords);
			supportTeamNames = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findBySL_FN_MN_LN_SN_EA_STN(
			supportLaborId, firstNames, middleNames, lastNames, screenNames,
			emailAddresses, supportTeamNames, andOperator, start, end, obc);
	}

	public List<SupportWorker> findBySupportTeamId(long supportTeamId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_SUPPORT_TEAM_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_SupportWorker", SupportWorkerImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(supportTeamId);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SupportWorker> findByR_STT_SRI(
		int role, Integer supportTeamType, long supportRegionId,
		String roleComparator, LinkedHashMap<String, Object> params) {

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_R_STT_SRI);

		if (roleComparator.equals(StringPool.EQUAL)) {
			sql = StringUtil.replace(
				sql, "[$ROLE_COMPARATOR$]", StringPool.EQUAL);
		}
		else {
			sql = StringUtil.replace(
				sql, "[$ROLE_COMPARATOR$]", StringPool.NOT_EQUAL);
		}

		if (supportTeamType == null) {
			sql = StringUtil.replace(
				sql, "(OSB_SupportTeam.type_ = ?) AND", StringPool.BLANK);
		}

		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_SupportWorker", SupportWorkerImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(role);

			if (supportTeamType != null) {
				qPos.add(supportTeamType);
			}

			qPos.add(supportRegionId);
			qPos.add(supportRegionId);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SupportWorker> findBySL_FN_MN_LN_SN_EA_STN(
		long supportLaborId, String[] firstNames, String[] middleNames,
		String[] lastNames, String[] screenNames, String[] emailAddresses,
		String[] supportTeamNames, boolean andOperator, int start, int end,
		OrderByComparator obc) {

		emailAddresses = CustomSQLUtil.keywords(emailAddresses);
		firstNames = CustomSQLUtil.keywords(firstNames);
		middleNames = CustomSQLUtil.keywords(middleNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		screenNames = CustomSQLUtil.keywords(screenNames);
		supportTeamNames = CustomSQLUtil.keywords(supportTeamNames);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_SL_FN_MN_LN_SN_EA_STN);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.emailAddress)", StringPool.LIKE, false,
				emailAddresses);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.firstName)", StringPool.LIKE, false,
				firstNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.middleName)", StringPool.LIKE, false,
				middleNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.lastName)", StringPool.LIKE, false,
				lastNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(User_.screenName)", StringPool.LIKE, false,
				screenNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(OSB_SupportTeam.name)", StringPool.LIKE, true,
				supportTeamNames);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_SupportWorker", SupportWorkerImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(supportLaborId);
			qPos.add(supportLaborId);
			qPos.add(emailAddresses, 2);
			qPos.add(firstNames, 2);
			qPos.add(middleNames, 2);
			qPos.add(lastNames, 2);
			qPos.add(screenNames, 2);
			qPos.add(supportTeamNames, 2);

			return (List<SupportWorker>)QueryUtil.list(
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

		if (key.equals("accountTier")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_TIER);
		}
		else if (key.equals("supportRegion")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);
		}
		else if (key.equals("supportTeamAccountEntry")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_SUPPORT_TEAM_ACCOUNT_ENTRY);
		}
		else if (key.equals("supportTeamLanguage")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_TEAM_LANGUAGE);
		}
		else if (key.equals("supportTeamType")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_TEAM_TYPE);
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

		if (key.equals("accountTier")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_TIER);
		}
		else if (key.equals("locationSupportRegion")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_LOCATION_SUPPORT_REGION);
		}
		else if (key.equals("supportRegion")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);
		}
		else if (key.equals("supportTeam")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_TEAM);
		}
		else if (key.equals("supportTeamAccountEntry")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_SUPPORT_TEAM_ACCOUNT_ENTRY);
		}
		else if (key.equals("supportTeamLanguage")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_TEAM_LANGUAGE);
		}
		else if (key.equals("supportTeamType")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_TEAM_TYPE);
		}
		else if (key.equals("userId")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_USER_ID);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5).concat(" AND ");
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
			Object value = entry.getValue();

			if (value instanceof Integer) {
				Integer valueInteger = (Integer)value;

				if (Validator.isNotNull(valueInteger)) {
					qPos.add(valueInteger);
				}
			}
			else if (value instanceof Integer[]) {
				Integer[] valueArray = (Integer[])value;

				for (int i = 0; i < valueArray.length; i++) {
					qPos.add(valueArray[i]);
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
			else if (value instanceof String[]) {
				String[] valueArray = (String[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (Validator.isNotNull(valueArray[i])) {
						qPos.add(valueArray[i]);
					}
				}
			}
		}
	}

}