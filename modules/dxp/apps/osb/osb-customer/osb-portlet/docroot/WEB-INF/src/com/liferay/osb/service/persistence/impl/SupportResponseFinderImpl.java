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

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.impl.SupportResponseImpl;
import com.liferay.osb.service.persistence.SupportResponseFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Lin Cui
 */
public class SupportResponseFinderImpl
	extends SupportResponseFinderBaseImpl implements SupportResponseFinder {

	public static final String FIND_BY_ACCOUNT_ENTRY =
		SupportResponseFinder.class.getName() + ".findByAccountEntry";

	public SupportResponse fetchByAccountEntry(long accountEntryId)
		throws PortalException {

		String sql = CustomSQLUtil.get(getClass(), FIND_BY_ACCOUNT_ENTRY);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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