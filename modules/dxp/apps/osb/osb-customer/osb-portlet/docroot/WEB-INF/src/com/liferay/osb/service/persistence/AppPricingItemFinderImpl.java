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

import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.impl.AppPricingItemImpl;
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
public class AppPricingItemFinderImpl
	extends BasePersistenceImpl<AppPricingItem>
	implements AppPricingItemFinder {

	public static final String FIND_BY_AVI_ALI =
		AppPricingItemFinder.class.getName() + ".findByAVI_ALI";

	public List<AppPricingItem> findByAVI_ALI(
			long appVersionId, long assetLicenseId)
		throws SystemException {

		String sql = CustomSQLUtil.get(FIND_BY_AVI_ALI);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OSB_AppPricingItem", AppPricingItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(appVersionId);
			qPos.add(assetLicenseId);

			return (List<AppPricingItem>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}