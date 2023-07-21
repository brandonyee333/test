/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.service.persistence.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikeFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeFinderImpl
	extends AssetEntrySetLikeFinderBaseImpl implements AssetEntrySetLikeFinder {

	public static final String FIND_BY_AESI_NOTC_C =
		AssetEntrySetLikeFinder.class.getName() + ".findByAESI_NotC_C";

	public List<AssetEntrySetLike> findByAESI_NotC_C(
		long assetEntrySetId, long classNameId, long classPK, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_AESI_NOTC_C);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("AssetEntrySetLike", AssetEntrySetLikeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(assetEntrySetId);
			qPos.add(classNameId);
			qPos.add(classPK);

			return (List<AssetEntrySetLike>)QueryUtil.list(
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