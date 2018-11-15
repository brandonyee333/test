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

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAComponentFinder;
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