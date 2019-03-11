/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.impl.OfferingEntryImpl;
import com.liferay.osb.service.persistence.OfferingEntryFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class OfferingEntryFinderImpl
	extends OfferingEntryFinderBaseImpl implements OfferingEntryFinder {

	public static final String COUNT_BY_U_AEI_PEI_T_S_SED =
		OfferingEntryFinder.class.getName() + ".countByU_AEI_PEI_T_S_SED";

	public static final String FILTER_BY_LICENSE =
		OfferingEntryFinder.class.getName() + ".filterByLicense";

	public static final String FIND_BY_U_AEI_PEI_T_S_SED =
		OfferingEntryFinder.class.getName() + ".findByU_AEI_PEI_T_S_SED";

	public static final String JOIN_BY_ACCOUNT_ENTRY =
		OfferingEntryFinder.class.getName() + ".joinByAccountEntry";

	public static final String JOIN_BY_LICENSE_LIFETIME =
		OfferingEntryFinder.class.getName() + ".joinByLicenseLifetime";

	public static final String JOIN_BY_PRODUCT_ENTRY =
		OfferingEntryFinder.class.getName() + ".joinByProductEntry";

	public static final String JOIN_BY_USER =
		OfferingEntryFinder.class.getName() + ".joinByUser";

	public static final String JOIN_BY_VALID_LICENSE =
		OfferingEntryFinder.class.getName() + ".joinByValidLicense";

	public static final String JOIN_BY_VALID_TICKET =
		OfferingEntryFinder.class.getName() + ".joinByValidTicket";

	public int countByU_AEI_PEI_T_S_SED(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, Date supportEndDateGT, Date supportEndDateLT,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		Timestamp supportEndDateGT_TS = CalendarUtil.getTimestamp(
			supportEndDateGT);
		Timestamp supportEndDateLT_TS = CalendarUtil.getTimestamp(
			supportEndDateLT);

		String sql = CustomSQLUtil.get(getClass(), COUNT_BY_U_AEI_PEI_T_S_SED);

		if (userId <= 0) {
			sql = StringUtil.replace(sql, _USER_SQL, StringPool.BLANK);
		}

		if (accountEntryId <= 0) {
			sql = StringUtil.replace(sql, _ACCOUNT_ENTRY_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.productEntryId", false, productEntryIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.type_", false, types);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.status", false, statuses);
		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			if (userId > 0) {
				qPos.add(userId);
			}

			if (accountEntryId > 0) {
				qPos.add(accountEntryId);
			}

			qPos.add(productEntryIds);
			qPos.add(types);
			qPos.add(statuses);
			qPos.add(supportEndDateGT_TS);
			qPos.add(supportEndDateGT_TS);
			qPos.add(supportEndDateLT_TS);
			qPos.add(supportEndDateLT_TS);

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

	public List<OfferingEntry> findByU_AEI_PEI_T_S_SED(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, Date supportEndDateGT, Date supportEndDateLT,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc) {

		Timestamp supportEndDateGT_TS = CalendarUtil.getTimestamp(
			supportEndDateGT);
		Timestamp supportEndDateLT_TS = CalendarUtil.getTimestamp(
			supportEndDateLT);

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_U_AEI_PEI_T_S_SED);

		if (userId <= 0) {
			sql = StringUtil.replace(sql, _USER_SQL, StringPool.BLANK);
		}

		if (accountEntryId <= 0) {
			sql = StringUtil.replace(sql, _ACCOUNT_ENTRY_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.productEntryId", false, productEntryIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.type_", false, types);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_OfferingEntry.status", false, statuses);
		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_OfferingEntry", OfferingEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			if (userId > 0) {
				qPos.add(userId);
			}

			if (accountEntryId > 0) {
				qPos.add(accountEntryId);
			}

			qPos.add(productEntryIds);
			qPos.add(types);
			qPos.add(statuses);
			qPos.add(supportEndDateGT_TS);
			qPos.add(supportEndDateGT_TS);
			qPos.add(supportEndDateLT_TS);
			qPos.add(supportEndDateLT_TS);

			return (List<OfferingEntry>)QueryUtil.list(
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

		if (key.equals("accountEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_ENTRY);
		}
		else if (key.equals("licenseLifetime")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_LICENSE_LIFETIME);
		}
		else if (key.equals("productEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRODUCT_ENTRY);
		}
		else if (key.equals("user")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_USER);
		}
		else if (key.equals("validLicense")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_VALID_LICENSE);
		}
		else if (key.equals("validTicket")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_VALID_TICKET);
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

			if (Validator.isNotNull(value) || key.equals("license") ||
				key.equals("validTicket")) {

				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_ENTRY);
		}
		else if (key.equals("licenseLifetime")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_LICENSE_LIFETIME);
		}
		else if (key.equals("productEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRODUCT_ENTRY);
		}
		else if (key.equals("user")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_USER);
		}
		else if (key.equals("validLicense")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_VALID_LICENSE);
		}
		else if (key.equals("validTicket")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_VALID_TICKET);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(
					pos + 5
				).concat(
					" AND "
				);
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

			if (key.equals("user")) {
				String[] valueArray = (String[])value;

				for (String valueString : valueArray) {
					valueString = CustomSQLUtil.keywords(valueString)[0];

					qPos.add(valueString);
					qPos.add(valueString);
				}
			}
			else if (value instanceof int[]) {
				int[] valueArray = (int[])value;

				for (int valueInt : valueArray) {
					qPos.add(valueInt);
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

				for (Long valueLong : valueArray) {
					qPos.add(valueLong);
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

	private static final String _ACCOUNT_ENTRY_SQL =
		"(OSB_OfferingEntry.accountEntryId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _USER_SQL =
		"(OSB_OfferingEntry.userId = ?) [$AND_OR_CONNECTOR$]";

}