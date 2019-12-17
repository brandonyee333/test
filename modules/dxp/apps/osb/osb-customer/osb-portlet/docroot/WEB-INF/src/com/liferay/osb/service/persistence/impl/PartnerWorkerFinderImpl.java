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

import com.liferay.osb.service.persistence.PartnerWorkerFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Iterator;

/**
 * @author Kyle Bischof
 */
public class PartnerWorkerFinderImpl
	extends PartnerWorkerFinderBaseImpl implements PartnerWorkerFinder {

	public static final String COUNT_PASSPORT_PARTNERS_BY_DOMAIN =
		PartnerWorkerFinder.class.getName() + ".countPassportPartnersByDomain";

	@Override
	public int countPassportPartnersByDomain(String domain) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), COUNT_PASSPORT_PARTNERS_BY_DOMAIN);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("count", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(domain);

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

}