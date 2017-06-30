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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.impl.TrainingExamResultImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultFinderImpl
	extends BasePersistenceImpl<AccountEntry>
		implements TrainingExamResultFinder {

	public static final String COUNT_BY_U_EI_SD =
		TrainingExamResultFinder.class.getName() + ".countByU_EI_SD";

	public static final String COUNT_BY_FN_LN_EA_EI =
		TrainingExamResultFinder.class.getName() + ".countByFN_LN_EA_EI";

	public static final String FIND_BY_U_EI_SD =
		TrainingExamResultFinder.class.getName() + ".findByU_EI_SD";

	public static final String FIND_BY_FN_LN_EA_EI =
		TrainingExamResultFinder.class.getName() + ".findByFN_LN_EA_EI";

	public int countByKeywords(String keywords) throws SystemException {
		String[] firstNames = null;
		String[] lastNames = null;
		String[] emailAddresses = null;
		String[] candidateKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			firstNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
			candidateKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByFN_LN_EA_EI(
			firstNames, lastNames, emailAddresses, candidateKeys, andOperator);
	}

	public int countByKeywords(long userId, String keywords)
		throws SystemException {

		String[] testKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			testKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_EI_SD(userId, testKeys, null, null, andOperator);
	}

	public int countByU_EI_SD(
			long userId, String testKey, Date startDateGT, Date startDateLT,
			boolean andOperator)
		throws SystemException {

		String[] testKeys = CustomSQLUtil.keywords(testKey);

		return countByU_EI_SD(
			userId, testKeys, startDateGT, startDateLT, andOperator);
	}

	public int countByFN_LN_EA_EI(
			String firstName, String lastName, String emailAddress,
			String candidateKey, boolean andOperator)
		throws SystemException {

		String[] firstNames = CustomSQLUtil.keywords(firstName);
		String[] lastNames = CustomSQLUtil.keywords(lastName);
		String[] emailAddresses = CustomSQLUtil.keywords(emailAddress);
		String[] candidateKeys = CustomSQLUtil.keywords(candidateKey);

		return countByFN_LN_EA_EI(
			firstNames, lastNames, emailAddresses, candidateKeys, andOperator);
	}

	public List<TrainingExamResult> findByKeywords(
			String keywords, boolean groupByUserId, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String[] firstNames = null;
		String[] lastNames = null;
		String[] emailAddresses = null;
		String[] candidateKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			firstNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);
			candidateKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByFN_LN_EA_EI(
			firstNames, lastNames, emailAddresses, candidateKeys, groupByUserId,
			andOperator, start, end, obc);
	}

	public List<TrainingExamResult> findByKeywords(
			long userId, String keywords, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String[] testKeys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			testKeys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_EI_SD(
			userId, testKeys, null, null, andOperator, start, end, obc);
	}

	public List<TrainingExamResult> findByU_EI_SD(
			long userId, String testKey, Date startDateGT, Date startDateLT,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] testKeys = CustomSQLUtil.keywords(testKey);

		return findByU_EI_SD(
			userId, testKeys, startDateGT, startDateLT, andOperator, start, end,
			obc);
	}

	public List<TrainingExamResult> findByFN_LN_EA_EI(
			String firstName, String lastName, String emailAddress,
			String candidateKey, boolean groupByUserId, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] firstNames = CustomSQLUtil.keywords(firstName);
		String[] lastNames = CustomSQLUtil.keywords(lastName);
		String[] emailAddresses = CustomSQLUtil.keywords(emailAddress);
		String[] candidateKeys = CustomSQLUtil.keywords(candidateKey);

		return findByFN_LN_EA_EI(
			firstNames, lastNames, emailAddresses, candidateKeys, groupByUserId,
			andOperator, start, end, obc);
	}

	protected int countByU_EI_SD(
			long userId, String[] testKeys, Date startDateGT, Date startDateLT,
			boolean andOperator)
		throws SystemException {

		testKeys = CustomSQLUtil.keywords(testKeys);

		String sql = replaceSQL(
			CustomSQLUtil.get(COUNT_BY_U_EI_SD), userId, testKeys, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
			Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

			setJoin(qPos, userId, testKeys, startDateGT_TS, startDateLT_TS);

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

	protected int countByFN_LN_EA_EI(
			String[] firstNames, String[] lastNames, String[] emailAddresses,
			String[] candidateKeys, boolean andOperator)
		throws SystemException {

		firstNames = CustomSQLUtil.keywords(firstNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses);
		candidateKeys = CustomSQLUtil.keywords(candidateKeys);

		String sql = replaceSQL(
			CustomSQLUtil.get(COUNT_BY_FN_LN_EA_EI), firstNames, lastNames,
			emailAddresses, candidateKeys, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, firstNames, lastNames, emailAddresses, candidateKeys);

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

	protected List<TrainingExamResult> findByU_EI_SD(
			long userId, String[] testKeys, Date startDateGT, Date startDateLT,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		testKeys = CustomSQLUtil.keywords(testKeys);

		String sql = replaceSQL(
			CustomSQLUtil.get(FIND_BY_U_EI_SD), userId, testKeys, andOperator);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TrainingExamResult", TrainingExamResultImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
			Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

			setJoin(qPos, userId, testKeys, startDateGT_TS, startDateLT_TS);

			return (List<TrainingExamResult>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<TrainingExamResult> findByFN_LN_EA_EI(
			String[] firstNames, String[] lastNames, String[] emailAddresses,
			String[] candidateKeys, boolean groupByUserId, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		firstNames = CustomSQLUtil.keywords(firstNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses);
		candidateKeys = CustomSQLUtil.keywords(candidateKeys);

		String sql = replaceSQL(
			CustomSQLUtil.get(FIND_BY_FN_LN_EA_EI), firstNames, lastNames,
			emailAddresses, candidateKeys, andOperator);

		if (!groupByUserId) {
			sql = CustomSQLUtil.removeGroupBy(sql);
		}

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("trainingExamResultId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, firstNames, lastNames, emailAddresses, candidateKeys);

			List<Long> trainingExamResultIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<TrainingExamResult> trainingExamResults =
				new ArrayList<TrainingExamResult>(trainingExamResultIds.size());

			for (Long trainingExamResultId : trainingExamResultIds) {
				TrainingExamResult trainingExamResult =
					TrainingExamResultUtil.findByPrimaryKey(
						trainingExamResultId);

				trainingExamResults.add(trainingExamResult);
			}

			return trainingExamResults;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String replaceSQL(
		String sql, long userId, String[] testKeys, boolean andOperator) {

		if (userId <= 0) {
			sql = StringUtil.replace(sql, _USER_ID_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_ExternalIdMapper2.externalId)", StringPool.LIKE,
			false, testKeys);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		long trainingExamClassNameId = PortalUtil.getClassNameId(
			TrainingExam.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.TRAININGEXAM$]",
			String.valueOf(trainingExamClassNameId));

		long trainingExamResultClassNameId = PortalUtil.getClassNameId(
			TrainingExamResult.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.TRAININGEXAMRESULT$]",
			String.valueOf(trainingExamResultClassNameId));

		return sql;
	}

	protected String replaceSQL(
		String sql, String[] firstNames, String[] lastNames,
		String[] emailAddresses, String[] candidateKeys, boolean andOperator) {

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_UserProfileHistory.firstName)", StringPool.LIKE,
			false, firstNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_UserProfileHistory.lastName)", StringPool.LIKE,
			false, lastNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_UserProfileHistory.emailAddresses)",
			StringPool.LIKE, false, emailAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_ExternalIdMapper.externalId)", StringPool.LIKE,
			false, candidateKeys);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		long trainingExamResultClassNameId = PortalUtil.getClassNameId(
			TrainingExamResult.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.TRAININGEXAMRESULT$]",
			String.valueOf(trainingExamResultClassNameId));

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, long userId, String[] testKeys, Timestamp startDateGT_TS,
		Timestamp startDateLT_TS) {

		if (userId > 0) {
			qPos.add(userId);
		}

		qPos.add(testKeys, 2);
		qPos.add(startDateGT_TS);
		qPos.add(startDateGT_TS);
		qPos.add(startDateLT_TS);
		qPos.add(startDateLT_TS);
	}

	protected void setJoin(
		QueryPos qPos, String[] firstNames, String[] lastNames,
		String[] emailAddresses, String[] candidateKeys) {

		qPos.add(firstNames, 2);
		qPos.add(lastNames, 2);
		qPos.add(emailAddresses, 2);
		qPos.add(candidateKeys, 2);
	}

	private static final String _USER_ID_SQL =
		"(OSB_TrainingCustomer.userId = ?) AND";

}