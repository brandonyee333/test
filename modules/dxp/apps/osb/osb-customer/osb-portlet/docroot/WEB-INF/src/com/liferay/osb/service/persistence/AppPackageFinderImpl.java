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

import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.impl.AppPackageImpl;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Ryan Park
 */
public class AppPackageFinderImpl
	extends BasePersistenceImpl<AppPackage>
	implements AppPackageFinder {

	public static final String FIND_BY_LATEST_COMPATIBILITY =
		AppPackageFinder.class.getName() + ".findByLatestCompatibility";

	public static final String FIND_BY_PBN_P_BSN_BV =
		AppPackageFinder.class.getName() + ".findByPBN_P_BSN_BV";

	public static final String FIND_BY_PBN_P_CN_RH =
		AppPackageFinder.class.getName() + ".findByPBN_P_CN_RH";

	public List<AppPackage> findByLatestCompatibility(
			long appEntryId, int compatibility, int status, int start, int end)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_LATEST_COMPATIBILITY);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppPackage", AppPackageImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(appEntryId);
			qPos.add(status);
			qPos.add(compatibility);
			qPos.add(PortalReleaseUtil.getBaseBuildNumber(compatibility));
			qPos.add(compatibility);
			qPos.add(true);

			return (List<AppPackage>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AppPackage> findByPBN_P_BSN_BV(
			int portalBuildNumber, boolean prepackaged,
			String bundleSymbolicName, String bundleVersion, int start, int end)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_PBN_P_BSN_BV);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppPackage", AppPackageImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(portalBuildNumber);
			qPos.add(prepackaged);
			qPos.add(bundleSymbolicName);
			qPos.add(bundleVersion);

			return (List<AppPackage>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AppPackage> findByPBN_P_CN_RH(
			int portalBuildNumber, boolean prepackaged, String contextName,
			String relengHash, int start, int end)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_PBN_P_CN_RH);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppPackage", AppPackageImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(portalBuildNumber);
			qPos.add(prepackaged);
			qPos.add(contextName);
			qPos.add(relengHash);

			return (List<AppPackage>)QueryUtil.list(
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