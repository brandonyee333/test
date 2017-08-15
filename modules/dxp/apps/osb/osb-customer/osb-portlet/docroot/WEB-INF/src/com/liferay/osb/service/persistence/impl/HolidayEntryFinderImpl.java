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

import com.liferay.osb.model.HolidayEntry;
import com.liferay.osb.model.impl.HolidayEntryImpl;
import com.liferay.osb.service.persistence.HolidayEntryFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class HolidayEntryFinderImpl
	extends HolidayEntryFinderBaseImpl implements HolidayEntryFinder {

	public static final String FIND_BY_U_RY =
		HolidayEntryFinder.class.getName() + ".findByU_RY";

	public static final String FIND_BY_U_RY_SD_ED =
		HolidayEntryFinder.class.getName() + ".findByU_RY_SD_ED";

	public List<HolidayEntry> findByU_RY(long userId, boolean repeatYearly) {
		String sql = CustomSQLUtil.get(getClass(), FIND_BY_U_RY);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_HolidayEntry", HolidayEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(repeatYearly);

			return (List<HolidayEntry>)q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<HolidayEntry> findByU_RY_SD_ED(
		long userId, Boolean repeatYearly, Date startDate, Date endDate) {

		Timestamp startDate_TS = CalendarUtil.getTimestamp(startDate);
		Timestamp endDate_TS = CalendarUtil.getTimestamp(endDate);

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_U_RY_SD_ED);

		if (repeatYearly == null) {
			sql = StringUtil.replace(
				sql, "(OSB_HolidayEntry.repeatYearly = ?) AND",
				StringPool.BLANK);
		}

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("OSB_HolidayEntry", HolidayEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			if (repeatYearly != null) {
				qPos.add(repeatYearly);
			}

			qPos.add(startDate_TS);
			qPos.add(endDate_TS);
			qPos.add(startDate_TS);
			qPos.add(endDate_TS);

			return (List<HolidayEntry>)q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}