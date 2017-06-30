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
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.impl.TrainingEventImpl;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Shin
 * @author Joan Kim
 * @author Douglas Wong
 * @author Yury Butrymovich
 */
public class TrainingEventFinderImpl extends BasePersistenceImpl<TrainingEvent>
	implements TrainingEventFinder {

	public static final String COUNT_BY_END_DATE =
		TrainingEventFinder.class.getName() + ".countByEndDate";

	public static final String COUNT_BY_USER_ID =
		TrainingEventFinder.class.getName() + ".countByUserId";

	public static final String COUNT_BY_TCI_ED =
		TrainingEventFinder.class.getName() + ".countByTCI_GtED";

	public static final String COUNT_BY_T_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA =
		TrainingEventFinder.class.getName() +
			".countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA";

	public static final String FILTER_BY_COUNTRY_IDS =
		TrainingEventFinder.class.getName() + ".filterByCountryIds";

	public static final String FILTER_BY_ONLINE =
		TrainingEventFinder.class.getName() + ".filterByOnline";

	public static final String FILTER_BY_PORTAL_MINOR_VERSION =
		TrainingEventFinder.class.getName() + ".filterByPortalMinorVersion";

	public static final String FILTER_BY_START_DATE =
		TrainingEventFinder.class.getName() + ".filterByStartDate";

	public static final String FILTER_BY_TRAINING_COURSE_IDS =
		TrainingEventFinder.class.getName() + ".filterByTrainingCourseIds";

	public static final String FILTER_BY_TYPE =
			TrainingEventFinder.class.getName() + ".filterByType";

	public static final String FIND_BY_END_DATE =
		TrainingEventFinder.class.getName() + ".findByEndDate";

	public static final String FIND_BY_PARAMS =
			TrainingEventFinder.class.getName() + ".findByParams";

	public static final String FIND_BY_USER_ID =
		TrainingEventFinder.class.getName() + ".findByUserId";

	public static final String FIND_BY_TCI_ED =
		TrainingEventFinder.class.getName() + ".findByTCI_GtED";

	public static final String FIND_BY_T_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA =
		TrainingEventFinder.class.getName() +
			".findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA";

	public static final String JOIN_BY_DDL_RECORD_SET =
		TrainingEventFinder.class.getName() + ".joinByDDLRecordSet";

	public int countByKeywords(
			String keywords, Long regionId, Long countryId, Date startDateGT,
			Date startDateLT, LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] names = null;
		String[] courses = null;
		String[] cities = null;
		String[] partners = null;
		String[] trainerFirstNames = null;
		String[] trainerLastNames = null;
		String[] trainerEmailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			courses = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			partners = CustomSQLUtil.keywords(keywords);
			trainerFirstNames = CustomSQLUtil.keywords(keywords);
			trainerLastNames = CustomSQLUtil.keywords(keywords);
			trainerEmailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			null, names, courses, null, cities, regionId, countryId, null,
			partners, startDateGT, startDateLT, trainerFirstNames,
			trainerLastNames, trainerEmailAddresses, params, andOperator);
	}

	public int countByEndDate(Date endDateGT, Date endDateLT)
		throws SystemException {

		String sql = replaceSQL(CustomSQLUtil.get(COUNT_BY_END_DATE));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(endDateGT);
			qPos.add(endDateLT);

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

	public int countByUserId(long userId) throws SystemException {
		String sql = replaceSQL(CustomSQLUtil.get(COUNT_BY_USER_ID));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

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

	public int countByTCI_GtED(long[] trainingCourseIds, Date endDateGT)
		throws SystemException {

		String sql = CustomSQLUtil.get(COUNT_BY_TCI_ED);

		sql = StringUtil.replace(
			sql, "[$TRAINING_COURSE_IDS$]",
			StringUtil.merge(trainingCourseIds));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(endDateGT);

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

	public int countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			Integer type, String name, String course,
			Integer portalMinorVersion, String city, Long regionId,
			Long countryId, String language, String partner, Date startDateGT,
			Date startDateLT, String trainerFirstName, String trainerLastName,
			String trainerEmailAddress, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String[] courses = CustomSQLUtil.keywords(course);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] languages = CustomSQLUtil.keywords(language);
		String[] names = CustomSQLUtil.keywords(name);
		String[] partners = CustomSQLUtil.keywords(partner);
		String[] trainerFirstNames = CustomSQLUtil.keywords(trainerFirstName);
		String[] trainerLastNames = CustomSQLUtil.keywords(trainerLastName);
		String[] trainerEmailAddresses = CustomSQLUtil.keywords(
			trainerEmailAddress);

		return countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			type, names, courses, portalMinorVersion, cities, regionId,
			countryId, languages, partners, startDateGT, startDateLT,
			trainerFirstNames, trainerLastNames, trainerEmailAddresses, params,
			andOperator);
	}

	public List<TrainingEvent> findByKeywords(
			String keywords, Long regionId, Long countryId, Date startDateGT,
			Date startDateLT, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] names = null;
		String[] courses = null;
		String[] cities = null;
		String[] partners = null;
		String[] trainerFirstNames = null;
		String[] trainerLastNames = null;
		String[] trainerEmailAddresses = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			courses = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			partners = CustomSQLUtil.keywords(keywords);
			trainerFirstNames = CustomSQLUtil.keywords(keywords);
			trainerLastNames = CustomSQLUtil.keywords(keywords);
			trainerEmailAddresses = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			null, names, courses, null, cities, regionId, countryId, null,
			partners, startDateGT, startDateLT, trainerFirstNames,
			trainerLastNames, trainerEmailAddresses, params, andOperator, start,
			end, obc);
	}

	public List<TrainingEvent> findByEndDate(
			Date endDateGT, Date endDateLT, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_END_DATE);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TrainingEvent", TrainingEventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(endDateGT);
			qPos.add(endDateLT);

			return (List<TrainingEvent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TrainingEvent> findByParams(
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_PARAMS);

		String where = getWhere(params);

		where = where.trim();

		if (where.endsWith("AND")) {
			where = where.substring(0, where.lastIndexOf("AND"));
		}

		sql = StringUtil.replace(sql, "[$WHERE$]", where);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TrainingEvent", TrainingEventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			return (List<TrainingEvent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TrainingEvent> findByUserId(
			long userId, int start, int end, OrderByComparator obc)
		throws SystemException {

		String sql = replaceSQL(CustomSQLUtil.get(FIND_BY_USER_ID));

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TrainingEvent", TrainingEventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<TrainingEvent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TrainingEvent> findByTCI_GtED(
			long[] trainingCourseIds, Date endDateGT, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_TCI_ED);

		sql = StringUtil.replace(
			sql, "[$TRAINING_COURSE_IDS$]",
			StringUtil.merge(trainingCourseIds));

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_TrainingEvent", TrainingEventImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(endDateGT);

			return (List<TrainingEvent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TrainingEvent> findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			Integer type, String name, String course,
			Integer portalMinorVersion, String city, Long regionId,
			Long countryId, String language, String partner, Date startDateGT,
			Date startDateLT, String trainerFirstName, String trainerLastName,
			String trainerEmailAddress, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] courses = CustomSQLUtil.keywords(course);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] languages = CustomSQLUtil.keywords(language);
		String[] names = CustomSQLUtil.keywords(name);
		String[] partners = CustomSQLUtil.keywords(partner);
		String[] trainerFirstNames = CustomSQLUtil.keywords(trainerFirstName);
		String[] trainerLastNames = CustomSQLUtil.keywords(trainerLastName);
		String[] trainerEmailAddresses = CustomSQLUtil.keywords(
			trainerEmailAddress);

		return findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			type, names, courses, portalMinorVersion, cities, regionId,
			countryId, languages, partners, startDateGT, startDateLT,
			trainerFirstNames, trainerLastNames, trainerEmailAddresses, params,
			andOperator, start, end, obc);
	}

	protected int countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			Integer type, String[] names, String[] courses,
			Integer portalMinorVersion, String[] cities, Long regionId,
			Long countryId, String[] languages, String[] partners,
			Date startDateGT, Date startDateLT, String[] trainerFirstNames,
			String[] trainerLastNames, String[] trainerEmailAddresses,
			LinkedHashMap<String, Object> params, boolean andOperator)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		names = CustomSQLUtil.keywords(names);
		courses = CustomSQLUtil.keywords(courses);
		cities = CustomSQLUtil.keywords(cities);
		languages = CustomSQLUtil.keywords(languages);
		partners = CustomSQLUtil.keywords(partners);
		trainerFirstNames = CustomSQLUtil.keywords(trainerFirstNames);
		trainerLastNames = CustomSQLUtil.keywords(trainerLastNames);
		trainerEmailAddresses = CustomSQLUtil.keywords(trainerEmailAddresses);

		String sql = replaceSQL(
			CustomSQLUtil.get(COUNT_BY_T_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA),
			type, names, courses, portalMinorVersion, cities, regionId,
			countryId, languages, partners, trainerFirstNames, trainerLastNames,
			trainerEmailAddresses, params, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
			Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

			setJoin(
				qPos, type, names, courses, portalMinorVersion, cities,
				regionId, countryId, languages, partners, startDateGT_TS,
				startDateLT_TS, trainerFirstNames, trainerLastNames,
				trainerEmailAddresses, params);

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

	protected List<TrainingEvent> findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			Integer type, String[] names, String[] courses,
			Integer portalMinorVersion, String[] cities, Long regionId,
			Long countryId, String[] languages, String[] partners,
			Date startDateGT, Date startDateLT, String[] trainerFirstNames,
			String[] trainerLastNames, String[] trainerEmailAddresses,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		names = CustomSQLUtil.keywords(names);
		courses = CustomSQLUtil.keywords(courses);
		cities = CustomSQLUtil.keywords(cities);
		languages = CustomSQLUtil.keywords(languages);
		partners = CustomSQLUtil.keywords(partners);
		trainerFirstNames = CustomSQLUtil.keywords(trainerFirstNames);
		trainerLastNames = CustomSQLUtil.keywords(trainerLastNames);
		trainerEmailAddresses = CustomSQLUtil.keywords(trainerEmailAddresses);

		String sql = replaceSQL(
			CustomSQLUtil.get(FIND_BY_T_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA), type,
			names, courses, portalMinorVersion, cities, regionId, countryId,
			languages, partners, trainerFirstNames, trainerLastNames,
			trainerEmailAddresses, params, andOperator);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("trainingEventId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
			Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

			setJoin(
				qPos, type, names, courses, portalMinorVersion, cities,
				regionId, countryId, languages, partners, startDateGT_TS,
				startDateLT_TS, trainerFirstNames, trainerLastNames,
				trainerEmailAddresses, params);

			List<Long> trainingEventIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<TrainingEvent> trainingEvents = new ArrayList<TrainingEvent>(
				trainingEventIds.size());

			for (Long trainingEventId : trainingEventIds) {
				TrainingEvent trainingEvent =
					TrainingEventUtil.findByPrimaryKey(trainingEventId);

				trainingEvents.add(trainingEvent);
			}

			return trainingEvents;
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

		if (key.equals("ddlRecordSet")) {
			join = CustomSQLUtil.get(JOIN_BY_DDL_RECORD_SET);
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
		String where = StringPool.BLANK;

		if (key.equals("countryIds")) {
			String filter = CustomSQLUtil.get(FILTER_BY_COUNTRY_IDS);

			where = StringUtil.replace(
				filter, "[$COUNTRY_IDS$]", (String)value);
		}
		else if (key.equals("ddlRecordSet")) {
			where = CustomSQLUtil.get(JOIN_BY_DDL_RECORD_SET);
		}
		else if (key.equals("online")) {
			if ((value instanceof Boolean)) {
				String operator;

				if ((Boolean)value) {
					operator = StringPool.EQUAL;
				}
				else {
					operator = StringPool.NOT_EQUAL;
				}

				String filter = CustomSQLUtil.get(FILTER_BY_ONLINE);

				where = StringUtil.replace(filter, "[$OPERATOR$]", operator);
			}
		}
		else if (key.equals("portalMinorVersion")) {
			where = CustomSQLUtil.get(FILTER_BY_PORTAL_MINOR_VERSION);
		}
		else if (key.equals("startDate")) {
			where = CustomSQLUtil.get(FILTER_BY_START_DATE);
		}
		else if (key.equals("trainingCourseIds")) {
			String filter = CustomSQLUtil.get(FILTER_BY_TRAINING_COURSE_IDS);

			where = StringUtil.replace(
				filter, "[$TRAINING_COURSE_IDS$]",
				StringUtil.merge((long[])value));
		}
		else if (key.equals("type")) {
			where = CustomSQLUtil.get(FILTER_BY_TYPE);
		}

		if (Validator.isNotNull(where)) {
			int pos = where.indexOf("WHERE");

			if (pos != -1) {
				where = where.substring(pos + 5, where.length());
			}

			where = where.concat(" AND ");
		}

		return where;
	}

	protected String replaceSQL(String sql) {
		long classNameId = PortalUtil.getClassNameId(TrainingEvent.class);

		return StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.TRAININGEVENT$]",
			String.valueOf(classNameId));
	}

	protected String replaceSQL(
		String sql, Integer type, String[] names, String[] courses,
		Integer portalMinorVersion, String[] cities, Long regionId,
		Long countryId, String[] languages, String[] partners,
		String[] trainerFirstNames, String[] trainerLastNames,
		String[] trainerEmailAddresses, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		if (type == null) {
			sql = StringUtil.replace(sql, _TYPE_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TrainingEvent.name)", StringPool.LIKE, false,
			names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TrainingCourse.name)", StringPool.LIKE, false,
			courses);

		if (portalMinorVersion == null) {
			sql = StringUtil.replace(
				sql, _PORTAL_MINOR_VERSION_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.city)", StringPool.LIKE, false, cities);

		if (regionId == null) {
			sql = StringUtil.replace(sql, _REGION_ID_SQL, StringPool.BLANK);
		}

		if (countryId == null) {
			sql = StringUtil.replace(sql, _COUNTRY_ID_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TrainingEvent.languageId)", StringPool.LIKE, false,
			languages);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_PartnerEntry.partnerEntryId", StringPool.EQUAL, false,
			partners);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.firstName)", StringPool.LIKE, false,
			trainerFirstNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.lastName)", StringPool.LIKE, false,
			trainerLastNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.emailAddress)", StringPool.LIKE, true,
			trainerEmailAddresses);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, Integer type, String[] names, String[] courses,
		Integer portalMinorVersion, String[] cities, Long regionId,
		Long countryId, String[] languages, String[] partners,
		Timestamp startDateGT_TS, Timestamp startDateLT_TS,
		String[] trainerFirstNames, String[] trainerLastNames,
		String[] trainerEmailAddresses, LinkedHashMap<String, Object> params) {

		setJoin(qPos, params);

		if (type != null) {
			qPos.add(type);
		}

		qPos.add(names, 2);
		qPos.add(courses, 2);

		if (portalMinorVersion != null) {
			qPos.add(portalMinorVersion);
		}

		qPos.add(cities, 2);

		if (regionId != null) {
			qPos.add(regionId);
		}

		if (countryId != null) {
			qPos.add(countryId);
		}

		qPos.add(languages, 2);
		qPos.add(partners, 2);
		qPos.add(startDateGT_TS);
		qPos.add(startDateGT_TS);
		qPos.add(startDateLT_TS);
		qPos.add(startDateLT_TS);
		qPos.add(trainerFirstNames, 2);
		qPos.add(trainerLastNames, 2);
		qPos.add(trainerEmailAddresses, 2);
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			Object value = entry.getValue();

			if (Validator.isNull(value)) {
				continue;
			}

			if (key.equals("countryIds") || key.equals("online") ||
				key.equals("trainingCourseIds")) {

				continue;
			}
			else if (key.equals("ddlRecordSet")) {
				if (value instanceof Integer) {
					Integer valueInteger = (Integer)value;

					qPos.add(valueInteger);
				}
			}
			else if (value instanceof Boolean) {
				qPos.add((Boolean)value);
			}
			else if (value instanceof Date) {
				qPos.add((Date)value);
			}
			else if (value instanceof Integer) {
				qPos.add((Integer)value);
			}
			else if (value instanceof Long) {
				qPos.add((Long)value);
			}
			else {
				qPos.add(String.valueOf(value));
			}
		}
	}

	private static final String _COUNTRY_ID_SQL =
		"(Address.countryId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _PORTAL_MINOR_VERSION_SQL =
		"(OSB_TrainingEvent.portalMinorVersion = ?) [$AND_OR_CONNECTOR$]";

	private static final String _REGION_ID_SQL =
		"(Address.regionId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _TYPE_SQL =
		"(OSB_TrainingEvent.type_ = ?) [$AND_OR_CONNECTOR$]";

}