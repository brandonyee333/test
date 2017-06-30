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

import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.impl.AssetReceiptLicenseImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Park
 */
public class AssetReceiptLicenseFinderImpl
	extends BasePersistenceImpl<AssetReceiptLicense>
	implements AssetReceiptLicenseFinder {

	public static final String COUNT_BY_OCN_OCP =
		AssetReceiptLicenseFinder.class.getName() + ".countByOCN_OCP";

	public static final String FIND_BY_OCN_OCP =
		AssetReceiptLicenseFinder.class.getName() + ".findByOCN_OCP";

	public int countByOCN_OCP(long ownerClassNameId, long ownerClassPK)
		throws SystemException {

		String sql = CustomSQLUtil.get(COUNT_BY_OCN_OCP);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerClassNameId);
			qPos.add(ownerClassPK);

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

	public List<AssetReceiptLicense> findByOCN_OCP(
			long ownerClassNameId, long ownerClassPK, int start, int end)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_OCN_OCP);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity(
				"OSB_AssetReceiptLicense", AssetReceiptLicenseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerClassNameId);
			qPos.add(ownerClassPK);

			return (List<AssetReceiptLicense>)QueryUtil.list(
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