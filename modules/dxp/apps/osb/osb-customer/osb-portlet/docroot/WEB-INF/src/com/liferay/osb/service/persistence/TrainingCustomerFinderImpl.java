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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Shin
 */
public class TrainingCustomerFinderImpl
	extends BasePersistenceImpl<TrainingCustomer>
	implements TrainingCustomerFinder {

	public static final String COUNT_BY_FN_LN_EA =
		TrainingCustomerFinder.class.getName() + ".countByFN_LN_EA";

	public static final String FIND_BY_FN_LN_EA =
		TrainingCustomerFinder.class.getName() + ".findByFN_LN_EA";

	public static final String JOIN_BY_EMAIL_ADDRESS =
		TrainingCustomerFinder.class.getName() + ".joinByEmailAddress";

	public int countByKeywords(String keywords) throws SystemException {
		String[] firstNames = null;
		String[] lastNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(keywords)) {
			firstNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);

			String[] values = {
				StringPool.PERCENT +
					TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX
			};

			for (String emailAddress : emailAddresses) {
				values = ArrayUtil.append(values, emailAddress);
				values = ArrayUtil.append(values, emailAddress);
			}

			params.put("emailAddress", values);
		}
		else {
			andOperator = true;
		}

		return countByFN_LN_EA(
			firstNames, lastNames, emailAddresses, params, andOperator);
	}

	public int countByFN_LN_EA(
			String firstName, String lastName, String emailAddress,
			boolean andOperator)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(emailAddress)) {
			String[] values = {
				StringPool.PERCENT +
					TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX
			};

			emailAddress = CustomSQLUtil.keywords(emailAddress)[0];

			values = ArrayUtil.append(values, emailAddress);
			values = ArrayUtil.append(values, emailAddress);

			params.put("emailAddress", values);
		}

		String[] firstNames = CustomSQLUtil.keywords(firstName);
		String[] lastNames = CustomSQLUtil.keywords(lastName);
		String[] emailAddresses = CustomSQLUtil.keywords(emailAddress);

		return countByFN_LN_EA(
			firstNames, lastNames, emailAddresses, params, andOperator);
	}

	public List<TrainingCustomer> findByKeywords(
			String keywords, int start, int end)
		throws SystemException {

		String[] firstNames = null;
		String[] lastNames = null;
		String[] emailAddresses = null;
		boolean andOperator = false;

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(keywords)) {
			firstNames = CustomSQLUtil.keywords(keywords);
			lastNames = CustomSQLUtil.keywords(keywords);
			emailAddresses = CustomSQLUtil.keywords(keywords);

			String[] values = {
				StringPool.PERCENT +
					TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX
			};

			for (String emailAddress : emailAddresses) {
				values = ArrayUtil.append(values, emailAddress);
				values = ArrayUtil.append(values, emailAddress);
			}

			params.put("emailAddress", values);
		}
		else {
			andOperator = true;
		}

		return findByFN_LN_EA(
			firstNames, lastNames, emailAddresses, params, andOperator, start,
			end);
	}

	protected int countByFN_LN_EA(
			String[] firstNames, String[] lastNames, String[] emailAddresses,
			LinkedHashMap<String, Object> params, boolean andOperator)
		throws SystemException {

		firstNames = CustomSQLUtil.keywords(firstNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses);

		String sql = CustomSQLUtil.get(COUNT_BY_FN_LN_EA);

		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.emailAddress)", StringPool.LIKE, true,
			emailAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.firstName)", StringPool.LIKE, false, firstNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.lastName)", StringPool.LIKE, true, lastNames);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);
			qPos.add(firstNames, 2);
			qPos.add(lastNames, 2);

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

	public List<TrainingCustomer> findByFN_LN_EA(
			String firstName, String lastName, String emailAddress,
			boolean andOperator, int start, int end)
		throws SystemException {

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(emailAddress)) {
			String[] values = {
				StringPool.PERCENT +
					TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX
			};

			emailAddress = CustomSQLUtil.keywords(emailAddress)[0];

			values = ArrayUtil.append(values, emailAddress);
			values = ArrayUtil.append(values, emailAddress);

			params.put("emailAddress", values);
		}

		String[] firstNames = CustomSQLUtil.keywords(firstName);
		String[] lastNames = CustomSQLUtil.keywords(lastName);
		String[] emailAddresses = CustomSQLUtil.keywords(emailAddress);

		return findByFN_LN_EA(
			firstNames, lastNames, emailAddresses, params, andOperator, start,
			end);
	}

	protected List<TrainingCustomer> findByFN_LN_EA(
			String[] firstNames, String[] lastNames, String[] emailAddresses,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end)
		throws SystemException {

		firstNames = CustomSQLUtil.keywords(firstNames);
		lastNames = CustomSQLUtil.keywords(lastNames);
		emailAddresses = CustomSQLUtil.keywords(emailAddresses);

		String sql = CustomSQLUtil.get(FIND_BY_FN_LN_EA);

		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.emailAddress)", StringPool.LIKE, true,
			emailAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.firstName)", StringPool.LIKE, false, firstNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(User_.lastName)", StringPool.LIKE, true, lastNames);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("userId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);
			qPos.add(firstNames, 2);
			qPos.add(lastNames, 2);

			List<TrainingCustomer> trainingCustomers =
				new ArrayList<TrainingCustomer>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long userId = (Long)itr.next();

				TrainingCustomer trainingCustomer =
					TrainingCustomerUtil.findByUserId_First(userId, null);

				trainingCustomers.add(trainingCustomer);
			}

			return trainingCustomers;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key) {
		String join = StringPool.BLANK;

		if (key.equals("emailAddress")) {
			join = CustomSQLUtil.get(JOIN_BY_EMAIL_ADDRESS);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(
					" [$AND_OR_CONNECTOR$] ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params != null) {
			Iterator<Map.Entry<String, Object>> itr =
				params.entrySet().iterator();

			while (itr.hasNext()) {
				Map.Entry<String, Object> entry = itr.next();

				Object value = entry.getValue();

				if (value instanceof String[]) {
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

}