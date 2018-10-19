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
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.impl.AccountEntryImpl;
import com.liferay.osb.service.persistence.AccountEntryFinder;
import com.liferay.osb.service.persistence.AccountEntryUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBCustomSQLUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

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
public class AccountEntryFinderImpl
	extends AccountEntryFinderBaseImpl implements AccountEntryFinder {

	public static final String
		COUNT_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z =
			AccountEntryFinder.class.getName() +
				".countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z";

	public static final String FILTER_BY_INDUSTRY =
		AccountEntryFinder.class.getName() + ".filterByIndustry";

	public static final String FILTER_BY_STATUS =
		AccountEntryFinder.class.getName() + ".filterByStatus";

	public static final String FILTER_BY_TIER =
		AccountEntryFinder.class.getName() + ".filterByTier";

	public static final String FILTER_BY_TYPE =
		AccountEntryFinder.class.getName() + ".filterByType";

	public static final String FIND_BY_SECURITY_PATCH =
		AccountEntryFinder.class.getName() + ".findBySecurityPatch";

	public static final String
		FIND_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z =
			AccountEntryFinder.class.getName() +
				".findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z";

	public static final String JOIN_BY_ACCOUNT_CUSTOMER =
		AccountEntryFinder.class.getName() + ".joinByAccountCustomer";

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

	public static final String JOIN_BY_ACCOUNT_WORKER =
		AccountEntryFinder.class.getName() + ".joinByAccountWorker";

	public static final String JOIN_BY_ACTIVE_LICENSE =
		AccountEntryFinder.class.getName() + ".joinByActiveLicense";

	public static final String JOIN_BY_ACTIVE_SUPPORT =
		AccountEntryFinder.class.getName() + ".joinByActiveSupport";

	public static final String JOIN_BY_EXPIRED_SUPPORT =
		AccountEntryFinder.class.getName() + ".joinByExpiredSupport";

	public static final String JOIN_BY_NO_SUPPORT =
		AccountEntryFinder.class.getName() + ".joinByNoSupport";

	public static final String JOIN_BY_OFFERING =
		AccountEntryFinder.class.getName() + ".joinByOfferingEntry";

	public static final String JOIN_BY_PARTNER_WORKER =
		AccountEntryFinder.class.getName() + ".joinByPartnerWorker";

	public static final String JOIN_BY_PRIMARY_PRODUCT_ENTRY =
		AccountEntryFinder.class.getName() + ".joinByPrimaryProductEntry";

	public static final String JOIN_BY_PRODUCT_ENTRY =
		AccountEntryFinder.class.getName() + ".joinByProductEntry";

	public static final String JOIN_BY_SUPPORT_REGION =
		AccountEntryFinder.class.getName() + ".joinBySupportRegion";

	public static final String JOIN_BY_SUPPORT_RESPONSE =
		AccountEntryFinder.class.getName() + ".joinBySupportResponse";

	public static final String JOIN_BY_TICKET_SUPPORT =
		AccountEntryFinder.class.getName() + ".joinByTicketSupport";

	public int countByKeywords(
		String keywords, LinkedHashMap<String, Object> params) {

		String[] names = null;
		String[] codes = null;
		String[] instructions = null;
		String[] notes = null;
		String[] partnerEntryCodes = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = OSBCustomSQLUtil.keywords(keywords);
			codes = CustomSQLUtil.keywords(keywords);
			instructions = CustomSQLUtil.keywords(keywords);
			notes = CustomSQLUtil.keywords(keywords);
			partnerEntryCodes = CustomSQLUtil.keywords(keywords);
			streets = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			zips = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
			null, null, null, null, null, null, names, codes, new int[0], null,
			new int[0], new int[0], instructions, notes, partnerEntryCodes,
			streets, null, null, cities, zips, params, andOperator);
	}

	public int countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String name, String code, int[] industries,
		Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		String instructions, String notes, String partnerEntryCode,
		String street, Long countryId, Long regionId, String city, String zip,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] codes = CustomSQLUtil.keywords(code);
		String[] instructionsArray = CustomSQLUtil.keywords(instructions);
		String[] notesArray = CustomSQLUtil.keywords(notes);
		String[] partnerEntryCodes = CustomSQLUtil.keywords(partnerEntryCode);
		String[] streets = CustomSQLUtil.keywords(street);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] zips = CustomSQLUtil.keywords(zip);

		return countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, names, codes, industries,
			partnerManagedSupport, tiers, statuses, instructionsArray,
			notesArray, partnerEntryCodes, streets, countryId, regionId, cities,
			zips, params, andOperator);
	}

	public List<AccountEntry> findByKeywords(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		String[] names = null;
		String[] codes = null;
		String[] instructions = null;
		String[] notes = null;
		String[] partnerEntryCodes = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = OSBCustomSQLUtil.keywords(keywords);
			codes = CustomSQLUtil.keywords(keywords);
			instructions = CustomSQLUtil.keywords(keywords);
			notes = CustomSQLUtil.keywords(keywords);
			partnerEntryCodes = CustomSQLUtil.keywords(keywords);
			streets = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			zips = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
			null, null, null, null, null, null, names, codes, new int[0], null,
			new int[0], new int[0], instructions, notes, partnerEntryCodes,
			streets, null, null, cities, zips, params, andOperator, start, end,
			obc);
	}

	public List<AccountEntry> findBySecurityPatch(
		String portletId, LinkedHashMap<String, Object> params) {

		Long userId = (Long)params.remove("accountEntryMembership");

		if (userId != null) {
			params.put("accountCustomer", userId);
		}

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_SECURITY_PATCH);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_AccountEntry", AccountEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(portletId);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AccountEntry> findBySupportResponse(long supportResponseId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("supportResponseIds", new long[] {supportResponseId});

		return findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
			null, null, null, null, null, null, new String[0], new String[0],
			new int[0], null, new int[0], new int[0], null, null, null,
			new String[0], null, null, new String[0], new String[0], params,
			true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AccountEntry> findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String name, String code, int[] industries,
		Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		String instructions, String notes, String partnerEntryCode,
		String street, Long countryId, Long regionId, String city, String zip,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc) {

		String[] names = OSBCustomSQLUtil.keywords(name);
		String[] codes = CustomSQLUtil.keywords(code);
		String[] instructionsArray = CustomSQLUtil.keywords(instructions);
		String[] notesArray = CustomSQLUtil.keywords(notes);
		String[] partnerEntryCodes = CustomSQLUtil.keywords(partnerEntryCode);
		String[] streets = CustomSQLUtil.keywords(street);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] zips = CustomSQLUtil.keywords(zip);

		return findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, names, codes, industries,
			partnerManagedSupport, tiers, statuses, instructionsArray,
			notesArray, partnerEntryCodes, streets, countryId, regionId, cities,
			zips, params, andOperator, start, end, obc);
	}

	protected int countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String[] names, String[] codes, int[] industries,
		Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		String[] instructions, String[] notes, String[] partnerEntryCodes,
		String[] streets, Long countryId, Long regionId, String[] cities,
		String[] zips, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		if (params == null) {
			params = new LinkedHashMap<>();
		}
		else if (params.containsKey("activeSupport") ||
				 params.containsKey("expiredSupport") ||
				 params.containsKey("productEntryIds") ||
				 params.containsKey("supportResponseIds") ||
				 params.containsKey("ticketSupport")) {

			params.put("offeringEntry", StringPool.BLANK);
		}

		if (params.containsKey("accountEnvironmentEnvASIds") ||
			params.containsKey("accountEnvironmentEnvDBIds") ||
			params.containsKey("accountEnvironmentEnvJVMIds") ||
			params.containsKey("accountEnvironmentEnvLFRIds") ||
			params.containsKey("accountEnvironmentEnvOSIds")) {

			params.put("env", StringPool.BLANK);
		}

		Long userId = (Long)params.remove("accountEntryMembership");

		LinkedHashMap<String, Object> params1 = new LinkedHashMap<>(params);

		LinkedHashMap<String, Object> params2 = new LinkedHashMap<>(params1);

		if (userId != null) {
			params1.put("accountCustomer", userId);
			params2.put("partnerWorker", userId);
		}

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		names = CustomSQLUtil.keywords(names);
		codes = CustomSQLUtil.keywords(codes);
		instructions = CustomSQLUtil.keywords(instructions);
		notes = CustomSQLUtil.keywords(notes);
		partnerEntryCodes = CustomSQLUtil.keywords(partnerEntryCodes);
		streets = CustomSQLUtil.keywords(streets);
		cities = CustomSQLUtil.keywords(cities);
		zips = CustomSQLUtil.keywords(zips);

		String sql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		StringBundler sb = new StringBundler(4);

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(
					getClass(),
					COUNT_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z),
				createUserId, modifiedUserId, names, codes, industries,
				partnerManagedSupport, tiers, statuses, instructions, notes,
				partnerEntryCodes, streets, countryId, regionId, cities, zips,
				params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(
						getClass(),
						COUNT_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z),
					createUserId, modifiedUserId, names, codes, industries,
					partnerManagedSupport, tiers, statuses, instructions, notes,
					partnerEntryCodes, streets, countryId, regionId, cities,
					zips, params2, andOperator));
			sb.append(")");
		}

		sql = StringUtil.replace(sql, "[$SELECT_SQL$]", sb.toString());

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS, names,
				codes, industries, partnerManagedSupport, tiers, statuses,
				instructions, notes, partnerEntryCodes, streets, countryId,
				regionId, cities, zips, params1);

			if (userId != null) {
				setJoin(
					qPos, createUserId, createDateGT_TS, createDateLT_TS,
					modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS, names,
					codes, industries, partnerManagedSupport, tiers, statuses,
					instructions, notes, partnerEntryCodes, streets, countryId,
					regionId, cities, zips, params2);
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

	protected List<AccountEntry> findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String[] names, String[] codes, int[] industries,
		Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		String[] instructions, String[] notes, String[] partnerEntryCodes,
		String[] streets, Long countryId, Long regionId, String[] cities,
		String[] zips, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator obc) {

		if (params == null) {
			params = new LinkedHashMap<>();
		}
		else if (params.containsKey("activeSupport") ||
				 params.containsKey("expiredSupport") ||
				 params.containsKey("productEntryIds") ||
				 params.containsKey("supportResponseIds") ||
				 params.containsKey("ticketSupport")) {

			params.put("offeringEntry", StringPool.BLANK);
		}

		if (params.containsKey("accountEnvironmentEnvASIds") ||
			params.containsKey("accountEnvironmentEnvDBIds") ||
			params.containsKey("accountEnvironmentEnvJVMIds") ||
			params.containsKey("accountEnvironmentEnvLFRIds") ||
			params.containsKey("accountEnvironmentEnvOSIds")) {

			params.put("env", StringPool.BLANK);
		}

		Long userId = (Long)params.remove("accountEntryMembership");

		LinkedHashMap<String, Object> params1 = new LinkedHashMap<>(params);

		LinkedHashMap<String, Object> params2 = new LinkedHashMap<>(params1);

		if (userId != null) {
			params1.put("accountCustomer", userId);
			params2.put("partnerWorker", userId);
		}

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		names = CustomSQLUtil.keywords(names);
		codes = CustomSQLUtil.keywords(codes);
		instructions = CustomSQLUtil.keywords(instructions);
		notes = CustomSQLUtil.keywords(notes);
		partnerEntryCodes = CustomSQLUtil.keywords(partnerEntryCodes);
		streets = CustomSQLUtil.keywords(streets);
		cities = CustomSQLUtil.keywords(cities);
		zips = CustomSQLUtil.keywords(zips);

		StringBundler sb = new StringBundler(4);

		sb.append(
			replaceSQL(
				CustomSQLUtil.get(
					getClass(),
					FIND_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z),
				createUserId, modifiedUserId, names, codes, industries,
				partnerManagedSupport, tiers, statuses, instructions, notes,
				partnerEntryCodes, streets, countryId, regionId, cities, zips,
				params1, andOperator));

		if (userId != null) {
			sb.append(" UNION (");
			sb.append(
				replaceSQL(
					CustomSQLUtil.get(
						getClass(),
						FIND_BY_U_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z),
					createUserId, modifiedUserId, names, codes, industries,
					partnerManagedSupport, tiers, statuses, instructions, notes,
					partnerEntryCodes, streets, countryId, regionId, cities,
					zips, params2, andOperator));
			sb.append(")");
		}

		String sql = sb.toString();

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("accountEntryId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS, names,
				codes, industries, partnerManagedSupport, tiers, statuses,
				instructions, notes, partnerEntryCodes, streets, countryId,
				regionId, cities, zips, params1);

			if (userId != null) {
				setJoin(
					qPos, createUserId, createDateGT_TS, createDateLT_TS,
					modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS, names,
					codes, industries, partnerManagedSupport, tiers, statuses,
					instructions, notes, partnerEntryCodes, streets, countryId,
					regionId, cities, zips, params2);
			}

			List<Long> accountEntryIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<AccountEntry> accountEntries = new ArrayList<>(
				accountEntryIds.size());

			for (Long accountEntryId : accountEntryIds) {
				AccountEntry accountEntry = AccountEntryUtil.findByPrimaryKey(
					accountEntryId);

				accountEntries.add(accountEntry);
			}

			return accountEntries;
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

			if (Validator.isNotNull(value) || key.equals("env") ||
				key.equals("offeringEntry")) {

				sb.append(getJoin(key, value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountCustomer")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_CUSTOMER);
		}
		else if (key.equals("accountEnvironmentEnvASIds")) {
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
		else if (key.equals("accountWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_WORKER);
		}
		else if (key.equals("activeLicense")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACTIVE_LICENSE);
		}
		else if (key.equals("env")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_ENVIRONMENT);
		}
		else if (key.equals("offeringEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_OFFERING);
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("primaryProductEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRIMARY_PRODUCT_ENTRY);
		}
		else if (key.equals("productEntryIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRODUCT_ENTRY);
		}
		else if (key.equals("supportRegionIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_REGION);
		}
		else if (key.equals("supportResponseIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_RESPONSE);
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

			if (Validator.isNotNull(value) || key.equals("noSupport")) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountCustomer")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_CUSTOMER);
		}
		else if (key.equals("accountEnvironmentEnvASIds")) {
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
		else if (key.equals("accountWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_WORKER);
		}
		else if (key.equals("activeLicense")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACTIVE_LICENSE);
		}
		else if (key.equals("activeSupport")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACTIVE_SUPPORT);

			Boolean objectBoolean = (Boolean)value;

			if (objectBoolean) {
				join = StringUtil.replace(
					join, "[$ACTIVE_SUPPORT_COMPARATOR$]", ">");
			}
			else {
				join = StringUtil.replace(
					join, "[$ACTIVE_SUPPORT_COMPARATOR$]", "<=");
			}
		}
		else if (key.equals("expiredSupport")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_EXPIRED_SUPPORT);

			if (value instanceof boolean[]) {
				boolean[] valueArray = (boolean[])value;

				if (valueArray[0]) {
					join = StringUtil.replace(
						join, "[$EXPIRED_SUPPORT_COMPARATOR$]", "<=");
				}
				else {
					join = StringUtil.replace(
						join, "[$EXPIRED_SUPPORT_COMPARATOR$]", ">");
				}
			}
		}
		else if (key.equals("industry")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_INDUSTRY);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.industry", true, (int[])value);
		}
		else if (key.equals("noSupport")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_NO_SUPPORT);
		}
		else if (key.equals("partnerWorker")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PARTNER_WORKER);
		}
		else if (key.equals("primaryProductEntry")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_PRIMARY_PRODUCT_ENTRY);
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
		else if (key.equals("supportResponseIds")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_SUPPORT_RESPONSE);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_OfferingEntry.supportResponseId", true,
				(long[])value);
		}
		else if (key.equals("status")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_STATUS);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.status", true, (int[])value);
		}
		else if (key.equals("tier")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_TIER);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.tier", true, (int[])value);
		}
		else if (key.equals("ticketSupport")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_TICKET_SUPPORT);

			Boolean objectBoolean = (Boolean)value;

			if (objectBoolean) {
				join = StringUtil.replace(
					join, "[$TICKET_SUPPORT_COMPARATOR$]", ">");
			}
			else {
				join = StringUtil.replace(
					join, "[$TICKET_SUPPORT_COMPARATOR$]", "<=");
			}
		}
		else if (key.equals("type")) {
			join = CustomSQLUtil.get(getClass(), FILTER_BY_TYPE);

			join = CustomSQLUtil.replaceKeywords(
				join, "OSB_AccountEntry.type_", true, (int[])value);
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

	protected String replaceSQL(
		String sql, Long createUserId, Long modifiedUserId, String[] names,
		String[] codes, int[] industries, Boolean partnerManagedSupport,
		int[] tiers, int[] statuses, String[] instructions, String[] notes,
		String[] partnerEntryCodes, String[] streets, Long countryId,
		Long regionId, String[] cities, String[] zips,
		LinkedHashMap<String, Object> params, boolean andOperator) {

		if (createUserId == null) {
			sql = StringUtil.replace(sql, _USER_ID_SQL, StringPool.BLANK);
		}

		if (modifiedUserId == null) {
			sql = StringUtil.replace(
				sql, _MODIFIED_USER_ID_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.name)", StringPool.LIKE, false, names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.code_)", StringPool.LIKE, false,
			codes);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AccountEntry.industry", false, industries);

		if (partnerManagedSupport == null) {
			sql = StringUtil.replace(
				sql, _PARTNER_MANAGED_SUPPORT_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AccountEntry.tier", false, tiers);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AccountEntry.status", false, statuses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.instructions)", StringPool.LIKE, false,
			instructions);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_AccountEntry.notes)", StringPool.LIKE, false,
			notes);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_PartnerEntry.code_)", StringPool.LIKE, false,
			partnerEntryCodes);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.street1)", StringPool.LIKE, true, streets);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.city)", StringPool.LIKE, false, cities);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.zip)", StringPool.LIKE, true, zips);

		if (countryId == null) {
			sql = StringUtil.replace(sql, _COUNTRY_ID_SQL, StringPool.BLANK);
		}

		if (regionId == null) {
			sql = StringUtil.replace(sql, _REGION_ID_SQL, StringPool.BLANK);
		}

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		long classNameId = PortalUtil.getClassNameId(AccountEntry.class);

		sql = StringUtil.replace(
			sql, "[$CLASS_NAME_ID_COM.LIFERAY.OSB.MODEL.ACCOUNTENTRY$]",
			String.valueOf(classNameId));

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			if (key.equals("accountWorker")) {
				Object value = entry.getValue();

				if (value instanceof Long[]) {
					Long[] valueArray = (Long[])value;

					if (valueArray.length == 2) {
						qPos.add(valueArray[0]);
						qPos.add(valueArray[1]);
						qPos.add(valueArray[1]);
					}
				}
			}
			else if (key.equals("activeSupport")) {
				qPos.add(OfferingEntryConstants.STATUS_ACTIVE);
			}
			else if (key.equals("expiredSupport")) {
				Object value = entry.getValue();

				if (value instanceof boolean[]) {
					boolean[] valueArray = (boolean[])value;

					long gracePeriod = 0;

					if (valueArray[1]) {
						gracePeriod = 30 * Time.DAY;
					}

					Date now = new Date(
						System.currentTimeMillis() - gracePeriod);

					qPos.add(now);
				}
			}
			else if (key.equals("noSupport")) {
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

				qPos.add((Integer)valueArray[3]);
				qPos.add((Integer)valueArray[4]);
			}
			else if (key.equals("ticketSupport")) {
				qPos.add(OfferingEntryConstants.STATUS_ACTIVE);
			}
			else {
				Object value = entry.getValue();

				if (value instanceof int[]) {
					int[] valueArray = (int[])value;

					for (int valueInt : valueArray) {
						if (Validator.isNotNull(valueInt)) {
							qPos.add(valueInt);
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

					for (long valueLong : valueArray) {
						if (Validator.isNotNull(valueLong)) {
							qPos.add(valueLong);
						}
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
	}

	protected void setJoin(
		QueryPos qPos, Long createUserId, Timestamp createDateGT,
		Timestamp createDateLT, Long modifiedUserId, Timestamp modifiedDateGT,
		Timestamp modifiedDateLT, String[] names, String[] codes,
		int[] industries, Boolean partnerManagedSupport, int[] tiers,
		int[] statuses, String[] instructions, String[] notes,
		String[] partnerEntryCodes, String[] streets, Long countryId,
		Long regionId, String[] cities, String[] zips,
		LinkedHashMap<String, Object> params) {

		qPos.add(OSBConstants.COMPANY_ID);

		setJoin(qPos, params);

		if (createUserId != null) {
			qPos.add(createUserId);
		}

		qPos.add(createDateGT);
		qPos.add(createDateGT);
		qPos.add(createDateLT);
		qPos.add(createDateLT);

		if (modifiedUserId != null) {
			qPos.add(modifiedUserId);
		}

		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateLT);
		qPos.add(modifiedDateLT);
		qPos.add(names, 2);
		qPos.add(codes, 2);
		qPos.add(industries);

		if (partnerManagedSupport != null) {
			qPos.add(partnerManagedSupport);
		}

		qPos.add(tiers);
		qPos.add(statuses);
		qPos.add(instructions, 2);
		qPos.add(notes, 2);
		qPos.add(partnerEntryCodes, 2);
		qPos.add(streets, 2);

		if (countryId != null) {
			qPos.add(countryId);
		}

		if (regionId != null) {
			qPos.add(regionId);
		}

		qPos.add(cities, 2);
		qPos.add(zips, 2);
	}

	private static final String _COUNTRY_ID_SQL =
		"(Address.countryId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _MODIFIED_USER_ID_SQL =
		"(OSB_AccountEntry.modifiedUserId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _PARTNER_MANAGED_SUPPORT_SQL =
		"(OSB_AccountEntry.partnerManagedSupport = ?) [$AND_OR_CONNECTOR$]";

	private static final String _REGION_ID_SQL =
		"(Address.regionId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _USER_ID_SQL =
		"(OSB_AccountEntry.userId = ?) [$AND_OR_CONNECTOR$]";

}