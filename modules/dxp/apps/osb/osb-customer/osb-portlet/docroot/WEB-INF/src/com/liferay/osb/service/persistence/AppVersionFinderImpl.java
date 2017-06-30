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

import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.impl.AppVersionImpl;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Douglas Wong
 * @author Joan Kim
 * @author Ryan Park
 */
public class AppVersionFinderImpl
	extends BasePersistenceImpl<AppVersion>
	implements AppVersionFinder {

	public static final String COUNT_BY_AEI_S =
		AppVersionFinder.class.getName() + ".countByAEI_S";

	public static final String FIND_BY_AEI_C_S =
		AppVersionFinder.class.getName() + ".findByAEI_C_S";

	public static final String FIND_BY_AEI_S =
		AppVersionFinder.class.getName() + ".findByAEI_S";

	public int countByAEI_S(long appEntryId, int[] statuses)
		throws SystemException {

		Session session = null;

		String sql = CustomSQLUtil.get(COUNT_BY_AEI_S);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AppVersion.status", true, statuses);

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(appEntryId);
			qPos.add(statuses);

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

	public List<AppVersion> findByAEI_S(
			long appEntryId, int[] statuses, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_AEI_S);

		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_AppVersion.status", true, statuses);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppVersion", AppVersionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(appEntryId);
			qPos.add(statuses);

			return (List<AppVersion>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AppVersion> findByAEI_C_S(
			long appEntryId, int compatibility, int status, int start, int end)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_AEI_C_S);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppVersion", AppVersionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(appEntryId);
			qPos.add(compatibility);
			qPos.add(PortalReleaseUtil.getBaseBuildNumber(compatibility));
			qPos.add(compatibility);
			qPos.add(true);
			qPos.add(status);

			return (List<AppVersion>)QueryUtil.list(
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