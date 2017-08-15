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

import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.SupportTeamConstants;
import com.liferay.osb.model.impl.SupportTeamImpl;
import com.liferay.osb.service.persistence.SupportTeamFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportTeamFinderImpl
	extends SupportTeamFinderBaseImpl implements SupportTeamFinder {

	public static final String COUNT_BY_N_T =
		SupportTeamFinder.class.getName() + ".countByN_T";

	public static final String FIND_BY_N_T =
		SupportTeamFinder.class.getName() + ".findByN_T";

	public static final String FIND_BY_U_R =
		SupportTeamFinder.class.getName() + ".findByU_R";

	public int countByKeywords(String keywords) {
		String name = null;
		Integer type = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			String[] names = CustomSQLUtil.keywords(keywords);

			name = StringUtil.merge(names);

			keywords = StringUtil.toLowerCase(keywords);

			if (keywords.contains("critical") ||
				keywords.contains("platinum")) {

				type = SupportTeamConstants.TYPE_PLATINUM_CRITICAL;
			}
			else if (keywords.contains("normal")) {
				type = SupportTeamConstants.TYPE_NORMAL;
			}
		}
		else {
			andOperator = true;
		}

		return countByN_T(name, type, andOperator);
	}

	public int countByN_T(String name, Integer type, boolean andOperator) {
		if (name != null) {
			name = StringUtil.replace(name, CharPool.SPACE, CharPool.PERCENT);

			if (!name.endsWith(StringPool.PERCENT)) {
				name += StringPool.PERCENT;
			}

			if (!name.startsWith(StringPool.PERCENT)) {
				name = StringPool.PERCENT + name;
			}
		}

		String[] names = CustomSQLUtil.keywords(name, true);

		String sql = CustomSQLUtil.get(getClass(), COUNT_BY_N_T);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_SupportTeam.name)", StringPool.LIKE, false, names);

		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);
			qPos.add(type);
			qPos.add(type);

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

	public List<SupportTeam> findByKeywords(
		String keywords, int start, int end, OrderByComparator obc) {

		String name = null;
		Integer type = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			String[] names = CustomSQLUtil.keywords(keywords);

			name = StringUtil.merge(names);

			keywords = StringUtil.toLowerCase(keywords);

			if (keywords.contains("critical") ||
				keywords.contains("platinum")) {

				type = SupportTeamConstants.TYPE_PLATINUM_CRITICAL;
			}
			else if (keywords.contains("normal")) {
				type = SupportTeamConstants.TYPE_NORMAL;
			}
		}
		else {
			andOperator = true;
		}

		return findByN_T(name, type, andOperator, start, end, obc);
	}

	public List<SupportTeam> findByN_T(
		String name, Integer type, boolean andOperator, int start, int end,
		OrderByComparator obc) {

		if (name != null) {
			name = StringUtil.replace(name, CharPool.SPACE, CharPool.PERCENT);

			if (!name.endsWith(StringPool.PERCENT)) {
				name += StringPool.PERCENT;
			}

			if (!name.startsWith(StringPool.PERCENT)) {
				name = StringPool.PERCENT + name;
			}
		}

		String[] names = CustomSQLUtil.keywords(name, true);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_N_T);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(OSB_SupportTeam.name)", StringPool.LIKE, false,
				names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_SupportTeam", SupportTeamImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);
			qPos.add(type);
			qPos.add(type);

			return (List<SupportTeam>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SupportTeam> findByU_R(long userId, int role) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_U_R);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_SupportTeam", SupportTeamImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(role);
			qPos.add(role);

			return (List<SupportTeam>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}