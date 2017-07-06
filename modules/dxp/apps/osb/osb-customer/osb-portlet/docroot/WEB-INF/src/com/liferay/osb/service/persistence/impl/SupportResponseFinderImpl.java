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

import com.liferay.osb.service.persistence.SupportResponseFinder;
import com.liferay.osb.service.persistence.SupportResponseUtil;

import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.impl.SupportResponseImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Lin Cui
 */
public class SupportResponseFinderImpl
	extends SupportResponseFinderBaseImpl
	implements SupportResponseFinder {

	public static final String FIND_BY_ACCOUNT_ENTRY =
		SupportResponseFinder.class.getName() + ".findByAccountEntry";

	public SupportResponse fetchByAccountEntry(long accountEntryId)
		throws PortalException, SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_ACCOUNT_ENTRY);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_SupportResponse", SupportResponseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(accountEntryId);

			List<SupportResponse> supportResponses =
				(List<SupportResponse>)QueryUtil.list(q, getDialect(), 0, 1);

			if (!supportResponses.isEmpty()) {
				return supportResponses.get(0);
			}

			return null;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}