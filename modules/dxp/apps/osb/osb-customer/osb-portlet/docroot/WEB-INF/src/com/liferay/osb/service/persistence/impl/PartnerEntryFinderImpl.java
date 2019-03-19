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

import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.impl.PartnerEntryImpl;
import com.liferay.osb.service.persistence.PartnerEntryFinder;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 */
public class PartnerEntryFinderImpl
	extends PartnerEntryFinderBaseImpl implements PartnerEntryFinder {

	public static final String COUNT_BY_C_S =
		PartnerEntryFinder.class.getName() + ".countByC_S";

	public static final String FILTER_BY_STATUS =
		PartnerEntryFinder.class.getName() + ".filterByStatus";

	public static final String FIND_BY_C_S =
		PartnerEntryFinder.class.getName() + ".findByC_S";

	public static final String JOIN_BY_CHILD_PARTNER_ENTRY =
		PartnerEntryFinder.class.getName() + ".joinByChildPartnerEntry";

	public static final String JOIN_BY_PARTNER_MANAGED_SUPPORT =
		PartnerEntryFinder.class.getName() + ".joinByPartnerManagedSupport";

	public static final String JOIN_BY_PARTNER_WORKER =
		PartnerEntryFinder.class.getName() + ".joinByPartnerWorker";

	public static final String JOIN_BY_SUPPORT_REGION =
		PartnerEntryFinder.class.getName() + ".joinBySupportRegion";

	public int countByKeywords(
		String keywords, LinkedHashMap<String, Object> params) {

		String[] codes = null;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
		}

		return countByC_S(
			codes, new int[] {WorkflowConstants.STATUS_ANY}, params, true);
	}

	public int countByC_S(
		String code, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		String[] codes = CustomSQLUtil.keywords(code);

		return countByC_S(codes, statuses, params, andOperator);
	}

	public List<PartnerEntry> findByKeywords(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end) {

		String[] codes = null;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
		}

		return findByC_S(
			codes, new int[] {WorkflowConstants.STATUS_ANY}, params, true,
			start, end);
	}

	public List<PartnerEntry> findByC_S(
		String code, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end) {

		String[] codes = CustomSQLUtil.keywords(code);

		return findByC_S(codes, statuses, params, andOperator, start, end);
	}

	protected int countByC_S(
		String[] codes, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		Long userId = (Long)params.remove("accountEntryMembership");

		if (userId != null) {
			params.put("partnerWorker", userId);
		}

		codes = CustomSQLUtil.keywords(codes);

		String sql = CustomSQLUtil.get(getClass(), COUNT_BY_C_S);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_PartnerEntry.code_)", StringPool.LIKE, false,
			codes);

		if (ArrayUtil.contains(statuses, WorkflowConstants.STATUS_ANY) ||
			ArrayUtil.isEmpty(statuses)) {

			sql = StringUtil.replace(sql, _STATUS_SQL, StringPool.BLANK);
		}

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		long classNameId = PortalUtil.getClassNameId(PartnerEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.PARTNERENTRY$]",
			String.valueOf(classNameId));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(codes, 2);

			if (!ArrayUtil.contains(statuses, WorkflowConstants.STATUS_ANY) &&
				!ArrayUtil.isEmpty(statuses)) {

				qPos.add(statuses);
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

	protected List<PartnerEntry> findByC_S(
		String[] codes, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end) {

		Long userId = (Long)params.remove("accountEntryMembership");

		if (userId != null) {
			params.put("partnerWorker", userId);
		}

		codes = CustomSQLUtil.keywords(codes);

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_C_S);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_PartnerEntry.code_)", StringPool.LIKE, true, codes);

		if (ArrayUtil.contains(statuses, WorkflowConstants.STATUS_ANY) ||
			ArrayUtil.isEmpty(statuses)) {

			sql = StringUtil.replace(sql, _STATUS_SQL, StringPool.BLANK);
		}

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		long classNameId = PortalUtil.getClassNameId(PartnerEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.PARTNERENTRY$]",
			String.valueOf(classNameId));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_PartnerEntry", PartnerEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(codes, 2);

			if (!ArrayUtil.contains(statuses, WorkflowConstants.STATUS_ANY) &&
				!ArrayUtil.isEmpty(statuses)) {

				qPos.add(statuses);
			}

			return (List<PartnerEntry>)QueryUtil.list(
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

		if (key.equals("managingSupport")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_PARTNER_MANAGED_SUPPORT);
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("supportRegionIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);
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

		if (key.equals("childPartnerEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_CHILD_PARTNER_ENTRY);

			Boolean valueBoolean = (Boolean)value;

			if (valueBoolean) {
				join = StringUtil.replace(join, "[$NOT_IN_OR_IN_CHECK$]", "IN");
			}
			else {
				join = StringUtil.replace(
					join, "[$NOT_IN_OR_IN_CHECK$]", "NOT IN");
			}
		}
		else if (key.equals("managingSupport")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_PARTNER_MANAGED_SUPPORT);
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("status")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_STATUS);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerEntry.status", true, (int[])value);
		}
		else if (key.equals("supportRegionIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_PartnerEntries_SupportRegions.supportRegionId", true,
				(long[])value);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				String substring = join.substring(pos + 5);

				join = substring.concat(" AND ");
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

			if (key.equals("childPartnerEntry")) {
				continue;
			}
			else {
				Object value = entry.getValue();

				if (value instanceof Boolean) {
					Boolean valueBoolean = (Boolean)value;

					if (Validator.isNotNull(valueBoolean)) {
						qPos.add(valueBoolean);
					}
				}
				else if (value instanceof int[]) {
					int[] valueArray = (int[])value;

					for (int valueInt : valueArray) {
						if (Validator.isNotNull(valueInt)) {
							qPos.add(valueInt);
						}
					}
				}
				else if (value instanceof Integer) {
					Integer valueInteger = (Integer)value;

					if (Validator.isNotNull(valueInteger)) {
						qPos.add(valueInteger);
					}
				}
				else if (value instanceof long[]) {
					long[] valueArray = (long[])value;

					for (long valueLong : valueArray) {
						if (Validator.isNotNull(valueLong)) {
							qPos.add(valueLong);
						}
					}
				}
				else if (value instanceof Long) {
					Long valueLong = (Long)value;

					if (Validator.isNotNull(valueLong)) {
						qPos.add(valueLong);
					}
				}
			}
		}
	}

	private static final String _STATUS_SQL =
		"[$AND_OR_CONNECTOR$] (OSB_PartnerEntry.status = ?)";

}