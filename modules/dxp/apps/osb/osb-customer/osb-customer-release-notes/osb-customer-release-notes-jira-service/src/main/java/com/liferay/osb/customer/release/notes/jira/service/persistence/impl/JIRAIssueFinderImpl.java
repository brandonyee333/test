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

package com.liferay.osb.customer.release.notes.jira.service.persistence.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueImpl;
import com.liferay.osb.customer.release.notes.jira.service.persistence.JIRAIssueFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Samuel Kong
 */
public class JIRAIssueFinderImpl
	extends JIRAIssueFinderBaseImpl implements JIRAIssueFinder {

	public static final String COUNT_BY_IS_RELATED_TO_JIRA_ISSUE =
		JIRAIssueFinder.class.getName() + ".countByIsRelatedToJIRAIssue";

	public static final String COUNT_BY_JIRA_PROJECT_VERSION =
		JIRAIssueFinder.class.getName() + ".countByJIRAProjectVersion";

	public static final String COUNT_BY_L_K =
		JIRAIssueFinder.class.getName() + ".countByL_K";

	public static final String FIND_BY_IS_RELATED_TO_JIRA_ISSUE =
		JIRAIssueFinder.class.getName() + ".findByIsRelatedToJIRAIssue";

	public static final String FIND_BY_JIRA_PROJECT_VERSION =
		JIRAIssueFinder.class.getName() + ".findByJIRAProjectVersion";

	public static final String FIND_BY_KEY =
		JIRAIssueFinder.class.getName() + ".findByKey";

	public static final String FIND_BY_L_K =
		JIRAIssueFinder.class.getName() + ".findByL_K";

	public int countByIsRelatedToJIRAIssue(long jiraIssueId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_IS_RELATED_TO_JIRA_ISSUE);

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

	public int countByJIRAProjectVersion(long jiraProjectVersionId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_BY_JIRA_PROJECT_VERSION);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraProjectVersionId);

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

	public int countByL_K(String label, String jiraProjectKey) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_L_K);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(label);
			qPos.add(jiraProjectKey);

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

	public List<JIRAIssue> findByIsRelatedToJIRAIssue(long jiraIssueId) {
		return findByIsRelatedToJIRAIssue(
			jiraIssueId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAIssue> findByIsRelatedToJIRAIssue(
		long jiraIssueId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_IS_RELATED_TO_JIRA_ISSUE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueId);

			return (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByJIRAProjectVersion(long jiraProjectVersionId) {
		return findByJIRAProjectVersion(
			jiraProjectVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAIssue> findByJIRAProjectVersion(
		long jiraProjectVersionId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_JIRA_PROJECT_VERSION);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraProjectVersionId);

			return (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public JIRAIssue findByKey(String jiraIssueKey) {
		String[] jiraIssueKeyArray = StringUtil.split(
			jiraIssueKey, StringPool.DASH);

		if (jiraIssueKeyArray.length != 2) {
			return null;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_KEY);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueKeyArray[1]);
			qPos.add(jiraIssueKeyArray[0]);

			List<JIRAIssue> list = q.list();

			if (list.isEmpty()) {
				return null;
			}

			return list.get(0);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByL_K(String label, String jiraProjectKey) {
		return findByL_K(
			label, jiraProjectKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAIssue> findByL_K(
		String label, String jiraProjectKey, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_L_K);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(label);
			qPos.add(jiraProjectKey);

			return (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}