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

import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.impl.AssetAuditImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

/**
 * @author Peter Shin
 */
public class AssetAuditFinderImpl
	extends BasePersistenceImpl<AssetAudit> implements AssetAuditFinder {

	public static final String FIND_BY_U_T =
		AssetAuditFinder.class.getName() + ".findByU_T";

	public static final String FIND_BY_VCNI_VCPK_T =
		AssetAuditFinder.class.getName() + ".findByGtCD_LtCD_VCNI_VCPK_T";

	public List<AssetAudit> findByU_T(long userId, int type, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_T);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AssetAudit", AssetAuditImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

			return (List<AssetAudit>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object[]> findByGtCD_LtCD_VCNI_VCPK_T(
			Date createDateGT, Date createDateLT, long vendorClassNameId,
			long vendorClassPK, int type, int start, int end)
		throws SystemException {

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_VCNI_VCPK_T);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("legalEntityName", Type.STRING);
			q.addScalar("COUNT_VALUE", Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDateGT_TS);
			qPos.add(createDateLT_TS);
			qPos.add(vendorClassNameId);
			qPos.add(vendorClassPK);
			qPos.add(type);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}