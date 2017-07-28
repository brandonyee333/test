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

import com.liferay.osb.model.TicketCannedResponse;
import com.liferay.osb.model.impl.TicketCannedResponseImpl;
import com.liferay.osb.service.persistence.TicketCannedResponseFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Amos Fong
 */
public class TicketCannedResponseFinderImpl
	extends TicketCannedResponseFinderBaseImpl
	implements TicketCannedResponseFinder {

	public static final String COUNT_BY_N_C =
		TicketCannedResponseFinder.class.getName() + ".countByN_C";

	public static final String FIND_BY_N_C =
		TicketCannedResponseFinder.class.getName() + ".findByN_C";

	public int countByKeywords(String keywords) {
		String[] names = null;
		String[] contents = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			contents = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByN_C(names, contents, andOperator);
	}

	public int countByN_C(String name, String content, boolean andOperator) {
		String[] names = CustomSQLUtil.keywords(name);
		String[] contents = CustomSQLUtil.keywords(content);

		return countByN_C(names, contents, andOperator);
	}

	public List<TicketCannedResponse> findByKeywords(
		String keywords, int start, int end) {

		String[] names = null;
		String[] contents = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			contents = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByN_C(names, contents, andOperator, start, end);
	}

	public List<TicketCannedResponse> findByN_C(
		String name, String content, boolean andOperator, int start, int end) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] contents = CustomSQLUtil.keywords(content);

		return findByN_C(names, contents, andOperator, start, end);
	}

	protected int countByN_C(
		String[] names, String[] contents, boolean andOperator) {

		names = CustomSQLUtil.keywords(names);
		contents = CustomSQLUtil.keywords(contents);

		String sql = CustomSQLUtil.get(COUNT_BY_N_C);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketCannedResponse.name)", StringPool.LIKE, false,
			names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketCannedResponse.content)", StringPool.LIKE,
			true, contents);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);
			qPos.add(contents, 2);

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

	protected List<TicketCannedResponse> findByN_C(
		String[] names, String[] contents, boolean andOperator, int start,
		int end) {

		names = CustomSQLUtil.keywords(names);
		contents = CustomSQLUtil.keywords(contents);

		String sql = CustomSQLUtil.get(FIND_BY_N_C);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketCannedResponse.name)", StringPool.LIKE, false,
			names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(OSB_TicketCannedResponse.content)", StringPool.LIKE,
			true, contents);
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				"OSB_TicketCannedResponse", TicketCannedResponseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);
			qPos.add(contents, 2);

			return (List<TicketCannedResponse>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}