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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.impl.AccountEntryImpl;
import com.liferay.osb.service.persistence.AccountEntryFinder;
import com.liferay.osb.util.OSBCustomSQLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class AccountEntryFinderImpl
	extends AccountEntryFinderBaseImpl implements AccountEntryFinder {

	public static final String COUNT_BY_KAK_DAK_N_C_I_S =
		AccountEntryFinder.class.getName() + ".countByKAK_DAK_N_C_I_S";

	public static final String FILTER_BY_STATUS =
		AccountEntryFinder.class.getName() + ".filterByStatus";

	public static final String FIND_BY_KAK_DAK_N_C_I_S =
		AccountEntryFinder.class.getName() + ".findByKAK_DAK_N_C_I_S";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironment";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_AS =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironmentEnvAS";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_DB =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironmentEnvDB";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_JVM =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironmentEnvJVM";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_LFR =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironmentEnvLFR";

	public static final String JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_OS =
		AccountEntryFinder.class.getName() + ".joinByAccountEnvironmentEnvOS";

	public static final String JOIN_BY_PRODUCT_ENTRY =
		AccountEntryFinder.class.getName() + ".joinByProductEntry";

	public static final String JOIN_BY_SUPPORT_REGION =
		AccountEntryFinder.class.getName() + ".joinBySupportRegion";

	public int countByKeywords(
		String keywords, LinkedHashMap<String, Object> params) {

		String[] names = null;
		String[] codes = null;
		String[] instructions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = OSBCustomSQLUtil.keywords(keywords);
			codes = CustomSQLUtil.keywords(keywords);
			instructions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByKAK_DAK_N_C_I_S(
			null, null, names, codes, new int[0], instructions, params,
			andOperator);
	}

	public int countByKAK_DAK_N_C_I_S(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		String[] koroneikiAccountKeys = CustomSQLUtil.keywords(
			koroneikiAccountKey);
		String[] dossieraAccountKeys = CustomSQLUtil.keywords(
			dossieraAccountKey);
		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] codes = CustomSQLUtil.keywords(code);
		String[] instructionsArray = CustomSQLUtil.keywords(instructions);

		return countByKAK_DAK_N_C_I_S(
			koroneikiAccountKeys, dossieraAccountKeys, names, codes, statuses,
			instructionsArray, params, andOperator);
	}

	public List<AccountEntry> findByKeywords(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		String[] names = null;
		String[] codes = null;
		String[] instructions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = OSBCustomSQLUtil.keywords(keywords);
			codes = CustomSQLUtil.keywords(keywords);
			instructions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByKAK_DAK_N_C_I_S(
			null, null, names, codes, new int[0], instructions, params,
			andOperator, start, end, obc);
	}

	public List<AccountEntry> findByKAK_DAK_N_C_I_S(
		String koroneikiAccountKey, String dossieraAccountKey, String name,
		String code, int[] statuses, String instructions,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc) {

		String[] koroneikiAccountKeys = CustomSQLUtil.keywords(
			koroneikiAccountKey);
		String[] dossieraAccountKeys = CustomSQLUtil.keywords(
			dossieraAccountKey);
		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] codes = CustomSQLUtil.keywords(code);
		String[] instructionsArray = CustomSQLUtil.keywords(instructions);

		return findByKAK_DAK_N_C_I_S(
			koroneikiAccountKeys, dossieraAccountKeys, names, codes, statuses,
			instructionsArray, params, andOperator, start, end, obc);
	}

	protected int countByKAK_DAK_N_C_I_S(
		String[] koroneikiAccountKeys, String[] dossieraAccountKeys,
		String[] names, String[] codes, int[] statuses, String[] instructions,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		if (params == null) {
			params = new LinkedHashMap<>();
		}
		else if (params.containsKey("accountEnvironmentEnvASIds") ||
				 params.containsKey("accountEnvironmentEnvDBIds") ||
				 params.containsKey("accountEnvironmentEnvJVMIds") ||
				 params.containsKey("accountEnvironmentEnvLFRIds") ||
				 params.containsKey("accountEnvironmentEnvOSIds") ||
				 params.containsKey("productEntryIds")) {

			params.put("env", StringPool.BLANK);
		}

		koroneikiAccountKeys = CustomSQLUtil.keywords(koroneikiAccountKeys);
		dossieraAccountKeys = CustomSQLUtil.keywords(dossieraAccountKeys);
		names = CustomSQLUtil.keywords(names);
		codes = CustomSQLUtil.keywords(codes);
		instructions = CustomSQLUtil.keywords(instructions);

		String sql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		StringBundler sb = new StringBundler(1);

		String countBySQL = CustomSQLUtil.get(
			getClass(), COUNT_BY_KAK_DAK_N_C_I_S);

		sb.append(
			replaceSQL(
				countBySQL, koroneikiAccountKeys, dossieraAccountKeys, names,
				codes, statuses, instructions, params, andOperator));

		sql = StringUtil.replace(sql, "[$SELECT_SQL$]", sb.toString());

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, koroneikiAccountKeys, dossieraAccountKeys, names, codes,
				statuses, instructions, params);

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

	protected List<AccountEntry> findByKAK_DAK_N_C_I_S(
		String[] koroneikiAccountKeys, String[] dossieraAccountKeys,
		String[] names, String[] codes, int[] statuses, String[] instructions,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc) {

		if (params == null) {
			params = new LinkedHashMap<>();
		}
		else if (params.containsKey("accountEnvironmentEnvASIds") ||
				 params.containsKey("accountEnvironmentEnvDBIds") ||
				 params.containsKey("accountEnvironmentEnvJVMIds") ||
				 params.containsKey("accountEnvironmentEnvLFRIds") ||
				 params.containsKey("accountEnvironmentEnvOSIds") ||
				 params.containsKey("productEntryIds")) {

			params.put("env", StringPool.BLANK);
		}

		koroneikiAccountKeys = CustomSQLUtil.keywords(koroneikiAccountKeys);
		dossieraAccountKeys = CustomSQLUtil.keywords(dossieraAccountKeys);
		names = CustomSQLUtil.keywords(names);
		codes = CustomSQLUtil.keywords(codes);
		instructions = CustomSQLUtil.keywords(instructions);

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_KAK_DAK_N_C_I_S);

		sql = replaceSQL(
			sql, koroneikiAccountKeys, dossieraAccountKeys, names, codes,
			statuses, instructions, params, andOperator);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_AccountEntry", AccountEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, koroneikiAccountKeys, dossieraAccountKeys, names, codes,
				statuses, instructions, params);

			return (List<AccountEntry>)QueryUtil.list(
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

			if (Validator.isNotNull(value) || key.equals("env")) {
				sb.append(getJoin(key, value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountEnvironmentEnvASIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_AS);
		}
		else if (key.equals("accountEnvironmentEnvDBIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_DB);
		}
		else if (key.equals("accountEnvironmentEnvJVMIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_JVM);
		}
		else if (key.equals("accountEnvironmentEnvLFRIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_LFR);
		}
		else if (key.equals("accountEnvironmentEnvOSIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_OS);
		}
		else if (key.equals("env")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT);
		}
		else if (key.equals("productEntryIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRODUCT_ENTRY);
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

		if (key.equals("accountEnvironmentEnvASIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_AS);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEnvironment.envAS", true, (long[])value);
		}
		else if (key.equals("accountEnvironmentEnvDBIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_DB);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEnvironment.envDB", true, (long[])value);
		}
		else if (key.equals("accountEnvironmentEnvJVMIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_JVM);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEnvironment.envJVM", true, (long[])value);
		}
		else if (key.equals("accountEnvironmentEnvLFRIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_LFR);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEnvironment.envLFR", true, (long[])value);
		}
		else if (key.equals("accountEnvironmentEnvOSIds")) {
			join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT_ENV_OS);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEnvironment.envOS", true, (long[])value);
		}
		else if (key.equals("productEntryIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRODUCT_ENTRY);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_OfferingEntry.productEntryId", true, (long[])value);
		}
		else if (key.equals("supportRegionIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntries_SupportRegions.supportRegionId", true,
				(long[])value);
		}
		else if (key.equals("status")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_STATUS);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.status", true, (int[])value);
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

	protected String replaceSQL(
		String sql, String[] koroneikiAccountKeys, String[] dossieraAccountKeys,
		String[] names, String[] codes, int[] statuses, String[] instructions,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.koroneikiAccountKey)", StringPool.LIKE,
			false, koroneikiAccountKeys);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.dossieraAccountKey)", StringPool.LIKE,
			false, dossieraAccountKeys);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.name)", StringPool.LIKE, false, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.code_)", StringPool.LIKE, false,
			codes);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AccountEntry.status", false, statuses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.instructions)", StringPool.LIKE, false,
			instructions);

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

			if (value instanceof int[]) {
				int[] valueArray = (int[])value;

				for (int valueInt : valueArray) {
					qPos.add(valueInt);
				}
			}
			else if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (key.equals("primaryProductEntry")) {
				Object[] valueArray = (Object[])entry.getValue();

				qPos.add((Integer)valueArray[0]);

				String valueString = StringUtil.toLowerCase(
					(String)valueArray[1]);

				valueString = StringUtil.quote(valueString, StringPool.PERCENT);

				qPos.add(valueString);

				String valueString2 = StringUtil.toLowerCase(
					(String)valueArray[2]);

				valueString2 = StringUtil.quote(
					valueString2, StringPool.PERCENT);

				qPos.add(valueString2);

				String valueString3 = StringUtil.toLowerCase(
					(String)valueArray[3]);

				valueString3 = StringUtil.quote(
					valueString3, StringPool.PERCENT);

				qPos.add(valueString3);

				qPos.add((Integer)valueArray[4]);

				String valueString5 = StringUtil.toLowerCase(
					(String)valueArray[5]);

				valueString5 = StringUtil.quote(
					valueString5, StringPool.PERCENT);

				qPos.add(valueString5);

				qPos.add((Integer)valueArray[6]);
			}
			else if (value instanceof long[]) {
				long[] valueArray = (long[])value;

				for (long valueLong : valueArray) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (Long valueLong : valueArray) {
					if (Validator.isNotNull(valueLong)) {
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

	protected void setJoin(
		QueryPos qPos, String[] koroneikiAccountKeys,
		String[] dossieraAccountKeys, String[] names, String[] codes,
		int[] statuses, String[] instructions,
		LinkedHashMap<String, Object> params) {

		setJoin(qPos, params);

		qPos.add(koroneikiAccountKeys, 2);
		qPos.add(dossieraAccountKeys, 2);
		qPos.add(names, 2);
		qPos.add(codes, 2);
		qPos.add(statuses);
		qPos.add(instructions, 2);
	}

}