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

package com.liferay.osb.customer.release.notes.service.persistence.impl;

import com.liferay.osb.customer.release.notes.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.model.impl.JIRAComponentImpl;
import com.liferay.osb.customer.release.notes.service.persistence.JIRAComponentFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Iterator;
import java.util.List;

/**
 * @author Samuel Kong
 */
public class JIRAComponentFinderImpl
	extends JIRAComponentFinderBaseImpl implements JIRAComponentFinder {

	public static final String COUNT_BY_JIRA_ISSUE =
		JIRAComponentFinder.class.getName() + ".countByJIRAIssue";

	public static final String FIND_BY_JIRA_ISSUE =
		JIRAComponentFinder.class.getName() + ".findByJIRAIssue";

	public int countByJIRAIssue(long jiraIssueId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_JIRA_ISSUE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueId);

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

	public List<JIRAComponent> findByJIRAIssue(long jiraIssueId) {
		return findByJIRAIssue(
			jiraIssueId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAComponent> findByJIRAIssue(
		long jiraIssueId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_JIRA_ISSUE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("component", JIRAComponentImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueId);

			return (List<JIRAComponent>)QueryUtil.list(
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