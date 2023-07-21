/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.shopping.model.ShoppingCoupon;
import com.liferay.shopping.model.impl.ShoppingCouponImpl;
import com.liferay.shopping.service.persistence.ShoppingCouponFinder;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCouponFinderImpl
	extends ShoppingCouponFinderBaseImpl implements ShoppingCouponFinder {

	public static final String COUNT_BY_G_C_C_A_DT =
		ShoppingCouponFinder.class.getName() + ".countByG_C_C_A_DT";

	public static final String FIND_BY_G_C_C_A_DT =
		ShoppingCouponFinder.class.getName() + ".findByG_C_C_A_DT";

	@Override
	public int countByG_C_C_A_DT(
		long groupId, long companyId, String code, boolean active,
		String discountType, boolean andOperator) {

		code = CustomSQLUtil.keywords(code)[0];

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), COUNT_BY_G_C_C_A_DT);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(companyId);
			qPos.add(code);
			qPos.add(code);
			qPos.add(active);
			qPos.add(discountType);
			qPos.add(discountType);

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

	@Override
	public List<ShoppingCoupon> findByG_C_C_A_DT(
		long groupId, long companyId, String code, boolean active,
		String discountType, boolean andOperator, int start, int end) {

		code = CustomSQLUtil.keywords(code)[0];

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_G_C_C_A_DT);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("ShoppingCoupon", ShoppingCouponImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(companyId);
			qPos.add(code);
			qPos.add(code);
			qPos.add(active);
			qPos.add(discountType);
			qPos.add(discountType);

			return (List<ShoppingCoupon>)QueryUtil.list(
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